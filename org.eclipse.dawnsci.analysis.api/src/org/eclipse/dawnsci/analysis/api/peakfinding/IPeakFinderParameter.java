/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.peakfinding;

public interface IPeakFinderParameter {
	
	/**
	 * equals() and hashcode() should be overridden since it is important that
	 * there is only one parameter with each name
	 */
	
	/**
	 * Get the name of the parameter
	 * @return String name of parameter
	 */
	public String getName();

	/**
	 * Get the boolean indicating whether this should be an integer parameter
	 * or not
	 * @return Boolean indicating if parameter is an integer
	 */
	public boolean isInt();
	
	/**
	 * Get value of the parameter
	 * @return Number value of the parameter
	 */
	public Number getValue();

	/**
	 * Set the value of the parameter, checking whether the supplied type is
	 * consistent with the expected isInt value
	 * @param value New value of the parameter
	 * @throws IllegalArgumentException if value is not correct type (Integer/Double)
	 */
	public void setValue(Number value);
}
