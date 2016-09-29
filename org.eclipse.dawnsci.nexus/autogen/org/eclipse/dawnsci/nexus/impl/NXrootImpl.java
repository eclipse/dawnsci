/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-09-28T15:24:07.968+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;
import java.util.Map;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;


import org.eclipse.dawnsci.nexus.*;

/**
 * Definition of the root NeXus group.
 * 
 * @version 1.0
 */
public class NXrootImpl extends NXobjectImpl implements NXroot {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_ENTRY);

	public NXrootImpl() {
		super();
	}

	public NXrootImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXroot.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_ROOT;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public String getAttributeNX_class() {
		return getAttrString(null, NX_ATTRIBUTE_NX_CLASS);
	}

	@Override
	public void setAttributeNX_class(String NX_class) {
		setAttribute(null, NX_ATTRIBUTE_NX_CLASS, NX_class);
	}

	@Override
	public String getAttributeFile_time() {
		return getAttrString(null, NX_ATTRIBUTE_FILE_TIME);
	}

	@Override
	public void setAttributeFile_time(String file_time) {
		setAttribute(null, NX_ATTRIBUTE_FILE_TIME, file_time);
	}

	@Override
	public String getAttributeFile_name() {
		return getAttrString(null, NX_ATTRIBUTE_FILE_NAME);
	}

	@Override
	public void setAttributeFile_name(String file_name) {
		setAttribute(null, NX_ATTRIBUTE_FILE_NAME, file_name);
	}

	@Override
	public String getAttributeFile_update_time() {
		return getAttrString(null, NX_ATTRIBUTE_FILE_UPDATE_TIME);
	}

	@Override
	public void setAttributeFile_update_time(String file_update_time) {
		setAttribute(null, NX_ATTRIBUTE_FILE_UPDATE_TIME, file_update_time);
	}

	@Override
	public String getAttributeNeXus_version() {
		return getAttrString(null, NX_ATTRIBUTE_NEXUS_VERSION);
	}

	@Override
	public void setAttributeNeXus_version(String NeXus_version) {
		setAttribute(null, NX_ATTRIBUTE_NEXUS_VERSION, NeXus_version);
	}

	@Override
	public String getAttributeHDF_version() {
		return getAttrString(null, NX_ATTRIBUTE_HDF_VERSION);
	}

	@Override
	public void setAttributeHDF_version(String HDF_version) {
		setAttribute(null, NX_ATTRIBUTE_HDF_VERSION, HDF_version);
	}

	@Override
	public String getAttributeHDF5_Version() {
		return getAttrString(null, NX_ATTRIBUTE_HDF5_VERSION);
	}

	@Override
	public void setAttributeHDF5_Version(String HDF5_Version) {
		setAttribute(null, NX_ATTRIBUTE_HDF5_VERSION, HDF5_Version);
	}

	@Override
	public String getAttributeXML_version() {
		return getAttrString(null, NX_ATTRIBUTE_XML_VERSION);
	}

	@Override
	public void setAttributeXML_version(String XML_version) {
		setAttribute(null, NX_ATTRIBUTE_XML_VERSION, XML_version);
	}

	@Override
	public String getAttributeH5py_version() {
		return getAttrString(null, NX_ATTRIBUTE_H5PY_VERSION);
	}

	@Override
	public void setAttributeH5py_version(String h5py_version) {
		setAttribute(null, NX_ATTRIBUTE_H5PY_VERSION, h5py_version);
	}

	@Override
	public String getAttributeCreator() {
		return getAttrString(null, NX_ATTRIBUTE_CREATOR);
	}

	@Override
	public void setAttributeCreator(String creator) {
		setAttribute(null, NX_ATTRIBUTE_CREATOR, creator);
	}

	@Override
	public NXentry getEntry() {
		return getChild("entry", NXentry.class);
	}

	@Override
	public void setEntry(NXentry entry) {
		putChild("entry", entry);
	}

	@Override
	public NXentry getEntry(String name) {
		return getChild(name, NXentry.class);
	}

	@Override
	public void setEntry(String name, NXentry entry) {
		putChild(name, entry);
	}

	@Override
	public Map<String, NXentry> getAllEntry() {
		return getChildren(NXentry.class);
	}
	
	@Override
	public void setAllEntry(Map<String, NXentry> entry) {
		setChildren(entry);
	}

	@Override
	public String getAttributeDefault() {
		return getAttrString(null, NX_ATTRIBUTE_DEFAULT);
	}

	@Override
	public void setAttributeDefault(String default_) {
		setAttribute(null, NX_ATTRIBUTE_DEFAULT, default_);
	}

}
