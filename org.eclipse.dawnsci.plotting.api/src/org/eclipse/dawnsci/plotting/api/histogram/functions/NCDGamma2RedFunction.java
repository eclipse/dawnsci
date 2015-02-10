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
 * Specific NCD Red channel mapping function
 */
public class NCDGamma2RedFunction extends AbstractMapFunction {

	@Override
	public String getMapFunctionName() {
		return "NCD Gamma II Red";
	}

	@Override
	public double mapFunction(double input) {
		if (input >= 0.753) return 1;
		if (input <= 0.188) return 0;
		if (input <= 0.251) return 0.316 * (input - 0.188) / (0.251 - 0.188);
		if (input <= 0.306) return 0.316;
		if (input <= 0.431) return 0.316 + (1 - 0.319) * (input - 0.306) / (0.431 - 0.306);
		if (input >= 0.682) return 0.639 + (1 - 0.639) * (input - 0.682) / (0.753 - 0.682);
		if (input >= 0.635) return 1 - (1 - 0.639) * (input - 0.635) / (0.682 - 0.635);
		return 1;					
	}

}
