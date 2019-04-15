package com.rabbit.samples.springwebsocketclient.javaxbased.encoding;

import com.google.gson.Gson;
import com.rabbit.samples.springwebsocketclient.javaxbased.domain.ChatMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 12 Apr 2019
 */
public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	private static Gson gson = new Gson();

	@Override
	public ChatMessage decode(String s) throws DecodeException {
		return gson.fromJson(s, ChatMessage.class);
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
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