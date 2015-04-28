package org.eclipse.dawnsci.data.server.test;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.junit.Test;

public class EventClient {

	@Test
	public void checkClientConnection() throws Exception {
		
        URI uri = URI.create("ws://localhost:8080/event/?path=c%3A/Work/results/TomographyDataSet.hdf5");

        WebSocketClientFactory factory = new WebSocketClientFactory();
        factory.start();
        WebSocketClient        client = new WebSocketClient(factory);
        try {
            
        	Connection connection = null;
        	try {
            	final EventClientSocket clientSocket = new EventClientSocket();
                 // Attempt Connect
                Future<Connection> fut = client.open(uri, clientSocket);
               
                // Wait for Connect
                connection = fut.get();
                
                // Send a message
                connection.sendMessage("Hello World");
                
                // Close session from the server
                while(connection.isOpen()) {
                	Thread.sleep(100);
                }
            } finally {
                if (connection!=null) connection.close();
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
