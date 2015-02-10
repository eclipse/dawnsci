/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.dataset.roi.json;

import java.util.Arrays;

public class GridROIBean extends ROIBean{

	public static final String TYPE = "GridROI";
	private double[] lengths; // width and height
	private double angle;   // angle in radians
	private double[] endPoint; // end point
	private double xSpacing = 50;
	private double ySpacing = 50;
	private boolean midPointOn = true;
	private boolean gridLinesOn = false;

	public GridROIBean(){
		type = TYPE;
	}

	public double[] getLengths() {
		return lengths;
	}

	public void setLengths(double[] lengths) {
		this.lengths = lengths;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double[] getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(double[] endPoint) {
		this.endPoint = endPoint;
	}

	public double getxSpacing() {
		return xSpacing;
	}

	public void setxSpacing(double xSpacing) {
		this.xSpacing = xSpacing;
	}

	public double getySpacing() {
		return ySpacing;
	}

	public void setySpacing(double ySpacing) {
		this.ySpacing = ySpacing;
	}

	public boolean isMidPointOn() {
		return midPointOn;
	}

	public void setMidPointOn(boolean midPointOn) {
		this.midPointOn = midPointOn;
	}

	public boolean isGridLinesOn() {
		return gridLinesOn;
	}

	public void setGridLinesOn(boolean gridLinesOn) {
		this.gridLinesOn = gridLinesOn;
	}


	@Override
	public String toString(){
		return String.format("{\"type\": \"%s\", \"name\": \"%s\", \"startPoint\": \"%s\", \"endPoint\": \"%s\", \"angle\": \"%s\"}", 
				type, name, Arrays.toString(startPoint), Arrays.toString(getEndPoint()), getAngle());
	}

}
