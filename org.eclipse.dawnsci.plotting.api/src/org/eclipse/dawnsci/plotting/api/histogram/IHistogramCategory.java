/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.histogram;

import org.eclipse.dawnsci.plotting.api.histogram.HistoCategory;

/**
 * used to manage colour categories
 */
public interface IHistogramCategory {

	/**
	 * 
	 * @return colour category
	 */
	public HistoCategory getCategory();
}
