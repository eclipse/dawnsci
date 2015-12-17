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
 * Template of a simple pinhole.
 * For more complex geometries NXaperture should be used.
 * 
 * @version 1.0
 */
public class NXpinholeImpl extends NXobjectImpl implements NXpinhole {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DEPENDS_ON = "depends_on";
	public static final String NX_DIAMETER = "diameter";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	protected NXpinholeImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXpinholeImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXpinhole.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_PINHOLE;
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
	public IDataset getDiameter() {
		return getDataset(NX_DIAMETER);
	}

	@Override
	public Number getDiameterScalar() {
		return getNumber(NX_DIAMETER);
	}

	public DataNode setDiameter(IDataset diameter) {
		return setDataset(NX_DIAMETER, diameter);
	}

	public DataNode setDiameterScalar(Number diameter) {
		return setField(NX_DIAMETER, diameter);
	}

}
