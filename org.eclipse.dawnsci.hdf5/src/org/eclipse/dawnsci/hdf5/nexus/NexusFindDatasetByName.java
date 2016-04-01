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
package org.eclipse.dawnsci.hdf5.nexus;


import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;

public class NexusFindDatasetByName implements IFindInNexus {

	public String text;
	private IHierarchicalDataFile file;
	
	public NexusFindDatasetByName(IHierarchicalDataFile file, String attributeText) {
		this.file = file;
		text = attributeText;
	}
	
	@Override
	public boolean inNexus(String nexusObjectPath) {
		try {
			if (file.isDataset(nexusObjectPath)) {
				final String name = nexusObjectPath.substring(nexusObjectPath.lastIndexOf('/')+1);
				if (name.toLowerCase().equals(text.toLowerCase())) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
}
