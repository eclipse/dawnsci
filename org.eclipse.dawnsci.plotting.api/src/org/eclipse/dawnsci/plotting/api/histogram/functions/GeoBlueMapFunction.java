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
 * Geographical Blue channel mapping
 */
public class GeoBlueMapFunction extends AbstractMapFunction {

	private static final double region1 = 1.0/6.0;
	private static final double region2 = 1.0/5.0;
	private static final double region3 = 1.0/3.0;
	private static final double region4 = 3.0/4.0;
	private static final double region5 = 4.0/5.0;
	
	@Override
	public String getMapFunctionName() {
		// TODO Auto-generated method stub
		return "GeoBlue";
	}

	@Override
	public double mapFunction(double input) {
		if (input < region1) 
			return  Math.sin(0.5 * Math.PI * (input / region1));
		else if (input >= region1 && input < region2)
			return 1;
		else if (input >= region2 && input < region3)
			return 1 - (input - region2) * 6;
		else if (input >= region3 && input < region4)
			return 0.15;
		else if (input >= region4 && input < region5)			
			return 0.15 + 3.125 * Math.sqrt(input-region4);
		else if (input >= region5)
			return 0.15 + 3.125 * Math.sqrt(region5-region4) + 6.25 * (input-region5) * (input-region5);
//			return 20 * (input-region4) * (input - region4);
		return 0;
	}

}
