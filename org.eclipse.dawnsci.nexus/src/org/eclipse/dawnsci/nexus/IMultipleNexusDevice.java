/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.nexus;

import java.util.List;

import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;

/**
 * Any device which can provide multiple NeXus objects to be added to a NeXus tree.
 */
public interface IMultipleNexusDevice {
	
	/**
	 * Returns the object providers required for writing correct NeXus files.
	 * 
	 * @see INexusDevice#getNexusProvider(NexusScanInfo)
	 * 
	 * @param info information about the scan which can be useful when creating datasets,
	 * 		e.g. the scan rank, <code>info.getRank()</code>
	 * @return a list of nexus providers for the given scan role
	 * @throws NexusException if the nexus objects could not be created for any reason
	 */
	public List<NexusObjectProvider<?>> getNexusProviders(NexusScanInfo info) throws NexusException;
	
}
