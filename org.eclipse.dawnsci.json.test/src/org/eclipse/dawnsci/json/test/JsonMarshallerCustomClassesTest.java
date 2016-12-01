/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test;

import static org.eclipse.dawnsci.json.test.JsonUtils.assertJsonEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.classregistry.TestObjectClassRegistry;
import org.eclipse.dawnsci.json.test.testobject.Animal;
import org.eclipse.dawnsci.json.test.testobject.Bird;
import org.eclipse.dawnsci.json.test.testobject.Cat;
import org.eclipse.dawnsci.json.test.testobject.CatWrapper;
import org.eclipse.dawnsci.json.test.testobject.ContainerBean;
import org.eclipse.dawnsci.json.test.testobject.Person;
import org.eclipse.dawnsci.json.test.testobject.ProjectBean;
import org.eclipse.dawnsci.json.test.testobject.TestStatusBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeNonRegisteredImpl;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Unit tests for the Jackson JSON marshaller which check the behaviour in a simulated OSGi environment where classes
 * need to be loaded from the correct bundles.
 * <p>
 * If the marshaller settings are changed, the new JSON string produced in each test can be written to std out by
 * uncommenting the relevant line in tearDown(), allowing it to be copied into the Java code to update the tests.
 *
 * @author Colin Palmer
 * @author Martin Gaughran
 *
 */
public class JsonMarshallerCustomClassesTest {

	private static final String JSON_FOR_JIM = "{\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n}";
	private static final String JSON_FOR_JOHN = "{\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"John\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  }\n}";
	private static final String JSON_FOR_JAMES = "{\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"James\" \n}";
	private static final String JSON_FOR_FELIX = "{\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}";
	private static final String JSON_FOR_ANIMAL_ARRAY = "[ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n} ]";
	private static final String JSON_FOR_OBJECT_ARRAY = "[ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n} ]";
	private static final String JSON_FOR_ANIMAL_LIST = "[ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n} ]";
	private static final String JSON_FOR_OBJECT_LIST = "[ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n} ]";
	private static final String JSON_FOR_OBJECT_SET = "[ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n}, {\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n} ]";
	private static final String JSON_FOR_OBJECT_MAP = "{\n  \"Polly\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  },\n  \"Felix\" : {\n    \"@type\" : \"jsontest.animal.cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  },\n  \"John\" : {\n    \"@type\" : \"jsontest.person\",\n    \"name\" : \"John\",\n    \"pet\" : {\n      \"@type\" : \"jsontest.animal.cat\",\n      \"name\" : \"Felix\",\n      \"whiskers\" : \"Luxuriant\"\n    }\n  },\n  \"Jim\" : {\n    \"@type\" : \"jsontest.person\",\n    \"name\" : \"Jim\",\n    \"pet\" : {\n      \"@type\" : \"jsontest.animal.bird\",\n      \"name\" : \"Polly\",\n      \"feathers\" : \"Green\"\n    }\n  }\n}";

	// Used to test marshalling without using the class registry-based id resolver.
	private static final String JSON_FOR_NESTED_STATIC_ATTRIBUTE_WITHOUT_TYPE_INFO = "{ \"cat\" : { \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\" } }";
	private static final String JSON_FOR_STATIC_OBJECT_WITHOUT_TYPE_INFO = "{ \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\" }";

	// An example of a bean used by Xia2 which could be sent by another process and must deserialize correctly in current version
	private static final String JSON_FOR_PROJECT_BEAN = "{\"status\":\"COMPLETE\",\"name\":\"X1_weak_M1S1_1 - X1_weak_M1S1_1\",\"message\":\"Xia2 run completed normally\",\"percentComplete\":100.0,\"userName\":\"awa25\",\"hostName\":\"cs04r-sc-vserv-45.diamond.ac.uk\",\"runDirectory\":\"/dls/i03/data/2016/cm14451-1/processed/tmp/2016-01-27/fake085224/MultiCrystal_1\",\"uniqueId\":\"1453910139320_94ed2a2b-997e-4dbc-ad6e-0c3c04bb2c82\",\"submissionTime\":1453910139340,\"properties\":null,\"projectName\":\"MultiCrystalRerun\",\"cystalName\":\"fake085224\",\"sweeps\":[{\"name\":\"X1_weak_M1S1_1\",\"sessionId\":\"55167\",\"dataCollectionId\":\"1007379\",\"imageDirectory\":\"/dls/i03/data/2016/cm14451-1/tmp/2016-01-27/fake085224/\",\"firstImageName\":\"X1_weak_M1S1_1_0001.cbf\",\"start\":1,\"end\":900,\"wavelength\":0.979493,\"xBeam\":212.51,\"yBeam\":219.98,\"resolution\":null}],\"wavelength\":\"NaN\",\"commandLineSwitches\":\"\",\"anomalous\":true,\"spaceGroup\":null,\"unitCell\":null,\"resolution\":null}";
	private static final String JSON_FOR_PROJECT_BEAN_WITH_TYPES = "{\n  \"@type\" : \"jsontest.projectbean\",\n  \"uniqueId\" : \"1453910139320_94ed2a2b-997e-4dbc-ad6e-0c3c04bb2c82\",\n  \"status\" : [ \"jsontest.teststatus\", \"COMPLETE\" ],\n  \"name\" : \"X1_weak_M1S1_1 - X1_weak_M1S1_1\",\n  \"message\" : \"Xia2 run completed normally\",\n  \"percentComplete\" : 100.0,\n  \"userName\" : \"awa25\",\n  \"hostName\" : \"cs04r-sc-vserv-45.diamond.ac.uk\",\n  \"runDirectory\" : \"/dls/i03/data/2016/cm14451-1/processed/tmp/2016-01-27/fake085224/MultiCrystal_1\",\n  \"submissionTime\" : 1453910139340,\n  \"projectName\" : \"MultiCrystalRerun\",\n  \"cystalName\" : \"fake085224\",\n  \"sweeps\" : [ {\n    \"@type\" : \"jsontest.sweepbean\",\n    \"name\" : \"X1_weak_M1S1_1\",\n    \"sessionId\" : \"55167\",\n    \"dataCollectionId\" : \"1007379\",\n    \"imageDirectory\" : \"/dls/i03/data/2016/cm14451-1/tmp/2016-01-27/fake085224/\",\n    \"firstImageName\" : \"X1_weak_M1S1_1_0001.cbf\",\n    \"start\" : 1,\n    \"end\" : 900,\n    \"wavelength\" : 0.979493,\n    \"xBeam\" : 212.51,\n    \"yBeam\" : 219.98\n  } ],\n  \"wavelength\" : \"NaN\",\n  \"commandLineSwitches\" : \"\",\n  \"anomalous\" : true\n}";

	// Json for 'contained' types (non-generic wrapper class
	private static final String JSON_FOR_CONTAINED_OBJECT_ARRAY = "{\n  \"@type\" : \"jsontest.containerbean\",\n  \"objArray\" : [ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n} ] \n}";
	private static final String JSON_FOR_CONTAINED_OBJECT_LIST = "{\n  \"@type\" : \"jsontest.containerbean\",\n  \"objList\" : [ {\n  \"@type\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@type\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@type\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n} ] \n}";
	private static final String JSON_FOR_CONTAINED_OBJECT_MAP = "{\n  \"@type\" : \"jsontest.containerbean\",\n  \"objMap\" : {\n  \"Polly\" : {\n    \"@type\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  },\n  \"Felix\" : {\n    \"@type\" : \"jsontest.animal.cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  },\n  \"John\" : {\n    \"@type\" : \"jsontest.person\",\n    \"name\" : \"John\",\n    \"pet\" : {\n      \"@type\" : \"jsontest.animal.cat\",\n      \"name\" : \"Felix\",\n      \"whiskers\" : \"Luxuriant\"\n    }\n  },\n  \"Jim\" : {\n    \"@type\" : \"jsontest.person\",\n    \"name\" : \"Jim\",\n    \"pet\" : {\n      \"@type\" : \"jsontest.animal.bird\",\n      \"name\" : \"Polly\",\n      \"feathers\" : \"Green\"\n    }\n  }\n} \n}";

	// Json for a range of error tests
	private static final String JSON_FOR_UNKNOWN_TOP_LEVEL_CLASS_ID = "{ \"@type\" : \"jsontest.doesnotexist\", \"string\" : \"Non-registered, top level class.\" }";
	private static final String JSON_FOR_NO_TOP_LEVEL_CLASS_ID = "{ \"string\" : \"Non-registered, top level class.\" }";
	private static final String JSON_FOR_UNKNOWN_NESTED_DYNAMIC_DEFINED_CLASS_ID = "{ \"@type\" : \"jsontest.person\", \"pet\" : { \"@type\" : \"jsontest.nonexistent\", \"whiskers\" : \"Luxuriant\", \"name\" : \"Tiddles\" } }";
	private static final String JSON_FOR_NESTED_DYNAMIC_DEFINED_CLASS_WITH_NO_ID = "{ \"@type\" : \"jsontest.person\", \"pet\" : { \"whiskers\" : \"Luxuriant\", \"name\" : \"Tiddles\" } }";
	private static final String JSON_FOR_UNKNOWN_NESTED_STATIC_DEFINED_CLASS_ID = "{ \"cat\" : { \"@type\" : \"jsontest.nonexistentcat\", \"whiskers\" : \"Luxuriant\", \"name\" : \"Tiddles\" } }";
	private static final String JSON_FOR_STATIC_DEFINED_CLASS_WITH_NO_ID = "{ \"cat\" : { \"whiskers\" : \"Luxuriant\", \"name\" : \"Tiddles\" } }";

	private IMarshallerService marshaller;
	private IMarshallerService marshallerWithNoRegistry;

	private String json;

	// Test objects
	private Bird polly;
	private Cat felix;
	private Person jim;
	private Person john;
	private Person james;

	@Before
	public void setUp() throws Exception {
		createTestObjects();
		MockitoAnnotations.initMocks(this);

		marshaller = new MarshallerService(new TestObjectClassRegistry());
		marshallerWithNoRegistry = new MarshallerService();

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

		james = new Person();
		james.setName("James");
		james.setPet(null);


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
	public void testDeserialisationOfJim() throws Exception {
		Person deserializedJim = marshaller.unmarshal(JSON_FOR_JIM, Person.class);
		assertEquals("Jim", deserializedJim.getName());
		assertThat(deserializedJim.getPet(), is(instanceOf(Bird.class)));
		Bird deserializedPolly = (Bird) deserializedJim.getPet();
		assertEquals("Polly", deserializedPolly.getName());
		assertEquals("Green", deserializedPolly.getFeathers());
	}

	@Test
	public void testDeserialisationOfJimAsNull() throws Exception {
		Person deserializedJim = marshaller.unmarshal(JSON_FOR_JIM, null);
		assertEquals("Jim", deserializedJim.getName());
		assertThat(deserializedJim.getPet(), is(instanceOf(Bird.class)));
		Bird deserializedPolly = (Bird) deserializedJim.getPet();
		assertEquals("Polly", deserializedPolly.getName());
		assertEquals("Green", deserializedPolly.getFeathers());
	}

	@Test
	public void testSerializationOfJamesWithNullPet() throws Exception {
		json = marshaller.marshal(james);
		assertJsonEquals(JSON_FOR_JAMES, json);
	}

	@Test
	public void testDeserialisationOfJamesWithNullPet() throws Exception {
		Person deserializedJames = marshaller.unmarshal(JSON_FOR_JAMES, Person.class);
		assertEquals("James", deserializedJames.getName());
		assertThat(deserializedJames.getPet(), is(nullValue()));
	}

	@Test
	public void testSerializationOfJohn() throws Exception {
		json = marshaller.marshal(john);
		assertJsonEquals(JSON_FOR_JOHN, json);
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

	@Test
	public void testDeserialisationOfJohnAsNull() throws Exception {
		Person deserializedJohn = marshaller.unmarshal(JSON_FOR_JOHN, null);
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
	public void testDeserialisationOfFelixAsNull() throws Exception {
		Animal deserializedFelix = marshaller.unmarshal(JSON_FOR_FELIX, null);
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
	public void testArrayDeserializationAnimalAsobjectArr() throws Exception {
		Object[] objectArr = marshaller.unmarshal(JSON_FOR_ANIMAL_ARRAY, Object[].class);
		assertThat(objectArr[0], is(instanceOf(Cat.class)));
		assertThat(objectArr[1], is(instanceOf(Bird.class)));
		assertThat(objectArr[2], is(instanceOf(Cat.class)));
		assertThat(((Cat) objectArr[0]).getWhiskers(), is(equalTo("Luxuriant")));
	}

	@Test
	public void testObjectArraySerialization() throws Exception {
		Object[] objectArr = new Object[] { felix, polly, jim };
		json = marshaller.marshal(objectArr);
		assertJsonEquals(JSON_FOR_OBJECT_ARRAY, json);
	}

	@Test
	public void testObjectArrayDeserialization() throws Exception {
		Object[] objectArr = marshaller.unmarshal(JSON_FOR_OBJECT_ARRAY, Object[].class);
		assertThat(objectArr[0], is(instanceOf(Cat.class)));
		assertThat(objectArr[1], is(instanceOf(Bird.class)));
		assertThat(objectArr[2], is(instanceOf(Person.class)));
		assertThat(((Person) objectArr[2]).getName(), is("Jim"));
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
	public void testObjectListSerialization() throws Exception {
		List<Object> objectList = Arrays.asList(felix, polly, jim);
		json = marshaller.marshal(objectList);
		assertJsonEquals(JSON_FOR_OBJECT_LIST, json);
	}

	@Test
	public void testObjectListDeserialization() throws Exception {
		@SuppressWarnings({ "unchecked" })
		List<Object> objectList = marshaller.unmarshal(JSON_FOR_OBJECT_LIST, List.class);
		assertThat(objectList.get(0), is(instanceOf(Cat.class)));
		assertThat(objectList.get(1), is(instanceOf(Bird.class)));
		assertThat(objectList.get(2), is(instanceOf(Person.class)));
		assertThat(((Person) objectList.get(2)).getName(), is("Jim"));
	}

	@Test
	public void testSetSerialization() throws Exception { // also relies on deserialization
		Set<Object> originalSet = new HashSet<>(Arrays.asList(felix, jim, jim));
		json = marshaller.marshal(originalSet);
		@SuppressWarnings("unchecked")
		Set<Object> deserializedSet = marshaller.unmarshal(json, Set.class);
		assertEquals(deserializedSet, originalSet);
	}

	@Test
	public void testSetDeserialization() throws Exception {
		@SuppressWarnings({ "unchecked" })
		Set<Object> objectSet = marshaller.unmarshal(JSON_FOR_OBJECT_SET, Set.class);
		assertThat(objectSet.size(), is(equalTo(2)));
	}

	@Test
	public void testMapSerialization() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(jim.getName(), jim);
		map.put(john.getName(), john);
		map.put(felix.getName(), felix);
		map.put(polly.getName(), polly);
		json = marshaller.marshal(map);
		assertJsonEquals(JSON_FOR_OBJECT_MAP, json);
	}

	@Test
	public void testMapDeserialization() throws Exception {
		Object object = marshaller.unmarshal(JSON_FOR_OBJECT_MAP, Map.class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) object;
		assertThat(map.size(), is(equalTo(4)));
		assertThat(map.get(jim.getName()), is(equalTo(jim)));
		assertThat(map.get(john.getName()), is(equalTo(john)));
		assertThat(map.get(felix.getName()), is(equalTo(felix)));
		assertThat(map.get(polly.getName()), is(equalTo(polly)));
	}

	@Test
	public void testGivenStaticallyDefinedObjectThenMarshallingWorksWithoutTypeInfo() throws Exception {
		json = marshaller.marshal(felix, false);
		assertJsonEquals(json, JSON_FOR_STATIC_OBJECT_WITHOUT_TYPE_INFO);
	}

	@Test
	public void testGivenStaticallyDefinedNestedAttributeThenMarshallingWorksWithoutTypeInfo() throws Exception {
		CatWrapper catWrapper = new CatWrapper();
		catWrapper.setCat(felix);

		json = marshaller.marshal(catWrapper, false);
		assertJsonEquals(json, JSON_FOR_NESTED_STATIC_ATTRIBUTE_WITHOUT_TYPE_INFO);
	}

	@Test
	public void testProjectBeanSerialization() throws Exception {
		ProjectBean bean = marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, ProjectBean.class);
		json = marshaller.marshal(bean);
		// New json is different from original because it has type info
		assertJsonEquals(JSON_FOR_PROJECT_BEAN_WITH_TYPES, json);
	}

	@Test
	public void testProjectBeanSerializationWithoutTypeInfo() throws Exception {
		ProjectBean bean = marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, ProjectBean.class);
		json = marshaller.marshal(bean, false);
		// New json is different from original because it does not include nulls.
		// Therefore, test unmarshalled beans instead.
		ProjectBean returnBean = marshaller.unmarshal(json, ProjectBean.class);
		assertEquals(returnBean, bean);
	}

	@Test
	public void testProjectBeanDeserialization() throws Exception {
		marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, ProjectBean.class);
	}

	@Test
	public void testProjectBeanDeserializationWithTypesAsNull() throws Exception {
		marshaller.unmarshal(JSON_FOR_PROJECT_BEAN_WITH_TYPES, null);
	}

	@Test(expected = JsonMappingException.class)
	public void testProjectBeanDeserializationWithWrongType() throws Exception {
		marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, TestStatusBean.class);
	}

	// The tests below work with a non-generic wrapper object. This is often not possible with generic
	// wrappers (as above) due to type erasure.

	@Test
	public void testContainedArraySerialization() throws Exception {
		ContainerBean container = new ContainerBean();
		Object[] objectArr = new Object[] { felix, polly, jim };
		container.setObjArray(objectArr);
		json = marshaller.marshal(container);
		assertJsonEquals(JSON_FOR_CONTAINED_OBJECT_ARRAY, json);
	}

	@Test
	public void testContainedArrayDeserialization() throws Exception {
		ContainerBean container = marshaller.unmarshal(JSON_FOR_CONTAINED_OBJECT_ARRAY, null);
		Object[] objectArr = container.getObjArray();
		assertThat(objectArr[0], is(instanceOf(Cat.class)));
		assertThat(objectArr[1], is(instanceOf(Bird.class)));
		assertThat(objectArr[2], is(instanceOf(Person.class)));
		assertThat(((Person) objectArr[2]).getName(), is("Jim"));
	}

	@Test
	public void testContainedListSerialization() throws Exception {
		ContainerBean container = new ContainerBean();
		List<Object> objectList = Arrays.asList(felix, polly, jim);
		container.setObjList(objectList);
		json = marshaller.marshal(container);
		assertJsonEquals(JSON_FOR_CONTAINED_OBJECT_LIST, json);
	}

	@Test
	public void testContainedListDeserialization() throws Exception {
		ContainerBean container = marshaller.unmarshal(JSON_FOR_CONTAINED_OBJECT_LIST, null);
		List<Object> objectList = (List<Object>) container.getObjList();
		assertThat(objectList.get(0), is(instanceOf(Cat.class)));
		assertThat(objectList.get(1), is(instanceOf(Bird.class)));
		assertThat(objectList.get(2), is(instanceOf(Person.class)));
		assertThat(((Person) objectList.get(2)).getName(), is("Jim"));
	}

	@Test
	public void testContainedMapSerialization() throws Exception {
		ContainerBean container = new ContainerBean();
		Map<String, Object> map = new HashMap<>();
		map.put(jim.getName(), jim);
		map.put(john.getName(), john);
		map.put(felix.getName(), felix);
		map.put(polly.getName(), polly);
		container.setObjMap(map);
		json = marshaller.marshal(container);
		assertJsonEquals(JSON_FOR_CONTAINED_OBJECT_MAP, json);
	}

	@Test
	public void testContainedMapDeserialization() throws Exception {
		ContainerBean container = marshaller.unmarshal(JSON_FOR_CONTAINED_OBJECT_MAP, null);
		Map<String, Object> objectMap = container.getObjMap();
		assertThat(objectMap.size(), is(equalTo(4)));
		assertThat(objectMap.get(jim.getName()), is(equalTo(jim)));
		assertThat(objectMap.get(john.getName()), is(equalTo(john)));
		assertThat(objectMap.get(felix.getName()), is(equalTo(felix)));
		assertThat(objectMap.get(polly.getName()), is(equalTo(polly)));
	}


	@Rule public ExpectedException exception = ExpectedException.none();

	@SuppressWarnings("unused")
	@Test
	public void testGivenClassIdNotRecognisedWhenUnmarshallingWithNullThenExceptionRaisedWithCorrectMessage() throws Exception {
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage(CoreMatchers.containsString("jsontest.doesnotexist"));

	    Object object;
	    object = marshaller.unmarshal(JSON_FOR_UNKNOWN_TOP_LEVEL_CLASS_ID, null);
	}

	@SuppressWarnings("unused")
	@Test
	public void testGivenClassIdNotPresentWhenUnmarshallingWithNullThenExceptionRaised() throws Exception {
		exception.expect(JsonMappingException.class);
	    exception.expectMessage(CoreMatchers.containsString("type id"));

	    Object object;
	    object = marshaller.unmarshal(JSON_FOR_NO_TOP_LEVEL_CLASS_ID, null);
	}

	@SuppressWarnings("unused")
	@Test
	public void testGivenNestedClassIdNotRecognisedWhenUnmarshallingDynamicDefinedObjectThenExceptionRaisedWithCorrectMessage() throws Exception {
		exception.expect(JsonMappingException.class);
	    exception.expectMessage(CoreMatchers.containsString("jsontest.nonexistent"));

	    Object object;
	    object = marshaller.unmarshal(JSON_FOR_UNKNOWN_NESTED_DYNAMIC_DEFINED_CLASS_ID, null);
	}

	@SuppressWarnings("unused")
	@Test
	public void testGivenNestedClassIdNotPresentWhenUnmarshallingDynamicDefinedObjectThenExceptionRaisedWithCorrectMessage() throws Exception {
		exception.expect(JsonMappingException.class);
	    exception.expectMessage(CoreMatchers.containsString("type id"));

	    Object object;
	    object = marshaller.unmarshal(JSON_FOR_NESTED_DYNAMIC_DEFINED_CLASS_WITH_NO_ID, null);
	}

	@Test
	public void testGivenClassIdNotRecognisedWhenUnmarshallingDynamicDefinedObjectThenMarshalSuccessful() throws Exception {
		TestTypeNonRegisteredImpl object;
		object = marshaller.unmarshal(JSON_FOR_UNKNOWN_TOP_LEVEL_CLASS_ID, TestTypeNonRegisteredImpl.class);
		assertEquals(object.getString(), "Non-registered, top level class.");
	}

	@Test
	public void testGivenNestedClassIdNotRecognisedWhenUnmarshallingStaticDefinedObjectThenMarshalSuccessful() throws Exception {
		CatWrapper catWrapper;
		catWrapper = marshallerWithNoRegistry.unmarshal(JSON_FOR_UNKNOWN_NESTED_STATIC_DEFINED_CLASS_ID, CatWrapper.class);
		assertEquals(catWrapper.getCat().getName(), "Tiddles");
		assertEquals(catWrapper.getCat().getWhiskers(), "Luxuriant");
	}

	@Test
	public void testGivenNestedClassIdNotPresentWhenUnmarshallingStaticDefinedObjectThenMarshalSuccessful() throws Exception {
		CatWrapper catWrapper;
		catWrapper = marshallerWithNoRegistry.unmarshal(JSON_FOR_STATIC_DEFINED_CLASS_WITH_NO_ID, CatWrapper.class);
		assertEquals(catWrapper.getCat().getName(), "Tiddles");
		assertEquals(catWrapper.getCat().getWhiskers(), "Luxuriant");
	}

	@Test
	public void testGivenClassNotRecognisedWhenMarshallingUnknownObjectThenExceptionRaisedWithCorrectMessage() throws Exception {
		exception.expect(JsonMappingException.class);
	    exception.expectMessage(CoreMatchers.containsString(Bird.class.toString()));

		json = marshallerWithNoRegistry.marshal(jim);
	}

}