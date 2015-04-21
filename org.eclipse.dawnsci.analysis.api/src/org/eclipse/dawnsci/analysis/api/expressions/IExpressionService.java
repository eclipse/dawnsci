/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.expressions;

/**
 * This service can be called to save get an engine for parsing mathematical expression strings and performing
 * calculations on datasets
 */
public interface IExpressionService {
	
	/**
	 * Method to rget an expression engine from the service
	 *  
	 * @return IExpressionEngine
	 */
	public IExpressionEngine getExpressionEngine();
	
}
