/*-
 * Copyright (c) 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.rpc;

public interface IAnalysisRpcPythonService extends IAnalysisRpcPythonRemote {

	/**
	 * Get the RPC Client so that additional calls can be made. newProxyInstance can be called on the resulting client
	 * to obtain a proxy for registered handlers.
	 * 
	 * @return instance of client.
	 */
	public IAnalysisRpcClient getClient();

	/**
	 * Convenience method around {@link #addHandlers(String, String[])} to add a single handler
	 */
	public void addHandler(String pycode, String single_handler_name) throws AnalysisRpcException;

	public void stop();

}