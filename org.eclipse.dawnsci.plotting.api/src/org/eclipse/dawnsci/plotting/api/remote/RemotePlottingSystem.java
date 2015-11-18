/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.remote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
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


/**
 * A plotting system which has been put into RMI so that the
 * stub may be called from Jython for scripting the plotting.
 * 
 */
public class RemotePlottingSystem<T> implements IRemotePlottingSystem {

	private IPlottingSystem<T> delegate;

	public RemotePlottingSystem(IPlottingSystem<T> system) throws Exception {
		if (system instanceof ThreadSafePlottingSystem) {
			delegate = system;
		} else {
			delegate = new ThreadSafePlottingSystem(system);
		}
	}
	
	public void printScaledPlotting() throws RemoteException {
		delegate.printScaledPlotting();
	}

	public IAnnotation createAnnotation(String name) throws RemoteException {
		try {
			return delegate.createAnnotation(name);
		} catch (Exception ne) {
			throw new RemoteException("Remote Exception", ne);
		}
	}

	public IImageTrace createImageTrace(String traceName) throws RemoteException {
		return delegate.createImageTrace(traceName);
	}

	public void printPlotting() throws RemoteException {
		delegate.printPlotting();
	}

	public IRemoteAxis createAxis(String title, boolean isYAxis, int side) throws RemoteException {
		return new RemoteAxis(delegate.createAxis(title, isYAxis, side));
	}

	public IRegion createRegion(String name, RegionType regionType)
			throws RemoteException {
		try {
		return delegate.createRegion(name, regionType);
	} catch (Exception ne) {
		throw new RemoteException("Remote Exception", ne);
	}
	}

	public void copyPlotting() throws RemoteException {
		delegate.copyPlotting();
	}

	public String savePlotting(String filename) throws RemoteException {
		try {
		return delegate.savePlotting(filename);
	} catch (Exception ne) {
		throw new RemoteException("Remote Exception", ne);
	}
	}

	public ILineTrace createLineTrace(String traceName) throws RemoteException {
		return delegate.createLineTrace(traceName);
	}

	public IRemoteAxis removeAxis(IRemoteAxis axis) throws RemoteException {
		return new RemoteAxis(delegate.removeAxis(axis.getDelegate()));
	}

	public void savePlotting(String filename, String filetype) throws RemoteException {
		try {
		delegate.savePlotting(filename, filetype);
		} catch (Exception ne) {
			throw new RemoteException("Remote Exception", ne);
		}

	}

	public void addAnnotation(IAnnotation region) throws RemoteException {
		delegate.addAnnotation(region);
	}

	public IVectorTrace createVectorTrace(String traceName) throws RemoteException {
		return delegate.createVectorTrace(traceName);
	}

	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) throws RemoteException {
		return delegate.getAdapter(adapter);
	}

	public void removeAnnotation(IAnnotation region) throws RemoteException {
		delegate.removeAnnotation(region);
	}

	public void addRegion(IRegion region) throws RemoteException {
		delegate.addRegion(region);
	}

	public List<IRemoteAxis> getAxes() throws RemoteException {
		List<IAxis> axes = delegate.getAxes();
		if (axes == null) return null;
		final List<IRemoteAxis> ret = new ArrayList<IRemoteAxis>(axes.size());
		for (IAxis axis : axes) ret.add(new RemoteAxis(axis));
		return ret;
	}

	public ISurfaceTrace createSurfaceTrace(String traceName) throws RemoteException {
		return delegate.createSurfaceTrace(traceName);
	}

	public IAnnotation getAnnotation(String name) throws RemoteException {
		return delegate.getAnnotation(name);
	}

	public IRemoteAxis getAxis(String name) throws RemoteException {
		return new RemoteAxis(delegate.getAxis(name));
	}

	public void removeRegion(IRegion region) throws RemoteException {
		delegate.removeRegion(region);
	}

	public void clearAnnotations() throws RemoteException {
		delegate.clearAnnotations();
	}

	public void renameAnnotation(IAnnotation annotation, String name) throws RemoteException {
		delegate.renameAnnotation(annotation, name);
	}

	public IIsosurfaceTrace createIsosurfaceTrace(String string) throws RemoteException {
		return delegate.createIsosurfaceTrace(string);
	}

	public IRemoteAxis getSelectedYAxis() throws RemoteException {
		return new RemoteAxis(delegate.getSelectedYAxis());
	}

	public IRegion getRegion(String name) throws RemoteException {
		return delegate.getRegion(name);
	}

	public IMulti2DTrace createMulti2DTrace(String traceName) throws RemoteException {
		return delegate.createMulti2DTrace(traceName);
	}

	public Collection<IRegion> getRegions(RegionType type) throws RemoteException {
		return delegate.getRegions(type);
	}

	public void setSelectedYAxis(IRemoteAxis yAxis) throws RemoteException {
		delegate.setSelectedYAxis(yAxis.getDelegate());
	}

	public ILineStackTrace createLineStackTrace(String traceName) throws RemoteException {
		return delegate.createLineStackTrace(traceName);
	}

	public IRemoteAxis getSelectedXAxis() throws RemoteException {
		return new RemoteAxis(delegate.getSelectedXAxis());
	}

	public boolean addRegionListener(IRegionListener l) throws RemoteException {
		return delegate.addRegionListener(l);
	}

	public boolean removeRegionListener(IRegionListener l) throws RemoteException {
		return delegate.removeRegionListener(l);
	}

	public void setSelectedXAxis(IRemoteAxis xAxis) throws RemoteException {
		delegate.setSelectedXAxis(xAxis.getDelegate());
	}

	public void clearRegions() throws RemoteException {
		delegate.clearRegions();
	}

	public IScatter3DTrace createScatter3DTrace(String traceName) throws RemoteException {
		return delegate.createScatter3DTrace(traceName);
	}

	public void clearRegionTool() throws RemoteException {
		delegate.clearRegionTool();
	}

	public void autoscaleAxes() throws RemoteException {
		delegate.autoscaleAxes();
	}

	public Collection<IRegion> getRegions() throws RemoteException {
		return delegate.getRegions();
	}

	public IImageStackTrace createImageStackTrace(String traceName) throws RemoteException {
		return delegate.createImageStackTrace(traceName);
	}

	public void renameRegion(IRegion region, String name) throws RemoteException {
		delegate.renameRegion(region, name);
	}

	public void addPositionListener(IPositionListener l) throws RemoteException {
		delegate.addPositionListener(l);
	}

	public void removePositionListener(IPositionListener l) throws RemoteException {
		delegate.removePositionListener(l);
	}

	public void addTrace(ITrace trace) throws RemoteException {
		delegate.addTrace(trace);
	}

	public void addClickListener(IClickListener l) throws RemoteException {
		delegate.addClickListener(l);
	}

	public void removeTrace(ITrace trace) throws RemoteException {
		delegate.removeTrace(trace);
	}

	public void removeClickListener(IClickListener l) throws RemoteException {
		delegate.removeClickListener(l);
	}

	public ITrace getTrace(String name) throws RemoteException {
		return delegate.getTrace(name);
	}

	public Collection<ITrace> getTraces() throws RemoteException {
		return delegate.getTraces();
	}

	public String getTitle() throws RemoteException {
		return delegate.getTitle();
	}

	public void setTitle(String title) throws RemoteException {
		delegate.setTitle(title);
	}

	public void clearTraces() throws RemoteException {
		delegate.clearTraces();
	}

	public Collection<ITrace> getTraces(Class<? extends ITrace> clazz) throws RemoteException {
		return delegate.getTraces(clazz);
	}

	public void addTraceListener(ITraceListener l) throws RemoteException {
		delegate.addTraceListener(l);
	}

	public void removeTraceListener(ITraceListener l) throws RemoteException {
		delegate.removeTraceListener(l);
	}

	public void renameTrace(ITrace trace, String name) throws RemoteException {
		try {
		delegate.renameTrace(trace, name);
		} catch (Exception ne) {
			throw new RemoteException("Remote Exception", ne);
		}
	}

	public String getPlotName() throws RemoteException {
		return delegate.getPlotName();
	}

	public boolean isXFirst() throws RemoteException {
		return delegate.isXFirst();
	}

	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			IProgressMonitor monitor) throws RemoteException {
		return delegate.createPlot1D(x, ys, monitor);
	}

	public void setXFirst(boolean xFirst) throws RemoteException {
		delegate.setXFirst(xFirst);
	}

	public void fireWillPlot(TraceWillPlotEvent evt) throws RemoteException {
		delegate.fireWillPlot(evt);
	}

	public void fireTraceUpdated(TraceEvent evt) throws RemoteException {
		delegate.fireTraceUpdated(evt);
	}

	public void fireTraceAdded(TraceEvent evt) throws RemoteException {
		delegate.fireTraceAdded(evt);
	}

	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			String title, IProgressMonitor monitor) throws RemoteException {
		return delegate.createPlot1D(x, ys, title, monitor);
	}

	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			List<String> dataNames, String title, IProgressMonitor monitor) throws RemoteException {
		return delegate.createPlot1D(x, ys, dataNames, title, monitor);
	}

	public List<ITrace> updatePlot1D(IDataset x, List<? extends IDataset> ys,
			IProgressMonitor monitor) throws RemoteException {
		return delegate.updatePlot1D(x, ys, monitor);
	}

	public List<ITrace> updatePlot1D(IDataset x, List<? extends IDataset> ys,
			List<String> dataNames, IProgressMonitor monitor) throws RemoteException {
		return delegate.updatePlot1D(x, ys, dataNames, monitor);
	}

	public ITrace createPlot2D(IDataset image, List<? extends IDataset> axes,
			IProgressMonitor monitor) throws RemoteException {
		return delegate.createPlot2D(image, axes, monitor);
	}

	public ITrace createPlot2D(IDataset image, List<? extends IDataset> axes,
			String dataName, IProgressMonitor monitor) throws RemoteException {
		return delegate.createPlot2D(image, axes, dataName, monitor);
	}

	public ITrace updatePlot2D(IDataset image, List<? extends IDataset> axes,
			IProgressMonitor monitor) throws RemoteException {
		return delegate.updatePlot2D(image, axes, monitor);
	}

	public ITrace updatePlot2D(IDataset image, List<? extends IDataset> axes,
			String dataName, IProgressMonitor monitor) throws RemoteException {
		return delegate.updatePlot2D(image, axes, dataName, monitor);
	}

	public void setPlotType(PlotType plotType) throws RemoteException {
		delegate.setPlotType(plotType);
	}

	public void append(String dataSetName, Number xValue, Number yValue,
			IProgressMonitor monitor) throws RemoteException {
		try {
		delegate.append(dataSetName, xValue, yValue, monitor);
		} catch (Exception ne) {
			throw new RemoteException("Remote Exception", ne);
		}
	}

	public void reset() throws RemoteException {
		delegate.reset();
	}

	public void resetAxes() throws RemoteException {
		delegate.resetAxes();
	}

	public void clear() throws RemoteException {
		delegate.clear();
	}

	public void dispose() throws RemoteException {
		delegate.dispose();
	}

	public void repaint() throws RemoteException {
		delegate.repaint();
	}

	public void repaint(boolean autoScale) throws RemoteException {
		delegate.repaint(autoScale);
	}

	public PlotType getPlotType() throws RemoteException {
		return delegate.getPlotType();
	}

	public boolean is2D() throws RemoteException {
		return delegate.is2D();
	}

	public IPlotActionSystem getPlotActionSystem() throws RemoteException {
		return delegate.getPlotActionSystem();
	}

	public void setDefaultCursor(int cursorType) throws RemoteException {
		delegate.setDefaultCursor(cursorType);
	}

	public void setKeepAspect(boolean b) throws RemoteException {
		delegate.setKeepAspect(b);
	}

	public void setEnabled(boolean enabled) throws RemoteException {
		delegate.setEnabled(enabled);
	}

	public boolean isEnabled() throws RemoteException {
		return delegate.isEnabled();
	}

	public boolean isShowIntensity() throws RemoteException {
		return delegate.isShowIntensity();
	}

	public void setShowIntensity(boolean b) throws RemoteException {
		delegate.setShowIntensity(b);
	}

	public void setShowLegend(boolean b) throws RemoteException {
		delegate.setShowLegend(b);
	}

	public boolean isDisposed() throws RemoteException {
		return delegate.isDisposed();
	}

	public void setColorOption(ColorOption colorOption) throws RemoteException {
		delegate.setColorOption(colorOption);
	}

	public boolean isRescale() throws RemoteException {
		return delegate.isRescale();
	}

	public void setRescale(boolean rescale) throws RemoteException {
		delegate.setRescale(rescale);
	}

	public void setFocus() throws RemoteException {
		delegate.setFocus();
	}
}
