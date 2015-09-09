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
	 * thickness along path of neutron travel
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThickness();

}
