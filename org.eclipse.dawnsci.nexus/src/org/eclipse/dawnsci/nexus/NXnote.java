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
	 * Date note created/added
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDate();

	/**
	 * Mime content type of note data field e.g. image/jpeg, text/plain, text/html
	 * 
	 * @return  the value.
	 */
	public IDataset getType();

	/**
	 * Name of original file name if note was read from an external source
	 * 
	 * @return  the value.
	 */
	public IDataset getFile_name();

	/**
	 * Title of an image or other details of the note
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

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
	 * Binary note data - if text, line terminator is [CR][LF].
	 * <p>
	 * <b>Type:</b> NX_BINARY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData();

}
