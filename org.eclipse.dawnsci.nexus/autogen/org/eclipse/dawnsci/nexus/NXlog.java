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

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * Information recorded as a function of time.
 * Description of information that is recorded against time,
 * such as information monitored during the run.
 * It contains
 * the logged values and the times at which they were measured as elapsed time since a starting
 * time recorded in ISO8601 format. This method of storing logged data helps to distinguish
 * instances in which a variable is a dimension scale of the data, in which case it is stored
 * in an :ref:`NXdata` group, and instances in which it is logged during the
 * run, when it should be stored in an :ref:`NXlog` group.
 * Note: When using multiple :ref:`NXlog` groups, it is suggested to place
 * them inside a :ref:`NXcollection` group. In such cases, when
 * :ref:`NXlog` is used in another class,
 * :ref:`NXcollection`/:ref:`NXlog` is then constructed.
 * 
 * @version 1.0
 */
public interface NXlog extends NXobject {

	public static final String NX_TIME = "time";
	public static final String NX_TIME_ATTRIBUTE_START = "start";
	public static final String NX_VALUE = "value";
	public static final String NX_RAW_VALUE = "raw_value";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_AVERAGE_VALUE = "average_value";
	public static final String NX_AVERAGE_VALUE_ERROR = "average_value_error";
	public static final String NX_MINIMUM_VALUE = "minimum_value";
	public static final String NX_MAXIMUM_VALUE = "maximum_value";
	public static final String NX_DURATION = "duration";
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
	 * @param time the time
	 */
	public DataNode setTime(IDataset time);

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
	public double getTimeScalar();

	/**
	 * Time of logged entry. The times are relative to the "start" attribute
	 * and in the units specified in the "units" attribute.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param time the time
	 */
	public DataNode setTimeScalar(double time);

	/**
	 * 
	 * @return  the value.
	 */
	public Date getTimeAttributeStart();
	
	/**
	 * 
	 * @param start the start
	 */
	public void setTimeAttributeStart(Date start);

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
	 * @param value the value
	 */
	public DataNode setValue(IDataset value);

	/**
	 * Array of logged value, such as temperature
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getValueScalar();

	/**
	 * Array of logged value, such as temperature
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param value the value
	 */
	public DataNode setValueScalar(Number value);

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
	 * @param raw_value the raw_value
	 */
	public DataNode setRaw_value(IDataset raw_value);

	/**
	 * Array of raw information, such as thermocouple voltage
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getRaw_valueScalar();

	/**
	 * Array of raw information, such as thermocouple voltage
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param raw_value the raw_value
	 */
	public DataNode setRaw_valueScalar(Number raw_value);

	/**
	 * Description of logged value
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * Description of logged value
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * Description of logged value
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * Description of logged value
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

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
	 * @param average_value the average_value
	 */
	public DataNode setAverage_value(IDataset average_value);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getAverage_valueScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param average_value the average_value
	 */
	public DataNode setAverage_valueScalar(double average_value);

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
	 * @param average_value_error the average_value_error
	 */
	public DataNode setAverage_value_error(IDataset average_value_error);

	/**
	 * estimated uncertainty (often used: standard deviation) of average_value
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getAverage_value_errorScalar();

	/**
	 * estimated uncertainty (often used: standard deviation) of average_value
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param average_value_error the average_value_error
	 */
	public DataNode setAverage_value_errorScalar(double average_value_error);

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
	 * @param minimum_value the minimum_value
	 */
	public DataNode setMinimum_value(IDataset minimum_value);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMinimum_valueScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param minimum_value the minimum_value
	 */
	public DataNode setMinimum_valueScalar(double minimum_value);

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
	 * @param maximum_value the maximum_value
	 */
	public DataNode setMaximum_value(IDataset maximum_value);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMaximum_valueScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param maximum_value the maximum_value
	 */
	public DataNode setMaximum_valueScalar(double maximum_value);

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
	 * @param duration the duration
	 */
	public DataNode setDuration(IDataset duration);

	/**
	 * Total time log was taken
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDurationScalar();

	/**
	 * Total time log was taken
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param duration the duration
	 */
	public DataNode setDurationScalar(double duration);

}
