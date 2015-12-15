/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;

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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	protected NXshapeImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXshapeImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXshape.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SHAPE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getShape() {
		return getDataset(NX_SHAPE);
	}

	@Override
	public String getShapeScalar() {
		return getString(NX_SHAPE);
	}

	public DataNode setShape(IDataset shape) {
		return setDataset(NX_SHAPE, shape);
	}

	public DataNode setShapeScalar(String shape) {
		return setString(NX_SHAPE, shape);
	}

	@Override
	public IDataset getSize() {
		return getDataset(NX_SIZE);
	}

	@Override
	public double getSizeScalar() {
		return getDouble(NX_SIZE);
	}

	public DataNode setSize(IDataset size) {
		return setDataset(NX_SIZE, size);
	}

	public DataNode setSizeScalar(double size) {
		return setField(NX_SIZE, size);
	}

	@Override
	public IDataset getDirection() {
		return getDataset(NX_DIRECTION);
	}

	@Override
	public String getDirectionScalar() {
		return getString(NX_DIRECTION);
	}

	public DataNode setDirection(IDataset direction) {
		return setDataset(NX_DIRECTION, direction);
	}

	public DataNode setDirectionScalar(String direction) {
		return setString(NX_DIRECTION, direction);
	}

}
