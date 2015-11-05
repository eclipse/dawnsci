/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
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
	 * Name of user responsible for this entry
	 * 
	 * @return  the value
	 */
	 public String getNameScalar();

	/**
	 * Role of user responsible for this entry.
	 * Suggested roles are "local_contact",
	 * "principal_investigator", and "proposer"
	 * 
	 * @return  the value.
	 */
	public IDataset getRole();	

	/**
	 * Role of user responsible for this entry.
	 * Suggested roles are "local_contact",
	 * "principal_investigator", and "proposer"
	 * 
	 * @return  the value
	 */
	 public String getRoleScalar();

	/**
	 * Affiliation of user
	 * 
	 * @return  the value.
	 */
	public IDataset getAffiliation();	

	/**
	 * Affiliation of user
	 * 
	 * @return  the value
	 */
	 public String getAffiliationScalar();

	/**
	 * Address of user
	 * 
	 * @return  the value.
	 */
	public IDataset getAddress();	

	/**
	 * Address of user
	 * 
	 * @return  the value
	 */
	 public String getAddressScalar();

	/**
	 * Telephone number of user
	 * 
	 * @return  the value.
	 */
	public IDataset getTelephone_number();	

	/**
	 * Telephone number of user
	 * 
	 * @return  the value
	 */
	 public String getTelephone_numberScalar();

	/**
	 * Fax number of user
	 * 
	 * @return  the value.
	 */
	public IDataset getFax_number();	

	/**
	 * Fax number of user
	 * 
	 * @return  the value
	 */
	 public String getFax_numberScalar();

	/**
	 * Email of user
	 * 
	 * @return  the value.
	 */
	public IDataset getEmail();	

	/**
	 * Email of user
	 * 
	 * @return  the value
	 */
	 public String getEmailScalar();

	/**
	 * facility based unique identifier for this person
	 * e.g. their identification code on the facility
	 * address/contact database
	 * 
	 * @return  the value.
	 */
	public IDataset getFacility_user_id();	

	/**
	 * facility based unique identifier for this person
	 * e.g. their identification code on the facility
	 * address/contact database
	 * 
	 * @return  the value
	 */
	 public String getFacility_user_idScalar();

}
