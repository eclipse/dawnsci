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
 * Fourth root map function, takes the fourth root of an input value
 */
public class FourthRootMapFunction extends AbstractMapFunction {
	@Override
	public String getMapFunctionName() {
		return "frthrt(x)";
	}

	@Override
	public double mapFunction(double input) {
		return Math.sqrt(Math.sqrt(input));
	}
}
