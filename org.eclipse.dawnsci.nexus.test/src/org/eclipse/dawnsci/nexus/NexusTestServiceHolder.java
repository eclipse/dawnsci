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
package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.builder.NexusBuilderFactory;

public class NexusTestServiceHolder {
	
	private static NexusBuilderFactory nexusBuilderFactory;
	
	private static INexusFileFactory nexusFileFactory;

	public void setNexusBuilderFactory(NexusBuilderFactory nexusBuilderFactory) {
		NexusTestServiceHolder.nexusBuilderFactory = nexusBuilderFactory; 
	}
	
	public static NexusBuilderFactory getNexusBuilderFactory() {
		return nexusBuilderFactory;
	}
	
	public void setNexusFileFactory(INexusFileFactory nexusFileFactory) {
		NexusTestServiceHolder.nexusFileFactory = nexusFileFactory; 
	}
	
	public static INexusFileFactory getNexusFileFactory() {
		return nexusFileFactory;
	}

}
