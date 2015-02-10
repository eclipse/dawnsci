/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.roi;

/**
 * Represents an orientable region of interest with the start point being the centre of rotation
 */
public interface IOrientableROI extends IROI {
	@Override
	public IOrientableROI copy();

	/**
	 * @return angle, in degrees, from 0 to 90 is from x-axis to y-axis
	 */
	public double getAngleDegrees();

	/**
	 * Set angle, in degrees
	 * @param degrees from 0 to 90 is from x-axis to y-axis
	 */
	public void setAngleDegrees(double degrees);

	/**
	 * @return angle, in radians
	 */
	public double getAngle();

	/**
	 * Set angle, in radians, from 0 to pi/2 is from x-axis to y-axis
	 * @param angle from 0 to pi/2 is from x-axis to y-axis
	 */
	public void setAngle(double angle);
}
