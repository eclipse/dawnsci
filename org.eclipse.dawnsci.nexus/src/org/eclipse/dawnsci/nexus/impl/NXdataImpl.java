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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * .. index:: plotting
 * (**required**) ``NXdata`` is a template of
 * plottable data and their dimension scales.
 * It is mandatory that there is at least one ``NXdata`` group
 * in each NXentry group.
 * Note that the ``variable`` and ``data``
 * can be defined with different names.
 * The ``signal`` and ``axes`` attribute of the
 * ``data`` item define which items
 * are plottable data and which are *dimension scales*.
 * * Each ``NXdata`` group will consist of only one data set
 * containing plottable data and their standard deviations.
 * * This data set may be of arbitrary rank up to a maximum
 * of ``NX_MAXRANK=32``.
 * * The plottable data will be identified by the attribute:
 * ``signal=1``
 * * The plottable data will identify the *dimension scale*
 * specification(s) in the ``axes`` attribute.
 * If available, the standard deviations of the data are to be
 * stored in a data set of the same rank and dimensions, with the name ``errors``.
 * * For each data dimension, there should be a one-dimensional array
 * of the same length.
 * * These one-dimensional arrays are the *dimension scales* of the
 * data, *i.e*. the values of the independent variables at which the data
 * is measured, such as scattering angle or energy transfer.
 * .. index:: link
 * There are two methods of linking
 * each data dimension to its respective dimension scale.
 * .. index:: axes (attribute)
 * The preferred (and recommended) method uses the ``axes``
 * attribute to specify the names of each *dimension scale*.
 * The older method uses the ``axis`` attribute on each
 * *dimension scale*
 * to identify
 * with an integer the axis whose value is the number of the dimension.
 * ``NXdata`` is used to implement one of the basic motivations in NeXus,
 * to provide a default plot for the data of this ``NXentry``. The actual data
 * might be stored in another group and (hard) linked to the ``NXdata`` group.
 * 
 * @version 1.0
 */
public class NXdataImpl extends NXobjectImpl implements NXdata {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_VARIABLE = "variable";
	public static final String NX_VARIABLE_ATTRIBUTE_LONG_NAME = "long_name";
	public static final String NX_VARIABLE_ATTRIBUTE_DISTRIBUTION = "distribution";
	public static final String NX_VARIABLE_ATTRIBUTE_FIRST_GOOD = "first_good";
	public static final String NX_VARIABLE_ATTRIBUTE_LAST_GOOD = "last_good";
	public static final String NX_VARIABLE_ATTRIBUTE_AXIS = "axis";
	public static final String NX_VARIABLE_ERRORS = "variable_errors";
	public static final String NX_DATA = "data";
	public static final String NX_DATA_ATTRIBUTE_SIGNAL = "signal";
	public static final String NX_DATA_ATTRIBUTE_AXES = "axes";
	public static final String NX_DATA_ATTRIBUTE_UNCERTAINTIES = "uncertainties";
	public static final String NX_DATA_ATTRIBUTE_LONG_NAME = "long_name";
	public static final String NX_ERRORS = "errors";
	public static final String NX_SCALING_FACTOR = "scaling_factor";
	public static final String NX_OFFSET = "offset";
	public static final String NX_X = "x";
	public static final String NX_Y = "y";
	public static final String NX_Z = "z";

	protected NXdataImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXdataImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXdata.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_DATA;
	}

	@Override
	public IDataset getVariable() {
		return getDataset(NX_VARIABLE);
	}

	@Override
	public Number getScalarVariable() {
		return getNumber(NX_VARIABLE);
	}

	public void setVariable(IDataset variable) {
		setDataset(NX_VARIABLE, variable);
	}

	public void setScalarVariable(Number variable) {
		setField(NX_VARIABLE, variable);
	}

	@Override
	public String getVariableAttributeLong_name() {
		return getAttrString(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_LONG_NAME);
	}

	public void setVariableAttributeLong_name(String long_name) {
		setAttribute(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_LONG_NAME, long_name);
	}

	@Override
	public boolean getVariableAttributeDistribution() {
		return getAttrBoolean(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_DISTRIBUTION);
	}

	public void setVariableAttributeDistribution(boolean distribution) {
		setAttribute(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_DISTRIBUTION, distribution);
	}

	@Override
	public long getVariableAttributeFirst_good() {
		return getAttrLong(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_FIRST_GOOD);
	}

	public void setVariableAttributeFirst_good(long first_good) {
		setAttribute(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_FIRST_GOOD, first_good);
	}

	@Override
	public long getVariableAttributeLast_good() {
		return getAttrLong(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_LAST_GOOD);
	}

	public void setVariableAttributeLast_good(long last_good) {
		setAttribute(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_LAST_GOOD, last_good);
	}

	@Override
	public long getVariableAttributeAxis() {
		return getAttrLong(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_AXIS);
	}

	public void setVariableAttributeAxis(long axis) {
		setAttribute(NX_VARIABLE, NX_VARIABLE_ATTRIBUTE_AXIS, axis);
	}

	@Override
	public IDataset getVariable_errors() {
		return getDataset(NX_VARIABLE_ERRORS);
	}

	@Override
	public Number getScalarVariable_errors() {
		return getNumber(NX_VARIABLE_ERRORS);
	}

	public void setVariable_errors(IDataset variable_errors) {
		setDataset(NX_VARIABLE_ERRORS, variable_errors);
	}

	public void setScalarVariable_errors(Number variable_errors) {
		setField(NX_VARIABLE_ERRORS, variable_errors);
	}

	@Override
	public IDataset getData() {
		return getDataset(NX_DATA);
	}

	@Override
	public Number getScalarData() {
		return getNumber(NX_DATA);
	}

	public void setData(IDataset data) {
		setDataset(NX_DATA, data);
	}

	public void setScalarData(Number data) {
		setField(NX_DATA, data);
	}

	@Override
	public long getDataAttributeSignal() {
		return getAttrLong(NX_DATA, NX_DATA_ATTRIBUTE_SIGNAL);
	}

	public void setDataAttributeSignal(long signal) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_SIGNAL, signal);
	}

	@Override
	public String getDataAttributeAxes() {
		return getAttrString(NX_DATA, NX_DATA_ATTRIBUTE_AXES);
	}

	public void setDataAttributeAxes(String axes) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_AXES, axes);
	}

	@Override
	public String getDataAttributeUncertainties() {
		return getAttrString(NX_DATA, NX_DATA_ATTRIBUTE_UNCERTAINTIES);
	}

	public void setDataAttributeUncertainties(String uncertainties) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_UNCERTAINTIES, uncertainties);
	}

	@Override
	public String getDataAttributeLong_name() {
		return getAttrString(NX_DATA, NX_DATA_ATTRIBUTE_LONG_NAME);
	}

	public void setDataAttributeLong_name(String long_name) {
		setAttribute(NX_DATA, NX_DATA_ATTRIBUTE_LONG_NAME, long_name);
	}

	@Override
	public IDataset getErrors() {
		return getDataset(NX_ERRORS);
	}

	@Override
	public Number getScalarErrors() {
		return getNumber(NX_ERRORS);
	}

	public void setErrors(IDataset errors) {
		setDataset(NX_ERRORS, errors);
	}

	public void setScalarErrors(Number errors) {
		setField(NX_ERRORS, errors);
	}

	@Override
	public IDataset getScaling_factor() {
		return getDataset(NX_SCALING_FACTOR);
	}

	@Override
	public double getScalarScaling_factor() {
		return getDouble(NX_SCALING_FACTOR);
	}

	public void setScaling_factor(IDataset scaling_factor) {
		setDataset(NX_SCALING_FACTOR, scaling_factor);
	}

	public void setScalarScaling_factor(double scaling_factor) {
		setField(NX_SCALING_FACTOR, scaling_factor);
	}

	@Override
	public IDataset getOffset() {
		return getDataset(NX_OFFSET);
	}

	@Override
	public double getScalarOffset() {
		return getDouble(NX_OFFSET);
	}

	public void setOffset(IDataset offset) {
		setDataset(NX_OFFSET, offset);
	}

	public void setScalarOffset(double offset) {
		setField(NX_OFFSET, offset);
	}

	@Override
	public IDataset getX() {
		return getDataset(NX_X);
	}

	@Override
	public double getScalarX() {
		return getDouble(NX_X);
	}

	public void setX(IDataset x) {
		setDataset(NX_X, x);
	}

	public void setScalarX(double x) {
		setField(NX_X, x);
	}

	@Override
	public IDataset getY() {
		return getDataset(NX_Y);
	}

	@Override
	public double getScalarY() {
		return getDouble(NX_Y);
	}

	public void setY(IDataset y) {
		setDataset(NX_Y, y);
	}

	public void setScalarY(double y) {
		setField(NX_Y, y);
	}

	@Override
	public IDataset getZ() {
		return getDataset(NX_Z);
	}

	@Override
	public double getScalarZ() {
		return getDouble(NX_Z);
	}

	public void setZ(IDataset z) {
		setDataset(NX_Z, z);
	}

	public void setScalarZ(double z) {
		setField(NX_Z, z);
	}

}
