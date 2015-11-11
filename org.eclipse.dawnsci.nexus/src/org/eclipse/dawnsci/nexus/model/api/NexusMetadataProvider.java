/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.model.api;

import java.util.Iterator;

import org.eclipse.dawnsci.nexus.NexusBaseClass;

public interface NexusMetadataProvider extends NexusTreeModification {

	public interface MetadataEntry {
		
		public String getName();
		
		public Object getValue();
		
	}
	
	public Iterator<MetadataEntry> getMetadataEntries();
	
	/**
	 * Returns the category of this base class. The 
	 * @return
	 */
	public NexusBaseClass getCategory();
	
}
