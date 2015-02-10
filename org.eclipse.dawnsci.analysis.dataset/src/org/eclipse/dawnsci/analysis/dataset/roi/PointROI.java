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

package org.eclipse.dawnsci.analysis.dataset.roi;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Class for 2D point region of interest
 */
public class PointROI extends ROIBase implements Serializable {

	public PointROI() {
		spt = new double[2];
	}

	public PointROI(double[] point) {
		setPoint(point);
	}

	public PointROI(double x, double y) {
		this();
		setPoint(x, y);
	}

	public PointROI(int[] point) {
		this();
		setPoint(point);
	}

	@Override
	public boolean containsPoint(double x, double y) {
		return spt[0] == x && spt[1] == y;
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		if (!super.isNearOutline(x, y, distance))
			return false;

		return Math.hypot(spt[0] - x, spt[1] - y) <= distance;
	}

	@Override
	public String toString() {
		return super.toString() + "point=" + Arrays.toString(spt);
	}

	@Override
	public double[] findHorizontalIntersections(double y) {
		if (y == spt[1])
			return new double[] {spt[0]};
		return null;
	}
}
