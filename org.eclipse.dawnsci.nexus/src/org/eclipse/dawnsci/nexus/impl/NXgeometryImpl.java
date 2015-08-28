/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * It is recommended that instances of NXgeometry be converted to
 * use :ref:`NXtransformations`.
 * This is the description for a general position of a component.
 * It is recommended to name an instance of NXgeometry as "geometry"
 * to aid in the use of the definition in simulation codes such as McStas.
 * Also, in HDF, linked items must share the same name.
 * However, it might not be possible or practical in all situations.
 * 
 * @version 1.0
 * @deprecated as decided at 2014 NIAC meeting, convert to use :ref:`NXtransformations`
 */
@Deprecated
public class NXgeometryImpl extends NXobjectImpl implements NXgeometry {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_COMPONENT_INDEX = "component_index";

	protected NXgeometryImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXgeometry.class;
	}

	@Override
	public NXshape getShape() {
		return getFirstChild(NXshape.class);
	}

	public void setShape(NXshape shape) {
		putChild(shape);
	}

	@Override
	public NXshape getShape(String name) {
		return getChild(name, NXshape.class);
	}

	public void setShape(String name, NXshape shape) {
		putChild(name, shape);
	}

	@Override
	public Map<String, NXshape> getAllShape() {
		return getChildren(NXshape.class);
	}

	public void setAllShape(Map<String, NXshape> shape) {
		setChildren(shape);
	}

	@Override
	public NXtranslation getTranslation() {
		return getFirstChild(NXtranslation.class);
	}

	public void setTranslation(NXtranslation translation) {
		putChild(translation);
	}

	@Override
	public NXtranslation getTranslation(String name) {
		return getChild(name, NXtranslation.class);
	}

	public void setTranslation(String name, NXtranslation translation) {
		putChild(name, translation);
	}

	@Override
	public Map<String, NXtranslation> getAllTranslation() {
		return getChildren(NXtranslation.class);
	}

	public void setAllTranslation(Map<String, NXtranslation> translation) {
		setChildren(translation);
	}

	@Override
	public NXorientation getOrientation() {
		return getFirstChild(NXorientation.class);
	}

	public void setOrientation(NXorientation orientation) {
		putChild(orientation);
	}

	@Override
	public NXorientation getOrientation(String name) {
		return getChild(name, NXorientation.class);
	}

	public void setOrientation(String name, NXorientation orientation) {
		putChild(name, orientation);
	}

	@Override
	public Map<String, NXorientation> getAllOrientation() {
		return getChildren(NXorientation.class);
	}

	public void setAllOrientation(Map<String, NXorientation> orientation) {
		setChildren(orientation);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getComponent_index() {
		return getDataset(NX_COMPONENT_INDEX);
	}

	public void setComponent_index(IDataset component_index) {
		setDataset(NX_COMPONENT_INDEX, component_index);
	}

}
