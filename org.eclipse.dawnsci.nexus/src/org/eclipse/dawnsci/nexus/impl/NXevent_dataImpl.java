/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Time-of-flight events
 * 
 * @version 1.0
 */
public class NXevent_dataImpl extends NXobjectImpl implements NXevent_data {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TIME_OF_FLIGHT = "time_of_flight";
	public static final String NX_PIXEL_NUMBER = "pixel_number";
	public static final String NX_PULSE_TIME = "pulse_time";
	public static final String NX_PULSE_TIME_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_EVENTS_PER_PULSE = "events_per_pulse";
	public static final String NX_PULSE_HEIGHT = "pulse_height";

	protected NXevent_dataImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXevent_data.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_EVENT_DATA;
	}

	@Override
	public IDataset getTime_of_flight() {
		return getDataset(NX_TIME_OF_FLIGHT);
	}

	public void setTime_of_flight(IDataset time_of_flight) {
		setDataset(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public IDataset getPixel_number() {
		return getDataset(NX_PIXEL_NUMBER);
	}

	public void setPixel_number(IDataset pixel_number) {
		setDataset(NX_PIXEL_NUMBER, pixel_number);
	}

	@Override
	public IDataset getPulse_time() {
		return getDataset(NX_PULSE_TIME);
	}

	public void setPulse_time(IDataset pulse_time) {
		setDataset(NX_PULSE_TIME, pulse_time);
	}

	@Override
	public Date getPulse_timeAttributeOffset() {
		return getAttrDate(NX_PULSE_TIME, NX_PULSE_TIME_ATTRIBUTE_OFFSET);
	}

	public void setPulse_timeAttributeOffset(Date offset) {
		setAttribute(NX_PULSE_TIME, NX_PULSE_TIME_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public IDataset getEvents_per_pulse() {
		return getDataset(NX_EVENTS_PER_PULSE);
	}

	public void setEvents_per_pulse(IDataset events_per_pulse) {
		setDataset(NX_EVENTS_PER_PULSE, events_per_pulse);
	}

	@Override
	public IDataset getPulse_height() {
		return getDataset(NX_PULSE_HEIGHT);
	}

	public void setPulse_height(IDataset pulse_height) {
		setDataset(NX_PULSE_HEIGHT, pulse_height);
	}

}
