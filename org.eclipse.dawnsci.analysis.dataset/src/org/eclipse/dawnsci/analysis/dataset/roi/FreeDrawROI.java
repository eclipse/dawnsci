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

/**
 * Class for a FreeDraw ROI
 */
public class FreeDrawROI extends PolylineROI implements Serializable {

	/**
	 * Zero point line
	 */
	public FreeDrawROI() {
		super();
	}

	public FreeDrawROI(double[] start) {
		super(start);
	}

	public FreeDrawROI(double x, double y) {
		super(x, y);
	}

	@Override
	public String toString() {
		/**
		 * You cannot have pts.toString() if the pts contains this! It
		 * is a recursive call. Fix to defect https://trac/scientific_software/ticket/1943
		 */
		if (pts.contains(this)) return super.toString();
		return pts.toString();
	}
}
