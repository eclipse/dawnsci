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

package org.eclipse.dawnsci.nexus;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * The neutron or x-ray storage ring/facility.
 * 
 * @version 1.0
 */
public interface NXsource extends NXobject {

	public static final String NX_DISTANCE = "distance";
	public static final String NX_NAME = "name";
	public static final String NX_NAME_ATTRIBUTE_SHORT_NAME = "short_name";
	public static final String NX_TYPE = "type";
	public static final String NX_PROBE = "probe";
	public static final String NX_POWER = "power";
	public static final String NX_EMITTANCE_X = "emittance_x";
	public static final String NX_EMITTANCE_Y = "emittance_y";
	public static final String NX_SIGMA_X = "sigma_x";
	public static final String NX_SIGMA_Y = "sigma_y";
	public static final String NX_FLUX = "flux";
	public static final String NX_ENERGY = "energy";
	public static final String NX_CURRENT = "current";
	public static final String NX_VOLTAGE = "voltage";
	public static final String NX_FREQUENCY = "frequency";
	public static final String NX_PERIOD = "period";
	public static final String NX_TARGET_MATERIAL = "target_material";
	public static final String NX_NUMBER_OF_BUNCHES = "number_of_bunches";
	public static final String NX_BUNCH_LENGTH = "bunch_length";
	public static final String NX_BUNCH_DISTANCE = "bunch_distance";
	public static final String NX_PULSE_WIDTH = "pulse_width";
	public static final String NX_MODE = "mode";
	public static final String NX_TOP_UP = "top_up";
	public static final String NX_LAST_FILL = "last_fill";
	public static final String NX_LAST_FILL_ATTRIBUTE_TIME = "time";
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
	 * @param distance the distance
	 */
	public DataNode setDistance(IDataset distance);

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
	public double getDistanceScalar();

	/**
	 * Effective distance from sample
	 * Distance as seen by radiation from sample. This number should be negative
	 * to signify that it is upstream of the sample.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistanceScalar(double distance);

	/**
	 * Name of source
	 * 
	 * @return  the value.
	 */
	public IDataset getName();
	
	/**
	 * Name of source
	 * 
	 * @param name the name
	 */
	public DataNode setName(IDataset name);

	/**
	 * Name of source
	 * 
	 * @return  the value.
	 */
	public String getNameScalar();

	/**
	 * Name of source
	 * 
	 * @param name the name
	 */
	public DataNode setNameScalar(String name);

	/**
	 * short name for source, perhaps the acronym
	 * 
	 * @return  the value.
	 */
	public String getNameAttributeShort_name();
	
	/**
	 * short name for source, perhaps the acronym
	 * 
	 * @param short_name the short_name
	 */
	public void setNameAttributeShort_name(String short_name);

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
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

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
	public String getTypeScalar();

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
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

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
	 * @param probe the probe
	 */
	public DataNode setProbe(IDataset probe);

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
	public String getProbeScalar();

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
	 * @param probe the probe
	 */
	public DataNode setProbeScalar(String probe);

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
	 * @param power the power
	 */
	public DataNode setPower(IDataset power);

	/**
	 * Source power
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPowerScalar();

	/**
	 * Source power
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @param power the power
	 */
	public DataNode setPowerScalar(double power);

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
	 * @param emittance_x the emittance_x
	 */
	public DataNode setEmittance_x(IDataset emittance_x);

	/**
	 * Source emittance (nm-rad) in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEmittance_xScalar();

	/**
	 * Source emittance (nm-rad) in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @param emittance_x the emittance_x
	 */
	public DataNode setEmittance_xScalar(double emittance_x);

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
	 * @param emittance_y the emittance_y
	 */
	public DataNode setEmittance_y(IDataset emittance_y);

	/**
	 * Source emittance (nm-rad) in Y (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEmittance_yScalar();

	/**
	 * Source emittance (nm-rad) in Y (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_EMITTANCE
	 * </p>
	 * 
	 * @param emittance_y the emittance_y
	 */
	public DataNode setEmittance_yScalar(double emittance_y);

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
	 * @param sigma_x the sigma_x
	 */
	public DataNode setSigma_x(IDataset sigma_x);

	/**
	 * particle beam size in x
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSigma_xScalar();

	/**
	 * particle beam size in x
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param sigma_x the sigma_x
	 */
	public DataNode setSigma_xScalar(double sigma_x);

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
	 * @param sigma_y the sigma_y
	 */
	public DataNode setSigma_y(IDataset sigma_y);

	/**
	 * particle beam size in y
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSigma_yScalar();

	/**
	 * particle beam size in y
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param sigma_y the sigma_y
	 */
	public DataNode setSigma_yScalar(double sigma_y);

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
	 * @param flux the flux
	 */
	public DataNode setFlux(IDataset flux);

	/**
	 * Source intensity/area (example: s-1 cm-2)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FLUX
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFluxScalar();

	/**
	 * Source intensity/area (example: s-1 cm-2)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FLUX
	 * </p>
	 * 
	 * @param flux the flux
	 */
	public DataNode setFluxScalar(double flux);

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
	 * @param energy the energy
	 */
	public DataNode setEnergy(IDataset energy);

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
	public double getEnergyScalar();

	/**
	 * Source energy.
	 * For storage rings, this would be the particle beam energy.
	 * For X-ray tubes, this would be the excitation voltage.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergyScalar(double energy);

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
	 * @param current the current
	 */
	public DataNode setCurrent(IDataset current);

	/**
	 * Accelerator, X-ray tube, or storage ring current
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCurrentScalar();

	/**
	 * Accelerator, X-ray tube, or storage ring current
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param current the current
	 */
	public DataNode setCurrentScalar(double current);

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
	 * @param voltage the voltage
	 */
	public DataNode setVoltage(IDataset voltage);

	/**
	 * Accelerator voltage
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getVoltageScalar();

	/**
	 * Accelerator voltage
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @param voltage the voltage
	 */
	public DataNode setVoltageScalar(double voltage);

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
	 * @param frequency the frequency
	 */
	public DataNode setFrequency(IDataset frequency);

	/**
	 * Frequency of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFrequencyScalar();

	/**
	 * Frequency of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param frequency the frequency
	 */
	public DataNode setFrequencyScalar(double frequency);

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
	 * @param period the period
	 */
	public DataNode setPeriod(IDataset period);

	/**
	 * Period of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PERIOD
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPeriodScalar();

	/**
	 * Period of pulsed source
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PERIOD
	 * </p>
	 * 
	 * @param period the period
	 */
	public DataNode setPeriodScalar(double period);

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
	 * @param target_material the target_material
	 */
	public DataNode setTarget_material(IDataset target_material);

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
	public String getTarget_materialScalar();

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
	 * @param target_material the target_material
	 */
	public DataNode setTarget_materialScalar(String target_material);

	/**
	 * any source/facility related messages/events that
	 * occurred during the experiment
	 * 
	 * @return  the value.
	 */
	public NXnote getNotes();
	
	/**
	 * any source/facility related messages/events that
	 * occurred during the experiment
	 * 
	 * @param notes the notes
	 */
	public void setNotes(NXnote notes);

	/**
	 * For storage rings, description of the bunch pattern.
	 * This is useful to describe irregular bunch patterns.
	 * 
	 * @return  the value.
	 */
	public NXdata getBunch_pattern();
	
	/**
	 * For storage rings, description of the bunch pattern.
	 * This is useful to describe irregular bunch patterns.
	 * 
	 * @param bunch_pattern the bunch_pattern
	 */
	public void setBunch_pattern(NXdata bunch_pattern);

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
	 * @param number_of_bunches the number_of_bunches
	 */
	public DataNode setNumber_of_bunches(IDataset number_of_bunches);

	/**
	 * For storage rings, the number of bunches in use.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getNumber_of_bunchesScalar();

	/**
	 * For storage rings, the number of bunches in use.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param number_of_bunches the number_of_bunches
	 */
	public DataNode setNumber_of_bunchesScalar(long number_of_bunches);

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
	 * @param bunch_length the bunch_length
	 */
	public DataNode setBunch_length(IDataset bunch_length);

	/**
	 * For storage rings, temporal length of the bunch
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBunch_lengthScalar();

	/**
	 * For storage rings, temporal length of the bunch
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param bunch_length the bunch_length
	 */
	public DataNode setBunch_lengthScalar(double bunch_length);

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
	 * @param bunch_distance the bunch_distance
	 */
	public DataNode setBunch_distance(IDataset bunch_distance);

	/**
	 * For storage rings, time between bunches
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBunch_distanceScalar();

	/**
	 * For storage rings, time between bunches
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param bunch_distance the bunch_distance
	 */
	public DataNode setBunch_distanceScalar(double bunch_distance);

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
	 * @param pulse_width the pulse_width
	 */
	public DataNode setPulse_width(IDataset pulse_width);

	/**
	 * temporal width of source pulse
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPulse_widthScalar();

	/**
	 * temporal width of source pulse
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param pulse_width the pulse_width
	 */
	public DataNode setPulse_widthScalar(double pulse_width);

	/**
	 * source pulse shape
	 * 
	 * @return  the value.
	 */
	public NXdata getPulse_shape();
	
	/**
	 * source pulse shape
	 * 
	 * @param pulse_shape the pulse_shape
	 */
	public void setPulse_shape(NXdata pulse_shape);

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
	 * @param mode the mode
	 */
	public DataNode setMode(IDataset mode);

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
	public String getModeScalar();

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
	 * @param mode the mode
	 */
	public DataNode setModeScalar(String mode);

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
	 * @param top_up the top_up
	 */
	public DataNode setTop_up(IDataset top_up);

	/**
	 * Is the synchrotron operating in top_up mode?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public boolean getTop_upScalar();

	/**
	 * Is the synchrotron operating in top_up mode?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param top_up the top_up
	 */
	public DataNode setTop_upScalar(boolean top_up);

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
	 * @param last_fill the last_fill
	 */
	public DataNode setLast_fill(IDataset last_fill);

	/**
	 * For storage rings, the current at the end of the most recent injection.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getLast_fillScalar();

	/**
	 * For storage rings, the current at the end of the most recent injection.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param last_fill the last_fill
	 */
	public DataNode setLast_fillScalar(Number last_fill);

	/**
	 * date and time of the most recent injection.
	 * 
	 * @return  the value.
	 */
	public Date getLast_fillAttributeTime();
	
	/**
	 * date and time of the most recent injection.
	 * 
	 * @param time the time
	 */
	public void setLast_fillAttributeTime(Date time);

	/**
	 * "Engineering" location of source
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * "Engineering" location of source
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);

	/**
	 * The wavelength or energy distribution of the source
	 * 
	 * @return  the value.
	 */
	public NXdata getDistribution();
	
	/**
	 * The wavelength or energy distribution of the source
	 * 
	 * @param distribution the distribution
	 */
	public void setDistribution(NXdata distribution);

}
