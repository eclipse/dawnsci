package org.eclipse.dawnsci.data.server.test;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.junit.Test;

public class EventClient {

	@Test
	public void checkClientConnection() throws Exception {
		
        URI uri = URI.create("ws://localhost:8080/event/?path=c%3A/Work/results/TomographyDataSet.hdf5");

        WebSocketClient client = new WebSocketClient();
        try {
            try {
                client.start();
                // The socket that receives events
                EventClientSocket    socket  = new EventClientSocket();
                 // Attempt Connect
                Future<Session> fut = client.connect(socket,uri);
                // Wait for Connect
                Session session = fut.get();
                // Send a message
                session.getRemote().sendString("Hello World");
                
                // Close session from the server
                while(session.isOpen()) {
                	Thread.sleep(100);
                }
                session.close();
            } finally {
                client.stop();
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
