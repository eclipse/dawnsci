package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertNotNull;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.tree.impl.DataNodeImpl;
import org.eclipse.dawnsci.nexus.impl.NXdataImpl;
import org.eclipse.dawnsci.nexus.impl.NXentryImpl;
import org.eclipse.dawnsci.nexus.impl.NXrootImpl;
import org.eclipse.dawnsci.nexus.impl.NexusFactory;
import org.junit.Test;

public class SimpleNexusFileTest {
	
	@Test
	public void testSimpleNexusFile() throws Exception {
		NXrootImpl root = NexusFactory.createNXroot();

		NXentryImpl entry = NexusFactory.createNXentry();
		root.setEntry(entry);
		assertNotNull(root.getEntry());
		
		NXdataImpl data = NexusFactory.createNXdata();
		entry.setData("data", data);
		assertNotNull(entry.getData("data"));
		
		IDataset newCountsData = DatasetFactory.ones(new int[] { 15 }, AbstractDataset.INT);

		data.addDataNode("counts", new DataNodeImpl(4l));
		ILazyWriteableDataset counts = data.getDataNode("counts").getWriteableDataset();
		counts.setShape(15);
		counts.setSlice(null, newCountsData, new int[] { 0 }, new int[] { 15 }, new int[] { 1 });
		
		
	}

}
