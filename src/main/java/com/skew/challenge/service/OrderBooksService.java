package com.skew.challenge.service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.skew.challenge.payload.BitstampResponsePayload;
import com.skew.challenge.payload.Order;
import com.skew.challenge.payload.OrderBook;

import static com.skew.challenge.constants.Constants.BITSTAMP_EXCHANGE;
import static com.skew.challenge.constants.Constants.MAX_ASKS;
import static com.skew.challenge.constants.Constants.MAX_BIDS;
import static com.skew.challenge.constants.Constants.PRICE_IDX;
import static com.skew.challenge.constants.Constants.QUANTITY_IDX;
import static com.skew.challenge.constants.Constants.CSV_FILE_NAME;
import static com.skew.challenge.constants.Constants.BOOKS_LEVELS;
import static com.skew.challenge.constants.Constants.BITSTAMP_BTC_USD;
import static com.skew.challenge.constants.Constants.BITSTAMP_BTC_GBP;
import static com.skew.challenge.constants.Constants.BITSTAMP_ETH_USD;
import static com.skew.challenge.constants.Constants.BITSTAMP_LTC_USD;
import static com.skew.challenge.constants.Constants.NORMALISED_BTC_USD;
import static com.skew.challenge.constants.Constants.NORMALISED_BTC_GBP;
import static com.skew.challenge.constants.Constants.NORMALISED_ETH_USD;
import static com.skew.challenge.constants.Constants.NORMALISED_LTC_USD;

public class OrderBooksService {

	private List<OrderBook> orderBooks;
	
	public OrderBooksService() {
		orderBooks = new ArrayList<OrderBook>();
		emptyFileContent();
	}

	public List<OrderBook> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}
	
	public int getAmountOfBooks() {
		return orderBooks.size();
	}
	
	public void parseOrderBook(BitstampResponsePayload response) {
		OrderBook book = new OrderBook();
		book.setExchange(BITSTAMP_EXCHANGE);
		book.setSymbol(getSymbol(response));
		
		for (String[] bid : response.getBids()) {
			if(book.getBids().size() == MAX_BIDS) {
				break;
			}
			
			Order newBid = new Order();
			newBid.setPrice(Double.parseDouble(bid[PRICE_IDX]));
			newBid.setQuantity(Double.parseDouble(bid[QUANTITY_IDX]));
			book.getBids().add(newBid);
		}
		
		for (String[] ask : response.getAsks()) {
			if(book.getAsks().size() == MAX_ASKS) {
				break;
			}
			
			Order newAsk = new Order();
			newAsk.setPrice(Double.parseDouble(ask[PRICE_IDX]));
			newAsk.setQuantity(Double.parseDouble(ask[QUANTITY_IDX]));
			book.getAsks().add(newAsk);
		}

		String orderBookAsJson = "";
		try {
			orderBookAsJson = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(book);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(bookIsValid(book) && sameBookIsNotAlreadyPrensent(book) && orderBooks.size() < BOOKS_LEVELS) {
			System.out.println(orderBookAsJson);
			orderBooks.add(book);
			new FileService(book, response.getTimestamp()).run();
		}
	}
	
	private String getSymbol(BitstampResponsePayload response) {
		String currencyPair = response.getChannel().split("_")[2];
		
		switch (currencyPair) {
			case BITSTAMP_BTC_USD:
				return NORMALISED_BTC_USD;
			case BITSTAMP_BTC_GBP:
				return NORMALISED_BTC_GBP;
			case BITSTAMP_ETH_USD:
				return NORMALISED_ETH_USD;
			case BITSTAMP_LTC_USD:
				return NORMALISED_LTC_USD;
			default:
				return "";
		}
	}
	
	private void emptyFileContent() {
		try {
			new PrintWriter(CSV_FILE_NAME).close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private boolean bookIsValid(OrderBook newBook) {
		return !newBook.getExchange().isEmpty() && !newBook.getSymbol().isEmpty()
				&& (newBook.getAsks() != null && !newBook.getAsks().isEmpty())
				&& (newBook.getBids() != null && !newBook.getBids().isEmpty());
	}
	
	private boolean sameBookIsNotAlreadyPrensent(OrderBook newBook) {
		for (OrderBook orderBook : orderBooks) {
			if(orderBook.toString().equals(newBook.toString())) {
				return false;
			}
		}
		return true;
	}
	
}
