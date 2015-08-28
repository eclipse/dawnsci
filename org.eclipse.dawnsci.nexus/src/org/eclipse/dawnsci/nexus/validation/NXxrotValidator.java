package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXattenuator;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXxrot'.
 */
public class NXxrotValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXxrot");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_entry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_entry_sample(group.getSample());

		// validate child group 'name' of type NXdata
		validateGroup_entry_name(group.getData());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate child group 'detector' of type NXdetector
		validateGroup_entry_instrument_detector(group.getDetector());

		// validate child group 'attenuator' of type NXattenuator
		validateGroup_entry_instrument_attenuator(group.getAttenuator());
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_detector(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'polar_angle' of type NX_FLOAT.
		final IDataset polar_angle = group.getPolar_angle();
		validateFieldNotNull("polar_angle)", polar_angle);
		validateFieldType("polar_angle)", polar_angle, NX_FLOAT);
		validateFieldUnits("polar_angle", polar_angle, NX_ANGLE);
		validateFieldRank("polar_angle", polar_angle, 3);
		validateFieldDimensions("polar_angle", polar_angle, "NXdetector", "np", "i", "j");

		// validate field 'beam_center_x' of type NX_FLOAT.
		final IDataset beam_center_x = group.getBeam_center_x();
		validateFieldNotNull("beam_center_x)", beam_center_x);
		validateFieldType("beam_center_x)", beam_center_x, NX_FLOAT);
		validateFieldUnits("beam_center_x", beam_center_x, NX_LENGTH);

		// validate field 'beam_center_y' of type NX_FLOAT.
		final IDataset beam_center_y = group.getBeam_center_y();
		validateFieldNotNull("beam_center_y)", beam_center_y);
		validateFieldType("beam_center_y)", beam_center_y, NX_FLOAT);
		validateFieldUnits("beam_center_y", beam_center_y, NX_LENGTH);
	}

	/**
	 * Validate group 'attenuator' of type NXattenuator.
	 */
	private void validateGroup_entry_instrument_attenuator(final NXattenuator group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("attenuator", NXattenuator.class, group);

		// validate field 'attenuator_transmission' of type NX_FLOAT.
		final IDataset attenuator_transmission = group.getAttenuator_transmission();
		validateFieldNotNull("attenuator_transmission)", attenuator_transmission);
		validateFieldType("attenuator_transmission)", attenuator_transmission, NX_FLOAT);
		validateFieldUnits("attenuator_transmission", attenuator_transmission, NX_ANY);
	}

	/**
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_entry_sample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample", NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'rotation_angle' of type NX_FLOAT.
		final IDataset rotation_angle = group.getRotation_angle();
		validateFieldNotNull("rotation_angle)", rotation_angle);
		validateFieldType("rotation_angle)", rotation_angle, NX_FLOAT);
		validateFieldUnits("rotation_angle", rotation_angle, NX_ANGLE);
		validateFieldRank("rotation_angle", rotation_angle, 1);
		validateFieldDimensions("rotation_angle", rotation_angle, null, "np");

		// validate field 'rotation_angle_step' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset rotation_angle_step = group.getDataset("rotation_angle_step");
		validateFieldNotNull("rotation_angle_step)", rotation_angle_step);
		validateFieldType("rotation_angle_step)", rotation_angle_step, NX_FLOAT);
		validateFieldUnits("rotation_angle_step", rotation_angle_step, NX_ANGLE);
		validateFieldRank("rotation_angle_step", rotation_angle_step, 1);
		validateFieldDimensions("rotation_angle_step", rotation_angle_step, null, "np");
	}

	/**
	 * Validate group 'name' of type NXdata.
	 */
	private void validateGroup_entry_name(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("name", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
