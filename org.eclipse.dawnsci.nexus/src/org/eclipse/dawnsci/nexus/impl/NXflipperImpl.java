/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a beamline spin flipper.
 * 
 * @version 1.0
 */
public class NXflipperImpl extends NXobjectImpl implements NXflipper {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_FLIP_TURNS = "flip_turns";
	public static final String NX_COMP_TURNS = "comp_turns";
	public static final String NX_GUIDE_TURNS = "guide_turns";
	public static final String NX_FLIP_CURRENT = "flip_current";
	public static final String NX_COMP_CURRENT = "comp_current";
	public static final String NX_GUIDE_CURRENT = "guide_current";
	public static final String NX_THICKNESS = "thickness";

	protected NXflipperImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXflipper.class;
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getFlip_turns() {
		return getDataset(NX_FLIP_TURNS);
	}

	public void setFlip_turns(IDataset flip_turns) {
		setDataset(NX_FLIP_TURNS, flip_turns);
	}

	@Override
	public IDataset getComp_turns() {
		return getDataset(NX_COMP_TURNS);
	}

	public void setComp_turns(IDataset comp_turns) {
		setDataset(NX_COMP_TURNS, comp_turns);
	}

	@Override
	public IDataset getGuide_turns() {
		return getDataset(NX_GUIDE_TURNS);
	}

	public void setGuide_turns(IDataset guide_turns) {
		setDataset(NX_GUIDE_TURNS, guide_turns);
	}

	@Override
	public IDataset getFlip_current() {
		return getDataset(NX_FLIP_CURRENT);
	}

	public void setFlip_current(IDataset flip_current) {
		setDataset(NX_FLIP_CURRENT, flip_current);
	}

	@Override
	public IDataset getComp_current() {
		return getDataset(NX_COMP_CURRENT);
	}

	public void setComp_current(IDataset comp_current) {
		setDataset(NX_COMP_CURRENT, comp_current);
	}

	@Override
	public IDataset getGuide_current() {
		return getDataset(NX_GUIDE_CURRENT);
	}

	public void setGuide_current(IDataset guide_current) {
		setDataset(NX_GUIDE_CURRENT, guide_current);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	public void setThickness(IDataset thickness) {
		setDataset(NX_THICKNESS, thickness);
	}

}
