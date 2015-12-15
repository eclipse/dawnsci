/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY);

	protected NXbeam_stopImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXbeam_stopImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXbeam_stop.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_BEAM_STOP;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getSize() {
		return getDataset(NX_SIZE);
	}

	@Override
	public double getSizeScalar() {
		return getDouble(NX_SIZE);
	}

	public DataNode setSize(IDataset size) {
		return setDataset(NX_SIZE, size);
	}

	public DataNode setSizeScalar(double size) {
		return setField(NX_SIZE, size);
	}

	@Override
	public IDataset getX() {
		return getDataset(NX_X);
	}

	@Override
	public double getXScalar() {
		return getDouble(NX_X);
	}

	public DataNode setX(IDataset x) {
		return setDataset(NX_X, x);
	}

	public DataNode setXScalar(double x) {
		return setField(NX_X, x);
	}

	@Override
	public IDataset getY() {
		return getDataset(NX_Y);
	}

	@Override
	public double getYScalar() {
		return getDouble(NX_Y);
	}

	public DataNode setY(IDataset y) {
		return setDataset(NX_Y, y);
	}

	public DataNode setYScalar(double y) {
		return setField(NX_Y, y);
	}

	@Override
	public IDataset getDistance_to_detector() {
		return getDataset(NX_DISTANCE_TO_DETECTOR);
	}

	@Override
	public double getDistance_to_detectorScalar() {
		return getDouble(NX_DISTANCE_TO_DETECTOR);
	}

	public DataNode setDistance_to_detector(IDataset distance_to_detector) {
		return setDataset(NX_DISTANCE_TO_DETECTOR, distance_to_detector);
	}

	public DataNode setDistance_to_detectorScalar(double distance_to_detector) {
		return setField(NX_DISTANCE_TO_DETECTOR, distance_to_detector);
	}

	@Override
	public IDataset getStatus() {
		return getDataset(NX_STATUS);
	}

	@Override
	public String getStatusScalar() {
		return getString(NX_STATUS);
	}

	public DataNode setStatus(IDataset status) {
		return setDataset(NX_STATUS, status);
	}

	public DataNode setStatusScalar(String status) {
		return setString(NX_STATUS, status);
	}

}
