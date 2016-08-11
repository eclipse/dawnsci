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

import java.util.Map;

import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXfermi_chopper;

/**
 * Validator for the application definition 'NXdirecttof'.
 */
public class NXdirecttofValidator extends AbstractNexusValidator implements NexusApplicationValidator {

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

		// validate field 'title' of unknown type.
		final IDataset title = group.getTitle();
		validateFieldNotNull("title", title);

		// validate field 'start_time' of type NX_DATE_TIME.
		final IDataset start_time = group.getStart_time();
		validateFieldNotNull("start_time", start_time);
		validateFieldType("start_time", start_time, NX_DATE_TIME);

		// validate field 'definition' of unknown type.
		final IDataset definition = group.getDefinition();
		validateFieldNotNull("definition", definition);
		validateFieldEnumeration("definition", definition,
				"NXdirecttof");

		// validate unnamed child group of type NXinstrument (possibly multiple)
		final Map<String, NXinstrument> allInstrument = group.getAllInstrument();
		for (final NXinstrument instrument : allInstrument.values()) {
			validateGroup_entry_NXinstrument(instrument);
		}
	}

	/**
	 * Validate unnamed group of type NXinstrument.
	 */
	private void validateGroup_entry_NXinstrument(final NXinstrument group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull(null, NXinstrument.class, group);

		// validate child group 'fermi_chopper' of type NXfermi_chopper
		validateGroup_entry_NXinstrument_fermi_chopper(group.getFermi_chopper());
	}

	/**
	 * Validate group 'fermi_chopper' of type NXfermi_chopper.
	 */
	private void validateGroup_entry_NXinstrument_fermi_chopper(final NXfermi_chopper group) throws NexusValidationException {
		// validate that the group is not null
		validateGroupNotNull("fermi_chopper", NXfermi_chopper.class, group);

		// validate field 'rotation_speed' of type NX_FLOAT.
		final IDataset rotation_speed = group.getRotation_speed();
		validateFieldNotNull("rotation_speed", rotation_speed);
		validateFieldType("rotation_speed", rotation_speed, NX_FLOAT);
		validateFieldUnits("rotation_speed", rotation_speed, NX_FREQUENCY);

		// validate field 'energy' of type NX_FLOAT.
		final IDataset energy = group.getEnergy();
		validateFieldNotNull("energy", energy);
		validateFieldType("energy", energy, NX_FLOAT);
		validateFieldUnits("energy", energy, NX_ENERGY);
	}
}
