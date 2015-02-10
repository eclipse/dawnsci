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
package org.eclipse.dawnsci.plotting.api.annotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.dawnsci.plotting.api.IPlottingSystem;

public class AnnotationUtils {

	/**
	 * Call to get a unique annotation name 
	 * @param nameStub
	 * @param system
	 * @return
	 */
	public static String getUniqueAnnotation(final String nameStub, final IPlottingSystem system, final String... usedNames) {
		int i = 1;
		@SuppressWarnings("unchecked")
		final List<String> used = usedNames!=null ? (List<String>) Arrays.asList(usedNames) : (List<String>) Collections.EMPTY_LIST;
		while(system.getAnnotation(nameStub+" "+i)!=null || used.contains(nameStub+" "+i)) {
			++i;
			if (i>10000) break; // something went wrong!
		}
		return nameStub+" "+i;
	}

	/**
	 * 
	 * @param system
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static final IAnnotation replaceCreateAnnotation(IPlottingSystem system, String name) throws Exception {
		
        if (system.getAnnotation(name)!=null) {
        	system.removeAnnotation(system.getAnnotation(name));
        }
		return system.createAnnotation(name);
	}

}
