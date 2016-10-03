/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.dawnsci.analysis.api.worker.Worker;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.SliceND;

/**
 * Class to hold state of a HDF5 file
 */
public class HDF5File {
	private String file;
	private long id;   // HDF5 low level ID
	private long time; // time of release
	private AtomicInteger count; // number of accessors
	private boolean writeable; // if true then can write
	private boolean canSWMR;

	private Worker thread; // optional writing thread

	/**
	 * 
	 * @param filePath
	 * @param id
	 * @param writeable
	 * @param canBeSWMR if true, can be a SWMR writer (must be writeable too)
	 */
	public HDF5File(String filePath, long id, boolean writeable, boolean canBeSWMR) {
		this.file = filePath;
		this.id = id;
		count = new AtomicInteger(1);
		this.writeable = writeable;
		this.canSWMR = canBeSWMR;
		if (!writeable && canBeSWMR) {
			throw new IllegalArgumentException("Only writeable files can be SWMR");
		}
	}

	public long getID() {
		return id;
	}

	/**
	 * @return release time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Set release time
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return number of accessors of file
	 */
	public int getCount() {
		return count.get();
	}

	/**
	 * Increment number of accessors of file
	 * @return incremented value
	 */
	public int incrementCount() {
		return count.incrementAndGet();
	}

	/**
	 * Decrement number of accessors of file
	 * @return decremented value
	 */
	public int decrementCount() {
		return count.decrementAndGet();
	}

	/**
	 * @return true if file is writable
	 */
	public boolean isWriteable() {
		return writeable;
	}

	/**
	 * @return true if the switch to SWMR writer mode has succeeded
	 */
	public boolean canSwitchSWMR() {
		return canSWMR;
	}

	private class WriteJob implements Runnable {
		private ILazyWriteableDataset out;
		private final IDataset data;
		private final SliceND slice;
		public WriteJob(final ILazyWriteableDataset out, final IDataset data, final SliceND slice) {
			this.out = out;
			this.data = data;
			this.slice = slice;
		}

		@Override
		public void run() {
//			System.err.printf("Writing " + DatasetUtils.convertToDataset(data).toString(true) + " to " + slice.toString());
			try {
				out.setSliceSync(null, data, slice);
			} catch (DatasetException e) {
				throw new RuntimeException(e);
			}
//			System.err.printf("... end\n");
		}
	}

	/**
	 * Add write job
	 * @param destination
	 * @param data
	 * @param slice
	 * @return true if writeable
	 */
	public synchronized boolean addWriteJob(ILazyWriteableDataset destination, IDataset data, SliceND slice) {
		if (writeable) {
			if (thread == null) {
				thread = new Worker("Writer for " + file);
			}
			thread.addJob(new WriteJob(destination, data, slice));
			return true;
		}
		return false;
	}

	/**
	 * Finish all writes (block until it is done)
	 */
	public synchronized void flushWrites() {
		if (thread != null) {
			thread.flush();
			Throwable t = thread.getThrowable();
			if (t != null) {
				throw new RuntimeException(t);
			}
		}
	}

	/**
	 * @param milliseconds to wait before finishing
	 */
	public synchronized void finish(long milliseconds) {
		if (thread != null) {
			thread.finish(milliseconds);
			Throwable t = thread.getThrowable();
			if (t != null) {
				throw new RuntimeException(t);
			}
		}
	}

	@Override
	public String toString() {
		return file;
	}
}