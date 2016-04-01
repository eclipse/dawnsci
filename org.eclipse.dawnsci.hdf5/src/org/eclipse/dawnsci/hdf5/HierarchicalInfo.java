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
package org.eclipse.dawnsci.hdf5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Information for the H5Loader. H5Loader is not normally active as it is superseded by HDF5Loader
 */
public class HierarchicalInfo {
	
	public static final String NAPIMOUNT = "napimount";
	public static final String NAPISCHEME = "nxfile";

	private Map<String, Integer> datasetSizes;
	private Map<String, int[]>   datasetShapes;
	private Map<String, String>  attributeValues;
	
	protected HierarchicalInfo() {
		datasetSizes  = new LinkedHashMap<String,Integer>(31);
		datasetShapes = new LinkedHashMap<String,int[]>(31);
		attributeValues = new LinkedHashMap<String, String>(89);
	}

	public void clear() {
		datasetSizes.clear();
		datasetShapes.clear();
		attributeValues.clear();
	}

	public List<String> getDataSetNames() {
		return Collections.unmodifiableList(new ArrayList<>(datasetShapes.keySet()));
	}

	public Map<String, Integer> getDataSetSizes() {
		return datasetSizes;
	}
	public Map<String, int[]> getDataSetShapes() {
		return datasetShapes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datasetShapes == null) ? 0 : datasetShapes.hashCode());
		result = prime * result
				+ ((datasetSizes == null) ? 0 : datasetSizes.hashCode());
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
		HierarchicalInfo other = (HierarchicalInfo) obj;
		if (datasetShapes == null) {
			if (other.datasetShapes != null)
				return false;
		} else if (!datasetShapes.equals(other.datasetShapes))
			return false;
		if (datasetSizes == null) {
			if (other.datasetSizes != null)
				return false;
		} else if (!datasetSizes.equals(other.datasetSizes))
			return false;
		return true;
	}

	public void addDataInfo(String fullName, int[] shape, long size) {
		datasetShapes.put(fullName, shape);
		datasetSizes.put(fullName, (int) size);
	}

	public void putAttribute(String fullAttributePath, String value) {
		this.attributeValues.put(fullAttributePath, value);
	}

	/**
	 * Returns a list of all the full paths to all the attributes.
	 * @return
	 */
	public Collection<String> getAttributes() {
		return attributeValues.keySet();
	}
	
	public String getAttributeValue(String key) {
		return attributeValues.get(key);
	}

}
