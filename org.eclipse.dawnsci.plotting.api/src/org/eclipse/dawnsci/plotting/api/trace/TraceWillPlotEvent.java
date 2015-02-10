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

import java.util.EventObject;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Note the source to this event can either be the ITrace
 * or the plotting system.
 * 
 * @author Matthew Gerring
 *
 */
public class TraceWillPlotEvent extends EventObject {

	public  boolean         doit = true;
	
	private IDataset image=null;
	private boolean         newImageDataset = false;
	
	private List<IDataset> axes=null;
	private IDataset xLineData=null, yLineData=null;
	private boolean         newLineDataset = false;
	
	private final boolean applyStraightAway;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6103365099398209061L;

	public TraceWillPlotEvent(Object source, boolean applyStraightAway) {
		super(source);
		this.applyStraightAway = applyStraightAway;
		
		final IDataset set = ((ITrace)source).getData();
		if (set!=null) {
		   image = set.getRank()==2 ? set : null;
		}

		if (source instanceof IImageTrace) {
			IImageTrace it = (IImageTrace)source;
			axes  = it.getAxes();
		}
		
		if (source instanceof ILineTrace) {
			ILineTrace lt = (ILineTrace)source;
			this.xLineData = lt.getXData();
			this.yLineData = lt.getYData();
		}

	}

	public TraceWillPlotEvent(Object source, IDataset x, IDataset y) {
		super(source);
		this.xLineData = x;
		this.yLineData = y;
		this.applyStraightAway = false;
	}


	public TraceWillPlotEvent(Object source, IDataset image, List<IDataset> axes) {
		super(source);
		this.image = image;
		this.axes  = axes;
		this.applyStraightAway = false;
	}

	public IDataset getImage() {
		return image;
	}

	@SuppressWarnings("unchecked")
	public void setImageData(IDataset image, List<? extends IDataset> axes) {
		this.image = image;
		this.axes = (List<IDataset>) axes;
		newImageDataset = true;
		
		if (applyStraightAway && source instanceof IImageTrace) {
			IImageTrace it = (IImageTrace)source;
            it.setData(image, axes, false);		
		}
	}

	public void setNewImageDataSet(boolean newImageDataSet) {
		this.newImageDataset = newImageDataSet;
	}

	public List<IDataset> getAxes() {
		return axes;
	}

	public IDataset getXData() {
		return xLineData;
	}
	public IDataset getYData() {
		return yLineData;
	}

	public void setLineData(IDataset xLineData, IDataset yLineData) {
		this.xLineData = xLineData;
		this.yLineData = yLineData;
		newLineDataset = true;
		
		if (applyStraightAway && source instanceof ILineTrace) {
			ILineTrace lt = (ILineTrace)source;
			lt.setData(xLineData, yLineData);
		}
	}

	public boolean isNewImageDataSet() {
		return newImageDataset;
	}

	public boolean isNewLineDataSet() {
		return newLineDataset;
	}

	public ITrace getTrace() {
		return (ITrace)getSource();
	}

	public IImageTrace getImageTrace() {
		ITrace trace = getTrace();
		return trace instanceof IImageTrace
			   ? (IImageTrace)trace
			   : null;
	}
	

}
