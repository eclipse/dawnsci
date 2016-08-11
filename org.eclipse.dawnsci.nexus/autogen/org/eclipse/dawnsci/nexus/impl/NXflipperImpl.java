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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * A spin flipper.
 * 
 * @version 1.0
 */
public class NXflipperImpl extends NXobjectImpl implements NXflipper {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXflipperImpl() {
		super();
	}

	public NXflipperImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXflipper.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_FLIPPER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	@Override
	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	@Override
	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
	}

	@Override
	public IDataset getFlip_turns() {
		return getDataset(NX_FLIP_TURNS);
	}

	@Override
	public double getFlip_turnsScalar() {
		return getDouble(NX_FLIP_TURNS);
	}

	@Override
	public DataNode setFlip_turns(IDataset flip_turns) {
		return setDataset(NX_FLIP_TURNS, flip_turns);
	}

	@Override
	public DataNode setFlip_turnsScalar(double flip_turns) {
		return setField(NX_FLIP_TURNS, flip_turns);
	}

	@Override
	public IDataset getComp_turns() {
		return getDataset(NX_COMP_TURNS);
	}

	@Override
	public double getComp_turnsScalar() {
		return getDouble(NX_COMP_TURNS);
	}

	@Override
	public DataNode setComp_turns(IDataset comp_turns) {
		return setDataset(NX_COMP_TURNS, comp_turns);
	}

	@Override
	public DataNode setComp_turnsScalar(double comp_turns) {
		return setField(NX_COMP_TURNS, comp_turns);
	}

	@Override
	public IDataset getGuide_turns() {
		return getDataset(NX_GUIDE_TURNS);
	}

	@Override
	public double getGuide_turnsScalar() {
		return getDouble(NX_GUIDE_TURNS);
	}

	@Override
	public DataNode setGuide_turns(IDataset guide_turns) {
		return setDataset(NX_GUIDE_TURNS, guide_turns);
	}

	@Override
	public DataNode setGuide_turnsScalar(double guide_turns) {
		return setField(NX_GUIDE_TURNS, guide_turns);
	}

	@Override
	public IDataset getFlip_current() {
		return getDataset(NX_FLIP_CURRENT);
	}

	@Override
	public double getFlip_currentScalar() {
		return getDouble(NX_FLIP_CURRENT);
	}

	@Override
	public DataNode setFlip_current(IDataset flip_current) {
		return setDataset(NX_FLIP_CURRENT, flip_current);
	}

	@Override
	public DataNode setFlip_currentScalar(double flip_current) {
		return setField(NX_FLIP_CURRENT, flip_current);
	}

	@Override
	public IDataset getComp_current() {
		return getDataset(NX_COMP_CURRENT);
	}

	@Override
	public double getComp_currentScalar() {
		return getDouble(NX_COMP_CURRENT);
	}

	@Override
	public DataNode setComp_current(IDataset comp_current) {
		return setDataset(NX_COMP_CURRENT, comp_current);
	}

	@Override
	public DataNode setComp_currentScalar(double comp_current) {
		return setField(NX_COMP_CURRENT, comp_current);
	}

	@Override
	public IDataset getGuide_current() {
		return getDataset(NX_GUIDE_CURRENT);
	}

	@Override
	public double getGuide_currentScalar() {
		return getDouble(NX_GUIDE_CURRENT);
	}

	@Override
	public DataNode setGuide_current(IDataset guide_current) {
		return setDataset(NX_GUIDE_CURRENT, guide_current);
	}

	@Override
	public DataNode setGuide_currentScalar(double guide_current) {
		return setField(NX_GUIDE_CURRENT, guide_current);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	@Override
	public double getThicknessScalar() {
		return getDouble(NX_THICKNESS);
	}

	@Override
	public DataNode setThickness(IDataset thickness) {
		return setDataset(NX_THICKNESS, thickness);
	}

	@Override
	public DataNode setThicknessScalar(double thickness) {
		return setField(NX_THICKNESS, thickness);
	}

}
