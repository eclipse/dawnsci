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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;

/**
 * Interface to encapsulate processing operations on an IRichDataset
 * The idea of this class is that its data context, the data it is operating
 * on is passed in as an IRichDataset. Then each slice is operated on in the
 * execute method.
 * 
 * For instance background correction, azimuthal integration.
 * 
 * Your operation many be contributed via an extension point from any plugin. 
 * In this case the operation will be instantiated through a no argument constructor
 * so that the operation type can be retrieved. 
 * 
 * When you create an IOperation is must be marked with @Operation so that 
 * it can be read into the operation service.
 */
public interface IOperation<M extends IOperationModel, D extends OperationData> {
	
	/**
	 * The operation name. The operation service allows operations to be 
	 * found by name which can be used to determine available operations.
	 * 
	 * This is usually set by the extension point, so there is no need to implement this
	 * if extending AbstractOperation
	 * 
	 * @return OperationType
	 */
	public String getName();

	/**
	 * The operation description. The operation service allows operations to be 
	 * found by name which can be used to determine available operations.
	 * 
	 * This is usually set by the extension point, so there is no need to implement this
	 * if extending AbstractOperation
	 * 
	 * @return OperationType
	 */
	public String getDescription();

	/**
	 * 
	 * @return The unique id for this operation.
	 */
	public String getId();

		
	/**
	 * The execute method operates on the data set last send via setDataset() and the
	 * information specific to this operation provided and 
	 * returns the processed data in the form of an IDataset
	 * 
	 * Multiple setData(..) and execute methods may be called. In addition execute()
	 * may be called multiple times for operations such as differential. Each time
	 * operating on the result of the last execution.
	 * 
	 * @return dataset which is the result of this operation.
	 */
	public D execute(IDataset slice, IMonitor monitor) throws OperationException;
	
	
	/**
	 * 
	 * @return the model which is to be used or null if no model has been set.
	 */
	public M getModel();

	
	/**
	 * The list of parameters are specific to the operation we are doing. NOTE parameters should not
	 * be data contained in IRichDataset, like Datasets, Metadata and Regions. Parameter examples are:
	 * double values for tolerances, xml configurations, JSON strings etc.
	 * 
	 * @param model
	 * @throws IllegalArgumentException if the parameters are not those required by the operation.
	 */
	public void setModel(M model);

	/**
	 * Gets the rank of input data for the operations algorithm. You might be iterating
	 * 4D data but when this operation is run on a slice of it, it must be done with a
	 * fixed input rank.
	 * 
	 * @return rank of slice data which we accept. For instance for integration we input 2(image) and output 1(integration)
	 */
	public OperationRank getInputRank();
	
	
	/**
	 * Gets the rank of output data for the operations algorithm. You might be iterating
	 * 4D data but when this operation is run on a slice of it, it must be done with a
	 * fixed input rank and this input will result in a fixed output rank.
	 * 
	 * @return rank of output data which we return. For instance for integration we input 2(image) and output 1(integration)
	 */
	public OperationRank getOutputRank();
	
	/**
	 * Set whether the output of this operation should be stored, for example, as intermediate data in an output file
	 * 
	 * @param storeOutput
	 */
	public void setStoreOutput(boolean storeOutput);
	
	/**
	 * Checks whether this operation has been flagged to have its output stored
	 * 
	 * @return storeOutput
	 */
	public boolean isStoreOutput();
	
	/**
	 * Sets whether to pass the processed or unprocessed (input) data into the next operation. This value is just a flag for the pipeline running the operation,
	 * operations implementing this interface should always return the processed OperationData.
	 * 
	 * For example, if set to true, the input to this operation is sent to the next operation in the series,
	 * if false, the output of this operation is passed as the input to the next operation.
	 * 
	 * @param passUnmodifiedData
	 */
	public void setPassUnmodifiedData(boolean passUnmodifiedData);

	/**
	 * Checks whether the processed or unprocessed data should be passed down the pipe
	 * @return passUnmodifiedData
	 */
	public boolean isPassUnmodifiedData();
	
	/**
	 * Called on each operation before a new file is processed
	 */
	public void init();
}
