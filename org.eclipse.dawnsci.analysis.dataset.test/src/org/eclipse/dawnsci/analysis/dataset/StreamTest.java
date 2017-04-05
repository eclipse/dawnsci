/*-
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Comparisons;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see the full slicing unit tests in plugin    uk.ac.diamond.scisoft.analysis.test
 *                                     package   uk/ac/diamond/scisoft/analysis/dataset
 * @author Matthew Gerring
 *
 */
public class StreamTest {

	@Test
	public void testLazyDatasetStream() {
		Dataset original = DatasetFactory.createRange(DoubleDataset.class,10);
		Dataset a = original.getSlice().reshape(new int[]{1,1,10});
		a = DatasetUtils.tile(a, new int[]{4,5,1});
		Stream<ILazyDataset> lzStream = Slices.stream(a, false, 0, 1);
		doTest(lzStream.map(d -> safeSlice(d)), original);
	}

	@Test
	public void testDatasetStream() {
		Dataset original = DatasetFactory.createRange(DoubleDataset.class,10);
		Dataset a = original.getSlice().reshape(new int[]{1,1,10});
		a = DatasetUtils.tile(a, new int[]{4,5,1});
		Stream<IDataset> lzStream = Slices.sliceStream(a, false, 0, 1);
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

	@Test
	public void iterateImagesNDStream() throws DatasetException {
		
		final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
		
		// That is more like it!
		Slices.stream(lz, 2,3).forEach(image -> assertTrue(Arrays.equals(new int[]{1,1,100, 100}, image.getShape())));
	}

	@Test
	public void iterateImageSumStream1() throws DatasetException {
		
		final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
		
		List<Number> maxes = Slices.sliceStream(lz, 2, 3).map(set->set.max()).collect(Collectors.toList());
		assertEquals(64*64, maxes.size());
	}

	@Test
	public void iterateImageSumStream2() throws DatasetException {
		
		final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
		
		List<Number> maxes = Slices.sliceStream(lz, 0, 1).map(set->set.max()).collect(Collectors.toList());
		assertEquals(100*100, maxes.size());
	}

	@Test
	public void iterateImageSumStream1DotParallel() throws DatasetException {
		
		final ILazyDataset lz = DatasetFactory.ones(64, 64, 100, 100);		
		
		List<Number> maxes = Slices.sliceStream(lz, 2, 3).parallel().map(set->set.max()).collect(Collectors.toList());
		assertEquals(64*64, maxes.size());
	}

	@Test
	public void iterateImageSumStream2DotParallel() throws DatasetException {
		
		final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
		
		List<Number> maxes = Slices.sliceStream(lz, 0, 1).parallel().map(set->set.max()).collect(Collectors.toList());
		assertEquals(100*100, maxes.size());
	}


	@Test
	public void iterateImageSumStream1BooleanParallel() throws DatasetException {
		
		final ILazyDataset lz = DatasetFactory.ones(64, 64, 100, 100);		
		
		List<Number> maxes = Slices.sliceStream(lz, true, 2, 3).map(set->set.max()).collect(Collectors.toList());
		assertEquals(64*64, maxes.size());
	}

	@Test
	public void iterateImageSumStream2BooleanParallel() throws DatasetException {
		
		final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
		
		List<Number> maxes = Slices.sliceStream(lz, true, 0, 1).map(set->set.max()).collect(Collectors.toList());
		assertEquals(100*100, maxes.size());
	}

	@Test
	public void iterateImagesStreamMaxImage() throws DatasetException {
		
		final ILazyDataset lz = Random.lazyRand(64, 64, 100, 100);		
		
		Dataset sum = DatasetFactory.zeros(1,1,100, 100);
		Slices.sliceStream(lz, 2, 3).forEach(image -> sum.iadd(image));
		
		IntStream.range(0, sum.getSize()).forEach(i -> assertTrue("The sum is "+sum.getElementDoubleAbs(i), sum.getElementDoubleAbs(i)>1000));
	}

}
