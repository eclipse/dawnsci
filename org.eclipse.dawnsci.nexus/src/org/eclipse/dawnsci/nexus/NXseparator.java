/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * definition for an electrostatic separator.
 * 
 * @version 1.0
 */
public interface NXseparator extends NXobject {

	/**
	 * extended description of the separator.
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
	 * current set on magnet supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSet_Bfield_current();

	/**
	 * current read from magnet supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Bfield_current();

	/**
	 * voltage read from magnet supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Bfield_voltage();

	/**
	 * current set on HT supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSet_Efield_voltage();

	/**
	 * current read from HT supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Efield_current();

	/**
	 * voltage read from HT supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Efield_voltage();

}
