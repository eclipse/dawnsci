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

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

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
	 * Time of logged entry. The times are relative to the "start" attribute
	 * and in the units specified in the "units" attribute.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getTimeScalar();

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
	 * Array of logged value, such as temperature
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getValueScalar();

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
	 * Array of raw information, such as thermocouple voltage
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getRaw_valueScalar();

	/**
	 * Description of logged value
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();	

	/**
	 * Description of logged value
	 * 
	 * @return  the value
	 */
	 public String getDescriptionScalar();

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
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getAverage_valueScalar();

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
	 * estimated uncertainty (often used: standard deviation) of average_value
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getAverage_value_errorScalar();

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
	 * @return  the value
	 */
	 public double getMinimum_valueScalar();

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
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getMaximum_valueScalar();

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

	/**
	 * Total time log was taken
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDurationScalar();

}
