/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;

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

	public DataNode setName(IDataset name) {
		return setDataset(NX_NAME, name);
	}

	public DataNode setNameScalar(String name) {
		return setString(NX_NAME, name);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getValue() {
		return getDataset(NX_VALUE);
	}

	@Override
	public Number getValueScalar() {
		return getNumber(NX_VALUE);
	}

	public DataNode setValue(IDataset value) {
		return setDataset(NX_VALUE, value);
	}

	public DataNode setValueScalar(Number value) {
		return setField(NX_VALUE, value);
	}

	@Override
	public IDataset getRaw_value() {
		return getDataset(NX_RAW_VALUE);
	}

	@Override
	public Number getRaw_valueScalar() {
		return getNumber(NX_RAW_VALUE);
	}

	public DataNode setRaw_value(IDataset raw_value) {
		return setDataset(NX_RAW_VALUE, raw_value);
	}

	public DataNode setRaw_valueScalar(Number raw_value) {
		return setField(NX_RAW_VALUE, raw_value);
	}

	@Override
	public IDataset getTarget_value() {
		return getDataset(NX_TARGET_VALUE);
	}

	@Override
	public Number getTarget_valueScalar() {
		return getNumber(NX_TARGET_VALUE);
	}

	public DataNode setTarget_value(IDataset target_value) {
		return setDataset(NX_TARGET_VALUE, target_value);
	}

	public DataNode setTarget_valueScalar(Number target_value) {
		return setField(NX_TARGET_VALUE, target_value);
	}

	@Override
	public IDataset getTolerance() {
		return getDataset(NX_TOLERANCE);
	}

	@Override
	public Number getToleranceScalar() {
		return getNumber(NX_TOLERANCE);
	}

	public DataNode setTolerance(IDataset tolerance) {
		return setDataset(NX_TOLERANCE, tolerance);
	}

	public DataNode setToleranceScalar(Number tolerance) {
		return setField(NX_TOLERANCE, tolerance);
	}

	@Override
	public IDataset getSoft_limit_min() {
		return getDataset(NX_SOFT_LIMIT_MIN);
	}

	@Override
	public Number getSoft_limit_minScalar() {
		return getNumber(NX_SOFT_LIMIT_MIN);
	}

	public DataNode setSoft_limit_min(IDataset soft_limit_min) {
		return setDataset(NX_SOFT_LIMIT_MIN, soft_limit_min);
	}

	public DataNode setSoft_limit_minScalar(Number soft_limit_min) {
		return setField(NX_SOFT_LIMIT_MIN, soft_limit_min);
	}

	@Override
	public IDataset getSoft_limit_max() {
		return getDataset(NX_SOFT_LIMIT_MAX);
	}

	@Override
	public Number getSoft_limit_maxScalar() {
		return getNumber(NX_SOFT_LIMIT_MAX);
	}

	public DataNode setSoft_limit_max(IDataset soft_limit_max) {
		return setDataset(NX_SOFT_LIMIT_MAX, soft_limit_max);
	}

	public DataNode setSoft_limit_maxScalar(Number soft_limit_max) {
		return setField(NX_SOFT_LIMIT_MAX, soft_limit_max);
	}

	@Override
	public IDataset getVelocity() {
		return getDataset(NX_VELOCITY);
	}

	@Override
	public Number getVelocityScalar() {
		return getNumber(NX_VELOCITY);
	}

	public DataNode setVelocity(IDataset velocity) {
		return setDataset(NX_VELOCITY, velocity);
	}

	public DataNode setVelocityScalar(Number velocity) {
		return setField(NX_VELOCITY, velocity);
	}

	@Override
	public IDataset getAcceleration_time() {
		return getDataset(NX_ACCELERATION_TIME);
	}

	@Override
	public Number getAcceleration_timeScalar() {
		return getNumber(NX_ACCELERATION_TIME);
	}

	public DataNode setAcceleration_time(IDataset acceleration_time) {
		return setDataset(NX_ACCELERATION_TIME, acceleration_time);
	}

	public DataNode setAcceleration_timeScalar(Number acceleration_time) {
		return setField(NX_ACCELERATION_TIME, acceleration_time);
	}

	@Override
	public IDataset getController_record() {
		return getDataset(NX_CONTROLLER_RECORD);
	}

	@Override
	public String getController_recordScalar() {
		return getString(NX_CONTROLLER_RECORD);
	}

	public DataNode setController_record(IDataset controller_record) {
		return setDataset(NX_CONTROLLER_RECORD, controller_record);
	}

	public DataNode setController_recordScalar(String controller_record) {
		return setString(NX_CONTROLLER_RECORD, controller_record);
	}

}
