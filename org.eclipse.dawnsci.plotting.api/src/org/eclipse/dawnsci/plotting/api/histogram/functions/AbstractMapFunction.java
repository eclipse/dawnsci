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

import org.eclipse.dawnsci.plotting.api.histogram.ITransferFunction;

/**
 * Abstract class for a general mapping function from one double to another one or a byte
 */
public abstract class AbstractMapFunction implements ITransferFunction {

	/**
	 * Clipped version of mapFunction
	 * @param input original value to map
	 * @return output double
	 */
	final public double getPoint(double input) {
		double value = mapFunction(input);
		if (value < 0.0) return 0.0;
		else if (value > 1.0) return 1.0;
		return value;
	}

	/**
	 * @param input original value to map
	 * @return byte (C-style usage) 0..255 but due to stupid Java signed bytes will be 
	 *              mapped to -128..127 in Java we have to use short
	 */
	final public short mapToByte(double input) {
		return (short)(255*getPoint(input));
	}

	/**
	 * Get the name of the function so it can be included in GUI components
	 * @return the function name
	 */
	abstract public String getMapFunctionName();

	
	/**
	 * Converts an input value to an output value.
	 * 
	 * @param input the input value (0 to 1)
	 * 
	 * @return the output value (0 to 1)
	 */
	abstract public double mapFunction(double input);
	
	
	@Override
	public int[] getArray() {
		int[] result = new int[256];
		for (int i = 0; i < result.length; i++) {
			result[i] = (int) (getPoint((double)i/256)*255);
		}
		return result;
	}

}
