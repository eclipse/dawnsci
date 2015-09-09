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

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * (**required**) Template of the top-level NeXus group which contains all the data and associated
 * information that comprise a single measurement. It is mandatory that there is at least one
 * group of this type in the NeXus file.
 * 
 * @version 1.0
 */
public interface NXentry extends NXobject {

	/**
	 * The required data group
	 * 
	 * @return  the value.
	 */
	public NXdata getData();
  
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
	 * name of the ''NXdata'' in this ''NXentry'' to use for the default plot
	 * 
	 * @return  the value.
	 */
	public String getAttributeDefault();

	/**
	 * ISIS Muon IDF_Version
	 * 
	 * @return  the value.
	 */
	public String getAttributeIDF_Version();

	/**
	 * Extended title for entry
	 * 
	 * @return  the value.
	 */
	public IDataset getTitle();

	/**
	 * Unique identifier for the experiment,
	 * defined by the facility,
	 * possibly linked to the proposals
	 * 
	 * @return  the value.
	 */
	public IDataset getExperiment_identifier();

	/**
	 * Brief summary of the experiment, including key objectives.
	 * 
	 * @return  the value.
	 */
	public IDataset getExperiment_description();

	/**
	 * Description of the full experiment (document in pdf, latex, ...)
	 * 
	 * @return  the value.
	 */
	public NXnote getExperiment_documentation();

	/**
	 * User or Data Acquisition defined group of NeXus files or NXentry
	 * 
	 * @return  the value.
	 */
	public IDataset getCollection_identifier();

	/**
	 * Brief summary of the collection, including grouping criteria.
	 * 
	 * @return  the value.
	 */
	public IDataset getCollection_description();

	/**
	 * unique identifier for the measurement, defined by the facility.
	 * 
	 * @return  the value.
	 */
	public IDataset getEntry_identifier();

	/**
	 * (alternate use: see same field in :ref:`NXsubentry` for preferred)
	 * Official NeXus NXDL schema to which this entry conforms.
	 * This field is provided so that **NXentry** can be the overlay position
	 * in a NeXus data file for an application definition and its
	 * set of groups, fields, and attributes.
	 * *It is advised* to use :ref:`NXsubentry`, instead, as the overlay position.
	 * 
	 * @return  the value.
	 */
	public IDataset getDefinition();

	/**
	 * NXDL version number
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeVersion();

	/**
	 * URL of NXDL file
	 * 
	 * @return  the value.
	 */
	public String getDefinitionAttributeURL();

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
	 * NXDL version number
	 * 
	 * @return  the value.
	 */
	public String getDefinition_localAttributeVersion();

	/**
	 * URL of NXDL file
	 * 
	 * @return  the value.
	 */
	public String getDefinition_localAttributeURL();

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
	 * Ending time of measurement
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnd_time();

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
	 * Such as "2007-3". Some user facilities organize their beam time into run cycles.
	 * 
	 * @return  the value.
	 */
	public IDataset getRun_cycle();

	/**
	 * Name of program used to generate this file
	 * 
	 * @return  the value.
	 */
	public IDataset getProgram_name();

	/**
	 * Program version number
	 * 
	 * @return  the value.
	 */
	public String getProgram_nameAttributeVersion();

	/**
	 * configuration of the program
	 * 
	 * @return  the value.
	 */
	public String getProgram_nameAttributeConfiguration();

	/**
	 * Revision id of the file due to re-calibration, reprocessing, new analysis, new
	 * instrument definition format, ...
	 * 
	 * @return  the value.
	 */
	public IDataset getRevision();

	/**
	 * 
	 * @return  the value.
	 */
	public String getRevisionAttributeComment();

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
	 * Notes describing entry
	 * 
	 * @return  the value.
	 */
	public NXnote getNotes();

	/**
	 * A small image that is representative of the entry. An example of this is a 640x480
	 * jpeg image automatically produced by a low resolution plot of the NXdata.
	 * 
	 * @return  the value.
	 */
	public NXnote getThumbnail();

	/**
	 * 
	 * @return  the value.
	 */
	public NXcharacterization getCharacterization();
  
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
	 * Get all NXcharacterization nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcharacterization for that node.
	 */
	public Map<String, NXcharacterization> getAllCharacterization();

	/**
	 * 
	 * @return  the value.
	 */
	public NXuser getUser();
  
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
	 * Get all NXuser nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXuser for that node.
	 */
	public Map<String, NXuser> getAllUser();

	/**
	 * 
	 * @return  the value.
	 */
	public NXsample getSample();
  
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
	 * Get all NXsample nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsample for that node.
	 */
	public Map<String, NXsample> getAllSample();

	/**
	 * 
	 * @return  the value.
	 */
	public NXinstrument getInstrument();
  
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
	 * Get all NXinstrument nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXinstrument for that node.
	 */
	public Map<String, NXinstrument> getAllInstrument();

	/**
	 * 
	 * @return  the value.
	 */
	public NXcollection getCollection();
  
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
	 * Get all NXcollection nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcollection for that node.
	 */
	public Map<String, NXcollection> getAllCollection();

	/**
	 * 
	 * @return  the value.
	 */
	public NXmonitor getMonitor();
  
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
	 * Get all NXmonitor nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXmonitor for that node.
	 */
	public Map<String, NXmonitor> getAllMonitor();

	/**
	 * 
	 * @return  the value.
	 */
	public NXparameters getParameters();
  
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
	 * Get all NXparameters nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXparameters for that node.
	 */
	public Map<String, NXparameters> getAllParameters();

	/**
	 * 
	 * @return  the value.
	 */
	public NXprocess getProcess();
  
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
	 * Get all NXprocess nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXprocess for that node.
	 */
	public Map<String, NXprocess> getAllProcess();

	/**
	 * 
	 * @return  the value.
	 */
	public NXsubentry getSubentry();
  
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
	 * Get all NXsubentry nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsubentry for that node.
	 */
	public Map<String, NXsubentry> getAllSubentry();

}
