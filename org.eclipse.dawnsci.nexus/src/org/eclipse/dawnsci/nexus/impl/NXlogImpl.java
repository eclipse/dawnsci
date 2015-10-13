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

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Definition of information that is recorded against time,
 * such as information monitored during the run.
 * It contains
 * the logged values and the times at which they were measured as elapsed time since a starting
 * time recorded in ISO8601 format. This method of storing logged data helps to distinguish
 * instances in which a variable is a dimension scale of the data, in which case it is stored
 * in an ``NXdata`` group, and instances in which it is logged during the
 * run, when it should be stored in an ``NXlog`` group.
 * Note: When using multiple ``NXlog`` groups, it is suggested to place
 * them inside a ``NXcollection`` group. In such cases, when
 * ``NXlog`` is used in another class,
 * ``NXcollection/NXlog`` is then constructed.
 * 
 * @version 1.0
 */
public class NXlogImpl extends NXobjectImpl implements NXlog {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TIME = "time";
	public static final String NX_TIME_ATTRIBUTE_START = "start";
	public static final String NX_VALUE = "value";
	public static final String NX_RAW_VALUE = "raw_value";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_AVERAGE_VALUE = "average_value";
	public static final String NX_AVERAGE_VALUE_ERROR = "average_value_error";
	public static final String NX_MINIMUM_VALUE = "minimum_value";
	public static final String NX_MAXIMUM_VALUE = "maximum_value";
	public static final String NX_DURATION = "duration";

	protected NXlogImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXlogImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXlog.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_LOG;
	}

	@Override
	public IDataset getTime() {
		return getDataset(NX_TIME);
	}

	@Override
	public double getScalarTime() {
		return getDouble(NX_TIME);
	}

	public void setTime(IDataset time) {
		setDataset(NX_TIME, time);
	}

	public void setScalarTime(double time) {
		setField(NX_TIME, time);
	}

	@Override
	public Date getTimeAttributeStart() {
		return getAttrDate(NX_TIME, NX_TIME_ATTRIBUTE_START);
	}

	public void setTimeAttributeStart(Date start) {
		setAttribute(NX_TIME, NX_TIME_ATTRIBUTE_START, start);
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
	public IDataset getAverage_value() {
		return getDataset(NX_AVERAGE_VALUE);
	}

	@Override
	public double getScalarAverage_value() {
		return getDouble(NX_AVERAGE_VALUE);
	}

	public void setAverage_value(IDataset average_value) {
		setDataset(NX_AVERAGE_VALUE, average_value);
	}

	public void setScalarAverage_value(double average_value) {
		setField(NX_AVERAGE_VALUE, average_value);
	}

	@Override
	public IDataset getAverage_value_error() {
		return getDataset(NX_AVERAGE_VALUE_ERROR);
	}

	@Override
	public double getScalarAverage_value_error() {
		return getDouble(NX_AVERAGE_VALUE_ERROR);
	}

	public void setAverage_value_error(IDataset average_value_error) {
		setDataset(NX_AVERAGE_VALUE_ERROR, average_value_error);
	}

	public void setScalarAverage_value_error(double average_value_error) {
		setField(NX_AVERAGE_VALUE_ERROR, average_value_error);
	}

	@Override
	public IDataset getMinimum_value() {
		return getDataset(NX_MINIMUM_VALUE);
	}

	@Override
	public double getScalarMinimum_value() {
		return getDouble(NX_MINIMUM_VALUE);
	}

	public void setMinimum_value(IDataset minimum_value) {
		setDataset(NX_MINIMUM_VALUE, minimum_value);
	}

	public void setScalarMinimum_value(double minimum_value) {
		setField(NX_MINIMUM_VALUE, minimum_value);
	}

	@Override
	public IDataset getMaximum_value() {
		return getDataset(NX_MAXIMUM_VALUE);
	}

	@Override
	public double getScalarMaximum_value() {
		return getDouble(NX_MAXIMUM_VALUE);
	}

	public void setMaximum_value(IDataset maximum_value) {
		setDataset(NX_MAXIMUM_VALUE, maximum_value);
	}

	public void setScalarMaximum_value(double maximum_value) {
		setField(NX_MAXIMUM_VALUE, maximum_value);
	}

	@Override
	public IDataset getDuration() {
		return getDataset(NX_DURATION);
	}

	@Override
	public double getScalarDuration() {
		return getDouble(NX_DURATION);
	}

	public void setDuration(IDataset duration) {
		setDataset(NX_DURATION, duration);
	}

	public void setScalarDuration(double duration) {
		setField(NX_DURATION, duration);
	}

}
