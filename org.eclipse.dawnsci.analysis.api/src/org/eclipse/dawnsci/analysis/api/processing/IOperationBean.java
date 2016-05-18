/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

import java.util.Map;

public interface IOperationBean {

	void setDataKey(String dataKey);

	void setFilePath(String fileName);

	void setOutputFilePath(String outputFilePath);

	void setDatasetPath(String datasetPath);

	void setSlicing(String slicing);

	void setPersistencePath(String persistencePath);

	void setAxesNames(Map<Integer, String> axesNames);

	void setParallelTimeout(long parallelTimeout);

	void setDeletePersistenceFile(boolean deletePersistenceFile);

	void setXmx(String xmx);

	void setDataDimensions(int[] dataDimensions);

	void setReadable(boolean readable);

}