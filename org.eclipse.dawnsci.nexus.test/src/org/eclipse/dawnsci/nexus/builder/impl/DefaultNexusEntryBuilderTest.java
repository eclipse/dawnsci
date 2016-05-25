package org.eclipse.dawnsci.nexus.builder.impl;

import static org.eclipse.dawnsci.nexus.NXentry.NX_ENTRY_IDENTIFIER;
import static org.eclipse.dawnsci.nexus.NXentry.NX_EXPERIMENT_IDENTIFIER;
import static org.eclipse.dawnsci.nexus.NXentry.NX_PROGRAM_NAME;
import static org.eclipse.dawnsci.nexus.NexusBaseClass.NX_SAMPLE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.eclipse.dawnsci.nexus.NXcollection;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.CustomNexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;
import org.junit.Before;
import org.junit.Test;

public class DefaultNexusEntryBuilderTest {
	
	public static class TestPositioner extends AbstractNexusObjectProvider<NXpositioner> {
	
		public TestPositioner() {
			super("positioner", NexusBaseClass.NX_POSITIONER, NXpositioner.NX_VALUE);
		}
		
		public TestPositioner(String name) {
			super(name, NexusBaseClass.NX_POSITIONER);
		}
		
		@Override
		protected NXpositioner doCreateNexusObject(NexusNodeFactory nodeFactory) {
			return nodeFactory.createNXpositioner();
		}

	}
	
	public static class TestDetector extends AbstractNexusObjectProvider<NXdetector> {

		public TestDetector() {
			super("detector",  NexusBaseClass.NX_DETECTOR);
		}
		
		@Override
		protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
			return nodeFactory.createNXdetector();
		}
		
	}
	
	public static class TestSource extends AbstractNexusObjectProvider<NXsource> {
		
		public TestSource() {
			super("source", NexusBaseClass.NX_SOURCE);
		}
		
		@Override
		protected NXsource doCreateNexusObject(NexusNodeFactory nodeFactory) {
			return nodeFactory.createNXsource();
		}
		
	}
	
	private CustomNexusEntryModification customModification = new CustomNexusEntryModification() {
		
		@Override
		public void modifyEntry(NXentry entry, NexusNodeFactory nodeFactory) {
			entry.setField("foo", "bar");
		}
		
	};


	private NexusEntryBuilder entryBuilder;
	
	private NXentry nxEntry;
	
	@Before
	public void setUp() throws Exception {
		entryBuilder = new DefaultNexusFileBuilder("test").newEntry();
		nxEntry = entryBuilder.getNXentry();
	}
	
	@Test
	public void testGetNXentry() {
		assertThat(entryBuilder.getNXentry(), notNullValue(NXentry.class));
	}
	
	@Test
	public void testGetNodeFactory() {
		assertThat(entryBuilder.getNodeFactory(), notNullValue(NexusNodeFactory.class));
	}
	
	@Test
	public void testAddDefaultGroups() {
		assertThat(nxEntry.getNumberOfGroupNodes(), is(0));
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getInstrument(), notNullValue(NXinstrument.class));
		assertThat(nxEntry.getSample(), notNullValue(NXsample.class));
	}
	
	@Test
	public void testAdd() throws NexusException {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		
		TestPositioner positionerProvider = new TestPositioner();
		entryBuilder.add(positionerProvider);
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(instrument.getNumberOfGroupNodes(), is(1));
		assertThat(instrument.getPositioner(), is(sameInstance(positionerProvider.getNexusObject())));
	}
	
	@Test
	public void testAdd_namedGroup() throws NexusException {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		
		TestPositioner positionerProvider = new TestPositioner("x");
		entryBuilder.add(positionerProvider);

		assertThat(instrument.getNumberOfGroupNodes(), is(1));
		assertThat(instrument.getPositioner(), is(nullValue()));
		assertThat(instrument.getPositioner("x"), is(sameInstance(positionerProvider.getNexusObject())));
	}
	
	@Test
	public void testAdd_positionerWithCollectionName() throws NexusException {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		
		AbstractNexusObjectProvider<NXpositioner> positioner = new TestPositioner("xPos");
		positioner.setCollectionName("scannables");
		entryBuilder.add(positioner);
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(instrument.getNumberOfGroupNodes(), is(1));
		
		NXcollection collection = instrument.getCollection("scannables");
		assertNotNull(collection);
		NXpositioner xPositioner = (NXpositioner) collection.getGroupNode("xPos");
		assertNotNull(xPositioner);
		assertThat(xPositioner, is(sameInstance(positioner.getNexusObject())));
	}
	
	@Test
	public void testAdd_samplePositioner() throws NexusException {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		NXsample sample = nxEntry.getSample();
		assertThat(sample.getNumberOfGroupNodes(), is(0));
		
		TestPositioner positionerProvider = new TestPositioner();
		positionerProvider.setCategory(NX_SAMPLE);
		entryBuilder.add(positionerProvider);
		
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		assertThat(sample.getNumberOfGroupNodes(), is(1));
		assertThat(sample.getPositioner(), is(sameInstance(positionerProvider.getNexusObject())));
	}
	
	@Test
	public void testAdd_sample() throws NexusException {
		NexusObjectProvider<NXsample> sampleProvider =
				new AbstractNexusObjectProvider<NXsample>("sample", NexusBaseClass.NX_SAMPLE) {

			@Override
			protected NXsample doCreateNexusObject(NexusNodeFactory nodeFactory) {
				return nodeFactory.createNXsample();
			}
			
		};
		
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXsample oldSample = nxEntry.getSample();
		assertThat(oldSample, is(notNullValue()));
		
		entryBuilder.add(sampleProvider);
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getSample(), is(sameInstance(sampleProvider.getNexusObject())));
		assertThat(nxEntry.getSample(), is(not(sameInstance(oldSample))));
	}
	
	@Test
	public void testAddAll() throws NexusException {
		TestPositioner xPositioner = new TestPositioner("x");
		TestPositioner yPositioner = new TestPositioner("y");
		TestPositioner zPositioner = new TestPositioner("z");
		TestDetector detector = new TestDetector();
		TestPositioner samplePositioner = new TestPositioner();
		samplePositioner.setCategory(NX_SAMPLE);
		
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		NXsample sample = nxEntry.getSample();
		assertThat(sample.getNumberOfGroupNodes(), is(0));

		entryBuilder.addAll(Arrays.asList(xPositioner, yPositioner, zPositioner, samplePositioner, detector));
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(instrument.getNumberOfGroupNodes(), is(4));
		assertThat(instrument.getPositioner("x"), is(sameInstance(xPositioner.getNexusObject())));
		assertThat(instrument.getPositioner("y"), is(sameInstance(yPositioner.getNexusObject())));
		assertThat(instrument.getPositioner("z"), is(sameInstance(zPositioner.getNexusObject())));
		assertThat(instrument.getDetector(), is(sameInstance(detector.getNexusObject())));
		assertThat(sample.getPositioner(), is(sameInstance(samplePositioner.getNexusObject())));
	}
	
	@Test
	public void testAddMetadata() throws NexusException {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getNumberOfDataNodes(), is(0));
		
		MapBasedMetadataProvider metadata = new MapBasedMetadataProvider();
		metadata.addMetadataEntry(NX_ENTRY_IDENTIFIER, "12345");
		metadata.addMetadataEntry(NX_EXPERIMENT_IDENTIFIER, "myexperiment");
		metadata.addMetadataEntry(NX_PROGRAM_NAME, "GDA 8.36.0");
		metadata.addMetadataEntry("scan_command", "scan foo bar etc");
		metadata.addMetadataEntry("scan_identifier", "a3d668c0-e3c4-4ed9-b127-4a202b2b6bac");
		metadata.addMetadataEntry(NXentry.NX_TITLE, "Test Scan");

		entryBuilder.addMetadata(metadata);
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getNumberOfDataNodes(), is(6));
		assertThat(nxEntry.getEntry_identifierScalar(), is(equalTo("12345")));
		assertThat(nxEntry.getExperiment_identifierScalar(), is(equalTo("myexperiment")));
		assertThat(nxEntry.getProgram_nameScalar(), is(equalTo("GDA 8.36.0")));
		assertThat(nxEntry.getString("scan_command"), is(equalTo("scan foo bar etc")));
		assertThat(nxEntry.getString("scan_identifier"), is(equalTo("a3d668c0-e3c4-4ed9-b127-4a202b2b6bac")));
		assertThat(nxEntry.getTitleScalar(), is(equalTo("Test Scan")));
	}
	
	@Test
	public void testModifyEntry() throws Exception {
		assertThat(nxEntry.getDataNode("foo"), is(nullValue()));
		
		entryBuilder.modifyEntry(customModification);
		
		assertThat(nxEntry.getDataNode("foo").getString(), is(equalTo("bar"))); 
	}
	
	@Test
	public void testModifyEntry_nexusObjectProvider() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		
		TestPositioner positionerProvider = new TestPositioner();
		entryBuilder.modifyEntry(positionerProvider);
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(instrument.getNumberOfGroupNodes(), is(1));
		assertThat(instrument.getPositioner(), is(sameInstance(positionerProvider.getNexusObject())));
	}
	
	@Test
	public void testModifyEntry_metadataProvider() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getNumberOfDataNodes(), is(0));
		
		MapBasedMetadataProvider metadata = new MapBasedMetadataProvider();
		metadata.addMetadataEntry(NX_ENTRY_IDENTIFIER, "12345");
		metadata.addMetadataEntry(NXentry.NX_TITLE, "Test Scan");

		entryBuilder.addMetadata(metadata);
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getNumberOfDataNodes(), is(2));
		assertThat(nxEntry.getEntry_identifierScalar(), is(equalTo("12345")));
		assertThat(nxEntry.getTitleScalar(), is(equalTo("Test Scan")));
	}
	
	@Test
	public void testModifyEntry_customModification() throws Exception {
		assertThat(nxEntry.getDataNode("foo"), is(nullValue()));
		
		entryBuilder.modifyEntry((NexusEntryModification) customModification);
		
		assertThat(nxEntry.getDataNode("foo").getString(), is(equalTo("bar"))); 
	}
	
	@Test
	public void testModifyEntry_collection() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		assertThat(nxEntry.getDataNode("foo"), is(nullValue()));
		
		TestPositioner positionerProvider = new TestPositioner();
		MapBasedMetadataProvider metadata = new MapBasedMetadataProvider();
		metadata.addMetadataEntry(NX_ENTRY_IDENTIFIER, "12345");
		metadata.addMetadataEntry(NXentry.NX_TITLE, "Test Scan");

		entryBuilder.modifyEntry(Arrays.asList(positionerProvider, metadata, customModification));
		
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(instrument.getNumberOfGroupNodes(), is(1));
		assertThat(instrument.getPositioner(), is(sameInstance(positionerProvider.getNexusObject())));
		assertThat(nxEntry.getEntry_identifierScalar(), is(equalTo("12345")));
		assertThat(nxEntry.getTitleScalar(), is(equalTo("Test Scan")));
	}
	
	@Test
	public void testCreateDefaultData() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getData(), is(nullValue()));

		NexusDataBuilder dataBuilder = entryBuilder.createDefaultData();
		
		assertThat(dataBuilder, is(notNullValue()));
		assertThat(nxEntry.getNumberOfGroupNodes(), is(4));
		assertThat(nxEntry.getData(), is(notNullValue()));
		assertThat(dataBuilder.getNxData(), is(sameInstance(nxEntry.getData())));
	}

	@Test
	public void testNewData() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getData(), is(nullValue()));

		NexusDataBuilder dataBuilder = entryBuilder.newData("testdata");
		
		assertThat(dataBuilder, is(notNullValue()));
		assertThat(nxEntry.getNumberOfGroupNodes(), is(4));
		assertThat(nxEntry.getData(), is(nullValue()));
		assertThat(nxEntry.getData("testdata"), is(notNullValue()));
		assertThat(dataBuilder.getNxData(), is(sameInstance(nxEntry.getData("testdata"))));
	}
	
	@Test
	public void testSetInstrumentName() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getData(), is(nullValue()));
		NXinstrument instrument = nxEntry.getInstrument();
		assertThat(instrument.getNumberOfGroupNodes(), is(0));
		assertThat(instrument.getNumberOfDataNodes(), is(0));
		assertThat(instrument.getName(), is(nullValue()));
		
		entryBuilder.setInstrumentName("i13");
		
		assertThat(instrument.getNumberOfDataNodes(), is(1));
		assertThat(instrument.getName().getString(), is(equalTo("i13")));
		assertThat(instrument.getNameScalar(), is(equalTo("i13")));
	}
	
	@Test
	public void testNewApplication() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getData(), is(nullValue()));
		
		NexusApplicationBuilder app = entryBuilder.newApplication(NexusApplicationDefinition.NX_TOMO);
		
		assertThat(app, is(notNullValue()));
		assertThat(nxEntry.getNumberOfGroupNodes(), is(4));
		assertThat(app.getNXsubentry(), is(sameInstance(nxEntry.getSubentry("tomo_entry"))));
	}

	@Test
	public void testNewApplication_subentryName() throws Exception {
		entryBuilder.addDefaultGroups();
		assertThat(nxEntry.getNumberOfGroupNodes(), is(3));
		assertThat(nxEntry.getData(), is(nullValue()));
		
		NexusApplicationBuilder app = entryBuilder.newApplication("appdef", NexusApplicationDefinition.NX_TOMO);
		
		assertThat(app, is(notNullValue()));
		assertThat(nxEntry.getNumberOfGroupNodes(), is(4));
		assertThat(app.getNXsubentry(), is(sameInstance(nxEntry.getSubentry("appdef"))));
	}
	
	@Test
	public void testGetDataNode() throws Exception {
		entryBuilder.addDefaultGroups();
		nxEntry.setTitleScalar("My Entry");
		nxEntry.getInstrument().setNameScalar("My instrument");
		
		assertThat(entryBuilder.getDataNode("title").getString(), is(equalTo("My Entry")));
		assertThat(entryBuilder.getDataNode("instrument/name").getString(), is(equalTo("My instrument")));
	}
	
	@Test(expected = NexusException.class)
	public void testGetDataNode_NotExist() throws Exception {
		entryBuilder.getDataNode("doesnotexist");
	}

	@Test(expected = NexusException.class)
	public void testGetDataNode_groupNode() throws Exception {
		entryBuilder.getDataNode("groupnode");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidate() throws Exception {
		// Note: this method used to use Mockito to validate that each subentry was validated,
		// however the plugin dependency on mockito appears to prevent plugin tests from running
		entryBuilder.newApplication("subentry1", NexusApplicationDefinition.NX_TOMO);
		entryBuilder.newApplication("subentry2", NexusApplicationDefinition.NX_TOMO);
		entryBuilder.validate();
		
//		verify(subentry1).validate();
//		verify(subentry2).validate();
//		verifyNoMoreInteractions(subentry1, subentry2);
	}

}
