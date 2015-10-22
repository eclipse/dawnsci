package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXprocess;
import org.eclipse.dawnsci.nexus.NXparameters;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXtomoproc'.
 */
public class NXtomoprocValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title)", title);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXtomoproc");

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

		// validate child group 'reconstruction' of type NXprocess
		validateGroup_entry_reconstruction(group.getProcess());

		// validate child group 'data' of type NXdata
		validateGroup_entry_data(group.getData());
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_entry_NXinstrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate unnamed child group of type NXsource (possibly multiple)
		final Map<String, NXsource> allSource = group.getAllSource();
		for (final NXsource source : allSource.values()) {
			validateGroup_entry_NXinstrument_NXsource(source);
		}
	}

	/**
	 * Validate unnamed group of type NXsource.
	 */
	private void validateGroup_entry_NXinstrument_NXsource(final NXsource group) throws NexusValidationException {
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
	private void validateGroup_entry_NXsample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
	}

	/**
	 * Validate group 'reconstruction' of type NXprocess.
	 */
	private void validateGroup_entry_reconstruction(final NXprocess group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("reconstruction", NXprocess.class, group);

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
		validateGroup_entry_reconstruction_parameters(group.getChild("parameters", NXparameters.class));
	}

	/**
	 * Validate group 'parameters' of type NXparameters.
	 */
	private void validateGroup_entry_reconstruction_parameters(final NXparameters group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("parameters", NXparameters.class, group);

		// validate field 'raw_file' of type NX_CHAR. Note: field not defined in base class.
		final IDataset raw_file = group.getDataset("raw_file");
		validateFieldNotNull("raw_file)", raw_file);
		validateFieldType("raw_file)", raw_file, NX_CHAR);
	}

	/**
	 * Validate group 'data' of type NXdata.
	 */
	private void validateGroup_entry_data(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("data", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "nx", "nx", "nz");

		// validate field 'x' of type NX_FLOAT.
		final IDataset x = group.getX();
		validateFieldNotNull("x)", x);
		validateFieldType("x)", x, NX_FLOAT);
		validateFieldUnits("x", x, NX_ANY);
		validateFieldRank("x", x, 1);
		validateFieldDimensions("x", x, null, "nx");

		// validate field 'y' of type NX_FLOAT.
		final IDataset y = group.getY();
		validateFieldNotNull("y)", y);
		validateFieldType("y)", y, NX_FLOAT);
		validateFieldUnits("y", y, NX_ANY);
		validateFieldRank("y", y, 1);
		validateFieldDimensions("y", y, null, "ny");

		// validate field 'z' of type NX_FLOAT.
		final IDataset z = group.getZ();
		validateFieldNotNull("z)", z);
		validateFieldType("z)", z, NX_FLOAT);
		validateFieldUnits("z", z, NX_ANY);
		validateFieldRank("z", z, 1);
		validateFieldDimensions("z", z, null, "nz");
	}
}
