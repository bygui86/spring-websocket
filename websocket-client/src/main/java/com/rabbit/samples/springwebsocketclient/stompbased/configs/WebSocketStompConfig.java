package com.rabbit.samples.springwebsocketclient.stompbased.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 15 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
@Configuration
public class WebSocketStompConfig {

	@Value("${websocket.server.stomp.url}")
	String websocketServerUrl;

	@Bean
	public MappingJackson2MessageConverter messageConverter() {

		log.debug("Create message converter...");

		return new MappingJackson2MessageConverter();
	}

	@Bean
	public WebSocketStompClient webSocketStompClient(final MappingJackson2MessageConverter messageConverter) {

		log.debug("Create WebSocketStompClient...");

		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport( new StandardWebSocketClient()) );
		WebSocketClient client = new SockJsClient(transports);

		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(messageConverter);
		// final TaskScheduler taskScheduler;
		// stompClient.setTaskScheduler(taskScheduler); // for heartbeats

		return stompClient;
	}

	@Bean
	public StompSession stompSession(final WebSocketStompClient stompClient, final StompSessionHandlerAdapter sessionHandler) throws InterruptedException, ExecutionException {

		log.debug("Create StompSession...");

		final ListenableFuture<StompSession> futureSession = stompClient.connect(getWebsocketServerUrl(), sessionHandler);
		while(!futureSession.isDone()) {
			log.debug("Waiting STOMP connection to return StompSession...");
			Thread.sleep(1000);
		}
		return futureSession.get();
	}

}
