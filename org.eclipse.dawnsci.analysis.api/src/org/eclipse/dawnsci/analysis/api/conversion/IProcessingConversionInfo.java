/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.conversion;

import org.eclipse.dawnsci.analysis.api.processing.ExecutionType;
import org.eclipse.dawnsci.analysis.api.processing.IExecutionVisitor;
import org.eclipse.dawnsci.analysis.api.processing.IOperation;

// TODO FIXME Javadocs on org.eclipse.dawnsci mandatory
public interface IProcessingConversionInfo {

	IExecutionVisitor getExecutionVisitor(String fileName);
	
	IOperation[] getOperationSeries();
	
	ProcessingOutputType getProcessingOutputType();
	
	ExecutionType getExecutionType();
	
	int getPoolSize();
	
	boolean isTryParallel();
	
}
