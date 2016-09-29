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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

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
public class NXtransformationsImpl extends NXobjectImpl implements NXtransformations {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXtransformationsImpl() {
		super();
	}

	public NXtransformationsImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXtransformations.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_TRANSFORMATIONS;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getTRANSFORMATION() {
		return getDataset(NX_TRANSFORMATION);
	}

	@Override
	public Number getTRANSFORMATIONScalar() {
		return getNumber(NX_TRANSFORMATION);
	}

	@Override
	public DataNode setTRANSFORMATION(IDataset TRANSFORMATION) {
		return setDataset(NX_TRANSFORMATION, TRANSFORMATION);
	}

	@Override
	public DataNode setTRANSFORMATIONScalar(Number TRANSFORMATION) {
		return setField(NX_TRANSFORMATION, TRANSFORMATION);
	}

	@Override
	public Map<String, IDataset> getAllTRANSFORMATION() {
		return getAllDatasets();
	}

	public void setTRANSFORMATION(String name, IDataset TRANSFORMATION) {
		setDataset(name, TRANSFORMATION);
	}

	@Override
	public String getTRANSFORMATIONAttributeTransformation_type() {
		return getAttrString(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	@Override
	public void setTRANSFORMATIONAttributeTransformation_type(String transformation_type) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getTRANSFORMATIONAttributeVector() {
		return getAttrNumber(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_VECTOR);
	}

	@Override
	public void setTRANSFORMATIONAttributeVector(Number vector) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getTRANSFORMATIONAttributeOffset() {
		return getAttrNumber(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET);
	}

	@Override
	public void setTRANSFORMATIONAttributeOffset(Number offset) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getTRANSFORMATIONAttributeOffset_units() {
		return getAttrString(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET_UNITS);
	}

	@Override
	public void setTRANSFORMATIONAttributeOffset_units(String offset_units) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getTRANSFORMATIONAttributeDepends_on() {
		return getAttrString(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_DEPENDS_ON);
	}

	@Override
	public void setTRANSFORMATIONAttributeDepends_on(String depends_on) {
		setAttribute(NX_TRANSFORMATION, NX_TRANSFORMATION_ATTRIBUTE_DEPENDS_ON, depends_on);
	}

}
