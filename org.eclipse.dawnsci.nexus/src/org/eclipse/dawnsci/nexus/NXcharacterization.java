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

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Note: This base class may be removed in future releases of NXDL.
 * If you have a use for this base class, please provide a description
 * of your intended use to the NIAC (nexus-committee@nexusformat.org).
 * .. TODO: NXcharacterization needs proper documentation
 * 
 * @version 1.0
 */
public interface NXcharacterization extends NXobject {

	/**
	 * If missing, the source file is the current file
	 * 
	 * @return  the value.
	 */
	public String getAttributeSource();

	/**
	 * 
	 * @return  the value.
	 */
	public String getAttributeLocation();

	/**
	 * If missing, the source file is NAPI readable
	 * 
	 * @return  the value.
	 */
	public String getAttributeMime_type();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getDefinition();

	/**
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeVersion();

	/**
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeURL();

}
