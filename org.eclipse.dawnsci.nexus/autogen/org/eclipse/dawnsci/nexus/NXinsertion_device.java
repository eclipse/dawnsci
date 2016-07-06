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

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * An insertion device, as used in a synchrotron light source.
 * 
 * @version 1.0
 */
public interface NXinsertion_device extends NXobject {

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
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>undulator</b> </li>
	 * <li><b>wiggler</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>undulator</b> </li>
	 * <li><b>wiggler</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>undulator</b> </li>
	 * <li><b>wiggler</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>undulator</b> </li>
	 * <li><b>wiggler</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * separation between opposing pairs of magnetic poles
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGap();
	
	/**
	 * separation between opposing pairs of magnetic poles
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param gap the gap
	 */
	public DataNode setGap(IDataset gap);

	/**
	 * separation between opposing pairs of magnetic poles
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getGapScalar();

	/**
	 * separation between opposing pairs of magnetic poles
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param gap the gap
	 */
	public DataNode setGapScalar(double gap);

	/**
	 * angular of gap difference between upstream and downstream ends of the insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTaper();
	
	/**
	 * angular of gap difference between upstream and downstream ends of the insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param taper the taper
	 */
	public DataNode setTaper(IDataset taper);

	/**
	 * angular of gap difference between upstream and downstream ends of the insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTaperScalar();

	/**
	 * angular of gap difference between upstream and downstream ends of the insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param taper the taper
	 */
	public DataNode setTaperScalar(double taper);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPhase();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param phase the phase
	 */
	public DataNode setPhase(IDataset phase);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPhaseScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param phase the phase
	 */
	public DataNode setPhaseScalar(double phase);

	/**
	 * number of poles
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPoles();
	
	/**
	 * number of poles
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param poles the poles
	 */
	public DataNode setPoles(IDataset poles);

	/**
	 * number of poles
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getPolesScalar();

	/**
	 * number of poles
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param poles the poles
	 */
	public DataNode setPolesScalar(long poles);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMagnetic_wavelength();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param magnetic_wavelength the magnetic_wavelength
	 */
	public DataNode setMagnetic_wavelength(IDataset magnetic_wavelength);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMagnetic_wavelengthScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param magnetic_wavelength the magnetic_wavelength
	 */
	public DataNode setMagnetic_wavelengthScalar(double magnetic_wavelength);

	/**
	 * beam displacement parameter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getK();
	
	/**
	 * beam displacement parameter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @param k the k
	 */
	public DataNode setK(IDataset k);

	/**
	 * beam displacement parameter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getKScalar();

	/**
	 * beam displacement parameter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @param k the k
	 */
	public DataNode setKScalar(double k);

	/**
	 * length of insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLength();
	
	/**
	 * length of insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param length the length
	 */
	public DataNode setLength(IDataset length);

	/**
	 * length of insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getLengthScalar();

	/**
	 * length of insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param length the length
	 */
	public DataNode setLengthScalar(double length);

	/**
	 * total power delivered by insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPower();
	
	/**
	 * total power delivered by insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @param power the power
	 */
	public DataNode setPower(IDataset power);

	/**
	 * total power delivered by insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPowerScalar();

	/**
	 * total power delivered by insertion device
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_POWER
	 * </p>
	 * 
	 * @param power the power
	 */
	public DataNode setPowerScalar(double power);

	/**
	 * energy of peak intensity in output spectrum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnergy();
	
	/**
	 * energy of peak intensity in output spectrum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergy(IDataset energy);

	/**
	 * energy of peak intensity in output spectrum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEnergyScalar();

	/**
	 * energy of peak intensity in output spectrum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergyScalar(double energy);

	/**
	 * bandwidth of peak energy
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBandwidth();
	
	/**
	 * bandwidth of peak energy
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param bandwidth the bandwidth
	 */
	public DataNode setBandwidth(IDataset bandwidth);

	/**
	 * bandwidth of peak energy
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBandwidthScalar();

	/**
	 * bandwidth of peak energy
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param bandwidth the bandwidth
	 */
	public DataNode setBandwidthScalar(double bandwidth);

	/**
	 * harmonic number of peak
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getHarmonic();
	
	/**
	 * harmonic number of peak
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param harmonic the harmonic
	 */
	public DataNode setHarmonic(IDataset harmonic);

	/**
	 * harmonic number of peak
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getHarmonicScalar();

	/**
	 * harmonic number of peak
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param harmonic the harmonic
	 */
	public DataNode setHarmonicScalar(long harmonic);

	/**
	 * spectrum of insertion device
	 * 
	 * @return  the value.
	 */
	public NXdata getSpectrum();
	
	/**
	 * spectrum of insertion device
	 * 
	 * @param spectrum the spectrum
	 */
	public void setSpectrum(NXdata spectrum);

	/**
	 * "Engineering" position of insertion device
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * "Engineering" position of insertion device
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * "Engineering" position of insertion device</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public NXgeometry getGeometry(String name);
	
	/**
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * "Engineering" position of insertion device</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param geometry the value to set
	 */
	public void setGeometry(String name, NXgeometry geometry);
	
	/**
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li>
	 * "Engineering" position of insertion device</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * "Engineering" position of insertion device</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

}
