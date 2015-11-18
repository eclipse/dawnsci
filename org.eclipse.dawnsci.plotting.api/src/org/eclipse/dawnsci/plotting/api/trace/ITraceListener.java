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

import java.util.EventListener;


public interface ITraceListener extends EventListener{

	/**
	 * Fired whenever a new trace is created, the source of TraceEvent
	 * is the ITrace created.
	 * 
	 * @param evt
	 */
	public void traceCreated(final TraceEvent evt);
	
	/**
	 * Fired whenever an existing trace is updated, the source of TraceEvent
	 * is the ITrace created.
	 * 
	 * @param evt
	 */
	public void traceUpdated(final TraceEvent evt);

	/**
	 * Fired whenever a new trace is added, the source of TraceEvent
	 * is the ITrace added.
	 * 
	 * @param evt
	 */
	public void traceAdded(final TraceEvent evt);

	/**
	 * Fired whenever a  trace is deleted, the source of TraceEvent
	 * is the ITrace.
	 * 
	 * @param evt
	 */
	public void traceRemoved(final TraceEvent evt);

	/**
	 * Notifies the listener that the plot technology
	 * requires the data to be resent back to the plot.
	 * 
	 * For instance the user has reconfigured the plot and
	 * the data should be sent again.
	 * 
	 * It is done this way to avoid caches of the plot data,
	 * which may be large, being made.
	 * 
	 *  Source of event is IPlottingSystem<Composite>
	 * 
	 * @param evt
	 */
	public void tracesUpdated(final TraceEvent evt);
	
	/**
	 * Called when all traces are cleared. Source of event is IPlottingSystem<Composite>
	 * @param evet
	 */
	public void tracesRemoved(TraceEvent evet);
	
	/**
	 * Fired when new traces are plotted. Source of event is ITrace or List<ITrace>
	 * @param evt
	 */
	public void tracesAdded(TraceEvent evt);
	
	
	
	
	/**
	 * Fired whenever a new trace is about to be plotted or replotted.
	 * This is normally from add or update events. The event data will
	 * always be the ITrace which is about to be plotted.
	 * 
	 * The source of TraceWillPlotEvent will be the Trace.
	 * 
	 * There are getter and setter methods for the data. Use the setter
	 * methods to change the data in the trace which will be plotted.
	 * 
	 * @param evt
	 */
	public void traceWillPlot(final TraceWillPlotEvent evt);

	
	/**
	 * Convenience class for creating listeners
	 * @author Matthew Gerring
	 *
	 */
	public class Stub implements ITraceListener {


		@Override
		public void traceWillPlot(TraceWillPlotEvent evt) {
			// Does not all update(...) intentionally.
		}
		
		@Override
		public void tracesUpdated(TraceEvent evt) {
			update(evt);
		}

		@Override
		public void tracesRemoved(TraceEvent evt) {
			update(evt);
		}

		@Override
		public void tracesAdded(TraceEvent evt) {
			update(evt);
		}

		@Override
		public void traceCreated(TraceEvent evt) {
			update(evt);
		}

		@Override
		public void traceUpdated(TraceEvent evt) {
			update(evt);
		}

		@Override
		public void traceAdded(TraceEvent evt) {
			update(evt);
		}

		@Override
		public void traceRemoved(TraceEvent evt) {
			update(evt);
		}
		
		protected void update(TraceEvent evt) {
			// Nothing
		}
		
	}
}
