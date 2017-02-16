/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
