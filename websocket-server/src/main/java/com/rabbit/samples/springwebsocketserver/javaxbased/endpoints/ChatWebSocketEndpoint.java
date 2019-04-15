package com.rabbit.samples.springwebsocketserver.javaxbased.endpoints;

import com.rabbit.samples.springwebsocketserver.javaxbased.domain.ChatMessage;
import com.rabbit.samples.springwebsocketserver.javaxbased.encoding.ChatMessageDecoder;
import com.rabbit.samples.springwebsocketserver.javaxbased.encoding.ChatMessageEncoder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
@Component
@ServerEndpoint(
		value = "/chat/{username}",
		encoders = ChatMessageEncoder.class,
		decoders = ChatMessageDecoder.class
)
public class ChatWebSocketEndpoint {

	Session session;
	static Set<ChatWebSocketEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
	static HashMap<String, String> users = new HashMap<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {

		this.session = session;
		chatEndpoints.add(this);
		users.put(session.getId(), username);

		ChatMessage message = new ChatMessage();
		message.setFrom(username);
		message.setContent("Connected!");
		broadcast(message);
	}

	@OnMessage
	public void onMessage(Session session, ChatMessage message) throws IOException, EncodeException {

		message.setFrom(users.get(session.getId()));
		broadcast(message);
	}

	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {

		chatEndpoints.remove(this);
		ChatMessage message = new ChatMessage();
		message.setFrom(users.get(session.getId()));
		message.setContent("Disconnected!");
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {

		// Do error handling here
		log.error("Error: {}", throwable.getMessage());
	}

	private static void broadcast(ChatMessage message) throws IOException, EncodeException {

		chatEndpoints.forEach(endpoint -> {
			synchronized (endpoint) {
				try {
					endpoint.session.getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException ex) {
					log.error("Exception broadcasting message: {}", ex.getMessage());
				}
			}
		});
	}

}
