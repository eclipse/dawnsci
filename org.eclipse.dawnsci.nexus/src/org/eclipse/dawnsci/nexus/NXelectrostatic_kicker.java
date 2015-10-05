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

/**
 * definition for a electrostatic kicker.
 * 
 * @version 1.0
 */
public interface NXelectrostatic_kicker extends NXobject {

	/**
	 * extended description of the kicker.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * define position of beamline element relative to production target
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBeamline_distance();

	/**
	 * kicker timing as defined by ``description`` attribute
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTiming();

	/**
	 * 
	 * @return  the value.
	 */
	public String getTimingAttributeDescription();

	/**
	 * current set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSet_current();

	/**
	 * current read from supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_current();

	/**
	 * volage set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSet_voltage();

	/**
	 * voltage read from supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_voltage();

}
