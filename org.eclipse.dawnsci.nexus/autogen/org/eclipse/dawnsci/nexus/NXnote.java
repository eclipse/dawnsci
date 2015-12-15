/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;

/**
 * This class can be used to store additional information in a
 * NeXus file e.g. pictures, movies, audio, additional text logs
 * 
 * @version 1.0
 */
public interface NXnote extends NXobject {

	/**
	 * Author or creator of note
	 * 
	 * @return  the value.
	 */
	public IDataset getAuthor();	

	/**
	 * Author or creator of note
	 * 
	 * @return  the value
	 */
	 public String getAuthorScalar();

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
	 * @return  the value
	 */
	 public Date getDateScalar();

	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @return  the value.
	 */
	public IDataset getFile_name();	

	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @return  the value
	 */
	 public String getFile_nameScalar();

	/**
	 * Title of an image or other details of the note
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();	

	/**
	 * Title of an image or other details of the note
	 * 
	 * @return  the value
	 */
	 public String getDescriptionScalar();

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
	 * @return  the value
	 */
	 public long getSequence_indexScalar();

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
	 * @return  the value
	 */
	 public Object getDataScalar();

}
