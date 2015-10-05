/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

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
	 * description of positioner
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

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
	 * Hardware device record, e.g. EPICS process variable, taco/tango ...
	 * 
	 * @return  the value.
	 */
	public IDataset getController_record();

}
