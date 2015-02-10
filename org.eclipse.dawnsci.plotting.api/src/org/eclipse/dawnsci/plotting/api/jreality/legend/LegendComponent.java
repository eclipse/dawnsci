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

import java.util.LinkedList;

import org.eclipse.dawnsci.plotting.api.jreality.impl.Plot1DGraphTable;
import org.eclipse.swt.widgets.Composite;

/**
 * Abstract LegendComponent that can be added to the DataSetPlotter as acting
 * legend
 */
public abstract class LegendComponent extends Composite {

	protected LinkedList<LegendChangeEventListener> listeners;
	
	/**
	 * @param parent
	 * @param style
	 */

	public LegendComponent(Composite parent, int style)
	{
		super(parent,style);
		listeners = new LinkedList<LegendChangeEventListener>();
	}
	
	/**
	 * Add a LegendChangeEventListener
	 * @param listener LegendChangeEventListener that should be added
	 */
	
	public void addLegendChangeEventListener(LegendChangeEventListener listener)
	{
		listeners.add(listener);
	}

	/**
	 * Remove a LegendChangeEventListener
	 * @param listener LegendChangeEventListener that should be removed
	 */
	
	public void removeLegendChangeEventListener(LegendChangeEventListener listener)
	{
		listeners.remove(listener);
	}
	
	/**
	 * Remove all LegendChangeEventListeners
	 */
	
	public void removeAllLegendChangeEventListener()
	{
		listeners.clear();
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		if (listeners != null)
			listeners.clear();
	}
	
	/**
	 * Update the legend table
	 * @param table List containing all the Plot appearance information
	 */
	public abstract void updateTable(Plot1DGraphTable table);

}
