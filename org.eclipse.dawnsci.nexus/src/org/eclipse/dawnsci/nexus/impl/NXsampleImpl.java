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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of the state of the sample. This could include scanned variables that
 * are associated with one of the data dimensions, e.g. the magnetic field, or
 * logged data, e.g. monitored temperature vs elapsed time.
 * 
 * @version 1.0
 */
public class NXsampleImpl extends NXobjectImpl implements NXsample {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_NAME = "name";
	public static final String NX_CHEMICAL_FORMULA = "chemical_formula";
	public static final String NX_TEMPERATURE = "temperature";
	public static final String NX_ELECTRIC_FIELD = "electric_field";
	public static final String NX_ELECTRIC_FIELD_ATTRIBUTE_DIRECTION = "direction";
	public static final String NX_MAGNETIC_FIELD = "magnetic_field";
	public static final String NX_MAGNETIC_FIELD_ATTRIBUTE_DIRECTION = "direction";
	public static final String NX_STRESS_FIELD = "stress_field";
	public static final String NX_STRESS_FIELD_ATTRIBUTE_DIRECTION = "direction";
	public static final String NX_PRESSURE = "pressure";
	public static final String NX_CHANGER_POSITION = "changer_position";
	public static final String NX_UNIT_CELL = "unit_cell";
	public static final String NX_UNIT_CELL_VOLUME = "unit_cell_volume";
	public static final String NX_SAMPLE_ORIENTATION = "sample_orientation";
	public static final String NX_ORIENTATION_MATRIX = "orientation_matrix";
	public static final String NX_MASS = "mass";
	public static final String NX_DENSITY = "density";
	public static final String NX_RELATIVE_MOLECULAR_MASS = "relative_molecular_mass";
	public static final String NX_TYPE = "type";
	public static final String NX_SITUATION = "situation";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_PREPARATION_DATE = "preparation_date";
	public static final String NX_COMPONENT = "component";
	public static final String NX_SAMPLE_COMPONENT = "sample_component";
	public static final String NX_CONCENTRATION = "concentration";
	public static final String NX_VOLUME_FRACTION = "volume_fraction";
	public static final String NX_SCATTERING_LENGTH_DENSITY = "scattering_length_density";
	public static final String NX_UNIT_CELL_CLASS = "unit_cell_class";
	public static final String NX_UNIT_CELL_GROUP = "unit_cell_group";
	public static final String NX_PATH_LENGTH = "path_length";
	public static final String NX_PATH_LENGTH_WINDOW = "path_length_window";
	public static final String NX_THICKNESS = "thickness";
	public static final String NX_EXTERNAL_DAC = "external_DAC";
	public static final String NX_SHORT_TITLE = "short_title";
	public static final String NX_ROTATION_ANGLE = "rotation_angle";
	public static final String NX_X_TRANSLATION = "x_translation";
	public static final String NX_DISTANCE = "distance";

	protected NXsampleImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsample.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_SAMPLE;
	}

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	public void setChemical_formula(IDataset chemical_formula) {
		setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	public void setTemperature(IDataset temperature) {
		setDataset(NX_TEMPERATURE, temperature);
	}

	@Override
	public IDataset getElectric_field() {
		return getDataset(NX_ELECTRIC_FIELD);
	}

	public void setElectric_field(IDataset electric_field) {
		setDataset(NX_ELECTRIC_FIELD, electric_field);
	}

	@Override
	public String getElectric_fieldAttributeDirection() {
		return getAttrString(NX_ELECTRIC_FIELD, NX_ELECTRIC_FIELD_ATTRIBUTE_DIRECTION);
	}

	public void setElectric_fieldAttributeDirection(String direction) {
		setAttribute(NX_ELECTRIC_FIELD, NX_ELECTRIC_FIELD_ATTRIBUTE_DIRECTION, direction);
	}

	@Override
	public IDataset getMagnetic_field() {
		return getDataset(NX_MAGNETIC_FIELD);
	}

	public void setMagnetic_field(IDataset magnetic_field) {
		setDataset(NX_MAGNETIC_FIELD, magnetic_field);
	}

	@Override
	public String getMagnetic_fieldAttributeDirection() {
		return getAttrString(NX_MAGNETIC_FIELD, NX_MAGNETIC_FIELD_ATTRIBUTE_DIRECTION);
	}

	public void setMagnetic_fieldAttributeDirection(String direction) {
		setAttribute(NX_MAGNETIC_FIELD, NX_MAGNETIC_FIELD_ATTRIBUTE_DIRECTION, direction);
	}

	@Override
	public IDataset getStress_field() {
		return getDataset(NX_STRESS_FIELD);
	}

	public void setStress_field(IDataset stress_field) {
		setDataset(NX_STRESS_FIELD, stress_field);
	}

	@Override
	public String getStress_fieldAttributeDirection() {
		return getAttrString(NX_STRESS_FIELD, NX_STRESS_FIELD_ATTRIBUTE_DIRECTION);
	}

	public void setStress_fieldAttributeDirection(String direction) {
		setAttribute(NX_STRESS_FIELD, NX_STRESS_FIELD_ATTRIBUTE_DIRECTION, direction);
	}

	@Override
	public IDataset getPressure() {
		return getDataset(NX_PRESSURE);
	}

	public void setPressure(IDataset pressure) {
		setDataset(NX_PRESSURE, pressure);
	}

	@Override
	public IDataset getChanger_position() {
		return getDataset(NX_CHANGER_POSITION);
	}

	public void setChanger_position(IDataset changer_position) {
		setDataset(NX_CHANGER_POSITION, changer_position);
	}

	@Override
	public IDataset getUnit_cell() {
		return getDataset(NX_UNIT_CELL);
	}

	public void setUnit_cell(IDataset unit_cell) {
		setDataset(NX_UNIT_CELL, unit_cell);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	public void setUnit_cell_volume(IDataset unit_cell_volume) {
		setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getSample_orientation() {
		return getDataset(NX_SAMPLE_ORIENTATION);
	}

	public void setSample_orientation(IDataset sample_orientation) {
		setDataset(NX_SAMPLE_ORIENTATION, sample_orientation);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	public void setOrientation_matrix(IDataset orientation_matrix) {
		setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public IDataset getMass() {
		return getDataset(NX_MASS);
	}

	public void setMass(IDataset mass) {
		setDataset(NX_MASS, mass);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	public void setDensity(IDataset density) {
		setDataset(NX_DENSITY, density);
	}

	@Override
	public IDataset getRelative_molecular_mass() {
		return getDataset(NX_RELATIVE_MOLECULAR_MASS);
	}

	public void setRelative_molecular_mass(IDataset relative_molecular_mass) {
		setDataset(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getSituation() {
		return getDataset(NX_SITUATION);
	}

	public void setSituation(IDataset situation) {
		setDataset(NX_SITUATION, situation);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getPreparation_date() {
		return getDataset(NX_PREPARATION_DATE);
	}

	public void setPreparation_date(IDataset preparation_date) {
		setDataset(NX_PREPARATION_DATE, preparation_date);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXbeam getBeam() {
		return getChild("beam", NXbeam.class);
	}

	public void setBeam(NXbeam beam) {
		putChild("beam", beam);
	}

	@Override
	public NXbeam getBeam(String name) {
		return getChild(name, NXbeam.class);
	}

	public void setBeam(String name, NXbeam beam) {
		putChild(name, beam);
	}

	@Override
	public Map<String, NXbeam> getAllBeam() {
		return getChildren(NXbeam.class);
	}

	public void setAllBeam(Map<String, NXbeam> beam) {
		setChildren(beam);
	}

	@Override
	public IDataset getComponent() {
		return getDataset(NX_COMPONENT);
	}

	public void setComponent(IDataset component) {
		setDataset(NX_COMPONENT, component);
	}

	@Override
	public IDataset getSample_component() {
		return getDataset(NX_SAMPLE_COMPONENT);
	}

	public void setSample_component(IDataset sample_component) {
		setDataset(NX_SAMPLE_COMPONENT, sample_component);
	}

	@Override
	public IDataset getConcentration() {
		return getDataset(NX_CONCENTRATION);
	}

	public void setConcentration(IDataset concentration) {
		setDataset(NX_CONCENTRATION, concentration);
	}

	@Override
	public IDataset getVolume_fraction() {
		return getDataset(NX_VOLUME_FRACTION);
	}

	public void setVolume_fraction(IDataset volume_fraction) {
		setDataset(NX_VOLUME_FRACTION, volume_fraction);
	}

	@Override
	public IDataset getScattering_length_density() {
		return getDataset(NX_SCATTERING_LENGTH_DENSITY);
	}

	public void setScattering_length_density(IDataset scattering_length_density) {
		setDataset(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	@Override
	public IDataset getUnit_cell_class() {
		return getDataset(NX_UNIT_CELL_CLASS);
	}

	public void setUnit_cell_class(IDataset unit_cell_class) {
		setDataset(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	@Override
	public IDataset getUnit_cell_group() {
		return getDataset(NX_UNIT_CELL_GROUP);
	}

	public void setUnit_cell_group(IDataset unit_cell_group) {
		setDataset(NX_UNIT_CELL_GROUP, unit_cell_group);
	}

	@Override
	public IDataset getPath_length() {
		return getDataset(NX_PATH_LENGTH);
	}

	public void setPath_length(IDataset path_length) {
		setDataset(NX_PATH_LENGTH, path_length);
	}

	@Override
	public IDataset getPath_length_window() {
		return getDataset(NX_PATH_LENGTH_WINDOW);
	}

	public void setPath_length_window(IDataset path_length_window) {
		setDataset(NX_PATH_LENGTH_WINDOW, path_length_window);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	public void setThickness(IDataset thickness) {
		setDataset(NX_THICKNESS, thickness);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
	}

	@Override
	public NXlog getTemperature_log() {
		return getChild("temperature_log", NXlog.class);
	}

	public void setTemperature_log(NXlog temperature_log) {
		putChild("temperature_log", temperature_log);
	}

	@Override
	public NXenvironment getTemperature_env() {
		return getChild("temperature_env", NXenvironment.class);
	}

	public void setTemperature_env(NXenvironment temperature_env) {
		putChild("temperature_env", temperature_env);
	}

	@Override
	public NXlog getMagnetic_field_log() {
		return getChild("magnetic_field_log", NXlog.class);
	}

	public void setMagnetic_field_log(NXlog magnetic_field_log) {
		putChild("magnetic_field_log", magnetic_field_log);
	}

	@Override
	public NXenvironment getMagnetic_field_env() {
		return getChild("magnetic_field_env", NXenvironment.class);
	}

	public void setMagnetic_field_env(NXenvironment magnetic_field_env) {
		putChild("magnetic_field_env", magnetic_field_env);
	}

	@Override
	public IDataset getExternal_DAC() {
		return getDataset(NX_EXTERNAL_DAC);
	}

	public void setExternal_DAC(IDataset external_DAC) {
		setDataset(NX_EXTERNAL_DAC, external_DAC);
	}

	@Override
	public NXlog getExternal_ADC() {
		return getChild("external_ADC", NXlog.class);
	}

	public void setExternal_ADC(NXlog external_ADC) {
		putChild("external_ADC", external_ADC);
	}

	@Override
	public IDataset getShort_title() {
		return getDataset(NX_SHORT_TITLE);
	}

	public void setShort_title(IDataset short_title) {
		setDataset(NX_SHORT_TITLE, short_title);
	}

	@Override
	public IDataset getRotation_angle() {
		return getDataset(NX_ROTATION_ANGLE);
	}

	public void setRotation_angle(IDataset rotation_angle) {
		setDataset(NX_ROTATION_ANGLE, rotation_angle);
	}

	@Override
	public IDataset getX_translation() {
		return getDataset(NX_X_TRANSLATION);
	}

	public void setX_translation(IDataset x_translation) {
		setDataset(NX_X_TRANSLATION, x_translation);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	@Override
	public NXpositioner getPositioner() {
		return getChild("positioner", NXpositioner.class);
	}

	public void setPositioner(NXpositioner positioner) {
		putChild("positioner", positioner);
	}

	@Override
	public NXpositioner getPositioner(String name) {
		return getChild(name, NXpositioner.class);
	}

	public void setPositioner(String name, NXpositioner positioner) {
		putChild(name, positioner);
	}

	@Override
	public Map<String, NXpositioner> getAllPositioner() {
		return getChildren(NXpositioner.class);
	}

	public void setAllPositioner(Map<String, NXpositioner> positioner) {
		setChildren(positioner);
	}

}
