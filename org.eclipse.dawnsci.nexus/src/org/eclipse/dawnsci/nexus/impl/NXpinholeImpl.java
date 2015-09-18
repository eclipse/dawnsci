/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a simple pinhole.
 * For more complex geometries NXaperture should be used.
 * 
 * @version 1.0
 */
public class NXpinholeImpl extends NXobjectImpl implements NXpinhole {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DEPENDS_ON = "depends_on";
	public static final String NX_DIAMETER = "diameter";

	protected NXpinholeImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXpinhole.class;
	}

	@Override
	public IDataset getDepends_on() {
		return getDataset(NX_DEPENDS_ON);
	}

	public void setDepends_on(IDataset depends_on) {
		setDataset(NX_DEPENDS_ON, depends_on);
	}

	@Override
	public IDataset getDiameter() {
		return getDataset(NX_DIAMETER);
	}

	public void setDiameter(IDataset diameter) {
		setDataset(NX_DIAMETER, diameter);
	}

}
