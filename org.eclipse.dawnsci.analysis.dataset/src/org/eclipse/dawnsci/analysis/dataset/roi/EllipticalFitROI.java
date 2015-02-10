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
import org.eclipse.dawnsci.analysis.dataset.roi.fitting.EllipseFitter;

/**
 * An elliptical region of interest which fits the points in a polygonal region of interest
 */
public class EllipticalFitROI extends EllipticalROI implements IFitROI, Serializable {

	private IPolylineROI proi;
	private boolean circleOnly;
	private transient IConicSectionFitter fitter; // The fitter is not serializable, the EllipticalFitROI is.
	private double residual;

	private EllipticalFitROI(double major, double minor, double angle, double ptx, double pty) {
		super(major, minor, angle, ptx, pty);
		residual = 0;
	}

	public EllipticalFitROI(IPolylineROI points) {
		this(points, false);
	}

	public EllipticalFitROI(IPolylineROI points, boolean fitCircle) {
		super(1, 0, 0);
		circleOnly = fitCircle;
		setPoints(points);
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		proi.downsample(subFactor);
	}

	@Override
	public EllipticalFitROI copy() {
		EllipticalFitROI c = new EllipticalFitROI(getSemiAxis(0), getSemiAxis(1), getAngle(), getPointX(), getPointY());
		c.proi = proi.copy();
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * Fit an ellipse to given polyline
	 * @param polyline
	 * @return fitter
	 */
	public static IConicSectionFitter fit(IPolylineROI polyline, final boolean fitCircle) {
		
//		IFittingAlgorithmService service = (IFittingAlgorithmService) Activator.getService(IFittingAlgorithmService.class);
		IDataset[] xy = polyline.makeCoordinateDatasets();
		if (fitCircle) {
			IConicSectionFitter f = new CircleFitter();
			f.geometricFit(xy[0], xy[1], null);
			return f;
		}

		IConicSectionFitter f = new EllipseFitter();
		f.geometricFit(xy[0], xy[1], null);
		return f;
	}

	
	/**
	 * Set points which are then used to fit ellipse
	 * @param points
	 */
	@Override
	public void setPoints(IPolylineROI points) {
		proi = points;
		int n = points.getNumberOfPoints();
		final double[] p;
		if (fitter == null) {
			fitter = fit(points, n < 5 || circleOnly);
			p = fitter.getParameters();
		} else {
			IDataset[] xy = points.makeCoordinateDatasets();
			p = fitter.getParameters();
			if (p.length < 5) {
				p[0] = getSemiAxis(0);
				p[1] = getPointX();
				p[2] = getPointY();
			} else {
				p[0] = getSemiAxis(0);
				p[1] = getSemiAxis(1);
				p[2] = getAngle();
				p[3] = getPointX();
				p[4] = getPointY();
			}
			fitter.geometricFit(xy[0], xy[1], p);
		}
		residual = fitter.getRMS();

		if (p.length < 5) {
			setSemiAxis(0, p[0]);
			setSemiAxis(1, p[0]);
			setAngle(0);
			setPoint(p[1], p[2]);
		} else {
			setSemiAxis(0, p[0]);
			setSemiAxis(1, p[1]);
			setAngle(p[2]);
			setPoint(p[3], p[4]);
		}
		setDirty();
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
		result = prime * result + (circleOnly ? 1231 : 1237);
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

		EllipticalFitROI other = (EllipticalFitROI) obj;
		if (circleOnly != other.circleOnly)
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
