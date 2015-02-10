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
package org.eclipse.dawnsci.plotting.api.trace;

import java.util.EventObject;

/**
 * Note the source to this event can either be the ITrace
 * or the plotting system.
 * 
 * @author Matthew Gerring
 *
 */
public class TraceEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063317569913121122L;

	public TraceEvent(Object source) {
		super(source);
	}

}
