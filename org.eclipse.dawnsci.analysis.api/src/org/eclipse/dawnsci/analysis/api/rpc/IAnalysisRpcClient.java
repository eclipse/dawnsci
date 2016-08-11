/*-
 * Copyright (c) 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public interface IAnalysisRpcClient {

	/**
	 * Issue a RPC call by calling request. The call is sent to the server on the registered port to the handler
	 * registered with the name passed to destination.
	 * <p>
	 * All arguments passed the server are "flattened" for transport, and automatically unflattened by the server before
	 * delivery to the destination handler. The return value is similarly flattened and unflattened.
	 * <p>
	 * If the delegated to method throws an exception, it is re-thrown here wrapped as an {@link AnalysisRpcException}.
	 * If the delegated to method returns an exception, it will be thrown rather than returned.
	 * <p>
	 * 
	 * @param destination
	 *            target handler in server
	 * @param args
	 *            arguments in the server
	 * @return value that the delegated to method returns
	 * @throws AnalysisRpcException
	 *             under a few conditions, always as a wrapper around the original cause. The original causes can be one
	 *             of:
	 *             <ul>
	 *             <li>{@link UnsupportedOperationException} if the arguments or return type failed to be flattened or
	 *             unflattened.</li>
	 *             <li>{@link AnalysisRpcRemoteException} if an exception occurred on the remote side of the call.</li>
	 *             <li>XmlRpcException if the underlying transport had a failure and XML-RPC was used as the transport
	 *             </li>
	 *             </ul>
	 */
	public Object request(String destination, Object[] args) throws AnalysisRpcException;

	/**
	 * Issue a RPC call by calling request, entering debug mode if server is available.
	 * 
	 * @param suspend
	 *            suspend on entry to handler
	 * @see #request(String, Object[])
	 */
	public Object request_debug(String destination, Object[] args, boolean suspend) throws AnalysisRpcException;

	/**
	 * Test if the server is up and running.
	 * 
	 * @warning The AnalysisRpc server may become unreachable between is_alive being called and a request being made.
	 * @return True if communication is working
	 */
	public boolean isAlive();

	/**
	 * Set PyDev Debugger settrace options. See pydevd.py for further documentation. Calling this method overrides
	 * previous settings and this only has an effect if it is called before any calls to
	 * {@link #request_debug(String, Object[], boolean)}
	 * 
	 * @param options
	 *            Key/Value pairs of parameters to settrace to their desired values.
	 * @throws AnalysisRpcException
	 */
	public void setPyDevSetTraceParams(Map<String, Object> options) throws AnalysisRpcException;

	/**
	 * Convenience method around {@link #setPyDevSetTraceParams(Map)} for the most common situation where only the port
	 * needs to be overridden.
	 * 
	 * @param port
	 * @throws AnalysisRpcException
	 */
	public void setPyDevSetTracePort(int port) throws AnalysisRpcException;

	/**
	 * Poll isAlive until True, raise an exception if isAlive is not true before the timeout.
	 * 
	 * @throws AnalysisRpcException
	 */
	public void waitUntilAlive() throws AnalysisRpcException;

	/**
	 * Convenience wrapper around newProxyInstance(ClassLoader, Class[])
	 * 
	 * @param interfaces
	 *            to implement in proxy. The class loader used is the class loader of the first interface in the list.
	 * @param debug
	 *            have calls use invoke_debug when true
	 */
	public Object newProxyInstance(Class<?>[] interfaces, boolean debug);

	/**
	 * Create a proxy that implements the given interfaces. All methods that are called on the proxy must be declared to
	 * throw {@link AnalysisRpcException}.
	 * 
	 * @param loader
	 *            class loader to use. See {@link Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)} for
	 *            details on the class loader.
	 * @param interfaces
	 *            Interfaces to implement for the proxy
	 * @param debug
	 *            have calls use invoke_debug when true
	 * @return a new instance of an object that implements the interfaces supplied.
	 */
	public Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, boolean debug);

	/**
	 * Poll isAlive until True, raise an exception if isAlive is not true before the timeout.
	 * 
	 * @param milliseconds
	 *            timeout time
	 * @throws AnalysisRpcException
	 */
	public void waitUntilAlive(long milliseconds) throws AnalysisRpcException;

	/**
	 * Return port number in use
	 * 
	 * @return port
	 */
	public int getPort();

	/**
	 * Convenience wrapper around newProxyInstance(Class[]) with no debug
	 * 
	 * @param <T>
	 * @param single_interface
	 *            Sinlge Interface to make proxy for
	 */
	public <T> T newProxyInstance(Class<T> single_interface);

	/**
	 * Convenience wrapper around newProxyInstance(Class[])
	 * 
	 * @param single_interface
	 *            Sinlge Interface to make proxy for
	 * @param debug
	 *            have calls use invoke_debug when true
	 */
	public <T> T newProxyInstance(Class<T> single_interface, boolean debug);

}
