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
package org.eclipse.dawnsci.nexus.test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.eclipse.dawnsci.nexus.NexusTestServiceHolder;
import org.junit.Test;

public class NexusBuilderPluginTest {
	
	@Test
	public void testNexusBuilderDS() {
		assertThat(NexusTestServiceHolder.getNexusBuilderFactory(), is(notNullValue()));
		assertThat(NexusTestServiceHolder.getNexusFileFactory(), is(notNullValue()));
	}
	
}
