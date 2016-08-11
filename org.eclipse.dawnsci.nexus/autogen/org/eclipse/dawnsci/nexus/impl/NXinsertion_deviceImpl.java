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
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * An insertion device, as used in a synchrotron light source.
 * 
 * @version 1.0
 */
public class NXinsertion_deviceImpl extends NXobjectImpl implements NXinsertion_device {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_GEOMETRY);

	public NXinsertion_deviceImpl() {
		super();
	}

	public NXinsertion_deviceImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXinsertion_device.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_INSERTION_DEVICE;
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
	public IDataset getGap() {
		return getDataset(NX_GAP);
	}

	@Override
	public double getGapScalar() {
		return getDouble(NX_GAP);
	}

	@Override
	public DataNode setGap(IDataset gap) {
		return setDataset(NX_GAP, gap);
	}

	@Override
	public DataNode setGapScalar(double gap) {
		return setField(NX_GAP, gap);
	}

	@Override
	public IDataset getTaper() {
		return getDataset(NX_TAPER);
	}

	@Override
	public double getTaperScalar() {
		return getDouble(NX_TAPER);
	}

	@Override
	public DataNode setTaper(IDataset taper) {
		return setDataset(NX_TAPER, taper);
	}

	@Override
	public DataNode setTaperScalar(double taper) {
		return setField(NX_TAPER, taper);
	}

	@Override
	public IDataset getPhase() {
		return getDataset(NX_PHASE);
	}

	@Override
	public double getPhaseScalar() {
		return getDouble(NX_PHASE);
	}

	@Override
	public DataNode setPhase(IDataset phase) {
		return setDataset(NX_PHASE, phase);
	}

	@Override
	public DataNode setPhaseScalar(double phase) {
		return setField(NX_PHASE, phase);
	}

	@Override
	public IDataset getPoles() {
		return getDataset(NX_POLES);
	}

	@Override
	public long getPolesScalar() {
		return getLong(NX_POLES);
	}

	@Override
	public DataNode setPoles(IDataset poles) {
		return setDataset(NX_POLES, poles);
	}

	@Override
	public DataNode setPolesScalar(long poles) {
		return setField(NX_POLES, poles);
	}

	@Override
	public IDataset getMagnetic_wavelength() {
		return getDataset(NX_MAGNETIC_WAVELENGTH);
	}

	@Override
	public double getMagnetic_wavelengthScalar() {
		return getDouble(NX_MAGNETIC_WAVELENGTH);
	}

	@Override
	public DataNode setMagnetic_wavelength(IDataset magnetic_wavelength) {
		return setDataset(NX_MAGNETIC_WAVELENGTH, magnetic_wavelength);
	}

	@Override
	public DataNode setMagnetic_wavelengthScalar(double magnetic_wavelength) {
		return setField(NX_MAGNETIC_WAVELENGTH, magnetic_wavelength);
	}

	@Override
	public IDataset getK() {
		return getDataset(NX_K);
	}

	@Override
	public double getKScalar() {
		return getDouble(NX_K);
	}

	@Override
	public DataNode setK(IDataset k) {
		return setDataset(NX_K, k);
	}

	@Override
	public DataNode setKScalar(double k) {
		return setField(NX_K, k);
	}

	@Override
	public IDataset getLength() {
		return getDataset(NX_LENGTH);
	}

	@Override
	public double getLengthScalar() {
		return getDouble(NX_LENGTH);
	}

	@Override
	public DataNode setLength(IDataset length) {
		return setDataset(NX_LENGTH, length);
	}

	@Override
	public DataNode setLengthScalar(double length) {
		return setField(NX_LENGTH, length);
	}

	@Override
	public IDataset getPower() {
		return getDataset(NX_POWER);
	}

	@Override
	public double getPowerScalar() {
		return getDouble(NX_POWER);
	}

	@Override
	public DataNode setPower(IDataset power) {
		return setDataset(NX_POWER, power);
	}

	@Override
	public DataNode setPowerScalar(double power) {
		return setField(NX_POWER, power);
	}

	@Override
	public IDataset getEnergy() {
		return getDataset(NX_ENERGY);
	}

	@Override
	public double getEnergyScalar() {
		return getDouble(NX_ENERGY);
	}

	@Override
	public DataNode setEnergy(IDataset energy) {
		return setDataset(NX_ENERGY, energy);
	}

	@Override
	public DataNode setEnergyScalar(double energy) {
		return setField(NX_ENERGY, energy);
	}

	@Override
	public IDataset getBandwidth() {
		return getDataset(NX_BANDWIDTH);
	}

	@Override
	public double getBandwidthScalar() {
		return getDouble(NX_BANDWIDTH);
	}

	@Override
	public DataNode setBandwidth(IDataset bandwidth) {
		return setDataset(NX_BANDWIDTH, bandwidth);
	}

	@Override
	public DataNode setBandwidthScalar(double bandwidth) {
		return setField(NX_BANDWIDTH, bandwidth);
	}

	@Override
	public IDataset getHarmonic() {
		return getDataset(NX_HARMONIC);
	}

	@Override
	public long getHarmonicScalar() {
		return getLong(NX_HARMONIC);
	}

	@Override
	public DataNode setHarmonic(IDataset harmonic) {
		return setDataset(NX_HARMONIC, harmonic);
	}

	@Override
	public DataNode setHarmonicScalar(long harmonic) {
		return setField(NX_HARMONIC, harmonic);
	}

	@Override
	public NXdata getSpectrum() {
		return getChild("spectrum", NXdata.class);
	}

	@Override
	public void setSpectrum(NXdata spectrum) {
		putChild("spectrum", spectrum);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	@Override
	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}
	
	@Override
	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

}
