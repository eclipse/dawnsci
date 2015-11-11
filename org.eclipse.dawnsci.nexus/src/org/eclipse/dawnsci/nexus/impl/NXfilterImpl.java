/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template for specifying the state of band pass filters.
 * If uncertain whether to use ``NXfilter`` (band-pass filter)
 * or ``NXattenuator`` (reduces beam intensity), then use
 * ``NXattenuator``.
 * 
 * @version 1.0
 */
public class NXfilterImpl extends NXobjectImpl implements NXfilter {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_STATUS = "status";
	public static final String NX_TEMPERATURE = "temperature";
	public static final String NX_THICKNESS = "thickness";
	public static final String NX_DENSITY = "density";
	public static final String NX_CHEMICAL_FORMULA = "chemical_formula";
	public static final String NX_UNIT_CELL_A = "unit_cell_a";
	public static final String NX_UNIT_CELL_B = "unit_cell_b";
	public static final String NX_UNIT_CELL_C = "unit_cell_c";
	public static final String NX_UNIT_CELL_ALPHA = "unit_cell_alpha";
	public static final String NX_UNIT_CELL_BETA = "unit_cell_beta";
	public static final String NX_UNIT_CELL_GAMMA = "unit_cell_gamma";
	public static final String NX_UNIT_CELL_VOLUME = "unit_cell_volume";
	public static final String NX_ORIENTATION_MATRIX = "orientation_matrix";
	public static final String NX_M_VALUE = "m_value";
	public static final String NX_SUBSTRATE_MATERIAL = "substrate_material";
	public static final String NX_SUBSTRATE_THICKNESS = "substrate_thickness";
	public static final String NX_COATING_MATERIAL = "coating_material";
	public static final String NX_SUBSTRATE_ROUGHNESS = "substrate_roughness";
	public static final String NX_COATING_ROUGHNESS = "coating_roughness";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_SENSOR);

	protected NXfilterImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXfilterImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXfilter.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_FILTER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	public IDataset getStatus() {
		return getDataset(NX_STATUS);
	}

	@Override
	public String getStatusScalar() {
		return getString(NX_STATUS);
	}

	public void setStatus(IDataset status) {
		setDataset(NX_STATUS, status);
	}

	public void setStatusScalar(String status) {
		setString(NX_STATUS, status);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	@Override
	public double getTemperatureScalar() {
		return getDouble(NX_TEMPERATURE);
	}

	public void setTemperature(IDataset temperature) {
		setDataset(NX_TEMPERATURE, temperature);
	}

	public void setTemperatureScalar(double temperature) {
		setField(NX_TEMPERATURE, temperature);
	}

	@Override
	public NXlog getTemperature_log() {
		return getChild("temperature_log", NXlog.class);
	}

	public void setTemperature_log(NXlog temperature_log) {
		putChild("temperature_log", temperature_log);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	@Override
	public double getThicknessScalar() {
		return getDouble(NX_THICKNESS);
	}

	public void setThickness(IDataset thickness) {
		setDataset(NX_THICKNESS, thickness);
	}

	public void setThicknessScalar(double thickness) {
		setField(NX_THICKNESS, thickness);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public Number getDensityScalar() {
		return getNumber(NX_DENSITY);
	}

	public void setDensity(IDataset density) {
		setDataset(NX_DENSITY, density);
	}

	public void setDensityScalar(Number density) {
		setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	@Override
	public String getChemical_formulaScalar() {
		return getString(NX_CHEMICAL_FORMULA);
	}

	public void setChemical_formula(IDataset chemical_formula) {
		setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	public void setChemical_formulaScalar(String chemical_formula) {
		setString(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public NXsensor getSensor_type() {
		return getChild("sensor_type", NXsensor.class);
	}

	public void setSensor_type(NXsensor sensor_type) {
		putChild("sensor_type", sensor_type);
	}

	@Override
	public IDataset getUnit_cell_a() {
		return getDataset(NX_UNIT_CELL_A);
	}

	@Override
	public double getUnit_cell_aScalar() {
		return getDouble(NX_UNIT_CELL_A);
	}

	public void setUnit_cell_a(IDataset unit_cell_a) {
		setDataset(NX_UNIT_CELL_A, unit_cell_a);
	}

	public void setUnit_cell_aScalar(double unit_cell_a) {
		setField(NX_UNIT_CELL_A, unit_cell_a);
	}

	@Override
	public IDataset getUnit_cell_b() {
		return getDataset(NX_UNIT_CELL_B);
	}

	@Override
	public double getUnit_cell_bScalar() {
		return getDouble(NX_UNIT_CELL_B);
	}

	public void setUnit_cell_b(IDataset unit_cell_b) {
		setDataset(NX_UNIT_CELL_B, unit_cell_b);
	}

	public void setUnit_cell_bScalar(double unit_cell_b) {
		setField(NX_UNIT_CELL_B, unit_cell_b);
	}

	@Override
	public IDataset getUnit_cell_c() {
		return getDataset(NX_UNIT_CELL_C);
	}

	@Override
	public double getUnit_cell_cScalar() {
		return getDouble(NX_UNIT_CELL_C);
	}

	public void setUnit_cell_c(IDataset unit_cell_c) {
		setDataset(NX_UNIT_CELL_C, unit_cell_c);
	}

	public void setUnit_cell_cScalar(double unit_cell_c) {
		setField(NX_UNIT_CELL_C, unit_cell_c);
	}

	@Override
	public IDataset getUnit_cell_alpha() {
		return getDataset(NX_UNIT_CELL_ALPHA);
	}

	@Override
	public double getUnit_cell_alphaScalar() {
		return getDouble(NX_UNIT_CELL_ALPHA);
	}

	public void setUnit_cell_alpha(IDataset unit_cell_alpha) {
		setDataset(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	public void setUnit_cell_alphaScalar(double unit_cell_alpha) {
		setField(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	@Override
	public IDataset getUnit_cell_beta() {
		return getDataset(NX_UNIT_CELL_BETA);
	}

	@Override
	public double getUnit_cell_betaScalar() {
		return getDouble(NX_UNIT_CELL_BETA);
	}

	public void setUnit_cell_beta(IDataset unit_cell_beta) {
		setDataset(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	public void setUnit_cell_betaScalar(double unit_cell_beta) {
		setField(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	@Override
	public IDataset getUnit_cell_gamma() {
		return getDataset(NX_UNIT_CELL_GAMMA);
	}

	@Override
	public double getUnit_cell_gammaScalar() {
		return getDouble(NX_UNIT_CELL_GAMMA);
	}

	public void setUnit_cell_gamma(IDataset unit_cell_gamma) {
		setDataset(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	public void setUnit_cell_gammaScalar(double unit_cell_gamma) {
		setField(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public double getUnit_cell_volumeScalar() {
		return getDouble(NX_UNIT_CELL_VOLUME);
	}

	public void setUnit_cell_volume(IDataset unit_cell_volume) {
		setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	public void setUnit_cell_volumeScalar(double unit_cell_volume) {
		setField(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	@Override
	public double getOrientation_matrixScalar() {
		return getDouble(NX_ORIENTATION_MATRIX);
	}

	public void setOrientation_matrix(IDataset orientation_matrix) {
		setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	public void setOrientation_matrixScalar(double orientation_matrix) {
		setField(NX_ORIENTATION_MATRIX, orientation_matrix);
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

}
