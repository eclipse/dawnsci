package org.eclipse.dawnsci.nexus.builder.impl;

import java.text.MessageFormat;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.builder.DataDevice;
import org.eclipse.dawnsci.nexus.builder.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;

public abstract class AbstractNexusDataBuilder implements NexusDataBuilder {

	protected final NXdata nxData;

	protected final NexusEntryBuilder entryBuilder;

	/**
	 * Create a new {@link DefaultNexusDataBuilder}. This constructor should only be
	 * called by {@link DefaultNexusEntryBuilder}.
	 * @param entryBuilder parent entry model
	 * @param nxData {@link NXdata} object to wrap
	 */
	protected AbstractNexusDataBuilder(NexusEntryBuilder entryBuilder,
			final NXdata nxData) {
		this.entryBuilder = entryBuilder;
		this.nxData = nxData;
	}
	
	/**
	 * Returns the data node with the given name
	 * @param nexusObjectProvider nexus object provider
	 * @param fieldName field name
	 * @return 
	 * @throws NexusException
	 */
	protected DataNode getDataNode(NexusObjectProvider<? extends NXobject> nexusObjectProvider,
			String fieldName) throws NexusException {
		final NXobject nexusObject = nexusObjectProvider.getNexusObject(entryBuilder.getNodeFactory(), true);
		final DataNode dataNode = nexusObject.getDataNode(fieldName);
		if (dataNode == null) {
			throw new IllegalArgumentException(MessageFormat.format("No such data node for group {0}: {1}",
					nexusObject.getNXclass().getSimpleName(), fieldName));
		}
	
		return dataNode;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusDataBuilder#getNexusData()
	 */
	@Override
	public NXdata getNxData() {
		return nxData;
	}

	@Override
	public void setPrimaryDevice(NexusObjectProvider<?> primaryDevice)
			throws NexusException {
		setPrimaryDevice(new DataDevice<>(primaryDevice, false));
	}

	@Override
	public void addDataDevice(NexusObjectProvider<?> dataDevice) throws NexusException {
		addDataDevice(new DataDevice<>(dataDevice, true));
	}

	@Override
	public void addDataDevice(NexusObjectProvider<?> dataDevice, int defaultAxisDimension) throws NexusException {
		addDataDevice(dataDevice, defaultAxisDimension, null);
	}

}
