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
 * This is the description of a detector module. Many detectors consist of
 * multiple
 * smaller modules. Sometimes it is important to know the exact position of such
 * modules.
 * This is the purpose of this group. It is a child group to NXdetector.
 * 
 * @version 1.0
 */
public interface NXdetector_module extends NXobject {

	/**
	 * A two value field which gives the index of the start of the
	 * modules data in the
	 * main area detector image in the underlying NXdetector module.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData_origin();

	/**
	 * Two values for the size of the module in pixels in each direction.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData_size();

	/**
	 * Offset of the module in regards to the origin of the detector in an
	 * arbitrary direction.
	 * <p>
	 * <b>Units:</b> NX_LENGTH
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getModule_offset();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getModule_offsetAttributeTransformation_type();

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getModule_offsetAttributeVector();

	/**
	 * A fixed offset applied before the transformation
	 * 
	 * @return  the value.
	 */
	public Number getModule_offsetAttributeOffset();

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getModule_offsetAttributeOffset_units();

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getModule_offsetAttributeDepends_on();

	/**
	 * Values along the direction of fastest varying pixel direction.The direction
	 * itself is given through the vector attribute
	 * <p>
	 * <b>Units:</b> NX_LENGTH
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFast_pixel_direction();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getFast_pixel_directionAttributeTransformation_type();

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getFast_pixel_directionAttributeVector();

	/**
	 * A fixed offset applied before the transformation
	 * 
	 * @return  the value.
	 */
	public Number getFast_pixel_directionAttributeOffset();

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getFast_pixel_directionAttributeOffset_units();

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getFast_pixel_directionAttributeDepends_on();

	/**
	 * Values along the direction of slow varying pixel direction. The direction
	 * itself is given through the vector attribute
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSlow_pixel_direction();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getSlow_pixel_directionAttributeTransformation_type();

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getSlow_pixel_directionAttributeVector();

	/**
	 * A fixed offset applied before the transformation
	 * 
	 * @return  the value.
	 */
	public Number getSlow_pixel_directionAttributeOffset();

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getSlow_pixel_directionAttributeOffset_units();

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getSlow_pixel_directionAttributeDepends_on();

}
