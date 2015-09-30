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
 * This is the description for a general orientation of a component - it is used by the
 * NXgeometry class
 * 
 * @version 1.0
 */
public interface NXorientation extends NXobject {

	/**
	 * Link to another object if we are using relative positioning, else absent
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Link to another object if we are using relative positioning, else absent</li>
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
	 * Link to another object if we are using relative positioning, else absent</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * The orientation information is stored as direction cosines. The direction cosines will
	 * be between the local coordinate directions and the reference directions (to origin or
	 * relative NXgeometry). Calling the local unit vectors (x',y',z') and the reference unit
	 * vectors (x,y,z) the six numbers will be [x' dot x, x' dot y, x' dot z, y' dot x, y' dot
	 * y, y' dot z] where "dot" is the scalar dot product (cosine of the angle between the unit
	 * vectors). The unit vectors in both the local and reference coordinates are right-handed
	 * and orthonormal.
	 * The pair of groups NXtranslation and NXorientation together
	 * describe the position of a component.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: numobj; 2: 6;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue();

}
