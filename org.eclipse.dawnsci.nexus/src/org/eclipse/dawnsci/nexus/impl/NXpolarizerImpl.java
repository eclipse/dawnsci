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

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a beamline spin polarizer.
 * This is a draft and is subject to revision.
 * 
 * @version 1.0
 */
public class NXpolarizerImpl extends NXobjectImpl implements NXpolarizer {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_COMPOSITION = "composition";
	public static final String NX_REFLECTION = "reflection";
	public static final String NX_EFFICIENCY = "efficiency";

	protected NXpolarizerImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXpolarizer.class;
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getComposition() {
		return getDataset(NX_COMPOSITION);
	}

	public void setComposition(IDataset composition) {
		setDataset(NX_COMPOSITION, composition);
	}

	@Override
	public IDataset getReflection() {
		return getDataset(NX_REFLECTION);
	}

	public void setReflection(IDataset reflection) {
		setDataset(NX_REFLECTION, reflection);
	}

	@Override
	public IDataset getEfficiency() {
		return getDataset(NX_EFFICIENCY);
	}

	public void setEfficiency(IDataset efficiency) {
		setDataset(NX_EFFICIENCY, efficiency);
	}

}
