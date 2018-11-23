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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.classregistry.TestObjectClassRegistry;
import org.eclipse.dawnsci.json.test.marshaller.TestTypeNonRegisteredMarshaller;
import org.eclipse.dawnsci.json.test.marshaller.TestTypeRegisteredMarshaller;
import org.eclipse.dawnsci.json.test.testobject.TestTypeBean;
import org.eclipse.dawnsci.json.test.testobject.TestTypeNonRegisteredImpl;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.databind.JsonMappingException;

public class JsonMarshallerSerializerTest {

	private static final String JSON_WITH_NO_REG_WITH_SERIALIZER = "{\"@type\":\"jsontest.testtypebean\",\"ttnonReg\":\"TestTypeNonRegisteredSerializer used.\"}";
	private static final String JSON_WITH_REG_WITH_NO_SERIALIZER = "{\"@type\":\"jsontest.testtypebean\",\"ttreg\": {\"@type\" : \"jsontest.testtyperegistered\", \"string\" : \"Registered, marshaller not available.\"} }";
	private static final String JSON_WITH_REG_WITH_SERIALIZER = "{\"@type\":\"jsontest.testtypebean\",\"ttreg\": \"TestTypeRegisteredSerializer used.\" }";

	private IMarshallerService marshaller;
	private IMarshallerService marshallerWithMarshalledInterfaces;

	private String json;

	@Before
	public void setUp() throws Exception {

		marshaller = new MarshallerService(new TestObjectClassRegistry());
		marshallerWithMarshalledInterfaces = new MarshallerService(Arrays.asList(new TestObjectClassRegistry()), Arrays.asList(new TestTypeRegisteredMarshaller(), new TestTypeNonRegisteredMarshaller()));
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

	@Rule public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGivenNonRegisteredClassAndNoSerializerWithMarshalThenExceptionRaised() throws Exception {
		exception.expect(JsonMappingException.class);
	    exception.expectMessage(CoreMatchers.containsString(TestTypeNonRegisteredImpl.class.toString()));

		TestTypeBean bean = new TestTypeBean();

		bean.setTTNonReg(new TestTypeNonRegisteredImpl("Non-Registered, marshaller not available."));
		json = marshaller.marshal(bean);

	}

	@Test
	public void testGivenNonRegisteredClassAndSerializerWithMarshalThenUsesSerializer() throws Exception {
		TestTypeBean bean = new TestTypeBean();
		bean.setTTNonReg(new TestTypeNonRegisteredImpl("Non-Registered, marshaller available."));
		json =	marshallerWithMarshalledInterfaces.marshal(bean);
		assertJsonEquals(JSON_WITH_NO_REG_WITH_SERIALIZER, json);
	}

	@Test
	public void testGivenRegisteredClassAndNoSerializerWithMarshalThenUsesClassRegistry() throws Exception {
		TestTypeBean bean = new TestTypeBean();
		bean.setTTReg(new TestTypeRegisteredImpl("Registered, marshaller not available."));
		json =	marshaller.marshal(bean);
		assertJsonEquals(JSON_WITH_REG_WITH_NO_SERIALIZER, json);
	}

	@Test
	public void testGivenRegisteredClassAndSerializerWithMarshalThenUsesSerializer() throws Exception {
		TestTypeBean bean = new TestTypeBean();
		bean.setTTReg(new TestTypeRegisteredImpl("Registered, marshaller available."));
		json =	marshallerWithMarshalledInterfaces.marshal(bean);
		assertJsonEquals(JSON_WITH_REG_WITH_SERIALIZER, json);
	}

	@Test
	public void testGivenNonRegisteredClassAndSerializerWithUnmarshalThenUsesDeserializer() throws Exception {
		TestTypeBean returnBean;
		returnBean = marshallerWithMarshalledInterfaces.unmarshal(JSON_WITH_NO_REG_WITH_SERIALIZER, null);
		assertEquals("TestTypeNonRegisteredDeserializer used.", returnBean.getTTNonReg().getString());
	}

	@Test
	public void testGivenRegisteredClassAndNoSerializerWithUnmarshalThenUsesClassRegistry() throws Exception {
		TestTypeBean returnBean;
		returnBean = marshaller.unmarshal(JSON_WITH_REG_WITH_NO_SERIALIZER, null);
		assertEquals("Registered, marshaller not available.", returnBean.getTTReg().getString());
	}

	@Test
	public void testGivenRegisteredClassAndSerializerWithUnmarshalThenUsesDeserializer() throws Exception {
		TestTypeBean returnBean;
		returnBean = marshallerWithMarshalledInterfaces.unmarshal(JSON_WITH_REG_WITH_SERIALIZER, null);
		assertEquals("TestTypeRegisteredDeserializer used.", returnBean.getTTReg().getString());
	}

	@Test
	public void testTreeSerializationSimple() throws Exception {

		Map<String, Object> map = new HashMap<>(1);
		map.put("/", new HashMap<>(1));
		testMap(map);
	}

	@Test
	public void testTreeSerializationSimpleNonHashMap() throws Exception {

		Map<String, Object> map = new HashMap<>(1);
		map.put("/", new Hashtable<>(1));
		testMap(map);
	}


	/**
	 * We cannot do this one yet, we still test it but we expect the
	 * maps not to be equal.
	 * @throws Exception
	 */
	@Test(expected=AssertionError.class)
	public void testTreeSerializationComplex() throws Exception {

		final ObjectInputStream in = new ObjectInputStream(getClass().getResourceAsStream("treetomap.ser"));
		Map<String, Object> map = (Map<String, Object>)in.readObject();
		in.close();
		testMap(map);
	}


	private Map<String, Object> testMap(Map<String, Object> map) throws Exception {

		assertTrue(!map.isEmpty());

		String json = marshaller.marshal(map, false);
       	Map<String, Object> pam = marshaller.unmarshal(json, Map.class);

       	assertEquals(map, pam);
       	return pam;
	}

}
