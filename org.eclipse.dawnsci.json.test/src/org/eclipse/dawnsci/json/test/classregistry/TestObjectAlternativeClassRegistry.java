/*-
 * Copyright © 2016 Diamond Light Source Ltd.
 *
 * This file is part of GDA.
 *
 * GDA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License version 3 as published by the Free
 * Software Foundation.
 *
 * GDA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with GDA. If not, see <http://www.gnu.org/licenses/>.
 */

package org.eclipse.dawnsci.json.test.classregistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredAlternativeImpl;

/**
 * Additional class registry, only used in JsonMarshallerClassRegistryTest.
 *
 */
public class TestObjectAlternativeClassRegistry implements IClassRegistry {

	private static HashMap<String, Class<?>> idToClassMap = new HashMap<String, Class<?>>();
	static {
		idToClassMap.put("jsontest.testtyperegisteredalt", TestTypeRegisteredAlternativeImpl.class);
	}

	@Override
	public Class<?> getClassFromId(String id) {
		return idToClassMap.get(id);
	}

	@Override
	public String getIdFromClass(Class<?> clazz) {
		for (Entry<String, Class<?>> entry : idToClassMap.entrySet()) {
			if (entry.getValue().equals(clazz)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public boolean isId(String id) {
		return idToClassMap.containsKey(id);
	}

	@Override
	public boolean isClass(Class<?> clazz) {
		return idToClassMap.containsValue(clazz);
	}

	@Override
	public Map<String, Class<?>> getIdToClassMap() {
		return idToClassMap;
	}
}
