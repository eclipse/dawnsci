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

import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXpositioner;

public interface NexusDataModel {

	public NXdata getNexusData();

	/**
	 * Sets the device to use as the data device. The data node from this device (as returned by {@link NexusObjectProvider#getBaseClassDataNodeName()}, e.g. 'data' for
	 * an {@link NXdetector}, 'value' for an {@link NXpositioner}.
	 *
	 * @param nexusDeviceAdapter
	 */
	public void setDataDevice(NexusObjectProvider<? extends NXobject> nexusDeviceAdapter) throws NexusException;

	/**
	 * Adds the device as an axis. The default data node for this device 
	 * (as returned by {@link NexusObjectProvider#getBaseClassDataNodeName()} will be used.
	 * @param dimensionIndex
	 * @param nexusDeviceAdapter
	 * @param makeDefault <code>true</code> to set this device as the default device for the axis, <code>false</code> otherwise
	 * @throws NexusException 
	 */
	public void addAxisDevice(int dimensionIndex,
			NexusObjectProvider<? extends NXobject> nexusDeviceAdapter, boolean makeDefault) throws NexusException;
	
	/**
	 * Adds the device as an axis. 
	 * @param dimensionIndex
	 * @param nexusDeviceAdapter
	 * @param makeDefault
	 * @param dataNodeName
	 * @throws NexusException
	 */
	public void addAxisDevice(int dimensionIndex,
			NexusObjectProvider<? extends NXobject> nexusDeviceAdapter,
			boolean makeDefault, String dataNodeName) throws NexusException; 

}
