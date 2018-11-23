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
package org.eclipse.dawnsci.remotedataset.server;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to be a jetty server with a port and is an IApplication
 * 
 * @author Matthew Gerring
 *
 */
abstract class PortServer implements IApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PortServer.class);

	private int port = 8080;
	
	protected Server server;
	
	/**
	 * Start the server, optionally blocking while the server runs.
	 * @param block
	 * @throws Exception
	 */
	public abstract void start(boolean block) throws Exception;

	@Override
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			logger.error("Cannot stop server!", e);
		}
	}

	public void start() throws Exception {
		start(Boolean.getBoolean("org.eclipse.dawnsci.remotedataset.server.blocking"));
	}
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
