/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

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

	public void setAngles(IDataset angles) {
		setDataset(NX_ANGLES, angles);
	}

	public void setAnglesScalar(double angles) {
		setField(NX_ANGLES, angles);
	}

	@Override
	public IDataset getPeriod() {
		return getDataset(NX_PERIOD);
	}

	@Override
	public double getPeriodScalar() {
		return getDouble(NX_PERIOD);
	}

	public void setPeriod(IDataset period) {
		setDataset(NX_PERIOD, period);
	}

	public void setPeriodScalar(double period) {
		setField(NX_PERIOD, period);
	}

	@Override
	public IDataset getDuty_cycle() {
		return getDataset(NX_DUTY_CYCLE);
	}

	@Override
	public double getDuty_cycleScalar() {
		return getDouble(NX_DUTY_CYCLE);
	}

	public void setDuty_cycle(IDataset duty_cycle) {
		setDataset(NX_DUTY_CYCLE, duty_cycle);
	}

	public void setDuty_cycleScalar(double duty_cycle) {
		setField(NX_DUTY_CYCLE, duty_cycle);
	}

	@Override
	public IDataset getDepth() {
		return getDataset(NX_DEPTH);
	}

	@Override
	public double getDepthScalar() {
		return getDouble(NX_DEPTH);
	}

	public void setDepth(IDataset depth) {
		setDataset(NX_DEPTH, depth);
	}

	public void setDepthScalar(double depth) {
		setField(NX_DEPTH, depth);
	}

	@Override
	public IDataset getDiffraction_order() {
		return getDataset(NX_DIFFRACTION_ORDER);
	}

	@Override
	public long getDiffraction_orderScalar() {
		return getLong(NX_DIFFRACTION_ORDER);
	}

	public void setDiffraction_order(IDataset diffraction_order) {
		setDataset(NX_DIFFRACTION_ORDER, diffraction_order);
	}

	public void setDiffraction_orderScalar(long diffraction_order) {
		setField(NX_DIFFRACTION_ORDER, diffraction_order);
	}

	@Override
	public IDataset getDeflection_angle() {
		return getDataset(NX_DEFLECTION_ANGLE);
	}

	@Override
	public double getDeflection_angleScalar() {
		return getDouble(NX_DEFLECTION_ANGLE);
	}

	public void setDeflection_angle(IDataset deflection_angle) {
		setDataset(NX_DEFLECTION_ANGLE, deflection_angle);
	}

	public void setDeflection_angleScalar(double deflection_angle) {
		setField(NX_DEFLECTION_ANGLE, deflection_angle);
	}

	@Override
	public IDataset getInterior_atmosphere() {
		return getDataset(NX_INTERIOR_ATMOSPHERE);
	}

	@Override
	public String getInterior_atmosphereScalar() {
		return getString(NX_INTERIOR_ATMOSPHERE);
	}

	public void setInterior_atmosphere(IDataset interior_atmosphere) {
		setDataset(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	public void setInterior_atmosphereScalar(String interior_atmosphere) {
		setString(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
	public IDataset getSubstrate_material() {
		return getDataset(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public String getSubstrate_materialScalar() {
		return getString(NX_SUBSTRATE_MATERIAL);
	}

	public void setSubstrate_material(IDataset substrate_material) {
		setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	public void setSubstrate_materialScalar(String substrate_material) {
		setString(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_density() {
		return getDataset(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public double getSubstrate_densityScalar() {
		return getDouble(NX_SUBSTRATE_DENSITY);
	}

	public void setSubstrate_density(IDataset substrate_density) {
		setDataset(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	public void setSubstrate_densityScalar(double substrate_density) {
		setField(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	@Override
	public IDataset getSubstrate_thickness() {
		return getDataset(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public double getSubstrate_thicknessScalar() {
		return getDouble(NX_SUBSTRATE_THICKNESS);
	}

	public void setSubstrate_thickness(IDataset substrate_thickness) {
		setDataset(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	public void setSubstrate_thicknessScalar(double substrate_thickness) {
		setField(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public IDataset getCoating_material() {
		return getDataset(NX_COATING_MATERIAL);
	}

	@Override
	public String getCoating_materialScalar() {
		return getString(NX_COATING_MATERIAL);
	}

	public void setCoating_material(IDataset coating_material) {
		setDataset(NX_COATING_MATERIAL, coating_material);
	}

	public void setCoating_materialScalar(String coating_material) {
		setString(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public IDataset getSubstrate_roughness() {
		return getDataset(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public double getSubstrate_roughnessScalar() {
		return getDouble(NX_SUBSTRATE_ROUGHNESS);
	}

	public void setSubstrate_roughness(IDataset substrate_roughness) {
		setDataset(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	public void setSubstrate_roughnessScalar(double substrate_roughness) {
		setField(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public IDataset getCoating_roughness() {
		return getDataset(NX_COATING_ROUGHNESS);
	}

	@Override
	public double getCoating_roughnessScalar() {
		return getDouble(NX_COATING_ROUGHNESS);
	}

	public void setCoating_roughness(IDataset coating_roughness) {
		setDataset(NX_COATING_ROUGHNESS, coating_roughness);
	}

	public void setCoating_roughnessScalar(double coating_roughness) {
		setField(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public IDataset getLayer_thickness() {
		return getDataset(NX_LAYER_THICKNESS);
	}

	@Override
	public double getLayer_thicknessScalar() {
		return getDouble(NX_LAYER_THICKNESS);
	}

	public void setLayer_thickness(IDataset layer_thickness) {
		setDataset(NX_LAYER_THICKNESS, layer_thickness);
	}

	public void setLayer_thicknessScalar(double layer_thickness) {
		setField(NX_LAYER_THICKNESS, layer_thickness);
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
