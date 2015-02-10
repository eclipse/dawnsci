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

package org.eclipse.dawnsci.analysis.dataset.roi.handler;

import org.eclipse.dawnsci.analysis.dataset.roi.GridROI;

/**
 * Wrapper class for a RectangularROI that adds handles
 * A GridROI is the same as a RectangularROI, but with grid information
 */
public class GridROIHandler extends RectangularROIHandler {

	/**
	 * @param roi
	 */
	public GridROIHandler(GridROI roi) {
		super(roi);
	}
}
