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

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;


/**
 * Definition of the root NeXus group.
 * 
 * @version 1.0
 */
public interface NXroot extends NXobject {

	public static final String NX_ATTRIBUTE_NX_CLASS = "NX_class";
	public static final String NX_ATTRIBUTE_FILE_TIME = "file_time";
	public static final String NX_ATTRIBUTE_FILE_NAME = "file_name";
	public static final String NX_ATTRIBUTE_FILE_UPDATE_TIME = "file_update_time";
	public static final String NX_ATTRIBUTE_NEXUS_VERSION = "NeXus_version";
	public static final String NX_ATTRIBUTE_HDF_VERSION = "HDF_version";
	public static final String NX_ATTRIBUTE_HDF5_VERSION = "HDF5_Version";
	public static final String NX_ATTRIBUTE_XML_VERSION = "XML_version";
	public static final String NX_ATTRIBUTE_H5PY_VERSION = "h5py_version";
	public static final String NX_ATTRIBUTE_CREATOR = "creator";
	public static final String NX_ATTRIBUTE_DEFAULT = "default";
	/**
	 * The root of any NeXus data file is an ``NXroot`` class
	 * (no other choice is allowed for a valid NeXus data file).
	 * This attribute cements that definition.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>NXroot</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getAttributeNX_class();
	
	/**
	 * The root of any NeXus data file is an ``NXroot`` class
	 * (no other choice is allowed for a valid NeXus data file).
	 * This attribute cements that definition.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>NXroot</b> </li></ul></p>
	 * </p>
	 * 
	 * @param NX_class the NX_class
	 */
	public void setAttributeNX_class(String NX_class);

	/**
	 * Date and time file was originally created
	 * 
	 * @return  the value.
	 */
	public String getAttributeFile_time();
	
	/**
	 * Date and time file was originally created
	 * 
	 * @param file_time the file_time
	 */
	public void setAttributeFile_time(String file_time);

	/**
	 * File name of original NeXus file
	 * 
	 * @return  the value.
	 */
	public String getAttributeFile_name();
	
	/**
	 * File name of original NeXus file
	 * 
	 * @param file_name the file_name
	 */
	public void setAttributeFile_name(String file_name);

	/**
	 * Date and time of last file change at close
	 * 
	 * @return  the value.
	 */
	public String getAttributeFile_update_time();
	
	/**
	 * Date and time of last file change at close
	 * 
	 * @param file_update_time the file_update_time
	 */
	public void setAttributeFile_update_time(String file_update_time);

	/**
	 * Version of NeXus API used in writing the file.
	 * Only used when the NAPI has written the file.
	 * Note that this is different from the version of the
	 * base class or application definition version number.
	 * 
	 * @return  the value.
	 */
	public String getAttributeNeXus_version();
	
	/**
	 * Version of NeXus API used in writing the file.
	 * Only used when the NAPI has written the file.
	 * Note that this is different from the version of the
	 * base class or application definition version number.
	 * 
	 * @param NeXus_version the NeXus_version
	 */
	public void setAttributeNeXus_version(String NeXus_version);

	/**
	 * Version of HDF (version 4) library used in writing the file
	 * 
	 * @return  the value.
	 */
	public String getAttributeHDF_version();
	
	/**
	 * Version of HDF (version 4) library used in writing the file
	 * 
	 * @param HDF_version the HDF_version
	 */
	public void setAttributeHDF_version(String HDF_version);

	/**
	 * Version of HDF5 library used in writing the file.
	 * Note this attribute is spelled with uppercase "V",
	 * different than other version attributes.
	 * 
	 * @return  the value.
	 */
	public String getAttributeHDF5_Version();
	
	/**
	 * Version of HDF5 library used in writing the file.
	 * Note this attribute is spelled with uppercase "V",
	 * different than other version attributes.
	 * 
	 * @param HDF5_Version the HDF5_Version
	 */
	public void setAttributeHDF5_Version(String HDF5_Version);

	/**
	 * Version of XML support library used in writing the XML file
	 * 
	 * @return  the value.
	 */
	public String getAttributeXML_version();
	
	/**
	 * Version of XML support library used in writing the XML file
	 * 
	 * @param XML_version the XML_version
	 */
	public void setAttributeXML_version(String XML_version);

	/**
	 * Version of h5py Python package used in writing the file
	 * 
	 * @return  the value.
	 */
	public String getAttributeH5py_version();
	
	/**
	 * Version of h5py Python package used in writing the file
	 * 
	 * @param h5py_version the h5py_version
	 */
	public void setAttributeH5py_version(String h5py_version);

	/**
	 * facility or program where file originated
	 * 
	 * @return  the value.
	 */
	public String getAttributeCreator();
	
	/**
	 * facility or program where file originated
	 * 
	 * @param creator the creator
	 */
	public void setAttributeCreator(String creator);

	/**
	 * entries
	 * 
	 * @return  the value.
	 */
	public NXentry getEntry();
	
	/**
	 * entries
	 * 
	 * @param entry the entry
	 */
	public void setEntry(NXentry entry);
  
	/**
	 * Get a NXentry node by name:
	 * <ul>
	 * <li>
	 * entries</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXentry for that node.
	 */
	public NXentry getEntry(String name);
	
	/**
	 * Set a NXentry node by name:
	 * <ul>
	 * <li>
	 * entries</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param entry the value to set
	 */
	public void setEntry(String name, NXentry entry);
	
	/**
	 * Get all NXentry nodes:
	 * <ul>
	 * <li>
	 * entries</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXentry for that node.
	 */
	public Map<String, NXentry> getAllEntry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * entries</li>
	 * </ul>
	 * 
	 * @param entry the child nodes to add 
	 */
	
	public void setAllEntry(Map<String, NXentry> entry);
	

	/**
	 * .. index:: plotting
	 * Declares which :ref:`NXentry` group contains
	 * the data to be shown by default.
	 * It is needed to resolve ambiguity when
	 * more than one :ref:`NXentry` group exists.
	 * The value is the name of the default :ref:`NXentry` group.
	 * It is recommended (as of NIAC2014) to use this attribute
	 * to help define the path to the default dataset to be plotted.
	 * See http://wiki.nexusformat.org/2014_How_to_find_default_data
	 * for a summary of the discussion.
	 * 
	 * @return  the value.
	 */
	public String getAttributeDefault();
	
	/**
	 * .. index:: plotting
	 * Declares which :ref:`NXentry` group contains
	 * the data to be shown by default.
	 * It is needed to resolve ambiguity when
	 * more than one :ref:`NXentry` group exists.
	 * The value is the name of the default :ref:`NXentry` group.
	 * It is recommended (as of NIAC2014) to use this attribute
	 * to help define the path to the default dataset to be plotted.
	 * See http://wiki.nexusformat.org/2014_How_to_find_default_data
	 * for a summary of the discussion.
	 * 
	 * @param default_ the default
	 */
	public void setAttributeDefault(String default_);

}
