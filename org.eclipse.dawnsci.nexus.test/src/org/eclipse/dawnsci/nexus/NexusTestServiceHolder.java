package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.builder.NexusBuilderFactory;

public class NexusTestServiceHolder {
	
	private static NexusBuilderFactory nexusBuilderFactory;
	
	private static INexusFileFactory nexusFileFactory;

	public static void setNexusBuilderFactory(NexusBuilderFactory nexusBuilderFactory) {
		NexusTestServiceHolder.nexusBuilderFactory = nexusBuilderFactory; 
	}
	
	public static NexusBuilderFactory getNexusBuilderFactory() {
		return nexusBuilderFactory;
	}
	
	public static void setNexusFileFactory(INexusFileFactory nexusFileFactory) {
		NexusTestServiceHolder.nexusFileFactory = nexusFileFactory; 
	}
	
	public static INexusFileFactory getNexusFileFactory() {
		return nexusFileFactory;
	}

}
