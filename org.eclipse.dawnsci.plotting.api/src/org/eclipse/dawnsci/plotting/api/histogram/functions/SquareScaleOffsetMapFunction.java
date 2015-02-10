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
 * Map function that returns the square of the scaled and offset input
 */
public class SquareScaleOffsetMapFunction extends AbstractMapFunction {
	private String functionName;
	private double scaleFactor;
	private double offset;
	
	/**
	 * Constructor for a square scale offset map function
	 *
	 * (scaleFactor*x + offset)^2
	 * @param functionName name of the function
	 * @param scaleFactor scaling factor
	 * @param offset offset factor
	 */
	public SquareScaleOffsetMapFunction(String functionName, double scaleFactor,
										double offset)
	{
		this.functionName = functionName;
		this.scaleFactor = scaleFactor;
		this.offset = offset;
	}
	
	@Override
	public String getMapFunctionName() {
		return functionName;
	}

	@Override
	public double mapFunction(double input) {
		double returnValue = input * scaleFactor + offset;
		returnValue *= returnValue;
		return returnValue;
	}
}
