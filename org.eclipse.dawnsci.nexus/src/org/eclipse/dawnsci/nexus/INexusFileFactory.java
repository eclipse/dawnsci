/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.nexus;

/**
 * Defines an interface for creating {@link NexusFile}s.
 *
 */
public interface INexusFileFactory {

	/**
	 * Create a new {@link NexusFile} with the given path. This can be used to read
	 * an existing NexusFile or to write a new file (overwriting any existing file).
	 * 
	 * @param path path
	 * @return new {@link NexusFile}
	 */
	public NexusFile newNexusFile(String path);
	
	/**
	 * Create a new {@link NexusFile} with the given path, optionally enabling SWMR
	 * (Single Write Multiple Read). This can be used to read
	 * an existing NexusFile or to write a new file (overwriting any existing file). 
	 * 
	 * @param path path
	 * @param enableSWMR <code>true</code> to enable SWMR, <code>false</code> otherwise
	 * @return new {@link NexusFile}
	 */
	public NexusFile newNexusFile(String path, boolean enableSWMR);
	
}
