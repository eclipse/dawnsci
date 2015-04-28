package org.eclipse.dawnsci.data.server.event;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;

class EventServerSocket implements WebSocket {
	
	private HttpServletRequest request;

	EventServerSocket(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void onOpen(Connection connection) {
		
		final String path = request.getParameter("path");
        
        try {
        	int count = 0;
        	while(connection.isOpen() && count<10) {
			    Thread.sleep(1000);
			    connection.sendMessage(path+" changed!");
			    ++count;
        	}
        	connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			connection.close();
		}
		
	}

	@Override
	public void onClose(int closeCode, String message) {
		// TODO Auto-generated method stub
		
	}

}