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

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;

/**
 * Used with 
 */
public interface SliceVisitor {

	/**
	 * Visit each slice
	 * @param data - the data sliced from the lazy data set
	 * @throws Exception
	 */
	public void visit(IDataset data) throws Exception;
	
	/**
	 * 
	 * @return true if the slicing is cancelled.
	 */
	public boolean isCancelled();
}
