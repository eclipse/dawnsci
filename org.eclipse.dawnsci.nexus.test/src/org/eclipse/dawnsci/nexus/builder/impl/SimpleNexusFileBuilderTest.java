/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.builder.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;
import org.eclipse.dawnsci.nexus.NXbeam;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.data.DataDeviceBuilder;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;

public class SimpleNexusFileBuilderTest extends AbstractNexusFileBuilderTestBase {
	
	private static class TestDetector extends AbstractNexusObjectProvider<NXdetector> {

		public TestDetector() {
			super("analyser", NexusBaseClass.NX_DETECTOR, NXdetector.NX_DATA);
		}
		
		@Override
		protected NXdetector createNexusObject() {
			final NXdetector nxDetector = NexusNodeFactory.createNXdetector();

			nxDetector.setDescription(StringDataset.createFromObject("Test Detector"));
			nxDetector.initializeLazyDataset(NXdetector.NX_DATA, 2, Double.class);
			// could add more fields

			return nxDetector;
		}

	}

	private static class TestBeam extends AbstractNexusObjectProvider<NXbeam> {

		public TestBeam() {
			super("beam", NexusBaseClass.NX_BEAM);
			setCategory(NexusBaseClass.NX_SAMPLE);
		}
		
		@Override
		protected NXbeam createNexusObject() {
			final NXbeam beam = NexusNodeFactory.createNXbeam();
			beam.setIncident_wavelength(DatasetFactory.createFromObject(123.456));
			beam.setFlux(DatasetFactory.createFromObject(12.34f));

			return beam;
		}

	}

	private static final String FILE_NAME = "simpleTestFile.nxs";
	
	private TestDetector detector;
	
	private TestBeam beam;

	protected String getFilename() {
		return FILE_NAME;
	}
	
	protected void addDataBuilder(NexusEntryBuilder entryModel) throws NexusException {
		NexusDataBuilder dataModel = entryModel.createDefaultData();
		dataModel.setPrimaryDevice(DataDeviceBuilder.newPrimaryDataDevice(detector));
	}
	
	protected List<NexusEntryModification> getNexusTreeModifications() {
		detector = new TestDetector();
		beam = new TestBeam();

		final List<NexusEntryModification> modifications = new ArrayList<>();
		modifications.add(detector);
		modifications.add(beam);
		
		return modifications;
	}

	@Override
	protected String getTestClassName() {
		return SimpleNexusFileBuilderTest.class.getCanonicalName();
	}
	
}
