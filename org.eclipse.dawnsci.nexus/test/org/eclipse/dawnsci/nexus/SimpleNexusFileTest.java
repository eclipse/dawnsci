package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.tree.impl.DataNodeImpl;
import org.eclipse.dawnsci.nexus.impl.NXdataImpl;
import org.eclipse.dawnsci.nexus.impl.NXentryImpl;
import org.eclipse.dawnsci.nexus.impl.NXrootImpl;

public class SimpleNexusFileTest {
	
	public void testSimpleNexusFile() throws Exception {
		NXroot root = new NXrootImpl(1l);
		
		NXentryImpl entry = new NXentryImpl(2l);
		entry.setData("data", new NXdataImpl(3l));
		
		NXdataImpl data = (NXdataImpl) entry.getData("data");

		IDataset newCountsData = DatasetFactory.ones(new int[] { 15 }, AbstractDataset.INT);

		data.addDataNode(null, "counts", new DataNodeImpl(4l));
		ILazyWriteableDataset counts = data.getDataNode("counts").getWriteableDataset();
		counts.setShape(15);
		counts.setSlice(null, newCountsData, new int[] { 0 }, new int[] { 15 }, new int[] { 1 });
		
		
	}

}
