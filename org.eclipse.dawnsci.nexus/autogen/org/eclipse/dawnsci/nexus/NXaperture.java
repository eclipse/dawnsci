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
 * A beamline aperture.
 * 
 * @version 1.0
 */
public interface NXaperture extends NXobject {

	public static final String NX_MATERIAL = "material";
	public static final String NX_DESCRIPTION = "description";
	/**
	 * location and shape of aperture
	 * .. TODO: documentation needs improvement, contributions welcome
	 * * description of terms is poor and leaves much to interpretation
	 * * Describe what is meant by translation _here_ and ...
	 * * Similar throughout base classes
	 * * Some base classes do this much better
	 * * Such as where is the gap written?
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * location and shape of aperture
	 * .. TODO: documentation needs improvement, contributions welcome
	 * * description of terms is poor and leaves much to interpretation
	 * * Describe what is meant by translation _here_ and ...
	 * * Similar throughout base classes
	 * * Some base classes do this much better
	 * * Such as where is the gap written?
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * location and shape of aperture
	 * .. TODO: documentation needs improvement, contributions welcome
	 * * description of terms is poor and leaves much to interpretation
	 * * Describe what is meant by translation _here_ and ...
	 * * Similar throughout base classes
	 * * Some base classes do this much better
	 * * Such as where is the gap written?</li>
	 * <li>
	 * location and shape of each blade</li>
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
	 * location and shape of aperture
	 * .. TODO: documentation needs improvement, contributions welcome
	 * * description of terms is poor and leaves much to interpretation
	 * * Describe what is meant by translation _here_ and ...
	 * * Similar throughout base classes
	 * * Some base classes do this much better
	 * * Such as where is the gap written?</li>
	 * <li>
	 * location and shape of each blade</li>
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
	 * location and shape of aperture
	 * .. TODO: documentation needs improvement, contributions welcome
	 * * description of terms is poor and leaves much to interpretation
	 * * Describe what is meant by translation _here_ and ...
	 * * Similar throughout base classes
	 * * Some base classes do this much better
	 * * Such as where is the gap written?</li>
	 * <li>
	 * location and shape of each blade</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * location and shape of aperture
	 * .. TODO: documentation needs improvement, contributions welcome
	 * * description of terms is poor and leaves much to interpretation
	 * * Describe what is meant by translation _here_ and ...
	 * * Similar throughout base classes
	 * * Some base classes do this much better
	 * * Such as where is the gap written?</li>
	 * <li>
	 * location and shape of each blade</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

	/**
	 * Absorbing material of the aperture
	 * 
	 * @return  the value.
	 */
	public IDataset getMaterial();
	
	/**
	 * Absorbing material of the aperture
	 * 
	 * @param material the material
	 */
	public DataNode setMaterial(IDataset material);

	/**
	 * Absorbing material of the aperture
	 * 
	 * @return  the value.
	 */
	public String getMaterialScalar();

	/**
	 * Absorbing material of the aperture
	 * 
	 * @param material the material
	 */
	public DataNode setMaterialScalar(String material);

	/**
	 * Description of aperture
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * Description of aperture
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * Description of aperture
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * Description of aperture
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * describe an additional information in a note*
	 * 
	 * @return  the value.
	 */
	public NXnote getNote();
	
	/**
	 * describe an additional information in a note*
	 * 
	 * @param note the note
	 */
	public void setNote(NXnote note);
  
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
	 * Set a NXnote node by name:
	 * <ul>
	 * <li>
	 * describe an additional information in a note*</li>
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
	 * describe an additional information in a note*</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXnote for that node.
	 */
	public Map<String, NXnote> getAllNote();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * describe an additional information in a note*</li>
	 * </ul>
	 * 
	 * @param note the child nodes to add 
	 */
	
	public void setAllNote(Map<String, NXnote> note);
	

}
