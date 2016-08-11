/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-06-07T11:32:36.439+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXmonochromator;
import org.eclipse.dawnsci.nexus.NXcollimator;
import org.eclipse.dawnsci.nexus.NXgeometry;
import org.eclipse.dawnsci.nexus.NXshape;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXsas'.
 */
public class NXsasValidator extends AbstractNexusValidator implements NexusApplicationValidator {

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

		// validate attribute 'entry'
		final Attribute entry_attr = group.getAttribute("entry");
		validateAttributeNotNull("entry", entry_attr);

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title", title);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time", start_time);
		validateFieldType("start_time", start_time, NX_DATE_TIME);

		// validate field 'end_time' of type NX_DATE_TIME.
		final IDataset end_time = group.getEnd_time();
		validateFieldNotNull("end_time", end_time);
		validateFieldType("end_time", end_time, NX_DATE_TIME);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition", definition);
		validateFieldEnumeration("definition", definition,
				"NXsas");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_NXentry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_NXentry_sample(group.getSample());

		// validate child group 'control' of type NXmonitor
		validateGroup_NXentry_control(group.getMonitor());

		// validate child group 'data' of type NXdata
		validateGroup_NXentry_data(group.getData());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_NXentry_instrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);
		validateFieldType("name", name, NX_CHAR);
		// validate child group 'source' of type NXsource
		validateGroup_NXentry_instrument_source(group.getSource());

		// validate child group 'monochromator' of type NXmonochromator
		validateGroup_NXentry_instrument_monochromator(group.getMonochromator());

		// validate child group 'collimator' of type NXcollimator
		validateGroup_NXentry_instrument_collimator(group.getCollimator());

		// validate child group 'detector' of type NXdetector
		validateGroup_NXentry_instrument_detector(group.getDetector());
	}

	/**
	 * Validate group 'source' of type NXsource.
	 */
	private void validateGroup_NXentry_instrument_source(final NXsource group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("source", NXsource.class, group);

		// validate field 'type' of unknown type.
		final IDataset type = group.getType();
		validateFieldNotNull("type", type);
		validateFieldEnumeration("type", type,
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
				"UV Plasma Source");

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);

		// validate field 'probe' of unknown type.
		final IDataset probe = group.getProbe();
		validateFieldNotNull("probe", probe);
		validateFieldEnumeration("probe", probe,
				"neutron",
				"x-ray");
	}

	/**
	 * Validate group 'monochromator' of type NXmonochromator.
	 */
	private void validateGroup_NXentry_instrument_monochromator(final NXmonochromator group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("monochromator", NXmonochromator.class, group);

		// validate field 'wavelength' of type NX_FLOAT.
		final IDataset wavelength = group.getWavelength();
		validateFieldNotNull("wavelength", wavelength);
		validateFieldType("wavelength", wavelength, NX_FLOAT);
		validateFieldUnits("wavelength", wavelength, NX_WAVELENGTH);

		// validate field 'wavelength_spread' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset wavelength_spread = group.getDataset("wavelength_spread");
		validateFieldNotNull("wavelength_spread", wavelength_spread);
		validateFieldType("wavelength_spread", wavelength_spread, NX_FLOAT);
	}

	/**
	 * Validate group 'collimator' of type NXcollimator.
	 */
	private void validateGroup_NXentry_instrument_collimator(final NXcollimator group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("collimator", NXcollimator.class, group);

		// validate child group 'geometry' of type NXgeometry
		validateGroup_NXentry_instrument_collimator_geometry(group.getGeometry());
	}

	/**
	 * Validate group 'geometry' of type NXgeometry.
	 */
	private void validateGroup_NXentry_instrument_collimator_geometry(final NXgeometry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("geometry", NXgeometry.class, group);

		// validate child group 'shape' of type NXshape
		validateGroup_NXentry_instrument_collimator_geometry_shape(group.getShape());
	}

	/**
	 * Validate group 'shape' of type NXshape.
	 */
	private void validateGroup_NXentry_instrument_collimator_geometry_shape(final NXshape group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("shape", NXshape.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'shape' of type NX_CHAR.
		final IDataset shape = group.getShape();
		validateFieldNotNull("shape", shape);
		validateFieldType("shape", shape, NX_CHAR);
		validateFieldEnumeration("shape", shape,
				"nxcylinder",
				"nxbox");

		// validate field 'size' of type NX_FLOAT.
		final IDataset size = group.getSize();
		validateFieldNotNull("size", size);
		validateFieldType("size", size, NX_FLOAT);
		validateFieldUnits("size", size, NX_LENGTH);
		validateFieldDimensions("size", size, "NXshape", "numobj", "nshapepar");
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_NXentry_instrument_detector(final NXdetector group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data", data);
		validateFieldType("data", data, NX_NUMBER);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 2);
		validateFieldDimensions("data", data, null, "nXPixel", "nYPixel");

		// validate field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		validateFieldNotNull("distance", distance);
		validateFieldType("distance", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);
		validateFieldRank("distance", distance, 3);
		validateFieldDimensions("distance", distance, "NXdetector", "np", "i", "j");

		// validate field 'x_pixel_size' of type NX_FLOAT.
		final IDataset x_pixel_size = group.getX_pixel_size();
		validateFieldNotNull("x_pixel_size", x_pixel_size);
		validateFieldType("x_pixel_size", x_pixel_size, NX_FLOAT);
		validateFieldUnits("x_pixel_size", x_pixel_size, NX_LENGTH);
		validateFieldRank("x_pixel_size", x_pixel_size, 2);
		validateFieldDimensions("x_pixel_size", x_pixel_size, "NXdetector", "i", "j");

		// validate field 'y_pixel_size' of type NX_FLOAT.
		final IDataset y_pixel_size = group.getY_pixel_size();
		validateFieldNotNull("y_pixel_size", y_pixel_size);
		validateFieldType("y_pixel_size", y_pixel_size, NX_FLOAT);
		validateFieldUnits("y_pixel_size", y_pixel_size, NX_LENGTH);
		validateFieldRank("y_pixel_size", y_pixel_size, 2);
		validateFieldDimensions("y_pixel_size", y_pixel_size, "NXdetector", "i", "j");

		// validate field 'polar_angle' of type NX_FLOAT.
		final IDataset polar_angle = group.getPolar_angle();
		validateFieldNotNull("polar_angle", polar_angle);
		validateFieldType("polar_angle", polar_angle, NX_FLOAT);
		validateFieldUnits("polar_angle", polar_angle, NX_ANGLE);
		validateFieldRank("polar_angle", polar_angle, 3);
		validateFieldDimensions("polar_angle", polar_angle, "NXdetector", "np", "i", "j");

		// validate field 'azimuthal_angle' of type NX_FLOAT.
		final IDataset azimuthal_angle = group.getAzimuthal_angle();
		validateFieldNotNull("azimuthal_angle", azimuthal_angle);
		validateFieldType("azimuthal_angle", azimuthal_angle, NX_FLOAT);
		validateFieldUnits("azimuthal_angle", azimuthal_angle, NX_ANGLE);
		validateFieldRank("azimuthal_angle", azimuthal_angle, 3);
		validateFieldDimensions("azimuthal_angle", azimuthal_angle, "NXdetector", "np", "i", "j");

		// validate field 'rotation_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset rotation_angle = group.getDataset("rotation_angle");
		validateFieldNotNull("rotation_angle", rotation_angle);
		validateFieldType("rotation_angle", rotation_angle, NX_FLOAT);
		validateFieldUnits("rotation_angle", rotation_angle, NX_ANGLE);

		// validate field 'aequatorial_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset aequatorial_angle = group.getDataset("aequatorial_angle");
		validateFieldNotNull("aequatorial_angle", aequatorial_angle);
		validateFieldType("aequatorial_angle", aequatorial_angle, NX_FLOAT);
		validateFieldUnits("aequatorial_angle", aequatorial_angle, NX_ANGLE);

		// validate field 'beam_center_x' of type NX_FLOAT.
		final IDataset beam_center_x = group.getBeam_center_x();
		validateFieldNotNull("beam_center_x", beam_center_x);
		validateFieldType("beam_center_x", beam_center_x, NX_FLOAT);
		validateFieldUnits("beam_center_x", beam_center_x, NX_LENGTH);

		// validate field 'beam_center_y' of type NX_FLOAT.
		final IDataset beam_center_y = group.getBeam_center_y();
		validateFieldNotNull("beam_center_y", beam_center_y);
		validateFieldType("beam_center_y", beam_center_y, NX_FLOAT);
		validateFieldUnits("beam_center_y", beam_center_y, NX_LENGTH);
	}

	/**
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_NXentry_sample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("sample", NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);

		// validate field 'aequatorial_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset aequatorial_angle = group.getDataset("aequatorial_angle");
		validateFieldNotNull("aequatorial_angle", aequatorial_angle);
		validateFieldType("aequatorial_angle", aequatorial_angle, NX_FLOAT);
		validateFieldUnits("aequatorial_angle", aequatorial_angle, NX_ANGLE);
	}

	/**
	 * Validate group 'control' of type NXmonitor.
	 */
	private void validateGroup_NXentry_control(final NXmonitor group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("control", NXmonitor.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'mode' of unknown type.
		final IDataset mode = group.getMode();
		validateFieldNotNull("mode", mode);
		validateFieldEnumeration("mode", mode,
				"monitor",
				"timer");

		// validate field 'preset' of type NX_FLOAT.
		final IDataset preset = group.getPreset();
		validateFieldNotNull("preset", preset);
		validateFieldType("preset", preset, NX_FLOAT);
		validateFieldUnits("preset", preset, NX_ANY);

		// validate field 'integral' of type NX_FLOAT.
		final IDataset integral = group.getIntegral();
		validateFieldNotNull("integral", integral);
		validateFieldType("integral", integral, NX_FLOAT);
		validateFieldUnits("integral", integral, NX_ANY);
	}

	/**
	 * Validate group 'data' of type NXdata.
	 */
	private void validateGroup_NXentry_data(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("data", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
