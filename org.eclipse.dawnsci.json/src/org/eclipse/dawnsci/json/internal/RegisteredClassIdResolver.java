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

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * {@link TypeIdResolver} implementation which converts between JSON strings and the class implementation,
 * using an IClassRegistry.
 * <p>
 *
 * @author Martin Gaughran
 *
 */
public class RegisteredClassIdResolver extends TypeIdResolverBase {

	private IClassRegistry registry;

	public RegisteredClassIdResolver(JavaType baseType, TypeFactory typeFactory, IClassRegistry registry) {
		super(baseType, typeFactory);
		this.registry = registry;
	}

	@Override
	public Id getMechanism() { return Id.CUSTOM; }

	@Override
	public String idFromValue(Object value) {
		return idFromValueAndType(value, value.getClass());
	}

	@Override
	public String idFromValueAndType(Object value, Class<?> clazz) {
		return registry.getIdFromClass(clazz);
	}

	@Override
	public JavaType typeFromId(String id) {
		Class<?> clazz = registry.getClassFromId(id);
		if (clazz == null) {
			throw new IllegalArgumentException("Class " + id + " not found");
		}
		JavaType type = _typeFactory.constructSpecializedType(_baseType, clazz);
		return type;

	}
}
