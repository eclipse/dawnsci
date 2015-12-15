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
import java.util.Map;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;


/**
 * Definition of the root NeXus group.
 * 
 * @version 1.0
 */
public interface NXroot extends NXobject {

	/**
	 * The root of any NeXus data file is an NXroot class
	 * (no other choice is allowed for a valid NeXus data file).
	 * This attribute cements that definition.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>NXroot</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getAttributeNX_class();	

	/**
	 * Date and time file was originally created
	 * 
	 * @return  the value.
	 */
	public String getAttributeFile_time();	

	/**
	 * File name of original NeXus file
	 * 
	 * @return  the value.
	 */
	public String getAttributeFile_name();	

	/**
	 * Date and time of last file change at close
	 * 
	 * @return  the value.
	 */
	public String getAttributeFile_update_time();	

	/**
	 * Version of NeXus API used in writing the file
	 * 
	 * @return  the value.
	 */
	public String getAttributeNeXus_version();	

	/**
	 * Version of NeXus API used in writing the file
	 * 
	 * @return  the value.
	 */
	public String getAttributeHDF_version();	

	/**
	 * Version of NeXus API used in writing the file.
	 * Note this attribute is spelled with uppercase "V",
	 * different than other version attributes.
	 * 
	 * @return  the value.
	 */
	public String getAttributeHDF5_Version();	

	/**
	 * Version of NeXus API used in writing the file
	 * 
	 * @return  the value.
	 */
	public String getAttributeXML_version();	

	/**
	 * facility or program where file originated
	 * 
	 * @return  the value.
	 */
	public String getAttributeCreator();	

	/**
	 * entries
	 * 
	 * @return  the value.
	 */
	public NXentry getEntry();	
  
	/**
	 * Get a NXentry node by name:
	 * <ul>
	 * <li>
	 * entries</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXentry for that node.
	 */
	public NXentry getEntry(String name);
	
	/**
	 * Get all NXentry nodes:
	 * <ul>
	 * <li>
	 * entries</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXentry for that node.
	 */
	public Map<String, NXentry> getAllEntry();

	/**
	 * name of the ''NXentry'' in this file to use for the default plot
	 * 
	 * @return  the value.
	 */
	public String getAttributeDefault();	

}
