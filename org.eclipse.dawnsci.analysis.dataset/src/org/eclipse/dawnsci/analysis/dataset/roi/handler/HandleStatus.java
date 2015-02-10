/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi.handler;

/**
 * possible handle states
 */
public enum HandleStatus {
	/**
	 * Specifies the handle does nothing
	 */
	NONE,
	/**
	 * Specifies the handle is for moving centre
	 */
	CMOVE,
	/**
	 * Specifies the handle is for moving ROI
	 */
	RMOVE,
	/**
	 * Specifies the handle is for resizing
	 */
	RESIZE,
	/**
	 * Specifies the handle is for re-orienting (i.e. move end but preserve length)
	 */
	REORIENT,
	/**
	 * Specifies the handle is for spinning
	 */
	ROTATE,
	/**
	 * Specifies the handle is for constrained ROI moving
	 */
	CRMOVE
}
