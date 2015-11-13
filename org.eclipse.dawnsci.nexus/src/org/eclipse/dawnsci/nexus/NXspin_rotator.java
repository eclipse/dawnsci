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
 * definition for a spin rotator.
 * 
 * @version 1.0
 */
public interface NXspin_rotator extends NXobject {

	/**
	 * extended description of the spin rotator.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();	

	/**
	 * extended description of the spin rotator.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getDescriptionScalar();

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
	 * define position of beamline element relative to production target
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBeamline_distanceScalar();

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
	 * current set on magnet supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSet_Bfield_currentScalar();

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
	 * current set on HT supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSet_Efield_voltageScalar();

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
