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
 * Use ``NXcollection`` to gather together any set of terms.
 * The original suggestion is to use this as a container
 * class for the description of a beamline.
 * For NeXus validation, ``NXcollection`` will always generate
 * a warning since it is always an optional group.
 * Anything (groups, fields, or attributes) placed in
 * an ``NXcollection`` group will not be validated.
 * 
 * @version 1.0
 */
public class NXcollectionImpl extends NXobjectImpl implements NXcollection {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_BEAMLINE = "beamline";

	protected NXcollectionImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXcollectionImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcollection.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_COLLECTION;
	}

	@Override
	public IDataset getBeamline() {
		return getDataset(NX_BEAMLINE);
	}

	@Override
	public String getBeamlineScalar() {
		return getString(NX_BEAMLINE);
	}

	public void setBeamline(IDataset beamline) {
		setDataset(NX_BEAMLINE, beamline);
	}

	public void setBeamlineScalar(String beamline) {
		setString(NX_BEAMLINE, beamline);
	}

}
