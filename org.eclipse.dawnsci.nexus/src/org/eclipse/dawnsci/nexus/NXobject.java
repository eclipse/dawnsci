/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.GroupNode;

/**
 * Base interface of all Nexus group nodes
 */
public interface NXobject extends GroupNode {

	/**
	 * @return name of Nexus class
	 */
	public Class<? extends NXobject> getNXclass();

	/**
	 * @param nxClass
	 * @return first child NXobject of given Nexus class or null if not found
	 */
	public <N extends NXobject> N getFirstChild(Class<N> nxClass);

	/**
	 * @param name of child
	 * @param nxClass
	 * @return named child NXobject of given Nexus class or null if not found
	 */
	public <N extends NXobject> N getChild(String name, Class<N> nxClass);
}
