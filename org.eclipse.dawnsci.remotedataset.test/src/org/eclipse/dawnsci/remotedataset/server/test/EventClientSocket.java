package org.eclipse.dawnsci.remotedataset.server.test;

import org.eclipse.jetty.websocket.WebSocket;


public class EventClientSocket implements WebSocket, WebSocket.OnTextMessage {

	@Override
	public void onOpen(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose(int closeCode, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String data) {
		System.out.println("Message from server "+data);
	}
	
}