package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;

/**
 * A test to check that methods to get/set scalar dataset work
 * correctly
 * 
 * @author Matthew Dickie
 */
public class ScalarDatasetTest extends AbstractNexusFileTestBase {

	@Override
	protected String getFilename() {
		return "nxslit_example.nxs";
	}

	@Override
	protected NXroot createNXroot() {
		NXroot root = NexusNodeFactory.createNXroot();
	
		NXentry entry = NexusNodeFactory.createNXentry();
		entry.setDurationScalar(12345l);
		entry.setCollection_timeScalar(0.1);
		root.setEntry(entry);
		
		NXslit slit = NexusNodeFactory.createNXslit();
		entry.addGroupNode("slit", slit);
		slit.setX_gapScalar(123.456);
		slit.setY_gapScalar(654.321);
		
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		instrument.setNameScalar("beamline-ixx");
		entry.setInstrument(instrument);
		
		NXsource source = NexusNodeFactory.createNXsource();
		source.setTop_upScalar(true);
		instrument.setSource(source);
		
		return root;
	}

	protected void checkNexusFile(TreeFile createdNexusTree, TreeFile loadedNexusTree) throws Exception {
		NXroot root = (NXroot) createdNexusTree.getGroupNode();
		assertNotNull(root);
		
		NXentry entry = root.getEntry();
		assertEquals(0.1, entry.getCollection_timeScalar(), 1e-15);
		assertEquals(12345l, entry.getDurationScalar().longValue());
		assertNotNull(entry);
		
		NXslit slit = (NXslit) entry.getGroupNode("slit");
		assertNotNull(slit);
		assertEquals(123.456, slit.getX_gapScalar().doubleValue(), 1e-15);
		assertEquals(654.321, slit.getY_gapScalar().doubleValue(), 1e-15);
		
		NXinstrument instrument = entry.getInstrument();
		assertEquals("beamline-ixx", instrument.getNameScalar());
		assertNotNull(instrument);
		
		NXsource source = instrument.getSource();
		assertEquals(true, source.getTop_upScalar().booleanValue());
	}
	
}
