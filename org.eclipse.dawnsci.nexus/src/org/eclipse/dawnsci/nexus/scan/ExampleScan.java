package org.eclipse.dawnsci.nexus.scan;

import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.impl.NXentryImpl;
import org.eclipse.dawnsci.nexus.impl.NXinstrumentImpl;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.impl.NXrootImpl;

/**
 * An example of how a scan might work - TEMPORARY CODE - TODO REMOVE
 */
public class ExampleScan {
	
	public NXroot buildNexusTree() {
		NxDevice<NXdetector> detectorDevice = null; // get the detector from somewhere
		
		NexusNodeFactory factory = new NexusNodeFactory();
		NXrootImpl root = factory.createNXroot();
		NXentryImpl entry = factory.createNXentry();
		root.setEntry(entry);
//		
		NXinstrumentImpl instrument = factory.createNXinstrument();
		entry.setInstrument(instrument);
//		
		@SuppressWarnings("null") // remove this line for production code
		NXdetector detector = detectorDevice.getNewBaseClassInstance();
		instrument.setDetector(detector);
		
		return root;
	}

}
