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
 * definition for a spin rotator.
 * 
 * @version 1.0
 */
public class NXspin_rotatorImpl extends NXobjectImpl implements NXspin_rotator {

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

	protected NXspin_rotatorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXspin_rotatorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXspin_rotator.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SPIN_ROTATOR;
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

	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getBeamline_distance() {
		return getDataset(NX_BEAMLINE_DISTANCE);
	}

	@Override
	public double getBeamline_distanceScalar() {
		return getDouble(NX_BEAMLINE_DISTANCE);
	}

	public DataNode setBeamline_distance(IDataset beamline_distance) {
		return setDataset(NX_BEAMLINE_DISTANCE, beamline_distance);
	}

	public DataNode setBeamline_distanceScalar(double beamline_distance) {
		return setField(NX_BEAMLINE_DISTANCE, beamline_distance);
	}

	@Override
	public IDataset getSet_Bfield_current() {
		return getDataset(NX_SET_BFIELD_CURRENT);
	}

	@Override
	public double getSet_Bfield_currentScalar() {
		return getDouble(NX_SET_BFIELD_CURRENT);
	}

	public DataNode setSet_Bfield_current(IDataset set_Bfield_current) {
		return setDataset(NX_SET_BFIELD_CURRENT, set_Bfield_current);
	}

	public DataNode setSet_Bfield_currentScalar(double set_Bfield_current) {
		return setField(NX_SET_BFIELD_CURRENT, set_Bfield_current);
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

	public DataNode setSet_Efield_voltage(IDataset set_Efield_voltage) {
		return setDataset(NX_SET_EFIELD_VOLTAGE, set_Efield_voltage);
	}

	public DataNode setSet_Efield_voltageScalar(double set_Efield_voltage) {
		return setField(NX_SET_EFIELD_VOLTAGE, set_Efield_voltage);
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
