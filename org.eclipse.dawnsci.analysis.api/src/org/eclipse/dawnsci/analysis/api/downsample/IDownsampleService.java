/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.downsample;

import java.util.List;

import org.eclipse.january.dataset.IDataset;

/**
 * Service for downsampling data.
 */
public interface IDownsampleService {

	/**
	 * Downsample  As in Downsample.fromString(...)
	 *
	 * In DAWN source code the implementation of this service is:
     * @see "uk.ac.diamond.scisoft.analysis.dataset.function.Downsample"
	 * 
	 * @param enc <Scheme to merge pixels>:<Shape of bin>
	 *        examples: 'MEAN:2', 'MEAN:2x3', 'MAXIMUM:2x2', 'MINIMUM:4x4', 'POINT:2x3x4'
	 *        
	 * @param data - the datasets to downsample    
	 *    
	 * @throws Exception if downsample encoding is incorrect
	 */
	public List<? extends IDataset> downsample(String enc, IDataset... data) throws Exception;
	
	/**
	 * Same as the downsample with a string argument but instead of encoding the
	 * types in the string, they are provided directly.
	 * @param mode
	 * @param binShape
	 * @param data
	 * @return list of downsampled datasets
	 */
	public List<? extends IDataset> downsample(DownsampleMode mode, int[] binShape, IDataset... data) throws Exception;
}
