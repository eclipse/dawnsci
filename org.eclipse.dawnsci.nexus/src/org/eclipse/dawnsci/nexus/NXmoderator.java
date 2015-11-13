/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * This is the description for a general moderator
 * 
 * @version 1.0
 */
public interface NXmoderator extends NXobject {

	/**
	 * "Engineering" position of moderator
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * "Engineering" position of moderator</li>
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
	 * "Engineering" position of moderator</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * Effective distance as seen by measuring radiation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();	

	/**
	 * Effective distance as seen by measuring radiation
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDistanceScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>H20</b> </li>
	 * <li><b>D20</b> </li>
	 * <li><b>Liquid H2</b> </li>
	 * <li><b>Liquid CH4</b> </li>
	 * <li><b>Liquid D2</b> </li>
	 * <li><b>Solid D2</b> </li>
	 * <li><b>C</b> </li>
	 * <li><b>Solid CH4</b> </li>
	 * <li><b>Solid H2</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>H20</b> </li>
	 * <li><b>D20</b> </li>
	 * <li><b>Liquid H2</b> </li>
	 * <li><b>Liquid CH4</b> </li>
	 * <li><b>Liquid D2</b> </li>
	 * <li><b>Solid D2</b> </li>
	 * <li><b>C</b> </li>
	 * <li><b>Solid CH4</b> </li>
	 * <li><b>Solid H2</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPoison_depth();	

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getPoison_depthScalar();

	/**
	 * whether the moderator is coupled
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCoupled();	

	/**
	 * whether the moderator is coupled
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getCoupledScalar();

	/**
	 * The material used for coupling. Usually Cd.
	 * 
	 * @return  the value.
	 */
	public IDataset getCoupling_material();	

	/**
	 * The material used for coupling. Usually Cd.
	 * 
	 * @return  the value
	 */
	 public String getCoupling_materialScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Gd</b> </li>
	 * <li><b>Cd</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPoison_material();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Gd</b> </li>
	 * <li><b>Cd</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getPoison_materialScalar();

	/**
	 * average/nominal moderator temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTemperature();	

	/**
	 * average/nominal moderator temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getTemperatureScalar();

	/**
	 * log file of moderator temperature
	 * 
	 * @return  the value.
	 */
	public NXlog getTemperature_log();	

	/**
	 * moderator pulse shape
	 * 
	 * @return  the value.
	 */
	public NXdata getPulse_shape();	

}
