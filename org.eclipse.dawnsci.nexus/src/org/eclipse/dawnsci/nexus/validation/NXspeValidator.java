package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXcollection;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXfermi_chopper;
import org.eclipse.dawnsci.nexus.NXsample;

/**
 * Validator for the application definition 'NXspe'.
 */
public class NXspeValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate field 'program_name' of unknown type.
		final IDataset program_name = group.getProgram_name();
		validateFieldNotNull("program_name)", program_name);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXSPE",
				"NXspe");

		// validate child group 'NXSPE_info' of type NXcollection
// $groupNameInBaseClass = collection
		validateGroup_NXentry_NXSPE_info(group.getCollection());

		// validate child group 'data' of type NXdata
// $groupNameInBaseClass = data
		validateGroup_NXentry_data(group.getData());

		// validate unnamed child group of type NXinstrument (possibly multiple)
// $groupNameInBaseClass = instrument
		final Map<String, NXinstrument> allInstrument = group.getAllInstrument();
		for (final NXinstrument instrument : allInstrument.values()) {
			validateGroup_NXentry_NXinstrument(instrument);
		}

		// validate unnamed child group of type NXsample (possibly multiple)
// $groupNameInBaseClass = sample
		final Map<String, NXsample> allSample = group.getAllSample();
		for (final NXsample sample : allSample.values()) {
			validateGroup_NXentry_NXsample(sample);
		}
	}

	/**
	 * Validate group 'NXSPE_info' of type NXcollection.
	 */
	private void validateGroup_NXentry_NXSPE_info(final NXcollection group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("NXSPE_info", NXcollection.class, group);

		// validate field 'fixed_energy' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset fixed_energy = group.getDataset("fixed_energy");
		validateFieldNotNull("fixed_energy)", fixed_energy);
		validateFieldType("fixed_energy)", fixed_energy, NX_FLOAT);
		validateFieldUnits("fixed_energy", fixed_energy, NX_ENERGY);

		// validate field 'ki_over_kf_scaling' of type NX_BOOLEAN. Note: field not defined in base class.
		final IDataset ki_over_kf_scaling = group.getDataset("ki_over_kf_scaling");
		validateFieldNotNull("ki_over_kf_scaling)", ki_over_kf_scaling);
		validateFieldType("ki_over_kf_scaling)", ki_over_kf_scaling, NX_BOOLEAN);

		// validate field 'psi' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset psi = group.getDataset("psi");
		validateFieldNotNull("psi)", psi);
		validateFieldType("psi)", psi, NX_FLOAT);
		validateFieldUnits("psi", psi, NX_ANGLE);
	}

	/**
	 * Validate group 'data' of type NXdata.
	 */
	private void validateGroup_NXentry_data(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("data", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'azimuthal' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset azimuthal = group.getDataset("azimuthal");
		validateFieldNotNull("azimuthal)", azimuthal);
		validateFieldType("azimuthal)", azimuthal, NX_FLOAT);
		validateFieldUnits("azimuthal", azimuthal, NX_ANGLE);

		// validate field 'azimuthal_width' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset azimuthal_width = group.getDataset("azimuthal_width");
		validateFieldNotNull("azimuthal_width)", azimuthal_width);
		validateFieldType("azimuthal_width)", azimuthal_width, NX_FLOAT);
		validateFieldUnits("azimuthal_width", azimuthal_width, NX_ANGLE);

		// validate field 'polar' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset polar = group.getDataset("polar");
		validateFieldNotNull("polar)", polar);
		validateFieldType("polar)", polar, NX_FLOAT);
		validateFieldUnits("polar", polar, NX_ANGLE);

		// validate field 'polar_width' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset polar_width = group.getDataset("polar_width");
		validateFieldNotNull("polar_width)", polar_width);
		validateFieldType("polar_width)", polar_width, NX_FLOAT);
		validateFieldUnits("polar_width", polar_width, NX_ANGLE);

		// validate field 'distance' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset distance = group.getDataset("distance");
		validateFieldNotNull("distance)", distance);
		validateFieldType("distance)", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_NUMBER);
		validateFieldDimensions("data", data, "NXdata", "n");

		// validate field 'error' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset error = group.getDataset("error");
		validateFieldNotNull("error)", error);
		validateFieldType("error)", error, NX_NUMBER);

		// validate field 'energy' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset energy = group.getDataset("energy");
		validateFieldNotNull("energy)", energy);
		validateFieldType("energy)", energy, NX_FLOAT);
		validateFieldUnits("energy", energy, NX_ENERGY);
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_NXentry_NXinstrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);

		// validate unnamed child group of type NXfermi_chopper (possibly multiple)
// $groupNameInBaseClass = fermi_chopper
		final Map<String, NXfermi_chopper> allFermi_chopper = group.getAllFermi_chopper();
		for (final NXfermi_chopper fermi_chopper : allFermi_chopper.values()) {
			validateGroup_NXentry_NXinstrument_NXfermi_chopper(fermi_chopper);
		}
	}

	/**
	 * Validate unnamed group of type NXfermi_chopper.
	 */
	private void validateGroup_NXentry_NXinstrument_NXfermi_chopper(final NXfermi_chopper group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXfermi_chopper.class, group);

		// validate field 'energy' of type NX_NUMBER.
		final IDataset energy = group.getEnergy();
		validateFieldNotNull("energy)", energy);
		validateFieldType("energy)", energy, NX_NUMBER);
		validateFieldUnits("energy", energy, NX_ENERGY);
	}

	/**
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'rotation_angle' of type NX_NUMBER.
		final IDataset rotation_angle = group.getRotation_angle();
		validateFieldNotNull("rotation_angle)", rotation_angle);
		validateFieldType("rotation_angle)", rotation_angle, NX_NUMBER);
		validateFieldUnits("rotation_angle", rotation_angle, NX_ANGLE);

		// validate field 'seblock' of type NX_CHAR. Note: field not defined in base class.
		final IDataset seblock = group.getDataset("seblock");
		validateFieldNotNull("seblock)", seblock);
		validateFieldType("seblock)", seblock, NX_CHAR);

		// validate field 'temperature' of type NX_NUMBER.
		final IDataset temperature = group.getTemperature();
		validateFieldNotNull("temperature)", temperature);
		validateFieldType("temperature)", temperature, NX_NUMBER);
		validateFieldUnits("temperature", temperature, NX_TEMPERATURE);
		validateFieldDimensions("temperature", temperature, "NXsample", "n_Temp");
	}
}
