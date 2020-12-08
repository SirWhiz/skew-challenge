package com.skew.challenge.main;

import org.java_websocket.client.WebSocketClient;
import java.net.URI;
import java.util.Map;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skew.challenge.payload.SubscribePayload;

public class SocketApp extends WebSocketClient {

	private final String BTS_SUBSCRIBE = "bts:subscribe";
	private final String ORDER_BOOKS_CHANNEL = "order_book_btcusd";

	public SocketApp(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	public SocketApp(URI serverURI) {
		super(serverURI);
	}

	public SocketApp(URI serverUri, Map<String, String> httpHeaders) {
		super(serverUri, httpHeaders);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		send(generateSubscribeJson());
		System.out.println("opened connection");
	}

	@Override
	public void onMessage(String message) {
		System.out.println("received: " + message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println(
				"Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
	}

	@Override
	public void onError(Exception ex) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}

	private String generateSubscribeJson() {
		SubscribePayload subPayload = new SubscribePayload();
		subPayload.setEvent(BTS_SUBSCRIBE);
		subPayload.setChannel(ORDER_BOOKS_CHANNEL);

		try {
			return new ObjectMapper().writeValueAsString(subPayload);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}