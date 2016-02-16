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
