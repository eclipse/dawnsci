/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi;

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.dataset.impl.BooleanDataset;

public class MaskingBean implements Serializable {

	public BooleanDataset mask = null;
	public Integer max = null;
	public Integer min = null;
	
	public MaskingBean(BooleanDataset maskDataset, Integer minthres, Integer maxthres) {
		mask = maskDataset;
		max = maxthres;
		min = minthres;
	}
	
	public BooleanDataset getMask() {
		return mask;
	}
}
