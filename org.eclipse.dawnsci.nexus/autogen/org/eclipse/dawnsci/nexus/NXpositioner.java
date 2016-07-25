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
 * A generic positioner such as a motor or piezo-electric transducer.
 * It is used to document the
 * current information of a piece of beam line equipment.
 * Note: When using multiple :ref:`NXpositioner` groups, it is suggested to place
 * them inside a :ref:`NXcollection` group. In such cases, when
 * :ref:`NXpositioner` is used in another class,
 * :ref:`NXcollection`/:ref:`NXpositioner`
 * is then constructed.
 * 
 * @version 1.0
 */
public interface NXpositioner extends NXobject {

	public static final String NX_NAME = "name";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_VALUE = "value";
	public static final String NX_RAW_VALUE = "raw_value";
	public static final String NX_TARGET_VALUE = "target_value";
	public static final String NX_TOLERANCE = "tolerance";
	public static final String NX_SOFT_LIMIT_MIN = "soft_limit_min";
	public static final String NX_SOFT_LIMIT_MAX = "soft_limit_max";
	public static final String NX_VELOCITY = "velocity";
	public static final String NX_ACCELERATION_TIME = "acceleration_time";
	public static final String NX_CONTROLLER_RECORD = "controller_record";
	/**
	 * symbolic or mnemonic name (one word)
	 * 
	 * @return  the value.
	 */
	public IDataset getName();
	
	/**
	 * symbolic or mnemonic name (one word)
	 * 
	 * @param name the name
	 */
	public DataNode setName(IDataset name);

	/**
	 * symbolic or mnemonic name (one word)
	 * 
	 * @return  the value.
	 */
	public String getNameScalar();

	/**
	 * symbolic or mnemonic name (one word)
	 * 
	 * @param name the name
	 */
	public DataNode setNameScalar(String name);

	/**
	 * description of positioner
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * description of positioner
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * description of positioner
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * description of positioner
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * best known value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getValue();
	
	/**
	 * best known value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param value the value
	 */
	public DataNode setValue(IDataset value);

	/**
	 * best known value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getValueScalar();

	/**
	 * best known value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param value the value
	 */
	public DataNode setValueScalar(Number value);

	/**
	 * raw value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRaw_value();
	
	/**
	 * raw value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param raw_value the raw_value
	 */
	public DataNode setRaw_value(IDataset raw_value);

	/**
	 * raw value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getRaw_valueScalar();

	/**
	 * raw value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param raw_value the raw_value
	 */
	public DataNode setRaw_valueScalar(Number raw_value);

	/**
	 * targeted (commanded) value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTarget_value();
	
	/**
	 * targeted (commanded) value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param target_value the target_value
	 */
	public DataNode setTarget_value(IDataset target_value);

	/**
	 * targeted (commanded) value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getTarget_valueScalar();

	/**
	 * targeted (commanded) value of positioner - need [n] as may be scanned
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param target_value the target_value
	 */
	public DataNode setTarget_valueScalar(Number target_value);

	/**
	 * maximum allowable difference between target_value and value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTolerance();
	
	/**
	 * maximum allowable difference between target_value and value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param tolerance the tolerance
	 */
	public DataNode setTolerance(IDataset tolerance);

	/**
	 * maximum allowable difference between target_value and value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getToleranceScalar();

	/**
	 * maximum allowable difference between target_value and value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @param tolerance the tolerance
	 */
	public DataNode setToleranceScalar(Number tolerance);

	/**
	 * minimum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSoft_limit_min();
	
	/**
	 * minimum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param soft_limit_min the soft_limit_min
	 */
	public DataNode setSoft_limit_min(IDataset soft_limit_min);

	/**
	 * minimum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getSoft_limit_minScalar();

	/**
	 * minimum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param soft_limit_min the soft_limit_min
	 */
	public DataNode setSoft_limit_minScalar(Number soft_limit_min);

	/**
	 * maximum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSoft_limit_max();
	
	/**
	 * maximum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param soft_limit_max the soft_limit_max
	 */
	public DataNode setSoft_limit_max(IDataset soft_limit_max);

	/**
	 * maximum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getSoft_limit_maxScalar();

	/**
	 * maximum allowed limit to set value
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param soft_limit_max the soft_limit_max
	 */
	public DataNode setSoft_limit_maxScalar(Number soft_limit_max);

	/**
	 * velocity of the positioner (distance moved per unit time)
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getVelocity();
	
	/**
	 * velocity of the positioner (distance moved per unit time)
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param velocity the velocity
	 */
	public DataNode setVelocity(IDataset velocity);

	/**
	 * velocity of the positioner (distance moved per unit time)
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getVelocityScalar();

	/**
	 * velocity of the positioner (distance moved per unit time)
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param velocity the velocity
	 */
	public DataNode setVelocityScalar(Number velocity);

	/**
	 * time to ramp the velocity up to full speed
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAcceleration_time();
	
	/**
	 * time to ramp the velocity up to full speed
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param acceleration_time the acceleration_time
	 */
	public DataNode setAcceleration_time(IDataset acceleration_time);

	/**
	 * time to ramp the velocity up to full speed
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAcceleration_timeScalar();

	/**
	 * time to ramp the velocity up to full speed
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param acceleration_time the acceleration_time
	 */
	public DataNode setAcceleration_timeScalar(Number acceleration_time);

	/**
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @return  the value.
	 */
	public IDataset getController_record();
	
	/**
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @param controller_record the controller_record
	 */
	public DataNode setController_record(IDataset controller_record);

	/**
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @return  the value.
	 */
	public String getController_recordScalar();

	/**
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @param controller_record the controller_record
	 */
	public DataNode setController_recordScalar(String controller_record);

}
