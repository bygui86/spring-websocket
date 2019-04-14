package com.rabbit.samples.springwebsocketserver.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
@Slf4j
public class EchoTextWebSocketHandler extends TextWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		log.debug("Opened new session in instance " + this);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		String echoMessage = message.getPayload();
		log.info("Echo message: {}", echoMessage);
		session.sendMessage(new TextMessage(echoMessage));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		log.error("WebSocket transport error: {}", exception.getMessage());
		session.close(CloseStatus.SERVER_ERROR);
	}

}
