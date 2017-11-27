/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

import java.io.Serializable;

import org.eclipse.january.dataset.IDataset;

/**
 * Extension to the OperationData class, including extra datasets,
 * which will not be written to file, but can be used for display
 * with the output data
 */
public class OperationDataForDisplay extends OperationData {

	private IDataset[] displayData;
	
	public OperationDataForDisplay() {
		super();
	}
	
	public OperationDataForDisplay(IDataset data) {
		super(data);
	}
	
	public OperationDataForDisplay(IDataset data, Serializable... aux) {
		super(data, aux);
	}

	public IDataset[] getDisplayData() {
		return displayData;
	}

	public void setDisplayData(IDataset... displayData) {
		this.displayData = displayData;
	}
}
