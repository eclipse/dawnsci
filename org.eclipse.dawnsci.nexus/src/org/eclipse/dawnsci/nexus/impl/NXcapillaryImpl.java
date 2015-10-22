/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-13T13:58:10.369+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This is a dictionary of field names to use for describing a capillary as used
 * in X-ray beamlines. Based on information provided by Gerd Wellenreuther.
 * 
 * @version 1.0
 */
public class NXcapillaryImpl extends NXobjectImpl implements NXcapillary {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_MANUFACTURER = "manufacturer";
	public static final String NX_MAXIMUM_INCIDENT_ANGLE = "maximum_incident_angle";
	public static final String NX_ACCEPTING_APERTURE = "accepting_aperture";
	public static final String NX_WORKING_DISTANCE = "working_distance";
	public static final String NX_FOCAL_SIZE = "focal_size";

	protected NXcapillaryImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXcapillaryImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcapillary.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_CAPILLARY;
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setTypeScalar(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getManufacturer() {
		return getDataset(NX_MANUFACTURER);
	}

	@Override
	public String getManufacturerScalar() {
		return getString(NX_MANUFACTURER);
	}

	public void setManufacturer(IDataset manufacturer) {
		setDataset(NX_MANUFACTURER, manufacturer);
	}

	public void setManufacturerScalar(String manufacturer) {
		setString(NX_MANUFACTURER, manufacturer);
	}

	@Override
	public IDataset getMaximum_incident_angle() {
		return getDataset(NX_MAXIMUM_INCIDENT_ANGLE);
	}

	@Override
	public double getMaximum_incident_angleScalar() {
		return getDouble(NX_MAXIMUM_INCIDENT_ANGLE);
	}

	public void setMaximum_incident_angle(IDataset maximum_incident_angle) {
		setDataset(NX_MAXIMUM_INCIDENT_ANGLE, maximum_incident_angle);
	}

	public void setMaximum_incident_angleScalar(double maximum_incident_angle) {
		setField(NX_MAXIMUM_INCIDENT_ANGLE, maximum_incident_angle);
	}

	@Override
	public IDataset getAccepting_aperture() {
		return getDataset(NX_ACCEPTING_APERTURE);
	}

	@Override
	public double getAccepting_apertureScalar() {
		return getDouble(NX_ACCEPTING_APERTURE);
	}

	public void setAccepting_aperture(IDataset accepting_aperture) {
		setDataset(NX_ACCEPTING_APERTURE, accepting_aperture);
	}

	public void setAccepting_apertureScalar(double accepting_aperture) {
		setField(NX_ACCEPTING_APERTURE, accepting_aperture);
	}

	@Override
	public NXdata getGain() {
		return getChild("gain", NXdata.class);
	}

	public void setGain(NXdata gain) {
		putChild("gain", gain);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

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

	public void setWorking_distance(IDataset working_distance) {
		setDataset(NX_WORKING_DISTANCE, working_distance);
	}

	public void setWorking_distanceScalar(double working_distance) {
		setField(NX_WORKING_DISTANCE, working_distance);
	}

	@Override
	public IDataset getFocal_size() {
		return getDataset(NX_FOCAL_SIZE);
	}

	@Override
	public double getFocal_sizeScalar() {
		return getDouble(NX_FOCAL_SIZE);
	}

	public void setFocal_size(IDataset focal_size) {
		setDataset(NX_FOCAL_SIZE, focal_size);
	}

	public void setFocal_sizeScalar(double focal_size) {
		setField(NX_FOCAL_SIZE, focal_size);
	}

}
