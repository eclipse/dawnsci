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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.dawnsci.doe.DOEField;
import org.eclipse.dawnsci.doe.DOEUtils;


/**
 * Bean to hold slice data
 */
public class DimsData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6889488003603498855L;

	/**
	 * The slice index if this dimension is sliced.
	 * 
	 * Should not really be public:

	 * THIS METHOD IS PUBLIC BECAUSE THE BEAN REFLECTION WAS NOT WORKING
	 * AND SAVING THE SLICE IN JAVA7. BY MAKING IT PUBLIC THE GETTERS AND
	 * SETTERS ARE NOT RELIED ON AND IT SETS VALUE DIRECTLY.
	 * 
	 */
	public int       slice=0;


	@DOEField(value=1, type=java.lang.Integer.class)
	private String    sliceRange;

	/**
	 * Data dimension 0,1,2,3,4 etc. as the shape dictates
	 */
	private int       dimension=-1;
	
	/**
	 * 0=x, 1=y, 2=z
	 */
	private AxisType       plotAxis=AxisType.SLICE;
	
	/**
	 * The span if a chunk is being pulled using the slice
	 * Usually the slice then becomes the start of the chunk.
	 */
	private int sliceSpan=5;

	public DimsData() {
		
	}
	
	public DimsData(final int dim) {
		this.dimension = dim;
	}

	/**
	 * IMPORTANT: DO not include sliceSpan! Or table does not refresh properly as it changes.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dimension;
		result = prime * result
				+ ((plotAxis == null) ? 0 : plotAxis.hashCode());
		result = prime * result + slice;
		result = prime * result
				+ ((sliceRange == null) ? 0 : sliceRange.hashCode());
		return result;
	}

	/**
	 * IMPORTANT: DO not include sliceSpan! Or table does not refresh properly as it changes.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DimsData other = (DimsData) obj;
		if (dimension != other.dimension)
			return false;
		if (plotAxis != other.plotAxis)
			return false;
		if (slice != other.slice)
			return false;
		if (sliceRange == null) {
			if (other.sliceRange != null)
				return false;
		} else if (!sliceRange.equals(other.sliceRange))
			return false;
		return true;
	}

	public String getSliceRange() {
		return getSliceRange(false);
	}
	public String getSliceRange(boolean force) {
		if (!force && !plotAxis.hasValue()) return null;
		return sliceRange;
	}

	public void setSliceRange(String sliceRange) {
		setSliceRange(sliceRange, false);
	}
	
	public void setSliceRange(String sliceRange, boolean changeAxis) {
		if (changeAxis && sliceRange!=null) setPlotAxis(AxisType.RANGE);
		this.sliceRange = sliceRange;
	}

	/**
	 * 0-based dimension.
	 * @return
	 */
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * -1=slice, 0=x, 1=y, 2=z, 102=range
	 */	
	public AxisType getPlotAxis() {
		return plotAxis;
	}

	public void setPlotAxis(AxisType axis) {
		this.plotAxis = axis;
	}

	public int getSlice() {
		if (plotAxis!=AxisType.SLICE && !plotAxis.isAdvanced()) return -1;
		return slice;
	}

	public void setSlice(int slice) {
		this.slice = slice;
	}

	@Override
	public String toString() {
		try {
			return BeanUtils.describe(this).toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String getUserString(final int upperRange){ 
		if (!plotAxis.hasValue()) return plotAxis.getLabel();
		if (isSlice()&&!getPlotAxis().isAdvanced()) return String.valueOf(getSlice());
		if (sliceRange!=null) return sliceRange;
		if (upperRange>0) return slice+";"+(upperRange-1)+";1";
        return String.valueOf(slice);
	}

	public List<DimsData> expand(final int size) {
		return expand(size, null);
	}

	@SuppressWarnings("unchecked")
	public List<DimsData> expand(final int size, ISliceRangeSubstituter substituter) {
		
		final List<DimsData> ret = new ArrayList<DimsData>(7);
		if (plotAxis!=AxisType.RANGE) {
			ret.add(this);
			return ret;
		}
		
		String sr = sliceRange;
		if (sr!=null) {

			if (substituter!=null) sr = substituter.substitute(sr);
			
			final Matcher matcher = Pattern.compile("(\\d+)\\:(\\d+)").matcher(sr);
			
			List<Number> rs;
			if ("all".equals(sr)) {
				rs = new ArrayList<Number>();
				for (int i = 0; i < size; i++) rs.add(i);
				
			} else if (matcher.matches()) {
				rs = new ArrayList<Number>();
				int start = Integer.parseInt(matcher.group(1));
				int end   = Integer.parseInt(matcher.group(2));
				for (int i = start; i <= end; i++) rs.add(i);

			} else {
				rs = (List<Number>)DOEUtils.expand(sr);
			}
			
			for (Number number : rs) {
				final DimsData val = new DimsData(this.dimension);
				val.setSlice(number.intValue());
				ret.add(val);
			}

			return ret;
		}
		
		for (int i = slice; i < size; i++) {
			final DimsData val = new DimsData(this.dimension);
			val.setSlice(i);
			ret.add(val);
		}
		return ret;
	}

	
	public DimsData clone() {
		final DimsData clone = new DimsData();
		clone.sliceRange = this.sliceRange;

		clone.dimension  = this.dimension;
		clone.plotAxis   = this.plotAxis;
		clone.slice      = this.slice;
        return clone;
	}

	public boolean isSlice() {
		return getPlotAxis()==AxisType.SLICE;
	}
	public boolean isTextRange() {
		return getPlotAxis()==AxisType.RANGE || getPlotAxis()==AxisType.Y_MANY;
	}

	public int getSliceSpan() {
		return sliceSpan;
	}

	public void setSliceSpan(int sliceSpan) {
		this.sliceSpan = sliceSpan;
	}

	public String getShapeLabel() {
		if (getPlotAxis().hasValue()) return getPlotAxis().getLabel();
		return String.valueOf(slice);
	}
}
