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

public interface IDownsampledTrace extends ITrace {

	/**
	 * Provides the square bin used for the downsample
	 * @return
	 */
	public int getBin();
	
	/**
	 * Add listener to be notifed if the dawnsampling changes.
	 * @param l
	 */
	public void addDownsampleListener(IDownSampleListener l);
	
	/**
	 * Remove listener so that it is not notified.
	 * @param l
	 */
	public void removeDownsampleListener(IDownSampleListener l);
	

}
