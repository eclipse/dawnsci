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
package org.eclipse.dawnsci.slicing.api;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;


/**
 * This class can read contributed slice components and return an
 * implementation of ISliceComponent which can be used for slicing.
 * 
 * A preference page will be added for choosing component from a drop down.
 * 
 * @author Matthew Gerring
 *
 */
public class SlicingFactory {
	
	
	/**
	 * 
	 * @param sliceGalleryId the id of a view which implements ISliceGallery
	 * @return
	 * @throws Exception
	 */
	public static ISliceSystem createSliceSystem(String sliceGalleryId) throws Exception {

		ISliceSystem system = createSliceSystem();
		system.setSliceGalleryId(sliceGalleryId);
		return system;
	}
	
	/**
	 * Current implementation just gives back the first slice component
	 * it can find.
	 * @return ISliceSystem
	 * 
	 */
	public static ISliceSystem createSliceSystem() throws Exception {
		
        IConfigurationElement[] systems = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.slicing.api.slicingSystem");
        return (ISliceSystem)(systems[0].createExecutableExtension("class"));
	}
}
