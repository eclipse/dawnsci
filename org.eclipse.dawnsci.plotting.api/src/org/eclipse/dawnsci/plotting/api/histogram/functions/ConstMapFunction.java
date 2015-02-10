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

package org.eclipse.dawnsci.plotting.api.histogram.functions;

/**
 * Constant map function this will always return a constant value no matter what the
 * input value is
 */

public class ConstMapFunction extends AbstractMapFunction {
	private double constValue;
	private String functionName;
	
	/**
	 * Constant colour map function
	 * @param constValue constant value to be returned
	 * @param functionName name of the function
	 */
	
	public ConstMapFunction(double constValue, String functionName)
	{
		this.functionName = functionName;
		this.constValue = constValue;
	}

	@Override
	public String getMapFunctionName() {
		return functionName;
	}

	@Override
	public double mapFunction(double input) {
		return constValue;
	}

}
