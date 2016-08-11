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
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * A monitor of incident beam data.
 * It is similar to the :ref:`NXdata` groups containing
 * monitor data and its associated dimension scale, e.g. time_of_flight or
 * wavelength in pulsed neutron instruments. However, it may also include
 * integrals, or scalar monitor counts, which are often used in both in both
 * pulsed and steady-state instrumentation.
 * 
 * @version 1.0
 */
public interface NXmonitor extends NXobject {

	public static final String NX_MODE = "mode";
	public static final String NX_START_TIME = "start_time";
	public static final String NX_END_TIME = "end_time";
	public static final String NX_PRESET = "preset";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_RANGE = "range";
	public static final String NX_NOMINAL = "nominal";
	public static final String NX_INTEGRAL = "integral";
	public static final String NX_TYPE = "type";
	public static final String NX_TIME_OF_FLIGHT = "time_of_flight";
	public static final String NX_EFFICIENCY = "efficiency";
	public static final String NX_DATA = "data";
	public static final String NX_SAMPLED_FRACTION = "sampled_fraction";
	public static final String NX_COUNT_TIME = "count_time";
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
	 * @param mode the mode
	 */
	public DataNode setMode(IDataset mode);

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
	public String getModeScalar();

	/**
	 * Count to a preset value based on either clock time (timer)
	 * or received monitor counts (monitor).
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>monitor</b> </li>
	 * <li><b>timer</b> </li></ul></p>
	 * </p>
	 * 
	 * @param mode the mode
	 */
	public DataNode setModeScalar(String mode);

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
	 * @param start_time the start_time
	 */
	public DataNode setStart_time(IDataset start_time);

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getStart_timeScalar();

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param start_time the start_time
	 */
	public DataNode setStart_timeScalar(Date start_time);

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
	 * @param end_time the end_time
	 */
	public DataNode setEnd_time(IDataset end_time);

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getEnd_timeScalar();

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param end_time the end_time
	 */
	public DataNode setEnd_timeScalar(Date end_time);

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
	 * @param preset the preset
	 */
	public DataNode setPreset(IDataset preset);

	/**
	 * preset value for time or monitor
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getPresetScalar();

	/**
	 * preset value for time or monitor
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param preset the preset
	 */
	public DataNode setPresetScalar(Number preset);

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
	 * @param distance the distance
	 */
	public DataNode setDistance(IDataset distance);

	/**
	 * Distance of monitor from sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDistanceScalar();

	/**
	 * Distance of monitor from sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistanceScalar(double distance);

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
	 * @param range the range
	 */
	public DataNode setRange(IDataset range);

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
	public double getRangeScalar();

	/**
	 * Range (X-axis, Time-of-flight, etc.) over which the integral was calculated
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @param range the range
	 */
	public DataNode setRangeScalar(double range);

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
	 * @param nominal the nominal
	 */
	public DataNode setNominal(IDataset nominal);

	/**
	 * Nominal reading to be used for normalisation purposes.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getNominalScalar();

	/**
	 * Nominal reading to be used for normalisation purposes.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param nominal the nominal
	 */
	public DataNode setNominalScalar(Number nominal);

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
	 * @param integral the integral
	 */
	public DataNode setIntegral(IDataset integral);

	/**
	 * Total integral monitor counts
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getIntegralScalar();

	/**
	 * Total integral monitor counts
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param integral the integral
	 */
	public DataNode setIntegralScalar(Number integral);

	/**
	 * Time variation of monitor counts
	 * 
	 * @return  the value.
	 */
	public NXlog getIntegral_log();
	
	/**
	 * Time variation of monitor counts
	 * 
	 * @param integral_log the integral_log
	 */
	public void setIntegral_log(NXlog integral_log);

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
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Fission Chamber</b> </li>
	 * <li><b>Scintillator</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Fission Chamber</b> </li>
	 * <li><b>Scintillator</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

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
	 * @param time_of_flight the time_of_flight
	 */
	public DataNode setTime_of_flight(IDataset time_of_flight);

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
	public double getTime_of_flightScalar();

	/**
	 * Time-of-flight
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param time_of_flight the time_of_flight
	 */
	public DataNode setTime_of_flightScalar(double time_of_flight);

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
	 * @param efficiency the efficiency
	 */
	public DataNode setEfficiency(IDataset efficiency);

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
	public Number getEfficiencyScalar();

	/**
	 * Monitor efficiency
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param efficiency the efficiency
	 */
	public DataNode setEfficiencyScalar(Number efficiency);

	/**
	 * Monitor data
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
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @param data the data
	 */
	public DataNode setData(IDataset data);

	/**
	 * Monitor data
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getDataScalar();

	/**
	 * Monitor data
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @param data the data
	 */
	public DataNode setDataScalar(Number data);

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
	 * @param sampled_fraction the sampled_fraction
	 */
	public DataNode setSampled_fraction(IDataset sampled_fraction);

	/**
	 * Proportion of incident beam sampled by the monitor (0<x<1)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSampled_fractionScalar();

	/**
	 * Proportion of incident beam sampled by the monitor (0<x<1)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @param sampled_fraction the sampled_fraction
	 */
	public DataNode setSampled_fractionScalar(double sampled_fraction);

	/**
	 * Geometry of the monitor
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * Geometry of the monitor
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
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
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Geometry of the monitor</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param geometry the value to set
	 */
	public void setGeometry(String name, NXgeometry geometry);
	
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
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Geometry of the monitor</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

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
	 * @param count_time the count_time
	 */
	public DataNode setCount_time(IDataset count_time);

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
	public double getCount_timeScalar();

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
	 * @param count_time the count_time
	 */
	public DataNode setCount_timeScalar(double count_time);

}
