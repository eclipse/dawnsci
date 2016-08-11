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
 * legacy class - recommend to use :ref:`NXtransformations` now
 * Description for a general orientation of a component - used by :ref:`NXgeometry`
 * 
 * @version 1.0
 */
public interface NXorientation extends NXobject {

	public static final String NX_VALUE = "value";
	/**
	 * Link to another object if we are using relative positioning, else absent
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * Link to another object if we are using relative positioning, else absent
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
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
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Link to another object if we are using relative positioning, else absent</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param geometry the value to set
	 */
	public void setGeometry(String name, NXgeometry geometry);
	
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
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Link to another object if we are using relative positioning, else absent</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

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
	 * @param value the value
	 */
	public DataNode setValue(IDataset value);

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
	public double getValueScalar();

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
	 * @param value the value
	 */
	public DataNode setValueScalar(double value);

}
