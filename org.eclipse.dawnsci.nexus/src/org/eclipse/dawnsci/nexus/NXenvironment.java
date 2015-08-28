/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * This class describes an external condition applied to the sample
 * 
 * @version 1.0
 */
public interface NXenvironment extends NXobject {

	/**
	 * Apparatus identification code/model number; e.g. OC100 011
	 * 
	 * @return  the value.
	 */
	public IDataset getName();

	/**
	 * Alternative short name, perhaps for dashboard display like a present Seblock name
	 * 
	 * @return  the value.
	 */
	public IDataset getShort_name();

	/**
	 * Type of apparatus. This could be the SE codes in scheduling database; e.g. OC/100
	 * 
	 * @return  the value.
	 */
	public IDataset getType();

	/**
	 * Description of the apparatus; e.g. 100mm bore orange cryostat with Roots pump
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * Program controlling the apparatus; e.g. LabView VI name
	 * 
	 * @return  the value.
	 */
	public IDataset getProgram();

	/**
	 * The position and orientation of the apparatus
	 * 
	 * @return  the value.
	 */
	public NXgeometry getPosition();

	/**
	 * Additional information, LabView logs, digital photographs, etc
	 * 
	 * @return  the value.
	 */
	public NXnote getNote();
  
	/**
	 * Get a NXnote node by name:
	 * <ul>
	 * <li>
	 * Additional information, LabView logs, digital photographs, etc</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXnote for that node.
	 */
	public NXnote getNote(String name);
	
	/**
	 * Get all NXnote nodes:
	 * <ul>
	 * <li>
	 * Additional information, LabView logs, digital photographs, etc</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXnote for that node.
	 */
	public Map<String, NXnote> getAllNote();

	/**
	 * 
	 * @return  the value.
	 */
	public NXsensor getSensor();
  
	/**
	 * Get a NXsensor node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXsensor for that node.
	 */
	public NXsensor getSensor(String name);
	
	/**
	 * Get all NXsensor nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsensor for that node.
	 */
	public Map<String, NXsensor> getAllSensor();

}
