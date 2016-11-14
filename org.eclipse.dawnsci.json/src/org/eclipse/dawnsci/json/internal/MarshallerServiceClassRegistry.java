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

package org.eclipse.dawnsci.json.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;

/**
 *
 * An IClassRegistry that is used to compile all the external extension
 * IClassRegistry's into a single object, using an extension point on this
 * bundle.
 *
 * @author Martin Gaughran
 */
public class MarshallerServiceClassRegistry {

	private Map<String, Class<?>> idToClassMap = new HashMap<String, Class<?>>();
	private boolean platformIsRunning;

	public MarshallerServiceClassRegistry(List<IClassRegistry> extraRegistries) throws ClassRegistryDuplicateIdException, CoreException {
		List<IClassRegistry> registries = new ArrayList<IClassRegistry>();
		platformIsRunning = Platform.isRunning();

		if (extraRegistries!=null) registries.addAll(extraRegistries);

		// Extensions not available for unit tests / non-OSGI mode.
		registries.addAll(getAvailableRegistryExtensions());

		for (IClassRegistry registry : registries) {
			populateClassMap(registry);
		}
	}

	private List<IClassRegistry> getAvailableRegistryExtensions() throws CoreException {
		List<IClassRegistry> registries = new ArrayList<IClassRegistry>();

		if (!platformIsRunning) return registries;

        IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.analysis.api.classregistry");

        for (IConfigurationElement e : elements) {
			final IClassRegistry registry = (IClassRegistry) e.createExecutableExtension("class");
			registries.add(registry);
        }

		return registries;
	}

	private void populateClassMap(IClassRegistry registry) throws ClassRegistryDuplicateIdException {
		Map<String, Class<?>> registryIdToClassMap = registry.getIdToClassMap();

		for (Entry<String, Class<?>> entry : registryIdToClassMap.entrySet()) {
			Class<?> prevVal = null;

			prevVal = idToClassMap.put(entry.getKey(), entry.getValue());
			// prevVal stores previous value, if there is one
			if ((prevVal != null) && (prevVal != entry.getValue())){
				String message = "Class id clash with registry: " + registry.toString() +
						". Previous value for id " + entry.getKey() + " is " + prevVal +
						". Overwriting value is: " + entry.getValue() + ".";
				throw new ClassRegistryDuplicateIdException(message);
			}
		}
	}

	public Class<?> getClassFromId(String id) {
		return idToClassMap.get(id);
	}

	public String getIdFromClass(Class<?> clazz) {
		for (Entry<String, Class<?>> entry : idToClassMap.entrySet()) {
			if (entry.getValue().equals(clazz)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public boolean isId(String id) {
		return idToClassMap.containsKey(id);
	}

	public boolean isClass(Class<?> clazz) {
		return idToClassMap.containsValue(clazz);
	}

	public class ClassRegistryDuplicateIdException extends Exception {

		public ClassRegistryDuplicateIdException(String message) {
			super(message);
		}
	}
}
