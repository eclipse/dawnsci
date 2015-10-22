/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-13T13:58:10.369+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a beamline mirror or supermirror.
 * 
 * @version 1.0
 */
public class NXmirrorImpl extends NXobjectImpl implements NXmirror {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_INCIDENT_ANGLE = "incident_angle";
	public static final String NX_BEND_ANGLE_X = "bend_angle_x";
	public static final String NX_BEND_ANGLE_Y = "bend_angle_y";
	public static final String NX_INTERIOR_ATMOSPHERE = "interior_atmosphere";
	public static final String NX_EXTERNAL_MATERIAL = "external_material";
	public static final String NX_M_VALUE = "m_value";
	public static final String NX_SUBSTRATE_MATERIAL = "substrate_material";
	public static final String NX_SUBSTRATE_DENSITY = "substrate_density";
	public static final String NX_SUBSTRATE_THICKNESS = "substrate_thickness";
	public static final String NX_COATING_MATERIAL = "coating_material";
	public static final String NX_SUBSTRATE_ROUGHNESS = "substrate_roughness";
	public static final String NX_COATING_ROUGHNESS = "coating_roughness";
	public static final String NX_EVEN_LAYER_MATERIAL = "even_layer_material";
	public static final String NX_EVEN_LAYER_DENSITY = "even_layer_density";
	public static final String NX_ODD_LAYER_MATERIAL = "odd_layer_material";
	public static final String NX_ODD_LAYER_DENSITY = "odd_layer_density";
	public static final String NX_LAYER_THICKNESS = "layer_thickness";

	protected NXmirrorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXmirrorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmirror.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_MIRROR;
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}

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

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setTypeScalar(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setDescriptionScalar(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getIncident_angle() {
		return getDataset(NX_INCIDENT_ANGLE);
	}

	@Override
	public double getIncident_angleScalar() {
		return getDouble(NX_INCIDENT_ANGLE);
	}

	public void setIncident_angle(IDataset incident_angle) {
		setDataset(NX_INCIDENT_ANGLE, incident_angle);
	}

	public void setIncident_angleScalar(double incident_angle) {
		setField(NX_INCIDENT_ANGLE, incident_angle);
	}

	@Override
	public NXdata getReflectivity() {
		return getChild("reflectivity", NXdata.class);
	}

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

	public void setBend_angle_x(IDataset bend_angle_x) {
		setDataset(NX_BEND_ANGLE_X, bend_angle_x);
	}

	public void setBend_angle_xScalar(double bend_angle_x) {
		setField(NX_BEND_ANGLE_X, bend_angle_x);
	}

	@Override
	public IDataset getBend_angle_y() {
		return getDataset(NX_BEND_ANGLE_Y);
	}

	@Override
	public double getBend_angle_yScalar() {
		return getDouble(NX_BEND_ANGLE_Y);
	}

	public void setBend_angle_y(IDataset bend_angle_y) {
		setDataset(NX_BEND_ANGLE_Y, bend_angle_y);
	}

	public void setBend_angle_yScalar(double bend_angle_y) {
		setField(NX_BEND_ANGLE_Y, bend_angle_y);
	}

	@Override
	public IDataset getInterior_atmosphere() {
		return getDataset(NX_INTERIOR_ATMOSPHERE);
	}

	@Override
	public String getInterior_atmosphereScalar() {
		return getString(NX_INTERIOR_ATMOSPHERE);
	}

	public void setInterior_atmosphere(IDataset interior_atmosphere) {
		setDataset(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	public void setInterior_atmosphereScalar(String interior_atmosphere) {
		setString(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
	public IDataset getExternal_material() {
		return getDataset(NX_EXTERNAL_MATERIAL);
	}

	@Override
	public String getExternal_materialScalar() {
		return getString(NX_EXTERNAL_MATERIAL);
	}

	public void setExternal_material(IDataset external_material) {
		setDataset(NX_EXTERNAL_MATERIAL, external_material);
	}

	public void setExternal_materialScalar(String external_material) {
		setString(NX_EXTERNAL_MATERIAL, external_material);
	}

	@Override
	public IDataset getM_value() {
		return getDataset(NX_M_VALUE);
	}

	@Override
	public double getM_valueScalar() {
		return getDouble(NX_M_VALUE);
	}

	public void setM_value(IDataset m_value) {
		setDataset(NX_M_VALUE, m_value);
	}

	public void setM_valueScalar(double m_value) {
		setField(NX_M_VALUE, m_value);
	}

	@Override
	public IDataset getSubstrate_material() {
		return getDataset(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public String getSubstrate_materialScalar() {
		return getString(NX_SUBSTRATE_MATERIAL);
	}

	public void setSubstrate_material(IDataset substrate_material) {
		setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	public void setSubstrate_materialScalar(String substrate_material) {
		setString(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_density() {
		return getDataset(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public double getSubstrate_densityScalar() {
		return getDouble(NX_SUBSTRATE_DENSITY);
	}

	public void setSubstrate_density(IDataset substrate_density) {
		setDataset(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	public void setSubstrate_densityScalar(double substrate_density) {
		setField(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	@Override
	public IDataset getSubstrate_thickness() {
		return getDataset(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public double getSubstrate_thicknessScalar() {
		return getDouble(NX_SUBSTRATE_THICKNESS);
	}

	public void setSubstrate_thickness(IDataset substrate_thickness) {
		setDataset(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	public void setSubstrate_thicknessScalar(double substrate_thickness) {
		setField(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public IDataset getCoating_material() {
		return getDataset(NX_COATING_MATERIAL);
	}

	@Override
	public String getCoating_materialScalar() {
		return getString(NX_COATING_MATERIAL);
	}

	public void setCoating_material(IDataset coating_material) {
		setDataset(NX_COATING_MATERIAL, coating_material);
	}

	public void setCoating_materialScalar(String coating_material) {
		setString(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public IDataset getSubstrate_roughness() {
		return getDataset(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public double getSubstrate_roughnessScalar() {
		return getDouble(NX_SUBSTRATE_ROUGHNESS);
	}

	public void setSubstrate_roughness(IDataset substrate_roughness) {
		setDataset(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	public void setSubstrate_roughnessScalar(double substrate_roughness) {
		setField(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public IDataset getCoating_roughness() {
		return getDataset(NX_COATING_ROUGHNESS);
	}

	@Override
	public double getCoating_roughnessScalar() {
		return getDouble(NX_COATING_ROUGHNESS);
	}

	public void setCoating_roughness(IDataset coating_roughness) {
		setDataset(NX_COATING_ROUGHNESS, coating_roughness);
	}

	public void setCoating_roughnessScalar(double coating_roughness) {
		setField(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public IDataset getEven_layer_material() {
		return getDataset(NX_EVEN_LAYER_MATERIAL);
	}

	@Override
	public String getEven_layer_materialScalar() {
		return getString(NX_EVEN_LAYER_MATERIAL);
	}

	public void setEven_layer_material(IDataset even_layer_material) {
		setDataset(NX_EVEN_LAYER_MATERIAL, even_layer_material);
	}

	public void setEven_layer_materialScalar(String even_layer_material) {
		setString(NX_EVEN_LAYER_MATERIAL, even_layer_material);
	}

	@Override
	public IDataset getEven_layer_density() {
		return getDataset(NX_EVEN_LAYER_DENSITY);
	}

	@Override
	public double getEven_layer_densityScalar() {
		return getDouble(NX_EVEN_LAYER_DENSITY);
	}

	public void setEven_layer_density(IDataset even_layer_density) {
		setDataset(NX_EVEN_LAYER_DENSITY, even_layer_density);
	}

	public void setEven_layer_densityScalar(double even_layer_density) {
		setField(NX_EVEN_LAYER_DENSITY, even_layer_density);
	}

	@Override
	public IDataset getOdd_layer_material() {
		return getDataset(NX_ODD_LAYER_MATERIAL);
	}

	@Override
	public String getOdd_layer_materialScalar() {
		return getString(NX_ODD_LAYER_MATERIAL);
	}

	public void setOdd_layer_material(IDataset odd_layer_material) {
		setDataset(NX_ODD_LAYER_MATERIAL, odd_layer_material);
	}

	public void setOdd_layer_materialScalar(String odd_layer_material) {
		setString(NX_ODD_LAYER_MATERIAL, odd_layer_material);
	}

	@Override
	public IDataset getOdd_layer_density() {
		return getDataset(NX_ODD_LAYER_DENSITY);
	}

	@Override
	public double getOdd_layer_densityScalar() {
		return getDouble(NX_ODD_LAYER_DENSITY);
	}

	public void setOdd_layer_density(IDataset odd_layer_density) {
		setDataset(NX_ODD_LAYER_DENSITY, odd_layer_density);
	}

	public void setOdd_layer_densityScalar(double odd_layer_density) {
		setField(NX_ODD_LAYER_DENSITY, odd_layer_density);
	}

	@Override
	public IDataset getLayer_thickness() {
		return getDataset(NX_LAYER_THICKNESS);
	}

	@Override
	public double getLayer_thicknessScalar() {
		return getDouble(NX_LAYER_THICKNESS);
	}

	public void setLayer_thickness(IDataset layer_thickness) {
		setDataset(NX_LAYER_THICKNESS, layer_thickness);
	}

	public void setLayer_thicknessScalar(double layer_thickness) {
		setField(NX_LAYER_THICKNESS, layer_thickness);
	}

	@Override
	public NXshape getShape() {
		return getChild("shape", NXshape.class);
	}

	public void setShape(NXshape shape) {
		putChild("shape", shape);
	}

	@Override
	public NXdata getFigure_data() {
		return getChild("figure_data", NXdata.class);
	}

	public void setFigure_data(NXdata figure_data) {
		putChild("figure_data", figure_data);
	}

}
