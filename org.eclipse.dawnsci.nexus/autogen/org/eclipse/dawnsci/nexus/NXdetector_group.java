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

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
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
public interface NXdetector_group extends NXobject {

	public static final String NX_GROUP_NAMES = "group_names";
	public static final String NX_GROUP_INDEX = "group_index";
	public static final String NX_GROUP_PARENT = "group_parent";
	public static final String NX_GROUP_TYPE = "group_type";
	/**
	 * Comma separated list of name
	 * 
	 * @return  the value.
	 */
	public IDataset getGroup_names();
	
	/**
	 * Comma separated list of name
	 * 
	 * @param group_names the group_names
	 */
	public DataNode setGroup_names(IDataset group_names);

	/**
	 * Comma separated list of name
	 * 
	 * @return  the value.
	 */
	public String getGroup_namesScalar();

	/**
	 * Comma separated list of name
	 * 
	 * @param group_names the group_names
	 */
	public DataNode setGroup_namesScalar(String group_names);

	/**
	 * Unique ID for group. A group_index array
	 * in ``NXdetector`` gives the base group for a detector element.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGroup_index();
	
	/**
	 * Unique ID for group. A group_index array
	 * in ``NXdetector`` gives the base group for a detector element.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param group_index the group_index
	 */
	public DataNode setGroup_index(IDataset group_index);

	/**
	 * Unique ID for group. A group_index array
	 * in ``NXdetector`` gives the base group for a detector element.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getGroup_indexScalar();

	/**
	 * Unique ID for group. A group_index array
	 * in ``NXdetector`` gives the base group for a detector element.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param group_index the group_index
	 */
	public DataNode setGroup_indexScalar(long group_index);

	/**
	 * Index of group parent in the hierarchy: -1 means no parent (i.e. a top level) group
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGroup_parent();
	
	/**
	 * Index of group parent in the hierarchy: -1 means no parent (i.e. a top level) group
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param group_parent the group_parent
	 */
	public DataNode setGroup_parent(IDataset group_parent);

	/**
	 * Index of group parent in the hierarchy: -1 means no parent (i.e. a top level) group
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getGroup_parentScalar();

	/**
	 * Index of group parent in the hierarchy: -1 means no parent (i.e. a top level) group
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param group_parent the group_parent
	 */
	public DataNode setGroup_parentScalar(long group_parent);

	/**
	 * Code number for group type, e.g. bank=1, tube=2 etc.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGroup_type();
	
	/**
	 * Code number for group type, e.g. bank=1, tube=2 etc.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param group_type the group_type
	 */
	public DataNode setGroup_type(IDataset group_type);

	/**
	 * Code number for group type, e.g. bank=1, tube=2 etc.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getGroup_typeScalar();

	/**
	 * Code number for group type, e.g. bank=1, tube=2 etc.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param group_type the group_type
	 */
	public DataNode setGroup_typeScalar(long group_type);

}
