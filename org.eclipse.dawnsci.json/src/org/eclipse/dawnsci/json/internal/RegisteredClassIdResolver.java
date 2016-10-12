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

	private MarshallerServiceClassRegistry registry;

	public RegisteredClassIdResolver(JavaType baseType, TypeFactory typeFactory, MarshallerServiceClassRegistry registry) {
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
		String id = registry.getIdFromClass(clazz);

		if (id == null) {
			// As far as I can tell, there isn't a standard way of indicating that an id
			// cannot be found for a particular type. As a result, this may or may not get
			// wrapped by a JsonMappingException, depending on where this code gets
			// called by Jackson, resulting in different error signatures. The text is
			// always included, however!
			throw new IllegalArgumentException("Id not found for " + clazz.toString());
		}

		return id;
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
