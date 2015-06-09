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
package org.eclipse.dawnsci.plotting.api.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.TraceWillPlotEvent;

/**
 * Please extend this class to define undoable filters.
 * 
 * @author Matthew Gerring
 *
 */
public abstract class AbstractPlottingFilter implements IPlottingFilter {
	
	protected List<OriginalData>  cache;
	protected boolean             active=true;
	protected Map<String, Object> configuration;

	public AbstractPlottingFilter() {
		this.cache = new ArrayList<OriginalData>(7);
	}

	@Override
	public void filter(IPlottingSystem system, TraceWillPlotEvent evt) throws Exception {
		final ITrace trace = (ITrace)evt.getSource();
		if (trace.getRank()!=getRank()) {
			if (getRank()>0) return;
		}
		cache.add(new OriginalData(evt));
		
		if (getRank()==1) {
			IDataset[] filtered = filter(evt.getXData(), evt.getYData());
			evt.setLineData(filtered[0]!=null?filtered[0]:evt.getXData(), filtered[1]);
		} else {
			Object[] filtered = filter(evt.getImage(), evt.getAxes());
			evt.setImageData((IDataset)filtered[0], (List<IDataset>)filtered[1]);
		}
	}
	
	/**
	 * Please implement for filtering 1D data
	 * @param x
	 * @param y
	 * @return the new values (0=x, 1=y, x may be null)
	 */
	protected IDataset[] filter(IDataset x,    IDataset y) throws Exception {
		return null;
	}
	
	/**
	 * Please implement for filtering images and larger data.
	 * @param data
	 * @param axes
	 * @return Object[]{0=newData, 1=List<IDataset> axes, may be null}
	 */
	protected Object[] filter(IDataset data, List<IDataset> axes) throws Exception {
		return null;
	}

	@Override
	public final void reset() {
		
		final Collection<OriginalData> tmpCache = new ArrayList<OriginalData>(cache);
		cache.clear();
		for (OriginalData data : tmpCache) {
			data.reset();
		}
	}

	public List<ITrace> getFilteredTraces() {
		if (cache==null || cache.isEmpty()) return null;
		final List<ITrace> filtered = new ArrayList<ITrace>(cache.size());
		for (OriginalData data : cache) {
			filtered.add(data.getTrace());
		}
		return filtered;
	}
	
	protected class OriginalData {
		
		private ITrace trace;
		private String name;
		
		private IDataset       image=null;
		private List<IDataset> axes=null;
		
		private IDataset xLineData=null, yLineData=null;


		public OriginalData(TraceWillPlotEvent evt) {
			this.trace = (ITrace)evt.getSource();
			this.name  = trace.getName();
			if (getRank()==1) {
				xLineData = evt.getXData();
				yLineData = evt.getYData();
			} else if (getRank()==2) {
				image     = evt.getImage();
				axes      = evt.getAxes();
			}
		}
		
		public ITrace getTrace() {
			return trace;
		}

		/**
		 * Sets the data of the trace to that which the trace
		 * was before we did the filter.
		 */
		public void reset() {
			if (getRank()==1) {
				((ILineTrace)trace).setData(xLineData, yLineData);
			} else if (getRank()==2) {
				((IImageTrace)trace).setData(image, axes, true);
			}
		}


		@Override
		public int hashCode() {
			return name.hashCode(); // Normally unique.
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OriginalData other = (OriginalData) obj;
			if (axes == null) {
				if (other.axes != null)
					return false;
			} else if (!axes.equals(other.axes))
				return false;
			if (image == null) {
				if (other.image != null)
					return false;
			} else if (!image.equals(other.image))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (xLineData == null) {
				if (other.xLineData != null)
					return false;
			} else if (!xLineData.equals(other.xLineData))
				return false;
			if (yLineData == null) {
				if (other.yLineData != null)
					return false;
			} else if (!yLineData.equals(other.yLineData))
				return false;
			return true;
		}
		
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void dispose() {
		if (cache!=null) cache.clear();
	}

	public Map<String, Object> getConfiguration() {
		if (configuration==null) configuration = new HashMap<String, Object>();
		return configuration;
	}

	public void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}

	public void putConfiguration(String key, Object value) {
		getConfiguration().put(key, value);
	}
}
