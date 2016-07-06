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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
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
	public IDataset getAnonymous__NEEDS_XSD_CHANGE__() {
		return getDataset(NX_ANONYMOUS__NEEDS_XSD_CHANGE__);
	}

	@Override
	public Number getAnonymous__NEEDS_XSD_CHANGE__Scalar() {
		return getNumber(NX_ANONYMOUS__NEEDS_XSD_CHANGE__);
	}

	@Override
	public DataNode setAnonymous__NEEDS_XSD_CHANGE__(IDataset anonymous__NEEDS_XSD_CHANGE__) {
		return setDataset(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, anonymous__NEEDS_XSD_CHANGE__);
	}

	@Override
	public DataNode setAnonymous__NEEDS_XSD_CHANGE__Scalar(Number anonymous__NEEDS_XSD_CHANGE__) {
		return setField(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, anonymous__NEEDS_XSD_CHANGE__);
	}

	@Override
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeTransformation_type() {
		return getAttrString(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	@Override
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeTransformation_type(String transformation_type) {
		setAttribute(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getAnonymous__NEEDS_XSD_CHANGE__AttributeVector() {
		return getAttrNumber(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_VECTOR);
	}

	@Override
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeVector(Number vector) {
		setAttribute(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getAnonymous__NEEDS_XSD_CHANGE__AttributeOffset() {
		return getAttrNumber(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_OFFSET);
	}

	@Override
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeOffset(Number offset) {
		setAttribute(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeOffset_units() {
		return getAttrString(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_OFFSET_UNITS);
	}

	@Override
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeOffset_units(String offset_units) {
		setAttribute(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeDepends_on() {
		return getAttrString(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_DEPENDS_ON);
	}

	@Override
	public void setAnonymous__NEEDS_XSD_CHANGE__AttributeDepends_on(String depends_on) {
		setAttribute(NX_ANONYMOUS__NEEDS_XSD_CHANGE__, NX_ANONYMOUS__NEEDS_XSD_CHANGE___ATTRIBUTE_DEPENDS_ON, depends_on);
	}

}
