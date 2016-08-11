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

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * A monitor of incident beam data.
 * It is similar to the :ref:`NXdata` groups containing
 * monitor data and its associated dimension scale, e.g. time_of_flight or
 * wavelength in pulsed neutron instruments. However, it may also include
 * integrals, or scalar monitor counts, which are often used in both in both
 * pulsed and steady-state instrumentation.
 * 
 * @version 1.0
 */
public class NXmonitorImpl extends NXobjectImpl implements NXmonitor {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_GEOMETRY);

	public NXmonitorImpl() {
		super();
	}

	public NXmonitorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmonitor.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_MONITOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getMode() {
		return getDataset(NX_MODE);
	}

	@Override
	public String getModeScalar() {
		return getString(NX_MODE);
	}

	@Override
	public DataNode setMode(IDataset mode) {
		return setDataset(NX_MODE, mode);
	}

	@Override
	public DataNode setModeScalar(String mode) {
		return setString(NX_MODE, mode);
	}

	@Override
	public IDataset getStart_time() {
		return getDataset(NX_START_TIME);
	}

	@Override
	public Date getStart_timeScalar() {
		return getDate(NX_START_TIME);
	}

	@Override
	public DataNode setStart_time(IDataset start_time) {
		return setDataset(NX_START_TIME, start_time);
	}

	@Override
	public DataNode setStart_timeScalar(Date start_time) {
		return setDate(NX_START_TIME, start_time);
	}

	@Override
	public IDataset getEnd_time() {
		return getDataset(NX_END_TIME);
	}

	@Override
	public Date getEnd_timeScalar() {
		return getDate(NX_END_TIME);
	}

	@Override
	public DataNode setEnd_time(IDataset end_time) {
		return setDataset(NX_END_TIME, end_time);
	}

	@Override
	public DataNode setEnd_timeScalar(Date end_time) {
		return setDate(NX_END_TIME, end_time);
	}

	@Override
	public IDataset getPreset() {
		return getDataset(NX_PRESET);
	}

	@Override
	public Number getPresetScalar() {
		return getNumber(NX_PRESET);
	}

	@Override
	public DataNode setPreset(IDataset preset) {
		return setDataset(NX_PRESET, preset);
	}

	@Override
	public DataNode setPresetScalar(Number preset) {
		return setField(NX_PRESET, preset);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	@Override
	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	@Override
	public DataNode setDistanceScalar(double distance) {
		return setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getRange() {
		return getDataset(NX_RANGE);
	}

	@Override
	public double getRangeScalar() {
		return getDouble(NX_RANGE);
	}

	@Override
	public DataNode setRange(IDataset range) {
		return setDataset(NX_RANGE, range);
	}

	@Override
	public DataNode setRangeScalar(double range) {
		return setField(NX_RANGE, range);
	}

	@Override
	public IDataset getNominal() {
		return getDataset(NX_NOMINAL);
	}

	@Override
	public Number getNominalScalar() {
		return getNumber(NX_NOMINAL);
	}

	@Override
	public DataNode setNominal(IDataset nominal) {
		return setDataset(NX_NOMINAL, nominal);
	}

	@Override
	public DataNode setNominalScalar(Number nominal) {
		return setField(NX_NOMINAL, nominal);
	}

	@Override
	public IDataset getIntegral() {
		return getDataset(NX_INTEGRAL);
	}

	@Override
	public Number getIntegralScalar() {
		return getNumber(NX_INTEGRAL);
	}

	@Override
	public DataNode setIntegral(IDataset integral) {
		return setDataset(NX_INTEGRAL, integral);
	}

	@Override
	public DataNode setIntegralScalar(Number integral) {
		return setField(NX_INTEGRAL, integral);
	}

	@Override
	public NXlog getIntegral_log() {
		return getChild("integral_log", NXlog.class);
	}

	@Override
	public void setIntegral_log(NXlog integral_log) {
		putChild("integral_log", integral_log);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	@Override
	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	@Override
	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
	}

	@Override
	public IDataset getTime_of_flight() {
		return getDataset(NX_TIME_OF_FLIGHT);
	}

	@Override
	public double getTime_of_flightScalar() {
		return getDouble(NX_TIME_OF_FLIGHT);
	}

	@Override
	public DataNode setTime_of_flight(IDataset time_of_flight) {
		return setDataset(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public DataNode setTime_of_flightScalar(double time_of_flight) {
		return setField(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public IDataset getEfficiency() {
		return getDataset(NX_EFFICIENCY);
	}

	@Override
	public Number getEfficiencyScalar() {
		return getNumber(NX_EFFICIENCY);
	}

	@Override
	public DataNode setEfficiency(IDataset efficiency) {
		return setDataset(NX_EFFICIENCY, efficiency);
	}

	@Override
	public DataNode setEfficiencyScalar(Number efficiency) {
		return setField(NX_EFFICIENCY, efficiency);
	}

	@Override
	public IDataset getData() {
		return getDataset(NX_DATA);
	}

	@Override
	public Number getDataScalar() {
		return getNumber(NX_DATA);
	}

	@Override
	public DataNode setData(IDataset data) {
		return setDataset(NX_DATA, data);
	}

	@Override
	public DataNode setDataScalar(Number data) {
		return setField(NX_DATA, data);
	}

	@Override
	public IDataset getSampled_fraction() {
		return getDataset(NX_SAMPLED_FRACTION);
	}

	@Override
	public double getSampled_fractionScalar() {
		return getDouble(NX_SAMPLED_FRACTION);
	}

	@Override
	public DataNode setSampled_fraction(IDataset sampled_fraction) {
		return setDataset(NX_SAMPLED_FRACTION, sampled_fraction);
	}

	@Override
	public DataNode setSampled_fractionScalar(double sampled_fraction) {
		return setField(NX_SAMPLED_FRACTION, sampled_fraction);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	@Override
	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}
	
	@Override
	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

	@Override
	public IDataset getCount_time() {
		return getDataset(NX_COUNT_TIME);
	}

	@Override
	public double getCount_timeScalar() {
		return getDouble(NX_COUNT_TIME);
	}

	@Override
	public DataNode setCount_time(IDataset count_time) {
		return setDataset(NX_COUNT_TIME, count_time);
	}

	@Override
	public DataNode setCount_timeScalar(double count_time) {
		return setField(NX_COUNT_TIME, count_time);
	}

}
