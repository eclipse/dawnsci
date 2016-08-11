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
 * A spin polarizer.
 * 
 * @version 1.0
 */
public class NXpolarizerImpl extends NXobjectImpl implements NXpolarizer {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXpolarizerImpl() {
		super();
	}

	public NXpolarizerImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXpolarizer.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_POLARIZER;
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
	public IDataset getComposition() {
		return getDataset(NX_COMPOSITION);
	}

	@Override
	public String getCompositionScalar() {
		return getString(NX_COMPOSITION);
	}

	@Override
	public DataNode setComposition(IDataset composition) {
		return setDataset(NX_COMPOSITION, composition);
	}

	@Override
	public DataNode setCompositionScalar(String composition) {
		return setString(NX_COMPOSITION, composition);
	}

	@Override
	public IDataset getReflection() {
		return getDataset(NX_REFLECTION);
	}

	@Override
	public long getReflectionScalar() {
		return getLong(NX_REFLECTION);
	}

	@Override
	public DataNode setReflection(IDataset reflection) {
		return setDataset(NX_REFLECTION, reflection);
	}

	@Override
	public DataNode setReflectionScalar(long reflection) {
		return setField(NX_REFLECTION, reflection);
	}

	@Override
	public IDataset getEfficiency() {
		return getDataset(NX_EFFICIENCY);
	}

	@Override
	public double getEfficiencyScalar() {
		return getDouble(NX_EFFICIENCY);
	}

	@Override
	public DataNode setEfficiency(IDataset efficiency) {
		return setDataset(NX_EFFICIENCY, efficiency);
	}

	@Override
	public DataNode setEfficiencyScalar(double efficiency) {
		return setField(NX_EFFICIENCY, efficiency);
	}

}
