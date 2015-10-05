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

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Definition to include references for example for detectors,
 * manuals, instruments, acquisition or analysis software used.
 * The idea would be to include this in the relevant NeXus object:
 * NXdetector for detectors, NXinstrument for instruments, etc
 * 
 * @version 1.0
 */
public interface NXcite extends NXobject {

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
	 * URL referencing the document or data.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUrl();

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
	 * Bibliographic reference data in EndNote format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEndnote();

	/**
	 * Bibliographic reference data in BibTeX format.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBibtex();

}
