/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

/**
 * This is a definition for reflection data from diffraction experiments
 * <p><b>Symbols:</b> <ul>
 * <li><b>n</b> 
 * number of reflections</li>
 * <li><b>m</b> 
 * number of experiments</li></ul></p>
 * 
 * @version 1.0
 */
public interface NXreflections extends NXobject {

	/**
	 * 
	 * @return  the value.
	 */
	public NXentry getEntry();
	
	/**
	 * 
	 * @param entry the entry
	 */
	public void setEntry(NXentry entry);
  
	/**
	 * Get a NXentry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXentry for that node.
	 */
	public NXentry getEntry(String name);
	
	/**
	 * Set a NXentry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param entry the value to set
	 */
	public void setEntry(String name, NXentry entry);
	
	/**
	 * Get all NXentry nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXentry for that node.
	 */
	public Map<String, NXentry> getAllEntry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param entry the child nodes to add 
	 */
	
	public void setAllEntry(Map<String, NXentry> entry);
	

}
