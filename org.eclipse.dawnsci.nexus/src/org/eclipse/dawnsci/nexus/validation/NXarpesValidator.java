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
import org.eclipse.dawnsci.nexus.NXmonochromator;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXarpes'.
 */
public class NXarpesValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate field 'title' of type NX_CHAR.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title)", title);
		validateFieldType("title)", title, NX_CHAR);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time)", start_time);
		validateFieldType("start_time)", start_time, NX_DATE_TIME);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXarpes");

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

		// validate unnamed child group of type NXdata (possibly multiple)
// $groupNameInBaseClass = data
		final Map<String, NXdata> allData = group.getAllData();
		for (final NXdata data : allData.values()) {
			validateGroup_NXentry_NXdata(data);
		}
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_NXentry_NXinstrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate unnamed child group of type NXsource (possibly multiple)
// $groupNameInBaseClass = source
		final Map<String, NXsource> allSource = group.getAllSource();
		for (final NXsource source : allSource.values()) {
			validateGroup_NXentry_NXinstrument_NXsource(source);
		}

		// validate child group 'monochromator' of type NXmonochromator
// $groupNameInBaseClass = monochromator
		validateGroup_NXentry_NXinstrument_monochromator(group.getMonochromator());

		// validate child group 'analyser' of type NXdetector
// $groupNameInBaseClass = detector
		validateGroup_NXentry_NXinstrument_analyser(group.getDetector());
	}

	/**
	 * Validate unnamed group of type NXsource.
	 */
	private void validateGroup_NXentry_NXinstrument_NXsource(final NXsource group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsource.class, group);

		// validate field 'type' of type NX_CHAR.
		final IDataset type = group.getType();
		validateFieldNotNull("type)", type);
		validateFieldType("type)", type, NX_CHAR);
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

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);

		// validate field 'probe' of unknown type.
		final IDataset probe = group.getProbe();
		validateFieldNotNull("probe)", probe);
		validateFieldEnumeration("probe", probe,
				"x-ray");
	}

	/**
	 * Validate group 'monochromator' of type NXmonochromator.
	 */
	private void validateGroup_NXentry_NXinstrument_monochromator(final NXmonochromator group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("monochromator", NXmonochromator.class, group);

		// validate field 'energy' of type NX_NUMBER.
		final IDataset energy = group.getEnergy();
		validateFieldNotNull("energy)", energy);
		validateFieldType("energy)", energy, NX_NUMBER);
		validateFieldUnits("energy", energy, NX_ENERGY);
	}

	/**
	 * Validate group 'analyser' of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_analyser(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("analyser", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_NUMBER);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 4);
		validateFieldDimensions("data", data, "NXdetector", "np", "i", "j", "tof");

		// validate field 'lens_mode' of type NX_CHAR. Note: field not defined in base class.
		final IDataset lens_mode = group.getDataset("lens_mode");
		validateFieldNotNull("lens_mode)", lens_mode);
		validateFieldType("lens_mode)", lens_mode, NX_CHAR);

		// validate field 'acquisition_mode' of unknown type.
		final IDataset acquisition_mode = group.getAcquisition_mode();
		validateFieldNotNull("acquisition_mode)", acquisition_mode);
		validateFieldType("acquisition_mode)", acquisition_mode, NX_CHAR);
		validateFieldEnumeration("acquisition_mode", acquisition_mode,
				"swept",
				"fixed");

		// validate field 'entrance_slit_shape' of unknown type. Note: field not defined in base class.
		final IDataset entrance_slit_shape = group.getDataset("entrance_slit_shape");
		validateFieldNotNull("entrance_slit_shape)", entrance_slit_shape);
		validateFieldEnumeration("entrance_slit_shape", entrance_slit_shape,
				"curved",
				"straight");

		// validate field 'entrance_slit_setting' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset entrance_slit_setting = group.getDataset("entrance_slit_setting");
		validateFieldNotNull("entrance_slit_setting)", entrance_slit_setting);
		validateFieldType("entrance_slit_setting)", entrance_slit_setting, NX_NUMBER);
		validateFieldUnits("entrance_slit_setting", entrance_slit_setting, NX_ANY);

		// validate field 'entrance_slit_size' of unknown type. Note: field not defined in base class.
		final IDataset entrance_slit_size = group.getDataset("entrance_slit_size");
		validateFieldNotNull("entrance_slit_size)", entrance_slit_size);
		validateFieldUnits("entrance_slit_size", entrance_slit_size, NX_LENGTH);

		// validate field 'pass_energy' of unknown type. Note: field not defined in base class.
		final IDataset pass_energy = group.getDataset("pass_energy");
		validateFieldNotNull("pass_energy)", pass_energy);
		validateFieldUnits("pass_energy", pass_energy, NX_ENERGY);

		// validate field 'time_per_channel' of unknown type. Note: field not defined in base class.
		final IDataset time_per_channel = group.getDataset("time_per_channel");
		validateFieldNotNull("time_per_channel)", time_per_channel);
		validateFieldUnits("time_per_channel", time_per_channel, NX_TIME);

		// validate field 'angles' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset angles = group.getDataset("angles");
		validateFieldNotNull("angles)", angles);
		validateFieldType("angles)", angles, NX_NUMBER);
		validateFieldUnits("angles", angles, NX_ANGLE);

		// validate field 'energies' of type NX_NUMBER. Note: field not defined in base class.
		final IDataset energies = group.getDataset("energies");
		validateFieldNotNull("energies)", energies);
		validateFieldType("energies)", energies, NX_NUMBER);
		validateFieldUnits("energies", energies, NX_ENERGY);

		// validate field 'sensor_size' of type NX_INT. Note: field not defined in base class.
		final IDataset sensor_size = group.getDataset("sensor_size");
		validateFieldNotNull("sensor_size)", sensor_size);
		validateFieldType("sensor_size)", sensor_size, NX_INT);
		validateFieldRank("sensor_size", sensor_size, 2);

		// validate field 'region_origin' of type NX_INT. Note: field not defined in base class.
		final IDataset region_origin = group.getDataset("region_origin");
		validateFieldNotNull("region_origin)", region_origin);
		validateFieldType("region_origin)", region_origin, NX_INT);
		validateFieldRank("region_origin", region_origin, 2);

		// validate field 'region_size' of type NX_INT. Note: field not defined in base class.
		final IDataset region_size = group.getDataset("region_size");
		validateFieldNotNull("region_size)", region_size);
		validateFieldType("region_size)", region_size, NX_INT);
		validateFieldRank("region_size", region_size, 2);
	}

	/**
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);

		// validate field 'temperature' of type NX_NUMBER.
		final IDataset temperature = group.getTemperature();
		validateFieldNotNull("temperature)", temperature);
		validateFieldType("temperature)", temperature, NX_NUMBER);
		validateFieldUnits("temperature", temperature, NX_TEMPERATURE);
		validateFieldDimensions("temperature", temperature, "NXsample", "n_Temp");
	}

	/**
	 * Validate unnamed group of type NXdata.
	 */
	private void validateGroup_NXentry_NXdata(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
