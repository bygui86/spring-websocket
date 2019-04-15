package com.rabbit.samples.springwebsocketclient.rest;

import com.rabbit.samples.springwebsocketclient.stompbased.domain.InputMessage;
import com.rabbit.samples.springwebsocketclient.stompbased.handlers.StompWebSocketSessionHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.rmi.activation.ActivationGroup_Stub;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 15 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@RestController
@RequestMapping("/websocket")
public class WebSocketClientRestController {

	static final String TOPIC = "/topic/replies";

	final StompSession stompSession;

	final StompWebSocketSessionHandler stompWebSocketSessionHandler;

	StompSession.Subscription subscription;

	@PreDestroy
	public void preDestroy(){

		unsubscribe();

		getStompSession().disconnect();
	}

	@PostMapping("/send")
	public void sendMessage(@RequestBody final InputMessage inputMessage) {

		if (getSubscription() != null) {
			log.info("Send message {} to subscription {}...", inputMessage, TOPIC);
			final StompSession.Receiptable receipt = getStompSession().send("/app/message", inputMessage);
			log.info("Message {} sent with receipt id {}", inputMessage, receipt.getReceiptId());

		} else {
			log.error("No active subscription!");
		}
	}

	@PostMapping("/subscribe")
	public void subscribe() {

		log.info("Subscribe to {}...", TOPIC);

		setSubscription(
				getStompSession().subscribe(TOPIC, getStompWebSocketSessionHandler())
		);

		log.info("New subscription id: {}", getSubscription().getSubscriptionId());
	}

	@DeleteMapping("/unsubscribe")
	public void unsubscribe() {

		if (getSubscription() != null) {
			log.info("Unsubscribe from {}...", TOPIC);
			getSubscription().unsubscribe();
			setSubscription(null);
			log.info("Subscription to {} closed", TOPIC);

		} else {
			log.error("No active subscription!");
		}
	}

}
