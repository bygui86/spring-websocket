package com.rabbit.samples.springwebsocketclient.javaxbased.configs;

import com.rabbit.samples.springwebsocketclient.javaxbased.clients.ChatWebSocketClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 15 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
@Configuration
public class WebSocketEndpointConfig {

	@Value("${websocket.server.chat.url}")
	String websocketServerUrl;

	@Bean
	public Session websocketClientEndpoint(final ChatWebSocketClient webSocketClient) throws URISyntaxException, IOException, DeploymentException {

		log.debug("Create WebSocket session...");

		WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
		Session session = webSocketContainer.connectToServer(webSocketClient, new URI(getWebsocketServerUrl()));

		log.info("Session opened '{}' with id '{}'", session.isOpen(), session.getId());

		return session;
	}

}
