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

package org.eclipse.dawnsci.json.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.classregister.TestObjectClassRegistry;
import org.eclipse.dawnsci.json.test.marshaller.TestTypeMixInMarshaller;
import org.eclipse.dawnsci.json.test.marshaller.TestTypeNonRegisteredMarshaller;
import org.eclipse.dawnsci.json.test.marshaller.TestTypeRegisteredMarshaller;
import org.eclipse.dawnsci.json.test.testobject.Animal;
import org.eclipse.dawnsci.json.test.testobject.Bird;
import org.eclipse.dawnsci.json.test.testobject.Cat;
import org.eclipse.dawnsci.json.test.testobject.Person;
import org.eclipse.dawnsci.json.test.testobject.TestTypeContainerBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeNonRegisteredImpl;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NewMarshallerServiceTest {

	private static final String JSON_FOR_JIM = "{\n  \"@class_id\" : \"jsontest.person\",\n  \"name\" : \"Jim\",\n  \"pet\" : {\n    \"@class_id\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  }\n}";
	private static final String JSON_FOR_JOHN = "{\n  \"@class_id\" : \"jsontest.person\",\n  \"name\" : \"John\",\n  \"pet\" : {\n    \"@class_id\" : \"jsontest.animal.cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  }\n}";
	private static final String JSON_FOR_FELIX = "{\n  \"@class_id\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}";
	private static final String JSON_FOR_ANIMAL_ARRAY = "[ {\n  \"@class_id\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@class_id\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@class_id\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n} ]";
	private static final String JSON_FOR_ANIMAL_LIST = "[ \"List\", [{\n  \"@class_id\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@class_id\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n}, {\n  \"@class_id\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n} ] ]";
	private static final String JSON_FOR_ANIMAL_SET = "[ {\n  \"@class_id\" : \"jsontest.animal.cat\",\n  \"name\" : \"Felix\",\n  \"whiskers\" : \"Luxuriant\"\n}, {\n  \"@class_id\" : \"jsontest.animal.bird\",\n  \"name\" : \"Polly\",\n  \"feathers\" : \"Green\"\n} ]";
	private static final String JSON_FOR_ANIMAL_MAP = "{\n \"Polly\" : {\n    \"@class_id\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  },\n  \"Felix\" : {\n    \"@class_id\" : \"jsontest.animal.cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  },\n  \"John\" : {\n    \"@class_id\" : \"jsontest.person\",\n    \"name\" : \"John\",\n    \"pet\" : {\n      \"@class_id\" : \"jsontest.animal.cat\",\n      \"name\" : \"Felix\",\n      \"whiskers\" : \"Luxuriant\"\n    }\n  },\n  \"Jim\" : {\n    \"@class_id\" : \"jsontest.person\",\n    \"name\" : \"Jim\",\n    \"pet\" : {\n      \"@class_id\" : \"jsontest.animal.bird\",\n      \"name\" : \"Polly\",\n      \"feathers\" : \"Green\"\n    }\n  }\n}";

	private IMarshallerService marshaller;
	private IMarshallerService marshallerWithMarshalledInterfaces;
	private IMarshallerService marshallerWithRegisteredMixIn;

	private String json;

	// Test objects
	private Bird polly;
	private Cat felix;
	private Person jim;
	private Person john;

	@Before
	public void setUp() throws Exception {
		createTestObjects();

		marshaller = new MarshallerService(new TestObjectClassRegistry());
		marshallerWithMarshalledInterfaces = new MarshallerService(Arrays.asList(new TestObjectClassRegistry()), Arrays.asList(new TestTypeRegisteredMarshaller(), new TestTypeNonRegisteredMarshaller()));
		marshallerWithRegisteredMixIn = new MarshallerService(Arrays.asList(new TestObjectClassRegistry()), Arrays.asList(new TestTypeMixInMarshaller()));
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
	public void testTypeProperties() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Object[] animalArray = new Animal[] { felix, polly, felix };
		ArrayList<Object> animalAL = new ArrayList<Object>();
		animalAL.add(felix);
		animalAL.add(polly);

		System.out.println("1");
		String json1 = marshaller.marshal(animalArray);
		System.out.println("2");
		String json2 = marshaller.marshal(polly);
		System.out.println("3");
		String json3 = marshaller.marshal(new ArrayList<Animal>());
		System.out.println("4");
		String json4 = marshaller.marshal(animalAL);
		@SuppressWarnings("unchecked")
		Object[] output = marshaller.unmarshal(json4, Object[].class);
//		System.out.println(((Animal[])output.toArray()[0])[0].equals(((Animal[])animalAL.toArray()[0])[0]));
		System.out.println();
		Object[] rAnimalArray = marshaller.unmarshal(json, null);
//		assertJsonEquals(JSON_FOR_ANIMAL_ARRAY, json);
	}

	@Test
	public void testArraySerialization() throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//
		Object[] animalArray = new Object[] {new Animal[] { felix, polly, felix }};
		ArrayList<Object> animalAL = new ArrayList<Object>();
		animalAL.add(new Animal[] { felix, polly, felix });

//		Object[] animalArray = new Animal[] { felix, polly, felix };
//		ArrayList<Object> anotherone = new ArrayList<Object>();
//		anotherone.add(felix);
//		anotherone.add(polly);


		String json4 = marshaller.marshal(animalAL);
		Collection<Object> output = marshaller.unmarshal(json4, null);
		System.out.println(((Animal[])output.toArray()[0])[0].equals(((Animal[])animalAL.toArray()[0])[0]));
	}

	@Test
	public void testMapSerialization() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
//
//		Object[] animalArray = new Object[] {new Animal[] { felix, polly, felix }};
		ArrayList<Object> animalAL = new ArrayList<Object>();
		animalAL.add(new Animal[] { felix, polly, felix });

		Map<String, Cat> bob = new HashMap<String, Cat>();
		bob.put("hithere", felix);
		bob.put("charcuterie", new Cat());

		Object[] animalArray = new Animal[] { felix, polly, felix };
		Bird[] birdArray = new Bird[] {polly, new Bird(), polly};
//		ArrayList<Object> anotherone = new ArrayList<Object>();
//		anotherone.add(felix);
//		anotherone.add(polly);

//		mapper.setSerializationInclusion(Include.NON_NULL);
//		mapper.enable(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS);

//		String json1 = marshaller.marshal(bob);
//		HashMap<String, Cat> output1 = marshaller.unmarshal(json1, null);


		//WORKS
//		String json2 = mapper.writeValueAsString(birdArray);
//		Bird[] output2 = (Bird[]) mapper.readValue(json2, Object.class);

		//WORKS
//		String json2 = marshaller.marshal(animalArray);
//		Object[] output2 = marshaller.unmarshal(json2, null);

		String json3 = marshaller.marshal(bob);
		@SuppressWarnings("unchecked")
//		HashMap<String, Cat> output3 = (HashMap<String, Cat>) mapper.readValue(json3, Object.class);
		Map<String, Cat> output3 = marshaller.unmarshal(json3, Map.class);

//		@SuppressWarnings("unchecked")
//		HashMap<String, Cat> output4 = (HashMap<String, Cat>) output3;

		System.out.println();
	}

	@Test
	public void testMapDeserialization() throws Exception {

		TestTypeContainerBean bean = new TestTypeContainerBean();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(john.getName(), polly);
		map.put(felix.getName(), felix);
		map.put(polly.getName(), polly);

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put(felix.getName(), felix);
		map2.put(polly.getName(), polly);

		bean.addToObjList(felix);
		bean.addToObjList(polly);
		bean.addToObjList(felix);
		bean.addToObjList(john);
		bean.addToObjList("hithere");

		// Doesn't work.
//		bean.addObj(map);
//		bean.addObj(map2);

		bean.addObjMapItem("kitty", felix);
		bean.addObjMapItem("birdy", polly);
		bean.addObjMapItem("notherkitty", felix);
		bean.addObjMapItem("perple", john);
		bean.addObjMapItem("str", "hithere");

		bean.setObjArrayAtIndex(1, felix);
		bean.setObjArrayAtIndex(2, john);

		bean.setObj(325345);

		String JSON_FOR_MAN_MAP = marshaller.marshal(bean);

		Object something = marshaller.unmarshal(JSON_FOR_MAN_MAP, null);
		Object endList = ((TestTypeContainerBean) something).getObjList();
		Object endMap = ((TestTypeContainerBean) something).getObjMap();
		Object endArr = ((TestTypeContainerBean) something).getObjArray();
		Object bob = ((TestTypeContainerBean)something).getObj();
		System.out.println();

	//		HashMap<String, Object> map = marshaller.unmarshal(JSON_FOR_ANIMAL_MAP, null);
	//		assertThat(map.size(), is(equalTo(4)));
	//		assertThat(map.get(jim.getName()), is(equalTo(jim)));
	//		assertThat(map.get(john.getName()), is(equalTo(john)));
	//		assertThat(map.get(felix.getName()), is(equalTo(felix)));
	//		assertThat(map.get(polly.getName()), is(equalTo(polly)));


//		HashMap<String, Animal> map = new HashMap<String, Animal>();
//
//		String JSON_FOR_ANIMAL_MAP = "{\n \"@class_id\" : \"HashMap\", \n \"Polly\" : {\n    \"@class_id\" : \"jsontest.animal.bird\",\n    \"name\" : \"Polly\",\n    \"feathers\" : \"Green\"\n  },\n  \"Felix\" : {\n    \"@class_id\" : \"jsontest.animal.cat\",\n    \"name\" : \"Felix\",\n    \"whiskers\" : \"Luxuriant\"\n  } \n}";
//		map = marshaller.unmarshal(JSON_FOR_ANIMAL_MAP, null);
//		Object bob = map.get("Polly");
//		assertThat(map.size(), is(equalTo(4)));
//		assertThat(map.get(jim.getName()), is(equalTo(jim)));
//		assertThat(map.get(john.getName()), is(equalTo(john)));
//		assertThat(map.get(felix.getName()), is(equalTo(felix)));
//		assertThat(map.get(polly.getName()), is(equalTo(polly)));
	}

	@Test
	public void testInterfaceAndSerialization() throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//
		Object[] animalArray = new Object[] {new Animal[] { felix, polly, felix }};
		ArrayList<Object> animalAL = new ArrayList<Object>();
		animalAL.add(new Animal[] { felix, polly, felix });

//		Object[] animalArray = new Animal[] { felix, polly, felix };
//		ArrayList<Object> anotherone = new ArrayList<Object>();
//		anotherone.add(felix);
//		anotherone.add(polly);


		String json4 = marshaller.marshal(animalAL);
		Collection<Object> output = marshaller.unmarshal(json4, null);
		System.out.println(((Animal[])output.toArray()[0])[0].equals(((Animal[])animalAL.toArray()[0])[0]));
	}

	@Test
	public void testNonRegisteredDeserialized() throws Exception {

		TestTypeContainerBean bean = new TestTypeContainerBean();
		TestTypeContainerBean returnBean;

		bean.setTTReg(new TestTypeRegisteredImpl("Registered, marshaller available"));
		String JsonRegMarshalAvail =
				marshallerWithMarshalledInterfaces.marshal(bean);
		returnBean = marshallerWithMarshalledInterfaces.unmarshal(JsonRegMarshalAvail, null);

		bean.setTTReg(new TestTypeRegisteredImpl("Registered, marshaller not available"));
		String JsonRegMarshalNotAvail =
				marshaller.marshal(bean);
		returnBean = marshaller.unmarshal(JsonRegMarshalNotAvail, null);

		bean = new TestTypeContainerBean();
		bean.setTTNonReg(new TestTypeNonRegisteredImpl("Non-Registered, marshaller available"));
		String JsonNonRegMarshalAvail =
				marshallerWithMarshalledInterfaces.marshal(bean);
		returnBean = marshallerWithMarshalledInterfaces.unmarshal(JsonNonRegMarshalAvail, null);

		bean.setTTNonReg(new TestTypeNonRegisteredImpl("Non-Registered, marshaller not available"));

//		try {
			String JsonNonRegMarshalNotAvail =
					marshaller.marshal(bean);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		System.out.println();
	}

	@Test
	public void testRegisteredMixInMarshaller() throws Exception {

	}

}
