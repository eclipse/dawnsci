/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.nexus.*;

/**
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

	public static final String NX_GROUP_NAMES = "group_names";
	public static final String NX_GROUP_INDEX = "group_index";
	public static final String NX_GROUP_PARENT = "group_parent";
	public static final String NX_GROUP_TYPE = "group_type";

	protected NXdetector_groupImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXdetector_group.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_DETECTOR_GROUP;
	}

	@Override
	public IDataset getGroup_names() {
		return getDataset(NX_GROUP_NAMES);
	}

	public void setGroup_names(IDataset group_names) {
		setDataset(NX_GROUP_NAMES, group_names);
	}

	@Override
	public IDataset getGroup_index() {
		return getDataset(NX_GROUP_INDEX);
	}

	public void setGroup_index(IDataset group_index) {
		setDataset(NX_GROUP_INDEX, group_index);
	}

	@Override
	public IDataset getGroup_parent() {
		return getDataset(NX_GROUP_PARENT);
	}

	public void setGroup_parent(IDataset group_parent) {
		setDataset(NX_GROUP_PARENT, group_parent);
	}

	@Override
	public IDataset getGroup_type() {
		return getDataset(NX_GROUP_TYPE);
	}

	public void setGroup_type(IDataset group_type) {
		setDataset(NX_GROUP_TYPE, group_type);
	}

}
