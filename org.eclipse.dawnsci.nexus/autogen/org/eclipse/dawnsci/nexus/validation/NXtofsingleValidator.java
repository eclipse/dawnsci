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

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXuser;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXtofsingle'.
 */
public class NXtofsingleValidator extends AbstractNexusValidator implements NexusApplicationValidator {

	@Override
	public void validate(NXroot root) throws NexusValidationException {
		// validate child group 'entry' of type NXentry
		validateGroup_entry(root.getEntry());
	}

	@Override
	public void validate(NXentry entry) throws NexusValidationException {
		validateGroup_entry(entry);
	}

	@Override
	public void validate(NXsubentry subentry) throws NexusValidationException {
		validateGroup_entry(subentry);
	}


	/**
	 * Validate group 'entry' of type NXentry.
	 */
	private void validateGroup_entry(final NXsubentry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("entry", NXentry.class, group);

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title", title);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time", start_time);
		validateFieldType("start_time", start_time, NX_DATE_TIME);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition", definition);
		validateFieldEnumeration("definition", definition,
				"NXtofsingle");

		// validate field 'duration' of type NX_FLOAT.
		final IDataset duration = group.getDuration();
		validateFieldNotNull("duration", duration);
		validateFieldType("duration", duration, NX_FLOAT);
		validateFieldUnits("duration", duration, NX_TIME);

		// validate field 'pre_sample_flightpath' of type NX_FLOAT.
		final IDataset pre_sample_flightpath = group.getPre_sample_flightpath();
		validateFieldNotNull("pre_sample_flightpath", pre_sample_flightpath);
		validateFieldType("pre_sample_flightpath", pre_sample_flightpath, NX_FLOAT);
		validateFieldUnits("pre_sample_flightpath", pre_sample_flightpath, NX_LENGTH);

		// validate child group 'user' of type NXuser
		validateGroup_entry_user(group.getUser());

		// validate unnamed child group of type NXinstrument (possibly multiple)
		final Map<String, NXinstrument> allInstrument = group.getAllInstrument();
		for (final NXinstrument instrument : allInstrument.values()) {
			validateGroup_entry_NXinstrument(instrument);
		}

		// validate unnamed child group of type NXsample (possibly multiple)
		final Map<String, NXsample> allSample = group.getAllSample();
		for (final NXsample sample : allSample.values()) {
			validateGroup_entry_NXsample(sample);
		}

		// validate unnamed child group of type NXmonitor (possibly multiple)
		final Map<String, NXmonitor> allMonitor = group.getAllMonitor();
		for (final NXmonitor monitor : allMonitor.values()) {
			validateGroup_entry_NXmonitor(monitor);
		}

		// validate child group 'data' of type NXdata
		validateGroup_entry_data(group.getData());
	}

	/**
	 * Validate group 'user' of type NXuser.
	 */
	private void validateGroup_entry_user(final NXuser group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("user", NXuser.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);
		validateFieldType("name", name, NX_CHAR);
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_entry_NXinstrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate child group 'detector' of type NXdetector
		validateGroup_entry_NXinstrument_detector(group.getDetector());
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_entry_NXinstrument_detector(final NXdetector group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data", data);
		validateFieldType("data", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "xsize", "ysize", "ntimechan");

		// validate field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		validateFieldNotNull("distance", distance);
		validateFieldType("distance", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);
		validateFieldRank("distance", distance, 1);
		validateFieldDimensions("distance", distance, null, 1);

		// validate field 'time_of_flight' of type NX_FLOAT.
		final IDataset time_of_flight = group.getTime_of_flight();
		validateFieldNotNull("time_of_flight", time_of_flight);
		validateFieldType("time_of_flight", time_of_flight, NX_FLOAT);
		validateFieldUnits("time_of_flight", time_of_flight, NX_TIME_OF_FLIGHT);
		validateFieldRank("time_of_flight", time_of_flight, 1);
		validateFieldDimensions("time_of_flight", time_of_flight, null, "ntimechan");

		// validate field 'polar_angle' of type NX_FLOAT.
		final IDataset polar_angle = group.getPolar_angle();
		validateFieldNotNull("polar_angle", polar_angle);
		validateFieldType("polar_angle", polar_angle, NX_FLOAT);
		validateFieldUnits("polar_angle", polar_angle, NX_ANGLE);
		validateFieldRank("polar_angle", polar_angle, 1);
		validateFieldDimensions("polar_angle", polar_angle, null, "ndet");

		// validate field 'azimuthal_angle' of type NX_FLOAT.
		final IDataset azimuthal_angle = group.getAzimuthal_angle();
		validateFieldNotNull("azimuthal_angle", azimuthal_angle);
		validateFieldType("azimuthal_angle", azimuthal_angle, NX_FLOAT);
		validateFieldUnits("azimuthal_angle", azimuthal_angle, NX_ANGLE);
		validateFieldRank("azimuthal_angle", azimuthal_angle, 1);
		validateFieldDimensions("azimuthal_angle", azimuthal_angle, null, "ndet");
	}

	/**
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_entry_NXsample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);

		// validate field 'nature' of type NX_CHAR. Note: field not defined in base class.
		final IDataset nature = group.getDataset("nature");
		validateFieldNotNull("nature", nature);
		validateFieldType("nature", nature, NX_CHAR);
		validateFieldEnumeration("nature", nature,
				"powder",
				"liquid",
				"single crystal");
	}

	/**
	 * Validate unnamed group of type NXmonitor.
	 */
	private void validateGroup_entry_NXmonitor(final NXmonitor group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXmonitor.class, group);
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

		// validate field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		validateFieldNotNull("distance", distance);
		validateFieldType("distance", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data", data);
		validateFieldType("data", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "ntimechan");

		// validate field 'time_of_flight' of type NX_FLOAT.
		final IDataset time_of_flight = group.getTime_of_flight();
		validateFieldNotNull("time_of_flight", time_of_flight);
		validateFieldType("time_of_flight", time_of_flight, NX_FLOAT);
		validateFieldUnits("time_of_flight", time_of_flight, NX_TIME_OF_FLIGHT);
		validateFieldRank("time_of_flight", time_of_flight, 1);
		validateFieldDimensions("time_of_flight", time_of_flight, null, "ntimechan");
	}

	/**
	 * Validate group 'data' of type NXdata.
	 */
	private void validateGroup_entry_data(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("data", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
