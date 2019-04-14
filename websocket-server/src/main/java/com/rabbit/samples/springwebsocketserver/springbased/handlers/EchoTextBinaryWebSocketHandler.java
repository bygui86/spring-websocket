package com.rabbit.samples.springwebsocketserver.springbased.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 12 Apr 2019
 */
@Slf4j
public class EchoTextBinaryWebSocketHandler extends AbstractWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {

		InetSocketAddress clientAddress = session.getRemoteAddress();
		HttpHeaders handshakeHeaders = session.getHandshakeHeaders();

		log.info("Accepted connection from: {}:{}", clientAddress.getHostString(), clientAddress.getPort());

		log.debug("Client hostname: {}", clientAddress.getHostName());
		log.debug("Client ip: {}", clientAddress.getAddress().getHostAddress());
		log.debug("Client port: {}", clientAddress.getPort());

		log.debug("Session accepted protocols: {}", session.getAcceptedProtocol());
		log.debug("Session binary message size limit: {}", session.getBinaryMessageSizeLimit());
		log.debug("Session id: {}", session.getId());
		log.debug("Session text message size limit: {}", session.getTextMessageSizeLimit());
		log.debug("Session uri: {}", session.getUri().toString());

		log.debug("Handshake header: Accept {}", handshakeHeaders.toString());
		log.debug("Handshake header: User-Agent {}", handshakeHeaders.get("User-Agent").toString());
		log.debug("Handshake header: Sec-WebSocket-Extensions {}", handshakeHeaders.get("Sec-WebSocket-Extensions").toString());
		log.debug("Handshake header: Sec-WebSocket-Key {}", handshakeHeaders.get("Sec-WebSocket-Key").toString());
		log.debug("Handshake header: Sec-WebSocket-Version {}", handshakeHeaders.get("Sec-WebSocket-Version").toString());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		log.info("Connection closed by {}:{}", session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
		super.afterConnectionClosed(session, status);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		String echoMessage = message.getPayload();
		log.info("Echo TEXT message: {}", echoMessage);
		session.sendMessage(new TextMessage(echoMessage));
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {

		final ByteBuffer payload = message.getPayload();
		log.info("Echo BINARY message: {}", payload);
		session.sendMessage(new BinaryMessage(payload));
	}

	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {

		final ByteBuffer payload = message.getPayload();
		log.info("Echo PONG message: {}", payload.asCharBuffer());
		session.sendMessage(new PongMessage(payload));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		log.error("WebSocket transport error: {}", exception.getMessage());
		session.close(CloseStatus.SERVER_ERROR);
	}

}
