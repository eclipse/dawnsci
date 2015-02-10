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

package org.eclipse.dawnsci.plotting.api.jreality.overlay;

import org.eclipse.dawnsci.plotting.api.jreality.tool.ImagePositionListener;

/**
 *
 */
public interface Overlay2DConsumer extends OverlayConsumer,ImagePositionListener {
	/**
	 * Make all overlays invisible
	 */
	public void hideOverlays();

	/**
	 * Make all overlays visible
	 */
	public void showOverlays();
}
