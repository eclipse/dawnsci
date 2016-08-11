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
 * A literature reference
 * Definition to include references for example for detectors,
 * manuals, instruments, acquisition or analysis software used.
 * The idea would be to include this in the relevant NeXus object:
 * :ref:`NXdetector` for detectors, :ref:`NXinstrument` for instruments, etc.
 * 
 * @version 1.0
 */
public class NXciteImpl extends NXobjectImpl implements NXcite {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXciteImpl() {
		super();
	}

	public NXciteImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcite.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_CITE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	@Override
	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getUrl() {
		return getDataset(NX_URL);
	}

	@Override
	public String getUrlScalar() {
		return getString(NX_URL);
	}

	@Override
	public DataNode setUrl(IDataset url) {
		return setDataset(NX_URL, url);
	}

	@Override
	public DataNode setUrlScalar(String url) {
		return setString(NX_URL, url);
	}

	@Override
	public IDataset getDoi() {
		return getDataset(NX_DOI);
	}

	@Override
	public String getDoiScalar() {
		return getString(NX_DOI);
	}

	@Override
	public DataNode setDoi(IDataset doi) {
		return setDataset(NX_DOI, doi);
	}

	@Override
	public DataNode setDoiScalar(String doi) {
		return setString(NX_DOI, doi);
	}

	@Override
	public IDataset getEndnote() {
		return getDataset(NX_ENDNOTE);
	}

	@Override
	public String getEndnoteScalar() {
		return getString(NX_ENDNOTE);
	}

	@Override
	public DataNode setEndnote(IDataset endnote) {
		return setDataset(NX_ENDNOTE, endnote);
	}

	@Override
	public DataNode setEndnoteScalar(String endnote) {
		return setString(NX_ENDNOTE, endnote);
	}

	@Override
	public IDataset getBibtex() {
		return getDataset(NX_BIBTEX);
	}

	@Override
	public String getBibtexScalar() {
		return getString(NX_BIBTEX);
	}

	@Override
	public DataNode setBibtex(IDataset bibtex) {
		return setDataset(NX_BIBTEX, bibtex);
	}

	@Override
	public DataNode setBibtexScalar(String bibtex) {
		return setString(NX_BIBTEX, bibtex);
	}

}
