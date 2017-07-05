/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.conversion;

import java.util.List;

public interface IConversionSchemeService {

	public List<IConversionScheme> getSchemes();

	public List<IConversionScheme> getSchemes(boolean visibleOnly);
	
	public IConversionScheme getScheme(String schemeClassName);
	
	public <U extends IConversionScheme> U getScheme(Class<U> schemeClass);
}
