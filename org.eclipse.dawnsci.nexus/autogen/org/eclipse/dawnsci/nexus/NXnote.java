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

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;

/**
 * Any additional freeform information not covered by the other base classes.
 * This class can be used to store additional information in a
 * NeXus file e.g. pictures, movies, audio, additional text logs
 * 
 * @version 1.0
 */
public interface NXnote extends NXobject {

	public static final String NX_AUTHOR = "author";
	public static final String NX_DATE = "date";
	public static final String NX_TYPE = "type";
	public static final String NX_FILE_NAME = "file_name";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_SEQUENCE_INDEX = "sequence_index";
	public static final String NX_DATA = "data";
	/**
	 * Author or creator of note
	 * 
	 * @return  the value.
	 */
	public IDataset getAuthor();
	
	/**
	 * Author or creator of note
	 * 
	 * @param author the author
	 */
	public DataNode setAuthor(IDataset author);

	/**
	 * Author or creator of note
	 * 
	 * @return  the value.
	 */
	public String getAuthorScalar();

	/**
	 * Author or creator of note
	 * 
	 * @param author the author
	 */
	public DataNode setAuthorScalar(String author);

	/**
	 * Date note created/added
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDate();
	
	/**
	 * Date note created/added
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param date the date
	 */
	public DataNode setDate(IDataset date);

	/**
	 * Date note created/added
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getDateScalar();

	/**
	 * Date note created/added
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param date the date
	 */
	public DataNode setDateScalar(Date date);

	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @return  the value.
	 */
	public IDataset getFile_name();
	
	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @param file_name the file_name
	 */
	public DataNode setFile_name(IDataset file_name);

	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @return  the value.
	 */
	public String getFile_nameScalar();

	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @param file_name the file_name
	 */
	public DataNode setFile_nameScalar(String file_name);

	/**
	 * Title of an image or other details of the note
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * Title of an image or other details of the note
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * Title of an image or other details of the note
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * Title of an image or other details of the note
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * Sequence index of note, for placing a sequence of
	 * multiple **NXnote** groups in an order. Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSequence_index();
	
	/**
	 * Sequence index of note, for placing a sequence of
	 * multiple **NXnote** groups in an order. Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @param sequence_index the sequence_index
	 */
	public DataNode setSequence_index(IDataset sequence_index);

	/**
	 * Sequence index of note, for placing a sequence of
	 * multiple **NXnote** groups in an order. Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getSequence_indexScalar();

	/**
	 * Sequence index of note, for placing a sequence of
	 * multiple **NXnote** groups in an order. Starts with 1.
	 * <p>
	 * <b>Type:</b> NX_POSINT
	 * </p>
	 * 
	 * @param sequence_index the sequence_index
	 */
	public DataNode setSequence_indexScalar(long sequence_index);

	/**
	 * Binary note data - if text, line terminator is [CR][LF].
	 * <p>
	 * <b>Type:</b> NX_BINARY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData();
	
	/**
	 * Binary note data - if text, line terminator is [CR][LF].
	 * <p>
	 * <b>Type:</b> NX_BINARY
	 * </p>
	 * 
	 * @param data the data
	 */
	public DataNode setData(IDataset data);

	/**
	 * Binary note data - if text, line terminator is [CR][LF].
	 * <p>
	 * <b>Type:</b> NX_BINARY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Object getDataScalar();

	/**
	 * Binary note data - if text, line terminator is [CR][LF].
	 * <p>
	 * <b>Type:</b> NX_BINARY
	 * </p>
	 * 
	 * @param data the data
	 */
	public DataNode setDataScalar(Object data);

}
