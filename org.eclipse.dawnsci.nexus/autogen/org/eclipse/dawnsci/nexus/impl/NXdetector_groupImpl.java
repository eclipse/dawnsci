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
 * Logical grouping of detector elements.
 * This class is used to allow a logical
 * grouping of detector elements (e.g. which tube, bank or group of banks) to be
 * recorded in the file. As well as allowing you to e.g just select the "left" or
 * "east" detectors, it may also be useful for determining which elements belong to the
 * same PSD tube and hence have e.g. the same dead time.
 * For example, if we had "bank1" composed
 * of "tube1", "tube2" and "tube3" then group_names would be the string "bank1,
 * bank1/tube1, bank1/tube2,bank1/tube3" group_index would be {1,2,3,4} group_parent
 * would be {-1,1,1,1}
 * The mapping array is interpreted as
 * group 1 is a top level group containing groups 2, 3 and 4
 * A ``group_index`` array in
 * ``NXdetector`` gives the base group for a detector element.
 * 
 * @version 1.0
 */
public class NXdetector_groupImpl extends NXobjectImpl implements NXdetector_group {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXdetector_groupImpl() {
		super();
	}

	public NXdetector_groupImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXdetector_group.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_DETECTOR_GROUP;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getGroup_names() {
		return getDataset(NX_GROUP_NAMES);
	}

	@Override
	public String getGroup_namesScalar() {
		return getString(NX_GROUP_NAMES);
	}

	@Override
	public DataNode setGroup_names(IDataset group_names) {
		return setDataset(NX_GROUP_NAMES, group_names);
	}

	@Override
	public DataNode setGroup_namesScalar(String group_names) {
		return setString(NX_GROUP_NAMES, group_names);
	}

	@Override
	public IDataset getGroup_index() {
		return getDataset(NX_GROUP_INDEX);
	}

	@Override
	public long getGroup_indexScalar() {
		return getLong(NX_GROUP_INDEX);
	}

	@Override
	public DataNode setGroup_index(IDataset group_index) {
		return setDataset(NX_GROUP_INDEX, group_index);
	}

	@Override
	public DataNode setGroup_indexScalar(long group_index) {
		return setField(NX_GROUP_INDEX, group_index);
	}

	@Override
	public IDataset getGroup_parent() {
		return getDataset(NX_GROUP_PARENT);
	}

	@Override
	public long getGroup_parentScalar() {
		return getLong(NX_GROUP_PARENT);
	}

	@Override
	public DataNode setGroup_parent(IDataset group_parent) {
		return setDataset(NX_GROUP_PARENT, group_parent);
	}

	@Override
	public DataNode setGroup_parentScalar(long group_parent) {
		return setField(NX_GROUP_PARENT, group_parent);
	}

	@Override
	public IDataset getGroup_type() {
		return getDataset(NX_GROUP_TYPE);
	}

	@Override
	public long getGroup_typeScalar() {
		return getLong(NX_GROUP_TYPE);
	}

	@Override
	public DataNode setGroup_type(IDataset group_type) {
		return setDataset(NX_GROUP_TYPE, group_type);
	}

	@Override
	public DataNode setGroup_typeScalar(long group_type) {
		return setField(NX_GROUP_TYPE, group_type);
	}

}
