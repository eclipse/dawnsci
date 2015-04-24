/*-
 * Copyright 2014 Diamond Light Source Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;

public class SourceInformation {

	private ILazyDataset parent;
	private String datasetName;
	private String filePath;
	
	/**
	 * Bean to store the information about the source of a dataset in the 
	 * SliceFromSeriesMetadata
	 * 
	 * @param filePath - the path to the data file
	 * @param datasetName - the name of the parent dataset
	 * @param parent - ILazydataset which the dataset containing this metadata comes from
	 */
	public SourceInformation(String filePath, String datasetName, ILazyDataset parent) {
		this.filePath = filePath;
		this.datasetName = datasetName;
		this.parent = parent;
		
	}
	
	public ILazyDataset getParent() {
		return parent;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public String getFilePath() {
		return filePath;
	}
	
	@Override
	public SourceInformation clone() {
		return new SourceInformation(new String(filePath), new String (datasetName), parent);
	}

}
