/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi;

import java.io.Serializable;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;


/**
 * Wrapper for a list of general ROIs
 */
public interface ROIList<T extends IROI> extends Serializable, List<T> {

	/**
	 * Add roi to list (if it is of correct class)
	 * @param roi
	 * @return true if added
	 */
	@Override
	public boolean add(IROI roi);

	/**
	 * Add roi to list
	 * @param roi
	 * @return true if added
	 */
	public boolean append(T roi);
}
