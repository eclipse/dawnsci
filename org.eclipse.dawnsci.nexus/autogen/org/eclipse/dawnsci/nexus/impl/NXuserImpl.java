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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
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
public class NXuserImpl extends NXobjectImpl implements NXuser {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXuserImpl() {
		super();
	}

	public NXuserImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXuser.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_USER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getNameScalar() {
		return getString(NX_NAME);
	}

	@Override
	public DataNode setName(IDataset name) {
		return setDataset(NX_NAME, name);
	}

	@Override
	public DataNode setNameScalar(String name) {
		return setString(NX_NAME, name);
	}

	@Override
	public IDataset getRole() {
		return getDataset(NX_ROLE);
	}

	@Override
	public String getRoleScalar() {
		return getString(NX_ROLE);
	}

	@Override
	public DataNode setRole(IDataset role) {
		return setDataset(NX_ROLE, role);
	}

	@Override
	public DataNode setRoleScalar(String role) {
		return setString(NX_ROLE, role);
	}

	@Override
	public IDataset getAffiliation() {
		return getDataset(NX_AFFILIATION);
	}

	@Override
	public String getAffiliationScalar() {
		return getString(NX_AFFILIATION);
	}

	@Override
	public DataNode setAffiliation(IDataset affiliation) {
		return setDataset(NX_AFFILIATION, affiliation);
	}

	@Override
	public DataNode setAffiliationScalar(String affiliation) {
		return setString(NX_AFFILIATION, affiliation);
	}

	@Override
	public IDataset getAddress() {
		return getDataset(NX_ADDRESS);
	}

	@Override
	public String getAddressScalar() {
		return getString(NX_ADDRESS);
	}

	@Override
	public DataNode setAddress(IDataset address) {
		return setDataset(NX_ADDRESS, address);
	}

	@Override
	public DataNode setAddressScalar(String address) {
		return setString(NX_ADDRESS, address);
	}

	@Override
	public IDataset getTelephone_number() {
		return getDataset(NX_TELEPHONE_NUMBER);
	}

	@Override
	public String getTelephone_numberScalar() {
		return getString(NX_TELEPHONE_NUMBER);
	}

	@Override
	public DataNode setTelephone_number(IDataset telephone_number) {
		return setDataset(NX_TELEPHONE_NUMBER, telephone_number);
	}

	@Override
	public DataNode setTelephone_numberScalar(String telephone_number) {
		return setString(NX_TELEPHONE_NUMBER, telephone_number);
	}

	@Override
	public IDataset getFax_number() {
		return getDataset(NX_FAX_NUMBER);
	}

	@Override
	public String getFax_numberScalar() {
		return getString(NX_FAX_NUMBER);
	}

	@Override
	public DataNode setFax_number(IDataset fax_number) {
		return setDataset(NX_FAX_NUMBER, fax_number);
	}

	@Override
	public DataNode setFax_numberScalar(String fax_number) {
		return setString(NX_FAX_NUMBER, fax_number);
	}

	@Override
	public IDataset getEmail() {
		return getDataset(NX_EMAIL);
	}

	@Override
	public String getEmailScalar() {
		return getString(NX_EMAIL);
	}

	@Override
	public DataNode setEmail(IDataset email) {
		return setDataset(NX_EMAIL, email);
	}

	@Override
	public DataNode setEmailScalar(String email) {
		return setString(NX_EMAIL, email);
	}

	@Override
	public IDataset getFacility_user_id() {
		return getDataset(NX_FACILITY_USER_ID);
	}

	@Override
	public String getFacility_user_idScalar() {
		return getString(NX_FACILITY_USER_ID);
	}

	@Override
	public DataNode setFacility_user_id(IDataset facility_user_id) {
		return setDataset(NX_FACILITY_USER_ID, facility_user_id);
	}

	@Override
	public DataNode setFacility_user_idScalar(String facility_user_id) {
		return setString(NX_FACILITY_USER_ID, facility_user_id);
	}

}
