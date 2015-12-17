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
 * description for a fresnel zone plate
 * 
 * @version 1.0
 */
public class NXfresnel_zone_plateImpl extends NXobjectImpl implements NXfresnel_zone_plate {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_FOCUS_PARAMETERS = "focus_parameters";
	public static final String NX_OUTER_DIAMETER = "outer_diameter";
	public static final String NX_OUTERMOST_ZONE_WIDTH = "outermost_zone_width";
	public static final String NX_CENTRAL_STOP_DIAMETER = "central_stop_diameter";
	public static final String NX_FABRICATION = "fabrication";
	public static final String NX_ZONE_HEIGHT = "zone_height";
	public static final String NX_ZONE_MATERIAL = "zone_material";
	public static final String NX_ZONE_SUPPORT_MATERIAL = "zone_support_material";
	public static final String NX_CENTRAL_STOP_MATERIAL = "central_stop_material";
	public static final String NX_CENTRAL_STOP_THICKNESS = "central_stop_thickness";
	public static final String NX_MASK_THICKNESS = "mask_thickness";
	public static final String NX_MASK_MATERIAL = "mask_material";
	public static final String NX_SUPPORT_MEMBRANE_MATERIAL = "support_membrane_material";
	public static final String NX_SUPPORT_MEMBRANE_THICKNESS = "support_membrane_thickness";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_TRANSFORMATIONS);

	protected NXfresnel_zone_plateImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXfresnel_zone_plateImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXfresnel_zone_plate.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_FRESNEL_ZONE_PLATE;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getFocus_parameters() {
		return getDataset(NX_FOCUS_PARAMETERS);
	}

	@Override
	public double getFocus_parametersScalar() {
		return getDouble(NX_FOCUS_PARAMETERS);
	}

	public DataNode setFocus_parameters(IDataset focus_parameters) {
		return setDataset(NX_FOCUS_PARAMETERS, focus_parameters);
	}

	public DataNode setFocus_parametersScalar(double focus_parameters) {
		return setField(NX_FOCUS_PARAMETERS, focus_parameters);
	}

	@Override
	public IDataset getOuter_diameter() {
		return getDataset(NX_OUTER_DIAMETER);
	}

	@Override
	public double getOuter_diameterScalar() {
		return getDouble(NX_OUTER_DIAMETER);
	}

	public DataNode setOuter_diameter(IDataset outer_diameter) {
		return setDataset(NX_OUTER_DIAMETER, outer_diameter);
	}

	public DataNode setOuter_diameterScalar(double outer_diameter) {
		return setField(NX_OUTER_DIAMETER, outer_diameter);
	}

	@Override
	public IDataset getOutermost_zone_width() {
		return getDataset(NX_OUTERMOST_ZONE_WIDTH);
	}

	@Override
	public double getOutermost_zone_widthScalar() {
		return getDouble(NX_OUTERMOST_ZONE_WIDTH);
	}

	public DataNode setOutermost_zone_width(IDataset outermost_zone_width) {
		return setDataset(NX_OUTERMOST_ZONE_WIDTH, outermost_zone_width);
	}

	public DataNode setOutermost_zone_widthScalar(double outermost_zone_width) {
		return setField(NX_OUTERMOST_ZONE_WIDTH, outermost_zone_width);
	}

	@Override
	public IDataset getCentral_stop_diameter() {
		return getDataset(NX_CENTRAL_STOP_DIAMETER);
	}

	@Override
	public double getCentral_stop_diameterScalar() {
		return getDouble(NX_CENTRAL_STOP_DIAMETER);
	}

	public DataNode setCentral_stop_diameter(IDataset central_stop_diameter) {
		return setDataset(NX_CENTRAL_STOP_DIAMETER, central_stop_diameter);
	}

	public DataNode setCentral_stop_diameterScalar(double central_stop_diameter) {
		return setField(NX_CENTRAL_STOP_DIAMETER, central_stop_diameter);
	}

	@Override
	public IDataset getFabrication() {
		return getDataset(NX_FABRICATION);
	}

	@Override
	public String getFabricationScalar() {
		return getString(NX_FABRICATION);
	}

	public DataNode setFabrication(IDataset fabrication) {
		return setDataset(NX_FABRICATION, fabrication);
	}

	public DataNode setFabricationScalar(String fabrication) {
		return setString(NX_FABRICATION, fabrication);
	}

	@Override
	public IDataset getZone_height() {
		return getDataset(NX_ZONE_HEIGHT);
	}

	@Override
	public double getZone_heightScalar() {
		return getDouble(NX_ZONE_HEIGHT);
	}

	public DataNode setZone_height(IDataset zone_height) {
		return setDataset(NX_ZONE_HEIGHT, zone_height);
	}

	public DataNode setZone_heightScalar(double zone_height) {
		return setField(NX_ZONE_HEIGHT, zone_height);
	}

	@Override
	public IDataset getZone_material() {
		return getDataset(NX_ZONE_MATERIAL);
	}

	@Override
	public String getZone_materialScalar() {
		return getString(NX_ZONE_MATERIAL);
	}

	public DataNode setZone_material(IDataset zone_material) {
		return setDataset(NX_ZONE_MATERIAL, zone_material);
	}

	public DataNode setZone_materialScalar(String zone_material) {
		return setString(NX_ZONE_MATERIAL, zone_material);
	}

	@Override
	public IDataset getZone_support_material() {
		return getDataset(NX_ZONE_SUPPORT_MATERIAL);
	}

	@Override
	public String getZone_support_materialScalar() {
		return getString(NX_ZONE_SUPPORT_MATERIAL);
	}

	public DataNode setZone_support_material(IDataset zone_support_material) {
		return setDataset(NX_ZONE_SUPPORT_MATERIAL, zone_support_material);
	}

	public DataNode setZone_support_materialScalar(String zone_support_material) {
		return setString(NX_ZONE_SUPPORT_MATERIAL, zone_support_material);
	}

	@Override
	public IDataset getCentral_stop_material() {
		return getDataset(NX_CENTRAL_STOP_MATERIAL);
	}

	@Override
	public String getCentral_stop_materialScalar() {
		return getString(NX_CENTRAL_STOP_MATERIAL);
	}

	public DataNode setCentral_stop_material(IDataset central_stop_material) {
		return setDataset(NX_CENTRAL_STOP_MATERIAL, central_stop_material);
	}

	public DataNode setCentral_stop_materialScalar(String central_stop_material) {
		return setString(NX_CENTRAL_STOP_MATERIAL, central_stop_material);
	}

	@Override
	public IDataset getCentral_stop_thickness() {
		return getDataset(NX_CENTRAL_STOP_THICKNESS);
	}

	@Override
	public double getCentral_stop_thicknessScalar() {
		return getDouble(NX_CENTRAL_STOP_THICKNESS);
	}

	public DataNode setCentral_stop_thickness(IDataset central_stop_thickness) {
		return setDataset(NX_CENTRAL_STOP_THICKNESS, central_stop_thickness);
	}

	public DataNode setCentral_stop_thicknessScalar(double central_stop_thickness) {
		return setField(NX_CENTRAL_STOP_THICKNESS, central_stop_thickness);
	}

	@Override
	public IDataset getMask_thickness() {
		return getDataset(NX_MASK_THICKNESS);
	}

	@Override
	public double getMask_thicknessScalar() {
		return getDouble(NX_MASK_THICKNESS);
	}

	public DataNode setMask_thickness(IDataset mask_thickness) {
		return setDataset(NX_MASK_THICKNESS, mask_thickness);
	}

	public DataNode setMask_thicknessScalar(double mask_thickness) {
		return setField(NX_MASK_THICKNESS, mask_thickness);
	}

	@Override
	public IDataset getMask_material() {
		return getDataset(NX_MASK_MATERIAL);
	}

	@Override
	public String getMask_materialScalar() {
		return getString(NX_MASK_MATERIAL);
	}

	public DataNode setMask_material(IDataset mask_material) {
		return setDataset(NX_MASK_MATERIAL, mask_material);
	}

	public DataNode setMask_materialScalar(String mask_material) {
		return setString(NX_MASK_MATERIAL, mask_material);
	}

	@Override
	public IDataset getSupport_membrane_material() {
		return getDataset(NX_SUPPORT_MEMBRANE_MATERIAL);
	}

	@Override
	public String getSupport_membrane_materialScalar() {
		return getString(NX_SUPPORT_MEMBRANE_MATERIAL);
	}

	public DataNode setSupport_membrane_material(IDataset support_membrane_material) {
		return setDataset(NX_SUPPORT_MEMBRANE_MATERIAL, support_membrane_material);
	}

	public DataNode setSupport_membrane_materialScalar(String support_membrane_material) {
		return setString(NX_SUPPORT_MEMBRANE_MATERIAL, support_membrane_material);
	}

	@Override
	public IDataset getSupport_membrane_thickness() {
		return getDataset(NX_SUPPORT_MEMBRANE_THICKNESS);
	}

	@Override
	public double getSupport_membrane_thicknessScalar() {
		return getDouble(NX_SUPPORT_MEMBRANE_THICKNESS);
	}

	public DataNode setSupport_membrane_thickness(IDataset support_membrane_thickness) {
		return setDataset(NX_SUPPORT_MEMBRANE_THICKNESS, support_membrane_thickness);
	}

	public DataNode setSupport_membrane_thicknessScalar(double support_membrane_thickness) {
		return setField(NX_SUPPORT_MEMBRANE_THICKNESS, support_membrane_thickness);
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
