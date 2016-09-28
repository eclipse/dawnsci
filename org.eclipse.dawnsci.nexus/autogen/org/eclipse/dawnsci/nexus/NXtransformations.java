/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-09-28T15:24:07.968+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

/**
 * Collection of translations and rotations to describe a geometry
 * A sequence of transformations lists the
 * offset and rotation steps needed to describe the position and orientation
 * of any movable or fixed device.
 * This class will usually contain all axes of a sample stage or goniometer.
 * The entry point (``depends_on``) will be outside of this class and point to a
 * field in here. Following the chain may also require following ``depends_on``
 * links to transformations outside, for example to a common base table.
 * ..
 * Given an entry point :math:`\vec{p_i}`, the point :math:`\vec{p_{i+1}}`
 * resulting from the next transformation (the field in this group named
 * in the ``depends_on`` attribute of the entry point)
 * may be expressed:
 * .. math::
 * \vec{p_{i+1}} = \vec{o_{i}} + \vec{V_{i}} * \vec{p_{i}}
 * where :math`\vec{o_{i}}` is the vector given in the ``@offset`` attribute
 * and :math`\vec{V_{i}}` is the vector given in the ``@vector`` attribute
 * TODO: this needs an equation (issue #484)
 * 
 * @version 1.0
 */
public interface NXtransformations extends NXobject {

	public static final String NX_TRANSFORMATION = "TRANSFORMATION";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_VECTOR = "vector";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_DEPENDS_ON = "depends_on";
	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not defined. The user is free to use any name
	 * that does not cause confusion. When using more than one TRANSFORMATION field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTRANSFORMATION();
	
	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not defined. The user is free to use any name
	 * that does not cause confusion. When using more than one TRANSFORMATION field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param TRANSFORMATION the TRANSFORMATION
	 */
	public DataNode setTRANSFORMATION(IDataset TRANSFORMATION);

	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not defined. The user is free to use any name
	 * that does not cause confusion. When using more than one TRANSFORMATION field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getTRANSFORMATIONScalar();

	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not defined. The user is free to use any name
	 * that does not cause confusion. When using more than one TRANSFORMATION field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param TRANSFORMATION the TRANSFORMATION
	 */
	public DataNode setTRANSFORMATIONScalar(Number TRANSFORMATION);
  
	/**
	 * Get all TRANSFORMATION fields:
	 *
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not defined. The user is free to use any name
	 * that does not cause confusion. When using more than one TRANSFORMATION field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  a map from node names to the ? extends IDataset for that node.
	 */
	public Map<String, ? extends IDataset> getAllTRANSFORMATION();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTRANSFORMATIONAttributeTransformation_type();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @param transformation_type the transformation_type
	 */
	public void setTRANSFORMATIONAttributeTransformation_type(String transformation_type);

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getTRANSFORMATIONAttributeVector();
	
	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @param vector the vector
	 */
	public void setTRANSFORMATIONAttributeVector(Number vector);

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @return  the value.
	 */
	public Number getTRANSFORMATIONAttributeOffset();
	
	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @param offset the offset
	 */
	public void setTRANSFORMATIONAttributeOffset(Number offset);

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getTRANSFORMATIONAttributeOffset_units();
	
	/**
	 * Units of the offset.
	 * 
	 * @param offset_units the offset_units
	 */
	public void setTRANSFORMATIONAttributeOffset_units(String offset_units);

	/**
	 * Points to the name of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getTRANSFORMATIONAttributeDepends_on();
	
	/**
	 * Points to the name of the next element in the geometry chain.
	 * 
	 * @param depends_on the depends_on
	 */
	public void setTRANSFORMATIONAttributeDepends_on(String depends_on);

}
