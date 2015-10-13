/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-12T11:55:04.232+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;

import org.eclipse.dawnsci.nexus.*;

/**
 * This class can be used to store additional information in a
 * NeXus file e.g. pictures, movies, audio, additional text logs
 * 
 * @version 1.0
 */
public class NXnoteImpl extends NXobjectImpl implements NXnote {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_AUTHOR = "author";
	public static final String NX_DATE = "date";
	public static final String NX_TYPE = "type";
	public static final String NX_FILE_NAME = "file_name";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_SEQUENCE_INDEX = "sequence_index";
	public static final String NX_DATA = "data";

	protected NXnoteImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXnoteImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXnote.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_NOTE;
	}

	@Override
	public IDataset getAuthor() {
		return getDataset(NX_AUTHOR);
	}

	@Override
	public String getScalarAuthor() {
		return getString(NX_AUTHOR);
	}

	public void setAuthor(IDataset author) {
		setDataset(NX_AUTHOR, author);
	}

	public void setScalarAuthor(String author) {
		setString(NX_AUTHOR, author);
	}

	@Override
	public IDataset getDate() {
		return getDataset(NX_DATE);
	}

	@Override
	public Date getScalarDate() {
		return getDate(NX_DATE);
	}

	public void setDate(IDataset date) {
		setDataset(NX_DATE, date);
	}

	public void setScalarDate(Date date) {
		setDate(NX_DATE, date);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getScalarType() {
		return getString(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setScalarType(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getFile_name() {
		return getDataset(NX_FILE_NAME);
	}

	@Override
	public String getScalarFile_name() {
		return getString(NX_FILE_NAME);
	}

	public void setFile_name(IDataset file_name) {
		setDataset(NX_FILE_NAME, file_name);
	}

	public void setScalarFile_name(String file_name) {
		setString(NX_FILE_NAME, file_name);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getScalarDescription() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setScalarDescription(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getSequence_index() {
		return getDataset(NX_SEQUENCE_INDEX);
	}

	@Override
	public long getScalarSequence_index() {
		return getLong(NX_SEQUENCE_INDEX);
	}

	public void setSequence_index(IDataset sequence_index) {
		setDataset(NX_SEQUENCE_INDEX, sequence_index);
	}

	public void setScalarSequence_index(long sequence_index) {
		setField(NX_SEQUENCE_INDEX, sequence_index);
	}

	@Override
	public IDataset getData() {
		return getDataset(NX_DATA);
	}

	@Override
	public Object getScalarData() {
		return getDataNode(NX_DATA).getDataset();
	}

	public void setData(IDataset data) {
		setDataset(NX_DATA, data);
	}

	public void setScalarData(Object data) {
		getDataNode(NX_DATA).setDataset(DatasetFactory.createFromObject(data));
	}

}
