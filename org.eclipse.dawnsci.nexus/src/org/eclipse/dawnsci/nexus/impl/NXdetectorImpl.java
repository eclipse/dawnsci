/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a detector, detector bank, or multidetector.
 * 
 * @version 1.1
 */
public class NXdetectorImpl extends NXobjectImpl implements NXdetector {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TIME_OF_FLIGHT = "time_of_flight";
	public static final String NX_TIME_OF_FLIGHT_ATTRIBUTE_AXIS = "axis";
	public static final String NX_TIME_OF_FLIGHT_ATTRIBUTE_PRIMARY = "primary";
	public static final String NX_TIME_OF_FLIGHT_ATTRIBUTE_LONG_NAME = "long_name";
	public static final String NX_RAW_TIME_OF_FLIGHT = "raw_time_of_flight";
	public static final String NX_RAW_TIME_OF_FLIGHT_ATTRIBUTE_FREQUENCY = "frequency";
	public static final String NX_DETECTOR_NUMBER = "detector_number";
	public static final String NX_DATA = "data";
	public static final String NX_DATA_ATTRIBUTE_SIGNAL = "signal";
	public static final String NX_DATA_ATTRIBUTE_AXES = "axes";
	public static final String NX_DATA_ATTRIBUTE_LONG_NAME = "long_name";
	public static final String NX_DATA_ATTRIBUTE_CHECK_SUM = "check_sum";
	public static final String NX_DATA_ERROR = "data_error";
	public static final String NX_X_PIXEL_OFFSET = "x_pixel_offset";
	public static final String NX_X_PIXEL_OFFSET_ATTRIBUTE_AXIS = "axis";
	public static final String NX_X_PIXEL_OFFSET_ATTRIBUTE_PRIMARY = "primary";
	public static final String NX_X_PIXEL_OFFSET_ATTRIBUTE_LONG_NAME = "long_name";
	public static final String NX_Y_PIXEL_OFFSET = "y_pixel_offset";
	public static final String NX_Y_PIXEL_OFFSET_ATTRIBUTE_AXIS = "axis";
	public static final String NX_Y_PIXEL_OFFSET_ATTRIBUTE_PRIMARY = "primary";
	public static final String NX_Y_PIXEL_OFFSET_ATTRIBUTE_LONG_NAME = "long_name";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_POLAR_ANGLE = "polar_angle";
	public static final String NX_AZIMUTHAL_ANGLE = "azimuthal_angle";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_LOCAL_NAME = "local_name";
	public static final String NX_SOLID_ANGLE = "solid_angle";
	public static final String NX_X_PIXEL_SIZE = "x_pixel_size";
	public static final String NX_Y_PIXEL_SIZE = "y_pixel_size";
	public static final String NX_DEAD_TIME = "dead_time";
	public static final String NX_GAS_PRESSURE = "gas_pressure";
	public static final String NX_DETECTION_GAS_PATH = "detection_gas_path";
	public static final String NX_CRATE = "crate";
	public static final String NX_CRATE_ATTRIBUTE_LOCAL_NAME = "local_name";
	public static final String NX_SLOT = "slot";
	public static final String NX_SLOT_ATTRIBUTE_LOCAL_NAME = "local_name";
	public static final String NX_INPUT = "input";
	public static final String NX_INPUT_ATTRIBUTE_LOCAL_NAME = "local_name";
	public static final String NX_TYPE = "type";
	public static final String NX_CALIBRATION_DATE = "calibration_date";
	public static final String NX_LAYOUT = "layout";
	public static final String NX_COUNT_TIME = "count_time";
	public static final String NX_SEQUENCE_NUMBER = "sequence_number";
	public static final String NX_BEAM_CENTER_X = "beam_center_x";
	public static final String NX_BEAM_CENTER_Y = "beam_center_y";
	public static final String NX_FRAME_START_NUMBER = "frame_start_number";
	public static final String NX_DIAMETER = "diameter";
	public static final String NX_ACQUISITION_MODE = "acquisition_mode";
	public static final String NX_ANGULAR_CALIBRATION_APPLIED = "angular_calibration_applied";
	public static final String NX_ANGULAR_CALIBRATION = "angular_calibration";
	public static final String NX_FLATFIELD_APPLIED = "flatfield_applied";
	public static final String NX_FLATFIELD = "flatfield";
	public static final String NX_FLATFIELD_ERROR = "flatfield_error";
	public static final String NX_PIXEL_MASK_APPLIED = "pixel_mask_applied";
	public static final String NX_PIXEL_MASK = "pixel_mask";
	public static final String NX_COUNTRATE_CORRECTION__APPLIED = "countrate_correction__applied";
	public static final String NX_BIT_DEPTH_READOUT = "bit_depth_readout";
	public static final String NX_DETECTOR_READOUT_TIME = "detector_readout_time";
	public static final String NX_TRIGGER_DELAY_TIME = "trigger_delay_time";
	public static final String NX_TRIGGER_DEAD_TIME = "trigger_dead_time";
	public static final String NX_FRAME_TIME = "frame_time";
	public static final String NX_GAIN_SETTING = "gain_setting";
	public static final String NX_SATURATION_VALUE = "saturation_value";
	public static final String NX_NUMBER_OF_CYCLES = "number_of_cycles";
	public static final String NX_SENSOR_MATERIAL = "sensor_material";
	public static final String NX_SENSOR_THICKNESS = "sensor_thickness";
	public static final String NX_THRESHOLD_ENERGY = "threshold_energy";

	protected NXdetectorImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXdetector.class;
	}

	@Override
	public IDataset getTime_of_flight() {
		return getDataset(NX_TIME_OF_FLIGHT);
	}

	public void setTime_of_flight(IDataset time_of_flight) {
		setDataset(NX_TIME_OF_FLIGHT, time_of_flight);
	}

	@Override
	public long getTime_of_flightAttributeAxis() {
		return getAttrLong(NX_TIME_OF_FLIGHT, NX_TIME_OF_FLIGHT_ATTRIBUTE_AXIS);
	}

	public void setTime_of_flightAttributeAxis(long axis) {
		setAttribute(NX_TIME_OF_FLIGHT, NX_TIME_OF_FLIGHT_ATTRIBUTE_AXIS, axis);
	}

	@Override
	public long getTime_of_flightAttributePrimary() {
		return getAttrLong(NX_TIME_OF_FLIGHT, NX_TIME_OF_FLIGHT_ATTRIBUTE_PRIMARY);
	}

	public void setTime_of_flightAttributePrimary(long primary) {
		setAttribute(NX_TIME_OF_FLIGHT, NX_TIME_OF_FLIGHT_ATTRIBUTE_PRIMARY, primary);
	}

	@Override
	public String getTime_of_flightAttributeLong_name() {
		return getAttrString(NX_TIME_OF_FLIGHT, NX_TIME_OF_FLIGHT_ATTRIBUTE_LONG_NAME);
	}

	public void setTime_of_flightAttributeLong_name(String long_name) {
		setAttribute(NX_TIME_OF_FLIGHT, NX_TIME_OF_FLIGHT_ATTRIBUTE_LONG_NAME, long_name);
	}

	@Override
	public IDataset getRaw_time_of_flight() {
		return getDataset(NX_RAW_TIME_OF_FLIGHT);
	}

	public void setRaw_time_of_flight(IDataset raw_time_of_flight) {
		setDataset(NX_RAW_TIME_OF_FLIGHT, raw_time_of_flight);
	}

	@Override
	public Number getRaw_time_of_flightAttributeFrequency() {
		return getAttrNumber(NX_RAW_TIME_OF_FLIGHT, NX_RAW_TIME_OF_FLIGHT_ATTRIBUTE_FREQUENCY);
	}

	public void setRaw_time_of_flightAttributeFrequency(Number frequency) {
		setAttribute(NX_RAW_TIME_OF_FLIGHT, NX_RAW_TIME_OF_FLIGHT_ATTRIBUTE_FREQUENCY, frequency);
	}

	@Override
	public IDataset getDetector_number() {
		return getDataset(NX_DETECTOR_NUMBER);
	}

	public void setDetector_number(IDataset detector_number) {
		setDataset(NX_DETECTOR_NUMBER, detector_number);
	}

	@Override
	public IDataset getData() {
		return getDataset(NX_DATA);
	}

	public void setData(IDataset data) {
		setDataset(NX_DATA, data);
	}

	@Override
	public long getDataAttributeSignal() {
		return getAttrLong(NX_DATA, NX_DATA_ATTRIBUTE_SIGNAL);
	}

	public void setDataAttributeSignal(long signal) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_SIGNAL, signal);
	}

	@Override
	public String getDataAttributeAxes() {
		return getAttrString(NX_DATA, NX_DATA_ATTRIBUTE_AXES);
	}

	public void setDataAttributeAxes(String axes) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_AXES, axes);
	}

	@Override
	public String getDataAttributeLong_name() {
		return getAttrString(NX_DATA, NX_DATA_ATTRIBUTE_LONG_NAME);
	}

	public void setDataAttributeLong_name(String long_name) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_LONG_NAME, long_name);
	}

	@Override
	public long getDataAttributeCheck_sum() {
		return getAttrLong(NX_DATA, NX_DATA_ATTRIBUTE_CHECK_SUM);
	}

	public void setDataAttributeCheck_sum(long check_sum) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_CHECK_SUM, check_sum);
	}

	@Override
	public IDataset getData_error() {
		return getDataset(NX_DATA_ERROR);
	}

	public void setData_error(IDataset data_error) {
		setDataset(NX_DATA_ERROR, data_error);
	}

	@Override
	public IDataset getX_pixel_offset() {
		return getDataset(NX_X_PIXEL_OFFSET);
	}

	public void setX_pixel_offset(IDataset x_pixel_offset) {
		setDataset(NX_X_PIXEL_OFFSET, x_pixel_offset);
	}

	@Override
	public long getX_pixel_offsetAttributeAxis() {
		return getAttrLong(NX_X_PIXEL_OFFSET, NX_X_PIXEL_OFFSET_ATTRIBUTE_AXIS);
	}

	public void setX_pixel_offsetAttributeAxis(long axis) {
		setAttribute(NX_X_PIXEL_OFFSET, NX_X_PIXEL_OFFSET_ATTRIBUTE_AXIS, axis);
	}

	@Override
	public long getX_pixel_offsetAttributePrimary() {
		return getAttrLong(NX_X_PIXEL_OFFSET, NX_X_PIXEL_OFFSET_ATTRIBUTE_PRIMARY);
	}

	public void setX_pixel_offsetAttributePrimary(long primary) {
		setAttribute(NX_X_PIXEL_OFFSET, NX_X_PIXEL_OFFSET_ATTRIBUTE_PRIMARY, primary);
	}

	@Override
	public String getX_pixel_offsetAttributeLong_name() {
		return getAttrString(NX_X_PIXEL_OFFSET, NX_X_PIXEL_OFFSET_ATTRIBUTE_LONG_NAME);
	}

	public void setX_pixel_offsetAttributeLong_name(String long_name) {
		setAttribute(NX_X_PIXEL_OFFSET, NX_X_PIXEL_OFFSET_ATTRIBUTE_LONG_NAME, long_name);
	}

	@Override
	public IDataset getY_pixel_offset() {
		return getDataset(NX_Y_PIXEL_OFFSET);
	}

	public void setY_pixel_offset(IDataset y_pixel_offset) {
		setDataset(NX_Y_PIXEL_OFFSET, y_pixel_offset);
	}

	@Override
	public long getY_pixel_offsetAttributeAxis() {
		return getAttrLong(NX_Y_PIXEL_OFFSET, NX_Y_PIXEL_OFFSET_ATTRIBUTE_AXIS);
	}

	public void setY_pixel_offsetAttributeAxis(long axis) {
		setAttribute(NX_Y_PIXEL_OFFSET, NX_Y_PIXEL_OFFSET_ATTRIBUTE_AXIS, axis);
	}

	@Override
	public long getY_pixel_offsetAttributePrimary() {
		return getAttrLong(NX_Y_PIXEL_OFFSET, NX_Y_PIXEL_OFFSET_ATTRIBUTE_PRIMARY);
	}

	public void setY_pixel_offsetAttributePrimary(long primary) {
		setAttribute(NX_Y_PIXEL_OFFSET, NX_Y_PIXEL_OFFSET_ATTRIBUTE_PRIMARY, primary);
	}

	@Override
	public String getY_pixel_offsetAttributeLong_name() {
		return getAttrString(NX_Y_PIXEL_OFFSET, NX_Y_PIXEL_OFFSET_ATTRIBUTE_LONG_NAME);
	}

	public void setY_pixel_offsetAttributeLong_name(String long_name) {
		setAttribute(NX_Y_PIXEL_OFFSET, NX_Y_PIXEL_OFFSET_ATTRIBUTE_LONG_NAME, long_name);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getPolar_angle() {
		return getDataset(NX_POLAR_ANGLE);
	}

	public void setPolar_angle(IDataset polar_angle) {
		setDataset(NX_POLAR_ANGLE, polar_angle);
	}

	@Override
	public IDataset getAzimuthal_angle() {
		return getDataset(NX_AZIMUTHAL_ANGLE);
	}

	public void setAzimuthal_angle(IDataset azimuthal_angle) {
		setDataset(NX_AZIMUTHAL_ANGLE, azimuthal_angle);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getLocal_name() {
		return getDataset(NX_LOCAL_NAME);
	}

	public void setLocal_name(IDataset local_name) {
		setDataset(NX_LOCAL_NAME, local_name);
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
	public IDataset getSolid_angle() {
		return getDataset(NX_SOLID_ANGLE);
	}

	public void setSolid_angle(IDataset solid_angle) {
		setDataset(NX_SOLID_ANGLE, solid_angle);
	}

	@Override
	public IDataset getX_pixel_size() {
		return getDataset(NX_X_PIXEL_SIZE);
	}

	public void setX_pixel_size(IDataset x_pixel_size) {
		setDataset(NX_X_PIXEL_SIZE, x_pixel_size);
	}

	@Override
	public IDataset getY_pixel_size() {
		return getDataset(NX_Y_PIXEL_SIZE);
	}

	public void setY_pixel_size(IDataset y_pixel_size) {
		setDataset(NX_Y_PIXEL_SIZE, y_pixel_size);
	}

	@Override
	public IDataset getDead_time() {
		return getDataset(NX_DEAD_TIME);
	}

	public void setDead_time(IDataset dead_time) {
		setDataset(NX_DEAD_TIME, dead_time);
	}

	@Override
	public IDataset getGas_pressure() {
		return getDataset(NX_GAS_PRESSURE);
	}

	public void setGas_pressure(IDataset gas_pressure) {
		setDataset(NX_GAS_PRESSURE, gas_pressure);
	}

	@Override
	public IDataset getDetection_gas_path() {
		return getDataset(NX_DETECTION_GAS_PATH);
	}

	public void setDetection_gas_path(IDataset detection_gas_path) {
		setDataset(NX_DETECTION_GAS_PATH, detection_gas_path);
	}

	@Override
	public IDataset getCrate() {
		return getDataset(NX_CRATE);
	}

	public void setCrate(IDataset crate) {
		setDataset(NX_CRATE, crate);
	}

	@Override
	public String getCrateAttributeLocal_name() {
		return getAttrString(NX_CRATE, NX_CRATE_ATTRIBUTE_LOCAL_NAME);
	}

	public void setCrateAttributeLocal_name(String local_name) {
		setAttribute(NX_CRATE, NX_CRATE_ATTRIBUTE_LOCAL_NAME, local_name);
	}

	@Override
	public IDataset getSlot() {
		return getDataset(NX_SLOT);
	}

	public void setSlot(IDataset slot) {
		setDataset(NX_SLOT, slot);
	}

	@Override
	public String getSlotAttributeLocal_name() {
		return getAttrString(NX_SLOT, NX_SLOT_ATTRIBUTE_LOCAL_NAME);
	}

	public void setSlotAttributeLocal_name(String local_name) {
		setAttribute(NX_SLOT, NX_SLOT_ATTRIBUTE_LOCAL_NAME, local_name);
	}

	@Override
	public IDataset getInput() {
		return getDataset(NX_INPUT);
	}

	public void setInput(IDataset input) {
		setDataset(NX_INPUT, input);
	}

	@Override
	public String getInputAttributeLocal_name() {
		return getAttrString(NX_INPUT, NX_INPUT_ATTRIBUTE_LOCAL_NAME);
	}

	public void setInputAttributeLocal_name(String local_name) {
		setAttribute(NX_INPUT, NX_INPUT_ATTRIBUTE_LOCAL_NAME, local_name);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public NXdata getEfficiency() {
		return getChild("efficiency", NXdata.class);
	}

	public void setEfficiency(NXdata efficiency) {
		putChild("efficiency", efficiency);
	}

	@Override
	public IDataset getCalibration_date() {
		return getDataset(NX_CALIBRATION_DATE);
	}

	public void setCalibration_date(IDataset calibration_date) {
		setDataset(NX_CALIBRATION_DATE, calibration_date);
	}

	@Override
	public NXnote getCalibration_method() {
		return getChild("calibration_method", NXnote.class);
	}

	public void setCalibration_method(NXnote calibration_method) {
		putChild("calibration_method", calibration_method);
	}

	@Override
	public IDataset getLayout() {
		return getDataset(NX_LAYOUT);
	}

	public void setLayout(IDataset layout) {
		setDataset(NX_LAYOUT, layout);
	}

	@Override
	public IDataset getCount_time() {
		return getDataset(NX_COUNT_TIME);
	}

	public void setCount_time(IDataset count_time) {
		setDataset(NX_COUNT_TIME, count_time);
	}

	@Override
	public NXnote getData_file() {
		return getChild("data_file", NXnote.class);
	}

	public void setData_file(NXnote data_file) {
		putChild("data_file", data_file);
	}

	@Override
	@Deprecated
	public NXcharacterization getCharacterization() {
		return getChild("characterization", NXcharacterization.class);
	}

	@Deprecated
	public void setCharacterization(NXcharacterization characterization) {
		putChild("characterization", characterization);
	}

	@Override
	@Deprecated
	public NXcharacterization getCharacterization(String name) {
		return getChild(name, NXcharacterization.class);
	}

	@Deprecated
	public void setCharacterization(String name, NXcharacterization characterization) {
		putChild(name, characterization);
	}

	@Override
	@Deprecated
	public Map<String, NXcharacterization> getAllCharacterization() {
		return getChildren(NXcharacterization.class);
	}

	@Deprecated
	public void setAllCharacterization(Map<String, NXcharacterization> characterization) {
		setChildren(characterization);
	}

	@Override
	public NXcollection getCollection() {
		return getChild("collection", NXcollection.class);
	}

	public void setCollection(NXcollection collection) {
		putChild("collection", collection);
	}

	@Override
	public NXcollection getCollection(String name) {
		return getChild(name, NXcollection.class);
	}

	public void setCollection(String name, NXcollection collection) {
		putChild(name, collection);
	}

	@Override
	public Map<String, NXcollection> getAllCollection() {
		return getChildren(NXcollection.class);
	}

	public void setAllCollection(Map<String, NXcollection> collection) {
		setChildren(collection);
	}

	@Override
	public IDataset getSequence_number() {
		return getDataset(NX_SEQUENCE_NUMBER);
	}

	public void setSequence_number(IDataset sequence_number) {
		setDataset(NX_SEQUENCE_NUMBER, sequence_number);
	}

	@Override
	public IDataset getBeam_center_x() {
		return getDataset(NX_BEAM_CENTER_X);
	}

	public void setBeam_center_x(IDataset beam_center_x) {
		setDataset(NX_BEAM_CENTER_X, beam_center_x);
	}

	@Override
	public IDataset getBeam_center_y() {
		return getDataset(NX_BEAM_CENTER_Y);
	}

	public void setBeam_center_y(IDataset beam_center_y) {
		setDataset(NX_BEAM_CENTER_Y, beam_center_y);
	}

	@Override
	public IDataset getFrame_start_number() {
		return getDataset(NX_FRAME_START_NUMBER);
	}

	public void setFrame_start_number(IDataset frame_start_number) {
		setDataset(NX_FRAME_START_NUMBER, frame_start_number);
	}

	@Override
	public IDataset getDiameter() {
		return getDataset(NX_DIAMETER);
	}

	public void setDiameter(IDataset diameter) {
		setDataset(NX_DIAMETER, diameter);
	}

	@Override
	public IDataset getAcquisition_mode() {
		return getDataset(NX_ACQUISITION_MODE);
	}

	public void setAcquisition_mode(IDataset acquisition_mode) {
		setDataset(NX_ACQUISITION_MODE, acquisition_mode);
	}

	@Override
	public IDataset getAngular_calibration_applied() {
		return getDataset(NX_ANGULAR_CALIBRATION_APPLIED);
	}

	public void setAngular_calibration_applied(IDataset angular_calibration_applied) {
		setDataset(NX_ANGULAR_CALIBRATION_APPLIED, angular_calibration_applied);
	}

	@Override
	public IDataset getAngular_calibration() {
		return getDataset(NX_ANGULAR_CALIBRATION);
	}

	public void setAngular_calibration(IDataset angular_calibration) {
		setDataset(NX_ANGULAR_CALIBRATION, angular_calibration);
	}

	@Override
	public IDataset getFlatfield_applied() {
		return getDataset(NX_FLATFIELD_APPLIED);
	}

	public void setFlatfield_applied(IDataset flatfield_applied) {
		setDataset(NX_FLATFIELD_APPLIED, flatfield_applied);
	}

	@Override
	public IDataset getFlatfield() {
		return getDataset(NX_FLATFIELD);
	}

	public void setFlatfield(IDataset flatfield) {
		setDataset(NX_FLATFIELD, flatfield);
	}

	@Override
	public IDataset getFlatfield_error() {
		return getDataset(NX_FLATFIELD_ERROR);
	}

	public void setFlatfield_error(IDataset flatfield_error) {
		setDataset(NX_FLATFIELD_ERROR, flatfield_error);
	}

	@Override
	public IDataset getPixel_mask_applied() {
		return getDataset(NX_PIXEL_MASK_APPLIED);
	}

	public void setPixel_mask_applied(IDataset pixel_mask_applied) {
		setDataset(NX_PIXEL_MASK_APPLIED, pixel_mask_applied);
	}

	@Override
	public IDataset getPixel_mask() {
		return getDataset(NX_PIXEL_MASK);
	}

	public void setPixel_mask(IDataset pixel_mask) {
		setDataset(NX_PIXEL_MASK, pixel_mask);
	}

	@Override
	public IDataset getCountrate_correction__applied() {
		return getDataset(NX_COUNTRATE_CORRECTION__APPLIED);
	}

	public void setCountrate_correction__applied(IDataset countrate_correction__applied) {
		setDataset(NX_COUNTRATE_CORRECTION__APPLIED, countrate_correction__applied);
	}

	@Override
	public IDataset getBit_depth_readout() {
		return getDataset(NX_BIT_DEPTH_READOUT);
	}

	public void setBit_depth_readout(IDataset bit_depth_readout) {
		setDataset(NX_BIT_DEPTH_READOUT, bit_depth_readout);
	}

	@Override
	public IDataset getDetector_readout_time() {
		return getDataset(NX_DETECTOR_READOUT_TIME);
	}

	public void setDetector_readout_time(IDataset detector_readout_time) {
		setDataset(NX_DETECTOR_READOUT_TIME, detector_readout_time);
	}

	@Override
	public IDataset getTrigger_delay_time() {
		return getDataset(NX_TRIGGER_DELAY_TIME);
	}

	public void setTrigger_delay_time(IDataset trigger_delay_time) {
		setDataset(NX_TRIGGER_DELAY_TIME, trigger_delay_time);
	}

	@Override
	public IDataset getTrigger_dead_time() {
		return getDataset(NX_TRIGGER_DEAD_TIME);
	}

	public void setTrigger_dead_time(IDataset trigger_dead_time) {
		setDataset(NX_TRIGGER_DEAD_TIME, trigger_dead_time);
	}

	@Override
	public IDataset getFrame_time() {
		return getDataset(NX_FRAME_TIME);
	}

	public void setFrame_time(IDataset frame_time) {
		setDataset(NX_FRAME_TIME, frame_time);
	}

	@Override
	public IDataset getGain_setting() {
		return getDataset(NX_GAIN_SETTING);
	}

	public void setGain_setting(IDataset gain_setting) {
		setDataset(NX_GAIN_SETTING, gain_setting);
	}

	@Override
	public IDataset getSaturation_value() {
		return getDataset(NX_SATURATION_VALUE);
	}

	public void setSaturation_value(IDataset saturation_value) {
		setDataset(NX_SATURATION_VALUE, saturation_value);
	}

	@Override
	public IDataset getNumber_of_cycles() {
		return getDataset(NX_NUMBER_OF_CYCLES);
	}

	public void setNumber_of_cycles(IDataset number_of_cycles) {
		setDataset(NX_NUMBER_OF_CYCLES, number_of_cycles);
	}

	@Override
	public IDataset getSensor_material() {
		return getDataset(NX_SENSOR_MATERIAL);
	}

	public void setSensor_material(IDataset sensor_material) {
		setDataset(NX_SENSOR_MATERIAL, sensor_material);
	}

	@Override
	public IDataset getSensor_thickness() {
		return getDataset(NX_SENSOR_THICKNESS);
	}

	public void setSensor_thickness(IDataset sensor_thickness) {
		setDataset(NX_SENSOR_THICKNESS, sensor_thickness);
	}

	@Override
	public IDataset getThreshold_energy() {
		return getDataset(NX_THRESHOLD_ENERGY);
	}

	public void setThreshold_energy(IDataset threshold_energy) {
		setDataset(NX_THRESHOLD_ENERGY, threshold_energy);
	}

	@Override
	public NXdetector_module getDetector_module() {
		return getChild("detector_module", NXdetector_module.class);
	}

	public void setDetector_module(NXdetector_module detector_module) {
		putChild("detector_module", detector_module);
	}

	@Override
	public NXdetector_module getDetector_module(String name) {
		return getChild(name, NXdetector_module.class);
	}

	public void setDetector_module(String name, NXdetector_module detector_module) {
		putChild(name, detector_module);
	}

	@Override
	public Map<String, NXdetector_module> getAllDetector_module() {
		return getChildren(NXdetector_module.class);
	}

	public void setAllDetector_module(Map<String, NXdetector_module> detector_module) {
		setChildren(detector_module);
	}

}
