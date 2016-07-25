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
 * A spin flipper.
 * 
 * @version 1.0
 */
public interface NXflipper extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_FLIP_TURNS = "flip_turns";
	public static final String NX_COMP_TURNS = "comp_turns";
	public static final String NX_GUIDE_TURNS = "guide_turns";
	public static final String NX_FLIP_CURRENT = "flip_current";
	public static final String NX_COMP_CURRENT = "comp_current";
	public static final String NX_GUIDE_CURRENT = "guide_current";
	public static final String NX_THICKNESS = "thickness";
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
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>coil</b> </li>
	 * <li><b>current-sheet</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>coil</b> </li>
	 * <li><b>current-sheet</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

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
	 * @param flip_turns the flip_turns
	 */
	public DataNode setFlip_turns(IDataset flip_turns);

	/**
	 * Linear density of turns (such as number of turns/cm) in flipping field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFlip_turnsScalar();

	/**
	 * Linear density of turns (such as number of turns/cm) in flipping field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @param flip_turns the flip_turns
	 */
	public DataNode setFlip_turnsScalar(double flip_turns);

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
	 * @param comp_turns the comp_turns
	 */
	public DataNode setComp_turns(IDataset comp_turns);

	/**
	 * Linear density of turns (such as number of turns/cm) in compensating field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getComp_turnsScalar();

	/**
	 * Linear density of turns (such as number of turns/cm) in compensating field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @param comp_turns the comp_turns
	 */
	public DataNode setComp_turnsScalar(double comp_turns);

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
	 * @param guide_turns the guide_turns
	 */
	public DataNode setGuide_turns(IDataset guide_turns);

	/**
	 * Linear density of turns (such as number of turns/cm) in guide field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getGuide_turnsScalar();

	/**
	 * Linear density of turns (such as number of turns/cm) in guide field coils
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PER_LENGTH
	 * </p>
	 * 
	 * @param guide_turns the guide_turns
	 */
	public DataNode setGuide_turnsScalar(double guide_turns);

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
	 * @param flip_current the flip_current
	 */
	public DataNode setFlip_current(IDataset flip_current);

	/**
	 * Flipping field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFlip_currentScalar();

	/**
	 * Flipping field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param flip_current the flip_current
	 */
	public DataNode setFlip_currentScalar(double flip_current);

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
	 * @param comp_current the comp_current
	 */
	public DataNode setComp_current(IDataset comp_current);

	/**
	 * Compensating field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getComp_currentScalar();

	/**
	 * Compensating field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param comp_current the comp_current
	 */
	public DataNode setComp_currentScalar(double comp_current);

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
	 * @param guide_current the guide_current
	 */
	public DataNode setGuide_current(IDataset guide_current);

	/**
	 * Guide field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getGuide_currentScalar();

	/**
	 * Guide field coil current in "on" state"
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param guide_current the guide_current
	 */
	public DataNode setGuide_currentScalar(double guide_current);

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
	 * @param thickness the thickness
	 */
	public DataNode setThickness(IDataset thickness);

	/**
	 * thickness along path of neutron travel
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getThicknessScalar();

	/**
	 * thickness along path of neutron travel
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThicknessScalar(double thickness);

}
