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

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;
import org.eclipse.dawnsci.nexus.NXbeam;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusProvider;
import org.eclipse.dawnsci.nexus.builder.DataDevice;
import org.eclipse.dawnsci.nexus.builder.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryModification;

public class SimpleNexusFileBuilderTest extends AbstractNexusFileBuilderTestBase {
	
	private static class TestDetector extends AbstractNexusProvider<NXdetector> {

		public TestDetector() {
			super("analyser", NexusBaseClass.NX_DETECTOR, NXdetector.NX_DATA);
		}
		
		@Override
		protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
			final NXdetector nxDetector = nodeFactory.createNXdetector();

			nxDetector.setDescription(StringDataset.createFromObject("Test Detector"));
			nxDetector.initializeLazyDataset(NXdetector.NX_DATA, 2, Dataset.FLOAT64);
			// could add more fields

			return nxDetector;
		}

	}

	private static class TestBeam extends AbstractNexusProvider<NXbeam> {

		public TestBeam() {
			super("beam", NexusBaseClass.NX_BEAM, NexusBaseClass.NX_SAMPLE, null);
		}
		
		@Override
		protected NXbeam doCreateNexusObject(NexusNodeFactory nodeFactory) {
			final NXbeam beam = nodeFactory.createNXbeam();
			beam.setIncident_wavelength(DatasetFactory.createFromObject(123.456));
			beam.setFlux(DatasetFactory.createFromObject(12.34f));

			return beam;
		}

	}

	private static final String FILE_NAME = "simpleTestFile.nx5";
	
	private TestDetector detector;
	
	private TestBeam beam;

	protected String getFilename() {
		return FILE_NAME;
	}
	
	protected void addDataBuilder(NexusEntryBuilder entryModel) throws NexusException {
		NexusDataBuilder dataModel = entryModel.createDefaultData();
		dataModel.setPrimaryDevice(new DataDevice<>(detector, false));
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
