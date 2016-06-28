package org.eclipse.dawnsci.nexus.test.util;

import static org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder.ATTR_NAME_AXES;
import static org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder.ATTR_NAME_SIGNAL;
import static org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder.ATTR_NAME_TARGET;
import static org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder.ATTR_SUFFIX_INDICES;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.eclipse.dawnsci.analysis.api.dataset.DatasetException;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.PositionIterator;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXroot;

public class NexusAssert {

	public static void assertNexusTreesEqual(final TreeFile expectedTree, final TreeFile actualTree) throws Exception {
		assertGroupNodesEqual("/", expectedTree.getGroupNode(), actualTree.getGroupNode());
	}
	
	public static void assertNodesEquals(String path, final Node expectedNode, final Node actualNode) throws Exception {
		if (expectedNode.isGroupNode()) {
			assertTrue(path, actualNode.isGroupNode());
			assertGroupNodesEqual(path, (GroupNode) expectedNode, (GroupNode) actualNode);
		} else if (expectedNode.isDataNode()) {
			assertTrue(path, actualNode.isDataNode());
			assertDataNodesEqual(path, (DataNode) expectedNode, (DataNode) actualNode);
		} else if (expectedNode.isSymbolicNode()) {
			assertTrue(path, actualNode.isSymbolicNode());
			assertSymbolicNodesEqual(path, (SymbolicNode) expectedNode, (SymbolicNode) actualNode);
		} else {
			fail("Unknown node type"); // sanity check, shouldn't be possible
		}
	}

	public static void assertGroupNodesEqual(String path,
			final GroupNode expectedGroup, final GroupNode actualGroup) throws Exception {
		if (expectedGroup == actualGroup) {
			return; // same object, trivial case
		}

		// check the groups have the same NXclass
		if (expectedGroup instanceof NXobject) {
			assertTrue(path, actualGroup instanceof NXobject);
			assertEquals(path, ((NXobject) expectedGroup).getNXclass(), ((NXobject) actualGroup).getNXclass());
		}
		
		// check numbers of data nodes and group nodes are the same
		assertEquals(path, expectedGroup.getNumberOfDataNodes(), actualGroup.getNumberOfDataNodes());
		assertEquals(path, expectedGroup.getNumberOfGroupNodes(), actualGroup.getNumberOfGroupNodes());

		// check number of attributes same (i.e. actualGroup has no additional attributes)
		// The additional attribute "target" is allowed.
		int expectedNumAttributes = expectedGroup.getNumberOfAttributes();
		if (expectedGroup.containsAttribute("target") && !actualGroup.containsAttribute("target")) {
			expectedNumAttributes--;
		}
		assertEquals(path, expectedNumAttributes, actualGroup.getNumberOfAttributes());
		
		// check attribute properties same for each attribute
		Iterator<String> attributeNameIterator = expectedGroup.getAttributeNameIterator();
		while (attributeNameIterator.hasNext()) {
			String attributeName = attributeNameIterator.next();
			String attrPath = path + Node.ATTRIBUTE + attributeName;
			Attribute expectedAttr = expectedGroup.getAttribute(attributeName);
			Attribute actualAttr = actualGroup.getAttribute(attributeName);
			if (!expectedAttr.getName().equals("target")) {
				assertNotNull(attrPath, actualAttr);
				assertAttributesEquals(attrPath, expectedAttr, actualAttr);
			}
		}

		// check child nodes same
		final Iterator<String> nodeNameIterator = expectedGroup.getNodeNameIterator();
		while (nodeNameIterator.hasNext()) {
			String nodeName = nodeNameIterator.next();
			String nodePath = path + "/" + nodeName;
			// node is either a group node or data node
			if (expectedGroup.containsGroupNode(nodeName)) {
				assertTrue(nodePath, actualGroup.containsGroupNode(nodeName));
				assertGroupNodesEqual(nodePath, expectedGroup.getGroupNode(nodeName), actualGroup.getGroupNode(nodeName));
			} else if (expectedGroup.containsDataNode(nodeName)) {
				// node is a data node
				assertTrue(nodePath, actualGroup.containsDataNode(nodeName));
				assertDataNodesEqual(nodePath, expectedGroup.getDataNode(nodeName), actualGroup.getDataNode(nodeName));
			} else if (expectedGroup.containsSymbolicNode(nodeName)) {
				assertTrue(nodePath, actualGroup.containsSymbolicNode(nodeName));
//				assertSymbolicNodesEqual(nodePath, expectedGroup.getDataNode(nodeName), actualGroup.getDataNode(nodeName));
				// TODO merge this into a single assertNodesEqual method, which delegates
				// to the appropriate method
			}
		}
	}

	public static void assertAttributesEquals(final String path, final Attribute expectedAttr,
			final Attribute actualAttr) {
		assertEquals(path, expectedAttr.getName(), actualAttr.getName());
		assertEquals(path, expectedAttr.getTypeName(), actualAttr.getTypeName());
		assertEquals(path, expectedAttr.getFirstElement(), actualAttr.getFirstElement());
		assertEquals(path, expectedAttr.getSize(), actualAttr.getSize());
		assertEquals(path, expectedAttr.getRank(), actualAttr.getRank());
		assertArrayEquals(path, expectedAttr.getShape(), actualAttr.getShape());
		assertDatasetsEqual(path, expectedAttr.getValue(), actualAttr.getValue());
	}

	public static void assertDataNodesEqual(final String path,
			final DataNode expectedDataNode, final DataNode actualDataNode) {
		// check number of attributes same (i.e. actualDataNode has no additional attributes)
		// additional attribute "target" is allowed, this is added automatically when saving the file
		int expectedNumAttributes = expectedDataNode.getNumberOfAttributes();
		if (expectedDataNode.containsAttribute("target") && !actualDataNode.containsAttribute("target")) {
			expectedNumAttributes--;
		}
		assertEquals(expectedNumAttributes, actualDataNode.getNumberOfAttributes());
		
		// check attributes properties same for each attribute
		Iterator<String> attributeNameIterator = expectedDataNode.getAttributeNameIterator();
		while (attributeNameIterator.hasNext()) {
			String attributeName = attributeNameIterator.next();
			String attrPath = path + Node.ATTRIBUTE + attributeName;
			Attribute expectedAttr = expectedDataNode.getAttribute(attributeName);
			Attribute actualAttr = actualDataNode.getAttribute(attributeName);
			if (!expectedAttr.getName().equals("target")) {
				assertNotNull(attrPath, expectedAttr);
				assertAttributesEquals(attrPath, expectedAttr, actualAttr);
			}
		}

		assertEquals(path, expectedDataNode.getTypeName(), actualDataNode.getTypeName());
		assertEquals(path, expectedDataNode.isAugmented(), actualDataNode.isAugmented());
		assertEquals(path, expectedDataNode.isString(), actualDataNode.isString());
		assertEquals(path, expectedDataNode.isSupported(), actualDataNode.isSupported());
		assertEquals(path, expectedDataNode.isUnsigned(), actualDataNode.isUnsigned());
		assertEquals(path, expectedDataNode.getMaxStringLength(), actualDataNode.getMaxStringLength());
		// TODO reinstate lines below and check why they break - dataNode2 is null
//		assertArrayEquals(path, dataNode1.getMaxShape(), dataNode2.getMaxShape());
//		assertArrayEquals(path, dataNode1.getChunkShape(), dataNode2.getChunkShape());
		assertEquals(path, expectedDataNode.getString(), actualDataNode.getString());
		assertDatasetsEqual(path, expectedDataNode.getDataset(), actualDataNode.getDataset());
	}

	public static void assertDatasetsEqual(final String path, final ILazyDataset expectedDataset,
			final ILazyDataset actualDataset) {
		// Note: dataset names can be different, as long as the containing data node names are the same
		// assertEquals(dataset1.getName(), dataset2.getName());
		// assertEquals(dataset1.getClass(), dataset2.getClass());
		assertEquals(path, expectedDataset.getElementClass(), actualDataset.getElementClass());
		assertEquals(path, expectedDataset.getElementsPerItem(), actualDataset.getElementsPerItem());
		assertEquals(path, expectedDataset.getSize(), actualDataset.getSize());
		if (actualDataset.getRank() == 0) {
			// TODO: special case for scalar datasets. This could be fixed in future by marking the
			// dataset as scalar in the HDF5 file
			assertEquals(path, 1, expectedDataset.getRank());
			assertArrayEquals(path, new int[] { 1 }, expectedDataset.getShape());
		} else {
			assertEquals(path, expectedDataset.getRank(), actualDataset.getRank());
			assertArrayEquals(path, expectedDataset.getShape(), actualDataset.getShape());
		}

		assertDatasetDataEqual(path, expectedDataset, actualDataset);

		// TODO: in future also check metadata
	}

	private static void assertDatasetDataEqual(final String path,
			final ILazyDataset expectedDataset, final ILazyDataset actualDataset) {
		if (expectedDataset instanceof Dataset && actualDataset instanceof Dataset) {
			assertEquals(path, expectedDataset, actualDataset); // uses Dataset.equals() method
		} else {
			assertEquals(expectedDataset.getSize(), actualDataset.getSize());
			if (expectedDataset.getSize() == 0) {
				return;
			}
			
			// getSlice() with no args loads whole dataset if a lazy dataset
			IDataset expectedSlice;
			IDataset actualSlice;
			try {
				expectedSlice = expectedDataset.getSlice();
				actualSlice = actualDataset.getSlice();
			} catch (DatasetException e) {
				throw new AssertionError("Could not get data from lazy dataset", e.getCause());
			}

			final int datatype = AbstractDataset.getDType(actualDataset);
			PositionIterator positionIterator = new PositionIterator(actualDataset.getShape());
			while (positionIterator.hasNext()) {
				int[] position = positionIterator.getPos();
				switch (datatype) {
				case Dataset.BOOL:
					assertEquals(path, expectedSlice.getBoolean(position), actualSlice.getBoolean(position));
					break;
				case Dataset.INT8:
					assertEquals(path, expectedSlice.getByte(position), actualSlice.getByte(position));
					break;
				case Dataset.INT32:
					assertEquals(path, expectedSlice.getInt(position), actualSlice.getInt(position));
					break;
				case Dataset.INT64:
					assertEquals(path, expectedSlice.getLong(position), actualSlice.getLong(position));
					break;
				case Dataset.FLOAT32:
					assertEquals(path, expectedSlice.getFloat(position), actualSlice.getFloat(position), 1e-7);
					break;
				case Dataset.FLOAT64:
					assertEquals(path, expectedSlice.getDouble(position), actualSlice.getDouble(position), 1e-15);
					break;
				case Dataset.STRING:
				case Dataset.DATE:
					assertEquals(path, expectedSlice.getString(position), actualSlice.getString(position));
					break;
				case Dataset.COMPLEX64:
				case Dataset.COMPLEX128:
				case Dataset.OBJECT:
					assertEquals(path, expectedSlice.getObject(position), actualSlice.getObject(position));
					break;
				}
			}
		}
	}
	
	public static void assertSymbolicNodesEqual(final String path,
			final SymbolicNode expectedSymbolicNode, final SymbolicNode actualSymbolicNode) {
		assertEquals(path, expectedSymbolicNode, actualSymbolicNode);
	}
	
	public static void assertSignal(NXdata nxData, String expectedSignalFieldName) {
		Attribute signalAttr = nxData.getAttribute(ATTR_NAME_SIGNAL);
		assertThat(signalAttr, is(notNullValue()));
		assertThat(signalAttr.getRank(), is(1));
		assertThat(signalAttr.getFirstElement(), is(equalTo(expectedSignalFieldName)));
		assertThat(nxData.getNode(expectedSignalFieldName), is(notNullValue()));
	}

	public static void assertAxes(NXdata nxData, String... expectedValues) {
		Attribute axesAttr = nxData.getAttribute(ATTR_NAME_AXES);
		assertThat(axesAttr, is(notNullValue()));
		assertThat(axesAttr.getRank(), is(1));
		assertThat(axesAttr.getShape()[0], is(expectedValues.length));
		IDataset value = axesAttr.getValue();
		for (int i = 0; i < expectedValues.length; i++) {
			assertThat(value.getString(i), is(equalTo(expectedValues[i])));
		}
	}

	public static void assertShape(NXdata nxData, String fieldName, int... expectedShape) {
		DataNode dataNode = nxData.getDataNode(fieldName);
		assertThat(fieldName, is(notNullValue()));
		int[] actualShape = dataNode.getDataset().getShape();
		assertArrayEquals(expectedShape, actualShape);
	}

	public static void assertIndices(NXdata nxData, String axisName, int... indices) {
		Attribute indicesAttr = nxData.getAttribute(axisName + ATTR_SUFFIX_INDICES);
		assertThat(indicesAttr, is(notNullValue()));
		assertThat(indicesAttr.getRank(), is(1));
		assertThat(indicesAttr.getShape()[0], is(indices.length));
		IDataset value = indicesAttr.getValue();
		for (int i = 0; i < indices.length; i++) {
			assertThat(value.getInt(i), is(equalTo(indices[i])));
		}
	}
	
	public static void assertTarget(NXdata nxData, String destName, NXroot nxRoot, String targetPath) {
		DataNode dataNode = nxData.getDataNode(destName);
		assertThat(dataNode, is(notNullValue()));
		Attribute targetAttr = dataNode.getAttribute(ATTR_NAME_TARGET);
		assertThat(targetAttr, is(notNullValue()));
		assertThat(targetAttr.getSize(), is(1));
		assertThat(targetAttr.getFirstElement(), is(equalTo(targetPath)));
		
		NodeLink nodeLink = nxRoot.findNodeLink(targetPath);
		assertTrue(nodeLink.isDestinationData());
		assertThat(nodeLink.getDestination(), is(sameInstance(dataNode)));
	}
	
}
