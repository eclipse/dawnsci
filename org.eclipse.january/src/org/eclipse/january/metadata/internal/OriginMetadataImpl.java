/*
 * Copyright (c) 2012, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.january.metadata.internal;

import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.metadata.OriginMetadata;

public class OriginMetadataImpl extends DimensionMetadataImpl implements OriginMetadata {

	private static final long serialVersionUID = 8024625240713615948L;

	private ILazyDataset parent;
	private Slice[] outSlice;
	private Slice[] currentSlice;
	
	private String datasetName;
	private String filePath;

	public OriginMetadataImpl() {
	}

	@Override
	public void initialize(ILazyDataset parent, Slice[] outSlice, int[] dataDims, String filePath, String datasetName) {
		super.initialize(dataDims, null, null);
		this.parent = parent;
		this.outSlice = outSlice;
		this.datasetName = datasetName;
		this.filePath = filePath;
	}

	private OriginMetadataImpl(OriginMetadataImpl origin) {
		super();
		super.initialize(null, null, null);
		parent = origin.parent == null ? null : origin.parent;
		outSlice = origin.outSlice == null ? null : origin.outSlice.clone();
		shape = origin.shape == null ? null : origin.shape.clone();
		datasetName = origin.datasetName == null ? null : new String(origin.datasetName);
		filePath = origin.filePath == null ? null : new String(origin.filePath);
		currentSlice = origin.currentSlice == null ? null : origin.currentSlice.clone();
	}

	@Override
	public ILazyDataset getParent() {
		return parent;
	}

	@Override
	public Slice[] getSliceFromInput() {
		return currentSlice;
	}
	
	public void setCurrentSlice(Slice[] currentSlice) {
		this.currentSlice = currentSlice;
	}
	
	@Override
	public OriginMetadata clone() {
		return new OriginMetadataImpl(this);
	}

	@Override
	public Slice[] getSliceInOutput() {
		return outSlice;
	}

	@Override
	public String getDatasetName() {
		return datasetName;
	}

	@Override
	public String getFilePath() {
		return filePath;
	}

}
