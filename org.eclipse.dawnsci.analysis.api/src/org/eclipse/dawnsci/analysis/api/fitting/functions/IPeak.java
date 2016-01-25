/*-
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.fitting.functions;

/**
 * A unimodal function (i.e. has one peak)
 */
public interface IPeak extends IFunction {

	/**
	 * Returns the peak position for that peak
	 * 
	 * @return peak position
	 */
	public double getPosition();

	/**
	 * Returns the full width half maximum of a peak
	 * 
	 * @return FWHM
	 */
	public double getFWHM();

	/**
	 * Returns the area under the peak
	 * 
	 * @return area under peak
	 */
	public double getArea();

	/**
	 * @return the height of the peak ( y at getPosition() )
	 */
	public double getHeight();

}
