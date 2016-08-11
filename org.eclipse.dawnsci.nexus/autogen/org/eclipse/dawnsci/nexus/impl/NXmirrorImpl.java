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
 * A beamline mirror or supermirror.
 * 
 * @version 1.0
 */
public class NXmirrorImpl extends NXobjectImpl implements NXmirror {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_SHAPE,
		NexusBaseClass.NX_DATA);

	public NXmirrorImpl() {
		super();
	}

	public NXmirrorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmirror.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_MIRROR;
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
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	@Override
	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	@Override
	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
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
	public String getSubstrate_materialScalar() {
		return getString(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public DataNode setSubstrate_material(IDataset substrate_material) {
		return setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public DataNode setSubstrate_materialScalar(String substrate_material) {
		return setString(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_density() {
		return getDataset(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public double getSubstrate_densityScalar() {
		return getDouble(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public DataNode setSubstrate_density(IDataset substrate_density) {
		return setDataset(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	@Override
	public DataNode setSubstrate_densityScalar(double substrate_density) {
		return setField(NX_SUBSTRATE_DENSITY, substrate_density);
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
	public String getCoating_materialScalar() {
		return getString(NX_COATING_MATERIAL);
	}

	@Override
	public DataNode setCoating_material(IDataset coating_material) {
		return setDataset(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public DataNode setCoating_materialScalar(String coating_material) {
		return setString(NX_COATING_MATERIAL, coating_material);
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
	public IDataset getEven_layer_material() {
		return getDataset(NX_EVEN_LAYER_MATERIAL);
	}

	@Override
	public String getEven_layer_materialScalar() {
		return getString(NX_EVEN_LAYER_MATERIAL);
	}

	@Override
	public DataNode setEven_layer_material(IDataset even_layer_material) {
		return setDataset(NX_EVEN_LAYER_MATERIAL, even_layer_material);
	}

	@Override
	public DataNode setEven_layer_materialScalar(String even_layer_material) {
		return setString(NX_EVEN_LAYER_MATERIAL, even_layer_material);
	}

	@Override
	public IDataset getEven_layer_density() {
		return getDataset(NX_EVEN_LAYER_DENSITY);
	}

	@Override
	public double getEven_layer_densityScalar() {
		return getDouble(NX_EVEN_LAYER_DENSITY);
	}

	@Override
	public DataNode setEven_layer_density(IDataset even_layer_density) {
		return setDataset(NX_EVEN_LAYER_DENSITY, even_layer_density);
	}

	@Override
	public DataNode setEven_layer_densityScalar(double even_layer_density) {
		return setField(NX_EVEN_LAYER_DENSITY, even_layer_density);
	}

	@Override
	public IDataset getOdd_layer_material() {
		return getDataset(NX_ODD_LAYER_MATERIAL);
	}

	@Override
	public String getOdd_layer_materialScalar() {
		return getString(NX_ODD_LAYER_MATERIAL);
	}

	@Override
	public DataNode setOdd_layer_material(IDataset odd_layer_material) {
		return setDataset(NX_ODD_LAYER_MATERIAL, odd_layer_material);
	}

	@Override
	public DataNode setOdd_layer_materialScalar(String odd_layer_material) {
		return setString(NX_ODD_LAYER_MATERIAL, odd_layer_material);
	}

	@Override
	public IDataset getOdd_layer_density() {
		return getDataset(NX_ODD_LAYER_DENSITY);
	}

	@Override
	public double getOdd_layer_densityScalar() {
		return getDouble(NX_ODD_LAYER_DENSITY);
	}

	@Override
	public DataNode setOdd_layer_density(IDataset odd_layer_density) {
		return setDataset(NX_ODD_LAYER_DENSITY, odd_layer_density);
	}

	@Override
	public DataNode setOdd_layer_densityScalar(double odd_layer_density) {
		return setField(NX_ODD_LAYER_DENSITY, odd_layer_density);
	}

	@Override
	public IDataset getLayer_thickness() {
		return getDataset(NX_LAYER_THICKNESS);
	}

	@Override
	public double getLayer_thicknessScalar() {
		return getDouble(NX_LAYER_THICKNESS);
	}

	@Override
	public DataNode setLayer_thickness(IDataset layer_thickness) {
		return setDataset(NX_LAYER_THICKNESS, layer_thickness);
	}

	@Override
	public DataNode setLayer_thicknessScalar(double layer_thickness) {
		return setField(NX_LAYER_THICKNESS, layer_thickness);
	}

	@Override
	public NXshape getShape() {
		return getChild("shape", NXshape.class);
	}

	@Override
	public void setShape(NXshape shape) {
		putChild("shape", shape);
	}

	@Override
	public NXdata getFigure_data() {
		return getChild("figure_data", NXdata.class);
	}

	@Override
	public void setFigure_data(NXdata figure_data) {
		putChild("figure_data", figure_data);
	}

}
