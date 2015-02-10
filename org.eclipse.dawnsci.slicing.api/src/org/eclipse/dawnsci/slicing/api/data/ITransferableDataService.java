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
package org.eclipse.dawnsci.slicing.api.data;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.plotting.api.expressions.IExpressionObject;

/**
 * A service for making ITransferableDataObjects
 * 
 * These objects can be used for providing data into different parts of the UI.
 * 
 * @author Matthew Gerring
 *
 */
public interface ITransferableDataService {

	/**
	 * Create a normal ITransferableDataObject. The name must reference one of the 
	 * keys in the IDataHolder.
	 * 
	 * @param holder
	 * @param meta
	 * @param name
	 * @return
	 */
	public ITransferableDataObject createData(IDataHolder holder, IMetadata meta, String name);

	/**
	 * Create a ITransferableDataObject which will be used to hold an expression
	 * @param holder
	 * @param meta
	 * @return
	 */
	public ITransferableDataObject createExpression(IDataHolder holder, IMetadata meta) ;
	
	/**
	 * Create a ITransferableDataObject expression
	 * @param holder
	 * @param meta
	 * @param expression
	 * @return
	 */
	public ITransferableDataObject createExpression(IDataHolder holder, IMetadata meta, IExpressionObject expression);
	

	/**
	 * Gets the current copied object, if any.
	 * 
	 * @return
	 */
	public ITransferableDataObject getBuffer();
	
	/**
	 * 
	 * @param buf
	 * @return previous bufer or null if there was none.
	 */
	public ITransferableDataObject setBuffer(ITransferableDataObject buf);

}
