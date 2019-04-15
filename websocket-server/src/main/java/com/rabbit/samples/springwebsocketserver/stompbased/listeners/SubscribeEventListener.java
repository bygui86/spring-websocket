package com.rabbit.samples.springwebsocketserver.stompbased.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 14 Apr 2019
 */
@Slf4j
@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

	@Override
	public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		log.info("Session ID: {}", headerAccessor.getSessionAttributes().get("sessionId").toString());
	}

}
