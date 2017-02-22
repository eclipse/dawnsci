/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset;

import java.lang.ref.SoftReference;
import java.util.IdentityHashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.dataset.impl.function.DatasetToDatasetFunction;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;

/**
 * Cache of computed dataset values
 */
public class DatasetCache {

	private DatasetToDatasetFunction function;
	private Map<IDataset, SoftReference<Dataset>> map;

	/**
	 * Create a cache for given function
	 * @param function
	 */
	public DatasetCache(DatasetToDatasetFunction function) {
		this.function = function;
		map = new IdentityHashMap<IDataset, SoftReference<Dataset>>();
	}

	/**
	 * Retrieve computed value (evaluate if not cached)
	 * @param input
	 * @return computed value
	 */
	public Dataset get(IDataset input) {
		SoftReference<Dataset> ref = map.get(input);
		Dataset output;
		if (ref == null || ref.get() == null) {
			output = DatasetUtils.convertToDataset(function.value(input).get(0));
			map.put(input, new SoftReference<Dataset>(output));
		} else {
			output = ref.get();
		}
		return output;
	}

	/**
	 * Remove entry from cache
	 * @param input
	 * @return true if was cached
	 */
	public boolean remove(Dataset input) {
		SoftReference<Dataset> ref = map.remove(input);
		if (ref == null || ref.get() == null) {
			return false;
		}
		ref.clear();
		return true;
	}

	/**
	 * Clear cache
	 */
	public void clear() {
		map.clear();
	}
}
