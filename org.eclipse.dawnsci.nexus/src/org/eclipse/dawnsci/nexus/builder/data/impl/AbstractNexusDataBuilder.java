package org.eclipse.dawnsci.nexus.builder.data.impl;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.data.DataDeviceBuilder;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusEntryBuilder;

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
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusDataBuilder#getNexusData()
	 */
	@Override
	public NXdata getNxData() {
		return nxData;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder#setPrimaryDevice(org.eclipse.dawnsci.nexus.builder.NexusObjectProvider)
	 */
	@Override
	public <N extends NXobject> void setPrimaryDevice(NexusObjectProvider<N> primaryDevice)
			throws NexusException {
		setPrimaryDevice(DataDeviceBuilder.newPrimaryDataDevice(primaryDevice));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder#addDataDevice(org.eclipse.dawnsci.nexus.builder.NexusObjectProvider)
	 */
	@Override
	public <N extends NXobject> void addAxisDevice(NexusObjectProvider<N> dataDevice) throws NexusException {
		addAxisDevice(DataDeviceBuilder.newAxisDataDevice(dataDevice));
	}

}
