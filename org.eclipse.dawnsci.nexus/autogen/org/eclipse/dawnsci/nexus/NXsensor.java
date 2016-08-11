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
 * A sensor used to monitor an external condition
 * The condition itself is described in :ref:`NXenvironment`.
 * 
 * @version 1.0
 */
public interface NXsensor extends NXobject {

	public static final String NX_MODEL = "model";
	public static final String NX_NAME = "name";
	public static final String NX_SHORT_NAME = "short_name";
	public static final String NX_ATTACHED_TO = "attached_to";
	public static final String NX_MEASUREMENT = "measurement";
	public static final String NX_TYPE = "type";
	public static final String NX_RUN_CONTROL = "run_control";
	public static final String NX_HIGH_TRIP_VALUE = "high_trip_value";
	public static final String NX_LOW_TRIP_VALUE = "low_trip_value";
	public static final String NX_VALUE = "value";
	public static final String NX_VALUE_DERIV1 = "value_deriv1";
	public static final String NX_VALUE_DERIV2 = "value_deriv2";
	public static final String NX_EXTERNAL_FIELD_BRIEF = "external_field_brief";
	/**
	 * Sensor identification code/model number
	 * 
	 * @return  the value.
	 */
	public IDataset getModel();
	
	/**
	 * Sensor identification code/model number
	 * 
	 * @param model the model
	 */
	public DataNode setModel(IDataset model);

	/**
	 * Sensor identification code/model number
	 * 
	 * @return  the value.
	 */
	public String getModelScalar();

	/**
	 * Sensor identification code/model number
	 * 
	 * @param model the model
	 */
	public DataNode setModelScalar(String model);

	/**
	 * Name for the sensor
	 * 
	 * @return  the value.
	 */
	public IDataset getName();
	
	/**
	 * Name for the sensor
	 * 
	 * @param name the name
	 */
	public DataNode setName(IDataset name);

	/**
	 * Name for the sensor
	 * 
	 * @return  the value.
	 */
	public String getNameScalar();

	/**
	 * Name for the sensor
	 * 
	 * @param name the name
	 */
	public DataNode setNameScalar(String name);

	/**
	 * Short name of sensor used e.g. on monitor display program
	 * 
	 * @return  the value.
	 */
	public IDataset getShort_name();
	
	/**
	 * Short name of sensor used e.g. on monitor display program
	 * 
	 * @param short_name the short_name
	 */
	public DataNode setShort_name(IDataset short_name);

	/**
	 * Short name of sensor used e.g. on monitor display program
	 * 
	 * @return  the value.
	 */
	public String getShort_nameScalar();

	/**
	 * Short name of sensor used e.g. on monitor display program
	 * 
	 * @param short_name the short_name
	 */
	public DataNode setShort_nameScalar(String short_name);

	/**
	 * where sensor is attached to ("sample" | "can")
	 * 
	 * @return  the value.
	 */
	public IDataset getAttached_to();
	
	/**
	 * where sensor is attached to ("sample" | "can")
	 * 
	 * @param attached_to the attached_to
	 */
	public DataNode setAttached_to(IDataset attached_to);

	/**
	 * where sensor is attached to ("sample" | "can")
	 * 
	 * @return  the value.
	 */
	public String getAttached_toScalar();

	/**
	 * where sensor is attached to ("sample" | "can")
	 * 
	 * @param attached_to the attached_to
	 */
	public DataNode setAttached_toScalar(String attached_to);

	/**
	 * Defines the axes for logged vector quantities if they are not the global instrument axes
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * Defines the axes for logged vector quantities if they are not the global instrument axes
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);

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
	 * @param measurement the measurement
	 */
	public DataNode setMeasurement(IDataset measurement);

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
	public String getMeasurementScalar();

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
	 * @param measurement the measurement
	 */
	public DataNode setMeasurementScalar(String measurement);

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
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

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
	public String getTypeScalar();

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
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

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
	 * @param run_control the run_control
	 */
	public DataNode setRun_control(IDataset run_control);

	/**
	 * Is data collection controlled or synchronised to this quantity:
	 * 1=no, 0=to "value", 1=to "value_deriv1", etc.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public boolean getRun_controlScalar();

	/**
	 * Is data collection controlled or synchronised to this quantity:
	 * 1=no, 0=to "value", 1=to "value_deriv1", etc.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param run_control the run_control
	 */
	public DataNode setRun_controlScalar(boolean run_control);

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
	 * @param high_trip_value the high_trip_value
	 */
	public DataNode setHigh_trip_value(IDataset high_trip_value);

	/**
	 * Upper control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getHigh_trip_valueScalar();

	/**
	 * Upper control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param high_trip_value the high_trip_value
	 */
	public DataNode setHigh_trip_valueScalar(double high_trip_value);

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
	 * @param low_trip_value the low_trip_value
	 */
	public DataNode setLow_trip_value(IDataset low_trip_value);

	/**
	 * Lower control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getLow_trip_valueScalar();

	/**
	 * Lower control bound of sensor reading if using run_control
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param low_trip_value the low_trip_value
	 */
	public DataNode setLow_trip_valueScalar(double low_trip_value);

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
	 * @param value the value
	 */
	public DataNode setValue(IDataset value);

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
	public double getValueScalar();

	/**
	 * nominal setpoint or average value
	 * - need [n] as may be a vector
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param value the value
	 */
	public DataNode setValueScalar(double value);

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
	 * @param value_deriv1 the value_deriv1
	 */
	public DataNode setValue_deriv1(IDataset value_deriv1);

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
	public double getValue_deriv1Scalar();

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
	 * @param value_deriv1 the value_deriv1
	 */
	public DataNode setValue_deriv1Scalar(double value_deriv1);

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
	 * @param value_deriv2 the value_deriv2
	 */
	public DataNode setValue_deriv2(IDataset value_deriv2);

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
	public double getValue_deriv2Scalar();

	/**
	 * Nominal/average second derivative of value
	 * - same dimensions as "value" (may be a vector)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: ;
	 * </p>
	 * 
	 * @param value_deriv2 the value_deriv2
	 */
	public DataNode setValue_deriv2Scalar(double value_deriv2);

	/**
	 * Time history of sensor readings
	 * 
	 * @return  the value.
	 */
	public NXlog getValue_log();
	
	/**
	 * Time history of sensor readings
	 * 
	 * @param value_log the value_log
	 */
	public void setValue_log(NXlog value_log);

	/**
	 * Time history of first derivative of sensor readings
	 * 
	 * @return  the value.
	 */
	public NXlog getValue_deriv1_log();
	
	/**
	 * Time history of first derivative of sensor readings
	 * 
	 * @param value_deriv1_log the value_deriv1_log
	 */
	public void setValue_deriv1_log(NXlog value_deriv1_log);

	/**
	 * Time history of second derivative of sensor readings
	 * 
	 * @return  the value.
	 */
	public NXlog getValue_deriv2_log();
	
	/**
	 * Time history of second derivative of sensor readings
	 * 
	 * @param value_deriv2_log the value_deriv2_log
	 */
	public void setValue_deriv2_log(NXlog value_deriv2_log);

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
	 * @param external_field_brief the external_field_brief
	 */
	public DataNode setExternal_field_brief(IDataset external_field_brief);

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
	public String getExternal_field_briefScalar();

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
	 * @param external_field_brief the external_field_brief
	 */
	public DataNode setExternal_field_briefScalar(String external_field_brief);

	/**
	 * For complex external fields not satisfied by External_field_brief
	 * 
	 * @return  the value.
	 */
	public NXorientation getExternal_field_full();
	
	/**
	 * For complex external fields not satisfied by External_field_brief
	 * 
	 * @param external_field_full the external_field_full
	 */
	public void setExternal_field_full(NXorientation external_field_full);

}
