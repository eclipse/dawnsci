/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

public class SliceFromLiveSeriesMetadata extends SliceFromSeriesMetadata {

	private int port;
	private String host;
	private String[] axesNames;
	
	public SliceFromLiveSeriesMetadata(SourceInformation source, SliceInformation slice, String host, int port, String[] axesNames) {
		super(source, slice);
		
		this.port = port;
		this.host = host;
		this.axesNames = axesNames;

	}

	public String[] getAxesNames() {
		return axesNames;
	}

	public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}
	
	@Override
	public SliceFromLiveSeriesMetadata clone() {
		SourceInformation soi = this.getSourceInfo() != null ? this.getSourceInfo().clone() : null;
		SliceInformation sli = this.getSliceInfo() != null ? this.getSliceInfo().clone() : null;
		
		return new SliceFromLiveSeriesMetadata(soi, sli, host, port,axesNames);
	}
}
