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

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
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
public interface NXcite extends NXobject {

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_URL = "url";
	public static final String NX_DOI = "doi";
	public static final String NX_ENDNOTE = "endnote";
	public static final String NX_BIBTEX = "bibtex";
	/**
	 * This should describe the reason for including this reference.
	 * For example: The dataset in this group was normalised using the method
	 * which is described in detail in this reference.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * This should describe the reason for including this reference.
	 * For example: The dataset in this group was normalised using the method
	 * which is described in detail in this reference.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * This should describe the reason for including this reference.
	 * For example: The dataset in this group was normalised using the method
	 * which is described in detail in this reference.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * This should describe the reason for including this reference.
	 * For example: The dataset in this group was normalised using the method
	 * which is described in detail in this reference.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * URL referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUrl();
	
	/**
	 * URL referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param url the url
	 */
	public DataNode setUrl(IDataset url);

	/**
	 * URL referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getUrlScalar();

	/**
	 * URL referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param url the url
	 */
	public DataNode setUrlScalar(String url);

	/**
	 * DOI referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDoi();
	
	/**
	 * DOI referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param doi the doi
	 */
	public DataNode setDoi(IDataset doi);

	/**
	 * DOI referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getDoiScalar();

	/**
	 * DOI referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param doi the doi
	 */
	public DataNode setDoiScalar(String doi);

	/**
	 * Bibliographic reference data in EndNote format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEndnote();
	
	/**
	 * Bibliographic reference data in EndNote format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param endnote the endnote
	 */
	public DataNode setEndnote(IDataset endnote);

	/**
	 * Bibliographic reference data in EndNote format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getEndnoteScalar();

	/**
	 * Bibliographic reference data in EndNote format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param endnote the endnote
	 */
	public DataNode setEndnoteScalar(String endnote);

	/**
	 * Bibliographic reference data in BibTeX format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBibtex();
	
	/**
	 * Bibliographic reference data in BibTeX format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param bibtex the bibtex
	 */
	public DataNode setBibtex(IDataset bibtex);

	/**
	 * Bibliographic reference data in BibTeX format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getBibtexScalar();

	/**
	 * Bibliographic reference data in BibTeX format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param bibtex the bibtex
	 */
	public DataNode setBibtexScalar(String bibtex);

}
