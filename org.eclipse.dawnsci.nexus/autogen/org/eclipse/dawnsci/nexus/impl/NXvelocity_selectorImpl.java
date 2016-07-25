/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-06-07T11:32:35.177+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * A neutron velocity selector
 * 
 * @version 1.0
 */
public class NXvelocity_selectorImpl extends NXobjectImpl implements NXvelocity_selector {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY);

	public NXvelocity_selectorImpl() {
		super();
	}

	public NXvelocity_selectorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXvelocity_selector.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_VELOCITY_SELECTOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	@Override
	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	@Override
	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
	}

	@Override
	public IDataset getRotation_speed() {
		return getDataset(NX_ROTATION_SPEED);
	}

	@Override
	public double getRotation_speedScalar() {
		return getDouble(NX_ROTATION_SPEED);
	}

	@Override
	public DataNode setRotation_speed(IDataset rotation_speed) {
		return setDataset(NX_ROTATION_SPEED, rotation_speed);
	}

	@Override
	public DataNode setRotation_speedScalar(double rotation_speed) {
		return setField(NX_ROTATION_SPEED, rotation_speed);
	}

	@Override
	public IDataset getRadius() {
		return getDataset(NX_RADIUS);
	}

	@Override
	public double getRadiusScalar() {
		return getDouble(NX_RADIUS);
	}

	@Override
	public DataNode setRadius(IDataset radius) {
		return setDataset(NX_RADIUS, radius);
	}

	@Override
	public DataNode setRadiusScalar(double radius) {
		return setField(NX_RADIUS, radius);
	}

	@Override
	public IDataset getSpwidth() {
		return getDataset(NX_SPWIDTH);
	}

	@Override
	public double getSpwidthScalar() {
		return getDouble(NX_SPWIDTH);
	}

	@Override
	public DataNode setSpwidth(IDataset spwidth) {
		return setDataset(NX_SPWIDTH, spwidth);
	}

	@Override
	public DataNode setSpwidthScalar(double spwidth) {
		return setField(NX_SPWIDTH, spwidth);
	}

	@Override
	public IDataset getLength() {
		return getDataset(NX_LENGTH);
	}

	@Override
	public double getLengthScalar() {
		return getDouble(NX_LENGTH);
	}

	@Override
	public DataNode setLength(IDataset length) {
		return setDataset(NX_LENGTH, length);
	}

	@Override
	public DataNode setLengthScalar(double length) {
		return setField(NX_LENGTH, length);
	}

	@Override
	public IDataset getNum() {
		return getDataset(NX_NUM);
	}

	@Override
	public long getNumScalar() {
		return getLong(NX_NUM);
	}

	@Override
	public DataNode setNum(IDataset num) {
		return setDataset(NX_NUM, num);
	}

	@Override
	public DataNode setNumScalar(long num) {
		return setField(NX_NUM, num);
	}

	@Override
	public IDataset getTwist() {
		return getDataset(NX_TWIST);
	}

	@Override
	public double getTwistScalar() {
		return getDouble(NX_TWIST);
	}

	@Override
	public DataNode setTwist(IDataset twist) {
		return setDataset(NX_TWIST, twist);
	}

	@Override
	public DataNode setTwistScalar(double twist) {
		return setField(NX_TWIST, twist);
	}

	@Override
	public IDataset getTable() {
		return getDataset(NX_TABLE);
	}

	@Override
	public double getTableScalar() {
		return getDouble(NX_TABLE);
	}

	@Override
	public DataNode setTable(IDataset table) {
		return setDataset(NX_TABLE, table);
	}

	@Override
	public DataNode setTableScalar(double table) {
		return setField(NX_TABLE, table);
	}

	@Override
	public IDataset getHeight() {
		return getDataset(NX_HEIGHT);
	}

	@Override
	public double getHeightScalar() {
		return getDouble(NX_HEIGHT);
	}

	@Override
	public DataNode setHeight(IDataset height) {
		return setDataset(NX_HEIGHT, height);
	}

	@Override
	public DataNode setHeightScalar(double height) {
		return setField(NX_HEIGHT, height);
	}

	@Override
	public IDataset getWidth() {
		return getDataset(NX_WIDTH);
	}

	@Override
	public double getWidthScalar() {
		return getDouble(NX_WIDTH);
	}

	@Override
	public DataNode setWidth(IDataset width) {
		return setDataset(NX_WIDTH, width);
	}

	@Override
	public DataNode setWidthScalar(double width) {
		return setField(NX_WIDTH, width);
	}

	@Override
	public IDataset getWavelength() {
		return getDataset(NX_WAVELENGTH);
	}

	@Override
	public double getWavelengthScalar() {
		return getDouble(NX_WAVELENGTH);
	}

	@Override
	public DataNode setWavelength(IDataset wavelength) {
		return setDataset(NX_WAVELENGTH, wavelength);
	}

	@Override
	public DataNode setWavelengthScalar(double wavelength) {
		return setField(NX_WAVELENGTH, wavelength);
	}

	@Override
	public IDataset getWavelength_spread() {
		return getDataset(NX_WAVELENGTH_SPREAD);
	}

	@Override
	public double getWavelength_spreadScalar() {
		return getDouble(NX_WAVELENGTH_SPREAD);
	}

	@Override
	public DataNode setWavelength_spread(IDataset wavelength_spread) {
		return setDataset(NX_WAVELENGTH_SPREAD, wavelength_spread);
	}

	@Override
	public DataNode setWavelength_spreadScalar(double wavelength_spread) {
		return setField(NX_WAVELENGTH_SPREAD, wavelength_spread);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

}
