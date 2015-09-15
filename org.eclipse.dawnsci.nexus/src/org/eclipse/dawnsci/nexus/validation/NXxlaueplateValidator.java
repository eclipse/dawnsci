package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXdetector;

/**
 * Validator for the application definition 'NXxlaueplate'.
 */
public class NXxlaueplateValidator extends AbstractNXValidator implements NXApplicationValidator {

@Override
	public void validate(NXroot root) throws Exception {
		// validate child group 'entry' of type NXentry
// $groupNameInBaseClass = entry
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
				"NXxlaueplate");

		// validate child group 'instrument' of type NXinstrument
// $groupNameInBaseClass = instrument
		validateGroup_entry_instrument(group.getInstrument());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate child group 'detector' of type NXdetector
// $groupNameInBaseClass = detector
		validateGroup_entry_instrument_detector(group.getDetector());
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_entry_instrument_detector(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'diameter' of type NX_FLOAT.
		final IDataset diameter = group.getDiameter();
		validateFieldNotNull("diameter)", diameter);
		validateFieldType("diameter)", diameter, NX_FLOAT);
		validateFieldUnits("diameter", diameter, NX_LENGTH);
	}
}
