/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-12T11:55:04.232+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Description of a Fermi chopper, possibly with curved slits.
 * 
 * @version 1.0
 */
public class NXfermi_chopperImpl extends NXobjectImpl implements NXfermi_chopper {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_ROTATION_SPEED = "rotation_speed";
	public static final String NX_RADIUS = "radius";
	public static final String NX_SLIT = "slit";
	public static final String NX_R_SLIT = "r_slit";
	public static final String NX_NUMBER = "number";
	public static final String NX_HEIGHT = "height";
	public static final String NX_WIDTH = "width";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_WAVELENGTH = "wavelength";
	public static final String NX_ENERGY = "energy";
	public static final String NX_ABSORBING_MATERIAL = "absorbing_material";
	public static final String NX_TRANSMITTING_MATERIAL = "transmitting_material";

	protected NXfermi_chopperImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXfermi_chopperImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXfermi_chopper.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_FERMI_CHOPPER;
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getScalarType() {
		return getString(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setScalarType(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getRotation_speed() {
		return getDataset(NX_ROTATION_SPEED);
	}

	@Override
	public double getScalarRotation_speed() {
		return getDouble(NX_ROTATION_SPEED);
	}

	public void setRotation_speed(IDataset rotation_speed) {
		setDataset(NX_ROTATION_SPEED, rotation_speed);
	}

	public void setScalarRotation_speed(double rotation_speed) {
		setField(NX_ROTATION_SPEED, rotation_speed);
	}

	@Override
	public IDataset getRadius() {
		return getDataset(NX_RADIUS);
	}

	@Override
	public double getScalarRadius() {
		return getDouble(NX_RADIUS);
	}

	public void setRadius(IDataset radius) {
		setDataset(NX_RADIUS, radius);
	}

	public void setScalarRadius(double radius) {
		setField(NX_RADIUS, radius);
	}

	@Override
	public IDataset getSlit() {
		return getDataset(NX_SLIT);
	}

	@Override
	public double getScalarSlit() {
		return getDouble(NX_SLIT);
	}

	public void setSlit(IDataset slit) {
		setDataset(NX_SLIT, slit);
	}

	public void setScalarSlit(double slit) {
		setField(NX_SLIT, slit);
	}

	@Override
	public IDataset getR_slit() {
		return getDataset(NX_R_SLIT);
	}

	@Override
	public double getScalarR_slit() {
		return getDouble(NX_R_SLIT);
	}

	public void setR_slit(IDataset r_slit) {
		setDataset(NX_R_SLIT, r_slit);
	}

	public void setScalarR_slit(double r_slit) {
		setField(NX_R_SLIT, r_slit);
	}

	@Override
	public IDataset getNumber() {
		return getDataset(NX_NUMBER);
	}

	@Override
	public long getScalarNumber() {
		return getLong(NX_NUMBER);
	}

	public void setNumber(IDataset number) {
		setDataset(NX_NUMBER, number);
	}

	public void setScalarNumber(long number) {
		setField(NX_NUMBER, number);
	}

	@Override
	public IDataset getHeight() {
		return getDataset(NX_HEIGHT);
	}

	@Override
	public double getScalarHeight() {
		return getDouble(NX_HEIGHT);
	}

	public void setHeight(IDataset height) {
		setDataset(NX_HEIGHT, height);
	}

	public void setScalarHeight(double height) {
		setField(NX_HEIGHT, height);
	}

	@Override
	public IDataset getWidth() {
		return getDataset(NX_WIDTH);
	}

	@Override
	public double getScalarWidth() {
		return getDouble(NX_WIDTH);
	}

	public void setWidth(IDataset width) {
		setDataset(NX_WIDTH, width);
	}

	public void setScalarWidth(double width) {
		setField(NX_WIDTH, width);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getScalarDistance() {
		return getDouble(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	public void setScalarDistance(double distance) {
		setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getWavelength() {
		return getDataset(NX_WAVELENGTH);
	}

	@Override
	public double getScalarWavelength() {
		return getDouble(NX_WAVELENGTH);
	}

	public void setWavelength(IDataset wavelength) {
		setDataset(NX_WAVELENGTH, wavelength);
	}

	public void setScalarWavelength(double wavelength) {
		setField(NX_WAVELENGTH, wavelength);
	}

	@Override
	public IDataset getEnergy() {
		return getDataset(NX_ENERGY);
	}

	@Override
	public double getScalarEnergy() {
		return getDouble(NX_ENERGY);
	}

	public void setEnergy(IDataset energy) {
		setDataset(NX_ENERGY, energy);
	}

	public void setScalarEnergy(double energy) {
		setField(NX_ENERGY, energy);
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
	public IDataset getAbsorbing_material() {
		return getDataset(NX_ABSORBING_MATERIAL);
	}

	@Override
	public String getScalarAbsorbing_material() {
		return getString(NX_ABSORBING_MATERIAL);
	}

	public void setAbsorbing_material(IDataset absorbing_material) {
		setDataset(NX_ABSORBING_MATERIAL, absorbing_material);
	}

	public void setScalarAbsorbing_material(String absorbing_material) {
		setString(NX_ABSORBING_MATERIAL, absorbing_material);
	}

	@Override
	public IDataset getTransmitting_material() {
		return getDataset(NX_TRANSMITTING_MATERIAL);
	}

	@Override
	public String getScalarTransmitting_material() {
		return getString(NX_TRANSMITTING_MATERIAL);
	}

	public void setTransmitting_material(IDataset transmitting_material) {
		setDataset(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

	public void setScalarTransmitting_material(String transmitting_material) {
		setString(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

}
