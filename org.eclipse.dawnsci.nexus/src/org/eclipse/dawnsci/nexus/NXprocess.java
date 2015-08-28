/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Document an event of data processing, reconstruction, or analysis for this data.
 * 
 * @version 1.0
 */
public interface NXprocess extends NXobject {

	/**
	 * Name of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getProgram();

	/**
	 * Sequence index of processing,
	 * for determining the order of multiple **NXprocess** steps.
	 * Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSequence_index();

	/**
	 * Version of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getVersion();

	/**
	 * Date and time of processing.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDate();

	/**
	 * The note will contain information about how the data was processed
	 * or anything about the data provenance.
	 * The contents of the note can be anything that the processing code
	 * can understand, or simple text.
	 * The name will be numbered to allow for ordering of steps.
	 * 
	 * @return  the value.
	 */
	public NXnote getNote();
  
	/**
	 * Get a NXnote node by name:
	 * <ul>
	 * <li>
	 * The note will contain information about how the data was processed
	 * or anything about the data provenance.
	 * The contents of the note can be anything that the processing code
	 * can understand, or simple text.
	 * The name will be numbered to allow for ordering of steps.</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXnote for that node.
	 */
	public NXnote getNote(String name);
	
	/**
	 * Get all NXnote nodes:
	 * <ul>
	 * <li>
	 * The note will contain information about how the data was processed
	 * or anything about the data provenance.
	 * The contents of the note can be anything that the processing code
	 * can understand, or simple text.
	 * The name will be numbered to allow for ordering of steps.</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXnote for that node.
	 */
	public Map<String, NXnote> getAllNote();

}
