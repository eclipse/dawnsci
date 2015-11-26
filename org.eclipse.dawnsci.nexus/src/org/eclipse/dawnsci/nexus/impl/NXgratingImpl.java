/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-17T13:07:37.011Z
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
 * Description for a diffraction grating, as could be used in a soft X-ray monochromator
 * 
 * @version 1.0
 */
public class NXgratingImpl extends NXobjectImpl implements NXgrating {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_ANGLES = "angles";
	public static final String NX_PERIOD = "period";
	public static final String NX_DUTY_CYCLE = "duty_cycle";
	public static final String NX_DEPTH = "depth";
	public static final String NX_DIFFRACTION_ORDER = "diffraction_order";
	public static final String NX_DEFLECTION_ANGLE = "deflection_angle";
	public static final String NX_INTERIOR_ATMOSPHERE = "interior_atmosphere";
	public static final String NX_SUBSTRATE_MATERIAL = "substrate_material";
	public static final String NX_SUBSTRATE_DENSITY = "substrate_density";
	public static final String NX_SUBSTRATE_THICKNESS = "substrate_thickness";
	public static final String NX_COATING_MATERIAL = "coating_material";
	public static final String NX_SUBSTRATE_ROUGHNESS = "substrate_roughness";
	public static final String NX_COATING_ROUGHNESS = "coating_roughness";
	public static final String NX_LAYER_THICKNESS = "layer_thickness";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_SHAPE,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_TRANSFORMATIONS);

	protected NXgratingImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXgratingImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXgrating.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_GRATING;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getAngles() {
		return getDataset(NX_ANGLES);
	}

	@Override
	public double getAnglesScalar() {
		return getDouble(NX_ANGLES);
	}

	public DataNode setAngles(IDataset angles) {
		return setDataset(NX_ANGLES, angles);
	}

	public DataNode setAnglesScalar(double angles) {
		return setField(NX_ANGLES, angles);
	}

	@Override
	public IDataset getPeriod() {
		return getDataset(NX_PERIOD);
	}

	@Override
	public double getPeriodScalar() {
		return getDouble(NX_PERIOD);
	}

	public DataNode setPeriod(IDataset period) {
		return setDataset(NX_PERIOD, period);
	}

	public DataNode setPeriodScalar(double period) {
		return setField(NX_PERIOD, period);
	}

	@Override
	public IDataset getDuty_cycle() {
		return getDataset(NX_DUTY_CYCLE);
	}

	@Override
	public double getDuty_cycleScalar() {
		return getDouble(NX_DUTY_CYCLE);
	}

	public DataNode setDuty_cycle(IDataset duty_cycle) {
		return setDataset(NX_DUTY_CYCLE, duty_cycle);
	}

	public DataNode setDuty_cycleScalar(double duty_cycle) {
		return setField(NX_DUTY_CYCLE, duty_cycle);
	}

	@Override
	public IDataset getDepth() {
		return getDataset(NX_DEPTH);
	}

	@Override
	public double getDepthScalar() {
		return getDouble(NX_DEPTH);
	}

	public DataNode setDepth(IDataset depth) {
		return setDataset(NX_DEPTH, depth);
	}

	public DataNode setDepthScalar(double depth) {
		return setField(NX_DEPTH, depth);
	}

	@Override
	public IDataset getDiffraction_order() {
		return getDataset(NX_DIFFRACTION_ORDER);
	}

	@Override
	public long getDiffraction_orderScalar() {
		return getLong(NX_DIFFRACTION_ORDER);
	}

	public DataNode setDiffraction_order(IDataset diffraction_order) {
		return setDataset(NX_DIFFRACTION_ORDER, diffraction_order);
	}

	public DataNode setDiffraction_orderScalar(long diffraction_order) {
		return setField(NX_DIFFRACTION_ORDER, diffraction_order);
	}

	@Override
	public IDataset getDeflection_angle() {
		return getDataset(NX_DEFLECTION_ANGLE);
	}

	@Override
	public double getDeflection_angleScalar() {
		return getDouble(NX_DEFLECTION_ANGLE);
	}

	public DataNode setDeflection_angle(IDataset deflection_angle) {
		return setDataset(NX_DEFLECTION_ANGLE, deflection_angle);
	}

	public DataNode setDeflection_angleScalar(double deflection_angle) {
		return setField(NX_DEFLECTION_ANGLE, deflection_angle);
	}

	@Override
	public IDataset getInterior_atmosphere() {
		return getDataset(NX_INTERIOR_ATMOSPHERE);
	}

	@Override
	public String getInterior_atmosphereScalar() {
		return getString(NX_INTERIOR_ATMOSPHERE);
	}

	public DataNode setInterior_atmosphere(IDataset interior_atmosphere) {
		return setDataset(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	public DataNode setInterior_atmosphereScalar(String interior_atmosphere) {
		return setString(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
	public IDataset getSubstrate_material() {
		return getDataset(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public String getSubstrate_materialScalar() {
		return getString(NX_SUBSTRATE_MATERIAL);
	}

	public DataNode setSubstrate_material(IDataset substrate_material) {
		return setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	public DataNode setSubstrate_materialScalar(String substrate_material) {
		return setString(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_density() {
		return getDataset(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public double getSubstrate_densityScalar() {
		return getDouble(NX_SUBSTRATE_DENSITY);
	}

	public DataNode setSubstrate_density(IDataset substrate_density) {
		return setDataset(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	public DataNode setSubstrate_densityScalar(double substrate_density) {
		return setField(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	@Override
	public IDataset getSubstrate_thickness() {
		return getDataset(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public double getSubstrate_thicknessScalar() {
		return getDouble(NX_SUBSTRATE_THICKNESS);
	}

	public DataNode setSubstrate_thickness(IDataset substrate_thickness) {
		return setDataset(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	public DataNode setSubstrate_thicknessScalar(double substrate_thickness) {
		return setField(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public IDataset getCoating_material() {
		return getDataset(NX_COATING_MATERIAL);
	}

	@Override
	public String getCoating_materialScalar() {
		return getString(NX_COATING_MATERIAL);
	}

	public DataNode setCoating_material(IDataset coating_material) {
		return setDataset(NX_COATING_MATERIAL, coating_material);
	}

	public DataNode setCoating_materialScalar(String coating_material) {
		return setString(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public IDataset getSubstrate_roughness() {
		return getDataset(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public double getSubstrate_roughnessScalar() {
		return getDouble(NX_SUBSTRATE_ROUGHNESS);
	}

	public DataNode setSubstrate_roughness(IDataset substrate_roughness) {
		return setDataset(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	public DataNode setSubstrate_roughnessScalar(double substrate_roughness) {
		return setField(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public IDataset getCoating_roughness() {
		return getDataset(NX_COATING_ROUGHNESS);
	}

	@Override
	public double getCoating_roughnessScalar() {
		return getDouble(NX_COATING_ROUGHNESS);
	}

	public DataNode setCoating_roughness(IDataset coating_roughness) {
		return setDataset(NX_COATING_ROUGHNESS, coating_roughness);
	}

	public DataNode setCoating_roughnessScalar(double coating_roughness) {
		return setField(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public IDataset getLayer_thickness() {
		return getDataset(NX_LAYER_THICKNESS);
	}

	@Override
	public double getLayer_thicknessScalar() {
		return getDouble(NX_LAYER_THICKNESS);
	}

	public DataNode setLayer_thickness(IDataset layer_thickness) {
		return setDataset(NX_LAYER_THICKNESS, layer_thickness);
	}

	public DataNode setLayer_thicknessScalar(double layer_thickness) {
		return setField(NX_LAYER_THICKNESS, layer_thickness);
	}

	@Override
	public NXshape getShape() {
		return getChild("shape", NXshape.class);
	}

	public void setShape(NXshape shape) {
		putChild("shape", shape);
	}

	@Override
	public NXdata getFigure_data() {
		return getChild("figure_data", NXdata.class);
	}

	public void setFigure_data(NXdata figure_data) {
		putChild("figure_data", figure_data);
	}

	@Override
	public NXtransformations getTransformations() {
		return getChild("transformations", NXtransformations.class);
	}

	public void setTransformations(NXtransformations transformations) {
		putChild("transformations", transformations);
	}

	@Override
	public NXtransformations getTransformations(String name) {
		return getChild(name, NXtransformations.class);
	}

	public void setTransformations(String name, NXtransformations transformations) {
		putChild(name, transformations);
	}

	@Override
	public Map<String, NXtransformations> getAllTransformations() {
		return getChildren(NXtransformations.class);
	}

	public void setAllTransformations(Map<String, NXtransformations> transformations) {
		setChildren(transformations);
	}

}
