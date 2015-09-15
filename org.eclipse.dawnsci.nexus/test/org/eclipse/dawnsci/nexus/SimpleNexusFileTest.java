package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertNotNull;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.tree.impl.DataNodeImpl;
import org.eclipse.dawnsci.nexus.impl.NXdataImpl;
import org.eclipse.dawnsci.nexus.impl.NXentryImpl;
import org.eclipse.dawnsci.nexus.impl.NXobjectFactory;
import org.eclipse.dawnsci.nexus.impl.NXrootImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleNexusFileTest {
	
	private NXobjectFactory nxObjectFactory;
	
	@Before
	public void setUp() {
		nxObjectFactory = new NXobjectFactory();
	}
	
	@After
	public void tearDown() {
		nxObjectFactory = null;
	}
	
	@Test
	public void testSimpleNexusFile() throws Exception {
		NXrootImpl root = nxObjectFactory.createNXroot();

		NXentryImpl entry = nxObjectFactory.createNXentry();
		root.setEntry(entry);
		assertNotNull(root.getEntry());
		
		NXdataImpl data = nxObjectFactory.createNXdata();
		entry.setData("data", data);
		assertNotNull(entry.getData("data"));
		
		IDataset newCountsData = DatasetFactory.ones(new int[] { 15 }, AbstractDataset.INT);

		data.addDataNode("counts", new DataNodeImpl(4l));
		ILazyWriteableDataset counts = data.getDataNode("counts").getWriteableDataset();
		counts.setShape(15);
		counts.setSlice(null, newCountsData, new int[] { 0 }, new int[] { 15 }, new int[] { 1 });
		
		
		
		
	}

}
