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
 * A quartic (raised to the fourth power) mapping function 
 */
public class QuarticMapFunction extends AbstractMapFunction {
	@Override
	public String getMapFunctionName() {
		return "x^4";
	}

	@Override
	public double mapFunction(double input) {
		return input*input*input*input;
	}
}
