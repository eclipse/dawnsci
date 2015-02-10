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
 * Specific NCD Blue channel mapping function
 */
public class NCDGamma2BlueFunction extends AbstractMapFunction {

	@Override
	public String getMapFunctionName() {
		return "NCD Gamma II Blue";
	}

	@Override
	public double mapFunction(double input) {
		if (input >= 0.690) return (input-0.690) / (1-0.690);
		if (input <= 0.192) return input/0.192;
		if (input <= 0.373) return 1 - (input - 0.192) / (0.373 - 0.192);
		if (input <= 0.506) return 0;
		if (input >= 0.624) return 0;
		if (input <= 0.569) return ((input-0.506)/(0.569-0.506)) * 0.322;
		if (input >= 0.569) return (1 - ((input-0.569)/(0.624-0.569))) * 0.322;
		return 0;
	}

}
