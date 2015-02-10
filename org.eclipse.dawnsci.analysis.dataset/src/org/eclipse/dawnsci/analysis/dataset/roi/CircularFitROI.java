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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.fitting.IConicSectionFitter;
import org.eclipse.dawnsci.analysis.api.roi.IFitROI;
import org.eclipse.dawnsci.analysis.api.roi.IPolylineROI;
import org.eclipse.dawnsci.analysis.dataset.roi.fitting.CircleFitter;

/**
 * A circular region of interest which fits the points in a polygonal region of interest
 */
public class CircularFitROI extends CircularROI implements IFitROI, Serializable {

	private IPolylineROI proi;
	private IConicSectionFitter fitter;
	private double residual;

	private CircularFitROI(double radius, double ptx, double pty) {
		super(radius, ptx, pty);
		residual = 0;
	}

	public CircularFitROI(IPolylineROI points) {
		super(1, 0, 0);
		setPoints(points);
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		proi.downsample(subFactor);
	}

	@Override
	public CircularFitROI copy() {
		CircularFitROI c = new CircularFitROI(getRadius(), getPointX(), getPointY());
		c.name = name;
		c.proi = proi.copy();
		c.plot = plot;
		return c;
	}

	/**
	 * Fit a circle to given polygon
	 * @param polyline
	 * @return fitter
	 */
	public static IConicSectionFitter fit(IPolylineROI polyline) {
		IDataset[] xy = polyline.makeCoordinateDatasets();

		IConicSectionFitter f = new CircleFitter();
		f.geometricFit(xy[0], xy[1], null);
		return f;
	}

	/**
	 * Set points which are then used to fit circle
	 * @param points
	 */
	@Override
	public void setPoints(IPolylineROI points) {
		proi = points;
		if (fitter == null) {
			fitter = fit(points);
		} else {
			IDataset[] xy = points.makeCoordinateDatasets();
			final double[] p = fitter.getParameters();
			p[0] = getRadius();
			p[1] = getPointX();
			p[2] = getPointY();
			fitter.geometricFit(xy[0], xy[1], p);
		}
		final double[] p = fitter.getParameters();
		residual = fitter.getRMS();

		setRadius(p[0]);
		setPoint(p[1], p[2]);
	}

	/**
	 * @return root mean squared of residuals
	 */
	@Override
	public double getRMS() {
		return residual;
	}

	/**
	 * @return fitter used
	 */
	public IConicSectionFitter getFitter() {
		return fitter;
	}

	/**
	 * @return points in polygon for fitting
	 */
	@Override
	public IPolylineROI getPoints() {
		return proi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fitter == null) ? 0 : fitter.hashCode());
		result = prime * result + ((proi == null) ? 0 : proi.hashCode());
		long temp;
		temp = Double.doubleToLongBits(residual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		CircularFitROI other = (CircularFitROI) obj;
		if (fitter == null) {
			if (other.fitter != null)
				return false;
		} else if (!fitter.equals(other.fitter))
			return false;
		if (proi == null) {
			if (other.proi != null)
				return false;
		} else if (!proi.equals(other.proi))
			return false;
		if (Double.doubleToLongBits(residual) != Double.doubleToLongBits(other.residual))
			return false;
		return true;
	}
}
