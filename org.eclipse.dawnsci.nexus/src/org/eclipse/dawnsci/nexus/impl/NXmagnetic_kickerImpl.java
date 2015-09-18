/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * definition for a magnetic kicker.
 * 
 * @version 1.0
 */
public class NXmagnetic_kickerImpl extends NXobjectImpl implements NXmagnetic_kicker {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_BEAMLINE_DISTANCE = "beamline_distance";
	public static final String NX_TIMING = "timing";
	public static final String NX_TIMING_ATTRIBUTE_DESCRIPTION = "description";
	public static final String NX_SET_CURRENT = "set_current";
	public static final String NX_SET_VOLTAGE = "set_voltage";

	protected NXmagnetic_kickerImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmagnetic_kicker.class;
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getBeamline_distance() {
		return getDataset(NX_BEAMLINE_DISTANCE);
	}

	public void setBeamline_distance(IDataset beamline_distance) {
		setDataset(NX_BEAMLINE_DISTANCE, beamline_distance);
	}

	@Override
	public IDataset getTiming() {
		return getDataset(NX_TIMING);
	}

	public void setTiming(IDataset timing) {
		setDataset(NX_TIMING, timing);
	}

	@Override
	public String getTimingAttributeDescription() {
		return getAttrString(NX_TIMING, NX_TIMING_ATTRIBUTE_DESCRIPTION);
	}

	public void setTimingAttributeDescription(String description) {
		setAttribute(NX_TIMING, NX_TIMING_ATTRIBUTE_DESCRIPTION, description);
	}

	@Override
	public IDataset getSet_current() {
		return getDataset(NX_SET_CURRENT);
	}

	public void setSet_current(IDataset set_current) {
		setDataset(NX_SET_CURRENT, set_current);
	}

	@Override
	public NXlog getRead_current() {
		return getChild("read_current", NXlog.class);
	}

	public void setRead_current(NXlog read_current) {
		putChild("read_current", read_current);
	}

	@Override
	public IDataset getSet_voltage() {
		return getDataset(NX_SET_VOLTAGE);
	}

	public void setSet_voltage(IDataset set_voltage) {
		setDataset(NX_SET_VOLTAGE, set_voltage);
	}

	@Override
	public NXlog getRead_voltage() {
		return getChild("read_voltage", NXlog.class);
	}

	public void setRead_voltage(NXlog read_voltage) {
		putChild("read_voltage", read_voltage);
	}

}
