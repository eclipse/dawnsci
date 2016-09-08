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
import java.util.List;

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
public class MarshallerServiceClassRegistry implements IClassRegistry {
	// TODO @Martin: Update to store class information in HashMap.
	// This initial version was used to enable the given registries to use logic
	// behind-the-scenes, but this is likely to be slow / not used.

	List<IClassRegistry> registries = new ArrayList<IClassRegistry>();

	public MarshallerServiceClassRegistry(List<IClassRegistry> extraRegisters) {
		try {
	        IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.analysis.api.classregistry");

	        for (IConfigurationElement e : elements) {
				final IClassRegistry registry = (IClassRegistry) e.createExecutableExtension("class");
				registries.add(registry);
	        }
		} catch (Exception ne) {
			// It is legal to fail the configuration elements because
			// one may use the json service in non-OSGi environment.
		}
		if (extraRegisters!=null) registries.addAll(extraRegisters);
	}

	@Override
	public Class<?> getClassFromId(String id) {
		Class<?> clazz;
		for (IClassRegistry r : registries) {
			clazz = r.getClassFromId(id);
			if (clazz != null) return clazz;
		}

		return null;
	}

	@Override
	public String getIdFromClass(Class<?> clazz) {
		String id;
		for (IClassRegistry r : registries) {
			id = r.getIdFromClass(clazz);
			if (id != null) return id;
		}
		return null;
	}

	@Override
	public Boolean hasId(String id) {
		for (IClassRegistry r : registries) {
			if (r.hasId(id)) return true;
		}
		return false;
	}

	@Override
	public Boolean hasClass(Class<?> clazz) {
		for (IClassRegistry r : registries) {
			if (r.hasClass(clazz)) return true;
		}
		return false;
	}

}
