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
 * An unvalidated set of terms, such as the description of a beam line.
 * Use :ref:`NXcollection` to gather together any set of terms.
 * The original suggestion is to use this as a container
 * class for the description of a beamline.
 * For NeXus validation, :ref:`NXcollection` will always generate
 * a warning since it is always an optional group.
 * Anything (groups, fields, or attributes) placed in
 * an :ref:`NXcollection` group will not be validated.
 * 
 * @version 1.0
 */
public class NXcollectionImpl extends NXobjectImpl implements NXcollection {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXcollectionImpl() {
		super();
	}

	public NXcollectionImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcollection.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_COLLECTION;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getBeamline() {
		return getDataset(NX_BEAMLINE);
	}

	@Override
	public String getBeamlineScalar() {
		return getString(NX_BEAMLINE);
	}

	@Override
	public DataNode setBeamline(IDataset beamline) {
		return setDataset(NX_BEAMLINE, beamline);
	}

	@Override
	public DataNode setBeamlineScalar(String beamline) {
		return setString(NX_BEAMLINE, beamline);
	}

}
