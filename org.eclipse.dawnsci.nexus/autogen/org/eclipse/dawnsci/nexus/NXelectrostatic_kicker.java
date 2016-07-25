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
 * definition for a electrostatic kicker.
 * 
 * @version 1.0
 */
public interface NXelectrostatic_kicker extends NXobject {

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_BEAMLINE_DISTANCE = "beamline_distance";
	public static final String NX_TIMING = "timing";
	public static final String NX_TIMING_ATTRIBUTE_DESCRIPTION = "description";
	public static final String NX_SET_CURRENT = "set_current";
	public static final String NX_SET_VOLTAGE = "set_voltage";
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
	 * extended description of the kicker.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * extended description of the kicker.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * extended description of the kicker.
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
	 * kicker timing as defined by ``description`` attribute
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param timing the timing
	 */
	public DataNode setTiming(IDataset timing);

	/**
	 * kicker timing as defined by ``description`` attribute
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTimingScalar();

	/**
	 * kicker timing as defined by ``description`` attribute
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param timing the timing
	 */
	public DataNode setTimingScalar(double timing);

	/**
	 * 
	 * @return  the value.
	 */
	public String getTimingAttributeDescription();
	
	/**
	 * 
	 * @param description the description
	 */
	public void setTimingAttributeDescription(String description);

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
	 * current set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param set_current the set_current
	 */
	public DataNode setSet_current(IDataset set_current);

	/**
	 * current set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSet_currentScalar();

	/**
	 * current set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param set_current the set_current
	 */
	public DataNode setSet_currentScalar(double set_current);

	/**
	 * current read from supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_current();
	
	/**
	 * current read from supply.
	 * 
	 * @param read_current the read_current
	 */
	public void setRead_current(NXlog read_current);

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
	 * volage set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @param set_voltage the set_voltage
	 */
	public DataNode setSet_voltage(IDataset set_voltage);

	/**
	 * volage set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSet_voltageScalar();

	/**
	 * volage set on supply.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * </p>
	 * 
	 * @param set_voltage the set_voltage
	 */
	public DataNode setSet_voltageScalar(double set_voltage);

	/**
	 * voltage read from supply.
	 * 
	 * @return  the value.
	 */
	public NXlog getRead_voltage();
	
	/**
	 * voltage read from supply.
	 * 
	 * @param read_voltage the read_voltage
	 */
	public void setRead_voltage(NXlog read_voltage);

}
