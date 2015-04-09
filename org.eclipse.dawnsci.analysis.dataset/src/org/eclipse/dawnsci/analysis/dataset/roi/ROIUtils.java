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

import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.roi.IROI;

public class ROIUtils {

	/**
	 * @param clazz
	 * @return new list to hold ROIs of same class
	 * @throws UnsupportedOperationException when there is no list class
	 */
	public static ROIList<?> createNewROIList(Class<? extends IROI> clazz) {
		if (clazz == null)
			return null;

		// be aware that order of tests is important as must be determined by class hierarchy
		if (PolygonalROI.class.isAssignableFrom(clazz))
			return new PolygonalROIList();
		// to-do add free draw
		else if (PolylineROI.class.isAssignableFrom(clazz))
			return new PolylineROIList();
		else if (PointROI.class.isAssignableFrom(clazz))
			return new PointROIList();
		// to-do add line 3D
		else if (LinearROI.class.isAssignableFrom(clazz))
			return new LinearROIList();
		else if (XAxisBoxROI.class.isAssignableFrom(clazz))
			return new XAxisBoxROIList();
		else if (XAxisLineBoxROI.class.isAssignableFrom(clazz))
			return new XAxisLineBoxROIList();
		else if (YAxisBoxROI.class.isAssignableFrom(clazz))
			return new YAxisBoxROIList();
		else if (YAxisLineBoxROI.class.isAssignableFrom(clazz))
			return new YAxisLineBoxROIList();
		else if (PerimeterBoxROI.class.isAssignableFrom(clazz))
			return new PerimeterBoxROIList();
		// to-do add grid
		else if (RectangularROI.class.isAssignableFrom(clazz))
			return new RectangularROIList();
		else if (RingROI.class.isAssignableFrom(clazz))
			return new RingROIList();
		else if (SectorROI.class.isAssignableFrom(clazz))
			return new SectorROIList();
		// to-do add circular fit
		else if (CircularROI.class.isAssignableFrom(clazz))
			return new CircularROIList();
		else if (EllipticalFitROI.class.isAssignableFrom(clazz))
			return new EllipticalFitROIList();
		else if (EllipticalROI.class.isAssignableFrom(clazz))
			return new EllipticalROIList();
		else if (ParabolicROI.class.isAssignableFrom(clazz))
			return new ParabolicROIList();
		else if (HyperbolicROI.class.isAssignableFrom(clazz))
			return new HyperbolicROIList();
		// to-do add surface plot

		throw new UnsupportedOperationException("No corresponding ROI list class");
	}

	/**
	 * @param roi
	 * @return new list to hold ROIs of same type
	 */
	public static ROIList<?> createNewROIList(IROI roi) {
		if (roi == null)
			return null;
		return createNewROIList(roi.getClass());
	}

	/**
	 * Convert int array of two numbers to double array
	 * @param pt
	 * @return double array
	 */
	public static double[] convertToDoubleArray(int[] pt) {
		assert pt.length == 2;
		return new double[] { pt[0], pt[1] };
	}

	/**
	 * Check if point (x, y) is close to a line segment given by direction (dx, dy) and length
	 * @param dx
	 * @param dy
	 * @param l
	 * @param x
	 * @param y
	 * @param distance
	 * @return true if given point is within distance of segment  
	 */
	public static boolean isNearSegment(double dx, double dy, double l, double x, double y, double distance) {
		double t = x * dx + y * dy; // parameter on segment
		if (t < 0 || t > l)
			return false;

		return Math.abs(x * dy - y * dx) <= distance;
	}

	/**
	 * Check if point (x, y) is close to a line segment given by vector (vx, vy)
	 * @param vx
	 * @param vy
	 * @param x
	 * @param y
	 * @param distance
	 * @return true if given point is within distance of segment  
	 */
	public static boolean isNearSegment(double vx, double vy, double x, double y, double distance) {
		double l = Math.hypot(vx, vy);
		return isNearSegment(vx/l, vy/l, l, x, y, distance);
	}

	/**
	 * Update maximum and minimum values according to point
	 * @param max
	 * @param min
	 * @param pt
	 */
	public static void updateMaxMin(double[] max, double[] min, double[] pt) {
		updateMaxMin(max, min, pt[0], pt[1]);
	}

	/**
	 * Update maximum and minimum values according to x and y
	 * @param max
	 * @param min
	 * @param x
	 * @param y
	 */
	public static void updateMaxMin(double[] max, double[] min, double x, double y) {
		if (x > max[0])
			max[0] = x;
		if (y > max[1])
			max[1] = y;
		if (x < min[0])
			min[0] = x;
		if (y < min[1])
			min[1] = y;
	}

	/**
	 * Find intersect of horizontal line with a line segment
	 * @param spt start point of segment
	 * @param ept end point of segment
	 * @param y ordinate
	 * @return abscissa can be null for non-intersecting case, else one or two values
	 */
	public static double[] findYIntersection(final double[] spt, final double[] ept, final double y) {
		double[] xi = null;
		double dy = ept[1] - spt[1];
		if (dy == 0) {
			if (y == spt[1]) {
				xi = new double[] { spt[0], ept[0] };
				Arrays.sort(xi);
			}
		} else {
			double ny = y - spt[1];
			if ((ny >= 0 && ny <= dy) || (ny <= 0 && ny >= dy)) {
				xi = new double[] { spt[0] + (ept[0] - spt[0]) * ny / dy};
			}
		}

		return xi;
	}
}
