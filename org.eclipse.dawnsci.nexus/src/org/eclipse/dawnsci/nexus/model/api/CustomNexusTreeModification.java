/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.model.api;

import org.eclipse.dawnsci.nexus.NXentry;

/**
 * A custom modification to the nexus tree.
 *
 */
public interface CustomNexusTreeModification extends NexusTreeModification {
	
	/**
	 * Perform a custom modification to the entry.
	 * @param entry
	 */
	public void modifyEntry(NXentry entry);

}
