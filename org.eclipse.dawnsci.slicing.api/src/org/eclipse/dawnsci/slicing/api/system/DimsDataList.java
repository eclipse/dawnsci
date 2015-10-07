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
package org.eclipse.dawnsci.slicing.api.system;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;

public class DimsDataList implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5902704017223885965L;
	
	
	private List<DimsData> dimsData;

	private boolean        expression;
		
	public DimsDataList() {
	}

	public DimsDataList(List<DimsData> chunk) {
		dimsData = chunk;
	}
	
	public DimsDataList(int[] dataShape) throws Exception {
		
		try {
						
			// For now we just assume the first dimensions are the slow ones to make an axis out
			// of. Later read the axis from the meta list but we do not have examples of this so
			// far.
			int xaxis=-1,yaxis=-1;
			for (int i = 0; i<dataShape.length; ++i) {
				add(new DimsData(i));
			}
			for (int i = dataShape.length-1; i>=0; i--) {
				
				if (dataShape[i]>1) {
					if (yaxis<0) {
						getDimsData(i).setPlotAxis(AxisType.Y);
						yaxis = i;
						continue;
					} else  if (xaxis<0) {
						getDimsData(i).setPlotAxis(AxisType.X);
						xaxis = i;
						continue;
					}
				}
			}
			
			// If we only found a y it may be a multiple-dimension set with only 1D possible.
			// In that case change y to x.
			if (yaxis>-1 && xaxis<0) {
				getDimsData(yaxis).setPlotAxis(AxisType.X);
			}
		} finally {
			//file.close();
		}
	}

	public Iterable<DimsData> iterable() {
		return dimsData;
	}
	
	public void add(DimsData dimension) {
		if (dimsData==null) dimsData = new ArrayList<DimsData>(3);
		if (dimsData.size()>dimension.getDimension() && dimension.getDimension()>-1) {
			dimsData.set(dimension.getDimension(), dimension);
		} else {
			dimsData.add(dimension);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dimsData == null) ? 0 : dimsData.hashCode());
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
		DimsDataList other = (DimsDataList) obj;
		if (dimsData == null) {
			if (other.dimsData != null)
				return false;
		} else if (!dimsData.equals(other.dimsData))
			return false;
		return true;
	}

	public static Object[] getDefault() {
		return new DimsData[]{new DimsData(0)};
	}
	
	public Object[] getElements() {
		if (dimsData==null) return null;
		return dimsData.toArray(new DimsData[dimsData.size()]);
	}

	public int size() {
		if (dimsData==null) return 0;
		return dimsData.size();
	}

	public DimsData getDimsData(int i) {
		if (dimsData==null) return null;
		return dimsData.get(i);
	}

	public Iterator<DimsData> iterator() {
		if (dimsData==null) return null;
		return dimsData.iterator();
	}
	
	public void clear() {
		if (dimsData!=null) dimsData.clear();
	}
	
	public String toString() {
		return toString(null);
	}
	public String toString(int[] shape) {
		
		final StringBuilder buf = new StringBuilder();
		buf.append("[ ");
		
		int index = 0;
		for (DimsData d : dimsData) {
			
			final int upper = shape!=null ? shape[index] : -1;
			buf.append(d.getUserString(upper));
			if (d!=dimsData.get(dimsData.size()-1)) buf.append(",  ");
			++index;
		}
		buf.append(" ]");
		return buf.toString();
	}

	public boolean isRangeDefined() {
		for (DimsData data : iterable()) {
			if (data.getSliceRange()!=null) return true;
		}
		return false;
	}
	
	public int getAxisCount() {
		if (dimsData==null) return -1;
		int count = 0;
		for (DimsData dd : dimsData) {
			if (!dd.getPlotAxis().hasValue()) count++;
		}
		return count;
	}
	
	public int getRangeCount() {
		int count = 0;
		for (DimsData dd : dimsData) {
			if (dd.getPlotAxis()==AxisType.RANGE) count++;
		}
		return count;
	}

	public boolean is2D() {
		return getAxisCount()==2;
	}
	
	public DimsDataList clone() {
		final DimsDataList clone = new DimsDataList();
		for (DimsData dd : iterable()) {
			DimsData dnew = dd.clone();
			clone.add(dnew);
		}
		clone.expression = expression;
		return clone;
	}

	/**
	 * Sets any axes there are to  the axis passed in
	 */
	public void normalise(AxisType axis) {
		for (DimsData dd : iterable()) {
			if (!dd.getPlotAxis().hasValue()) dd.setPlotAxis(axis);
		}
	}

	/**
	 * Probably not best algorithm but we are dealing with very small arrays here.
	 * This is simply trying to ensure that only one dimension is selected as an
	 * axis because the plot has changed.
	 * 
	 * @param iaxisToFind
	 */
	public void setSingleAxisOnly(AxisType iaxisToFind, AxisType iaxisValue) {
		DimsData found = null;
		for (DimsData dd : iterable()) {
			if (dd.getPlotAxis()==iaxisToFind) {
				dd.setPlotAxis(iaxisValue);
				found=dd;
			}
		}
		
		if (found!=null) {
			for (DimsData dd : iterable()) {
				if (dd==found) continue;
				dd.setPlotAxis(AxisType.SLICE);
			}
			return;
		} else { // We have to decide which of the others is x
			
			for (DimsData dd : iterable()) {
				if (!dd.getPlotAxis().hasValue()) {
				    dd.setPlotAxis(iaxisValue);
				    found=dd;
				}
			}
			for (DimsData dd : iterable()) {
				if (dd==found) continue;
				dd.setPlotAxis(AxisType.SLICE);
			}
		}
	}

	/**
	 * Bit of a complex  method. It simply tries to leave the data with
	 * two axes selected by finding the most likely two dimensions that
	 * should be plot axes.
	 * 
	 * @param firstAxis
	 * @param secondAxis
	 */
	public void setTwoAxesOnly(AxisType firstAxis, AxisType secondAxis) {
		boolean foundFirst = false, foundSecond = false;
		for (DimsData dd : iterable()) {
			if (dd.getPlotAxis()==firstAxis)  foundFirst  = true;
			if (dd.getPlotAxis()==secondAxis) foundSecond = true;
		}
		
		if (foundFirst&&foundSecond) {
			for (DimsData dd : iterable()) {
				if (dd.getPlotAxis()==firstAxis)  continue;
				if (dd.getPlotAxis()==secondAxis) continue;
				if (dd.getPlotAxis()==AxisType.RANGE) continue;
				dd.setPlotAxis(AxisType.SLICE);
			}
			return;
		} else { // We have to decide which of the others is first and second
			
			if (!foundFirst)  foundFirst  = processAxis(firstAxis, secondAxis);
			if (!foundSecond) foundSecond = processAxis(secondAxis, firstAxis);
			
			for (DimsData dd : iterable()) {
				if (dd.getPlotAxis()==firstAxis)  continue;
				if (dd.getPlotAxis()==secondAxis) continue;
				if (dd.getPlotAxis()==AxisType.RANGE) continue;
				dd.setPlotAxis(AxisType.SLICE);
			}
			return;
				
		}
		
	}
	
	/**
	 * Bit of a complex  method. It simply tries to leave the data with
	 * two axes selected by finding the most likely two dimensions that
	 * should be plot axes.
	 * 
	 * @param firstAxis
	 * @param secondAxis
	 * @param thirdAxis
	 */
	public void setThreeAxesOnly(AxisType firstAxis, AxisType secondAxis, AxisType thirdAxis) {

		boolean foundFirst = false, foundSecond = false, foundThird = false;
		for (DimsData dd : iterable()) {
			if (dd.getPlotAxis()==firstAxis)  foundFirst  = true;
			if (dd.getPlotAxis()==secondAxis) foundSecond = true;
			if (dd.getPlotAxis()==thirdAxis)  foundThird  = true;
		}
		
		if (foundFirst&&foundSecond&&foundThird) {
			for (DimsData dd : iterable()) {
				if (dd.getPlotAxis()==firstAxis)  continue;
				if (dd.getPlotAxis()==secondAxis) continue;
				if (dd.getPlotAxis()==thirdAxis)  continue;
				if (dd.getPlotAxis()==AxisType.RANGE) continue;
				dd.setPlotAxis(AxisType.SLICE);
			}
			return;
		} else { // We have to decide which of the others is first and second
			
			if (!foundFirst)  foundFirst  = processAxis(firstAxis,  secondAxis, thirdAxis);
			if (!foundSecond) foundSecond = processAxis(secondAxis, firstAxis,  thirdAxis);
			if (!foundThird)  foundThird  = processAxis(thirdAxis,  firstAxis,  secondAxis);
			
			for (DimsData dd : iterable()) {
				if (dd.getPlotAxis()==firstAxis)  continue;
				if (dd.getPlotAxis()==secondAxis) continue;
				if (dd.getPlotAxis()==thirdAxis)  continue;
				if (dd.getPlotAxis()==AxisType.RANGE) continue;
				dd.setPlotAxis(AxisType.SLICE);
			}
			return;
				
		}

	}

	
	private final boolean processAxis(AxisType axis, AxisType... ignoredAxes) {
		
		final List<Object> ignored = asList(ignoredAxes);
		for (DimsData dd : iterable()) {
			if (!dd.getPlotAxis().hasValue() && !ignored.contains(dd.getPlotAxis())) {
			    dd.setPlotAxis(axis);
			    return true;
			}
		}	
		
		for (DimsData dd : iterable()) {
			if (!ignored.contains(dd.getPlotAxis())) {
				dd.setPlotAxis(axis);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Convert a primitive array to a list.
	 * @param array - an array of primitives
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private static final <T> List<T> asList(final Object array) {
		
        if (!array.getClass().isArray()) throw new IllegalArgumentException("Not an array");
        return new AbstractList<T>() {
            @Override
            public T get(int index) {
                return (T) Array.get(array, index);
            }

            @Override
            public int size() {
                return Array.getLength(array);
            }
        };
    }

	public boolean isXFirst() {
		for (DimsData dd : iterable()) {
			if (dd.getPlotAxis().hasValue()) continue;
			return dd.getPlotAxis()==AxisType.X;
		}
		return false;
	}

	public void reverseImage() {
		for (DimsData dd : iterable()) {
			if (dd.getPlotAxis()==AxisType.X) {
				dd.setPlotAxis(AxisType.Y);
				continue;
			}
			
			if (dd.getPlotAxis()==AxisType.Y) {
				dd.setPlotAxis(AxisType.X);
				continue;
			}
		}
	}

	public boolean isExpression() {
		return expression;
	}

	public void setExpression(boolean expression) {
		this.expression = expression;
	}

	public boolean isEmpty() {
		return dimsData==null || dimsData.isEmpty();
	}

	public boolean isAdvanced() {
		for (DimsData dd : iterable()) {
            if (dd.getPlotAxis().isAdvanced()) return true;
		}
		return false;
	}

	/**
	 * DO NOT USE THIS IS FOR XML SERLIALIZATION
	 * @return
	 */
	public List<DimsData> getDimsData() {
		return dimsData;
	}

	/**
	 * DO NOT USE THIS IS FOR XML SERLIALIZATION
	 * @return
	 */
	public void setDimsData(List<DimsData> dimsData) {
		this.dimsData = dimsData;
	}

	public void removeLargeStacks(ISliceSystem slicingSystem, int maxStack) {
		
		for (DimsData dd : getDimsData()) {
			if (dd.getPlotAxis().isStack(slicingSystem)) {
				if (dd.getSliceRange(true)==null || "".equals(dd.getSliceRange(true)) || "all".equals(dd.getSliceRange(true))) {
					final ILazyDataset lz = slicingSystem.getData().getLazySet();
					if (lz!=null) {
						final int size = lz.getShape()[dd.getDimension()];
						if (size>=maxStack) { // We set a default slice
							dd.setSliceRange("0:25");
						}
					}
				}
			}
		}
		
	}

	public Slice[] toSliceArray(int[] dataShape) {
		
		final Slice[] ret = new Slice[size()];
		for (int i = 0; i < size(); i++) {
			DimsData dd = getDimsData(i);			
			if (dd.isSlice()) {
				ret[i] = new Slice(dd.getSlice(), dd.getSlice()+1);
			} else {
				ret[i] = new Slice(dataShape[dd.getDimension()]);
			}
		}
		return ret;	
	}

	/**
	 * Export to Map from DimsDataList
	 * @return
	 */
	public Map<Integer, String> toMap() {
		final Map<Integer, String> ret = new HashMap<Integer, String>(size());
		for (DimsData dd : iterable()) {
			if (dd.isSlice()) {
				ret.put(dd.getDimension(), String.valueOf(dd.getSlice()));
			} else if (dd.isTextRange()) {
				ret.put(dd.getDimension(), dd.getSliceRange()!=null ? dd.getSliceRange() : "all");
			} else if ( dd.getPlotAxis()!=null){
				ret.put(dd.getDimension(), dd.getPlotAxis().getName());
			}
		}
	    return ret;
	}

    /**
     * Set the current DimsDataList to what is defined in the pass in map.
     * @param map
     * @param shape
     */
	public void fromMap(Map<Integer, String> map, int[] shape) {
		
		clear();
		
		for (int i = 0; i < shape.length; i++) {
			add(new DimsData(i));
		}

		if (map.isEmpty()) { // Make one up
			getDimsData(0).setSliceRange("all");
			if (size()==2) {
				getDimsData(1).setPlotAxis(AxisType.X);

			} else if (size()>2) {
				getDimsData(1).setPlotAxis(AxisType.Y);
				getDimsData(2).setPlotAxis(AxisType.X);
				for (int i = 3; i < size(); i++) {
					getDimsData(i).setSlice(0);
				}
			}

		} else { // Init one from map saved

			int dim = 0;
			
			for (DimsData dd : iterable()) {
				String value = map.get(dd.getDimension());
				if (value==null) value = map.get(String.valueOf(dd.getDimension()));
				if (value!=null) {
					if ("all".equals(value)) {
						dd.setPlotAxis(AxisType.RANGE);
						continue;
					}
					
					AxisType at = AxisType.forLabel(value);
					if (at!=null) {
						dd.setPlotAxis(at);
						continue;
					}
					
					try {
						dd.setSlice(Integer.parseInt(value));
					} catch (Exception ne) {
						dd.setSliceRange(value);
					}
				} else {
					AxisType type = AxisType.forAxis(dim);
					dd.setPlotAxis(type);
					++dim;
				}
			}

		}
		
	}

}
