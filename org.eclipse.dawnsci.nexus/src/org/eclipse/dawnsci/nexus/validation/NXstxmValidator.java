package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXmonochromator;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXmonitor;

/**
 * Validator for the application definition 'NXstxm'.
 */
public class NXstxmValidator extends AbstractNXValidator implements NXApplicationValidator {

@Override
	public void validate(NXroot root) throws Exception {
		// validate unnamed child group of type NXentry (possibly multiple)
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

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title)", title);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time)", start_time);
		validateFieldType("start_time)", start_time, NX_DATE_TIME);

		// validate field 'end_time' of type NX_DATE_TIME.
		final IDataset end_time = group.getEnd_time();
		validateFieldNotNull("end_time)", end_time);
		validateFieldType("end_time)", end_time, NX_DATE_TIME);

		// validate field 'definition' of type NX_CHAR.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldType("definition)", definition, NX_CHAR);
		validateFieldEnumeration("definition", definition,
				"NXstxm");

		// validate unnamed child group of type NXinstrument
		validateGroup_NXentry_NXinstrument(group.getInstrument());

		// validate unnamed child group of type NXsample (possibly multiple)
		final Map<String, NXsample> allSample = group.getAllSample();
		for (final NXsample sample : allSample.values()) {
			validateGroup_NXentry_NXsample(sample);
		}

		// validate unnamed child group of type NXdata (possibly multiple)
		final Map<String, NXdata> allData = group.getAllData();
		for (final NXdata data : allData.values()) {
			validateGroup_NXentry_NXdata(data);
		}

		// validate optional child group 'control' of type NXmonitor
		if (group.getMonitor() != null) {
			validateGroup_NXentry_control(group.getMonitor());
		}
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_NXentry_NXinstrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate unnamed child group of type NXsource
		validateGroup_NXentry_NXinstrument_NXsource(group.getSource());

		// validate child group 'monochromator' of type NXmonochromator
		validateGroup_NXentry_NXinstrument_monochromator(group.getMonochromator());

		// validate unnamed child group of type NXdetector (possibly multiple)
		final Map<String, NXdetector> allDetector = group.getAllDetector();
		for (final NXdetector detector : allDetector.values()) {
			validateGroup_NXentry_NXinstrument_NXdetector(detector);
		}

		// validate optional child group 'sample_x' of type NXdetector
		if (group.getDetector() != null) {
			validateGroup_NXentry_NXinstrument_sample_x(group.getDetector());
		}

		// validate optional child group 'sample_y' of type NXdetector
		if (group.getDetector() != null) {
			validateGroup_NXentry_NXinstrument_sample_y(group.getDetector());
		}

		// validate optional child group 'sample_z' of type NXdetector
		if (group.getDetector() != null) {
			validateGroup_NXentry_NXinstrument_sample_z(group.getDetector());
		}
	}

	/**
	 * Validate unnamed group of type NXsource.
	 */
	private void validateGroup_NXentry_NXinstrument_NXsource(final NXsource group) throws Exception {
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
				"muon",
				"electron",
				"ultraviolet",
				"visible light",
				"positron",
				"proton");
	}

	/**
	 * Validate group 'monochromator' of type NXmonochromator.
	 */
	private void validateGroup_NXentry_NXinstrument_monochromator(final NXmonochromator group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("monochromator", NXmonochromator.class, group);

		// validate field 'energy' of unknown type.
		final IDataset energy = group.getEnergy();
		validateFieldNotNull("energy)", energy);
		validateFieldType("energy)", energy, NX_FLOAT);
		validateFieldUnits("energy", energy, NX_ENERGY);
		validateFieldRank("energy", energy, 1);
		validateFieldDimensions("energy", energy, null, "NumP");
	}

	/**
	 * Validate unnamed group of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_NXdetector(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_NUMBER);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 4);
		validateFieldDimensions("data", data, null, "NumP");
	}

	/**
	 * Validate optional group 'sample_x' of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_sample_x(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample_x", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_FLOAT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_FLOAT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "NumP");
	}

	/**
	 * Validate optional group 'sample_y' of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_sample_y(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample_y", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_FLOAT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_FLOAT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "NumP");
	}

	/**
	 * Validate optional group 'sample_z' of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_sample_z(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample_z", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_FLOAT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_FLOAT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "NumP");
	}

	/**
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'rotation_angle' of type NX_FLOAT.
		final IDataset rotation_angle = group.getRotation_angle();
		validateFieldNotNull("rotation_angle)", rotation_angle);
		validateFieldType("rotation_angle)", rotation_angle, NX_FLOAT);
		validateFieldUnits("rotation_angle", rotation_angle, NX_ANGLE);
	}

	/**
	 * Validate unnamed group of type NXdata.
	 */
	private void validateGroup_NXentry_NXdata(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'stxm_scan_type' of unknown type. Note: field not defined in base class.
		final IDataset stxm_scan_type = group.getDataset("stxm_scan_type");
		validateFieldNotNull("stxm_scan_type)", stxm_scan_type);
		validateFieldEnumeration("stxm_scan_type", stxm_scan_type,
				"sample point spectrum",
				"sample line spectrum",
				"sample image",
				"sample image stack",
				"sample focus",
				"osa image",
				"osa focus",
				"detector image",
				"generic scan");

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_NUMBER);
		validateFieldDimensions("data", data, "NXdata", "n");

		// validate field 'energy' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset energy = group.getDataset("energy");
		validateFieldNotNull("energy)", energy);
		validateFieldType("energy)", energy, NX_FLOAT);
		validateFieldRank("energy", energy, 1);
		validateFieldDimensions("energy", energy, null, "NumE");

		// validate field 'sample_y' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset sample_y = group.getDataset("sample_y");
		validateFieldNotNull("sample_y)", sample_y);
		validateFieldType("sample_y)", sample_y, NX_FLOAT);
		validateFieldRank("sample_y", sample_y, 1);
		validateFieldDimensions("sample_y", sample_y, null, "NumY");

		// validate field 'sample_x' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset sample_x = group.getDataset("sample_x");
		validateFieldNotNull("sample_x)", sample_x);
		validateFieldType("sample_x)", sample_x, NX_FLOAT);
		validateFieldRank("sample_x", sample_x, 1);
		validateFieldDimensions("sample_x", sample_x, null, "NumX");
	}

	/**
	 * Validate optional group 'control' of type NXmonitor.
	 */
	private void validateGroup_NXentry_control(final NXmonitor group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("control", NXmonitor.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_FLOAT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_FLOAT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldDimensions("data", data, "NXmonitor", "n");
	}
}
