package com.skew.challenge.main;

import java.net.URI;
import java.net.URISyntaxException;

import static com.skew.challenge.constants.Constants.BITSTAMP_URL;

public class Main {

	public static void main(String[] args) {
		SocketClient c;
		try {
			c = new SocketClient(new URI(BITSTAMP_URL));
			c.connect();
		} catch (URISyntaxException e) {
			System.out.println("There was an error trying to connect to " + BITSTAMP_URL);
		}
	}

}
