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
package org.eclipse.dawnsci.plotting.api;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

public interface IPlottingSystemSelection {
	
	/**
	 * Select some data and return it for future use.
	 * @param name
	 * @param clearOthers
	 * @return
	 */
	public IDataset setDatasetSelected(final String name, final boolean clearOthers);
	

	/**
	 * This method can be used to select all 1D plots. It is not part of the AbstractPlottingSystem
	 * because plots are selected outside of the main plotting system. @see PlotDataView and pages of it.
	 * 
	 * @param b
	 */
	public void setAll1DSelected(boolean allSelected);
}
