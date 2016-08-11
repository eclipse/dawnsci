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
 * legacy only - not intended for new use - may be removed in the future
 * Note: This base class may be removed in future releases of NXDL.
 * If you have a use for this base class, please provide a description
 * of your intended use to the NIAC (nexus-committee@nexusformat.org).
 * .. TODO: NXcharacterization needs proper documentation
 * 
 * @version 1.0
 */
public class NXcharacterizationImpl extends NXobjectImpl implements NXcharacterization {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXcharacterizationImpl() {
		super();
	}

	public NXcharacterizationImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcharacterization.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_CHARACTERIZATION;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public String getAttributeSource() {
		return getAttrString(null, NX_ATTRIBUTE_SOURCE);
	}

	@Override
	public void setAttributeSource(String source) {
		setAttribute(null, NX_ATTRIBUTE_SOURCE, source);
	}

	@Override
	public String getAttributeLocation() {
		return getAttrString(null, NX_ATTRIBUTE_LOCATION);
	}

	@Override
	public void setAttributeLocation(String location) {
		setAttribute(null, NX_ATTRIBUTE_LOCATION, location);
	}

	@Override
	public String getAttributeMime_type() {
		return getAttrString(null, NX_ATTRIBUTE_MIME_TYPE);
	}

	@Override
	public void setAttributeMime_type(String mime_type) {
		setAttribute(null, NX_ATTRIBUTE_MIME_TYPE, mime_type);
	}

	@Override
	public IDataset getDefinition() {
		return getDataset(NX_DEFINITION);
	}

	@Override
	public String getDefinitionScalar() {
		return getString(NX_DEFINITION);
	}

	@Override
	public DataNode setDefinition(IDataset definition) {
		return setDataset(NX_DEFINITION, definition);
	}

	@Override
	public DataNode setDefinitionScalar(String definition) {
		return setString(NX_DEFINITION, definition);
	}

	@Override
	public String getDefinitionAttributeVersion() {
		return getAttrString(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_VERSION);
	}

	@Override
	public void setDefinitionAttributeVersion(String version) {
		setAttribute(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_VERSION, version);
	}

	@Override
	public String getDefinitionAttributeURL() {
		return getAttrString(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_URL);
	}

	@Override
	public void setDefinitionAttributeURL(String URL) {
		setAttribute(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_URL, URL);
	}

}
