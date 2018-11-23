/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
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