/*-
 * Copyright (c) 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.rpc;

import java.io.IOException;

/**
 * Server class for AnalysisRpc.
 * <p>
 * Within SDA, consider registering new handlers with AnalysisRpcServerProvider rather than creating a new server on an
 * additional port See AnalysisRpcBasicTest See the Ananlysis Rpc Basic Test for an example of use
 */
public interface IAnalysisRpcServer {

	/**
	 * Register a new handler for AnalysisRpc to delegate incoming calls to.
	 * 
	 * @param name
	 *            the registered name
	 * @param handler
	 *            the handler to delegate calls to
	 * @see IAnalysisRpcHandler
	 */
	public void addHandler(String name, IAnalysisRpcHandler handler);

	/**
	 * Remove an existing handler from the server. This is an unusual operation to do and exists mostly to enable
	 * testing.
	 * 
	 * @param serviceName
	 *            to unregister
	 */
	public void removeHandler(String serviceName);

	/**
	 * Get the handler registered with the given name. This is an unusual operation to do and exists mostly to enable
	 * testing.
	 * 
	 * @param serviceName
	 *            to query
	 * @return handler associated with this service or <code>null</code> if none registered.
	 */
	public IAnalysisRpcHandler getHandler(String serviceName);

	/**
	 * Start the server. The server can be started before all handlers are added.
	 * 
	 * @throws AnalysisRpcException
	 *             if there is an exception starting the underlying XML-RPC server. The underlying {@link IOException}
	 *             is wrapped.
	 */
	public void start() throws AnalysisRpcException;

	/**
	 * Shutdown the server and stop servicing requests.
	 */
	public void shutdown();

	public IAnalysisRpcHandler getDestination(String destination);

	/**
	 * Returns the port, on which the AnalysisRpcServer is running. This method may be invoked after {@link #start()}
	 * only.
	 * <p>
	 * For example, it may be useful to call {@link #getPort()} if the server is created on port 0 (auto assign a port)
	 * 
	 * @return Port number
	 */
	public int getPort();

}
