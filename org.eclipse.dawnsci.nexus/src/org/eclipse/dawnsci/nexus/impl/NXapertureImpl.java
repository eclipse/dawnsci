/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-17T13:07:37.011Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a beamline aperture.
 * 
 * @version 1.0
 */
public class NXapertureImpl extends NXobjectImpl implements NXaperture {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_MATERIAL = "material";
	public static final String NX_DESCRIPTION = "description";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_NOTE);

	protected NXapertureImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXapertureImpl(final long oid) {
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

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}

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

	public DataNode setMaterial(IDataset material) {
		return setDataset(NX_MATERIAL, material);
	}

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

	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public NXnote getNote() {
		return getChild("note", NXnote.class);
	}

	public void setNote(NXnote note) {
		putChild("note", note);
	}

	@Override
	public NXnote getNote(String name) {
		return getChild(name, NXnote.class);
	}

	public void setNote(String name, NXnote note) {
		putChild(name, note);
	}

	@Override
	public Map<String, NXnote> getAllNote() {
		return getChildren(NXnote.class);
	}

	public void setAllNote(Map<String, NXnote> note) {
		setChildren(note);
	}

}
