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



/**
 * <code>
 * 
 * myIPlottingFilter1 = new AbstactPlottingFilter() {
 * 
 *     public int getRank() {
 *         return 1;
 *     }
 *     protected IDataset[] filter(IDataset x,    IDataset y) {
 *         return new IDataset[]{null, Maths.square(y));
 *     }
 * }
 * 
 * IFilterDecorator dec = PlottingFactory.createFilterDecorator(IPlottingSystem)
 * dec.addFilter(myIPlottingFilter1);
 * // Maybe, dec.addFilter(myIPlottingFilter2);
 * 
 * // later, if you need to.
 * dec.reset();
 * 
 * </code>
 * 
 * @author Matthew Gerring
 *
 */
public interface IFilterDecorator {

	/**
	 * Add a filter to the decorator.
	 * @param filter
	 */
	public void addFilter(IPlottingFilter filter);
	
	/**
	 * Add a filter to the decorator.
	 * @param filter
	 */
	public void removeFilter(IPlottingFilter filter);
	
	/**
	 * Sets the decorator active (by default it will be).
	 * 
	 * Setting inactive will leave any filtered data plotted
	 * but process no more filters. Call clear() reset any
	 * existing filters and clear the list of filters.
	 * 
	 * @param isActive
	 */
	public void setActive(boolean isActive);

	/**
	 * @return true if active
	 */
	public boolean isActive();

	/**
	 * Resets data to be as if no filter has been done, then clears all filters
	 * in this decorator. More filters can be added again later.
	 */
	public void clear();
	
	/**
	 * Disposes of this IFilterDecorator. It cannot
	 * be used after dispose has been called.
	 */
	public void dispose();
	
	/**
	 * Applies a new filter on existing data. By default when a filter is
	 * created, all the data plotted is left alone and only new data plotted,
	 * will have the filter applied. Calling this method applies the filter to 
	 * existing plots as well.
	 * 
	 * It does not refilter existing filtered traces, if any.
	 */
	public void apply();

	/**
	 * Replaces the data of the plot with unfiltered data leaving all of the
	 * filters active.
	 */
	public void reset();
}
