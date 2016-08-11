/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-06-07T11:32:35.177+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * Collection of translations and rotations to describe a geometry
 * A sequence of transformations lists the
 * offset and rotation steps needed to describe the position and orientation
 * of any movable or fixed device.
 * This class will usually contain all axis of a sample stage or goniometer.
 * The entry point (``depends_on``) will be outside of this class and point to a
 * field in here. Following the chain may also require following ``depends_on``
 * links to transformations outside, for example to a common base table.
 * 
 * @version 1.0
 */
public interface NXtransformations extends NXobject {

	public static final String NX_ANONYMOUS__NEEDS_XSD_CHANGE__ = "anonymous__NEEDS_XSD_CHANGE__";
	public static final String NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_VECTOR = "vector";
	public static final String NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_OFFSET = "offset";
	public static final String NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_DEPENDS_ON = "depends_on";
	/**
	 * Units need to be appropriate for translation or rotation
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAnonymous__NEEDS_XSD_CHANGE__();
	
	/**
	 * Units need to be appropriate for translation or rotation
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param anonymous__NEEDS_XSD_CHANGE__ the anonymous__NEEDS_XSD_CHANGE_
	 */
	public DataNode setAnonymous__NEEDS_XSD_CHANGE__(IDataset anonymous__NEEDS_XSD_CHANGE__);

	/**
	 * Units need to be appropriate for translation or rotation
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAnonymous__NEEDS_XSD_CHANGE__Scalar();

	/**
	 * Units need to be appropriate for translation or rotation
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param anonymous__NEEDS_XSD_CHANGE__ the anonymous__NEEDS_XSD_CHANGE_
	 */
	public DataNode setAnonymous__NEEDS_XSD_CHANGE__Scalar(Number anonymous__NEEDS_XSD_CHANGE__);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeTransformation_type();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @param transformation_type the transformation_type
	 */
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeTransformation_type(String transformation_type);

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getAnonymous__NEEDS_XSD_CHANGE__AttributeVector();
	
	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @param vector the vector
	 */
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeVector(Number vector);

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @return  the value.
	 */
	public Number getAnonymous__NEEDS_XSD_CHANGE__AttributeOffset();
	
	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @param offset the offset
	 */
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeOffset(Number offset);

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeOffset_units();
	
	/**
	 * Units of the offset.
	 * 
	 * @param offset_units the offset_units
	 */
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeOffset_units(String offset_units);

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeDepends_on();
	
	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @param depends_on the depends_on
	 */
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeDepends_on(String depends_on);

}
