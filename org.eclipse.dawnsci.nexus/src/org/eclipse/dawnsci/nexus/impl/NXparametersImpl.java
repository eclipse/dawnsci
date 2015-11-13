/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

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
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_PARAMETERS;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
