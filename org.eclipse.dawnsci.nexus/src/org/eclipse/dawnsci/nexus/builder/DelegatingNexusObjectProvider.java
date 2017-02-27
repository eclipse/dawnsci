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
package org.eclipse.dawnsci.nexus.builder;

import java.util.function.Supplier;

import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;

public class DelegatingNexusObjectProvider<N extends NXobject> extends AbstractNexusObjectProvider<N> {

	private final Supplier<N> supplier; 
	
	public DelegatingNexusObjectProvider(String name, NexusBaseClass nexusBaseClass,
			Supplier<N> nexusObjectSupplier) {
		super(name, nexusBaseClass);
		this.supplier = nexusObjectSupplier;
	}

	@Override
	protected N createNexusObject() {
		return supplier.get();
	}

}
