/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.tree.impl;

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.metadata.DimensionMetadata;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.dataset.impl.IndexIterator;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;
import org.eclipse.dawnsci.analysis.dataset.metadata.DimensionMetadataImpl;

public class DataNodeImpl extends NodeImpl implements DataNode, Serializable {
	protected static final long serialVersionUID = 9089016783319981598L;

	private boolean unsigned = false;
	private boolean string = false;
	private boolean supported = false;
	private boolean augmented = false;
	private ILazyDataset dataset;
	private long[] maxShape;
	private long[] chunkShape;
	private String text;
	private String type;
	private int maxTextLength = -1;

	/**
	 * Construct a data node with given object ID
	 * @param oid object ID
	 */
	public DataNodeImpl(long oid) {
		super(oid);
	}

	@Override
	public boolean isUnsigned() {
		return unsigned;
	}

	@Override
	public void setUnsigned(boolean isUnsigned) {
		unsigned = isUnsigned;
	}

	@Override
	public boolean isString() {
		return string;
	}

	@Override
	public int getMaxStringLength() {
		return maxTextLength;
	}

	@Override
	public void setMaxStringLength(int length) {
		maxTextLength = length;
	}

	@Override
	public String getTypeName() {
		return type;
	}

	@Override
	public void setTypeName(String name) {
		type = name;
	}

	@Override
	public long[] getMaxShape() {
		if (dataset != null && dataset instanceof IDynamicDataset) {
			return toLongArray(((IDynamicDataset) dataset).getMaxShape());
		}
		
		return maxShape;
	}

	@Override
	public void setMaxShape(long... maxShape) {
		if (maxShape != null && dataset != null && maxShape.length != dataset.getRank()) {
			throw new IllegalArgumentException("Maximum shape must match rank of dataset");
		}
		this.maxShape = maxShape;
		
		if (dataset != null && dataset instanceof IDynamicDataset) {
			((IDynamicDataset) dataset).setMaxShape(toIntArray(maxShape));
		}
	}
	
	@Override
	public long[] getChunkShape() {
		if (dataset != null && dataset instanceof ILazyWriteableDataset) {
			return toLongArray(((ILazyWriteableDataset) dataset).getChunking());
		}
		
		return chunkShape;
	}

	@Override
	public void setChunkShape(long... chunkShape) {
		if (chunkShape != null && dataset != null && chunkShape.length != dataset.getRank()) {
			throw new IllegalArgumentException("Chunk shape must match rank of dataset");
		}
		this.chunkShape = chunkShape;
		
		if (dataset instanceof ILazyWriteableDataset) {
			((ILazyWriteableDataset) dataset).setChunking(toIntArray(chunkShape));
		}
	}

	@Override
	public boolean isSupported() {
		return supported;
	}

	@Override
	public void setEmpty() {
		dataset = null;
		supported = true;
	}

	@Override
	public String getString() {
		if (!string)
			return null;
		if (text != null)
			return text;
	
		StringDataset a;
		if (dataset instanceof StringDataset)
			a = (StringDataset) dataset;
		else
			a = (StringDataset) dataset.getSlice();
	
		StringBuilder out = new StringBuilder();
		IndexIterator it = a.getIterator();
		while (it.hasNext()) {
			out.append(a.getAbs(it.index));
			out.append('\n');
		}
		int end = out.length() - 1;
		return end > 0 ? out.substring(0, end) : "";
	}

	@Override
	public void setString(final String text) {
		this.text = text;
		if (text != null) {
			maxTextLength = text.getBytes().length;
			string = true;
			supported = true;
		}
	}

	@Override
	public ILazyDataset getDataset() {
		return dataset;
	}

	@Override
	public ILazyWriteableDataset getWriteableDataset() {
		return (ILazyWriteableDataset) (dataset instanceof ILazyWriteableDataset ? dataset : null);
	}

	@Override
	public void setDataset(final ILazyDataset lazyDataset) {
		dataset = lazyDataset;
		int rank = dataset.getRank();
		int[] mshape = null;
		if (maxShape != null) {
			mshape = new int[rank];
			for (int i = 0; i < rank; i++) {
				mshape[i] = (int) maxShape[i];
			}
		}
		int[] cshape = null;
		if (chunkShape != null) {
			cshape = new int[rank];
			for (int i = 0; i < rank; i++) {
				cshape[i] = (int) chunkShape[i];
			}
		}
		if (mshape != null || cshape != null) {
			DimensionMetadata dmd = new DimensionMetadataImpl(dataset.getShape(), mshape, cshape);
			dataset.addMetadata(dmd);
		}

		supported = true;
		string = lazyDataset instanceof StringDataset || lazyDataset.elementClass() == String.class;
	}

	@Override
	public boolean isAugmented() {
		return augmented;
	}

	@Override
	public void setAugmented() {
		augmented = true;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder(super.toString());
	
		out.append(INDENT);
		if (string) {
			out.append(getString());
		} else if (supported) {
			out.append(dataset == null ? "empty" : dataset.toString());
		} else {
			out.append("unsupported");
		}
		return out.toString();
	}
	
	private int[] toIntArray(long[] longArray) {
		final int[] intArray = new int[longArray.length];
		for (int i = 0; i < longArray.length; i++) {
			long longValue = longArray[i];
			if (longValue > Integer.MAX_VALUE) {
				throw new IllegalArgumentException("Dimension size is too large for an integer " + longValue);
			}
			
			intArray[i] = (int) longValue;
		}
		
		return intArray;
	}
	
	private long[] toLongArray(int[] intArray) {
		final long[] longArray = new long[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			longArray[i] = intArray[i];
		}
		
		return longArray;
	}

}
