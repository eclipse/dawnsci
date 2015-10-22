/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-13T13:58:10.369+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Use ``NXtransformations`` to gather together any set of movable or fixed
 * elements positioning the device described by the class that contains this.
 * 
 * @version 1.0
 */
public class NXtransformationsImpl extends NXobjectImpl implements NXtransformations {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TRANSFORMATION = "transformation";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_VECTOR = "vector";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_TRANSFORMATION_ATTRIBUTE_DEPENDS_ON = "depends_on";

	protected NXtransformationsImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXtransformationsImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXtransformations.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_TRANSFORMATIONS;
	}

	@Override
	public IDataset getTransformation() {
		return getDataset(NX_TRANSFORMATION);
	}

	@Override
	public Number getTransformationScalar() {
		return getNumber(NX_TRANSFORMATION);
	}

	public void setTransformation(IDataset transformation) {
		setDataset(NX_TRANSFORMATION, transformation);
	}

	public void setTransformationScalar(Number transformation) {
		setField(NX_TRANSFORMATION, transformation);
	}

	@Override
	public Map<String, Dataset> getAllTransformation() {
		return getAllDatasets(null);
	}

	public void setTransformation(String name, IDataset transformation) {
		setDataset(name, transformation);
	}

	@Override
	public String getTransformationAttributeTransformation_type() {
		return getAttrString(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	public void setTransformationAttributeTransformation_type(String transformation_type) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getTransformationAttributeVector() {
		return getAttrNumber(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_VECTOR);
	}

	public void setTransformationAttributeVector(Number vector) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getTransformationAttributeOffset() {
		return getAttrNumber(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET);
	}

	public void setTransformationAttributeOffset(Number offset) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getTransformationAttributeOffset_units() {
		return getAttrString(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET_UNITS);
	}

	public void setTransformationAttributeOffset_units(String offset_units) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getTransformationAttributeDepends_on() {
		return getAttrString(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_DEPENDS_ON);
	}

	public void setTransformationAttributeDepends_on(String depends_on) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_DEPENDS_ON, depends_on);
	}

}
