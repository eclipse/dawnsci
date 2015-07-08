/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.slicing.api.data;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;


/**
 * An easy way to insert any LazyDataset into the GUI programmatically.
 */
public class TransferableLazyDataset extends AbstractTransferableDataObject {
	
	private ILazyDataset delegate;

	public TransferableLazyDataset(ILazyDataset delegate) {
		this.delegate = delegate;
		this.name     = delegate.getName();
		this.transientData = true;
	}

	@Override
	public IDataset getData(IMonitor monitor) {
		return delegate.getSlice();
	}

	@Override
	public ILazyDataset getLazyData(IMonitor monitor) {
		return delegate;
	}

	@Override
	public String getFileName() {
		return delegate instanceof IRemoteDataset
			  ? (new File( ((IRemoteDataset)delegate).getPath() )).getName()
		      : null;
	}

	@Override
	public int[] getShape(boolean force) {
		return delegate.getShape();
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public void setName(String name) {
		delegate.setName(name);
		super.setName(name);
	}

	@Override
	public String getPath() {
		if (delegate instanceof  IRemoteDataset) {
			IRemoteDataset rd = (IRemoteDataset)delegate;
			if (rd.getDataset()!=null) return rd.getDataset(); 
		}
	    return delegate.getName();
	}

	@Override
	public String getDisplayName(String rootName) {
		return getPath();
	}

	@Override
	public ITransferableDataObject clone() {
		return new TransferableLazyDataset(delegate);
	}

	@Override
	public void dispose() {
		delegate = null;
	}

	@Override
	public String getFilePath() {
		return delegate instanceof IRemoteDataset
				  ? ((IRemoteDataset)delegate).getPath()
			      : null;
	}

}
