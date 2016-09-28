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

import org.eclipse.dawnsci.analysis.api.worker.Worker;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
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
	private final static String OUTPUT_DIRECTORY = "test-scratch/";
	private static final String SYNCHRONOUS_FILE_NAME = OUTPUT_DIRECTORY + "syncbenchmark.nxs";
	private final static String SINGLE_THREAD_FILE_NAME = OUTPUT_DIRECTORY + "singlebenchmark.nxs";
	private final static String MULTIPLE_THREAD_FILE_NAME = OUTPUT_DIRECTORY + "multiplebenchmark.nxs";
	private final static String DETECTOR_LOCATION = "/entry1/data%02d/";
	private final static String POSN_LOCATION = "pos%01d";
	private final static String ROI_LOCATION = "roi%02d";

	private class WriteJob implements Runnable {
		private ILazyWriteableDataset out;
		private final IDataset data;
		private final SliceND slice;
		public WriteJob(final ILazyWriteableDataset out, final Dataset data, final SliceND slice) {
			this.out = out;
			this.data = data;
			this.slice = slice;
		}

		@Override
		public void run() {
			try {
				out.setSlice(null, data, slice);
			} catch (DatasetException e) {
				throw new RuntimeException(e);
			}
			
		}
	}


	private class Detector {
		private ILazyWriteableDataset data;
		private ILazyWriteableDataset[] posn;
		private ILazyWriteableDataset[] roi;

		private Dataset  dd;
		private Dataset[] pd;
		private Dataset[] rd;

		private final Worker[] thread;

		public Detector(int p, int r, Worker... thread) {
			posn = new ILazyWriteableDataset[p];
			roi = new ILazyWriteableDataset[r];
			pd = new Dataset[p];
			rd = new Dataset[r];
			this.thread = thread == null || thread.length == 0 ? null : thread;
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

		int nt = 0;
		public void writeAllData(SliceND dslice, SliceND rslice, SliceND pslice) {
			nt = 0;

			write(data, dd, dslice);

			for (int i = 0; i < roi.length; i++) {
				write(roi[i], rd[i], rslice);
			}

			for (int i = 0; i < posn.length; i++) {
				write(posn[i], pd[i], pslice);
			}
		}

		public void write(final ILazyWriteableDataset out, final Dataset data, final SliceND slice) {
			if (thread == null) {
				try {
					out.setSlice(null, data, slice);
				} catch (DatasetException e) {
					throw new RuntimeException(e);
				}
			} else {
				Worker t = thread[nt++];
				if (nt == thread.length) {
					nt = 0;
				}
				t.addJob(new WriteJob(out, data, slice));
			}
		}
	}

	private int[] totalShape;
	private int[] detectorShape;
	private int scanRank;


	public void setScanDetails(int[] scanShape, int[] detectorShape) {
		this.detectorShape = detectorShape;
		scanRank = scanShape.length;
		int detectorRank = detectorShape.length;
		totalShape = new int[scanRank + detectorRank];
		System.arraycopy(scanShape, 0, totalShape, 0, scanRank);
		System.arraycopy(detectorShape, 0, totalShape, scanRank, detectorRank);

		System.out.printf("Scan is %s and detector is %s\n", Arrays.toString(scanShape), Arrays.toString(detectorShape));
	}

	@Test
	public void benchmarkZeroThreads() throws Throwable {
		System.out.println("=== start synchronous ===");
		setScanDetails(new int[] {12, 48}, new int[] {10, 4096});
		benchmark(SYNCHRONOUS_FILE_NAME, 0, 1, 12);
		System.out.println("=== end synchronous ===\n");
	}

	@Test
	public void benchmarkSingleThread() throws Throwable {
		System.out.println("=== start single ===");
		setScanDetails(new int[] {12, 48}, new int[] {10, 4096});
		benchmark(SINGLE_THREAD_FILE_NAME, 1, 1, 12);
		System.out.println("=== end single ===\n");
	}

	@Test
	public void benchmarkMultipleThreads() throws Throwable {
		System.out.println("=== start multiple ===");
		setScanDetails(new int[] {12, 48}, new int[] {10, 4096});
		benchmark(MULTIPLE_THREAD_FILE_NAME, 1, 12);
		System.out.println("=== end multiple ===\n");
	}

	public void benchmark(String file, int detectors, int rois) throws Throwable {
		benchmark(file, detectors * (1 + scanRank + rois), detectors, rois);
	}

	public void benchmark(String file, int threads, int detectors, int rois) throws Throwable {
		System.out.printf("Using %d threads for %dD scan of %d detectors with %d rois\n", threads, scanRank, detectors, rois);
		Worker[] thread = createWorkers(threads);

		Detector[] detector = prepareDetectorsAndFile(detectors, rois, file, thread);

		long now = -System.nanoTime();
		for (Detector d : detector) {
			runDetector(d);
		}

		deleteWorkers(thread);
		now += System.nanoTime();

		List<Long> wtime = new ArrayList<>();
		for (Worker t : thread) {
			wtime.addAll(t.getTimes());
		}
		if (wtime.size() > 0) {
			System.out.println("Writing  stats in us:");
			List<Long> itime = printStats(wtime, true);
			System.out.printf("Outliers removed %5d of %5d\n", wtime.size() - itime.size(), wtime.size());
			printStats(itime, false);
		}

		System.out.printf("Total time %.1fms\n" , now/1e6);
	}

	public Worker[] createWorkers(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Number of threads must be zero or more");
		}

		Worker[] thread = new Worker[n];
		for (int i = 0; i < n; i++) {
			thread[i] = new Worker(String.format("Writer %03d/%d", i, n));
		}
		return thread;
	}

	public void deleteWorkers(Worker... thread) {
		for (Worker w : thread) {
			w.flush();
		}
		for (Worker w : thread) {
			w.finish(500);
		}
	}

	private Detector[] prepareDetectorsAndFile(int detectors, int rois, String file, Worker... thread) throws NexusException {

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

		try (NexusFile nf = new NexusFileHDF5(file)) {
			nf.createAndOpenToWrite();

			for (int d = 0; d < detectors; d++) {
				Detector dt = new Detector(scanRank, rois, thread);
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
	 * @param dt 
	 * @throws Throwable
	 */
	protected void runDetector(Detector dt) throws Throwable {
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

			// set up ROI slice
			for (int i = 0; i < r; i++) {
				rslice.setSlice(i, dslice.getStart()[i], dslice.getStop()[i], dslice.getStep()[i]);
			}

			now = -System.nanoTime();
			dt.writeAllData(dslice.clone(), rslice.clone(), pslice.clone());
			now += System.nanoTime();
			wtime.add(now/1000l);
		}

		System.out.println("Creation stats in us:");
		List<Long> itime = printStats(dtime, true);
		System.out.printf("Outliers removed %5d of %5d\n", dtime.size() - itime.size(), dtime.size());
		printStats(itime, false);
		System.out.println();

		System.out.println("Dispatch stats in us:");
		itime = printStats(wtime, true);
		System.out.printf("Outliers removed %5d of %5d\n", wtime.size() - itime.size(), wtime.size());
		printStats(itime, false);
		System.out.println();
	}

	public List<Long> printStats(List<Long> time, boolean withHisto) {
		Dataset d = DatasetFactory.createFromList(time);
		double med = (double) Stats.median(d);
		double iqr = (double) Stats.iqr(d);
		long min = (long) d.min();
		long max = (long) d.max();

		System.out.printf("Min is %5d\n", min);
		System.out.printf("Med is %5d\n", (long) med);
		System.out.printf("Max is %5d\n", max);
		System.out.printf("IQR is %5d\n", (long) iqr);

		if (!withHisto) {
			return null;
		}

		int bins = 8;
		int[] histo = new int[bins];
		long width = (max - min) / bins;
		for (long l : time) {
			int i = (int) ((l - min) / width);
			histo[i < bins ? i : i - 1]++;
		}
		System.out.printf("\nTime     Count\n");
		for (int i = 0; i < bins; i++) {
			System.out.printf("%7d %5d\n", min + i*width, histo[i]);
		}
		System.out.println();
		// TODO time analysis

		long limit = (long) (med + 2.5*iqr);
		System.out.printf("Threshold is %5d (median + 2.5*iqr)\n", limit);

		List<Long> inliers = new ArrayList<>();
		for (long t : time) {
			if (t <= limit) {
				inliers.add(t);
			}
		}
		return inliers;
	}
}
