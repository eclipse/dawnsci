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

import java.util.EventListener;

public interface IDownSampleListener extends EventListener{

	/**
	 * Notifies of current downsample rate of an IImageTrace.
	 * 
	 * May be fired multiple times with the same rate and will be fired
	 * during zooming in and out. Therefore work should not be done in this
	 * callback.
	 * 
	 * @param evt
	 */
	void downSampleChanged(DownSampleEvent evt);
}
