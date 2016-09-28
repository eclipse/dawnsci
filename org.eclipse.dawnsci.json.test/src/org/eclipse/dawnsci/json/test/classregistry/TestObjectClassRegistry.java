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
import org.eclipse.dawnsci.json.test.testobject.Animal;
import org.eclipse.dawnsci.json.test.testobject.Bird;
import org.eclipse.dawnsci.json.test.testobject.Cat;
import org.eclipse.dawnsci.json.test.testobject.ContainerBean;
import org.eclipse.dawnsci.json.test.testobject.ObjectWrapper;
import org.eclipse.dawnsci.json.test.testobject.Person;
import org.eclipse.dawnsci.json.test.testobject.ProjectBean;
import org.eclipse.dawnsci.json.test.testobject.ROIWrapper;
import org.eclipse.dawnsci.json.test.testobject.SweepBean;
import org.eclipse.dawnsci.json.test.testobject.TestIdBean;
import org.eclipse.dawnsci.json.test.testobject.TestStatus;
import org.eclipse.dawnsci.json.test.testobject.TestStatusBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;

public class TestObjectClassRegistry implements IClassRegistry {

	private static HashMap<String, Class<?>> idToClassMap = new HashMap<String, Class<?>>();
	static {
		idToClassMap.put("jsontest.animal", Animal.class);
		idToClassMap.put("jsontest.animal.bird", Bird.class);
		idToClassMap.put("jsontest.animal.cat", Cat.class);
		idToClassMap.put("jsontest.containerbean", ContainerBean.class);
		idToClassMap.put("jsontest.objectwrapper", ObjectWrapper.class);
		idToClassMap.put("jsontest.person", Person.class);
		idToClassMap.put("jsontest.projectbean", ProjectBean.class);
		idToClassMap.put("jsontest.roiwrapper", ROIWrapper.class);
		idToClassMap.put("jsontest.sweepbean", SweepBean.class);
		idToClassMap.put("jsontest.testidbean", TestIdBean.class);
		idToClassMap.put("jsontest.teststatus", TestStatus.class);
		idToClassMap.put("jsontest.teststatusbean", TestStatusBean.class);
		idToClassMap.put("jsontest.testtypebean", TestTypeBean.class);
		idToClassMap.put("jsontest.testtyperegistered", TestTypeRegisteredImpl.class);
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
