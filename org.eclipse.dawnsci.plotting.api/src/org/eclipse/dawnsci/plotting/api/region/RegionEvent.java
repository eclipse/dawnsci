/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.region;

import java.util.Collection;
import java.util.EventObject;

/**
 * Event with source of the IRegion affected.
 * 
 * @author Matthew Gerring
 *
 */
public class RegionEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3121767937881041584L;

	private Collection<IRegion> regions;

	public RegionEvent(Object source) {
		super(source);
	}
	public RegionEvent(Object source, Collection<IRegion> regions) {
		super(source);
		this.regions = regions;
	}
	
	public IRegion getRegion() {
		return (IRegion)getSource();
	}
	
	public Collection<IRegion> getRegions() {
		return regions;
	}

}
