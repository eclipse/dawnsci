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
 * Wrapper for a list of polygonal ROIs
 */
public class PolygonalROIList extends ArrayList<PolygonalROI> implements ROIList<PolygonalROI> {

	@Override
	public boolean add(IROI roi) {
		if (roi instanceof PolygonalROI)
			return super.add((PolygonalROI) roi);
		return false;
	}

	@Override
	public boolean append(PolygonalROI roi) {
		return super.add(roi);
	}
}
