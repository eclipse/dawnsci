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
	 * This method is used to track a events (log, action, exception etc) in the application. 
	 * 
	 * @param name
	 *            Unique name (human readable) of event to be tracked
	 *            Example: Data_Browsing_Perspective_launch
	 * @throws Exception
	 */
	public void track(String name) throws Exception;

}
