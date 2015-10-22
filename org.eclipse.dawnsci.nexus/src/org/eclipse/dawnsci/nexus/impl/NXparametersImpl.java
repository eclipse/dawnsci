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
 * Container for parameters, usually used in processing or analysis.
 * 
 * @version 1.0
 */
public class NXparametersImpl extends NXobjectImpl implements NXparameters {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TERM = "term";
	public static final String NX_TERM_ATTRIBUTE_UNITS = "units";

	protected NXparametersImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXparametersImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXparameters.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_PARAMETERS;
	}

	@Override
	public IDataset getTerm() {
		return getDataset(NX_TERM);
	}

	@Override
	public String getTermScalar() {
		return getString(NX_TERM);
	}

	public void setTerm(IDataset term) {
		setDataset(NX_TERM, term);
	}

	public void setTermScalar(String term) {
		setString(NX_TERM, term);
	}

	@Override
	public String getTermAttributeUnits() {
		return getAttrString(NX_TERM, NX_TERM_ATTRIBUTE_UNITS);
	}

	public void setTermAttributeUnits(String units) {
		setAttribute(NX_TERM, NX_TERM_ATTRIBUTE_UNITS, units);
	}

}
