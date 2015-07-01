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
 *    Simon Berriman
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A wrapper on Java RMI, so that objects can be remotely exported easily.
 */
public class RMIServerProvider extends ServerProvider {

	/** TCP port value indicating 'unset' or 'auto' */
	public static final int UNSET_PORT = 0;
	/** Minimum valid value for a TCP port */
	public static final int MIN_VALID_PORT = 1;
	/** Maximum valid value for a TCP port */
	public static final int MAX_VALID_PORT = 65535;

	/** SLF4J Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(RMIServerProvider.class);
	/** Singleton instance */
	private static final RMIServerProvider INSTANCE = new RMIServerProvider();

	private int port = UNSET_PORT;
	private Registry serverRegistry = null;

	/**
	 * We need to ensure the Remote objects are not garbage collected. Therefore we need to keep a strong reference to
	 * them. If we don't, we can, sometime down the line of a run get:
	 * "java.rmi.NoSuchObjectException: no such object in table" Numerous references on the web about this, for example:
	 * http://stackoverflow.com/questions/645208/java-rmi-nosuchobjectexception-no-such-object-in-table
	 */
	private Map<String, Remote> remotes = new HashMap<String, Remote>();
	private Queue<String> waitingRemotes = null;

	/**
	 * Get Instance of provider
	 *
	 * @return instance
	 */
	public static RMIServerProvider getInstance() {
		return INSTANCE;
	}

	/** Singleton constructor */
	private RMIServerProvider() {
		// No operation
	}

	/**
	 * Export the remote object under a given name for access as a service. This is the method you call on the server
	 * side to make an object available. The registry connection must have been initialised already with a call to
	 * {@link #initServer()} for the bound object to be immediately available. Otherwise, the requested binding will be
	 * queued until the registry connection is available.
	 *
	 * @param serviceName
	 *            name of the service
	 * @param object
	 *            object to export
	 * @throws AccessException
	 *             if the registry is available and local, and security restrictions prevent binding
	 * @throws RemoteException
	 *             if RMI is disabled entirely, or if object binding fails for a non-security related reason
	 */
	public synchronized void exportAndRegisterObject(String serviceName, Remote object) throws AccessException,
			RemoteException {

		disabledCheck();

		remotes.put(serviceName, object);

		if (serverRegistry == null) {
			LOGGER.info("Registry not initialised. Queuing " + serviceName);

			if (waitingRemotes == null) {
				waitingRemotes = new LinkedList<String>();
			}

			waitingRemotes.add(serviceName);

		} else {
			bindExportedObject(serviceName, object);
		}
	}

	/**
	 * Unbinds a service, if it is bound.
	 *
	 * @param name
	 *            Service to unbind
	 * @throws AccessException
	 *             if the registry is local, and security restrictions prevent unbinding
	 * @throws RemoteException
	 *             if unbind fails for a non-security related reason
	 */
	public synchronized void unbind(String name) throws AccessException, RemoteException {
		if (serverRegistry != null) {
			try {
				serverRegistry.unbind(name);
			} catch (NotBoundException e) {
				LOGGER.warn("Service '" + name + "' was not bound to unbind - ignoring");
			}
		}

		if (waitingRemotes != null) {
			waitingRemotes.remove(name);
		}
		remotes.remove(name);
	}

	/**
	 * Creates or connects to an existing RMI registry using previously set details. This includes a limited amount of
	 * sanity checking. This method <b>must</b> be called from the application before RMI objects are attempted to be
	 * used. If any objects have been queued for exporting, they will be exported by this method assuming a valid
	 * registry is available to do so.
	 *
	 * @throws IllegalStateException
	 *             if this method is attempted to be called more than once, or if a sanity check (i.e. invalid port
	 *             number) fails.
	 * @throws IOException
	 *             if RMI is disabled by a system property, or the remote object store cannot be unmarshalled
	 */
	public synchronized void initServer() throws IllegalStateException, IOException {
		disabledCheck();

		if (serverRegistry != null) {
			throw new IllegalStateException("RMI Server Provider has already been initialised");
		}

		if (port == UNSET_PORT) {
			throw new IllegalStateException("Port has not been set.");
		} else if (port <= MIN_VALID_PORT || port >= MAX_VALID_PORT) {
			throw new IllegalStateException("Port is out of range.");
		}

		LOGGER.info("Creating RMI Server on port " + port);
		serverRegistry = LocateRegistry.createRegistry(port);

		if (waitingRemotes != null) {
			for (String serviceName : waitingRemotes) {
				bindExportedObject(serviceName, remotes.get(serviceName));
			}

			waitingRemotes = null;
		}
	}

	/**
	 * Checks if RMI is disabled, as specified by the system property
	 * "uk.ac.diamond.scisoft.analysis.rmiserverprovider.disable". Either does nothing if RMI is allowed, or throws an
	 * exception if its disabled.
	 *
	 * @throws RemoteException
	 *             if RMI is disabled by the system property
	 */
	private void disabledCheck() throws RemoteException {
		if (Boolean.getBoolean("uk.ac.diamond.scisoft.analysis.rmiserverprovider.disable")) {
			throw new RemoteException(
					"Analysis RPC Server disabled with property uk.ac.diamond.scisoft.analysis.rmiserverprovider.disable");
		}
	}

	/**
	 * Performs the actual task of binding an object to a service name in the RMI registry. Does nothing if the RMI
	 * server has not been initialised.
	 *
	 * @param serviceName
	 *            Name of service to publish
	 * @param object
	 *            Object to publish to RMI
	 * @throws AccessException
	 *             if the registry is local, and security restrictions prevent binding
	 * @throws RemoteException
	 *             if export or bind fails for a non-security related reason
	 */
	private void bindExportedObject(String serviceName, Remote object) throws AccessException, RemoteException {
		if (serverRegistry != null) {
			LOGGER.info("Adding RMI service " + serviceName);
			serverRegistry.rebind(serviceName, UnicastRemoteObject.exportObject(object, 0));
		}
	}

	/**
	 * Provide a port number to use.
	 *
	 * @param rmiPortNumber
	 *            new port number
	 * @throws IllegalStateException
	 *             if already serving on the previous port
	 * @throws IllegalArgumentException
	 *             if the port number is < 1 or > 65535
	 */
	public final void setPort(int rmiPortNumber) throws IllegalStateException, IllegalArgumentException {
		if (rmiPortNumber < MIN_VALID_PORT || rmiPortNumber > MAX_VALID_PORT)
			throw new IllegalArgumentException("Port number must be between " + MIN_VALID_PORT + " and "
					+ MAX_VALID_PORT);
		if (serverRegistry != null)
			throw new IllegalStateException("RMI Server Provider has already been initialised, "
					+ "'setPort' must be called before any handlers are added.");

		port = rmiPortNumber;
		firePortListeners(port, false);
	}

	/**
	 * Return Port number in use, or 0 if not set
	 *
	 * @return port number
	 */
	public int getPort() {
		return port;
	}
}
