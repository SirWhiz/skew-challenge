package com.skew.challenge.payload;

public class SubscribePayload {

	private String event;
	private DataPayload data;

	public SubscribePayload() {
		super();
		data = new DataPayload();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public DataPayload getData() {
		return data;
	}

	public void setData(DataPayload data) {
		this.data = data;
	}

	public void setChannel(String channel) {
		data.setChannel(channel);
	}

	public String getChannel() {
		return data.getChannel();
	}

	private class DataPayload {
		private String channel;

		public DataPayload() {
			super();
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}
	}

}
