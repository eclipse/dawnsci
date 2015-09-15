/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Time-of-flight events
 * 
 * @version 1.0
 */
public interface NXevent_data extends NXobject {

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
	 * ISO8601
	 * 
	 * @return  the value.
	 */
	public Date getPulse_timeAttributeOffset();

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

}
