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

import org.eclipse.january.DatasetException;
import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.IDatasetConnector;


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
		try {
			return delegate.getSlice();
		} catch (DatasetException e) {
		}
		return null;
	}

	@Override
	public ILazyDataset getLazyData(IMonitor monitor) {
		return delegate;
	}

	@Override
	public String getFileName() {
		return delegate instanceof IDatasetConnector
			  ? (new File( ((IDatasetConnector)delegate).getPath() )).getName()
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
		if (delegate instanceof IDatasetConnector) {
			IDatasetConnector rd = (IDatasetConnector)delegate;
			if (rd.getDatasetName()!=null) return rd.getDatasetName(); 
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
		return delegate instanceof IDatasetConnector
				  ? ((IDatasetConnector)delegate).getPath()
			      : null;
	}

}
