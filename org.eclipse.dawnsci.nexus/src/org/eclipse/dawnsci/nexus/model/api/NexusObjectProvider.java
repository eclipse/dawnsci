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

import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;

/**
 * Defines the interface for a class that knows how to create a nexus object,
 * (i.e. an instance of a NeXus base class).
 * 
 * @param <N> a subinterface of {@link NXobject} that this object can create
 */
public interface NexusObjectProvider<N extends NXobject> extends NexusTreeModification {

	/**
	 * Get the name of the provider. This is used as the name of the 
	 * base class instance (i.e. group) in the parent group it is added to
	 * @return name of base
	 */
	public String getName();

	/**
	 * Return the NeXus base class for this group.
	 * @return
	 */
	public NexusBaseClass getNexusBaseClass();

	/**
	 * Creates and returns an instance of the appropriate NeXus base class.
	 * @param nodeFactory node factory
	 * @return new base class instance
	 */
	public N createNexusObject(NexusNodeFactory nodeFactory);
	
	/**
	 * If {@link #createNexusObject(NexusNodeFactory)} has previously been invoked
	 * on this object, returns the nexus base class instance that was returned by that method,
	 * otherwise returns <code>null</code>. This method should <em>not</em> return a new
	 * nexus base class each time.
	 * @return nexus base class instance
	 */
	public N getNexusObject();

	/**
	 * Returns the name of the default data node within the base class. This is the
	 * data node that will be linked to when added to a {@link NexusDataModel}.
	 *
	 * @return data node name
	 */
	public String getDefaultDataNodeName();

	/**
	 * Returns the device category for this base class provider. The base class
	 * will be added to the device of this category, if available.
	 * @return
	 */
	public NexusBaseClass getDeviceCategory();

}
