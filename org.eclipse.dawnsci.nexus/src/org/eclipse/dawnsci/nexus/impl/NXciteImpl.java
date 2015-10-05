/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

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

	protected NXciteImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcite.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_CITE;
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getUrl() {
		return getDataset(NX_URL);
	}

	public void setUrl(IDataset url) {
		setDataset(NX_URL, url);
	}

	@Override
	public IDataset getDoi() {
		return getDataset(NX_DOI);
	}

	public void setDoi(IDataset doi) {
		setDataset(NX_DOI, doi);
	}

	@Override
	public IDataset getEndnote() {
		return getDataset(NX_ENDNOTE);
	}

	public void setEndnote(IDataset endnote) {
		setDataset(NX_ENDNOTE, endnote);
	}

	@Override
	public IDataset getBibtex() {
		return getDataset(NX_BIBTEX);
	}

	public void setBibtex(IDataset bibtex) {
		setDataset(NX_BIBTEX, bibtex);
	}

}
