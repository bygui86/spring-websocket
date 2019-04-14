package com.rabbit.samples.springwebsocketserver.brokerbased.handlers;

import com.rabbit.samples.springwebsocketserver.brokerbased.domain.OutputMessage;
import com.rabbit.samples.springwebsocketserver.brokerbased.domain.InputMessage;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 11 Apr 2019
 */
@Slf4j
// @Component
public class WebSocketMessageBrokerHandler {

	// @MessageMapping("/chat") // The root is configured by the Application Destination Proxy
	// @SendTo("/topic/messages")
	public OutputMessage handleInputMessage(InputMessage inputMessage) {

		log.info("Message received: {}", inputMessage);

		return OutputMessage
				.builder()
				.from(inputMessage.getFrom())
				.content(inputMessage.getContent())
				.time(new SimpleDateFormat("HH:mm").format(new Date()))
				.build();
	}

}
