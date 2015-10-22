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
import org.eclipse.dawnsci.nexus.NXcollimator;
import org.eclipse.dawnsci.nexus.NXgeometry;
import org.eclipse.dawnsci.nexus.NXshape;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXsastof'.
 */
public class NXsastofValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time)", start_time);
		validateFieldType("start_time)", start_time, NX_DATE_TIME);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXsastof");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_NXentry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_NXentry_sample(group.getSample());

		// validate child group 'control' of type NXmonitor
		validateGroup_NXentry_control(group.getMonitor());

		// validate child group 'data' of type NXdata
		validateGroup_NXentry_data(group.getData());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_NXentry_instrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);
		// validate child group 'source' of type NXsource
		validateGroup_NXentry_instrument_source(group.getSource());

		// validate child group 'collimator' of type NXcollimator
		validateGroup_NXentry_instrument_collimator(group.getCollimator());

		// validate child group 'detector' of type NXdetector
		validateGroup_NXentry_instrument_detector(group.getDetector());
	}

	/**
	 * Validate group 'source' of type NXsource.
	 */
	private void validateGroup_NXentry_instrument_source(final NXsource group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("source", NXsource.class, group);

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
				"x-ray");
	}

	/**
	 * Validate group 'collimator' of type NXcollimator.
	 */
	private void validateGroup_NXentry_instrument_collimator(final NXcollimator group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("collimator", NXcollimator.class, group);

		// validate child group 'geometry' of type NXgeometry
		validateGroup_NXentry_instrument_collimator_geometry(group.getGeometry());
	}

	/**
	 * Validate group 'geometry' of type NXgeometry.
	 */
	private void validateGroup_NXentry_instrument_collimator_geometry(final NXgeometry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("geometry", NXgeometry.class, group);

		// validate child group 'shape' of type NXshape
		validateGroup_NXentry_instrument_collimator_geometry_shape(group.getShape());
	}

	/**
	 * Validate group 'shape' of type NXshape.
	 */
	private void validateGroup_NXentry_instrument_collimator_geometry_shape(final NXshape group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("shape", NXshape.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'shape' of type NX_CHAR.
		final IDataset shape = group.getShape();
		validateFieldNotNull("shape)", shape);
		validateFieldType("shape)", shape, NX_CHAR);
		validateFieldEnumeration("shape", shape,
				"nxcylinder",
				"nxbox");

		// validate field 'size' of type NX_FLOAT.
		final IDataset size = group.getSize();
		validateFieldNotNull("size)", size);
		validateFieldType("size)", size, NX_FLOAT);
		validateFieldUnits("size", size, NX_LENGTH);
		validateFieldDimensions("size", size, "NXshape", "numobj", "nshapepar");
	}

	/**
	 * Validate group 'detector' of type NXdetector.
	 */
	private void validateGroup_NXentry_instrument_detector(final NXdetector group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("detector", NXdetector.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of type NX_NUMBER.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_NUMBER);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 3);
		validateFieldDimensions("data", data, null, "nXPixel", "nYPixel", "nTOF");

		// validate field 'time_of_flight' of type NX_FLOAT.
		final IDataset time_of_flight = group.getTime_of_flight();
		validateFieldNotNull("time_of_flight)", time_of_flight);
		validateFieldType("time_of_flight)", time_of_flight, NX_FLOAT);
		validateFieldUnits("time_of_flight", time_of_flight, NX_TIME_OF_FLIGHT);
		validateFieldRank("time_of_flight", time_of_flight, 1);
		validateFieldDimensions("time_of_flight", time_of_flight, null, "nTOF");

		// validate field 'distance' of type NX_FLOAT.
		final IDataset distance = group.getDistance();
		validateFieldNotNull("distance)", distance);
		validateFieldType("distance)", distance, NX_FLOAT);
		validateFieldUnits("distance", distance, NX_LENGTH);
		validateFieldRank("distance", distance, 3);
		validateFieldDimensions("distance", distance, "NXdetector", "np", "i", "j");

		// validate field 'x_pixel_size' of type NX_FLOAT.
		final IDataset x_pixel_size = group.getX_pixel_size();
		validateFieldNotNull("x_pixel_size)", x_pixel_size);
		validateFieldType("x_pixel_size)", x_pixel_size, NX_FLOAT);
		validateFieldUnits("x_pixel_size", x_pixel_size, NX_LENGTH);
		validateFieldRank("x_pixel_size", x_pixel_size, 2);
		validateFieldDimensions("x_pixel_size", x_pixel_size, "NXdetector", "i", "j");

		// validate field 'y_pixel_size' of type NX_FLOAT.
		final IDataset y_pixel_size = group.getY_pixel_size();
		validateFieldNotNull("y_pixel_size)", y_pixel_size);
		validateFieldType("y_pixel_size)", y_pixel_size, NX_FLOAT);
		validateFieldUnits("y_pixel_size", y_pixel_size, NX_LENGTH);
		validateFieldRank("y_pixel_size", y_pixel_size, 2);
		validateFieldDimensions("y_pixel_size", y_pixel_size, "NXdetector", "i", "j");

		// validate field 'polar_angle' of type NX_FLOAT.
		final IDataset polar_angle = group.getPolar_angle();
		validateFieldNotNull("polar_angle)", polar_angle);
		validateFieldType("polar_angle)", polar_angle, NX_FLOAT);
		validateFieldUnits("polar_angle", polar_angle, NX_ANGLE);
		validateFieldRank("polar_angle", polar_angle, 3);
		validateFieldDimensions("polar_angle", polar_angle, "NXdetector", "np", "i", "j");

		// validate field 'azimuthal_angle' of type NX_FLOAT.
		final IDataset azimuthal_angle = group.getAzimuthal_angle();
		validateFieldNotNull("azimuthal_angle)", azimuthal_angle);
		validateFieldType("azimuthal_angle)", azimuthal_angle, NX_FLOAT);
		validateFieldUnits("azimuthal_angle", azimuthal_angle, NX_ANGLE);
		validateFieldRank("azimuthal_angle", azimuthal_angle, 3);
		validateFieldDimensions("azimuthal_angle", azimuthal_angle, "NXdetector", "np", "i", "j");

		// validate field 'rotation_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset rotation_angle = group.getDataset("rotation_angle");
		validateFieldNotNull("rotation_angle)", rotation_angle);
		validateFieldType("rotation_angle)", rotation_angle, NX_FLOAT);
		validateFieldUnits("rotation_angle", rotation_angle, NX_ANGLE);

		// validate field 'aequatorial_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset aequatorial_angle = group.getDataset("aequatorial_angle");
		validateFieldNotNull("aequatorial_angle)", aequatorial_angle);
		validateFieldType("aequatorial_angle)", aequatorial_angle, NX_FLOAT);
		validateFieldUnits("aequatorial_angle", aequatorial_angle, NX_ANGLE);

		// validate field 'beam_center_x' of type NX_FLOAT.
		final IDataset beam_center_x = group.getBeam_center_x();
		validateFieldNotNull("beam_center_x)", beam_center_x);
		validateFieldType("beam_center_x)", beam_center_x, NX_FLOAT);
		validateFieldUnits("beam_center_x", beam_center_x, NX_LENGTH);

		// validate field 'beam_center_y' of type NX_FLOAT.
		final IDataset beam_center_y = group.getBeam_center_y();
		validateFieldNotNull("beam_center_y)", beam_center_y);
		validateFieldType("beam_center_y)", beam_center_y, NX_FLOAT);
		validateFieldUnits("beam_center_y", beam_center_y, NX_LENGTH);
	}

	/**
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_NXentry_sample(final NXsample group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("sample", NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);

		// validate field 'aequatorial_angle' of type NX_FLOAT. Note: field not defined in base class.
		final IDataset aequatorial_angle = group.getDataset("aequatorial_angle");
		validateFieldNotNull("aequatorial_angle)", aequatorial_angle);
		validateFieldType("aequatorial_angle)", aequatorial_angle, NX_FLOAT);
		validateFieldUnits("aequatorial_angle", aequatorial_angle, NX_ANGLE);
	}

	/**
	 * Validate group 'control' of type NXmonitor.
	 */
	private void validateGroup_NXentry_control(final NXmonitor group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("control", NXmonitor.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'mode' of unknown type.
		final IDataset mode = group.getMode();
		validateFieldNotNull("mode)", mode);
		validateFieldEnumeration("mode", mode,
				"monitor",
				"timer");

		// validate field 'preset' of type NX_FLOAT.
		final IDataset preset = group.getPreset();
		validateFieldNotNull("preset)", preset);
		validateFieldType("preset)", preset, NX_FLOAT);
		validateFieldUnits("preset", preset, NX_ANY);

		// validate field 'data' of type NX_INT.
		final IDataset data = group.getData();
		validateFieldNotNull("data)", data);
		validateFieldType("data)", data, NX_INT);
		validateFieldUnits("data", data, NX_ANY);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "nTOF");

		// validate field 'time_of_flight' of type NX_FLOAT.
		final IDataset time_of_flight = group.getTime_of_flight();
		validateFieldNotNull("time_of_flight)", time_of_flight);
		validateFieldType("time_of_flight)", time_of_flight, NX_FLOAT);
		validateFieldUnits("time_of_flight", time_of_flight, NX_TIME_OF_FLIGHT);
		validateFieldRank("time_of_flight", time_of_flight, 1);
		validateFieldDimensions("time_of_flight", time_of_flight, null, "nTOF");
	}

	/**
	 * Validate group 'data' of type NXdata.
	 */
	private void validateGroup_NXentry_data(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("data", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

	}
}
