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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This class describes an external condition applied to the sample
 * 
 * @version 1.0
 */
public class NXenvironmentImpl extends NXobjectImpl implements NXenvironment {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_NAME = "name";
	public static final String NX_SHORT_NAME = "short_name";
	public static final String NX_TYPE = "type";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_PROGRAM = "program";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_NOTE,
		NexusBaseClass.NX_SENSOR);

	protected NXenvironmentImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXenvironmentImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXenvironment.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_ENVIRONMENT;
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

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	public void setNameScalar(String name) {
		setString(NX_NAME, name);
	}

	@Override
	public IDataset getShort_name() {
		return getDataset(NX_SHORT_NAME);
	}

	@Override
	public String getShort_nameScalar() {
		return getString(NX_SHORT_NAME);
	}

	public void setShort_name(IDataset short_name) {
		setDataset(NX_SHORT_NAME, short_name);
	}

	public void setShort_nameScalar(String short_name) {
		setString(NX_SHORT_NAME, short_name);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setTypeScalar(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setDescriptionScalar(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getProgram() {
		return getDataset(NX_PROGRAM);
	}

	@Override
	public String getProgramScalar() {
		return getString(NX_PROGRAM);
	}

	public void setProgram(IDataset program) {
		setDataset(NX_PROGRAM, program);
	}

	public void setProgramScalar(String program) {
		setString(NX_PROGRAM, program);
	}

	@Override
	public NXgeometry getPosition() {
		return getChild("position", NXgeometry.class);
	}

	public void setPosition(NXgeometry position) {
		putChild("position", position);
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

	@Override
	public NXsensor getSensor() {
		return getChild("sensor", NXsensor.class);
	}

	public void setSensor(NXsensor sensor) {
		putChild("sensor", sensor);
	}

	@Override
	public NXsensor getSensor(String name) {
		return getChild(name, NXsensor.class);
	}

	public void setSensor(String name, NXsensor sensor) {
		putChild(name, sensor);
	}

	@Override
	public Map<String, NXsensor> getAllSensor() {
		return getChildren(NXsensor.class);
	}

	public void setAllSensor(Map<String, NXsensor> sensor) {
		setChildren(sensor);
	}

}