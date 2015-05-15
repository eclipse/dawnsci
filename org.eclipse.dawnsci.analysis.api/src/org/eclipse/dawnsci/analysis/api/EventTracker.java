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

	/**
	 * This method is used to track tool creation events given a tool name. It will track the event name with the prefix
	 * "/Tool/"
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void trackToolEvent(String name) throws Exception;

	/**
	 * This method is used to track perspective launch events given a perspective name. It will track the event name
	 * with the prefix "/perspective/".
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void trackPerspectiveEvent(String name) throws Exception;

	/**
	 * This method is used to track action events like a user click on a "run" button given the action's name. It will
	 * track the event name with the prefix "/Action/".
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void trackActionEvent(String name) throws Exception;
}
