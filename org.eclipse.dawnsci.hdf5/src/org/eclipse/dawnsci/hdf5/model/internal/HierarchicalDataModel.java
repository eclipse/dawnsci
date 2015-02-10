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
package org.eclipse.dawnsci.hdf5.model.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataFileModel;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataModel;

public class HierarchicalDataModel implements IHierarchicalDataModel {
	private static final IHierarchicalDataFileModel INVALID_FILE_MODEL = new IHierarchicalDataFileModel() {

		@Override
		public boolean hasPath(String path) {
			return false;
		}

		@Override
		public Object getPath(String path) {
			return null;
		}
	};

	private Map<String, IHierarchicalDataFileModel> cache = Collections
			.synchronizedMap(new HashMap<String, IHierarchicalDataFileModel>());
	private IHierarchicalDataModelGetFileModel getModel;

	public HierarchicalDataModel(IHierarchicalDataModelGetFileModel getModel) {
		this.getModel = getModel;
	}

	@Override
	public IHierarchicalDataFileModel getFileModel(IFile file) {
		synchronized (cache) {
			if (file == null)
				return INVALID_FILE_MODEL;
			IPath rawLocation = file.getRawLocation();
			if (rawLocation == null)
				return INVALID_FILE_MODEL;
			final String fullPath = rawLocation.toOSString();
			if (cache.containsKey(fullPath)) {
				return cache.get(fullPath);
			}

			IHierarchicalDataFileModel model = getModel.createFileModel(fullPath);
			cache.put(fullPath, model);
			return model;
		}
	}

	/**
	 * Remove any cached information about a given file.
	 * <p>
	 * It is expected that this is called on Eclipse resource deltas to remove
	 * files that have been modified.
	 *
	 * @param file
	 *            to expunge cache for
	 */
	public void clearFileCache(IFile file) {
		if (file != null) {
			IPath rawLocation = file.getRawLocation();
			if (rawLocation != null) {
				synchronized (cache) {
					cache.remove(rawLocation.toOSString());
				}
			}
		}
	}
}
