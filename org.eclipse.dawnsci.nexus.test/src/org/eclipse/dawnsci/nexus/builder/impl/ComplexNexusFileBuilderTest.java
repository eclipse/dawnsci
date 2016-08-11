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

import org.eclipse.dawnsci.nexus.NXcollection;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.CustomNexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.appdef.impl.TomoApplicationBuilder;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;


public class ComplexNexusFileBuilderTest extends AbstractNexusFileBuilderTestBase {
	
	private static class SimplePositioner extends AbstractNexusObjectProvider<NXpositioner> {

		public SimplePositioner(final String name) {
			super(name, NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}
		
		@Override
		public NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.initializeLazyDataset(NXpositioner.NX_VALUE, 1, Double.class);
			
			return positioner;
		}

	}
	
	private static final class TomoScanDevicePositioner extends AbstractNexusObjectProvider<NXpositioner> {

		public TomoScanDevicePositioner() {
			super("tomoScanDevice", NexusBaseClass.NX_POSITIONER, "ss1_rot");
			setUseDeviceNameInNXdata(false);
			setAxisDataFieldNames("imageNumber", "image_key", "ss1_X", "ss1_rot", "tomography_shutter");
		}
		
		@Override
		public NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.initializeLazyDataset("imageNumber", 1, Double.class);
			positioner.initializeLazyDataset("image_key", 1, Double.class);
			positioner.initializeLazyDataset("ss1_X", 1, Double.class);
			positioner.initializeLazyDataset("ss1_rot", 1, Double.class);
			positioner.initializeLazyDataset("tomography_shutter", 1, Double.class);
			
			return positioner;
		}

	}
	
	private static class TestDetector extends AbstractNexusObjectProvider<NXdetector> {
		
		public TestDetector() {
			super("pc01_hw_hdf", NexusBaseClass.NX_DETECTOR, NXdetector.NX_DATA,
					NXdetector.NX_COUNT_TIME, "start_time", "time_ms");
		}
		
		@Override
		public NXdetector createNexusObject() {
			final NXdetector detector = NexusNodeFactory.createNXdetector();
			
			detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Short.class);
			detector.initializeLazyDataset(NXdetector.NX_COUNT_TIME, 1, Double.class);
			IDataset regionOrigin = DatasetFactory.createFromObject(new int[] {0, 0}, 1, 2);
			detector.setField("region_origin", regionOrigin);
			IDataset regionSize = DatasetFactory.createFromObject(new int[] {2560, 2160}, 1, 2);
			detector.setField("region_size", regionSize);
			detector.initializeLazyDataset("start_time", 1, Double.class);
			detector.initializeLazyDataset("time_ms", 1, Long.class); // unsigned int 32 in original nexus file
			// image_key required by NXtomo application definition
			detector.initializeLazyDataset("image_key", 1, Integer.class);
			detector.setAttribute("image_key", "target", "/entry/instrument/pc01_hw_hdf/image_key");
			
			return detector;
		}

	}
	
	private static class TestSource extends AbstractNexusObjectProvider<NXsource> {

		public TestSource() {
			super("source", NexusBaseClass.NX_SOURCE);
		}

		@Override
		protected NXsource createNexusObject() {
			final NXsource source = NexusNodeFactory.createNXsource();
			source.setCurrentScalar(-1.0);
			source.setEnergyScalar(-1.0);
			source.setNameScalar("DLS");
			source.setProbeScalar("X-ray");
			source.setTypeScalar("type");
			
			return source;
		}
		
	}
	
	/**
	 * In the real world the before_scan collection is used by GDA to store additional data it needs.
	 * In a real world system this provider could fetch the details from the scan.
	 */
	private static class BeforeScan extends AbstractNexusObjectProvider<NXcollection> {

		public BeforeScan() {
			super("before_scan", NexusBaseClass.NX_COLLECTION);
		}

		@Override
		protected NXcollection createNexusObject() {
			NXcollection beforeScan = NexusNodeFactory.createNXcollection();
			
			// Create cs1 collection: 
			NXcollection cs1 = NexusNodeFactory.createNXcollection();
			beforeScan.addGroupNode("cs1", cs1);
			cs1.setAttribute(null, "metadata_type", "scannable");	
			
			String[] cs1FieldNames = new String[] { "cs1_x", "cs1_y", "cs1_z" };
			double[] cs1FieldValues = new double[] { 202.2512, 0.15, -976.472 };
			for (int i = 0; i < cs1FieldNames.length; i++) {
				cs1.setField(cs1FieldNames[i], cs1FieldValues[i]);
				cs1.setAttribute(cs1FieldNames[i], "field_type", "input");
				cs1.setAttribute(cs1FieldNames[i], "format", "%5.5g");
				cs1.setAttribute(cs1FieldNames[i], "units", "mm");
			}
			
			// Create sample stage collection
			NXcollection sampleStage = NexusNodeFactory.createNXcollection();
			beforeScan.addGroupNode("sample_stage", sampleStage);
			String[] ss1FieldNames = new String[] { "ss1_X", "ss1_Y", "ss1_Z", "ss1_rot",
					"ss1_samplex", "ss1_sampley", "ss1_samplez" };
			double[] ss1FieldValues = new double[] { -976.472, 9.9764, 25.1812, -88.2, 1365.111, -3900.095, 75.966 };
			String[] units = new String[] { "mm", "mm", "mm", "deg", "um", "um", "um" };
			for (int i = 0; i < ss1FieldNames.length; i++) {
				sampleStage.setField(ss1FieldNames[i], ss1FieldValues[i]);
				sampleStage.setAttribute(ss1FieldNames[i], "field_type", "input");
				sampleStage.setAttribute(ss1FieldNames[i], "units", units[i]);
				sampleStage.setAttribute(ss1FieldNames[i], "format", "%5.5g");
				if (ss1FieldNames[i].contains("sample")) {
					sampleStage.setAttribute(ss1FieldNames[i], "target",
							"/entry/before_scan/sample_stage/" + ss1FieldValues[i]);
				}
			}
			
			return beforeScan;
		}
		
	}
	
	private static final String FILE_NAME = "complexTestFile.nxs";
	
	@Override
	protected String getFilename() {
		return FILE_NAME;
	}
	
	private SimplePositioner actualTimePositioner;
	
	private SimplePositioner beamOkPositioner;
	
	private SimplePositioner ioncIPositioner;
	
	private TestDetector testDetector;
	
	private TestSource testSource;
	
	private BeforeScan beforeScan;
	
	private TomoScanDevicePositioner tomoScanDevicePositioner;
	
	private NexusUser user;

	private MapBasedMetadataProvider scanData;

	@Override
	protected List<NexusEntryModification> getNexusTreeModifications() {
		beforeScan = new BeforeScan();
		actualTimePositioner = new SimplePositioner("actualTime");
		beamOkPositioner = new SimplePositioner("beakok");
		ioncIPositioner = new SimplePositioner("ionc_i");
		tomoScanDevicePositioner = new TomoScanDevicePositioner();
		testDetector = new TestDetector();
		testSource = new TestSource();
		user = new NexusUser("user01");
		user.setUsername("myusername");
		
		scanData = new MapBasedMetadataProvider();
		scanData.addMetadataEntry("entry_identifier", "24737");
		scanData.addMetadataEntry("experiment_identifier", "nt9396-1");
		scanData.addMetadataEntry("program_name", "GDA 8.36.0");
		scanData.addMetadataEntry("scan_command", "scan tomoScanDevice Start: -88.200000 Stop: 91.800000 Step: 2.000000 Darks every:0 imagesPerDark:5 Flats every:0 imagesPerFlat:5 InBeamPosition:11.150000 OutOfBeamPosition:5.000000 numImages 111  actualTime ionc_i pco1_hw_hdf 0.1 beamok");
		scanData.addMetadataEntry("scan_dimensions", 111);
		scanData.addMetadataEntry("scan_identifier", "a3d668c0-e3c4-4ed9-b127-4a202b2b6bac");
		scanData.addMetadataEntry("title", "AKingUVA_7050wSSwire_InSitu_95RH_2MMgCl2_p4ul_p4h");
		
		List<NexusEntryModification> nexusObjects = new ArrayList<>();
		nexusObjects.add(beforeScan);
		nexusObjects.add(scanData);
		nexusObjects.add(actualTimePositioner);
		nexusObjects.add(beamOkPositioner);
		nexusObjects.add(ioncIPositioner);
		nexusObjects.add(testDetector);
		nexusObjects.add(testSource);
		nexusObjects.add(tomoScanDevicePositioner);
		nexusObjects.add(user);
		nexusObjects.add(new CustomNexusEntryModification() {
			
			@Override
			public void modifyEntry(NXentry entry) {
				entry.setAttribute("title", "target", "/entry/title");
			}
		});
		
		return nexusObjects;
	}
	
	@Override
	protected void configureEntryModel(NexusEntryBuilder nexusEntryModel) {
		nexusEntryModel.setInstrumentName("i13");
	}

	@Override
	protected void addDataBuilder(NexusEntryBuilder entryModel) throws NexusException {
		NexusDataBuilder dataBuilder = entryModel.newData(testDetector.getName());
		dataBuilder.setPrimaryDevice(testDetector);
		
		dataBuilder.addAxisDevice(tomoScanDevicePositioner, 0);
		dataBuilder.addAxisDevice(actualTimePositioner);
		dataBuilder.addAxisDevice(beamOkPositioner);
		dataBuilder.addAxisDevice(ioncIPositioner);

		// TODO, add these fields as part of the detector (primary) device
		// TODO also add region_origin and region_size
//		AxisDataDevice<NXdetector> detectorAxisDevice = new DataDevice<>(testDetector), null, 0);
//		detectorAxisDevice.setSourceFields("count_time", "start_time", "time_ms");
//		detectorAxisDevice.set
//		detectorAxisDevice.setIsPrimary(true);
//		dataBuilder.addDataDevice(detectorAxisDevice);
	}
	
	protected void addApplicationDefinitions(NexusEntryBuilder nexusEntryModel) throws NexusException {
		TomoApplicationBuilder appDefModel =
				(TomoApplicationBuilder) nexusEntryModel.newApplication(NexusApplicationDefinition.NX_TOMO);
		appDefModel.addDefaultGroups();
		appDefModel.setTitle(nexusEntryModel.getDataNode(NXentry.NX_TITLE));
		appDefModel.setControl(ioncIPositioner);
		appDefModel.setSource(testSource);
		appDefModel.setDetector(testDetector);
		appDefModel.setSampleName("test sample");
		appDefModel.setRotationAngle(tomoScanDevicePositioner);
		appDefModel.setXTranslation(nexusEntryModel.getDataNode("before_scan/sample_stage/ss1_samplex"));
		appDefModel.setYTranslation(nexusEntryModel.getDataNode("before_scan/sample_stage/ss1_sampley"));
		appDefModel.setZTranslation(nexusEntryModel.getDataNode("before_scan/sample_stage/ss1_samplez"));
		
		appDefModel.newData(); 
	}

	@Override
	protected String getTestClassName() {
		return ComplexNexusFileBuilderTest.class.getCanonicalName();
	}

}
