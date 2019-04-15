package com.rabbit.samples.springwebsocketserver.stompbased.handlers;

import com.rabbit.samples.springwebsocketserver.stompbased.domain.OutputMessage;
import com.rabbit.samples.springwebsocketserver.stompbased.domain.InputMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 11 Apr 2019
 */
@Slf4j
// @FieldDefaults(level = AccessLevel.PRIVATE)
// @Getter(AccessLevel.PROTECTED)
// @AllArgsConstructor
@Controller
public class StompWebSocketController {

	// SimpMessageSendingOperations simpMessageSendingOperations;

	/**
	 * @MessageMapping root is configured by the Application Destination Proxy.
	 *
	 * @SendTo is used to broadcast the message to a queue.
	 * @SendToUser is used to broadcast the message to a particular user.
	 *
	 * @see com.rabbit.samples.springwebsocketserver.stompbased.configs.WebSocketStompConfig#configureMessageBroker(MessageBrokerRegistry)
	 */
	@MessageMapping("/message")
	@SendTo("/topic/replies") // same as org.springframework.messaging.simp.SimpMessageSendingOperations.convertAndSend(..)
	// @SendToUser("/topic/replies") // same as org.springframework.messaging.simp.SimpMessageSendingOperations.convertAndSendToUser(..)
	public OutputMessage handleInputMessage(InputMessage inputMessage) {

		log.info("Message received: {}", inputMessage);

		OutputMessage outputMessage = OutputMessage
				.builder()
				.from(inputMessage.getFrom())
				.content(inputMessage.getContent())
				.time(new SimpleDateFormat("HH:mm").format(new Date()))
				.build();

		// Instead of @SendTo
		// getSimpMessageSendingOperations().convertAndSend("/topic/reply", outputMessage);
		// Instead of @SendToUser
		// getSimpMessageSendingOperations().convertAndSendToUser(inputMessage.getFrom(), "/topic/reply", outputMessage);

		return outputMessage;
	}

	@MessageExceptionHandler // throw any exceptions caused by STOMP to the end user on the /queue/errors destination
	@SendToUser("/topic/errors")
	public String handleException(Throwable exception) {

		log.error("Message exception handling, error: {}", exception.getMessage());
		return exception.getMessage();
	}

}
