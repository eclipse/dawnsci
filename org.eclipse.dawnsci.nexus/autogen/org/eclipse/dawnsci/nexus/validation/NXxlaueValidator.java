/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-06-07T11:32:36.439+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.validation;
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXdata;

/**
 * Validator for the application definition 'NXxlaue'.
 */
public class NXxlaueValidator extends AbstractNexusValidator implements NexusApplicationValidator {

	@Override
	public void validate(NXroot root) throws NexusValidationException {
		// validate child group 'entry' of type NXentry
		validateGroup_entry(root.getEntry());
	}

	@Override
	public void validate(NXentry entry) throws NexusValidationException {
		validateGroup_entry(entry);
	}

	@Override
	public void validate(NXsubentry subentry) throws NexusValidationException {
		validateGroup_entry(subentry);
	}


	/**
	 * Validate group 'entry' of type NXentry.
	 */
	private void validateGroup_entry(final NXsubentry group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("entry", NXentry.class, group);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition", definition);
		validateFieldEnumeration("definition", definition,
				"NXxlaue");

		// validate child group 'instrument' of type NXinstrument
		validateGroup_entry_instrument(group.getInstrument());
	}

	/**
	 * Validate group 'instrument' of type NXinstrument.
	 */
	private void validateGroup_entry_instrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("instrument", NXinstrument.class, group);

		// validate child group 'source' of type NXsource
		validateGroup_entry_instrument_source(group.getSource());
	}

	/**
	 * Validate group 'source' of type NXsource.
	 */
	private void validateGroup_entry_instrument_source(final NXsource group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("source", NXsource.class, group);

		// validate child group 'distribution' of type NXdata
		validateGroup_entry_instrument_source_distribution(group.getDistribution());
	}

	/**
	 * Validate group 'distribution' of type NXdata.
	 */
	private void validateGroup_entry_instrument_source_distribution(final NXdata group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("distribution", NXdata.class, group);
		clearLocalGroupDimensionPlaceholderValues();

		// validate field 'data' of unknown type.
		final IDataset data = group.getData();
		validateFieldNotNull("data", data);
		validateFieldType("data", data, NX_NUMBER);
		validateFieldRank("data", data, 1);
		validateFieldDimensions("data", data, null, "ne");

		// validate field 'wavelength' of unknown type. Note: field not defined in base class.
		final IDataset wavelength = group.getDataset("wavelength");
		validateFieldNotNull("wavelength", wavelength);
		validateFieldUnits("wavelength", wavelength, NX_WAVELENGTH);
		validateFieldRank("wavelength", wavelength, 1);
		validateFieldDimensions("wavelength", wavelength, null, "ne");
	}
}
