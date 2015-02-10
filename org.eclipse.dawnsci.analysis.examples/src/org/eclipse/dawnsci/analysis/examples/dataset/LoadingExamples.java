/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.analysis.examples.dataset;

import java.io.File;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.examples.Activator;
import org.junit.Test;

/**
 * 
 * Simply loads data using the LoaderService
 * 
 * You may also provide your own loader to the service using an 
 * extension point. Please contact DAWN-DEV@JISCMAIL.AC.UK for information
 * on doing this.
 * 
 * @author Matthew Gerring
 *
 */
public class LoadingExamples {
	
	private static ILoaderService service;
	
	public static void setLoaderService(ILoaderService s) {
		service = s;
	}
	
	@Test
	public void loadSeveral1D() throws Throwable {
		
		final File        loc  = new File(Activator.getBundleLocation(Activator.PLUGIN_ID), "metalmix.mca");
		final IDataHolder dh   = service.getData(loc.getAbsolutePath(), new IMonitor.Stub());
		
		System.out.println("We have several data as follows: ");
		for (String name : dh.getNames()) {
			System.out.println("We have loaded '"+name+"' with shape "+Arrays.toString(dh.getDataset(name).getShape()));
		}

	}
		
	@Test
	public void load2D() throws Throwable {
		
		final File     loc   = new File(Activator.getBundleLocation(Activator.PLUGIN_ID), "pow_M99S5_1_0001.cbf");
		final IDataset image = service.getDataset(loc.getAbsolutePath(), new IMonitor.Stub());
		
		System.out.println("We have loaded an image of shape "+Arrays.toString(image.getShape()));
	}
}
