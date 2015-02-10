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

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Object to hold return data from an IOperation.
 */
public class OperationData {

	private IDataset data;
	private Serializable[] auxData;

	public IDataset getData() {
		return data;
	}

	public OperationData() {
		this(null, (Serializable) null);
	}

	public OperationData(IDataset data) {
		this(data, (Serializable) null);
	}

	public OperationData(IDataset data, Serializable... aux) {
		this.data = data;
		this.auxData = aux;
	}

	public void setData(IDataset data) {
		this.data = data;
	}

	public Serializable[] getAuxData() {
		return auxData;
	}

	public void setAuxData(Serializable... auxData) {
		this.auxData = auxData;
	}

}
