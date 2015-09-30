/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * A class for a beamstop. Beamstops and their positions are important for SANS
 * and SAXS experiments.
 * 
 * @version 1.0
 */
public class NXbeam_stopImpl extends NXobjectImpl implements NXbeam_stop {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_SIZE = "size";
	public static final String NX_X = "x";
	public static final String NX_Y = "y";
	public static final String NX_DISTANCE_TO_DETECTOR = "distance_to_detector";
	public static final String NX_STATUS = "status";

	protected NXbeam_stopImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXbeam_stop.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_BEAM_STOP;
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}

	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getSize() {
		return getDataset(NX_SIZE);
	}

	public void setSize(IDataset size) {
		setDataset(NX_SIZE, size);
	}

	@Override
	public IDataset getX() {
		return getDataset(NX_X);
	}

	public void setX(IDataset x) {
		setDataset(NX_X, x);
	}

	@Override
	public IDataset getY() {
		return getDataset(NX_Y);
	}

	public void setY(IDataset y) {
		setDataset(NX_Y, y);
	}

	@Override
	public IDataset getDistance_to_detector() {
		return getDataset(NX_DISTANCE_TO_DETECTOR);
	}

	public void setDistance_to_detector(IDataset distance_to_detector) {
		setDataset(NX_DISTANCE_TO_DETECTOR, distance_to_detector);
	}

	@Override
	public IDataset getStatus() {
		return getDataset(NX_STATUS);
	}

	public void setStatus(IDataset status) {
		setDataset(NX_STATUS, status);
	}

}
