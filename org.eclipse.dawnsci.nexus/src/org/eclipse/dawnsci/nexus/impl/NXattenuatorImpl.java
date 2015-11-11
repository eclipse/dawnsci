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

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Description of a device that reduces the intensity of a beam by attenuation.
 * If uncertain whether to use ``NXfilter`` (band-pass filter)
 * or ``NXattenuator`` (reduces beam intensity), then choose
 * ``NXattenuator``.
 * 
 * @version 1.0
 */
public class NXattenuatorImpl extends NXobjectImpl implements NXattenuator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DISTANCE = "distance";
	public static final String NX_TYPE = "type";
	public static final String NX_THICKNESS = "thickness";
	public static final String NX_SCATTERING_CROSS_SECTION = "scattering_cross_section";
	public static final String NX_ABSORPTION_CROSS_SECTION = "absorption_cross_section";
	public static final String NX_ATTENUATOR_TRANSMISSION = "attenuator_transmission";
	public static final String NX_STATUS = "status";
	public static final String NX_STATUS_ATTRIBUTE_TIME = "time";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	protected NXattenuatorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXattenuatorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXattenuator.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_ATTENUATOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	public void setDistanceScalar(double distance) {
		setField(NX_DISTANCE, distance);
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
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	@Override
	public double getThicknessScalar() {
		return getDouble(NX_THICKNESS);
	}

	public void setThickness(IDataset thickness) {
		setDataset(NX_THICKNESS, thickness);
	}

	public void setThicknessScalar(double thickness) {
		setField(NX_THICKNESS, thickness);
	}

	@Override
	public IDataset getScattering_cross_section() {
		return getDataset(NX_SCATTERING_CROSS_SECTION);
	}

	@Override
	public double getScattering_cross_sectionScalar() {
		return getDouble(NX_SCATTERING_CROSS_SECTION);
	}

	public void setScattering_cross_section(IDataset scattering_cross_section) {
		setDataset(NX_SCATTERING_CROSS_SECTION, scattering_cross_section);
	}

	public void setScattering_cross_sectionScalar(double scattering_cross_section) {
		setField(NX_SCATTERING_CROSS_SECTION, scattering_cross_section);
	}

	@Override
	public IDataset getAbsorption_cross_section() {
		return getDataset(NX_ABSORPTION_CROSS_SECTION);
	}

	@Override
	public double getAbsorption_cross_sectionScalar() {
		return getDouble(NX_ABSORPTION_CROSS_SECTION);
	}

	public void setAbsorption_cross_section(IDataset absorption_cross_section) {
		setDataset(NX_ABSORPTION_CROSS_SECTION, absorption_cross_section);
	}

	public void setAbsorption_cross_sectionScalar(double absorption_cross_section) {
		setField(NX_ABSORPTION_CROSS_SECTION, absorption_cross_section);
	}

	@Override
	public IDataset getAttenuator_transmission() {
		return getDataset(NX_ATTENUATOR_TRANSMISSION);
	}

	@Override
	public double getAttenuator_transmissionScalar() {
		return getDouble(NX_ATTENUATOR_TRANSMISSION);
	}

	public void setAttenuator_transmission(IDataset attenuator_transmission) {
		setDataset(NX_ATTENUATOR_TRANSMISSION, attenuator_transmission);
	}

	public void setAttenuator_transmissionScalar(double attenuator_transmission) {
		setField(NX_ATTENUATOR_TRANSMISSION, attenuator_transmission);
	}

	@Override
	public IDataset getStatus() {
		return getDataset(NX_STATUS);
	}

	@Override
	public String getStatusScalar() {
		return getString(NX_STATUS);
	}

	public void setStatus(IDataset status) {
		setDataset(NX_STATUS, status);
	}

	public void setStatusScalar(String status) {
		setString(NX_STATUS, status);
	}

	@Override
	public Date getStatusAttributeTime() {
		return getAttrDate(NX_STATUS, NX_STATUS_ATTRIBUTE_TIME);
	}

	public void setStatusAttributeTime(Date time) {
		setAttribute(NX_STATUS, NX_STATUS_ATTRIBUTE_TIME, time);
	}

}
