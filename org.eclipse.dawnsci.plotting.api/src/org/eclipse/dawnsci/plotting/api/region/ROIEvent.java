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

import java.util.EventObject;

import org.eclipse.dawnsci.analysis.api.roi.IROI;

public class ROIEvent extends EventObject {

	public enum DRAG_TYPE{
		RESIZE,
		TRANSLATE;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5892437380421200585L;
	private IROI roi;

	public ROIEvent(Object source, IROI region) {
		super(source);
		this.roi = region;
	}

	public IROI getROI() {
		return roi;
	}

	public DRAG_TYPE getDragType() {
		return dragType;
	}

	public void setDragType(DRAG_TYPE dragType) {
		this.dragType = dragType;
	}

	private DRAG_TYPE dragType;
}
