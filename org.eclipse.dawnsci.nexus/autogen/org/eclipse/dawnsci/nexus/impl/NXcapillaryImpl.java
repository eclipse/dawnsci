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

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * A capillary lens to focus the X-ray beam.
 * Based on information provided by Gerd Wellenreuther (DESY).
 * 
 * @version 1.0
 */
public class NXcapillaryImpl extends NXobjectImpl implements NXcapillary {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_DATA);

	public NXcapillaryImpl() {
		super();
	}

	public NXcapillaryImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcapillary.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_CAPILLARY;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	public IDataset getManufacturer() {
		return getDataset(NX_MANUFACTURER);
	}

	@Override
	public String getManufacturerScalar() {
		return getString(NX_MANUFACTURER);
	}

	@Override
	public DataNode setManufacturer(IDataset manufacturer) {
		return setDataset(NX_MANUFACTURER, manufacturer);
	}

	@Override
	public DataNode setManufacturerScalar(String manufacturer) {
		return setString(NX_MANUFACTURER, manufacturer);
	}

	@Override
	public IDataset getMaximum_incident_angle() {
		return getDataset(NX_MAXIMUM_INCIDENT_ANGLE);
	}

	@Override
	public double getMaximum_incident_angleScalar() {
		return getDouble(NX_MAXIMUM_INCIDENT_ANGLE);
	}

	@Override
	public DataNode setMaximum_incident_angle(IDataset maximum_incident_angle) {
		return setDataset(NX_MAXIMUM_INCIDENT_ANGLE, maximum_incident_angle);
	}

	@Override
	public DataNode setMaximum_incident_angleScalar(double maximum_incident_angle) {
		return setField(NX_MAXIMUM_INCIDENT_ANGLE, maximum_incident_angle);
	}

	@Override
	public IDataset getAccepting_aperture() {
		return getDataset(NX_ACCEPTING_APERTURE);
	}

	@Override
	public double getAccepting_apertureScalar() {
		return getDouble(NX_ACCEPTING_APERTURE);
	}

	@Override
	public DataNode setAccepting_aperture(IDataset accepting_aperture) {
		return setDataset(NX_ACCEPTING_APERTURE, accepting_aperture);
	}

	@Override
	public DataNode setAccepting_apertureScalar(double accepting_aperture) {
		return setField(NX_ACCEPTING_APERTURE, accepting_aperture);
	}

	@Override
	public NXdata getGain() {
		return getChild("gain", NXdata.class);
	}

	@Override
	public void setGain(NXdata gain) {
		putChild("gain", gain);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	@Override
	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
	}

	@Override
	public IDataset getWorking_distance() {
		return getDataset(NX_WORKING_DISTANCE);
	}

	@Override
	public double getWorking_distanceScalar() {
		return getDouble(NX_WORKING_DISTANCE);
	}

	@Override
	public DataNode setWorking_distance(IDataset working_distance) {
		return setDataset(NX_WORKING_DISTANCE, working_distance);
	}

	@Override
	public DataNode setWorking_distanceScalar(double working_distance) {
		return setField(NX_WORKING_DISTANCE, working_distance);
	}

	@Override
	public IDataset getFocal_size() {
		return getDataset(NX_FOCAL_SIZE);
	}

	@Override
	public double getFocal_sizeScalar() {
		return getDouble(NX_FOCAL_SIZE);
	}

	@Override
	public DataNode setFocal_size(IDataset focal_size) {
		return setDataset(NX_FOCAL_SIZE, focal_size);
	}

	@Override
	public DataNode setFocal_sizeScalar(double focal_size) {
		return setField(NX_FOCAL_SIZE, focal_size);
	}

}
