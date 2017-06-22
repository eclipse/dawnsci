/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * The neutron or x-ray storage ring/facility.
 * 
 * @version 1.0
 */
public class NXsourceImpl extends NXobjectImpl implements NXsource {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_NOTE,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_DATA);

	public NXsourceImpl() {
		super();
	}

	public NXsourceImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsource.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SOURCE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public Double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	@Override
	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	@Override
	public DataNode setDistanceScalar(Double distance) {
		return setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getNameScalar() {
		return getString(NX_NAME);
	}

	@Override
	public DataNode setName(IDataset name) {
		return setDataset(NX_NAME, name);
	}

	@Override
	public DataNode setNameScalar(String name) {
		return setString(NX_NAME, name);
	}

	@Override
	public String getNameAttributeShort_name() {
		return getAttrString(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME);
	}

	@Override
	public void setNameAttributeShort_name(String short_name) {
		setAttribute(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME, short_name);
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
	public IDataset getProbe() {
		return getDataset(NX_PROBE);
	}

	@Override
	public String getProbeScalar() {
		return getString(NX_PROBE);
	}

	@Override
	public DataNode setProbe(IDataset probe) {
		return setDataset(NX_PROBE, probe);
	}

	@Override
	public DataNode setProbeScalar(String probe) {
		return setString(NX_PROBE, probe);
	}

	@Override
	public IDataset getPower() {
		return getDataset(NX_POWER);
	}

	@Override
	public Double getPowerScalar() {
		return getDouble(NX_POWER);
	}

	@Override
	public DataNode setPower(IDataset power) {
		return setDataset(NX_POWER, power);
	}

	@Override
	public DataNode setPowerScalar(Double power) {
		return setField(NX_POWER, power);
	}

	@Override
	public IDataset getEmittance_x() {
		return getDataset(NX_EMITTANCE_X);
	}

	@Override
	public Double getEmittance_xScalar() {
		return getDouble(NX_EMITTANCE_X);
	}

	@Override
	public DataNode setEmittance_x(IDataset emittance_x) {
		return setDataset(NX_EMITTANCE_X, emittance_x);
	}

	@Override
	public DataNode setEmittance_xScalar(Double emittance_x) {
		return setField(NX_EMITTANCE_X, emittance_x);
	}

	@Override
	public IDataset getEmittance_y() {
		return getDataset(NX_EMITTANCE_Y);
	}

	@Override
	public Double getEmittance_yScalar() {
		return getDouble(NX_EMITTANCE_Y);
	}

	@Override
	public DataNode setEmittance_y(IDataset emittance_y) {
		return setDataset(NX_EMITTANCE_Y, emittance_y);
	}

	@Override
	public DataNode setEmittance_yScalar(Double emittance_y) {
		return setField(NX_EMITTANCE_Y, emittance_y);
	}

	@Override
	public IDataset getSigma_x() {
		return getDataset(NX_SIGMA_X);
	}

	@Override
	public Double getSigma_xScalar() {
		return getDouble(NX_SIGMA_X);
	}

	@Override
	public DataNode setSigma_x(IDataset sigma_x) {
		return setDataset(NX_SIGMA_X, sigma_x);
	}

	@Override
	public DataNode setSigma_xScalar(Double sigma_x) {
		return setField(NX_SIGMA_X, sigma_x);
	}

	@Override
	public IDataset getSigma_y() {
		return getDataset(NX_SIGMA_Y);
	}

	@Override
	public Double getSigma_yScalar() {
		return getDouble(NX_SIGMA_Y);
	}

	@Override
	public DataNode setSigma_y(IDataset sigma_y) {
		return setDataset(NX_SIGMA_Y, sigma_y);
	}

	@Override
	public DataNode setSigma_yScalar(Double sigma_y) {
		return setField(NX_SIGMA_Y, sigma_y);
	}

	@Override
	public IDataset getFlux() {
		return getDataset(NX_FLUX);
	}

	@Override
	public Double getFluxScalar() {
		return getDouble(NX_FLUX);
	}

	@Override
	public DataNode setFlux(IDataset flux) {
		return setDataset(NX_FLUX, flux);
	}

	@Override
	public DataNode setFluxScalar(Double flux) {
		return setField(NX_FLUX, flux);
	}

	@Override
	public IDataset getEnergy() {
		return getDataset(NX_ENERGY);
	}

	@Override
	public Double getEnergyScalar() {
		return getDouble(NX_ENERGY);
	}

	@Override
	public DataNode setEnergy(IDataset energy) {
		return setDataset(NX_ENERGY, energy);
	}

	@Override
	public DataNode setEnergyScalar(Double energy) {
		return setField(NX_ENERGY, energy);
	}

	@Override
	public IDataset getCurrent() {
		return getDataset(NX_CURRENT);
	}

	@Override
	public Double getCurrentScalar() {
		return getDouble(NX_CURRENT);
	}

	@Override
	public DataNode setCurrent(IDataset current) {
		return setDataset(NX_CURRENT, current);
	}

	@Override
	public DataNode setCurrentScalar(Double current) {
		return setField(NX_CURRENT, current);
	}

	@Override
	public IDataset getVoltage() {
		return getDataset(NX_VOLTAGE);
	}

	@Override
	public Double getVoltageScalar() {
		return getDouble(NX_VOLTAGE);
	}

	@Override
	public DataNode setVoltage(IDataset voltage) {
		return setDataset(NX_VOLTAGE, voltage);
	}

	@Override
	public DataNode setVoltageScalar(Double voltage) {
		return setField(NX_VOLTAGE, voltage);
	}

	@Override
	public IDataset getFrequency() {
		return getDataset(NX_FREQUENCY);
	}

	@Override
	public Double getFrequencyScalar() {
		return getDouble(NX_FREQUENCY);
	}

	@Override
	public DataNode setFrequency(IDataset frequency) {
		return setDataset(NX_FREQUENCY, frequency);
	}

	@Override
	public DataNode setFrequencyScalar(Double frequency) {
		return setField(NX_FREQUENCY, frequency);
	}

	@Override
	public IDataset getPeriod() {
		return getDataset(NX_PERIOD);
	}

	@Override
	public Double getPeriodScalar() {
		return getDouble(NX_PERIOD);
	}

	@Override
	public DataNode setPeriod(IDataset period) {
		return setDataset(NX_PERIOD, period);
	}

	@Override
	public DataNode setPeriodScalar(Double period) {
		return setField(NX_PERIOD, period);
	}

	@Override
	public IDataset getTarget_material() {
		return getDataset(NX_TARGET_MATERIAL);
	}

	@Override
	public String getTarget_materialScalar() {
		return getString(NX_TARGET_MATERIAL);
	}

	@Override
	public DataNode setTarget_material(IDataset target_material) {
		return setDataset(NX_TARGET_MATERIAL, target_material);
	}

	@Override
	public DataNode setTarget_materialScalar(String target_material) {
		return setString(NX_TARGET_MATERIAL, target_material);
	}

	@Override
	public NXnote getNotes() {
		return getChild("notes", NXnote.class);
	}

	@Override
	public void setNotes(NXnote notes) {
		putChild("notes", notes);
	}

	@Override
	public NXdata getBunch_pattern() {
		return getChild("bunch_pattern", NXdata.class);
	}

	@Override
	public void setBunch_pattern(NXdata bunch_pattern) {
		putChild("bunch_pattern", bunch_pattern);
	}

	@Override
	public IDataset getNumber_of_bunches() {
		return getDataset(NX_NUMBER_OF_BUNCHES);
	}

	@Override
	public Long getNumber_of_bunchesScalar() {
		return getLong(NX_NUMBER_OF_BUNCHES);
	}

	@Override
	public DataNode setNumber_of_bunches(IDataset number_of_bunches) {
		return setDataset(NX_NUMBER_OF_BUNCHES, number_of_bunches);
	}

	@Override
	public DataNode setNumber_of_bunchesScalar(Long number_of_bunches) {
		return setField(NX_NUMBER_OF_BUNCHES, number_of_bunches);
	}

	@Override
	public IDataset getBunch_length() {
		return getDataset(NX_BUNCH_LENGTH);
	}

	@Override
	public Double getBunch_lengthScalar() {
		return getDouble(NX_BUNCH_LENGTH);
	}

	@Override
	public DataNode setBunch_length(IDataset bunch_length) {
		return setDataset(NX_BUNCH_LENGTH, bunch_length);
	}

	@Override
	public DataNode setBunch_lengthScalar(Double bunch_length) {
		return setField(NX_BUNCH_LENGTH, bunch_length);
	}

	@Override
	public IDataset getBunch_distance() {
		return getDataset(NX_BUNCH_DISTANCE);
	}

	@Override
	public Double getBunch_distanceScalar() {
		return getDouble(NX_BUNCH_DISTANCE);
	}

	@Override
	public DataNode setBunch_distance(IDataset bunch_distance) {
		return setDataset(NX_BUNCH_DISTANCE, bunch_distance);
	}

	@Override
	public DataNode setBunch_distanceScalar(Double bunch_distance) {
		return setField(NX_BUNCH_DISTANCE, bunch_distance);
	}

	@Override
	public IDataset getPulse_width() {
		return getDataset(NX_PULSE_WIDTH);
	}

	@Override
	public Double getPulse_widthScalar() {
		return getDouble(NX_PULSE_WIDTH);
	}

	@Override
	public DataNode setPulse_width(IDataset pulse_width) {
		return setDataset(NX_PULSE_WIDTH, pulse_width);
	}

	@Override
	public DataNode setPulse_widthScalar(Double pulse_width) {
		return setField(NX_PULSE_WIDTH, pulse_width);
	}

	@Override
	public NXdata getPulse_shape() {
		return getChild("pulse_shape", NXdata.class);
	}

	@Override
	public void setPulse_shape(NXdata pulse_shape) {
		putChild("pulse_shape", pulse_shape);
	}

	@Override
	public IDataset getMode() {
		return getDataset(NX_MODE);
	}

	@Override
	public String getModeScalar() {
		return getString(NX_MODE);
	}

	@Override
	public DataNode setMode(IDataset mode) {
		return setDataset(NX_MODE, mode);
	}

	@Override
	public DataNode setModeScalar(String mode) {
		return setString(NX_MODE, mode);
	}

	@Override
	public IDataset getTop_up() {
		return getDataset(NX_TOP_UP);
	}

	@Override
	public Boolean getTop_upScalar() {
		return getBoolean(NX_TOP_UP);
	}

	@Override
	public DataNode setTop_up(IDataset top_up) {
		return setDataset(NX_TOP_UP, top_up);
	}

	@Override
	public DataNode setTop_upScalar(Boolean top_up) {
		return setField(NX_TOP_UP, top_up);
	}

	@Override
	public IDataset getLast_fill() {
		return getDataset(NX_LAST_FILL);
	}

	@Override
	public Number getLast_fillScalar() {
		return getNumber(NX_LAST_FILL);
	}

	@Override
	public DataNode setLast_fill(IDataset last_fill) {
		return setDataset(NX_LAST_FILL, last_fill);
	}

	@Override
	public DataNode setLast_fillScalar(Number last_fill) {
		return setField(NX_LAST_FILL, last_fill);
	}

	@Override
	public Date getLast_fillAttributeTime() {
		return getAttrDate(NX_LAST_FILL, NX_LAST_FILL_ATTRIBUTE_TIME);
	}

	@Override
	public void setLast_fillAttributeTime(Date time) {
		setAttribute(NX_LAST_FILL, NX_LAST_FILL_ATTRIBUTE_TIME, time);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXdata getDistribution() {
		return getChild("distribution", NXdata.class);
	}

	@Override
	public void setDistribution(NXdata distribution) {
		putChild("distribution", distribution);
	}

}
