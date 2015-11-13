/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Document an event of data processing, reconstruction, or analysis for this data.
 * 
 * @version 1.0
 */
public class NXprocessImpl extends NXobjectImpl implements NXprocess {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_PROGRAM = "program";
	public static final String NX_SEQUENCE_INDEX = "sequence_index";
	public static final String NX_VERSION = "version";
	public static final String NX_DATE = "date";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_NOTE);

	protected NXprocessImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXprocessImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXprocess.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_PROCESS;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	public IDataset getSequence_index() {
		return getDataset(NX_SEQUENCE_INDEX);
	}

	@Override
	public long getSequence_indexScalar() {
		return getLong(NX_SEQUENCE_INDEX);
	}

	public void setSequence_index(IDataset sequence_index) {
		setDataset(NX_SEQUENCE_INDEX, sequence_index);
	}

	public void setSequence_indexScalar(long sequence_index) {
		setField(NX_SEQUENCE_INDEX, sequence_index);
	}

	@Override
	public IDataset getVersion() {
		return getDataset(NX_VERSION);
	}

	@Override
	public String getVersionScalar() {
		return getString(NX_VERSION);
	}

	public void setVersion(IDataset version) {
		setDataset(NX_VERSION, version);
	}

	public void setVersionScalar(String version) {
		setString(NX_VERSION, version);
	}

	@Override
	public IDataset getDate() {
		return getDataset(NX_DATE);
	}

	@Override
	public Date getDateScalar() {
		return getDate(NX_DATE);
	}

	public void setDate(IDataset date) {
		setDataset(NX_DATE, date);
	}

	public void setDateScalar(Date date) {
		setDate(NX_DATE, date);
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
