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
 * Container for parameters, usually used in processing or analysis.
 * 
 * @version 1.0
 */
public interface NXparameters extends NXobject {

	public static final String NX_TERM = "term";
	public static final String NX_TERM_ATTRIBUTE_UNITS = "units";
	/**
	 * A parameter (also known as a term) that is used in or results from processing.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTerm();
	
	/**
	 * A parameter (also known as a term) that is used in or results from processing.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param term the term
	 */
	public DataNode setTerm(IDataset term);

	/**
	 * A parameter (also known as a term) that is used in or results from processing.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTermScalar();

	/**
	 * A parameter (also known as a term) that is used in or results from processing.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param term the term
	 */
	public DataNode setTermScalar(String term);

	/**
	 * 
	 * @return  the value.
	 */
	public String getTermAttributeUnits();
	
	/**
	 * 
	 * @param units the units
	 */
	public void setTermAttributeUnits(String units);

}
