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
 * A sensor used to monitor an external condition
 * The condition itself is described in :ref:`NXenvironment`.
 * 
 * @version 1.0
 */
public class NXsensorImpl extends NXobjectImpl implements NXsensor {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_ORIENTATION);

	public NXsensorImpl() {
		super();
	}

	public NXsensorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsensor.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SENSOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getModel() {
		return getDataset(NX_MODEL);
	}

	@Override
	public String getModelScalar() {
		return getString(NX_MODEL);
	}

	@Override
	public DataNode setModel(IDataset model) {
		return setDataset(NX_MODEL, model);
	}

	@Override
	public DataNode setModelScalar(String model) {
		return setString(NX_MODEL, model);
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
	public IDataset getShort_name() {
		return getDataset(NX_SHORT_NAME);
	}

	@Override
	public String getShort_nameScalar() {
		return getString(NX_SHORT_NAME);
	}

	@Override
	public DataNode setShort_name(IDataset short_name) {
		return setDataset(NX_SHORT_NAME, short_name);
	}

	@Override
	public DataNode setShort_nameScalar(String short_name) {
		return setString(NX_SHORT_NAME, short_name);
	}

	@Override
	public IDataset getAttached_to() {
		return getDataset(NX_ATTACHED_TO);
	}

	@Override
	public String getAttached_toScalar() {
		return getString(NX_ATTACHED_TO);
	}

	@Override
	public DataNode setAttached_to(IDataset attached_to) {
		return setDataset(NX_ATTACHED_TO, attached_to);
	}

	@Override
	public DataNode setAttached_toScalar(String attached_to) {
		return setString(NX_ATTACHED_TO, attached_to);
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
	public IDataset getMeasurement() {
		return getDataset(NX_MEASUREMENT);
	}

	@Override
	public String getMeasurementScalar() {
		return getString(NX_MEASUREMENT);
	}

	@Override
	public DataNode setMeasurement(IDataset measurement) {
		return setDataset(NX_MEASUREMENT, measurement);
	}

	@Override
	public DataNode setMeasurementScalar(String measurement) {
		return setString(NX_MEASUREMENT, measurement);
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
	public IDataset getRun_control() {
		return getDataset(NX_RUN_CONTROL);
	}

	@Override
	public boolean getRun_controlScalar() {
		return getBoolean(NX_RUN_CONTROL);
	}

	@Override
	public DataNode setRun_control(IDataset run_control) {
		return setDataset(NX_RUN_CONTROL, run_control);
	}

	@Override
	public DataNode setRun_controlScalar(boolean run_control) {
		return setField(NX_RUN_CONTROL, run_control);
	}

	@Override
	public IDataset getHigh_trip_value() {
		return getDataset(NX_HIGH_TRIP_VALUE);
	}

	@Override
	public double getHigh_trip_valueScalar() {
		return getDouble(NX_HIGH_TRIP_VALUE);
	}

	@Override
	public DataNode setHigh_trip_value(IDataset high_trip_value) {
		return setDataset(NX_HIGH_TRIP_VALUE, high_trip_value);
	}

	@Override
	public DataNode setHigh_trip_valueScalar(double high_trip_value) {
		return setField(NX_HIGH_TRIP_VALUE, high_trip_value);
	}

	@Override
	public IDataset getLow_trip_value() {
		return getDataset(NX_LOW_TRIP_VALUE);
	}

	@Override
	public double getLow_trip_valueScalar() {
		return getDouble(NX_LOW_TRIP_VALUE);
	}

	@Override
	public DataNode setLow_trip_value(IDataset low_trip_value) {
		return setDataset(NX_LOW_TRIP_VALUE, low_trip_value);
	}

	@Override
	public DataNode setLow_trip_valueScalar(double low_trip_value) {
		return setField(NX_LOW_TRIP_VALUE, low_trip_value);
	}

	@Override
	public IDataset getValue() {
		return getDataset(NX_VALUE);
	}

	@Override
	public double getValueScalar() {
		return getDouble(NX_VALUE);
	}

	@Override
	public DataNode setValue(IDataset value) {
		return setDataset(NX_VALUE, value);
	}

	@Override
	public DataNode setValueScalar(double value) {
		return setField(NX_VALUE, value);
	}

	@Override
	public IDataset getValue_deriv1() {
		return getDataset(NX_VALUE_DERIV1);
	}

	@Override
	public double getValue_deriv1Scalar() {
		return getDouble(NX_VALUE_DERIV1);
	}

	@Override
	public DataNode setValue_deriv1(IDataset value_deriv1) {
		return setDataset(NX_VALUE_DERIV1, value_deriv1);
	}

	@Override
	public DataNode setValue_deriv1Scalar(double value_deriv1) {
		return setField(NX_VALUE_DERIV1, value_deriv1);
	}

	@Override
	public IDataset getValue_deriv2() {
		return getDataset(NX_VALUE_DERIV2);
	}

	@Override
	public double getValue_deriv2Scalar() {
		return getDouble(NX_VALUE_DERIV2);
	}

	@Override
	public DataNode setValue_deriv2(IDataset value_deriv2) {
		return setDataset(NX_VALUE_DERIV2, value_deriv2);
	}

	@Override
	public DataNode setValue_deriv2Scalar(double value_deriv2) {
		return setField(NX_VALUE_DERIV2, value_deriv2);
	}

	@Override
	public NXlog getValue_log() {
		return getChild("value_log", NXlog.class);
	}

	@Override
	public void setValue_log(NXlog value_log) {
		putChild("value_log", value_log);
	}

	@Override
	public NXlog getValue_deriv1_log() {
		return getChild("value_deriv1_log", NXlog.class);
	}

	@Override
	public void setValue_deriv1_log(NXlog value_deriv1_log) {
		putChild("value_deriv1_log", value_deriv1_log);
	}

	@Override
	public NXlog getValue_deriv2_log() {
		return getChild("value_deriv2_log", NXlog.class);
	}

	@Override
	public void setValue_deriv2_log(NXlog value_deriv2_log) {
		putChild("value_deriv2_log", value_deriv2_log);
	}

	@Override
	public IDataset getExternal_field_brief() {
		return getDataset(NX_EXTERNAL_FIELD_BRIEF);
	}

	@Override
	public String getExternal_field_briefScalar() {
		return getString(NX_EXTERNAL_FIELD_BRIEF);
	}

	@Override
	public DataNode setExternal_field_brief(IDataset external_field_brief) {
		return setDataset(NX_EXTERNAL_FIELD_BRIEF, external_field_brief);
	}

	@Override
	public DataNode setExternal_field_briefScalar(String external_field_brief) {
		return setString(NX_EXTERNAL_FIELD_BRIEF, external_field_brief);
	}

	@Override
	public NXorientation getExternal_field_full() {
		return getChild("external_field_full", NXorientation.class);
	}

	@Override
	public void setExternal_field_full(NXorientation external_field_full) {
		putChild("external_field_full", external_field_full);
	}

}
