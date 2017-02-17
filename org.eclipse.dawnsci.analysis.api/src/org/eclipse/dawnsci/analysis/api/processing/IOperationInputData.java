/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
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

import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.january.dataset.IDataset;

public interface IOperationInputData {

	public IDataset getInputData();
	
	@SuppressWarnings("rawtypes")
	public List<IOperation> getCurrentOperations();
	
}
