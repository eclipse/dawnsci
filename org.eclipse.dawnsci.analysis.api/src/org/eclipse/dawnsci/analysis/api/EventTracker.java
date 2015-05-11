/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api;

public interface EventTracker {

	/**
	 * This method is used to track events (log, action, exception etc) in the application. 
	 * 
	 * @param name
	 *            Name of event to be tracked
	 * @throws Exception
	 */
	public void track(String name) throws Exception;

	/**
	 * This method is used to track events from extension points given by their id and label
	 * 
	 * @param id
	 *            Unique id
	 * @param label
	 *            Name
	 */
	public void track(String id, String label) throws Exception;
}
