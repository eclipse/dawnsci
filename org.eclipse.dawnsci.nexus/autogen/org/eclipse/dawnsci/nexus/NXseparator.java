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
 * definition for an electrostatic separator.
 * 
 * @version 1.0
 */
public interface NXseparator extends NXobject {

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_BEAMLINE_DISTANCE = "beamline_distance";
	public static final String NX_SET_BFIELD_CURRENT = "set_Bfield_current";
	public static final String NX_SET_EFIELD_VOLTAGE = "set_Efield_voltage";
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
	 * extended description of the separator.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * extended description of the separator.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * extended description of the separator.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

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
	 * @param beamline_distance the beamline_distance
	 */
	public DataNode setBeamline_distance(IDataset beamline_distance);

	/**
	 * define position of beamline element relative to production target
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBeamline_distanceScalar();

	/**
	 * define position of beamline element relative to production target
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param beamline_distance the beamline_distance
	 */
	public DataNode setBeamline_distanceScalar(double beamline_distance);

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
	 * @param set_Bfield_current the set_Bfield_current
	 */
	public DataNode setSet_Bfield_current(IDataset set_Bfield_current);

	/**
	 * current set on magnet supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSet_Bfield_currentScalar();

	/**
	 * current set on magnet supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param set_Bfield_current the set_Bfield_current
	 */
	public DataNode setSet_Bfield_currentScalar(double set_Bfield_current);

	/**
	 * current read from magnet supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Bfield_current();
	
	/**
	 * current read from magnet supply.
	 * 
	 * @param read_Bfield_current the read_Bfield_current
	 */
	public void setRead_Bfield_current(NXlog read_Bfield_current);

	/**
	 * voltage read from magnet supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Bfield_voltage();
	
	/**
	 * voltage read from magnet supply.
	 * 
	 * @param read_Bfield_voltage the read_Bfield_voltage
	 */
	public void setRead_Bfield_voltage(NXlog read_Bfield_voltage);

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
	 * @param set_Efield_voltage the set_Efield_voltage
	 */
	public DataNode setSet_Efield_voltage(IDataset set_Efield_voltage);

	/**
	 * current set on HT supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSet_Efield_voltageScalar();

	/**
	 * current set on HT supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @param set_Efield_voltage the set_Efield_voltage
	 */
	public DataNode setSet_Efield_voltageScalar(double set_Efield_voltage);

	/**
	 * current read from HT supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Efield_current();
	
	/**
	 * current read from HT supply.
	 * 
	 * @param read_Efield_current the read_Efield_current
	 */
	public void setRead_Efield_current(NXlog read_Efield_current);

	/**
	 * voltage read from HT supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_Efield_voltage();
	
	/**
	 * voltage read from HT supply.
	 * 
	 * @param read_Efield_voltage the read_Efield_voltage
	 */
	public void setRead_Efield_voltage(NXlog read_Efield_voltage);

}
