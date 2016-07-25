/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.image;

import org.eclipse.january.dataset.IDataset;

public interface IImageTracker {

	/**
	 * Initialise the tracker given a default location of a region of interest, an input data and a {@link TrackerType}.
	 * <br>
	 * The location is a double array of size 8.
	 * 
	 * @param input
	 * @param location
	 * @param type
	 * @throws Exception 
	 */
	public void initialize(IDataset input, double[] location, TrackerType type) throws Exception;

	/**
	 * Track a region of interest on an input data
	 * 
	 * @param data
	 * @return new location of tracked region
	 * @throws Exception
	 */
	public double[] track(IDataset data) throws Exception;

	/**
	 * TrackerType:
	 * 
	 */
	public enum TrackerType {
		TLD,
		CIRCULANT,
		SPARSEFLOW,
		MEANSHIFTCOMANICIU2003;
	}
}
