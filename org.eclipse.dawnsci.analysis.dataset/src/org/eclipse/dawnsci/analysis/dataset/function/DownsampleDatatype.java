/*-
 * Copyright (c) 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.function;

import org.eclipse.january.dataset.Dataset;

/**
 * Enumerations for down-sample datatypes
 */
public enum DownsampleDatatype {
	BOOL(Dataset.BOOL),
	INTEGER(Dataset.INT),
	INTEGER8(Dataset.INT8),
	INTEGER16(Dataset.INT16),
	INTEGER32(Dataset.INT32),
	INTEGER64(Dataset.INT64),
	FLOAT(Dataset.FLOAT),
	FLOAT32(Dataset.FLOAT32),
	FLOAT64(Dataset.FLOAT64);

	int datatype;

	DownsampleDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getDatatype() {
		return datatype;
	}

}
