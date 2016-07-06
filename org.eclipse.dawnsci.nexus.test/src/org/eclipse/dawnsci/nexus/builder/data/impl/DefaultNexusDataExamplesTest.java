package org.eclipse.dawnsci.nexus.builder.data.impl;

import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertAxes;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertIndices;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertShape;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertSignal;
import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertTarget;

import java.util.Arrays;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.data.DataDeviceBuilder;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusFileBuilder;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.FloatDataset;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class tests that DefaultNexusDataBuilder
 * can construct the example {@link NXdata} structures from the
 * document http://wiki.nexusformat.org/2014_axes_and_uncertainties.
 */
public class DefaultNexusDataExamplesTest {
	
	public static class TestDetector extends AbstractNexusObjectProvider<NXdetector> {
	
		private final int[] shape;
		
		private boolean hasTimeOfFlight;
		
		public TestDetector(int... shape) {
			this("testDetector", shape);
		}
		
		public TestDetector(String name, int... shape) {
			this(name, true, shape);
		}
		
		public TestDetector(String name, boolean useDeviceName, int... shape) {
			super(name, NexusBaseClass.NX_DETECTOR, NXdetector.NX_DATA);
			setUseDeviceNameInNXdata(useDeviceName);
			this.shape = shape;
		}
		
		
		@Override
		protected NXdetector createNexusObject() {
			NXdetector detector = NexusNodeFactory.createNXdetector();
			detector.setData(DatasetFactory.zeros(FloatDataset.class, shape));
			if (hasTimeOfFlight) {
				detector.setTime_of_flight(DatasetFactory.zeros(FloatDataset.class, shape[shape.length - 1]));
			}
			
			return detector;
		}
		
	}

	public static class TestPositioner extends AbstractNexusObjectProvider<NXpositioner> {
		
		private final int[] shape;
		
		public TestPositioner(String name, int... shape) {
			super(name, NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
			this.shape = shape;
		}
		
		@Override
		protected NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.setValue(DatasetFactory.zeros(FloatDataset.class, shape));
			return positioner;
		}
		
	}
	
	public static class PolarAnglePositioner extends AbstractNexusObjectProvider<NXpositioner> {
		
		private final int dimensionIndex; 
		
		private final int[] scanShape;
		
		public PolarAnglePositioner(String name, int dimensionIndex, int[] scanShape) {
			super(name, NexusBaseClass.NX_POSITIONER);
			this.dimensionIndex = dimensionIndex;
			this.scanShape = scanShape;
			setAxisDataFieldNames("rbv", "demand");
			setDefaultAxisDataFieldName("demand");
		}
		
		@Override
		protected NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.setField("rbv", DatasetFactory.zeros(FloatDataset.class, scanShape));
			positioner.setField("demand", DatasetFactory.zeros(FloatDataset.class, scanShape[dimensionIndex]));
			return positioner;
		}
		
	}
	
	private NexusDataBuilder dataBuilder;
	
	private NXroot nxRoot;
	
	private NXdata nxData;

	private NexusEntryBuilder entryBuilder;
	
	@Before
	public void setUp() throws Exception {
		NexusFileBuilder fileBuilder = new DefaultNexusFileBuilder("test");
		nxRoot = fileBuilder.getNXroot();
		entryBuilder = fileBuilder.newEntry();
		entryBuilder.addDefaultGroups();
		dataBuilder = entryBuilder.createDefaultData();
		nxData = dataBuilder.getNxData();
	}
	
	private void addToEntry(NexusObjectProvider<?>... nexusObjects) throws NexusException {
		entryBuilder.addAll(Arrays.asList(nexusObjects));
	}
	
	@Test
	public void testExample1() throws NexusException {
		TestDetector detector = new TestDetector("data", 100);
		TestPositioner positioner = new TestPositioner("x", 100);
		
		addToEntry(detector, positioner);
		dataBuilder.setPrimaryDevice(detector);
		dataBuilder.addAxisDevice(positioner, 0);
		
		assertSignal(nxData, "data");
		assertAxes(nxData, "x");
		
		assertShape(nxData, "data", 100);
		assertTarget(nxData, "data", nxRoot, "/entry/instrument/data/data");

		assertShape(nxData, "x", 100);
		assertIndices(nxData, "x", 0);
		assertTarget(nxData, "x", nxRoot, "/entry/instrument/x/value");
	}
	
	@Test
	public void testExample2() throws NexusException {
		TestDetector mainDetector = new TestDetector("data", 1000, 20);
		TestDetector pressureDet = new TestDetector("pressure", 20);
		TestDetector temperatureDet = new TestDetector("temperature", 20);
		TestDetector timeDet = new TestDetector("time", 1000);
		
		addToEntry(mainDetector, pressureDet, temperatureDet, timeDet);
		dataBuilder.setPrimaryDevice(mainDetector);
		dataBuilder.addAxisDevice(pressureDet, 1);
		dataBuilder.addAxisDevice(temperatureDet, null, 1);
		dataBuilder.addAxisDevice(timeDet, 0);
		
		assertSignal(nxData, "data");
		assertAxes(nxData, "time", "pressure");
		
		assertShape(nxData, "data", 1000, 20);
		assertTarget(nxData, "data", nxRoot, "/entry/instrument/data/data");
		
		assertShape(nxData, "pressure", 20);
		assertIndices(nxData, "pressure", 1);
		assertTarget(nxData, "pressure", nxRoot, "/entry/instrument/pressure/data");
		
		assertShape(nxData, "temperature", 20);
		assertIndices(nxData, "temperature", 1);
		assertTarget(nxData, "temperature", nxRoot, "/entry/instrument/temperature/data");
		
		assertShape(nxData, "time", 1000);
		assertIndices(nxData, "time", 0);
		assertTarget(nxData, "time", nxRoot, "/entry/instrument/time/data");
	}
	
	/**
	 * @throws NexusException
	 */
	@Test
	public void testExample3() throws NexusException {
		TestDetector mainDetector = new TestDetector("det", true, 100, 100000);
		TestDetector pressureDetector = new TestDetector("pressure", 100);
		TestDetector tofDetector = new TestDetector("tof", 100000);
		
		addToEntry(mainDetector, pressureDetector, tofDetector);
		dataBuilder.setPrimaryDevice(mainDetector);
		dataBuilder.addAxisDevice(pressureDetector, 0);
		dataBuilder.addAxisDevice(tofDetector, 1);
		
		assertSignal(nxData, "det");
		assertAxes(nxData, "pressure", "tof");
		assertShape(nxData, "det", 100, 100000);
		assertTarget(nxData, "det", nxRoot, "/entry/instrument/det/data");
		
		assertShape(nxData, "pressure", 100);
		assertIndices(nxData, "pressure", 0);
		assertTarget(nxData, "pressure", nxRoot, "/entry/instrument/pressure/data");
		
		assertShape(nxData, "tof", 100000);
		assertIndices(nxData, "tof", 1);
		assertTarget(nxData, "tof", nxRoot, "/entry/instrument/tof/data");
	}

	@Test
	public void testExample4() throws NexusException {
		// note, wiki page example has 100x512x100000 but this is too large to allocate
		TestDetector mainDetector = new TestDetector("det", 100, 512, 1000);
		TestDetector tofDetector = new TestDetector("tof", 1000);
		TestPositioner xPositioner = new TestPositioner("x", 100, 512);
		TestPositioner yPositioner = new TestPositioner("y", 100, 512);

		addToEntry(mainDetector, tofDetector, xPositioner, yPositioner);
		dataBuilder.setPrimaryDevice(DataDeviceBuilder.newPrimaryDataDevice(mainDetector));
		dataBuilder.addAxisDevice(tofDetector, 2);
		dataBuilder.addAxisDevice(xPositioner, 0, 0, 1);
		dataBuilder.addAxisDevice(yPositioner, 1, 0, 1);
		
		assertSignal(nxData, "det");
		assertAxes(nxData, "x", "y", "tof");
		assertShape(nxData, "det", 100, 512, 1000);
		assertTarget(nxData, "det", nxRoot, "/entry/instrument/det/data");
		
		assertIndices(nxData, "x", 0, 1);
		assertShape(nxData, "x", 100, 512);
		assertTarget(nxData, "x", nxRoot, "/entry/instrument/x/value");
		
		assertIndices(nxData, "y", 0, 1);
		assertShape(nxData, "y", 100, 512);
		assertTarget(nxData, "y", nxRoot, "/entry/instrument/y/value");
	}
	
	@Test
	public void testExample5() throws NexusException {
		TestDetector mainDetector = new TestDetector("det1", 50, 5, 1024);
		PolarAnglePositioner polarAnglePositioner = new PolarAnglePositioner(
				"polar_angle", 0, new int[] { 50, 5 });
		TestPositioner frameNumberPositioner = new TestPositioner("frame_number", 5);
		TestPositioner timePositioner = new TestPositioner("time", 50, 5);

		addToEntry(mainDetector, polarAnglePositioner, frameNumberPositioner, timePositioner);
		dataBuilder.setPrimaryDevice(DataDeviceBuilder.newPrimaryDataDevice(mainDetector));
		dataBuilder.addAxisDevice(polarAnglePositioner, 0, 0, 1);
		dataBuilder.addAxisDevice(frameNumberPositioner, 1);
		dataBuilder.addAxisDevice(timePositioner, null, 0, 1);
		
		assertSignal(nxData, "det1");
		assertAxes(nxData, "polar_angle_demand", "frame_number", ".");
		assertShape(nxData, "det1", 50, 5, 1024);
		assertTarget(nxData, "det1", nxRoot, "/entry/instrument/det1/data");
		
		assertIndices(nxData, "polar_angle_demand", 0);
		assertShape(nxData, "polar_angle_demand", 50);
		assertTarget(nxData, "polar_angle_demand", nxRoot, "/entry/instrument/polar_angle/demand");
		assertIndices(nxData, "polar_angle_rbv", 0, 1);
		assertShape(nxData, "polar_angle_rbv", 50, 5);
		assertTarget(nxData, "polar_angle_rbv", nxRoot, "/entry/instrument/polar_angle/rbv");
		
		assertIndices(nxData, "frame_number", 1);
		assertShape(nxData, "frame_number", 5);
		assertTarget(nxData, "frame_number", nxRoot, "/entry/instrument/frame_number/value");
		
		assertIndices(nxData, "time", 0, 1);
		assertShape(nxData, "time", 50, 5);
		assertTarget(nxData, "time", nxRoot, "/entry/instrument/time/value");
	}

}
