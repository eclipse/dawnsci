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
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of monitor data. It is similar to the NXdata groups containing
 * monitor data and its associated dimension scale, e.g. time_of_flight or
 * wavelength in pulsed neutron instruments. However, it may also include
 * integrals, or scalar monitor counts, which are often used in both in both
 * pulsed and steady-state instrumentation.
 * 
 * @version 1.0
 */
public class NXmonitorImpl extends NXobjectImpl implements NXmonitor {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_MODE = "mode";
	public static final String NX_START_TIME = "start_time";
	public static final String NX_END_TIME = "end_time";
	public static final String NX_PRESET = "preset";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_RANGE = "range";
	public static final String NX_NOMINAL = "nominal";
	public static final String NX_INTEGRAL = "integral";
	public static final String NX_TYPE = "type";
	public static final String NX_TIME_OF_FLIGHT = "time_of_flight";
	public static final String NX_EFFICIENCY = "efficiency";
	public static final String NX_DATA = "data";
	public static final String NX_DATA_ATTRIBUTE_SIGNAL = "signal";
	public static final String NX_DATA_ATTRIBUTE_AXES = "axes";
	public static final String NX_SAMPLED_FRACTION = "sampled_fraction";
	public static final String NX_COUNT_TIME = "count_time";

	protected NXmonitorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXmonitorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmonitor.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_MONITOR;
	}

	@Override
	public IDataset getMode() {
		return getDataset(NX_MODE);
	}

	@Override
	public String getScalarMode() {
		return getString(NX_MODE);
	}

	public void setMode(IDataset mode) {
		setDataset(NX_MODE, mode);
	}

	public void setScalarMode(String mode) {
		setString(NX_MODE, mode);
	}

	@Override
	public IDataset getStart_time() {
		return getDataset(NX_START_TIME);
	}

	@Override
	public Date getScalarStart_time() {
		return getDate(NX_START_TIME);
	}

	public void setStart_time(IDataset start_time) {
		setDataset(NX_START_TIME, start_time);
	}

	public void setScalarStart_time(Date start_time) {
		setDate(NX_START_TIME, start_time);
	}

	@Override
	public IDataset getEnd_time() {
		return getDataset(NX_END_TIME);
	}

	@Override
	public Date getScalarEnd_time() {
		return getDate(NX_END_TIME);
	}

	public void setEnd_time(IDataset end_time) {
		setDataset(NX_END_TIME, end_time);
	}

	public void setScalarEnd_time(Date end_time) {
		setDate(NX_END_TIME, end_time);
	}

	@Override
	public IDataset getPreset() {
		return getDataset(NX_PRESET);
	}

	@Override
	public Number getScalarPreset() {
		return getNumber(NX_PRESET);
	}

	public void setPreset(IDataset preset) {
		setDataset(NX_PRESET, preset);
	}

	public void setScalarPreset(Number preset) {
		setField(NX_PRESET, preset);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getScalarDistance() {
		return getDouble(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	public void setScalarDistance(double distance) {
		setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getRange() {
		return getDataset(NX_RANGE);
	}

	@Override
	public double getScalarRange() {
		return getDouble(NX_RANGE);
	}

	public void setRange(IDataset range) {
		setDataset(NX_RANGE, range);
	}

	public void setScalarRange(double range) {
		setField(NX_RANGE, range);
	}

	@Override
	public IDataset getNominal() {
		return getDataset(NX_NOMINAL);
	}

	@Override
	public Number getScalarNominal() {
		return getNumber(NX_NOMINAL);
	}

	public void setNominal(IDataset nominal) {
		setDataset(NX_NOMINAL, nominal);
	}

	public void setScalarNominal(Number nominal) {
		setField(NX_NOMINAL, nominal);
	}

	@Override
	public IDataset getIntegral() {
		return getDataset(NX_INTEGRAL);
	}

	@Override
	public Number getScalarIntegral() {
		return getNumber(NX_INTEGRAL);
	}

	public void setIntegral(IDataset integral) {
		setDataset(NX_INTEGRAL, integral);
	}

	public void setScalarIntegral(Number integral) {
		setField(NX_INTEGRAL, integral);
	}

	@Override
	public NXlog getIntegral_log() {
		return getChild("integral_log", NXlog.class);
	}

	public void setIntegral_log(NXlog integral_log) {
		putChild("integral_log", integral_log);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getScalarType() {
		return getString(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setScalarType(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getTime_of_flight() {
		return getDataset(NX_TIME_OF_FLIGHT);
	}

	@Override
	public double getScalarTime_of_flight() {
		return getDouble(NX_TIME_OF_FLIGHT);
	}

	public void setTime_of_flight(IDataset time_of_flight) {
		setDataset(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	public void setScalarTime_of_flight(double time_of_flight) {
		setField(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public IDataset getEfficiency() {
		return getDataset(NX_EFFICIENCY);
	}

	@Override
	public Number getScalarEfficiency() {
		return getNumber(NX_EFFICIENCY);
	}

	public void setEfficiency(IDataset efficiency) {
		setDataset(NX_EFFICIENCY, efficiency);
	}

	public void setScalarEfficiency(Number efficiency) {
		setField(NX_EFFICIENCY, efficiency);
	}

	@Override
	public IDataset getData() {
		return getDataset(NX_DATA);
	}

	@Override
	public Number getScalarData() {
		return getNumber(NX_DATA);
	}

	public void setData(IDataset data) {
		setDataset(NX_DATA, data);
	}

	public void setScalarData(Number data) {
		setField(NX_DATA, data);
	}

	@Override
	public long getDataAttributeSignal() {
		return getAttrLong(NX_DATA, NX_DATA_ATTRIBUTE_SIGNAL);
	}

	public void setDataAttributeSignal(long signal) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_SIGNAL, signal);
	}

	@Override
	public String getDataAttributeAxes() {
		return getAttrString(NX_DATA, NX_DATA_ATTRIBUTE_AXES);
	}

	public void setDataAttributeAxes(String axes) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_AXES, axes);
	}

	@Override
	public IDataset getSampled_fraction() {
		return getDataset(NX_SAMPLED_FRACTION);
	}

	@Override
	public double getScalarSampled_fraction() {
		return getDouble(NX_SAMPLED_FRACTION);
	}

	public void setSampled_fraction(IDataset sampled_fraction) {
		setDataset(NX_SAMPLED_FRACTION, sampled_fraction);
	}

	public void setScalarSampled_fraction(double sampled_fraction) {
		setField(NX_SAMPLED_FRACTION, sampled_fraction);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}

	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

	@Override
	public IDataset getCount_time() {
		return getDataset(NX_COUNT_TIME);
	}

	@Override
	public double getScalarCount_time() {
		return getDouble(NX_COUNT_TIME);
	}

	public void setCount_time(IDataset count_time) {
		setDataset(NX_COUNT_TIME, count_time);
	}

	public void setScalarCount_time(double count_time) {
		setField(NX_COUNT_TIME, count_time);
	}

}
