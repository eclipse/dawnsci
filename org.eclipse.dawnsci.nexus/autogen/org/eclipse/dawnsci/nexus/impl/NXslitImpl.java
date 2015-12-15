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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

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
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SLIT;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getDepends_on() {
		return getDataset(NX_DEPENDS_ON);
	}

	@Override
	public String getDepends_onScalar() {
		return getString(NX_DEPENDS_ON);
	}

	public DataNode setDepends_on(IDataset depends_on) {
		return setDataset(NX_DEPENDS_ON, depends_on);
	}

	public DataNode setDepends_onScalar(String depends_on) {
		return setString(NX_DEPENDS_ON, depends_on);
	}

	@Override
	public IDataset getX_gap() {
		return getDataset(NX_X_GAP);
	}

	@Override
	public Number getX_gapScalar() {
		return getNumber(NX_X_GAP);
	}

	public DataNode setX_gap(IDataset x_gap) {
		return setDataset(NX_X_GAP, x_gap);
	}

	public DataNode setX_gapScalar(Number x_gap) {
		return setField(NX_X_GAP, x_gap);
	}

	@Override
	public IDataset getY_gap() {
		return getDataset(NX_Y_GAP);
	}

	@Override
	public Number getY_gapScalar() {
		return getNumber(NX_Y_GAP);
	}

	public DataNode setY_gap(IDataset y_gap) {
		return setDataset(NX_Y_GAP, y_gap);
	}

	public DataNode setY_gapScalar(Number y_gap) {
		return setField(NX_Y_GAP, y_gap);
	}

}
