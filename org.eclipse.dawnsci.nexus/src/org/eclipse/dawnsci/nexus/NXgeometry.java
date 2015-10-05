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

/**
 * It is recommended that instances of NXgeometry be converted to
 * use :ref:`NXtransformations`.
 * This is the description for a general position of a component.
 * It is recommended to name an instance of NXgeometry as "geometry"
 * to aid in the use of the definition in simulation codes such as McStas.
 * Also, in HDF, linked items must share the same name.
 * However, it might not be possible or practical in all situations.
 * 
 * @version 1.0
 * @deprecated as decided at 2014 NIAC meeting, convert to use :ref:`NXtransformations`
 */
@Deprecated
public interface NXgeometry extends NXobject {

	/**
	 * shape/size information of component
	 * 
	 * @return  the value.
	 */
	public NXshape getShape();
  
	/**
	 * Get a NXshape node by name:
	 * <ul>
	 * <li>
	 * shape/size information of component</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXshape for that node.
	 */
	public NXshape getShape(String name);
	
	/**
	 * Get all NXshape nodes:
	 * <ul>
	 * <li>
	 * shape/size information of component</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXshape for that node.
	 */
	public Map<String, NXshape> getAllShape();

	/**
	 * translation of component
	 * 
	 * @return  the value.
	 */
	public NXtranslation getTranslation();
  
	/**
	 * Get a NXtranslation node by name:
	 * <ul>
	 * <li>
	 * translation of component</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXtranslation for that node.
	 */
	public NXtranslation getTranslation(String name);
	
	/**
	 * Get all NXtranslation nodes:
	 * <ul>
	 * <li>
	 * translation of component</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXtranslation for that node.
	 */
	public Map<String, NXtranslation> getAllTranslation();

	/**
	 * orientation of component
	 * 
	 * @return  the value.
	 */
	public NXorientation getOrientation();
  
	/**
	 * Get a NXorientation node by name:
	 * <ul>
	 * <li>
	 * orientation of component</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXorientation for that node.
	 */
	public NXorientation getOrientation(String name);
	
	/**
	 * Get all NXorientation nodes:
	 * <ul>
	 * <li>
	 * orientation of component</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXorientation for that node.
	 */
	public Map<String, NXorientation> getAllOrientation();

	/**
	 * Optional description/label. Probably only present if we are
	 * an additional reference point for components rather than the
	 * location of a real component.
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * Position of the component along the beam path. The sample is at 0, components upstream
	 * have negative component_index, components downstream have positive
	 * component_index.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getComponent_index();

}
