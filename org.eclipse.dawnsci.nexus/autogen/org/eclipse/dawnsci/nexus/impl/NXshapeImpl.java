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
 * legacy class - (used by :ref:`NXgeometry`) - the shape and size of a component.
 * This is the description of the general shape and size of a
 * component, which may be made up of ``numobj`` separate
 * elements - it is used by the :ref:`NXgeometry` class
 * 
 * @version 1.0
 */
public class NXshapeImpl extends NXobjectImpl implements NXshape {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXshapeImpl() {
		super();
	}

	public NXshapeImpl(final long oid) {
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

	@Override
	public DataNode setShape(IDataset shape) {
		return setDataset(NX_SHAPE, shape);
	}

	@Override
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

	@Override
	public DataNode setSize(IDataset size) {
		return setDataset(NX_SIZE, size);
	}

	@Override
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

	@Override
	public DataNode setDirection(IDataset direction) {
		return setDataset(NX_DIRECTION, direction);
	}

	@Override
	public DataNode setDirectionScalar(String direction) {
		return setString(NX_DIRECTION, direction);
	}

}
