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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asks == null) ? 0 : asks.hashCode());
		result = prime * result + ((bids == null) ? 0 : bids.hashCode());
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}
	
}
