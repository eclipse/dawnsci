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
 * Class for rectangular region of interest : XAxis type
 */
public class XAxisBoxROI extends RectangularROI implements Serializable {

	/**
	 * Default square of 10 pixels
	 */
	public XAxisBoxROI() {
		super(10, 0.0);
	}

	/**
	 * Square constructor
	 * 
	 * @param width
	 * @param angle
	 */
	public XAxisBoxROI(double width, double angle) {
		super(0, 0, width, width, angle);
	}

	/**
	 * @param width
	 * @param height
	 * @param angle
	 */
	public XAxisBoxROI(double width, double height, double angle) {
		super(0, 0, width, height, angle);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param width
	 * @param height
	 * @param angle
	 */
	public XAxisBoxROI(double ptx, double pty, double width, double height, double angle) {
		super(ptx, pty, width, height, angle, false);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param width
	 * @param height
	 * @param angle
	 * @param clip 
	 */
	public XAxisBoxROI(double ptx, double pty, double width, double height, double angle, boolean clip) {
		super(ptx, pty, width, height, angle, clip);
	}
}
