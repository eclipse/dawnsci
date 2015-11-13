/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Use ``NXtransformations`` to gather together any set of movable or fixed
 * elements positioning the device described by the class that contains this.
 * 
 * @version 1.0
 */
public interface NXtransformations extends NXobject {

	/**
	 * Units need to be appropriate for translation or rotation
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAnonymous__NEEDS_XSD_CHANGE__();	

	/**
	 * Units need to be appropriate for translation or rotation
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getAnonymous__NEEDS_XSD_CHANGE__Scalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeTransformation_type();	

	/**
	 * Three values that define the axis for this transformation
	 * 
	 * @return  the value.
	 */
	public Number getAnonymous__NEEDS_XSD_CHANGE__AttributeVector();	

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * 
	 * @return  the value.
	 */
	public Number getAnonymous__NEEDS_XSD_CHANGE__AttributeOffset();	

	/**
	 * Units of the offset.
	 * 
	 * @return  the value.
	 */
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeOffset_units();	

	/**
	 * Points to the path of the next element in the geometry chain.
	 * 
	 * @return  the value.
	 */
	public String getAnonymous__NEEDS_XSD_CHANGE__AttributeDepends_on();	

}
