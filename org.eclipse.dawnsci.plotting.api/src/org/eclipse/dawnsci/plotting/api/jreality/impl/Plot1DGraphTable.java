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

package org.eclipse.dawnsci.plotting.api.jreality.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Plot1DGraphColourTable 
 */

public class Plot1DGraphTable {

	
	private List<Plot1DAppearance> legendEntries = 
		Collections.synchronizedList(new LinkedList<Plot1DAppearance>());
	
	/**
	 * Get the number of legend entries
	 * @return the number of legend entries
	 */
	
	public synchronized int getLegendSize()
	{
		return legendEntries.size();
	}
	
	/**
	 * Get the entry / description of a specific position in the legend map
	 * @param nr entry number
	 * @return Description of the entry at the asked position
	 */
	
	public synchronized Plot1DAppearance getLegendEntry(int nr)
	{
		assert nr < legendEntries.size();
		return legendEntries.get(nr);
	}
	
	/**
	 * Push an entry on the top of the list of the legend
	 * @param newEntry name of the new entry
	 */
	public synchronized void pushEntryOnLegend(Plot1DAppearance newEntry)
	{
		legendEntries.add(0,newEntry);
	}
	
	/**
	 * Add an entry on the back of the list of the legend
	 * @param newEntry name of the new entry
	 * @return the index added.
	 */
	
	public synchronized int addEntryOnLegend(Plot1DAppearance newEntry)
	{
		legendEntries.add(newEntry);
		return legendEntries.size()-1;
	}

	/**
	 * Add entry at given index
	 * @param i index
	 * @param newEntry
	 */
	public synchronized void addEntryOnLegend(int i, Plot1DAppearance newEntry)
	{
		legendEntries.add(i, newEntry);
	}

	/**
	 * Delete an entry in the legend map at a specific position
	 * @param nr position in the legend map
	 */
	public synchronized void deleteLegendEntry(int nr)
	{
		assert nr < legendEntries.size() -1 && nr >= 0;
		legendEntries.remove(nr);
	}
	
	public synchronized void deleteLegendEntry(Plot1DAppearance entry)
	{
		legendEntries.remove(entry);
	}

	/**
	 * Clear the whole legend map
	 */
	
	public synchronized void clearLegend()
	{
		legendEntries.clear();
	}

	public int size() {
		return legendEntries.size();
	}
		
}
