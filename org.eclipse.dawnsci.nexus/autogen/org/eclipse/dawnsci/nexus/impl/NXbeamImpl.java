/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_DATA);

	protected NXbeamImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXbeamImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXbeam.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_BEAM;
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

	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	public DataNode setDistanceScalar(double distance) {
		return setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getIncident_energy() {
		return getDataset(NX_INCIDENT_ENERGY);
	}

	@Override
	public double getIncident_energyScalar() {
		return getDouble(NX_INCIDENT_ENERGY);
	}

	public DataNode setIncident_energy(IDataset incident_energy) {
		return setDataset(NX_INCIDENT_ENERGY, incident_energy);
	}

	public DataNode setIncident_energyScalar(double incident_energy) {
		return setField(NX_INCIDENT_ENERGY, incident_energy);
	}

	@Override
	public IDataset getFinal_energy() {
		return getDataset(NX_FINAL_ENERGY);
	}

	@Override
	public double getFinal_energyScalar() {
		return getDouble(NX_FINAL_ENERGY);
	}

	public DataNode setFinal_energy(IDataset final_energy) {
		return setDataset(NX_FINAL_ENERGY, final_energy);
	}

	public DataNode setFinal_energyScalar(double final_energy) {
		return setField(NX_FINAL_ENERGY, final_energy);
	}

	@Override
	public IDataset getEnergy_transfer() {
		return getDataset(NX_ENERGY_TRANSFER);
	}

	@Override
	public double getEnergy_transferScalar() {
		return getDouble(NX_ENERGY_TRANSFER);
	}

	public DataNode setEnergy_transfer(IDataset energy_transfer) {
		return setDataset(NX_ENERGY_TRANSFER, energy_transfer);
	}

	public DataNode setEnergy_transferScalar(double energy_transfer) {
		return setField(NX_ENERGY_TRANSFER, energy_transfer);
	}

	@Override
	public IDataset getIncident_wavelength() {
		return getDataset(NX_INCIDENT_WAVELENGTH);
	}

	@Override
	public double getIncident_wavelengthScalar() {
		return getDouble(NX_INCIDENT_WAVELENGTH);
	}

	public DataNode setIncident_wavelength(IDataset incident_wavelength) {
		return setDataset(NX_INCIDENT_WAVELENGTH, incident_wavelength);
	}

	public DataNode setIncident_wavelengthScalar(double incident_wavelength) {
		return setField(NX_INCIDENT_WAVELENGTH, incident_wavelength);
	}

	@Override
	public IDataset getIncident_wavelength_spread() {
		return getDataset(NX_INCIDENT_WAVELENGTH_SPREAD);
	}

	@Override
	public double getIncident_wavelength_spreadScalar() {
		return getDouble(NX_INCIDENT_WAVELENGTH_SPREAD);
	}

	public DataNode setIncident_wavelength_spread(IDataset incident_wavelength_spread) {
		return setDataset(NX_INCIDENT_WAVELENGTH_SPREAD, incident_wavelength_spread);
	}

	public DataNode setIncident_wavelength_spreadScalar(double incident_wavelength_spread) {
		return setField(NX_INCIDENT_WAVELENGTH_SPREAD, incident_wavelength_spread);
	}

	@Override
	public IDataset getIncident_beam_divergence() {
		return getDataset(NX_INCIDENT_BEAM_DIVERGENCE);
	}

	@Override
	public double getIncident_beam_divergenceScalar() {
		return getDouble(NX_INCIDENT_BEAM_DIVERGENCE);
	}

	public DataNode setIncident_beam_divergence(IDataset incident_beam_divergence) {
		return setDataset(NX_INCIDENT_BEAM_DIVERGENCE, incident_beam_divergence);
	}

	public DataNode setIncident_beam_divergenceScalar(double incident_beam_divergence) {
		return setField(NX_INCIDENT_BEAM_DIVERGENCE, incident_beam_divergence);
	}

	@Override
	public IDataset getFinal_wavelength() {
		return getDataset(NX_FINAL_WAVELENGTH);
	}

	@Override
	public double getFinal_wavelengthScalar() {
		return getDouble(NX_FINAL_WAVELENGTH);
	}

	public DataNode setFinal_wavelength(IDataset final_wavelength) {
		return setDataset(NX_FINAL_WAVELENGTH, final_wavelength);
	}

	public DataNode setFinal_wavelengthScalar(double final_wavelength) {
		return setField(NX_FINAL_WAVELENGTH, final_wavelength);
	}

	@Override
	public IDataset getIncident_polarization() {
		return getDataset(NX_INCIDENT_POLARIZATION);
	}

	@Override
	public double getIncident_polarizationScalar() {
		return getDouble(NX_INCIDENT_POLARIZATION);
	}

	public DataNode setIncident_polarization(IDataset incident_polarization) {
		return setDataset(NX_INCIDENT_POLARIZATION, incident_polarization);
	}

	public DataNode setIncident_polarizationScalar(double incident_polarization) {
		return setField(NX_INCIDENT_POLARIZATION, incident_polarization);
	}

	@Override
	public IDataset getFinal_polarization() {
		return getDataset(NX_FINAL_POLARIZATION);
	}

	@Override
	public double getFinal_polarizationScalar() {
		return getDouble(NX_FINAL_POLARIZATION);
	}

	public DataNode setFinal_polarization(IDataset final_polarization) {
		return setDataset(NX_FINAL_POLARIZATION, final_polarization);
	}

	public DataNode setFinal_polarizationScalar(double final_polarization) {
		return setField(NX_FINAL_POLARIZATION, final_polarization);
	}

	@Override
	public IDataset getFinal_wavelength_spread() {
		return getDataset(NX_FINAL_WAVELENGTH_SPREAD);
	}

	@Override
	public double getFinal_wavelength_spreadScalar() {
		return getDouble(NX_FINAL_WAVELENGTH_SPREAD);
	}

	public DataNode setFinal_wavelength_spread(IDataset final_wavelength_spread) {
		return setDataset(NX_FINAL_WAVELENGTH_SPREAD, final_wavelength_spread);
	}

	public DataNode setFinal_wavelength_spreadScalar(double final_wavelength_spread) {
		return setField(NX_FINAL_WAVELENGTH_SPREAD, final_wavelength_spread);
	}

	@Override
	public IDataset getFinal_beam_divergence() {
		return getDataset(NX_FINAL_BEAM_DIVERGENCE);
	}

	@Override
	public double getFinal_beam_divergenceScalar() {
		return getDouble(NX_FINAL_BEAM_DIVERGENCE);
	}

	public DataNode setFinal_beam_divergence(IDataset final_beam_divergence) {
		return setDataset(NX_FINAL_BEAM_DIVERGENCE, final_beam_divergence);
	}

	public DataNode setFinal_beam_divergenceScalar(double final_beam_divergence) {
		return setField(NX_FINAL_BEAM_DIVERGENCE, final_beam_divergence);
	}

	@Override
	public IDataset getFlux() {
		return getDataset(NX_FLUX);
	}

	@Override
	public double getFluxScalar() {
		return getDouble(NX_FLUX);
	}

	public DataNode setFlux(IDataset flux) {
		return setDataset(NX_FLUX, flux);
	}

	public DataNode setFluxScalar(double flux) {
		return setField(NX_FLUX, flux);
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
