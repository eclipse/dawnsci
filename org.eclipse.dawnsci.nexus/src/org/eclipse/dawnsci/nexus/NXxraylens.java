/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * This is a dictionary of field names to use for
 * describing a X-ray lens as used at
 * synchrotron beam lines.
 * Based on information provided by Gerd Wellenreuther.
 * 
 * @version 1.0
 */
public interface NXxraylens extends NXobject {

	/**
	 * Geometry of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>paraboloid</b> </li>
	 * <li><b>spherical</b> </li>
	 * <li><b>elliptical</b> </li>
	 * <li><b>hyperbolical</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_geometry();

	/**
	 * Is the device symmetric?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSymmetric();

	/**
	 * Is the device cylindrical?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCylindrical();

	/**
	 * Orientation of the cylinder axis.
	 * 
	 * @return  the value.
	 */
	public NXnote getCylinder_orientation();

	/**
	 * The type of focus of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>line</b> </li>
	 * <li><b>point</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFocus_type();

	/**
	 * Thickness of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_thickness();

	/**
	 * Length of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_length();

	/**
	 * Radius of the curvature as measured in the middle of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCurvature();

	/**
	 * Diameter or radius of the lens.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAperture();

	/**
	 * Number of lenses that make up the compound lens.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNumber_of_lenses();

	/**
	 * Material used to make the lens.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_material();

	/**
	 * Gas used to fill the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGas();

	/**
	 * Gas pressure in the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGas_pressure();

}
