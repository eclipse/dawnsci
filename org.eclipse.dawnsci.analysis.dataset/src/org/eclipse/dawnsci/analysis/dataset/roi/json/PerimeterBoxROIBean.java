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

public class PerimeterBoxROIBean extends ROIBean {

	public static final String TYPE = "PerimeterBoxROI";

	private double[] lengths; // width and height

	private double angle;   // angle in radians

	private double[] endPoint; // end point

	public PerimeterBoxROIBean(){
		type = TYPE;
	}

	/**
	 * Returns the lengths (width[0] and height[1])
	 * @return double[]
	 */
	public double[] getLengths(){
		return lengths;
	}

	/**
	 * Returns the angle
	 * @return double
	 */
	public double getAngle(){
		return angle;
	}

	/**
	 * Returns the End point of the rectangle
	 * @return double[]
	 */
	public double[] getEndPoint(){
		return endPoint;
	}

	/**
	 * Set the width[0] and height[1] 
	 * @param lengths
	 */
	public void setLengths(double[] lengths){
		this.lengths = lengths;
	}

	/**
	 * Set the angle
	 * @param angle
	 */
	public void setAngle(double angle){
		this.angle = angle;
	}

	/**
	 * Set the end point of the Rectangle
	 * @param endPoint
	 */
	public void setEndPoint(double[] endPoint){
		this.endPoint = endPoint;
	}

	@Override
	public String toString(){
		return String.format("{\"type\": \"%s\", \"name\": \"%s\", \"startPoint\": \"%s\", \"endPoint\": \"%s\", \"angle\": \"%s\"}", 
				type, name, Arrays.toString(startPoint), Arrays.toString(endPoint), angle);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(endPoint);
		result = prime * result + Arrays.hashCode(lengths);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerimeterBoxROIBean other = (PerimeterBoxROIBean) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		if (!Arrays.equals(endPoint, other.endPoint))
			return false;
		if (!Arrays.equals(lengths, other.lengths))
			return false;
		return true;
	}

}
