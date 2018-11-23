/*-
 *******************************************************************************
 * Copyright (c) 2011, 2017 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *    Baha El-Kassaby - Removal of IHierchicalDataFile and HObject usage
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.editor;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.StringDataset;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H5LabelProvider extends ColumnLabelProvider implements ITableLabelProvider {

	private static final Logger logger = LoggerFactory.getLogger(H5LabelProvider.class);

	public H5LabelProvider() {
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * { "Name", "Class", "Dims", "Type", "Size" };
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		if (element == null)
			return null;
		Object object = null;
		if (element instanceof Attribute) {
			object = (Attribute) element;
		} else if (element instanceof NodeLink) {
			object = (NodeLink) element;
		} else {
			return null;
		}
		switch(columnIndex) {
		case 0:
			if(object instanceof Attribute)
				return ((Attribute)object).getName();
			else if (object instanceof NodeLink)
				return ((NodeLink)object).getName();
			return "";
		case 1:
			if(object instanceof Attribute)
				return "Attribute";
			else if (object instanceof NodeLink) {
				Node node = ((NodeLink)object).getDestination();
				if (node instanceof GroupNode) {
					return "Group";
				} else if (node instanceof DataNode) {
					return "Dataset";
				} else {
					return object.getClass().getConstructors()[0].getName();
				}
			}
			return "";
		case 2:
			if (object instanceof NodeLink) {
				Node node = ((NodeLink)object).getDestination();
				if (node instanceof DataNode) {
					DataNode datanode = (DataNode) node;
					int[] shape = datanode.getDataset().getShape();
					if (shape==null) return "";
					return Arrays.toString(shape);
				}
			}
			return "";
		case 3:
			if (object instanceof NodeLink) {
				Node node = ((NodeLink)object).getDestination();
				if (node instanceof DataNode) {
					DataNode datanode = (DataNode) node;
					String typename = datanode.getTypeName();
					return ""+typename;
				}
			}
			return "";
		case 4:
			if (object instanceof NodeLink) {
				Node node = ((NodeLink)object).getDestination();
				if (node instanceof DataNode) {
					DataNode datanode = (DataNode) node;
					ILazyDataset lazy = datanode.getDataset();
					long memSize = getSize(lazy);
					if (memSize<0) return "";
					return formatSize(memSize);
				}
			}
			return "";
		default:
			return null;
		}
	}

	private long getSize(ILazyDataset data) {
		try {
			IDataset slice = data.getSlice();
			int bpi = slice.getItemBytes();
			if (bpi != 0) {
				int[] shape = slice.getShape();
				if (shape == null)
					return -1;
				long size = shape[0];
				for (int i = 1; i < shape.length; i++)
					size *= shape[i];
				return bpi * size;
			} else {
				if (slice instanceof StringDataset) {
					StringDataset strdata = (StringDataset) slice;
					String[] dataArray = strdata.getData();
					String string = dataArray[0];
					long size = string.length();
					return size;
				}
				return -1;
			}
		} catch (Exception e) {
			logger.debug("Error getting size:" + e.getMessage());
			return -1;
		}
	}

	private static final double BASE = 1024, KB = BASE, MB = KB * BASE, GB = MB * BASE;
	private static final DecimalFormat df = new DecimalFormat("#.##");

	public static String formatSize(double size) {
		if (size >= GB) {
			return df.format(size / GB) + " GB";
		}
		if (size >= MB) {
			return df.format(size / MB) + " MB";
		}
		if (size >= KB) {
			return df.format(size / KB) + " KB";
		}
		return "" + (int) size + " bytes";
	}

	@Override
	public void dispose() {
	}

}
