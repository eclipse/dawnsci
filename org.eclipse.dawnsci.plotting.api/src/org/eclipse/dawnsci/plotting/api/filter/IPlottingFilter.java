/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.filter;

import java.util.List;

import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.TraceWillPlotEvent;

public interface IPlottingFilter {

	/**
	 * You may override the 
	 * @param system
	 * @param trace
	 */
	public void filter(IPlottingSystem system, TraceWillPlotEvent trace);
	
	/**
	 * Replaces any filtered traces we have made with original data
	 * and leaves this filter active.
	 * 
	 * If this is the first filter in the chain, it will reset the data
	 * of the trace to original data. If not the data will be reset to 
	 * how it was before this filter was called - i.e. the originally passed
	 * in data to the filter.
	 * 
	 * If you are implementing IPlottingFilter, use the AbstractPlottingFilter
	 * version of reset.
	 */
	public void reset();
	
	/**
	 * The data rank to filter. Currently you must implement this as either 1 or 2. 1
	 * for XY plots and 2 for 2D data like images and surfaces.
	 * @return the rank of data to apply this filter to.
	 */
	public int getRank();
	
	/**
	 * 
	 * @return true if filter active.
	 */
	public boolean isActive();

	/**
	 * Sets whether the filter is live without undoing any previous filtering.
	 * 
	 * @param active
	 */
	public void setActive(boolean active);
	
	/**
	 * 
	 * @return a list of traces already processed by this filter.
	 */
	public List<ITrace> getFilteredTraces();
	
	/**
	 * Called when the plotting system is disposed and the filter is
	 * no longer active.
	 */
	public void dispose();

}
