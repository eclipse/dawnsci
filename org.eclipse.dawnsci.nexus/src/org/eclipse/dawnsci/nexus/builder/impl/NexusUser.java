package org.eclipse.dawnsci.nexus.builder.impl;

import org.eclipse.dawnsci.nexus.NXuser;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;

/**
 * Represents a nexus user. An object of this type can be added to
 * a {@link NexusEntryBuilder} using the {@link NexusEntryBuilder#add(org.eclipse.dawnsci.nexus.builder.NexusObjectProvider)
 * method to add a user to the nexus entry.
 */
public class NexusUser extends AbstractNexusObjectProvider<NXuser> {
	
	private static final String FIELD_NAME_USERNAME = "username";

	private String name = null;
	
	private String username = null;
	
	private String role = null;
	
	private String affiliation = null;
	
	private String address = null;
	
	private String telephoneNumber = null;
	
	private String faxNumber = null;
	
	private String email = null;
	
	private String facilityUserId = null;
	
	/**
	 * Creates a new {@link NexusUser} with the given name in the parent group.
	 * @param groupName name within parent group
	 */
	public NexusUser(String groupName) {
		super(groupName, NexusBaseClass.NX_USER);
	}
	
	/**
	 * Creates a new {@link NexusUser} with the given name in the parent group,
	 * and with a 'name' field with the given name.
	 * @param groupName name within parent group
	 * @param name name name of user
	 */
	public NexusUser(String groupName, String name) {
		super(groupName, NexusBaseClass.NX_USER);
		setName(name);
	}
	
	/* (non-Javadoc)
	 * @see org.dawnsci.nexus.builder.impl.AbstractNexusObjectProvider#doCreateNexusObject(org.eclipse.dawnsci.nexus.impl.NexusNodeFactory)
	 */
	@Override
	protected NXuser doCreateNexusObject(NexusNodeFactory nodeFactory) {
		NXuser user = nodeFactory.createNXuser();
		
		if (name != null) {
			user.setNameScalar(name);
		}
		if (username != null) {
			user.setField(FIELD_NAME_USERNAME, username);
		}
		if (role != null) {
			user.setRoleScalar(role);
		}
		if (affiliation != null) {
			user.setAffiliationScalar(affiliation);
		}
		if (address != null) {
			user.setAddressScalar(address);
		}
		if (telephoneNumber != null) {
			user.setTelephone_numberScalar(telephoneNumber);
		}
		if (faxNumber != null) {
			user.setFax_numberScalar(faxNumber);
		}
		if (email != null) {
			user.setEmailScalar(email);
		}
		if (facilityUserId != null) {
			user.setFacility_user_idScalar(facilityUserId);
		}
		
		return user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFacilityUserId(String facilityUserId) {
		this.facilityUserId = facilityUserId;
	}
	
}
