package com.rabbit.samples.springwebsocketserver.configs;

import com.rabbit.samples.springwebsocketserver.endpoints.ReverseWebSocketEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 12 Apr 2019
 */
@Slf4j
@Configuration
public class WebSocketEndpointConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {

		log.debug("Configure ServerEndpointExporter");

		return new ServerEndpointExporter();
	}

	@Bean
	public ReverseWebSocketEndpoint reverseWebSocketHandler() {

		log.debug("Create ReverseWebSocketHandler");

		return new ReverseWebSocketEndpoint();
	}

}
