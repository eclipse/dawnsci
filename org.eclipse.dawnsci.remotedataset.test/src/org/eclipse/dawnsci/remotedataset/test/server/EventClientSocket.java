package org.eclipse.dawnsci.remotedataset.test.server;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;


public class EventClientSocket extends WebSocketAdapter {

	@Override
	public void onWebSocketText(String message) {
	    System.out.println("Message from server "+message);
    }
	
}