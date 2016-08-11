/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.IDataset;
import org.junit.Before;

public class ComplexNexusFileTest extends AbstractNexusFileTestBase {

	private static final String FILE_NAME = "complex.nxs";

	private Map<String, Object> testData;

	private NXroot root;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		setupTestData();
	}

	private void setupTestData() {
		final int size = 10;
		final int[] shape = { size };

		testData = new HashMap<String, Object>();
		testData.put("cs1_x", 202.2512);
		testData.put("cs1_y", 0.15);
		testData.put("cs1_z", -976.472);
		testData.put("ss1_X", 11.5);
		testData.put("ss1_Y", 9.9764);
		testData.put("ss1_Z", 25.1812);
		testData.put("ss1_rot", -88.2);
		testData.put("ss1_samplex", 1365.111); // rounded
		testData.put("ss1_sampley", -3900.095); // rounded
		testData.put("ss1_samplez", 75.966); // rounded
		testData.put("actualTime", createDoubleDataset(
				1.39112723398E9, 1.391127234478E9, 1.391127234673E9, 1.391127234871E9, 1.391127235068E9,
				1.391127248978E9, 1.391127249481E9, 1.391127249751E9, 1.391127249953E9, 1.391127250154E9));
		testData.put("beamok", DatasetFactory.ones(shape, Dataset.FLOAT64));
		testData.put("ionc_i", createDoubleDataset(
				8.374020580781096, 8.370683990350841, 8.370854377232897, 8.373055360469023, 8.375409630828424,
				8.363466060032364, 8.361367064643199, 8.360248862560692, 8.358109255244436, 8.358963327124483));
		testData.put("count_time", createFilledDataset(0.1, size));
		testData.put("data", createDetectorDataDataset());
		testData.put("region_origin", DatasetFactory.zeros(new int[] { 1,  2 }, Dataset.FLOAT32));
		testData.put("region_size", create2DIntDataset(new int[][] { { 2560, 2160 } }));
		testData.put("start_time", createDoubleDataset(
				8.779, 9.277, 9.473, 9.670, 9.867, 23.777, 24.282, 24.532, 24.748, 24.950));
		testData.put("time_ms", createLongDataset( // note: this dataset has type unsigned 32-bit integer in the source nexus file
				3852797190l, 3852797688l, 3852797884l, 3852798081l, 3852798278l,
				3852712188l, 3852812693l, 3852812943l, 3852813159l, 3852813361l));
		testData.put("current", createDoubleDataset(-1.0));
		testData.put("energy", createDoubleDataset(-1.0));
		testData.put("sourceName", "DLS");
		testData.put("sourceProbe", "X-ray");
		testData.put("sourceType", "Synchotron X-Ray Source");

		testData.put("imageNumber", DatasetFactory.createRange(9.0, Dataset.FLOAT64));
		testData.put("image_key", createDoubleDataset(2.0, 2.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0));
		testData.put("tomoScanDevice.ss1_X", createDoubleDataset(11.150060000000002, 11.15, 11.15, 11.15, 11.15,
				5.00002, 5.0, 5.0, 5.0, 5.0));
		testData.put("tomoScanDevice.ss1_rot", createFilledDataset(-88.2, 10));
		testData.put("tomography_shutter", createDoubleDataset(0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, 1.0, 1.0, 1.0));
	}
	
	private IDataset getTestData(String name) {
		Object data = testData.get(name);
		if (data instanceof IDataset) {
			return (IDataset) data;
		} else if (data != null) {
			return DatasetFactory.createFromObject(data);
		}

		throw new IllegalArgumentException("No such dataset: " + name);
	}

	private static IDataset createDoubleDataset(double... values) {
		return DatasetFactory.createFromObject(values);
	}

	private static IDataset createIntegerDataset(int... values) {
		return DatasetFactory.createFromObject(values);
	}

	private static IDataset createLongDataset(long... values) {
		return DatasetFactory.createFromObject(values);
	}


	private static Dataset createFilledDataset(final double value, final int size) {
		DoubleDataset dataset = DatasetFactory.zeros(DoubleDataset.class, size);
		dataset.fill(value);
		return dataset;
	}

	private static Dataset create2DIntDataset(final int[][] values) {
		return DatasetFactory.createFromObject(values);
	}

	private static Dataset create2DFloatDataset(final float[][] values) {
		return DatasetFactory.createFromObject(values);
	}

	private IDataset createDetectorDataDataset() {
		final int[] shape = new int[] { 10, 10, 10 };
		final int totalSize = 1000;
		// in this test case, same 2d 10x10 data is used in each of the 10 'pages' (third dimension)
		final short[][] data = new short[][] {
				{ 0, 94, 97, 98, 97, 122, 98, 95, 95, 100 },
				{ 0, 96, 95, 104, 100, 121, 103, 95, 96, 94, 90 },
				{ 0, 88, 89, 102, 99, 93, 99, 95, 97, 92, 93 },
				{ 0, 96, 91, 98, 100, 93, 100, 96, 97, 96 },
				{ 0, 92, 91, 99, 95, 95, 95, 97, 99, 96, 96 },
				{ 0, 29261, 29994, 30354, 30325, 30621, 30357, 30148, 30915, 30818 },
				{ 0, 29775, 29996, 30644, 30213, 30879, 29915, 30666, 30786, 30990 },
				{ 0, 29792, 29606, 30125, 30327, 30651, 30095, 30387, 30457, 30804 },
				{ 0, 29659, 30340, 30736, 29566, 30741, 30312, 30655, 30458, 31115 },
				{ 0, 29750, 30138, 29977, 30468, 30942, 29739, 30694, 30401, 31084 }};
		
		short[] flatArray = new short[totalSize];
		int pageOffset = 0;
		for (int page = 0; page < 10; page++) {
			int rowOffset = pageOffset;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					flatArray[rowOffset + j] = data[i][j];
				}
				rowOffset += 10;
			}
			pageOffset += 100;
		}
		
		return DatasetFactory.createFromObject(flatArray, shape);
	}
	
	private DataNode getDataNode(final String path) {
		return (DataNode) root.findNodeLink(path).getDestination();
	}

	private NXcollection createCS1Collection() {
		NXcollection cs1Collection = NexusNodeFactory.createNXcollection();

		final String[] csFieldNames = new String[] { "cs1_x", "cs1_y", "cs1_z" };
		for (String fieldName : csFieldNames) {
			IDataset value = getTestData(fieldName);
			cs1Collection.setDataset(fieldName, value);
			cs1Collection.setAttribute(fieldName, "field_type", "input");
			cs1Collection.setAttribute(fieldName, "format", "%5.5g");
			cs1Collection.setAttribute(fieldName, "units", "mm");
		}

		return cs1Collection;
	}

	private NXcollection createSampleStageCollection() {
		NXcollection sampleStageCollection = NexusNodeFactory.createNXcollection();

		String[] sampleStageFields = new String[] { "ss1_X", "ss1_Y", "ss1_Z", "ss1_rot", "ss1_samplex", "ss1_sampley", "ss1_samplez" };
		for (String fieldName : sampleStageFields) {
			IDataset value = getTestData(fieldName);
			sampleStageCollection.setField(fieldName, value);
			sampleStageCollection.setAttribute(fieldName, "field_type", "input");
			sampleStageCollection.setAttribute(fieldName, "format", "%5.5g");
			String units;
			if (fieldName.contains("sample")) {
				units = "um";
				sampleStageCollection.setAttribute(fieldName, "target", "entry1/before_scan/sample_stage/" + fieldName);
			} else if (fieldName.equals("ss1_rot")) {
				units = "deg";
			} else {
				units = "mm";
			}
			sampleStageCollection.setAttribute(fieldName, "units", units);
		}

		return sampleStageCollection;
	}

	private NXinstrument createInstrument() {
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();

		// actualTime : NXpositioner
		NXpositioner actualTimePositioner = NexusNodeFactory.createNXpositioner();
		instrument.setPositioner("actualTime", actualTimePositioner);
		actualTimePositioner.setDataset("actualTime", getTestData("actualTime"));
		actualTimePositioner.setAttribute("actualTime", "local_name", "actualTime.actualTime");
		actualTimePositioner.setAttribute("actualTime", "target", "/entry1/instrument/actualTime/actualTime");

		// beamok : NXpositioner
		NXpositioner beamokPositioner = NexusNodeFactory.createNXpositioner();
		instrument.setPositioner("beamok", beamokPositioner);
		beamokPositioner.setDataset("beamok", getTestData("beamok"));
		beamokPositioner.setAttribute("beamok", "local_name", "beamok.beamok");
		beamokPositioner.setAttribute("beamok", "target", "/entry1/instrument/beamok/beamok");

		// icon_i : NXpositioner
		NXpositioner ioncIPositioner = NexusNodeFactory.createNXpositioner();
		instrument.setPositioner("ionc_i", ioncIPositioner);
		// use a range for the dataset (not the actual data from the example file)
		ioncIPositioner.setDataset("ionc_i", getTestData("ionc_i"));
		ioncIPositioner.setAttribute("ionc_i", "local_name", "ionc_i.ionc_i");
		ioncIPositioner.setAttribute("ionc_i", "target", "/entry1/instrument/ionc_i/ionc_i");

		instrument.setName(DatasetFactory.createFromObject("i13"));

		// pc01_hw_hdf: NXdetector
		NXdetector pco1HwHdfDectector = NexusNodeFactory.createNXdetector();
		instrument.setDetector("pco1_hw_hdf", pco1HwHdfDectector);

		pco1HwHdfDectector.setDataset("count_time", getTestData("count_time"));
		pco1HwHdfDectector.setAttribute("count_time", "local_name", "pc01_hw_hdf.count_time");
		pco1HwHdfDectector.setAttribute("count_time", "target", "/entry1/instrument/pco1_hw_hdf");

		pco1HwHdfDectector.setData(getTestData("data"));
		pco1HwHdfDectector.setAttribute("data", "target", "/entry1/instrument/pco1_hw_hdf/data");

		pco1HwHdfDectector.setDataset("region_origin", getTestData("region_origin"));
		pco1HwHdfDectector.setAttribute("region_origin", "target", "/entry1/instrument/pco1_hw_hdf/region_origin");
		pco1HwHdfDectector.setDataset("region_size", getTestData("region_size"));
		pco1HwHdfDectector.setAttribute("region_size", "target", "/entry1/instrument/pco1_hw_hdf/region_size");

		pco1HwHdfDectector.setDataset("start_time", getTestData("start_time"));
		pco1HwHdfDectector.setAttribute("start_time", "start", "2014-01-31T00:13:45");
		pco1HwHdfDectector.setAttribute("start_time", "units", "s");
		pco1HwHdfDectector.setAttribute("start_time", "target", "/entry1/instrument/pco1_hw_hdf/start_time");

		pco1HwHdfDectector.setDataset("time_ms", getTestData("time_ms"));
		pco1HwHdfDectector.setAttribute("time_ms", "units", "ms");
		pco1HwHdfDectector.setAttribute("time_ms", "target", "/entry1/instrument/pco1_hw_hdf/time_ms");

		// source : NXsource
		NXsource source = NexusNodeFactory.createNXsource();
		instrument.setSource(source);
		source.setDataset("current", getTestData("current"));
		source.setAttribute("current", "units", "mA");

		source.setDataset("energy", getTestData("energy"));
		source.setAttribute("energy", "units", "GeV");

		source.setName(getTestData("sourceName"));
		source.setProbe(getTestData("sourceProbe"));
		source.setType(getTestData("sourceType"));

		// tomoScanDevice : NXpositioner
		NXpositioner tomoScanDevice = NexusNodeFactory.createNXpositioner();
		instrument.setPositioner("tomoScanDevice", tomoScanDevice);
		tomoScanDevice.setDataset("imageNumber", getTestData("imageNumber"));
		tomoScanDevice.setAttribute("imageNumber", "axis", "1");
		tomoScanDevice.setAttribute("imageNumber", "local_name", "tomoScanDevice.imageNumber");
		tomoScanDevice.setAttribute("imageNumber", "target", "/entry1/instrument/tomoScanDevice/imageNumber");

		tomoScanDevice.setDataset("image_key", getTestData("image_key"));
		tomoScanDevice.setAttribute("image_key", "axis", "1");
		tomoScanDevice.setAttribute("image_key", "local_name", "tomoScanDevice.image_key");
		tomoScanDevice.setAttribute("image_key", "target", "/entry1/instrument/tomoScanDevice/image_key");

		tomoScanDevice.setDataset("ss1_X", getTestData("tomoScanDevice.ss1_X"));
		tomoScanDevice.setAttribute("ss1_X", "axis", "1");
		tomoScanDevice.setAttribute("ss1_X", "local_name", "tomoScanDevice.ss1_X");
		tomoScanDevice.setAttribute("ss1_X", "target", "/entry1/instrument/tomoScanDevice/ss1_X");

		tomoScanDevice.setDataset("ss1_rot", getTestData("tomoScanDevice.ss1_rot"));
		tomoScanDevice.setAttribute("ss1_rot", "axis", "1");
		tomoScanDevice.setAttribute("ss1_rot", "label", "1");
		tomoScanDevice.setAttribute("ss1_rot", "local_name", "tomoScanDevice.ss1_rot");
		tomoScanDevice.setAttribute("ss1_rot", "primary", "1");
		tomoScanDevice.setAttribute("ss1_rot", "target", "/entry1/instrument/tomoScanDevice/ss1_rot");

		tomoScanDevice.setDataset("tomography_shutter", getTestData("tomography_shutter"));
		tomoScanDevice.setAttribute("tomography_shutter", "axis", "1");
		tomoScanDevice.setAttribute("tomography_shutter", "local_name", "tomoScanDevice.tomography_shutter");
		tomoScanDevice.setAttribute("tomography_shutter", "target", "/entry1/instrument/tomoScanDevice/tomography_shutter");

		return instrument;
	}

	private NXdata createPco1HwHdfData() {
		NXdata data = NexusNodeFactory.createNXdata();

		data.addDataNode("actualTime", getDataNode("/entry1/instrument/actualTime/actualTime"));
		data.addDataNode("beamok", getDataNode("/entry1/instrument/beamok/beamok"));
		data.addDataNode("count_time", getDataNode("/entry1/instrument/pco1_hw_hdf/count_time"));
		data.addDataNode("data", getDataNode("/entry1/instrument/pco1_hw_hdf/data"));
		data.addDataNode("imageNumber", getDataNode("/entry1/instrument/tomoScanDevice/imageNumber"));
		data.addDataNode("image_key", getDataNode("/entry1/instrument/tomoScanDevice/image_key"));
		data.addDataNode("ionc_i", getDataNode("/entry1/instrument/ionc_i/ionc_i"));
		data.addDataNode("region_origin", getDataNode("/entry1/instrument/pco1_hw_hdf/region_origin"));
		data.addDataNode("region_size", getDataNode("/entry1/instrument/pco1_hw_hdf/region_size"));
		data.addDataNode("ss1_X", getDataNode("/entry1/instrument/tomoScanDevice/ss1_X"));
		data.addDataNode("ss1_rot", getDataNode("/entry1/instrument/tomoScanDevice/ss1_rot"));
		data.addDataNode("start_time", getDataNode("/entry1/instrument/pco1_hw_hdf/start_time"));
		data.addDataNode("time_ms", getDataNode("/entry1/instrument/pco1_hw_hdf/time_ms"));
		data.addDataNode("tomography_shutter", getDataNode("/entry1/instrument/tomoScanDevice/tomography_shutter"));
		return data;
	}

	private NXsubentry createTomoEntry() {
		NXsubentry tomoEntry = NexusNodeFactory.createNXsubentry();

		// control : NXmonitor
		NXmonitor controlMonitor = NexusNodeFactory.createNXmonitor();
		tomoEntry.setMonitor("control", controlMonitor);
		controlMonitor.addDataNode("data", getDataNode("entry1/instrument/ionc_i/ionc_i"));

		// data : NXdata
		NXdata dataGroupNode = NexusNodeFactory.createNXdata();
		tomoEntry.setData(dataGroupNode);

		dataGroupNode.addDataNode("data", getDataNode("/entry1/instrument/pco1_hw_hdf/data"));
		dataGroupNode.addDataNode("rotation_angle", getDataNode("/entry1/instrument/tomoScanDevice/ss1_rot"));

		tomoEntry.setDefinition(DatasetFactory.createFromObject("definition"));

		// instrument : NXinstrument
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		tomoEntry.setInstrument(instrument);

		// detector : NXdetector
		NXdetector detector = NexusNodeFactory.createNXdetector();
		instrument.setDetector(detector);
		// tomo_entry/instrument/detector/data has the same data as tomo_entry/data/data
		detector.addDataNode("data", dataGroupNode.getDataNode("data"));
		// note some of these links in the original file are strange
		detector.addDataNode("distance", getDataNode("/entry1/scan_identifier"));

		detector.addDataNode("image_key", getDataNode("/entry1/instrument/tomoScanDevice/image_key"));
		detector.addDataNode("x_pixel_size", getDataNode("/entry1/scan_identifier"));
		detector.addDataNode("y_pixel_size", getDataNode("/entry1/scan_identifier"));

		// source : NXsource - hardlink to /entry1/instument/source
		NXsource source = (NXsource) root.findNodeLink("/entry1/instrument/source").getDestination();
		instrument.addGroupNode("source", source);

		// sample : NXsample
		NXsample sample = NexusNodeFactory.createNXsample();
		tomoEntry.setSample(sample);
		sample.addDataNode("rotation_angle", getDataNode("/entry1/instrument/tomoScanDevice/ss1_rot"));
		sample.addDataNode("x_translation", getDataNode("/entry1/before_scan/sample_stage/ss1_samplex"));
		sample.addDataNode("y_translation", getDataNode("/entry1/before_scan/sample_stage/ss1_sampley"));
		sample.addDataNode("z_translation", getDataNode("/entry1/before_scan/sample_stage/ss1_samplez"));

		tomoEntry.addDataNode("title", getDataNode("/entry1/title"));

		return tomoEntry;
	}

	@Override
	protected NXroot createNXroot() {
		root = NexusNodeFactory.createNXroot();

		// create the single entry object of the nexus file
		NXentry entry1 = NexusNodeFactory.createNXentry();
		root.setEntry("entry1", entry1);
		// TODO, should we set any attributes on the root, or are they only set when a file is loaded?
		assertNotNull(root.getEntry("entry1"));

		NXcollection beforeScanCollection = NexusNodeFactory.createNXcollection();
		entry1.setCollection("before_scan", beforeScanCollection);

		NXcollection cs1Collection = createCS1Collection();
		beforeScanCollection.addGroupNode("cs1", cs1Collection);

		NXcollection sampleStage = createSampleStageCollection();
		beforeScanCollection.addGroupNode("sample_stage", sampleStage);

		entry1.setEntry_identifier(DatasetFactory.createFromObject("24737"));
		entry1.setExperiment_identifier(DatasetFactory.createFromObject("mt9396-1"));

		NXinstrument instrument = createInstrument();
		entry1.setInstrument(instrument);

		NXdata pco1HwHdfData = createPco1HwHdfData();
		entry1.setData("pco1_hw_hdf", pco1HwHdfData);

		entry1.setProgram_name(DatasetFactory.createFromObject("GDA 8.36.0"));
		entry1.setField("scan_command", "scan tomoScanDevice Start: -88.200000 Stop: 91.800000 Step: 2.000000 Darks every:0 imagesPerDark:5 Flats every:0 imagesPerFlat:5 InBeamPosition:11.150000 OutOfBeamPosition:5.000000 numImages 111  actualTime ionc_i pco1_hw_hdf 0.1 beamok");
		entry1.setField("scan_dimensions", 111);
		entry1.setField("scan_identifier", "a3d668c0-e3c4-4ed9-b127-4a202b2b6bac");
		entry1.setAttribute("scan_identifier", "target", "/entry1/scan_identifier");

		entry1.setField("title", "AKingUVA_705wSSwire_InSitu_95RH_2MMgCI2_p4ul_p4h");
		entry1.setAttribute("title", "target", "/entry1/title");

		NXsubentry tomoEntry = createTomoEntry();
		entry1.setSubentry("tomo_entry", tomoEntry);

		NXuser user = NexusNodeFactory.createNXuser();
		entry1.setUser(user);
		user.setField("username", "ssg37927");

		return root;
	}

	@Override
	protected String getFilename() {
		return FILE_NAME;
	}

}
