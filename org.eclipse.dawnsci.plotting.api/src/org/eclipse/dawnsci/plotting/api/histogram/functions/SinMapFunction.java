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
 * Sine map function return a sin value from the input using some given frequency modulation and
 * optionally returning the absolute value
 */
public class SinMapFunction extends AbstractMapFunction {
	private String functionName;
	private double freqMod;
	private boolean useAbsolute;
	
	/**
	 * SinMapFunction constructor
	 * @param functionName function name
	 * @param frequency frequency modulator
	 * @param useAbs use absolute value
	 */
	public SinMapFunction(String functionName, 
						  double frequency, boolean useAbs)
	{
		this.functionName = functionName;
		this.freqMod = frequency;
		this.useAbsolute = useAbs;
	}
	@Override
	public String getMapFunctionName() {
		return functionName;
	}

	@Override
	public double mapFunction(double input) {
		double returnValue = Math.sin(input * freqMod);
		if (useAbsolute)
			returnValue = Math.abs(returnValue);
		return returnValue;
	}
}
