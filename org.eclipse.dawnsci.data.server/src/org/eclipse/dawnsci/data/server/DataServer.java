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
package org.eclipse.dawnsci.data.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This object is designed to start the server and 
 * can be used as a spring object for instance.
 *  
 * @author fcp94556
 *
 */
public class DataServer implements IApplication{
	
	private static final Logger logger = LoggerFactory.getLogger(DataServer.class);
	
	private int port = 8080;
	
	private Server server;
	
	public DataServer() {
         	
	}
	
	/**
	 * Method replaces main(...) when running things with OSGi
	 * 
	 * Not called on GDA server will will probably start the server by calling
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {

		BasicConfigurator.configure();

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

	@Override
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			logger.error("Cannot stop server!", e);
		}
	}

	public void start() throws Exception {
		start(true);
	}
	
	public void start(boolean block) throws Exception {
		
		this.server = new Server(port);
        
    	// We enable sessions on the server so that 
		// we can cache LoaderFactories to a given session.
		// The loader factory therefore needs a non-global 
		// data soft reference cache.
        SessionHandler sessionHandler = new SessionHandler();
        SessionManager sessionManager = new HashSessionManager();
        sessionManager.setSessionIdManager(new HashSessionIdManager());
        sessionHandler.setSessionManager(sessionManager);
        sessionHandler.setHandler(new DataHandler());
     
        server.setHandler(sessionHandler);    

	    server.start();
	    if (block) server.join();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
