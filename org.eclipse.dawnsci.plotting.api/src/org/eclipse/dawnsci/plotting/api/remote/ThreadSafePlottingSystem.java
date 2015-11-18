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
package org.eclipse.dawnsci.plotting.api.remote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.IPlotActionSystem;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.annotation.IAnnotation;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.dawnsci.plotting.api.axis.IClickListener;
import org.eclipse.dawnsci.plotting.api.axis.IPositionListener;
import org.eclipse.dawnsci.plotting.api.region.IRegion;
import org.eclipse.dawnsci.plotting.api.region.IRegion.RegionType;
import org.eclipse.dawnsci.plotting.api.region.IRegionListener;
import org.eclipse.dawnsci.plotting.api.trace.ColorOption;
import org.eclipse.dawnsci.plotting.api.trace.IImageStackTrace;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.IIsosurfaceTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineStackTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.IMulti2DTrace;
import org.eclipse.dawnsci.plotting.api.trace.IScatter3DTrace;
import org.eclipse.dawnsci.plotting.api.trace.ISurfaceTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;
import org.eclipse.dawnsci.plotting.api.trace.IVectorTrace;
import org.eclipse.dawnsci.plotting.api.trace.TraceEvent;
import org.eclipse.dawnsci.plotting.api.trace.TraceWillPlotEvent;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
/**
 * Will be a thread safe version of all the plotting system methods.
 * 
 * @author Matthew Gerring
 *
 */
public class ThreadSafePlottingSystem<T> extends ThreadSafeObject implements IPlottingSystem<T> {
	
	private IPlottingSystem delegate;

	public ThreadSafePlottingSystem(IPlottingSystem delegate) throws Exception {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public IImageTrace createImageTrace(String traceName) {
		return new ThreadSafeTrace((IImageTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}
	
	public Control setControl(Control alternative, boolean isToolbar) {
		throw new RuntimeException("Expert method "+getMethodName(Thread.currentThread().getStackTrace())+" is not allowed in remote mode!");
	}

	@Override
	public ILineTrace createLineTrace(String traceName) {
		return new ThreadSafeTrace((ILineTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public IVectorTrace createVectorTrace(String traceName) {
		return new ThreadSafeTrace((IVectorTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public ISurfaceTrace createSurfaceTrace(String traceName) {
		return new ThreadSafeTrace((ISurfaceTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}
	@Override
	public IIsosurfaceTrace createIsosurfaceTrace(String traceName) {
		return new ThreadSafeTrace((IIsosurfaceTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public IMulti2DTrace createMulti2DTrace(String traceName) {
		return new ThreadSafeTrace((IMulti2DTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public ILineStackTrace createLineStackTrace(String traceName) {
		return new ThreadSafeTrace((ILineStackTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public IScatter3DTrace createScatter3DTrace(String traceName) {
		return new ThreadSafeTrace((IScatter3DTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public IImageStackTrace createImageStackTrace(String traceName) {
		return new ThreadSafeTrace((IImageStackTrace)call(getMethodName(Thread.currentThread().getStackTrace()), traceName));
	}

	@Override
	public void addTrace(ITrace trace) {
		if (trace instanceof ThreadSafeTrace) trace = ((ThreadSafeTrace)trace).getDelegate();
		call(getMethodName(Thread.currentThread().getStackTrace()), trace);
	}

	@Override
	public void removeTrace(ITrace trace) {
		if (trace instanceof ThreadSafeTrace) trace = ((ThreadSafeTrace)trace).getDelegate();
		call(getMethodName(Thread.currentThread().getStackTrace()), trace);
	}

	@Override
	public ITrace getTrace(String name) {
		ITrace trace = delegate.getTrace(name);
		if (trace==null) return null;
		return new ThreadSafeTrace(trace);
	}

	@Override
	public Collection<ITrace> getTraces() {
		return getThreadSafe(delegate.getTraces());
	}

	private List<ITrace> getThreadSafe(Collection<ITrace> traces) {
		if (traces==null) return null;
		if (traces.isEmpty()) return Collections.emptyList();
		List<ITrace> ret = new ArrayList<ITrace>(traces.size());
		for (ITrace iTrace : traces) ret.add(new ThreadSafeTrace(iTrace));
		return ret;
	}

	@Override
	public Collection<ITrace> getTraces(Class<? extends ITrace> clazz) {
		return getThreadSafe(delegate.getTraces(clazz));
	}

	@Override
	public void addTraceListener(ITraceListener l) {
		delegate.addTraceListener(l);
	}

	@Override
	public void removeTraceListener(ITraceListener l) {
		delegate.removeTraceListener(l);
	}

	@Override
	public void renameTrace(ITrace trace, String name) throws Exception {
		if (trace instanceof ThreadSafeTrace) trace = ((ThreadSafeTrace)trace).getDelegate();
		call(getMethodName(Thread.currentThread().getStackTrace()), trace, name);
	}

	@Override
	public IRegion createRegion(String name, RegionType regionType) throws Exception {
		return (IRegion)call(getMethodName(Thread.currentThread().getStackTrace()), name, regionType);
	}

	@Override
	public void addRegion(IRegion region) {
		call(getMethodName(Thread.currentThread().getStackTrace()), region);
	}

	@Override
	public void removeRegion(IRegion region) {
		call(getMethodName(Thread.currentThread().getStackTrace()), region);
	}

	@Override
	public IRegion getRegion(String name) {
		return delegate.getRegion(name);
	}

	@Override
	public Collection<IRegion> getRegions(RegionType type) {
        return delegate.getRegions(type);
	}

	@Override
	public boolean addRegionListener(IRegionListener l) {
		return delegate.addRegionListener(l);
	}

	@Override
	public boolean removeRegionListener(IRegionListener l) {
		return delegate.removeRegionListener(l);
	}

	@Override
	public void clearRegions() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}
	@Override
	public void clearTraces() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public Collection<IRegion> getRegions() {
		return delegate.getRegions();
	}

	@Override
	public void renameRegion(IRegion region, String name) {
		call(getMethodName(Thread.currentThread().getStackTrace()), region, name);
	}

	@Override
	public IAxis createAxis(String title, boolean isYAxis, int side) {
		return 	new ThreadSafeAxis((IAxis)call(getMethodName(Thread.currentThread().getStackTrace()), 
				           new Class[]{String.class, boolean.class, int.class},
				           title, isYAxis, side));
	}

	@Override
	public IAxis getSelectedYAxis() {
		return new ThreadSafeAxis(delegate.getSelectedYAxis());
	}

	@Override
	public void setSelectedYAxis(IAxis yAxis) {
		if (yAxis instanceof ThreadSafeAxis) yAxis = ((ThreadSafeAxis)yAxis).getDelegate();
		call(getMethodName(Thread.currentThread().getStackTrace()), yAxis);
	}

	@Override
	public IAxis getSelectedXAxis() {
		return new ThreadSafeAxis(delegate.getSelectedXAxis());
	}

	@Override
	public void setSelectedXAxis(IAxis xAxis) {
		if (xAxis instanceof ThreadSafeAxis) xAxis = ((ThreadSafeAxis)xAxis).getDelegate();
		call(getMethodName(Thread.currentThread().getStackTrace()), xAxis);
	}

	@Override
	public void autoscaleAxes() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public IAnnotation createAnnotation(String name) throws Exception {
		return (IAnnotation)call(getMethodName(Thread.currentThread().getStackTrace()), name);
	}

	@Override
	public void addAnnotation(IAnnotation annot) {
		call(getMethodName(Thread.currentThread().getStackTrace()), annot);
	}

	@Override
	public void removeAnnotation(IAnnotation annot) {
		call(getMethodName(Thread.currentThread().getStackTrace()), annot);
	}

	@Override
	public IAnnotation getAnnotation(String name) {
		return delegate.getAnnotation(name);
	}

	@Override
	public void clearAnnotations() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void renameAnnotation(IAnnotation annotation, String name) {
		call(getMethodName(Thread.currentThread().getStackTrace()), annotation, name);
	}

	@Override
	public void printPlotting() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void copyPlotting() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public String savePlotting(String filename) throws Exception {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()), filename);
	}

	@Override
	public void savePlotting(String filename, String filetype) throws Exception {
		call(getMethodName(Thread.currentThread().getStackTrace()), filename, filetype);
	}

	@Override
	public String getTitle() {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setTitle(String title) {
		call(getMethodName(Thread.currentThread().getStackTrace()), title);
	}
	@Override
	public void setTitleColor(Color color) {
		call(getMethodName(Thread.currentThread().getStackTrace()), color);
	}
	@Override
	public void setBackgroundColor(Color color) {
		call(getMethodName(Thread.currentThread().getStackTrace()), color);
	}

	@Override
	public void createPlotPart(T parent, 
			                   String plotName,
			                   IActionBars bars, 
			                   PlotType hint, 
			                   IWorkbenchPart part) {
		
		throw new RuntimeException("Cannot call createPlotPart, only allowed to use this from python!");
	}

	@Override
	public String getPlotName() {
		return delegate.getPlotName();
	}

	@Override
	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys, IProgressMonitor monitor) {
		return getThreadSafe(delegate.createPlot1D(x, ys, monitor));
	}

	@Override
	public List<ITrace> createPlot1D(IDataset x,
			List<? extends IDataset> ys, String title, IProgressMonitor monitor) {
		return getThreadSafe(delegate.createPlot1D(x, ys, title, monitor));
	}

	@Override
	public List<ITrace> updatePlot1D(IDataset x,
			List<? extends IDataset> ys, IProgressMonitor monitor) {
		return getThreadSafe(delegate.updatePlot1D(x, ys, monitor));
	}

	@Override
	public ITrace createPlot2D(IDataset image,
			List<? extends IDataset> axes, IProgressMonitor monitor) {
		return new ThreadSafeTrace(createPlot2D(image, axes, monitor));
	}

	@Override
	public ITrace updatePlot2D(IDataset image,
			List<? extends IDataset> axes, IProgressMonitor monitor) {
		return new ThreadSafeTrace(delegate.updatePlot2D(image, axes, monitor));
	}

	@Override
	public void setPlotType(PlotType plotType) {
		call(getMethodName(Thread.currentThread().getStackTrace()), plotType);
	}

	@Override
	public void append(String dataSetName, Number xValue, Number yValue, IProgressMonitor monitor) throws Exception {
		delegate.append(dataSetName, xValue, yValue, monitor);
	}

	@Override
	public void reset() {
		delegate.reset();
	}

	@Override
	public void resetAxes() {
		delegate.resetAxes();
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public void dispose() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void repaint() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}
	
	@Override
	public void repaint(boolean autoScale) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{boolean.class}, autoScale);
	}

	@Override
	public T getPlotComposite() {
		return (T) delegate.getPlotComposite();
	}

	@Override
	public ISelectionProvider getSelectionProvider() {
		return delegate.getSelectionProvider();
	}

	@Override
	public PlotType getPlotType() {
		return delegate.getPlotType();
	}

	@Override
	public boolean is2D() {
		return delegate.is2D();
	}

	@Override
	public IActionBars getActionBars() {
		return delegate.getActionBars();
	}

	@Override
	public IPlotActionSystem getPlotActionSystem() {
		return delegate.getPlotActionSystem();
	}

	@Override
	public void setDefaultCursor(int cursorType) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{int.class}, cursorType);
	}

	@Override
	public IAxis removeAxis(IAxis axis) {
		if (axis instanceof ThreadSafeAxis) axis = ((ThreadSafeAxis)axis).getDelegate();
		return (IAxis)call(getMethodName(Thread.currentThread().getStackTrace()), axis);	
	}  
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IAxis> getAxes() {
		List<IAxis> axes = (List<IAxis>)call(getMethodName(Thread.currentThread().getStackTrace()));
		if (axes==null) return null;
		List<IAxis> ret = new ArrayList<IAxis>(axes.size());
		for (IAxis iAxis : axes) ret.add(new ThreadSafeAxis(iAxis));
		return ret;
	}
	
	@Override
	public IAxis getAxis(String name) {
		return new ThreadSafeAxis((IAxis)call(getMethodName(Thread.currentThread().getStackTrace()), name));	
	}

	
	@Override
	public void addPositionListener(IPositionListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{IPositionListener.class}, l);
	}

	@Override
	public void removePositionListener(IPositionListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{IPositionListener.class}, l);
	}

	@Override
	public void setKeepAspect(boolean b) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { boolean.class }, b);
	}


	@Override
	public boolean isShowIntensity() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setShowIntensity(boolean b) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { boolean.class }, b);
	}
	
	@Override
	public boolean isShowValueLabels() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setShowValueLabels(boolean b) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { boolean.class }, b);
	}


	@Override
	public void setShowLegend(boolean b) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { boolean.class }, b);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { adapter }, adapter);
	}


	@Override
	public boolean isDisposed() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setColorOption(ColorOption colorOption) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { ColorOption.class }, colorOption);
	}

	@Override
	public boolean isRescale() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setRescale(boolean rescale) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[] { boolean.class }, rescale);
	}

	@Override
	public void setFocus() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}
	
	public boolean isXFirst() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	/**
	 * Set if the first plot is the x-axis.
	 * @param xFirst
	 */
	public void setXFirst(boolean xFirst) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{boolean.class}, xFirst);
	}
	
	public void fireWillPlot(final TraceWillPlotEvent evt) {
		call(getMethodName(Thread.currentThread().getStackTrace()), evt);
	}
	
	/**
	 * May be used to force a trace to fire update listeners in the plotting system.
	 * @param evt
	 */
	public void fireTraceUpdated(final TraceEvent evt) {
		call(getMethodName(Thread.currentThread().getStackTrace()), evt);		
	}

	public void fireTraceAdded(final TraceEvent evt) {
		call(getMethodName(Thread.currentThread().getStackTrace()), evt);		
	}

	@Override
	public IWorkbenchPart getPart() {
		return (IWorkbenchPart)call(getMethodName(Thread.currentThread().getStackTrace()));
	}
	@Override
	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			List<String> dataNames, String title, IProgressMonitor monitor) {
		return getThreadSafe(delegate.createPlot1D(x,ys,dataNames, title, monitor));
	}

	@Override
	public List<ITrace> updatePlot1D(IDataset x, List<? extends IDataset> ys,
			List<String> dataNames, IProgressMonitor monitor) {
		return getThreadSafe(delegate.updatePlot1D( x,ys,dataNames, monitor));
	}

	@Override
	public List<ITrace> updatePlot1D(IDataset x, List<? extends IDataset> ys,
			String plotTitle, IProgressMonitor monitor) {
		return getThreadSafe(delegate.updatePlot1D( x,ys, plotTitle, monitor));
	}

	@Override
	public ITrace createPlot2D(IDataset image, List<? extends IDataset> axes,
			String dataName, IProgressMonitor monitor) {
		return new ThreadSafeTrace(delegate.createPlot2D(image, axes, dataName, monitor));
	}

	@Override
	public ITrace updatePlot2D(IDataset image, List<? extends IDataset> axes,
			String dataName, IProgressMonitor monitor) {
		return new ThreadSafeTrace(delegate.updatePlot2D(image, axes, dataName, monitor));
	}

	@Override
	public void setEnabled(boolean enabled) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{boolean.class}, enabled);		
	}

	@Override
	public boolean isEnabled() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}
	

	@Override
	public void addClickListener(IClickListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{IClickListener.class}, l);
	}

	@Override
	public void removeClickListener(IClickListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), new Class[]{IClickListener.class}, l);
	}

	@Override
	public void clearRegionTool() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void printScaledPlotting() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void moveTrace(String oldName, String name) {
		call(getMethodName(Thread.currentThread().getStackTrace()), oldName, name);
	}
}
