/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.NXaperture;
import org.eclipse.dawnsci.nexus.NXcollection;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsensor;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.january.dataset.DTypeUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.junit.Assert;
import org.junit.Test;

public class NXobjectTest {
	
	@Test
	public void testGetNXclass() {
		// test a few of the base classes to ensure they have the expected NXclass
		assertEquals(NXaperture.class, NexusNodeFactory.createNXaperture().getNXclass());
		assertEquals(NXentry.class, NexusNodeFactory.createNXentry().getNXclass());
		assertEquals(NXinstrument.class, NexusNodeFactory.createNXinstrument().getNXclass());
		assertEquals(NXsample.class, NexusNodeFactory.createNXsample().getNXclass());
	}
	
	@Test
	public void testPutGetChild() {
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		NXdetector detector = NexusNodeFactory.createNXdetector();
		instrument.putChild("detector", detector);
		
		assertNotNull(instrument.getChild("detector", NXdetector.class));
		assertSame(detector, instrument.getChild("detector", NXdetector.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPutGetChild_existingChildWithSameNameButDifferentType() {
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		NXsensor sensor = NexusNodeFactory.createNXsensor();
		instrument.putChild("detector", sensor);
		
		NXdetector detector = NexusNodeFactory.createNXdetector();
		instrument.putChild("detector", detector);
	}
	
	@Test
	public void testGetChildren() {
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		NXdetector detector1 = NexusNodeFactory.createNXdetector();
		NXdetector detector2 = NexusNodeFactory.createNXdetector();
		NXdetector detector3 = NexusNodeFactory.createNXdetector();
		instrument.putChild("detector1", detector1);
		instrument.putChild("detector2", detector2);
		instrument.putChild("detector3", detector3);

		// add a child of a different type to check that isn't returned as well
		NXsensor sensor = NexusNodeFactory.createNXsensor();
		instrument.putChild("sensor", sensor);
		
		final Map<String, NXdetector> detectors = instrument.getChildren(NXdetector.class);
		assertEquals(3, detectors.size());
		assertSame(detector1, detectors.get("detector1"));
		assertSame(detector2, detectors.get("detector2"));
		assertSame(detector3, detectors.get("detector3"));
	}
	
	@Test
	public void testSetChildren() {
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		NXdetector detector1 = NexusNodeFactory.createNXdetector();
		NXdetector detector2 = NexusNodeFactory.createNXdetector();
		NXdetector detector3 = NexusNodeFactory.createNXdetector();
		
		final Map<String, NXdetector> detectors = new HashMap<>();
		detectors.put("detector1", detector1);
		detectors.put("detector2", detector2);
		detectors.put("detector3", detector3);
		instrument.setChildren(detectors);
		
		// add a child of a different type to check that isn't returned as well
		NXsensor sensor = NexusNodeFactory.createNXsensor();
		instrument.putChild("sensor", sensor);
		
		final Map<String, NXdetector> detectors2 = instrument.getChildren(NXdetector.class);
		assertEquals(3, detectors2.size());
		assertSame(detector1, detectors2.get("detector1"));
		assertSame(detector2, detectors2.get("detector2"));
		assertSame(detector3, detectors2.get("detector3"));
	}

	@Test
	public void testGetSetDataset() {
		NXdetector detector = NexusNodeFactory.createNXdetector();
		IDataset numberOfCycles = DatasetFactory.createFromObject(3);
		detector.setNumber_of_cycles(numberOfCycles);
		
		assertNotNull(detector.getNumber_of_cycles());
		assertSame(numberOfCycles, detector.getNumber_of_cycles());
	}
	
	@Test
	public void testInitializeLazyDataset() {
		NXdetector detector = NexusNodeFactory.createNXdetector();
		ILazyWriteableDataset dataset = detector.initializeLazyDataset(NXdetector.NX_DATA, 2, Double.class);
		assertNotNull(dataset);
		assertEquals(2, dataset.getRank());
		assertEquals(Double.class, dataset.getElementClass());
		assertEquals(DTypeUtils.getDType(dataset), Dataset.FLOAT64);
		
		assertSame(dataset, detector.getLazyWritableDataset(NXdetector.NX_DATA));
		DataNode dataNode = detector.getDataNode(NXdetector.NX_DATA);
		assertNotNull(dataNode);
		assertSame(dataset, dataNode.getDataset());
	}
	
	@Test
	public void testInitializeFixedSizeLazyDataset() {
		NXcollection scanPointsCollection = NexusNodeFactory.createNXcollection();
		final int[] shape = new int[] { 1 };
		ILazyWriteableDataset dataset = scanPointsCollection.initializeFixedSizeLazyDataset("scan_finished", shape, Integer.class);
		assertNotNull(dataset);
		assertEquals(1, dataset.getRank());
		assertArrayEquals(shape, dataset.getShape());
		assertArrayEquals(shape, dataset.getMaxShape());
		assertEquals(Integer.class, dataset.getElementClass());
		assertEquals(DTypeUtils.getDType(dataset), Dataset.INT32);
		
		assertSame(dataset, scanPointsCollection.getLazyWritableDataset("scan_finished"));
		DataNode dataNode = scanPointsCollection.getDataNode("scan_finished");
		assertNotNull(dataNode);
		assertSame(dataset, dataNode.getDataset());
	}
	
	@Test
	public void testGetSetString() {
		// check value is initially null
		NXsensor sensor = NexusNodeFactory.createNXsensor();
		assertNull(sensor.getString(NXsensor.NX_NAME));
		
		// check value after setting
		final String name = "my sensor";
		sensor.setField(NXsensor.NX_NAME, name);
		assertEquals(name, sensor.getString(NXsensor.NX_NAME)); 
	}
	
	@Test
	public void testGetSetBoolean() {
		// check value is initially null
		NXdetector detector = NexusNodeFactory.createNXdetector();
		assertNull(detector.getBoolean(NXdetector.NX_ANGULAR_CALIBRATION_APPLIED));
		
		// check value after setting
		boolean angularCalibrationApplied = true;
		detector.setField(NXdetector.NX_ANGULAR_CALIBRATION_APPLIED, angularCalibrationApplied);
		assertEquals(angularCalibrationApplied, detector.getBoolean(NXdetector.NX_ANGULAR_CALIBRATION_APPLIED));

		angularCalibrationApplied = false; 
		detector.setField(NXdetector.NX_ANGULAR_CALIBRATION_APPLIED, angularCalibrationApplied);
		assertEquals(angularCalibrationApplied, detector.getBoolean(NXdetector.NX_ANGULAR_CALIBRATION_APPLIED));
	}
	
	@Test
	public void testGetSetLong() {
		// check value is initially null
		NXsensor sensor = NexusNodeFactory.createNXsensor();
		assertNull(sensor.getLong(NXsensor.NX_HIGH_TRIP_VALUE));
		
		// check value after setting
		final long value = 1234567890l;
		sensor.setField(NXsensor.NX_HIGH_TRIP_VALUE, value); 
		assertEquals(value, sensor.getLong(NXsensor.NX_HIGH_TRIP_VALUE).longValue()); 
	}
	
	@Test
	public void testGetSetDouble() {
		// check value is initially null
		NXdetector detector = NexusNodeFactory.createNXdetector();
		assertNull(detector.getDouble(NXdetector.NX_DISTANCE));
		
		// check value after setting
		final double distance = 2.4;
		detector.setField(NXdetector.NX_DISTANCE, distance);
		assertEquals(distance, detector.getDouble(NXdetector.NX_DISTANCE), 0.0); 
	}
	
	@Test
	public void testGetSetNumber_floatingPoint() {
		// check value is initially null
		NXdetector detector = NexusNodeFactory.createNXdetector();
		assertNull(detector.getDouble(NXdetector.NX_DISTANCE));
		
		// check value after setting
		final double distance = 2.4;
		detector.setField(NXdetector.NX_DISTANCE, distance);
		assertEquals(Double.valueOf(distance), detector.getNumber(NXdetector.NX_DISTANCE)); 
	}
	
	@Test
	public void testGetSetNumber_integer() {
		// check value is initially null
		NXdetector detector = NexusNodeFactory.createNXdetector();
		assertNull(detector.getNumber(NXdetector.NX_FRAME_START_NUMBER));
		
		// check value after setting
		final int frameStartNumber = 12;
		detector.setField(NXdetector.NX_FRAME_START_NUMBER, frameStartNumber); 
		// all integers stored as Java longs
		assertEquals(Long.valueOf(frameStartNumber), detector.getNumber(NXdetector.NX_FRAME_START_NUMBER));
	}
	
	@Test
	public void testGetSetDate() {
		// check value is initially null
		NXdetector detector = NexusNodeFactory.createNXdetector();
		assertNull(detector.getDate(NXdetector.NX_CALIBRATION_DATE));
		
		// check value after setting
		// Use current date to nearest second (the string format used does not include milliseconds)
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		
		detector.setField(NXdetector.NX_CALIBRATION_DATE, date);
		assertEquals(date, detector.getDate(NXdetector.NX_CALIBRATION_DATE));
	}
	
	@Test
	public void testGetAllDatasets() {
		NXdetector detector = NexusNodeFactory.createNXdetector();
		
		Dataset azimuthalAngle = DatasetFactory.createFromObject(12.34);
		Dataset numberOfCycles = DatasetFactory.createFromObject(7);
		Dataset distance = DatasetFactory.createFromObject(500);
		
		detector.setAzimuthal_angle(azimuthalAngle);
		detector.setNumber_of_cycles(numberOfCycles);
		detector.setDistance(distance);
		
		final Map<String, IDataset> datasets = detector.getAllDatasets();
		assertEquals(3, datasets.size());
		assertSame(azimuthalAngle, datasets.get(NXdetector.NX_AZIMUTHAL_ANGLE));
		assertSame(numberOfCycles, datasets.get(NXdetector.NX_NUMBER_OF_CYCLES));
		assertSame(distance, datasets.get(NXdetector.NX_DISTANCE));
	}
	
	@Test
	public void testGetSetAttribute() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttr("field", "attribute"));
		
		// check value after setting
		String value = "attrVal";
		detector.setField("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		IDataset dataset = detector.getAttr("field", "attribute");
		assertNotNull(dataset);
		assertEquals(1, dataset.getRank());
		assertEquals(1, dataset.getSize());
		assertEquals(value, dataset.getString(0));
	}
	
	@Test
	public void testGetSetAttrString() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrString("field", "attribute"));
		
		// check value after setting
		String value = "attrVal";
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrString("field", "attribute"));
	}
	
	@Test
	public void testGetSetAttrBoolean() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrBoolean("field", "attribute"));

		// check value after setting
		final boolean value = true;
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrBoolean("field", "attribute"));
	}
	
	@Test
	public void testGetSetAttrLong() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrLong("field", "attribute"));
		
		// check value after setting
		final Long value = 1234567890l;
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrLong("field", "attribute"));
	}
	
	@Test
	public void testGetSetAttrDouble() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrDouble("field", "attribute"));
		
		// check value after setting
		final double value = 1234.5678;
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrDouble("field", "attribute"), 0.0);
	}
	
	@Test
	public void testGetSetAttrNumber_floatingPoint() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrNumber("field", "attribute"));

		// check value after setting
		final double value = 1234.5678;
		detector.setAttribute("field", "attribute", value);
		assertEquals(Double.valueOf(value), detector.getAttrNumber("field", "attribute"));
	}
	
	@Test
	public void testGetSetAttrNumber_integer() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrNumber("field", "attribute"));
		
		// check value after setting
		final int value = 1234;
		detector.setAttribute("field", "attribute", value);
		assertEquals(Long.valueOf(value), detector.getAttrNumber("field", "attribute"));
	}
	
	@Test
	public void testGetSetAttrDate() {
		// check value is null initially
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.setField("field", "fieldValue");
		assertNull(detector.getAttrNumber("field", "attribute"));
		
		// check value after setting
		// Use current date to nearest second (the string format used does not include milliseconds)
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		
		detector.setAttribute("field", "attribute", date);
		assertEquals(date, detector.getAttrDate("field", "attribute"));
	}
	
}
