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

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Definition of information that is recorded against time,
 * such as information monitored during the run.
 * It contains
 * the logged values and the times at which they were measured as elapsed time since a starting
 * time recorded in ISO8601 format. This method of storing logged data helps to distinguish
 * instances in which a variable is a dimension scale of the data, in which case it is stored
 * in an ``NXdata`` group, and instances in which it is logged during the
 * run, when it should be stored in an ``NXlog`` group.
 * Note: When using multiple ``NXlog`` groups, it is suggested to place
 * them inside a ``NXcollection`` group. In such cases, when
 * ``NXlog`` is used in another class,
 * ``NXcollection/NXlog`` is then constructed.
 * 
 * @version 1.0
 */
public interface NXlog extends NXobject {

	/**
	 * Time of logged entry. The times are relative to the "start" attribute
	 * and in the units specified in the "units" attribute.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTime();

	/**
	 * 
	 * @return  the value.
	 */
	public Date getTimeAttributeStart();

	/**
	 * Array of logged value, such as temperature
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue();

	/**
	 * Array of raw information, such as thermocouple voltage
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRaw_value();

	/**
	 * Description of logged value
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAverage_value();

	/**
	 * estimated uncertainty (often used: standard deviation) of average_value
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAverage_value_error();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMinimum_value();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMaximum_value();

	/**
	 * Total time log was taken
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDuration();

}
