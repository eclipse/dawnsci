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

package org.eclipse.dawnsci.analysis.api.dataset;


/**
 * This service gives access to parts of the scisoft.analysis plugin
 * without having to import the concrete plugin uk.ac.diamond.scisoft.analysis.dataset.
 * 
 * So you don't end up with dependencies required by loaders when you do simple
 * dataset manipulation for instance.
 * 
 */
public interface IDatasetMathsService {

	/**
	 * Boolean
	 */
	public static final int BOOL = 0;

	/**
	 * Signed 8-bit integer
	 */
	public static final int INT8 = 1;

	/**
	 * Signed 16-bit integer
	 */
	public static final int INT16 = 2;

	/**
	 * Signed 32-bit integer
	 */
	public static final int INT32 = 3;
	/**
	 * Integer (same as signed 32-bit integer)
	 */
	public static final int INT = INT32;

	/**
	 * Signed 64-bit integer
	 */
	public static final int INT64 = 4;

	/**
	 * 32-bit floating point
	 */
	public static final int FLOAT32 = 5;

	/**
	 * 64-bit floating point
	 */
	public static final int FLOAT64 = 6;

	/**
	 * Floating point (same as 64-bit floating point)
	 */
	public static final int FLOAT = FLOAT64;
	
	/**
	 * Create a range without importing DatasetFactory
	 * @param stop
	 * @param dtype
	 * @return IDataset
	 */
	public IDataset createRange(final double stop, final int dtype);

	/**
	 * Create a range without importing DatasetFactory
	 * @param start
	 * @param stop
	 * @param step
	 * @param dtype
	 * @return IDataset
	 */
	public IDataset createRange(final double start, final double stop, final double step, final int dtype);

	/**
	 * Create a dataset using a bunch of doubles.
	 * @param raw
	 * @param shape
	 * @return IDataset
	 */
	public IDataset createDoubleDataset(double[] raw, int... shape);

	/**
	 * Ensures that the dataset has been converted to a Dataset (ie loaded data)
	 * @param data
	 * @return the set
	 */
	public IDataset convertToDataset(IDataset data);

	/**
	 * 
	 * @param data
	 * @param axis
	 * @return summed data
	 */
	public IDataset sum(IDataset data, int axis);

	/**
	 * 
	 * @param data
	 * @return transposed data
	 */
	public IDataset transpose(IDataset data);

	/**
	 * Take the mean along a given dimension.
	 * @param data
	 * @param axis
	 * @return mean
	 */
	public IDataset mean(IDataset data, int axis);

	/**
	 * 
	 * @param data
	 * @param axis
	 * @return max
	 */
	public IDataset max(IDataset data, int axis);

	/**
	 * 
	 * @param data
	 * @param axis
	 * @return min
	 */
	public IDataset min(IDataset data, int axis);

	/**
	 * 
	 * @param data
	 * @param axis
	 * @return median
	 */
	public IDataset median(IDataset data, int axis);

	/**
	 * 
	 * @param data
	 * @param axis
	 * @return mode
	 */
	public IDataset mode(IDataset data, int axis);
}
