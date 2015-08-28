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
 * ``NXsubentry`` is a base class virtually identical to ``NXentry``
 * and is used as the (overlay) location for application definitions.
 * Use a separate ``NXsubentry`` for each application definition.
 * To use ``NXsubentry`` with a hypothetical application definition
 * called ``NXmyappdef``:
 * * Create a group with attribute ``NX_class="NXsubentry"``
 * * Within that group, create a field called ``definition="NXmyappdef"``.
 * * There are two optional attributes of definition: ``version`` and ``URL``
 * The intended use is to define application definitions for a
 * multi-technique ``NXentry``. Previously, an application definition
 * replaced ``NXentry`` with its own definition.
 * With the increasing popularity of instruments combining
 * multiple techniques for data collection (such as SAXS/WAXS instruments),
 * it was recognized the application definitions must be entered in the NeXus
 * data file tree as children of ``NXentry``.
 * 
 * @version 1.0
 */
public class NXsubentryImpl extends NXobjectImpl implements NXsubentry {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_ATTRIBUTE_IDF_VERSION = "IDF_Version";
	public static final String NX_TITLE = "title";
	public static final String NX_EXPERIMENT_IDENTIFIER = "experiment_identifier";
	public static final String NX_EXPERIMENT_DESCRIPTION = "experiment_description";
	public static final String NX_COLLECTION_IDENTIFIER = "collection_identifier";
	public static final String NX_COLLECTION_DESCRIPTION = "collection_description";
	public static final String NX_ENTRY_IDENTIFIER = "entry_identifier";
	public static final String NX_DEFINITION = "definition";
	public static final String NX_DEFINITION_ATTRIBUTE_VERSION = "version";
	public static final String NX_DEFINITION_ATTRIBUTE_URL = "URL";
	public static final String NX_DEFINITION_LOCAL = "definition_local";
	public static final String NX_DEFINITION_LOCAL_ATTRIBUTE_VERSION = "version";
	public static final String NX_DEFINITION_LOCAL_ATTRIBUTE_URL = "URL";
	public static final String NX_START_TIME = "start_time";
	public static final String NX_END_TIME = "end_time";
	public static final String NX_DURATION = "duration";
	public static final String NX_COLLECTION_TIME = "collection_time";
	public static final String NX_RUN_CYCLE = "run_cycle";
	public static final String NX_PROGRAM_NAME = "program_name";
	public static final String NX_PROGRAM_NAME_ATTRIBUTE_VERSION = "version";
	public static final String NX_PROGRAM_NAME_ATTRIBUTE_CONFIGURATION = "configuration";
	public static final String NX_REVISION = "revision";
	public static final String NX_REVISION_ATTRIBUTE_COMMENT = "comment";
	public static final String NX_PRE_SAMPLE_FLIGHTPATH = "pre_sample_flightpath";

	protected NXsubentryImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsubentry.class;
	}

	@Override
	public String getAttributeIDF_Version() {
		return getAttrString(null, NX_ATTRIBUTE_IDF_VERSION);
	}

	public void setAttributeIDF_Version(String IDF_Version) {
		setAttribute(null, NX_ATTRIBUTE_IDF_VERSION, IDF_Version);
	}

	@Override
	public IDataset getTitle() {
		return getDataset(NX_TITLE);
	}

	public void setTitle(IDataset title) {
		setDataset(NX_TITLE, title);
	}

	@Override
	public IDataset getExperiment_identifier() {
		return getDataset(NX_EXPERIMENT_IDENTIFIER);
	}

	public void setExperiment_identifier(IDataset experiment_identifier) {
		setDataset(NX_EXPERIMENT_IDENTIFIER, experiment_identifier);
	}

	@Override
	public IDataset getExperiment_description() {
		return getDataset(NX_EXPERIMENT_DESCRIPTION);
	}

	public void setExperiment_description(IDataset experiment_description) {
		setDataset(NX_EXPERIMENT_DESCRIPTION, experiment_description);
	}

	@Override
	public NXnote getExperiment_documentation() {
		return getFirstChild(NXnote.class);
	}

	public void setExperiment_documentation(NXnote experiment_documentation) {
		putChild(experiment_documentation);
	}

	@Override
	public IDataset getCollection_identifier() {
		return getDataset(NX_COLLECTION_IDENTIFIER);
	}

	public void setCollection_identifier(IDataset collection_identifier) {
		setDataset(NX_COLLECTION_IDENTIFIER, collection_identifier);
	}

	@Override
	public IDataset getCollection_description() {
		return getDataset(NX_COLLECTION_DESCRIPTION);
	}

	public void setCollection_description(IDataset collection_description) {
		setDataset(NX_COLLECTION_DESCRIPTION, collection_description);
	}

	@Override
	public IDataset getEntry_identifier() {
		return getDataset(NX_ENTRY_IDENTIFIER);
	}

	public void setEntry_identifier(IDataset entry_identifier) {
		setDataset(NX_ENTRY_IDENTIFIER, entry_identifier);
	}

	@Override
	public IDataset getDefinition() {
		return getDataset(NX_DEFINITION);
	}

	public void setDefinition(IDataset definition) {
		setDataset(NX_DEFINITION, definition);
	}

	@Override
	public String getDefinitionAttributeVersion() {
		return getAttrString(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_VERSION);
	}

	public void setDefinitionAttributeVersion(String version) {
		setAttribute(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_VERSION, version);
	}

	@Override
	public String getDefinitionAttributeURL() {
		return getAttrString(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_URL);
	}

	public void setDefinitionAttributeURL(String URL) {
		setAttribute(NX_DEFINITION, NX_DEFINITION_ATTRIBUTE_URL, URL);
	}

	@Override
	public IDataset getDefinition_local() {
		return getDataset(NX_DEFINITION_LOCAL);
	}

	public void setDefinition_local(IDataset definition_local) {
		setDataset(NX_DEFINITION_LOCAL, definition_local);
	}

	@Override
	public String getDefinition_localAttributeVersion() {
		return getAttrString(NX_DEFINITION_LOCAL, NX_DEFINITION_LOCAL_ATTRIBUTE_VERSION);
	}

	public void setDefinition_localAttributeVersion(String version) {
		setAttribute(NX_DEFINITION_LOCAL, NX_DEFINITION_LOCAL_ATTRIBUTE_VERSION, version);
	}

	@Override
	public String getDefinition_localAttributeURL() {
		return getAttrString(NX_DEFINITION_LOCAL, NX_DEFINITION_LOCAL_ATTRIBUTE_URL);
	}

	public void setDefinition_localAttributeURL(String URL) {
		setAttribute(NX_DEFINITION_LOCAL, NX_DEFINITION_LOCAL_ATTRIBUTE_URL, URL);
	}

	@Override
	public IDataset getStart_time() {
		return getDataset(NX_START_TIME);
	}

	public void setStart_time(IDataset start_time) {
		setDataset(NX_START_TIME, start_time);
	}

	@Override
	public IDataset getEnd_time() {
		return getDataset(NX_END_TIME);
	}

	public void setEnd_time(IDataset end_time) {
		setDataset(NX_END_TIME, end_time);
	}

	@Override
	public IDataset getDuration() {
		return getDataset(NX_DURATION);
	}

	public void setDuration(IDataset duration) {
		setDataset(NX_DURATION, duration);
	}

	@Override
	public IDataset getCollection_time() {
		return getDataset(NX_COLLECTION_TIME);
	}

	public void setCollection_time(IDataset collection_time) {
		setDataset(NX_COLLECTION_TIME, collection_time);
	}

	@Override
	public IDataset getRun_cycle() {
		return getDataset(NX_RUN_CYCLE);
	}

	public void setRun_cycle(IDataset run_cycle) {
		setDataset(NX_RUN_CYCLE, run_cycle);
	}

	@Override
	public IDataset getProgram_name() {
		return getDataset(NX_PROGRAM_NAME);
	}

	public void setProgram_name(IDataset program_name) {
		setDataset(NX_PROGRAM_NAME, program_name);
	}

	@Override
	public String getProgram_nameAttributeVersion() {
		return getAttrString(NX_PROGRAM_NAME, NX_PROGRAM_NAME_ATTRIBUTE_VERSION);
	}

	public void setProgram_nameAttributeVersion(String version) {
		setAttribute(NX_PROGRAM_NAME, NX_PROGRAM_NAME_ATTRIBUTE_VERSION, version);
	}

	@Override
	public String getProgram_nameAttributeConfiguration() {
		return getAttrString(NX_PROGRAM_NAME, NX_PROGRAM_NAME_ATTRIBUTE_CONFIGURATION);
	}

	public void setProgram_nameAttributeConfiguration(String configuration) {
		setAttribute(NX_PROGRAM_NAME, NX_PROGRAM_NAME_ATTRIBUTE_CONFIGURATION, configuration);
	}

	@Override
	public IDataset getRevision() {
		return getDataset(NX_REVISION);
	}

	public void setRevision(IDataset revision) {
		setDataset(NX_REVISION, revision);
	}

	@Override
	public String getRevisionAttributeComment() {
		return getAttrString(NX_REVISION, NX_REVISION_ATTRIBUTE_COMMENT);
	}

	public void setRevisionAttributeComment(String comment) {
		setAttribute(NX_REVISION, NX_REVISION_ATTRIBUTE_COMMENT, comment);
	}

	@Override
	public IDataset getPre_sample_flightpath() {
		return getDataset(NX_PRE_SAMPLE_FLIGHTPATH);
	}

	public void setPre_sample_flightpath(IDataset pre_sample_flightpath) {
		setDataset(NX_PRE_SAMPLE_FLIGHTPATH, pre_sample_flightpath);
	}

	@Override
	public NXnote getNotes() {
		return getFirstChild(NXnote.class);
	}

	public void setNotes(NXnote notes) {
		putChild(notes);
	}

	@Override
	public NXnote getThumbnail() {
		return getFirstChild(NXnote.class);
	}

	public void setThumbnail(NXnote thumbnail) {
		putChild(thumbnail);
	}

	@Override
	public NXcharacterization getCharacterization() {
		return getFirstChild(NXcharacterization.class);
	}

	public void setCharacterization(NXcharacterization characterization) {
		putChild(characterization);
	}

	@Override
	public NXcharacterization getCharacterization(String name) {
		return getChild(name, NXcharacterization.class);
	}

	public void setCharacterization(String name, NXcharacterization characterization) {
		putChild(name, characterization);
	}

	@Override
	public Map<String, NXcharacterization> getAllCharacterization() {
		return getChildren(NXcharacterization.class);
	}

	public void setAllCharacterization(Map<String, NXcharacterization> characterization) {
		setChildren(characterization);
	}

	@Override
	public NXuser getUser() {
		return getFirstChild(NXuser.class);
	}

	public void setUser(NXuser user) {
		putChild(user);
	}

	@Override
	public NXuser getUser(String name) {
		return getChild(name, NXuser.class);
	}

	public void setUser(String name, NXuser user) {
		putChild(name, user);
	}

	@Override
	public Map<String, NXuser> getAllUser() {
		return getChildren(NXuser.class);
	}

	public void setAllUser(Map<String, NXuser> user) {
		setChildren(user);
	}

	@Override
	public NXsample getSample() {
		return getFirstChild(NXsample.class);
	}

	public void setSample(NXsample sample) {
		putChild(sample);
	}

	@Override
	public NXsample getSample(String name) {
		return getChild(name, NXsample.class);
	}

	public void setSample(String name, NXsample sample) {
		putChild(name, sample);
	}

	@Override
	public Map<String, NXsample> getAllSample() {
		return getChildren(NXsample.class);
	}

	public void setAllSample(Map<String, NXsample> sample) {
		setChildren(sample);
	}

	@Override
	public NXinstrument getInstrument() {
		return getFirstChild(NXinstrument.class);
	}

	public void setInstrument(NXinstrument instrument) {
		putChild(instrument);
	}

	@Override
	public NXinstrument getInstrument(String name) {
		return getChild(name, NXinstrument.class);
	}

	public void setInstrument(String name, NXinstrument instrument) {
		putChild(name, instrument);
	}

	@Override
	public Map<String, NXinstrument> getAllInstrument() {
		return getChildren(NXinstrument.class);
	}

	public void setAllInstrument(Map<String, NXinstrument> instrument) {
		setChildren(instrument);
	}

	@Override
	public NXcollection getCollection() {
		return getFirstChild(NXcollection.class);
	}

	public void setCollection(NXcollection collection) {
		putChild(collection);
	}

	@Override
	public NXcollection getCollection(String name) {
		return getChild(name, NXcollection.class);
	}

	public void setCollection(String name, NXcollection collection) {
		putChild(name, collection);
	}

	@Override
	public Map<String, NXcollection> getAllCollection() {
		return getChildren(NXcollection.class);
	}

	public void setAllCollection(Map<String, NXcollection> collection) {
		setChildren(collection);
	}

	@Override
	public NXmonitor getMonitor() {
		return getFirstChild(NXmonitor.class);
	}

	public void setMonitor(NXmonitor monitor) {
		putChild(monitor);
	}

	@Override
	public NXmonitor getMonitor(String name) {
		return getChild(name, NXmonitor.class);
	}

	public void setMonitor(String name, NXmonitor monitor) {
		putChild(name, monitor);
	}

	@Override
	public Map<String, NXmonitor> getAllMonitor() {
		return getChildren(NXmonitor.class);
	}

	public void setAllMonitor(Map<String, NXmonitor> monitor) {
		setChildren(monitor);
	}

	@Override
	public NXdata getData() {
		return getFirstChild(NXdata.class);
	}

	public void setData(NXdata data) {
		putChild(data);
	}

	@Override
	public NXdata getData(String name) {
		return getChild(name, NXdata.class);
	}

	public void setData(String name, NXdata data) {
		putChild(name, data);
	}

	@Override
	public Map<String, NXdata> getAllData() {
		return getChildren(NXdata.class);
	}

	public void setAllData(Map<String, NXdata> data) {
		setChildren(data);
	}

	@Override
	public NXparameters getParameters() {
		return getFirstChild(NXparameters.class);
	}

	public void setParameters(NXparameters parameters) {
		putChild(parameters);
	}

	@Override
	public NXparameters getParameters(String name) {
		return getChild(name, NXparameters.class);
	}

	public void setParameters(String name, NXparameters parameters) {
		putChild(name, parameters);
	}

	@Override
	public Map<String, NXparameters> getAllParameters() {
		return getChildren(NXparameters.class);
	}

	public void setAllParameters(Map<String, NXparameters> parameters) {
		setChildren(parameters);
	}

	@Override
	public NXprocess getProcess() {
		return getFirstChild(NXprocess.class);
	}

	public void setProcess(NXprocess process) {
		putChild(process);
	}

	@Override
	public NXprocess getProcess(String name) {
		return getChild(name, NXprocess.class);
	}

	public void setProcess(String name, NXprocess process) {
		putChild(name, process);
	}

	@Override
	public Map<String, NXprocess> getAllProcess() {
		return getChildren(NXprocess.class);
	}

	public void setAllProcess(Map<String, NXprocess> process) {
		setChildren(process);
	}

}
