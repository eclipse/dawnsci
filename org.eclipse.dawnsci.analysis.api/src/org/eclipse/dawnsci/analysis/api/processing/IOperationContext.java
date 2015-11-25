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

package org.eclipse.dawnsci.analysis.api.processing;

import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;

/**
 * Essential things to set
 * 1. Either: 
 *   a) an ILazyDataset (setData(..)) or    [If the lazydataset is there it takes precidence.]
 *   b) the filePath and datasetPath
 * 
 * 2. The slicing
 * 4. The series to run
 * 
 * Optional
 * 1. Execution type
 * 2. Parallel timeout (default 5000ms) [series runs have no timeout]
 * 3. Vistor
 * 4. Monitor
 * 
 * Parallel Timeout Handling:
 * ExecutionType.GRAPH has 10 minutes applied when setExecutionType(...) is called, if parallelTimeout=-1
 *    You can use setParallelTimeout(...) to change this.

 */
public interface IOperationContext {

	/**
	 * 
	 * @return The data which we are slicing
	 */
	public ILazyDataset getData() throws Exception;
	
	/**
	 * 
	 * The data which we are slicing
	 */
	public void setData(ILazyDataset data);

	/**
	 * Path to data file
	 */
	public String getFilePath();
	
	/**
	 * Path to data file
	 * @param filePath
	 */
	public void setFilePath(String filePath);
	
	/**
	 * Path to dataset
	 * @return path
	 */
	public String getDatasetPath();
	
	/**
	 * Path to dataset
	 * @param datasetPath
	 */
	public void setDatasetPath(String datasetPath);


	/**
	 * 
	 * @return A map of data dimension to String representing the slice done on this dimension. 
	 */
	public SliceND getSlicing();

	/**
	 * 
	 * A map of data dimension to String representing the slice done on this dimension. 
	 */
	public void setSlicing(SliceND slicing);
	
	
	public int[] getDataDimensions();
	
	public void setDataDimensions(int[] dataDimensions);
	
	
	/**
	 * 
	 * @return the pipeline
	 */ 
	public IOperation<? extends IOperationModel, ? extends OperationData>[] getSeries();

	/**
	 * Set the pipeline, must be non-null.
	 * @param series
	 */
	public void setSeries(IOperation<? extends IOperationModel, ? extends OperationData>... series);

	/**
	 * Set the pipeline, must be non-null.
	 * @param series
	 */
	public void setSeries(List<IOperation<? extends IOperationModel, ? extends OperationData>> series);

	/**
	 * 
	 * @return monitor  or null
	 */
	public IMonitor getMonitor();

	/**
	 * 
	 * @param monitor
	 */
	public void setMonitor(IMonitor monitor);

	/**
	 * 
	 * @return vistor or null
	 */
	public IExecutionVisitor getVisitor();

	/**
	 * 
	 * @param visitor
	 */
	public void setVisitor(IExecutionVisitor visitor);

	/**
	 * 
	 * @return execution type.
	 */
	public ExecutionType getExecutionType();
	
	/**
	 * 
	 * @param executionType
	 */
	public void setExecutionType(ExecutionType executionType) ;

	/**
	 * Timeout in parallel mode.
	 * @return timeout
	 */
	public long getParallelTimeout();


	/**
	 * Optionally configure the parallel timeout of this service.
	 * 
	 * @param timeoutMs
	 */
	public void setParallelTimeout(long timeoutMs);

	/**
	 * The number of threads in the pool to execute the pipeline
	 * as an event directory pipeline.
	 * 
	 * return the count
	 */
	public int getPoolSize();
	
	
	/**
	 * The number of images which may be queued in each actor.
	 * By default the value is 1. Set it higher if you have the 
	 * memory as bottle necks will be reduced.
	 * 
	 * return the count
	 */
	public void setPoolSize(int count);
	
	public void setKey(ILazyDataset key);
	
	public ILazyDataset getKey();

}
