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
public interface NXmonochromator extends NXobject {

	public static final String NX_WAVELENGTH = "wavelength";
	public static final String NX_WAVELENGTH_ERROR = "wavelength_error";
	public static final String NX_ENERGY = "energy";
	public static final String NX_ENERGY_ERROR = "energy_error";
	/**
	 * wavelength selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength();
	
	/**
	 * wavelength selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelength(IDataset wavelength);

	/**
	 * wavelength selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWavelengthScalar();

	/**
	 * wavelength selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelengthScalar(double wavelength);

	/**
	 * wavelength standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength_error();
	
	/**
	 * wavelength standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength_error the wavelength_error
	 */
	public DataNode setWavelength_error(IDataset wavelength_error);

	/**
	 * wavelength standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWavelength_errorScalar();

	/**
	 * wavelength standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength_error the wavelength_error
	 */
	public DataNode setWavelength_errorScalar(double wavelength_error);

	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnergy();
	
	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergy(IDataset energy);

	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEnergyScalar();

	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergyScalar(double energy);

	/**
	 * energy standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnergy_error();
	
	/**
	 * energy standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy_error the energy_error
	 */
	public DataNode setEnergy_error(IDataset energy_error);

	/**
	 * energy standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEnergy_errorScalar();

	/**
	 * energy standard deviation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy_error the energy_error
	 */
	public DataNode setEnergy_errorScalar(double energy_error);

	/**
	 * 
	 * @return  the value.
	 */
	public NXdata getDistribution();
	
	/**
	 * 
	 * @param distribution the distribution
	 */
	public void setDistribution(NXdata distribution);

	/**
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);

	/**
	 * Use as many crystals as necessary to describe
	 * 
	 * @return  the value.
	 */
	public NXcrystal getCrystal();
	
	/**
	 * Use as many crystals as necessary to describe
	 * 
	 * @param crystal the crystal
	 */
	public void setCrystal(NXcrystal crystal);
  
	/**
	 * Get a NXcrystal node by name:
	 * <ul>
	 * <li>
	 * Use as many crystals as necessary to describe</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcrystal for that node.
	 */
	public NXcrystal getCrystal(String name);
	
	/**
	 * Set a NXcrystal node by name:
	 * <ul>
	 * <li>
	 * Use as many crystals as necessary to describe</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param crystal the value to set
	 */
	public void setCrystal(String name, NXcrystal crystal);
	
	/**
	 * Get all NXcrystal nodes:
	 * <ul>
	 * <li>
	 * Use as many crystals as necessary to describe</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcrystal for that node.
	 */
	public Map<String, NXcrystal> getAllCrystal();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Use as many crystals as necessary to describe</li>
	 * </ul>
	 * 
	 * @param crystal the child nodes to add 
	 */
	
	public void setAllCrystal(Map<String, NXcrystal> crystal);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXvelocity_selector getVelocity_selector();
	
	/**
	 * 
	 * @param velocity_selector the velocity_selector
	 */
	public void setVelocity_selector(NXvelocity_selector velocity_selector);
  
	/**
	 * Get a NXvelocity_selector node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXvelocity_selector for that node.
	 */
	public NXvelocity_selector getVelocity_selector(String name);
	
	/**
	 * Set a NXvelocity_selector node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param velocity_selector the value to set
	 */
	public void setVelocity_selector(String name, NXvelocity_selector velocity_selector);
	
	/**
	 * Get all NXvelocity_selector nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXvelocity_selector for that node.
	 */
	public Map<String, NXvelocity_selector> getAllVelocity_selector();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param velocity_selector the child nodes to add 
	 */
	
	public void setAllVelocity_selector(Map<String, NXvelocity_selector> velocity_selector);
	

	/**
	 * For diffraction grating based monochromators
	 * 
	 * @return  the value.
	 */
	public NXgrating getGrating();
	
	/**
	 * For diffraction grating based monochromators
	 * 
	 * @param grating the grating
	 */
	public void setGrating(NXgrating grating);
  
	/**
	 * Get a NXgrating node by name:
	 * <ul>
	 * <li>
	 * For diffraction grating based monochromators</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgrating for that node.
	 */
	public NXgrating getGrating(String name);
	
	/**
	 * Set a NXgrating node by name:
	 * <ul>
	 * <li>
	 * For diffraction grating based monochromators</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param grating the value to set
	 */
	public void setGrating(String name, NXgrating grating);
	
	/**
	 * Get all NXgrating nodes:
	 * <ul>
	 * <li>
	 * For diffraction grating based monochromators</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgrating for that node.
	 */
	public Map<String, NXgrating> getAllGrating();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * For diffraction grating based monochromators</li>
	 * </ul>
	 * 
	 * @param grating the child nodes to add 
	 */
	
	public void setAllGrating(Map<String, NXgrating> grating);
	

}
