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

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;

/**
 * This interface specifies the methods required to allow the ScanFileHolder to save data to a file of some
 * description
 */
public interface IFileSaver {

	/**
	 * This function is called when the ScanFileHolder needs to save data from a particular source.
	 * It can also be called on by itself
	 * 
	 * @param holder
	 *            The object storing all the data to be saved
	 * @throws ScanFileHolderException
	 */
	void saveFile(IDataHolder holder) throws ScanFileHolderException;
}
