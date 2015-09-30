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
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Description of an insertion device, as in a synchrotron.
 * 
 * @version 1.0
 */
public interface NXinsertion_device extends NXobject {

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
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPhase();

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
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMagnetic_wavelength();

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
	 * spectrum of insertion device
	 * 
	 * @return  the value.
	 */
	public NXdata getSpectrum();

	/**
	 * "Engineering" position of insertion device
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
  
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
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li>
	 * "Engineering" position of insertion device</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

}
