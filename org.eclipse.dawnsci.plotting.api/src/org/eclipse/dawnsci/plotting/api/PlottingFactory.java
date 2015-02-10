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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dawnsci.plotting.api.filter.IFilterDecorator;
import org.eclipse.dawnsci.plotting.api.remote.ThreadSafePlottingSystem;
import org.eclipse.dawnsci.plotting.api.tool.IToolPageSystem;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PlottingFactory is a way to create an IPlottingSystem. 
 * 
 * You can use the factory directly or conjure the service
 * from the magic of OSGI.
 * 
 * @author Matthew Gerring
 *
 */
public class PlottingFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(PlottingFactory.class);
	
	/**
	 * This class has a public constructor so that the squish tests can get a references using
	 * the class loader. Really it should be private. 
	 * 
	 * In the squish tests there is a script called 'use_case_utils.py' with a def called getPlottingSystem(...)
	 * which requires this to be there.
	 * 
	 */
	public PlottingFactory() {
		
	}
	
	/**
	 * Reads the extension points for the plotting systems registered and returns
	 * a plotting system based on the users current preferences.
	 * 
	 * @return
	 */
	public static IPlottingSystem createPlottingSystem() throws Exception {
				
		final ScopedPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE,"org.dawb.workbench.ui");
		String plotType = store.getString("org.dawb.plotting.system.choice");
		if (plotType.isEmpty()) plotType = System.getProperty("org.dawb.plotting.system.choice");// For Geoff et. al. can override.
		if (plotType==null) plotType = "org.dawb.workbench.editors.plotting.lightWeightPlottingSystem"; // That is usually around
		
        IPlottingSystem system = createPlottingSystem(plotType);
        if (system!=null) return system;
		
        IConfigurationElement[] systems = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.plotting.api.plottingClass");
        IPlottingSystem ifnotfound = (IPlottingSystem)systems[0].createExecutableExtension("class");
		store.setValue("org.dawb.plotting.system.choice", systems[0].getAttribute("id"));
		return ifnotfound;
		
	}
	
	/**
	 * Always returns the light weight plotter if one is available, otherwise null.
	 * 
	 * @return
	 */
	public static IPlottingSystem getLightWeightPlottingSystem() throws Exception {
				
		return  createPlottingSystem("org.dawb.workbench.editors.plotting.lightWeightPlottingSystem");		
	}
	
	private static final IPlottingSystem createPlottingSystem(final String plottingSystemId) throws CoreException {
		
        IConfigurationElement[] systems = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.plotting.api.plottingClass");
        for (IConfigurationElement ia : systems) {
			if (ia.getAttribute("id").equals(plottingSystemId)) return (IPlottingSystem)ia.createExecutableExtension("class");
		}
		
        return null;
	}

	
	private static Map<String, IPlottingSystem> plottingSystems;
	
	/**
	 * Removes a plot system from the registered names.
	 * @param plotName
	 * @return the removed system
	 */
	public static IPlottingSystem removePlottingSystem(String plotName) {
		if (filterCache!=null && filterCache.containsKey(plotName)) {
			final List<IFilterDecorator> decorators = filterCache.remove(plotName);
			for (IFilterDecorator decorator : decorators) decorator.dispose();
		}
		if (plottingSystems==null) return null;
		return plottingSystems.remove(plotName);
	}

	/**
	 * Registers a plotting system by name. NOTE if the name is already used this
	 * will overwrite the old one!
	 * 
	 * @param plotName
	 * @param abstractPlottingSystem
	 * @return the replaced system if any or null otherwise.
	 */
	public static IPlottingSystem registerPlottingSystem(final String                 plotName,
			                                             final IPlottingSystem abstractPlottingSystem) {
		
		
		if (plottingSystems==null) plottingSystems = new HashMap<String, IPlottingSystem>(7);
		return plottingSystems.put(plotName, abstractPlottingSystem);
	}
		
	/**
	 * Get a plotting system by name. NOTE if more than one plotting system has the same name the
	 * last one registered with this name is returned.
	 * 
	 * NOTE an AbstractPlottingSystem is also a IToolPageSystem, you can get tool pages here.
	 * 
	 * @param plotName
	 * @return AbstractPlottingSystem or null
	 */
	public static IPlottingSystem getPlottingSystem(String plotName) {
		return getPlottingSystem(plotName, false);
	}
	
	/**
	 * Get a plotting system by name. NOTE if more than one plotting system has the same name the
	 * last one registered with this name is returned.
	 * 
	 * NOTE an AbstractPlottingSystem is also a IToolPageSystem, you can get tool pages here.

	 * @param plotName
	 * @param threadSafe - set if all the methods on the plotting system should be thread safe.
	 *                     Generally used for plotting systems on servers.
	 * @return
	 */
	public static IPlottingSystem getPlottingSystem(String plotName, boolean threadSafe) {
		if (plottingSystems==null) return null;
		IPlottingSystem ps = plottingSystems.get(plotName);
	    try {
			return threadSafe ? new ThreadSafePlottingSystem(ps) : ps;
		} catch (Exception e) {
			if (threadSafe) {
				logger.error("Cannot create thread safe system, will return UI thread one.", e);
			}
			return ps;
		}
	}

	/**
	 * Get a tool page system by name (normally a plotting system is also a toolPage system).
	 * @param plotName
	 * @return
	 */
	public static IToolPageSystem getToolSystem(String plotName) {
		if (plottingSystems==null) return null;
		return (IToolPageSystem)plottingSystems.get(plotName).getAdapter(IToolPageSystem.class);
	}

	/**
	 * Get all the registered plotting systems.
	 * @internal
	 * @return
	 */
	public static IPlottingSystem[] getPlottingSystems() {
		if (plottingSystems==null) return null;
		return plottingSystems.values().toArray(new IPlottingSystem[plottingSystems.size()]);
	}
	
	private static Map<String,List<IFilterDecorator>> filterCache;
	/**
	 * 
	 * @param system
	 * @return a new decorator which will filter the data being plotting by the system.
	 */
	public static IFilterDecorator createFilterDecorator(IPlottingSystem system) {
		if (filterCache==null) filterCache = new HashMap<String, List<IFilterDecorator>>();
		List<IFilterDecorator> decorators = (List<IFilterDecorator>)filterCache.get(system.getPlotName());
		if (decorators==null) {
			decorators = new ArrayList<IFilterDecorator>();
			filterCache.put(system.getPlotName(), decorators);
		}
		FilterDecoratorImpl dec = new FilterDecoratorImpl(system);
		decorators.add(dec);
		return dec;
	}
}
