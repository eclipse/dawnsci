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

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;

/**
 * Any additional freeform information not covered by the other base classes.
 * This class can be used to store additional information in a
 * NeXus file e.g. pictures, movies, audio, additional text logs
 * 
 * @version 1.0
 */
public class NXnoteImpl extends NXobjectImpl implements NXnote {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXnoteImpl() {
		super();
	}

	public NXnoteImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXnote.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_NOTE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getAuthor() {
		return getDataset(NX_AUTHOR);
	}

	@Override
	public String getAuthorScalar() {
		return getString(NX_AUTHOR);
	}

	@Override
	public DataNode setAuthor(IDataset author) {
		return setDataset(NX_AUTHOR, author);
	}

	@Override
	public DataNode setAuthorScalar(String author) {
		return setString(NX_AUTHOR, author);
	}

	@Override
	public IDataset getDate() {
		return getDataset(NX_DATE);
	}

	@Override
	public Date getDateScalar() {
		return getDate(NX_DATE);
	}

	@Override
	public DataNode setDate(IDataset date) {
		return setDataset(NX_DATE, date);
	}

	@Override
	public DataNode setDateScalar(Date date) {
		return setDate(NX_DATE, date);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	@Override
	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	@Override
	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
	}

	@Override
	public IDataset getFile_name() {
		return getDataset(NX_FILE_NAME);
	}

	@Override
	public String getFile_nameScalar() {
		return getString(NX_FILE_NAME);
	}

	@Override
	public DataNode setFile_name(IDataset file_name) {
		return setDataset(NX_FILE_NAME, file_name);
	}

	@Override
	public DataNode setFile_nameScalar(String file_name) {
		return setString(NX_FILE_NAME, file_name);
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
	public IDataset getSequence_index() {
		return getDataset(NX_SEQUENCE_INDEX);
	}

	@Override
	public long getSequence_indexScalar() {
		return getLong(NX_SEQUENCE_INDEX);
	}

	@Override
	public DataNode setSequence_index(IDataset sequence_index) {
		return setDataset(NX_SEQUENCE_INDEX, sequence_index);
	}

	@Override
	public DataNode setSequence_indexScalar(long sequence_index) {
		return setField(NX_SEQUENCE_INDEX, sequence_index);
	}

	@Override
	public IDataset getData() {
		return getDataset(NX_DATA);
	}

	@Override
	public Object getDataScalar() {
		return getDataNode(NX_DATA).getDataset();
	}

	@Override
	public DataNode setData(IDataset data) {
		return setDataset(NX_DATA, data);
	}

	@Override
	public DataNode setDataScalar(Object data) {
		return setField(NX_DATA, data);
	}

}
