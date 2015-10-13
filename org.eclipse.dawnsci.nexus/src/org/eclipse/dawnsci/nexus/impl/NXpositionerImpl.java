/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-12T11:55:04.232+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

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
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_POSITIONER;
	}

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getScalarName() {
		return getString(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	public void setScalarName(String name) {
		setString(NX_NAME, name);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getScalarDescription() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setScalarDescription(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getValue() {
		return getDataset(NX_VALUE);
	}

	@Override
	public Number getScalarValue() {
		return getNumber(NX_VALUE);
	}

	public void setValue(IDataset value) {
		setDataset(NX_VALUE, value);
	}

	public void setScalarValue(Number value) {
		setField(NX_VALUE, value);
	}

	@Override
	public IDataset getRaw_value() {
		return getDataset(NX_RAW_VALUE);
	}

	@Override
	public Number getScalarRaw_value() {
		return getNumber(NX_RAW_VALUE);
	}

	public void setRaw_value(IDataset raw_value) {
		setDataset(NX_RAW_VALUE, raw_value);
	}

	public void setScalarRaw_value(Number raw_value) {
		setField(NX_RAW_VALUE, raw_value);
	}

	@Override
	public IDataset getTarget_value() {
		return getDataset(NX_TARGET_VALUE);
	}

	@Override
	public Number getScalarTarget_value() {
		return getNumber(NX_TARGET_VALUE);
	}

	public void setTarget_value(IDataset target_value) {
		setDataset(NX_TARGET_VALUE, target_value);
	}

	public void setScalarTarget_value(Number target_value) {
		setField(NX_TARGET_VALUE, target_value);
	}

	@Override
	public IDataset getTolerance() {
		return getDataset(NX_TOLERANCE);
	}

	@Override
	public Number getScalarTolerance() {
		return getNumber(NX_TOLERANCE);
	}

	public void setTolerance(IDataset tolerance) {
		setDataset(NX_TOLERANCE, tolerance);
	}

	public void setScalarTolerance(Number tolerance) {
		setField(NX_TOLERANCE, tolerance);
	}

	@Override
	public IDataset getSoft_limit_min() {
		return getDataset(NX_SOFT_LIMIT_MIN);
	}

	@Override
	public Number getScalarSoft_limit_min() {
		return getNumber(NX_SOFT_LIMIT_MIN);
	}

	public void setSoft_limit_min(IDataset soft_limit_min) {
		setDataset(NX_SOFT_LIMIT_MIN, soft_limit_min);
	}

	public void setScalarSoft_limit_min(Number soft_limit_min) {
		setField(NX_SOFT_LIMIT_MIN, soft_limit_min);
	}

	@Override
	public IDataset getSoft_limit_max() {
		return getDataset(NX_SOFT_LIMIT_MAX);
	}

	@Override
	public Number getScalarSoft_limit_max() {
		return getNumber(NX_SOFT_LIMIT_MAX);
	}

	public void setSoft_limit_max(IDataset soft_limit_max) {
		setDataset(NX_SOFT_LIMIT_MAX, soft_limit_max);
	}

	public void setScalarSoft_limit_max(Number soft_limit_max) {
		setField(NX_SOFT_LIMIT_MAX, soft_limit_max);
	}

	@Override
	public IDataset getVelocity() {
		return getDataset(NX_VELOCITY);
	}

	@Override
	public Number getScalarVelocity() {
		return getNumber(NX_VELOCITY);
	}

	public void setVelocity(IDataset velocity) {
		setDataset(NX_VELOCITY, velocity);
	}

	public void setScalarVelocity(Number velocity) {
		setField(NX_VELOCITY, velocity);
	}

	@Override
	public IDataset getAcceleration_time() {
		return getDataset(NX_ACCELERATION_TIME);
	}

	@Override
	public Number getScalarAcceleration_time() {
		return getNumber(NX_ACCELERATION_TIME);
	}

	public void setAcceleration_time(IDataset acceleration_time) {
		setDataset(NX_ACCELERATION_TIME, acceleration_time);
	}

	public void setScalarAcceleration_time(Number acceleration_time) {
		setField(NX_ACCELERATION_TIME, acceleration_time);
	}

	@Override
	public IDataset getController_record() {
		return getDataset(NX_CONTROLLER_RECORD);
	}

	@Override
	public String getScalarController_record() {
		return getString(NX_CONTROLLER_RECORD);
	}

	public void setController_record(IDataset controller_record) {
		setDataset(NX_CONTROLLER_RECORD, controller_record);
	}

	public void setScalarController_record(String controller_record) {
		setString(NX_CONTROLLER_RECORD, controller_record);
	}

}
