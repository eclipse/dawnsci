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
