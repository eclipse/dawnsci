package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXprocess;
import org.eclipse.dawnsci.nexus.NXparameters;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXxasproc'.
 */
public class NXxasprocValidator extends AbstractNXValidator implements NXApplicationValidator {

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
//		validateGroup_entry(entry);  TODO validate entry
	}

	@Override
	public void validate(NXsubentry subentry) throws NexusValidationException {
//		validateGroup_entry(subentry);  TODO validate entry
	}


	/**
	 * Validate unnamed group of type NXentry.
	 */
	private void validateGroup_NXentry(final NXentry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXentry.class, group);

		// validate attribute 'entry'
		final Attribute entryAttr = group.getAttribute("entry");
		validateAttributeNotNull("entry", entryAttr);

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title)", title);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXxasproc");

		// validate unnamed child group of type NXsample (possibly multiple)
		final Map<String, NXsample> allSample = group.getAllSample();
		for (final NXsample sample : allSample.values()) {
			validateGroup_NXentry_NXsample(sample);
		}

		// validate child group 'XAS_data_reduction' of type NXprocess
		validateGroup_NXentry_XAS_data_reduction(group.getProcess());

		// validate unnamed child group of type NXdata (possibly multiple)
		final Map<String, NXdata> allData = group.getAllData();
		for (final NXdata data : allData.values()) {
			validateGroup_NXentry_NXdata(data);
		}
	}

	/**
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
	}

	/**
	 * Validate group 'XAS_data_reduction' of type NXprocess.
	 */
	private void validateGroup_NXentry_XAS_data_reduction(final NXprocess group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("XAS_data_reduction", NXprocess.class, group);

		// validate field 'program' of type NX_CHAR.
		final IDataset program = group.getProgram();
		validateFieldNotNull("program)", program);
		validateFieldType("program)", program, NX_CHAR);

		// validate field 'version' of type NX_CHAR.
		final IDataset version = group.getVersion();
		validateFieldNotNull("version)", version);
		validateFieldType("version)", version, NX_CHAR);

		// validate field 'date' of type NX_DATE_TIME.
		final IDataset date = group.getDate();
		validateFieldNotNull("date)", date);
		validateFieldType("date)", date, NX_DATE_TIME);

		// validate child group 'parameters' of type NXparameters
		validateGroup_NXentry_XAS_data_reduction_parameters(group.getChild("parameters", NXparameters.class));
	}

	/**
	 * Validate group 'parameters' of type NXparameters.
	 */
	private void validateGroup_NXentry_XAS_data_reduction_parameters(final NXparameters group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("parameters", NXparameters.class, group);

		// validate field 'raw_file' of type NX_CHAR. Note: field not defined in base class.
		final IDataset raw_file = group.getDataset("raw_file");
		validateFieldNotNull("raw_file)", raw_file);
		validateFieldType("raw_file)", raw_file, NX_CHAR);
	}

	/**
	 * Validate unnamed group of type NXdata.
	 */
	private void validateGroup_NXentry_NXdata(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'energy' of unknown type. Note: field not defined in base class.
		final IDataset energy = group.getDataset("energy");
		validateFieldNotNull("energy)", energy);
		validateFieldRank("energy", energy, 1);
		validateFieldDimensions("energy", energy, null, "np");

		// validate field 'data' of type NX_FLOAT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_FLOAT);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "np");
	}
}
