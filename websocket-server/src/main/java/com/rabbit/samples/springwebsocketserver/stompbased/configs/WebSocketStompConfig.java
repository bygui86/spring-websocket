package com.rabbit.samples.springwebsocketserver.stompbased.configs;

import com.rabbit.samples.springwebsocketserver.stompbased.domain.InputMessage;
import com.rabbit.samples.springwebsocketserver.stompbased.interceptors.HttpHandshakeInterceptor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.security.Principal;


/**
 * STOMP stands for Streaming Text Oriented Messaging Protocol.
 * As per wiki, STOMP is a simple text-based protocol, designed for working with message-oriented middleware (MOM).
 * It provides an interoperable wire format that allows STOMP clients to talk with any message broker supporting the protocol.
 * Spring provides default support for it but you are open to choose any other messaging protocol such as RabbitMQ or ActiveMQ.
 *
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 11 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * The advantage of using sockJS here is whenever the websocket connection is disconnected or the websocket connection can not be established,
	 * then the connection will be downgraded to HTTP and the communication between client and server can still continue.
	 */
	@Override
	public void registerStompEndpoints(final StompEndpointRegistry registry) {

		log.debug("Register STOMP endpoints");

		registry.addEndpoint("/messages")
				.setAllowedOrigins("*")
				.addInterceptors(new HttpHandshakeInterceptor())
				.withSockJS()
		;
	}

	/**
	 * This method enables a simple memory-based message broker to carry the messages back to the client on destinations prefixed with "/topic".
	 *
	 * It also designates the "/app" prefix for messages that are bound for @MessageMapping-annotated methods in controller/handler class.
	 * This prefix will be used to define all the message mappings: for example, "/app/message" is the endpoint that
	 * @see com.rabbit.samples.springwebsocketserver.stompbased.handlers.StompWebSocketController#handleInputMessage(InputMessage) method is mapped to handle.
	 */
	@Override
	public void configureMessageBroker(final MessageBrokerRegistry config) {

		log.debug("Configure message broker");

		// memory-based message broker
		config.enableSimpleBroker("/topic");

		// prefix for messages that are bound for @MessageMapping-annotated methods in controller classes
		config.setApplicationDestinationPrefixes("/app");
	}

}
