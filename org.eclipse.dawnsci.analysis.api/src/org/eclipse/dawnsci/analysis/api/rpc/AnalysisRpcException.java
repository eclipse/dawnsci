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
 * Wrapper exception for any one of a number of possible failures. Call {@link #getCause()} for the underlying exception
 */
public class AnalysisRpcException extends Exception {
	private static final long serialVersionUID = 8996421526351837418L;

	public AnalysisRpcException() {
		super();
	}

	public AnalysisRpcException(String s) {
		super(s);
	}

	public AnalysisRpcException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnalysisRpcException(Throwable cause) {
		super(cause);
	}

}
