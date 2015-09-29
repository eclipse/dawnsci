package org.eclipse.dawnsci.nexus.scan;

import java.util.List;

import org.eclipse.dawnsci.nexus.NXobject;


/**
 * Represents a nexus scan.
 * TODO: this forces client code to implement this interface, instead the builder could just take a list of devices? 
 *
 */
public interface NexusScan {
	
	/**
	 * @return the devices for this scan
	 */
	public List<NxDevice<? extends NXobject>> getDevices();
	
}
