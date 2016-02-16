package org.eclipse.dawnsci.nexus;


public class ServiceHolder {
	
	private static INexusFileFactory nexusFileFactory;
	
	public static INexusFileFactory getNexusFileFactory() {
		return nexusFileFactory;
	}
	
	public static void setNexusFileFactory(INexusFileFactory nexusFileFactory) {
		ServiceHolder.nexusFileFactory = nexusFileFactory;
	}

}
