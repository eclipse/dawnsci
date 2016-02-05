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

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.impl.NXdataImpl;

/**
 * Wraps a {@link NXdataImpl} object and adds methods to configure it
 * by adding {@link NexusObjectProvider}s providing the data node and
 * and axis data.
 * 
 */
public interface NexusDataBuilder {
	
	public static final String NO_DEFAULT_AXIS_PLACEHOLDER = ".";

	public static final String ATTR_NAME_SIGNAL = "signal";

	public static final String ATTR_NAME_AXES = "axes";

	public static final String ATTR_SUFFIX_INDICES = "_indices";

	// TODO rewrite Javadocs
	
	/**
	 * Returns the wrapped {@link NXdata} object. This can be used if client code
	 * needs to modify the {@link NXdata} group in a custom way.
	 * @return the {@link NXdata}
	 */
	public NXdata getNxData();

	public void setPrimaryDevice(NexusObjectProvider<?> primaryDevice) throws NexusException;
	
	public void setPrimaryDevice(DataDevice<?> primaryDeviceModel) throws NexusException;
	
	public void addDataDevice(NexusObjectProvider<?> dataDevice) throws NexusException;
	
	public void addDataDevice(NexusObjectProvider<?> dataDevice, int defaultAxisDimension) throws NexusException;
	
	public void addDataDevice(NexusObjectProvider<?> dataDevice, Integer defaultAxisDimension, int... dimensionMappings) throws NexusException;
	
	public void addDataDevice(DataDevice<?> dataDeviceModel) throws NexusException;
	
}
