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
package org.eclipse.dawnsci.plotting.api.trace;

import java.util.Collection;

/**
 * No methods in this interface are thread safe.
 * 
 * @author Matthew Gerring
 *
 */
public interface ITraceSystem {
	
	
	/**
	 * Creates a line trace used for 1D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * @param traceName
	 * @return
	 */
	public IImageTrace createImageTrace(String traceName);
	
	/**
	 * Creates a line trace used for 1D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * @param traceName
	 * @return
	 */
	public ICompositeTrace createCompositeTrace(String traceName);

	/**
	 * Creates a line trace used for 1D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * @param traceName
	 * @return
	 */
	public ILineTrace createLineTrace(String traceName);

	/**
	 * Creates a vector trace which may be added to a 1D or 2D plot.
	 * The Vector trace will draw arrows over the plot at various locations.
	 * 
	 * @param traceName
	 * @return
	 */
	public IVectorTrace createVectorTrace(String traceName);

	/**
	 * Creates a surface trace used for 3D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * @param traceName
	 * @return
	 */
	public ISurfaceTrace createSurfaceTrace(String traceName);

    /**
     * 
     * @param string
     * @return
     */
	public IIsosurfaceTrace createIsosurfaceTrace(String string);

	/**
	 * Creates a multi image trace. This does not add the trace
	 * or give it any data.
	 * 
	 * @param traceName
	 * @return
	 */
	public IMulti2DTrace createMulti2DTrace(String traceName);

	/**
	 * Creates a stack trace used for 3D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * A stack trace is a 3D set of lines.
	 * 
	 * @param traceName
	 * @return
	 */
	public ILineStackTrace createLineStackTrace(String traceName);

	/**
	 * Creates a scatter 3D plot trace used for 3D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * @param traceName
	 * @return
	 */
	public IScatter3DTrace createScatter3DTrace(String traceName);

	/**
	 * Creates a stack trace used for 2D plotting. This does not add the trace
	 * or give it any data.
	 * 
	 * A stack trace is a 3D set of images. Only one image is visible at a time
	 * in the 2D plot.
	 * 
	 * @param traceName
	 * @return
	 */
	public IImageStackTrace createImageStackTrace(String traceName);

	/**
	 * Adds and plots the trace. Not Thread safe
	 * @param trace
	 */
	public void addTrace(ITrace trace);

	/**
	 * Adds and plots the trace.
	 * @param trace
	 */
	public void removeTrace(ITrace trace);

    /**
     * return a plotted trace by name.
     * @param name
     * @return
     */
	public ITrace getTrace(String name);
	
	/**
	 * Call this method to retrieve what is currently plotted.
	 * See all ITraceListener.
	 * 
	 * @return
	 */
	public Collection<ITrace> getTraces();
	
	/**
	 * Remove the currently plotted traces. Thread safe
	 */
	public void clearTraces();

	/**
	 * Call this method to retrieve what is currently plotted by trace type
	 * See all ITraceListener.
	 * 
	 * @return
	 */
	public Collection<ITrace> getTraces(Class<? extends ITrace> clazz);
	

	/**
	 * Add a listener to be notified of new traces plotted
	 * @param l
	 */
	public void addTraceListener(final ITraceListener l);
	
	/**
	 * Remove listener to avoid memory leaks
	 * @param l
	 */
	public void removeTraceListener(final ITraceListener l);
	
	/**
	 * Renames the trace, better than calling setName on the ITrace as the
	 * collection of traces is updated properly. No event will be fired.
	 */
	public void renameTrace(ITrace trace, String name) throws Exception;

	/**
	 * Moves the trace definition in the internal hastable. 
	 * Used internally only - do not call.
	 * @internal
	 */
	public void moveTrace(String oldName, String name);

	
	/**
	 * For 1D plotting, if the first plot is the x-axis, returns true.
	 * @return
	 */
	public boolean isXFirst();

	/**
	 * Set if the first plot is the x-axis.
	 * @param xFirst
	 */
	public void setXFirst(boolean xFirst);

	/**
	 * Notify that the trace is about to plot. Internal use only.
	 * @internal
	 * @param evt
	 */
	public void fireWillPlot(final TraceWillPlotEvent evt);
	
	/**
	 * May be used to force a trace to fire update listeners in the plotting system. Internal use only.
	 * @internal
	 * @param evt
	 */
	public void fireTraceUpdated(final TraceEvent evt);
	
	/**
	 * Call to notify that a trace has been added. Internal use only.
	 * @internal
	 * @param evt
	 */
	public void fireTraceAdded(final TraceEvent evt);
}

