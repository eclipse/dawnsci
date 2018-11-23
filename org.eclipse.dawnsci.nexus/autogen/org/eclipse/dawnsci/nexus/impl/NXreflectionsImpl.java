/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This is a definition for reflection data from diffraction experiments
 * 
 * @version 1.0
 */
public class NXreflectionsImpl extends NXobjectImpl implements NXreflections {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_ENTRY);

	public NXreflectionsImpl() {
		super();
	}

	public NXreflectionsImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXreflections.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_REFLECTIONS;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public NXentry getEntry() {
		return getChild("entry", NXentry.class);
	}

	@Override
	public void setEntry(NXentry entry) {
		putChild("entry", entry);
	}

	@Override
	public NXentry getEntry(String name) {
		return getChild(name, NXentry.class);
	}

	@Override
	public void setEntry(String name, NXentry entry) {
		putChild(name, entry);
	}

	@Override
	public Map<String, NXentry> getAllEntry() {
		return getChildren(NXentry.class);
	}
	
	@Override
	public void setAllEntry(Map<String, NXentry> entry) {
		setChildren(entry);
	}

}
