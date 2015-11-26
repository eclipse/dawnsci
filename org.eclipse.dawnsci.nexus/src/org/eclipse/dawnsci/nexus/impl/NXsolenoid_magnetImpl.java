/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-17T13:07:37.011Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * definition for a solenoid magnet.
 * 
 * @version 1.0
 */
public class NXsolenoid_magnetImpl extends NXobjectImpl implements NXsolenoid_magnet {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_BEAMLINE_DISTANCE = "beamline_distance";
	public static final String NX_SET_CURRENT = "set_current";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG);

	protected NXsolenoid_magnetImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXsolenoid_magnetImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsolenoid_magnet.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SOLENOID_MAGNET;
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
	public IDataset getSet_current() {
		return getDataset(NX_SET_CURRENT);
	}

	@Override
	public double getSet_currentScalar() {
		return getDouble(NX_SET_CURRENT);
	}

	public DataNode setSet_current(IDataset set_current) {
		return setDataset(NX_SET_CURRENT, set_current);
	}

	public DataNode setSet_currentScalar(double set_current) {
		return setField(NX_SET_CURRENT, set_current);
	}

	@Override
	public NXlog getRead_current() {
		return getChild("read_current", NXlog.class);
	}

	public void setRead_current(NXlog read_current) {
		putChild("read_current", read_current);
	}

	@Override
	public NXlog getRead_voltage() {
		return getChild("read_voltage", NXlog.class);
	}

	public void setRead_voltage(NXlog read_voltage) {
		putChild("read_voltage", read_voltage);
	}

}
