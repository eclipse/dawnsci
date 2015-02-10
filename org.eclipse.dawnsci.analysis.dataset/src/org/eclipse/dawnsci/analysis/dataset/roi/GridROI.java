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

public class GridROI extends RectangularROI implements Serializable {

	double xSpacing = 50;
	double ySpacing = 50;
	boolean midPointOn = true;
	boolean gridLinesOn = false;
	private GridPreferences gridPref = new GridPreferences();

	@SuppressWarnings("unused")
	private GridROI() {
		super();
	}

	@SuppressWarnings("unused")
	private GridROI(double width, double angle) {
		super(width, angle);
	}

	@SuppressWarnings("unused")
	private GridROI(double width, double height, double angle) {
		super(width, height, angle);
	}
	
	/**
	 * If using this constructor, you should set x and y spacing and GridPreferences
	 * at some point.
	 * 
	 * @param ptx
	 * @param pty
	 * @param width
	 * @param height
	 * @param angle
	 */
	public GridROI(double ptx, double pty, double width, double height, double angle) {
		super(ptx, pty, width, height, angle);
	}

	@SuppressWarnings("unused")
	private GridROI(double ptx, double pty, double width, double height, double angle, boolean clip) {
		super(ptx, pty, width, height, angle, clip);
	}

	private GridROI(double ptx, double pty, double width, double height, double angle, double xSpacing, double ySpacing) {
		super(ptx, pty, width, height, angle, false);
		this.xSpacing = xSpacing;
		this.ySpacing = ySpacing;
	}

	public GridROI(double ptx, double pty, double width, double height, double angle, double xSpacing, double ySpacing,
			boolean gridLinesOn, boolean midPointOn) {
		this(ptx, pty, width, height, angle, xSpacing, ySpacing);
		this.gridLinesOn = gridLinesOn;
		this.midPointOn = midPointOn;
	}

	public GridROI(double ptx, double pty, double width, double height, double angle, double xSpacing, double ySpacing,
			boolean gridLinesOn, boolean midPointOn, GridPreferences gridPref) {
		this(ptx, pty, width, height, angle, xSpacing, ySpacing);
		this.gridLinesOn = gridLinesOn;
		this.midPointOn = midPointOn;
		this.gridPref = gridPref;
	}

	public GridROI(GridPreferences gridPrefs) {
		this.gridPref = gridPrefs;
	}

	@Override
	public GridROI copy() {
		GridROI c = new GridROI(spt[0], spt[1], len[0], len[1], getAngle(), xSpacing,
				ySpacing, gridLinesOn, midPointOn, gridPref);
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * @return Returns the spacing of X and Y grid
	 */
	public double[] getSpacing() {
		return new double[] { xSpacing, ySpacing };
	}

	/**
	 * @return integer lengths
	 */
	public int[] getIntSpacing() {
		return new int[] { (int) xSpacing, (int) ySpacing };
	}

	/**
	 * Work out where all the grid points (middle of grid box) are
	 * 
	 * @return grid points
	 */
	public double[][] getGridPoints() {
		final double[] len = getLengths();
		final double[] spt = getPointRef();
		int xGrids = (int) ((len[0] / xSpacing) + 0.5);
		int yGrids = (int) ((len[1] / ySpacing) + 0.5);
		double[] xLocs = new double[xGrids];
		double[] yLocs = new double[yGrids];

		if (xGrids != 0 && yGrids != 0) {
			xLocs[0] = spt[0] + xSpacing / 2.0;
			yLocs[0] = spt[1] + ySpacing / 2.0;

			for (int i = 1; i < xGrids; i++) {
				xLocs[i] = xLocs[0] + xSpacing * i;
			}
			for (int i = 1; i < yGrids; i++) {
				yLocs[i] = yLocs[0] + ySpacing * i;
			}
		}
		double[][] gp = new double[2][];
		gp[0] = xLocs;
		gp[1] = yLocs;
		return gp;
	}

	public double[][] getGridLines() {
		double[][] gp = getGridPoints();
		int xGrids = gp[0].length;
		int yGrids = gp[1].length;
		double[][] gl = new double[2][];

		if (getSpacing()[0] * xGrids >= getLengths()[0]) {
			xGrids--;
			if (xGrids < 0) {
				xGrids = 0;
			}
			gl[0] = new double[xGrids];
		} else {
			gl[0] = gp[0];
		}

		if (getSpacing()[1] * yGrids >= getLengths()[1]) {
			yGrids--;
			if (yGrids < 0) {
				yGrids = 0;
			}
			gl[1] = new double[yGrids];
		} else {
			gl[1] = gp[1];
		}

		for (int i = 0; i < xGrids; i++) {
			gl[0][i] = gp[0][i] + xSpacing / 2.0;
		}
		for (int i = 0; i < yGrids; i++) {
			gl[1][i] = gp[1][i] + ySpacing / 2.0;
		}
		return gl;
	}

	/**
	 * @return Returns the value for x-axis resolution
	 */
	public double getxSpacing() {
		return xSpacing;
	}

	/**
	 * @return Returns the value for y-axis resolution
	 */
	public double getySpacing() {
		return ySpacing;
	}

	/**
	 * @param xSpacing
	 *            Sets grid resolution for x-axis
	 * @param ySpacing
	 *            Sets grid resolution for y-axis
	 */
	public void setxySpacing(double xSpacing, double ySpacing) {
		this.xSpacing = xSpacing;
		this.ySpacing = ySpacing;
	}
	public void setxSpacing(double xSpacing) {
		this.xSpacing = xSpacing;
	}
	public void setySpacing(double ySpacing) {
		this.ySpacing = ySpacing;
	}

	/**
	 * @return Returns true if midpoints are enabled, false otherwise
	 */
	public boolean isMidPointOn() {
		return midPointOn;
	}

	/**
	 * @param midPointOn
	 *            Turns on display of midpoints
	 */
	public void setMidPointOn(boolean midPointOn) {
		this.midPointOn = midPointOn;
	}

	/**
	 * @return Returns true if gridpoints are enabled, false otherwise
	 */
	public boolean isGridLineOn() {
		return gridLinesOn;
	}

	/**
	 * @param gridLinesOn
	 *            Turns on display of gridpoints
	 */
	public void setGridLineOn(boolean gridLinesOn) {
		this.gridLinesOn = gridLinesOn;
	}

	public int getNumberOfPoints() {
		int[] dimensions = getDimensions();
		return dimensions[0] * dimensions[1];
	}

	public int[] getDimensions() {
		double[][] gp = getGridPoints();
		return new int[] { gp[0].length, gp[1].length };
	}

	/**
	 * returns an array of (x, y) tuples that represent the physical amount the motors 
	 * have to be driven relative to the current position (when taking the image) to drive 
	 * every point on the grid into the beam.
	 * 
	 * @return the tuples
	 */
	public double[][] getPhysicalGridPoints() {
		double[][] gp = getGridPoints();
		double[][] xyTuples = new double[getNumberOfPoints()][2];
		
		double cang = Math.cos(getAngle());
		double sang = Math.sin(getAngle());
		
		final double[] spt = getPointRef();
		double[] beam = getBeamCentre();
		double[] ppmm = new double[] { gridPref.getResolutionX(), gridPref.getResolutionY() };
		
		int i = 0;
		for (double x : gp[0]) {
			for (double y : gp[1]) {
				double[] tuple = xyTuples[i];
				tuple[0] = spt[0] + (x - spt[0]) * cang - (y - spt[1]) * sang;
				tuple[0] -= beam[0];
				tuple[0] /= ppmm[0];
				tuple[1] = spt[1] + (y - spt[1]) * cang + (x - spt[0]) * sang;
				tuple[1] -= beam[1];
				tuple[1] /= ppmm[1];
				i++;
			}
		}
		return xyTuples;
	}

	/**
	 * beam centre on camera image
	 * @return x and y pixels for beam centre
	 */
	public double[] getBeamCentre() {
		return new double[] { gridPref.getBeamlinePosX(), gridPref.getBeamlinePosY() };
	}
	
	/**
	 * pixel size in x and y in m 
	 * @return pixel size in x and y in m
	 */
	public double[] getPixelSizeM() {
		return new double[] { 0.001/gridPref.getResolutionX(), 0.001/gridPref.getResolutionY() };
	}

	@Override
	public String toString() {
		return super.toString() + String.format(", spacing=[%g, %g]", xSpacing, ySpacing);
	}

	public GridPreferences getGridPreferences() {
		return gridPref;
	}

	public void setGridPreferences(GridPreferences gridPref) {
		this.gridPref = gridPref;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (gridLinesOn ? 1231 : 1237);
		result = prime * result + ((gridPref == null) ? 0 : gridPref.hashCode());
		result = prime * result + (midPointOn ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(xSpacing);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ySpacing);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		GridROI other = (GridROI) obj;
		if (gridLinesOn != other.gridLinesOn)
			return false;
		if (gridPref == null) {
			if (other.gridPref != null)
				return false;
		} else if (!gridPref.equals(other.gridPref))
			return false;
		if (midPointOn != other.midPointOn)
			return false;
		if (Double.doubleToLongBits(xSpacing) != Double.doubleToLongBits(other.xSpacing))
			return false;
		if (Double.doubleToLongBits(ySpacing) != Double.doubleToLongBits(other.ySpacing))
			return false;
		return true;
	}
}
