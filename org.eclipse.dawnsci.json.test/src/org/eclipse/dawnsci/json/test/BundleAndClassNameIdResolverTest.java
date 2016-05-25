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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.json.internal.BundleAndClassNameIdResolver;
import org.eclipse.dawnsci.json.internal.BundleAndClassNameIdResolver.ClassFinder;
import org.eclipse.dawnsci.json.test.testobject.Animal;
import org.eclipse.dawnsci.json.test.testobject.Bird;
import org.eclipse.dawnsci.json.test.testobject.Cat;
import org.eclipse.dawnsci.json.test.testobject.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Unit tests for the BundleAndClassNameIdResolver
 *
 * @author Colin Palmer
 *
 */
public class BundleAndClassNameIdResolverTest {

	private static final String OBJECT_ID = "bundle=&version=&class=java.lang.Object";
	private static final String STRING_ID = "bundle=&version=&class=java.lang.String";
	private static final String ARRAY_LIST_ID = "bundle=&version=&class=java.util.ArrayList";
	private static final String PERSON_ID = "bundle=uk.ac.diamond.daq.test.example&version=1.2.0.test&class=org.eclipse.dawnsci.json.test.testobject.Person";
	private static final String PERSON_ARRAY_ID = "bundle=&version=&class=[Lorg.eclipse.dawnsci.json.test.testobject.Person;";
	private static final String BIRD_ID = "bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird";
	private static final String CAT_ID = "bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat";
	private static final String BIRD_FROM_OTHER_VERSION = "bundle=uk.ac.diamond.daq.test.example&version=%s&class=org.eclipse.dawnsci.json.test.testobject.Bird";
	private static final String NONEXISTENT_CORE_CLASS_ID = "bundle=&version=&class=org.eclipse.dawnsci.json.internal.ClassNotFound";
	private static final String NONEXISTENT_BUNDLE_ID = "bundle=uk.ac.diamond.daq.nonexistent&version=1.0.0&classorg.eclipse.dawnsci.json.internal.ClassNotFound";

	private BundleAndClassNameIdResolver resolver;
	private TestBundleProvider bundleProvider;
	private ClassFinder classFinder;

	@Mock private Bundle exampleBundleV1;
	@Mock private Bundle exampleBundleV2;
	@Mock private Bundle exampleBundleV3;
	@Mock private Bundle otherExampleBundle;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(exampleBundleV1.getSymbolicName()).thenReturn(Person.BUNDLE_NAME_FOR_TESTING);
		when(exampleBundleV1.getVersion()).thenReturn(new Version(Person.BUNDLE_VERSION_FOR_TESTING));
		MockClassLoaderAnswer exampleV1Answer = new MockClassLoaderAnswer(Person.class, Animal.class);
		when(exampleBundleV1.loadClass(any())).thenAnswer(exampleV1Answer);

		when(exampleBundleV2.getSymbolicName()).thenReturn(Bird.BUNDLE_NAME_FOR_TESTING);
		when(exampleBundleV2.getVersion()).thenReturn(new Version(Bird.BUNDLE_VERSION_FOR_TESTING));
		when(exampleBundleV2.loadClass(any())).thenAnswer(new MockClassLoaderAnswer(Person.class, Animal.class, Bird.class));

		when(exampleBundleV3.getSymbolicName()).thenReturn(Bird.BUNDLE_NAME_FOR_TESTING);
		when(exampleBundleV3.getVersion()).thenReturn(new Version(3, 0 ,0));
		when(exampleBundleV3.loadClass(any())).thenAnswer(new MockClassLoaderAnswer(Person.class, Animal.class, Bird.class));

		when(otherExampleBundle.getSymbolicName()).thenReturn(Cat.BUNDLE_NAME_FOR_TESTING);
		when(otherExampleBundle.getVersion()).thenReturn(Version.emptyVersion);
		when(otherExampleBundle.loadClass(any())).thenAnswer(new MockClassLoaderAnswer(Cat.class));

		TestBundleProvider testBundleProvider = new TestBundleProvider();
		testBundleProvider.registerBundleForClass(Person.class, exampleBundleV1);
		testBundleProvider.registerBundleForClass(Animal.class, exampleBundleV1);
		testBundleProvider.registerBundleForClass(Bird.class, exampleBundleV2);
		testBundleProvider.registerBundleForClass(Cat.class, otherExampleBundle);
		testBundleProvider.addBundle(exampleBundleV3);
		this.bundleProvider = testBundleProvider;

		this.classFinder = className -> {
			if (className.startsWith("java.")) {
				return Class.forName(className);
			}
			throw new ClassNotFoundException();
		};

		// Default resolver with null baseType works in most cases
		resolver = new BundleAndClassNameIdResolver(null, TypeFactory.defaultInstance(), bundleProvider, classFinder);
	}

	@After
	public void tearDown() throws Exception {
		resolver = null;
		bundleProvider = null;
		BundleAndClassNameIdResolver.clearCache();
	}

	@Test
	public void testIdFromObjectValue() throws Exception {
		String id = resolver.idFromValue(new Object());
		assertThat(id, is(equalTo(OBJECT_ID)));
	}

	@Test
	public void testIdFromObjectClass() throws Exception {
		String id = resolver.idFromValueAndType(null, Object.class);
		assertThat(id, is(equalTo(OBJECT_ID)));
	}

	@Test
	public void testIdFromString() throws Exception {
		String id = resolver.idFromValue("Hello world!");
		assertThat(id, is(equalTo(STRING_ID)));
	}

	@Test
	public void testIdFromPersonValue() throws Exception {
		String id = resolver.idFromValue(new Person());
		assertThat(id, is(equalTo(PERSON_ID)));
	}

	@Test
	public void testIdFromPersonClass() throws Exception {
		String id = resolver.idFromValueAndType(null, Person.class);
		assertThat(id, is(equalTo(PERSON_ID)));
	}

	@Test
	public void testIdFromBirdValue() throws Exception {
		String id = resolver.idFromValue(new Bird());
		assertThat(id, is(equalTo(BIRD_ID)));
	}

	@Test
	public void testIdFromPersonValueAndBirdClass() throws Exception {
		// This test documents the resolver behaviour if the types are incompatible, but that situation should not arise.
		// Possibly the resolver should actually throw an exception if it does?
		String id = resolver.idFromValueAndType(new Person(), Bird.class);
		assertThat(id, is(equalTo(BIRD_ID)));
	}

	@Test
	public void testIdFromCatValue() throws Exception {
		String id = resolver.idFromValue(new Cat());
		assertThat(id, is(equalTo(CAT_ID)));
	}

	@Test
	public void testIdFromPersonArrayValue() throws Exception {
		Person[] people = new Person[] { new Person(), new Person() };
		String id = resolver.idFromValue(people);
		assertThat(id, is(equalTo(PERSON_ARRAY_ID)));
	}

	@Test
	public void testIdFromPersonListValue() throws Exception {
		List<Person> people = Arrays.asList(new Person[] { new Person(), new Person() });
		String id = resolver.idFromValue(people);
		assertThat(id, is(equalTo(ARRAY_LIST_ID)));
	}

	@Test
	public void testObjectFromId() throws Exception {
		SimpleType objectType = SimpleType.construct(Object.class);
		resolver = new BundleAndClassNameIdResolver(objectType, TypeFactory.defaultInstance(), bundleProvider, classFinder);
		JavaType resolvedType = resolver.typeFromId(OBJECT_ID);
		assertThat(resolvedType, is(equalTo(objectType)));
	}

	@Test
	public void testPersonFromId() throws Exception {
		SimpleType personType = SimpleType.construct(Person.class);
		resolver = new BundleAndClassNameIdResolver(personType, TypeFactory.defaultInstance(), bundleProvider, classFinder);
		JavaType resolvedType = resolver.typeFromId(PERSON_ID);
		assertThat(resolvedType, is(equalTo(personType)));
	}

	@Test
	public void testBirdFromId() throws Exception {
		// Important test - provides supertype (Animal) as base type but expects the subtype (Bird) to be returned
		SimpleType animalType = SimpleType.construct(Animal.class);
		resolver = new BundleAndClassNameIdResolver(animalType, TypeFactory.defaultInstance(), bundleProvider, classFinder);
		JavaType resolvedType = resolver.typeFromId(BIRD_ID);
		SimpleType birdType = SimpleType.construct(Bird.class);
		assertThat(resolvedType, is(equalTo(birdType)));
		// Check it was loaded from the correct bundle version
		verify(exampleBundleV1, never()).loadClass(Bird.class.getName());
		verify(exampleBundleV2).loadClass(Bird.class.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCoreClassNotFound() throws Exception {
		resolver.typeFromId(NONEXISTENT_CORE_CLASS_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBundleClassNotFound() throws Exception {
		resolver.typeFromId(NONEXISTENT_BUNDLE_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBirdFromVeryOldBundleVersion() throws Exception {
		SimpleType animalType = SimpleType.construct(Animal.class);
		resolver = new BundleAndClassNameIdResolver(animalType, TypeFactory.defaultInstance(), bundleProvider, classFinder);
		resolver.typeFromId(String.format(BIRD_FROM_OTHER_VERSION, "1.1.0"));
		// This should throw after an attempt to load the class from exampleBundleV1, which is the closest match
		// available but does not have Bird.class
	}

	@Test
	public void testBirdFromOlderBundleVersion() throws Exception {
		SimpleType animalType = SimpleType.construct(Animal.class);
		resolver = new BundleAndClassNameIdResolver(animalType, TypeFactory.defaultInstance(), bundleProvider, classFinder);
		JavaType resolvedType = resolver.typeFromId(String.format(BIRD_FROM_OTHER_VERSION, "1.9.0"));
		SimpleType birdType = SimpleType.construct(Bird.class);
		assertThat(resolvedType, is(equalTo(birdType)));
		// Check it was loaded from the correct bundle version
		verify(exampleBundleV1, never()).loadClass(Bird.class.getName());
		verify(exampleBundleV2).loadClass(Bird.class.getName());
	}

	@Test
	public void testBirdFromNewerBundleVersion() throws Exception {
		SimpleType animalType = SimpleType.construct(Animal.class);
		resolver = new BundleAndClassNameIdResolver(animalType, TypeFactory.defaultInstance(), bundleProvider, classFinder);
		JavaType resolvedType = resolver.typeFromId(String.format(BIRD_FROM_OTHER_VERSION, "2.0.1"));
		SimpleType birdType = SimpleType.construct(Bird.class);
		assertThat(resolvedType, is(equalTo(birdType)));
		// Check it was loaded from the correct bundle version
		verify(exampleBundleV1, never()).loadClass(Bird.class.getName());
		verify(exampleBundleV2, never()).loadClass(Bird.class.getName());
		verify(exampleBundleV3).loadClass(Bird.class.getName());
	}

	@Test
	public void testCachePreventsMultipleCallsToBundleProvider() throws Exception {
		SimpleType personType = SimpleType.construct(Person.class);
		BundleAndClassNameIdResolver personResolver = new BundleAndClassNameIdResolver(personType, TypeFactory.defaultInstance(), bundleProvider);
		personResolver.typeFromId(PERSON_ID);
		assertThat(bundleProvider.wasGetBundlesCalled(), is(true));
		personResolver.typeFromId(PERSON_ID);
		assertThat(bundleProvider.wasGetBundlesCalled(), is(false));
		SimpleType animalType = SimpleType.construct(Animal.class);
		BundleAndClassNameIdResolver animalResolver = new BundleAndClassNameIdResolver(animalType, TypeFactory.defaultInstance(), bundleProvider);
		animalResolver.typeFromId(BIRD_ID);
		assertThat(bundleProvider.wasGetBundlesCalled(), is(true));
		animalResolver.typeFromId(BIRD_ID);
		animalResolver.typeFromId(BIRD_ID);
		assertThat(bundleProvider.wasGetBundlesCalled(), is(false));
		animalResolver.typeFromId(CAT_ID);
		assertThat(bundleProvider.wasGetBundlesCalled(), is(true));
		animalResolver.typeFromId(CAT_ID);
		animalResolver.typeFromId(BIRD_ID);
		animalResolver.typeFromId(CAT_ID);
		assertThat(bundleProvider.wasGetBundlesCalled(), is(false));
	}
}
