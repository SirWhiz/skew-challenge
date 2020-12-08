package com.skew.challenge.main;

import org.java_websocket.client.WebSocketClient;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class SocketApp extends WebSocketClient {

	  public SocketApp(URI serverUri, Draft draft) {
	    super(serverUri, draft);
	  }

	  public SocketApp(URI serverURI) {
	    super(serverURI);
	  }

	  public SocketApp(URI serverUri, Map<String, String> httpHeaders) {
	    super(serverUri, httpHeaders);
	  }

	  @Override
	  public void onOpen(ServerHandshake handshakedata) {
	    send("Hello, it is me. Mario :)");
	    System.out.println("opened connection");
	  }

	  @Override
	  public void onMessage(String message) {
	    System.out.println("received: " + message);
	  }

	  @Override
	  public void onClose(int code, String reason, boolean remote) {
	    // The codecodes are documented in class org.java_websocket.framing.CloseFrame
	    System.out.println(
	        "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
            + reason);
	  }

	  @Override
	  public void onError(Exception ex) {
	    ex.printStackTrace();
	    // if the error is fatal then onClose will be called additionally
	  }
	
}