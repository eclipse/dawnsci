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

import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * This interface specifies the methods required to allow the ScanFileHolder to load in data from a file of some
 * description
 */
public interface IFileLoader {

	/**
	 * This function is called when the ScanFileHolder needs to load data from a particular source.
	 * It can also be called on by itself
	 * 
	 * @return This returned object is all the data which has been loaded returned in a small object package.
	 * @throws ScanFileHolderException
	 */
	IDataHolder loadFile() throws ScanFileHolderException;

	/**
	 * This function is called when the ScanFileHolder needs to load data from a particular source
	 * It can also be called on by itself
	 * 
	 * @param mon - may be null
	 * @return This returned object is all the data which has been loaded returned in a small object package.
	 * @throws ScanFileHolderException
	 */
	IDataHolder loadFile(IMonitor mon) throws ScanFileHolderException;

	/**
	 * Set the loader to load metadata as well as data (all loaders default to this behaviour)
	 * 
	 * @param willLoadMetadata
	 */
	void setLoadMetadata(boolean willLoadMetadata);

	/**
	 * Set the loader to load all datasets lazily
	 * 
	 * @param willLoadLazily
	 */
	void setLoadAllLazily(boolean willLoadLazily);

	/**
	 * If a loader supports asynchronous load
	 * Throws RuntimeException is asynch not supported.
	 * @param treeOnTop
	 */
	void setAsyncLoad(boolean treeOnTop);

	/**
	 * If this loader supports asynchronous loading
	 * @return if loading or throws RuntimeException if asynchronous loading is not supported.
	 */
	boolean isLoading();
}
