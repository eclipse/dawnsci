/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Collection of axis-based translations and rotations to describe a geometry.
 * May also contain axes that do not move and therefore do not have a transformation
 * type specified, but are useful in understanding coordinate frames within which
 * transformations are done, or in documenting important directions, such as the
 * direction of gravity.
 * A nested sequence of transformations lists the offset and rotation steps
 * needed to describe the position and orientation of any movable or fixed device.
 * There will be one or more transformations (axes) defined by one or more fields
 * for each transformation. The all-caps name ``AXISNAME`` designates the
 * particular axis generating a transformation (e.g. a rotation axis or a translation
 * axis or a general axis). The attribute ``units="NX_TRANSFORMATION"`` designates the
 * units will be appropriate to the ``transformation_type`` attribute:
 * * ``NX_LENGTH`` for ``translation``
 * * ``NX_ANGLE`` for ``rotation``
 * * ``NX_UNITLESS`` for axes for which no transformation type is specified
 * This class will usually contain all axes of a sample stage or goniometer or
 * a detector. The NeXus default McSTAS coordinate frame is assumed, but additional
 * useful coordinate axes may be defined by using axes for which no transformation
 * type has been specified.
 * The entry point (``depends_on``) will be outside of this class and point to a
 * field in here. Following the chain may also require following ``depends_on``
 * links to transformations outside, for example to a common base table. If
 * a relative path is given, it is relative to the group enclosing the ``depends_on``
 * specification.
 * For a chain of three transformations, where :math:`T_1` depends on :math:`T_2`
 * and that in turn depends on :math:`T_3`, the final transformation :math:`T_f` is
 * .. math::
 * T_f = T_3 T_2 T_1
 * In explicit terms, the transformations are a subset of affine transformations
 * expressed as 4x4 matrices that act on homogeneous coordinates, :math:`w=(x,y,z,1)^T`.
 * For rotation and translation,
 * .. math::
 * T_r &= \left( \begin{matrix} R & o \\
 * 0_3 & 1 \end{matrix} \right) \\
 * T_t &= \left( \begin{matrix} I_3 & t + o \\
 * 0_3 & 1 \end{matrix} \right)
 * where :math:`R` is the usual 3x3 rotation matrix, :math:`o` is an offset vector,
 * :math:`0_3` is a row of 3 zeros, :math:`I_3` is the 3x3 identity matrix and
 * :math:`t` is the translation vector.
 * :math:`o` is given the ``offset`` attribute, :math:`t` is given by the ``vector``
 * attribute multiplied by the field value, and :math:`R` is defined as a rotation
 * about an axis in the direction of ``vector``, of angle of the field value.
 * 
 * @version 1.1
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
	public IDataset getAXISNAME() {
		return getDataset(NX_AXISNAME);
	}

	@Override
	public Number getAXISNAMEScalar() {
		return getNumber(NX_AXISNAME);
	}

	@Override
	public DataNode setAXISNAME(IDataset AXISNAME) {
		return setDataset(NX_AXISNAME, AXISNAME);
	}

	@Override
	public DataNode setAXISNAMEScalar(Number AXISNAME) {
		return setField(NX_AXISNAME, AXISNAME);
	}

	@Override
	public Map<String, IDataset> getAllAXISNAME() {
		return getAllDatasets();
	}

	public void setAXISNAME(String name, IDataset AXISNAME) {
		setDataset(name, AXISNAME);
	}

	@Override
	public String getAXISNAMEAttributeTransformation_type() {
		return getAttrString(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_TRANSFORMATION_TYPE);
	}

	@Override
	public void setAXISNAMEAttributeTransformation_type(String transformation_type) {
		setAttribute(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_TRANSFORMATION_TYPE, transformation_type);
	}

	@Override
	public Number getAXISNAMEAttributeVector() {
		return getAttrNumber(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_VECTOR);
	}

	@Override
	public void setAXISNAMEAttributeVector(Number vector) {
		setAttribute(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_VECTOR, vector);
	}

	@Override
	public Number getAXISNAMEAttributeOffset() {
		return getAttrNumber(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_OFFSET);
	}

	@Override
	public void setAXISNAMEAttributeOffset(Number offset) {
		setAttribute(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public String getAXISNAMEAttributeOffset_units() {
		return getAttrString(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_OFFSET_UNITS);
	}

	@Override
	public void setAXISNAMEAttributeOffset_units(String offset_units) {
		setAttribute(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_OFFSET_UNITS, offset_units);
	}

	@Override
	public String getAXISNAMEAttributeDepends_on() {
		return getAttrString(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_DEPENDS_ON);
	}

	@Override
	public void setAXISNAMEAttributeDepends_on(String depends_on) {
		setAttribute(NX_AXISNAME, NX_AXISNAME_ATTRIBUTE_DEPENDS_ON, depends_on);
	}

	@Override
	public IDataset getAXISNAME_end() {
		return getDataset(NX_AXISNAME_END);
	}

	@Override
	public Number getAXISNAME_endScalar() {
		return getNumber(NX_AXISNAME_END);
	}

	@Override
	public DataNode setAXISNAME_end(IDataset AXISNAME_end) {
		return setDataset(NX_AXISNAME_END, AXISNAME_end);
	}

	@Override
	public DataNode setAXISNAME_endScalar(Number AXISNAME_end) {
		return setField(NX_AXISNAME_END, AXISNAME_end);
	}

	@Override
	public Map<String, IDataset> getAllAXISNAME_end() {
		return getAllDatasets();
	}

	public void setAXISNAME_end(String name, IDataset AXISNAME_end) {
		setDataset(name, AXISNAME_end);
	}

	@Override
	public IDataset getAXISNAME_increment_set() {
		return getDataset(NX_AXISNAME_INCREMENT_SET);
	}

	@Override
	public Number getAXISNAME_increment_setScalar() {
		return getNumber(NX_AXISNAME_INCREMENT_SET);
	}

	@Override
	public DataNode setAXISNAME_increment_set(IDataset AXISNAME_increment_set) {
		return setDataset(NX_AXISNAME_INCREMENT_SET, AXISNAME_increment_set);
	}

	@Override
	public DataNode setAXISNAME_increment_setScalar(Number AXISNAME_increment_set) {
		return setField(NX_AXISNAME_INCREMENT_SET, AXISNAME_increment_set);
	}

	@Override
	public Map<String, IDataset> getAllAXISNAME_increment_set() {
		return getAllDatasets();
	}

	public void setAXISNAME_increment_set(String name, IDataset AXISNAME_increment_set) {
		setDataset(name, AXISNAME_increment_set);
	}

}
