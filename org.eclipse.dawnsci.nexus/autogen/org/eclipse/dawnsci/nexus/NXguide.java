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
 * A neutron optical element to direct the path of the beam.
 * :ref:`NXguide` is used by neutron instruments to describe
 * a guide consists of several mirrors building a shape through which
 * neutrons can be guided or directed. The simplest such form is box shaped
 * although elliptical guides are gaining in popularity.
 * The individual parts of a guide usually have common characteristics
 * but there are cases where they are different.
 * For example, a neutron guide might consist of 2 or 4 coated walls or
 * a supermirror bender with multiple, coated vanes.
 * To describe polarizing supermirrors such as used in neutron reflection,
 * it may be necessary to revise this definition of :ref:`NXguide`
 * to include :ref:`NXpolarizer` and/or :ref:`NXmirror`.
 * When even greater complexity exists in the definition of what
 * constitutes a *guide*, it has been suggested that :ref:`NXguide`
 * be redefined as a :ref:`NXcollection` of :ref:`NXmirror` each
 * having their own :ref:`NXgeometry` describing their location(s).
 * For the more general case when describing mirrors, consider using
 * :ref:`NXmirror`.
 * NOTE: The NeXus International Advisory Committee welcomes
 * comments for revision and improvement of
 * this definition of :ref:`NXguide`.
 * <p><b>Symbols:</b> <ul>
 * <li><b>nsurf</b> 
 * number of reflecting surfaces</li>
 * <li><b>nwl</b> 
 * number of wavelengths</li></ul></p>
 * 
 * @version 1.0
 */
public interface NXguide extends NXobject {

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_INCIDENT_ANGLE = "incident_angle";
	public static final String NX_BEND_ANGLE_X = "bend_angle_x";
	public static final String NX_BEND_ANGLE_Y = "bend_angle_y";
	public static final String NX_INTERIOR_ATMOSPHERE = "interior_atmosphere";
	public static final String NX_EXTERNAL_MATERIAL = "external_material";
	public static final String NX_M_VALUE = "m_value";
	public static final String NX_SUBSTRATE_MATERIAL = "substrate_material";
	public static final String NX_SUBSTRATE_THICKNESS = "substrate_thickness";
	public static final String NX_COATING_MATERIAL = "coating_material";
	public static final String NX_SUBSTRATE_ROUGHNESS = "substrate_roughness";
	public static final String NX_COATING_ROUGHNESS = "coating_roughness";
	public static final String NX_NUMBER_SECTIONS = "number_sections";
	/**
	 * TODO: Explain what this NXgeometry group means. What is intended here?
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * TODO: Explain what this NXgeometry group means. What is intended here?
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
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
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * TODO: Explain what this NXgeometry group means. What is intended here?</li>
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
	 * TODO: Explain what this NXgeometry group means. What is intended here?</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * TODO: Explain what this NXgeometry group means. What is intended here?</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

	/**
	 * A description of this particular instance of ``NXguide``.
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * A description of this particular instance of ``NXguide``.
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * A description of this particular instance of ``NXguide``.
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * A description of this particular instance of ``NXguide``.
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

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
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param incident_angle the incident_angle
	 */
	public DataNode setIncident_angle(IDataset incident_angle);

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getIncident_angleScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param incident_angle the incident_angle
	 */
	public DataNode setIncident_angleScalar(double incident_angle);

	/**
	 * Reflectivity as function of reflecting surface and wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getReflectivity();
	
	/**
	 * Reflectivity as function of reflecting surface and wavelength
	 * 
	 * @param reflectivity the reflectivity
	 */
	public void setReflectivity(NXdata reflectivity);

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
	 * @param bend_angle_x the bend_angle_x
	 */
	public DataNode setBend_angle_x(IDataset bend_angle_x);

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBend_angle_xScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param bend_angle_x the bend_angle_x
	 */
	public DataNode setBend_angle_xScalar(double bend_angle_x);

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
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param bend_angle_y the bend_angle_y
	 */
	public DataNode setBend_angle_y(IDataset bend_angle_y);

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBend_angle_yScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param bend_angle_y the bend_angle_y
	 */
	public DataNode setBend_angle_yScalar(double bend_angle_y);

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
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>vacuum</b> </li>
	 * <li><b>helium</b> </li>
	 * <li><b>argon</b> </li></ul></p>
	 * </p>
	 * 
	 * @param interior_atmosphere the interior_atmosphere
	 */
	public DataNode setInterior_atmosphere(IDataset interior_atmosphere);

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
	public String getInterior_atmosphereScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>vacuum</b> </li>
	 * <li><b>helium</b> </li>
	 * <li><b>argon</b> </li></ul></p>
	 * </p>
	 * 
	 * @param interior_atmosphere the interior_atmosphere
	 */
	public DataNode setInterior_atmosphereScalar(String interior_atmosphere);

	/**
	 * external material outside substrate
	 * 
	 * @return  the value.
	 */
	public IDataset getExternal_material();
	
	/**
	 * external material outside substrate
	 * 
	 * @param external_material the external_material
	 */
	public DataNode setExternal_material(IDataset external_material);

	/**
	 * external material outside substrate
	 * 
	 * @return  the value.
	 */
	public String getExternal_materialScalar();

	/**
	 * external material outside substrate
	 * 
	 * @param external_material the external_material
	 */
	public DataNode setExternal_materialScalar(String external_material);

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
	 * The ``m`` value for a supermirror, which defines the supermirror
	 * regime in multiples of the critical angle of Nickel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param m_value the m_value
	 */
	public DataNode setM_value(IDataset m_value);

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
	public double getM_valueScalar();

	/**
	 * The ``m`` value for a supermirror, which defines the supermirror
	 * regime in multiples of the critical angle of Nickel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param m_value the m_value
	 */
	public DataNode setM_valueScalar(double m_value);

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
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param substrate_material the substrate_material
	 */
	public DataNode setSubstrate_material(IDataset substrate_material);

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSubstrate_materialScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param substrate_material the substrate_material
	 */
	public DataNode setSubstrate_materialScalar(double substrate_material);

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
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param substrate_thickness the substrate_thickness
	 */
	public DataNode setSubstrate_thickness(IDataset substrate_thickness);

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
	public double getSubstrate_thicknessScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param substrate_thickness the substrate_thickness
	 */
	public DataNode setSubstrate_thicknessScalar(double substrate_thickness);

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
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param coating_material the coating_material
	 */
	public DataNode setCoating_material(IDataset coating_material);

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCoating_materialScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param coating_material the coating_material
	 */
	public DataNode setCoating_materialScalar(double coating_material);

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
	 * @param substrate_roughness the substrate_roughness
	 */
	public DataNode setSubstrate_roughness(IDataset substrate_roughness);

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
	public double getSubstrate_roughnessScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param substrate_roughness the substrate_roughness
	 */
	public DataNode setSubstrate_roughnessScalar(double substrate_roughness);

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
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param coating_roughness the coating_roughness
	 */
	public DataNode setCoating_roughness(IDataset coating_roughness);

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
	public double getCoating_roughnessScalar();

	/**
	 * TODO: documentation needed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param coating_roughness the coating_roughness
	 */
	public DataNode setCoating_roughnessScalar(double coating_roughness);

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
	
	/**
	 * number of substrate sections (also called ``nsurf`` as an
	 * index in the ``NXguide`` specification)
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param number_sections the number_sections
	 */
	public DataNode setNumber_sections(IDataset number_sections);

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
	public long getNumber_sectionsScalar();

	/**
	 * number of substrate sections (also called ``nsurf`` as an
	 * index in the ``NXguide`` specification)
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param number_sections the number_sections
	 */
	public DataNode setNumber_sectionsScalar(long number_sections);

}
