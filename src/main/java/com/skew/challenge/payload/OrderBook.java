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
	
}
