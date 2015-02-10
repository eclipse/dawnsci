/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ROIUtils;

/**
 * Abstract class for region of interest handles
 * 
 * Its super class holds the primitive IDs for handle areas
 */
abstract public class ROIHandler<T extends IROI> {
	protected T roi;
	protected int handle;
	protected HandleStatus status;
	protected List<Integer> list;

	public ROIHandler() {
		list = new ArrayList<Integer>();
	}

	public boolean add(int e) {
		return list.add(e);
	}

	public int get(int index) {
		return list.get(index);
	}

	public int set(int index, int element) {
		return list.set(index, element);
	}

	public int remove(int index) {
		return list.remove(index);
	}

	public int size() {
		return list.size();
	}

	public int indexOf(int o) {
		return list.indexOf(o);
	}

	public boolean contains(int o) {
		return list.contains(o);
	}

	public List<Integer> getAll() {
		return list;
	}

	/**
	 * @param handle
	 * @param size 
	 * @return handle point for corner of handle box
	 */
	abstract public double[] getHandlePoint(int handle, int size);

	/**
	 * @param handle
	 * @param size
	 * @return anchor point for scale invariant display and acts as centre of rotation
	 */
	abstract public double[] getAnchorPoint(int handle, int size);

	/**
	 * @return ROI
	 */
	public T getROI() {
		return roi;
	}

	/**
	 * @return centre handle ID
	 */
	abstract public int getCentreHandle();

	/**
	 * @param roi The roi to set.
	 */
	public void setROI(T roi) {
		this.roi = roi;
	}

	/**
	 * Set handle used and status in dragging
	 * @param handle
	 * @param dragStatus
	 */
	public void configureDragging(int handle, HandleStatus dragStatus) {
		this.handle = handle;
		status = dragStatus;
	}

	/**
	 * Reset configuration for dragging
	 */
	public void unconfigureDragging() {
		handle = -1;
		status = HandleStatus.NONE;
	}

	/**
	 * @return handle status
	 */
	public HandleStatus getStatus() {
		return status;
	}

	/**
	 * Interpret mouse dragging
	 * @param spt
	 * @param ept
	 * @return roi
	 */
	public T interpretMouseDragging(int[] spt, int[] ept) {
		return interpretMouseDragging(ROIUtils.convertToDoubleArray(spt), ROIUtils.convertToDoubleArray(ept));
	}

	/**
	 * Interpret mouse dragging
	 * @param spt
	 * @param ept
	 * @return roi
	 */
	abstract public T interpretMouseDragging(double[] spt, double[] ept);
}
