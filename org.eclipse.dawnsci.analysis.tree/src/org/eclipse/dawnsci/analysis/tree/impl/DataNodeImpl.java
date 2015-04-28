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

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.dataset.impl.IndexIterator;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;

public class DataNodeImpl extends NodeImpl implements DataNode, Serializable {
	protected static final long serialVersionUID = 9089016783319981598L;

	private boolean string = false;
	private boolean supported = false;
	private boolean augmented = false;
	private ILazyDataset dataset;
	private long[] maxShape;
	private String text;
	private String type;

	/**
	 * Construct a data node with given object ID
	 * @param oid object ID
	 */
	public DataNodeImpl(long oid) {
		super(oid);
	}

	@Override
	public boolean isString() {
		return string;
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
		return maxShape;
	}

	@Override
	public void setMaxShape(long[] maxShape) {
		this.maxShape = maxShape;
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
		int end = out.length();
		out.delete(end-1, end);
		return out.toString();
	}

	@Override
	public void setString(final String text) {
		this.text = text;
		string = true;
		supported = true;
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
}
