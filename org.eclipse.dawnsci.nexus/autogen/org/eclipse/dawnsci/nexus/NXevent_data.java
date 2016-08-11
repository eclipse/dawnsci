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
 * Time-of-flight events
 * 
 * @version 1.0
 */
public interface NXevent_data extends NXobject {

	public static final String NX_TIME_OF_FLIGHT = "time_of_flight";
	public static final String NX_PIXEL_NUMBER = "pixel_number";
	public static final String NX_PULSE_TIME = "pulse_time";
	public static final String NX_PULSE_TIME_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_EVENTS_PER_PULSE = "events_per_pulse";
	public static final String NX_PULSE_HEIGHT = "pulse_height";
	/**
	 * A list of time of flight for each event as it comes in.
	 * This list is for all pulses with information to attach
	 * to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTime_of_flight();
	
	/**
	 * A list of time of flight for each event as it comes in.
	 * This list is for all pulses with information to attach
	 * to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param time_of_flight the time_of_flight
	 */
	public DataNode setTime_of_flight(IDataset time_of_flight);

	/**
	 * A list of time of flight for each event as it comes in.
	 * This list is for all pulses with information to attach
	 * to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getTime_of_flightScalar();

	/**
	 * A list of time of flight for each event as it comes in.
	 * This list is for all pulses with information to attach
	 * to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param time_of_flight the time_of_flight
	 */
	public DataNode setTime_of_flightScalar(long time_of_flight);

	/**
	 * There will be extra information in the NXdetector to convert
	 * pixel_number to detector_number. This list is for all pulses with
	 * information to attach to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPixel_number();
	
	/**
	 * There will be extra information in the NXdetector to convert
	 * pixel_number to detector_number. This list is for all pulses with
	 * information to attach to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param pixel_number the pixel_number
	 */
	public DataNode setPixel_number(IDataset pixel_number);

	/**
	 * There will be extra information in the NXdetector to convert
	 * pixel_number to detector_number. This list is for all pulses with
	 * information to attach to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getPixel_numberScalar();

	/**
	 * There will be extra information in the NXdetector to convert
	 * pixel_number to detector_number. This list is for all pulses with
	 * information to attach to a particular pulse located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param pixel_number the pixel_number
	 */
	public DataNode setPixel_numberScalar(long pixel_number);

	/**
	 * The time that each pulse started with respect to the offset
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPulse_time();
	
	/**
	 * The time that each pulse started with respect to the offset
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @param pulse_time the pulse_time
	 */
	public DataNode setPulse_time(IDataset pulse_time);

	/**
	 * The time that each pulse started with respect to the offset
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getPulse_timeScalar();

	/**
	 * The time that each pulse started with respect to the offset
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @param pulse_time the pulse_time
	 */
	public DataNode setPulse_timeScalar(long pulse_time);

	/**
	 * ISO8601
	 * 
	 * @return  the value.
	 */
	public Date getPulse_timeAttributeOffset();
	
	/**
	 * ISO8601
	 * 
	 * @param offset the offset
	 */
	public void setPulse_timeAttributeOffset(Date offset);

	/**
	 * This connects the index "i" to the index "j".
	 * The jth element is the number of events in "i"
	 * that occurred during the jth pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEvents_per_pulse();
	
	/**
	 * This connects the index "i" to the index "j".
	 * The jth element is the number of events in "i"
	 * that occurred during the jth pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @param events_per_pulse the events_per_pulse
	 */
	public DataNode setEvents_per_pulse(IDataset events_per_pulse);

	/**
	 * This connects the index "i" to the index "j".
	 * The jth element is the number of events in "i"
	 * that occurred during the jth pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getEvents_per_pulseScalar();

	/**
	 * This connects the index "i" to the index "j".
	 * The jth element is the number of events in "i"
	 * that occurred during the jth pulse.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: j;
	 * </p>
	 * 
	 * @param events_per_pulse the events_per_pulse
	 */
	public DataNode setEvents_per_pulseScalar(long events_per_pulse);

	/**
	 * If voltages from the ends of the detector are read out this
	 * is where they go. This list is for all events with information
	 * to attach to a particular pulse height. The information to
	 * attach to a particular pulse is located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i; 2: k;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPulse_height();
	
	/**
	 * If voltages from the ends of the detector are read out this
	 * is where they go. This list is for all events with information
	 * to attach to a particular pulse height. The information to
	 * attach to a particular pulse is located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i; 2: k;
	 * </p>
	 * 
	 * @param pulse_height the pulse_height
	 */
	public DataNode setPulse_height(IDataset pulse_height);

	/**
	 * If voltages from the ends of the detector are read out this
	 * is where they go. This list is for all events with information
	 * to attach to a particular pulse height. The information to
	 * attach to a particular pulse is located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i; 2: k;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPulse_heightScalar();

	/**
	 * If voltages from the ends of the detector are read out this
	 * is where they go. This list is for all events with information
	 * to attach to a particular pulse height. The information to
	 * attach to a particular pulse is located in events_per_pulse.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: i; 2: k;
	 * </p>
	 * 
	 * @param pulse_height the pulse_height
	 */
	public DataNode setPulse_heightScalar(double pulse_height);

}
