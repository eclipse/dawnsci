/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of user's contact information. The format allows more
 * than one user with the same affiliation and contact information,
 * but a second NXuser group should be used if they have different
 * affiliations, etc.
 * 
 * @version 1.0
 */
public interface NXuser extends NXobject {

	/**
	 * Name of user responsible for this entry
	 * 
	 * @return  the value.
	 */
	public IDataset getName();

	/**
	 * Role of user responsible for this entry.
	 * Suggested roles are "local_contact",
	 * "principal_investigator", and "proposer"
	 * 
	 * @return  the value.
	 */
	public IDataset getRole();

	/**
	 * Affiliation of user
	 * 
	 * @return  the value.
	 */
	public IDataset getAffiliation();

	/**
	 * Address of user
	 * 
	 * @return  the value.
	 */
	public IDataset getAddress();

	/**
	 * Telephone number of user
	 * 
	 * @return  the value.
	 */
	public IDataset getTelephone_number();

	/**
	 * Fax number of user
	 * 
	 * @return  the value.
	 */
	public IDataset getFax_number();

	/**
	 * Email of user
	 * 
	 * @return  the value.
	 */
	public IDataset getEmail();

	/**
	 * facility based unique identifier for this person
	 * e.g. their identification code on the facility
	 * address/contact database
	 * 
	 * @return  the value.
	 */
	public IDataset getFacility_user_id();

}
