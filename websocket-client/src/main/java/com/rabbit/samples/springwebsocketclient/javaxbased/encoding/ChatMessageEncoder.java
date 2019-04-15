package com.rabbit.samples.springwebsocketclient.javaxbased.encoding;

import com.google.gson.Gson;
import com.rabbit.samples.springwebsocketclient.javaxbased.domain.ChatMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 12 Apr 2019
 */
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

	private static Gson gson = new Gson();

	@Override
	public String encode(ChatMessage message) throws EncodeException {
		return gson.toJson(message);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
		// Custom initialization logic
	}

	@Override
	public void destroy() {
		// Close resources
	}

}