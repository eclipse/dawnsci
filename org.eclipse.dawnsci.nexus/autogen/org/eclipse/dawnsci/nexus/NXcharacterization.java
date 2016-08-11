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
 * legacy only - not intended for new use - may be removed in the future
 * Note: This base class may be removed in future releases of NXDL.
 * If you have a use for this base class, please provide a description
 * of your intended use to the NIAC (nexus-committee@nexusformat.org).
 * .. TODO: NXcharacterization needs proper documentation
 * 
 * @version 1.0
 */
public interface NXcharacterization extends NXobject {

	public static final String NX_ATTRIBUTE_SOURCE = "source";
	public static final String NX_ATTRIBUTE_LOCATION = "location";
	public static final String NX_ATTRIBUTE_MIME_TYPE = "mime_type";
	public static final String NX_DEFINITION = "definition";
	public static final String NX_DEFINITION_ATTRIBUTE_VERSION = "version";
	public static final String NX_DEFINITION_ATTRIBUTE_URL = "URL";
	/**
	 * If missing, the source file is the current file
	 * 
	 * @return  the value.
	 */
	public String getAttributeSource();
	
	/**
	 * If missing, the source file is the current file
	 * 
	 * @param source the source
	 */
	public void setAttributeSource(String source);

	/**
	 * 
	 * @return  the value.
	 */
	public String getAttributeLocation();
	
	/**
	 * 
	 * @param location the location
	 */
	public void setAttributeLocation(String location);

	/**
	 * If missing, the source file is NAPI readable
	 * 
	 * @return  the value.
	 */
	public String getAttributeMime_type();
	
	/**
	 * If missing, the source file is NAPI readable
	 * 
	 * @param mime_type the mime_type
	 */
	public void setAttributeMime_type(String mime_type);

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getDefinition();
	
	/**
	 * 
	 * @param definition the definition
	 */
	public DataNode setDefinition(IDataset definition);

	/**
	 * 
	 * @return  the value.
	 */
	public String getDefinitionScalar();

	/**
	 * 
	 * @param definition the definition
	 */
	public DataNode setDefinitionScalar(String definition);

	/**
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeVersion();
	
	/**
	 * 
	 * @param version the version
	 */
	public void setDefinitionAttributeVersion(String version);

	/**
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeURL();
	
	/**
	 * 
	 * @param URL the URL
	 */
	public void setDefinitionAttributeURL(String URL);

}
