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

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * Any information on the sample.
 * This could include scanned variables that
 * are associated with one of the data dimensions, e.g. the magnetic field, or
 * logged data, e.g. monitored temperature vs elapsed time.
 * 
 * @version 1.0
 */
public class NXsampleImpl extends NXobjectImpl implements NXsample {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_BEAM,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_ENVIRONMENT,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_ENVIRONMENT,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_POSITIONER);

	public NXsampleImpl() {
		super();
	}

	public NXsampleImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsample.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SAMPLE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getNameScalar() {
		return getString(NX_NAME);
	}

	@Override
	public DataNode setName(IDataset name) {
		return setDataset(NX_NAME, name);
	}

	@Override
	public DataNode setNameScalar(String name) {
		return setString(NX_NAME, name);
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
	public IDataset getElectric_field() {
		return getDataset(NX_ELECTRIC_FIELD);
	}

	@Override
	public double getElectric_fieldScalar() {
		return getDouble(NX_ELECTRIC_FIELD);
	}

	@Override
	public DataNode setElectric_field(IDataset electric_field) {
		return setDataset(NX_ELECTRIC_FIELD, electric_field);
	}

	@Override
	public DataNode setElectric_fieldScalar(double electric_field) {
		return setField(NX_ELECTRIC_FIELD, electric_field);
	}

	@Override
	public String getElectric_fieldAttributeDirection() {
		return getAttrString(NX_ELECTRIC_FIELD, NX_ELECTRIC_FIELD_ATTRIBUTE_DIRECTION);
	}

	@Override
	public void setElectric_fieldAttributeDirection(String direction) {
		setAttribute(NX_ELECTRIC_FIELD, NX_ELECTRIC_FIELD_ATTRIBUTE_DIRECTION, direction);
	}

	@Override
	public IDataset getMagnetic_field() {
		return getDataset(NX_MAGNETIC_FIELD);
	}

	@Override
	public double getMagnetic_fieldScalar() {
		return getDouble(NX_MAGNETIC_FIELD);
	}

	@Override
	public DataNode setMagnetic_field(IDataset magnetic_field) {
		return setDataset(NX_MAGNETIC_FIELD, magnetic_field);
	}

	@Override
	public DataNode setMagnetic_fieldScalar(double magnetic_field) {
		return setField(NX_MAGNETIC_FIELD, magnetic_field);
	}

	@Override
	public String getMagnetic_fieldAttributeDirection() {
		return getAttrString(NX_MAGNETIC_FIELD, NX_MAGNETIC_FIELD_ATTRIBUTE_DIRECTION);
	}

	@Override
	public void setMagnetic_fieldAttributeDirection(String direction) {
		setAttribute(NX_MAGNETIC_FIELD, NX_MAGNETIC_FIELD_ATTRIBUTE_DIRECTION, direction);
	}

	@Override
	public IDataset getStress_field() {
		return getDataset(NX_STRESS_FIELD);
	}

	@Override
	public double getStress_fieldScalar() {
		return getDouble(NX_STRESS_FIELD);
	}

	@Override
	public DataNode setStress_field(IDataset stress_field) {
		return setDataset(NX_STRESS_FIELD, stress_field);
	}

	@Override
	public DataNode setStress_fieldScalar(double stress_field) {
		return setField(NX_STRESS_FIELD, stress_field);
	}

	@Override
	public String getStress_fieldAttributeDirection() {
		return getAttrString(NX_STRESS_FIELD, NX_STRESS_FIELD_ATTRIBUTE_DIRECTION);
	}

	@Override
	public void setStress_fieldAttributeDirection(String direction) {
		setAttribute(NX_STRESS_FIELD, NX_STRESS_FIELD_ATTRIBUTE_DIRECTION, direction);
	}

	@Override
	public IDataset getPressure() {
		return getDataset(NX_PRESSURE);
	}

	@Override
	public double getPressureScalar() {
		return getDouble(NX_PRESSURE);
	}

	@Override
	public DataNode setPressure(IDataset pressure) {
		return setDataset(NX_PRESSURE, pressure);
	}

	@Override
	public DataNode setPressureScalar(double pressure) {
		return setField(NX_PRESSURE, pressure);
	}

	@Override
	public IDataset getChanger_position() {
		return getDataset(NX_CHANGER_POSITION);
	}

	@Override
	public long getChanger_positionScalar() {
		return getLong(NX_CHANGER_POSITION);
	}

	@Override
	public DataNode setChanger_position(IDataset changer_position) {
		return setDataset(NX_CHANGER_POSITION, changer_position);
	}

	@Override
	public DataNode setChanger_positionScalar(long changer_position) {
		return setField(NX_CHANGER_POSITION, changer_position);
	}

	@Override
	public IDataset getUnit_cell() {
		return getDataset(NX_UNIT_CELL);
	}

	@Override
	public double getUnit_cellScalar() {
		return getDouble(NX_UNIT_CELL);
	}

	@Override
	public DataNode setUnit_cell(IDataset unit_cell) {
		return setDataset(NX_UNIT_CELL, unit_cell);
	}

	@Override
	public DataNode setUnit_cellScalar(double unit_cell) {
		return setField(NX_UNIT_CELL, unit_cell);
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
	public IDataset getSample_orientation() {
		return getDataset(NX_SAMPLE_ORIENTATION);
	}

	@Override
	public double getSample_orientationScalar() {
		return getDouble(NX_SAMPLE_ORIENTATION);
	}

	@Override
	public DataNode setSample_orientation(IDataset sample_orientation) {
		return setDataset(NX_SAMPLE_ORIENTATION, sample_orientation);
	}

	@Override
	public DataNode setSample_orientationScalar(double sample_orientation) {
		return setField(NX_SAMPLE_ORIENTATION, sample_orientation);
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
	public IDataset getMass() {
		return getDataset(NX_MASS);
	}

	@Override
	public double getMassScalar() {
		return getDouble(NX_MASS);
	}

	@Override
	public DataNode setMass(IDataset mass) {
		return setDataset(NX_MASS, mass);
	}

	@Override
	public DataNode setMassScalar(double mass) {
		return setField(NX_MASS, mass);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public double getDensityScalar() {
		return getDouble(NX_DENSITY);
	}

	@Override
	public DataNode setDensity(IDataset density) {
		return setDataset(NX_DENSITY, density);
	}

	@Override
	public DataNode setDensityScalar(double density) {
		return setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getRelative_molecular_mass() {
		return getDataset(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public double getRelative_molecular_massScalar() {
		return getDouble(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public DataNode setRelative_molecular_mass(IDataset relative_molecular_mass) {
		return setDataset(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public DataNode setRelative_molecular_massScalar(double relative_molecular_mass) {
		return setField(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
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
	public IDataset getSituation() {
		return getDataset(NX_SITUATION);
	}

	@Override
	public String getSituationScalar() {
		return getString(NX_SITUATION);
	}

	@Override
	public DataNode setSituation(IDataset situation) {
		return setDataset(NX_SITUATION, situation);
	}

	@Override
	public DataNode setSituationScalar(String situation) {
		return setString(NX_SITUATION, situation);
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
	public IDataset getPreparation_date() {
		return getDataset(NX_PREPARATION_DATE);
	}

	@Override
	public Date getPreparation_dateScalar() {
		return getDate(NX_PREPARATION_DATE);
	}

	@Override
	public DataNode setPreparation_date(IDataset preparation_date) {
		return setDataset(NX_PREPARATION_DATE, preparation_date);
	}

	@Override
	public DataNode setPreparation_dateScalar(Date preparation_date) {
		return setDate(NX_PREPARATION_DATE, preparation_date);
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
	public NXbeam getBeam() {
		return getChild("beam", NXbeam.class);
	}

	@Override
	public void setBeam(NXbeam beam) {
		putChild("beam", beam);
	}

	@Override
	public NXbeam getBeam(String name) {
		return getChild(name, NXbeam.class);
	}

	@Override
	public void setBeam(String name, NXbeam beam) {
		putChild(name, beam);
	}

	@Override
	public Map<String, NXbeam> getAllBeam() {
		return getChildren(NXbeam.class);
	}
	
	@Override
	public void setAllBeam(Map<String, NXbeam> beam) {
		setChildren(beam);
	}

	@Override
	public IDataset getComponent() {
		return getDataset(NX_COMPONENT);
	}

	@Override
	public String getComponentScalar() {
		return getString(NX_COMPONENT);
	}

	@Override
	public DataNode setComponent(IDataset component) {
		return setDataset(NX_COMPONENT, component);
	}

	@Override
	public DataNode setComponentScalar(String component) {
		return setString(NX_COMPONENT, component);
	}

	@Override
	public IDataset getSample_component() {
		return getDataset(NX_SAMPLE_COMPONENT);
	}

	@Override
	public String getSample_componentScalar() {
		return getString(NX_SAMPLE_COMPONENT);
	}

	@Override
	public DataNode setSample_component(IDataset sample_component) {
		return setDataset(NX_SAMPLE_COMPONENT, sample_component);
	}

	@Override
	public DataNode setSample_componentScalar(String sample_component) {
		return setString(NX_SAMPLE_COMPONENT, sample_component);
	}

	@Override
	public IDataset getConcentration() {
		return getDataset(NX_CONCENTRATION);
	}

	@Override
	public double getConcentrationScalar() {
		return getDouble(NX_CONCENTRATION);
	}

	@Override
	public DataNode setConcentration(IDataset concentration) {
		return setDataset(NX_CONCENTRATION, concentration);
	}

	@Override
	public DataNode setConcentrationScalar(double concentration) {
		return setField(NX_CONCENTRATION, concentration);
	}

	@Override
	public IDataset getVolume_fraction() {
		return getDataset(NX_VOLUME_FRACTION);
	}

	@Override
	public double getVolume_fractionScalar() {
		return getDouble(NX_VOLUME_FRACTION);
	}

	@Override
	public DataNode setVolume_fraction(IDataset volume_fraction) {
		return setDataset(NX_VOLUME_FRACTION, volume_fraction);
	}

	@Override
	public DataNode setVolume_fractionScalar(double volume_fraction) {
		return setField(NX_VOLUME_FRACTION, volume_fraction);
	}

	@Override
	public IDataset getScattering_length_density() {
		return getDataset(NX_SCATTERING_LENGTH_DENSITY);
	}

	@Override
	public double getScattering_length_densityScalar() {
		return getDouble(NX_SCATTERING_LENGTH_DENSITY);
	}

	@Override
	public DataNode setScattering_length_density(IDataset scattering_length_density) {
		return setDataset(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	@Override
	public DataNode setScattering_length_densityScalar(double scattering_length_density) {
		return setField(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	@Override
	public IDataset getUnit_cell_class() {
		return getDataset(NX_UNIT_CELL_CLASS);
	}

	@Override
	public String getUnit_cell_classScalar() {
		return getString(NX_UNIT_CELL_CLASS);
	}

	@Override
	public DataNode setUnit_cell_class(IDataset unit_cell_class) {
		return setDataset(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	@Override
	public DataNode setUnit_cell_classScalar(String unit_cell_class) {
		return setString(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	@Override
	public IDataset getUnit_cell_group() {
		return getDataset(NX_UNIT_CELL_GROUP);
	}

	@Override
	public String getUnit_cell_groupScalar() {
		return getString(NX_UNIT_CELL_GROUP);
	}

	@Override
	public DataNode setUnit_cell_group(IDataset unit_cell_group) {
		return setDataset(NX_UNIT_CELL_GROUP, unit_cell_group);
	}

	@Override
	public DataNode setUnit_cell_groupScalar(String unit_cell_group) {
		return setString(NX_UNIT_CELL_GROUP, unit_cell_group);
	}

	@Override
	public IDataset getPath_length() {
		return getDataset(NX_PATH_LENGTH);
	}

	@Override
	public double getPath_lengthScalar() {
		return getDouble(NX_PATH_LENGTH);
	}

	@Override
	public DataNode setPath_length(IDataset path_length) {
		return setDataset(NX_PATH_LENGTH, path_length);
	}

	@Override
	public DataNode setPath_lengthScalar(double path_length) {
		return setField(NX_PATH_LENGTH, path_length);
	}

	@Override
	public IDataset getPath_length_window() {
		return getDataset(NX_PATH_LENGTH_WINDOW);
	}

	@Override
	public double getPath_length_windowScalar() {
		return getDouble(NX_PATH_LENGTH_WINDOW);
	}

	@Override
	public DataNode setPath_length_window(IDataset path_length_window) {
		return setDataset(NX_PATH_LENGTH_WINDOW, path_length_window);
	}

	@Override
	public DataNode setPath_length_windowScalar(double path_length_window) {
		return setField(NX_PATH_LENGTH_WINDOW, path_length_window);
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
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	@Override
	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
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
	public NXenvironment getTemperature_env() {
		return getChild("temperature_env", NXenvironment.class);
	}

	@Override
	public void setTemperature_env(NXenvironment temperature_env) {
		putChild("temperature_env", temperature_env);
	}

	@Override
	public NXlog getMagnetic_field_log() {
		return getChild("magnetic_field_log", NXlog.class);
	}

	@Override
	public void setMagnetic_field_log(NXlog magnetic_field_log) {
		putChild("magnetic_field_log", magnetic_field_log);
	}

	@Override
	public NXenvironment getMagnetic_field_env() {
		return getChild("magnetic_field_env", NXenvironment.class);
	}

	@Override
	public void setMagnetic_field_env(NXenvironment magnetic_field_env) {
		putChild("magnetic_field_env", magnetic_field_env);
	}

	@Override
	public IDataset getExternal_DAC() {
		return getDataset(NX_EXTERNAL_DAC);
	}

	@Override
	public double getExternal_DACScalar() {
		return getDouble(NX_EXTERNAL_DAC);
	}

	@Override
	public DataNode setExternal_DAC(IDataset external_DAC) {
		return setDataset(NX_EXTERNAL_DAC, external_DAC);
	}

	@Override
	public DataNode setExternal_DACScalar(double external_DAC) {
		return setField(NX_EXTERNAL_DAC, external_DAC);
	}

	@Override
	public NXlog getExternal_ADC() {
		return getChild("external_ADC", NXlog.class);
	}

	@Override
	public void setExternal_ADC(NXlog external_ADC) {
		putChild("external_ADC", external_ADC);
	}

	@Override
	public IDataset getShort_title() {
		return getDataset(NX_SHORT_TITLE);
	}

	@Override
	public String getShort_titleScalar() {
		return getString(NX_SHORT_TITLE);
	}

	@Override
	public DataNode setShort_title(IDataset short_title) {
		return setDataset(NX_SHORT_TITLE, short_title);
	}

	@Override
	public DataNode setShort_titleScalar(String short_title) {
		return setString(NX_SHORT_TITLE, short_title);
	}

	@Override
	public IDataset getRotation_angle() {
		return getDataset(NX_ROTATION_ANGLE);
	}

	@Override
	public double getRotation_angleScalar() {
		return getDouble(NX_ROTATION_ANGLE);
	}

	@Override
	public DataNode setRotation_angle(IDataset rotation_angle) {
		return setDataset(NX_ROTATION_ANGLE, rotation_angle);
	}

	@Override
	public DataNode setRotation_angleScalar(double rotation_angle) {
		return setField(NX_ROTATION_ANGLE, rotation_angle);
	}

	@Override
	public IDataset getX_translation() {
		return getDataset(NX_X_TRANSLATION);
	}

	@Override
	public double getX_translationScalar() {
		return getDouble(NX_X_TRANSLATION);
	}

	@Override
	public DataNode setX_translation(IDataset x_translation) {
		return setDataset(NX_X_TRANSLATION, x_translation);
	}

	@Override
	public DataNode setX_translationScalar(double x_translation) {
		return setField(NX_X_TRANSLATION, x_translation);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	@Override
	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	@Override
	public DataNode setDistanceScalar(double distance) {
		return setField(NX_DISTANCE, distance);
	}

	@Override
	public NXpositioner getPositioner() {
		return getChild("positioner", NXpositioner.class);
	}

	@Override
	public void setPositioner(NXpositioner positioner) {
		putChild("positioner", positioner);
	}

	@Override
	public NXpositioner getPositioner(String name) {
		return getChild(name, NXpositioner.class);
	}

	@Override
	public void setPositioner(String name, NXpositioner positioner) {
		putChild(name, positioner);
	}

	@Override
	public Map<String, NXpositioner> getAllPositioner() {
		return getChildren(NXpositioner.class);
	}
	
	@Override
	public void setAllPositioner(Map<String, NXpositioner> positioner) {
		setChildren(positioner);
	}

}
