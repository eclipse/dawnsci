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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * {@link TypeIdResolver} implementation which converts between JSON strings and the information necessary to load a class in OSGi, that is,
 * the fully-qualified class name, bundle symbolic name and bundle version.
 * <p>
 * Generic types are currently not handled. It might be possible to do this (perhaps by delegating to ClassNameIdResolver) but all calls made
 * to ClassUtils would need overriding to use correct bundle classloaders.
 * <p>
 * Also, non-static inner types will probably fail with the current implementation, but this has not been tested.
 *
 * @author Colin Palmer
 *
 */
public class RegisteredClassIdResolver extends TypeIdResolverBase {

	/**
	 * Interface to allow Jackson ClassUtil to be mocked for testing
	 */
	public interface ClassFinder {
		public Class<?> findClass(String className) throws ClassNotFoundException;
	}

//	private static final Logger logger = LoggerFactory.getLogger(BundleAndClassNameIdResolver.class);
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
//		try {
		Class<?> clazz = registry.getClassFromId(id);
		// This probably doesn't handle generics, except for arrays and collections
		// see ClassNameIdResolver#typeFromId() for more on this
		//JavaType type = _typeFactory.constructSpecializedType(_baseType, clazz);
		return _typeFactory.constructSpecializedType(_baseType, clazz);

		//TODO I need to throw an exception when a class id cannot be decoded.

//		} catch (ClassNotFoundException e) {
//			throw new IllegalArgumentException("Class " + id + " not found", e);
//		}
	}
}
