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
import java.util.List;

public class PolygonalROIBean extends ROIBean{

	public static final String TYPE = "PolygonalROI";
	private List<double[]> points;

	public PolygonalROIBean(){
		type = TYPE;
	}

	/**
	 * Set the list of points 
	 * @param points
	 */
	public void setPoints(List<double[]> points){
		this.points = points;
	}

	/**
	 * Returns the list of points (x[0] and y[1] coordinates)
	 * @return points
	 */
	public List<double[]> getPoints(){
		return points;
	}

	@Override
	public String toString(){
		return String.format("{\"type\": \"%s\", \"name\": \"%s\", \"startPoint\": \"%s\", \"points\": \"%s\"}", 
				type, name, Arrays.toString(startPoint), Arrays.toString(points.toArray()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((points == null) ? 0 : points.hashCode());
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
		PolygonalROIBean other = (PolygonalROIBean) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}

}
