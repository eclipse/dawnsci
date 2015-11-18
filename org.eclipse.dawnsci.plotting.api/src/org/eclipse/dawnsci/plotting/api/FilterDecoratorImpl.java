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
import java.util.Collection;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.filter.IFilterDecorator;
import org.eclipse.dawnsci.plotting.api.filter.IPlottingFilter;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;
import org.eclipse.dawnsci.plotting.api.trace.TraceWillPlotEvent;
import org.eclipse.swt.widgets.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is not intended to be accessed directly.
 * 
 * Instead use:
 * <code>
 * IFilterDecorator dec = PlottingFactory.createFilterDecorator(IPlottingSystem<Composite>)
 * dec.addFilter(myFilter extends AbstractPlottingFilter);
 * </code>
 * 
@internal
**/
class FilterDecoratorImpl implements IFilterDecorator {
	
	private static final Logger logger = LoggerFactory.getLogger(FilterDecoratorImpl.class);
	
	private IPlottingSystem<?>     system;
	private List<IPlottingFilter>  filters;
	private boolean filterActive      = true;
	private boolean processingAllowed = true;
    private boolean isDisposed        = false;
    
    private ITraceListener listener;
    
	FilterDecoratorImpl(final IPlottingSystem<?> system) {
		this.system  = system;
		this.filters = new ArrayList<IPlottingFilter>(3);
		
		listener = new ITraceListener.Stub() {
			@Override
			public void traceWillPlot(TraceWillPlotEvent evt) {
				
				IDataset       xo  = evt.getXData();
				IDataset       yo  = evt.getYData();
				IDataset       imo = evt.getImage();
				List<IDataset> axo = evt.getAxes();
				
				if (!filterActive)       return;
				if (!processingAllowed)  return;
				if (system.isDisposed()) return;
				if (evt.getTrace() != null && !evt.getTrace().isUserTrace()) return;
				try {
					process(evt);
				} catch (Exception e) {
					logger.error("Exception processing filter in list! All filters removed.", e);
					evt.setImageData(imo, axo);
					evt.setLineData(xo, yo);
				}
			}
		};
		system.addTraceListener(listener);
	}

	protected void process(TraceWillPlotEvent evt) throws Exception {
		if (filters.isEmpty()) return;
		for (IPlottingFilter filter : filters) {
			final ITrace trace = (ITrace)evt.getSource();
			if (trace.getRank()!=filter.getRank()) {
				if (filter.getRank()>0) continue;
			}
			filter.filter(system, evt);
		}
	}

	@Override
	public void addFilter(IPlottingFilter filter) {
		if (isDisposed) throw new RuntimeException("IFilterDecorator is disposed!");
		if (filters.contains(filter))  throw new RuntimeException("The filter is already added!");
		filters.add(filter);
	}

	@Override
	public void removeFilter(IPlottingFilter filter) {
		if (isDisposed) throw new RuntimeException("IFilterDecorator is disposed!");
		try {
			processingAllowed = false;
			filter.reset();
		} finally {
			processingAllowed = true;
		}
		filters.remove(filter);
		filter.dispose();
	}

	@Override
	public void setActive(boolean isActive) {
		this.filterActive = isActive;
	}

	@Override
	public boolean isActive() {
		return filterActive;
	}

	@Override
	public void clear() {
		reset();
		filters.clear();
		
		if (!system.isDisposed()) system.repaint();
	}

	@Override
	public void reset() {
		try {
			processingAllowed = false;
			if (filters.size()>0) filters.get(0).reset(); // Puts data back to original data.
		} finally {
			processingAllowed = true;
		}
	}
	

	@Override
	public void apply() {
		final Collection<ITrace> traces = system.getTraces();
		
		final Collection<ITrace> existing = getExistingFilteredTraces();
		for (ITrace trace : traces) {
			if (trace instanceof ILineTrace) {
				ILineTrace lt = (ILineTrace)trace;
				lt.setData(lt.getXData(), lt.getYData());
				
			} else if (trace instanceof IImageTrace) {
				IImageTrace it = (IImageTrace)trace;
				it.setData(it.getData(), it.getAxes(), false);
			}
		}
		
		system.repaint();
		
	}



	private Collection<ITrace> getExistingFilteredTraces() {
		if (filters.isEmpty()) return null;
        final IPlottingFilter filter = filters.get(0);
		return filter.getFilteredTraces();
	}

	@Override
	public void dispose() {
		for (IPlottingFilter filter : filters) filter.dispose();
		isDisposed = true;
		clear();
		system.removeTraceListener(listener);
	}
	
}
