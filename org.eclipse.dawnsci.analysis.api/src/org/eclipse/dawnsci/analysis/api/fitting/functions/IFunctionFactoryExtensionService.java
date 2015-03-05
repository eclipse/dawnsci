/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.fitting.functions;

import java.util.List;

public interface IFunctionFactoryExtensionService {
	/**
	 * Load extension points & register with the FunctionFactory
	 */
	public void registerFunctionExtensionPoints();
	
	/**
	 * Report the plugin which contributed the function
	 */
	public List<String> getPlugins();

}
