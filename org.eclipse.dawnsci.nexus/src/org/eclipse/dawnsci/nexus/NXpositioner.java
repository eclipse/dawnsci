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
 * This group describes a generic positioner
 * such as a motor or piezo-electric transducer. It is used to document the
 * current information of a piece of beam line equipment.
 * Note: When using multiple ``NXpositioner`` groups, it is suggested to place
 * them inside a ``NXcollection`` group. In such cases, when
 * ``NXpositioner`` is used in another class,
 * ``NXcollection/NXpositioner``
 * is then constructed.
 * 
 * @version 1.0
 */
public interface NXpositioner extends NXobject {

	/**
	 * symbolic or mnemonic name (one word)
	 * 
	 * @return  the value.
	 */
	public IDataset getName();	

	/**
	 * symbolic or mnemonic name (one word)
	 * 
	 * @return  the value
	 */
	 public String getNameScalar();

	/**
	 * description of positioner
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();	

	/**
	 * description of positioner
	 * 
	 * @return  the value
	 */
	 public String getDescriptionScalar();

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
	 * @return  the value
	 */
	 public Number getValueScalar();

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
	 * @return  the value
	 */
	 public Number getRaw_valueScalar();

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
	 * @return  the value
	 */
	 public Number getTarget_valueScalar();

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
	 * @return  the value
	 */
	 public Number getToleranceScalar();

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
	 * @return  the value
	 */
	 public Number getSoft_limit_minScalar();

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
	 * @return  the value
	 */
	 public Number getSoft_limit_maxScalar();

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
	 * @return  the value
	 */
	 public Number getVelocityScalar();

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
	 * @return  the value
	 */
	 public Number getAcceleration_timeScalar();

	/**
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @return  the value.
	 */
	public IDataset getController_record();	

	/**
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @return  the value
	 */
	 public String getController_recordScalar();

}
