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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
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
 * 
 * @version 1.0
 */
public class NXguideImpl extends NXobjectImpl implements NXguide {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_DATA);

	public NXguideImpl() {
		super();
	}

	public NXguideImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXguide.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_GUIDE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	@Override
	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}
	
	@Override
	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	@Override
	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getIncident_angle() {
		return getDataset(NX_INCIDENT_ANGLE);
	}

	@Override
	public double getIncident_angleScalar() {
		return getDouble(NX_INCIDENT_ANGLE);
	}

	@Override
	public DataNode setIncident_angle(IDataset incident_angle) {
		return setDataset(NX_INCIDENT_ANGLE, incident_angle);
	}

	@Override
	public DataNode setIncident_angleScalar(double incident_angle) {
		return setField(NX_INCIDENT_ANGLE, incident_angle);
	}

	@Override
	public NXdata getReflectivity() {
		return getChild("reflectivity", NXdata.class);
	}

	@Override
	public void setReflectivity(NXdata reflectivity) {
		putChild("reflectivity", reflectivity);
	}

	@Override
	public IDataset getBend_angle_x() {
		return getDataset(NX_BEND_ANGLE_X);
	}

	@Override
	public double getBend_angle_xScalar() {
		return getDouble(NX_BEND_ANGLE_X);
	}

	@Override
	public DataNode setBend_angle_x(IDataset bend_angle_x) {
		return setDataset(NX_BEND_ANGLE_X, bend_angle_x);
	}

	@Override
	public DataNode setBend_angle_xScalar(double bend_angle_x) {
		return setField(NX_BEND_ANGLE_X, bend_angle_x);
	}

	@Override
	public IDataset getBend_angle_y() {
		return getDataset(NX_BEND_ANGLE_Y);
	}

	@Override
	public double getBend_angle_yScalar() {
		return getDouble(NX_BEND_ANGLE_Y);
	}

	@Override
	public DataNode setBend_angle_y(IDataset bend_angle_y) {
		return setDataset(NX_BEND_ANGLE_Y, bend_angle_y);
	}

	@Override
	public DataNode setBend_angle_yScalar(double bend_angle_y) {
		return setField(NX_BEND_ANGLE_Y, bend_angle_y);
	}

	@Override
	public IDataset getInterior_atmosphere() {
		return getDataset(NX_INTERIOR_ATMOSPHERE);
	}

	@Override
	public String getInterior_atmosphereScalar() {
		return getString(NX_INTERIOR_ATMOSPHERE);
	}

	@Override
	public DataNode setInterior_atmosphere(IDataset interior_atmosphere) {
		return setDataset(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
	public DataNode setInterior_atmosphereScalar(String interior_atmosphere) {
		return setString(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
	public IDataset getExternal_material() {
		return getDataset(NX_EXTERNAL_MATERIAL);
	}

	@Override
	public String getExternal_materialScalar() {
		return getString(NX_EXTERNAL_MATERIAL);
	}

	@Override
	public DataNode setExternal_material(IDataset external_material) {
		return setDataset(NX_EXTERNAL_MATERIAL, external_material);
	}

	@Override
	public DataNode setExternal_materialScalar(String external_material) {
		return setString(NX_EXTERNAL_MATERIAL, external_material);
	}

	@Override
	public IDataset getM_value() {
		return getDataset(NX_M_VALUE);
	}

	@Override
	public double getM_valueScalar() {
		return getDouble(NX_M_VALUE);
	}

	@Override
	public DataNode setM_value(IDataset m_value) {
		return setDataset(NX_M_VALUE, m_value);
	}

	@Override
	public DataNode setM_valueScalar(double m_value) {
		return setField(NX_M_VALUE, m_value);
	}

	@Override
	public IDataset getSubstrate_material() {
		return getDataset(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public double getSubstrate_materialScalar() {
		return getDouble(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public DataNode setSubstrate_material(IDataset substrate_material) {
		return setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public DataNode setSubstrate_materialScalar(double substrate_material) {
		return setField(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_thickness() {
		return getDataset(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public double getSubstrate_thicknessScalar() {
		return getDouble(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public DataNode setSubstrate_thickness(IDataset substrate_thickness) {
		return setDataset(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public DataNode setSubstrate_thicknessScalar(double substrate_thickness) {
		return setField(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public IDataset getCoating_material() {
		return getDataset(NX_COATING_MATERIAL);
	}

	@Override
	public double getCoating_materialScalar() {
		return getDouble(NX_COATING_MATERIAL);
	}

	@Override
	public DataNode setCoating_material(IDataset coating_material) {
		return setDataset(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public DataNode setCoating_materialScalar(double coating_material) {
		return setField(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public IDataset getSubstrate_roughness() {
		return getDataset(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public double getSubstrate_roughnessScalar() {
		return getDouble(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public DataNode setSubstrate_roughness(IDataset substrate_roughness) {
		return setDataset(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public DataNode setSubstrate_roughnessScalar(double substrate_roughness) {
		return setField(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public IDataset getCoating_roughness() {
		return getDataset(NX_COATING_ROUGHNESS);
	}

	@Override
	public double getCoating_roughnessScalar() {
		return getDouble(NX_COATING_ROUGHNESS);
	}

	@Override
	public DataNode setCoating_roughness(IDataset coating_roughness) {
		return setDataset(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public DataNode setCoating_roughnessScalar(double coating_roughness) {
		return setField(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public IDataset getNumber_sections() {
		return getDataset(NX_NUMBER_SECTIONS);
	}

	@Override
	public long getNumber_sectionsScalar() {
		return getLong(NX_NUMBER_SECTIONS);
	}

	@Override
	public DataNode setNumber_sections(IDataset number_sections) {
		return setDataset(NX_NUMBER_SECTIONS, number_sections);
	}

	@Override
	public DataNode setNumber_sectionsScalar(long number_sections) {
		return setField(NX_NUMBER_SECTIONS, number_sections);
	}

}
