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

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a simple slit.
 * For more complex geometries NXaperture should be used.
 * 
 * @version 1.0
 */
public class NXslitImpl extends NXobjectImpl implements NXslit {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DEPENDS_ON = "depends_on";
	public static final String NX_X_GAP = "x_gap";
	public static final String NX_Y_GAP = "y_gap";

	protected NXslitImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXslitImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXslit.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_SLIT;
	}

	@Override
	public IDataset getDepends_on() {
		return getDataset(NX_DEPENDS_ON);
	}

	@Override
	public String getDepends_onScalar() {
		return getString(NX_DEPENDS_ON);
	}

	public void setDepends_on(IDataset depends_on) {
		setDataset(NX_DEPENDS_ON, depends_on);
	}

	public void setDepends_onScalar(String depends_on) {
		setString(NX_DEPENDS_ON, depends_on);
	}

	@Override
	public IDataset getX_gap() {
		return getDataset(NX_X_GAP);
	}

	@Override
	public Number getX_gapScalar() {
		return getNumber(NX_X_GAP);
	}

	public void setX_gap(IDataset x_gap) {
		setDataset(NX_X_GAP, x_gap);
	}

	public void setX_gapScalar(Number x_gap) {
		setField(NX_X_GAP, x_gap);
	}

	@Override
	public IDataset getY_gap() {
		return getDataset(NX_Y_GAP);
	}

	@Override
	public Number getY_gapScalar() {
		return getNumber(NX_Y_GAP);
	}

	public void setY_gap(IDataset y_gap) {
		setDataset(NX_Y_GAP, y_gap);
	}

	public void setY_gapScalar(Number y_gap) {
		setField(NX_Y_GAP, y_gap);
	}

}
