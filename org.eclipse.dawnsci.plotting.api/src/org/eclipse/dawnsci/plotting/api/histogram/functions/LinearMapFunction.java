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
 * General linear map function with offset
 * 
 * This will scale the input by a specified scaling factor and adds an
 * offset to it, optional it is possible to get the absolute value 
 */
public class LinearMapFunction extends AbstractMapFunction {
	private String functionName;
	private double scaleFactor;
	private double offset;
	private boolean useAbsolute;

	/**
	 * Linear: x
	 */
	public LinearMapFunction()
	{
		this("x", 1.0, 0.0, false);
	}

	/**
	 * Scaled linear: scaleFactor * x
	 * @param functionName
	 * @param scaleFactor
	 * @param useAbs
	 */
	public LinearMapFunction(String functionName,
										double scaleFactor,
										boolean useAbs)
	{
		this(functionName, scaleFactor, 0.0, useAbs);
	}

	/**
	 * Scaled linear: scaleFactor * x + offset
	 *
	 * @param functionName name of the function
	 * @param scaleFactor scaling factor
	 * @param offset offset
	 * @param useAbs use absolute value?
	 */
	public LinearMapFunction(String functionName,
										double scaleFactor,
										double offset,
										boolean useAbs)
	{
		this.functionName = functionName;
		this.scaleFactor = scaleFactor;
		this.offset = offset;
		this.useAbsolute = useAbs;
	}

	@Override
	public String getMapFunctionName() {
		return functionName;
	}

	@Override
	public double mapFunction(double input) {
		double returnValue = input * scaleFactor + offset;
		if (useAbsolute)
			returnValue = Math.abs(returnValue);
		return returnValue;
	}
}
