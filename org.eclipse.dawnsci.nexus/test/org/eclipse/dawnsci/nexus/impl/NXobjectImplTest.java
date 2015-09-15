package org.eclipse.dawnsci.nexus.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.nexus.NXaperture;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsensor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NXobjectImplTest {
	
	private NXobjectFactory nxObjectFactory;
		
	@Before
	public void setUp() {
		nxObjectFactory = new NXobjectFactory();
	}
	
	@After
	public void tearDown() {
		nxObjectFactory = null;
	}
	
	@Test
	public void testGetNXclass() {
		// test a few of the base classes to ensure they have the expected NXclass
		assertEquals(NXaperture.class, nxObjectFactory.createNXaperture().getNXclass());
		assertEquals(NXentry.class, nxObjectFactory.createNXentry().getNXclass());
		assertEquals(NXinstrument.class, nxObjectFactory.createNXinstrument().getNXclass());
		assertEquals(NXsample.class, nxObjectFactory.createNXsample().getNXclass());
	}
	
	@Test
	public void testPutGetChild() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXdetector detector = nxObjectFactory.createNXdetector();
		instrument.putChild("detector", detector);
		
		assertNotNull(instrument.getChild("detector", NXdetector.class));
		assertSame(detector, instrument.getChild("detector", NXdetector.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPutGetChild_existingChildWithSameNameButDifferentType() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXsensor sensor = nxObjectFactory.createNXsensor();
		instrument.putChild("detector", sensor);
		
		NXdetector detector = nxObjectFactory.createNXdetector();
		instrument.putChild("detector", detector);
	}
	
	@Test
	public void testGetChildren() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXdetector detector1 = nxObjectFactory.createNXdetector();
		NXdetector detector2 = nxObjectFactory.createNXdetector();
		NXdetector detector3 = nxObjectFactory.createNXdetector();
		instrument.putChild("detector1", detector1);
		instrument.putChild("detector2", detector2);
		instrument.putChild("detector3", detector3);

		// add a child of a different type to check that isn't returned as well
		NXsensor sensor = nxObjectFactory.createNXsensor();
		instrument.putChild("sensor", sensor);
		
		final Map<String, NXdetector> detectors = instrument.getChildren(NXdetector.class);
		assertEquals(3, detectors.size());
		assertSame(detector1, detectors.get("detector1"));
		assertSame(detector2, detectors.get("detector2"));
		assertSame(detector3, detectors.get("detector3"));
	}
	
	@Test
	public void testSetChildren() {
		NXinstrumentImpl instrument = nxObjectFactory.createNXinstrument();
		NXdetector detector1 = nxObjectFactory.createNXdetector();
		NXdetector detector2 = nxObjectFactory.createNXdetector();
		NXdetector detector3 = nxObjectFactory.createNXdetector();
		
		final Map<String, NXdetector> detectors = new HashMap<>();
		detectors.put("detector1", detector1);
		detectors.put("detector2", detector2);
		detectors.put("detector3", detector3);
		instrument.setChildren(detectors);
		
		// add a child of a different type to check that isn't returned as well
		NXsensor sensor = nxObjectFactory.createNXsensor();
		instrument.putChild("sensor", sensor);
		
		final Map<String, NXdetector> detectors2 = instrument.getChildren(NXdetector.class);
		assertEquals(3, detectors2.size());
		assertSame(detector1, detectors2.get("detector1"));
		assertSame(detector2, detectors2.get("detector2"));
		assertSame(detector3, detectors2.get("detector3"));
	}

	@Test
	public void testSetGetDataset() {
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		IDataset numberOfCycles = DatasetFactory.createFromObject(3);
		detector.setNumber_of_cycles(numberOfCycles);
		
		assertNotNull(detector.getNumber_of_cycles());
		assertSame(numberOfCycles, detector.getNumber_of_cycles());
	}
	
	@Test
	public void testSetGetString() {
		final String name = "my sensor";
		NXsensorImpl sensor = nxObjectFactory.createNXsensor();
		sensor.setString(NXsensorImpl.NX_NAME, name);
		assertEquals(name, sensor.getString(NXsensorImpl.NX_NAME)); 
	}
	
	@Test
	public void testSetGetBoolean() {
		boolean angularCalibrationApplied = true; 
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.set(NXdetectorImpl.NX_ANGULAR_CALIBRATION_APPLIED, angularCalibrationApplied);
		assertEquals(angularCalibrationApplied, detector.getBoolean(NXdetectorImpl.NX_ANGULAR_CALIBRATION_APPLIED));

		angularCalibrationApplied = false; 
		detector.set(NXdetectorImpl.NX_ANGULAR_CALIBRATION_APPLIED, angularCalibrationApplied);
		assertEquals(angularCalibrationApplied, detector.getBoolean(NXdetectorImpl.NX_ANGULAR_CALIBRATION_APPLIED));
	}
	
	@Test
	public void testSetGetLong() {
		NXsensorImpl sensor = nxObjectFactory.createNXsensor();
		final long value = 1234567890l;
		sensor.set(NXsensorImpl.NX_HIGH_TRIP_VALUE, value); 
		assertEquals(value, sensor.getLong(NXsensorImpl.NX_HIGH_TRIP_VALUE)); 
	}
	
	@Test
	public void testSetGetDouble() {
		final double distance = 2.4;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.set(NXdetectorImpl.NX_DISTANCE, distance);
		
		assertEquals(distance, detector.getDouble(NXdetectorImpl.NX_DISTANCE), 0.0); 
	}
	
	@Test
	public void testSetGetNumber_floatingPoint() {
		final double distance = 2.4;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.set(NXdetectorImpl.NX_DISTANCE, distance);
		
		assertEquals(Double.valueOf(distance), detector.getNumber(NXdetectorImpl.NX_DISTANCE)); 
	}
	
	@Test
	public void testSetGetNumber_integer() {
		final int frameStartNumber = 12;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.set(NXdetectorImpl.NX_FRAME_START_NUMBER, frameStartNumber); 
		// all integers stored as Java longs
		assertEquals(Long.valueOf(frameStartNumber), detector.getNumber(NXdetectorImpl.NX_FRAME_START_NUMBER));
	}
	
	@Test
	public void testSetGetDate() {
		// Use current date to nearest second (the string format used does not include milliseconds)
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setDate(NXdetectorImpl.NX_CALIBRATION_DATE, date);
		assertEquals(date, detector.getDate(NXdetectorImpl.NX_CALIBRATION_DATE));
	}
	
	@Test
	public void testGetAllDatasets() {
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		
		Dataset azimuthalAngle = DatasetFactory.createFromObject(12.34);
		Dataset numberOfCycles = DatasetFactory.createFromObject(7);
		Dataset distance = DatasetFactory.createFromObject(500);
		
		detector.setAzimuthal_angle(azimuthalAngle);
		detector.setNumber_of_cycles(numberOfCycles);
		detector.setDistance(distance);
		
		final Map<String, Dataset> datasets = detector.getAllDatasets(null);
		assertEquals(3, datasets.size());
		assertSame(azimuthalAngle, datasets.get(NXdetectorImpl.NX_AZIMUTHAL_ANGLE));
		assertSame(numberOfCycles, datasets.get(NXdetectorImpl.NX_NUMBER_OF_CYCLES));
		assertSame(distance, datasets.get(NXdetectorImpl.NX_DISTANCE));
	}
	
	@Test
	public void testGetAllDatasets_withAttribute() {
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		
		Dataset azimuthalAngle = DatasetFactory.createFromObject(12.34);
		Dataset numberOfCycles = DatasetFactory.createFromObject(7);
		Dataset distance = DatasetFactory.createFromObject(500);
		
		detector.setAzimuthal_angle(azimuthalAngle);
		detector.setNumber_of_cycles(numberOfCycles);
		detector.setDistance(distance);
		
		detector.setAttribute(NXdetectorImpl.NX_AZIMUTHAL_ANGLE, "attrName", "attrVal");
		detector.setAttribute(NXdetectorImpl.NX_DISTANCE, "attrName", "attrVal");
		
		final Map<String, Dataset> datasets = detector.getAllDatasets("attrName");
		assertEquals(2, datasets.size());
		assertSame(azimuthalAngle, datasets.get(NXdetectorImpl.NX_AZIMUTHAL_ANGLE));
		assertSame(distance, datasets.get(NXdetectorImpl.NX_DISTANCE));
	}
	@Test
	public void testSetGetAttribute() {
		String value = "attrVal";
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		IDataset dataset = detector.getAttr("field", "attribute");
		assertNotNull(dataset);
		assertEquals(1, dataset.getRank());
		assertEquals(1, dataset.getSize());
		assertEquals(value, dataset.getString(0));
	}
	
	@Test
	public void testSetGetAttrString() {
		String value = "attrVal";
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrString("field", "attribute"));
	}
	
	@Test
	public void testSetGetAttrBoolean() {
		final boolean value = true;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrBoolean("field", "attribute"));
	}
	
	@Test
	public void testSetGetAttrLong() {
		final long value = 1234567890l;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrLong("field", "attribute"));
	}
	
	@Test
	public void testSetGetAttrDouble() {
		final double value = 1234.5678;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		assertEquals(value, detector.getAttrDouble("field", "attribute"), 0.0);
	}
	
	@Test
	public void testSetGetAttrNumber_floatingPoint() {
		final double value = 1234.5678;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		assertEquals(Double.valueOf(value), detector.getAttrNumber("field", "attribute"));
	}
	
	@Test
	public void testSetGetAttrNumber_integer() {
		final int value = 1234;
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttribute("field", "attribute", value);
		assertEquals(Long.valueOf(value), detector.getAttrNumber("field", "attribute"));
		
	}
	
	@Test
	public void testGetSetDate() {
		// Use current date to nearest second (the string format used does not include milliseconds)
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		
		NXdetectorImpl detector = nxObjectFactory.createNXdetector();
		detector.setString("field", "fieldValue");
		detector.setAttrDate("field", "attribute", date);
		assertEquals(date, detector.getAttrDate("field", "attribute"));
	}
	
}
