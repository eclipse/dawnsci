/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-06-07T11:32:35.177+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * Contact information for a user.
 * The format allows more
 * than one user with the same affiliation and contact information,
 * but a second :ref:`NXuser` group should be used if they have different
 * affiliations, etc.
 * 
 * @version 1.0
 */
public interface NXuser extends NXobject {

	public static final String NX_NAME = "name";
	public static final String NX_ROLE = "role";
	public static final String NX_AFFILIATION = "affiliation";
	public static final String NX_ADDRESS = "address";
	public static final String NX_TELEPHONE_NUMBER = "telephone_number";
	public static final String NX_FAX_NUMBER = "fax_number";
	public static final String NX_EMAIL = "email";
	public static final String NX_FACILITY_USER_ID = "facility_user_id";
	/**
	 * Name of user responsible for this entry
	 * 
	 * @return  the value.
	 */
	public IDataset getName();
	
	/**
	 * Name of user responsible for this entry
	 * 
	 * @param name the name
	 */
	public DataNode setName(IDataset name);

	/**
	 * Name of user responsible for this entry
	 * 
	 * @return  the value.
	 */
	public String getNameScalar();

	/**
	 * Name of user responsible for this entry
	 * 
	 * @param name the name
	 */
	public DataNode setNameScalar(String name);

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
	 * @param role the role
	 */
	public DataNode setRole(IDataset role);

	/**
	 * Role of user responsible for this entry.
	 * Suggested roles are "local_contact",
	 * "principal_investigator", and "proposer"
	 * 
	 * @return  the value.
	 */
	public String getRoleScalar();

	/**
	 * Role of user responsible for this entry.
	 * Suggested roles are "local_contact",
	 * "principal_investigator", and "proposer"
	 * 
	 * @param role the role
	 */
	public DataNode setRoleScalar(String role);

	/**
	 * Affiliation of user
	 * 
	 * @return  the value.
	 */
	public IDataset getAffiliation();
	
	/**
	 * Affiliation of user
	 * 
	 * @param affiliation the affiliation
	 */
	public DataNode setAffiliation(IDataset affiliation);

	/**
	 * Affiliation of user
	 * 
	 * @return  the value.
	 */
	public String getAffiliationScalar();

	/**
	 * Affiliation of user
	 * 
	 * @param affiliation the affiliation
	 */
	public DataNode setAffiliationScalar(String affiliation);

	/**
	 * Address of user
	 * 
	 * @return  the value.
	 */
	public IDataset getAddress();
	
	/**
	 * Address of user
	 * 
	 * @param address the address
	 */
	public DataNode setAddress(IDataset address);

	/**
	 * Address of user
	 * 
	 * @return  the value.
	 */
	public String getAddressScalar();

	/**
	 * Address of user
	 * 
	 * @param address the address
	 */
	public DataNode setAddressScalar(String address);

	/**
	 * Telephone number of user
	 * 
	 * @return  the value.
	 */
	public IDataset getTelephone_number();
	
	/**
	 * Telephone number of user
	 * 
	 * @param telephone_number the telephone_number
	 */
	public DataNode setTelephone_number(IDataset telephone_number);

	/**
	 * Telephone number of user
	 * 
	 * @return  the value.
	 */
	public String getTelephone_numberScalar();

	/**
	 * Telephone number of user
	 * 
	 * @param telephone_number the telephone_number
	 */
	public DataNode setTelephone_numberScalar(String telephone_number);

	/**
	 * Fax number of user
	 * 
	 * @return  the value.
	 */
	public IDataset getFax_number();
	
	/**
	 * Fax number of user
	 * 
	 * @param fax_number the fax_number
	 */
	public DataNode setFax_number(IDataset fax_number);

	/**
	 * Fax number of user
	 * 
	 * @return  the value.
	 */
	public String getFax_numberScalar();

	/**
	 * Fax number of user
	 * 
	 * @param fax_number the fax_number
	 */
	public DataNode setFax_numberScalar(String fax_number);

	/**
	 * Email of user
	 * 
	 * @return  the value.
	 */
	public IDataset getEmail();
	
	/**
	 * Email of user
	 * 
	 * @param email the email
	 */
	public DataNode setEmail(IDataset email);

	/**
	 * Email of user
	 * 
	 * @return  the value.
	 */
	public String getEmailScalar();

	/**
	 * Email of user
	 * 
	 * @param email the email
	 */
	public DataNode setEmailScalar(String email);

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
	 * @param facility_user_id the facility_user_id
	 */
	public DataNode setFacility_user_id(IDataset facility_user_id);

	/**
	 * facility based unique identifier for this person
	 * e.g. their identification code on the facility
	 * address/contact database
	 * 
	 * @return  the value.
	 */
	public String getFacility_user_idScalar();

	/**
	 * facility based unique identifier for this person
	 * e.g. their identification code on the facility
	 * address/contact database
	 * 
	 * @param facility_user_id the facility_user_id
	 */
	public DataNode setFacility_user_idScalar(String facility_user_id);

}
