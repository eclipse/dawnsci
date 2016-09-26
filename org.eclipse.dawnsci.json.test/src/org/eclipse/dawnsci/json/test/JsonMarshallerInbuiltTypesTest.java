/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.dawnsci.json.test;

import static org.eclipse.dawnsci.json.test.JsonUtils.assertJsonEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.classregistry.TestObjectClassRegistry;
import org.eclipse.dawnsci.json.test.testobject.ObjectWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Jackson JSON marshaller which check (de)serialization of a range of primitive types and
 * standard Java objects (including null objects).
 * <p>
 * If the marshaller settings are changed, the new JSON string produced in each test can be written to std out by
 * uncommenting the relevant line in tearDown(), allowing it to be copied into the Java code to update the tests.
 *
 * @author Colin Palmer
 * @author Martin Gaughran
 *
 */
public class JsonMarshallerInbuiltTypesTest {

	private static final String TEST_STRING = "Hello world!";
	private static final String JSON_FOR_TEST_STRING = "\"Hello world!\"";
	private static final int TEST_INT = -56;
	private static final String JSON_FOR_TEST_INT = "-56";
	private static final long TEST_LONG = 12345678900L;
	private static final String JSON_FOR_TEST_LONG = "12345678900";

	private static final String[] STRING_ARRAY = { "a", "b", "c" };
	private static final String JSON_FOR_STRING_ARRAY = "[ \"a\", \"b\", \"c\" ]";
	private static final String JSON_FOR_OBJECT_ARRAY = "[ \"a\", \"b\", 5 ]";
	private static final String JSON_FOR_STRING_OBJECT_MAP = "{ \"String key\" : \"String value\", \"Int key\" : 5 }";
	private static final String JSON_FOR_WRAPPED_STRING = "{\n  \"@class_id\" : \"jsontest.objectwrapper\",\n  \"object\" : \"Test string\"\n}";
	private static final String JSON_FOR_WRAPPED_STRING_WITHOUT_TYPES = "{\n  \"object\" : \"Test string\"\n}";
	private IMarshallerService marshaller;
	private String json;

	@Before
	public void setUp() throws Exception {
		marshaller = new MarshallerService(new TestObjectClassRegistry());
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
	public void testNullSerialization() throws Exception {
		// Not actually sure whether it's best to just serialize null (which Jackson allows perfectly happily) or throw
		// a NullPointerException. For now we'll just serialize it and confirm that behaviour with this test, but it
		// might be better to throw instead.
		json = marshaller.marshal(null);
		assertJsonEquals("null", json);
	}

	@Test
	public void testNullDeserialization() throws Exception {
		Object actual = marshaller.unmarshal("null", Object.class);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testIntSerialization() throws Exception {
		json = marshaller.marshal(TEST_INT);
		assertJsonEquals(JSON_FOR_TEST_INT, json);
	}

	@Test
	public void testIntDeserialization() throws Exception {
		Object result = marshaller.unmarshal(JSON_FOR_TEST_INT, Object.class);
		assertThat(result, is(equalTo(TEST_INT)));
	}

	@Test
	public void testIntDeserializationAsNull() throws Exception {
		Object result = marshaller.unmarshal(JSON_FOR_TEST_INT, null);
		assertThat(result, is(equalTo(TEST_INT)));
	}

	@Test
	public void testLongSerialization() throws Exception {
		json = marshaller.marshal(TEST_LONG);
		assertJsonEquals(JSON_FOR_TEST_LONG, json);
	}

	@Test
	public void testLongDeserialization() throws Exception {
		Object result = marshaller.unmarshal(JSON_FOR_TEST_LONG, Object.class);
		assertThat(result, is(equalTo(TEST_LONG)));
	}

	@Test
	public void testLongDeserializationAsNull() throws Exception {
		Object result = marshaller.unmarshal(JSON_FOR_TEST_LONG, null);
		assertThat(result, is(equalTo(TEST_LONG)));
	}

	@Test
	public void testStringSerialization() throws Exception {
		json = marshaller.marshal(TEST_STRING);
		assertJsonEquals(JSON_FOR_TEST_STRING, json);
	}

	@Test
	public void testStringDeserialization() throws Exception {
		Object result = marshaller.unmarshal(JSON_FOR_TEST_STRING, Object.class);
		assertEquals(TEST_STRING, result);
	}

	@Test
	public void testStringDeserializationAsNull() throws Exception {
		Object result = marshaller.unmarshal(JSON_FOR_TEST_STRING, null);
		assertEquals(TEST_STRING, result);
	}

	@Test
	public void testStringArraySerialization() throws Exception {
		json = marshaller.marshal(STRING_ARRAY);
		assertJsonEquals(JSON_FOR_STRING_ARRAY, json);
	}

	@Test
	public void testStringArrayDeserialization() throws Exception {
		String[] actual = marshaller.unmarshal(JSON_FOR_STRING_ARRAY, String[].class);
		assertArrayEquals(actual, STRING_ARRAY);
	}

	@Test
	public void testObjectArraySerialization() throws Exception {
		json = marshaller.marshal(new Object[] { "a", "b", 5 });
		assertJsonEquals(JSON_FOR_OBJECT_ARRAY, json);
	}

	@Test
	public void testObjectArrayDeserialization() throws Exception {
		Object[] actual = marshaller.unmarshal(JSON_FOR_OBJECT_ARRAY, Object[].class);
		assertArrayEquals(actual, new Object[] { "a", "b", 5 });
	}

	@Test
	public void testSimpleMapDeserialization() throws Exception {
		Map<String, Object> expected = new HashMap<>();
		expected.put("String key", "String value");
		expected.put("Int key", 5);
		Map<?,?> actual = marshaller.unmarshal(JSON_FOR_STRING_OBJECT_MAP, Map.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testWrappedStringSerialization() throws Exception {
		ObjectWrapper<String> wrapper = new ObjectWrapper<>("Test string");
		json = marshaller.marshal(wrapper);
	}

	@Test
	public void testWrappedStringDeserialization() throws Exception {
		ObjectWrapper<String> expected = new ObjectWrapper<>("Test string");
		Object actual = marshaller.unmarshal(JSON_FOR_WRAPPED_STRING, null);
		assertEquals(expected, actual);
	}

	@Test
	public void testWrappedStringDeserializationWithoutTypeInfo() throws Exception {
		ObjectWrapper<String> expected = new ObjectWrapper<>("Test string");
		Object actual = marshaller.unmarshal(JSON_FOR_WRAPPED_STRING_WITHOUT_TYPES, ObjectWrapper.class);
		assertEquals(expected, actual);
	}
}
