/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5.nexus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;
import org.eclipse.january.dataset.Random;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;
import org.eclipse.january.dataset.SliceNDIterator;
import org.eclipse.january.dataset.Stats;
import org.junit.Test;

/**
 * Simulate a spectroscopy data collection
 * 
 * Scan [s0, s1] (2D) positioners
 * MCA [s0, s1, number of elements, number of channels]
 * MCA sum of elements [s0, s1, number of channels]
 * ROI scannables [s0, s1] for each ROI
 */
public class NexusFileBenchmarkTest {

	private final static String FILE_NAME = "test-scratch/nexusbenchmark.nxs";
	private final static String DETECTOR_LOCATION = "/entry1/data%02d/";
	private final static String POSN_LOCATION = "pos%01d";
	private final static String ROI_LOCATION = "roi%02d";

	class Detector {
		ILazyWriteableDataset data;
		ILazyWriteableDataset[] posn;
		ILazyWriteableDataset[] roi;

		Dataset  dd;
		Dataset[] pd;
		Dataset[] rd;

		public Detector(int p, int r) {
			posn = new ILazyWriteableDataset[p];
			roi = new ILazyWriteableDataset[r];
			pd = new Dataset[p];
			rd = new Dataset[r];
		}

		private static final int ROI_SLICE_LENGTH = 40;

		public void createData(int[] detectorShapeInScan, int[] scanShapeInScan) {
			dd = Random.randn(detectorShapeInScan);
			int r = dd.getRank();
			Slice[] slice = new Slice[r];

			// randomly select start positions of slices
			IntegerDataset start = Random.randint(0, detectorShapeInScan[r - 1] - ROI_SLICE_LENGTH, new int[] {roi.length});
			for (int i = 0; i < roi.length; i++) {
				// sum a slice along last dimension of detector data
				int s = start.get(i);
				slice[slice.length - 1] = new Slice(s, s + ROI_SLICE_LENGTH);
				rd[i] = dd.getSlice(slice).sum(r - 1);
			}

			r = scanShapeInScan.length;
			int[] shape = new int[r];
			Arrays.fill(shape, 1);
			for (int i = 0; i < posn.length; i++) {
				pd[i] = Random.randn(shape);
			}
		}

		public void writeData(SliceND dslice, SliceND rslice, SliceND pslice) {
			try {
				data.setSlice(null, dd, dslice);
				
				for (int i = 0; i < roi.length; i++) {
					roi[i].setSlice(null, rd[i], rslice);
				}

				for (int i = 0; i < posn.length; i++) {
					posn[i].setSlice(null, pd[i], pslice);
				}
			} catch (DatasetException e) {
				e.printStackTrace();
			}
		}
	}

	private Detector[] detector;
	private int[] totalShape;
	private int[] detectorShape;
	int scanRank;

	@Test
	public void testBenchmark() throws Throwable {
		int detectors = 1;

		int[] scanShape = new int[] {12, 48};
		detectorShape = new int[] {10, 4096};
		scanRank = scanShape.length;
		int detectorRank = detectorShape.length;
		totalShape = new int[scanRank + detectorRank];
		System.arraycopy(scanShape, 0, totalShape, 0, scanRank);
		System.arraycopy(detectorShape, 0, totalShape, scanRank, detectorRank);

		int rois = 12;

		detector = prepareFile(detectors, rois);

		doTestOfDataSet(0);
	}

	private Detector[] prepareFile(int detectors, int rois) throws NexusException {

		Detector[] detector = new Detector[detectors];
		int dRank = detectorShape.length;

		int[] tShape = new int[scanRank + dRank];
		Arrays.fill(tShape, ILazyWriteableDataset.UNLIMITED);
		System.arraycopy(detectorShape, 0, tShape, scanRank, dRank);

		int[] pShape = new int[scanRank];
		Arrays.fill(pShape, ILazyWriteableDataset.UNLIMITED);

		int[] rShape = new int[scanRank + dRank - 1];
		Arrays.fill(rShape, ILazyWriteableDataset.UNLIMITED);
		System.arraycopy(detectorShape, 0, tShape, scanRank, dRank - 1);

		try (NexusFile nf = new NexusFileHDF5(FILE_NAME)) {
			nf.createAndOpenToWrite();

			for (int d = 0; d < detectors; d++) {
				Detector dt = new Detector(scanRank, rois);
				detector[d] = dt;

				String dg = String.format(DETECTOR_LOCATION, d);
				ILazyWriteableDataset lds = new LazyWriteableDataset("data", Dataset.FLOAT64, tShape, null, null, null);
				dt.data = lds;
				nf.createData(dg + "data", lds, true);

				for (int p = 0; p < scanRank; p++) {
					String pd = String.format(POSN_LOCATION, p);
					lds = new LazyWriteableDataset(pd, Dataset.FLOAT64, pShape, null, null, null);
					dt.posn[p] = lds;
					nf.createData(dg + pd, lds, true);
				}

				for (int r = 0; r < rois; r++) {
					String rd = String.format(ROI_LOCATION, r);
					lds = new LazyWriteableDataset(rd, Dataset.FLOAT64, rShape, null, null, null);
					dt.roi[r] = lds;
					nf.createData(dg + rd, lds, true);
				}
			}
		}

		return detector;
	}

	/**
	 * For each detector
	 * per scan point
	 * write position
	 * generates and writes random detector data
	 * write roi data
	 * 
	 * @param d
	 * @throws Throwable
	 */
//	@Override
	protected void doTestOfDataSet(int d) throws Throwable {
		Detector dt = detector[d];
		
		SliceND slice = new SliceND(totalShape);
		int[] omit = new int[detectorShape.length];
		for (int i = 0; i < omit.length; i++) {
			omit[i] = scanRank + i;
		}

		SliceNDIterator it = new SliceNDIterator(slice, omit);
		SliceND dslice = it.getOutputSlice(); // detector
		SliceND pslice = it.getUsedSlice(); // scan
		int r = totalShape.length - 1; // roi rank
		SliceND rslice = new SliceND(Arrays.copyOfRange(totalShape, 0, r));
		List<Long> dtime = new ArrayList<Long>();
		List<Long> wtime = new ArrayList<Long>();
		while (it.hasNext()) {
			long now;
			now = -System.nanoTime();
			dt.createData(dslice.getShape(), pslice.getSourceShape());
			now += System.nanoTime();
			dtime.add(now/1000l);
//			System.err.printf("Data creation took %7.3fms\n", now*1e-6);

			for (int i = 0; i < r; i++) {
				rslice.setSlice(i, dslice.getStart()[i], dslice.getStop()[i], dslice.getStep()[i]);
			}

			now = -System.nanoTime();
			dt.writeData(dslice, rslice, pslice);
			now += System.nanoTime();
			wtime.add(now/1000l);
//			System.err.printf("Data writing took  %7.3fms\n", now*1e-6);
		}

		System.out.println("Creation stats in us:");
		List<Long> itime = printStats(dtime);
		System.out.printf("Outliers removed %5d of %5d\n", dtime.size() - itime.size(), dtime.size());
		printStats(itime);
		System.out.println();

		System.out.println("Writing  stats in us:");
		itime = printStats(wtime);
		System.out.printf("Outliers removed %5d of %5d\n", wtime.size() - itime.size(), wtime.size());
		printStats(itime);
	}

	public List<Long> printStats(List<Long> time) {
		Dataset d = DatasetFactory.createFromList(time);
		double med = (double) Stats.median(d);
		double iqr = (double) Stats.iqr(d);
		long min = (long) d.min();
		long max = (long) d.max();

		System.out.printf("Min is    %5d\n", min);
		System.out.printf("Median is %5d\n", (long) med);
		System.out.printf("Max is    %5d\n", max);
		System.out.printf("IQR is    %5d\n", (long) iqr);

		long limit = (long) (med + 2.5*iqr);
		List<Long> inliers = new ArrayList<>();
		for (long t : time) {
			if (t <= limit) {
				inliers.add(t);
			}
		}
		System.out.printf("Threshold %5d\n", limit);

		return inliers;
	}
}
