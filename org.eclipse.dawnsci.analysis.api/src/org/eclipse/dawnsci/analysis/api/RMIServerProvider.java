/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A simple interface to Java RMI so that objects can be exported using the defaults encoded in this class.
 */
public class RMIServerProvider extends ServerProvider{
	private static final Logger logger = LoggerFactory.getLogger(RMIServerProvider.class);

	private static RMIServerProvider instance = new RMIServerProvider();
	private int port = 0;
	private Registry serverRegistry = null;

	/**
	 * We need to ensure the Remote objects are not garbage collected. Therefore we need to keep a strong reference to
	 * them. If we don't, we can, sometime down the line of a run get:
	 * "java.rmi.NoSuchObjectException: no such object in table" Numerous references on the web about this, for example:
	 * http://stackoverflow.com/questions/645208/java-rmi-nosuchobjectexception-no-such-object-in-table
	 */
	private Map<String, Remote> remotes = new HashMap<String, Remote>();

	/**
	 * Get Instance of provider
	 * 
	 * @return instance
	 */
	public static RMIServerProvider getInstance() {
		return instance;
	}

	private RMIServerProvider() {
	}

	/**
	 * Export the remote object given under the named service.
	 * <p>
	 * This is the method you call on the "server" to make an object available.
	 * 
	 * @param serviceName
	 *            name of the service
	 * @param object
	 *            remote to export
	 * @throws AlreadyBoundException
	 * @throws IOException if automatic port selection fails
	 */
	public synchronized void exportAndRegisterObject(String serviceName, Remote object) throws AlreadyBoundException, IOException {

		if (Boolean.getBoolean("uk.ac.diamond.scisoft.analysis.rmiserverprovider.disable")) {
			throw new RemoteException("Analysis RPC Server disabled with property uk.ac.diamond.scisoft.analysis.rmiserverprovider.disable");
		}
			
		if (port == 0) {
			// Use ServerSocket method for obtaining random(ish) free port
			ServerSocket s = null;
			try {
				s = new ServerSocket(0);
				port = s.getLocalPort();
				firePortListeners(port, true);
				
			} finally {
				if (s != null) {
					s.close();
				}
			}
			if (port < 0) {
				// The idea of this check is based on some code in PyDev which implies that ServerSocket
				// can return -1 when a firewall configuration is causing a problem
				throw new IOException("Unable to obtain free port, is a firewall running?");
			}
		}

		Remote stub = UnicastRemoteObject.exportObject(object, port);

		if (serverRegistry == null) {
			serverRegistry = LocateRegistry.createRegistry(port);
			logger.info("Starting RMI Server on port " + port);
		}
		logger.info("Adding " + serviceName);
		serverRegistry.rebind(serviceName, stub);
		remotes.put(serviceName, object);
	}


	public void unbind(String name) throws AccessException, RemoteException, NotBoundException {
		if (serverRegistry == null) return;
		serverRegistry.unbind(name);
		remotes.remove(name);
	}

	/**
	 * Provide a port number to use. Allows overriding the default, particularly useful if multiple instances of SDA are
	 * run on the same machine.
	 * 
	 * @param rmiPortNumber
	 *            new port number, or 0 to use default port
	 * @throws IllegalStateException
	 *             if already serving on the previous port
	 * @throws IllegalArgumentException
	 *             if the port number is < 0
	 */
	public void setPort(int rmiPortNumber) throws IllegalStateException, IllegalArgumentException {
		if (rmiPortNumber < 0)
			throw new IllegalArgumentException("Port number must be >= 0");
		if (serverRegistry != null)
			throw new IllegalStateException("RMI Server Provider has already used the existing port, "
					+ "setPort must be called before any handlers are added.");

		port = rmiPortNumber;
		firePortListeners(port, false);
	}

	/**
	 * Return Port number in use
	 * 
	 * @return port number
	 */
	public int getPort() {
		return port;
	}

}
