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
 * A custom piece-wise linear mapping function
 */
public class CustomAPieceWiseLinearMapFunction extends AbstractMapFunction {
	@Override
	public String getMapFunctionName() {
		return "4x;1;-2x+1.84;12.5x-11.5";
	}

	@Override
	public double mapFunction(double input) {
		double returnValue;
		if (input < 0.25) {
			returnValue = 4.0 * input;
		} else if (input < 0.42) {
			returnValue = 1.0;
		} else if (input < 0.92) {
			returnValue = -2.0 * input + 1.84;
		} else {
			returnValue = 12.5 * input - 11.5;
		}
		return returnValue;
	}
}
