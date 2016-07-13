/*-
 * Copyright (c) 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.rpc;

/**
 * The methods that must be supported by the Python Analysis RPC server that is launched by
 * {@link IAnalysisRpcPythonService}.
 */
public interface IAnalysisRpcPythonRemote {
	/**
	 * Add new handlers to the running server. The pycode is 'exec'uted with custom dictionaries, the names in
	 * handler_names must be defined by code and then are added to the server
	 * 
	 * @param pycode
	 *            The Python code that is executed (with exec) to generate the handlers named in handler_names
	 * @param handler_names
	 *            The string names of the handlers to register with the server
	 * @throws AnalysisRpcException
	 */
	public void addHandlers(String pycode, String[] handler_names) throws AnalysisRpcException;

	/**
	 * Set the scisoftpy plotting port to direct plots back to.
	 * 
	 * @param port
	 *            Port that the Analysis RPC Plot Server is listening on
	 * @throws AnalysisRpcException
	 */
	public void setPlottingPort(int port) throws AnalysisRpcException;
}