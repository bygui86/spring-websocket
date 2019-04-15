package com.rabbit.samples.springwebsocketclient.stompbased.handlers;

import com.rabbit.samples.springwebsocketclient.stompbased.domain.InputMessage;
import com.rabbit.samples.springwebsocketclient.stompbased.domain.OutputMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 15 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Component
public class StompWebSocketSessionHandler extends StompSessionHandlerAdapter {

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

		log.info("New STOMP session established: {}", session.getSessionId());
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {

		return OutputMessage.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {

		OutputMessage msg = (OutputMessage) payload;
		log.info("Received message '{}' from '{}' at '{}'", msg.getContent(), msg.getFrom(), msg.getTime());
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {

		log.error("Got an exception: {}", exception.getMessage());
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {

		log.error("Got a transport error: {}", exception.getMessage());
	}

}
