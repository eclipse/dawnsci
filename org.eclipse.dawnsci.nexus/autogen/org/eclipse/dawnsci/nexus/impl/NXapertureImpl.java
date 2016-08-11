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
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * A beamline aperture.
 * 
 * @version 1.0
 */
public class NXapertureImpl extends NXobjectImpl implements NXaperture {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_NOTE);

	public NXapertureImpl() {
		super();
	}

	public NXapertureImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXaperture.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_APERTURE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	@Override
	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}
	
	@Override
	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

	@Override
	public IDataset getMaterial() {
		return getDataset(NX_MATERIAL);
	}

	@Override
	public String getMaterialScalar() {
		return getString(NX_MATERIAL);
	}

	@Override
	public DataNode setMaterial(IDataset material) {
		return setDataset(NX_MATERIAL, material);
	}

	@Override
	public DataNode setMaterialScalar(String material) {
		return setString(NX_MATERIAL, material);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	@Override
	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public NXnote getNote() {
		return getChild("note", NXnote.class);
	}

	@Override
	public void setNote(NXnote note) {
		putChild("note", note);
	}

	@Override
	public NXnote getNote(String name) {
		return getChild(name, NXnote.class);
	}

	@Override
	public void setNote(String name, NXnote note) {
		putChild(name, note);
	}

	@Override
	public Map<String, NXnote> getAllNote() {
		return getChildren(NXnote.class);
	}
	
	@Override
	public void setAllNote(Map<String, NXnote> note) {
		setChildren(note);
	}

}
