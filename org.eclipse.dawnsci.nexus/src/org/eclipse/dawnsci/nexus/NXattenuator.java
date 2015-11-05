/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Description of a device that reduces the intensity of a beam by attenuation.
 * If uncertain whether to use ``NXfilter`` (band-pass filter)
 * or ``NXattenuator`` (reduces beam intensity), then choose
 * ``NXattenuator``.
 * 
 * @version 1.0
 */
public interface NXattenuator extends NXobject {

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
	 * Distance from sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDistanceScalar();

	/**
	 * Type or composition of attenuator, e.g. polythene
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * Type or composition of attenuator, e.g. polythene
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Thickness of attenuator along beam direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThickness();	

	/**
	 * Thickness of attenuator along beam direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getThicknessScalar();

	/**
	 * Scattering cross section (coherent+incoherent)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CROSS_SECTION
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getScattering_cross_section();	

	/**
	 * Scattering cross section (coherent+incoherent)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CROSS_SECTION
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getScattering_cross_sectionScalar();

	/**
	 * Absorption cross section
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CROSS_SECTION
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAbsorption_cross_section();	

	/**
	 * Absorption cross section
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CROSS_SECTION
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getAbsorption_cross_sectionScalar();

	/**
	 * The nominal amount of the beam that gets through
	 * (transmitted intensity)/(incident intensity)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAttenuator_transmission();	

	/**
	 * The nominal amount of the beam that gets through
	 * (transmitted intensity)/(incident intensity)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getAttenuator_transmissionScalar();

	/**
	 * In or out or moving of the beam
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>in</b> </li>
	 * <li><b>out</b> </li>
	 * <li><b>moving</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getStatus();	

	/**
	 * In or out or moving of the beam
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>in</b> </li>
	 * <li><b>out</b> </li>
	 * <li><b>moving</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getStatusScalar();

	/**
	 * time stamp for this observation
	 * 
	 * @return  the value.
	 */
	public Date getStatusAttributeTime();	

}
