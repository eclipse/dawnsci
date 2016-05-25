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

package org.eclipse.dawnsci.nexus.builder.data;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.impl.NXdataImpl;

/**
 * Wraps a {@link NXdataImpl} object and provides methods to configure it by adding
 * {@link NexusObjectProvider}s providing the data node and axis data.
 */
public interface NexusDataBuilder {
	
	public static final String NO_DEFAULT_AXIS_PLACEHOLDER = ".";

	public static final String ATTR_NAME_SIGNAL = "signal";

	public static final String ATTR_NAME_AXES = "axes";
	
	public static final String ATTR_NAME_TARGET = "target";
	
	public static final String ATTR_NAME_LONG_NAME = "long_name";

	public static final String ATTR_SUFFIX_INDICES = "_indices";

	/**
	 * Returns the wrapped {@link NXdata} object. This can be used if client
	 * code needs to modify the {@link NXdata} group in a custom way.
	 * 
	 * @return the {@link NXdata}
	 */
	public NXdata getNxData();

	/**
	 * Sets the given device as the primary device of the wrapped {@link NXdata}
	 * group. The data node of the device with the name as returned by
	 * {@link NexusObjectProvider#getPrimaryDataFieldName()} will be set
	 * as the default data field of this device, (referenced by the
	 * <code>@signal</code> other fields as returned by
	 * {@link NexusObjectProvider#getAxisDataFieldNames()} will also be added.
	 * 
	 * @param primaryDevice
	 *            primary device
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public <N extends NXobject> void setPrimaryDevice(NexusObjectProvider<N> primaryDevice)
			throws NexusException;

	/**
	 * Sets the given device as the primary device of the wrapped {@link NXdata}
	 * group. The given {@link DataDevice} object wraps a
	 * {@link NexusObjectProvider} and provides methods for configuring how the
	 * {@link DataNode}s for the device should be added to the {@link NXdata}
	 * group.
	 * 
	 * @param primaryDeviceModel
	 *            a {@link DataDevice} wrapping the {@link NexusObjectProvider}
	 *            that provides the {@link NXobject} whose fields should be
	 *            added to the {@link NXdata}
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public <N extends NXobject> void setPrimaryDevice(PrimaryDataDevice<N> primaryDeviceModel)
			throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group. The fields do add are determined by
	 * {@link NexusObjectProvider#getAxisDataFieldNames()}.
	 * <p>
	 * 
	 * @param dataDevice
	 *            device
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public <N extends NXobject> void addAxisDevice(NexusObjectProvider<N> dataDevice)
			throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group. The fields do add are determined by
	 * {@link NexusObjectProvider#getAxisDataFieldNames()}.
	 * <p>
	 * Additionally the field with the name as returned by
	 * {@link NexusObjectProvider#getDefaultAxisDataFieldName()}, or by
	 * {@link NexusObjectProvider#getPrimaryDataFieldName()} if the
	 * former is <code>null</code> is set as the default axis of the main data
	 * field of the {@link NXdata} for the dimension with the given index (e.g.
	 * the value of the <code>@axes</code> attribute at that index is set to the
	 * name of this field).
	 * <p>
	 * The dimension mappings for each field are set to those given. The
	 * possible exception is that if the field that default axis dimension
	 * applies to has a rank of 1 then this single dimension will be mapped to
	 * the dimension of the given index of the main dataset.
	 * 
	 * @param dataDevice
	 *            data device
	 * @param defaultAxisDimension
	 *            the index of the axis of the main data field of the wrapped
	 *            {@link NXdata} for which this device provides a default axis
	 *            value
	 * @param dimensionMappings dimension mappings between the data field(s) of the device
	 *   and the <code>@signal</code> field of the {@link NXdata} group
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public <N extends NXobject> void addAxisDevice(NexusObjectProvider<N> dataDevice,
			Integer defaultAxisDimension, int... dimensionMappings)
			throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group. The given {@link DataDevice} object wraps a
	 * {@link NexusObjectProvider} and provides methods for configuring how the
	 * {@link DataNode}s for the device should be added to the {@link NXdata}
	 * 
	 * @param dataDeviceModel
	 *            a {@link DataDevice} wrapping the {@link NexusObjectProvider}
	 *            that provides the {@link NXobject} whose fields should be
	 *            added to the {@link NXdata}
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public <N extends NXobject> void addAxisDevice(AxisDataDevice<N> dataDeviceModel) throws NexusException;

}
