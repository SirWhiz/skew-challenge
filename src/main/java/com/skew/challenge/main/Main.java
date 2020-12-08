package com.skew.challenge.main;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

	private final static String URL = "wss://ws.bitstamp.net";

	public static void main(String[] args) {
		System.out.println("Starting..");

		SocketApp c;
		try {
			c = new SocketApp(new URI(URL));
			c.connect();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		new Scanner(System.in).nextLine();
	}

}
