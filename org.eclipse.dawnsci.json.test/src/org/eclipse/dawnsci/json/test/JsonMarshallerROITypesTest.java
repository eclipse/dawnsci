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
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RingROI;
import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.classregistry.TestObjectClassRegistry;
import org.eclipse.dawnsci.json.test.testobject.ObjectWrapper;
import org.eclipse.dawnsci.json.test.testobject.ROIWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Jackson JSON marshaller which check (de)serialization of a range of bare ROI types, plus
 * those wrapped by some kind of ObjectWrapper.
 * <p>
 * If the marshaller settings are changed, the new JSON string produced in each test can be written to std out by
 * uncommenting the relevant line in tearDown(), allowing it to be copied into the Java code to update the tests.
 *
 * @author Colin Palmer
 * @author Martin Gaughran
 *
 */
public class JsonMarshallerROITypesTest {

	// Example of JSON produced for an ROI
	private static final String JSON_FOR_RECTANGULAR_ROI = "{\n  \"@class_id\" : \"roi.rectangular\",\n  \"lengths\" : [ 8.0, 6.1 ],\n  \"angle\" : 0.0,\n  \"point\" : [ -3.5, 4.0 ]\n}";

	private static final String JSON_FOR_WRAPPED_RECTANGULAR_ROI = "{\n  \"@class_id\" : \"jsontest.roiwrapper\",\n  \"object\" : {\n    \"@class_id\" : \"roi.rectangular\",\n    \"lengths\" : [ 8.0, 6.1 ],\n    \"angle\" : 0.0,\n    \"point\" : [ -3.5, 4.0 ]\n  }\n}";
	private static final String JSON_FOR_GENERIC_WRAPPED_RECTANGULAR_ROI = "{\n  \"@class_id\" : \"jsontest.objectwrapper\",\n  \"object\" : {\n    \"@class_id\" : \"roi.rectangular\",\n    \"lengths\" : [ 8.0, 6.1 ],\n    \"angle\" : 0.0,\n    \"point\" : [ -3.5, 4.0 ]\n  }\n}";

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
	public void testROIDeserializationAsNull() throws Exception {
		IROI actual = marshaller.unmarshal(JSON_FOR_RECTANGULAR_ROI, null);
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
	public void testROIFieldDeserializationAsNull() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ROIWrapper expected = new ROIWrapper(roi);
		ROIWrapper actual = marshaller.unmarshal(JSON_FOR_WRAPPED_RECTANGULAR_ROI, null);
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
	public void testGenericROIFieldDeserializationAsNull() throws Exception {
		IROI roi = new RectangularROI(-3.5, 4.0, 8.0, 6.1, 0.0);
		ObjectWrapper<IROI> expected = new ObjectWrapper<>(roi);
		ObjectWrapper<?> actual = marshaller.unmarshal(JSON_FOR_GENERIC_WRAPPED_RECTANGULAR_ROI, null);
		assertEquals(expected, actual);
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
		Object actual = marshaller.unmarshal(json, null);
		assertEquals(expected, actual);
	}

	@Test
	public void testRectangularROI() throws Exception {
		double xStartPoint = 10.15;
		double yStartPoint = 12.45;
		double width = 325.10;
		double height = 125.87;
		// Create ROI
		RectangularROI rectangularROI = new RectangularROI(xStartPoint, yStartPoint, width, height, 0);
		Object expected = rectangularROI.copy();
		json = marshaller.marshal(rectangularROI);
		Object actual = marshaller.unmarshal(json, null);
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
		Object actual = marshaller.unmarshal(json, null);
		assertEquals(expected, actual);
	}

	@Test
	public void testRingROI() throws Exception {
		double ptx = 17.5;
		double pty = 25.1;
		double innerRadius = 14.4;
		double outerRadius = 37.89;

		// Create ROI
		RingROI sectorROI = new RingROI(ptx, pty, innerRadius, outerRadius);
		Object expected = sectorROI.copy();
		json = marshaller.marshal(sectorROI);
		Object actual = marshaller.unmarshal(json, null);
		assertEquals(expected, actual);
	}

	@Test
	public void testSectorROI() throws Exception {
		double ptx = 17.5;
		double pty = 25.1;
		double startAngle = 10;
		double endAngle = 25.5;
		double innerRadius = 14.4;
		double outerRadius = 37.89;

		// Create ROI
		SectorROI sectorROI = new SectorROI(ptx, pty, innerRadius, outerRadius, startAngle, endAngle);
		Object expected = sectorROI.copy();
		json = marshaller.marshal(sectorROI);
		Object actual = marshaller.unmarshal(json, null);
		assertEquals(expected, actual);
	}

	@Test
	public void testPointROI() throws Exception {
		// Create ROI
		PointROI pointROI = new PointROI(10.23, -12.3);
		Object expected = pointROI.copy();
		json = marshaller.marshal(pointROI);
		Object actual = marshaller.unmarshal(json, null);
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
		Object actual = marshaller.unmarshal(json, null);
		assertEquals(expected, actual);
	}

}