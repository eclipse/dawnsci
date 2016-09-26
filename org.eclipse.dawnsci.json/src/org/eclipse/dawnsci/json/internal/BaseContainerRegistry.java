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

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
*
* An IClassRegister that is used to compile all the external extension
* IClassRegister's into a single object, using an extension point on this
* bundle.
*
* @author Martin Gaughran
*/
class BaseContainerRegistry implements IClassRegistry {

	// TODO: Delete class.

	private ObjectMapper mapper = new ObjectMapper();

	private static final HashMap<String, Class<?>> idToClassMap = new HashMap<String, Class<?>>(){
		{
			put("ArrayList", ArrayList.class);
			put("Enum", Enum.class);
			put("HashMap", HashMap.class);
		}
	};
	private static final HashMap<String, String> classTypeToIdMap = new HashMap<String, String>(){
		{
			put("ArrayType", "ArrayList");
			put("EnumType", "Enum");
			put("MapLikeType", "HashMap");

		}
	};

	@Override
	public Class<?> getClassFromId(String id) {
		return idToClassMap.get(id);
	}

	@Override
	public String getIdFromClass(Class<?> clazz) {
		return classTypeToIdMap.get(getTypeKey(clazz));
	}

	@Override
	public Boolean hasId(String id) {
		return (idToClassMap.get(id) != null) ? true : false;
	}

	@Override
	public Boolean hasClass(Class<?> clazz) {
		return (getTypeKey(clazz) != null) ? true : false;
	}

	private String getTypeKey(Class<?> clazz) {
		JavaType classType = mapper.constructType(clazz);

		System.out.println(clazz.toString());

		if (classType.getContentType() != null) {
			System.out.println(classType.getContentType().toString());
		} else {
			System.out.println("ContentType is null");
		}

		System.out.println(classType.isCollectionLikeType());
		System.out.println(classType.isArrayType());
		System.out.println(classType.isEnumType());
		System.out.println(classType.isMapLikeType());


		if (!classType.isCollectionLikeType()) return null;

		if (classType.isArrayType()) return "ArrayType";
		if (classType.isEnumType()) return "EnumType";
		if (classType.isMapLikeType()) return "MapLikeType";

		return null;
	}

}
