package org.eclipse.dawnsci.nexus.builder.impl;

import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertAxes;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertIndices;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertNodesEquals;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertSignal;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusProvider;
import org.eclipse.dawnsci.nexus.builder.DataDevice;
import org.eclipse.dawnsci.nexus.builder.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.junit.Before;
import org.junit.Test;

public class DefaultNexusDataBuilderTest {
	
	public static class TestDetector extends AbstractNexusProvider<NXdetector> {

		public TestDetector() {
			super("testDetector", NexusBaseClass.NX_DETECTOR);
		}
		
		@Override
		protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXdetector detector = nodeFactory.createNXdetector();
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Dataset.FLOAT64);
			return detector;
		}
		
	}
	
	public static class MultipleFieldTestDetector extends AbstractNexusProvider<NXdetector> {
		
		public MultipleFieldTestDetector() {
			super("detector2", NexusBaseClass.NX_DETECTOR);
			setPrimaryDataFieldName(NXdetector.NX_DATA);
			addDataField(NXdetector.NX_TIME_OF_FLIGHT, 2);
		}
		
		@Override
		protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXdetector detector = nodeFactory.createNXdetector();
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Dataset.FLOAT64);
			detector.setAttribute(NXdetector.NX_DATA, "units", "mm");
			detector.initializeLazyDataset(NXdetector.NX_TIME_OF_FLIGHT, 1, Dataset.FLOAT64);
			
			return detector;
		}
		
	}
	
	public static class MultipleDataFieldTestDetector extends AbstractNexusProvider<NXdetector> {
		
		public MultipleDataFieldTestDetector() {
			super("detector", NexusBaseClass.NX_DETECTOR);
			setPrimaryDataFieldName(NXdetector.NX_DATA);
			addDataField("sum", null);
		}
		
		@Override
		protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXdetector detector = nodeFactory.createNXdetector();
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Dataset.FLOAT64);
			detector.initializeLazyDataset("sum", 3, Dataset.FLOAT64);
			
			return detector;
		}
		
	}
	
	public static class TestDetectorWithExternalLink extends AbstractNexusProvider<NXdetector> {
		
		public TestDetectorWithExternalLink() {
			super("testDetector", NexusBaseClass.NX_DETECTOR);
		}
		
		@Override
		protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXdetector detector = nodeFactory.createNXdetector();
			// create an external link instead of a lazy dataset
			addExternalLink(detector, NXdetector.NX_DATA, "", "/entry/data", 3);
			return detector;
		}
		
	}
	
	public static class TestPositioner extends AbstractNexusProvider<NXpositioner> {
		
		public TestPositioner() {
			super("positioner", NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}
		
		public TestPositioner(String name) {
			super(name, NexusBaseClass.NX_POSITIONER);
		}
		
		@Override
		protected NXpositioner doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXpositioner positioner = nodeFactory.createNXpositioner();
			positioner.initializeLazyDataset(NXpositioner.NX_VALUE, 1, Dataset.FLOAT64);
			positioner.initializeLazyDataset("source", 1, Dataset.FLOAT64);
			return positioner;
		}
		
	}

	public static class MultipleFieldTestPositioner extends AbstractNexusProvider<NXpositioner> {
		
		public MultipleFieldTestPositioner() {
			super("ss1", NexusBaseClass.NX_POSITIONER);
			setDataFieldNames("field1", "field2", "field3", "field4");
		}
		
		@Override
		protected NXpositioner doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXpositioner positioner = nodeFactory.createNXpositioner();
			positioner.initializeLazyDataset("field1", 1, Dataset.FLOAT64);
			positioner.initializeLazyDataset("field2", 1, Dataset.FLOAT64);
			positioner.initializeLazyDataset("field3", 1, Dataset.FLOAT64);
			positioner.initializeLazyDataset("field4", 1, Dataset.FLOAT64);
			return positioner;
		}
		
	}
	
	public static class TestPositionerWithExternalLink extends AbstractNexusProvider<NXpositioner> {
		
		public TestPositionerWithExternalLink() {
			super("positioner", NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}
		
		@Override
		protected NXpositioner doCreateNexusObject(NexusNodeFactory nodeFactory) {
			NXpositioner positioner = nodeFactory.createNXpositioner();
			addExternalLink(positioner, NXpositioner.NX_VALUE, "", "/entry/data", 1);
			
			return positioner;
		}
		
	}

	private NexusDataBuilder dataBuilder;
	
	private NXdata nxData;
	
	@Before
	public void setUp() throws Exception {
		NexusFileBuilder fileBuilder = new DefaultNexusFileBuilder("test");
		NexusEntryBuilder entryBuilder = fileBuilder.newEntry();
		dataBuilder = entryBuilder.createDefaultData();
		nxData = dataBuilder.getNxData();
	}
	
	@Test
	public void testGetNxData() {
		assertThat(dataBuilder.getNxData(), notNullValue());
	}
	
	@Test
	public void testSetPrimaryDataDevice() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		assertSignal(nxData, NXdetector.NX_DATA);
		assertAxes(nxData, ".", ".", ".");
		assertThat(nxData.getDataNode(NXdetector.NX_DATA), is(sameInstance(
				detector.getNexusObject().getDataNode(NXdetector.NX_DATA))));
	}
	
	@Test
	public void testSetPrimaryDataDevice_useDeviceName() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(new DataDevice<>(detector, true));
		
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		assertSignal(nxData, "testDetector");
		assertAxes(nxData, ".", ".", ".");
		assertThat(nxData.getDataNode("testDetector"), is(sameInstance(
				detector.getNexusObject().getDataNode(NXdetector.NX_DATA))));
	}
	
	@Test
	public void testSetPrimaryDataDevice_setSignalFieldName() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		MultipleDataFieldTestDetector detector = new MultipleDataFieldTestDetector();
		DataDevice<NXdetector> dataDevice = new DataDevice<>(detector, false);
		dataDevice.setPrimaryDataSourceFieldName("sum");
		dataBuilder.setPrimaryDevice(dataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertSignal(nxData, "sum");
		assertAxes(nxData, ".", ".", ".");
		assertThat(nxData.getDataNode("sum"), is(sameInstance(
				detector.getNexusObject().getDataNode("sum"))));
	}
	
	@Test
	public void testSetPrimaryDataDevice_multipleFields() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		MultipleFieldTestDetector detector = new MultipleFieldTestDetector();
		DataDevice<NXdetector> dataDevice = new DataDevice<>(detector);
		dataDevice.setUseDeviceName(false);
		dataDevice.setDestinationFieldName(NXdetector.NX_TIME_OF_FLIGHT, "tof");
		dataBuilder.setPrimaryDevice(dataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertSignal(nxData, "data");
		assertAxes(nxData, ".", ".", "tof");
		assertIndices(nxData, "tof", 2);
		assertThat(nxData.getDataNode("data"), is(sameInstance(
				detector.getNexusObject().getDataNode(NXdetector.NX_DATA))));
		assertThat(nxData.getDataNode("tof"), is(sameInstance(
				detector.getNexusObject().getDataNode(NXdetector.NX_TIME_OF_FLIGHT))));
	}
	
	@Test
	public void testSetPrimaryDataDevice_fieldName() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		TestDetector detector = new TestDetector();
		DataDevice<?> detectorDataDevice = new DataDevice<>(detector);
		detectorDataDevice.setDestinationFieldName(NXdetector.NX_DATA, "foo");
		dataBuilder.setPrimaryDevice(detectorDataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		assertSignal(nxData, "foo");
		assertAxes(nxData, ".", ".", ".");
		assertThat(nxData.getDataNode("foo"), is(sameInstance(
				detector.getNexusObject().getDataNode(NXdetector.NX_DATA))));
	}
	
	@Test
	public void testSetPrimaryDataDevice_externalLink() throws Exception {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		TestDetectorWithExternalLink detector = new TestDetectorWithExternalLink();
		dataBuilder.setPrimaryDevice(detector);
		
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		assertSignal(nxData, NXdetector.NX_DATA);
		assertAxes(nxData, ".", ".", ".");
		assertNodesEquals("", nxData.getNode("data"),
				detector.getNexusObject().getNode(NXdetector.NX_DATA));
	}
	
	@Test
	public void testAddDataDevice() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositioner positioner = new TestPositioner();
		dataBuilder.addDataDevice(positioner, null, 0);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", ".", ".");
		assertIndices(nxData, "positioner", 0);
		assertThat(nxData.getDataNode("positioner"), is(sameInstance(
				positioner.getNexusObject().getDataNode(NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testAddDataDevice_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositioner positioner = new TestPositioner();
		dataBuilder.addDataDevice(positioner, 0);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, "positioner", ".", ".");
		assertIndices(nxData, "positioner", 0);
		assertThat(nxData.getDataNode("positioner"), is(sameInstance(
				positioner.getNexusObject().getDataNode(NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testAddDataDevice_sourceFieldName() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositioner positioner = new TestPositioner();
		DataDevice<NXpositioner> axisDevice = new DataDevice<>(positioner, null, 0);
		axisDevice.setUseDeviceName(false);
		axisDevice.setSourceFields("source");
		dataBuilder.addDataDevice(axisDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", ".", ".");
		assertIndices(nxData, "source", 0);
		assertThat(nxData.getDataNode("source"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddDataDevice_sourceFieldName_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositioner positioner = new TestPositioner();
		DataDevice<NXpositioner> dataDevice = new DataDevice<>(positioner, 0);
		dataDevice.setUseDeviceName(false);
		dataDevice.addSourceField("source", 0);
		dataBuilder.addDataDevice(dataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(5));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(3));
		
		assertAxes(nxData, "source", ".", ".");
		assertIndices(nxData, "source", 0);
		assertThat(nxData.getDataNode("source"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddDataDevice_sourceAndDestinationFieldNames() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositioner positioner = new TestPositioner();
		DataDevice<NXpositioner> dataDevice = new DataDevice<>(positioner, null, 0);
		dataDevice.setUseDeviceName(false);
		dataDevice.clearSourceFields().addSourceField("source", "dest");
		dataBuilder.addDataDevice(dataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", ".", ".");
		assertIndices(nxData, "dest", 0);
		assertThat(nxData.getDataNode("dest"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddDataDevice_sourceAndDestinationFieldNames_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositioner positioner = new TestPositioner();
		DataDevice<NXpositioner> dataDevice = new DataDevice<>(positioner, false, 1);
		dataDevice.setUseDeviceName(false);
		dataDevice.setDestinationFieldName(NXpositioner.NX_VALUE, "dest");
		dataBuilder.addDataDevice(dataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", "dest", ".");
		assertIndices(nxData, "dest", 1);
		assertThat(nxData.getDataNode("dest"), is(sameInstance(
				positioner.getNexusObject().getDataNode(NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testAddDataDevice_multipleFields() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		MultipleFieldTestPositioner positioner = new MultipleFieldTestPositioner();
//		DataDevice<NXpositioner> axisDevice = new DataDevice<>(positioner, new int[] { 0 });
//		axisDevice.setUseDeviceName(false);
		dataBuilder.addDataDevice(positioner, 0);
//		dataBuilder.addDevice(axisDevice);

		assertThat(nxData.getNumberOfAttributes(), is(7));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(5));
		
		assertAxes(nxData, ".", ".", ".");
		for (String sourceFieldName : positioner.getDataFieldNames()) {
			String destinationFieldName = positioner.getName() + "_" + sourceFieldName;
			assertIndices(nxData, destinationFieldName, 0);
			assertThat(nxData.getDataNode(destinationFieldName), is(sameInstance(
					positioner.getNexusObject().getDataNode(sourceFieldName))));
		}
	}
	
	@Test
	public void testAddDataDevice_multipleFields_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		MultipleFieldTestPositioner positioner = new MultipleFieldTestPositioner();
		DataDevice<NXpositioner> axisDevice = new DataDevice<>(positioner, false, "field3", 2);
		axisDevice.setUseDeviceName(false);
		dataBuilder.addDataDevice(axisDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(7));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(5));
		
		assertAxes(nxData, ".", ".", "field3");
		for (String sourceFieldName : positioner.getDataFieldNames()) {
			assertIndices(nxData, sourceFieldName, sourceFieldName.equals("field3") ? 2 : 0);
			assertThat(nxData.getDataNode(sourceFieldName), is(sameInstance(
					positioner.getNexusObject().getDataNode(sourceFieldName))));
		}
	}
	
	@Test
	public void testAddDataDevice_externalLink() throws Exception {
		TestDetector detector = new TestDetector();
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		TestPositionerWithExternalLink positioner = new TestPositionerWithExternalLink();
		dataBuilder.addDataDevice(positioner, 0);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, "positioner", ".", ".");
		assertIndices(nxData, "positioner", 0);
		assertNodesEquals("", nxData.getNode("positioner"),
				positioner.getNexusObject().getNode(NXpositioner.NX_VALUE));
	}
	
}
