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
 * Geometry and logical description of a detector module.
 * Many detectors consist of multiple
 * smaller modules. Sometimes it is important to know the exact position of such
 * modules.
 * This is the purpose of this group. It is a child group to NXdetector.
 * 
 * @version 1.0
 */
public interface NXdetector_module extends NXobject {

	public static final String NX_DATA_ORIGIN = "data_origin";
	public static final String NX_DATA_SIZE = "data_size";
	public static final String NX_MODULE_OFFSET = "module_offset";
	public static final String NX_MODULE_OFFSET_ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_MODULE_OFFSET_ATTRIBUTE_VECTOR = "vector";
	public static final String NX_MODULE_OFFSET_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_MODULE_OFFSET_ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_MODULE_OFFSET_ATTRIBUTE_DEPENDS_ON = "depends_on";
	public static final String NX_FAST_PIXEL_DIRECTION = "fast_pixel_direction";
	public static final String NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_VECTOR = "vector";
	public static final String NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_DEPENDS_ON = "depends_on";
	public static final String NX_SLOW_PIXEL_DIRECTION = "slow_pixel_direction";
	public static final String NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_VECTOR = "vector";
	public static final String NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_DEPENDS_ON = "depends_on";
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
	 * A two value field which gives the index of the start of the
	 * modules data in the
	 * main area detector image in the underlying NXdetector module.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param data_origin the data_origin
	 */
	public DataNode setData_origin(IDataset data_origin);

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
	public long getData_originScalar();

	/**
	 * A two value field which gives the index of the start of the
	 * modules data in the
	 * main area detector image in the underlying NXdetector module.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param data_origin the data_origin
	 */
	public DataNode setData_originScalar(long data_origin);

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
	 * Two values for the size of the module in pixels in each direction.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param data_size the data_size
	 */
	public DataNode setData_size(IDataset data_size);

	/**
	 * Two values for the size of the module in pixels in each direction.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getData_sizeScalar();

	/**
	 * Two values for the size of the module in pixels in each direction.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param data_size the data_size
	 */
	public DataNode setData_sizeScalar(long data_size);

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
	 * Offset of the module in regards to the origin of the detector in an
	 * arbitrary direction.
	 * <p>
	 * <b>Units:</b> NX_LENGTH
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param module_offset the module_offset
	 */
	public DataNode setModule_offset(IDataset module_offset);

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
	public Number getModule_offsetScalar();

	/**
	 * Offset of the module in regards to the origin of the detector in an
	 * arbitrary direction.
	 * <p>
	 * <b>Units:</b> NX_LENGTH
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param module_offset the module_offset
	 */
	public DataNode setModule_offsetScalar(Number module_offset);

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
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li></ul></p>
	 * </p>
	 * 
	 * @param transformation_type the transformation_type
	 */
	public void setModule_offsetAttributeTransformation_type(String transformation_type);

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getModule_offsetAttributeVector();
	
	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @param vector the vector
	 */
	public void setModule_offsetAttributeVector(Number vector);

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @return  the value.
	 */
	public Number getModule_offsetAttributeOffset();
	
	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @param offset the offset
	 */
	public void setModule_offsetAttributeOffset(Number offset);

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getModule_offsetAttributeOffset_units();
	
	/**
	 * Units of the offset.
	 * 
	 * @param offset_units the offset_units
	 */
	public void setModule_offsetAttributeOffset_units(String offset_units);

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getModule_offsetAttributeDepends_on();
	
	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @param depends_on the depends_on
	 */
	public void setModule_offsetAttributeDepends_on(String depends_on);

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
	 * Values along the direction of fastest varying pixel direction.The direction
	 * itself is given through the vector attribute
	 * <p>
	 * <b>Units:</b> NX_LENGTH
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param fast_pixel_direction the fast_pixel_direction
	 */
	public DataNode setFast_pixel_direction(IDataset fast_pixel_direction);

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
	public Number getFast_pixel_directionScalar();

	/**
	 * Values along the direction of fastest varying pixel direction.The direction
	 * itself is given through the vector attribute
	 * <p>
	 * <b>Units:</b> NX_LENGTH
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param fast_pixel_direction the fast_pixel_direction
	 */
	public DataNode setFast_pixel_directionScalar(Number fast_pixel_direction);

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
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li></ul></p>
	 * </p>
	 * 
	 * @param transformation_type the transformation_type
	 */
	public void setFast_pixel_directionAttributeTransformation_type(String transformation_type);

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getFast_pixel_directionAttributeVector();
	
	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @param vector the vector
	 */
	public void setFast_pixel_directionAttributeVector(Number vector);

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @return  the value.
	 */
	public Number getFast_pixel_directionAttributeOffset();
	
	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @param offset the offset
	 */
	public void setFast_pixel_directionAttributeOffset(Number offset);

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getFast_pixel_directionAttributeOffset_units();
	
	/**
	 * Units of the offset.
	 * 
	 * @param offset_units the offset_units
	 */
	public void setFast_pixel_directionAttributeOffset_units(String offset_units);

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getFast_pixel_directionAttributeDepends_on();
	
	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @param depends_on the depends_on
	 */
	public void setFast_pixel_directionAttributeDepends_on(String depends_on);

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
	 * Values along the direction of slow varying pixel direction. The direction
	 * itself is given through the vector attribute
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param slow_pixel_direction the slow_pixel_direction
	 */
	public DataNode setSlow_pixel_direction(IDataset slow_pixel_direction);

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
	public Number getSlow_pixel_directionScalar();

	/**
	 * Values along the direction of slow varying pixel direction. The direction
	 * itself is given through the vector attribute
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param slow_pixel_direction the slow_pixel_direction
	 */
	public DataNode setSlow_pixel_directionScalar(Number slow_pixel_direction);

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
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li></ul></p>
	 * </p>
	 * 
	 * @param transformation_type the transformation_type
	 */
	public void setSlow_pixel_directionAttributeTransformation_type(String transformation_type);

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getSlow_pixel_directionAttributeVector();
	
	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @param vector the vector
	 */
	public void setSlow_pixel_directionAttributeVector(Number vector);

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @return  the value.
	 */
	public Number getSlow_pixel_directionAttributeOffset();
	
	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @param offset the offset
	 */
	public void setSlow_pixel_directionAttributeOffset(Number offset);

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getSlow_pixel_directionAttributeOffset_units();
	
	/**
	 * Units of the offset.
	 * 
	 * @param offset_units the offset_units
	 */
	public void setSlow_pixel_directionAttributeOffset_units(String offset_units);

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getSlow_pixel_directionAttributeDepends_on();
	
	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @param depends_on the depends_on
	 */
	public void setSlow_pixel_directionAttributeDepends_on(String depends_on);

}
