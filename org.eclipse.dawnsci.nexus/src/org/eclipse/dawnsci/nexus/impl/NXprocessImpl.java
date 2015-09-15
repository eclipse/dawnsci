/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
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

	protected NXprocessImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXprocess.class;
	}

	@Override
	public IDataset getProgram() {
		return getDataset(NX_PROGRAM);
	}

	public void setProgram(IDataset program) {
		setDataset(NX_PROGRAM, program);
	}

	@Override
	public IDataset getSequence_index() {
		return getDataset(NX_SEQUENCE_INDEX);
	}

	public void setSequence_index(IDataset sequence_index) {
		setDataset(NX_SEQUENCE_INDEX, sequence_index);
	}

	@Override
	public IDataset getVersion() {
		return getDataset(NX_VERSION);
	}

	public void setVersion(IDataset version) {
		setDataset(NX_VERSION, version);
	}

	@Override
	public IDataset getDate() {
		return getDataset(NX_DATE);
	}

	public void setDate(IDataset date) {
		setDataset(NX_DATE, date);
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
