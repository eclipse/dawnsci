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
package org.eclipse.dawnsci.hdf5;

import java.util.ArrayList;
import java.util.List;

import ncsa.hdf.object.Group;

public class HierarchicalDataFileUtils {
	// delete empty strings
	private static String[] cleanArray(String[] array) {

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			if (!array[i].isEmpty()) {
				list.add(array[i]);
			}
		}
		String[] result = new String[list.size()];
		return list.toArray(result);
	}

	/**
	 * Creates the group identified by fullEntry, marking all nodes as Nexus, and the last as the given nexusEntry
	 * 
	 * @param file
	 * @param fullEntry - path to be created
	 * @param nexusEntry - attribute value for final node
	 * @return
	 * @throws Exception
	 */
	public static String createParentEntry(IHierarchicalDataFile file, String fullEntry, String nexusEntry) throws Exception {
		
		String[] entries = fullEntry.split("/");
		entries = cleanArray(entries);
		String parent = null;
		for (int i = 0; i < entries.length; i++) {
			String entry = null;
			if (i == 0) {
				entry = file.group(entries[i]);
				file.setNexusAttribute(entry, Nexus.ENTRY);
				parent = entry;
			} else if (i == entries.length - 1) {
				entry = file.group(entries[i], parent);
				file.setNexusAttribute(entry, nexusEntry);
				parent = entry;
			} else {
				entry = file.group(entries[i], parent);
				file.setNexusAttribute(entry, Nexus.ENTRY);
				parent = entry;
			}
		}
		return parent;
	}

}
