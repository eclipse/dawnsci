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
package org.eclipse.dawnsci.hdf5.model;

import org.eclipse.core.resources.IFile;

/**
 * A Model of a collection of Hierarchical Data File (e.g. Nexus/HDF5) where
 * each file is referred to by its IFile
 *
 * @author Tracy Miranda
 */
public interface IHierarchicalDataModel {

	/**
	 * Get the File model for the given IFile.
	 *
	 * @param file
	 *            IFile to load
	 * @return a File model of the Hierarchical Data File (e.g. Nexus/HDF5)
	 */
	IHierarchicalDataFileModel getFileModel(IFile file);
}
