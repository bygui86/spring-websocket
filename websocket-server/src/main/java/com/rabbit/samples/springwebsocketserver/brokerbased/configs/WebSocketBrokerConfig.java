package com.rabbit.samples.springwebsocketserver.brokerbased.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 11 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Configuration
// @EnableWebSocketMessageBroker
// public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {
public class WebSocketMessageBrokerConfig {

	String websocketEndpoint = "/ws";

	String websocketAppDestinationProxies = "/app";

	String websocketSimpleBroker = "/topic";

	// @Override
	public void registerStompEndpoints(final StompEndpointRegistry registry) {

		log.debug("Register STOMP endpoints");

		registry.addEndpoint(getWebsocketEndpoint());
		// registry.addEndpoint(getWebsocketEndpoint()).withSockJS();
	}

	// @Override
	public void configureMessageBroker(final MessageBrokerRegistry config) {

		log.debug("Configure message broker");

		config.setApplicationDestinationPrefixes(getWebsocketAppDestinationProxies());
		config.enableSimpleBroker(getWebsocketSimpleBroker());
	}

}
