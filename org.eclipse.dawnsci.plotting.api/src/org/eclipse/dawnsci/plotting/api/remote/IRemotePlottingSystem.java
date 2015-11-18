/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.IPlotActionSystem;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.annotation.IAnnotation;
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
 * The methods of IPlottingSystem<Composite> with RemoteExceptions declared.
 * Used for Jython scripting of plotting system.
 */
public interface IRemotePlottingSystem extends Remote {

	public IImageTrace createImageTrace(String traceName) throws RemoteException;

	public ILineTrace createLineTrace(String traceName) throws RemoteException;

	public IVectorTrace createVectorTrace(String traceName) throws RemoteException;

	public ISurfaceTrace createSurfaceTrace(String traceName) throws RemoteException;

	public IIsosurfaceTrace createIsosurfaceTrace(String traceName) throws RemoteException;

	public IMulti2DTrace createMulti2DTrace(String traceName) throws RemoteException;

	public ILineStackTrace createLineStackTrace(String traceName) throws RemoteException;

	public IScatter3DTrace createScatter3DTrace(String traceName) throws RemoteException;

	public IImageStackTrace createImageStackTrace(String traceName) throws RemoteException;

	public void addTrace(ITrace trace) throws RemoteException;

	public void removeTrace(ITrace trace) throws RemoteException;

	public ITrace getTrace(String name) throws RemoteException;

	public Collection<ITrace> getTraces() throws RemoteException;

	public Collection<ITrace> getTraces(Class<? extends ITrace> clazz) throws RemoteException;

	public void addTraceListener(ITraceListener l) throws RemoteException;

	public void removeTraceListener(ITraceListener l) throws RemoteException;

	public void renameTrace(ITrace trace, String name) throws RemoteException;

	public IRegion createRegion(String name, RegionType regionType)
			throws RemoteException;

	public void addRegion(IRegion region) throws RemoteException;

	public void removeRegion(IRegion region) throws RemoteException;

	public IRegion getRegion(String name) throws RemoteException;

	public Collection<IRegion> getRegions(RegionType type) throws RemoteException;

	public boolean addRegionListener(IRegionListener l) throws RemoteException;

	public boolean removeRegionListener(IRegionListener l) throws RemoteException;

	public void clearRegions() throws RemoteException;

	public void clearTraces() throws RemoteException;

	public Collection<IRegion> getRegions() throws RemoteException;

	public void renameRegion(IRegion region, String name) throws RemoteException;

	public IRemoteAxis createAxis(String title, boolean isYAxis, int side) throws RemoteException;

	public IRemoteAxis getSelectedYAxis() throws RemoteException;

	public void setSelectedYAxis(IRemoteAxis yAxis) throws RemoteException;

	public IRemoteAxis getSelectedXAxis() throws RemoteException;

	public void setSelectedXAxis(IRemoteAxis xAxis) throws RemoteException;

	public void autoscaleAxes() throws RemoteException;

	public IAnnotation createAnnotation(String name) throws RemoteException;

	public void addAnnotation(IAnnotation annot) throws RemoteException;

	public void removeAnnotation(IAnnotation annot) throws RemoteException;

	public IAnnotation getAnnotation(String name) throws RemoteException;

	public void clearAnnotations() throws RemoteException;

	public void renameAnnotation(IAnnotation annotation, String name) throws RemoteException;

	public void printPlotting() throws RemoteException;

	public void copyPlotting() throws RemoteException;

	public String savePlotting(String filename) throws RemoteException;

	public void savePlotting(String filename, String filetype) throws RemoteException;

	public String getTitle() throws RemoteException;

	public void setTitle(String title) throws RemoteException;

	public String getPlotName() throws RemoteException;

	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			IProgressMonitor monitor) throws RemoteException;

	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			String title, IProgressMonitor monitor) throws RemoteException;

	public List<ITrace> updatePlot1D(IDataset x, List<? extends IDataset> ys,
			IProgressMonitor monitor) throws RemoteException;

	public ITrace createPlot2D(IDataset image, List<? extends IDataset> axes,
			IProgressMonitor monitor) throws RemoteException;

	public ITrace updatePlot2D(IDataset image, List<? extends IDataset> axes,
			IProgressMonitor monitor) throws RemoteException;

	public void setPlotType(PlotType plotType) throws RemoteException;

	public void append(String dataSetName, Number xValue, Number yValue,
			IProgressMonitor monitor) throws RemoteException;

	public void reset() throws RemoteException;

	public void resetAxes() throws RemoteException;

	public void clear() throws RemoteException;

	public void dispose() throws RemoteException;

	public void repaint() throws RemoteException;

	public void repaint(boolean autoScale) throws RemoteException;

	public PlotType getPlotType() throws RemoteException;

	public boolean is2D() throws RemoteException;

	public IPlotActionSystem getPlotActionSystem() throws RemoteException;

	public void setDefaultCursor(int cursorType) throws RemoteException;

	public IRemoteAxis removeAxis(IRemoteAxis axis) throws RemoteException;

	public List<IRemoteAxis> getAxes() throws RemoteException;

	public IRemoteAxis getAxis(String name) throws RemoteException;

	public void addPositionListener(IPositionListener l) throws RemoteException;

	public void removePositionListener(IPositionListener l) throws RemoteException;

	public void setKeepAspect(boolean b) throws RemoteException;

	public boolean isShowIntensity() throws RemoteException;

	public void setShowIntensity(boolean b) throws RemoteException;

	public void setShowLegend(boolean b) throws RemoteException;

	public Object getAdapter(Class adapter) throws RemoteException;

	public boolean isDisposed() throws RemoteException;

	public void setColorOption(ColorOption colorOption) throws RemoteException;

	public boolean isRescale() throws RemoteException;

	public void setRescale(boolean rescale) throws RemoteException;

	public void setFocus() throws RemoteException;

	public boolean isXFirst() throws RemoteException;

	/**
	 * Set if the first plot is the x-axis.
	 * @param xFirst
	 */
	public void setXFirst(boolean xFirst) throws RemoteException;

	public void fireWillPlot(TraceWillPlotEvent evt) throws RemoteException;

	/**
	 * May be used to force a trace to fire update listeners in the plotting system.
	 * @param evt
	 */
	public void fireTraceUpdated(TraceEvent evt) throws RemoteException;

	public void fireTraceAdded(TraceEvent evt) throws RemoteException;

	public List<ITrace> createPlot1D(IDataset x, List<? extends IDataset> ys,
			List<String> dataNames, String title, IProgressMonitor monitor) throws RemoteException;

	public List<ITrace> updatePlot1D(IDataset x, List<? extends IDataset> ys,
			List<String> dataNames, IProgressMonitor monitor) throws RemoteException;

	public ITrace createPlot2D(IDataset image, List<? extends IDataset> axes,
			String dataName, IProgressMonitor monitor) throws RemoteException;

	public ITrace updatePlot2D(IDataset image, List<? extends IDataset> axes,
			String dataName, IProgressMonitor monitor) throws RemoteException;

	public void setEnabled(boolean enabled) throws RemoteException;

	public boolean isEnabled() throws RemoteException;

	public void addClickListener(IClickListener l) throws RemoteException;

	public void removeClickListener(IClickListener l) throws RemoteException;

	public void clearRegionTool() throws RemoteException;

	public void printScaledPlotting() throws RemoteException;
}
