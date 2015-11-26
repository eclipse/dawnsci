/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-17T13:07:37.011Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of a beamline spin flipper.
 * 
 * @version 1.0
 */
public interface NXflipper extends NXobject {

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>coil</b> </li>
	 * <li><b>current-sheet</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>coil</b> </li>
	 * <li><b>current-sheet</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Linear density of turns (such as number of turns/cm) in flipping field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlip_turns();	

	/**
	 * Linear density of turns (such as number of turns/cm) in flipping field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFlip_turnsScalar();

	/**
	 * Linear density of turns (such as number of turns/cm) in compensating field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getComp_turns();	

	/**
	 * Linear density of turns (such as number of turns/cm) in compensating field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getComp_turnsScalar();

	/**
	 * Linear density of turns (such as number of turns/cm) in guide field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGuide_turns();	

	/**
	 * Linear density of turns (such as number of turns/cm) in guide field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getGuide_turnsScalar();

	/**
	 * Flipping field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlip_current();	

	/**
	 * Flipping field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFlip_currentScalar();

	/**
	 * Compensating field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getComp_current();	

	/**
	 * Compensating field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getComp_currentScalar();

	/**
	 * Guide field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGuide_current();	

	/**
	 * Guide field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getGuide_currentScalar();

	/**
	 * thickness along path of neutron travel
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThickness();	

	/**
	 * thickness along path of neutron travel
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getThicknessScalar();

}
