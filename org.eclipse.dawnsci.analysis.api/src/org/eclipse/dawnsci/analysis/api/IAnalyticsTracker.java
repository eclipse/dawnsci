/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api;

public interface IAnalyticsTracker {

	/**
	 * Track the focusPoint in the application asynchronously. <br/>
	 * 
	 * @param name
	 *            Unique name ID of focus point to be tracked
	 * @param isAsynchronous
	 *            if True the tracking will be done asynchronously
	 * @throws Exception
	 */
	public void track(String name, boolean isAsynchronous) throws Exception;

}
