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

package org.eclipse.dawnsci.nexus;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of the neutron or x-ray source, insertion devices and/or moderators.
 * 
 * @version 1.0
 */
public interface NXsource extends NXobject {

	/**
	 * Effective distance from sample
	 * Distance as seen by radiation from sample. This number should be negative
	 * to signify that it is upstream of the sample.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();	

	/**
	 * Effective distance from sample
	 * Distance as seen by radiation from sample. This number should be negative
	 * to signify that it is upstream of the sample.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDistanceScalar();

	/**
	 * Name of source
	 * 
	 * @return  the value.
	 */
	public IDataset getName();	

	/**
	 * Name of source
	 * 
	 * @return  the value
	 */
	 public String getNameScalar();

	/**
	 * short name for source, perhaps the acronym
	 * 
	 * @return  the value.
	 */
	public String getNameAttributeShort_name();	

	/**
	 * type of radiation source (pick one from the enumerated list and spell exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Spallation Neutron Source</b> </li>
	 * <li><b>Pulsed Reactor Neutron Source</b> </li>
	 * <li><b>Reactor Neutron Source</b> </li>
	 * <li><b>Synchrotron X-ray Source</b> </li>
	 * <li><b>Pulsed Muon Source</b> </li>
	 * <li><b>Rotating Anode X-ray</b> </li>
	 * <li><b>Fixed Tube X-ray</b> </li>
	 * <li><b>UV Laser</b> </li>
	 * <li><b>Free-Electron Laser</b> </li>
	 * <li><b>Optical Laser</b> </li>
	 * <li><b>Ion Source</b> </li>
	 * <li><b>UV Plasma Source</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * type of radiation source (pick one from the enumerated list and spell exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Spallation Neutron Source</b> </li>
	 * <li><b>Pulsed Reactor Neutron Source</b> </li>
	 * <li><b>Reactor Neutron Source</b> </li>
	 * <li><b>Synchrotron X-ray Source</b> </li>
	 * <li><b>Pulsed Muon Source</b> </li>
	 * <li><b>Rotating Anode X-ray</b> </li>
	 * <li><b>Fixed Tube X-ray</b> </li>
	 * <li><b>UV Laser</b> </li>
	 * <li><b>Free-Electron Laser</b> </li>
	 * <li><b>Optical Laser</b> </li>
	 * <li><b>Ion Source</b> </li>
	 * <li><b>UV Plasma Source</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * type of radiation probe (pick one from the enumerated list and spell exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>neutron</b> </li>
	 * <li><b>x-ray</b> </li>
	 * <li><b>muon</b> </li>
	 * <li><b>electron</b> </li>
	 * <li><b>ultraviolet</b> </li>
	 * <li><b>visible light</b> </li>
	 * <li><b>positron</b> </li>
	 * <li><b>proton</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getProbe();	

	/**
	 * type of radiation probe (pick one from the enumerated list and spell exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>neutron</b> </li>
	 * <li><b>x-ray</b> </li>
	 * <li><b>muon</b> </li>
	 * <li><b>electron</b> </li>
	 * <li><b>ultraviolet</b> </li>
	 * <li><b>visible light</b> </li>
	 * <li><b>positron</b> </li>
	 * <li><b>proton</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getProbeScalar();

	/**
	 * Source power
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPower();	

	/**
	 * Source power
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getPowerScalar();

	/**
	 * Source emittance (nm-rad) in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEmittance_x();	

	/**
	 * Source emittance (nm-rad) in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getEmittance_xScalar();

	/**
	 * Source emittance (nm-rad) in Y (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEmittance_y();	

	/**
	 * Source emittance (nm-rad) in Y (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getEmittance_yScalar();

	/**
	 * particle beam size in x
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSigma_x();	

	/**
	 * particle beam size in x
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSigma_xScalar();

	/**
	 * particle beam size in y
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSigma_y();	

	/**
	 * particle beam size in y
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSigma_yScalar();

	/**
	 * Source intensity/area (example: s-1 cm-2)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FLUX
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlux();	

	/**
	 * Source intensity/area (example: s-1 cm-2)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FLUX
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFluxScalar();

	/**
	 * Source energy.
	 * For storage rings, this would be the particle beam energy.
	 * For X-ray tubes, this would be the excitation voltage.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnergy();	

	/**
	 * Source energy.
	 * For storage rings, this would be the particle beam energy.
	 * For X-ray tubes, this would be the excitation voltage.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getEnergyScalar();

	/**
	 * Accelerator, X-ray tube, or storage ring current
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCurrent();	

	/**
	 * Accelerator, X-ray tube, or storage ring current
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getCurrentScalar();

	/**
	 * Accelerator voltage
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getVoltage();	

	/**
	 * Accelerator voltage
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getVoltageScalar();

	/**
	 * Frequency of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFrequency();	

	/**
	 * Frequency of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFrequencyScalar();

	/**
	 * Period of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PERIOD
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPeriod();	

	/**
	 * Period of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PERIOD
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getPeriodScalar();

	/**
	 * Pulsed source target material
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Ta</b> </li>
	 * <li><b>W</b> </li>
	 * <li><b>depleted_U</b> </li>
	 * <li><b>enriched_U</b> </li>
	 * <li><b>Hg</b> </li>
	 * <li><b>Pb</b> </li>
	 * <li><b>C</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTarget_material();	

	/**
	 * Pulsed source target material
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Ta</b> </li>
	 * <li><b>W</b> </li>
	 * <li><b>depleted_U</b> </li>
	 * <li><b>enriched_U</b> </li>
	 * <li><b>Hg</b> </li>
	 * <li><b>Pb</b> </li>
	 * <li><b>C</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getTarget_materialScalar();

	/**
	 * any source/facility related messages/events that
	 * occurred during the experiment
	 * 
	 * @return  the value.
	 */
	public NXnote getNotes();	

	/**
	 * For storage rings, description of the bunch pattern.
	 * This is useful to describe irregular bunch patterns.
	 * 
	 * @return  the value.
	 */
	public NXdata getBunch_pattern();	

	/**
	 * For storage rings, the number of bunches in use.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNumber_of_bunches();	

	/**
	 * For storage rings, the number of bunches in use.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getNumber_of_bunchesScalar();

	/**
	 * For storage rings, temporal length of the bunch
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBunch_length();	

	/**
	 * For storage rings, temporal length of the bunch
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBunch_lengthScalar();

	/**
	 * For storage rings, time between bunches
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBunch_distance();	

	/**
	 * For storage rings, time between bunches
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBunch_distanceScalar();

	/**
	 * temporal width of source pulse
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPulse_width();	

	/**
	 * temporal width of source pulse
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getPulse_widthScalar();

	/**
	 * source pulse shape
	 * 
	 * @return  the value.
	 */
	public NXdata getPulse_shape();	

	/**
	 * source operating mode
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Single Bunch</b> 
	 * for storage rings</li>
	 * <li><b>Multi Bunch</b> 
	 * for storage rings</li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMode();	

	/**
	 * source operating mode
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Single Bunch</b> 
	 * for storage rings</li>
	 * <li><b>Multi Bunch</b> 
	 * for storage rings</li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getModeScalar();

	/**
	 * Is the synchrotron operating in top_up mode?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTop_up();	

	/**
	 * Is the synchrotron operating in top_up mode?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getTop_upScalar();

	/**
	 * For storage rings, the current at the end of the most recent injection.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLast_fill();	

	/**
	 * For storage rings, the current at the end of the most recent injection.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getLast_fillScalar();

	/**
	 * date and time of the most recent injection.
	 * 
	 * @return  the value.
	 */
	public Date getLast_fillAttributeTime();	

	/**
	 * "Engineering" location of source
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	

	/**
	 * The wavelength or energy distribution of the source
	 * 
	 * @return  the value.
	 */
	public NXdata getDistribution();	

}
