package org.eclipse.dawnsci.nexus.builder.appdef.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusFileBuilder;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;
import org.junit.Before;
import org.junit.Test;

public class TomoApplicationBuilderTest {

	public static class TestPositioner extends AbstractNexusObjectProvider<NXpositioner> {
		
		public TestPositioner() {
			super("positioner", NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}
		
		public TestPositioner(String name) {
			super(name, NexusBaseClass.NX_POSITIONER);
		}
		
		@Override
		protected NXpositioner createNexusObject() {
			NXpositioner positioner = NexusNodeFactory.createNXpositioner();
			positioner.initializeLazyDataset(NXpositioner.NX_VALUE, 1, Double.class);
			return positioner;
		}
		
	}
	
	private TomoApplicationBuilder tomoBuilder;
	
	private NXsubentry subentry;
	
	@Before
	public void setUp() throws Exception {
		NexusFileBuilder fileBuilder = new DefaultNexusFileBuilder("test");
		NexusEntryBuilder entryBuilder = fileBuilder.newEntry();
		tomoBuilder = (TomoApplicationBuilder) entryBuilder.newApplication(NexusApplicationDefinition.NX_TOMO);
		subentry = tomoBuilder.getNXsubentry();
	}
	
	@Test
	public void testSetTitle_string() {
		tomoBuilder.setTitle("tomo appdef");
		assertThat(subentry.getTitleScalar(), is(equalTo("tomo appdef")));
	}
	
	@Test
	public void testSetTitle_dataNode() {
		DataNode dataNode = NexusNodeFactory.createDataNode();
		dataNode.setDataset(DatasetFactory.createFromObject("tomo appdef"));
		tomoBuilder.setTitle(dataNode);
		assertThat(subentry.getTitleScalar(), is(equalTo("tomo appdef")));
	}
	
	@Test
	public void testSetSource() throws Exception {
		tomoBuilder.addDefaultGroups();
		NexusObjectProvider<NXsource> sourceProvider = new AbstractNexusObjectProvider<NXsource>(
				"source", NexusBaseClass.NX_SOURCE) {

			@Override
			protected NXsource createNexusObject() {
				NXsource source = NexusNodeFactory.createNXsource();
				source.setTypeScalar("Synchotron X-Ray source");
				source.setNameScalar("DLS");
				source.setProbeScalar("x-ray");
				return source;
			}
		};
		
		tomoBuilder.setSource(sourceProvider);
		NXsource source = subentry.getInstrument().getSource();
		assertThat(source, is(notNullValue()));
		assertThat(source.getTypeScalar(), is("Synchotron X-Ray source"));
		assertThat(source.getNameScalar(), is("DLS"));
	}
	
	@Test
	public void testSetDetector() throws Exception {
		tomoBuilder.addDefaultGroups();
		NexusObjectProvider<NXdetector> detectorProvider =
				new AbstractNexusObjectProvider<NXdetector>("detector", NexusBaseClass.NX_DETECTOR) {

			@Override
			protected NXdetector createNexusObject() {
				NXdetector detector = NexusNodeFactory.createNXdetector();
				detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Double.class);
				detector.initializeLazyDataset("image_key", 1, Short.class);
				detector.setX_pixel_sizeScalar(1.5);
				detector.setY_pixel_sizeScalar(2.5);
				detector.setDistanceScalar(0.75);
				detector.setField("x_rotation_axis_pixel_position", 123.456);
				detector.setField("y_rotation_axis_pixel_position", 654.321);
				
				return detector;
			}
			
		};
		
		tomoBuilder.setDetector(detectorProvider);
		NXdetector detector = subentry.getInstrument().getDetector();
		assertThat(detector, is(sameInstance(detectorProvider.getNexusObject())));
	}
	
	@Test
	public void testSetSample() throws NexusException {
		tomoBuilder.addDefaultGroups();
		NexusObjectProvider<NXsample> sampleProvider = new AbstractNexusObjectProvider<NXsample>(
				"sample", NexusBaseClass.NX_SAMPLE) {

			@Override
			protected NXsample createNexusObject() {
				NXsample sample = NexusNodeFactory.createNXsample();
				sample.setNameScalar("my sample");
				sample.setRotation_angleScalar(123.456);
				sample.setX_translationScalar(23.432);
				sample.setField("y_translation", 79.238);
				sample.setField("z_translation", 129.752);
				
				return sample;
			}
			
		};
		
		tomoBuilder.setSample(sampleProvider);
		NXsample sample = subentry.getSample();
		assertThat(sample, is(sameInstance(sampleProvider.getNexusObject())));
	}
	
	@Test
	public void testSetSampleName() {
		tomoBuilder.addDefaultGroups();
		tomoBuilder.setSampleName("test sample");
		assertThat(subentry.getSample().getNameScalar(), is(equalTo("test sample")));
	}
	
	@Test
	public void testSetRotationAngle_positioner() throws NexusException {
		tomoBuilder.addDefaultGroups();
		TestPositioner rotationAnglePositioner = new TestPositioner();
		tomoBuilder.setRotationAngle(rotationAnglePositioner);
		assertThat(subentry.getSample().getDataNode(NXsample.NX_ROTATION_ANGLE),
				is(sameInstance(rotationAnglePositioner.getNexusObject().getDataNode(
						NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testSetRotationAngle_dataNode() throws NexusException {
		tomoBuilder.addDefaultGroups();
		DataNode rotationAngle = NexusNodeFactory.createDataNode();
		ILazyWriteableDataset dataset = new LazyWriteableDataset("rotation_angle", Dataset.FLOAT64, new int[] { 100 }, null, null, null);
		rotationAngle.setDataset(dataset);
		tomoBuilder.setRotationAngle(rotationAngle);

		assertThat(subentry.getSample().getDataNode(NXsample.NX_ROTATION_ANGLE),
				is(sameInstance(rotationAngle)));
	}
	
	@Test
	public void testSetXTranslation_positioner() throws NexusException {
		tomoBuilder.addDefaultGroups();
		TestPositioner xPositioner = new TestPositioner();
		tomoBuilder.setXTranslation(xPositioner);
		assertThat(subentry.getSample().getDataNode(NXsample.NX_X_TRANSLATION),
				is(sameInstance(xPositioner.getNexusObject().getDataNode(
						NXpositioner.NX_VALUE))));

	}
	
	@Test
	public void testSetXTranslation_dataNode() throws NexusException {
		tomoBuilder.addDefaultGroups();
		DataNode xTranslation = NexusNodeFactory.createDataNode();
		ILazyWriteableDataset dataset = new LazyWriteableDataset("x_translation", Dataset.FLOAT64, new int[] { 100 }, null, null, null);
		xTranslation.setDataset(dataset);
		tomoBuilder.setXTranslation(xTranslation);

		assertThat(subentry.getSample().getDataNode(NXsample.NX_X_TRANSLATION),
				is(sameInstance(xTranslation)));
	}
	
	@Test
	public void testSetYTranslation_positioner() throws NexusException {
		tomoBuilder.addDefaultGroups();
		TestPositioner yPositioner = new TestPositioner();
		tomoBuilder.setYTranslation(yPositioner);
		assertThat(subentry.getSample().getDataNode("y_translation"),
				is(sameInstance(yPositioner.getNexusObject().getDataNode(
						NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testSetYTranslation_dataNode() throws NexusException {
		tomoBuilder.addDefaultGroups();
		DataNode yTranslation = NexusNodeFactory.createDataNode();
		ILazyWriteableDataset dataset = new LazyWriteableDataset("x_translation", Dataset.FLOAT64, new int[] { 100 }, null, null, null);
		yTranslation.setDataset(dataset);
		tomoBuilder.setYTranslation(yTranslation);

		assertThat(subentry.getSample().getDataNode("y_translation"),
				is(sameInstance(yTranslation)));
	}
	
	@Test
	public void testSetZTranslation_positioner() throws NexusException {
		tomoBuilder.addDefaultGroups();
		TestPositioner zPositioner = new TestPositioner();
		tomoBuilder.setZTranslation(zPositioner);
		assertThat(subentry.getSample().getDataNode("z_translation"),
				is(sameInstance(zPositioner.getNexusObject().getDataNode(
						NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testSetZTranslation_dataNode() throws NexusException {
		tomoBuilder.addDefaultGroups();
		DataNode zTranslation = NexusNodeFactory.createDataNode();
		ILazyWriteableDataset dataset = new LazyWriteableDataset("z_translation", Dataset.FLOAT64, new int[] { 100 }, null, null, null);
		zTranslation.setDataset(dataset);
		tomoBuilder.setZTranslation(zTranslation);

		assertThat(subentry.getSample().getDataNode("z_translation"),
				is(sameInstance(zTranslation)));
	}
	
	@Test
	public void testSetControl() throws NexusException {
		tomoBuilder.addDefaultGroups();
		TestPositioner control = new TestPositioner();
		tomoBuilder.setControl(control);
		assertThat(subentry.getMonitor("control").getDataNode(NXmonitor.NX_DATA),
				is(sameInstance(control.getNexusObject().getDataNode(NXpositioner.NX_VALUE))));
	}
	
	@Test
	public void testSetNewData() throws NexusException {
		tomoBuilder.addDefaultGroups();
		NXinstrument instrument = subentry.getInstrument();
		NXdetector detector = NexusNodeFactory.createNXdetector();
		instrument.setDetector(detector);
		detector.initializeLazyDataset(NXdetector.NX_DATA, 3, Double.class);
		detector.initializeLazyDataset("image_key", 1, Short.class);
		NXsample sample = subentry.getSample();
		sample.setRotation_angleScalar(2.5);
		
		tomoBuilder.newData();

		assertThat(subentry.getData().getDataNode(NXdata.NX_DATA),
				is(sameInstance((DataNode) detector.getDataNode(NXdetector.NX_DATA))));
		assertThat(subentry.getData().getDataNode(NXsample.NX_ROTATION_ANGLE),
				is(sameInstance((DataNode) sample.getDataNode(NXsample.NX_ROTATION_ANGLE))));
		assertThat(subentry.getData().getDataNode("image_key"),
				is(sameInstance((DataNode) detector.getDataNode("image_key")))); 
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidate() throws Exception {
		// an empty subentry will fail validation
		tomoBuilder.validate();
	}

}
