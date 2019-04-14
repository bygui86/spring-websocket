package com.rabbit.samples.springwebsocketserver.springbased.handlers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 12 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
public class EchoBroadcastWebSocketHandler extends AbstractWebSocketHandler {

	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {

		InetSocketAddress clientAddress = session.getRemoteAddress();
		log.info("Accepted connection from: {}:{}", clientAddress.getHostString(), clientAddress.getPort());
		getSessions().add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		log.info("Connection closed by {}:{}", session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
		super.afterConnectionClosed(session, status);
		getSessions().remove(session);
	}

	/**
	 * All messages will be broadcasted to all users.
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		String echoMessage = message.getPayload();
		log.info("Echo TEXT message in broadcasting: {}", echoMessage);

		for(WebSocketSession registeredSession : sessions) {
			registeredSession.sendMessage(new TextMessage(echoMessage));
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		log.error("WebSocket transport error: {}", exception.getMessage());
		session.close(CloseStatus.SERVER_ERROR);
	}

}
