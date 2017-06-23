/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T11:33:23.232+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.january.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXaperture;
import org.eclipse.dawnsci.nexus.NXcollimator;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXprocess;
import org.eclipse.dawnsci.nexus.NXnote;
import org.eclipse.dawnsci.nexus.NXcollection;

/**
 * Validator for the application definition 'NXcanSAS'.
 */
public class NXcanSASValidator extends AbstractNexusValidator implements NexusApplicationValidator {

	@Override
	public void validate(NXroot root) throws NexusValidationException {
		// validate unnamed child group of type NXentry (possibly multiple)
		final Map<String, NXentry> allEntry = root.getAllEntry();
		for (final NXentry entry : allEntry.values()) {
			validateGroup_NXentry(entry);
		}
	}

	@Override
	public void validate(NXentry entry) throws NexusValidationException {
		validateGroup_NXentry(entry);
	}

	@Override
	public void validate(NXsubentry subentry) throws NexusValidationException {
		validateGroup_NXentry(subentry);
	}


	/**
	 * Validate unnamed group of type NXentry.
	 */
	private void validateGroup_NXentry(final NXsubentry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXentry.class, group);

		// validate attribute 'default'
		final Attribute default_attr = group.getAttribute("default");
		validateAttributeNotNull("default", default_attr);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASentry");

		// validate attribute 'version'
		final Attribute version_attr = group.getAttribute("version");
		validateAttributeNotNull("version", version_attr);
		validateAttributeEnumeration("version", version_attr,
				"1.0");

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition", definition);
		validateFieldEnumeration("definition", definition,
				"NXcanSAS");

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title", title);

		// validate field 'run' of unknown type. Note: field not defined in base class.
		final IDataset run = group.getDataset("run");
		validateFieldNotNull("run", run);
		// validate attribute 'name' of field 'run'
		final Attribute run_attr_name = group.getAttribute("name");
		validateAttributeNotNull("name", run_attr_name);


		// validate unnamed child group of type NXdata (possibly multiple)
		final Map<String, NXdata> allData = group.getAllData();
		for (final NXdata data : allData.values()) {
			validateGroup_NXentry_NXdata(data);
		}

		// validate unnamed child group of type NXinstrument (possibly multiple)
		final Map<String, NXinstrument> allInstrument = group.getAllInstrument();
		for (final NXinstrument instrument : allInstrument.values()) {
			validateGroup_NXentry_NXinstrument(instrument);
		}

		// validate unnamed child group of type NXsample (possibly multiple)
		final Map<String, NXsample> allSample = group.getAllSample();
		for (final NXsample sample : allSample.values()) {
			validateGroup_NXentry_NXsample(sample);
		}

		// validate unnamed child group of type NXprocess (possibly multiple)
		final Map<String, NXprocess> allProcess = group.getAllProcess();
		for (final NXprocess process : allProcess.values()) {
			validateGroup_NXentry_NXprocess(process);
		}

		// validate unnamed child group of type NXcollection (possibly multiple)
		final Map<String, NXcollection> allCollection = group.getAllCollection();
		for (final NXcollection collection : allCollection.values()) {
			validateGroup_NXentry_NXcollection(collection);
		}

		// validate unnamed child group of type NXdata (possibly multiple)
		// Manually commented out this section as it uses a duplicate local variable name
		// This is due to NXcanSAS.nxdl.xml having two unnamed child group entries under NXentry.
		// We should decide what to do about this. Commenting out for now as validator are not currently used.
		// See http://jira.diamond.ac.uk/browse/DAQ-720
//		final Map<String, NXdata> allData = group.getAllData();
//		for (final NXdata data : allData.values()) {
//			validateGroup_NXentry_NXdata(data);
//		}
	}

	/**
	 * Validate unnamed group of type NXdata.
	 */
	private void validateGroup_NXentry_NXdata(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASdata");

		// validate attribute 'signal'
		final Attribute signal_attr = group.getAttribute("signal");
		validateAttributeNotNull("signal", signal_attr);
		validateAttributeType("signal", signal_attr, NX_CHAR);
		validateAttributeEnumeration("signal", signal_attr,
				"I");

		// validate attribute 'I_axes'
		final Attribute I_axes_attr = group.getAttribute("I_axes");
		validateAttributeNotNull("I_axes", I_axes_attr);

		// validate attribute 'Q_indices'
		final Attribute Q_indices_attr = group.getAttribute("Q_indices");
		validateAttributeNotNull("Q_indices", Q_indices_attr);
		validateAttributeType("Q_indices", Q_indices_attr, NX_INT);

		// validate attribute 'mask'
		final Attribute mask_attr = group.getAttribute("mask");
		validateAttributeNotNull("mask", mask_attr);
		validateAttributeType("mask", mask_attr, NX_CHAR);

		// validate attribute 'Mask_indices'
		final Attribute Mask_indices_attr = group.getAttribute("Mask_indices");
		validateAttributeNotNull("Mask_indices", Mask_indices_attr);

		// validate attribute 'timestamp'
		final Attribute timestamp_attr = group.getAttribute("timestamp");
		validateAttributeNotNull("timestamp", timestamp_attr);
		validateAttributeType("timestamp", timestamp_attr, NX_DATE_TIME);

		// validate field 'Q' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset Q = group.getDataset("Q");
		validateFieldNotNull("Q", Q);
		validateFieldType("Q", Q, NX_NUMBER);
		validateFieldUnits("Q", Q, NX_PER_LENGTH);
		// validate attribute 'units' of field 'Q'
		final Attribute Q_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", Q_attr_units);
		validateAttributeEnumeration("units", Q_attr_units,
				"1/m",
				"1/nm",
				"1/angstrom");

		// validate attribute 'uncertainties' of field 'Q'
		final Attribute Q_attr_uncertainties = group.getAttribute("uncertainties");
		validateAttributeNotNull("uncertainties", Q_attr_uncertainties);

		// validate attribute 'resolutions' of field 'Q'
		final Attribute Q_attr_resolutions = group.getAttribute("resolutions");
		validateAttributeNotNull("resolutions", Q_attr_resolutions);
		validateAttributeType("resolutions", Q_attr_resolutions, NX_CHAR);

		// validate attribute 'resolutions_description' of field 'Q'
		final Attribute Q_attr_resolutions_description = group.getAttribute("resolutions_description");
		validateAttributeNotNull("resolutions_description", Q_attr_resolutions_description);
		validateAttributeType("resolutions_description", Q_attr_resolutions_description, NX_CHAR);


		// validate field 'I' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset I = group.getDataset("I");
		validateFieldNotNull("I", I);
		validateFieldType("I", I, NX_NUMBER);
		// validate attribute 'units' of field 'I'
		final Attribute I_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", I_attr_units);
		validateAttributeEnumeration("units", I_attr_units,
				"1/m",
				"1/cm",
				"m2/g",
				"cm2/g",
				"arbitrary");

		// validate attribute 'uncertainties' of field 'I'
		final Attribute I_attr_uncertainties = group.getAttribute("uncertainties");
		validateAttributeNotNull("uncertainties", I_attr_uncertainties);

		// validate attribute 'scaling_factor' of field 'I'
		final Attribute I_attr_scaling_factor = group.getAttribute("scaling_factor");
		validateAttributeNotNull("scaling_factor", I_attr_scaling_factor);


		// validate optional field 'Idev' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset Idev = group.getDataset("Idev");
		if (Idev != null) {
			validateFieldType("Idev", Idev, NX_NUMBER);
		// validate attribute 'units' of field 'Idev'
		final Attribute Idev_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", Idev_attr_units);
		validateAttributeEnumeration("units", Idev_attr_units,
				"1/m",
				"1/cm",
				"m2/g",
				"cm2/g",
				"arbitrary");

		}

		// validate optional field 'Qdev' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset Qdev = group.getDataset("Qdev");
		if (Qdev != null) {
			validateFieldType("Qdev", Qdev, NX_NUMBER);
			validateFieldUnits("Qdev", Qdev, NX_PER_LENGTH);
		// validate attribute 'units' of field 'Qdev'
		final Attribute Qdev_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", Qdev_attr_units);
		validateAttributeEnumeration("units", Qdev_attr_units,
				"1/m",
				"1/nm",
				"1/angstrom");

		}

		// validate optional field 'dQw' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset dQw = group.getDataset("dQw");
		if (dQw != null) {
			validateFieldType("dQw", dQw, NX_NUMBER);
			validateFieldUnits("dQw", dQw, NX_PER_LENGTH);
		// validate attribute 'units' of field 'dQw'
		final Attribute dQw_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", dQw_attr_units);
		validateAttributeEnumeration("units", dQw_attr_units,
				"1/m",
				"1/nm",
				"1/angstrom");

		}

		// validate optional field 'dQl' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset dQl = group.getDataset("dQl");
		if (dQl != null) {
			validateFieldType("dQl", dQl, NX_NUMBER);
			validateFieldUnits("dQl", dQl, NX_PER_LENGTH);
		// validate attribute 'units' of field 'dQl'
		final Attribute dQl_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", dQl_attr_units);
		validateAttributeEnumeration("units", dQl_attr_units,
				"1/m",
				"1/nm",
				"1/angstrom");

		}

		// validate optional field 'Qmean' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset Qmean = group.getDataset("Qmean");
		if (Qmean != null) {
			validateFieldType("Qmean", Qmean, NX_NUMBER);
			validateFieldUnits("Qmean", Qmean, NX_PER_LENGTH);
		// validate attribute 'units' of field 'Qmean'
		final Attribute Qmean_attr_units = group.getAttribute("units");
		validateAttributeNotNull("units", Qmean_attr_units);
		validateAttributeEnumeration("units", Qmean_attr_units,
				"1/m",
				"1/nm",
				"1/angstrom");

		}

		// validate optional field 'ShadowFactor' of unknown type. Note: field not defined in base class.
		final IDataset ShadowFactor = group.getDataset("ShadowFactor");
		if (ShadowFactor != null) {
			validateFieldUnits("ShadowFactor", ShadowFactor, NX_DIMENSIONLESS);
		}
	}

	/**
	 * Validate optional unnamed group of type NXinstrument.
	 */
	private void validateGroup_NXentry_NXinstrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASinstrument");

		// validate unnamed child group of type NXaperture (possibly multiple)
		final Map<String, NXaperture> allAperture = group.getAllAperture();
		for (final NXaperture aperture : allAperture.values()) {
			validateGroup_NXentry_NXinstrument_NXaperture(aperture);
		}

		// validate unnamed child group of type NXcollimator (possibly multiple)
		final Map<String, NXcollimator> allCollimator = group.getAllCollimator();
		for (final NXcollimator collimator : allCollimator.values()) {
			validateGroup_NXentry_NXinstrument_NXcollimator(collimator);
		}

		// validate unnamed child group of type NXdetector (possibly multiple)
		final Map<String, NXdetector> allDetector = group.getAllDetector();
		for (final NXdetector detector : allDetector.values()) {
			validateGroup_NXentry_NXinstrument_NXdetector(detector);
		}

		// validate unnamed child group of type NXsource (possibly multiple)
		final Map<String, NXsource> allSource = group.getAllSource();
		for (final NXsource source : allSource.values()) {
			validateGroup_NXentry_NXinstrument_NXsource(source);
		}
	}

	/**
	 * Validate optional unnamed group of type NXaperture.
	 */
	private void validateGroup_NXentry_NXinstrument_NXaperture(final NXaperture group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXaperture.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASaperture");

		// validate field 'shape' of unknown type. Note: field not defined in base class.
		final IDataset shape = group.getDataset("shape");
		validateFieldNotNull("shape", shape);

		// validate optional field 'x_gap' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset x_gap = group.getDataset("x_gap");
		if (x_gap != null) {
			validateFieldType("x_gap", x_gap, NX_NUMBER);
			validateFieldUnits("x_gap", x_gap, NX_LENGTH);
		}

		// validate optional field 'y_gap' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset y_gap = group.getDataset("y_gap");
		if (y_gap != null) {
			validateFieldType("y_gap", y_gap, NX_NUMBER);
			validateFieldUnits("y_gap", y_gap, NX_LENGTH);
		}
	}

	/**
	 * Validate optional unnamed group of type NXcollimator.
	 */
	private void validateGroup_NXentry_NXinstrument_NXcollimator(final NXcollimator group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXcollimator.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SAScollimation");

		// validate optional field 'length' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset length = group.getDataset("length");
		if (length != null) {
			validateFieldType("length", length, NX_NUMBER);
			validateFieldUnits("length", length, NX_LENGTH);
		}

		// validate optional field 'distance' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset distance = group.getDataset("distance");
		if (distance != null) {
			validateFieldType("distance", distance, NX_NUMBER);
			validateFieldUnits("distance", distance, NX_LENGTH);
		}
	}

	/**
	 * Validate optional unnamed group of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_NXdetector(final NXdetector group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASdetector");

		// validate field 'name' of unknown type. Note: field not defined in base class.
		final IDataset name = group.getDataset("name");
		validateFieldNotNull("name", name);

		// validate optional field 'SDD' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset SDD = group.getDataset("SDD");
		if (SDD != null) {
			validateFieldType("SDD", SDD, NX_NUMBER);
			validateFieldUnits("SDD", SDD, NX_LENGTH);
		}

		// validate optional field 'slit_length' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset slit_length = group.getDataset("slit_length");
		if (slit_length != null) {
			validateFieldType("slit_length", slit_length, NX_NUMBER);
			validateFieldUnits("slit_length", slit_length, NX_PER_LENGTH);
		}

		// validate optional field 'x_position' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset x_position = group.getDataset("x_position");
		if (x_position != null) {
			validateFieldType("x_position", x_position, NX_NUMBER);
			validateFieldUnits("x_position", x_position, NX_LENGTH);
		}

		// validate optional field 'y_position' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset y_position = group.getDataset("y_position");
		if (y_position != null) {
			validateFieldType("y_position", y_position, NX_NUMBER);
			validateFieldUnits("y_position", y_position, NX_LENGTH);
		}

		// validate optional field 'roll' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset roll = group.getDataset("roll");
		if (roll != null) {
			validateFieldType("roll", roll, NX_NUMBER);
			validateFieldUnits("roll", roll, NX_ANGLE);
		}

		// validate optional field 'pitch' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset pitch = group.getDataset("pitch");
		if (pitch != null) {
			validateFieldType("pitch", pitch, NX_NUMBER);
			validateFieldUnits("pitch", pitch, NX_ANGLE);
		}

		// validate optional field 'yaw' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset yaw = group.getDataset("yaw");
		if (yaw != null) {
			validateFieldType("yaw", yaw, NX_NUMBER);
			validateFieldUnits("yaw", yaw, NX_ANGLE);
		}

		// validate optional field 'beam_center_x' of type NX_FLOAT.
		final IDataset beam_center_x = group.getBeam_center_x();
		if (beam_center_x != null) {
			validateFieldType("beam_center_x", beam_center_x, NX_FLOAT);
			validateFieldUnits("beam_center_x", beam_center_x, NX_LENGTH);
		}

		// validate optional field 'beam_center_y' of type NX_FLOAT.
		final IDataset beam_center_y = group.getBeam_center_y();
		if (beam_center_y != null) {
			validateFieldType("beam_center_y", beam_center_y, NX_FLOAT);
			validateFieldUnits("beam_center_y", beam_center_y, NX_LENGTH);
		}

		// validate optional field 'x_pixel_size' of type NX_FLOAT.
		final IDataset x_pixel_size = group.getX_pixel_size();
		if (x_pixel_size != null) {
			validateFieldType("x_pixel_size", x_pixel_size, NX_FLOAT);
			validateFieldUnits("x_pixel_size", x_pixel_size, NX_LENGTH);
			validateFieldRank("x_pixel_size", x_pixel_size, 2);
			validateFieldDimensions("x_pixel_size", x_pixel_size, "NXdetector", "i", "j");
		}

		// validate optional field 'y_pixel_size' of type NX_FLOAT.
		final IDataset y_pixel_size = group.getY_pixel_size();
		if (y_pixel_size != null) {
			validateFieldType("y_pixel_size", y_pixel_size, NX_FLOAT);
			validateFieldUnits("y_pixel_size", y_pixel_size, NX_LENGTH);
			validateFieldRank("y_pixel_size", y_pixel_size, 2);
			validateFieldDimensions("y_pixel_size", y_pixel_size, "NXdetector", "i", "j");
		}
	}

	/**
	 * Validate optional unnamed group of type NXsource.
	 */
	private void validateGroup_NXentry_NXinstrument_NXsource(final NXsource group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXsource.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASsource");

		// validate field 'radiation' of unknown type. Note: field not defined in base class.
		final IDataset radiation = group.getDataset("radiation");
		validateFieldNotNull("radiation", radiation);
		validateFieldEnumeration("radiation", radiation,
				"Spallation Neutron Source",
				"Pulsed Reactor Neutron Source",
				"Reactor Neutron Source",
				"Synchrotron X-ray Source",
				"Pulsed Muon Source",
				"Rotating Anode X-ray",
				"Fixed Tube X-ray",
				"UV Laser",
				"Free-Electron Laser",
				"Optical Laser",
				"Ion Source",
				"UV Plasma Source",
				"neutron",
				"x-ray",
				"muon",
				"electron",
				"ultraviolet",
				"visible light",
				"positron",
				"proton");

		// validate optional field 'beam_shape' of unknown type. Note: field not defined in base class.
		final IDataset beam_shape = group.getDataset("beam_shape");
		if (beam_shape != null) {
		}

		// validate optional field 'incident_wavelength' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset incident_wavelength = group.getDataset("incident_wavelength");
		if (incident_wavelength != null) {
			validateFieldType("incident_wavelength", incident_wavelength, NX_NUMBER);
			validateFieldUnits("incident_wavelength", incident_wavelength, NX_WAVELENGTH);
		}

		// validate optional field 'wavelength_min' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset wavelength_min = group.getDataset("wavelength_min");
		if (wavelength_min != null) {
			validateFieldType("wavelength_min", wavelength_min, NX_NUMBER);
			validateFieldUnits("wavelength_min", wavelength_min, NX_WAVELENGTH);
		}

		// validate optional field 'wavelength_max' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset wavelength_max = group.getDataset("wavelength_max");
		if (wavelength_max != null) {
			validateFieldType("wavelength_max", wavelength_max, NX_NUMBER);
			validateFieldUnits("wavelength_max", wavelength_max, NX_WAVELENGTH);
		}

		// validate optional field 'incident_wavelength_spread' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset incident_wavelength_spread = group.getDataset("incident_wavelength_spread");
		if (incident_wavelength_spread != null) {
			validateFieldType("incident_wavelength_spread", incident_wavelength_spread, NX_NUMBER);
			validateFieldUnits("incident_wavelength_spread", incident_wavelength_spread, NX_WAVELENGTH);
		}

		// validate optional field 'beam_size_x' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset beam_size_x = group.getDataset("beam_size_x");
		if (beam_size_x != null) {
			validateFieldType("beam_size_x", beam_size_x, NX_NUMBER);
			validateFieldUnits("beam_size_x", beam_size_x, NX_LENGTH);
		}

		// validate optional field 'beam_size_y' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset beam_size_y = group.getDataset("beam_size_y");
		if (beam_size_y != null) {
			validateFieldType("beam_size_y", beam_size_y, NX_NUMBER);
			validateFieldUnits("beam_size_y", beam_size_y, NX_LENGTH);
		}
	}

	/**
	 * Validate optional unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASsample");

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);

		// validate optional field 'thickness' of type NX_FLOAT.
		final IDataset thickness = group.getThickness();
		if (thickness != null) {
			validateFieldType("thickness", thickness, NX_FLOAT);
			validateFieldUnits("thickness", thickness, NX_LENGTH);
		}

		// validate optional field 'transmission' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset transmission = group.getDataset("transmission");
		if (transmission != null) {
			validateFieldType("transmission", transmission, NX_NUMBER);
			validateFieldUnits("transmission", transmission, NX_DIMENSIONLESS);
		}

		// validate optional field 'temperature' of type NX_NUMBER.
		final IDataset temperature = group.getTemperature();
		if (temperature != null) {
			validateFieldType("temperature", temperature, NX_NUMBER);
			validateFieldUnits("temperature", temperature, NX_TEMPERATURE);
			validateFieldDimensions("temperature", temperature, "NXsample", "n_Temp");
		}

		// validate optional field 'details' of unknown type. Note: field not defined in base class.
		final IDataset details = group.getDataset("details");
		if (details != null) {
		}

		// validate optional field 'x_position' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset x_position = group.getDataset("x_position");
		if (x_position != null) {
			validateFieldType("x_position", x_position, NX_NUMBER);
			validateFieldUnits("x_position", x_position, NX_LENGTH);
		}

		// validate optional field 'y_position' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset y_position = group.getDataset("y_position");
		if (y_position != null) {
			validateFieldType("y_position", y_position, NX_NUMBER);
			validateFieldUnits("y_position", y_position, NX_LENGTH);
		}

		// validate optional field 'roll' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset roll = group.getDataset("roll");
		if (roll != null) {
			validateFieldType("roll", roll, NX_NUMBER);
			validateFieldUnits("roll", roll, NX_ANGLE);
		}

		// validate optional field 'pitch' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset pitch = group.getDataset("pitch");
		if (pitch != null) {
			validateFieldType("pitch", pitch, NX_NUMBER);
			validateFieldUnits("pitch", pitch, NX_ANGLE);
		}

		// validate optional field 'yaw' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset yaw = group.getDataset("yaw");
		if (yaw != null) {
			validateFieldType("yaw", yaw, NX_NUMBER);
			validateFieldUnits("yaw", yaw, NX_ANGLE);
		}
	}

	/**
	 * Validate optional unnamed group of type NXprocess.
	 */
	private void validateGroup_NXentry_NXprocess(final NXprocess group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXprocess.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASprocess");

		// validate optional field 'name' of unknown type. Note: field not defined in base class.
		final IDataset name = group.getDataset("name");
		if (name != null) {
		}

		// validate optional field 'date' of type NX_DATE_TIME.
		final IDataset date = group.getDate();
		if (date != null) {
			validateFieldType("date", date, NX_DATE_TIME);
		}

		// validate optional field 'description' of unknown type. Note: field not defined in base class.
		final IDataset description = group.getDataset("description");
		if (description != null) {
		}

		// validate optional field 'term' of unknown type. Note: field not defined in base class.
		final IDataset term = group.getDataset("term");
		if (term != null) {
		}

		// validate unnamed child group of type NXnote (possibly multiple)
		final Map<String, NXnote> allNote = group.getAllNote();
		for (final NXnote note : allNote.values()) {
			validateGroup_NXentry_NXprocess_NXnote(note);
		}

		// validate unnamed child group of type NXcollection (possibly multiple)
		final Map<String, NXcollection> allCollection = group.getChildren(NXcollection.class);
		for (final NXcollection collection : allCollection.values()) {
			validateGroup_NXentry_NXprocess_NXcollection(collection);
		}
	}

	/**
	 * Validate optional unnamed group of type NXnote.
	 */
	private void validateGroup_NXentry_NXprocess_NXnote(final NXnote group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXnote.class, group);

	}

	/**
	 * Validate optional unnamed group of type NXcollection.
	 */
	private void validateGroup_NXentry_NXprocess_NXcollection(final NXcollection group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXcollection.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASprocessnote");

	}

	/**
	 * Validate optional unnamed group of type NXcollection.
	 */
	private void validateGroup_NXentry_NXcollection(final NXcollection group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXcollection.class, group);

		// validate attribute 'canSAS_class'
		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
				"SASnote");

	}

	// Manually commented out this method as it has a duplicate name.
	// This is due to NXcanSAS.nxdl.xml having two unnamed child group entries under NXentry.
	// We should decide what to do about this. Commenting out for now as validator are not currently used.
	// See http://jira.diamond.ac.uk/browse/DAQ-720
//	/**
//	 * Validate optional unnamed group of type NXdata.
//	 */
//	private void validateGroup_NXentry_NXdata(final NXdata group) throws NexusValidationException {
//		// validate that the group is not null
//		validateGroupNotNull(null, NXdata.class, group);
//		clearLocalGroupDimensionPlaceholderValues();
//
//		// validate attribute 'canSAS_class'
//		final Attribute canSAS_class_attr = group.getAttribute("canSAS_class");
//		validateAttributeNotNull("canSAS_class", canSAS_class_attr);
//		validateAttributeEnumeration("canSAS_class", canSAS_class_attr,
//				"SAStransmission_spectrum");
//
//		// validate attribute 'signal'
//		final Attribute signal_attr = group.getAttribute("signal");
//		validateAttributeNotNull("signal", signal_attr);
//		validateAttributeType("signal", signal_attr, NX_CHAR);
//		validateAttributeEnumeration("signal", signal_attr,
//				"T");
//
//		// validate attribute 'T_axes'
//		final Attribute T_axes_attr = group.getAttribute("T_axes");
//		validateAttributeNotNull("T_axes", T_axes_attr);
//		validateAttributeEnumeration("T_axes", T_axes_attr,
//				"T");
//
//		// validate attribute 'name'
//		final Attribute name_attr = group.getAttribute("name");
//		validateAttributeNotNull("name", name_attr);
//
//		// validate attribute 'timestamp'
//		final Attribute timestamp_attr = group.getAttribute("timestamp");
//		validateAttributeNotNull("timestamp", timestamp_attr);
//		validateAttributeType("timestamp", timestamp_attr, NX_DATE_TIME);
//
//		// validate field 'lambda' of type NX_NUMBER. Note: field not defined in base class.
//		final IDataset lambda = group.getDataset("lambda");
//		validateFieldNotNull("lambda", lambda);
//		validateFieldType("lambda", lambda, NX_NUMBER);
//		validateFieldUnits("lambda", lambda, NX_WAVELENGTH);
//
//		// validate field 'T' of type NX_NUMBER. Note: field not defined in base class.
//		final IDataset T = group.getDataset("T");
//		validateFieldNotNull("T", T);
//		validateFieldType("T", T, NX_NUMBER);
//		validateFieldUnits("T", T, NX_DIMENSIONLESS);
//		// validate attribute 'uncertainties' of field 'T'
//		final Attribute T_attr_uncertainties = group.getAttribute("uncertainties");
//		validateAttributeNotNull("uncertainties", T_attr_uncertainties);
//
//
//		// validate field 'Tdev' of type NX_NUMBER. Note: field not defined in base class.
//		final IDataset Tdev = group.getDataset("Tdev");
//		validateFieldNotNull("Tdev", Tdev);
//		validateFieldType("Tdev", Tdev, NX_NUMBER);
//		validateFieldUnits("Tdev", Tdev, NX_DIMENSIONLESS);
//	}
}
