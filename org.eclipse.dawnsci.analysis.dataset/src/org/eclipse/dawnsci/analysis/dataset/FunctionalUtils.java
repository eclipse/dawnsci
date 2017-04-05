/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.dawnsci.analysis.dataset.slicer.SliceViewIterator;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.LongDataset;

/**
 * Provide static methods to use Datasets in functional ways 
 */
public class FunctionalUtils {

	private static final int FLAGS = Spliterator.SIZED | Spliterator.DISTINCT | Spliterator.CONCURRENT
			| Spliterator.IMMUTABLE | Spliterator.NONNULL;

	private FunctionalUtils() {
	}

	private static class DatasetIndexOperator implements IntUnaryOperator {

		private int[] shape;
		private int[] strides;
		private int offset;
		private int rank;

		public DatasetIndexOperator(Dataset a) {
			shape = a.getShape();
			rank = shape.length;
			strides = a.getStrides().clone();
			offset = a.getOffset();
		}

		@Override
		public int applyAsInt(int value) {
			int v = value;
			int index = offset;
			for (int i = rank-1; v > 0 && i >= 0; i--) {
				int s = shape[i];
				int p = v % s;
				if (p != 0) {
					index += p * strides[i];
				}
				v /= s;
			}
			return index;
		}
	}

	/**
	 * Create a function to map an ordinal to the actual index used in a view of
	 * a dataset
	 * @param a dataset
	 * @return function
	 */
	public static IntUnaryOperator createDatasetIndexFunction(Dataset a) {
		if (a.getStrides() == null) {
			return i -> i;
		}
		return new DatasetIndexOperator(a);
	}

	private static class IndexSpliterator extends Spliterators.AbstractIntSpliterator {

		private boolean gotNext;
		private final IndexIterator it;

		public IndexSpliterator(Dataset a) {
			super(a.getSize(), FLAGS);
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(IntConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept(it.index);
			}

			return gotNext;
		}
	}

	private static class DatasetIntSpliterator extends Spliterators.AbstractIntSpliterator {
		private boolean gotNext;
		private final IndexIterator it;
		private final Dataset data;

		public DatasetIntSpliterator(Dataset a) {
			super(a.getSize(), FLAGS);
			data = a;
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(IntConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept((int) data.getElementLongAbs(it.index));
			}

			return gotNext;
		}
	}

	private static class DatasetLongSpliterator extends Spliterators.AbstractLongSpliterator {
		private boolean gotNext;
		private final IndexIterator it;
		private final Dataset data;

		public DatasetLongSpliterator(Dataset a) {
			super(a.getSize(), FLAGS);
			data = a;
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(LongConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept(data.getElementLongAbs(it.index));
			}

			return gotNext;
		}
	}

	private static class DatasetDoubleSpliterator extends Spliterators.AbstractDoubleSpliterator {
	
		private boolean gotNext;
		private final IndexIterator it;
		private final Dataset data;

		public DatasetDoubleSpliterator(Dataset a) {
			super(a.getSize(), FLAGS);
			data = a;
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(DoubleConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept(data.getElementDoubleAbs(it.index));
			}

			return gotNext;
		}
	}

	private static class DoubleDatasetSpliterator extends Spliterators.AbstractDoubleSpliterator {
		private boolean gotNext;
		private final IndexIterator it;
		private final DoubleDataset data;

		public DoubleDatasetSpliterator(DoubleDataset a) {
			super(a.getSize(), FLAGS);
			data = a;
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(DoubleConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept(data.getAbs(it.index));
			}

			return gotNext;
		}
	}

	private static class IntegerDatasetSpliterator extends Spliterators.AbstractIntSpliterator {
		private boolean gotNext;
		private final IndexIterator it;
		private final IntegerDataset data;

		public IntegerDatasetSpliterator(IntegerDataset a) {
			super(a.getSize(), FLAGS);
			data = a;
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(IntConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept(data.getAbs(it.index));
			}

			return gotNext;
		}
	}

	private static class LongDatasetSpliterator extends Spliterators.AbstractLongSpliterator {
		private boolean gotNext;
		private final IndexIterator it;
		private final LongDataset data;

		public LongDatasetSpliterator(LongDataset a) {
			super(a.getSize(), FLAGS);
			data = a;
			gotNext = a.getSize() > 0;
			it = a.getIterator();
		}

		@Override
		public boolean tryAdvance(LongConsumer action) {
			if (action == null) {
				throw new NullPointerException();
			}

			gotNext = it.hasNext();
			if (gotNext) {
				action.accept(data.getAbs(it.index));
			}

			return gotNext;
		}
	}
	
	private static class LazyDatasetSpliterator extends Spliterators.AbstractSpliterator<ILazyDataset> {

		private final SliceViewIterator it;
		
		public LazyDatasetSpliterator(SliceViewIterator it){
			super(it.getTotal(),FLAGS);
			this.it = it;
		}

		@Override
		public boolean tryAdvance(Consumer<? super ILazyDataset> action) {
			if (action == null) {
				throw new NullPointerException();
			}

			boolean hasNext = it.hasNext();
			if (hasNext) {
				action.accept(it.next());
			}

			return hasNext;
		}
	}
	
	private static class DatasetSpliterator extends Spliterators.AbstractSpliterator<IDataset> {

		private final SliceViewIterator it;
		
		public DatasetSpliterator(SliceViewIterator it){
			super(it.getTotal(),FLAGS);
			this.it = it;
		}

		@Override
		public boolean tryAdvance(Consumer<? super IDataset> action) {
			if (action == null) {
				throw new NullPointerException();
			}

			boolean hasNext = it.hasNext();
			if (hasNext) {
				try {
					action.accept(it.next().getSlice());
				} catch (DatasetException e) {
					throw new RuntimeException("Could not slice dataset",e);
				}
			}

			return hasNext;
		}
	}

	/**
	 * Create a stream of index for iterating through a dataset
	 * @param a dataset
	 * @return index stream
	 */
	
	public static IntStream createDatasetIndexStream(Dataset a, boolean parallel) {
		return StreamSupport.intStream(new IndexSpliterator(a), parallel);
	}

	/**
	 * Create a stream of long values from iterating through a dataset
	 * @param a dataset
	 * @return long stream
	 */
	public static IntStream createDatasetStream(IntegerDataset a, boolean parallel) {
		if (a.getStrides() == null) {
			StreamSupport.intStream(Spliterators.spliterator(a.getData(), FLAGS), parallel);
		}
		return StreamSupport.intStream(new IntegerDatasetSpliterator(a), parallel);
	}

	/**
	 * Create a stream of long values from iterating through a dataset
	 * @param a dataset
	 * @return long stream
	 */
	public static LongStream createDatasetStream(LongDataset a, boolean parallel) {
		if (a.getStrides() == null) {
			StreamSupport.longStream(Spliterators.spliterator(a.getData(), FLAGS), parallel);
		}
		return StreamSupport.longStream(new LongDatasetSpliterator(a), parallel);
	}

	/**
	 * Create a stream of double values from iterating through a dataset
	 * @param a dataset
	 * @return double stream
	 */
	public static DoubleStream createDatasetStream(DoubleDataset a, boolean parallel) {
		if (a.getStrides() == null) {
			StreamSupport.doubleStream(Spliterators.spliterator(a.getData(), FLAGS), parallel);
		}
		return StreamSupport.doubleStream(new DoubleDatasetSpliterator(a), parallel);
	}

	/**
	 * Create a stream of int values from iterating through a dataset
	 * @param a dataset
	 * @return int stream
	 */
	public static IntStream createDatasetIntStream(Dataset a, boolean parallel) {
		if (a instanceof IntegerDataset) {
			return createDatasetStream((IntegerDataset) a, parallel);
		}
		return StreamSupport.intStream(new DatasetIntSpliterator(a), parallel);
	}

	/**
	 * Create a stream of long values from iterating through a dataset
	 * @param a dataset
	 * @return long stream
	 */
	public static LongStream createDatasetLongStream(Dataset a, boolean parallel) {
		if (a instanceof LongDataset) {
			return createDatasetStream((LongDataset) a, parallel);
		}
		return StreamSupport.longStream(new DatasetLongSpliterator(a), parallel);
	}

	/**
	 * Create a stream of double values from iterating through a dataset
	 * @param a dataset
	 * @return double stream
	 */
	public static DoubleStream createDatasetDoubleStream(Dataset a, boolean parallel) {
		if (a instanceof DoubleDataset) {
			return createDatasetStream((DoubleDataset) a, parallel);
		}
		return StreamSupport.doubleStream(new DatasetDoubleSpliterator(a), parallel);
	}
	
	/**
	 * Create a stream of ILazyDatasets from iterating through an ILazyDataset, omiting some axes
	 * 
	 * @param a - ILazyDataset
	 * @param omit - dimensions to omit from iteration (e.g. dimensions of image in a stack)
	 * @param parallel
	 * @return stream
	 */
	public static Stream<ILazyDataset> createLazyDatasetStream(ILazyDataset a, int[] omit, boolean parallel) {
		SliceViewIterator it = new SliceViewIterator(a, null, omit);
		return StreamSupport.stream(new LazyDatasetSpliterator(it), parallel);
	}
	
	/**
	 * Create a stream of IDataset from iterating through an ILazyDataset, omiting some axes
	 * 
	 * @param a - ILazyDataset
	 * @param omit - dimensions to omit from iteration (e.g. dimensions of image in a stack)
	 * @param parallel
	 * @return stream
	 */
	public static Stream<IDataset> createDatasetStream(ILazyDataset a, int[] omit, boolean parallel) {
		SliceViewIterator it = new SliceViewIterator(a, null, omit);
		return StreamSupport.stream(new DatasetSpliterator(it), parallel);
	}
}
