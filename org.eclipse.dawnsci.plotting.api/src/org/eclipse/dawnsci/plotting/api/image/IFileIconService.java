/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */ 
package org.eclipse.dawnsci.plotting.api.image;

import java.io.File;

import org.eclipse.swt.graphics.Image;

public interface IFileIconService {

	
	/**
	 * Returns the icon to use for a given file, uses eclipse and OS icons,
	 * supports directories.
	 */
	public Image getIconForFile(final File file);
	
	/**
	 * Returns the icon to use for a given file, uses eclipse and OS icons,
	 * supports directories.
	 */
	public Image getIconForFile(final String path);

}
