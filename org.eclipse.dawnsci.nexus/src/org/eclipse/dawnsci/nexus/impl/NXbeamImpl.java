/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of the state of the neutron or X-ray beam at any location. It will be referenced
 * by beamline component groups within the NXinstrument group or by the NXsample group. Note
 * that variables such as the incident energy could be scalar values or arrays. This group is
 * especially valuable in storing the results of instrument simulations in which it is useful
 * to specify the beam profile, time distribution etc. at each beamline component. Otherwise,
 * its most likely use is in the NXsample group in which it defines the results of the neutron
 * scattering by the sample, e.g., energy transfer, polarizations.
 * 
 * @version 1.0
 */
public class NXbeamImpl extends NXobjectImpl implements NXbeam {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DISTANCE = "distance";
	public static final String NX_INCIDENT_ENERGY = "incident_energy";
	public static final String NX_FINAL_ENERGY = "final_energy";
	public static final String NX_ENERGY_TRANSFER = "energy_transfer";
	public static final String NX_INCIDENT_WAVELENGTH = "incident_wavelength";
	public static final String NX_INCIDENT_WAVELENGTH_SPREAD = "incident_wavelength_spread";
	public static final String NX_INCIDENT_BEAM_DIVERGENCE = "incident_beam_divergence";
	public static final String NX_FINAL_WAVELENGTH = "final_wavelength";
	public static final String NX_INCIDENT_POLARIZATION = "incident_polarization";
	public static final String NX_FINAL_POLARIZATION = "final_polarization";
	public static final String NX_FINAL_WAVELENGTH_SPREAD = "final_wavelength_spread";
	public static final String NX_FINAL_BEAM_DIVERGENCE = "final_beam_divergence";
	public static final String NX_FLUX = "flux";

	protected NXbeamImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXbeam.class;
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getIncident_energy() {
		return getDataset(NX_INCIDENT_ENERGY);
	}

	public void setIncident_energy(IDataset incident_energy) {
		setDataset(NX_INCIDENT_ENERGY, incident_energy);
	}

	@Override
	public IDataset getFinal_energy() {
		return getDataset(NX_FINAL_ENERGY);
	}

	public void setFinal_energy(IDataset final_energy) {
		setDataset(NX_FINAL_ENERGY, final_energy);
	}

	@Override
	public IDataset getEnergy_transfer() {
		return getDataset(NX_ENERGY_TRANSFER);
	}

	public void setEnergy_transfer(IDataset energy_transfer) {
		setDataset(NX_ENERGY_TRANSFER, energy_transfer);
	}

	@Override
	public IDataset getIncident_wavelength() {
		return getDataset(NX_INCIDENT_WAVELENGTH);
	}

	public void setIncident_wavelength(IDataset incident_wavelength) {
		setDataset(NX_INCIDENT_WAVELENGTH, incident_wavelength);
	}

	@Override
	public IDataset getIncident_wavelength_spread() {
		return getDataset(NX_INCIDENT_WAVELENGTH_SPREAD);
	}

	public void setIncident_wavelength_spread(IDataset incident_wavelength_spread) {
		setDataset(NX_INCIDENT_WAVELENGTH_SPREAD, incident_wavelength_spread);
	}

	@Override
	public IDataset getIncident_beam_divergence() {
		return getDataset(NX_INCIDENT_BEAM_DIVERGENCE);
	}

	public void setIncident_beam_divergence(IDataset incident_beam_divergence) {
		setDataset(NX_INCIDENT_BEAM_DIVERGENCE, incident_beam_divergence);
	}

	@Override
	public IDataset getFinal_wavelength() {
		return getDataset(NX_FINAL_WAVELENGTH);
	}

	public void setFinal_wavelength(IDataset final_wavelength) {
		setDataset(NX_FINAL_WAVELENGTH, final_wavelength);
	}

	@Override
	public IDataset getIncident_polarization() {
		return getDataset(NX_INCIDENT_POLARIZATION);
	}

	public void setIncident_polarization(IDataset incident_polarization) {
		setDataset(NX_INCIDENT_POLARIZATION, incident_polarization);
	}

	@Override
	public IDataset getFinal_polarization() {
		return getDataset(NX_FINAL_POLARIZATION);
	}

	public void setFinal_polarization(IDataset final_polarization) {
		setDataset(NX_FINAL_POLARIZATION, final_polarization);
	}

	@Override
	public IDataset getFinal_wavelength_spread() {
		return getDataset(NX_FINAL_WAVELENGTH_SPREAD);
	}

	public void setFinal_wavelength_spread(IDataset final_wavelength_spread) {
		setDataset(NX_FINAL_WAVELENGTH_SPREAD, final_wavelength_spread);
	}

	@Override
	public IDataset getFinal_beam_divergence() {
		return getDataset(NX_FINAL_BEAM_DIVERGENCE);
	}

	public void setFinal_beam_divergence(IDataset final_beam_divergence) {
		setDataset(NX_FINAL_BEAM_DIVERGENCE, final_beam_divergence);
	}

	@Override
	public IDataset getFlux() {
		return getDataset(NX_FLUX);
	}

	public void setFlux(IDataset flux) {
		setDataset(NX_FLUX, flux);
	}

	@Override
	public NXdata getData() {
		return getChild("data", NXdata.class);
	}

	public void setData(NXdata data) {
		putChild("data", data);
	}

	@Override
	public NXdata getData(String name) {
		return getChild(name, NXdata.class);
	}

	public void setData(String name, NXdata data) {
		putChild(name, data);
	}

	@Override
	public Map<String, NXdata> getAllData() {
		return getChildren(NXdata.class);
	}

	public void setAllData(Map<String, NXdata> data) {
		setChildren(data);
	}

}
