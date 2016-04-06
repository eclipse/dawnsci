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

import static org.eclipse.dawnsci.json.test.JsonUtils.assertJsonEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.internal.Activator;
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
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * Unit tests for the Jackson JSON marshaller which check the behaviour in a simulated OSGi environment where classes
 * need to be loaded from the correct bundles.
 * <p>
 * If the marshaller settings are changed, the new JSON string produced in each test can be written to std out by
 * uncommenting the relevant line in tearDown(), allowing it to be copied into the Java code to update the tests.
 *
 * @author Colin Palmer
 *
 */
public class JsonMarshallerOSGiBundleTest {

	private static final String JSON_FOR_JIM = "{\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=1.2.0.test&class=org.eclipse.dawnsci.json.test.testobject.Person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n}";
	private static final String JSON_FOR_JOHN = "{\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=1.2.0.test&class=org.eclipse.dawnsci.json.test.testobject.Person\",\n  \"name\" : \"John\",\n  \"pet\" : {\n    \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  }\n}";
	private static final String JSON_FOR_FELIX = "{\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}";
	private static final String JSON_FOR_ANIMAL_ARRAY = "[ \"bundle=&version=&class=[Lorg.eclipse.dawnsci.json.test.testobject.Animal;\", [ {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n} ] ]";
	private static final String JSON_FOR_ANIMAL_LIST = "[ \"bundle=&version=&class=java.util.ArrayList\", [ {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n} ] ]";
	private static final String JSON_FOR_ANIMAL_SET = "[ \"bundle=&version=&class=java.util.HashSet\", [ {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n} ] ]";
	private static final String JSON_FOR_ANIMAL_MAP = "{\n  \"@bundle_and_class\" : \"bundle=&version=&class=java.util.HashMap\",\n  \"Polly\" : {\n    \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  },\n  \"Felix\" : {\n    \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  },\n  \"John\" : {\n    \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=1.2.0.test&class=org.eclipse.dawnsci.json.test.testobject.Person\",\n    \"name\" : \"John\",\n    \"pet\" : {\n      \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.other_example&version=0.0.0&class=org.eclipse.dawnsci.json.test.testobject.Cat\",\n      \"name\" : \"Felix\",\n      \"whiskers\" : \"Luxuriant\"\n    }\n  },\n  \"Jim\" : {\n    \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=1.2.0.test&class=org.eclipse.dawnsci.json.test.testobject.Person\",\n    \"name\" : \"Jim\",\n    \"pet\" : {\n      \"@bundle_and_class\" : \"bundle=uk.ac.diamond.daq.test.example&version=2.0.0&class=org.eclipse.dawnsci.json.test.testobject.Bird\",\n      \"name\" : \"Polly\",\n      \"feathers\" : \"Green\"\n    }\n  }\n}";

	private IMarshallerService marshaller;

	private String json;

	// Test objects
	private Bird polly;
	private Cat felix;
	private Person jim;
	private Person john;

	// Mocks
	@Mock private Bundle exampleBundleV1;
	@Mock private Bundle exampleBundleV2;
	@Mock private Bundle otherExampleBundle;
	@Mock private BundleContext bundleContext;

	@Before
	public void setUp() throws Exception {
		createTestObjects();
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

		when(bundleContext.getBundles()).thenReturn(new Bundle[] { exampleBundleV1, exampleBundleV2, otherExampleBundle });
		new Activator().start(bundleContext);

		TestBundleProvider bundleProvider = new TestBundleProvider();
		bundleProvider.registerBundleForClass(Person.class, exampleBundleV1);
		bundleProvider.registerBundleForClass(Animal.class, exampleBundleV1);
		bundleProvider.registerBundleForClass(Bird.class, exampleBundleV2);
		bundleProvider.registerBundleForClass(Cat.class, otherExampleBundle);

		marshaller = new MarshallerService(bundleProvider);
	}

	private void createTestObjects() {

		polly = new Bird();
		polly.setName("Polly");
		polly.setFeathers("Green");

		felix = new Cat();
		felix.setName("Felix");
		felix.setWhiskers("Luxuriant");

		jim = new Person();
		jim.setName("Jim");
		jim.setPet(polly);

		john = new Person();
		john.setName("John");
		john.setPet(felix);
	}

	@After
	public void tearDown() throws Exception {
		if (json != null) {
			// So we can see what's going on
//			System.out.println("JSON: " + json);

			// To make it easy to replace expected JSON values in the code when we're sure they're correct
			@SuppressWarnings("unused")
			String javaLiteralForJSONString = '"' + StringEscapeUtils.escapeJava(json) + '"';
//			System.out.println("Java literal:\n" + javaLiteralForJSONString);
		}
		json = null;
		marshaller = null;
	}

	@Test
	public void testSerializationOfJim() throws Exception {
		json = marshaller.marshal(jim);
		assertJsonEquals(JSON_FOR_JIM, json);
	}

	@Test
	public void testSerializationOfJohn() throws Exception {
		json = marshaller.marshal(john);
		assertJsonEquals(JSON_FOR_JOHN, json);
	}

	@Test
	public void testDeserialisationOfJim() throws Exception {
		Person deserializedJim = marshaller.unmarshal(JSON_FOR_JIM, Person.class);
		assertEquals("Jim", deserializedJim.getName());
		assertThat(deserializedJim.getPet(), is(instanceOf(Bird.class)));
		Bird deserializedPolly = (Bird) deserializedJim.getPet();
		assertEquals("Polly", deserializedPolly.getName());
		assertEquals("Green", deserializedPolly.getFeathers());
	}

	@Test
	public void testDeserialisationOfJohn() throws Exception {
		Person deserializedJohn = marshaller.unmarshal(JSON_FOR_JOHN, Person.class);
		assertEquals("John", deserializedJohn.getName());
		assertThat(deserializedJohn.getPet(), is(instanceOf(Cat.class)));
		Cat deserializedFelix = (Cat) deserializedJohn.getPet();
		assertEquals("Felix", deserializedFelix.getName());
		assertEquals("Luxuriant", deserializedFelix.getWhiskers());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeserialisationOfJohnAsAnimal() throws Exception {
		marshaller.unmarshal(JSON_FOR_JOHN, Animal.class);
	}

	@Test
	public void testSerialisationOfFelix() throws Exception {
		json = marshaller.marshal(felix);
		assertJsonEquals(JSON_FOR_FELIX, json);
	}

	@Test
	public void testDeserialisationOfFelixAsAnimal() throws Exception {
		Animal deserializedFelix = marshaller.unmarshal(JSON_FOR_FELIX, Animal.class);
		assertEquals("Felix", deserializedFelix.getName());
		assertThat(deserializedFelix, is(instanceOf(Cat.class)));
		assertEquals("Luxuriant", ((Cat) deserializedFelix).getWhiskers());
	}

	@Test
	public void testArraySerialization() throws Exception {
		Object[] animalArray = new Animal[] { felix, polly, felix };
		json = marshaller.marshal(animalArray);
		assertJsonEquals(JSON_FOR_ANIMAL_ARRAY, json);
	}

	@Test
	public void testArrayDeserializationAsAnimalArray() throws Exception {
		Animal[] animalArray = marshaller.unmarshal(JSON_FOR_ANIMAL_ARRAY, Animal[].class);
		assertThat(animalArray[0], is(instanceOf(Cat.class)));
		assertThat(animalArray[1], is(instanceOf(Bird.class)));
		assertThat(animalArray[2], is(instanceOf(Cat.class)));
		assertThat(animalArray[0].getName(), is("Felix"));
	}

	@Test
	public void testArrayDeserializationAsObjectArray() throws Exception {
		Object[] objectArray = marshaller.unmarshal(JSON_FOR_ANIMAL_ARRAY, Object[].class);
		assertThat(objectArray[0], is(instanceOf(Cat.class)));
		assertThat(objectArray[1], is(instanceOf(Bird.class)));
		assertThat(objectArray[2], is(instanceOf(Cat.class)));
		assertThat(((Cat) objectArray[0]).getWhiskers(), is(equalTo("Luxuriant")));
	}

	@Test
	public void testListSerialization() throws Exception {
		List<Animal> animalList = Arrays.asList(felix, polly, felix);
		json = marshaller.marshal(animalList);
		assertJsonEquals(JSON_FOR_ANIMAL_LIST, json);
	}

	@Test
	public void testListDeserialization() throws Exception {
		@SuppressWarnings({ "unchecked" })
		List<Animal> animalList = marshaller.unmarshal(JSON_FOR_ANIMAL_LIST, List.class);
		assertThat(animalList.get(0), is(instanceOf(Cat.class)));
		assertThat(animalList.get(1), is(instanceOf(Bird.class)));
		assertThat(animalList.get(2), is(instanceOf(Cat.class)));
		assertThat(animalList.get(0).getName(), is("Felix"));
	}

	@Test
	public void testSetDeserialization() throws Exception {
		@SuppressWarnings({ "unchecked" })
		Set<Animal> animalSet = marshaller.unmarshal(JSON_FOR_ANIMAL_SET, Set.class);
		assertThat(animalSet.size(), is(equalTo(2)));
	}

	@Test
	public void testSetSerialization() throws Exception { // also relies on deserialization
		Set<Animal> originalSet = new HashSet<>(Arrays.asList(felix, polly, felix));
		json = marshaller.marshal(originalSet);
		@SuppressWarnings("unchecked")
		Set<Animal> deserializedSet = marshaller.unmarshal(json, Set.class);
		assertEquals(deserializedSet, originalSet);
	}

	@Test
	public void testMapSerialization() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(jim.getName(), jim);
		map.put(john.getName(), john);
		map.put(felix.getName(), felix);
		map.put(polly.getName(), polly);
		json = marshaller.marshal(map);
		assertJsonEquals(JSON_FOR_ANIMAL_MAP, json);
	}

	@Test
	public void testMapDeserialization() throws Exception {
		Object object = marshaller.unmarshal(JSON_FOR_ANIMAL_MAP, Object.class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) object;
		assertThat(map.size(), is(equalTo(4)));
		assertThat(map.get(jim.getName()), is(equalTo(jim)));
		assertThat(map.get(john.getName()), is(equalTo(john)));
		assertThat(map.get(felix.getName()), is(equalTo(felix)));
		assertThat(map.get(polly.getName()), is(equalTo(polly)));
	}
}