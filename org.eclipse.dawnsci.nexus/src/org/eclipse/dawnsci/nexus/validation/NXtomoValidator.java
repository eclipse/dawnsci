package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXtomo'.
 */
public class NXtomoValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate optional field 'title' of unknown type.
		final IDataset title = group.getTitle();
		if (title != null) {
		}

		// validate optional field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		if (start_time != null) {
			validateFieldType("start_time)", start_time, NX_DATE_TIME);
		}

		// validate optional field 'end_time' of type NX_DATE_TIME.
		final IDataset end_time = group.getEnd_time();
		if (end_time != null) {
			validateFieldType("end_time)", end_time, NX_DATE_TIME);
		}

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXtomo");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_entry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_entry_sample(group.getSample());

		// validate optional child group 'control' of type NXmonitor
		if (group.getMonitor() != null) {
			validateGroup_entry_control(group.getMonitor());
		}

		// validate child group 'data' of type NXdata
		validateGroup_entry_data(group.getData());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate optional unnamed child group of type NXsource
		if (group.getSource() != null) {
			validateGroup_entry_instrument_NXsource(group.getSource());
		}

		// validate child group 'detector' of type NXdetector
		validateGroup_entry_instrument_detector(group.getDetector());
	}

	/**
	 * Validate optional unnamed group of type NXsource.
	 */
	private void validateGroup_entry_instrument_NXsource(final NXsource group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsource.class, group);

		// validate optional field 'type' of unknown type.
		final IDataset type = group.getType();
		if (type != null) {
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
		}

		// validate optional field 'name' of unknown type.
		final IDataset name = group.getName();
		if (name != null) {
		}

		// validate optional field 'probe' of unknown type.
		final IDataset probe = group.getProbe();
		if (probe != null) {
			validateFieldEnumeration("probe", probe,
					"neutron",
					"x-ray",
					"electron");
		}
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_detector(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "nFrames", "xsize", "ysize");

		// validate field 'image_key' of type NX_INT. Note: field not defined in base class.
		final IDataset image_key = group.getDataset("image_key");
		validateFieldNotNull("image_key)", image_key);
		validateFieldType("image_key)", image_key, NX_INT);
		validateFieldRank("image_key", image_key, 1);
		validateFieldDimensions("image_key", image_key, null, "nFrames");

		// validate optional field 'x_pixel_size' of type NX_FLOAT.
		final IDataset x_pixel_size = group.getX_pixel_size();
		if (x_pixel_size != null) {
			validateFieldType("x_pixel_size)", x_pixel_size, NX_FLOAT);
			validateFieldUnits("x_pixel_size", x_pixel_size, NX_LENGTH);
			validateFieldRank("x_pixel_size", x_pixel_size, 2);
			validateFieldDimensions("x_pixel_size", x_pixel_size, "NXdetector", "i", "j");
		}

		// validate optional field 'y_pixel_size' of type NX_FLOAT.
		final IDataset y_pixel_size = group.getY_pixel_size();
		if (y_pixel_size != null) {
			validateFieldType("y_pixel_size)", y_pixel_size, NX_FLOAT);
			validateFieldUnits("y_pixel_size", y_pixel_size, NX_LENGTH);
			validateFieldRank("y_pixel_size", y_pixel_size, 2);
			validateFieldDimensions("y_pixel_size", y_pixel_size, "NXdetector", "i", "j");
		}

		// validate optional field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		if (distance != null) {
			validateFieldType("distance)", distance, NX_FLOAT);
			validateFieldUnits("distance", distance, NX_LENGTH);
			validateFieldRank("distance", distance, 3);
			validateFieldDimensions("distance", distance, "NXdetector", "np", "i", "j");
		}

		// validate optional field 'x_rotation_axis_pixel_position' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset x_rotation_axis_pixel_position = group.getDataset("x_rotation_axis_pixel_position");
		if (x_rotation_axis_pixel_position != null) {
			validateFieldType("x_rotation_axis_pixel_position)", x_rotation_axis_pixel_position, NX_FLOAT);
		}

		// validate optional field 'y_rotation_axis_pixel_position' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset y_rotation_axis_pixel_position = group.getDataset("y_rotation_axis_pixel_position");
		if (y_rotation_axis_pixel_position != null) {
			validateFieldType("y_rotation_axis_pixel_position)", y_rotation_axis_pixel_position, NX_FLOAT);
		}
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
		validateFieldDimensions("rotation_angle", rotation_angle, null, "nFrames");

		// validate optional field 'x_translation' of type NX_FLOAT.
		final IDataset x_translation = group.getX_translation();
		if (x_translation != null) {
			validateFieldType("x_translation)", x_translation, NX_FLOAT);
			validateFieldUnits("x_translation", x_translation, NX_LENGTH);
			validateFieldRank("x_translation", x_translation, 1);
			validateFieldDimensions("x_translation", x_translation, null, "nFrames");
		}

		// validate optional field 'y_translation' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset y_translation = group.getDataset("y_translation");
		if (y_translation != null) {
			validateFieldType("y_translation)", y_translation, NX_FLOAT);
			validateFieldUnits("y_translation", y_translation, NX_LENGTH);
			validateFieldRank("y_translation", y_translation, 1);
			validateFieldDimensions("y_translation", y_translation, null, "nFrames");
		}

		// validate optional field 'z_translation' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset z_translation = group.getDataset("z_translation");
		if (z_translation != null) {
			validateFieldType("z_translation)", z_translation, NX_FLOAT);
			validateFieldUnits("z_translation", z_translation, NX_LENGTH);
			validateFieldRank("z_translation", z_translation, 1);
			validateFieldDimensions("z_translation", z_translation, null, "nFrames");
		}
	}

	/**
	 * Validate optional group 'control' of type NXmonitor.
	 */
	private void validateGroup_entry_control(final NXmonitor group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("control", NXmonitor.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_FLOAT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_FLOAT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "nFrames");
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
