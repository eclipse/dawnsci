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
 * Specific NCD Green channel mapping function
 */
public class NCDGamma2GreenFunction extends AbstractMapFunction {

	@Override
	public String getMapFunctionName() {
		return "NCD Gamma II Green";
	}

	@Override
	public double mapFunction(double input) {
		if (input >= 0.749) return 1;
		if (input <= 0.447) return 0;
		if (input <= 0.569) return 0.639 * (input - 0.447) / (0.569 - 0.447);
		if (input >= 0.690) return 0.639 + (1 - 0.639) * (input - 0.690) / (0.749 - 0.690); 
		return 0.639;
	}

}
