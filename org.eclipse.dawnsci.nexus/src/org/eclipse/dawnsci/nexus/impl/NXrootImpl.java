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
import java.util.Map;

import org.eclipse.dawnsci.nexus.*;

/**
 * Definition of the root NeXus group.
 * 
 * @version 1.0
 */
public class NXrootImpl extends NXobjectImpl implements NXroot {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_ATTRIBUTE_NX_CLASS = "NX_class";
	public static final String NX_ATTRIBUTE_FILE_TIME = "file_time";
	public static final String NX_ATTRIBUTE_FILE_NAME = "file_name";
	public static final String NX_ATTRIBUTE_FILE_UPDATE_TIME = "file_update_time";
	public static final String NX_ATTRIBUTE_NEXUS_VERSION = "NeXus_version";
	public static final String NX_ATTRIBUTE_HDF_VERSION = "HDF_version";
	public static final String NX_ATTRIBUTE_HDF5_VERSION = "HDF5_Version";
	public static final String NX_ATTRIBUTE_XML_VERSION = "XML_version";
	public static final String NX_ATTRIBUTE_CREATOR = "creator";
	public static final String NX_ATTRIBUTE_DEFAULT = "default";

	protected NXrootImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXrootImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXroot.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_ROOT;
	}

	@Override
	public String getAttributeNX_class() {
		return getAttrString(null, NX_ATTRIBUTE_NX_CLASS);
	}

	public void setAttributeNX_class(String NX_class) {
		setAttribute(null, NX_ATTRIBUTE_NX_CLASS, NX_class);
	}

	@Override
	public String getAttributeFile_time() {
		return getAttrString(null, NX_ATTRIBUTE_FILE_TIME);
	}

	public void setAttributeFile_time(String file_time) {
		setAttribute(null, NX_ATTRIBUTE_FILE_TIME, file_time);
	}

	@Override
	public String getAttributeFile_name() {
		return getAttrString(null, NX_ATTRIBUTE_FILE_NAME);
	}

	public void setAttributeFile_name(String file_name) {
		setAttribute(null, NX_ATTRIBUTE_FILE_NAME, file_name);
	}

	@Override
	public String getAttributeFile_update_time() {
		return getAttrString(null, NX_ATTRIBUTE_FILE_UPDATE_TIME);
	}

	public void setAttributeFile_update_time(String file_update_time) {
		setAttribute(null, NX_ATTRIBUTE_FILE_UPDATE_TIME, file_update_time);
	}

	@Override
	public String getAttributeNeXus_version() {
		return getAttrString(null, NX_ATTRIBUTE_NEXUS_VERSION);
	}

	public void setAttributeNeXus_version(String NeXus_version) {
		setAttribute(null, NX_ATTRIBUTE_NEXUS_VERSION, NeXus_version);
	}

	@Override
	public String getAttributeHDF_version() {
		return getAttrString(null, NX_ATTRIBUTE_HDF_VERSION);
	}

	public void setAttributeHDF_version(String HDF_version) {
		setAttribute(null, NX_ATTRIBUTE_HDF_VERSION, HDF_version);
	}

	@Override
	public String getAttributeHDF5_Version() {
		return getAttrString(null, NX_ATTRIBUTE_HDF5_VERSION);
	}

	public void setAttributeHDF5_Version(String HDF5_Version) {
		setAttribute(null, NX_ATTRIBUTE_HDF5_VERSION, HDF5_Version);
	}

	@Override
	public String getAttributeXML_version() {
		return getAttrString(null, NX_ATTRIBUTE_XML_VERSION);
	}

	public void setAttributeXML_version(String XML_version) {
		setAttribute(null, NX_ATTRIBUTE_XML_VERSION, XML_version);
	}

	@Override
	public String getAttributeCreator() {
		return getAttrString(null, NX_ATTRIBUTE_CREATOR);
	}

	public void setAttributeCreator(String creator) {
		setAttribute(null, NX_ATTRIBUTE_CREATOR, creator);
	}

	@Override
	public NXentry getEntry() {
		return getChild("entry", NXentry.class);
	}

	public void setEntry(NXentry entry) {
		putChild("entry", entry);
	}

	@Override
	public NXentry getEntry(String name) {
		return getChild(name, NXentry.class);
	}

	public void setEntry(String name, NXentry entry) {
		putChild(name, entry);
	}

	@Override
	public Map<String, NXentry> getAllEntry() {
		return getChildren(NXentry.class);
	}

	public void setAllEntry(Map<String, NXentry> entry) {
		setChildren(entry);
	}

	@Override
	public String getAttributeDefault() {
		return getAttrString(null, NX_ATTRIBUTE_DEFAULT);
	}

	public void setAttributeDefault(String default_) {
		setAttribute(null, NX_ATTRIBUTE_DEFAULT, default_);
	}

}
