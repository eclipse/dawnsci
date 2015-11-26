/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-17T13:07:37.011Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of a beamline collimator.
 * 
 * @version 1.0
 */
public interface NXcollimator extends NXobject {

	/**
	 * position, shape and size
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * position, shape and size</li>
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
	 * position, shape and size</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Soller</b> </li>
	 * <li><b>radial</b> </li>
	 * <li><b>oscillating</b> </li>
	 * <li><b>honeycomb</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Soller</b> </li>
	 * <li><b>radial</b> </li>
	 * <li><b>oscillating</b> </li>
	 * <li><b>honeycomb</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Angular divergence of Soller collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSoller_angle();	

	/**
	 * Angular divergence of Soller collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSoller_angleScalar();

	/**
	 * divergence of collimator in local x direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_x();	

	/**
	 * divergence of collimator in local x direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDivergence_xScalar();

	/**
	 * divergence of collimator in local y direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_y();	

	/**
	 * divergence of collimator in local y direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDivergence_yScalar();

	/**
	 * Frequency of oscillating collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFrequency();	

	/**
	 * Frequency of oscillating collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFrequencyScalar();

	/**
	 * Log of frequency
	 * 
	 * @return  the value.
	 */
	public NXlog getFrequency_log();	

	/**
	 * blade thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBlade_thickness();	

	/**
	 * blade thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBlade_thicknessScalar();

	/**
	 * blade spacing
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBlade_spacing();	

	/**
	 * blade spacing
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBlade_spacingScalar();

	/**
	 * name of absorbing material
	 * 
	 * @return  the value.
	 */
	public IDataset getAbsorbing_material();	

	/**
	 * name of absorbing material
	 * 
	 * @return  the value
	 */
	 public String getAbsorbing_materialScalar();

	/**
	 * name of transmitting material
	 * 
	 * @return  the value.
	 */
	public IDataset getTransmitting_material();	

	/**
	 * name of transmitting material
	 * 
	 * @return  the value
	 */
	 public String getTransmitting_materialScalar();

}
