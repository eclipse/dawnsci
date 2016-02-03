package org.eclipse.dawnsci.remotedataset.test.server;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.junit.Test;

/**
 * Not a proper test! This test is used to check events on the server without a RemoteDataset being present.
 * 
 * @author Matthew Gerring
 *
 */
public class EventClientTest {

	@Test
	public void checkClientConnection() throws Exception {
		
        URI uri = URI.create("ws://localhost:8080/event/?path=c%3A/Work/results/Test.txt");

        WebSocketClient client = new WebSocketClient();
        client.start();
        try {
            
        	Session connection = null;
        	try {
            	final EventClientSocket clientSocket = new EventClientSocket();
                 // Attempt Connect
                Future<Session> fut = client.connect(clientSocket, uri);
               
                // Wait for Connect
                connection = fut.get();
                
                // Send a message
                connection.getRemote().sendString("Hello World");
                
                // Close session from the server
                while(connection.isOpen()) {
                	Thread.sleep(100);
                }
            } finally {
                if (connection!=null) connection.close();
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            throw t;
        } finally {
        	client.stop();
        }
    }
}
