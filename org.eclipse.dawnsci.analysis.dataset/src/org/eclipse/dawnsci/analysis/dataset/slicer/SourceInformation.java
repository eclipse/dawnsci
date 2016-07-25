/*
 * Copyright (c) 2014, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.eclipse.january.dataset.ILazyDataset;

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
