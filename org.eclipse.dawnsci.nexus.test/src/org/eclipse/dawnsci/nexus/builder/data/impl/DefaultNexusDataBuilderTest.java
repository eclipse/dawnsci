package org.eclipse.dawnsci.nexus.builder.data.impl;

import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertAxes;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertIndices;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertNodesEquals;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertSignal;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.data.AxisDataDevice;
import org.eclipse.dawnsci.nexus.builder.data.DataDeviceBuilder;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.data.PrimaryDataDevice;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusFileBuilder;
import org.junit.Before;
import org.junit.Test;

public class DefaultNexusDataBuilderTest {
	
	public static class TestDetector extends AbstractNexusObjectProvider<NXdetector> {

		public TestDetector() {
			super("testDetector", NexusBaseClass.NX_DETECTOR, NXdetector.NX_DATA);
		}
		
		@Override
		protected NXdetector createNexusObject() {
			NXdetector detector = NexusNodeFactory.createNXdetector();
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Double.class);
			return detector;
		}
		
	}
	
	public static class TestDetectorWithAxisField extends AbstractNexusObjectProvider<NXdetector> {
		
		public TestDetectorWithAxisField() {
			super("detector2", NexusBaseClass.NX_DETECTOR);
			setPrimaryDataFieldName(NXdetector.NX_DATA);
			addAxisDataField(NXdetector.NX_TIME_OF_FLIGHT, 2, null);
		}
		
		@Override
		protected NXdetector createNexusObject() {
			NXdetector detector = NexusNodeFactory.createNXdetector();
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Double.class);
			detector.setAttribute(NXdetector.NX_DATA, "units", "mm");
			detector.initializeLazyDataset(NXdetector.NX_TIME_OF_FLIGHT, 1, Double.class);
			
			return detector;
		}
		
	}
	
	public static class TestDetectorWithMultiplePrimaryDataFields extends AbstractNexusObjectProvider<NXdetector> {
		
		public TestDetectorWithMultiplePrimaryDataFields() {
			super("detector", NexusBaseClass.NX_DETECTOR);
			setPrimaryDataFieldName(NXdetector.NX_DATA);
			addAdditionalPrimaryDataFieldName("sum");
		}
		
		@Override
		protected NXdetector createNexusObject() {
			NXdetector detector = NexusNodeFactory.createNXdetector();
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Double.class);
			detector.initializeLazyDataset("sum", 3, Double.class);
			
			return detector;
		}
		
	}
	
	public static class TestDetectorWithExternalLink extends AbstractNexusObjectProvider<NXdetector> {
		
		public TestDetectorWithExternalLink() {
			super("testDetector", NexusBaseClass.NX_DETECTOR, NXdetector.NX_DATA);
			setExternalFileName("external.nxs");
		}
		
		@Override
		protected NXdetector createNexusObject() {
			NXdetector detector = NexusNodeFactory.createNXdetector();
			// create an external link instead of a lazy dataset
			addExternalLink(detector, NXdetector.NX_DATA, "/entry/data", 3);
			return detector;
		}
		
	}
	
	public static class TestPositioner extends AbstractNexusObjectProvider<NXpositioner> {
		
		public TestPositioner() {
			super("positioner", NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}
		
		public TestPositioner(String name) {
			super(name, NexusBaseClass.NX_POSITIONER);
		}
		
		@Override
		protected NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.initializeLazyDataset(NXpositioner.NX_VALUE, 1, Double.class);
			positioner.initializeLazyDataset("source", 1, Double.class);
			return positioner;
		}
		
	}

	public static class MultipleFieldTestPositioner extends AbstractNexusObjectProvider<NXpositioner> {
		
		public MultipleFieldTestPositioner() {
			super("ss1", NexusBaseClass.NX_POSITIONER);
			setUseDeviceNameInNXdata(false);
			setAxisDataFieldNames("field1", "field2", "field3", "field4");
		}
		
		@Override
		protected NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.initializeLazyDataset("field1", 1, Double.class);
			positioner.initializeLazyDataset("field2", 1, Double.class);
			positioner.initializeLazyDataset("field3", 1, Double.class);
			positioner.initializeLazyDataset("field4", 1, Double.class);
			return positioner;
		}
		
	}
	
	public static class TestPositionerWithExternalLink extends AbstractNexusObjectProvider<NXpositioner> {
		
		public TestPositionerWithExternalLink() {
			super("positioner", NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
			setExternalFileName("external.nxs");
		}
		
		@Override
		protected NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			addExternalLink(positioner, NXpositioner.NX_VALUE, "/entry/data", 1);
			
			return positioner;
		}
		
	}

	private NexusDataBuilder dataBuilder;
	
	private NXdata nxData;
	
	@Before
	public void setUp() throws Exception {
		DefaultNexusFileBuilder fileBuilder = new DefaultNexusFileBuilder("test");
		DefaultNexusEntryBuilder entryBuilder = fileBuilder.newEntry();
		entryBuilder.addDefaultGroups();
		dataBuilder = entryBuilder.createDefaultData();
		nxData = dataBuilder.getNxData();
	}
	
	private void addToEntry(NexusObjectProvider<?>... nexusObjects) throws NexusException {
		// TODO it would be good to be able to add fields from a group that hasn't been created already 
		for (NexusObjectProvider<?> nexusObjectProvider : nexusObjects) {
			nexusObjectProvider.getNexusObject();
		}
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
		addToEntry(detector);
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
		detector.setUseDeviceNameInNXdata(true);
		addToEntry(detector);
		dataBuilder.setPrimaryDevice(detector);
		
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
		
		TestDetectorWithAxisField detector = new TestDetectorWithAxisField();
		addToEntry(detector);
		PrimaryDataDevice<NXdetector> dataDevice = DataDeviceBuilder.newPrimaryDataDevice(detector,
				NXdetector.NX_TIME_OF_FLIGHT);
		dataBuilder.setPrimaryDevice(dataDevice);
		
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		assertSignal(nxData, NXdetector.NX_TIME_OF_FLIGHT);
		assertAxes(nxData, ".");
		assertThat(nxData.getDataNode(NXdetector.NX_TIME_OF_FLIGHT), is(sameInstance(
				detector.getNexusObject().getDataNode(NXdetector.NX_TIME_OF_FLIGHT))));
	}
	
	@Test
	public void testSetPrimaryDataDevice_multipleFields() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		TestDetectorWithAxisField detector = new TestDetectorWithAxisField();
		addToEntry(detector);
		DataDeviceBuilder<NXdetector> detectorDataDevice =
				DataDeviceBuilder.newPrimaryDataDeviceBuilder(detector);
		detectorDataDevice.setDestinationFieldName(NXdetector.NX_TIME_OF_FLIGHT, "tof");
		dataBuilder.setPrimaryDevice((PrimaryDataDevice<NXdetector>) detectorDataDevice.build());
		
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
	public void testSetPrimaryDataDevice_setDestinationFieldName() throws NexusException {
		assertThat(nxData.getNumberOfAttributes(), is(1));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(0));
		
		TestDetector detector = new TestDetector();
		addToEntry(detector);
		DataDeviceBuilder<NXdetector> detectorDataDevice = new DataDeviceBuilder<>(detector, true);
		detectorDataDevice.setDestinationFieldName(NXdetector.NX_DATA, "foo");
		dataBuilder.setPrimaryDevice((PrimaryDataDevice<NXdetector>) detectorDataDevice.build());
		
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
		addToEntry(detector);
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
	public void testAddAxisDevice() throws NexusException {
		TestDetector detector = new TestDetector();
		TestPositioner positioner = new TestPositioner();
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		dataBuilder.addAxisDevice(positioner, null, 0);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", ".", ".");
		assertIndices(nxData, "positioner", 0);
		assertThat(nxData.getDataNode("positioner"), is(sameInstance(
				positioner.getNexusObject().getDataNode(NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testAddAxisDevice_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		TestPositioner positioner = new TestPositioner();
		addToEntry(detector, positioner);

		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		dataBuilder.addAxisDevice(positioner, 0);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, "positioner", ".", ".");
		assertIndices(nxData, "positioner", 0);
		assertThat(nxData.getDataNode("positioner"), is(sameInstance(
				positioner.getNexusObject().getDataNode(NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testAddAxisDevice_setSourceFieldName() throws NexusException {
		TestDetector detector = new TestDetector();
		TestPositioner positioner = new TestPositioner();
		positioner.setUseDeviceNameInNXdata(false);
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		DataDeviceBuilder<NXpositioner> axisDevice = DataDeviceBuilder.newAxisDataDeviceBuilder(
				positioner);
		axisDevice.clearAxisFields();
		axisDevice.addAxisField("source");
		axisDevice.setDefaultAxisSourceFieldName("source");
		dataBuilder.addAxisDevice((AxisDataDevice<?>) axisDevice.build());
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", ".", ".");
		assertIndices(nxData, "source", 0);
		assertThat(nxData.getDataNode("source"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddAxisDevice_sourceFieldName_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		TestPositioner positioner = new TestPositioner();
		positioner.setUseDeviceNameInNXdata(false);
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		DataDeviceBuilder<NXpositioner> axisDevice = DataDeviceBuilder.newAxisDataDeviceBuilder(
				positioner);
		axisDevice.clearAxisFields();
		axisDevice.addAxisField("source", 0);
		dataBuilder.addAxisDevice((AxisDataDevice<?>) axisDevice.build());
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, "source", ".", ".");
		assertIndices(nxData, "source", 0);
		assertThat(nxData.getDataNode("source"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddAxisDevice_sourceAndDestinationFieldNames() throws NexusException {
		TestDetector detector = new TestDetector();
		TestPositioner positioner = new TestPositioner();
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		DataDeviceBuilder<NXpositioner> axisDevice = DataDeviceBuilder.newAxisDataDeviceBuilder(
				positioner);
		axisDevice.setAxisFields("source");
		axisDevice.setDestinationFieldName("source", "dest");
		dataBuilder.addAxisDevice((AxisDataDevice<?>) axisDevice.build());
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", ".", ".");
		assertIndices(nxData, "dest", 0);
		assertThat(nxData.getDataNode("dest"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddAxisDevice_sourceAndDestinationFieldNames_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		TestPositioner positioner = new TestPositioner();
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		DataDeviceBuilder<NXpositioner> axisDevice = DataDeviceBuilder.newAxisDataDeviceBuilder(
				positioner, 1);
		axisDevice.setAxisFields("source");
		axisDevice.setDefaultAxisSourceFieldName("source");
		axisDevice.setDestinationFieldName("source", "dest");
		dataBuilder.addAxisDevice((AxisDataDevice<?>) axisDevice.build());
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, ".", "dest", ".");
		assertIndices(nxData, "dest", 1);
		assertThat(nxData.getDataNode("dest"), is(sameInstance(
				positioner.getNexusObject().getDataNode("source"))));
	}
	
	@Test
	public void testAddAxisDevice_multipleFields() throws NexusException {
		TestDetector detector = new TestDetector();
		MultipleFieldTestPositioner positioner = new MultipleFieldTestPositioner();
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		dataBuilder.addAxisDevice(positioner, 0);

		assertThat(nxData.getNumberOfAttributes(), is(7));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(5));
		
		assertAxes(nxData, "field1", ".", ".");
		for (String sourceFieldName : positioner.getAxisDataFieldNames()) {
			assertIndices(nxData, sourceFieldName, 0);
			assertThat(nxData.getDataNode(sourceFieldName), is(sameInstance(
					positioner.getNexusObject().getDataNode(sourceFieldName))));
		}
	}
	
	@Test
	public void testAddAxisDevice_multipleFields_defaultAxisForDimension() throws NexusException {
		TestDetector detector = new TestDetector();
		MultipleFieldTestPositioner positioner = new MultipleFieldTestPositioner();
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		DataDeviceBuilder<NXpositioner> axisDevice = DataDeviceBuilder.newAxisDataDeviceBuilder(
				positioner, "field3", 2);
		dataBuilder.addAxisDevice((AxisDataDevice<?>) axisDevice.build());
		
		assertThat(nxData.getNumberOfAttributes(), is(7));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(5));
		
		assertAxes(nxData, ".", ".", "field3");
		for (String sourceFieldName : positioner.getAxisDataFieldNames()) {
			
			assertIndices(nxData, sourceFieldName, sourceFieldName.equals("field3") ? 2 : 0);
			assertThat(nxData.getDataNode(sourceFieldName), is(sameInstance(
					positioner.getNexusObject().getDataNode(sourceFieldName))));
		}
	}
	
	@Test
	public void testAddAxisDevice_externalLink() throws Exception {
		TestDetector detector = new TestDetector();
		TestPositionerWithExternalLink positioner = new TestPositionerWithExternalLink();
		addToEntry(detector, positioner);
		
		dataBuilder.setPrimaryDevice(detector);
		assertThat(nxData.getNumberOfAttributes(), is(3));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(1));
		
		dataBuilder.addAxisDevice(positioner, 0);
		
		assertThat(nxData.getNumberOfAttributes(), is(4));
		assertThat(nxData.getNumberOfGroupNodes(), is(0));
		assertThat(nxData.getNumberOfDataNodes(), is(2));
		
		assertAxes(nxData, "positioner", ".", ".");
		assertIndices(nxData, "positioner", 0);
		assertNodesEquals("", nxData.getNode("positioner"),
				positioner.getNexusObject().getNode(NXpositioner.NX_VALUE));
	}
	
}
