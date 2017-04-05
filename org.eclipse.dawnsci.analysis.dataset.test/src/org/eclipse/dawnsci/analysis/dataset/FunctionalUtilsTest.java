package org.eclipse.dawnsci.analysis.dataset;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.eclipse.january.dataset.ByteDataset;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.FloatDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.LongDataset;
import org.eclipse.january.dataset.Random;
import org.eclipse.january.dataset.ShortDataset;
import org.eclipse.january.dataset.Slice;
import org.junit.Test;

public class FunctionalUtilsTest {

	@SuppressWarnings("unchecked")
	private static final Class <? extends Dataset>[] allDS = new Class[] {
			ByteDataset.class,
			ShortDataset.class,
			IntegerDataset.class,
			LongDataset.class,
			FloatDataset.class,
			DoubleDataset.class,
	};

	private static final double TOLERANCE = 1e-15;

	@Test
	public void testDatasetIndexFunction() {
		Dataset a = DatasetFactory.createRange(144).reshape(12, -1);
		generateAndCheckFunction(a);
		generateAndCheckFunction(a.getTransposedView());

		Dataset v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		generateAndCheckFunction(v);
		generateAndCheckFunction(v.getTransposedView());

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		assertEquals(0, z.getSize());
		generateAndCheckFunction(z);
		generateAndCheckFunction(z.getTransposedView());
	}

	private int[] generateIndexes(Dataset v) {
		int size = v.getSize();
		int[] eIndex = new int[size];
		IndexIterator it = v.getIterator();
		for (int i = 0; it.hasNext() || i < size ; i++) { // unnecessary check (is ORed) to keep SonarQube happy
			eIndex[i] = it.index;
		}
		return eIndex;
	}

	private void generateAndCheckFunction(Dataset v) {
		int[] eIndex = generateIndexes(v);
		int[] aIndex = IntStream.range(0, v.getSize()).map(Datasets.createDatasetIndexFunction(v)).toArray();

		assertArrayEquals(eIndex, aIndex);
	}

	@Test
	public void testDatasetIndexStream() {
		testDatasetIndexStream(false);
		testDatasetIndexStream(true);
	}

	private void testDatasetIndexStream(boolean parallel) {
		Dataset a = DatasetFactory.createRange(144).reshape(12, -1);
		generateAndCheckStream(a, parallel);
		generateAndCheckStream(a.getTransposedView(), parallel);

		Dataset v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		generateAndCheckStream(v, parallel);
		generateAndCheckStream(v.getTransposedView(), parallel);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		generateAndCheckStream(z, parallel);
		generateAndCheckStream(z.getTransposedView(), parallel);
	}

	private void generateAndCheckStream(Dataset v, boolean parallel) {
		int[] eIndex = generateIndexes(v);
		int[] aIndex = Datasets.createDatasetIndexStream(v, parallel).toArray();

		assertArrayEquals(eIndex, aIndex);
	}

	private void testDatasetIntStream(Dataset a, boolean parallel) {
		int[] eArray = a.cast(IntegerDataset.class).getData();
		int[] aArray = Datasets.createDatasetIntStream(a, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		Dataset v = a.getTransposedView();
		eArray = v.copy(IntegerDataset.class).getData();
		aArray = Datasets.createDatasetIntStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		eArray = v.copy(IntegerDataset.class).getData();
		aArray = Datasets.createDatasetIntStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		v = v.getTransposedView();
		eArray = v.copy(IntegerDataset.class).getData();
		aArray = Datasets.createDatasetIntStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		eArray = z.copy(IntegerDataset.class).getData();
		aArray = Datasets.createDatasetIntStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		z = z.getTransposedView();
		eArray = z.copy(IntegerDataset.class).getData();
		aArray = Datasets.createDatasetIntStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
	}

	private void testDatasetLongStream(Dataset a, boolean parallel) {
		long[] eArray = a.cast(LongDataset.class).getData();
		long[] aArray = Datasets.createDatasetLongStream(a, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		Dataset v = a.getTransposedView();
		eArray = v.copy(LongDataset.class).getData();
		aArray = Datasets.createDatasetLongStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		eArray = v.copy(LongDataset.class).getData();
		aArray = Datasets.createDatasetLongStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		v = v.getTransposedView();
		eArray = v.copy(LongDataset.class).getData();
		aArray = Datasets.createDatasetLongStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		eArray = z.copy(LongDataset.class).getData();
		aArray = Datasets.createDatasetLongStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		z = z.getTransposedView();
		eArray = z.copy(LongDataset.class).getData();
		aArray = Datasets.createDatasetLongStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
	}

	private void testDatasetDoubleStream(Dataset a, boolean parallel) {
		double[] eArray = a.cast(DoubleDataset.class).getData();
		double[] aArray = Datasets.createDatasetDoubleStream(a, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
		Dataset v = a.getTransposedView();
		eArray = v.copy(DoubleDataset.class).getData();
		aArray = Datasets.createDatasetDoubleStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);

		v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		eArray = v.copy(DoubleDataset.class).getData();
		aArray = Datasets.createDatasetDoubleStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
		v = v.getTransposedView();
		eArray = v.copy(DoubleDataset.class).getData();
		aArray = Datasets.createDatasetDoubleStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		eArray = z.copy(DoubleDataset.class).getData();
		aArray = Datasets.createDatasetDoubleStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
		z = z.getTransposedView();
		eArray = z.copy(DoubleDataset.class).getData();
		aArray = Datasets.createDatasetDoubleStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
	}

	@Test
	public void testDatasetIntStream() {
		Dataset a = Random.randn(0., 5000., 12, 12);

		for (Class<? extends Dataset> c : allDS) {
			Dataset v = a.cast(c);
			testDatasetIntStream(v, false);
			testDatasetIntStream(v, true);
		}
	}

	@Test
	public void testDatasetLongStream() {
		Dataset a = Random.randn(0., 5000., 12, 12);

		for (Class<? extends Dataset> c : allDS) {
			Dataset v = a.cast(c);
			testDatasetLongStream(v, false);
			testDatasetLongStream(v, true);
		}
	}

	@Test
	public void testDatasetDoubleStream() {
		Dataset a = Random.randn(0., 5000., 12, 12);

		for (Class<? extends Dataset> c : allDS) {
			Dataset v = a.cast(c);
			testDatasetDoubleStream(v, false);
			testDatasetDoubleStream(v, true);
		}
	}
}
