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

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.metadata.OriginMetadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;

/**
 * This interface is designed to be called when a series of operations has been 
 * completed on a dataset which is being processed. It is a visitor pattern
 * which is notified of the processed data in the stack. The user then has the option
 * of keeping the full data in memory (if possible), writing to file or plotting and
 * then letting the data go out of scope.
 */
public interface IExecutionVisitor {
	
	/**
	 * Initialise the execution visitor with the series of operations that are going to run
	 *  
	 * @param series
	 * @throws Exception
	 */
	public void init(IOperation<? extends IOperationModel, ? extends OperationData>[] series, ILazyDataset dataset ) throws Exception;
	
	/**
	 * Tell the execution visitor to close, releasing its resources.
	 * 
	 * Called when the pipeline has run through completely.
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception;
	
    /**
     * Called when an execution in the pipeline has run, before the end	but after a given operation.
     * 
     * Provides the option of saving the steps information to a file if required.
     * 
     * @param intermediateData
     * @param data
     */
	public void notify(IOperation<? extends IOperationModel, ? extends OperationData> intermediateData, OperationData data);
	
	/**
	 * Called when the series of operations has been done, with the current slice
	 * @param result
	 */
	public void executed(OperationData result, IMonitor monitor) throws Exception;
	
	
	public class Stub implements IExecutionVisitor {

		@Override
		public void executed(OperationData result, IMonitor monitor) throws Exception {
			
		}

		@Override
		public void notify(IOperation<? extends IOperationModel, ? extends OperationData> intermeadiateData, OperationData data) {
			
		}

		@Override
		public void init(IOperation<? extends IOperationModel, ? extends OperationData>[] series, ILazyDataset dataset) {
			
		}

		@Override
		public void close() throws Exception {
			
		}
		
	}



}
