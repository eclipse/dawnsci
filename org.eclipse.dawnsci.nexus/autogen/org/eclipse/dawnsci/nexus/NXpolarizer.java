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
 * A spin polarizer.
 * 
 * @version 1.0
 */
public interface NXpolarizer extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_COMPOSITION = "composition";
	public static final String NX_REFLECTION = "reflection";
	public static final String NX_EFFICIENCY = "efficiency";
	/**
	 * one of these values: "crystal", "supermirror", "3He"
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * one of these values: "crystal", "supermirror", "3He"
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * one of these values: "crystal", "supermirror", "3He"
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * one of these values: "crystal", "supermirror", "3He"
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * description of the composition of the polarizing material
	 * 
	 * @return  the value.
	 */
	public IDataset getComposition();
	
	/**
	 * description of the composition of the polarizing material
	 * 
	 * @param composition the composition
	 */
	public DataNode setComposition(IDataset composition);

	/**
	 * description of the composition of the polarizing material
	 * 
	 * @return  the value.
	 */
	public String getCompositionScalar();

	/**
	 * description of the composition of the polarizing material
	 * 
	 * @param composition the composition
	 */
	public DataNode setCompositionScalar(String composition);

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
	 * @param reflection the reflection
	 */
	public DataNode setReflection(IDataset reflection);

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
	public long getReflectionScalar();

	/**
	 * [hkl] values of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param reflection the reflection
	 */
	public DataNode setReflectionScalar(long reflection);

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
	 * @param efficiency the efficiency
	 */
	public DataNode setEfficiency(IDataset efficiency);

	/**
	 * polarizing efficiency
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEfficiencyScalar();

	/**
	 * polarizing efficiency
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @param efficiency the efficiency
	 */
	public DataNode setEfficiencyScalar(double efficiency);

}
