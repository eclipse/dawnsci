/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-09-28T15:24:07.968+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * A diffraction grating, as could be used in a soft X-ray monochromator
 * 
 * @version 1.0
 */
public class NXgratingImpl extends NXobjectImpl implements NXgrating {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_SHAPE,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_TRANSFORMATIONS);

	public NXgratingImpl() {
		super();
	}

	public NXgratingImpl(final long oid) {
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
	public Double getAnglesScalar() {
		return getDouble(NX_ANGLES);
	}

	@Override
	public DataNode setAngles(IDataset angles) {
		return setDataset(NX_ANGLES, angles);
	}

	@Override
	public DataNode setAnglesScalar(Double angles) {
		return setField(NX_ANGLES, angles);
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
	public IDataset getDuty_cycle() {
		return getDataset(NX_DUTY_CYCLE);
	}

	@Override
	public Double getDuty_cycleScalar() {
		return getDouble(NX_DUTY_CYCLE);
	}

	@Override
	public DataNode setDuty_cycle(IDataset duty_cycle) {
		return setDataset(NX_DUTY_CYCLE, duty_cycle);
	}

	@Override
	public DataNode setDuty_cycleScalar(Double duty_cycle) {
		return setField(NX_DUTY_CYCLE, duty_cycle);
	}

	@Override
	public IDataset getDepth() {
		return getDataset(NX_DEPTH);
	}

	@Override
	public Double getDepthScalar() {
		return getDouble(NX_DEPTH);
	}

	@Override
	public DataNode setDepth(IDataset depth) {
		return setDataset(NX_DEPTH, depth);
	}

	@Override
	public DataNode setDepthScalar(Double depth) {
		return setField(NX_DEPTH, depth);
	}

	@Override
	public IDataset getDiffraction_order() {
		return getDataset(NX_DIFFRACTION_ORDER);
	}

	@Override
	public Long getDiffraction_orderScalar() {
		return getLong(NX_DIFFRACTION_ORDER);
	}

	@Override
	public DataNode setDiffraction_order(IDataset diffraction_order) {
		return setDataset(NX_DIFFRACTION_ORDER, diffraction_order);
	}

	@Override
	public DataNode setDiffraction_orderScalar(Long diffraction_order) {
		return setField(NX_DIFFRACTION_ORDER, diffraction_order);
	}

	@Override
	public IDataset getDeflection_angle() {
		return getDataset(NX_DEFLECTION_ANGLE);
	}

	@Override
	public Double getDeflection_angleScalar() {
		return getDouble(NX_DEFLECTION_ANGLE);
	}

	@Override
	public DataNode setDeflection_angle(IDataset deflection_angle) {
		return setDataset(NX_DEFLECTION_ANGLE, deflection_angle);
	}

	@Override
	public DataNode setDeflection_angleScalar(Double deflection_angle) {
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

	@Override
	public DataNode setInterior_atmosphere(IDataset interior_atmosphere) {
		return setDataset(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
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

	@Override
	public DataNode setSubstrate_material(IDataset substrate_material) {
		return setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public DataNode setSubstrate_materialScalar(String substrate_material) {
		return setString(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_density() {
		return getDataset(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public Double getSubstrate_densityScalar() {
		return getDouble(NX_SUBSTRATE_DENSITY);
	}

	@Override
	public DataNode setSubstrate_density(IDataset substrate_density) {
		return setDataset(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	@Override
	public DataNode setSubstrate_densityScalar(Double substrate_density) {
		return setField(NX_SUBSTRATE_DENSITY, substrate_density);
	}

	@Override
	public IDataset getSubstrate_thickness() {
		return getDataset(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public Double getSubstrate_thicknessScalar() {
		return getDouble(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public DataNode setSubstrate_thickness(IDataset substrate_thickness) {
		return setDataset(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public DataNode setSubstrate_thicknessScalar(Double substrate_thickness) {
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

	@Override
	public DataNode setCoating_material(IDataset coating_material) {
		return setDataset(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public DataNode setCoating_materialScalar(String coating_material) {
		return setString(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public IDataset getSubstrate_roughness() {
		return getDataset(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public Double getSubstrate_roughnessScalar() {
		return getDouble(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public DataNode setSubstrate_roughness(IDataset substrate_roughness) {
		return setDataset(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public DataNode setSubstrate_roughnessScalar(Double substrate_roughness) {
		return setField(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public IDataset getCoating_roughness() {
		return getDataset(NX_COATING_ROUGHNESS);
	}

	@Override
	public Double getCoating_roughnessScalar() {
		return getDouble(NX_COATING_ROUGHNESS);
	}

	@Override
	public DataNode setCoating_roughness(IDataset coating_roughness) {
		return setDataset(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public DataNode setCoating_roughnessScalar(Double coating_roughness) {
		return setField(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public IDataset getLayer_thickness() {
		return getDataset(NX_LAYER_THICKNESS);
	}

	@Override
	public Double getLayer_thicknessScalar() {
		return getDouble(NX_LAYER_THICKNESS);
	}

	@Override
	public DataNode setLayer_thickness(IDataset layer_thickness) {
		return setDataset(NX_LAYER_THICKNESS, layer_thickness);
	}

	@Override
	public DataNode setLayer_thicknessScalar(Double layer_thickness) {
		return setField(NX_LAYER_THICKNESS, layer_thickness);
	}

	@Override
	public NXshape getShape() {
		return getChild("shape", NXshape.class);
	}

	@Override
	public void setShape(NXshape shape) {
		putChild("shape", shape);
	}

	@Override
	public NXdata getFigure_data() {
		return getChild("figure_data", NXdata.class);
	}

	@Override
	public void setFigure_data(NXdata figure_data) {
		putChild("figure_data", figure_data);
	}

	@Override
	public NXtransformations getTransformations() {
		return getChild("transformations", NXtransformations.class);
	}

	@Override
	public void setTransformations(NXtransformations transformations) {
		putChild("transformations", transformations);
	}

	@Override
	public NXtransformations getTransformations(String name) {
		return getChild(name, NXtransformations.class);
	}

	@Override
	public void setTransformations(String name, NXtransformations transformations) {
		putChild(name, transformations);
	}

	@Override
	public Map<String, NXtransformations> getAllTransformations() {
		return getChildren(NXtransformations.class);
	}
	
	@Override
	public void setAllTransformations(Map<String, NXtransformations> transformations) {
		setChildren(transformations);
	}

}
