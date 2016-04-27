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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.testobject.ProjectBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Unit tests for the Jackson JSON marshaller which check (de)serialization of a range of types, but without the
 * complication of checking classes are loaded from the correct OSGi bundles.
 * <p>
 * If the marshaller settings are changed, the new JSON string produced in each test can be written to std out by
 * uncommenting the relevant line in tearDown(), allowing it to be copied into the Java code to update the tests.
 *
 * @author Colin Palmer
 *
 */
public class JsonMarshallerNonOSGiTest {

	private static final String TEST_STRING = "Hello world!";
	private static final String JSON_FOR_TEST_STRING = "\"Hello world!\"";
	private static final int TEST_INT = -56;
	private static final String JSON_FOR_TEST_INT = "-56";
	private static final long TEST_LONG = 12345678900L;
	private static final String JSON_FOR_TEST_LONG = "12345678900";

	// An example of a bean used by Xia2 which could be sent by another process and must deserialize correctly in current version
	private static final String JSON_FOR_PROJECT_BEAN = "{\"status\":\"COMPLETE\",\"name\":\"X1_weak_M1S1_1 - X1_weak_M1S1_1\",\"message\":\"Xia2 run completed normally\",\"percentComplete\":100.0,\"userName\":\"awa25\",\"hostName\":\"cs04r-sc-vserv-45.diamond.ac.uk\",\"runDirectory\":\"/dls/i03/data/2016/cm14451-1/processed/tmp/2016-01-27/fake085224/MultiCrystal_1\",\"uniqueId\":\"1453910139320_94ed2a2b-997e-4dbc-ad6e-0c3c04bb2c82\",\"submissionTime\":1453910139340,\"properties\":null,\"projectName\":\"MultiCrystalRerun\",\"cystalName\":\"fake085224\",\"sweeps\":[{\"name\":\"X1_weak_M1S1_1\",\"sessionId\":\"55167\",\"dataCollectionId\":\"1007379\",\"imageDirectory\":\"/dls/i03/data/2016/cm14451-1/tmp/2016-01-27/fake085224/\",\"firstImageName\":\"X1_weak_M1S1_1_0001.cbf\",\"start\":1,\"end\":900,\"wavelength\":0.979493,\"xBeam\":212.51,\"yBeam\":219.98,\"resolution\":null}],\"wavelength\":\"NaN\",\"commandLineSwitches\":\"\",\"anomalous\":true,\"spaceGroup\":null,\"unitCell\":null,\"resolution\":null}";

	// Example of JSON produced for an ROI
	private static final String JSON_FOR_RECTANGULAR_ROI = "{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI\",\n  \"lengths\" : [ 8.0, 6.1 ],\n  \"angle\" : 0.0,\n  \"point\" : [ -3.5, 4.0 ]\n}";

	private static final String JSON_FOR_WRAPPED_RECTANGULAR_ROI = "{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.ROIWrapper\",\n  \"object\" : {\n    \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI\",\n    \"lengths\" : [ 8.0, 6.1 ],\n    \"angle\" : 0.0,\n    \"point\" : [ -3.5, 4.0 ]\n  }\n}";
	private static final String JSON_FOR_GENERIC_WRAPPED_RECTANGULAR_ROI = "{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.ObjectWrapper\",\n  \"object\" : {\n    \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI\",\n    \"lengths\" : [ 8.0, 6.1 ],\n    \"angle\" : 0.0,\n    \"point\" : [ -3.5, 4.0 ]\n  }\n}";
	private static final String JSON_FOR_WRAPPED_RECTANGULAR_ROI_LIST = "{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.ObjectWrapper\",\n  \"object\" : [ \"bundle=&version=&class=java.util.ArrayList\", [ {\n    \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI\",\n    \"lengths\" : [ 8.0, 6.1 ],\n    \"angle\" : 0.0,\n    \"point\" : [ -3.5, 4.0 ]\n  } ] ]\n}";

	private static final String[] STRING_ARRAY = { "a", "b", "c" };
	private static final String JSON_FOR_STRING_ARRAY = "[ \"a\", \"b\", \"c\" ]";
	private static final String JSON_FOR_WRAPPED_STRING_ARRAY = "{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.ObjectWrapper\",\n  \"object\" : [ \"bundle=&version=&class=[Ljava.lang.String;\", [ \"a\", \"b\", \"c\" ] ]\n}";

	private IMarshallerService marshaller;
	private String json;

	@Before
	public void setUp() throws Exception {
		marshaller = new MarshallerService();
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
	public void testSimpleStringArrayDeserialization() throws Exception {
		String[] actual = marshaller.unmarshal("[ \"a\", \"b\", \"c\" ]", String[].class);
		assertArrayEquals(actual, STRING_ARRAY);
	}

	@Test
	public void testStringArrayFieldSerialization() throws Exception {
		ObjectWrapper<String[]> arrayWrapper = new ObjectWrapper<>(STRING_ARRAY);
		json = marshaller.marshal(arrayWrapper);
		assertJsonEquals(JSON_FOR_WRAPPED_STRING_ARRAY, json);
	}

	@Test
	public void testStringArrayFieldDeserialization() throws Exception {
		ObjectWrapper<String[]> expected = new ObjectWrapper<>(STRING_ARRAY);
		ObjectWrapper<?> actual = marshaller.unmarshal(JSON_FOR_WRAPPED_STRING_ARRAY, ObjectWrapper.class);
		assertArrayEquals(expected.getObject(), (Object[]) actual.getObject());
	}

	@Test
	public void testObjectArraySerialization() throws Exception {
		json = marshaller.marshal(new Object[] { "a", "b", 5 });
		assertJsonEquals("[ \"bundle=&version=&class=[Ljava.lang.Object;\", [ \"a\", \"b\", 5 ] ]", json);
	}

	@Test
	public void testObjectArrayDeserialization() throws Exception {
		Object[] actual = marshaller.unmarshal("[ \"a\", \"b\", 5 ]", Object[].class);
		assertArrayEquals(actual, new Object[] { "a", "b", 5 });
	}

	@Test
	public void testSimpleMapDeserialization() throws Exception {
		Map<String, Object> expected = new HashMap<>();
		expected.put("String key", "String value");
		expected.put("Int key", 5);
		Map<?,?> actual = marshaller.unmarshal("{ \"String key\" : \"String value\", \"Int key\" : 5 }", Map.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testProjectBeanDeserialization() throws Exception {
		marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, ProjectBean.class);
	}

	@Test(expected = JsonMappingException.class)
	public void testProjectBeanDeserializationWithWrongType() throws Exception {
		marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, TestStatusBean.class);
	}

	@Test
	public void testProjectBeanSerialization() throws Exception {
		ProjectBean bean = marshaller.unmarshal(JSON_FOR_PROJECT_BEAN, ProjectBean.class);
		json = marshaller.marshal(bean);
		// New json is different from original because it has type info
		assertJsonEquals("{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.testobject.ProjectBean\",\n  \"uniqueId\" : \"1453910139320_94ed2a2b-997e-4dbc-ad6e-0c3c04bb2c82\",\n  \"status\" : [ \"bundle=&version=&class=org.eclipse.dawnsci.json.test.TestStatus\", \"COMPLETE\" ],\n  \"name\" : \"X1_weak_M1S1_1 - X1_weak_M1S1_1\",\n  \"message\" : \"Xia2 run completed normally\",\n  \"percentComplete\" : 100.0,\n  \"userName\" : \"awa25\",\n  \"hostName\" : \"cs04r-sc-vserv-45.diamond.ac.uk\",\n  \"runDirectory\" : \"/dls/i03/data/2016/cm14451-1/processed/tmp/2016-01-27/fake085224/MultiCrystal_1\",\n  \"submissionTime\" : 1453910139340,\n  \"projectName\" : \"MultiCrystalRerun\",\n  \"cystalName\" : \"fake085224\",\n  \"sweeps\" : [ \"bundle=&version=&class=java.util.ArrayList\", [ {\n    \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.testobject.SweepBean\",\n    \"name\" : \"X1_weak_M1S1_1\",\n    \"sessionId\" : \"55167\",\n    \"dataCollectionId\" : \"1007379\",\n    \"imageDirectory\" : \"/dls/i03/data/2016/cm14451-1/tmp/2016-01-27/fake085224/\",\n    \"firstImageName\" : \"X1_weak_M1S1_1_0001.cbf\",\n    \"start\" : 1,\n    \"end\" : 900,\n    \"wavelength\" : 0.979493,\n    \"xBeam\" : 212.51,\n    \"yBeam\" : 219.98\n  } ] ],\n  \"wavelength\" : \"NaN\",\n  \"commandLineSwitches\" : \"\",\n  \"anomalous\" : true\n}", json);
	}

	@Test
	public void testROISerialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		json = marshaller.marshal(roi);
		assertJsonEquals(JSON_FOR_RECTANGULAR_ROI, json);
	}

	@Test
	public void testROIDeserialization() throws Exception {
		IROI actual = marshaller.unmarshal(JSON_FOR_RECTANGULAR_ROI, IROI.class);
		IROI expected = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		assertEquals(expected, actual);
	}

	@Test
	public void testROIFieldSerialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ROIWrapper roiWrapper = new ROIWrapper(roi);
		json = marshaller.marshal(roiWrapper);
		assertJsonEquals(JSON_FOR_WRAPPED_RECTANGULAR_ROI, json);
	}

	@Test
	public void testROIFieldDeserialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ROIWrapper expected = new ROIWrapper(roi);
		ROIWrapper actual = marshaller.unmarshal(JSON_FOR_WRAPPED_RECTANGULAR_ROI, ROIWrapper.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testGenericROIFieldSerialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ObjectWrapper<IROI> roiWrapper = new ObjectWrapper<>(roi);
		json = marshaller.marshal(roiWrapper);
		assertJsonEquals(JSON_FOR_GENERIC_WRAPPED_RECTANGULAR_ROI, json);
	}

	@Test
	public void testGenericROIFieldDeserialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ObjectWrapper<IROI> expected = new ObjectWrapper<>(roi);
		ObjectWrapper<?> actual = marshaller.unmarshal(JSON_FOR_GENERIC_WRAPPED_RECTANGULAR_ROI, ObjectWrapper.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testROIListFieldSerialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ObjectWrapper<List<IROI>> roiWrapper = new ObjectWrapper<>(Arrays.asList(roi));
		json = marshaller.marshal(roiWrapper);
		assertJsonEquals(JSON_FOR_WRAPPED_RECTANGULAR_ROI_LIST, json);
	}

	@Test
	public void testROIListFieldDeserialization() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ObjectWrapper<List<IROI>> expected = new ObjectWrapper<>(Arrays.asList(roi));
		ObjectWrapper<?> actual = marshaller.unmarshal(JSON_FOR_WRAPPED_RECTANGULAR_ROI_LIST, ObjectWrapper.class);
		assertEquals(expected.getObject(), actual.getObject());
	}

	@Test
	public void testCircularROI() throws Exception {
		double xCentre = 15.25;
		double yCentre = -23.65;
		double radius = 5.64;

		// Create ROI
		CircularROI circularROI = new CircularROI(radius, xCentre, yCentre);
		Object expected = circularROI.copy();
		json = marshaller.marshal(circularROI);
		Object actual = marshaller.unmarshal(json, Object.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testLinearROI() throws Exception {
		double xStart = 10.23;
		double xStop = 34.25;
		double yStart = -12.3;
		double yStop = 11.4;

		// Create ROI
		LinearROI linearROI = new LinearROI(new double[] { xStart, yStart }, new double[] { xStop, yStop });
		Object expected = linearROI.copy();
		json = marshaller.marshal(linearROI);
		Object actual = marshaller.unmarshal(json, Object.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testPointROI() throws Exception {
		// Create ROI
		PointROI pointROI = new PointROI(10.23, -12.3);
		Object expected = pointROI.copy();
		json = marshaller.marshal(pointROI);
		Object actual = marshaller.unmarshal(json, Object.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testPolygonalROI() throws Exception {
		// Create ROI
		PolygonalROI polygonalROI = new PolygonalROI();
		polygonalROI.insertPoint(-4.5, 8.31);
		polygonalROI.insertPoint(-1.0, 4.5e3);
		polygonalROI.insertPoint(-1.0, 4.5e3);
		polygonalROI.insertPoint(1.325e-12, 100.0);
		polygonalROI.insertPoint(-4.6, 8.32);
		Object expected = polygonalROI.copy();
		json = marshaller.marshal(polygonalROI);
		Object actual = marshaller.unmarshal(json, Object.class);
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
		Object actual = marshaller.unmarshal("{\n  \"@bundle_and_class\" : \"bundle=&version=&class=org.eclipse.dawnsci.json.test.ObjectWrapper\",\n  \"object\" : \"Test string\"\n}", null);
		assertEquals(expected, actual);
	}

	@Test
	public void testWrappedStringDeserializationWithoutTypeInfo() throws Exception {
		ObjectWrapper<String> expected = new ObjectWrapper<>("Test string");
		Object actual = marshaller.unmarshal("{\n  \"object\" : \"Test string\"\n}", ObjectWrapper.class);
		assertEquals(expected, actual);
	}
}

class ObjectWrapper<T> {
	T object;
	public ObjectWrapper() {
	}
	public ObjectWrapper(T object) {
		this.object = object;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectWrapper<?> other = (ObjectWrapper<?>) obj;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		return true;
	}
}

class ROIWrapper extends ObjectWrapper<IROI> {
	public ROIWrapper() {
	}
	public ROIWrapper(IROI roi) {
		this.object = roi;
	}
}