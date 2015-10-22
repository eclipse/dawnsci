package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXxkappa'.
 */
public class NXxkappaValidator extends AbstractNXValidator implements NXApplicationValidator {

	@Override
	public void validate(NXroot root) throws NexusValidationException {
		// validate child group 'entry' of type NXentry
		validateGroup_entry(root.getEntry());
	}

	@Override
	public void validate(NXentry entry) throws NexusValidationException {
//		validateGroup_entry(entry);  TODO validate entry
	}

	@Override
	public void validate(NXsubentry subentry) throws NexusValidationException {
//		validateGroup_entry(subentry);  TODO validate entry
	}


	/**
	 * Validate group 'entry' of type NXentry.
	 */
	private void validateGroup_entry(final NXentry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("entry", NXentry.class, group);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXxkappa");

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
		validateFieldNotNull("polar_angle)", polar_angle);
		validateFieldType("polar_angle)", polar_angle, NX_FLOAT);
		validateFieldUnits("polar_angle", polar_angle, NX_ANGLE);
		validateFieldRank("polar_angle", polar_angle, 1);
		validateFieldDimensions("polar_angle", polar_angle, null, "np");
	}

	/**
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_entry_sample(final NXsample group) throws NexusValidationException {
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

		// validate field 'kappa' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset kappa = group.getDataset("kappa");
		validateFieldNotNull("kappa)", kappa);
		validateFieldType("kappa)", kappa, NX_FLOAT);
		validateFieldUnits("kappa", kappa, NX_ANGLE);
		validateFieldRank("kappa", kappa, 1);
		validateFieldDimensions("kappa", kappa, null, "np");

		// validate field 'phi' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset phi = group.getDataset("phi");
		validateFieldNotNull("phi)", phi);
		validateFieldType("phi)", phi, NX_FLOAT);
		validateFieldUnits("phi", phi, NX_ANGLE);
		validateFieldRank("phi", phi, 1);
		validateFieldDimensions("phi", phi, null, "np");

		// validate field 'alpha' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset alpha = group.getDataset("alpha");
		validateFieldNotNull("alpha)", alpha);
		validateFieldType("alpha)", alpha, NX_FLOAT);
		validateFieldUnits("alpha", alpha, NX_ANGLE);
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
