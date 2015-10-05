/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.nexus.*;

/**
 * Template of user's contact information. The format allows more
 * than one user with the same affiliation and contact information,
 * but a second NXuser group should be used if they have different
 * affiliations, etc.
 * 
 * @version 1.0
 */
public class NXuserImpl extends NXobjectImpl implements NXuser {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_NAME = "name";
	public static final String NX_ROLE = "role";
	public static final String NX_AFFILIATION = "affiliation";
	public static final String NX_ADDRESS = "address";
	public static final String NX_TELEPHONE_NUMBER = "telephone_number";
	public static final String NX_FAX_NUMBER = "fax_number";
	public static final String NX_EMAIL = "email";
	public static final String NX_FACILITY_USER_ID = "facility_user_id";

	protected NXuserImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXuser.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_USER;
	}

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	@Override
	public IDataset getRole() {
		return getDataset(NX_ROLE);
	}

	public void setRole(IDataset role) {
		setDataset(NX_ROLE, role);
	}

	@Override
	public IDataset getAffiliation() {
		return getDataset(NX_AFFILIATION);
	}

	public void setAffiliation(IDataset affiliation) {
		setDataset(NX_AFFILIATION, affiliation);
	}

	@Override
	public IDataset getAddress() {
		return getDataset(NX_ADDRESS);
	}

	public void setAddress(IDataset address) {
		setDataset(NX_ADDRESS, address);
	}

	@Override
	public IDataset getTelephone_number() {
		return getDataset(NX_TELEPHONE_NUMBER);
	}

	public void setTelephone_number(IDataset telephone_number) {
		setDataset(NX_TELEPHONE_NUMBER, telephone_number);
	}

	@Override
	public IDataset getFax_number() {
		return getDataset(NX_FAX_NUMBER);
	}

	public void setFax_number(IDataset fax_number) {
		setDataset(NX_FAX_NUMBER, fax_number);
	}

	@Override
	public IDataset getEmail() {
		return getDataset(NX_EMAIL);
	}

	public void setEmail(IDataset email) {
		setDataset(NX_EMAIL, email);
	}

	@Override
	public IDataset getFacility_user_id() {
		return getDataset(NX_FACILITY_USER_ID);
	}

	public void setFacility_user_id(IDataset facility_user_id) {
		setDataset(NX_FACILITY_USER_ID, facility_user_id);
	}

}
