package org.eclipse.dawnsci.nexus.validation;

import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXmonochromator;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsource;

public class NXarpesValidator extends AbstractNXValidator implements NXApplicationValidator {

	@Override
	public void validate(NXentry rootEntry) throws Exception {
		// validate attribute 'entry'
		validateAttributeNotNull("entry", rootEntry);
		
		// validate field 'title', type NX_CHAR -- TODO how to test type (we don't know the scale)
		validateDatasetField("title", rootEntry.getTitle(), NexusDataType.NX_CHAR);
		
		// validate field 'start_time', type NX_DATE_TIME 
		validateDatasetField("start_time", rootEntry.getStart_time(), NexusDataType.NX_DATE_TIME);
		
		// validate field definition, single permitted value 'NXarpes'
		validateEnumerationField("definition", rootEntry.getDefinition(), "NXarpes");
		
		// validate unnamed group of type 'NXinstrument'
		validateNXinstrumentGroup(rootEntry.getInstrument());
		
		// validate unnamed group of type 'NXsample'
		validateNXsampleGroup(rootEntry.getSample());
		
		// validate unnamed group of type 'NXdata'
		// TODO how to translate links?
		validateNXdataGroup(rootEntry.getData());
	}
	
	/**
	 * Validate the unnamed group of type NXinstrument
	 * @throws Exception 
	 */
	private void validateNXinstrumentGroup(NXinstrument nxInstrument) throws Exception {
		// validate that the group is not null 
		// NOTE: only call when minOccurs is not 0
		validateGroupNotNull(null, "NXinstrument", nxInstrument);
		
		// validate the unnamed group of type NXinstrument
		validateNXsourceGroup(nxInstrument.getSource());
		
		// validate group 'monochromator' of type NXmonochromator
		validateMonochromatorGroup(nxInstrument.getMonochromator());
		
		// validate group 'analyser' of type NXdetector
		validateAnalyser(nxInstrument.getDetector());
	}
	
	/**
	 * Validate the unnamed group of type NXsource
	 */
	private void validateNXsourceGroup(NXsource nxSource) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, "NXsource", nxSource);
		
		// validate enumeration field 'type'
		// XXX: this enumeration is from the NXsource base class definition
		// XXX: this assumes enumerations must be strings, so we don't validate the NX_CHAR type
		validateEnumerationField("type", nxSource.getType(),
				"Spallation Neutron Source",
				"Pulsed Reactor Neutron Source",
				"Reactor Neutron Source",
				"Synchrotron X-ray Source"); // XXX: and 8 more enumerated items
		
		// validate field 'name' is not null
		validateDatasetField("name", nxSource.getName(), NexusDataType.NX_CHAR);
		
		// validate enumeration field 'probe'
		// XXX: this enumeration in the application definition overrides the one in the Nxsource base class definition
		// XXX: again, we don't need to validate that the enumeration is a string
		validateEnumerationField("probe", nxSource.getProbe(), "x-ray");
	}
	
	/**
	 * Validate group monochromator of type NXmonochromator
	 * XXX: should method name be based on group name or type?
	 * Is type unique?
	 */
	private void validateMonochromatorGroup(NXmonochromator monochromator) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("monochromator", "NXmonochromator", monochromator);
		
		// validate field 'energy'
		validateDatasetField("energy", monochromator.getEnergy(), NX_NUMBER, NX_ENERGY);
	}
	
	private void validateAnalyser(NXdetector analyser) throws Exception {
		// validate that the group is not null
		validateGroupNotNull("analyser", "NXanalyser", analyser);
		
		// validate field 'data' of type NX NUMBER, units (from base class def) NX_ANY
		validateDatasetField("data", analyser.getData(), NX_NUMBER, NX_ANY, 4);
		
		// validate field 'lens_mode' of type NX_CHAR
		// TODO this field and many others do not exist in the base class - should they go in some metadata class
		validateDatasetField("lens_mode", analyser.getLensMode(), NX_CHAR);
		
		// validate field 'acquisition_mode' of type NX_CHAR (type from base class) - TODO enumeration different in application def from base class)
		validateEnumerationField("acquisition_mode", analyser.getAcquisition_mode(), "swept", "fixed");
		
		// validate enumeration field 'entrace_slit_shape" - TODO: field does not exist in base class 
		validateEnumerationField("entrance_slit_shaped", analyser.getEntrance_slit_shape(), "curved", "straight");
		
		// validate field 'entrance_slit_setting' of type 'NX_NUMBER', units NX_ANY - TODO field does not exist in base class
		validateDatasetField("entrance_slit_setting", analyser.getEntrance_slit_setting(), NX_NUMBER, NX_ANY);
		
		// validate field 'entrance_slit_size' of unknown type with unit 'NX_LENGTH'
		validateDatasetField("entrance_slit_size", analyser.getEntrance_slit_size(), NX_LENGTH); // TODO this and following fields have units but no type
		
		// validate field 'pass_energy' of unknown type with unit 'NX_ENERGY'
		validateDatasetField("pass_energy", analyser.getPass_energy(), NX_ENERGY);
		
		// validate field 'time_per_channel' of unknown type with unit 'NX_TIME'
		validateDatasetField("time_per_channel", analyser.getTime_per_channel(), NX_TIME);
		
		// validate field 'angles' of type 'NX_NUMBER' with unit 'NX_ANGLE'
		validateDatasetField("angles", analyser.getAngles(), NX_NUMBER, NX_ANGLE);
		
		// validate field 'energies' of type 'NX_NUMBER' with unit 'NX_ENERGY'
		validateDatasetField("energies", analyser.getEnergies(), NX_NUMBER, NX_ENERGY);
		
		// validate field 'sensor_size' of type 'NX_INT'
		validateDatasetField("sensor_size", analyser.getSensor_size(), NX_INT);
		
		// validate field 'region_origin' of type 'NX_INT'
		validateDatasetField("region_origin", analyser.getRegion_origin(), NX_INT);
		
		// validate field 'region_size' of type 'NX_INT'
		validateDatasetField("region_size", analyser.getRegion_size(), NX_INT);
	}
	
	private void validateNXsampleGroup(NXsample sample) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, "NXsample", sample);
		
		validateDatasetField("name", sample.getName(), NX_CHAR);
		
		validateDatasetField("temperature", sample.getTemperature(), NX_NUMBER, NX_TEMPERATURE);
	}
	
	private void validateNXdataGroup(NXdata data) throws Exception {
		// validate that the group is not null
		validateGroupNotNull(null, "NXdata", data);
	}

}
