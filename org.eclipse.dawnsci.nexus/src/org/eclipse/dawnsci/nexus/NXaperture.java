/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of a beamline aperture.
 * 
 * @version 1.0
 */
public interface NXaperture extends NXobject {

	/**
	 * location and shape of aperture
	 * .. TODO:
	 * description of terms is poor and leaves much to interpretation
	 * Describe what is meant by translation _here_ and ...
	 * Similar throughout base classes
	 * Some base classes do this much better
	 * Such as where is the gap written?
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * location and shape of aperture
	 * .. TODO:
	 * description of terms is poor and leaves much to interpretation
	 * Describe what is meant by translation _here_ and ...
	 * Similar throughout base classes
	 * Some base classes do this much better
	 * Such as where is the gap written?</li>
	 * <li>
	 * location and shape of each blade</li>
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
	 * location and shape of aperture
	 * .. TODO:
	 * description of terms is poor and leaves much to interpretation
	 * Describe what is meant by translation _here_ and ...
	 * Similar throughout base classes
	 * Some base classes do this much better
	 * Such as where is the gap written?</li>
	 * <li>
	 * location and shape of each blade</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * Absorbing material of the aperture
	 * 
	 * @return  the value.
	 */
	public IDataset getMaterial();

	/**
	 * Description of aperture
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * describe an additional information in a note*
	 * 
	 * @return  the value.
	 */
	public NXnote getNote();
  
	/**
	 * Get a NXnote node by name:
	 * <ul>
	 * <li>
	 * describe an additional information in a note*</li>
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
	 * describe an additional information in a note*</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXnote for that node.
	 */
	public Map<String, NXnote> getAllNote();

}
