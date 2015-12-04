/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus.hdf5;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.api.tree.TreeUtils;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyWriteableDataset;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.hdf5.HDF5AttributeResource;
import org.eclipse.dawnsci.hdf5.HDF5DatasetResource;
import org.eclipse.dawnsci.hdf5.HDF5DataspaceResource;
import org.eclipse.dawnsci.hdf5.HDF5DatatypeResource;
import org.eclipse.dawnsci.hdf5.HDF5FileFactory;
import org.eclipse.dawnsci.hdf5.HDF5LazyLoader;
import org.eclipse.dawnsci.hdf5.HDF5LazySaver;
import org.eclipse.dawnsci.hdf5.HDF5ObjectResource;
import org.eclipse.dawnsci.hdf5.HDF5PropertiesResource;
import org.eclipse.dawnsci.hdf5.HDF5Resource;
import org.eclipse.dawnsci.hdf5.HDF5Utils;
import org.eclipse.dawnsci.hdf5.HDF5Utils.DatasetType;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.hdf5.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusUtils;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.hdf5lib.exceptions.HDF5Exception;
import ncsa.hdf.hdf5lib.exceptions.HDF5LibraryException;
import ncsa.hdf.hdf5lib.structs.H5G_info_t;
import ncsa.hdf.hdf5lib.structs.H5L_info_t;
import ncsa.hdf.hdf5lib.structs.H5O_info_t;

public class NexusFileHDF5 implements NexusFile {

	private static final Logger logger = LoggerFactory.getLogger(NexusFileHDF5.class);

	//TODO: Clean up and move stuff to helper classes?


	public enum NodeType {
		GROUP(HDF5Constants.H5O_TYPE_GROUP),
		DATASET(HDF5Constants.H5O_TYPE_DATASET);
		public final int value;
		private static Map<Integer, NodeType> map = new HashMap<Integer, NodeType>();

		static {
			for (NodeType nt : NodeType.values()) {
				map.put(nt.value, nt);
			}
		}

		NodeType(int value) {
			this.value = value;
		}

		public static NodeType valueOf(int type) {
			return map.get(type);
		}
	}

	final class ParsedNode {
		public final String name;
		public final String nxClass;
		ParsedNode(String name, String nxClass) {
			this.name = name;
			this.nxClass = nxClass;
		}
	}

	final class NodeData {
		public final String name;
		public final String nxClass;
		public final String path;
		public final Node node;
		public final NodeType type;
		NodeData(String name, String nxClass, String path, Node node, NodeType type) {
			this.name = name;
			this.nxClass = nxClass;
			this.path = path;
			this.node = node;
			this.type = type;
		}
	}

	private long fileId = -1;

	private String fileName;

	private TreeFile tree;

	private static long ROOT_NODE_ADDR = -1234;

	private Map<Long, Node> nodeMap; //used to remember node locations in file for detecting hard links

	private boolean writeable = false;

	private Map<Node, String> passedNodeMap; // associate given nodes with "canonical" path (used for working out hardlinks)

	private boolean useSWMR = false;

	public NexusFileHDF5(String path) {
		this(path, false);
	}

	public NexusFileHDF5(String path, boolean enableSWMR) {
		fileName = path;
		useSWMR  = enableSWMR;
	}

	private void initializeTree() {
		if (tree == null) {
			tree = TreeFactory.createTreeFile(fileName.hashCode(), fileName);
			((TreeFileImpl) tree).setGroupNode(NexusNodeFactory.createNXroot(0l));
			nodeMap = new HashMap<Long, Node>();
			passedNodeMap = new HashMap<Node, String>();
		} else {
			throw new IllegalStateException("File is already open");
		}
	}

	private ParsedNode[] parseAugmentedPath(String path) {
		if (!path.startsWith(Tree.ROOT)) {
			throw new IllegalArgumentException("Path must be absolute");
		}
		String[] parts = path.split(Node.SEPARATOR);
		ParsedNode[] nodes = new ParsedNode[parts.length];
		int i = 0;
		for (String p : parts) {
			String[] pair = p.split(NXCLASS_SEPARATOR, 2);
			nodes[i++] = new ParsedNode(pair[0], pair.length > 1 ? pair[1] : "");
		}
		return nodes;
	}

	private void assertOpen() {
		if (tree == null) {
			throw new IllegalStateException("File has been closed");
		}
	}

	private void assertCanWrite() {
		assertOpen();
		if (!writeable) {
			throw new IllegalStateException("Cannot write as opened as read-only");
		}
	}

	@Override
	public void openToRead() throws NexusException {
		try {
			fileId = HDF5FileFactory.acquireFile(fileName, false);
		} catch (ScanFileHolderException e) {
			throw new NexusException("Cannot open to read", e);
		}
		initializeTree();
	}

	@Override
	public void openToWrite(boolean createIfNecessary) throws NexusException {
		if (new java.io.File(fileName).exists()) {
			try {
				fileId = HDF5FileFactory.acquireFile(fileName, true);
			} catch (ScanFileHolderException e) {
				throw new NexusException("Cannot open to write", e);
			}
		} else if (createIfNecessary) {
			try {
				fileId = HDF5FileFactory.acquireFile(fileName, true);
			} catch (ScanFileHolderException e) {
				throw new NexusException("Cannot create to write", e);
			}
		} else {
			throw new NexusException("File not found and not created");
		}
		initializeTree();
		writeable = true;
	}

	@Override
	public void createAndOpenToWrite() throws NexusException {
		try {
			fileId = HDF5FileFactory.acquireFileAsNew(fileName, useSWMR);
		} catch (ScanFileHolderException e) {
			throw new NexusException("Cannot create to write", e);
		}
		initializeTree();
		writeable = true;
	}

	@Override
	public void setDebug(boolean debug) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPath(Node node) {
		return TreeUtils.getPath(tree,  node);
	}

	@Override
	public GroupNode getGroup(String augmentedPath, boolean createPathIfNecessary) throws NexusException {
		assertOpen();
		String plainPath = NexusUtils.stripAugmentedPath(augmentedPath);
		NodeLink link = tree.findNodeLink(plainPath);
		if (link != null) {
			if (link.isDestinationGroup()) {
				GroupNode g = (GroupNode) link.getDestination();
				if (!g.isPopulated() && getLinkTarget(plainPath) != NO_LINK) {
					//"leaf" group nodes are never populated and paths that traverse napimounts cannot be populated
					//via the simple path mechanism
					if (!plainPath.endsWith(Node.SEPARATOR)) {
						plainPath += Node.SEPARATOR;
					}
					populateGroupNode(plainPath, g);
				}
				return g;
			}
			throw new NexusException("Path specified is not for a group");
		}
		NodeData node = getGroupNode(augmentedPath, createPathIfNecessary);
		//NodeData node = getNode(augmentedPath, createPathIfNecessary);
		return (GroupNode) (node.name == null ? null : (node.node));
	}

	@Override
	public GroupNode getGroup(GroupNode group,
			String name,
			String nxClass,
			boolean createPathIfNecessary) throws NexusException {
		String path = getPath(group);
		if (path == null) {
			throw new NullPointerException("Group path must not be null");
		}
		return getGroup(NexusUtils.addToAugmentPath(path,  name,  nxClass), createPathIfNecessary);
	}

	private void createGroupNode(long oid, GroupNode group, String path, String name, String nxClass)
			throws NexusException {
		GroupNode g;
		long fileAddr = getLinkTarget(path + Node.SEPARATOR + name);
		if (!nodeMap.containsKey(fileAddr)) {
			// create the new group, a subclass of NXobject if nxClass is set. Note nxClass is not yet known when loading
			if (nxClass == null || nxClass.equals("")) {
				g = TreeFactory.createGroupNode(oid);
			} else {
				try {
					g = NexusNodeFactory.createNXobjectForClass(nxClass, oid);
				} catch (IllegalArgumentException e) {
					logger.warn("Attribute {} was {} but not a known one", NXCLASS, nxClass);
					g = TreeFactory.createGroupNode(oid);
				}
			}
			if (nxClass != null && !nxClass.isEmpty()) {
				g.addAttribute(TreeFactory.createAttribute(NXCLASS, nxClass, false));
			}
			cacheAttributes(path + Node.SEPARATOR + name, g);
			// if the new attributes now includes an nxClass attribute, create
			// the appropriate subclass of NXobject (TODO is there a better way of doing this?)
			if (!(g instanceof NXobject) && g.getAttribute(NXCLASS) != null) {
				nxClass = g.getAttribute(NXCLASS).getFirstElement();
				if (nxClass != null && !nxClass.isEmpty()) {
					try {
						g = NexusNodeFactory.createNXobjectForClass(nxClass, oid);
						cacheAttributes(path + Node.SEPARATOR + name, g);
					} catch (IllegalArgumentException e) {
						logger.warn("Attribute {} was {} but not a known one", NXCLASS, nxClass);
					}
				}
			}

			if (fileAddr != IS_EXTERNAL_LINK && fileAddr != NO_LINK &&  !testForExternalLink(path)) {
				//if our node is an external link we cannot cache its file location
				//we cannot handle hard links in external files
				nodeMap.put(fileAddr, g);
			}
		} else {
			g = (GroupNode)nodeMap.get(fileAddr);
		}
		group.addGroupNode(name, g);
	}

	public static final Charset UTF8 = Charset.forName("UTF-8");

	private void cacheAttributes(String path, Node node) throws NexusException {
		try {
			H5O_info_t objInfo = H5.H5Oget_info_by_name(fileId, path, HDF5Constants.H5P_DEFAULT);
			long numAttrs = objInfo.num_attrs;
			for (long i = 0; i < numAttrs; i++) {
				
				Dataset dataset = HDF5Utils.getAttrDataset(fileId, path, i);
				if (dataset == null || node.containsAttribute(dataset.getName())) {
					//we don't need to read an attribute we already have
					continue;
				}
				node.addAttribute(createAttribute(dataset));
			}
		} catch (HDF5Exception e) {
			throw new NexusException("Could not retrieve node attributes");
		}
		return;
	}

	private void populateGroupNode(String path, GroupNode group) throws NexusException {
		cacheAttributes(path, group);
		try {
			H5G_info_t groupInfo = H5.H5Gget_info_by_name(fileId, path, HDF5Constants.H5P_DEFAULT);
			for (long i = 0; i < groupInfo.nlinks; i++) {
				//we have to open the object itself to handle external links
				//H5.H5Lget_name_by_idx(fileId, "X", ....) will fail if X is an external link node, as will similar methods
				try (HDF5Resource objResource = new HDF5ObjectResource( H5.H5Oopen(fileId, path, HDF5Constants.H5P_DEFAULT) )) {
					long objId = objResource.getResource();
					String linkName = H5.H5Lget_name_by_idx(objId, ".", HDF5Constants.H5_INDEX_NAME,
							HDF5Constants.H5_ITER_INC, i, HDF5Constants.H5P_DEFAULT);
					H5L_info_t linkInfo = H5.H5Lget_info_by_idx(objId, ".", HDF5Constants.H5_INDEX_NAME,
							HDF5Constants.H5_ITER_INC, i, HDF5Constants.H5P_DEFAULT);
					if (linkInfo.type == HDF5Constants.H5L_TYPE_EXTERNAL) {
						String[] value = new String[2];
						H5.H5Lget_val(objId, linkName, value, HDF5Constants.H5P_DEFAULT);
						String extFilePath = value[1];
						if (!new File(extFilePath).exists()) {
							//TODO: cache "lazy" node
							//this results on a potentially invalid cache
							continue;
						}
					}
					String childPath = path + linkName;
					H5O_info_t objectInfo = H5.H5Oget_info_by_name(fileId, childPath, HDF5Constants.H5P_DEFAULT);
					if (objectInfo.type == HDF5Constants.H5O_TYPE_GROUP) {
						createGroupNode(childPath.hashCode(), group, path, linkName, "");
					} else if (objectInfo.type == HDF5Constants.H5O_TYPE_DATASET) {
						group.addDataNode(linkName, getDataNodeFromFile(childPath, group, linkName));
					} else {
						throw new NexusException("Unhandled object type");
					}
				}
			}
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not process over child links", e);
		}
		return;
	}

	private static String determineExternalFilePath(String filePathFragment, String currentFile) throws NexusException {
		//try filepath as relative location first, then absolute
		String currentLocation = currentFile.substring(0, currentFile.lastIndexOf("/") + 1);
		if (new File(currentLocation + filePathFragment).exists()) {
			return currentLocation + filePathFragment;
		} else if (new File(filePathFragment).exists()) {
			return filePathFragment;
		}
		throw new NexusException("Could not find specified file");
	}

	private boolean isNapiMount(Node node) {
		return node.containsAttribute("napimount");
	}

	private NodeData getNodeAfterNapiMount(String fileName,
			String externalMountPoint,
			String internalMountPoint,
			String pathAfterMount,
			GroupNode parentGroup) throws NexusException {

		//We open a *new* NexusFile, have it process its own cache, then just add the bit
		//we're interested in into our own cache
		Node childNode;
		try (NexusFileHDF5 extFile = new NexusFileHDF5(fileName)) {
			String fullPathInExtFile = externalMountPoint + Node.SEPARATOR + pathAfterMount;
			extFile.openToRead();
			ParsedNode[] parsed = parseAugmentedPath(internalMountPoint);
			String nodeName = parsed[parsed.length - 1].name;
			switch(extFile.getNodeType(externalMountPoint)) {
			case DATASET:
				throw new NexusException("Invalid napimount - must not point to dataset");
			case GROUP:
				//we have to add the mounted node and process the new tree
				childNode = extFile.getGroup(externalMountPoint, false);
				String internalPath = internalMountPoint + pathAfterMount;

				GroupNode newNode = TreeFactory.createGroupNode(internalPath.hashCode());
				//we want a copy of the node in the other file since we do not want to
				//adding attributes in the other file's cache
				//this is a bit of a hack
				GroupNode originalNode = (GroupNode) childNode;
				cacheAttributes(internalMountPoint, newNode);
				Iterator<? extends Attribute> attributes = originalNode.getAttributeIterator();
				Iterator<String> names = originalNode.getNodeNameIterator();
				while (attributes.hasNext()) {
					Attribute attr = attributes.next();
					newNode.addAttribute(attr);
				}
				while (names.hasNext()) {
					String name = names.next();
					NodeLink link = originalNode.getNodeLink(name);
					newNode.addNode(name, link.getDestination());
				}
				parentGroup.addGroupNode(nodeName, newNode);
				return extFile.getNode(fullPathInExtFile, false);
			}
		}
		return null;
	}

	private NodeData getGroupNode(String augmentedPath, boolean createPathIfNecessary) throws NexusException {
		NodeData node = getNode(augmentedPath, createPathIfNecessary);
		if (node.type != NodeType.GROUP) {
			throw new NexusException("Found dataset node instead of group node");
		}
		return node;
	}

	/**
	 * Try to retrieve or create the target node (and parents), building cache as necessary.
	 * Cannot create data nodes
	 */
	private NodeData getNode(String augmentedPath, boolean createPathIfNecessary) throws NexusException {
		ParsedNode[] parsedNodes = parseAugmentedPath(augmentedPath);
		StringBuilder pathBuilder = new StringBuilder(Tree.ROOT);
		String parentPath = pathBuilder.toString();
		GroupNode group = tree.getGroupNode();
		if (parsedNodes.length < 1) {
			return new NodeData(Tree.ROOT, null, null, group, NodeType.GROUP);
		}
		Node node = group;
		ParsedNode parsedNode = parsedNodes[0];
		NodeType type = null;
		//traverse to target node
		for (int i = 0; i < parsedNodes.length; i++) {
			parsedNode = parsedNodes[i];
			if (parsedNode.name.isEmpty()) {
				continue;
			}
			parentPath = pathBuilder.toString();
			pathBuilder.append(parsedNode.name);
			pathBuilder.append(Node.SEPARATOR);
			String path = pathBuilder.toString();
			type = getNodeType(path);
			if (type == null || type == NodeType.GROUP) {
				//make sure the group actually exists
				long nodeId = openGroup(path, parsedNode.nxClass, createPathIfNecessary);
				closeNode(nodeId);
				type = NodeType.GROUP;
			} else if (type == NodeType.DATASET) {
				//should be last iteration of loop (final path segment)
				if (i < parsedNodes.length - 1) {
					throw new NexusException("Dataset node should only be final path segment");
				}
				//dataset node *must* exist on disk because we are not creating
				//so just establish in cache and return that object
				if (group.containsDataNode(parsedNode.name)) {
					node = group.getDataNode(parsedNode.name);
				} else {
					node = getData(path);
					group.addDataNode(parsedNode.name, (DataNode) node);
				}
				break;
			} else {
				throw new NexusException("Unhandled node type");
			}
			//establish group in our cache
			try {
				H5L_info_t linkInfo = (H5.H5Lget_info(fileId, path, HDF5Constants.H5P_DEFAULT));
				if (linkInfo.type == HDF5Constants.H5L_TYPE_SOFT) {
					String[] name = new String[2];
					H5.H5Lget_val(fileId, path, name, HDF5Constants.H5P_DEFAULT);
					path = name[0];
					if (!group.containsGroupNode(parsedNode.name)) {
						NodeData linkedNode = getGroupNode(path, false);
						group.addGroupNode(parsedNode.name, (GroupNode)linkedNode.node);
					}
				}
			} catch (HDF5LibraryException e) {
				throw new NexusException("Could not query if path is soft link", e);
			}
			if (!group.containsGroupNode(parsedNode.name)) {
				createGroupNode(path.hashCode(), group, parentPath, parsedNode.name, parsedNode.nxClass);
			}
			GroupNode parentGroup = group;
			group = group.getGroupNode(parsedNode.name);
			if (!group.isPopulated()) {
				//make sure cached node looks like node on files
				populateGroupNode(path, group);
			}
			if (isNapiMount(group)) {
				parentGroup.removeGroupNode(group);
				IDataset mountData = group.getAttribute("napimount").getValue();
				String mountString = mountData.getString(0);
				mountString = mountString.replace("nxfile://", "");
				String[] parts = mountString.split("#");
				String extFileName = determineExternalFilePath(parts[0], this.fileName);
				String extMountPoint = Node.SEPARATOR + parts[1];
				StringBuilder pathAfterMount = new StringBuilder();
				for (int j = i + 1; j < parsedNodes.length; j++) {
					pathAfterMount.append(Node.SEPARATOR);
					pathAfterMount.append(parsedNodes[j].name);
				}
				return getNodeAfterNapiMount(extFileName, extMountPoint, path, pathAfterMount.toString(), parentGroup);
			}
			node = group;
		}
		return new NodeData(parsedNode.name, parsedNode.nxClass, parentPath, node, type);
	}

	private NodeType getNodeType(String absolutePath) throws NexusException {
		//TODO: inspect cache first (checking for napimounts, as they seriously mess things up)
		try {
			if (!testLinkExists(absolutePath)) {
				return null;
			}
			H5O_info_t info = H5.H5Oget_info_by_name(fileId, absolutePath, HDF5Constants.H5P_DEFAULT);
			NodeType type = NodeType.valueOf(info.type);
			if (type == null) {
				throw new NexusException("Unsupported object type");
			}
			return type;
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not get object information", e);
		}
	}

	private long openNode(String absolutePath) throws NexusException {
		if (!absolutePath.startsWith(Tree.ROOT)) {
			throw new IllegalArgumentException("Group path must be absolute");
		}
		try {
			return H5.H5Oopen(fileId, absolutePath, HDF5Constants.H5P_DEFAULT);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Cannot open object", e);
		}
	}

	private long openGroup(String absolutePath, String nxClass, boolean create) throws NexusException {
		NodeType type = getNodeType(absolutePath);
		long groupId;

		if (type == null) {
			if (create && writeable) {
				try {
					groupId = H5.H5Gcreate(fileId, absolutePath,
							HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);
				} catch (HDF5LibraryException e) {
					throw new NexusException("Cannot create group", e);
				}
				addAttribute(absolutePath, TreeFactory.createAttribute(NXCLASS, nxClass, false));
			} else {
				throw new NexusException("Group does not exist and cannot be created");
			}
		} else if (type != NodeType.GROUP) {
			throw new NexusException("Specified path is not a group");
		} else {
			groupId = openNode(absolutePath);
		}
		return groupId;
	}

	private long openDataset(String absolutePath) throws NexusException {
		NodeType type = getNodeType(absolutePath);
		if (type != NodeType.DATASET) {
			throw new NexusException("Path does not refer to dataset");
		}
		return openNode(absolutePath);
	}

	private void closeNode(long nodeId) throws NexusException {
		try {
			H5.H5Oclose(nodeId);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Cannot close node", e);
		}
	}

	private DataNode createDataNode(GroupNode parentNode, String path, String name,
			long[] shape, int datasetType, boolean unsigned, long[] maxShape, long[] chunks) throws NexusException {
		int[] iShape = shape == null ? null : HDF5Utils.toIntArray(shape);
		int[] iMaxShape = maxShape == null ? null : HDF5Utils.toIntArray(maxShape);
		int[] iChunks = chunks == null ? null : HDF5Utils.toIntArray(chunks);
		DataNode dataNode = TreeFactory.createDataNode(path.hashCode());
		cacheAttributes(path, dataNode);
		parentNode.addDataNode(name, dataNode);
		dataNode.setUnsigned(unsigned);
		ILazyDataset lazyDataset = null;
		int itemSize = 1;
		boolean extendUnsigned = false;
		Object[] fill = getFillValue(datasetType);
		if (writeable) {
			lazyDataset = new LazyWriteableDataset(name, datasetType, iShape, iMaxShape, iChunks,
					new HDF5LazySaver(null, fileName, path, name, iShape, itemSize,
							datasetType, extendUnsigned, iMaxShape, iChunks, fill));
		} else {
			lazyDataset = new LazyDataset(name, datasetType, iShape,
					new HDF5LazyLoader(null, fileName, path, name, iShape, itemSize,
							datasetType, extendUnsigned));
		}
		dataNode.setDataset(lazyDataset);
		if (!testForExternalLink(path)) {
			nodeMap.put(getLinkTarget(path), dataNode);
		}
		return dataNode;
	}

	private DataNode getDataNodeFromFile(String path, GroupNode parentNode, String dataName) throws NexusException {
		if (!testForExternalLink(path)) {
			DataNode node = (DataNode) nodeMap.get(getLinkTarget(path));
			if (node != null) {
				return node;
			}
		}
		long[] shape = null;
		long[] maxShape = null;
		long[] chunks = null;
		DatasetType type;
		try (HDF5Resource hdfDataset = new HDF5DatasetResource(openDataset(path));
				HDF5Resource hdfDataspace = new HDF5DataspaceResource( H5.H5Dget_space(hdfDataset.getResource()) );
				HDF5Resource hdfDatatype = new HDF5DatatypeResource( H5.H5Dget_type(hdfDataset.getResource()) );
				 HDF5Resource hdfNativetype = new HDF5DatatypeResource( H5.H5Tget_native_type(hdfDatatype.getResource()))) {
			final long datasetId = hdfDataset.getResource();
			final long dataspaceId = hdfDataspace.getResource();
			type = HDF5Utils.getDatasetType(hdfDatatype.getResource(), hdfNativetype.getResource());
			if (type == null) {
				throw new NexusException("Unknown data type");
			}
			int nDims = H5.H5Sget_simple_extent_ndims(dataspaceId);
			try (HDF5Resource hdfProperties = new HDF5PropertiesResource(H5.H5Dget_create_plist(datasetId))) {
				long propertiesId = hdfProperties.getResource();
				if (H5.H5Pget_layout(propertiesId) == HDF5Constants.H5D_CHUNKED) {
					chunks = new long[nDims];
					H5.H5Pget_chunk(propertiesId, nDims, chunks);
				} else {
					//H5D_COMPACT || H5D_CONTIGUOUS can have chunk array set to the shape
					//or can leave null and dataset stuff assumes the same
				}
			}
			shape = new long[nDims];
			maxShape = new long[nDims];
			H5.H5Sget_simple_extent_dims(dataspaceId, shape, maxShape);
		} catch (HDF5Exception e) {
			throw new NexusException("Error reading dataspace", e);
		}
		return createDataNode(parentNode, path, dataName, shape, type.dtype, type.unsigned, maxShape, chunks);
	}

	@Override
	public DataNode getData(String path) throws NexusException {
		assertOpen();

		path = NexusUtils.stripAugmentedPath(path);
		//check if the data node itself is a symlink
		DataNode dataNode;
		long fileAddr = getLinkTarget(path);
		if (fileAddr != IS_EXTERNAL_LINK && fileAddr != NO_LINK) {
			Node node = nodeMap.get(fileAddr);
			if (node instanceof DataNode) {
				return (DataNode) node;
			} else if (node != null) {
				throw new IllegalArgumentException("Path specified is not for a dataset");
			}
		}

		NodeLink link = tree.findNodeLink(path);
		if (link != null) {
			if (link.isDestinationData()) {
				return (DataNode) link.getDestination();
			}
			throw new IllegalArgumentException("Path specified is not for a dataset");
		}

		String dataName = NexusUtils.getName(path);
		String parentPath = path.substring(0, path.lastIndexOf(dataName));
		NodeData parentNodeData = getGroupNode(parentPath, false);
		if (parentNodeData.name == null) {
			return null;
		}

		NodeType nodeType = getNodeType(path);
		if (nodeType == null || nodeType != NodeType.DATASET) {
			//inablity to find nodetype could indicate the path traverses a napimount
			Node potentialDataNode = getNode(path, false).node;
			if (potentialDataNode instanceof DataNode) {
				return (DataNode) potentialDataNode;
			}
			throw new NexusException("Path points to non-dataset object");
		}

		dataNode = getDataNodeFromFile(path, (GroupNode)parentNodeData.node, dataName);
		return dataNode;
	}

	@Override
	public DataNode getData(GroupNode group, String name) throws NexusException {
		String path = NexusUtils.addToAugmentPath(getPath(group), name, null);
		return getData(path);
	}

	private static final int MAX_CHUNK = 1024 * 1024; //default chunk cache is 1MB so we should fit in that.
	private static final int MIN_CHUNK = 8 * 1024;
	private static final int BASE_CHUNK = 16 * 1024;
	private static final int UNLIMITED_DIM_ESTIMATE = 64; //we'd like this to be lower than typical detector sizes

	//TODO: lustre might prefer larger chunks (1MB or 4MB)
	//      we might want to reconsider this later as a result
	private long[] estimateChunking(long[] shape, long[] maxshape, int typeSize) {
		//typesize is 1 for VLen structures which is off by a factor of 16 (ish).
		//unlikely to cause any issues unless we have a dataset containing 65000 strings
		boolean fixedSize = true;
		long[] chunk = new long[shape.length];
		boolean[] fixedDims = new boolean[shape.length];
		for (int i = 0; i < shape.length; i++) {
			fixedDims[i] = maxshape[i] == shape[i];
			fixedSize &= fixedDims[i];
			chunk[i] = maxshape[i] > 0 ? maxshape[i] : UNLIMITED_DIM_ESTIMATE; //we have to have *something* for unlimited dimensions
		}
		long rawDataSize = typeSize;
		for (long c : chunk) {
			rawDataSize *= c;
			assert rawDataSize > 0;
		}
		//heuristic copied from h5py
		//(2 << ... instead of 1 << ... because we want to "round up" the exponent)
		long targetSize = BASE_CHUNK * (2 << (int) Math.log10(rawDataSize / (1024 * 1024)));
		if (targetSize > MAX_CHUNK) {
			targetSize = MAX_CHUNK;
		} else if (targetSize < MIN_CHUNK) {
			targetSize = MIN_CHUNK;
		}

		long chunkByteSize = rawDataSize;
		//make sure we're over the minimum chunk size if possible
		if (!fixedSize) {
			while (chunkByteSize < targetSize) {
				int count = 0;
				for (int i = 0; i < chunk.length; i++) {
					if (!fixedDims[i]) {
						chunk[i] *= 2;
						count++;
					}
				}
				chunkByteSize <<= count;
			}
		}
		int idx = 0;
		//halve each axis in turn until we're under the maximum size, and smaller than the target size or within 50% of it
		while ( !((chunkByteSize < targetSize || (chunkByteSize - targetSize) / (double)targetSize < 0.5) &&
				chunkByteSize < MAX_CHUNK) ) {
			chunk[idx] = (long)Math.ceil(chunk[idx] / 2.0);
			chunkByteSize /= 2;
			long p = 1;
			for (long c : chunk) p *= c;
			if (p == 1) break;
			idx++;
			idx %= chunk.length;
		}
		return chunk;
	}

	@Override
	public DataNode createData(String path, ILazyWriteableDataset data, int compression, boolean createPathIfNecessary) throws NexusException {
		return createData(path, null, data, compression, createPathIfNecessary);
	}

	@Override
	public DataNode createData(String path, String name, ILazyWriteableDataset data, int compression, boolean createPathIfNecessary)
			throws NexusException {
		assertCanWrite();
		NodeData parentNode = getGroupNode(path, createPathIfNecessary);
		if (parentNode.name == null) {
			return null;
		}
		String parentPath = path.endsWith(Node.SEPARATOR) ? path : path + Node.SEPARATOR;
		if (name == null) {
			name = data.getName();
		}
		if (name == null || name.isEmpty()) {
			throw new NullPointerException("Dataset name must be defined");
		}
		String dataPath = parentPath + name;
		if (isPathValid(dataPath)) {
			throw new NexusException("Object already exists at specified location");
		}

		int itemSize = 1;
		int dataType = AbstractDataset.getDType(data);
		int[] iShape = data.getShape();
		int[] iMaxShape = data.getMaxShape();
		int[] iChunks = data.getChunking();
		Object[] fillValue = getFillValue(data.elementClass());

		long[] shape = HDF5Utils.toLongArray(iShape);
		long[] maxShape = HDF5Utils.toLongArray(iMaxShape);
		long[] chunks = HDF5Utils.toLongArray(iChunks);
		boolean stringDataset = data.elementClass().equals(String.class);
		long hdfType = getHDF5Type(data);
		try {
			try (HDF5Resource hdfDatatype = new HDF5DatatypeResource(H5.H5Tcopy(hdfType));
					HDF5Resource hdfDataspace = new HDF5DataspaceResource(H5.H5Screate_simple(shape.length, shape, maxShape));
					HDF5Resource hdfProperties = new HDF5PropertiesResource(H5.H5Pcreate(HDF5Constants.H5P_DATASET_CREATE))) {

				final long hdfPropertiesId = hdfProperties.getResource();
				final long hdfDatatypeId = hdfDatatype.getResource();
				final long hdfDataspaceId = hdfDataspace.getResource();

				boolean recalcChunks = true;
				if (chunks != null) {
					for (long c : chunks) {
						if (c > 1) {
							recalcChunks = false;
							break;
						}
					}
				}
				//chunks == null check is unnecessary, but compiler warns otherwise
				if (!Arrays.equals(shape, maxShape) && (recalcChunks || chunks == null || chunks[chunks.length - 1] == 1)) {
					logger.warn("Inappropriate chunking requested for {}; attempting to estimate suitable chunking.", name);
					chunks = estimateChunking(shape, maxShape, H5.H5Tget_size(hdfDatatypeId));
					iChunks = HDF5Utils.toIntArray(chunks);
					data.setChunking(iChunks);
				}

				if (stringDataset) {
					H5.H5Tset_cset(hdfDatatypeId, HDF5Constants.H5T_CSET_UTF8);
					H5.H5Tset_size(hdfDatatypeId, HDF5Constants.H5T_VARIABLE);
				} else if (fillValue != null) {
					//Strings must not have a fill value set
					H5.H5Pset_fill_value(hdfPropertiesId, hdfDatatypeId, fillValue);
				}
				if (chunks != null) {
					//these have to be set in this order
					H5.H5Pset_layout(hdfPropertiesId, HDF5Constants.H5D_CHUNKED);
					H5.H5Pset_chunk(hdfPropertiesId, chunks.length, chunks);
				}
				int deflateLevel = 0;
				switch (compression) {
				case COMPRESSION_LZW_L1:
					deflateLevel = 1;
					break;
				default:
					compression = COMPRESSION_NONE;
					break;
				}
				if (compression != COMPRESSION_NONE) {
					H5.H5Pset_deflate(hdfPropertiesId, deflateLevel);
				}
				long datasetId = H5.H5Dcreate(fileId, dataPath, hdfDatatypeId, hdfDataspaceId,
						HDF5Constants.H5P_DEFAULT, hdfPropertiesId, HDF5Constants.H5P_DEFAULT);
				H5.H5Dclose(datasetId);
			}
		} catch (HDF5Exception e) {
			throw new NexusException("Could not create dataset", e);
		}

		HDF5LazySaver saver = new HDF5LazySaver(null, fileName, parentPath, name,
				iShape, itemSize, dataType, false, iMaxShape, iChunks, fillValue);
		data.setSaver(saver);

		DataNode dataNode = TreeFactory.createDataNode(dataPath.hashCode());
		((GroupNode)parentNode.node).addDataNode(name, dataNode);
		dataNode.setDataset(data);
		long fileAddr = getLinkTarget(dataPath);
		nodeMap.put(fileAddr, dataNode);
		return dataNode;
	}

	@Override
	public DataNode createData(String path, ILazyWriteableDataset data, boolean createPathIfNecessary) throws NexusException {
		return createData(path, null, data, createPathIfNecessary);
	}

	@Override
	public DataNode createData(String path, String name, ILazyWriteableDataset data, boolean createPathIfNecessary)
			throws NexusException {
		return createData(path, name, data, COMPRESSION_NONE, createPathIfNecessary);
	}

	@Override
	public DataNode createData(GroupNode group, ILazyWriteableDataset data, int compression) throws NexusException {
		return createData(group, null, data, compression);
	}

	@Override
	public DataNode createData(GroupNode group, String name, ILazyWriteableDataset data, int compression) throws NexusException {
		String path = getPath(group);
		return createData(path, name, data, compression, true);
	}

	@Override
	public DataNode createData(GroupNode group, ILazyWriteableDataset data) throws NexusException {
		return createData(group, null, data);
	}

	@Override
	public DataNode createData(GroupNode group, String name, ILazyWriteableDataset data) throws NexusException {
		String path = getPath(group);
		return createData(path, name, data, true);
	}

	@Override
	public DataNode createData(String path, IDataset data, boolean createPathIfNecessary) throws NexusException {
		return createData(path, null, data, createPathIfNecessary);
	}

	@Override
	public DataNode createData(String path, String name, IDataset data, boolean createPathIfNecessary) throws NexusException {
		assertCanWrite();

		NodeData parentNode = getGroupNode(path, createPathIfNecessary);
		if (name == null) {
			name = data.getName();
		}
		if (name == null || name.isEmpty()) {
			throw new NullPointerException("Dataset name must be defined");
		}

		String dataPath = parentNode.path + parentNode.name + Node.SEPARATOR + name;
		if (isPathValid(dataPath)) {
			throw new NexusException("Object already exists at specified location");
		}

		boolean stringDataset = data.elementClass().equals(String.class);//ngd.isChar();
		final long[] shape = data.getRank() == 0 ? new long[] {1} : HDF5Utils.toLongArray(data.getShape());

		long type = getHDF5Type(data);

		try {
			try (HDF5Resource hdfDatatype = new HDF5DatatypeResource(H5.H5Tcopy(type));
					HDF5Resource hdfDataspace = new HDF5DataspaceResource(
							H5.H5Screate_simple(shape.length, shape, (long[])null))) {

				final long datatypeId = hdfDatatype.getResource();
				final long dataspaceId = hdfDataspace.getResource();
				if (stringDataset) {
					H5.H5Tset_cset(datatypeId, HDF5Constants.H5T_CSET_UTF8);
					H5.H5Tset_size(datatypeId, HDF5Constants.H5T_VARIABLE);
				}
				try (HDF5Resource hdfDataset = new HDF5DatasetResource(
						H5.H5Dcreate(fileId, dataPath, datatypeId, dataspaceId,
								HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT));) {

					final long dataId = hdfDataset.getResource();
					if (stringDataset) {
						String[] strings = (String[])DatasetUtils.serializeDataset(data);
						H5.H5DwriteString(dataId, datatypeId, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, strings);
					} else {
						Serializable buffer = DatasetUtils.serializeDataset(data);
						H5.H5Dwrite(dataId, datatypeId, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buffer);
					}
				}
			}
		} catch (HDF5Exception e) {
			throw new NexusException("Could not create dataset", e);
		}
		DataNode dataNode = TreeFactory.createDataNode(dataPath.hashCode());
		long fileAddr = getLinkTarget(dataPath);
		nodeMap.put(fileAddr, dataNode);
		dataNode.setDataset(data);
		((GroupNode) parentNode.node).addDataNode(name, dataNode);
		return dataNode;
	}

	@Override
	public DataNode createData(GroupNode group, IDataset data) throws NexusException {
		return createData(group, null, data);
	}

	@Override
	public DataNode createData(GroupNode group, String name, IDataset data) throws NexusException {
		String path = getPath(group);
		return createData(path, name, data, true);
	}

	@Override
	public void addNode(GroupNode group, String name, Node node) throws NexusException {
		String parentPath = getPath(group);
		recursivelyUpdateTree(parentPath, name, node);
	}

	@Override
	public void addNode(String path, Node node) throws NexusException {
		if (path == null || path == "") {
			throw new IllegalArgumentException("Path name must be specified.");
		}
		if (path == "/") {
			recursivelyUpdateTree("/", null, node);
		} else {
			String[] components = path.split(Node.SEPARATOR);
			String name = components[components.length - 1];
			String parentPath = path.substring(0, path.lastIndexOf(name));
			recursivelyUpdateTree(parentPath, name, node);
		}
	}

	private void recursivelyUpdateTree(String parentPath, String name, Node node) throws NexusException {
		String nxClass = node.containsAttribute("NX_class") ? node.getAttribute("NX_class").getValue().getString(0) : "";
		String fullPath = parentPath + Node.SEPARATOR + (name == null ? "" : name);
		fullPath = fullPath.replaceAll("//", "/");
		NodeData parentNodeData = getNode(parentPath, false);
		GroupNode parentNode = (GroupNode) parentNodeData.node;
		if (passedNodeMap.containsKey(node)) {
			if (fullPath != passedNodeMap.get(node)) {
				createHardLink(passedNodeMap.get(node), fullPath);
			}
			return;
		}
		if (node instanceof GroupNode) {
			GroupNode updatingGroupNode = (GroupNode) node;
			if (!parentNode.containsGroupNode(name)) {
				if (nxClass.isEmpty()) {
					logger.warn("Adding node at " + fullPath + " without an NXclass");
				}
				long id = openGroup(fullPath, nxClass, true);
				closeNode(id);
			}
			GroupNode existingNode = (GroupNode) getNode(fullPath, false).node;
			Iterator<? extends Attribute> it = node.getAttributeIterator();
			while (it.hasNext()) {
				Attribute attr = it.next();
				if (!existingNode.containsAttribute(attr.getName())) {
					IDataset value = attr.getValue().getSlice();
					value.setName(attr.getName());
					addAttribute(existingNode, createAttribute(value));
				}
			}
			for (String childName : updatingGroupNode.getNames()) {
				Node childNode = updatingGroupNode.getNodeLink(childName).getDestination();
				recursivelyUpdateTree(fullPath, childName, childNode);
			}
		} else if (node instanceof DataNode) {
			DataNode updatingDataNode = (DataNode) node;
			if (!parentNode.containsDataNode(name)) {
				ILazyDataset dataset = updatingDataNode.getDataset();
				if ((dataset instanceof IDataset)) {
					createData(parentNode, name, (IDataset) dataset);
				} else if ((dataset instanceof ILazyWriteableDataset)) {
					createData(parentNode, name, (ILazyWriteableDataset) dataset);
				} else {
					throw new NexusException("Unrecognised dataset type");
				}
			}
			DataNode existingNode = getData(parentNode, name);
			Iterator<? extends Attribute> it = node.getAttributeIterator();
			while (it.hasNext()) {
				Attribute attr = it.next();
				if (!existingNode.containsAttribute(attr.getName())) {
					IDataset value = attr.getValue().getSlice();
					value.setName(attr.getName());
					addAttribute(existingNode, createAttribute(value));
				}
			}
		} else {
			throw new NexusException("Node to update is not a group or data node");
		}
		passedNodeMap.put(node, fullPath);
	}

	@Override
	public Attribute createAttribute(IDataset attr) {
		assertOpen();
		Attribute a = TreeFactory.createAttribute(attr.getName());
		a.setValue(attr);
		return a;
	}

	@Override
	public void addAttribute(String path, Attribute... attribute) throws NexusException {
		assertCanWrite();
		Charset utf8 = Charset.forName("UTF-8");
		for (Attribute attr : attribute) {
			String attrName = attr.getName();
			if (attrName == null || attrName.isEmpty()) {
				throw new NullPointerException("Attribute must have a name");
			}
			Node node = getNode(path, false).node;
			node.addAttribute(attr);
			try {
				//if an attribute with the same name already exists, we delete it to be consistent with NAPI
				if (H5.H5Aexists_by_name(fileId, path, attrName, HDF5Constants.H5P_DEFAULT)) {
					try {
						H5.H5Adelete_by_name(fileId, path, attrName, HDF5Constants.H5P_DEFAULT);
					} catch (HDF5LibraryException e) {
						throw new NexusException("Could not delete existing attribute", e);
					}
				}
			} catch (HDF5LibraryException e) {
				throw new NexusException("Error inspecting existing attributes", e);
			}
			IDataset attrData = attr.getValue();
			long baseHdf5Type = getHDF5Type(attrData);
			final long[] shape = attrData.getRank() == 0 ? new long[] {1} : HDF5Utils.toLongArray(attrData.getShape());
			try {
				try (HDF5Resource typeResource = new HDF5DatatypeResource(H5.H5Tcopy(baseHdf5Type));
						HDF5Resource spaceResource = new HDF5DataspaceResource(
								H5.H5Screate_simple(shape.length, shape, shape))) {

					long datatypeId = typeResource.getResource();
					long dataspaceId = spaceResource.getResource();
					boolean stringDataset = attrData.elementClass().equals(String.class);
					Serializable buffer = DatasetUtils.serializeDataset(attrData);
					if (stringDataset) {
						String[] strings = (String[]) buffer;
						int strCount = strings.length;
						int maxLength = 0;
						byte[][] stringbuffers = new byte[strCount][];
						int i = 0;
						for (String str : strings) {
							stringbuffers[i] = str.getBytes(utf8);
							int l = stringbuffers[i].length;
							if (l > maxLength) maxLength = l;
							i++;
						}
						maxLength++; //we require null terminators
						buffer = new byte[maxLength * strCount];
						int offset = 0;
						for (byte[] str: stringbuffers) {
							System.arraycopy(str, 0, buffer, offset, str.length);
							offset += maxLength;
						}

						H5.H5Tset_cset(datatypeId, HDF5Constants.H5T_CSET_ASCII);
						H5.H5Tset_size(datatypeId, maxLength);
					}
					try (HDF5Resource attributeResource = new HDF5AttributeResource(
							H5.H5Acreate_by_name(fileId, path, attrName, datatypeId, dataspaceId,
									HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT))) {

						if (stringDataset) {
							H5.H5Awrite(attributeResource.getResource(), datatypeId, buffer);
						} else {
							H5.H5Awrite(attributeResource.getResource(), datatypeId, buffer);
						}
					}
				}
			} catch (HDF5Exception e) {
				throw new NexusException("Could not create attribute", e);
			}
		}
	}

	@Override
	public void addAttribute(Node node, Attribute... attribute) throws NexusException {
		String path = getPath(node);
		addAttribute(path, attribute);
	}

	private void createSoftLink(String source, String destination) throws NexusException {
		boolean useNameAtSource = destination.endsWith(Node.SEPARATOR);
		String linkName = destination;
		if (!useNameAtSource) {
			destination = destination.substring(0, destination.lastIndexOf(Node.SEPARATOR));
			if (destination.isEmpty()) destination = Tree.ROOT;
		} else {
			int index = source.lastIndexOf(Node.SEPARATOR);
			linkName += source.substring(index);
		}
		getGroupNode(destination, true);
		try {
			H5.H5Lcreate_soft(source, fileId, linkName, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not create hard link", e);
		}
	}

	private void createHardLink(String source, String destination) throws NexusException {
		boolean useNameAtSource = destination.endsWith(Node.SEPARATOR);
		NodeData sourceData = getNode(source, false);
		if (sourceData.name == null) {
			throw new IllegalArgumentException("Source does not exist");
		}
		String linkName = destination;
		String nodeName;
		if (!useNameAtSource) {
			destination = destination.substring(0, destination.lastIndexOf(Node.SEPARATOR));
			nodeName = source.substring(source.lastIndexOf(Node.SEPARATOR));
			if (destination.isEmpty()) destination = Tree.ROOT;
		} else {
			int index = source.lastIndexOf(Node.SEPARATOR);
			nodeName = source.substring(index);
			linkName += nodeName;
		}

		GroupNode destNode = (GroupNode)getGroupNode(destination, true).node;
		switch(sourceData.type) {
		case DATASET:
			destNode.addDataNode(nodeName, (DataNode) sourceData.node);
			break;
		case GROUP:
			destNode.addGroupNode(nodeName, (GroupNode) sourceData.node);
			break;
		}
		try {
			H5.H5Lcreate_hard(fileId, source, fileId, linkName, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not create hard link", e);
		}
		//TODO: Maybe remove this and fix the areas where this is required (NexusLoader and some tests?)
		IDataset target = DatasetFactory.createFromObject(source);
		target.setName("target");
		Attribute targetAttr = createAttribute(target);
		addAttribute(linkName, targetAttr);
	}

	@Override
	public void link(String source, String destination) throws NexusException {
		assertCanWrite();
		createHardLink(NexusUtils.stripAugmentedPath(source), NexusUtils.stripAugmentedPath(destination));
	}

	@Override
	public void linkExternal(URI source, String destination, boolean isGroup) throws NexusException {
		//creates a soft link *at* destination *to* source
		assertCanWrite();
		destination = NexusUtils.stripAugmentedPath(destination);
		boolean useNameAtSource = destination.endsWith(Node.SEPARATOR);
		String linkName = destination;
		String sourceString = source.toString();
		//the URI is malformed if the specified path was relative, so we have to manually extract the path
		String externalFileName;
		if (sourceString.startsWith("#")) {
			externalFileName = "";
		} else {
			externalFileName = sourceString.replaceFirst("nxfile://", "");
		}
		String externalNexusPath = source.getFragment();
		if (externalFileName.contains("#")) {
			externalFileName = externalFileName.substring(0, externalFileName.indexOf("#"));
		}
		externalNexusPath = NexusUtils.stripAugmentedPath(externalNexusPath);
		if (externalFileName == null || externalFileName.isEmpty()) {
			createSoftLink(externalNexusPath, destination);
			return;
		}
		if (!externalNexusPath.startsWith(Tree.ROOT)) {
			externalNexusPath = Tree.ROOT + externalNexusPath;
		}

		if (!useNameAtSource) {
			destination = destination.substring(0, destination.lastIndexOf(Node.SEPARATOR));
			if (destination.isEmpty()) destination = Tree.ROOT;
		}
		//create the destination node (the path on our side of the link)
		getGroupNode(destination, true);
		if (useNameAtSource) {
			int index = externalNexusPath.lastIndexOf(Node.SEPARATOR);
			linkName += externalNexusPath.substring(index);
		}

		try (HDF5Resource linkAccess = new HDF5PropertiesResource(H5.H5Pcreate(HDF5Constants.H5P_LINK_ACCESS));
				HDF5Resource fileAccess = new HDF5PropertiesResource(H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS))) {
			long lapl = linkAccess.getResource();
			long fapl = fileAccess.getResource();
			H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
			H5.H5Pset_elink_fapl(lapl, fapl);
			// TODO: Flag should probably be H5F_ACC_RDONLY | H5F_ACC_SWMR_READ but not yet supported
			H5.H5Pset_elink_acc_flags(lapl, HDF5Constants.H5F_ACC_RDONLY);
			H5.H5Lcreate_external(externalFileName, externalNexusPath, fileId, linkName, HDF5Constants.H5P_DEFAULT, lapl);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not create external link", e);
		}
	}

	public void activateSwmrMode() throws NexusException {
		if (!useSWMR) {
			throw new IllegalStateException("File was not created to use SWMR");
		}

		//*
		try {
			H5.H5Fstart_swmr_write(fileId);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not switch to SWMR mode", e);
		}
		/*/
				try {
					HDF5FileFactory.releaseFile(fileName, true);
					fileId = -1;
					try {
						fileId = HDF5FileFactory.acquireForSwmrWrite(fileName);
				} catch (Exception e) {
						fileId = HDF5FileFactory.acquireFile(fileName, writeable);
						throw e;
					}
					// int result = H5.H5Fstart_swmr_write(fileId);
					// result++;
				} catch (ScanFileHolderException e) {
					throw new NexusException("Could not switch to SWMR mode", e);
				}
		 */
	}
	
	@Override
	public void flush() throws NexusException {
		if (fileId == -1) {
			return;
		}
		try {
			H5.H5Fflush(fileId, HDF5Constants.H5F_SCOPE_GLOBAL);
		} catch (HDF5LibraryException e) {
			throw new NexusException("Cannot flush file", e);
		}
		return;
	}

	private void tryToCloseOpenObjects() throws NexusException {
		try {
			//try datasets, datatypes and groups (things that can be closed with H5Oclose)
			int typeIdentifier = HDF5Constants.H5F_OBJ_DATASET |
					HDF5Constants.H5F_OBJ_DATATYPE |
					HDF5Constants.H5F_OBJ_GROUP |
					HDF5Constants.H5F_OBJ_LOCAL;
			int openObjectCount = H5.H5Fget_obj_count(fileId, typeIdentifier);
			if (openObjectCount > 0) {
				logger.debug("Trying to close hdf5 file with open objects");
				long[] openIds = new long[openObjectCount];
				H5.H5Fget_obj_ids(fileId, typeIdentifier, openObjectCount, openIds);
				for (int i = 0; i < openObjectCount; i++) {
					long id = openIds[i];
					try {
						H5.H5Oclose(id);
						openIds[i] = -1;
					} catch (HDF5LibraryException e) {
						logger.error("Error closing hdf5 file - could not close open object");
					}
				}
			}
			//try attributes
			typeIdentifier = HDF5Constants.H5F_OBJ_ATTR | HDF5Constants.H5F_OBJ_LOCAL;
			openObjectCount = H5.H5Fget_obj_count(fileId, typeIdentifier);
			if (openObjectCount > 0) {
				logger.debug("Trying to close hdf5 file with open attributes");
				long[] attrIds = new long[openObjectCount];
				H5.H5Fget_obj_ids(fileId, typeIdentifier, openObjectCount, attrIds);
				for (int i = 0; i < openObjectCount; i++) {
					long id = attrIds[i];
					try {
						H5.H5Aclose(id);
						attrIds[i] = -1;
					} catch (HDF5LibraryException e) {
						logger.error("Error closing hdf5 file - could not close open attribute");
					}
				}
			}
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not query for open objects", e);
		}
	}

	@Override
	public void close() throws NexusException {
		if (fileId == -1) {
			return;
		}
		try {
			tryToCloseOpenObjects();
			fileId = -1;
			tree = null;
			nodeMap = null;
			passedNodeMap = null;
			writeable = false;
		} catch (NexusException e) {
			throw new NexusException("Cannot close file", e);
		} finally {
			try {
				HDF5FileFactory.releaseFile(fileName, true);
			} catch (ScanFileHolderException e) {
				throw new NexusException("Cannot release file", e);
			}
		}
	}

	@Override
	public boolean isPathValid(String path) {
		try {
			return H5.H5Lexists(fileId, path, HDF5Constants.H5P_DEFAULT);
		} catch (HDF5LibraryException e) {
			return false;
		}
	}

	private static long IS_EXTERNAL_LINK = -4370;
	private static long NO_LINK = -3422;

	private boolean testForExternalLink(String path) throws NexusException {
		//TODO: might want to cache results
		ParsedNode[] parsedNodes = parseAugmentedPath(path);
		StringBuilder currentPath = new StringBuilder(Tree.ROOT);
		try {
			for (ParsedNode node : parsedNodes) {
				if (node.name.isEmpty()) {
					continue;
				}
				currentPath.append(Node.SEPARATOR);
				currentPath.append(node.name);
				H5L_info_t linkInfo = H5.H5Lget_info(fileId, currentPath.toString(), HDF5Constants.H5P_DEFAULT);
				if (linkInfo.type == HDF5Constants.H5L_TYPE_EXTERNAL) {
					return true;
				}
			}
		} catch (HDF5LibraryException e) {
			throw new NexusException("Error checking for external links", e);
		}
		return false;
	}
	private long getLinkTarget(String path) throws NexusException {
		try {
			//The H5 library doesn't consider the "root" node to really be a group but NAPI does,
			//so we emulate the old behaviour by pretending it has a file address
			if (path.equals(Tree.ROOT)) {
				return ROOT_NODE_ADDR;
			}
			if (!testLinkExists(path)) {
				return NO_LINK;
			}
			H5L_info_t linkInfo = H5.H5Lget_info(fileId, path, HDF5Constants.H5P_DEFAULT);
			if (linkInfo.type == HDF5Constants.H5L_TYPE_SOFT) {
				String[] name = new String[2];
				H5.H5Lget_val(fileId, path, name, HDF5Constants.H5P_DEFAULT);
				return getLinkTarget(name[0]);
			} else if (linkInfo.type == HDF5Constants.H5L_TYPE_HARD) {
				return linkInfo.address_val_size;
			} else if (linkInfo.type == HDF5Constants.H5L_TYPE_EXTERNAL) {
				return IS_EXTERNAL_LINK;
			}
			throw new NexusException("Unhandled link type");
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not get link target", e);
		}
	}

	private boolean testLinkExists(String path) throws NexusException {
		ParsedNode[] parsedNodes = parseAugmentedPath(path);
		StringBuilder currentPath = new StringBuilder(Tree.ROOT);
		try {
			for (ParsedNode parsedNode : parsedNodes) {
				if (parsedNode.name == null || parsedNode.name.isEmpty()) {
					continue;
				}
				currentPath.append(parsedNode.name);
				if (!H5.H5Lexists(fileId, currentPath.toString(), HDF5Constants.H5P_DEFAULT)) {
					return false;
				}
				currentPath.append(Node.SEPARATOR);
			}
			return true;
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not verify chain", e);
		}
	}

	private static long getHDF5Type(ILazyDataset data) {
		Class<?> clazz = data.elementClass();
		if (clazz.equals(String.class)) {
			return HDF5Constants.H5T_C_S1;
		} else if (clazz.equals(Byte.class)) {
			return HDF5Constants.H5T_NATIVE_INT8;
		} else if (clazz.equals(Short.class)) {
			return HDF5Constants.H5T_NATIVE_INT16;
		} else if (clazz.equals(Integer.class)) {
			return HDF5Constants.H5T_NATIVE_INT32;
		} else if (clazz.equals(Long.class)) {
			return HDF5Constants.H5T_NATIVE_INT64;
		} else if (clazz.equals(Float.class)) {
			return HDF5Constants.H5T_NATIVE_FLOAT;
		} else if (clazz.equals(Double.class)) {
			return HDF5Constants.H5T_NATIVE_DOUBLE;
		}
		throw new IllegalArgumentException("Invalid datatype requested");
	}

	private static Object[] getFillValue(Class<?> clazz) {
		if (clazz == Integer.class) {
			return new Integer[] {0};
		} else if (clazz == Long.class) {
			return new Long[] {0L};
		} else if (clazz == Double.class) {
			return new Double[] {Double.NaN};
		} else if (clazz == Float.class) {
			return new Float[] {Float.NaN};
		} else if (clazz == Short.class) {
			return new Short[] {0};
		} else if (clazz == Byte.class) {
			return new Byte[] {0};
		} else if (clazz == String.class) {
			//TODO: change to "" when HDF supports strings as fill value
			return null;
		}
		return null;
	}

	private static Object[] getFillValue(int datasetType) {
		switch(datasetType) {
		case Dataset.FLOAT64:
			return new Double[] {Double.NaN};
		case Dataset.FLOAT32:
			return new Float[] {Float.NaN};
		case Dataset.INT32:
			return new Integer[] {0};
		case Dataset.INT64:
			return new Long[] {0L};
		case Dataset.INT16:
			return new Short[] {0};
		case Dataset.INT8:
			return new Byte[] {0};
		case Dataset.STRING:
			//TODO: support string fill when HDF5 library does
			return null;
		default:
			return null;
		}
	}
}
