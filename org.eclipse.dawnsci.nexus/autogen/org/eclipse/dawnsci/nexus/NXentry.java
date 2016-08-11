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

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * (**required**) :ref:`NXentry` describes the measurement.
 * The top-level NeXus group which contains all the data and associated
 * information that comprise a single measurement. It is mandatory that there is at least one
 * group of this type in the NeXus file.
 * 
 * @version 1.0
 */
public interface NXentry extends NXsubentry {

	public static final String NX_ATTRIBUTE_DEFAULT = "default";
	public static final String NX_ATTRIBUTE_IDF_VERSION = "IDF_Version";
	public static final String NX_TITLE = "title";
	public static final String NX_EXPERIMENT_IDENTIFIER = "experiment_identifier";
	public static final String NX_EXPERIMENT_DESCRIPTION = "experiment_description";
	public static final String NX_COLLECTION_IDENTIFIER = "collection_identifier";
	public static final String NX_COLLECTION_DESCRIPTION = "collection_description";
	public static final String NX_ENTRY_IDENTIFIER = "entry_identifier";
	public static final String NX_FEATURES = "features";
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
	/**
	 * .. index:: plotting
	 * Declares which :ref:`NXdata` (or :ref:`NXsubentry`) group
	 * contains the data to be shown by default.
	 * It is needed to resolve ambiguity when more than one :ref:`NXdata` group exists.
	 * The value is the name of the default :ref:`NXdata` group.
	 * It is recommended (as of NIAC2014) to use this attribute
	 * to help define the path to the default dataset to be plotted.
	 * See http://wiki.nexusformat.org/2014_How_to_find_default_data
	 * for a summary of the discussion.
	 * 
	 * @return  the value.
	 */
	public String getAttributeDefault();
	
	/**
	 * .. index:: plotting
	 * Declares which :ref:`NXdata` (or :ref:`NXsubentry`) group
	 * contains the data to be shown by default.
	 * It is needed to resolve ambiguity when more than one :ref:`NXdata` group exists.
	 * The value is the name of the default :ref:`NXdata` group.
	 * It is recommended (as of NIAC2014) to use this attribute
	 * to help define the path to the default dataset to be plotted.
	 * See http://wiki.nexusformat.org/2014_How_to_find_default_data
	 * for a summary of the discussion.
	 * 
	 * @param default_ the default
	 */
	public void setAttributeDefault(String default_);

	/**
	 * The required data group
	 * 
	 * @return  the value.
	 */
	public NXdata getData();
	
	/**
	 * The required data group
	 * 
	 * @param data the data
	 */
	public void setData(NXdata data);
  
	/**
	 * Get a NXdata node by name:
	 * <ul>
	 * <li>
	 * The required data group</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXdata for that node.
	 */
	public NXdata getData(String name);
	
	/**
	 * Set a NXdata node by name:
	 * <ul>
	 * <li>
	 * The required data group</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param data the value to set
	 */
	public void setData(String name, NXdata data);
	
	/**
	 * Get all NXdata nodes:
	 * <ul>
	 * <li>
	 * The required data group</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXdata for that node.
	 */
	public Map<String, NXdata> getAllData();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * The required data group</li>
	 * </ul>
	 * 
	 * @param data the child nodes to add 
	 */
	
	public void setAllData(Map<String, NXdata> data);
	

	/**
	 * ISIS Muon IDF_Version
	 * 
	 * @return  the value.
	 */
	public String getAttributeIDF_Version();
	
	/**
	 * ISIS Muon IDF_Version
	 * 
	 * @param IDF_Version the IDF_Version
	 */
	public void setAttributeIDF_Version(String IDF_Version);

	/**
	 * Extended title for entry
	 * 
	 * @return  the value.
	 */
	public IDataset getTitle();
	
	/**
	 * Extended title for entry
	 * 
	 * @param title the title
	 */
	public DataNode setTitle(IDataset title);

	/**
	 * Extended title for entry
	 * 
	 * @return  the value.
	 */
	public String getTitleScalar();

	/**
	 * Extended title for entry
	 * 
	 * @param title the title
	 */
	public DataNode setTitleScalar(String title);

	/**
	 * Unique identifier for the experiment,
	 * defined by the facility,
	 * possibly linked to the proposals
	 * 
	 * @return  the value.
	 */
	public IDataset getExperiment_identifier();
	
	/**
	 * Unique identifier for the experiment,
	 * defined by the facility,
	 * possibly linked to the proposals
	 * 
	 * @param experiment_identifier the experiment_identifier
	 */
	public DataNode setExperiment_identifier(IDataset experiment_identifier);

	/**
	 * Unique identifier for the experiment,
	 * defined by the facility,
	 * possibly linked to the proposals
	 * 
	 * @return  the value.
	 */
	public String getExperiment_identifierScalar();

	/**
	 * Unique identifier for the experiment,
	 * defined by the facility,
	 * possibly linked to the proposals
	 * 
	 * @param experiment_identifier the experiment_identifier
	 */
	public DataNode setExperiment_identifierScalar(String experiment_identifier);

	/**
	 * Brief summary of the experiment, including key objectives.
	 * 
	 * @return  the value.
	 */
	public IDataset getExperiment_description();
	
	/**
	 * Brief summary of the experiment, including key objectives.
	 * 
	 * @param experiment_description the experiment_description
	 */
	public DataNode setExperiment_description(IDataset experiment_description);

	/**
	 * Brief summary of the experiment, including key objectives.
	 * 
	 * @return  the value.
	 */
	public String getExperiment_descriptionScalar();

	/**
	 * Brief summary of the experiment, including key objectives.
	 * 
	 * @param experiment_description the experiment_description
	 */
	public DataNode setExperiment_descriptionScalar(String experiment_description);

	/**
	 * Description of the full experiment (document in pdf, latex, ...)
	 * 
	 * @return  the value.
	 */
	public NXnote getExperiment_documentation();
	
	/**
	 * Description of the full experiment (document in pdf, latex, ...)
	 * 
	 * @param experiment_documentation the experiment_documentation
	 */
	public void setExperiment_documentation(NXnote experiment_documentation);

	/**
	 * User or Data Acquisition defined group of NeXus files or NXentry
	 * 
	 * @return  the value.
	 */
	public IDataset getCollection_identifier();
	
	/**
	 * User or Data Acquisition defined group of NeXus files or NXentry
	 * 
	 * @param collection_identifier the collection_identifier
	 */
	public DataNode setCollection_identifier(IDataset collection_identifier);

	/**
	 * User or Data Acquisition defined group of NeXus files or NXentry
	 * 
	 * @return  the value.
	 */
	public String getCollection_identifierScalar();

	/**
	 * User or Data Acquisition defined group of NeXus files or NXentry
	 * 
	 * @param collection_identifier the collection_identifier
	 */
	public DataNode setCollection_identifierScalar(String collection_identifier);

	/**
	 * Brief summary of the collection, including grouping criteria.
	 * 
	 * @return  the value.
	 */
	public IDataset getCollection_description();
	
	/**
	 * Brief summary of the collection, including grouping criteria.
	 * 
	 * @param collection_description the collection_description
	 */
	public DataNode setCollection_description(IDataset collection_description);

	/**
	 * Brief summary of the collection, including grouping criteria.
	 * 
	 * @return  the value.
	 */
	public String getCollection_descriptionScalar();

	/**
	 * Brief summary of the collection, including grouping criteria.
	 * 
	 * @param collection_description the collection_description
	 */
	public DataNode setCollection_descriptionScalar(String collection_description);

	/**
	 * unique identifier for the measurement, defined by the facility.
	 * 
	 * @return  the value.
	 */
	public IDataset getEntry_identifier();
	
	/**
	 * unique identifier for the measurement, defined by the facility.
	 * 
	 * @param entry_identifier the entry_identifier
	 */
	public DataNode setEntry_identifier(IDataset entry_identifier);

	/**
	 * unique identifier for the measurement, defined by the facility.
	 * 
	 * @return  the value.
	 */
	public String getEntry_identifierScalar();

	/**
	 * unique identifier for the measurement, defined by the facility.
	 * 
	 * @param entry_identifier the entry_identifier
	 */
	public DataNode setEntry_identifierScalar(String entry_identifier);

	/**
	 * Reserved for furture use by NIAC.
	 * See https://github.com/nexusformat/definitions/issues/382
	 * 
	 * @return  the value.
	 */
	public IDataset getFeatures();
	
	/**
	 * Reserved for furture use by NIAC.
	 * See https://github.com/nexusformat/definitions/issues/382
	 * 
	 * @param features the features
	 */
	public DataNode setFeatures(IDataset features);

	/**
	 * Reserved for furture use by NIAC.
	 * See https://github.com/nexusformat/definitions/issues/382
	 * 
	 * @return  the value.
	 */
	public String getFeaturesScalar();

	/**
	 * Reserved for furture use by NIAC.
	 * See https://github.com/nexusformat/definitions/issues/382
	 * 
	 * @param features the features
	 */
	public DataNode setFeaturesScalar(String features);

	/**
	 * (alternate use: see same field in :ref:`NXsubentry` for preferred)
	 * Official NeXus NXDL schema to which this entry conforms.
	 * This field is provided so that :ref:`NXentry` can be the overlay position
	 * in a NeXus data file for an application definition and its
	 * set of groups, fields, and attributes.
	 * *It is advised* to use :ref:`NXsubentry`, instead, as the overlay position.
	 * 
	 * @return  the value.
	 */
	public IDataset getDefinition();
	
	/**
	 * (alternate use: see same field in :ref:`NXsubentry` for preferred)
	 * Official NeXus NXDL schema to which this entry conforms.
	 * This field is provided so that :ref:`NXentry` can be the overlay position
	 * in a NeXus data file for an application definition and its
	 * set of groups, fields, and attributes.
	 * *It is advised* to use :ref:`NXsubentry`, instead, as the overlay position.
	 * 
	 * @param definition the definition
	 */
	public DataNode setDefinition(IDataset definition);

	/**
	 * (alternate use: see same field in :ref:`NXsubentry` for preferred)
	 * Official NeXus NXDL schema to which this entry conforms.
	 * This field is provided so that :ref:`NXentry` can be the overlay position
	 * in a NeXus data file for an application definition and its
	 * set of groups, fields, and attributes.
	 * *It is advised* to use :ref:`NXsubentry`, instead, as the overlay position.
	 * 
	 * @return  the value.
	 */
	public String getDefinitionScalar();

	/**
	 * (alternate use: see same field in :ref:`NXsubentry` for preferred)
	 * Official NeXus NXDL schema to which this entry conforms.
	 * This field is provided so that :ref:`NXentry` can be the overlay position
	 * in a NeXus data file for an application definition and its
	 * set of groups, fields, and attributes.
	 * *It is advised* to use :ref:`NXsubentry`, instead, as the overlay position.
	 * 
	 * @param definition the definition
	 */
	public DataNode setDefinitionScalar(String definition);

	/**
	 * NXDL version number
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeVersion();
	
	/**
	 * NXDL version number
	 * 
	 * @param version the version
	 */
	public void setDefinitionAttributeVersion(String version);

	/**
	 * URL of NXDL file
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeURL();
	
	/**
	 * URL of NXDL file
	 * 
	 * @param URL the URL
	 */
	public void setDefinitionAttributeURL(String URL);

	/**
	 * Local NXDL schema extended from the entry
	 * specified in the ``definition`` field.
	 * This contains any locally-defined,
	 * additional fields in the entry.
	 * 
	 * @deprecated see same field in :ref:`NXsubentry` for preferred use
	 * @return  the value.
	 */
	@Deprecated
	public IDataset getDefinition_local();
	
	/**
	 * Local NXDL schema extended from the entry
	 * specified in the ``definition`` field.
	 * This contains any locally-defined,
	 * additional fields in the entry.
	 * 
	 * @deprecated see same field in :ref:`NXsubentry` for preferred use
	 * @param definition_local the definition_local
	 */
	@Deprecated
	public DataNode setDefinition_local(IDataset definition_local);

	/**
	 * Local NXDL schema extended from the entry
	 * specified in the ``definition`` field.
	 * This contains any locally-defined,
	 * additional fields in the entry.
	 * 
	 * @deprecated see same field in :ref:`NXsubentry` for preferred use
	 * @return  the value.
	 */
	@Deprecated
	public String getDefinition_localScalar();

	/**
	 * Local NXDL schema extended from the entry
	 * specified in the ``definition`` field.
	 * This contains any locally-defined,
	 * additional fields in the entry.
	 * 
	 * @deprecated see same field in :ref:`NXsubentry` for preferred use
	 * @param definition_local the definition_local
	 */
	@Deprecated
	public DataNode setDefinition_localScalar(String definition_local);

	/**
	 * NXDL version number
	 * 
	 * @return  the value.
	 */
	public String getDefinition_localAttributeVersion();
	
	/**
	 * NXDL version number
	 * 
	 * @param version the version
	 */
	public void setDefinition_localAttributeVersion(String version);

	/**
	 * URL of NXDL file
	 * 
	 * @return  the value.
	 */
	public String getDefinition_localAttributeURL();
	
	/**
	 * URL of NXDL file
	 * 
	 * @param URL the URL
	 */
	public void setDefinition_localAttributeURL(String URL);

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getStart_time();
	
	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param start_time the start_time
	 */
	public DataNode setStart_time(IDataset start_time);

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getStart_timeScalar();

	/**
	 * Starting time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param start_time the start_time
	 */
	public DataNode setStart_timeScalar(Date start_time);

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnd_time();
	
	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param end_time the end_time
	 */
	public DataNode setEnd_time(IDataset end_time);

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getEnd_timeScalar();

	/**
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param end_time the end_time
	 */
	public DataNode setEnd_timeScalar(Date end_time);

	/**
	 * Duration of measurement
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDuration();
	
	/**
	 * Duration of measurement
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param duration the duration
	 */
	public DataNode setDuration(IDataset duration);

	/**
	 * Duration of measurement
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getDurationScalar();

	/**
	 * Duration of measurement
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param duration the duration
	 */
	public DataNode setDurationScalar(long duration);

	/**
	 * Time transpired actually collecting data i.e. taking out time when collection was
	 * suspended due to e.g. temperature out of range
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCollection_time();
	
	/**
	 * Time transpired actually collecting data i.e. taking out time when collection was
	 * suspended due to e.g. temperature out of range
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param collection_time the collection_time
	 */
	public DataNode setCollection_time(IDataset collection_time);

	/**
	 * Time transpired actually collecting data i.e. taking out time when collection was
	 * suspended due to e.g. temperature out of range
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCollection_timeScalar();

	/**
	 * Time transpired actually collecting data i.e. taking out time when collection was
	 * suspended due to e.g. temperature out of range
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @param collection_time the collection_time
	 */
	public DataNode setCollection_timeScalar(double collection_time);

	/**
	 * Such as "2007-3". Some user facilities organize their beam time into run cycles.
	 * 
	 * @return  the value.
	 */
	public IDataset getRun_cycle();
	
	/**
	 * Such as "2007-3". Some user facilities organize their beam time into run cycles.
	 * 
	 * @param run_cycle the run_cycle
	 */
	public DataNode setRun_cycle(IDataset run_cycle);

	/**
	 * Such as "2007-3". Some user facilities organize their beam time into run cycles.
	 * 
	 * @return  the value.
	 */
	public String getRun_cycleScalar();

	/**
	 * Such as "2007-3". Some user facilities organize their beam time into run cycles.
	 * 
	 * @param run_cycle the run_cycle
	 */
	public DataNode setRun_cycleScalar(String run_cycle);

	/**
	 * Name of program used to generate this file
	 * 
	 * @return  the value.
	 */
	public IDataset getProgram_name();
	
	/**
	 * Name of program used to generate this file
	 * 
	 * @param program_name the program_name
	 */
	public DataNode setProgram_name(IDataset program_name);

	/**
	 * Name of program used to generate this file
	 * 
	 * @return  the value.
	 */
	public String getProgram_nameScalar();

	/**
	 * Name of program used to generate this file
	 * 
	 * @param program_name the program_name
	 */
	public DataNode setProgram_nameScalar(String program_name);

	/**
	 * Program version number
	 * 
	 * @return  the value.
	 */
	public String getProgram_nameAttributeVersion();
	
	/**
	 * Program version number
	 * 
	 * @param version the version
	 */
	public void setProgram_nameAttributeVersion(String version);

	/**
	 * configuration of the program
	 * 
	 * @return  the value.
	 */
	public String getProgram_nameAttributeConfiguration();
	
	/**
	 * configuration of the program
	 * 
	 * @param configuration the configuration
	 */
	public void setProgram_nameAttributeConfiguration(String configuration);

	/**
	 * Revision id of the file due to re-calibration, reprocessing, new analysis, new
	 * instrument definition format, ...
	 * 
	 * @return  the value.
	 */
	public IDataset getRevision();
	
	/**
	 * Revision id of the file due to re-calibration, reprocessing, new analysis, new
	 * instrument definition format, ...
	 * 
	 * @param revision the revision
	 */
	public DataNode setRevision(IDataset revision);

	/**
	 * Revision id of the file due to re-calibration, reprocessing, new analysis, new
	 * instrument definition format, ...
	 * 
	 * @return  the value.
	 */
	public String getRevisionScalar();

	/**
	 * Revision id of the file due to re-calibration, reprocessing, new analysis, new
	 * instrument definition format, ...
	 * 
	 * @param revision the revision
	 */
	public DataNode setRevisionScalar(String revision);

	/**
	 * 
	 * @return  the value.
	 */
	public String getRevisionAttributeComment();
	
	/**
	 * 
	 * @param comment the comment
	 */
	public void setRevisionAttributeComment(String comment);

	/**
	 * This is the flightpath before the sample position. This can be determined by a chopper,
	 * by the moderator or the source itself. In other words: it the distance to the component
	 * which gives the T0 signal to the detector electronics. If another component in the
	 * NXinstrument hierarchy provides this information, this should be a link.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPre_sample_flightpath();
	
	/**
	 * This is the flightpath before the sample position. This can be determined by a chopper,
	 * by the moderator or the source itself. In other words: it the distance to the component
	 * which gives the T0 signal to the detector electronics. If another component in the
	 * NXinstrument hierarchy provides this information, this should be a link.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param pre_sample_flightpath the pre_sample_flightpath
	 */
	public DataNode setPre_sample_flightpath(IDataset pre_sample_flightpath);

	/**
	 * This is the flightpath before the sample position. This can be determined by a chopper,
	 * by the moderator or the source itself. In other words: it the distance to the component
	 * which gives the T0 signal to the detector electronics. If another component in the
	 * NXinstrument hierarchy provides this information, this should be a link.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPre_sample_flightpathScalar();

	/**
	 * This is the flightpath before the sample position. This can be determined by a chopper,
	 * by the moderator or the source itself. In other words: it the distance to the component
	 * which gives the T0 signal to the detector electronics. If another component in the
	 * NXinstrument hierarchy provides this information, this should be a link.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param pre_sample_flightpath the pre_sample_flightpath
	 */
	public DataNode setPre_sample_flightpathScalar(double pre_sample_flightpath);

	/**
	 * Notes describing entry
	 * 
	 * @return  the value.
	 */
	public NXnote getNotes();
	
	/**
	 * Notes describing entry
	 * 
	 * @param notes the notes
	 */
	public void setNotes(NXnote notes);

	/**
	 * A small image that is representative of the entry. An example of this is a 640x480
	 * jpeg image automatically produced by a low resolution plot of the NXdata.
	 * 
	 * @return  the value.
	 */
	public NXnote getThumbnail();
	
	/**
	 * A small image that is representative of the entry. An example of this is a 640x480
	 * jpeg image automatically produced by a low resolution plot of the NXdata.
	 * 
	 * @param thumbnail the thumbnail
	 */
	public void setThumbnail(NXnote thumbnail);

	/**
	 * 
	 * @return  the value.
	 */
	public NXcharacterization getCharacterization();
	
	/**
	 * 
	 * @param characterization the characterization
	 */
	public void setCharacterization(NXcharacterization characterization);
  
	/**
	 * Get a NXcharacterization node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcharacterization for that node.
	 */
	public NXcharacterization getCharacterization(String name);
	
	/**
	 * Set a NXcharacterization node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param characterization the value to set
	 */
	public void setCharacterization(String name, NXcharacterization characterization);
	
	/**
	 * Get all NXcharacterization nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcharacterization for that node.
	 */
	public Map<String, NXcharacterization> getAllCharacterization();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param characterization the child nodes to add 
	 */
	
	public void setAllCharacterization(Map<String, NXcharacterization> characterization);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXuser getUser();
	
	/**
	 * 
	 * @param user the user
	 */
	public void setUser(NXuser user);
  
	/**
	 * Get a NXuser node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXuser for that node.
	 */
	public NXuser getUser(String name);
	
	/**
	 * Set a NXuser node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param user the value to set
	 */
	public void setUser(String name, NXuser user);
	
	/**
	 * Get all NXuser nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXuser for that node.
	 */
	public Map<String, NXuser> getAllUser();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param user the child nodes to add 
	 */
	
	public void setAllUser(Map<String, NXuser> user);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXsample getSample();
	
	/**
	 * 
	 * @param sample the sample
	 */
	public void setSample(NXsample sample);
  
	/**
	 * Get a NXsample node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXsample for that node.
	 */
	public NXsample getSample(String name);
	
	/**
	 * Set a NXsample node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param sample the value to set
	 */
	public void setSample(String name, NXsample sample);
	
	/**
	 * Get all NXsample nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsample for that node.
	 */
	public Map<String, NXsample> getAllSample();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param sample the child nodes to add 
	 */
	
	public void setAllSample(Map<String, NXsample> sample);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXinstrument getInstrument();
	
	/**
	 * 
	 * @param instrument the instrument
	 */
	public void setInstrument(NXinstrument instrument);
  
	/**
	 * Get a NXinstrument node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXinstrument for that node.
	 */
	public NXinstrument getInstrument(String name);
	
	/**
	 * Set a NXinstrument node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param instrument the value to set
	 */
	public void setInstrument(String name, NXinstrument instrument);
	
	/**
	 * Get all NXinstrument nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXinstrument for that node.
	 */
	public Map<String, NXinstrument> getAllInstrument();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param instrument the child nodes to add 
	 */
	
	public void setAllInstrument(Map<String, NXinstrument> instrument);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXcollection getCollection();
	
	/**
	 * 
	 * @param collection the collection
	 */
	public void setCollection(NXcollection collection);
  
	/**
	 * Get a NXcollection node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcollection for that node.
	 */
	public NXcollection getCollection(String name);
	
	/**
	 * Set a NXcollection node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param collection the value to set
	 */
	public void setCollection(String name, NXcollection collection);
	
	/**
	 * Get all NXcollection nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcollection for that node.
	 */
	public Map<String, NXcollection> getAllCollection();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param collection the child nodes to add 
	 */
	
	public void setAllCollection(Map<String, NXcollection> collection);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXmonitor getMonitor();
	
	/**
	 * 
	 * @param monitor the monitor
	 */
	public void setMonitor(NXmonitor monitor);
  
	/**
	 * Get a NXmonitor node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXmonitor for that node.
	 */
	public NXmonitor getMonitor(String name);
	
	/**
	 * Set a NXmonitor node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param monitor the value to set
	 */
	public void setMonitor(String name, NXmonitor monitor);
	
	/**
	 * Get all NXmonitor nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXmonitor for that node.
	 */
	public Map<String, NXmonitor> getAllMonitor();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param monitor the child nodes to add 
	 */
	
	public void setAllMonitor(Map<String, NXmonitor> monitor);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXparameters getParameters();
	
	/**
	 * 
	 * @param parameters the parameters
	 */
	public void setParameters(NXparameters parameters);
  
	/**
	 * Get a NXparameters node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXparameters for that node.
	 */
	public NXparameters getParameters(String name);
	
	/**
	 * Set a NXparameters node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param parameters the value to set
	 */
	public void setParameters(String name, NXparameters parameters);
	
	/**
	 * Get all NXparameters nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXparameters for that node.
	 */
	public Map<String, NXparameters> getAllParameters();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param parameters the child nodes to add 
	 */
	
	public void setAllParameters(Map<String, NXparameters> parameters);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXprocess getProcess();
	
	/**
	 * 
	 * @param process the process
	 */
	public void setProcess(NXprocess process);
  
	/**
	 * Get a NXprocess node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXprocess for that node.
	 */
	public NXprocess getProcess(String name);
	
	/**
	 * Set a NXprocess node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param process the value to set
	 */
	public void setProcess(String name, NXprocess process);
	
	/**
	 * Get all NXprocess nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXprocess for that node.
	 */
	public Map<String, NXprocess> getAllProcess();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param process the child nodes to add 
	 */
	
	public void setAllProcess(Map<String, NXprocess> process);
	

	/**
	 * 
	 * @return  the value.
	 */
	public NXsubentry getSubentry();
	
	/**
	 * 
	 * @param subentry the subentry
	 */
	public void setSubentry(NXsubentry subentry);
  
	/**
	 * Get a NXsubentry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXsubentry for that node.
	 */
	public NXsubentry getSubentry(String name);
	
	/**
	 * Set a NXsubentry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param subentry the value to set
	 */
	public void setSubentry(String name, NXsubentry subentry);
	
	/**
	 * Get all NXsubentry nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsubentry for that node.
	 */
	public Map<String, NXsubentry> getAllSubentry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param subentry the child nodes to add 
	 */
	
	public void setAllSubentry(Map<String, NXsubentry> subentry);
	

}
