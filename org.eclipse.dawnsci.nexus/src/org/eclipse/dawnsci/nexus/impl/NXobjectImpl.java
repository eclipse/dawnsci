/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.DataNodeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.GroupNodeImpl;
import org.eclipse.dawnsci.nexus.NXobject;

public class NXobjectImpl extends GroupNodeImpl implements NXobject {

	protected static final long serialVersionUID = GroupNodeImpl.serialVersionUID;

	/**
	 * Name of attribute
	 */
	public static final String NX_CLASS = "NX_class";

	protected NXobjectImpl(long oid) {
		super(oid);
		Attribute a = TreeFactory.createAttribute(NX_CLASS);
		String n = getNXclass().getName();
		int i = n.lastIndexOf(".");
		a.setValue(n.substring(i + 1));
		addAttribute(a);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXobject.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <N extends NXobject> N getFirstChild(Class<N> nxClass) {
		for (NodeLink n : this) {
			if (!n.isDestinationGroup())
				continue;
			GroupNode g = (GroupNode) n.getDestination();
			if (g.getClass().equals(nxClass)) {
				return (N) g;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <N extends NXobject> N getChild(String name, Class<N> nxClass) {
		GroupNode g = getGroupNode(name);
		if (g == null || g.getClass().equals(nxClass))
			return null;
		return (N) g;
	}

	@SuppressWarnings("unchecked")
	protected <N extends NXobject> Map<String, N> getChildren(Class<N> nxClass) {
		Map<String, N> map = new LinkedHashMap<>();
		for (NodeLink n : this) {
			if (!n.isDestinationGroup())
				continue;
			GroupNode g = (GroupNode) n.getDestination();
			if (g.getClass().equals(nxClass)) {
				map.put(n.getName(), (N) g);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	protected <N extends NXobject> void putChild(N child) {
		Class<N> nxClass = (Class<N>) child.getClass();
		for (NodeLink n : this) {
			if (!n.isDestinationGroup())
				continue;
			GroupNode g = (GroupNode) n.getDestination();
			if (g.getClass().equals(nxClass)) {
				addGroupNode(n.getPath(), n.getName(), child);
				return;
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <N extends NXobject> void putChild(String name, N child) {
		String path = null;
		if (containsGroupNode(name)) {
			NodeLink n = getNodeLink(name);
			GroupNode g = (GroupNode) n.getDestination();
			Class<N> nxClass = (Class<N>) child.getClass();
			if (!g.getClass().equals(nxClass)) {
				throw new IllegalArgumentException("There is a group of given name but of a different NX class");
			}
			path = n.getPath();
		}

		if (path == null) {
			for (NodeLink n : this) {
				if (path == null) {
					path = n.getPath();
				}
			}
		}
		addGroupNode(path, name, child);
	}

	@SuppressWarnings("unchecked")
	protected <N extends NXobject> void setChildren(Map<String, N> map) {
		map = new LinkedHashMap<>(map);
		String path = null;
		for (NodeLink n : this) {
			if (path == null) {
				path = n.getPath();
			}
			if (!n.isDestinationGroup())
				continue;
			if (map.containsKey(n.getName())) {
				N child = map.remove(n.getName());
				GroupNode g = (GroupNode) n.getDestination();
				if (g.getClass().equals(child.getClass())) {
					addGroupNode(n.getPath(), n.getName(), child);
					map.put(n.getName(), (N) g);
				}
			}
		}
		for (String n : map.keySet()) {
			N child = map.get(n);
			addGroupNode(path, n, child);
		}
	}

	protected String getString(String name) {
		if (!containsDataNode(name)) {
			return null;
		}
		return getDataNode(name).getString();
	}

	protected void setString(String name, String value) {
		if (containsDataNode(name)) {
			DataNodeImpl n = getDataNode(name);
			if (!n.isString()) {
				throw new IllegalArgumentException("Node is not a string");
			}
			n.setString(value);
		}
	}

	protected IDataset getDataset(String name) {
		if (!containsDataNode(name)) {
			return null;
		}
		return getCached(name);
	}

	protected void setDataset(String name, IDataset value) {
		if (containsDataNode(name)) {
			DataNodeImpl n = getDataNode(name);
			n.setDataset(value);
		}
	}

	protected Map<String, Dataset> getAllDatasets(String attrName) {
		Map<String, Dataset> map = new LinkedHashMap<>();
		
		for (NodeLink n : this) {
			if (!n.isDestinationData())
				continue;
			if (attrName != null) {
				Node d = n.getDestination();
				if (!d.containsAttribute(attrName))
					continue;
			}
			map.put(n.getName(), getCached(n.getName()));
		}
		return map;
	}

	private static final int CACHE_LIMIT = 1024;

	private Map<String, Dataset> cached = new HashMap<>();

	private Dataset getCached(String name) {
		if (!cached.containsKey(name)) {
			DataNodeImpl dn = getDataNode(name);
			ILazyDataset lazy = dn.getDataset();
			if (!(lazy instanceof IDataset)) {
				int size = lazy.getSize();
				if (size > CACHE_LIMIT) {
					int rank = lazy.getRank();
					int[] shape = lazy.getShape();
					// build up slice from last dimension
					int[] stop = new int[rank];
					int t = 1;
					int i = rank - 1;
					do {
						stop[i] = shape[i];
						t *= shape[i];
					} while (t < CACHE_LIMIT);
					while (i >= 0) {
						stop[i--] = 1;
					}
					lazy = lazy.getSliceView(new SliceND(shape, null, stop, null));
				} else {
					lazy = lazy.getSlice();
				}
			}
			cached.put(name, DatasetUtils.convertToDataset((IDataset) lazy));
		}
		return cached.get(name);
	}

	protected boolean getBoolean(String name) {
		Dataset d = getCached(name);
		return d.getElementLongAbs(0) != 0;
	}

	protected long getLong(String name) {
		Dataset d = getCached(name);
		return d.getElementLongAbs(0);
	}

	protected double getDouble(String name) {
		Dataset d = getCached(name);
		return d.getElementDoubleAbs(0);
	}

	protected Number getNumber(String name) {
		Dataset d = getCached(name);
		if (d.hasFloatingPointElements())
			return d.getElementDoubleAbs(0);
		return d.getElementLongAbs(0);
	}

	protected Date getDate(String name) {
		try {
			return DateFormat.getDateTimeInstance().parse(getString(name));
		} catch (ParseException e) {
			return null;
		}
	}

	protected void set(String name, Object value) {
		Dataset d = getCached(name);
		d.setObjectAbs(0, value);
	}

	protected void setDate(String name, Date date) {
		setString(name, DateFormat.getDateTimeInstance().format(date));
	}

	private Node getNode(String name) {
		NodeLink link = null;
		link = getNodeLink(name);
		if (link == null) {
			throw new IllegalArgumentException("Node not in group");
		}
		return link.getDestination();
	}

	private static String makeAttributeKey(String name, String attrName) {
		return name == null ? ATTRIBUTE + attrName : name + ATTRIBUTE + attrName;
	}

	/**
	 * @param name name of node (if null then current group)
	 * @param attrName
	 * @param attrValue
	 */
	protected void setAttribute(String name, String attrName, Object attrValue) {
		Node node = name == null ? this : getNode(name);
		Attribute a = node.containsAttribute(attrName) ? node.getAttribute(attrName) : TreeFactory.createAttribute(attrName);
		a.setValue(attrValue);
		node.addAttribute(a);
		cached.put(makeAttributeKey(name, attrName), DatasetUtils.convertToDataset(a.getValue()));
	}

	private Dataset getCachedAttribute(String name, String attrName) {
		String key = makeAttributeKey(name, attrName);
		if (!cached.containsKey(key)) {
			Node node = name == null ? this : getNode(name);
			Attribute a = node.getAttribute(attrName);
			cached.put(key, DatasetUtils.convertToDataset(a.getValue()));
		}

		return cached.get(key);
	}

	protected Dataset getAttr(String name, String attrName) {
		return getCachedAttribute(name, attrName);
	}

	protected String getAttrString(String name, String attrName) {
		Node node = name == null ? this : getNode(name);
		Attribute a = node.getAttribute(attrName);
		return a.getFirstElement();
	}

	protected boolean getAttrBoolean(String name, String attrName) {
		Dataset d = getCachedAttribute(name, attrName);
		return d.getElementLongAbs(0) != 0;
	}

	protected long getAttrLong(String name, String attrName) {
		Dataset d = getCachedAttribute(name, attrName);
		return d.getElementLongAbs(0);
	}

	protected double getAttrDouble(String name, String attrName) {
		Dataset d = getCachedAttribute(name, attrName);
		return d.getElementDoubleAbs(0);
	}

	protected Number getAttrNumber(String name, String attrName) {
		Dataset d = getCachedAttribute(name, attrName);
		return d.hasFloatingPointElements() ? d.getElementDoubleAbs(0) : d.getElementLongAbs(0);
	}

	protected Date getAttrDate(String name, String attrName) {
		try {
			return DateFormat.getDateTimeInstance().parse(getAttrString(name, attrName));
		} catch (ParseException e) {
			return null;
		}
	}

	protected void setAttrDate(String name, String attrName, Date date) {
		setAttribute(name, attrName, DateFormat.getDateTimeInstance().format(date));
	}
}
