/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-12T11:55:04.232+01:00
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

	protected NXsampleImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXsampleImpl(final long oid) {
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

	@Override
	public String getScalarName() {
		return getString(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	public void setScalarName(String name) {
		setString(NX_NAME, name);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	@Override
	public String getScalarChemical_formula() {
		return getString(NX_CHEMICAL_FORMULA);
	}

	public void setChemical_formula(IDataset chemical_formula) {
		setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	public void setScalarChemical_formula(String chemical_formula) {
		setString(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	@Override
	public double getScalarTemperature() {
		return getDouble(NX_TEMPERATURE);
	}

	public void setTemperature(IDataset temperature) {
		setDataset(NX_TEMPERATURE, temperature);
	}

	public void setScalarTemperature(double temperature) {
		setField(NX_TEMPERATURE, temperature);
	}

	@Override
	public IDataset getElectric_field() {
		return getDataset(NX_ELECTRIC_FIELD);
	}

	@Override
	public double getScalarElectric_field() {
		return getDouble(NX_ELECTRIC_FIELD);
	}

	public void setElectric_field(IDataset electric_field) {
		setDataset(NX_ELECTRIC_FIELD, electric_field);
	}

	public void setScalarElectric_field(double electric_field) {
		setField(NX_ELECTRIC_FIELD, electric_field);
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

	@Override
	public double getScalarMagnetic_field() {
		return getDouble(NX_MAGNETIC_FIELD);
	}

	public void setMagnetic_field(IDataset magnetic_field) {
		setDataset(NX_MAGNETIC_FIELD, magnetic_field);
	}

	public void setScalarMagnetic_field(double magnetic_field) {
		setField(NX_MAGNETIC_FIELD, magnetic_field);
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

	@Override
	public double getScalarStress_field() {
		return getDouble(NX_STRESS_FIELD);
	}

	public void setStress_field(IDataset stress_field) {
		setDataset(NX_STRESS_FIELD, stress_field);
	}

	public void setScalarStress_field(double stress_field) {
		setField(NX_STRESS_FIELD, stress_field);
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

	@Override
	public double getScalarPressure() {
		return getDouble(NX_PRESSURE);
	}

	public void setPressure(IDataset pressure) {
		setDataset(NX_PRESSURE, pressure);
	}

	public void setScalarPressure(double pressure) {
		setField(NX_PRESSURE, pressure);
	}

	@Override
	public IDataset getChanger_position() {
		return getDataset(NX_CHANGER_POSITION);
	}

	@Override
	public long getScalarChanger_position() {
		return getLong(NX_CHANGER_POSITION);
	}

	public void setChanger_position(IDataset changer_position) {
		setDataset(NX_CHANGER_POSITION, changer_position);
	}

	public void setScalarChanger_position(long changer_position) {
		setField(NX_CHANGER_POSITION, changer_position);
	}

	@Override
	public IDataset getUnit_cell() {
		return getDataset(NX_UNIT_CELL);
	}

	@Override
	public double getScalarUnit_cell() {
		return getDouble(NX_UNIT_CELL);
	}

	public void setUnit_cell(IDataset unit_cell) {
		setDataset(NX_UNIT_CELL, unit_cell);
	}

	public void setScalarUnit_cell(double unit_cell) {
		setField(NX_UNIT_CELL, unit_cell);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public double getScalarUnit_cell_volume() {
		return getDouble(NX_UNIT_CELL_VOLUME);
	}

	public void setUnit_cell_volume(IDataset unit_cell_volume) {
		setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	public void setScalarUnit_cell_volume(double unit_cell_volume) {
		setField(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getSample_orientation() {
		return getDataset(NX_SAMPLE_ORIENTATION);
	}

	@Override
	public double getScalarSample_orientation() {
		return getDouble(NX_SAMPLE_ORIENTATION);
	}

	public void setSample_orientation(IDataset sample_orientation) {
		setDataset(NX_SAMPLE_ORIENTATION, sample_orientation);
	}

	public void setScalarSample_orientation(double sample_orientation) {
		setField(NX_SAMPLE_ORIENTATION, sample_orientation);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	@Override
	public double getScalarOrientation_matrix() {
		return getDouble(NX_ORIENTATION_MATRIX);
	}

	public void setOrientation_matrix(IDataset orientation_matrix) {
		setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	public void setScalarOrientation_matrix(double orientation_matrix) {
		setField(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public IDataset getMass() {
		return getDataset(NX_MASS);
	}

	@Override
	public double getScalarMass() {
		return getDouble(NX_MASS);
	}

	public void setMass(IDataset mass) {
		setDataset(NX_MASS, mass);
	}

	public void setScalarMass(double mass) {
		setField(NX_MASS, mass);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public double getScalarDensity() {
		return getDouble(NX_DENSITY);
	}

	public void setDensity(IDataset density) {
		setDataset(NX_DENSITY, density);
	}

	public void setScalarDensity(double density) {
		setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getRelative_molecular_mass() {
		return getDataset(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public double getScalarRelative_molecular_mass() {
		return getDouble(NX_RELATIVE_MOLECULAR_MASS);
	}

	public void setRelative_molecular_mass(IDataset relative_molecular_mass) {
		setDataset(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	public void setScalarRelative_molecular_mass(double relative_molecular_mass) {
		setField(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getScalarType() {
		return getString(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setScalarType(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getSituation() {
		return getDataset(NX_SITUATION);
	}

	@Override
	public String getScalarSituation() {
		return getString(NX_SITUATION);
	}

	public void setSituation(IDataset situation) {
		setDataset(NX_SITUATION, situation);
	}

	public void setScalarSituation(String situation) {
		setString(NX_SITUATION, situation);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getScalarDescription() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setScalarDescription(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getPreparation_date() {
		return getDataset(NX_PREPARATION_DATE);
	}

	@Override
	public Date getScalarPreparation_date() {
		return getDate(NX_PREPARATION_DATE);
	}

	public void setPreparation_date(IDataset preparation_date) {
		setDataset(NX_PREPARATION_DATE, preparation_date);
	}

	public void setScalarPreparation_date(Date preparation_date) {
		setDate(NX_PREPARATION_DATE, preparation_date);
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

	@Override
	public String getScalarComponent() {
		return getString(NX_COMPONENT);
	}

	public void setComponent(IDataset component) {
		setDataset(NX_COMPONENT, component);
	}

	public void setScalarComponent(String component) {
		setString(NX_COMPONENT, component);
	}

	@Override
	public IDataset getSample_component() {
		return getDataset(NX_SAMPLE_COMPONENT);
	}

	@Override
	public String getScalarSample_component() {
		return getString(NX_SAMPLE_COMPONENT);
	}

	public void setSample_component(IDataset sample_component) {
		setDataset(NX_SAMPLE_COMPONENT, sample_component);
	}

	public void setScalarSample_component(String sample_component) {
		setString(NX_SAMPLE_COMPONENT, sample_component);
	}

	@Override
	public IDataset getConcentration() {
		return getDataset(NX_CONCENTRATION);
	}

	@Override
	public double getScalarConcentration() {
		return getDouble(NX_CONCENTRATION);
	}

	public void setConcentration(IDataset concentration) {
		setDataset(NX_CONCENTRATION, concentration);
	}

	public void setScalarConcentration(double concentration) {
		setField(NX_CONCENTRATION, concentration);
	}

	@Override
	public IDataset getVolume_fraction() {
		return getDataset(NX_VOLUME_FRACTION);
	}

	@Override
	public double getScalarVolume_fraction() {
		return getDouble(NX_VOLUME_FRACTION);
	}

	public void setVolume_fraction(IDataset volume_fraction) {
		setDataset(NX_VOLUME_FRACTION, volume_fraction);
	}

	public void setScalarVolume_fraction(double volume_fraction) {
		setField(NX_VOLUME_FRACTION, volume_fraction);
	}

	@Override
	public IDataset getScattering_length_density() {
		return getDataset(NX_SCATTERING_LENGTH_DENSITY);
	}

	@Override
	public double getScalarScattering_length_density() {
		return getDouble(NX_SCATTERING_LENGTH_DENSITY);
	}

	public void setScattering_length_density(IDataset scattering_length_density) {
		setDataset(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	public void setScalarScattering_length_density(double scattering_length_density) {
		setField(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	@Override
	public IDataset getUnit_cell_class() {
		return getDataset(NX_UNIT_CELL_CLASS);
	}

	@Override
	public String getScalarUnit_cell_class() {
		return getString(NX_UNIT_CELL_CLASS);
	}

	public void setUnit_cell_class(IDataset unit_cell_class) {
		setDataset(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	public void setScalarUnit_cell_class(String unit_cell_class) {
		setString(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	@Override
	public IDataset getUnit_cell_group() {
		return getDataset(NX_UNIT_CELL_GROUP);
	}

	@Override
	public String getScalarUnit_cell_group() {
		return getString(NX_UNIT_CELL_GROUP);
	}

	public void setUnit_cell_group(IDataset unit_cell_group) {
		setDataset(NX_UNIT_CELL_GROUP, unit_cell_group);
	}

	public void setScalarUnit_cell_group(String unit_cell_group) {
		setString(NX_UNIT_CELL_GROUP, unit_cell_group);
	}

	@Override
	public IDataset getPath_length() {
		return getDataset(NX_PATH_LENGTH);
	}

	@Override
	public double getScalarPath_length() {
		return getDouble(NX_PATH_LENGTH);
	}

	public void setPath_length(IDataset path_length) {
		setDataset(NX_PATH_LENGTH, path_length);
	}

	public void setScalarPath_length(double path_length) {
		setField(NX_PATH_LENGTH, path_length);
	}

	@Override
	public IDataset getPath_length_window() {
		return getDataset(NX_PATH_LENGTH_WINDOW);
	}

	@Override
	public double getScalarPath_length_window() {
		return getDouble(NX_PATH_LENGTH_WINDOW);
	}

	public void setPath_length_window(IDataset path_length_window) {
		setDataset(NX_PATH_LENGTH_WINDOW, path_length_window);
	}

	public void setScalarPath_length_window(double path_length_window) {
		setField(NX_PATH_LENGTH_WINDOW, path_length_window);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	@Override
	public double getScalarThickness() {
		return getDouble(NX_THICKNESS);
	}

	public void setThickness(IDataset thickness) {
		setDataset(NX_THICKNESS, thickness);
	}

	public void setScalarThickness(double thickness) {
		setField(NX_THICKNESS, thickness);
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

	@Override
	public double getScalarExternal_DAC() {
		return getDouble(NX_EXTERNAL_DAC);
	}

	public void setExternal_DAC(IDataset external_DAC) {
		setDataset(NX_EXTERNAL_DAC, external_DAC);
	}

	public void setScalarExternal_DAC(double external_DAC) {
		setField(NX_EXTERNAL_DAC, external_DAC);
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

	@Override
	public String getScalarShort_title() {
		return getString(NX_SHORT_TITLE);
	}

	public void setShort_title(IDataset short_title) {
		setDataset(NX_SHORT_TITLE, short_title);
	}

	public void setScalarShort_title(String short_title) {
		setString(NX_SHORT_TITLE, short_title);
	}

	@Override
	public IDataset getRotation_angle() {
		return getDataset(NX_ROTATION_ANGLE);
	}

	@Override
	public double getScalarRotation_angle() {
		return getDouble(NX_ROTATION_ANGLE);
	}

	public void setRotation_angle(IDataset rotation_angle) {
		setDataset(NX_ROTATION_ANGLE, rotation_angle);
	}

	public void setScalarRotation_angle(double rotation_angle) {
		setField(NX_ROTATION_ANGLE, rotation_angle);
	}

	@Override
	public IDataset getX_translation() {
		return getDataset(NX_X_TRANSLATION);
	}

	@Override
	public double getScalarX_translation() {
		return getDouble(NX_X_TRANSLATION);
	}

	public void setX_translation(IDataset x_translation) {
		setDataset(NX_X_TRANSLATION, x_translation);
	}

	public void setScalarX_translation(double x_translation) {
		setField(NX_X_TRANSLATION, x_translation);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getScalarDistance() {
		return getDouble(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	public void setScalarDistance(double distance) {
		setField(NX_DISTANCE, distance);
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
