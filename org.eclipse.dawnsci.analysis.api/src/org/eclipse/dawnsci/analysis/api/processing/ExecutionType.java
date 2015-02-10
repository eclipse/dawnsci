/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.analysis.api.processing;

/**
 * Types of execution when IOperationService.execute is called.
 */
public enum ExecutionType {
	SERIES(-1), PARALLEL(5000), GRAPH(10*1000*60);
	
	private final long timeout;
	ExecutionType(long timeout) {
		this.timeout = timeout;	
	}
	
	/**
	 * This timeout is applied 
	 * @return timeout in ms
	 */
	public long getTimeout() {
		return timeout;
	}
}
