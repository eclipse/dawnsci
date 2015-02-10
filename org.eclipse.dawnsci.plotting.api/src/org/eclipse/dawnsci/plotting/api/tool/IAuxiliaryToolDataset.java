/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.tool;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Interface used to add extra dataset to a tool
 * @author wqk87977
 *
 */
public interface IAuxiliaryToolDataset {

	/**
	 * Adds dataset to the tool
	 * @param data
	 */
	void addDataset(IDataset data);

	/**
	 * removes dataset
	 * @param data
	 */
	void removeDataset(IDataset data);
}
