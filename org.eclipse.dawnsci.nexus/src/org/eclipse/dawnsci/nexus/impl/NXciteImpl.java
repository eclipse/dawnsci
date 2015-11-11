/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Definition to include references for example for detectors,
 * manuals, instruments, acquisition or analysis software used.
 * The idea would be to include this in the relevant NeXus object:
 * NXdetector for detectors, NXinstrument for instruments, etc
 * 
 * @version 1.0
 */
public class NXciteImpl extends NXobjectImpl implements NXcite {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_URL = "url";
	public static final String NX_DOI = "doi";
	public static final String NX_ENDNOTE = "endnote";
	public static final String NX_BIBTEX = "bibtex";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	protected NXciteImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXciteImpl(final long oid) {
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

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setDescriptionScalar(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getUrl() {
		return getDataset(NX_URL);
	}

	@Override
	public String getUrlScalar() {
		return getString(NX_URL);
	}

	public void setUrl(IDataset url) {
		setDataset(NX_URL, url);
	}

	public void setUrlScalar(String url) {
		setString(NX_URL, url);
	}

	@Override
	public IDataset getDoi() {
		return getDataset(NX_DOI);
	}

	@Override
	public String getDoiScalar() {
		return getString(NX_DOI);
	}

	public void setDoi(IDataset doi) {
		setDataset(NX_DOI, doi);
	}

	public void setDoiScalar(String doi) {
		setString(NX_DOI, doi);
	}

	@Override
	public IDataset getEndnote() {
		return getDataset(NX_ENDNOTE);
	}

	@Override
	public String getEndnoteScalar() {
		return getString(NX_ENDNOTE);
	}

	public void setEndnote(IDataset endnote) {
		setDataset(NX_ENDNOTE, endnote);
	}

	public void setEndnoteScalar(String endnote) {
		setString(NX_ENDNOTE, endnote);
	}

	@Override
	public IDataset getBibtex() {
		return getDataset(NX_BIBTEX);
	}

	@Override
	public String getBibtexScalar() {
		return getString(NX_BIBTEX);
	}

	public void setBibtex(IDataset bibtex) {
		setDataset(NX_BIBTEX, bibtex);
	}

	public void setBibtexScalar(String bibtex) {
		setString(NX_BIBTEX, bibtex);
	}

}
