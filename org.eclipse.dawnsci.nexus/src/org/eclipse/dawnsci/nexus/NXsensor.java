/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * This class describes a sensor used to monitor an external condition
 * - the condition itself is described in NXenvironment
 * 
 * @version 1.0
 */
public interface NXsensor extends NXobject {

	/**
	 * Sensor identification code/model number
	 * 
	 * @return  the value.
	 */
	public IDataset getModel();	

	/**
	 * Sensor identification code/model number
	 * 
	 * @return  the value
	 */
	 public String getModelScalar();

	/**
	 * Name for the sensor
	 * 
	 * @return  the value.
	 */
	public IDataset getName();	

	/**
	 * Name for the sensor
	 * 
	 * @return  the value
	 */
	 public String getNameScalar();

	/**
	 * Short name of sensor used e.g. on monitor display program
	 * 
	 * @return  the value.
	 */
	public IDataset getShort_name();	

	/**
	 * Short name of sensor used e.g. on monitor display program
	 * 
	 * @return  the value
	 */
	 public String getShort_nameScalar();

	/**
	 * where sensor is attached to ("sample" | "can")
	 * 
	 * @return  the value.
	 */
	public IDataset getAttached_to();	

	/**
	 * where sensor is attached to ("sample" | "can")
	 * 
	 * @return  the value
	 */
	 public String getAttached_toScalar();

	/**
	 * Defines the axes for logged vector quantities if they are not the global instrument axes
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	

	/**
	 * name for measured signal
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>temperature</b> </li>
	 * <li><b>pH</b> </li>
	 * <li><b>magnetic_field</b> </li>
	 * <li><b>electric_field</b> </li>
	 * <li><b>conductivity</b> </li>
	 * <li><b>resistance</b> </li>
	 * <li><b>voltage</b> </li>
	 * <li><b>pressure</b> </li>
	 * <li><b>flow</b> </li>
	 * <li><b>stress</b> </li>
	 * <li><b>strain</b> </li>
	 * <li><b>shear</b> </li>
	 * <li><b>surface_pressure</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMeasurement();	

	/**
	 * name for measured signal
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>temperature</b> </li>
	 * <li><b>pH</b> </li>
	 * <li><b>magnetic_field</b> </li>
	 * <li><b>electric_field</b> </li>
	 * <li><b>conductivity</b> </li>
	 * <li><b>resistance</b> </li>
	 * <li><b>voltage</b> </li>
	 * <li><b>pressure</b> </li>
	 * <li><b>flow</b> </li>
	 * <li><b>stress</b> </li>
	 * <li><b>strain</b> </li>
	 * <li><b>shear</b> </li>
	 * <li><b>surface_pressure</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getMeasurementScalar();

	/**
	 * The type of hardware used for the measurement.
	 * Examples (suggestions but not restrictions):
	 * :Temperature:
	 * J | K | T | E | R | S | Pt100 | Rh/Fe
	 * :pH:
	 * Hg/Hg2Cl2 | Ag/AgCl | ISFET
	 * :Ion selective electrode:
	 * specify species; e.g. Ca2+
	 * :Magnetic field:
	 * Hall
	 * :Surface pressure:
	 * wilhelmy plate
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * The type of hardware used for the measurement.
	 * Examples (suggestions but not restrictions):
	 * :Temperature:
	 * J | K | T | E | R | S | Pt100 | Rh/Fe
	 * :pH:
	 * Hg/Hg2Cl2 | Ag/AgCl | ISFET
	 * :Ion selective electrode:
	 * specify species; e.g. Ca2+
	 * :Magnetic field:
	 * Hall
	 * :Surface pressure:
	 * wilhelmy plate
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Is data collection controlled or synchronised to this quantity:
	 * 1=no, 0=to "value", 1=to "value_deriv1", etc.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRun_control();	

	/**
	 * Is data collection controlled or synchronised to this quantity:
	 * 1=no, 0=to "value", 1=to "value_deriv1", etc.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getRun_controlScalar();

	/**
	 * Upper control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getHigh_trip_value();	

	/**
	 * Upper control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getHigh_trip_valueScalar();

	/**
	 * Lower control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLow_trip_value();	

	/**
	 * Lower control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getLow_trip_valueScalar();

	/**
	 * nominal setpoint or average value
	 * - need [n] as may be a vector
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue();	

	/**
	 * nominal setpoint or average value
	 * - need [n] as may be a vector
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getValueScalar();

	/**
	 * Nominal/average first derivative of value
	 * e.g. strain rate
	 * - same dimensions as "value" (may be a vector)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue_deriv1();	

	/**
	 * Nominal/average first derivative of value
	 * e.g. strain rate
	 * - same dimensions as "value" (may be a vector)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getValue_deriv1Scalar();

	/**
	 * Nominal/average second derivative of value
	 * - same dimensions as "value" (may be a vector)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue_deriv2();	

	/**
	 * Nominal/average second derivative of value
	 * - same dimensions as "value" (may be a vector)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getValue_deriv2Scalar();

	/**
	 * Time history of sensor readings
	 * 
	 * @return  the value.
	 */
	public NXlog getValue_log();	

	/**
	 * Time history of first derivative of sensor readings
	 * 
	 * @return  the value.
	 */
	public NXlog getValue_deriv1_log();	

	/**
	 * Time history of second derivative of sensor readings
	 * 
	 * @return  the value.
	 */
	public NXlog getValue_deriv2_log();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>along beam</b> </li>
	 * <li><b>across beam</b> </li>
	 * <li><b>transverse</b> </li>
	 * <li><b>solenoidal</b> </li>
	 * <li><b>flow shear gradient</b> </li>
	 * <li><b>flow vorticity</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getExternal_field_brief();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>along beam</b> </li>
	 * <li><b>across beam</b> </li>
	 * <li><b>transverse</b> </li>
	 * <li><b>solenoidal</b> </li>
	 * <li><b>flow shear gradient</b> </li>
	 * <li><b>flow vorticity</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getExternal_field_briefScalar();

	/**
	 * For complex external fields not satisfied by External_field_brief
	 * 
	 * @return  the value.
	 */
	public NXorientation getExternal_field_full();	

}
