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

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
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

	/**
	 * Returns the wrapped {@link NXdata} object.
	 * @return the {@link NXdata}
	 */
	public NXdata getNxData();

	/**
	 * Sets the device to use as the data device. Adds a field to the wrapped {@link NXdata}
	 * object linking to the default data field of the NeXus object for the given
	 * {@link NexusObjectProvider}, where the
	 * NeXus object is as returned by {@link NexusObjectProvider#getNexusObject()}
	 * and the field linked to is the field within that object with the name as returned by
	 * {@link NexusObjectProvider#getDefaultDataFieldName()} 
	 * 
	 * @param nexusObjectProvider the {@link NexusObjectProvider} for the device 
	 * @throws NexusException if the device cannot be added for any reason
	 */
	public void setDataDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider) throws NexusException;
	
	/**
	 * Sets the device to use and the data device. Adds a field to the wrapped {@link NXdata}
	 * object linking to the data field with the name as given by <code>datafieldName</code>
	 * within the NeXus object for the given {@link NexusObjectProvider}, where the
	 * NeXus object is as returned by {@link NexusObjectProvider#getNexusObject()}
	 * @param nexusObjectProvider {@link NexusObjectProvider} for the device
	 * @param destinationFieldName name of the data field to create within {@link NXdata} linking
	 *   to the default data node of the given {@link NexusObjectProvider}.
	 * @throws NexusException if the device cannot be added for any reason 
	 */
	public void setDataDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider,
			String destinationFieldName) throws NexusException;

	public void addAxisDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider) throws NexusException;
	
	public void addAxisDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider,
			int defaultAxisDimensionIndex) throws NexusException;
	
	/**
	 * Add the default field of the nexus object (i.e. group) for the given
	 * {@link NexusObjectProvider} as an axis device to the {@link NXdata}.
	 * @param nexusObjectProvider the {@link NexusObjectProvider} for the device
	 * @param dimensionMappings maps each dimension of the default data field to the
	 *   dimension of the main data field with the given index
	 * @throws NexusException if the device cannot be added for any reason
	 */
	public void addAxisDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider,
			int[] dimensionMappings) throws NexusException;

	/**
	 * Add the default field of the nexus object (i.e. group) for the given
	 * {@link NexusObjectProvider} as an axis device to the {@link NXdata}. Additionally
	 * sets this data node as the primary axis device for the dimension of the
	 * default data field of the {@link NXdata} with the given index.
	 * param nexusObjectProvider the {@link NexusObjectProvider} for the device
	 * @param defaultAxisDimensionIndex the index of the dimension of the main
	 *   data field of the {@link NXdata} for which this device is the primary axis
	 * @param dimensionMappings maps each dimension of the default data field to the
	 *   dimension of the main data field with the given index
	 * @throws NexusException if the device cannot be added for any reason
	 */
	public void addAxisDevice(NexusObjectProvider<? extends NXobject> nexusObjectProvider,
			int defaultAxisDimensionIndex, int[] dimensionMappings) throws NexusException;

	/**
	 * Adds the appropriate fields for the nexus object returned by the
	 * wrapped {@link NexusObjectProvider} within the given {@link AxisDevice} to
	 * the {@link NXdata} object wrapped by this object.
	 * The fields added to the {@link NXdata} group are those returned by
	 * {@link AxisDevice#getSourceFieldNames()}. For each source field returned
	 * a link is created within the {@link NXdata} group with the name as returned by 
	 * {@link AxisDevice#getDestinationFieldName(String)} for the source field name,
	 * linking to the existing {@link DataNode} with the given name within the
	 * source nexus object returned by the {@link NexusObjectProvider#getNexusObject()}
	 * method of the nexus object provider wrapped by the given {@link AxisDevice}.
	 * @param axisDevice axis device
	 * @throws NexusException if the device cannot be added for any reason
	 */
	public void addAxisDevice(AxisDevice<? extends NXobject> axisDevice) throws NexusException;
	
}
