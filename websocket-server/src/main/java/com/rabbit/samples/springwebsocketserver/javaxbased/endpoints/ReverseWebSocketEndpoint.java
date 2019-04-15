package com.rabbit.samples.springwebsocketserver.javaxbased.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;


@Slf4j
@ServerEndpoint("/reverse")
@Component
public class ReverseWebSocketEndpoint {

	@OnOpen
	public void onOpen(Session session) throws IOException {

		final URI uri = session.getRequestURI();
		log.info("Accepted connection from: {}", uri);
	}

	@OnMessage
	public void handleMessage(Session session, String message) throws IOException {

		String reverse = new StringBuilder(message).reverse().toString();
		log.info("Message: {}, Reverse: {}", reverse);
		session.getBasicRemote().sendText(reverse);
	}

	@OnClose
	public void onClose(Session session) throws IOException {

		final URI uri = session.getRequestURI();
		log.info("Connection closed by: {}", uri);
	}

	@OnError
	public void onError(Session session, Throwable throwable) throws IOException {

		log.error("WebSocket transport error: {}", throwable.getMessage());
		session.close(
				new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage())
		);
	}

}
