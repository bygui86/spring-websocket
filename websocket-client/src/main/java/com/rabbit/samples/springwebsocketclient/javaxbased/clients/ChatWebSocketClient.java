package com.rabbit.samples.springwebsocketclient.javaxbased.clients;

import com.rabbit.samples.springwebsocketclient.javaxbased.encoding.ChatMessageDecoder;
import com.rabbit.samples.springwebsocketclient.javaxbased.encoding.ChatMessageEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.ClientEndpoint;
import javax.websocket.MessageHandler;
import javax.websocket.OnMessage;
import javax.websocket.Session;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 15 Apr 2019
 */
@Slf4j
@ClientEndpoint(
		encoders = ChatMessageEncoder.class,
		decoders = ChatMessageDecoder.class
)
@Component
public class ChatWebSocketClient {

	@OnMessage
	public void processMessageFromServer(String message, Session session) {

		log.info("Chat message received: {}", message);
	}

}
