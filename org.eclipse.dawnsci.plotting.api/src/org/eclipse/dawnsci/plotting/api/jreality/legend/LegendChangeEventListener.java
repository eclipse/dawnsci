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

package org.eclipse.dawnsci.plotting.api.jreality.legend;

/**
 * Listener that listens on different LegendChangeEvents, e.g. deleting entries
 */
public interface LegendChangeEventListener {

	/**
	 * Called on lengend update
	 * @param evt
	 */
	public void legendUpdated(LegendChangeEvent evt);
	/**
	 * An entry has been deleted out of the legend
	 * @param evt LegendChangeEvent object that contains all relevant information
	 */
	
	public void legendDeleted(LegendChangeEvent evt);
	
}
