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
package org.eclipse.dawnsci.doe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains a list of parameters that are part of the range.
 */
public class RangeInfo implements Serializable {

	private List<FieldValue> experiments;

	public RangeInfo() {
		experiments = new ArrayList<FieldValue>(7);
	}
		
	public void clear() {
		experiments.clear();
	}

	/**
	 * @return Returns the experiments.
	 */
	public List<FieldValue> getExperiments() {
		return experiments;
	}

	/**
	 * @param e The experiments to set.
	 */
	public void setExperiments(List<FieldValue> e) {
		if (e == null) {
			experiments.clear();
			return;
		}
		this.experiments = e;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((experiments == null) ? 0 : experiments.hashCode());
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
		RangeInfo other = (RangeInfo) obj;
		if (experiments == null) {
			if (other.experiments != null)
				return false;
		} else if (!experiments.equals(other.experiments))
			return false;
		return true;
	}

	public void set(FieldValue fieldValue) {
		final int index = experiments.indexOf(fieldValue);
		if (index>-1) {
			experiments.set(index, fieldValue);
			return;
		}
		experiments.add(fieldValue);
	}

	public static String format(final List<RangeInfo> info) {

		final StringBuilder buf = new StringBuilder();
		
		// Do header
		buf.append(info.get(0).getHeader());
		buf.append("\n");
		
		// Do values
		for (RangeInfo rangeInfo : info) {
			buf.append(rangeInfo.getValues());
			buf.append("\n");
		}
		
		return buf.toString();
	}

	public String getHeader() {
		final StringBuilder buf = new StringBuilder();
		for (FieldValue field : getExperiments()) {
			buf.append(field.getName());
			buf.append("\t");
		}
		return buf.toString();
	}
	
	public String getValues() {
		final StringBuilder buf = new StringBuilder();
		for (FieldValue field : getExperiments()) {
			buf.append(field.getValue());
			buf.append("\t");
		}
		return buf.toString();
	}

	public boolean isEmpty() {
		return experiments.isEmpty();
	}

	public Map<String, Class<?>> getColumnClasses() {
		final Map<String, Class<?>> columns = new LinkedHashMap<String, Class<?>>(31);
		for (FieldValue fv : experiments) {
			columns.put(fv.getName(), fv.getOriginalObject().getClass());
		}
		return columns;
	}

	/**
	 * Fields should not be large so a loop is ok, could
	 * replace with a map one day.
	 * 
	 * @param name
	 * @return value or null
	 */
	public String getColumnValue(final String name) {
		if (experiments.isEmpty()) return null;
		if (name == null)          return null;
		for (FieldValue fv : experiments) {
			if (name.equals(fv.getName())) return fv.getValue();
		}
		
		return null;
	}
}
