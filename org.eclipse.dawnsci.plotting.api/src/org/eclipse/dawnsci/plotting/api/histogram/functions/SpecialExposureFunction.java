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
 *
 */
public class SpecialExposureFunction extends AbstractMapFunction {

	private char mode;
	private double minThreshold;
	private double maxThreshold;
	private String functionName;
	
	public SpecialExposureFunction(String functionName,
								   double minThreshold,
								   double maxThreshold,
								   char mode)
	{
		this.functionName = functionName;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
		this.mode = mode;
	}
	
	public void setThresholds(double minThreshold,
							  double maxThresHold)
	{
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThresHold;
	}
	
	@Override
	public String getMapFunctionName() {
		// TODO Auto-generated method stub
		return functionName;
	}

	@Override
	public double mapFunction(double input) {
		double returnValue = 0;
		switch (mode) {
			case 'r':
			{
				if (input < minThreshold)
					returnValue = 0;
				else
					returnValue = input;
			}
			break;
			case 'g':
			{
				if (input < minThreshold ||
					input > maxThreshold)
					returnValue = 0;
				else
					returnValue = input;
			}
			break;
			case 'b':
			{
				returnValue = input;
				if (input < minThreshold)
					returnValue =  (minThreshold - input) / minThreshold;
				if (input > maxThreshold)
					returnValue = 0;
			}
			break;
		}
		return returnValue;
	}

}
