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
package org.eclipse.dawnsci.plotting.api;

import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.jface.action.IContributionManager;

public interface ITraceActionProvider {
	/**
	 * Creates the trace actions (add line, box etc.) in the IContributionManager
	 * @param toolBarManager
	 * @param imageTrace
	 * @param system
	 */
	public <T> void fillTraceActions(IContributionManager toolBarManager, ITrace imageTrace, IPlottingSystem<T> system);

}
