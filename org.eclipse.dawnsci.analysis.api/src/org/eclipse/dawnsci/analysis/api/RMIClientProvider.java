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

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * A simple interface to Java RMI so that objects can be exported using the defaults encoded in this class.
 */
public class RMIClientProvider extends AbstractClientProvider {
	private static RMIClientProvider instance = new RMIClientProvider();

	/**
	 * Get Instance of provider
	 * 
	 * @return instance
	 */
	public static RMIClientProvider getInstance() {
		return instance;
	}

	private RMIClientProvider() {
	}

	/**
	 * Return a proxy to the object on the server.
	 * <p>
	 * This is the method you call from the "client" code (e.g. the code run from Jython) to access the server.
	 * 
	 * @param host
	 *            host name to connect to, or <code>null</code> for localhost
	 * @param serviceName
	 *            name of a registered service
	 * @param port
	 *            port on server to get registry from, or 0 for automatic port (using SCISOFT_RMI_PORT env variable)
	 * @return remote proxy remote object
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws AccessException
	 */
	public Remote lookup(String host, String serviceName, int port) throws RemoteException, NotBoundException,
			AccessException {
		if (port == 0)
			port = getPort();
		Registry registry = LocateRegistry.getRegistry(host, port);
		return registry.lookup(serviceName);
	}

	/**
	 * Return a proxy to the object on the server.
	 * <p>
	 * This is the method you call from the "client" code (e.g. the code run from Jython) to access the server.
	 * 
	 * @param host
	 *            host name to connect to, or <code>null</code> for localhost
	 * @param serviceName
	 *            name of a registered service
	 * @return remote proxy remote object
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws AccessException
	 */
	public Remote lookup(String host, String serviceName) throws RemoteException, NotBoundException, AccessException {
		return lookup(host, serviceName, 0);
	}

	@Override
	protected String getEnvName() {
		return "SCISOFT_RMI_PORT";
	}


}
