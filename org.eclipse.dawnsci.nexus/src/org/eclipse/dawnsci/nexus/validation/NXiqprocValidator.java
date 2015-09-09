package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXprocess;
import org.eclipse.dawnsci.nexus.NXparameters;
import org.eclipse.dawnsci.nexus.NXparameters;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXiqproc'.
 */
public class NXiqprocValidator extends AbstractNXValidator implements NXApplicationValidator {

@Override
	public void validate(NXroot root) throws Exception {
		// validate unnamed child group of type NXentry (possibly multiple)
// $groupNameInBaseClass = entry
		final Map<String, NXentry> allEntry = root.getAllEntry();
		for (final NXentry entry : allEntry.values()) {
			validateGroup_NXentry(entry);
		}
	}

	/**
	 * Validate unnamed group of type NXentry.
	 */
	private void validateGroup_NXentry(final NXentry group) throws Exception {
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
				"NXiqproc");

		// validate child group 'instrument' of type NXinstrument
// $groupNameInBaseClass = instrument
		validateGroup_NXentry_instrument(group.getInstrument());

		// validate unnamed child group of type NXsample (possibly multiple)
// $groupNameInBaseClass = sample
		final Map<String, NXsample> allSample = group.getAllSample();
		for (final NXsample sample : allSample.values()) {
			validateGroup_NXentry_NXsample(sample);
		}

		// validate child group 'reduction' of type NXprocess
// $groupNameInBaseClass = process
		validateGroup_NXentry_reduction(group.getProcess());

		// validate unnamed child group of type NXdata (possibly multiple)
// $groupNameInBaseClass = data
		final Map<String, NXdata> allData = group.getAllData();
		for (final NXdata data : allData.values()) {
			validateGroup_NXentry_NXdata(data);
		}
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_NXentry_instrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);
		// validate unnamed child group of type NXsource (possibly multiple)
// $groupNameInBaseClass = source
		final Map<String, NXsource> allSource = group.getAllSource();
		for (final NXsource source : allSource.values()) {
			validateGroup_NXentry_instrument_NXsource(source);
		}
	}

	/**
	 * Validate unnamed group of type NXsource.
	 */
	private void validateGroup_NXentry_instrument_NXsource(final NXsource group) throws Exception {
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
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
	}

	/**
	 * Validate group 'reduction' of type NXprocess.
	 */
	private void validateGroup_NXentry_reduction(final NXprocess group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("reduction", NXprocess.class, group);

		// validate field 'program' of type NX_CHAR.
		final IDataset program = group.getProgram();
		validateFieldNotNull("program)", program);
		validateFieldType("program)", program, NX_CHAR);

		// validate field 'version' of type NX_CHAR.
		final IDataset version = group.getVersion();
		validateFieldNotNull("version)", version);
		validateFieldType("version)", version, NX_CHAR);

		// validate child group 'input' of type NXparameters
// $groupNameInBaseClass = parameters
		validateGroup_NXentry_reduction_input(group.getChild("input", NXparameters.class));

		// validate child group 'output' of type NXparameters
// $groupNameInBaseClass = parameters
		validateGroup_NXentry_reduction_output(group.getChild("output", NXparameters.class));
	}

	/**
	 * Validate group 'input' of type NXparameters.
	 */
	private void validateGroup_NXentry_reduction_input(final NXparameters group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("input", NXparameters.class, group);

		// validate field 'filenames' of type NX_CHAR. Note: field not defined in base class.
		final IDataset filenames = group.getDataset("filenames");
		validateFieldNotNull("filenames)", filenames);
		validateFieldType("filenames)", filenames, NX_CHAR);
	}

	/**
	 * Validate group 'output' of type NXparameters.
	 */
	private void validateGroup_NXentry_reduction_output(final NXparameters group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("output", NXparameters.class, group);

	}

	/**
	 * Validate unnamed group of type NXdata.
	 */
	private void validateGroup_NXentry_NXdata(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "NE", "NQX", "NQY");

		// validate field 'variable' of unknown type.
		final IDataset variable = group.getVariable();
		validateFieldNotNull("variable)", variable);
		validateFieldType("variable)", variable, NX_NUMBER);
		validateFieldRank("variable", variable, 1);
		validateFieldDimensions("variable", variable, null, "NE");

		// validate field 'qx' of unknown type. Note: field not defined in base class.
		final IDataset qx = group.getDataset("qx");
		validateFieldNotNull("qx)", qx);
		validateFieldRank("qx", qx, 1);
		validateFieldDimensions("qx", qx, null, "NQX");

		// validate field 'qy' of unknown type. Note: field not defined in base class.
		final IDataset qy = group.getDataset("qy");
		validateFieldNotNull("qy)", qy);
		validateFieldRank("qy", qy, 1);
		validateFieldDimensions("qy", qy, null, "NQY");
	}
}
