package org.eclipse.dawnsci.json.test.classregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;
import org.eclipse.dawnsci.json.test.testobject.CatWrapper;

/**
 * Additional class registry, only used in JsonMarshallerClassRegistryTest. This is used
 * to test issues relating to duplicate id's.
 *
 */
public class TestObjectClashingIdClassRegistry implements IClassRegistry {

	private static final Map<String, Class<?>> idToClassMap;
	static {
		Map<String, Class<?>> tmp = new HashMap<String, Class<?>>();

		tmp.put("jsontest.animal", CatWrapper.class);

		idToClassMap = Collections.unmodifiableMap(tmp);
	}

	@Override
	public Map<String, Class<?>> getIdToClassMap() {
		return idToClassMap;
	}
}
