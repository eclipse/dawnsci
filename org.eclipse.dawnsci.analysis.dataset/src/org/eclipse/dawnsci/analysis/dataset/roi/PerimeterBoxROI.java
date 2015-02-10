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
 * Class for rectangular region of interest : perimeter type (with coloured edges)
 */
public class PerimeterBoxROI extends RectangularROI implements Serializable {

	/**
	 * Default square of 10 pixels
	 */
	public PerimeterBoxROI() {
		super(10, 0.0);
	}

	/**
	 * Square constructor
	 * 
	 * @param width
	 * @param angle
	 */
	public PerimeterBoxROI(double width, double angle) {
		super(width, angle);
	}

	/**
	 * @param width
	 * @param height
	 * @param angle
	 */
	public PerimeterBoxROI(double width, double height, double angle) {
		super(width, height, angle);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param width
	 * @param height
	 * @param angle
	 */
	public PerimeterBoxROI(double ptx, double pty, double width, double height, double angle) {
		super(ptx, pty, width, height, angle);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param width
	 * @param height
	 * @param angle
	 * @param clip 
	 */
	public PerimeterBoxROI(double ptx, double pty, double width, double height, double angle, boolean clip) {
		super(ptx, pty, width, height, angle, clip);
	}

	/**
	 * @return a copy
	 */
	@Override
	public PerimeterBoxROI copy() {
		PerimeterBoxROI c = new PerimeterBoxROI(spt[0], spt[1], len[0], len[1], getAngle(), isClippingCompensation());
		c.name = name;
		c.plot = plot;
		return c;
	}
}
