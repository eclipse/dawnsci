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
 * Geographical Red channel mapping
 */
public class GeoRedMapFunction extends AbstractMapFunction {

	private static final double region1 = 1.0/7.0;
	private static final double region2 = 2.0/5.0;
	private static final double region3 = 3.0/4.0;
	@Override
	public String getMapFunctionName() {
		return "GeoRed";
	}

	@Override
	public double mapFunction(double input) {
		if (input < region1)
			return 0;
		if (input >= region1 && input < region2)
			return (input-region1) * 4;
		else if (input >= region2 && input < region3)
			return 1.0 - 0.5 * (input-region2);
		else if (input >= region3)
			return 1.0 - 0.5 * (region3-region2) + 3.75 * (input-region3) * (input-region3);
		return 1;
	}

}
