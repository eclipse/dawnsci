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
 * For band pass beam filters.
 * If uncertain whether to use :ref:`NXfilter` (band-pass filter)
 * or :ref:`NXattenuator` (reduces beam intensity), then use
 * :ref:`NXattenuator`.
 * 
 * @version 1.0
 */
public class NXfilterImpl extends NXobjectImpl implements NXfilter {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_SENSOR);

	public NXfilterImpl() {
		super();
	}

	public NXfilterImpl(final long oid) {
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
	public IDataset getStatus() {
		return getDataset(NX_STATUS);
	}

	@Override
	public String getStatusScalar() {
		return getString(NX_STATUS);
	}

	@Override
	public DataNode setStatus(IDataset status) {
		return setDataset(NX_STATUS, status);
	}

	@Override
	public DataNode setStatusScalar(String status) {
		return setString(NX_STATUS, status);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	@Override
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

	@Override
	public DataNode setTemperature(IDataset temperature) {
		return setDataset(NX_TEMPERATURE, temperature);
	}

	@Override
	public DataNode setTemperatureScalar(double temperature) {
		return setField(NX_TEMPERATURE, temperature);
	}

	@Override
	public NXlog getTemperature_log() {
		return getChild("temperature_log", NXlog.class);
	}

	@Override
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

	@Override
	public DataNode setThickness(IDataset thickness) {
		return setDataset(NX_THICKNESS, thickness);
	}

	@Override
	public DataNode setThicknessScalar(double thickness) {
		return setField(NX_THICKNESS, thickness);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public Number getDensityScalar() {
		return getNumber(NX_DENSITY);
	}

	@Override
	public DataNode setDensity(IDataset density) {
		return setDataset(NX_DENSITY, density);
	}

	@Override
	public DataNode setDensityScalar(Number density) {
		return setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	@Override
	public String getChemical_formulaScalar() {
		return getString(NX_CHEMICAL_FORMULA);
	}

	@Override
	public DataNode setChemical_formula(IDataset chemical_formula) {
		return setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public DataNode setChemical_formulaScalar(String chemical_formula) {
		return setString(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public NXsensor getSensor_type() {
		return getChild("sensor_type", NXsensor.class);
	}

	@Override
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

	@Override
	public DataNode setUnit_cell_a(IDataset unit_cell_a) {
		return setDataset(NX_UNIT_CELL_A, unit_cell_a);
	}

	@Override
	public DataNode setUnit_cell_aScalar(double unit_cell_a) {
		return setField(NX_UNIT_CELL_A, unit_cell_a);
	}

	@Override
	public IDataset getUnit_cell_b() {
		return getDataset(NX_UNIT_CELL_B);
	}

	@Override
	public double getUnit_cell_bScalar() {
		return getDouble(NX_UNIT_CELL_B);
	}

	@Override
	public DataNode setUnit_cell_b(IDataset unit_cell_b) {
		return setDataset(NX_UNIT_CELL_B, unit_cell_b);
	}

	@Override
	public DataNode setUnit_cell_bScalar(double unit_cell_b) {
		return setField(NX_UNIT_CELL_B, unit_cell_b);
	}

	@Override
	public IDataset getUnit_cell_c() {
		return getDataset(NX_UNIT_CELL_C);
	}

	@Override
	public double getUnit_cell_cScalar() {
		return getDouble(NX_UNIT_CELL_C);
	}

	@Override
	public DataNode setUnit_cell_c(IDataset unit_cell_c) {
		return setDataset(NX_UNIT_CELL_C, unit_cell_c);
	}

	@Override
	public DataNode setUnit_cell_cScalar(double unit_cell_c) {
		return setField(NX_UNIT_CELL_C, unit_cell_c);
	}

	@Override
	public IDataset getUnit_cell_alpha() {
		return getDataset(NX_UNIT_CELL_ALPHA);
	}

	@Override
	public double getUnit_cell_alphaScalar() {
		return getDouble(NX_UNIT_CELL_ALPHA);
	}

	@Override
	public DataNode setUnit_cell_alpha(IDataset unit_cell_alpha) {
		return setDataset(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	@Override
	public DataNode setUnit_cell_alphaScalar(double unit_cell_alpha) {
		return setField(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	@Override
	public IDataset getUnit_cell_beta() {
		return getDataset(NX_UNIT_CELL_BETA);
	}

	@Override
	public double getUnit_cell_betaScalar() {
		return getDouble(NX_UNIT_CELL_BETA);
	}

	@Override
	public DataNode setUnit_cell_beta(IDataset unit_cell_beta) {
		return setDataset(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	@Override
	public DataNode setUnit_cell_betaScalar(double unit_cell_beta) {
		return setField(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	@Override
	public IDataset getUnit_cell_gamma() {
		return getDataset(NX_UNIT_CELL_GAMMA);
	}

	@Override
	public double getUnit_cell_gammaScalar() {
		return getDouble(NX_UNIT_CELL_GAMMA);
	}

	@Override
	public DataNode setUnit_cell_gamma(IDataset unit_cell_gamma) {
		return setDataset(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	@Override
	public DataNode setUnit_cell_gammaScalar(double unit_cell_gamma) {
		return setField(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public double getUnit_cell_volumeScalar() {
		return getDouble(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public DataNode setUnit_cell_volume(IDataset unit_cell_volume) {
		return setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public DataNode setUnit_cell_volumeScalar(double unit_cell_volume) {
		return setField(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	@Override
	public double getOrientation_matrixScalar() {
		return getDouble(NX_ORIENTATION_MATRIX);
	}

	@Override
	public DataNode setOrientation_matrix(IDataset orientation_matrix) {
		return setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public DataNode setOrientation_matrixScalar(double orientation_matrix) {
		return setField(NX_ORIENTATION_MATRIX, orientation_matrix);
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

}
