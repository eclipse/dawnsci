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

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * Document an event of data processing, reconstruction, or analysis for this data.
 * 
 * @version 1.0
 */
public interface NXprocess extends NXobject {

	public static final String NX_PROGRAM = "program";
	public static final String NX_SEQUENCE_INDEX = "sequence_index";
	public static final String NX_VERSION = "version";
	public static final String NX_DATE = "date";
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
	 * Name of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param program the program
	 */
	public DataNode setProgram(IDataset program);

	/**
	 * Name of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getProgramScalar();

	/**
	 * Name of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param program the program
	 */
	public DataNode setProgramScalar(String program);

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
	 * Sequence index of processing,
	 * for determining the order of multiple **NXprocess** steps.
	 * Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @param sequence_index the sequence_index
	 */
	public DataNode setSequence_index(IDataset sequence_index);

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
	public long getSequence_indexScalar();

	/**
	 * Sequence index of processing,
	 * for determining the order of multiple **NXprocess** steps.
	 * Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @param sequence_index the sequence_index
	 */
	public DataNode setSequence_indexScalar(long sequence_index);

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
	 * Version of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param version the version
	 */
	public DataNode setVersion(IDataset version);

	/**
	 * Version of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getVersionScalar();

	/**
	 * Version of the program used
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param version the version
	 */
	public DataNode setVersionScalar(String version);

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
	 * Date and time of processing.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param date the date
	 */
	public DataNode setDate(IDataset date);

	/**
	 * Date and time of processing.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getDateScalar();

	/**
	 * Date and time of processing.
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param date the date
	 */
	public DataNode setDateScalar(Date date);

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
	 * The note will contain information about how the data was processed
	 * or anything about the data provenance.
	 * The contents of the note can be anything that the processing code
	 * can understand, or simple text.
	 * The name will be numbered to allow for ordering of steps.
	 * 
	 * @param note the note
	 */
	public void setNote(NXnote note);
  
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
	 * Set a NXnote node by name:
	 * <ul>
	 * <li>
	 * The note will contain information about how the data was processed
	 * or anything about the data provenance.
	 * The contents of the note can be anything that the processing code
	 * can understand, or simple text.
	 * The name will be numbered to allow for ordering of steps.</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param note the value to set
	 */
	public void setNote(String name, NXnote note);
	
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
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * The note will contain information about how the data was processed
	 * or anything about the data provenance.
	 * The contents of the note can be anything that the processing code
	 * can understand, or simple text.
	 * The name will be numbered to allow for ordering of steps.</li>
	 * </ul>
	 * 
	 * @param note the child nodes to add 
	 */
	
	public void setAllNote(Map<String, NXnote> note);
	

}
