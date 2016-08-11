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
 * A wavelength defining device.
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


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_CRYSTAL,
		NexusBaseClass.NX_VELOCITY_SELECTOR,
		NexusBaseClass.NX_GRATING);

	public NXmonochromatorImpl() {
		super();
	}

	public NXmonochromatorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmonochromator.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_MONOCHROMATOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getWavelength() {
		return getDataset(NX_WAVELENGTH);
	}

	@Override
	public double getWavelengthScalar() {
		return getDouble(NX_WAVELENGTH);
	}

	@Override
	public DataNode setWavelength(IDataset wavelength) {
		return setDataset(NX_WAVELENGTH, wavelength);
	}

	@Override
	public DataNode setWavelengthScalar(double wavelength) {
		return setField(NX_WAVELENGTH, wavelength);
	}

	@Override
	public IDataset getWavelength_error() {
		return getDataset(NX_WAVELENGTH_ERROR);
	}

	@Override
	public double getWavelength_errorScalar() {
		return getDouble(NX_WAVELENGTH_ERROR);
	}

	@Override
	public DataNode setWavelength_error(IDataset wavelength_error) {
		return setDataset(NX_WAVELENGTH_ERROR, wavelength_error);
	}

	@Override
	public DataNode setWavelength_errorScalar(double wavelength_error) {
		return setField(NX_WAVELENGTH_ERROR, wavelength_error);
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
	public IDataset getEnergy_error() {
		return getDataset(NX_ENERGY_ERROR);
	}

	@Override
	public double getEnergy_errorScalar() {
		return getDouble(NX_ENERGY_ERROR);
	}

	@Override
	public DataNode setEnergy_error(IDataset energy_error) {
		return setDataset(NX_ENERGY_ERROR, energy_error);
	}

	@Override
	public DataNode setEnergy_errorScalar(double energy_error) {
		return setField(NX_ENERGY_ERROR, energy_error);
	}

	@Override
	public NXdata getDistribution() {
		return getChild("distribution", NXdata.class);
	}

	@Override
	public void setDistribution(NXdata distribution) {
		putChild("distribution", distribution);
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
	public NXcrystal getCrystal() {
		return getChild("crystal", NXcrystal.class);
	}

	@Override
	public void setCrystal(NXcrystal crystal) {
		putChild("crystal", crystal);
	}

	@Override
	public NXcrystal getCrystal(String name) {
		return getChild(name, NXcrystal.class);
	}

	@Override
	public void setCrystal(String name, NXcrystal crystal) {
		putChild(name, crystal);
	}

	@Override
	public Map<String, NXcrystal> getAllCrystal() {
		return getChildren(NXcrystal.class);
	}
	
	@Override
	public void setAllCrystal(Map<String, NXcrystal> crystal) {
		setChildren(crystal);
	}

	@Override
	public NXvelocity_selector getVelocity_selector() {
		return getChild("velocity_selector", NXvelocity_selector.class);
	}

	@Override
	public void setVelocity_selector(NXvelocity_selector velocity_selector) {
		putChild("velocity_selector", velocity_selector);
	}

	@Override
	public NXvelocity_selector getVelocity_selector(String name) {
		return getChild(name, NXvelocity_selector.class);
	}

	@Override
	public void setVelocity_selector(String name, NXvelocity_selector velocity_selector) {
		putChild(name, velocity_selector);
	}

	@Override
	public Map<String, NXvelocity_selector> getAllVelocity_selector() {
		return getChildren(NXvelocity_selector.class);
	}
	
	@Override
	public void setAllVelocity_selector(Map<String, NXvelocity_selector> velocity_selector) {
		setChildren(velocity_selector);
	}

	@Override
	public NXgrating getGrating() {
		return getChild("grating", NXgrating.class);
	}

	@Override
	public void setGrating(NXgrating grating) {
		putChild("grating", grating);
	}

	@Override
	public NXgrating getGrating(String name) {
		return getChild(name, NXgrating.class);
	}

	@Override
	public void setGrating(String name, NXgrating grating) {
		putChild(name, grating);
	}

	@Override
	public Map<String, NXgrating> getAllGrating() {
		return getChildren(NXgrating.class);
	}
	
	@Override
	public void setAllGrating(Map<String, NXgrating> grating) {
		setChildren(grating);
	}

}
