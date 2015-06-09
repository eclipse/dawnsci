/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.filter;

import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Interface used to define user interface which configures a plotting filter.
 */
public interface FilterConfiguration {
	
	/**
	 * Called to provide the filter to which configuration will be sent.
	 * @param filter
	 */
	public void init(IPlottingSystem system, IPlottingFilter filter);

	/**
	 * Called once at start to create user interface for setting the filter's properties.
	 * @param parent
	 */
	public Control createControl(Composite parent);
}
