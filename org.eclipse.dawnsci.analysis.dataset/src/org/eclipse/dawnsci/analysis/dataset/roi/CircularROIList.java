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

import java.util.ArrayList;

import org.eclipse.dawnsci.analysis.api.roi.IROI;


/**
 * Wrapper for a list of circular ROIs
 */
public class CircularROIList extends ArrayList<CircularROI> implements ROIList<CircularROI> {

	@Override
	public boolean add(IROI roi) {
		if (roi instanceof CircularROI)
			return super.add((CircularROI) roi);
		return false;
	}

	@Override
	public boolean append(CircularROI roi) {
		return super.add(roi);
	}
}
