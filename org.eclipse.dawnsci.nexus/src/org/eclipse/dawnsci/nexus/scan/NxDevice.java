package org.eclipse.dawnsci.nexus.scan;

import org.eclipse.dawnsci.nexus.NXobject;

/**
 * This interface defines a method that returns a new instance
 * of the appropriate NeXus base class for this device.
 *
 * @param <D> the NeXus bass class for the device
 */
public interface NxDevice<D extends NXobject> {
	
	/**
	 * The {@link Class} of the NeXus object returned by
	 * {@link #getNewBaseClassInstance()}, a
	 * subclass of {@link NXobject}.
	 * 
	 * @return NeXus base class
	 */
	public Class<D> getNexusBaseClass();

	/**
	 * Returns a new instance of the appropriate NeXus base
	 * class for this device.
	 * 
	 * @return new instance of NeXus base class
	 */
	public D getNewBaseClassInstance();
	
}
