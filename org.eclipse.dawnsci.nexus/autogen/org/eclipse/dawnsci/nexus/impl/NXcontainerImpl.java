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
 * State of a container holding the sample under investigation.
 * A container is any object in the beam path which absorbs the beam and
 * whose contribution to the overall attenuation/scattering needs to be
 * determined to process the experimental data. Examples of containers
 * include glass capillary tubes, vanadium cans, windows in furnaces or
 * diamonds in a Diamond Anvil Cell. The following figures show a complex
 * example of a container:
 * .. figure:: container/ComplexExampleContainer.png
 * A hypothetical capillary furnace. The beam passes from left to right
 * (blue dashes), passing through window 1, then window 2, before
 * passing through the downstream wall of the capillary. It is then
 * scattered by the sample with scattered beams passing through the
 * upstream wall of the capillary, then windows 4 and 5. As part of the
 * corrections for a PDF experiment it is necessary to subtract the PDF
 * of the empty container (i.e. each of the windows and the capillary).
 * To calculate the PDF of the empty container it is necessary to have
 * the measured scattering data and to know the nature (e.g. density,
 * elemental composition, etc.) of the portion of the container which
 * the beam passed through.
 * .. figure:: container/ComplexContainerBeampath.png
 * A complete description of the shapes of the container elements with
 * their orientation relative to the beam and also information on
 * whether they are upstream or downstream of the sample is also
 * therefore important. For example, although the windows 2 and 4 have
 * the same shape, the path taken through them by the beam is very
 * different and this needs to be modelled. Furthermore, it is not
 * inconceivable that windows might move during an experiment and thus
 * the changes to the beampath would need to be accounted for.
 * This class encodes the position of the container with respect to the
 * sample and allows the calculation of the beampath through the container.
 * It also includes sufficient data to model beam absorption of the
 * container and a link to a dataset containing a measurement of the
 * container with nothing inside, to allow data corrections (at a specific
 * beam energy/measurement time) to be made.
 * 
 * @version 1.0
 */
public class NXcontainerImpl extends NXobjectImpl implements NXcontainer {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_BEAM,
		NexusBaseClass.NX_SHAPE,
		NexusBaseClass.NX_TRANSFORMATIONS);

	public NXcontainerImpl() {
		super();
	}

	public NXcontainerImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcontainer.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_CONTAINER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	@Override
	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	@Override
	public String getChemical_formulaScalar() {
		return getString(NX_CHEMICAL_FORMULA);
	}

	@Override
	public DataNode setChemical_formula(IDataset chemical_formula) {
		return setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public DataNode setChemical_formulaScalar(String chemical_formula) {
		return setString(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public double getDensityScalar() {
		return getDouble(NX_DENSITY);
	}

	@Override
	public DataNode setDensity(IDataset density) {
		return setDataset(NX_DENSITY, density);
	}

	@Override
	public DataNode setDensityScalar(double density) {
		return setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getPacking_fraction() {
		return getDataset(NX_PACKING_FRACTION);
	}

	@Override
	public double getPacking_fractionScalar() {
		return getDouble(NX_PACKING_FRACTION);
	}

	@Override
	public DataNode setPacking_fraction(IDataset packing_fraction) {
		return setDataset(NX_PACKING_FRACTION, packing_fraction);
	}

	@Override
	public DataNode setPacking_fractionScalar(double packing_fraction) {
		return setField(NX_PACKING_FRACTION, packing_fraction);
	}

	@Override
	public IDataset getRelative_molecular_mass() {
		return getDataset(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public double getRelative_molecular_massScalar() {
		return getDouble(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public DataNode setRelative_molecular_mass(IDataset relative_molecular_mass) {
		return setDataset(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public DataNode setRelative_molecular_massScalar(double relative_molecular_mass) {
		return setField(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public NXbeam getBeam() {
		return getChild("beam", NXbeam.class);
	}

	@Override
	public void setBeam(NXbeam beam) {
		putChild("beam", beam);
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
	public NXtransformations getOrientation() {
		return getChild("orientation", NXtransformations.class);
	}

	@Override
	public void setOrientation(NXtransformations orientation) {
		putChild("orientation", orientation);
	}
	// Unprocessed link: reference_measurement

}
