/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.plotting.api.area;

import java.util.Collection;
import java.util.List;

import org.eclipse.dawnsci.plotting.api.annotation.IAnnotation;
import org.eclipse.dawnsci.plotting.api.region.IRegion;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;

/**
 * This interface describes any plotting area with particular area components
 * and/or characteristics (traces, annotations, zoom type, etc)
 */
public interface IPlotArea {

	/**
	 * @return the list of traces
	 */
	public List<ITrace> getTraces();

	/**
	 * Removes the given trace
	 * @param trace
	 */
	public void removeTrace(ITrace trace);

	/**
	 * @return the list of annotations
	 */
	public List<IAnnotation> getAnnotations();

	/**
	 * Removes the given annotation
	 * @param annotation
	 */
	public void removeAnnotation(IAnnotation annotation);

	/**
	 * @return the active zoom option/type
	 */
	public ZoomOption getZoomOption();

	/**
	 * Adds a listener to the plot area
	 * @param listener
	 * @return true if this collection changed as a result of the call
	 */
	public boolean addImageTraceListener(ITraceListener listener);

	/**
	 * Removes the given listener from the plot area
	 * @param listener
	 * @return true if an element was removed as a result of this call
	 */
	public boolean removeImageTraceListener(ITraceListener listener);

	/**
	 * @return the image trace if there is any, null otherwise
	 */
	public IImageTrace getImageTrace();

	/**
	 * @return a collection of all the region names
	 */
	public Collection<String> getRegionNames();

	/**
	 * @return a list of all regions
	 */
	public List<IRegion> getRegions();

	/**
	 * Get the region with given name
	 * @param name
	 * @return region
	 */
	public IRegion getRegion(String name);

}
