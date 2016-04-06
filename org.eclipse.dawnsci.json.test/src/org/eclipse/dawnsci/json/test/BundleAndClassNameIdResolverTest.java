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
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.json.internal.BundleAndClassNameIdResolver;
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
	private static final String NONEXISTENT_BIRD_ID = "bundle=uk.ac.diamond.daq.test.example&version=1.2.0.test&class=org.eclipse.dawnsci.json.test.testobject.Bird";
	private static final String NONEXISTENT_CORE_CLASS_ID = "bundle=&version=&class=org.eclipse.dawnsci.json.internal.ClassNotFound";
	private static final String NONEXISTENT_BUNDLE_ID = "bundle=uk.ac.diamond.daq.nonexistent&version=1.0.0&classorg.eclipse.dawnsci.json.internal.ClassNotFound";

	private BundleAndClassNameIdResolver resolver;
	private TestBundleProvider bundleProvider;

	@Mock private Bundle exampleBundleV1;
	@Mock private Bundle exampleBundleV2;
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
		when(exampleBundleV2.loadClass(any())).thenAnswer(new MockClassLoaderAnswer(Bird.class));

		when(otherExampleBundle.getSymbolicName()).thenReturn(Cat.BUNDLE_NAME_FOR_TESTING);
		when(otherExampleBundle.getVersion()).thenReturn(Version.emptyVersion);
		when(otherExampleBundle.loadClass(any())).thenAnswer(new MockClassLoaderAnswer(Cat.class));

		TestBundleProvider testBundleProvider = new TestBundleProvider();
		testBundleProvider.registerBundleForClass(Person.class, exampleBundleV1);
		testBundleProvider.registerBundleForClass(Animal.class, exampleBundleV1);
		testBundleProvider.registerBundleForClass(Bird.class, exampleBundleV2);
		testBundleProvider.registerBundleForClass(Cat.class, otherExampleBundle);
		this.bundleProvider = testBundleProvider;

		// Default resolver with null baseType works in most cases
		resolver = new BundleAndClassNameIdResolver(null, TypeFactory.defaultInstance(), bundleProvider);
	}

	@After
	public void tearDown() throws Exception {
		resolver = null;
		bundleProvider = null;
		BundleAndClassNameIdResolver.clearCache();
	}

	@Test
	public void testIdFromObjectValue() {
		String id = resolver.idFromValue(new Object());
		assertThat(id, is(equalTo(OBJECT_ID)));
	}

	@Test
	public void testIdFromObjectClass() {
		String id = resolver.idFromValueAndType(null, Object.class);
		assertThat(id, is(equalTo(OBJECT_ID)));
	}

	@Test
	public void testIdFromString() {
		String id = resolver.idFromValue("Hello world!");
		assertThat(id, is(equalTo(STRING_ID)));
	}

	@Test
	public void testIdFromPersonValue() {
		String id = resolver.idFromValue(new Person());
		assertThat(id, is(equalTo(PERSON_ID)));
	}

	@Test
	public void testIdFromPersonClass() {
		String id = resolver.idFromValueAndType(null, Person.class);
		assertThat(id, is(equalTo(PERSON_ID)));
	}

	@Test
	public void testIdFromBirdValue() {
		String id = resolver.idFromValue(new Bird());
		assertThat(id, is(equalTo(BIRD_ID)));
	}

	@Test
	public void testIdFromPersonValueAndBirdClass() {
		// This test documents the resolver behaviour if the types are incompatible, but that situation should not arise.
		// Possibly the resolver should actually throw an exception if it does?
		String id = resolver.idFromValueAndType(new Person(), Bird.class);
		assertThat(id, is(equalTo(BIRD_ID)));
	}

	@Test
	public void testIdFromCatValue() {
		String id = resolver.idFromValue(new Cat());
		assertThat(id, is(equalTo(CAT_ID)));
	}

	@Test
	public void testIdFromPersonArrayValue() {
		Person[] people = new Person[] { new Person(), new Person() };
		String id = resolver.idFromValue(people);
		assertThat(id, is(equalTo(PERSON_ARRAY_ID)));
	}

	@Test
	public void testIdFromPersonListValue() {
		List<Person> people = Arrays.asList(new Person[] { new Person(), new Person() });
		String id = resolver.idFromValue(people);
		assertThat(id, is(equalTo(ARRAY_LIST_ID)));
	}

	@Test
	public void testObjectFromId() {
		SimpleType objectType = SimpleType.construct(Object.class);
		resolver = new BundleAndClassNameIdResolver(objectType, TypeFactory.defaultInstance(), bundleProvider);
		JavaType resolvedType = resolver.typeFromId(OBJECT_ID);
		assertThat(resolvedType, is(equalTo(objectType)));
	}

	@Test
	public void testPersonFromId() {
		SimpleType personType = SimpleType.construct(Person.class);
		resolver = new BundleAndClassNameIdResolver(personType, TypeFactory.defaultInstance(), bundleProvider);
		JavaType resolvedType = resolver.typeFromId(PERSON_ID);
		assertThat(resolvedType, is(equalTo(personType)));
	}

	@Test
	public void testBirdFromId() {
		// Important test - provides supertype (Animal) as base type but expects the subtype (Bird) to be returned
		SimpleType animalType = SimpleType.construct(Animal.class);
		resolver = new BundleAndClassNameIdResolver(animalType, TypeFactory.defaultInstance(), bundleProvider);
		JavaType resolvedType = resolver.typeFromId(BIRD_ID);
		SimpleType birdType = SimpleType.construct(Bird.class);
		assertThat(resolvedType, is(equalTo(birdType)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCoreClassNotFound() {
		resolver.typeFromId(NONEXISTENT_CORE_CLASS_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBundleClassNotFound() {
		resolver.typeFromId(NONEXISTENT_BUNDLE_ID);
	}

	@Test
	public void testClassInWrongBundleVersion() {
		// Need to give a baseType for this one to avoid NPE
		SimpleType objectType = SimpleType.construct(Object.class);
		resolver = new BundleAndClassNameIdResolver(objectType, TypeFactory.defaultInstance(), bundleProvider);

		// Ideally this should fail with IllegalArgumentException to simulate the case where the class cannot be found
		// because it is in the wrong bundle. But the Bird class is available to the standard classloader (unlike in
		// the OSGi case) so this actually works, but a ClassNotFoundException is logged at WARN level.
		// This test is kept simply to document behaviour.
		resolver.typeFromId(NONEXISTENT_BIRD_ID);
	}

	@Test
	public void testCachePreventsMultipleCallsToBundleProvider() {
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
