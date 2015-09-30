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

import java.util.Date;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of the neutron or x-ray source, insertion devices and/or moderators.
 * 
 * @version 1.0
 */
public class NXsourceImpl extends NXobjectImpl implements NXsource {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DISTANCE = "distance";
	public static final String NX_NAME = "name";
	public static final String NX_NAME_ATTRIBUTE_SHORT_NAME = "short_name";
	public static final String NX_TYPE = "type";
	public static final String NX_PROBE = "probe";
	public static final String NX_POWER = "power";
	public static final String NX_EMITTANCE_X = "emittance_x";
	public static final String NX_EMITTANCE_Y = "emittance_y";
	public static final String NX_SIGMA_X = "sigma_x";
	public static final String NX_SIGMA_Y = "sigma_y";
	public static final String NX_FLUX = "flux";
	public static final String NX_ENERGY = "energy";
	public static final String NX_CURRENT = "current";
	public static final String NX_VOLTAGE = "voltage";
	public static final String NX_FREQUENCY = "frequency";
	public static final String NX_PERIOD = "period";
	public static final String NX_TARGET_MATERIAL = "target_material";
	public static final String NX_NUMBER_OF_BUNCHES = "number_of_bunches";
	public static final String NX_BUNCH_LENGTH = "bunch_length";
	public static final String NX_BUNCH_DISTANCE = "bunch_distance";
	public static final String NX_PULSE_WIDTH = "pulse_width";
	public static final String NX_MODE = "mode";
	public static final String NX_TOP_UP = "top_up";
	public static final String NX_LAST_FILL = "last_fill";
	public static final String NX_LAST_FILL_ATTRIBUTE_TIME = "time";

	protected NXsourceImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsource.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_SOURCE;
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	@Override
	public String getNameAttributeShort_name() {
		return getAttrString(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME);
	}

	public void setNameAttributeShort_name(String short_name) {
		setAttribute(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME, short_name);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getProbe() {
		return getDataset(NX_PROBE);
	}

	public void setProbe(IDataset probe) {
		setDataset(NX_PROBE, probe);
	}

	@Override
	public IDataset getPower() {
		return getDataset(NX_POWER);
	}

	public void setPower(IDataset power) {
		setDataset(NX_POWER, power);
	}

	@Override
	public IDataset getEmittance_x() {
		return getDataset(NX_EMITTANCE_X);
	}

	public void setEmittance_x(IDataset emittance_x) {
		setDataset(NX_EMITTANCE_X, emittance_x);
	}

	@Override
	public IDataset getEmittance_y() {
		return getDataset(NX_EMITTANCE_Y);
	}

	public void setEmittance_y(IDataset emittance_y) {
		setDataset(NX_EMITTANCE_Y, emittance_y);
	}

	@Override
	public IDataset getSigma_x() {
		return getDataset(NX_SIGMA_X);
	}

	public void setSigma_x(IDataset sigma_x) {
		setDataset(NX_SIGMA_X, sigma_x);
	}

	@Override
	public IDataset getSigma_y() {
		return getDataset(NX_SIGMA_Y);
	}

	public void setSigma_y(IDataset sigma_y) {
		setDataset(NX_SIGMA_Y, sigma_y);
	}

	@Override
	public IDataset getFlux() {
		return getDataset(NX_FLUX);
	}

	public void setFlux(IDataset flux) {
		setDataset(NX_FLUX, flux);
	}

	@Override
	public IDataset getEnergy() {
		return getDataset(NX_ENERGY);
	}

	public void setEnergy(IDataset energy) {
		setDataset(NX_ENERGY, energy);
	}

	@Override
	public IDataset getCurrent() {
		return getDataset(NX_CURRENT);
	}

	public void setCurrent(IDataset current) {
		setDataset(NX_CURRENT, current);
	}

	@Override
	public IDataset getVoltage() {
		return getDataset(NX_VOLTAGE);
	}

	public void setVoltage(IDataset voltage) {
		setDataset(NX_VOLTAGE, voltage);
	}

	@Override
	public IDataset getFrequency() {
		return getDataset(NX_FREQUENCY);
	}

	public void setFrequency(IDataset frequency) {
		setDataset(NX_FREQUENCY, frequency);
	}

	@Override
	public IDataset getPeriod() {
		return getDataset(NX_PERIOD);
	}

	public void setPeriod(IDataset period) {
		setDataset(NX_PERIOD, period);
	}

	@Override
	public IDataset getTarget_material() {
		return getDataset(NX_TARGET_MATERIAL);
	}

	public void setTarget_material(IDataset target_material) {
		setDataset(NX_TARGET_MATERIAL, target_material);
	}

	@Override
	public NXnote getNotes() {
		return getChild("notes", NXnote.class);
	}

	public void setNotes(NXnote notes) {
		putChild("notes", notes);
	}

	@Override
	public NXdata getBunch_pattern() {
		return getChild("bunch_pattern", NXdata.class);
	}

	public void setBunch_pattern(NXdata bunch_pattern) {
		putChild("bunch_pattern", bunch_pattern);
	}

	@Override
	public IDataset getNumber_of_bunches() {
		return getDataset(NX_NUMBER_OF_BUNCHES);
	}

	public void setNumber_of_bunches(IDataset number_of_bunches) {
		setDataset(NX_NUMBER_OF_BUNCHES, number_of_bunches);
	}

	@Override
	public IDataset getBunch_length() {
		return getDataset(NX_BUNCH_LENGTH);
	}

	public void setBunch_length(IDataset bunch_length) {
		setDataset(NX_BUNCH_LENGTH, bunch_length);
	}

	@Override
	public IDataset getBunch_distance() {
		return getDataset(NX_BUNCH_DISTANCE);
	}

	public void setBunch_distance(IDataset bunch_distance) {
		setDataset(NX_BUNCH_DISTANCE, bunch_distance);
	}

	@Override
	public IDataset getPulse_width() {
		return getDataset(NX_PULSE_WIDTH);
	}

	public void setPulse_width(IDataset pulse_width) {
		setDataset(NX_PULSE_WIDTH, pulse_width);
	}

	@Override
	public NXdata getPulse_shape() {
		return getChild("pulse_shape", NXdata.class);
	}

	public void setPulse_shape(NXdata pulse_shape) {
		putChild("pulse_shape", pulse_shape);
	}

	@Override
	public IDataset getMode() {
		return getDataset(NX_MODE);
	}

	public void setMode(IDataset mode) {
		setDataset(NX_MODE, mode);
	}

	@Override
	public IDataset getTop_up() {
		return getDataset(NX_TOP_UP);
	}

	public void setTop_up(IDataset top_up) {
		setDataset(NX_TOP_UP, top_up);
	}

	@Override
	public IDataset getLast_fill() {
		return getDataset(NX_LAST_FILL);
	}

	public void setLast_fill(IDataset last_fill) {
		setDataset(NX_LAST_FILL, last_fill);
	}

	@Override
	public Date getLast_fillAttributeTime() {
		return getAttrDate(NX_LAST_FILL, NX_LAST_FILL_ATTRIBUTE_TIME);
	}

	public void setLast_fillAttributeTime(Date time) {
		setAttribute(NX_LAST_FILL, NX_LAST_FILL_ATTRIBUTE_TIME, time);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXdata getDistribution() {
		return getChild("distribution", NXdata.class);
	}

	public void setDistribution(NXdata distribution) {
		putChild("distribution", distribution);
	}

}
