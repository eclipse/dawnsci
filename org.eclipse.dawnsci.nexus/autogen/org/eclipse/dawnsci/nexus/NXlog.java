/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

/**
 * Information recorded as a function of time.
 * Description of information that is recorded against
 * time. There are two common use cases for this:
 * - When logging data such as temperature during a run
 * - When data is taken in streaming mode data acquisition,
 * i.e. just timestamp, value pairs are stored and
 * correlated later in data reduction with other data,
 * It both cases NXlog contains
 * the logged or streamed values and the times at which they were measured as elapsed time since a starting
 * time recorded in ISO8601 format. The time units are
 * specified in the units attribute. An optional scaling attribute
 * can be used to accomodate non standard clocks.
 * This method of storing logged data helps to distinguish
 * instances in which a variable is a dimension scale of the data, in which case it is stored
 * in an :ref:`NXdata` group, and instances in which it is logged during the
 * run, when it should be stored in an :ref:`NXlog` group.
 * In order to make random access to timestamped data faster there is an optional array pair of
 * ``cue_timestamp_zero`` and ``cue_index``. The ``cue_timestamp_zero`` will
 * contain coarser timestamps than in the time array, say
 * every five minutes. The ``cue_index`` will then contain the
 * index into the time,value pair of arrays for that
 * coarser ``cue_timestamp_zero``.
 * 
 * @version 1.1
 */
public interface NXlog extends NXobject {

	public static final String NX_TIME = "time";
	public static final String NX_TIME_ATTRIBUTE_START = "start";
	public static final String NX_TIME_ATTRIBUTE_SCALING = "scaling";
	public static final String NX_VALUE = "value";
	public static final String NX_RAW_VALUE = "raw_value";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_AVERAGE_VALUE = "average_value";
	public static final String NX_AVERAGE_VALUE_ERROR = "average_value_error";
	public static final String NX_MINIMUM_VALUE = "minimum_value";
	public static final String NX_MAXIMUM_VALUE = "maximum_value";
	public static final String NX_DURATION = "duration";
	public static final String NX_CUE_TIMESTAMP_ZERO = "cue_timestamp_zero";
	public static final String NX_CUE_TIMESTAMP_ZERO_ATTRIBUTE_START = "start";
	public static final String NX_CUE_INDEX = "cue_index";
	/**
	 * Time of logged entry. The times are relative to the "start" attribute
	 * and in the units specified in the "units"
	 * attribute. Please note that absolute
	 * timestamps under unix are relative to ``1970-01-01T:00:00``.
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
	 * and in the units specified in the "units"
	 * attribute. Please note that absolute
	 * timestamps under unix are relative to ``1970-01-01T:00:00``.
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
	 * and in the units specified in the "units"
	 * attribute. Please note that absolute
	 * timestamps under unix are relative to ``1970-01-01T:00:00``.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getTimeScalar();

	/**
	 * Time of logged entry. The times are relative to the "start" attribute
	 * and in the units specified in the "units"
	 * attribute. Please note that absolute
	 * timestamps under unix are relative to ``1970-01-01T:00:00``.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param time the time
	 */
	public DataNode setTimeScalar(Double time);

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
	 * 
	 * @return  the value.
	 */
	public Number getTimeAttributeScaling();
	
	/**
	 * 
	 * @param scaling the scaling
	 */
	public void setTimeAttributeScaling(Number scaling);

	/**
	 * Array of logged value, such as temperature. If this is
	 * a single value the dimensionality is
	 * nEntries. However, NXlog can also be used to store
	 * multi dimensional time stamped data such as images. In
	 * this example the dimensionality of values would be value[nEntries,xdim,ydim].
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue();
	
	/**
	 * Array of logged value, such as temperature. If this is
	 * a single value the dimensionality is
	 * nEntries. However, NXlog can also be used to store
	 * multi dimensional time stamped data such as images. In
	 * this example the dimensionality of values would be value[nEntries,xdim,ydim].
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param value the value
	 */
	public DataNode setValue(IDataset value);

	/**
	 * Array of logged value, such as temperature. If this is
	 * a single value the dimensionality is
	 * nEntries. However, NXlog can also be used to store
	 * multi dimensional time stamped data such as images. In
	 * this example the dimensionality of values would be value[nEntries,xdim,ydim].
	 * <p>
	 * <b>Units:</b> NX_ANY
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getValueScalar();

	/**
	 * Array of logged value, such as temperature. If this is
	 * a single value the dimensionality is
	 * nEntries. However, NXlog can also be used to store
	 * multi dimensional time stamped data such as images. In
	 * this example the dimensionality of values would be value[nEntries,xdim,ydim].
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
	public Double getAverage_valueScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param average_value the average_value
	 */
	public DataNode setAverage_valueScalar(Double average_value);

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
	public Double getAverage_value_errorScalar();

	/**
	 * estimated uncertainty (often used: standard deviation) of average_value
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param average_value_error the average_value_error
	 */
	public DataNode setAverage_value_errorScalar(Double average_value_error);

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
	public Double getMinimum_valueScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param minimum_value the minimum_value
	 */
	public DataNode setMinimum_valueScalar(Double minimum_value);

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
	public Double getMaximum_valueScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param maximum_value the maximum_value
	 */
	public DataNode setMaximum_valueScalar(Double maximum_value);

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
	public Double getDurationScalar();

	/**
	 * Total time log was taken
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param duration the duration
	 */
	public DataNode setDurationScalar(Double duration);

	/**
	 * Timestamps matching the corresponding cue_index into the
	 * time, value pair.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCue_timestamp_zero();
	
	/**
	 * Timestamps matching the corresponding cue_index into the
	 * time, value pair.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param cue_timestamp_zero the cue_timestamp_zero
	 */
	public DataNode setCue_timestamp_zero(IDataset cue_timestamp_zero);

	/**
	 * Timestamps matching the corresponding cue_index into the
	 * time, value pair.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getCue_timestamp_zeroScalar();

	/**
	 * Timestamps matching the corresponding cue_index into the
	 * time, value pair.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param cue_timestamp_zero the cue_timestamp_zero
	 */
	public DataNode setCue_timestamp_zeroScalar(Date cue_timestamp_zero);

	/**
	 * 
	 * @return  the value.
	 */
	public Date getCue_timestamp_zeroAttributeStart();
	
	/**
	 * 
	 * @param start the start
	 */
	public void setCue_timestamp_zeroAttributeStart(Date start);

	/**
	 * Index into the time, value pair matching the corresponding
	 * cue_timestamp.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCue_index();
	
	/**
	 * Index into the time, value pair matching the corresponding
	 * cue_timestamp.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param cue_index the cue_index
	 */
	public DataNode setCue_index(IDataset cue_index);

	/**
	 * Index into the time, value pair matching the corresponding
	 * cue_timestamp.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Long getCue_indexScalar();

	/**
	 * Index into the time, value pair matching the corresponding
	 * cue_timestamp.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param cue_index the cue_index
	 */
	public DataNode setCue_indexScalar(Long cue_index);

}
