package org.eclipse.dawnsci.nexus.builder.data;

import org.eclipse.dawnsci.nexus.NXobject;

/**
 * Axis data device.
 * @author wgp76868
 *
 * @param <N>
 */
public interface AxisDataDevice<N extends NXobject> extends DataDevice<N> {
	
	// no additional methods, this interface exists only for type safety
	
	/**
	 * Overrides the {@link DataDevice#isPrimary()} to return <code>true</code> always, as this
	 * is a primary device. 
	 * 
	 * @see org.eclipse.dawnsci.nexus.builder.data.DataDevice#isPrimary()
	 * @return <code>true</code>, always
	 */
	public default boolean isPrimary() {
		return false;
	}
	
}