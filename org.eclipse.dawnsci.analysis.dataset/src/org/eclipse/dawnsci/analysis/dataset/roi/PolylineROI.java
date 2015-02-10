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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.dawnsci.analysis.api.roi.IPolylineROI;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;

/**
 * Class for a polyline ROI (really a list of point ROIs)
 */
public class PolylineROI extends PointROI implements IPolylineROI, Serializable {
	protected List<IROI> pts; // first element should point to this if size > 0

	/**
	 * Zero point line
	 */
	public PolylineROI() {
		super();
		pts = new ArrayList<IROI>();
	}

	public PolylineROI(IPolylineROI poly) {
		this();
		for (IROI p: poly) {
			addPoint(p.getPoint());
		}
		name = poly.getName();
		plot = poly.isPlot();
	}

	public PolylineROI(double[] start) {
		super();
		pts = new ArrayList<IROI>();
		setPoint(start);
	}

	public PolylineROI(double x, double y) {
		this(new double[] {x, y});
	}

	@Override
	public void downsample(double subFactor) {
		spt[0] /= subFactor;
		spt[1] /= subFactor;
		for (int i = 1, imax = pts.size(); i < imax; i++) { // don't call for first point
			pts.get(i).downsample(subFactor);
		}
		setDirty();
	}

	@Override
	public void setPoint(double[] point) {
		super.setPoint(point);
		if (pts.size() == 0) {
			pts.add(this);
		}
	}

	/**
	 * 
	 * @param point
	 */
	public void setPoint(PointROI point) {
		setPoint(point.spt);
	}

	/**
	 * Set point of polyline at index
	 * @param i index
	 * @param point
	 */
	@Override
	public void setPoint(int i, IROI point) {
		if (i == 0) {
			setPoint(point.getPointRef());
		} else {
			pts.set(i, point instanceof PointROI ? (PointROI) point : new PointROI(point.getPointRef()));
		}
		setDirty();
	}

	public void setPoint(int i, double[] point) {
		setPoint(i, new PointROI(point));
	}

	/**
	 * Set point of polyline at index
	 * @param i index
	 * @param x
	 * @param y
	 */
	public void setPoint(int i, double x, double y) {
		setPoint(i, new PointROI(x, y));
	}

	/**
	 * Add point to polyline
	 * @param point
	 */
	@Override
	public void insertPoint(IROI point) {
		if (pts.size() == 0) {
			super.setPoint(point.getPointRef());
			pts.add(this);
		} else {
			pts.add(point instanceof PointROI ? (PointROI) point : new PointROI(point.getPointRef()));
			setDirty();
		}
	}

	public void insertPoint(double[] point) {
		insertPoint(new PointROI(point));
	}

	/**
	 * Add point to polyline
	 * @param point
	 */
	public void insertPoint(int[] point) {
		insertPoint(new double[] { point[0], point[1] });
	}

	/**
	 * Add point to polyline
	 * @param x
	 * @param y
	 */
	public void insertPoint(double x, double y) {
		insertPoint(new double[] {x, y});
	}

	/**
	 * Insert point to polyline at index
	 * @param i index
	 * @param point
	 */
	public void insertPoint(int i, PointROI point) {
		if (i == 0) {
			if (pts.size() == 0) {
				setPoint(point);
			} else { // copy current and then shift
				pts.set(0, new PointROI(spt));
				spt = point.spt;
				pts.add(0, this);
			}
		} else {
			pts.add(i, point);
		}
		setDirty();
	}

	/**
	 * Insert point to polyline at index
	 * @param i index
	 * @param point
	 */
	public void insertPoint(int i, double[] point) {
		insertPoint(i, new PointROI(point));
	}

	/**
	 * Insert point to polyline at index
	 * @param i index
	 * @param x
	 * @param y
	 */
	public void insertPoint(int i, double x, double y) {
		insertPoint(i, new double[] {x, y});
	}

	/**
	 * @return number of sides
	 */
	public int getSides() {
		final int size = pts.size();
		return size == 0 ? 0 : size - 1;
	}

	@Override
	public int getNumberOfPoints() {
		return pts.size();
	}

	/**
	 * @param i
	 * @return x value of i-th point
	 */
	public double getPointX(int i) {
		return pts.get(i).getPointRef()[0];
	}

	/**
	 * @param i
	 * @return y value of i-th point
	 */
	public double getPointY(int i) {
		return pts.get(i).getPointRef()[1];
	}

	@Override
	public PointROI getPoint(int i) {
		return (PointROI) pts.get(i);
	}

	@Override
	public RectangularROI getBounds() {
		if (bounds == null) {
			double[] max = new double[] { -Double.MAX_VALUE, -Double.MAX_VALUE };
			double[] min = new double[] { Double.MAX_VALUE, Double.MAX_VALUE };
			for (int i = 0, imax = pts.size(); i < imax; i++) {
				ROIUtils.updateMaxMin(max, min, pts.get(i).getPointRef());
			}
			bounds = new RectangularROI();
			bounds.setPoint(min);
			bounds.setLengths(max[0] - min[0], max[1] - min[1]);
		}
		return bounds;
	}

	@Override
	public boolean containsPoint(double x, double y) {
		return isNearOutline(x, y, Math.max(Math.ulp(x), Math.ulp(y)));
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		if (super.isNearOutline(x, y, distance))
			return true;

		int imax = pts.size();
		if (imax < 2)
			return true;

		double[] p = pts.get(0).getPointRef();
		double ox = p[0];
		double oy = p[1];
		for (int i = 1; i < imax; i++) {
			p = pts.get(i).getPointRef();
			double px = p[0];
			double py = p[1];
			if (ROIUtils.isNearSegment(px - ox, py - oy, x - ox, y - oy, distance))
				return true;
			ox = px;
			oy = py;
		}
		return false;
	}

	/**
	 * @return iterator over points
	 */
	@Override
	public Iterator<IROI> iterator() {
		return pts.iterator();
	}

	@Override
	public PolylineROI copy() {
		PolylineROI c = new PolylineROI(spt.clone());
		for (int i = 1, imax = pts.size(); i < imax; i++)
			c.insertPoint(pts.get(i).copy());
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * Remove point at given index
	 * @param i
	 */
	public void removePoint(int i) {
		pts.remove(i);
		setDirty();
	}

	@Override
	public void removeAllPoints() {
		pts.clear();
		setDirty();
	}

	@Override
	public String toString() {
		/**
		 * You cannot have pts.toString() if the pts contains this! It
		 * is a recursive call. Fix to defect https://trac/scientific_software/ticket/1943
		 */
		if (pts.contains(this)) return super.toString();
		return pts.toString();
	}

	@Override
	public Dataset[] makeCoordinateDatasets() {
		int n = pts.size();
		double[] x = new double[n];
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			double[] p = pts.get(i).getPointRef();
			x[i] = p[0];
			y[i] = p[1];
		}

		return new DoubleDataset[] { new DoubleDataset(x), new DoubleDataset(y) };
	}

	protected Set<Double> calculateHorizontalIntersections(final double y) {
		double[] xi;
		double[] pta;
		double[] ptb = null;
		Set<Double> values = new TreeSet<Double>();
		Iterator<IROI> it = pts.iterator();
		ptb = it.next().getPointRef();
		while (it.hasNext()) {
			pta = ptb;
			ptb = it.next().getPointRef();
			xi = ROIUtils.findYIntersection(pta, ptb, y);
			if (xi != null) {
				for (double x : xi)
					values.add(x);
			}
		}
		return values;
	}

	@Override
	public double[] findHorizontalIntersections(final double y) {
		if (!intersectHorizontal(y))
			return null;

		if (pts.size() == 1) {
			return pts.get(0).findHorizontalIntersections(y);
		}

		Set<Double> values = calculateHorizontalIntersections(y);
		double[] xi = new double[values.size()];
		int i = 0;
		for (Double d : values) {
			xi[i++] = d;
		}
		return xi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pts == null) ? 0 : pts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		PolylineROI other = (PolylineROI) obj;
		if (pts == null) {
			if (other.pts != null)
				return false;
		} else if (!pts.equals(other.pts))
			return false;
		return true;
	}
}
