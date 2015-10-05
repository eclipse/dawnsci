/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This is the description of a detector module. Many detectors consist of
 * multiple
 * smaller modules. Sometimes it is important to know the exact position of such
 * modules.
 * This is the purpose of this group. It is a child group to NXdetector.
 * 
 * @version 1.0
 */
public class NXdetector_moduleImpl extends NXobjectImpl implements NXdetector_module {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

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

	protected NXdetector_moduleImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXdetector_module.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_DETECTOR_MODULE;
	}

	@Override
	public IDataset getData_origin() {
		return getDataset(NX_DATA_ORIGIN);
	}

	public void setData_origin(IDataset data_origin) {
		setDataset(NX_DATA_ORIGIN, data_origin);
	}

	@Override
	public IDataset getData_size() {
		return getDataset(NX_DATA_SIZE);
	}

	public void setData_size(IDataset data_size) {
		setDataset(NX_DATA_SIZE, data_size);
	}

	@Override
	public IDataset getModule_offset() {
		return getDataset(NX_MODULE_OFFSET);
	}

	public void setModule_offset(IDataset module_offset) {
		setDataset(NX_MODULE_OFFSET, module_offset);
	}

	@Override
	public String getModule_offsetAttributeTransformation_type() {
		return getAttrString(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	public void setModule_offsetAttributeTransformation_type(String transformation_type) {
		setAttribute(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getModule_offsetAttributeVector() {
		return getAttrNumber(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_VECTOR);
	}

	public void setModule_offsetAttributeVector(Number vector) {
		setAttribute(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getModule_offsetAttributeOffset() {
		return getAttrNumber(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_OFFSET);
	}

	public void setModule_offsetAttributeOffset(Number offset) {
		setAttribute(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getModule_offsetAttributeOffset_units() {
		return getAttrString(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_OFFSET_UNITS);
	}

	public void setModule_offsetAttributeOffset_units(String offset_units) {
		setAttribute(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getModule_offsetAttributeDepends_on() {
		return getAttrString(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_DEPENDS_ON);
	}

	public void setModule_offsetAttributeDepends_on(String depends_on) {
		setAttribute(NX_MODULE_OFFSET, NX_MODULE_OFFSET_ATTRIBUTE_DEPENDS_ON, depends_on);
	}

	@Override
	public IDataset getFast_pixel_direction() {
		return getDataset(NX_FAST_PIXEL_DIRECTION);
	}

	public void setFast_pixel_direction(IDataset fast_pixel_direction) {
		setDataset(NX_FAST_PIXEL_DIRECTION, fast_pixel_direction);
	}

	@Override
	public String getFast_pixel_directionAttributeTransformation_type() {
		return getAttrString(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	public void setFast_pixel_directionAttributeTransformation_type(String transformation_type) {
		setAttribute(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getFast_pixel_directionAttributeVector() {
		return getAttrNumber(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_VECTOR);
	}

	public void setFast_pixel_directionAttributeVector(Number vector) {
		setAttribute(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getFast_pixel_directionAttributeOffset() {
		return getAttrNumber(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_OFFSET);
	}

	public void setFast_pixel_directionAttributeOffset(Number offset) {
		setAttribute(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getFast_pixel_directionAttributeOffset_units() {
		return getAttrString(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_OFFSET_UNITS);
	}

	public void setFast_pixel_directionAttributeOffset_units(String offset_units) {
		setAttribute(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getFast_pixel_directionAttributeDepends_on() {
		return getAttrString(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_DEPENDS_ON);
	}

	public void setFast_pixel_directionAttributeDepends_on(String depends_on) {
		setAttribute(NX_FAST_PIXEL_DIRECTION, NX_FAST_PIXEL_DIRECTION_ATTRIBUTE_DEPENDS_ON, depends_on);
	}

	@Override
	public IDataset getSlow_pixel_direction() {
		return getDataset(NX_SLOW_PIXEL_DIRECTION);
	}

	public void setSlow_pixel_direction(IDataset slow_pixel_direction) {
		setDataset(NX_SLOW_PIXEL_DIRECTION, slow_pixel_direction);
	}

	@Override
	public String getSlow_pixel_directionAttributeTransformation_type() {
		return getAttrString(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	public void setSlow_pixel_directionAttributeTransformation_type(String transformation_type) {
		setAttribute(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getSlow_pixel_directionAttributeVector() {
		return getAttrNumber(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_VECTOR);
	}

	public void setSlow_pixel_directionAttributeVector(Number vector) {
		setAttribute(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getSlow_pixel_directionAttributeOffset() {
		return getAttrNumber(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_OFFSET);
	}

	public void setSlow_pixel_directionAttributeOffset(Number offset) {
		setAttribute(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getSlow_pixel_directionAttributeOffset_units() {
		return getAttrString(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_OFFSET_UNITS);
	}

	public void setSlow_pixel_directionAttributeOffset_units(String offset_units) {
		setAttribute(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getSlow_pixel_directionAttributeDepends_on() {
		return getAttrString(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_DEPENDS_ON);
	}

	public void setSlow_pixel_directionAttributeDepends_on(String depends_on) {
		setAttribute(NX_SLOW_PIXEL_DIRECTION, NX_SLOW_PIXEL_DIRECTION_ATTRIBUTE_DEPENDS_ON, depends_on);
	}

}
