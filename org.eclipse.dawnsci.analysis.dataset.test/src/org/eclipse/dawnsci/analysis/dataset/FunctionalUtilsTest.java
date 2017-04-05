package org.eclipse.dawnsci.analysis.dataset;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.ByteDataset;
import org.eclipse.january.dataset.Comparisons;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.FloatDataset;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.LongDataset;
import org.eclipse.january.dataset.Random;
import org.eclipse.january.dataset.ShortDataset;
import org.eclipse.january.dataset.Slice;
import org.junit.Assert;
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
		int[] aIndex = IntStream.range(0, v.getSize()).map(FunctionalUtils.createDatasetIndexFunction(v)).toArray();

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
		int[] aIndex = FunctionalUtils.createDatasetIndexStream(v, parallel).toArray();

		assertArrayEquals(eIndex, aIndex);
	}

	private void testDatasetIntStream(Dataset a, boolean parallel) {
		int[] eArray = a.cast(IntegerDataset.class).getData();
		int[] aArray = FunctionalUtils.createDatasetIntStream(a, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		Dataset v = a.getTransposedView();
		eArray = v.copy(IntegerDataset.class).getData();
		aArray = FunctionalUtils.createDatasetIntStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		eArray = v.copy(IntegerDataset.class).getData();
		aArray = FunctionalUtils.createDatasetIntStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		v = v.getTransposedView();
		eArray = v.copy(IntegerDataset.class).getData();
		aArray = FunctionalUtils.createDatasetIntStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		eArray = z.copy(IntegerDataset.class).getData();
		aArray = FunctionalUtils.createDatasetIntStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		z = z.getTransposedView();
		eArray = z.copy(IntegerDataset.class).getData();
		aArray = FunctionalUtils.createDatasetIntStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
	}

	private void testDatasetLongStream(Dataset a, boolean parallel) {
		long[] eArray = a.cast(LongDataset.class).getData();
		long[] aArray = FunctionalUtils.createDatasetLongStream(a, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		Dataset v = a.getTransposedView();
		eArray = v.copy(LongDataset.class).getData();
		aArray = FunctionalUtils.createDatasetLongStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		eArray = v.copy(LongDataset.class).getData();
		aArray = FunctionalUtils.createDatasetLongStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		v = v.getTransposedView();
		eArray = v.copy(LongDataset.class).getData();
		aArray = FunctionalUtils.createDatasetLongStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		eArray = z.copy(LongDataset.class).getData();
		aArray = FunctionalUtils.createDatasetLongStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
		z = z.getTransposedView();
		eArray = z.copy(LongDataset.class).getData();
		aArray = FunctionalUtils.createDatasetLongStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray);
	}

	private void testDatasetDoubleStream(Dataset a, boolean parallel) {
		double[] eArray = a.cast(DoubleDataset.class).getData();
		double[] aArray = FunctionalUtils.createDatasetDoubleStream(a, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
		Dataset v = a.getTransposedView();
		eArray = v.copy(DoubleDataset.class).getData();
		aArray = FunctionalUtils.createDatasetDoubleStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);

		v = a.getSliceView(new Slice(3, 10), new Slice(null, null, -2));
		eArray = v.copy(DoubleDataset.class).getData();
		aArray = FunctionalUtils.createDatasetDoubleStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
		v = v.getTransposedView();
		eArray = v.copy(DoubleDataset.class).getData();
		aArray = FunctionalUtils.createDatasetDoubleStream(v, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);

		Dataset z = a.getSliceView(new Slice(3, 3), new Slice(1, 1));
		eArray = z.copy(DoubleDataset.class).getData();
		aArray = FunctionalUtils.createDatasetDoubleStream(z, parallel).toArray();
		assertArrayEquals(eArray, aArray, TOLERANCE);
		z = z.getTransposedView();
		eArray = z.copy(DoubleDataset.class).getData();
		aArray = FunctionalUtils.createDatasetDoubleStream(z, parallel).toArray();
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
	
	@Test
	public void testLazyDatasetStream() {
		Dataset original = DatasetFactory.createRange(DoubleDataset.class,10);
		Dataset a = original.getSlice().reshape(new int[]{1,1,10});
		a = DatasetUtils.tile(a, new int[]{4,5,1});
		Stream<ILazyDataset> lzStream = FunctionalUtils.createLazyDatasetStream(a, new int[]{0, 1}, false);
		doTest(lzStream.map(d -> safeSlice(d)), original);
	}
	
	@Test
	public void testDatasetStream() {
		Dataset original = DatasetFactory.createRange(DoubleDataset.class,10);
		Dataset a = original.getSlice().reshape(new int[]{1,1,10});
		a = DatasetUtils.tile(a, new int[]{4,5,1});
		Stream<IDataset> lzStream = FunctionalUtils.createDatasetStream(a, new int[]{0, 1}, false);
		doTest(lzStream,original);
	}
	
	private void doTest(Stream<IDataset> stream, Dataset original) {
		List<Double> collect = stream.map(d -> d.max().doubleValue()).collect(Collectors.toList());
		Dataset d = DatasetFactory.createFromList(collect);
		Assert.assertTrue(Comparisons.equalTo(original, d).all());
	}
	
	private static IDataset safeSlice(ILazyDataset lz) {
		try {
			return lz.getSlice();
		} catch (DatasetException e) {
			throw new RuntimeException("Couldn't slice data");
		}
	}
}
