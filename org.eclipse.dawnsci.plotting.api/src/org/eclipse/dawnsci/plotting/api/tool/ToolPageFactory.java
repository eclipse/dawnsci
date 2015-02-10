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
package org.eclipse.dawnsci.plotting.api.tool;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class ToolPageFactory {

	/**
	 * Get a tool by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static IToolPage getToolPage(final String id) throws Exception {
		
	    final IConfigurationElement[] configs = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.plotting.api.toolPage");
	    for (final IConfigurationElement e : configs) {
	    	if (id.equals(e.getAttribute("id"))) {
	    		return (IToolPage)e.createExecutableExtension("class");
	    	}
	    }
        return null;
	}
	
	/**
	 * Get a tool by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<String> getToolPageIds() throws Exception {
		
		final List<String> ret = new ArrayList<String>(31);
	    final IConfigurationElement[] configs = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.plotting.api.toolPage");
	    for (final IConfigurationElement e : configs) {
            ret.add(e.getAttribute("id"));
	    }
        return ret;
	}

}
