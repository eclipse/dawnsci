/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-13T13:58:10.369+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of a beamline spin polarizer.
 * This is a draft and is subject to revision.
 * 
 * @version 1.0
 */
public interface NXpolarizer extends NXobject {

	/**
	 * one of these values: "crystal", "supermirror", "3He"
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * one of these values: "crystal", "supermirror", "3He"
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * description of the composition of the polarizing material
	 * 
	 * @return  the value.
	 */
	public IDataset getComposition();	

	/**
	 * description of the composition of the polarizing material
	 * 
	 * @return  the value
	 */
	 public String getCompositionScalar();

	/**
	 * [hkl] values of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getReflection();	

	/**
	 * [hkl] values of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getReflectionScalar();

	/**
	 * polarizing efficiency
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEfficiency();	

	/**
	 * polarizing efficiency
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getEfficiencyScalar();

}
