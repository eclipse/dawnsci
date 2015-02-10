/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.io;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;


/**
 * Holds data needed to get a slice from a nexus file. Since 
 * quite a bit of information is needed, we use an object.
 */
public class SliceObject implements Cloneable, Serializable {

	private String path;
	private String name;
	private int[]  fullShape;  // Not always needed
	private int[]  slicedShape;// The final shape expected
    private int[]  sliceStart; 
    private int[]  sliceStop; 
    private int[]  sliceStep;
    private int    x=-1;
    private int    y=-1;
    private int    xSize=-1;
    private int    ySize=-1;
    private boolean isRange;
    
    /**
     * Dataset name required for dimension where 1 is the first.
     */
    private Map<Integer,String> axisNames;
    
    /**
     * Name, expression name to a lazy dataset which can be used for an axis.
     */
	private Map<String, IDataset> expressionAxes;
	
	/**
	 * This is the DimsDataList object which can optionally be saved with 
	 * the slice information. For instance if the axes have been marked with
	 * mathematical operations.
	 */
	private Serializable dimensionalData;

    public SliceObject() {
    	this.axisNames = new HashMap<Integer, String>(3);
    }
    
	public void clear() {
		path          =null;
		name          =null;
		slicedShape   =null;// The final shape expected
	    sliceStart    =null; 
	    sliceStop     =null; 
	    sliceStep     =null;
	    axisNames     =null; // Do not clear
	    expressionAxes=null; // Do not clear
	}
    
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((axes == null) ? 0 : axes.hashCode());
		result = prime * result + ((axisNames == null) ? 0 : axisNames.hashCode());
		result = prime * result + ((dimensionalData == null) ? 0 : dimensionalData.hashCode());
		result = prime * result + ((expressionAxes == null) ? 0 : expressionAxes.hashCode());
		result = prime * result + Arrays.hashCode(fullShape);
		result = prime * result + (isRange ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((shapeMessage == null) ? 0 : shapeMessage.hashCode());
		result = prime * result + Arrays.hashCode(sliceStart);
		result = prime * result + Arrays.hashCode(sliceStep);
		result = prime * result + Arrays.hashCode(sliceStop);
		result = prime * result + Arrays.hashCode(slicedShape);
		result = prime * result + x;
		result = prime * result + xSize;
		result = prime * result + y;
		result = prime * result + ySize;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SliceObject other = (SliceObject) obj;
		if (axes == null) {
			if (other.axes != null)
				return false;
		} else if (!axes.equals(other.axes))
			return false;
		if (axisNames == null) {
			if (other.axisNames != null)
				return false;
		} else if (!axisNames.equals(other.axisNames))
			return false;
		if (dimensionalData == null) {
			if (other.dimensionalData != null)
				return false;
		} else if (!dimensionalData.equals(other.dimensionalData))
			return false;
		if (expressionAxes == null) {
			if (other.expressionAxes != null)
				return false;
		} else if (!expressionAxes.equals(other.expressionAxes))
			return false;
		if (!Arrays.equals(fullShape, other.fullShape))
			return false;
		if (isRange != other.isRange)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (shapeMessage == null) {
			if (other.shapeMessage != null)
				return false;
		} else if (!shapeMessage.equals(other.shapeMessage))
			return false;
		if (!Arrays.equals(sliceStart, other.sliceStart))
			return false;
		if (!Arrays.equals(sliceStep, other.sliceStep))
			return false;
		if (!Arrays.equals(sliceStop, other.sliceStop))
			return false;
		if (!Arrays.equals(slicedShape, other.slicedShape))
			return false;
		if (x != other.x)
			return false;
		if (xSize != other.xSize)
			return false;
		if (y != other.y)
			return false;
		if (ySize != other.ySize)
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getSliceStart() {
		return sliceStart;
	}
	public void setSliceStart(int[] sliceStart) {
		this.sliceStart = sliceStart;
	}
	public int[] getSliceStop() {
		return sliceStop;
	}
	public void setSliceStop(int[] sliceStop) {
		this.sliceStop = sliceStop;
	}
	public int[] getSliceStep() {
		return sliceStep;
	}
	public void setSliceStep(int[] sliceStep) {
		this.sliceStep = sliceStep;
	}

    @Override
	public SliceObject clone() {
    	final SliceObject ret = new SliceObject();
    	ret.fullShape = fullShape;
    	ret.name = name;
    	ret.path = path;
    	ret.sliceStart = sliceStart;
    	ret.sliceStop  = sliceStop;
    	ret.sliceStep  = sliceStep;
    	ret.axisNames.putAll(axisNames);
    	if (expressionAxes!=null) { // Point to old map.
    		ret.expressionAxes = expressionAxes;
    	}
		return ret;
    }
    
    // These methods intentionally not part of the slice API as
    // not really needed by the loader.
    
    private List<IDataset> axes;
    private String         shapeMessage;

	public List<IDataset> getAxes() {
		return axes;
	}
	public void setAxes(List<IDataset> axes) {
		this.axes = axes;
	}
	public String getShapeMessage() {
		return shapeMessage;
	}
	public void setShapeMessage(String shapeMessage) {
		this.shapeMessage = shapeMessage;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isAxis(int i) {
		return x==i||y==i;
	}
	public int[] getSlicedShape() {
		return slicedShape;
	}
	public void setSlicedShape(int[] slicedShape) {
		this.slicedShape=slicedShape;
	}
	public boolean isRange() {
		return isRange;
	}
	public void setRange(boolean isRange) {
		this.isRange = isRange;
	}

	public int[] getFullShape() {
		return fullShape;
	}

	public void setFullShape(int[] fullShape) {
		this.fullShape = fullShape;
	}

	public Map<Integer, String> getAxisNames() {
		return axisNames;
	}

	public void setAxisNames(Map<Integer, String> nexusAxes) {
		this.axisNames = nexusAxes;
	}
    
	public void setAxisName(int inexusDim, String name) {
		axisNames.put(inexusDim, name);
	}

	public String getAxisName(int inexusDim) {
		return axisNames.get(inexusDim);
	}

	public void putExpressionAxis(String name, IDataset set) {
		if (expressionAxes == null) expressionAxes = new HashMap<String, IDataset>(7);
		expressionAxes.put(name, set);
	}
	
	public IDataset getExpressionAxis(String name) {
		if (expressionAxes == null) return null;
		return expressionAxes.get(name);
	}

	public Serializable getDimensionalData() {
		return dimensionalData;
	}

	public void setDimensionalData(Serializable dimensionalData) {
		this.dimensionalData = dimensionalData;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}
  
}
