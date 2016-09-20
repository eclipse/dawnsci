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

package org.eclipse.dawnsci.json.test.testobject;

import java.util.HashMap;
import java.util.Map.Entry;

public class ClassRegistry {

	private static HashMap<String, Class<?>> idToClassMap = new HashMap<String, Class<?>>();

	public static void register(String id, Class<?> clazz) {
		idToClassMap.put(id, clazz);
	}

	public static Class<?> getClassFromId(String id) {
		return idToClassMap.get(id);
	}

	public static String getIdFromClass(Class<?> clazz) {
		for (Entry<String, Class<?>> entry : idToClassMap.entrySet()) {
			if (entry.getValue().equals(clazz)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static Boolean hasId(String id) {
		return idToClassMap.containsKey(id);
	}

	public static Boolean hasClass(Class<?> clazz) {
		return idToClassMap.containsValue(clazz);
	}
}
