/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-01-13T18:08:19.722Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * This is the description for the general spatial location
 * of a component - it is used by the NXgeometry class
 * 
 * @version 1.0
 */
public interface NXtranslation extends NXobject {

	public static final String NX_DISTANCES = "distances";
	/**
	 * Link to other object if we are relative, else absent
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * Link to other object if we are relative, else absent
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);

	/**
	 * (x,y,z)
	 * This field describes the lateral movement of a component.
	 * The pair of groups NXtranslation and NXorientation together
	 * describe the position of a component.
	 * For absolute position, the origin is the scattering center (where a perfectly
	 * aligned sample would be) with the z-axis pointing downstream and the y-axis
	 * pointing gravitationally up. For a relative position the NXtranslation is
	 * taken into account before the NXorientation. The axes are right-handed and
	 * orthonormal.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: numobj; 2: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistances();
	
	/**
	 * (x,y,z)
	 * This field describes the lateral movement of a component.
	 * The pair of groups NXtranslation and NXorientation together
	 * describe the position of a component.
	 * For absolute position, the origin is the scattering center (where a perfectly
	 * aligned sample would be) with the z-axis pointing downstream and the y-axis
	 * pointing gravitationally up. For a relative position the NXtranslation is
	 * taken into account before the NXorientation. The axes are right-handed and
	 * orthonormal.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: numobj; 2: 3;
	 * </p>
	 * 
	 * @param distances the distances
	 */
	public DataNode setDistances(IDataset distances);

	/**
	 * (x,y,z)
	 * This field describes the lateral movement of a component.
	 * The pair of groups NXtranslation and NXorientation together
	 * describe the position of a component.
	 * For absolute position, the origin is the scattering center (where a perfectly
	 * aligned sample would be) with the z-axis pointing downstream and the y-axis
	 * pointing gravitationally up. For a relative position the NXtranslation is
	 * taken into account before the NXorientation. The axes are right-handed and
	 * orthonormal.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: numobj; 2: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDistancesScalar();

	/**
	 * (x,y,z)
	 * This field describes the lateral movement of a component.
	 * The pair of groups NXtranslation and NXorientation together
	 * describe the position of a component.
	 * For absolute position, the origin is the scattering center (where a perfectly
	 * aligned sample would be) with the z-axis pointing downstream and the y-axis
	 * pointing gravitationally up. For a relative position the NXtranslation is
	 * taken into account before the NXorientation. The axes are right-handed and
	 * orthonormal.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: numobj; 2: 3;
	 * </p>
	 * 
	 * @param distances the distances
	 */
	public DataNode setDistancesScalar(double distances);

}
