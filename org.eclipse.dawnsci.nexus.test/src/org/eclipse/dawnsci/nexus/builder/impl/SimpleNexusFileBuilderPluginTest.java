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
package org.eclipse.dawnsci.nexus.builder.impl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.eclipse.dawnsci.nexus.NexusTestServiceHolder;
import org.eclipse.dawnsci.nexus.builder.NexusBuilderFactory;


public class SimpleNexusFileBuilderPluginTest extends SimpleNexusFileBuilderTest {

	@Override
	protected NexusBuilderFactory getNexusBuilderFactory() {
		return NexusTestServiceHolder.getNexusBuilderFactory();
	}
	
	@Override
	protected void checkNexusBuilderFactory(NexusBuilderFactory nexusBuilderFactory) {
		assertThat(nexusBuilderFactory, is(sameInstance(NexusTestServiceHolder.getNexusBuilderFactory())));
	}
	
}
