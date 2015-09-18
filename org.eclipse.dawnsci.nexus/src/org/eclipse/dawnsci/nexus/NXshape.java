/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * This is the description of the general shape and size of a
 * component, which may be made up of ``numobj`` separate
 * elements - it is used by the :ref:`NXgeometry` class
 * 
 * @version 1.0
 */
public interface NXshape extends NXobject {

	/**
	 * general shape of a component
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>nxflat</b> </li>
	 * <li><b>nxcylinder</b> </li>
	 * <li><b>nxbox</b> </li>
	 * <li><b>nxsphere</b> </li>
	 * <li><b>nxcone</b> </li>
	 * <li><b>nxelliptical</b> </li>
	 * <li><b>nxtoroidal</b> </li>
	 * <li><b>nxparabolic</b> </li>
	 * <li><b>nxpolynomial</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getShape();

	/**
	 * physical extent of the object along its local axes (after NXorientation)
	 * with the center of mass at the local origin (after NXtranslation).
	 * The meaning and location of these axes will vary according to the value
	 * of the "shape" variable.
	 * ``nshapepar`` defines how many parameters:
	 * - For "nxcylinder" type the parameters are (diameter,height) and a three value orientation vector of the cylinder.
	 * - For the "nxbox" type the parameters are (length,width,height).
	 * - For the "nxsphere" type the parameters are (diameter).
	 * - For nxcone cone half aperture
	 * - For nxelliptical, semi-major axis, semi-minor-axis, angle of major axis and pole
	 * - For nxtoroidal, major radius, minor radius
	 * - For nxparabolic, parabolic parameter a
	 * - For nxpolynomial, an array of polynom coefficients, the dimension of the array
	 * encodes the degree of the polynom
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: numobj; 2: nshapepar;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSize();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>concave</b> </li>
	 * <li><b>convex</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDirection();

}
