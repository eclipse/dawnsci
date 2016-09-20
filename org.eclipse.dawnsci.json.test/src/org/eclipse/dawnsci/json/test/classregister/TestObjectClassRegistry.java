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

package org.eclipse.dawnsci.json.test.classregister;

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;
import org.eclipse.dawnsci.json.test.testobject.ClassRegistry;

public class TestObjectClassRegistry implements IClassRegistry {

	@Override
	public Class<?> getClassFromId(String id) {
		return ClassRegistry.getClassFromId(id);
	}

	@Override
	public String getIdFromClass(Class<?> clazz) {
		return ClassRegistry.getIdFromClass(clazz);
	}

	@Override
	public Boolean hasId(String id) {
		return ClassRegistry.hasId(id);
	}

	@Override
	public Boolean hasClass(Class<?> clazz) {
		return ClassRegistry.hasClass(clazz);
	}

}
