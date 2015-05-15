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

package org.eclipse.dawnsci.analysis.api.tree;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;

/**
 * Node that holds data
 */
public interface DataNode extends Node {

	/**
	 * @return true if data is unsigned
	 */
	public boolean isUnsigned();

	/**
	 * @param isUnsigned
	 */
	public void setUnsigned(boolean isUnsigned);

	/**
	 * @return true if data is string data
	 */
	public boolean isString();

	/**
	 * @return HDF5 type name
	 */
	public String getTypeName();

	/**
	 * Set HDF5 type name
	 * @param name
	 */
	public void setTypeName(String name);

	/**
	 * @return maximum shape
	 */
	public long[] getMaxShape();

	/**
	 * Set maximum shape of dataset
	 * @param maxShape
	 */
	public void setMaxShape(long[] maxShape);

	/**
	 * @return true if this dataset is supported
	 */
	public boolean isSupported();

	/**
	 * Set dataset to be empty
	 */
	public void setEmpty();

	/**
	 * Get a string if this dataset is a string or dataset
	 * @return string or null
	 */
	public String getString();

	/**
	 * Set string
	 * @param text
	 */
	public void setString(String text);

	/**
	 * Get maximum length of any string contained in node when it is encoded as bytes
	 * @return length in bytes or -1 if not a string
	 */
	public int getMaxStringLength();

	/**
	 * Set maximum length of any string contained in node when it is encoded as bytes
	 * @param length in bytes
	 */
	public void setMaxStringLength(int length);

	/**
	 * This can return null for empty datasets
	 * @return lazy dataset
	 */
	public ILazyDataset getDataset();

	/**
	 * This can return null if not writeable or is empty
	 * @return lazy writeable dataset
	 */
	public ILazyWriteableDataset getWriteableDataset();

	/**
	 * Set given (lazy) dataset
	 * @param lazyDataset
	 */
	public void setDataset(ILazyDataset lazyDataset);

	/**
	 * @return true if dataset has been augmented with metadata
	 */
	public boolean isAugmented();

	/**
	 * Set state to indicate dataset has been augmented with metadata
	 */
	public void setAugmented();
}
