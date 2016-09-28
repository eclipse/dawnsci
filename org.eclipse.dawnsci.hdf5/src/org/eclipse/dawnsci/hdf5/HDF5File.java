/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

/**
 * Class to hold state of a HDF5 file
 */
public class HDF5File {
	private long id;   // HDF5 low level ID
	private long time; // time of release
	private int count; // number of accessors
	private boolean writeable; // if true then can write

	public HDF5File(long id, boolean writeable) {
		this.id = id;
		count = 1;
		this.writeable = writeable;
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
		return count;
	}

	/**
	 * Increment number of accessors of file
	 */
	public void incrementCount() {
		count++;
	}

	/**
	 * Decrement number of accessors of file
	 */
	public void decrementCount() {
		count--;
	}

	/**
	 * @return true if file is writable
	 */
	public boolean isWriteable() {
		return writeable;
	}
}
