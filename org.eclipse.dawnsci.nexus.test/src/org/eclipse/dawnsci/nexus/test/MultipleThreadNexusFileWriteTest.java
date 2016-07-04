package org.eclipse.dawnsci.nexus.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.IntegerDataset;
import org.eclipse.dawnsci.hdf5.HDF5FileFactory;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileFactoryHDF5;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.ServiceHolder;
import org.eclipse.dawnsci.nexus.TestUtils;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusScanFile;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusFileBuilder;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MultipleThreadNexusFileWriteTest {

	private static abstract class AbstractTestDevice<N extends NXobject>
		extends AbstractNexusObjectProvider<N>
		implements Callable<Boolean> {

		private int nextStepNumber = 0;

		private boolean initialized = false;

		private long stepTime;

		private int numSteps;

		private Exception exception = null;

		public AbstractTestDevice(String name, NexusBaseClass nexusBaseClass, String defaultDatasetName) {
			super(name, nexusBaseClass, defaultDatasetName);
		}

		public void initializeScan(final long stepTime, final int numSteps) {
			this.stepTime = stepTime;
			this.numSteps = numSteps;

			initialized = true;
		}

		protected void doStep() throws Exception {
			Thread.sleep(stepTime);
			writeNewData(nextStepNumber++);
		}

		protected abstract void writeNewData(int stepNumber) throws Exception;

		@Override
		public Boolean call() throws Exception {
			if (!initialized) {
				throw new IllegalStateException("device " + getName() + " not initialized");
			}

			try {
				for (int i = 0; i < numSteps; i++) {
					doStep();
				}
				return Boolean.TRUE;
			} catch (final Exception e) {
				System.err.println("Exception in device " + getName() + ": " + e.getMessage());
				e.printStackTrace();
				exception = e;
				return Boolean.FALSE;
			}
		}

		public Exception getException() {
			return exception;
		}
	}


	public static class TestDetector extends AbstractTestDevice<NXdetector> implements Callable<Boolean> {

		private final int numRows;
		private final int numColumns;

		public TestDetector(int rows, int columns) {
			super("detector", NexusBaseClass.NX_DETECTOR, NXdata.NX_DATA);
			this.numRows = rows;
			this.numColumns = columns;
		}

		@Override
		protected NXdetector createNexusObject() {
			final NXdetector detector = NexusNodeFactory.createNXdetector();
			final ILazyWriteableDataset dataset = detector.initializeLazyDataset(
					NXdetector.NX_DATA, 3, Dataset.INT32);
			dataset.setMaxShape(new int[] { ILazyWriteableDataset.UNLIMITED, numRows, numColumns });
			return detector;
		}

		@Override
		protected void writeNewData(int stepNumber) throws Exception {
			final ILazyWriteableDataset dataset = getWriteableDataset(NXdetector.NX_DATA);

			final int[] startPos = new int[] { stepNumber, 0, 0 };
			final int[] stopPos = new int[] { stepNumber + 1, numRows, numColumns };
			final IDataset newData = createNewData();

			dataset.setSlice(null, newData, startPos, stopPos, null);
		}

		private IDataset createNewData() {
			final IntegerDataset dataset = DatasetFactory.zeros(IntegerDataset.class, numColumns, numRows);
			final ThreadLocalRandom random = ThreadLocalRandom.current();
			for (int rowNum = 0; rowNum < numRows; rowNum++) {
				for (int columnNum = 0; columnNum < numColumns; columnNum++) {
					dataset.setItem(random.nextInt(), rowNum, columnNum);
				}
			}

			return dataset;
		}

	}

	public static class TestPositioner extends AbstractTestDevice<NXpositioner> {

		private double[] testData;

		public TestPositioner(String name) {
			super(name, NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}

		@Override
		protected NXpositioner createNexusObject() {
			final NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.initializeLazyDataset(NXpositioner.NX_VALUE, 1, Dataset.FLOAT64);
			return positioner;
		}

		@Override
		public void initializeScan(final long stepTime, final int numSteps) {
			final ThreadLocalRandom random = ThreadLocalRandom.current();
			testData = new double[numSteps];
			for (int i = 0; i < testData.length; i++) {
				testData[i] = random.nextDouble(0, Double.MAX_VALUE);
			}

			super.initializeScan(stepTime, numSteps);
		}

		@Override
		protected void writeNewData(int stepNumber) throws Exception {
			final ILazyWriteableDataset dataset = getWriteableDataset(NXpositioner.NX_VALUE);

			final int[] startPos = new int[] { stepNumber };
			final int[] stopPos = new int[] { stepNumber + 1 };
			final Dataset newData = DatasetFactory.createFromObject(testData[stepNumber]);

			dataset.setSlice(null, newData, startPos, stopPos, null);
		}

	}

	private static final int DETECTOR_ROWS = 1024;
	private static final int DETECTOR_COLUMNS = 1024;

	private static final String FILE_NAME = "positioners.nx5";

	private static String testScratchDirectoryName;

	private String filePath;

	private TestDetector detector;

	private List<TestPositioner> positioners;
	
	private NexusScanFile nexusScanFile;

	@Before
	public void setUp() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(getClass().getCanonicalName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
		ServiceHolder.setNexusFileFactory(new NexusFileFactoryHDF5());
		filePath = testScratchDirectoryName + FILE_NAME;
	}

	private List<TestPositioner> createPositioners(int numPositioners) {
		final List<TestPositioner> positioners = new ArrayList<>(numPositioners);
		for (int i = 0; i < numPositioners; i++) {
			positioners.add(new TestPositioner(String.format("pos%03d", i)));
		}

		return positioners;
	}

	private void createNexusFile(final int numPositioners) throws NexusException {
		NexusFileBuilder fileBuilder = new DefaultNexusFileBuilder(filePath);
		final NexusEntryBuilder entryBuilder = fileBuilder.newEntry();
		entryBuilder.addDefaultGroups();
		detector = new TestDetector(DETECTOR_ROWS, DETECTOR_COLUMNS);
		positioners = createPositioners(numPositioners);
		entryBuilder.add(detector);
		entryBuilder.addAll(positioners);

		final NexusDataBuilder dataBuilder = entryBuilder.createDefaultData();
		dataBuilder.setPrimaryDevice(detector);
		for (TestPositioner positioner : positioners) {
			dataBuilder.addAxisDevice(positioner);
		}
		
		nexusScanFile = fileBuilder.createFile();
	}

	private void initializeDevices(final long stepTime, final int numSteps) {
		detector.initializeScan(stepTime, numSteps);
		for (final TestPositioner positioner : positioners) {
			positioner.initializeScan(stepTime, numSteps);
		}
	}

	private void runThreads(int numPositioners, final int numSteps, long timeout) throws Exception {
		// run the devices
		final int numDevices = numPositioners + 1;
		final ExecutorService executors = Executors.newFixedThreadPool(numDevices);
		final List<AbstractTestDevice<?>> devices = new ArrayList<>(numDevices);
		devices.add(detector);
		devices.addAll(positioners);

		final List<Future<Boolean>> results = executors.invokeAll(devices, timeout, TimeUnit.MILLISECONDS);

		// check that all devices completed normally
		for (int i = 0; i < results.size(); i++) {
			final AbstractTestDevice<?> device = devices.get(i);
			final Future<Boolean> result = results.get(i);
			if (!result.isDone()) {
				// not actually possible as isDone true even if invokeAll times out
				throw new RuntimeException("Device " + device.getName() + " has not completed");
			}
			if (Boolean.FALSE.equals(result.get(0, TimeUnit.MILLISECONDS))) {
				if (device.getException() != null) {
					throw new RuntimeException("Device " + device.getName() + " threw an exception.",
							device.getException());
				}
			}
		}
	}

	private void checkFile(int numPositioners, int numSteps) throws NexusException {
		final TreeFile file = NexusTestUtils.loadNexusFile(filePath, true);
		final NXroot root = (NXroot) file.getGroupNode();
		final NXentry entry = root.getEntry();
		final NXinstrument instrument = entry.getInstrument();
		final Collection<NXpositioner> positioners = instrument.getAllPositioner().values();
		assertThat(positioners.size(), is(equalTo(numPositioners)));
		int[] expectedShape = { numSteps };
		for (final NXpositioner positioner : positioners) {
			final IDataset valueDataset = positioner.getValue();
			assertThat(valueDataset, is(notNullValue()));
			assertArrayEquals(expectedShape, valueDataset.getShape());
		}
		final NXdetector detector = instrument.getDetector();
		assertThat(detector, is(notNullValue()));
		final DataNode detectorData = detector.getDataNode(NXdetector.NX_DATA);
		assertThat(detectorData, is(notNullValue()));
		final ILazyDataset detectorDataset = detectorData.getDataset();
		assertThat(detectorDataset, is(notNullValue()));
		expectedShape = new int[] { numSteps, DETECTOR_ROWS, DETECTOR_COLUMNS };
		assertArrayEquals(expectedShape, detectorDataset.getShape());
	}

	public void doTestMultiplePositioners(final int numPositioners, final int numSteps, final long stepTime) throws Exception {
		createNexusFile(numPositioners);
		initializeDevices(stepTime, numSteps);
		nexusScanFile.openToWrite();
		final long timeout = (numSteps + 1) * stepTime * 2;
		runThreads(numPositioners, numSteps, timeout);
		nexusScanFile.close();
		checkFile(numPositioners, numSteps);
		HDF5FileFactory.releaseFile(filePath, true);
	}

	public void doTestNPositioners(final int numPositioners) throws Exception {
		doTestMultiplePositioners(numPositioners, 100, 100);
	}

	@Test
	public void test2Positioners() throws Exception {
		doTestNPositioners(2);
	}

	@Test
	public void test20Positioners() throws Exception {
		doTestNPositioners(20);
	}

	@Test
	public void test200Positioners() throws Exception {
		doTestNPositioners(200);
	}

	@Test
	@Ignore // this test times out most times
	public void test500Positioners() throws Exception {
		doTestNPositioners(500);
	}

}
