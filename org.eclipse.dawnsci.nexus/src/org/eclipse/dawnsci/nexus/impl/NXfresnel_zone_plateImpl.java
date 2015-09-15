/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

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

	protected NXfresnel_zone_plateImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXfresnel_zone_plate.class;
	}

	@Override
	public IDataset getFocus_parameters() {
		return getDataset(NX_FOCUS_PARAMETERS);
	}

	public void setFocus_parameters(IDataset focus_parameters) {
		setDataset(NX_FOCUS_PARAMETERS, focus_parameters);
	}

	@Override
	public IDataset getOuter_diameter() {
		return getDataset(NX_OUTER_DIAMETER);
	}

	public void setOuter_diameter(IDataset outer_diameter) {
		setDataset(NX_OUTER_DIAMETER, outer_diameter);
	}

	@Override
	public IDataset getOutermost_zone_width() {
		return getDataset(NX_OUTERMOST_ZONE_WIDTH);
	}

	public void setOutermost_zone_width(IDataset outermost_zone_width) {
		setDataset(NX_OUTERMOST_ZONE_WIDTH, outermost_zone_width);
	}

	@Override
	public IDataset getCentral_stop_diameter() {
		return getDataset(NX_CENTRAL_STOP_DIAMETER);
	}

	public void setCentral_stop_diameter(IDataset central_stop_diameter) {
		setDataset(NX_CENTRAL_STOP_DIAMETER, central_stop_diameter);
	}

	@Override
	public IDataset getFabrication() {
		return getDataset(NX_FABRICATION);
	}

	public void setFabrication(IDataset fabrication) {
		setDataset(NX_FABRICATION, fabrication);
	}

	@Override
	public IDataset getZone_height() {
		return getDataset(NX_ZONE_HEIGHT);
	}

	public void setZone_height(IDataset zone_height) {
		setDataset(NX_ZONE_HEIGHT, zone_height);
	}

	@Override
	public IDataset getZone_material() {
		return getDataset(NX_ZONE_MATERIAL);
	}

	public void setZone_material(IDataset zone_material) {
		setDataset(NX_ZONE_MATERIAL, zone_material);
	}

	@Override
	public IDataset getZone_support_material() {
		return getDataset(NX_ZONE_SUPPORT_MATERIAL);
	}

	public void setZone_support_material(IDataset zone_support_material) {
		setDataset(NX_ZONE_SUPPORT_MATERIAL, zone_support_material);
	}

	@Override
	public IDataset getCentral_stop_material() {
		return getDataset(NX_CENTRAL_STOP_MATERIAL);
	}

	public void setCentral_stop_material(IDataset central_stop_material) {
		setDataset(NX_CENTRAL_STOP_MATERIAL, central_stop_material);
	}

	@Override
	public IDataset getCentral_stop_thickness() {
		return getDataset(NX_CENTRAL_STOP_THICKNESS);
	}

	public void setCentral_stop_thickness(IDataset central_stop_thickness) {
		setDataset(NX_CENTRAL_STOP_THICKNESS, central_stop_thickness);
	}

	@Override
	public IDataset getMask_thickness() {
		return getDataset(NX_MASK_THICKNESS);
	}

	public void setMask_thickness(IDataset mask_thickness) {
		setDataset(NX_MASK_THICKNESS, mask_thickness);
	}

	@Override
	public IDataset getMask_material() {
		return getDataset(NX_MASK_MATERIAL);
	}

	public void setMask_material(IDataset mask_material) {
		setDataset(NX_MASK_MATERIAL, mask_material);
	}

	@Override
	public IDataset getSupport_membrane_material() {
		return getDataset(NX_SUPPORT_MEMBRANE_MATERIAL);
	}

	public void setSupport_membrane_material(IDataset support_membrane_material) {
		setDataset(NX_SUPPORT_MEMBRANE_MATERIAL, support_membrane_material);
	}

	@Override
	public IDataset getSupport_membrane_thickness() {
		return getDataset(NX_SUPPORT_MEMBRANE_THICKNESS);
	}

	public void setSupport_membrane_thickness(IDataset support_membrane_thickness) {
		setDataset(NX_SUPPORT_MEMBRANE_THICKNESS, support_membrane_thickness);
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
