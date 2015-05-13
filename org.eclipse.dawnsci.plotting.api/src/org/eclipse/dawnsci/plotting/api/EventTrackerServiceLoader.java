/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.plotting.api;

import org.eclipse.dawnsci.analysis.api.EventTracker;

/**
 * This class is used to inject the analytics service using OSGI
 *
 */
public class EventTrackerServiceLoader {

	private static EventTracker service;

	/**
	 * Used for OSGI injection
	 */
	public EventTrackerServiceLoader() {
		
	}

	/**
	 * Injected by OSGI
	 * @param et
	 */
	public static void setService(EventTracker et) {
		service = et;
	}

	public static EventTracker getService() {
		return service;
	}
}
