/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Colin Palmer - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.json.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * An Answer which simulates loading classes by name.
 * <p>
 * It will search the classes given in the constructor for one whose name matches the first method argument, and throw
 * a ClassNotFoundException if no classes match.
 *
 * @author Colin Palmer
 *
 */
public class MockClassLoaderAnswer implements Answer<Class<?>> {

	final Set<Class<?>> classes;

	public MockClassLoaderAnswer(Class<?>... classes) {
		this.classes = new HashSet<>();
		this.classes.addAll(Arrays.asList(classes));
	}

	@Override
	public Class<?> answer(InvocationOnMock invocation) throws Throwable {
		String className = (String) invocation.getArguments()[0];
		for (Class<?> clazz : classes) {
			if (clazz.getName().equals(className)) {
				return clazz;
			}
		}
		throw new ClassNotFoundException("Class " + className + " not found");
	}
}