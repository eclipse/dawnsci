/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

import java.util.List;

/**
 * Interface to set up an operation processing run
 */
public interface IOperationBean {

	public void setDataKey(String dataKey);

	public void setFilePath(String fileName);

	public void setOutputFilePath(String outputFilePath);

	public void setDatasetPath(String datasetPath);

	public void setSlicing(String slicing);

	public void setProcessingPath(String persistencePath);

	public void setAxesNames(List<String>[] axesNames);

	public void setDeleteProcessingFile(boolean deletePersistenceFile);

	public void setXmx(String xmx);

	public void setDataDimensions(int[] dataDimensions);

	public void setReadable(boolean readable);
	
	public void setName(String name);
	
	public void setRunDirectory(String visitDir);
	
	public void setParallelizable(boolean parallelizable);

}