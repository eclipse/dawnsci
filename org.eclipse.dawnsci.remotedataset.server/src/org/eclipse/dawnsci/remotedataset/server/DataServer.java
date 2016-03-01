/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.server;

import java.util.HashMap;
import java.util.Map;


import org.eclipse.dawnsci.remotedataset.server.event.EventServlet;
import org.eclipse.dawnsci.remotedataset.server.event.FileMonitorSocket;
import org.eclipse.dawnsci.remotedataset.server.info.InfoServlet;
import org.eclipse.dawnsci.remotedataset.server.slice.SliceServlet;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * This object is designed to start the server and 
 * can be used as a spring object for instance.
 *  
 * @author Matthew Gerring
 *
 */
public class DataServer extends PortServer {
	
	
	public DataServer() {
         	
	}
	
	/**
	 * Method replaces main(...) when running things with OSGi
	 * 
	 * Not called on GDA server will will probably start the server by calling
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {

		final Map      args          = context.getArguments();
		final String[] configuration = (String[])args.get("application.args");
        
		Map<String, String> conf = new HashMap<String, String>(7);
		for (int i = 0; i < configuration.length; i++) {
			final String pkey = configuration[i];
			if (pkey.startsWith("-")) {
				conf.put(pkey.substring(1), configuration[i+1]);
			}
		}
      
    	if (conf.containsKey("port")) {
    		setPort(Integer.parseInt(conf.get("port").toString()));
    	} 
    	start(); // blocking
    	
    	return server;// We are done with this application now.
	}

	
	public void start(boolean block) throws Exception {
		
		this.server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(getPort());
		connector.setReuseAddress(true);
		server.addConnector(connector);   
		
		// We enable sessions on the server so that 
		// we can cache LoaderFactories to a given session.
		// The loader factory therefore needs a non-global 
		// data soft reference cache.
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
      
        // Make individual servlets
        // Slicing (large data in binary http)
		ServletHolder holderSlice = new ServletHolder("slice", SliceServlet.class);
		context.addServlet(holderSlice, "/slice/*");
		
		// Doing events, like data changing shape.
		// FIXME Should not be needed
		WebSocketHandler wsHandler = new WebSocketHandler() {
			@Override
			public void configure(WebSocketServletFactory factory) {
				factory.register(FileMonitorSocket.class);
			}
		};
		context.setHandler(wsHandler);
		// FIXME End should not be needed.		
		ServletHolder holderInfo = new ServletHolder("info", InfoServlet.class);
		context.addServlet(holderInfo, "/info/*");
     
		// Events json objects to notifyu of problems.
		ServletHolder holderEvent = new ServletHolder("event", EventServlet.class);
		context.addServlet(holderEvent, "/event/*");
		
	    server.start();
	    if (block) server.join();

	}

	
	/**
	 * 
	 * 	
	 * 
	    JETTY 9 
		
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(getPort());
		connector.setReuseAddress(true);
		server.addConnector(connector);   	

		// We enable sessions on the server so that 
		// we can cache LoaderFactories to a given session.
		// The loader factory therefore needs a non-global 
		// data soft reference cache.
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		// Doing slicing
		ServletHolder holderSlice = new ServletHolder("slice", SliceServlet.class);
		context.addServlet(holderSlice, "/slice/*");

		// Doing events, like data changing shape.
		// FIXME Should not be needed
		WebSocketHandler wsHandler = new WebSocketHandler() {
			@Override
			public void configure(WebSocketServletFactory factory) {
				factory.register(EventServerSocket.class);
			}
		};
		context.setHandler(wsHandler);
		// FIXME End should not be needed.

		ServletHolder holderEvents = new ServletHolder("event", EventServlet.class);
		context.addServlet(holderEvents, "/event/*");

		server.start();
		if (block) server.join();

	}

	 */
}
