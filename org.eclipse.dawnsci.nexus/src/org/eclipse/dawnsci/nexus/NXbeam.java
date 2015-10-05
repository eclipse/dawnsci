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

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

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
public interface NXbeam extends NXobject {

	/**
	 * Distance from sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();

	/**
	 * Energy on entering beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_energy();

	/**
	 * Energy on leaving beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFinal_energy();

	/**
	 * Energy change caused by beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnergy_transfer();

	/**
	 * Wavelength on entering beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_wavelength();

	/**
	 * Wavelength spread FWHM on entering component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_wavelength_spread();

	/**
	 * Divergence of beam entering this component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 2; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_beam_divergence();

	/**
	 * Wavelength on leaving beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFinal_wavelength();

	/**
	 * Polarization vector on entering beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: 2; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_polarization();

	/**
	 * Polarization vector on leaving beamline component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: 2; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFinal_polarization();

	/**
	 * Wavelength spread FWHM of beam leaving this component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFinal_wavelength_spread();

	/**
	 * Divergence FWHM of beam leaving this component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 2; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFinal_beam_divergence();

	/**
	 * flux incident on beam plane area
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FLUX
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlux();

	/**
	 * Distribution of beam with respect to relevant variable e.g. wavelength. This is mainly
	 * useful for simulations which need to store plottable information at each beamline
	 * component.
	 * 
	 * @return  the value.
	 */
	public NXdata getData();
  
	/**
	 * Get a NXdata node by name:
	 * <ul>
	 * <li>
	 * Distribution of beam with respect to relevant variable e.g. wavelength. This is mainly
	 * useful for simulations which need to store plottable information at each beamline
	 * component.</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXdata for that node.
	 */
	public NXdata getData(String name);
	
	/**
	 * Get all NXdata nodes:
	 * <ul>
	 * <li>
	 * Distribution of beam with respect to relevant variable e.g. wavelength. This is mainly
	 * useful for simulations which need to store plottable information at each beamline
	 * component.</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXdata for that node.
	 */
	public Map<String, NXdata> getAllData();

}
