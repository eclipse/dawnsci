package org.eclipse.dawnsci.nexus.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.nexus.NXaperture;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsensor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NXobjectImplTest {
	
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
	public void testGetNXclass() {
		// test a few of the base classes to ensure they have the expected NXclass
		assertEquals(NXaperture.class, nxObjectFactory.createNXaperture().getNXclass());
		assertEquals(NXentry.class, nxObjectFactory.createNXentry().getNXclass());
		assertEquals(NXinstrument.class, nxObjectFactory.createNXinstrument().getNXclass());
		assertEquals(NXsample.class, nxObjectFactory.createNXsample().getNXclass());
	}
	
	@Test
	public void testPutGetChild() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXdetector detector = nxObjectFactory.createNXdetector();
		instrument.putChild("detector", detector);
		
		assertNotNull(instrument.getChild("detector", NXdetector.class));
		assertSame(detector, instrument.getChild("detector", NXdetector.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPutGetChild_existingChildWithSameNameButDifferentType() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXsensor sensor = nxObjectFactory.createNXsensor();
		instrument.putChild("detector", sensor);
		
		NXdetector detector = nxObjectFactory.createNXdetector();
		instrument.putChild("detector", detector);
	}
	
	@Test
	public void testGetChildren() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXdetector detector1 = nxObjectFactory.createNXdetector();
		NXdetector detector2 = nxObjectFactory.createNXdetector();
		NXdetector detector3 = nxObjectFactory.createNXdetector();
		instrument.putChild("detector1", detector1);
		instrument.putChild("detector2", detector2);
		instrument.putChild("detector3", detector3);

		// add a child of a different type to check that isn't returned as well
		NXsensor sensor = nxObjectFactory.createNXsensor();
		instrument.putChild("sensor", sensor);
		
		final Map<String, NXdetector> detectors = instrument.getChildren(NXdetector.class);
		assertEquals(3, detectors.size());
		assertSame(detector1, detectors.get("detector1"));
		assertSame(detector2, detectors.get("detector2"));
		assertSame(detector3, detectors.get("detector3"));
	}
	
	@Test
	public void testSetChildren() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXdetector detector1 = nxObjectFactory.createNXdetector();
		NXdetector detector2 = nxObjectFactory.createNXdetector();
		NXdetector detector3 = nxObjectFactory.createNXdetector();
		
		final Map<String, NXdetector> detectors = new HashMap<>();
		detectors.put("detector1", detector1);
		detectors.put("detector2", detector2);
		detectors.put("detector3", detector3);
		instrument.setChildren(detectors);
		
		// add a child of a different type to check that isn't returned as well
		NXsensor sensor = nxObjectFactory.createNXsensor();
		instrument.putChild("sensor", sensor);
		
		final Map<String, NXdetector> detectors2 = instrument.getChildren(NXdetector.class);
		assertEquals(3, detectors2.size());
		assertSame(detector1, detectors2.get("detector1"));
		assertSame(detector2, detectors2.get("detector2"));
		assertSame(detector3, detectors2.get("detector3"));
	}

	@Test
	public void testGetSetDataset() {
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		IDataset numberOfCycles = DatasetFactory.createFromObject(3);
		detector.setNumber_of_cycles(numberOfCycles);
		
		assertNotNull(detector.getNumber_of_cycles());
		assertSame(numberOfCycles, detector.getNumber_of_cycles());
	}
	
}
