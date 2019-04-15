package com.rabbit.samples.springwebsocketserver.stompbased.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 14 Apr 2019
 */
@Slf4j
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(
			ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

		log.info("Interceptor BEFORE handshake...");

		if (request instanceof ServletServerHttpRequest) {

			log.info("ServletServerHttpRequest intercepted...");

			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			attributes.put("sessionId", session.getId());
		}
		return true;
	}

	@Override
	public void afterHandshake(
			ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {

		log.info("Interceptor AFTER handshake...");
	}

}
