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
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of monitor data. It is similar to the NXdata groups containing
 * monitor data and its associated dimension scale, e.g. time_of_flight or
 * wavelength in pulsed neutron instruments. However, it may also include
 * integrals, or scalar monitor counts, which are often used in both in both
 * pulsed and steady-state instrumentation.
 * 
 * @version 1.0
 */
public interface NXmonitor extends NXobject {

	/**
	 * Count to a preset value based on either clock time (timer)
	 * or received monitor counts (monitor).
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>monitor</b> </li>
	 * <li><b>timer</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMode();	

	/**
	 * Count to a preset value based on either clock time (timer)
	 * or received monitor counts (monitor).
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>monitor</b> </li>
	 * <li><b>timer</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getModeScalar();

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getStart_time();	

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Date getStart_timeScalar();

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnd_time();	

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Date getEnd_timeScalar();

	/**
	 * preset value for time or monitor
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPreset();	

	/**
	 * preset value for time or monitor
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getPresetScalar();

	/**
	 * Distance of monitor from sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();	

	/**
	 * Distance of monitor from sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDistanceScalar();

	/**
	 * Range (X-axis, Time-of-flight, etc.) over which the integral was calculated
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRange();	

	/**
	 * Range (X-axis, Time-of-flight, etc.) over which the integral was calculated
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getRangeScalar();

	/**
	 * Nominal reading to be used for normalisation purposes.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNominal();	

	/**
	 * Nominal reading to be used for normalisation purposes.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getNominalScalar();

	/**
	 * Total integral monitor counts
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIntegral();	

	/**
	 * Total integral monitor counts
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getIntegralScalar();

	/**
	 * Time variation of monitor counts
	 * 
	 * @return  the value.
	 */
	public NXlog getIntegral_log();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Fission Chamber</b> </li>
	 * <li><b>Scintillator</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Fission Chamber</b> </li>
	 * <li><b>Scintillator</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Time-of-flight
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTime_of_flight();	

	/**
	 * Time-of-flight
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getTime_of_flightScalar();

	/**
	 * Monitor efficiency
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEfficiency();	

	/**
	 * Monitor efficiency
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getEfficiencyScalar();

	/**
	 * Monitor data
	 * The signal and axes attributes take the same definitions as in :ref:`NXdata`:
	 * :signal:
	 * ``signal=1`` means this is the plottable data
	 * :axes:
	 * ``axes="names"`` where names are defined as a colon-delimited string
	 * within this attribute in the C-order of the data array
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData();	

	/**
	 * Monitor data
	 * The signal and axes attributes take the same definitions as in :ref:`NXdata`:
	 * :signal:
	 * ``signal=1`` means this is the plottable data
	 * :axes:
	 * ``axes="names"`` where names are defined as a colon-delimited string
	 * within this attribute in the C-order of the data array
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getDataScalar();

	/**
	 * as defined for NXdata
	 * 
	 * @return  the value.
	 */
	public long getDataAttributeSignal();	

	/**
	 * as defined for NXdata
	 * 
	 * @return  the value.
	 */
	public String getDataAttributeAxes();	

	/**
	 * Proportion of incident beam sampled by the monitor (0<x<1)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSampled_fraction();	

	/**
	 * Proportion of incident beam sampled by the monitor (0<x<1)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSampled_fractionScalar();

	/**
	 * Geometry of the monitor
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Geometry of the monitor</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public NXgeometry getGeometry(String name);
	
	/**
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li>
	 * Geometry of the monitor</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * Elapsed actual counting time, can be an array of size ``np``
	 * when scanning. This is not the difference of the calendar time
	 * but the time the instrument was really counting, without
	 * pauses or times lost due beam unavailability
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCount_time();	

	/**
	 * Elapsed actual counting time, can be an array of size ``np``
	 * when scanning. This is not the difference of the calendar time
	 * but the time the instrument was really counting, without
	 * pauses or times lost due beam unavailability
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getCount_timeScalar();

}
