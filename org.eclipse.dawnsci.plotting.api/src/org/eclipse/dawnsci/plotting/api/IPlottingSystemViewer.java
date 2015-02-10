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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.annotation.IAnnotation;
import org.eclipse.dawnsci.plotting.api.annotation.IAnnotationSystem;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.dawnsci.plotting.api.axis.IAxisSystem;
import org.eclipse.dawnsci.plotting.api.axis.IClickListener;
import org.eclipse.dawnsci.plotting.api.axis.IPositionListener;
import org.eclipse.dawnsci.plotting.api.region.IRegion;
import org.eclipse.dawnsci.plotting.api.region.IRegion.RegionType;
import org.eclipse.dawnsci.plotting.api.region.IRegionListener;
import org.eclipse.dawnsci.plotting.api.region.IRegionSystem;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public interface IPlottingSystemViewer extends IAxisSystem, IRegionSystem, IAnnotationSystem {


	/**
	 * The widget which this plot is displayed on.
	 * @return
	 */
	public Composite getControl();
	
	/**
	 * Called to create the plot viewer.
	 * @param parent
	 */
	public void createControl(Composite parent);
	
	/**
	 * Called when the plotting system decides that it will use this viewer.
	 * @param system
	 */
	public void init(IPlottingSystem system);
	
	/**
	 * Returns true if this viewer can deal with this plot type.
	 * @param clazz
	 * @return
	 */
	public boolean isTraceTypeSupported(Class<? extends ITrace> clazz);
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public ITrace createTrace(String name, Class<? extends ITrace> clazz);
	
	/**
	 * Short cut method for creating several line traces in one go.
	 * @param title
	 * @param xIn
	 * @param ysIn
	 * @param dataNames
	 * @param traceMap
	 * @param colorMap
	 * @param monitor
	 * @return
	 * @deprecated line plot specific
	 */
    @Deprecated
	public List<ILineTrace> createLineTraces(String title, IDataset xIn,
			List<? extends IDataset> ysIn, List<String> dataNames,
			Map<String, ITrace> traceMap, Map<Object, Color> colorMap,
			IProgressMonitor monitor);

	/**
	 * 
	 * @param xfirst
	 * @Deprecated line plot specific
	 */
	@Deprecated
	public void setXFirst(boolean xfirst);

	/**
	 * Add the trace to the view.
	 */
	public boolean addTrace(ITrace trace);

	
	/**
	 * Add the trace to the view.
	 */
	public void removeTrace(ITrace trace);

	/**
	 * 
	 * @param type
	 * @return true if this viewer deals with this plot type.
	 */
	public boolean isPlotTypeSupported(PlotType type);
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title);
	
    /**
     * 
     * @param color
     */
	public void setTitleColor(Color color);
	
	/**
     * 
     * @param color
     */
	public void setBackgroundColor(Color color);

	/**
	 * 
	 * @return title
	 */
	public String getTitle();

	/**
	 * Reset the plot
	 * @param force true or false, might be ignored for some plot viewers.
	 */
	public void reset(boolean force);

	/**
	 * Called to dispose the viewer.
	 */
	public void dispose();
	
	/**
	 * Create an SWT image of the plot and return it
	 * @param size of image
	 * @return may be implemented to return null in which case no screenshot may be taken of the plot.
	 */
	public Image getImage(Rectangle size);
	
	/**
	 * Called to update the viewer if the role is known to change.
	 * @param type
	 */
	public void updatePlottingRole(PlotType type);

	/**
	 * Called to setFocus on the viewer, might do nothing depending on the toolkit that the viewer uses.
	 */
	public void setFocus();
	
	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * 
	 * @return
	 */
	public boolean isEnabled();

	/**
	 * 
	 * @param l
	 */
	public void addImageTraceListener(ITraceListener l);

	/**
	 * 
	 * @param l
	 */
	public void removeImageTraceListener(ITraceListener l);

	/**
	 * Can be called to remove currently plotted traces, may do nothing depending on viewer implementation.
	 */
	public void clearTraces();

	/**
	 * 
	 * @param b
	 */
	public void setShowLegend(boolean b);



	public boolean isShowIntensity();
	/**
	 * 
	 * @param b
	 */
	public void setShowIntensity(boolean b);

	/**
	 * 
	 * @param autoScale
	 */
	public void repaint(boolean autoScale);
	
	/**
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(IPropertyChangeListener listener);

	
	/**
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(IPropertyChangeListener listener);

	/**
	 * One of the CURSOR_<i>XXX</i> values.
	 * @param cursorType
	 */
	public void setDefaultCursor(int cursorType);

	/**
	 * 
	 * @param checked
	 */
	public void setKeepAspect(boolean checked);


	public void setRescale(boolean rescale);

	
	
	public class Stub implements IPlottingSystemViewer {


		protected IPlottingSystem system;
		
		public Stub() {
			
		}

		@Override
		public void resetAxes() {
			
			
		}

		@Override
		public IAxis createAxis(String title, boolean isYAxis, int side) {
			
			return null;
		}

		@Override
		public IAxis removeAxis(IAxis axis) {
			
			return null;
		}

		@Override
		public List<IAxis> getAxes() {
			
			return null;
		}

		@Override
		public IAxis getAxis(String name) {
			
			return null;
		}

		@Override
		public IAxis getSelectedYAxis() {
			
			return null;
		}

		@Override
		public void setSelectedYAxis(IAxis yAxis) {
			
			
		}

		@Override
		public IAxis getSelectedXAxis() {
			
			return null;
		}

		@Override
		public void setSelectedXAxis(IAxis xAxis) {
			
			
		}

		@Override
		public void autoscaleAxes() {
			
			
		}

		@Override
		public void addPositionListener(IPositionListener l) {
			
			
		}

		@Override
		public void removePositionListener(IPositionListener l) {
			
			
		}

		@Override
		public void addClickListener(IClickListener l) {
			
			
		}

		@Override
		public void removeClickListener(IClickListener l) {
			
			
		}

		@Override
		public IRegion createRegion(String name, RegionType regionType)
				throws Exception {
			
			return null;
		}

		@Override
		public void addRegion(IRegion region) {
			
			
		}

		@Override
		public void removeRegion(IRegion region) {
			
			
		}

		@Override
		public IRegion getRegion(String name) {
			
			return null;
		}

		@Override
		public Collection<IRegion> getRegions(RegionType type) {
			
			return null;
		}

		@Override
		public boolean addRegionListener(IRegionListener l) {
			
			return false;
		}

		@Override
		public boolean removeRegionListener(IRegionListener l) {
			
			return false;
		}

		@Override
		public void clearRegions() {
			
			
		}

		@Override
		public void clearRegionTool() {
			
			
		}

		@Override
		public Collection<IRegion> getRegions() {
			
			return null;
		}

		@Override
		public void renameRegion(IRegion region, String name) {
			
			
		}

		@Override
		public IAnnotation createAnnotation(String name) throws Exception {
			
			return null;
		}

		@Override
		public void addAnnotation(IAnnotation region) {
			
			
		}

		@Override
		public void removeAnnotation(IAnnotation region) {
			
			
		}

		@Override
		public IAnnotation getAnnotation(String name) {
			
			return null;
		}

		@Override
		public void clearAnnotations() {
			
			
		}

		@Override
		public void renameAnnotation(IAnnotation annotation, String name) {
			
			
		}

		@Override
		public Composite getControl() {
			
			return null;
		}

		@Override
		public void createControl(Composite parent) {
			
			
		}
		
		public void init(IPlottingSystem system) {
			this.system = system;
		}

		@Override
		public boolean isTraceTypeSupported(Class<? extends ITrace> clazz) {
			
			return false;
		}

		@Override
		public ITrace createTrace(String name, Class<? extends ITrace> clazz) {
			
			return null;
		}

		@Override
		public List<ILineTrace> createLineTraces(String title, IDataset xIn,
				List<? extends IDataset> ysIn, List<String> dataNames,
				Map<String, ITrace> traceMap, Map<Object, Color> colorMap,
				IProgressMonitor monitor) {
			
			return null;
		}

		@Override
		public void setXFirst(boolean xfirst) {
			
			
		}

		@Override
		public boolean addTrace(ITrace trace) {
			
			return false;
		}

		@Override
		public void removeTrace(ITrace trace) {
			
			
		}

		@Override
		public boolean isPlotTypeSupported(PlotType type) {
			
			return false;
		}

		@Override
		public void setTitle(String title) {
			
			
		}

		@Override
		public String getTitle() {
			
			return null;
		}

		@Override
		public void reset(boolean force) {
			
			
		}

		@Override
		public void dispose() {
			
			
		}

		@Override
		public Image getImage(Rectangle size) {
			
			return null;
		}

		@Override
		public void updatePlottingRole(PlotType type) {
			
			
		}

		@Override
		public void setFocus() {
			
			
		}

		@Override
		public void setEnabled(boolean enabled) {
			
			
		}

		@Override
		public boolean isEnabled() {
			
			return false;
		}

		@Override
		public void addImageTraceListener(ITraceListener l) {
			
			
		}

		@Override
		public void removeImageTraceListener(ITraceListener l) {
			
			
		}

		@Override
		public void clearTraces() {
			
			
		}

		@Override
		public void setShowLegend(boolean b) {
			
			
		}



		public boolean isShowIntensity() {
			return false;
		}
		
		@Override
		public void setShowIntensity(boolean b) {
			
			
		}

		@Override
		public void repaint(boolean autoScale) {
			
			
		}

		@Override
		public void addPropertyChangeListener(IPropertyChangeListener listener) {
			
			
		}

		@Override
		public void removePropertyChangeListener(
				IPropertyChangeListener listener) {
			
			
		}

		@Override
		public void setDefaultCursor(int cursorType) {
			
			
		}

		@Override
		public void setKeepAspect(boolean checked) {
			
			
		}

		@Override
		public void setRescale(boolean rescale) {
			
			
		}

		@Override
		public void setTitleColor(Color color) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBackgroundColor(Color color) {
			// TODO Auto-generated method stub
			
		}
		
	}



}
