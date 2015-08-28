package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXuser;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXsample;

/**
 * Validator for the application definition 'NXarchive'.
 */
public class NXarchiveValidator extends AbstractNXValidator implements NXApplicationValidator {

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

		// validate attribute 'index'
		final Attribute indexAttr = group.getAttribute("index");
		validateAttributeNotNull("index", indexAttr);

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title)", title);

		// validate field 'experiment_identifier' of type NX_CHAR.
		final IDataset experiment_identifier = group.getExperiment_identifier();
		validateFieldNotNull("experiment_identifier)", experiment_identifier);
		validateFieldType("experiment_identifier)", experiment_identifier, NX_CHAR);

		// validate field 'experiment_description' of type NX_CHAR.
		final IDataset experiment_description = group.getExperiment_description();
		validateFieldNotNull("experiment_description)", experiment_description);
		validateFieldType("experiment_description)", experiment_description, NX_CHAR);

		// validate field 'collection_identifier' of type NX_CHAR.
		final IDataset collection_identifier = group.getCollection_identifier();
		validateFieldNotNull("collection_identifier)", collection_identifier);
		validateFieldType("collection_identifier)", collection_identifier, NX_CHAR);

		// validate field 'collection_description' of type NX_CHAR.
		final IDataset collection_description = group.getCollection_description();
		validateFieldNotNull("collection_description)", collection_description);
		validateFieldType("collection_description)", collection_description, NX_CHAR);

		// validate field 'entry_identifier' of type NX_CHAR.
		final IDataset entry_identifier = group.getEntry_identifier();
		validateFieldNotNull("entry_identifier)", entry_identifier);
		validateFieldType("entry_identifier)", entry_identifier, NX_CHAR);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time)", start_time);
		validateFieldType("start_time)", start_time, NX_DATE_TIME);

		// validate field 'end_time' of type NX_DATE_TIME.
		final IDataset end_time = group.getEnd_time();
		validateFieldNotNull("end_time)", end_time);
		validateFieldType("end_time)", end_time, NX_DATE_TIME);

		// validate field 'duration' of type NX_FLOAT.
		final IDataset duration = group.getDuration();
		validateFieldNotNull("duration)", duration);
		validateFieldType("duration)", duration, NX_FLOAT);
		validateFieldUnits("duration", duration, NX_TIME);

		// validate field 'collection_time' of type NX_FLOAT.
		final IDataset collection_time = group.getCollection_time();
		validateFieldNotNull("collection_time)", collection_time);
		validateFieldType("collection_time)", collection_time, NX_FLOAT);
		validateFieldUnits("collection_time", collection_time, NX_TIME);

		// validate field 'run_cycle' of type NX_CHAR.
		final IDataset run_cycle = group.getRun_cycle();
		validateFieldNotNull("run_cycle)", run_cycle);
		validateFieldType("run_cycle)", run_cycle, NX_CHAR);

		// validate field 'revision' of type NX_CHAR.
		final IDataset revision = group.getRevision();
		validateFieldNotNull("revision)", revision);
		validateFieldType("revision)", revision, NX_CHAR);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition)", definition);
		validateFieldEnumeration("definition", definition,
				"NXarchive");

		// validate field 'program' of type NX_CHAR. Note: field not defined in base class.
		final IDataset program = group.getDataset("program");
		validateFieldNotNull("program)", program);
		validateFieldType("program)", program, NX_CHAR);

		// validate field 'release_date' of type NX_CHAR. Note: field not defined in base class.
		final IDataset release_date = group.getDataset("release_date");
		validateFieldNotNull("release_date)", release_date);
		validateFieldType("release_date)", release_date, NX_CHAR);
		validateFieldUnits("release_date", release_date, NX_TIME);

		// validate child group 'user' of type NXuser
		validateGroup_entry_user(group.getUser());

		// validate child group 'instrument' of type NXinstrument
		validateGroup_entry_instrument(group.getInstrument());

		// validate child group 'sample' of type NXsample
		validateGroup_entry_sample(group.getSample());
	}

	/**
	 * Validate group 'user' of type NXuser.
	 */
	private void validateGroup_entry_user(final NXuser group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("user", NXuser.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);

		// validate field 'role' of type NX_CHAR.
		final IDataset role = group.getRole();
		validateFieldNotNull("role)", role);
		validateFieldType("role)", role, NX_CHAR);

		// validate field 'facility_user_id' of type NX_CHAR.
		final IDataset facility_user_id = group.getFacility_user_id();
		validateFieldNotNull("facility_user_id)", facility_user_id);
		validateFieldType("facility_user_id)", facility_user_id, NX_CHAR);
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate field 'name' of type NX_CHAR.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);
		validateFieldType("name)", name, NX_CHAR);

		// validate field 'description' of type NX_CHAR. Note: field not defined in base class.
		final IDataset description = group.getDataset("description");
		validateFieldNotNull("description)", description);
		validateFieldType("description)", description, NX_CHAR);
		// validate unnamed child group of type NXsource (possibly multiple)
		final Map<String, NXsource> allSource = group.getAllSource();
		for (final NXsource source : allSource.values()) {
			validateGroup_entry_instrument_NXsource(source);
		}
	}

	/**
	 * Validate unnamed group of type NXsource.
	 */
	private void validateGroup_entry_instrument_NXsource(final NXsource group) throws Exception {
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
				"Synchrotron X-Ray Source",
				"Pulsed Muon Source",
				"Rotating Anode X-Ray",
				"Fixed Tube X-Ray");

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
	 * Validate group 'sample' of type NXsample.
	 */
	private void validateGroup_entry_sample(final NXsample group) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("sample", NXsample.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'name' of unknown type.
		final IDataset name = group.getName();
		validateFieldNotNull("name)", name);

		// validate field 'sample_id' of type NX_CHAR. Note: field not defined in base class.
		final IDataset sample_id = group.getDataset("sample_id");
		validateFieldNotNull("sample_id)", sample_id);
		validateFieldType("sample_id)", sample_id, NX_CHAR);

		// validate field 'description' of type NX_CHAR.
		final IDataset description = group.getDescription();
		validateFieldNotNull("description)", description);
		validateFieldType("description)", description, NX_CHAR);

		// validate field 'type' of type NX_CHAR.
		final IDataset type = group.getType();
		validateFieldNotNull("type)", type);
		validateFieldType("type)", type, NX_CHAR);
		validateFieldEnumeration("type", type,
				"sample",
				"sample+can",
				"calibration sample",
				"normalisation sample",
				"simulated data",
				"none",
				"sample_environment");

		// validate field 'chemical_formula' of type NX_CHAR.
		final IDataset chemical_formula = group.getChemical_formula();
		validateFieldNotNull("chemical_formula)", chemical_formula);
		validateFieldType("chemical_formula)", chemical_formula, NX_CHAR);

		// validate field 'preparation_date' of type NX_CHAR.
		final IDataset preparation_date = group.getPreparation_date();
		validateFieldNotNull("preparation_date)", preparation_date);
		validateFieldType("preparation_date)", preparation_date, NX_CHAR);
		validateFieldUnits("preparation_date", preparation_date, NX_TIME);

		// validate field 'situation' of type NX_CHAR.
		final IDataset situation = group.getSituation();
		validateFieldNotNull("situation)", situation);
		validateFieldType("situation)", situation, NX_CHAR);
		validateFieldEnumeration("situation", situation,
				"air",
				"vacuum",
				"inert atmosphere",
				"oxidising atmosphere",
				"reducing atmosphere",
				"sealed can",
				"other");

		// validate field 'temperature' of type NX_FLOAT.
		final IDataset temperature = group.getTemperature();
		validateFieldNotNull("temperature)", temperature);
		validateFieldType("temperature)", temperature, NX_FLOAT);
		validateFieldUnits("temperature", temperature, NX_TEMPERATURE);
		validateFieldDimensions("temperature", temperature, "NXsample", "n_Temp");

		// validate field 'magnetic_field' of type NX_FLOAT.
		final IDataset magnetic_field = group.getMagnetic_field();
		validateFieldNotNull("magnetic_field)", magnetic_field);
		validateFieldType("magnetic_field)", magnetic_field, NX_FLOAT);
		validateFieldUnits("magnetic_field", magnetic_field, NX_CURRENT);
		validateFieldDimensions("magnetic_field", magnetic_field, "NXsample", "n_mField");

		// validate field 'electric_field' of type NX_FLOAT.
		final IDataset electric_field = group.getElectric_field();
		validateFieldNotNull("electric_field)", electric_field);
		validateFieldType("electric_field)", electric_field, NX_FLOAT);
		validateFieldUnits("electric_field", electric_field, NX_VOLTAGE);
		validateFieldDimensions("electric_field", electric_field, "NXsample", "n_eField");

		// validate field 'stress_field' of type NX_FLOAT.
		final IDataset stress_field = group.getStress_field();
		validateFieldNotNull("stress_field)", stress_field);
		validateFieldType("stress_field)", stress_field, NX_FLOAT);
		validateFieldUnits("stress_field", stress_field, NX_UNITLESS);
		validateFieldDimensions("stress_field", stress_field, "NXsample", "n_sField");

		// validate field 'pressure' of type NX_FLOAT.
		final IDataset pressure = group.getPressure();
		validateFieldNotNull("pressure)", pressure);
		validateFieldType("pressure)", pressure, NX_FLOAT);
		validateFieldUnits("pressure", pressure, NX_PRESSURE);
		validateFieldDimensions("pressure", pressure, "NXsample", "n_pField");
	}
}
