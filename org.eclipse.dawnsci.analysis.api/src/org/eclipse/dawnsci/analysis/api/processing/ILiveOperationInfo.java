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

import org.eclipse.january.dataset.IDynamicDataset;


public interface ILiveOperationInfo {
	
	public IDynamicDataset[] getKeys();
	
	public IDynamicDataset getComplete();

}
