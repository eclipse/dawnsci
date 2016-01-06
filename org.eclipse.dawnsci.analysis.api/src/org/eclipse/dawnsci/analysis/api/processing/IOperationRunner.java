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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a running mechanism for running a pipeline
 * @author Matthew Gerring
 *
 */
public interface IOperationRunner {

	public static final Logger logger = LoggerFactory.getLogger(IOperationRunner.class);

	/**
	 * 
	 * @param context
	 */
	public void init(IOperationContext context) throws Exception;
	
	/**
	 * Execute the whole pipeline
	 */
	public void execute() throws Exception;
	
	/**
	 * 
	 * @return The types that this runner should be used to run 
	 */
	public ExecutionType[] getExecutionTypes();
}
