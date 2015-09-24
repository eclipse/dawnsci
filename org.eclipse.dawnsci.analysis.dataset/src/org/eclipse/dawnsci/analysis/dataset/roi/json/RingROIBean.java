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

public class RingROIBean extends ROIBean {

	public static final String TYPE = "RingROI";

	protected double[] radii; // radii

	protected double dpp; // Sampling rate used for profile calculations in dots per pixel

	private boolean clippingCompensation; // compensate for clipping
	private boolean averageArea;

	public RingROIBean(){
		type = TYPE;
	}

	/**
	 * Set the radii
	 * @param radii
	 */
	public void setRadii(double[] radii){
		this.radii = radii;
	}

	/**
	 * Set sampling rate used in profile calculations  
	 * 
	 * @param dpp
	 *			sampling rate in dots per pixel; 
	 */
	public void setDpp(double dpp) {
		this.dpp = dpp;
	}

	/**
	 * Returns the radii
	 * @return radii
	 */
	public double[] getRadii(){
		return radii;
	}

	/**
	 * Return sampling rate used in profile calculations
	 * 
	 * @return
	 * 			sampling rate in dots per pixel
	 */
	public double getDpp() {
		return dpp;
	}

	public boolean isClippingCompensation() {
		return clippingCompensation;
	}

	public void setClippingCompensation(boolean clippingCompensation) {
		this.clippingCompensation = clippingCompensation;
	}

	public boolean isAverageArea() {
		return averageArea;
	}

	public void setAverageArea(boolean averageArea) {
		this.averageArea = averageArea;
	}

	@Override
	public String toString(){
		return String.format("{\"type\": \"%s\", \"name\": \"%s\", \"startPoint\": \"%s\", \"radii\": \"%s\", \"dpp\": \"%s\"}", 
				type, name, Arrays.toString(startPoint), Arrays.toString(radii), dpp);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (averageArea ? 1231 : 1237);
		result = prime * result + (clippingCompensation ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(dpp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(radii);
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
		RingROIBean other = (RingROIBean) obj;
		if (averageArea != other.averageArea)
			return false;
		if (clippingCompensation != other.clippingCompensation)
			return false;
		if (Double.doubleToLongBits(dpp) != Double.doubleToLongBits(other.dpp))
			return false;
		if (!Arrays.equals(radii, other.radii))
			return false;
		return true;
	}

}

