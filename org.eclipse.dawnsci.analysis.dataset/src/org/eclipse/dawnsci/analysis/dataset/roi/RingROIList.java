/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *    Baha El Kassaby
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi;

import java.util.ArrayList;

import org.eclipse.dawnsci.analysis.api.roi.IROI;

/**
 * Wrapper for a list of ring ROIs
 */
public class RingROIList extends ArrayList<RingROI> implements ROIList<RingROI> {
	@Override
	public boolean add(IROI roi) {
		if (roi instanceof RingROI)
			return super.add((RingROI) roi);
		return false;
	}

	@Override
	public boolean append(RingROI roi) {
		return super.add(roi);
	}
}
