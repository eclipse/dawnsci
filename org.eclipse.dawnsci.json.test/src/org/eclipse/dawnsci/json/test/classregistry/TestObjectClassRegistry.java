/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test.classregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.dawnsci.json.test.testobject.TestStatus;
import org.eclipse.dawnsci.json.test.testobject.TestStatusBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;

public class TestObjectClassRegistry implements IClassRegistry {

	private static final Map<String, Class<?>> idToClassMap;
	static {
		Map<String, Class<?>> tmp = new HashMap<String, Class<?>>();

		tmp.put("jsontest.animal", Animal.class);
		tmp.put("jsontest.animal.bird", Bird.class);
		tmp.put("jsontest.animal.cat", Cat.class);
		tmp.put("jsontest.containerbean", ContainerBean.class);
		tmp.put("jsontest.objectwrapper", ObjectWrapper.class);
		tmp.put("jsontest.person", Person.class);
		tmp.put("jsontest.projectbean", ProjectBean.class);
		tmp.put("jsontest.roiwrapper", ROIWrapper.class);
		tmp.put("jsontest.sweepbean", SweepBean.class);
		tmp.put("jsontest.teststatus", TestStatus.class);
		tmp.put("jsontest.teststatusbean", TestStatusBean.class);
		tmp.put("jsontest.testtypebean", TestTypeBean.class);
		tmp.put("jsontest.testtyperegistered", TestTypeRegisteredImpl.class);

		idToClassMap = Collections.unmodifiableMap(tmp);
	}

	@Override
	public Map<String, Class<?>> getIdToClassMap() {
		return idToClassMap;
	}
}
