/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

public interface IOperationExporter {
	
	/**
	 * 
	 * @param context
	 */
	public void init(IOperationContext context) throws Exception;


	/**
	 * Export an IOperationContext to a file openable in another format.
	 * 
	 * For instance ExecutionType.GRAPH would be a MOML file.
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public void export(String filePath) throws Exception;
	
	
	/**
	 * 
	 * @return The types that this exporter should be used to run 
	 */
	public ExecutionType[] getExecutionTypes();

}
