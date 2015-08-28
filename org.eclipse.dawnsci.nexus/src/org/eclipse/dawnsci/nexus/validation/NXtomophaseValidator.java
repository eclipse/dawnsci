package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXtomophase'.
 */
public class NXtomophaseValidator extends AbstractNXValidator implements NXApplicationValidator {

@Override
	public void validate(NXroot root) throws Exception {
		// validate child group 'entry' of type NXentry
		validateGroup_entry(root.getEntry());
	}

	/**
	 * Validate group 'entry' of type NXentry.
	 */
	private void validateGroup_entry(final NXentry group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("entry", NXentry.class, group);

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title)", title);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time)", start_time);
		validateFieldType("start_time)", start_time, NX_DATE_TIME);

		// validate field 'end_time' of type NX_DATE_TIME.
		final IDataset end_time = group.getEnd_time();
		validateFieldNotNull("end_time)", end_time);
		validateFieldType("end_time)", end_time, NX_DATE_TIME);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXtomophase");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_entry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_entry_sample(group.getSample());

		// validate child group 'control' of type NXmonitor
		validateGroup_entry_control(group.getMonitor());

		// validate child group 'data' of type NXdata
		validateGroup_entry_data(group.getData());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate unnamed child group of type NXsource (possibly multiple)
		final Map<String, NXsource> allSource = group.getAllSource();
		for (final NXsource source : allSource.values()) {
			validateGroup_entry_instrument_NXsource(source);
		}

		// validate child group 'bright_field' of type NXdetector
		validateGroup_entry_instrument_bright_field(group.getDetector());

		// validate child group 'dark_field' of type NXdetector
		validateGroup_entry_instrument_dark_field(group.getDetector());

		// validate child group 'sample' of type NXdetector
		validateGroup_entry_instrument_sample(group.getDetector());
	}

	/**
	 * Validate unnamed group of type NXsource.
	 */
	private void validateGroup_entry_instrument_NXsource(final NXsource group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsource.class, group);

		// validate field 'type' of unknown type.
		final IDataset type = group.getType();
		validateFieldNotNull("type)", type);
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
		validateFieldNotNull("name)", name);

		// validate field 'probe' of unknown type.
		final IDataset probe = group.getProbe();
		validateFieldNotNull("probe)", probe);
		validateFieldEnumeration("probe", probe,
				"neutron",
				"x-ray",
				"electron");
	}

	/**
	 * Validate group 'bright_field' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_bright_field(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("bright_field", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "nBrightFrames", "xsize", "ysize");

		// validate field 'sequence_number' of type NX_INT.
		final IDataset sequence_number = group.getSequence_number();
		validateFieldNotNull("sequence_number)", sequence_number);
		validateFieldType("sequence_number)", sequence_number, NX_INT);
		validateFieldRank("sequence_number", sequence_number, 1);
		validateFieldDimensions("sequence_number", sequence_number, null, "nBrightFrames");
	}

	/**
	 * Validate group 'dark_field' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_dark_field(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("dark_field", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "nDarkFrames", "xsize", "ysize");

		// validate field 'sequence_number' of type NX_INT.
		final IDataset sequence_number = group.getSequence_number();
		validateFieldNotNull("sequence_number)", sequence_number);
		validateFieldType("sequence_number)", sequence_number, NX_INT);
		validateFieldRank("sequence_number", sequence_number, 1);
		validateFieldDimensions("sequence_number", sequence_number, null, "nDarkFrames");
	}

	/**
	 * Validate group 'sample' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_sample(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 4);
		validateFieldDimensions("data", data, null, "nSampleFrames", "nPhase", "xsize", "ysize");

		// validate field 'sequence_number' of type NX_INT.
		final IDataset sequence_number = group.getSequence_number();
		validateFieldNotNull("sequence_number)", sequence_number);
		validateFieldType("sequence_number)", sequence_number, NX_INT);
		validateFieldRank("sequence_number", sequence_number, 2);
		validateFieldDimensions("sequence_number", sequence_number, null, "nSampleFrames", "nPhase");

		// validate field 'x_pixel_size' of type NX_FLOAT.
		final IDataset x_pixel_size = group.getX_pixel_size();
		validateFieldNotNull("x_pixel_size)", x_pixel_size);
		validateFieldType("x_pixel_size)", x_pixel_size, NX_FLOAT);
		validateFieldUnits("x_pixel_size", x_pixel_size, NX_LENGTH);
		validateFieldRank("x_pixel_size", x_pixel_size, 2);
		validateFieldDimensions("x_pixel_size", x_pixel_size, "NXdetector", "i", "j");

		// validate field 'y_pixel_size' of type NX_FLOAT.
		final IDataset y_pixel_size = group.getY_pixel_size();
		validateFieldNotNull("y_pixel_size)", y_pixel_size);
		validateFieldType("y_pixel_size)", y_pixel_size, NX_FLOAT);
		validateFieldUnits("y_pixel_size", y_pixel_size, NX_LENGTH);
		validateFieldRank("y_pixel_size", y_pixel_size, 2);
		validateFieldDimensions("y_pixel_size", y_pixel_size, "NXdetector", "i", "j");

		// validate field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		validateFieldNotNull("distance)", distance);
		validateFieldType("distance)", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);
		validateFieldRank("distance", distance, 3);
		validateFieldDimensions("distance", distance, "NXdetector", "np", "i", "j");
	}

	/**
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_entry_sample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample", NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);

		// validate field 'rotation_angle' of type NX_FLOAT.
		final IDataset rotation_angle = group.getRotation_angle();
		validateFieldNotNull("rotation_angle)", rotation_angle);
		validateFieldType("rotation_angle)", rotation_angle, NX_FLOAT);
		validateFieldUnits("rotation_angle", rotation_angle, NX_ANGLE);
		validateFieldRank("rotation_angle", rotation_angle, 1);
		validateFieldDimensions("rotation_angle", rotation_angle, null, "nSampleFrames");

		// validate field 'x_translation' of type NX_FLOAT.
		final IDataset x_translation = group.getX_translation();
		validateFieldNotNull("x_translation)", x_translation);
		validateFieldType("x_translation)", x_translation, NX_FLOAT);
		validateFieldUnits("x_translation", x_translation, NX_LENGTH);
		validateFieldRank("x_translation", x_translation, 1);
		validateFieldDimensions("x_translation", x_translation, null, "nSampleFrames");

		// validate field 'y_translation' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset y_translation = group.getDataset("y_translation");
		validateFieldNotNull("y_translation)", y_translation);
		validateFieldType("y_translation)", y_translation, NX_FLOAT);
		validateFieldUnits("y_translation", y_translation, NX_LENGTH);
		validateFieldRank("y_translation", y_translation, 1);
		validateFieldDimensions("y_translation", y_translation, null, "nSampleFrames");

		// validate field 'z_translation' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset z_translation = group.getDataset("z_translation");
		validateFieldNotNull("z_translation)", z_translation);
		validateFieldType("z_translation)", z_translation, NX_FLOAT);
		validateFieldUnits("z_translation", z_translation, NX_LENGTH);
		validateFieldRank("z_translation", z_translation, 1);
		validateFieldDimensions("z_translation", z_translation, null, "nSampleFrames");
	}

	/**
	 * Validate group 'control' of type NXmonitor.
	 */
	private void validateGroup_entry_control(final NXmonitor group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("control", NXmonitor.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'integral' of type NX_FLOAT.
		final IDataset integral = group.getIntegral();
		validateFieldNotNull("integral)", integral);
		validateFieldType("integral)", integral, NX_FLOAT);
		validateFieldUnits("integral", integral, NX_ANY);
		validateFieldRank("integral", integral, 1);
		validateFieldDimensions("integral", integral, null, "nDarkFrames + nBrightFrames + nSampleFrame");
	}

	/**
	 * Validate group 'data' of type NXdata.
	 */
	private void validateGroup_entry_data(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("data", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
