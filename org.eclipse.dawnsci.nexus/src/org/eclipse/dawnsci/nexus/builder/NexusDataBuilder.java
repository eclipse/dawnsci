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

package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;

/**
 * Wraps a model of a {@link NXdata}
 */
public interface NexusDataBuilder {

	public NXdata getNexusData();

	/**
	 * Sets the device to use as the data device. Adds a field to the wrapped {@link NXdata}
	 * object linking to the default data field of the NeXus object for the given
	 * {@link NexusObjectProvider}, where the
	 * NeXus object is as returned by {@link NexusObjectProvider#getNexusObject()}
	 * and the field linked to is the field within that object with the name as returned by
	 * {@link NexusObjectProvider#getDefaultDataFieldName()} 
	 * 
	 *
	 * @param nexusObjectProvider
	 */
	public void setDataDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider) throws NexusException;
	
	/**
	 * Sets the device to use and the data device. Adds a field to the wrapped {@link NXdata}
	 * object linking to the data field with the name as given by <code>datafieldName</code>
	 * within the NeXus object for the given {@link NexusObjectProvider}, where the
	 * NeXus object is as returned by {@link NexusObjectProvider#getNexusObject()}
	 * @param nexusObjectProvider
	 * @param dataFieldName
	 * @throws NexusException
	 */
	public void setDataDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider, String dataFieldName) throws NexusException;

	/**
	 * Adds the device as an axis. The default field for this device 
	 * (as returned by {@link NexusObjectProvider#getDefaultDataFieldName()} will be used.
	 * @param dimensionIndex
	 * @param nexusObjectProvider
	 * @param makeDefault <code>true</code> to set this device as the default device for the axis, <code>false</code> otherwise
	 * @throws NexusException 
	 */
	public void addAxisDevice(int dimensionIndex,
			NexusObjectProvider<? extends NXobject> nexusObjectProvider, boolean makeDefault) throws NexusException;
	
	/**
	 * Adds the device as an axis. 
	 * @param dimensionIndex
	 * @param nexusObjectProvider
	 * @param makeDefault
	 * @param fieldName
	 * @throws NexusException
	 */
	public void addAxisDevice(int dimensionIndex,
			NexusObjectProvider<? extends NXobject> nexusObjectProvider,
			boolean makeDefault, String fieldName) throws NexusException; 

}
