package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXmonochromator;

/**
 * Validator for the application definition 'NXindirecttof'.
 */
public class NXindirecttofValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXindirecttof");

		// validate unnamed child group of type NXinstrument (possibly multiple)
		final Map<String, NXinstrument> allInstrument = group.getAllInstrument();
		for (final NXinstrument instrument : allInstrument.values()) {
			validateGroup_entry_NXinstrument(instrument);
		}
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_entry_NXinstrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate child group 'analyser' of type NXmonochromator
		validateGroup_entry_NXinstrument_analyser(group.getMonochromator());
	}

	/**
	 * Validate group 'analyser' of type NXmonochromator.
	 */
	private void validateGroup_entry_NXinstrument_analyser(final NXmonochromator group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("analyser", NXmonochromator.class, group);

		// validate field 'energy' of type NX_FLOAT.
		final IDataset energy = group.getEnergy();
		validateFieldNotNull("energy)", energy);
		validateFieldType("energy)", energy, NX_FLOAT);
		validateFieldUnits("energy", energy, NX_ENERGY);
		validateFieldRank("energy", energy, 1);
		validateFieldDimensions("energy", energy, null, "nDet");

		// validate field 'polar_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset polar_angle = group.getDataset("polar_angle");
		validateFieldNotNull("polar_angle)", polar_angle);
		validateFieldType("polar_angle)", polar_angle, NX_FLOAT);
		validateFieldUnits("polar_angle", polar_angle, NX_ANGLE);
		validateFieldRank("polar_angle", polar_angle, 1);
		validateFieldDimensions("polar_angle", polar_angle, null, "ndet");

		// validate field 'distance' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset distance = group.getDataset("distance");
		validateFieldNotNull("distance)", distance);
		validateFieldType("distance)", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);
		validateFieldRank("distance", distance, 1);
		validateFieldDimensions("distance", distance, null, "ndet");
	}
}
