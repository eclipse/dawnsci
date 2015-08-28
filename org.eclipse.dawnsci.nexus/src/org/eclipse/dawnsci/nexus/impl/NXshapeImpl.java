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
 * This is the description of the general shape and size of a
 * component, which may be made up of ``numobj`` separate
 * elements - it is used by the :ref:`NXgeometry` class
 * 
 * @version 1.0
 */
public class NXshapeImpl extends NXobjectImpl implements NXshape {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_SHAPE = "shape";
	public static final String NX_SIZE = "size";
	public static final String NX_DIRECTION = "direction";

	protected NXshapeImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXshape.class;
	}

	@Override
	public IDataset getShape() {
		return getDataset(NX_SHAPE);
	}

	public void setShape(IDataset shape) {
		setDataset(NX_SHAPE, shape);
	}

	@Override
	public IDataset getSize() {
		return getDataset(NX_SIZE);
	}

	public void setSize(IDataset size) {
		setDataset(NX_SIZE, size);
	}

	@Override
	public IDataset getDirection() {
		return getDataset(NX_DIRECTION);
	}

	public void setDirection(IDataset direction) {
		setDataset(NX_DIRECTION, direction);
	}

}
