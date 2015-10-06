package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXattenuator;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXtransformations;
import org.eclipse.dawnsci.nexus.NXcollection;
import org.eclipse.dawnsci.nexus.NXdetector_module;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXbeam;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXmx'.
 */
public class NXmxValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate optional field 'title' of type NX_CHAR.
		final IDataset title = group.getTitle();
		if (title != null) {
			validateFieldType("title)", title, NX_CHAR);
		}

		// validate optional field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		if (start_time != null) {
			validateFieldType("start_time)", start_time, NX_DATE_TIME);
		}

		// validate optional field 'end_time' of type NX_DATE_TIME.
		final IDataset end_time = group.getEnd_time();
		if (end_time != null) {
			validateFieldType("end_time)", end_time, NX_DATE_TIME);
		}

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXmx");

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

		// validate unnamed child group of type NXattenuator (possibly multiple)
// $groupNameInBaseClass = attenuator
		final Map<String, NXattenuator> allAttenuator = group.getAllAttenuator();
		for (final NXattenuator attenuator : allAttenuator.values()) {
			validateGroup_NXentry_NXinstrument_NXattenuator(attenuator);
		}

		// validate unnamed child group of type NXdetector (possibly multiple)
// $groupNameInBaseClass = detector
		final Map<String, NXdetector> allDetector = group.getAllDetector();
		for (final NXdetector detector : allDetector.values()) {
			validateGroup_NXentry_NXinstrument_NXdetector(detector);
		}
	}

	/**
	 * Validate optional unnamed group of type NXattenuator.
	 */
	private void validateGroup_NXentry_NXinstrument_NXattenuator(final NXattenuator group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXattenuator.class, group);

		// validate optional field 'attenuator_transmission' of type NX_NUMBER.
		final IDataset attenuator_transmission = group.getAttenuator_transmission();
		if (attenuator_transmission != null) {
			validateFieldType("attenuator_transmission)", attenuator_transmission, NX_NUMBER);
			validateFieldUnits("attenuator_transmission", attenuator_transmission, NX_UNITLESS);
		}
	}

	/**
	 * Validate unnamed group of type NXdetector.
	 */
	private void validateGroup_NXentry_NXinstrument_NXdetector(final NXdetector group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'depends_on' of type NX_CHAR. Note: field not defined in base class.
		final IDataset depends_on = group.getDataset("depends_on");
		validateFieldNotNull("depends_on)", depends_on);
		validateFieldType("depends_on)", depends_on, NX_CHAR);

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_NUMBER);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "np", "i", "j");

		// validate optional field 'description' of unknown type.
		final IDataset description = group.getDescription();
		if (description != null) {
		}

		// validate optional field 'time_per_channel' of unknown type. Note: field not defined in base class.
		final IDataset time_per_channel = group.getDataset("time_per_channel");
		if (time_per_channel != null) {
			validateFieldUnits("time_per_channel", time_per_channel, NX_TIME);
		}

		// validate optional field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		if (distance != null) {
			validateFieldType("distance)", distance, NX_FLOAT);
			validateFieldUnits("distance", distance, NX_LENGTH);
			validateFieldRank("distance", distance, 3);
			validateFieldDimensions("distance", distance, "NXdetector", "np", "i", "j");
		}

		// validate optional field 'dead_time' of type NX_FLOAT.
		final IDataset dead_time = group.getDead_time();
		if (dead_time != null) {
			validateFieldType("dead_time)", dead_time, NX_FLOAT);
			validateFieldUnits("dead_time", dead_time, NX_TIME);
			validateFieldRank("dead_time", dead_time, 3);
			validateFieldDimensions("dead_time", dead_time, "NXdetector", "np", "i", "j");
		}

		// validate optional field 'count_time' of type NX_NUMBER.
		final IDataset count_time = group.getCount_time();
		if (count_time != null) {
			validateFieldType("count_time)", count_time, NX_NUMBER);
			validateFieldUnits("count_time", count_time, NX_TIME);
			validateFieldRank("count_time", count_time, 1);
			validateFieldDimensions("count_time", count_time, "NXdetector", "np");
		}

		// validate optional field 'beam_center_x' of type NX_FLOAT.
		final IDataset beam_center_x = group.getBeam_center_x();
		if (beam_center_x != null) {
			validateFieldType("beam_center_x)", beam_center_x, NX_FLOAT);
			validateFieldUnits("beam_center_x", beam_center_x, NX_LENGTH);
		}

		// validate optional field 'beam_center_y' of type NX_FLOAT.
		final IDataset beam_center_y = group.getBeam_center_y();
		if (beam_center_y != null) {
			validateFieldType("beam_center_y)", beam_center_y, NX_FLOAT);
			validateFieldUnits("beam_center_y", beam_center_y, NX_LENGTH);
		}

		// validate optional field 'angular_calibration_applied' of type NX_BOOLEAN.
		final IDataset angular_calibration_applied = group.getAngular_calibration_applied();
		if (angular_calibration_applied != null) {
			validateFieldType("angular_calibration_applied)", angular_calibration_applied, NX_BOOLEAN);
		}

		// validate optional field 'angular_calibration' of type NX_FLOAT.
		final IDataset angular_calibration = group.getAngular_calibration();
		if (angular_calibration != null) {
			validateFieldType("angular_calibration)", angular_calibration, NX_FLOAT);
			validateFieldRank("angular_calibration", angular_calibration, 2);
			validateFieldDimensions("angular_calibration", angular_calibration, null, "i", "j");
		}

		// validate optional field 'flatfield_applied' of type NX_BOOLEAN.
		final IDataset flatfield_applied = group.getFlatfield_applied();
		if (flatfield_applied != null) {
			validateFieldType("flatfield_applied)", flatfield_applied, NX_BOOLEAN);
		}

		// validate optional field 'flatfield' of type NX_FLOAT.
		final IDataset flatfield = group.getFlatfield();
		if (flatfield != null) {
			validateFieldType("flatfield)", flatfield, NX_FLOAT);
			validateFieldRank("flatfield", flatfield, 2);
			validateFieldDimensions("flatfield", flatfield, null, "i", "j");
		}

		// validate optional field 'flatfield_error' of type NX_FLOAT.
		final IDataset flatfield_error = group.getFlatfield_error();
		if (flatfield_error != null) {
			validateFieldType("flatfield_error)", flatfield_error, NX_FLOAT);
			validateFieldRank("flatfield_error", flatfield_error, 2);
			validateFieldDimensions("flatfield_error", flatfield_error, null, "i", "j");
		}

		// validate optional field 'pixel_mask_applied' of type NX_BOOLEAN.
		final IDataset pixel_mask_applied = group.getPixel_mask_applied();
		if (pixel_mask_applied != null) {
			validateFieldType("pixel_mask_applied)", pixel_mask_applied, NX_BOOLEAN);
		}

		// validate optional field 'pixel_mask' of type NX_INT.
		final IDataset pixel_mask = group.getPixel_mask();
		if (pixel_mask != null) {
			validateFieldType("pixel_mask)", pixel_mask, NX_INT);
			validateFieldRank("pixel_mask", pixel_mask, 2);
			validateFieldDimensions("pixel_mask", pixel_mask, null, "i", "j");
		}

		// validate optional field 'countrate_correction__applied' of type NX_BOOLEAN.
		final IDataset countrate_correction__applied = group.getCountrate_correction__applied();
		if (countrate_correction__applied != null) {
			validateFieldType("countrate_correction__applied)", countrate_correction__applied, NX_BOOLEAN);
		}

		// validate optional field 'bit_depth_readout' of type NX_INT.
		final IDataset bit_depth_readout = group.getBit_depth_readout();
		if (bit_depth_readout != null) {
			validateFieldType("bit_depth_readout)", bit_depth_readout, NX_INT);
		}

		// validate optional field 'detector_readout_time' of type NX_FLOAT.
		final IDataset detector_readout_time = group.getDetector_readout_time();
		if (detector_readout_time != null) {
			validateFieldType("detector_readout_time)", detector_readout_time, NX_FLOAT);
			validateFieldUnits("detector_readout_time", detector_readout_time, NX_TIME);
		}

		// validate optional field 'frame_time' of type NX_FLOAT.
		final IDataset frame_time = group.getFrame_time();
		if (frame_time != null) {
			validateFieldType("frame_time)", frame_time, NX_FLOAT);
			validateFieldUnits("frame_time", frame_time, NX_TIME);
			validateFieldRank("frame_time", frame_time, 1);
			validateFieldDimensions("frame_time", frame_time, "NXdetector", "NP");
		}

		// validate optional field 'gain_setting' of type NX_CHAR.
		final IDataset gain_setting = group.getGain_setting();
		if (gain_setting != null) {
			validateFieldType("gain_setting)", gain_setting, NX_CHAR);
			validateFieldEnumeration("gain_setting", gain_setting,
					"high",
					"standard",
					"fast",
					"auto");
		}

		// validate optional field 'saturation_value' of type NX_INT.
		final IDataset saturation_value = group.getSaturation_value();
		if (saturation_value != null) {
			validateFieldType("saturation_value)", saturation_value, NX_INT);
		}

		// validate optional field 'sensor_material' of type NX_CHAR.
		final IDataset sensor_material = group.getSensor_material();
		if (sensor_material != null) {
			validateFieldType("sensor_material)", sensor_material, NX_CHAR);
		}

		// validate optional field 'sensor_thickness' of type NX_FLOAT.
		final IDataset sensor_thickness = group.getSensor_thickness();
		if (sensor_thickness != null) {
			validateFieldType("sensor_thickness)", sensor_thickness, NX_FLOAT);
			validateFieldUnits("sensor_thickness", sensor_thickness, NX_LENGTH);
		}

		// validate optional field 'threshold_energy' of type NX_FLOAT.
		final IDataset threshold_energy = group.getThreshold_energy();
		if (threshold_energy != null) {
			validateFieldType("threshold_energy)", threshold_energy, NX_FLOAT);
			validateFieldUnits("threshold_energy", threshold_energy, NX_ENERGY);
		}

		// validate optional field 'type' of unknown type.
		final IDataset type = group.getType();
		if (type != null) {
		}
		// validate NXtransformations groups (special case)
		final Map<String, NXtransformations> allTransformations = group.getChildren(NXtransformations.class);
		validateTransformations(allTransformations, depends_on.getString(0));

		// validate unnamed child group of type NXcollection (possibly multiple)
// $groupNameInBaseClass = collection
		final Map<String, NXcollection> allCollection = group.getAllCollection();
		for (final NXcollection collection : allCollection.values()) {
			validateGroup_NXentry_NXinstrument_NXdetector_NXcollection(collection);
		}

		// validate unnamed child group of type NXdetector_module (possibly multiple)
// $groupNameInBaseClass = detector_module
		final Map<String, NXdetector_module> allDetector_module = group.getAllDetector_module();
		for (final NXdetector_module detector_module : allDetector_module.values()) {
			validateGroup_NXentry_NXinstrument_NXdetector_NXdetector_module(detector_module);
		}
	}

	/**
	 * Validate optional unnamed group of type NXcollection.
	 */
	private void validateGroup_NXentry_NXinstrument_NXdetector_NXcollection(final NXcollection group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXcollection.class, group);

	}

	/**
	 * Validate unnamed group of type NXdetector_module.
	 */
	private void validateGroup_NXentry_NXinstrument_NXdetector_NXdetector_module(final NXdetector_module group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXdetector_module.class, group);

		// validate field 'data_origin' of type NX_INT.
		final IDataset data_origin = group.getData_origin();
		validateFieldNotNull("data_origin)", data_origin);
		validateFieldType("data_origin)", data_origin, NX_INT);

		// validate field 'data_size' of type NX_INT.
		final IDataset data_size = group.getData_size();
		validateFieldNotNull("data_size)", data_size);
		validateFieldType("data_size)", data_size, NX_INT);

		// validate field 'module_offset' of type NX_NUMBER.
		final IDataset module_offset = group.getModule_offset();
		validateFieldNotNull("module_offset)", module_offset);
		validateFieldType("module_offset)", module_offset, NX_NUMBER);
		validateFieldUnits("module_offset", module_offset, NX_LENGTH);

		// validate field 'fast_pixel_direction' of type NX_NUMBER.
		final IDataset fast_pixel_direction = group.getFast_pixel_direction();
		validateFieldNotNull("fast_pixel_direction)", fast_pixel_direction);
		validateFieldType("fast_pixel_direction)", fast_pixel_direction, NX_NUMBER);
		validateFieldUnits("fast_pixel_direction", fast_pixel_direction, NX_LENGTH);

		// validate field 'slow_pixel_direction' of type NX_NUMBER.
		final IDataset slow_pixel_direction = group.getSlow_pixel_direction();
		validateFieldNotNull("slow_pixel_direction)", slow_pixel_direction);
		validateFieldType("slow_pixel_direction)", slow_pixel_direction, NX_NUMBER);
		validateFieldUnits("slow_pixel_direction", slow_pixel_direction, NX_LENGTH);
	}

	/**
	 * Validate unnamed group of type NXsample.
	 */
	private void validateGroup_NXentry_NXsample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate optional field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		if (name != null) {
			validateFieldType("name)", name, NX_CHAR);
		}

		// validate optional field 'depends_on' of type NX_CHAR. Note: field not defined in base class.
		final IDataset depends_on = group.getDataset("depends_on");
		if (depends_on != null) {
			validateFieldType("depends_on)", depends_on, NX_CHAR);
		}

		// validate optional field 'temperature' of unknown type.
		final IDataset temperature = group.getTemperature();
		if (temperature != null) {
			validateFieldType("temperature)", temperature, NX_FLOAT);
			validateFieldUnits("temperature", temperature, NX_TEMPERATURE);
			validateFieldDimensions("temperature", temperature, "NXsample", "n_Temp");
		}

		// validate unnamed child group of type NXbeam (possibly multiple)
// $groupNameInBaseClass = beam
		final Map<String, NXbeam> allBeam = group.getAllBeam();
		for (final NXbeam beam : allBeam.values()) {
			validateGroup_NXentry_NXsample_NXbeam(beam);
		}

		// validate NXtransformations groups (special case)
		final Map<String, NXtransformations> allTransformations = group.getChildren(NXtransformations.class);
		validateTransformations(allTransformations, depends_on.getString(0));
	}

	/**
	 * Validate unnamed group of type NXbeam.
	 */
	private void validateGroup_NXentry_NXsample_NXbeam(final NXbeam group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, NXbeam.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate optional field 'incident_wavelength' of type NX_NUMBER.
		final IDataset incident_wavelength = group.getIncident_wavelength();
		if (incident_wavelength != null) {
			validateFieldType("incident_wavelength)", incident_wavelength, NX_NUMBER);
			validateFieldUnits("incident_wavelength", incident_wavelength, NX_WAVELENGTH);
			validateFieldRank("incident_wavelength", incident_wavelength, 1);
			validateFieldDimensions("incident_wavelength", incident_wavelength, "NXbeam", "i");
		}

		// validate optional field 'flux' of type NX_FLOAT.
		final IDataset flux = group.getFlux();
		if (flux != null) {
			validateFieldType("flux)", flux, NX_FLOAT);
			validateFieldUnits("flux", flux, NX_FLUX);
			validateFieldRank("flux", flux, 1);
			validateFieldDimensions("flux", flux, "NXbeam", "i");
		}

		// validate optional field 'incident_polarisation_stokes' of unknown type. Note: field not defined in base class.
		final IDataset incident_polarisation_stokes = group.getDataset("incident_polarisation_stokes");
		if (incident_polarisation_stokes != null) {
			validateFieldRank("incident_polarisation_stokes", incident_polarisation_stokes, 2);
			validateFieldDimensions("incident_polarisation_stokes", incident_polarisation_stokes, null, "np", 4);
		}
		// validate optional child group 'incident_wavelength_spectrum' of type NXdata
// $groupNameInBaseClass = data
		if (group.getData() != null) {
			validateGroup_NXentry_NXsample_NXbeam_incident_wavelength_spectrum(group.getData());
		}
	}

	/**
	 * Validate optional group 'incident_wavelength_spectrum' of type NXdata.
	 */
	private void validateGroup_NXentry_NXsample_NXbeam_incident_wavelength_spectrum(final NXdata group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("incident_wavelength_spectrum", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

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
