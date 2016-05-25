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

package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;

/**
 * A custom modification to an {@link NXentry}.
 */
public interface CustomNexusEntryModification extends NexusEntryModification {
	
	/**
	 * Perform a custom modification to the entry.
	 * @param entry nexus entry to modify
	 * @param nodeFactory node factory for creating nexus objects
	 */
	public void modifyEntry(NXentry entry, NexusNodeFactory nodeFactory) throws NexusException;

}
