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
package org.eclipse.dawnsci.plotting.api.region;

import java.util.EventListener;

public interface IROIListener extends EventListener {

	
	public class Stub implements IROIListener {

		@Override
		public void roiDragged(ROIEvent evt) {
			update(evt);

		}

		@Override
		public void roiChanged(ROIEvent evt) {
			update(evt);

		}
		
		@Override
		public void roiSelected(ROIEvent evt) {
			update(evt);

		}

		/**
		 * Override to do something whenever a roi changes.
		 * NOTE Do not do much work on this or the UI will go slow...
		 * @param evt
		 */
		public void update(ROIEvent evt) {
	
		}
	}

	/**
	 * Called when the region is being dragged around and
	 * its value is being updated in a live way. Do not do
	 * a lot of work in this callback.
	 * 
	 * @param evt
	 */
	void roiDragged(ROIEvent evt);

	/**
	 * Called when the region changes position, and the user has
	 * finished clicking and dragging or when the region position
	 * has been updated programmatically.
	 * 
	 * @param evt
	 */
	void roiChanged(ROIEvent evt);
	
	/**
	 * Called when the region has a single click on it which does
	 * not change its position.
	 * 
	 * @param evt
	 */
	void roiSelected(ROIEvent evt);

}
