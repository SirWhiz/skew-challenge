package com.skew.challenge.payload;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {

	private String exchange;
	private String symbol;
	private List<Order> bids;
	private List<Order> asks;

	public OrderBook() {
		super();
		bids = new ArrayList<Order>();
		asks = new ArrayList<Order>();
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<Order> getBids() {
		return bids;
	}

	public void setBids(List<Order> bids) {
		this.bids = bids;
	}

	public List<Order> getAsks() {
		return asks;
	}

	public void setAsks(List<Order> asks) {
		this.asks = asks;
	}
	
	@Override
	public String toString() {
		return "OrderBook [exchange=" + exchange + ", symbol=" + symbol + ", bids=" + bids + ", asks=" + asks + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof OrderBook == false) {
			return false;
		}
		
		OrderBook book = (OrderBook) o;
		return this.getExchange().equals(book.getExchange()) && this.getSymbol().equals(book.getSymbol())
				&& ordersAreEqual(this.getAsks(), book.getAsks()) && ordersAreEqual(this.getBids(), book.getBids());
	}
	
	private boolean ordersAreEqual(List<Order> orders, List<Order> newOrders) {
		if(orders.size() != newOrders.size()) {
			return false;
		}
		
		for (int i = 0; i < orders.size(); i++) {
			if(!orders.get(i).getPrice().equals(newOrders.get(i).getPrice())
					|| !orders.get(i).getQuantity().equals(newOrders.get(i).getQuantity())) {
				return false;
			}
		}
		return true;
	}
	
}
