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
 * (**required**) Template of the top-level NeXus group which contains all the data and associated
 * information that comprise a single measurement. It is mandatory that there is at least one
 * group of this type in the NeXus file.
 * 
 * @version 1.0
 */
public class NXentryImpl extends NXobjectImpl implements NXentry {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_ATTRIBUTE_DEFAULT = "default";
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

	protected NXentryImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXentry.class;
	}

	@Override
	public NXdata getData() {
		return getChild("data", NXdata.class);
	}

	public void setData(NXdata data) {
		putChild("data", data);
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
	public String getAttributeDefault() {
		return getAttrString(null, NX_ATTRIBUTE_DEFAULT);
	}

	public void setAttributeDefault(String default_) {
		setAttribute(null, NX_ATTRIBUTE_DEFAULT, default_);
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
		return getChild("experiment_documentation", NXnote.class);
	}

	public void setExperiment_documentation(NXnote experiment_documentation) {
		putChild("experiment_documentation", experiment_documentation);
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
	@Deprecated
	public IDataset getDefinition_local() {
		return getDataset(NX_DEFINITION_LOCAL);
	}

	@Deprecated
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
		return getChild("notes", NXnote.class);
	}

	public void setNotes(NXnote notes) {
		putChild("notes", notes);
	}

	@Override
	public NXnote getThumbnail() {
		return getChild("thumbnail", NXnote.class);
	}

	public void setThumbnail(NXnote thumbnail) {
		putChild("thumbnail", thumbnail);
	}

	@Override
	public NXcharacterization getCharacterization() {
		return getChild("characterization", NXcharacterization.class);
	}

	public void setCharacterization(NXcharacterization characterization) {
		putChild("characterization", characterization);
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
		return getChild("user", NXuser.class);
	}

	public void setUser(NXuser user) {
		putChild("user", user);
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
		return getChild("sample", NXsample.class);
	}

	public void setSample(NXsample sample) {
		putChild("sample", sample);
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
		return getChild("instrument", NXinstrument.class);
	}

	public void setInstrument(NXinstrument instrument) {
		putChild("instrument", instrument);
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
		return getChild("collection", NXcollection.class);
	}

	public void setCollection(NXcollection collection) {
		putChild("collection", collection);
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
		return getChild("monitor", NXmonitor.class);
	}

	public void setMonitor(NXmonitor monitor) {
		putChild("monitor", monitor);
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
	public NXparameters getParameters() {
		return getChild("parameters", NXparameters.class);
	}

	public void setParameters(NXparameters parameters) {
		putChild("parameters", parameters);
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
		return getChild("process", NXprocess.class);
	}

	public void setProcess(NXprocess process) {
		putChild("process", process);
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

	@Override
	public NXsubentry getSubentry() {
		return getChild("subentry", NXsubentry.class);
	}

	public void setSubentry(NXsubentry subentry) {
		putChild("subentry", subentry);
	}

	@Override
	public NXsubentry getSubentry(String name) {
		return getChild(name, NXsubentry.class);
	}

	public void setSubentry(String name, NXsubentry subentry) {
		putChild(name, subentry);
	}

	@Override
	public Map<String, NXsubentry> getAllSubentry() {
		return getChildren(NXsubentry.class);
	}

	public void setAllSubentry(Map<String, NXsubentry> subentry) {
		setChildren(subentry);
	}

}
