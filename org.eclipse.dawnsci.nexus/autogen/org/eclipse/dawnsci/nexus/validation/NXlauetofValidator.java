package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXlauetof'.
 */
public class NXlauetofValidator extends AbstractNexusValidator implements NexusApplicationValidator {

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

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition", definition);
		validateFieldEnumeration("definition", definition,
				"NXlauetof");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_entry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_entry_sample(group.getSample());

		// validate child group 'control' of type NXmonitor
		validateGroup_entry_control(group.getMonitor());

		// validate child group 'name' of type NXdata
		validateGroup_entry_name(group.getData());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate child group 'detector' of type NXdetector
		validateGroup_entry_instrument_detector(group.getDetector());
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_detector(final NXdetector group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

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

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data", data);
		validateFieldType("data", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "number of x pixels", "number of y pixels", "nTOF");
		// validate attribute 'signal' of field 'data'
		final Attribute data_attr_signal = group.getAttribute("signal");
		validateAttributeNotNull("signal", data_attr_signal);
		validateAttributeType("signal", data_attr_signal, NX_POSINT);
		validateAttributeEnumeration("signal", data_attr_signal,
				"1");


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

		// validate field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		validateFieldNotNull("distance", distance);
		validateFieldType("distance", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);
		validateFieldRank("distance", distance, 3);
		validateFieldDimensions("distance", distance, "NXdetector", "np", "i", "j");

		// validate field 'time_of_flight' of type NX_FLOAT.
		final IDataset time_of_flight = group.getTime_of_flight();
		validateFieldNotNull("time_of_flight", time_of_flight);
		validateFieldType("time_of_flight", time_of_flight, NX_FLOAT);
		validateFieldUnits("time_of_flight", time_of_flight, NX_TIME_OF_FLIGHT);
		validateFieldRank("time_of_flight", time_of_flight, 1);
		validateFieldDimensions("time_of_flight", time_of_flight, null, "nTOF");
	}

	/**
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_entry_sample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("sample", NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name", name);

		// validate field 'orientation_matrix' of type NX_FLOAT.
		final IDataset orientation_matrix = group.getOrientation_matrix();
		validateFieldNotNull("orientation_matrix", orientation_matrix);
		validateFieldType("orientation_matrix", orientation_matrix, NX_FLOAT);
		validateFieldRank("orientation_matrix", orientation_matrix, 2);
		validateFieldDimensions("orientation_matrix", orientation_matrix, null, 3, 3);

		// validate field 'unit_cell' of type NX_FLOAT.
		final IDataset unit_cell = group.getUnit_cell();
		validateFieldNotNull("unit_cell", unit_cell);
		validateFieldType("unit_cell", unit_cell, NX_FLOAT);
		validateFieldUnits("unit_cell", unit_cell, NX_LENGTH);
		validateFieldRank("unit_cell", unit_cell, 1);
		validateFieldDimensions("unit_cell", unit_cell, null, 6);
	}

	/**
	 * Validate group 'control' of type NXmonitor.
	 */
	private void validateGroup_entry_control(final NXmonitor group) throws NexusValidationException {
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

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data", data);
		validateFieldType("data", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "nTOF");

		// validate field 'time_of_flight' of type NX_FLOAT.
		final IDataset time_of_flight = group.getTime_of_flight();
		validateFieldNotNull("time_of_flight", time_of_flight);
		validateFieldType("time_of_flight", time_of_flight, NX_FLOAT);
		validateFieldUnits("time_of_flight", time_of_flight, NX_TIME_OF_FLIGHT);
		validateFieldRank("time_of_flight", time_of_flight, 1);
		validateFieldDimensions("time_of_flight", time_of_flight, null, "nTOF");
	}

	/**
	 * Validate group 'name' of type NXdata.
	 */
	private void validateGroup_entry_name(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("name", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
