/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.peakfinding;

public class PeakFinderInfo {
	
	private String name;
	private String description;
	private IPeakFinder peakFinder;
	
	public PeakFinderInfo(String nm, String desc, IPeakFinder pf) {
		this.name = nm;
		this.description = desc;
		this.peakFinder = pf;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public IPeakFinder getPeakFinder() {
		return peakFinder;
	}
}
