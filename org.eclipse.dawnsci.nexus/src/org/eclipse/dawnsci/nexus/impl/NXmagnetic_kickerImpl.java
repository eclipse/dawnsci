/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG);

	protected NXmagnetic_kickerImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXmagnetic_kickerImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmagnetic_kicker.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_MAGNETIC_KICKER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	public IDataset getBeamline_distance() {
		return getDataset(NX_BEAMLINE_DISTANCE);
	}

	@Override
	public double getBeamline_distanceScalar() {
		return getDouble(NX_BEAMLINE_DISTANCE);
	}

	public void setBeamline_distance(IDataset beamline_distance) {
		setDataset(NX_BEAMLINE_DISTANCE, beamline_distance);
	}

	public void setBeamline_distanceScalar(double beamline_distance) {
		setField(NX_BEAMLINE_DISTANCE, beamline_distance);
	}

	@Override
	public IDataset getTiming() {
		return getDataset(NX_TIMING);
	}

	@Override
	public double getTimingScalar() {
		return getDouble(NX_TIMING);
	}

	public void setTiming(IDataset timing) {
		setDataset(NX_TIMING, timing);
	}

	public void setTimingScalar(double timing) {
		setField(NX_TIMING, timing);
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

	@Override
	public double getSet_currentScalar() {
		return getDouble(NX_SET_CURRENT);
	}

	public void setSet_current(IDataset set_current) {
		setDataset(NX_SET_CURRENT, set_current);
	}

	public void setSet_currentScalar(double set_current) {
		setField(NX_SET_CURRENT, set_current);
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

	@Override
	public double getSet_voltageScalar() {
		return getDouble(NX_SET_VOLTAGE);
	}

	public void setSet_voltage(IDataset set_voltage) {
		setDataset(NX_SET_VOLTAGE, set_voltage);
	}

	public void setSet_voltageScalar(double set_voltage) {
		setField(NX_SET_VOLTAGE, set_voltage);
	}

	@Override
	public NXlog getRead_voltage() {
		return getChild("read_voltage", NXlog.class);
	}

	public void setRead_voltage(NXlog read_voltage) {
		putChild("read_voltage", read_voltage);
	}

}
