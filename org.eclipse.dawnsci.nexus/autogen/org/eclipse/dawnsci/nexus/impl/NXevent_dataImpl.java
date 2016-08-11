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

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * Time-of-flight events
 * 
 * @version 1.0
 */
public class NXevent_dataImpl extends NXobjectImpl implements NXevent_data {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXevent_dataImpl() {
		super();
	}

	public NXevent_dataImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXevent_data.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_EVENT_DATA;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getTime_of_flight() {
		return getDataset(NX_TIME_OF_FLIGHT);
	}

	@Override
	public long getTime_of_flightScalar() {
		return getLong(NX_TIME_OF_FLIGHT);
	}

	@Override
	public DataNode setTime_of_flight(IDataset time_of_flight) {
		return setDataset(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public DataNode setTime_of_flightScalar(long time_of_flight) {
		return setField(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public IDataset getPixel_number() {
		return getDataset(NX_PIXEL_NUMBER);
	}

	@Override
	public long getPixel_numberScalar() {
		return getLong(NX_PIXEL_NUMBER);
	}

	@Override
	public DataNode setPixel_number(IDataset pixel_number) {
		return setDataset(NX_PIXEL_NUMBER, pixel_number);
	}

	@Override
	public DataNode setPixel_numberScalar(long pixel_number) {
		return setField(NX_PIXEL_NUMBER, pixel_number);
	}

	@Override
	public IDataset getPulse_time() {
		return getDataset(NX_PULSE_TIME);
	}

	@Override
	public long getPulse_timeScalar() {
		return getLong(NX_PULSE_TIME);
	}

	@Override
	public DataNode setPulse_time(IDataset pulse_time) {
		return setDataset(NX_PULSE_TIME, pulse_time);
	}

	@Override
	public DataNode setPulse_timeScalar(long pulse_time) {
		return setField(NX_PULSE_TIME, pulse_time);
	}

	@Override
	public Date getPulse_timeAttributeOffset() {
		return getAttrDate(NX_PULSE_TIME, NX_PULSE_TIME_ATTRIBUTE_OFFSET);
	}

	@Override
	public void setPulse_timeAttributeOffset(Date offset) {
		setAttribute(NX_PULSE_TIME, NX_PULSE_TIME_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public IDataset getEvents_per_pulse() {
		return getDataset(NX_EVENTS_PER_PULSE);
	}

	@Override
	public long getEvents_per_pulseScalar() {
		return getLong(NX_EVENTS_PER_PULSE);
	}

	@Override
	public DataNode setEvents_per_pulse(IDataset events_per_pulse) {
		return setDataset(NX_EVENTS_PER_PULSE, events_per_pulse);
	}

	@Override
	public DataNode setEvents_per_pulseScalar(long events_per_pulse) {
		return setField(NX_EVENTS_PER_PULSE, events_per_pulse);
	}

	@Override
	public IDataset getPulse_height() {
		return getDataset(NX_PULSE_HEIGHT);
	}

	@Override
	public double getPulse_heightScalar() {
		return getDouble(NX_PULSE_HEIGHT);
	}

	@Override
	public DataNode setPulse_height(IDataset pulse_height) {
		return setDataset(NX_PULSE_HEIGHT, pulse_height);
	}

	@Override
	public DataNode setPulse_heightScalar(double pulse_height) {
		return setField(NX_PULSE_HEIGHT, pulse_height);
	}

}
