package com.skew.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.skew.challenge.payload.BitstampResponsePayload;
import com.skew.challenge.payload.Order;
import com.skew.challenge.payload.OrderBook;

public class OrderBooksService {

	private final String BITSTAMP_EXCHANGE = "Bitstamp";
	private final String BTC_USD = "BTC/USD";
	private final Integer PRICE_IDX = 0;
	private final Integer QUANTITY_IDX = 1;
	private List<OrderBook> orderBooks;
	
	public OrderBooksService() {
		orderBooks = new ArrayList<OrderBook>();
	}

	public List<OrderBook> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}
	
	public void parseOrderBook(BitstampResponsePayload response) {
		OrderBook book = new OrderBook();
		book.setExchange(BITSTAMP_EXCHANGE);
		book.setSymbol(BTC_USD);
		
		for (String[] bid : response.getBids()) {
			Order newBid = new Order();
			newBid.setPrice(Double.parseDouble(bid[PRICE_IDX]));
			newBid.setQuantity(Double.parseDouble(bid[QUANTITY_IDX]));
			
			book.getBids().add(newBid);
		}
		
		for (String[] ask : response.getAsks()) {
			Order newAsk = new Order();
			newAsk.setPrice(Double.parseDouble(ask[PRICE_IDX]));
			newAsk.setQuantity(Double.parseDouble(ask[QUANTITY_IDX]));
			
			book.getAsks().add(newAsk);
		}
		
		System.out.println("Parsed book: " + book);
		orderBooks.add(book);
	}
	
}
