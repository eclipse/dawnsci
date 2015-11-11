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
 * definition for an electrostatic separator.
 * 
 * @version 1.0
 */
public class NXseparatorImpl extends NXobjectImpl implements NXseparator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_BEAMLINE_DISTANCE = "beamline_distance";
	public static final String NX_SET_BFIELD_CURRENT = "set_Bfield_current";
	public static final String NX_SET_EFIELD_VOLTAGE = "set_Efield_voltage";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG);

	protected NXseparatorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXseparatorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXseparator.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SEPARATOR;
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
	public IDataset getSet_Bfield_current() {
		return getDataset(NX_SET_BFIELD_CURRENT);
	}

	@Override
	public double getSet_Bfield_currentScalar() {
		return getDouble(NX_SET_BFIELD_CURRENT);
	}

	public void setSet_Bfield_current(IDataset set_Bfield_current) {
		setDataset(NX_SET_BFIELD_CURRENT, set_Bfield_current);
	}

	public void setSet_Bfield_currentScalar(double set_Bfield_current) {
		setField(NX_SET_BFIELD_CURRENT, set_Bfield_current);
	}

	@Override
	public NXlog getRead_Bfield_current() {
		return getChild("read_Bfield_current", NXlog.class);
	}

	public void setRead_Bfield_current(NXlog read_Bfield_current) {
		putChild("read_Bfield_current", read_Bfield_current);
	}

	@Override
	public NXlog getRead_Bfield_voltage() {
		return getChild("read_Bfield_voltage", NXlog.class);
	}

	public void setRead_Bfield_voltage(NXlog read_Bfield_voltage) {
		putChild("read_Bfield_voltage", read_Bfield_voltage);
	}

	@Override
	public IDataset getSet_Efield_voltage() {
		return getDataset(NX_SET_EFIELD_VOLTAGE);
	}

	@Override
	public double getSet_Efield_voltageScalar() {
		return getDouble(NX_SET_EFIELD_VOLTAGE);
	}

	public void setSet_Efield_voltage(IDataset set_Efield_voltage) {
		setDataset(NX_SET_EFIELD_VOLTAGE, set_Efield_voltage);
	}

	public void setSet_Efield_voltageScalar(double set_Efield_voltage) {
		setField(NX_SET_EFIELD_VOLTAGE, set_Efield_voltage);
	}

	@Override
	public NXlog getRead_Efield_current() {
		return getChild("read_Efield_current", NXlog.class);
	}

	public void setRead_Efield_current(NXlog read_Efield_current) {
		putChild("read_Efield_current", read_Efield_current);
	}

	@Override
	public NXlog getRead_Efield_voltage() {
		return getChild("read_Efield_voltage", NXlog.class);
	}

	public void setRead_Efield_voltage(NXlog read_Efield_voltage) {
		putChild("read_Efield_voltage", read_Efield_voltage);
	}

}
