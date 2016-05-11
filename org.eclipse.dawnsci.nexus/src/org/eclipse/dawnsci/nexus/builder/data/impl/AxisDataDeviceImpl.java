package org.eclipse.dawnsci.nexus.builder.data.impl;

import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.builder.data.AxisDataDevice;

public class AxisDataDeviceImpl<N extends NXobject> extends DataDeviceImpl<N> implements AxisDataDevice<N> {

	public AxisDataDeviceImpl(N nexusObject) {
		super(nexusObject);
	}

}
