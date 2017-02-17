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

package org.eclipse.dawnsci.json;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;

class ArrayRegistry implements IClassRegistry {

	private static final Map<String, Class<?>> idToClassMap;
	static {

		Map<String, Class<?>> tmp = new HashMap<>();
		tmp.put("String[]",    String[].class);

		idToClassMap = Collections.unmodifiableMap(tmp);
	}
	@Override
	public Map<String, Class<?>> getIdToClassMap() {
		return idToClassMap;
	}

}
