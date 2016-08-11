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
 * Parameters for controlling external conditions
 * 
 * @version 1.0
 */
public interface NXenvironment extends NXobject {

	public static final String NX_NAME = "name";
	public static final String NX_SHORT_NAME = "short_name";
	public static final String NX_TYPE = "type";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_PROGRAM = "program";
	/**
	 * Apparatus identification code/model number; e.g. OC100 011
	 * 
	 * @return  the value.
	 */
	public IDataset getName();
	
	/**
	 * Apparatus identification code/model number; e.g. OC100 011
	 * 
	 * @param name the name
	 */
	public DataNode setName(IDataset name);

	/**
	 * Apparatus identification code/model number; e.g. OC100 011
	 * 
	 * @return  the value.
	 */
	public String getNameScalar();

	/**
	 * Apparatus identification code/model number; e.g. OC100 011
	 * 
	 * @param name the name
	 */
	public DataNode setNameScalar(String name);

	/**
	 * Alternative short name, perhaps for dashboard display like a present Seblock name
	 * 
	 * @return  the value.
	 */
	public IDataset getShort_name();
	
	/**
	 * Alternative short name, perhaps for dashboard display like a present Seblock name
	 * 
	 * @param short_name the short_name
	 */
	public DataNode setShort_name(IDataset short_name);

	/**
	 * Alternative short name, perhaps for dashboard display like a present Seblock name
	 * 
	 * @return  the value.
	 */
	public String getShort_nameScalar();

	/**
	 * Alternative short name, perhaps for dashboard display like a present Seblock name
	 * 
	 * @param short_name the short_name
	 */
	public DataNode setShort_nameScalar(String short_name);

	/**
	 * Type of apparatus. This could be the SE codes in scheduling database; e.g. OC/100
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * Type of apparatus. This could be the SE codes in scheduling database; e.g. OC/100
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * Type of apparatus. This could be the SE codes in scheduling database; e.g. OC/100
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * Type of apparatus. This could be the SE codes in scheduling database; e.g. OC/100
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * Description of the apparatus; e.g. 100mm bore orange cryostat with Roots pump
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * Description of the apparatus; e.g. 100mm bore orange cryostat with Roots pump
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * Description of the apparatus; e.g. 100mm bore orange cryostat with Roots pump
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * Description of the apparatus; e.g. 100mm bore orange cryostat with Roots pump
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * Program controlling the apparatus; e.g. LabView VI name
	 * 
	 * @return  the value.
	 */
	public IDataset getProgram();
	
	/**
	 * Program controlling the apparatus; e.g. LabView VI name
	 * 
	 * @param program the program
	 */
	public DataNode setProgram(IDataset program);

	/**
	 * Program controlling the apparatus; e.g. LabView VI name
	 * 
	 * @return  the value.
	 */
	public String getProgramScalar();

	/**
	 * Program controlling the apparatus; e.g. LabView VI name
	 * 
	 * @param program the program
	 */
	public DataNode setProgramScalar(String program);

	/**
	 * The position and orientation of the apparatus
	 * 
	 * @return  the value.
	 */
	public NXgeometry getPosition();
	
	/**
	 * The position and orientation of the apparatus
	 * 
	 * @param position the position
	 */
	public void setPosition(NXgeometry position);

	/**
	 * Additional information, LabView logs, digital photographs, etc
	 * 
	 * @return  the value.
	 */
	public NXnote getNote();
	
	/**
	 * Additional information, LabView logs, digital photographs, etc
	 * 
	 * @param note the note
	 */
	public void setNote(NXnote note);
  
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
	 * Set a NXnote node by name:
	 * <ul>
	 * <li>
	 * Additional information, LabView logs, digital photographs, etc</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param note the value to set
	 */
	public void setNote(String name, NXnote note);
	
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
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Additional information, LabView logs, digital photographs, etc</li>
	 * </ul>
	 * 
	 * @param note the child nodes to add 
	 */
	
	public void setAllNote(Map<String, NXnote> note);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXsensor getSensor();
	
	/**
	 * 
	 * @param sensor the sensor
	 */
	public void setSensor(NXsensor sensor);
  
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
	 * Set a NXsensor node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param sensor the value to set
	 */
	public void setSensor(String name, NXsensor sensor);
	
	/**
	 * Get all NXsensor nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsensor for that node.
	 */
	public Map<String, NXsensor> getAllSensor();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param sensor the child nodes to add 
	 */
	
	public void setAllSensor(Map<String, NXsensor> sensor);
	

}
