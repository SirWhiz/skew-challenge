package com.skew.challenge.main;

import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Scanner;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skew.challenge.payload.BitstampResponsePayload;
import com.skew.challenge.payload.SubscribePayload;
import com.skew.challenge.service.OrderBooksService;

import static com.skew.challenge.constants.Constants.BOOKS_LEVELS;
import static com.skew.challenge.constants.Constants.BTS_SUBSCRIBE;
import static com.skew.challenge.constants.Constants.ORDER_BOOKS_CHANNEL;
import static com.skew.challenge.constants.Constants.BITSTAMP_BTC_USD;
import static com.skew.challenge.constants.Constants.BITSTAMP_BTC_GBP;
import static com.skew.challenge.constants.Constants.BITSTAMP_ETH_USD;
import static com.skew.challenge.constants.Constants.BITSTAMP_LTC_USD;
import static com.skew.challenge.constants.Constants.CSV_FILE_NAME;

public class SocketClient extends WebSocketClient {

	private OrderBooksService booksService;
	private String selectedCurrencyPair;

	public SocketClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
		selectedCurrencyPair = "";
	}

	public SocketClient(URI serverURI) {
		super(serverURI);
		selectedCurrencyPair = "";
		showCurrencyMenu();
	}

	public SocketClient(URI serverUri, Map<String, String> httpHeaders) {
		super(serverUri, httpHeaders);
		selectedCurrencyPair = "";
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		send(generateSubscribeJson());
		booksService = new OrderBooksService();
	}

	@Override
	public void onMessage(String message) {
		try {
			if(booksService.getAmountOfBooks() == BOOKS_LEVELS) {
				this.close();
			}
			
			BitstampResponsePayload data = new ObjectMapper().readValue(message, BitstampResponsePayload.class);
			booksService.parseOrderBook(data);
		} catch (JsonParseException e) {
			System.out.println("Error trying to parse JSON response from Bitstamp");
		} catch (JsonMappingException e) {
			System.out.println("Error trying to map response from Bitstamp");
		} catch (IOException e) {
			System.out.println("Error trying to read information from Bitstamp's response");
		}
		
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("Order book information was saved to " + CSV_FILE_NAME);
	}

	@Override
	public void onError(Exception ex) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}
	
	private void showCurrencyMenu() {
		System.out.println("--- Select a currecy pair to retrieve its order books ---");
		System.out.println("1 - BTC/USD (Default)");
		System.out.println("2 - BTC/GBP");
		System.out.println("3 - ETH/USD");
		System.out.println("4 - LTC/USD");
		
		Scanner reader = new Scanner(System.in);
		String option = reader.next();
		reader.close();
		
		switch(option) {
			case "1":
				selectedCurrencyPair = BITSTAMP_BTC_USD;
				break;
			case "2":
				selectedCurrencyPair = BITSTAMP_BTC_GBP;
				break;
			case "3":
				selectedCurrencyPair = BITSTAMP_ETH_USD;
				break;
			case "4":
				selectedCurrencyPair = BITSTAMP_LTC_USD;
				break;
			default:
				selectedCurrencyPair = BITSTAMP_BTC_USD;
				break;
		}
	}

	private String generateSubscribeJson() {
		SubscribePayload subPayload = new SubscribePayload();
		subPayload.setEvent(BTS_SUBSCRIBE);
		subPayload.setChannel(ORDER_BOOKS_CHANNEL + selectedCurrencyPair);

		try {
			return new ObjectMapper().writeValueAsString(subPayload);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}