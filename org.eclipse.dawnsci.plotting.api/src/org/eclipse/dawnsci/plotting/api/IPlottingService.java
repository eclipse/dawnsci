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
package org.eclipse.dawnsci.plotting.api;

import org.eclipse.dawnsci.plotting.api.filter.IFilterDecorator;
import org.eclipse.dawnsci.plotting.api.tool.IToolPageSystem;

/**
 * This is a service for wrapping PlottingFactory for
 * those that prefer to use a service rather than a 
 * factory.
 * @author Matthew Gerring
 *
 */
public interface IPlottingService {

	/**
	 * Use this method to create a new plotting system.
	 * This system may then be used to create plotting
	 * on an SWT component by calling createPlotPart(...)
	 * 
	 * @return
	 * @throws Exception
	 */
	public <T> IPlottingSystem<T> createPlottingSystem() throws Exception;

	/**
	 * Get a plotting system. (Same as getPlottingSystem(name, false))
	 * 
	 * @param plotName
	 * @return
	 */
	public <T> IPlottingSystem<T> getPlottingSystem(String plotName);
	
	/**
	 * Get a plotting system from the registered plotting system list.
	 * 
	 * @param plotName
	 * @param threadSafe - if true, the plotting system will be wrapped in a class which ensures all
	 * calls are thread safe.
	 * @return
	 */
	public <T> IPlottingSystem<T> getPlottingSystem(String plotName, boolean threadSafe);
	/**
	 * 
	 * Return a tool system for a given plot name
	 * (The actual implementation uses the getAdapter(IToolPageSystem.class)
	 * call on to IPlottingSystem.)
	 * 
	 * @param plotName
	 * @return
	 */
	public IToolPageSystem getToolSystem(String plotName);
	
	
	/**
	 * Register a plotting system. Usually there is no need to call this as AbstractPlottingSystem
	 * will register automatically.
	 * 
	 * @param plotName
	 * @param system
	 * @return
	 */
	public <T> IPlottingSystem<T> registerPlottingSystem(final String plotName, final IPlottingSystem<T> system);

	/**
	 * 
	 * @param plotName
	 * @return
	 */
	public <T> IPlottingSystem<T> removePlottingSystem(String plotName);

	
	/**
	 * A plotting system may have a filter decorator registered with it which
	 * can interscept and modify data before it is plotted.
	 * 
	 * @param system
	 * @return
	 */
	public <T> IFilterDecorator createFilterDecorator(IPlottingSystem<T> system);

	/**
	 * Add a listener to be notified when a new plotting system is registered.
	 * @param l
	 */
	public void addRegistrationListener(IPlotRegistrationListener l);
	
	/**
	 * Remove a plot registration listener.
	 * @param l
	 */
	public void removeRegistrationListener(IPlotRegistrationListener l);
	
}
