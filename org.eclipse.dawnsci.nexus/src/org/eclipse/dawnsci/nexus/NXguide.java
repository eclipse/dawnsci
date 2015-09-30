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
 * ``NXguide`` is used by neutron instruments to describe
 * a guide consists of several mirrors building a shape through which
 * neutrons can be guided or directed. The simplest such form is box shaped
 * although elliptical guides are gaining in popularity.
 * The individual parts of a guide usually have common characteristics
 * but there are cases where they are different.
 * For example, a neutron guide might consist of 2 or 4 coated walls or
 * a supermirror bender with multiple, coated vanes.
 * To describe polarizing supermirrors such as used in neutron reflection,
 * it may be necessary to revise this definition of ``NXguide``
 * to include ``NXpolarizer`` and/or ``NXmirror``.
 * When even greater complexity exists in the definition of what
 * constitutes a *guide*, it has been suggested that ``NXguide``
 * be redefined as a ``NXcollection`` of
 * ``NXmirrors`` each having their own
 * ``NXgeometries`` describing their location(s).
 * For the more general case when describing mirrors, consider using
 * ``NXmirror``.
 * NOTE: The NeXus International Advisory Committee welcomes
 * comments for revision and improvement of
 * this definition of ``NXguide``.
 * <p><b>Symbols:</b> <ul>
 * <li><b>nsurf</b> 
 * number of reflecting surfaces</li>
 * <li><b>nwl</b> 
 * number of wavelengths</li></ul></p>
 * 
 * @version 1.0
 */
public interface NXguide extends NXobject {

	/**
	 * TODO: Explain what this NXgeometry group means. What is intended here?
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * TODO: Explain what this NXgeometry group means. What is intended here?</li>
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
	 * TODO: Explain what this NXgeometry group means. What is intended here?</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * A description of this particular instance of ``NXguide``.
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIncident_angle();

	/**
	 * Reflectivity as function of reflecting surface and wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getReflectivity();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBend_angle_x();

	/**
	 * TODO: documentation needed
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
	 * The ``m`` value for a supermirror, which defines the supermirror
	 * regime in multiples of the critical angle of Nickel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getM_value();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_material();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_thickness();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCoating_material();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_roughness();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCoating_roughness();

	/**
	 * number of substrate sections (also called ``nsurf`` as an
	 * index in the ``NXguide`` specification)
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNumber_sections();

}
