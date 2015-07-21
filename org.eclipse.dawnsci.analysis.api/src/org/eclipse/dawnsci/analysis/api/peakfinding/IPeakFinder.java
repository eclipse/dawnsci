/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.peakfinding;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

public interface IPeakFinder {
	
	/**
	 * Get the name of the peak finding method.
	 * 
	 * @return peak finder's name
	 */
	public String getName();
	
	/**
	 * Get the map of parameters which can be set for this method.
	 * 
	 * @return <String name, Number value> map of all parameters available 
	 * to control this IPeakFinder. 
	 */
	public Map<String, IPeakFinderParameter> getParameters();
	
	/**
	 * Get a named parameter from the set of parameters.
	 * 
	 * @param pName name of the parameter in the map
	 * @return parameter value
	 */
	public IPeakFinderParameter getParameter(String pName) throws Exception;
	
	/**
	 * Gets the current numerical value of the named parameter
	 * @param pName String name of parameter
	 * @return Number value of the parameter
	 * @throws Exception
	 */
	public Number getParameterValue(String pName) throws Exception;
	
	/**
	 * Change the current value of a named parameter with a parameter
	 * @param pName String name of parameter
	 * @param param IPeakFinderParameter object
	 * @throws Exception
	 */
	public void setParameter(String pName, IPeakFinderParameter param) throws Exception;
	
	/**
	 * Change the current value of a named parameter to the given value.
	 * 
	 * @param pName name of the parameter to update
	 * @param pValue parameter value
	 */
	public void setParameterValue(String pName, Number pValue) throws Exception;
	
	/**
	 * An implementation of an algorithm capable of identifying a number peaks
	 * within a one dimensional dataset. As its input, this method takes the
	 * dataset and two optional arguments, the x-coordinate dataset and the
	 * maximum number of peaks to find.
	 * This algorithm may have a set of additional parameters to control its
	 * behaviour, which may be accessed/changed using the getters/setters of 
	 * this class.
	 * 
	 * @param xData x-coordinate dataset
	 * @param yData histogram/distribution dataset to find peaks in
	 * @param nPeaks maximum number of peaks to find
	 * @return A set containing all or a number of peaks found by this IPeakFinder
	 */
	public Map<Integer, Double> findPeaks(IDataset xData, IDataset yData, Integer nPeaks);
	
	/**
	 * Restore peak finder parameters to the default values of this class
	 */
	public void resetParameters();
}
