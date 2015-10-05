/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.nexus.*;

/**
 * Description of an insertion device, as in a synchrotron.
 * 
 * @version 1.0
 */
public class NXinsertion_deviceImpl extends NXobjectImpl implements NXinsertion_device {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_GAP = "gap";
	public static final String NX_TAPER = "taper";
	public static final String NX_PHASE = "phase";
	public static final String NX_POLES = "poles";
	public static final String NX_MAGNETIC_WAVELENGTH = "magnetic_wavelength";
	public static final String NX_K = "k";
	public static final String NX_LENGTH = "length";
	public static final String NX_POWER = "power";
	public static final String NX_ENERGY = "energy";
	public static final String NX_BANDWIDTH = "bandwidth";
	public static final String NX_HARMONIC = "harmonic";

	protected NXinsertion_deviceImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXinsertion_device.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_INSERTION_DEVICE;
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getGap() {
		return getDataset(NX_GAP);
	}

	public void setGap(IDataset gap) {
		setDataset(NX_GAP, gap);
	}

	@Override
	public IDataset getTaper() {
		return getDataset(NX_TAPER);
	}

	public void setTaper(IDataset taper) {
		setDataset(NX_TAPER, taper);
	}

	@Override
	public IDataset getPhase() {
		return getDataset(NX_PHASE);
	}

	public void setPhase(IDataset phase) {
		setDataset(NX_PHASE, phase);
	}

	@Override
	public IDataset getPoles() {
		return getDataset(NX_POLES);
	}

	public void setPoles(IDataset poles) {
		setDataset(NX_POLES, poles);
	}

	@Override
	public IDataset getMagnetic_wavelength() {
		return getDataset(NX_MAGNETIC_WAVELENGTH);
	}

	public void setMagnetic_wavelength(IDataset magnetic_wavelength) {
		setDataset(NX_MAGNETIC_WAVELENGTH, magnetic_wavelength);
	}

	@Override
	public IDataset getK() {
		return getDataset(NX_K);
	}

	public void setK(IDataset k) {
		setDataset(NX_K, k);
	}

	@Override
	public IDataset getLength() {
		return getDataset(NX_LENGTH);
	}

	public void setLength(IDataset length) {
		setDataset(NX_LENGTH, length);
	}

	@Override
	public IDataset getPower() {
		return getDataset(NX_POWER);
	}

	public void setPower(IDataset power) {
		setDataset(NX_POWER, power);
	}

	@Override
	public IDataset getEnergy() {
		return getDataset(NX_ENERGY);
	}

	public void setEnergy(IDataset energy) {
		setDataset(NX_ENERGY, energy);
	}

	@Override
	public IDataset getBandwidth() {
		return getDataset(NX_BANDWIDTH);
	}

	public void setBandwidth(IDataset bandwidth) {
		setDataset(NX_BANDWIDTH, bandwidth);
	}

	@Override
	public IDataset getHarmonic() {
		return getDataset(NX_HARMONIC);
	}

	public void setHarmonic(IDataset harmonic) {
		setDataset(NX_HARMONIC, harmonic);
	}

	@Override
	public NXdata getSpectrum() {
		return getChild("spectrum", NXdata.class);
	}

	public void setSpectrum(NXdata spectrum) {
		putChild("spectrum", spectrum);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}

	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

}
