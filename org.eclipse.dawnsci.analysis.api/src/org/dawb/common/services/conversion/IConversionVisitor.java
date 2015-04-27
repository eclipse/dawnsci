/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.dawb.common.services.conversion;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Optionally instead of a conversion scheme, a separate visitor
 * may be defined. This is called instead of the conversion scheme,
 * and overides it.
 */
public interface IConversionVisitor {
	
	/**
	 * A name that might be shown to the user, describing this conversion.
	 * @return String scheme name
	 */
	String getConversionSchemeName();

	/**
	 * Called at start of processing
	 * @param context
	 */
	void init(IConversionContext context) throws Exception;

	/**
	 * Called to process the slices for conversion.
	 * @param context
	 * @param slice
	 */
	void visit(IConversionContext context, IDataset slice) throws Exception;

	/**
	 * Called at end of processing
	 * @param context
	 */
	void close(IConversionContext context) throws Exception;

	/**
	 * Defines if a given data set rank is supported by this visitor.
	 * @param length
	 * @return true of rank of conversion is supported
	 */
	boolean isRankSupported(int length);

	/**
	 * Notifies the visitor of all the datasets in all the files (may be repeats)
	 * which we will visit.
	 * 
	 * @param expandedDatasets
	 */
	void setExpandedDatasets(List<String> expandedDatasets);

}
