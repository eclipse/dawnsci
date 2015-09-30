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
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This is a base class for everything which
 * selects a wavelength or energy, be it a
 * monochromator crystal, a velocity selector,
 * an undulator or whatever.
 * The expected units are:
 * * wavelength: angstrom
 * * energy: eV
 * 
 * @version 1.0
 */
public class NXmonochromatorImpl extends NXobjectImpl implements NXmonochromator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_WAVELENGTH = "wavelength";
	public static final String NX_WAVELENGTH_ERROR = "wavelength_error";
	public static final String NX_ENERGY = "energy";
	public static final String NX_ENERGY_ERROR = "energy_error";

	protected NXmonochromatorImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmonochromator.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_MONOCHROMATOR;
	}

	@Override
	public IDataset getWavelength() {
		return getDataset(NX_WAVELENGTH);
	}

	public void setWavelength(IDataset wavelength) {
		setDataset(NX_WAVELENGTH, wavelength);
	}

	@Override
	public IDataset getWavelength_error() {
		return getDataset(NX_WAVELENGTH_ERROR);
	}

	public void setWavelength_error(IDataset wavelength_error) {
		setDataset(NX_WAVELENGTH_ERROR, wavelength_error);
	}

	@Override
	public IDataset getEnergy() {
		return getDataset(NX_ENERGY);
	}

	public void setEnergy(IDataset energy) {
		setDataset(NX_ENERGY, energy);
	}

	@Override
	public IDataset getEnergy_error() {
		return getDataset(NX_ENERGY_ERROR);
	}

	public void setEnergy_error(IDataset energy_error) {
		setDataset(NX_ENERGY_ERROR, energy_error);
	}

	@Override
	public NXdata getDistribution() {
		return getChild("distribution", NXdata.class);
	}

	public void setDistribution(NXdata distribution) {
		putChild("distribution", distribution);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXcrystal getCrystal() {
		return getChild("crystal", NXcrystal.class);
	}

	public void setCrystal(NXcrystal crystal) {
		putChild("crystal", crystal);
	}

	@Override
	public NXcrystal getCrystal(String name) {
		return getChild(name, NXcrystal.class);
	}

	public void setCrystal(String name, NXcrystal crystal) {
		putChild(name, crystal);
	}

	@Override
	public Map<String, NXcrystal> getAllCrystal() {
		return getChildren(NXcrystal.class);
	}

	public void setAllCrystal(Map<String, NXcrystal> crystal) {
		setChildren(crystal);
	}

	@Override
	public NXvelocity_selector getVelocity_selector() {
		return getChild("velocity_selector", NXvelocity_selector.class);
	}

	public void setVelocity_selector(NXvelocity_selector velocity_selector) {
		putChild("velocity_selector", velocity_selector);
	}

	@Override
	public NXvelocity_selector getVelocity_selector(String name) {
		return getChild(name, NXvelocity_selector.class);
	}

	public void setVelocity_selector(String name, NXvelocity_selector velocity_selector) {
		putChild(name, velocity_selector);
	}

	@Override
	public Map<String, NXvelocity_selector> getAllVelocity_selector() {
		return getChildren(NXvelocity_selector.class);
	}

	public void setAllVelocity_selector(Map<String, NXvelocity_selector> velocity_selector) {
		setChildren(velocity_selector);
	}

	@Override
	public NXgrating getGrating() {
		return getChild("grating", NXgrating.class);
	}

	public void setGrating(NXgrating grating) {
		putChild("grating", grating);
	}

	@Override
	public NXgrating getGrating(String name) {
		return getChild(name, NXgrating.class);
	}

	public void setGrating(String name, NXgrating grating) {
		putChild(name, grating);
	}

	@Override
	public Map<String, NXgrating> getAllGrating() {
		return getChildren(NXgrating.class);
	}

	public void setAllGrating(Map<String, NXgrating> grating) {
		setChildren(grating);
	}

}
