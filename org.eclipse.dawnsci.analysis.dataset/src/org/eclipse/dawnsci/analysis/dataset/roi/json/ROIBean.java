/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.dataset.roi.json;


/**
 * ROI bean used to marshall/unmarshall to / from JSON strings <br>
 * A converter can be used to convert these bean to IROI<br> 
 * (See uk.ac.diamond.scisoft.analysis.persistence.bean.roi.ROIBeanConverter) 
 */
public abstract class ROIBean {

	protected String type;

	protected String name;

	protected double startPoint[]; // start or centre coordinates

	public ROIBean(){
		
	}

	/**
	 * Returns the type of roibean
	 * @return type
	 */
	public String getType(){
		return type;
	}

	/**
	 * Returns the ROI name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the start (or centre) point x[0] and y[1]
	 * @return startPoint
	 */
	public double[] getStartPoint() {
		return startPoint;
	}

	/**
	 * Set the type of roibean
	 * @param type
	 */
	public void setType(String type){
		this.type = type;
	}

	/**
	 * Set the name
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Set the Start point of the ROI x,y coordinates with x=[0] and y=[1]
	 * @param startPoint
	 */
	public void setStartPoint(double[] startPoint){
		this.startPoint = startPoint;
	}

}
