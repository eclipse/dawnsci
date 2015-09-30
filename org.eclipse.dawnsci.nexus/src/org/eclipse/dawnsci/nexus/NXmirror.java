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
 * Template of a beamline mirror or supermirror.
 * 
 * @version 1.0
 */
public interface NXmirror extends NXobject {

	/**
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public NXgeometry getGeometry(String name);
	
	/**
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>single</b> 
	 * mirror with a single material as a reflecting surface</li>
	 * <li><b>multi</b> 
	 * mirror with stacked, multiple layers as a reflecting surface</li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();

	/**
	 * description of this mirror
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_angle();

	/**
	 * Reflectivity as function of wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getReflectivity();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBend_angle_x();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBend_angle_y();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>vacuum</b> </li>
	 * <li><b>helium</b> </li>
	 * <li><b>argon</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getInterior_atmosphere();

	/**
	 * external material outside substrate
	 * 
	 * @return  the value.
	 */
	public IDataset getExternal_material();

	/**
	 * The m value for a supermirror, which defines the supermirror
	 * regime in multiples of the critical angle of Nickel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getM_value();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_material();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_density();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_thickness();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getCoating_material();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_roughness();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCoating_roughness();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getEven_layer_material();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEven_layer_density();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getOdd_layer_material();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOdd_layer_density();

	/**
	 * An array describing the thickness of each layer
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLayer_thickness();

	/**
	 * A NXshape group describing the shape of the mirror
	 * 
	 * @return  the value.
	 */
	public NXshape getShape();

	/**
	 * Numerical description of the surface figure of the mirror.
	 * 
	 * @return  the value.
	 */
	public NXdata getFigure_data();

}
