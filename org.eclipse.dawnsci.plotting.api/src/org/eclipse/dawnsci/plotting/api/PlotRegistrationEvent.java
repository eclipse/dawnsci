/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api;

import java.util.EventObject;

public class PlotRegistrationEvent extends EventObject {

	public PlotRegistrationEvent(IPlottingSystem source) {
		super(source);
	}
	
	public IPlottingSystem getPlottingSystem() {
		return (IPlottingSystem)getSource();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4641035778836070765L;

}
