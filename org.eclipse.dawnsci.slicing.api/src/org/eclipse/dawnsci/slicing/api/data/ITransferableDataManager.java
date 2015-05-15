/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.slicing.api.data;

public interface ITransferableDataManager {

	/**
	 * Call to add some data. This could add some data to a 
	 * table which the user can then choose to plot for instance.
	 * @param data
	 */
	public void addData(ITransferableDataObject data);
	
	/**
	 * Call to add some data. This could add some data to a 
	 * table which the user can then choose to plot for instance.
	 * @param data
	 */
	public void removeData(ITransferableDataObject data);
}
