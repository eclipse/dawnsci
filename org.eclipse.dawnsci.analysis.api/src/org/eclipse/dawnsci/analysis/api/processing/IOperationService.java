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

import java.util.Collection;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;

/**
 * This is a service for creating and returning operations.
 * 
 * Usage:
 * <code>
 *  IOperationService service = ...  // OSGI injection
 *  IOperationContext context = service.createContext();
 *  context.setData(...);
 *  context.setSlicing(...);
 *  context.setSeries(...);
 *  service.execute(context);
 *  
 * </code>
 *  
 * 
 * The service is provided by another plugin and returned using OSGI.
 */
public interface IOperationService {
	
	/**
	 * Creates an empty context 
     * You must set at least the data, slicing and series of operations on this
     * context.
	 * @return context
	 */
	public IOperationContext createContext();
	
	/**
	 * Executes a chain of operations in series.
	 *
     * NOTE the operations must have their model data set prior to execution.
     *
	 * @param context
	 * @throws OperationException
	 */
	public void execute(IOperationContext context) throws OperationException;
	
	
	/**
	 * Get the name of the operation with this ID
	 * @param id
	 * @return name
	 */
	public String getName(String id) throws Exception;
	
	/**
	 * Get the description of the operation with this ID
	 * @param id
	 * @return description
	 */
	public String getDescription(String id) throws Exception;

	/**
	 * Finds all operations by doing a search
	 * using the regex passed in. All operations whose descriptions/IDs
	 * match the regex are returned. For instance:
	 * 
	 * find("correction");  // Might give operations "Dark correction", "Flat correction"
	 * find("integration"); // Might give operations "Azimuthal integration", "Radial integration", "Box integration", "Line integration"
	 * 
	 * NOTE the regex will be matched as follows on the id of the operation:
	 * 1. if matching on the ID
	 * 2. if matching the description in lower case.
	 * 3. if indexOf the regex in the ID is >0
	 * 4. if indexOf the regex in the description is >0
	 * 
	 * @param operationRegex
	 * @return list of operations which match
	 */
	public Collection<IOperation<? extends IOperationModel, ? extends OperationData>> find(String operationRegex)  throws Exception;
	
	/**
	 * Finds all operations with either the input rank or output rank (when isInput=false)
	 * is passed in.
	 * 
	 * If rank of ANY is used then only those matching exactly ANY are returned, not all operations.
	 * If rank of SAME is used then only those matching exactly SAME are returned, not all operations.
	 * 
	 * @param rank
	 * @param isInput - true to search inputs, false to search outputs.
	 * @return list of operations which match
	 */
	public Collection<IOperation<? extends IOperationModel, ? extends OperationData>> find(OperationRank rank, boolean isInput)  throws Exception;

	/**
	 * Finds the first operation matching a search
	 * using the regex passed in. All operations whose descriptions/IDs
	 * match the regex are returned. For instance:
	 * 
	 * find("correction");  // Might give operations "Dark correction", "Flat correction"
	 * find("integration"); // Might give operations "Azimuthal integration", "Radial integration", "Box integration", "Line integration"
	 * 
	 * NOTE the regex will be matched as follows on the ID of the operation:
	 * 1. if matching on the ID
	 * 2. if matching the description in lower case.
	 * 3. if indexOf the regex in the ID is >0
	 * 4. if indexOf the regex in the description is >0
	 * 
	 * @param operationRegex
	 * @return list of operations which match
	 */
	public IOperation<? extends IOperationModel, ? extends OperationData>  findFirst(String operationRegex)  throws Exception;
	
	/**
	 * Gets the IDs of all the operations. The id is defined in the extension point.
	 * @return all the IDs which have been contributed via extensions
	 */
	public Collection<String> getRegisteredOperations()  throws Exception;

	/**
	 * Get operations by category.
	 * @return a Map of operations by category. Sorted alphabetically the key is the category description.
	 * Uncategorized operations appear at the end.
	 */
	public Map<String, Collection<IOperation<? extends IOperationModel, ? extends OperationData>>> getCategorizedOperations() throws Exception;
	
	/**
	 * Creates an operation by using its type. This method will create a new
	 * operation using the no argument constructor.
	 * 
	 * @return IOperation
	 */
	public IOperation<? extends IOperationModel, ? extends OperationData> create(String operationId) throws Exception;
	
	/**
	 * Checks the extension point for the model class pertinent to this operation.
	 * 
	 * @return IOperation
	 */
	public Class<? extends IOperationModel> getModelClass(String operationId) throws Exception;

	/**
	 * Method to validate a pipeline, throwing an exception if the pipeline is not valid.
	 * 
	 * @param firstSlice
	 * @param series
	 * @throws InvalidRankException
	 */
	public void validate(IDataset firstSlice, IOperation<? extends IOperationModel, ? extends OperationData>... series) throws InvalidRankException, OperationException;

	/**
	 * Runs a set of operations by following a graph chaining the operations together.
	 * This run uses a recursive method and 
	 * @param root
	 * @return IRichDataset
	 */
	// If we start to need things like this:
	//public IRichDataset executeGraph(IOperationNode root) throws OperationException;
	// Then this service has inadvertently become a workflow tool and we are reinventing Ptolemy
	
	
	/**
	 * Call this method to load some operations not by extension point but by parsing the classes
	 * in a package which implement IOperation. This is useful if your code for the operations is
	 * not eclipse or you are writing a unit test.
	 * 
	 * @param l classloader where package can be found.
	 * @param pakage separated by dots.
	 */
	public void createOperations(ClassLoader l, String pakage)  throws Exception;
	
	/**
	 * Return the category a the operation with the specified id belongs to
	 * 
	 * @param operationId
	 * @return category
	 */
	public OperationCategory getCategory(String operationId);
	
}
