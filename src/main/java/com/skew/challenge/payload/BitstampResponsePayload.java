package com.skew.challenge.payload;

import java.util.ArrayList;
import java.util.List;

public class BitstampResponsePayload {

	private OrderBookResponsePayload data;
	private String channel;
	private String event;
	
	public BitstampResponsePayload() {
		super();
		data = new OrderBookResponsePayload();
	}
	
	public OrderBookResponsePayload getData() {
		return data;
	}

	public void setData(OrderBookResponsePayload data) {
		this.data = data;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getTimestamp() {
		return data.getTimestamp();
	}

	public void setTimestamp(String timestamp) {
		data.setTimestamp(timestamp);
	}

	public String getMicrotimestamp() {
		return data.getMicrotimestamp();
	}

	public void setMicrotimestamp(String microtimestamp) {
		data.setMicrotimestamp(microtimestamp);
	}

	public List<String[]> getBids() {
		return data.getBids();
	}

	public void setBids(List<String[]> bids) {
		data.setBids(bids);
	}

	public List<String[]> getAsks() {
		return data.getAsks();
	}
	
	public void setAsks(List<String[]> asks) {
		data.setAsks(asks);
	}
	
	private class OrderBookResponsePayload {
		
		private String timestamp;
		private String microtimestamp;
		List<String[]> bids;
		List<String[]> asks;
	
		public OrderBookResponsePayload() {
			super();
			bids = new ArrayList<String[]>();
			asks = new ArrayList<String[]>();
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getMicrotimestamp() {
			return microtimestamp;
		}

		public void setMicrotimestamp(String microtimestamp) {
			this.microtimestamp = microtimestamp;
		}

		public List<String[]> getBids() {
			return bids;
		}

		public void setBids(List<String[]> bids) {
			this.bids = bids;
		}

		public List<String[]> getAsks() {
			return asks;
		}

		public void setAsks(List<String[]> asks) {
			this.asks = asks;
		}
	}
	
}
