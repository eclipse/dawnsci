/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.io;

import java.util.List;

public interface ILoaderFactoryExtensionService {
	/**
	 * Loads external loaders into the factory.
	 */
	public void registerExtensionPoints();

	/**
	 * 
	 * @return list of plugins that contributed loaders
	 */
	public List<String> getPlugins();

	/**
	 * 
	 * @return list of extension strings that contributed loaders
	 */
	public List<String> getExtensions();

	/**
	 * The expression for parsing stacks.
	 * This expression is set by eclipse preference and a preference page exists to edit it.
	 * @return expression - for instance:  "(.+)_(\d+)."
	 */
	public String getStackExpression();
}
