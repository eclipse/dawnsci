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
 * A device that reduces the intensity of a beam by attenuation.
 * If uncertain whether to use :ref:`NXfilter` (band-pass filter)
 * or :ref:`NXattenuator` (reduces beam intensity), then choose
 * :ref:`NXattenuator`.
 * 
 * @version 1.0
 */
public class NXattenuatorImpl extends NXobjectImpl implements NXattenuator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXattenuatorImpl() {
		super();
	}

	public NXattenuatorImpl(final long oid) {
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

	@Override
	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	@Override
	public DataNode setDistanceScalar(double distance) {
		return setField(NX_DISTANCE, distance);
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
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	@Override
	public double getThicknessScalar() {
		return getDouble(NX_THICKNESS);
	}

	@Override
	public DataNode setThickness(IDataset thickness) {
		return setDataset(NX_THICKNESS, thickness);
	}

	@Override
	public DataNode setThicknessScalar(double thickness) {
		return setField(NX_THICKNESS, thickness);
	}

	@Override
	public IDataset getScattering_cross_section() {
		return getDataset(NX_SCATTERING_CROSS_SECTION);
	}

	@Override
	public double getScattering_cross_sectionScalar() {
		return getDouble(NX_SCATTERING_CROSS_SECTION);
	}

	@Override
	public DataNode setScattering_cross_section(IDataset scattering_cross_section) {
		return setDataset(NX_SCATTERING_CROSS_SECTION, scattering_cross_section);
	}

	@Override
	public DataNode setScattering_cross_sectionScalar(double scattering_cross_section) {
		return setField(NX_SCATTERING_CROSS_SECTION, scattering_cross_section);
	}

	@Override
	public IDataset getAbsorption_cross_section() {
		return getDataset(NX_ABSORPTION_CROSS_SECTION);
	}

	@Override
	public double getAbsorption_cross_sectionScalar() {
		return getDouble(NX_ABSORPTION_CROSS_SECTION);
	}

	@Override
	public DataNode setAbsorption_cross_section(IDataset absorption_cross_section) {
		return setDataset(NX_ABSORPTION_CROSS_SECTION, absorption_cross_section);
	}

	@Override
	public DataNode setAbsorption_cross_sectionScalar(double absorption_cross_section) {
		return setField(NX_ABSORPTION_CROSS_SECTION, absorption_cross_section);
	}

	@Override
	public IDataset getAttenuator_transmission() {
		return getDataset(NX_ATTENUATOR_TRANSMISSION);
	}

	@Override
	public double getAttenuator_transmissionScalar() {
		return getDouble(NX_ATTENUATOR_TRANSMISSION);
	}

	@Override
	public DataNode setAttenuator_transmission(IDataset attenuator_transmission) {
		return setDataset(NX_ATTENUATOR_TRANSMISSION, attenuator_transmission);
	}

	@Override
	public DataNode setAttenuator_transmissionScalar(double attenuator_transmission) {
		return setField(NX_ATTENUATOR_TRANSMISSION, attenuator_transmission);
	}

	@Override
	public IDataset getStatus() {
		return getDataset(NX_STATUS);
	}

	@Override
	public String getStatusScalar() {
		return getString(NX_STATUS);
	}

	@Override
	public DataNode setStatus(IDataset status) {
		return setDataset(NX_STATUS, status);
	}

	@Override
	public DataNode setStatusScalar(String status) {
		return setString(NX_STATUS, status);
	}

	@Override
	public Date getStatusAttributeTime() {
		return getAttrDate(NX_STATUS, NX_STATUS_ATTRIBUTE_TIME);
	}

	@Override
	public void setStatusAttributeTime(Date time) {
		setAttribute(NX_STATUS, NX_STATUS_ATTRIBUTE_TIME, time);
	}

}
