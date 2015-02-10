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

package org.eclipse.dawnsci.analysis.api.fitting.functions;

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;


/**
 * Function interface used for fitting
 */
public interface IDataBasedFunction extends Serializable {

	public static final long serialVersionUID = -6729243965994162061L;

	/**
	 * Set the raw data values and associated axis values
	 * @param x
	 * @param data
	 */
	public void setData(IDataset x, IDataset data);

}
