/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This group describes a generic positioner
 * such as a motor or piezo-electric transducer. It is used to document the
 * current information of a piece of beam line equipment.
 * Note: When using multiple ``NXpositioner`` groups, it is suggested to place
 * them inside a ``NXcollection`` group. In such cases, when
 * ``NXpositioner`` is used in another class,
 * ``NXcollection/NXpositioner``
 * is then constructed.
 * 
 * @version 1.0
 */
public class NXpositionerImpl extends NXobjectImpl implements NXpositioner {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_NAME = "name";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_VALUE = "value";
	public static final String NX_RAW_VALUE = "raw_value";
	public static final String NX_TARGET_VALUE = "target_value";
	public static final String NX_TOLERANCE = "tolerance";
	public static final String NX_SOFT_LIMIT_MIN = "soft_limit_min";
	public static final String NX_SOFT_LIMIT_MAX = "soft_limit_max";
	public static final String NX_VELOCITY = "velocity";
	public static final String NX_ACCELERATION_TIME = "acceleration_time";
	public static final String NX_CONTROLLER_RECORD = "controller_record";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	protected NXpositionerImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXpositionerImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXpositioner.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_POSITIONER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getNameScalar() {
		return getString(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	public void setNameScalar(String name) {
		setString(NX_NAME, name);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setDescriptionScalar(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getValue() {
		return getDataset(NX_VALUE);
	}

	@Override
	public Number getValueScalar() {
		return getNumber(NX_VALUE);
	}

	public void setValue(IDataset value) {
		setDataset(NX_VALUE, value);
	}

	public void setValueScalar(Number value) {
		setField(NX_VALUE, value);
	}

	@Override
	public IDataset getRaw_value() {
		return getDataset(NX_RAW_VALUE);
	}

	@Override
	public Number getRaw_valueScalar() {
		return getNumber(NX_RAW_VALUE);
	}

	public void setRaw_value(IDataset raw_value) {
		setDataset(NX_RAW_VALUE, raw_value);
	}

	public void setRaw_valueScalar(Number raw_value) {
		setField(NX_RAW_VALUE, raw_value);
	}

	@Override
	public IDataset getTarget_value() {
		return getDataset(NX_TARGET_VALUE);
	}

	@Override
	public Number getTarget_valueScalar() {
		return getNumber(NX_TARGET_VALUE);
	}

	public void setTarget_value(IDataset target_value) {
		setDataset(NX_TARGET_VALUE, target_value);
	}

	public void setTarget_valueScalar(Number target_value) {
		setField(NX_TARGET_VALUE, target_value);
	}

	@Override
	public IDataset getTolerance() {
		return getDataset(NX_TOLERANCE);
	}

	@Override
	public Number getToleranceScalar() {
		return getNumber(NX_TOLERANCE);
	}

	public void setTolerance(IDataset tolerance) {
		setDataset(NX_TOLERANCE, tolerance);
	}

	public void setToleranceScalar(Number tolerance) {
		setField(NX_TOLERANCE, tolerance);
	}

	@Override
	public IDataset getSoft_limit_min() {
		return getDataset(NX_SOFT_LIMIT_MIN);
	}

	@Override
	public Number getSoft_limit_minScalar() {
		return getNumber(NX_SOFT_LIMIT_MIN);
	}

	public void setSoft_limit_min(IDataset soft_limit_min) {
		setDataset(NX_SOFT_LIMIT_MIN, soft_limit_min);
	}

	public void setSoft_limit_minScalar(Number soft_limit_min) {
		setField(NX_SOFT_LIMIT_MIN, soft_limit_min);
	}

	@Override
	public IDataset getSoft_limit_max() {
		return getDataset(NX_SOFT_LIMIT_MAX);
	}

	@Override
	public Number getSoft_limit_maxScalar() {
		return getNumber(NX_SOFT_LIMIT_MAX);
	}

	public void setSoft_limit_max(IDataset soft_limit_max) {
		setDataset(NX_SOFT_LIMIT_MAX, soft_limit_max);
	}

	public void setSoft_limit_maxScalar(Number soft_limit_max) {
		setField(NX_SOFT_LIMIT_MAX, soft_limit_max);
	}

	@Override
	public IDataset getVelocity() {
		return getDataset(NX_VELOCITY);
	}

	@Override
	public Number getVelocityScalar() {
		return getNumber(NX_VELOCITY);
	}

	public void setVelocity(IDataset velocity) {
		setDataset(NX_VELOCITY, velocity);
	}

	public void setVelocityScalar(Number velocity) {
		setField(NX_VELOCITY, velocity);
	}

	@Override
	public IDataset getAcceleration_time() {
		return getDataset(NX_ACCELERATION_TIME);
	}

	@Override
	public Number getAcceleration_timeScalar() {
		return getNumber(NX_ACCELERATION_TIME);
	}

	public void setAcceleration_time(IDataset acceleration_time) {
		setDataset(NX_ACCELERATION_TIME, acceleration_time);
	}

	public void setAcceleration_timeScalar(Number acceleration_time) {
		setField(NX_ACCELERATION_TIME, acceleration_time);
	}

	@Override
	public IDataset getController_record() {
		return getDataset(NX_CONTROLLER_RECORD);
	}

	@Override
	public String getController_recordScalar() {
		return getString(NX_CONTROLLER_RECORD);
	}

	public void setController_record(IDataset controller_record) {
		setDataset(NX_CONTROLLER_RECORD, controller_record);
	}

	public void setController_recordScalar(String controller_record) {
		setString(NX_CONTROLLER_RECORD, controller_record);
	}

}
