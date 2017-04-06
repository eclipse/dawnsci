/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jacob Filik - initial API and implementation and/or initial documentation
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 */
package org.eclipse.dawnsci.analysis.dataset;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.dawnsci.analysis.dataset.slicer.SliceViewIterator;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;

/**
 * This class provides slices as streams for Lazy Datasets for instance:
 * 
 * <pre>
 * <code>
final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
List<Number> maxes = SliceStreamSupport.sliceStream(lz, 2, 3).map(set->set.max()).collect(Collectors.toList());
  </code>
 *</pre> 
 * @see StreamSupport 
 */
public class SliceStreamSupport {

	private static final int FLAGS = Spliterator.SIZED | Spliterator.DISTINCT | Spliterator.CONCURRENT
			| Spliterator.IMMUTABLE | Spliterator.NONNULL;

	/**
	 * Create a dataset stream from a Lazy Dataset by specifying the axes that you
	 * would like to size over.
	 * 
	 * @param lazy
	 * @param omit
	 * @return a stream of lazy data
	 */
	public static Stream<ILazyDataset> stream(ILazyDataset lazy, int... omit) {
		return stream(lazy, false, omit);
	}
	
	/**
	 * 
	 * @param lazy
	 * @param omit
	 * @param parallel
	 * @return stream of lazy datasets
	 */
	public static Stream<ILazyDataset> stream(ILazyDataset lazy, boolean parallel, int... omit) {
		SliceViewIterator it = new SliceViewIterator(lazy, null, omit);
		return StreamSupport.stream(new LazyDatasetSpliterator(it), parallel);
	}

	/**
	 * Create a dataset stream from a Lazy Dataset by specifying the axes that you
	 * would like to size over.
	 * 
	 * @param lazy
	 * @param omit
	 * @return a stream of lazy data
	 */
	public static Stream<IDataset> sliceStream(ILazyDataset lazy, int... omit) {
		return sliceStream(lazy, false, omit);
	}
	
	/**
	 * 
	 * @param lazy
	 * @param omit
	 * @param parallel
	 * @return stream of lazy datasets
	 */
	public static Stream<IDataset> sliceStream(ILazyDataset lazy, boolean parallel, int... omit) {
		SliceViewIterator it = new SliceViewIterator(lazy, null, omit);
		return StreamSupport.stream(new DatasetSpliterator(it), parallel);
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
}
