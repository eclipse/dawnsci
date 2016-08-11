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

package org.eclipse.dawnsci.analysis.dataset.impl.function;

import java.util.List;

import org.eclipse.january.dataset.IDataset;

/**
 * This interface is to define functions that take a dataset or an array of datasets and returns numbers
 * (which could be complex and so returned as pairs)
 */
public interface DatasetToNumberFunction {

	/**
	 * @param datasets
	 * @return list of objects
	 */
	public List<? extends Number> value(IDataset... datasets);

}
