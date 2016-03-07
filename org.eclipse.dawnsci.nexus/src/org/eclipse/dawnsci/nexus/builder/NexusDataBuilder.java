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
 * Wraps a {@link NXdataImpl} object and adds methods to configure it by adding
 * {@link NexusObjectProvider}s providing the data node and and axis data.
 * 
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
	 * {@link NexusObjectProvider#getPrimaryDataField()} will be set
	 * as the default data field of this device, (referenced by the
	 * <code>@signal</code> other fields as returned by
	 * {@link NexusObjectProvider#getDataFields()} will also be added.
	 * 
	 * @param primaryDevice
	 *            primary device
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public void setPrimaryDevice(NexusObjectProvider<?> primaryDevice)
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
	public void setPrimaryDevice(DataDevice<?> primaryDeviceModel)
			throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group.
	 * 
	 * @param dataDevice
	 *            device
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public void addDataDevice(NexusObjectProvider<?> dataDevice)
			throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group. The fields do add are determined by
	 * {@link NexusObjectProvider#getDataFields()}.
	 * <p>
	 * Additionally the field with the name as returned by
	 * {@link NexusObjectProvider#getDemandDataField()}, or by
	 * {@link NexusObjectProvider#getPrimaryDataField()} if the
	 * former is <code>null</code> is set as the default axis of the main data
	 * field of the {@link NXdata} for the dimension with the given index (e.g.
	 * the value of the <code>@axes</code> attribute at that index is set to the
	 * name of this field).
	 * <p>
	 * The dimension mappings for each field are set to those of the scan, e.g.
	 * each data field maps to the dimension of the default data field of the
	 * {@link NXdata} section with the same index.
	 * 
	 * @param dataDevice
	 *            data device
	 * @param defaultAxisDimension
	 *            the index of the axis of the main data field of the wrapped
	 *            {@link NXdata} for which this device provides a default axis
	 *            value
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public void addDataDevice(NexusObjectProvider<?> dataDevice,
			int defaultAxisDimension) throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group. The fields do add are determined by
	 * {@link NexusObjectProvider#getDataFields()}.
	 * <p>
	 * Additionally the field with the name as returned by
	 * {@link NexusObjectProvider#getDemandDataField()}, or by
	 * {@link NexusObjectProvider#getPrimaryDataField()} if the
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
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public void addDataDevice(NexusObjectProvider<?> dataDevice,
			Integer defaultAxisDimension, int... dimensionMappings)
			throws NexusException;

	/**
	 * Adds the data fields of the given device to the wrapped {@link NXdata}
	 * group. The given {@link DataDevice} object wraps a
	 * {@link NexusObjectProvider} and provides methods for configuring how the
	 * {@link DataNode}s for the device should be added to the {@link NXdata}
	 * 
	 * @param primaryDeviceModel
	 *            a {@link DataDevice} wrapping the {@link NexusObjectProvider}
	 *            that provides the {@link NXobject} whose fields should be
	 *            added to the {@link NXdata}
	 * @throws NexusException
	 *             if the device could not be added for any reason
	 */
	public void addDataDevice(DataDevice<?> dataDeviceModel)
			throws NexusException;

}
