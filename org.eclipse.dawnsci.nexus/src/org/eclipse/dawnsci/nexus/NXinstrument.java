/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-30T13:22:49.763Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of instrument descriptions comprising various beamline components.
 * Each component will also be a NeXus group defined by its distance from the
 * sample. Negative distances represent beamline components that are before the
 * sample while positive distances represent components that are after the sample.
 * This device allows the unique identification of beamline components in a way
 * that is valid for both reactor and pulsed instrumentation.
 * 
 * @version 1.0
 */
public interface NXinstrument extends NXobject {

	/**
	 * Name of instrument
	 * 
	 * @return  the value.
	 */
	public IDataset getName();	

	/**
	 * Name of instrument
	 * 
	 * @return  the value
	 */
	 public String getNameScalar();

	/**
	 * short name for instrument, perhaps the acronym
	 * 
	 * @return  the value.
	 */
	public String getNameAttributeShort_name();	

	/**
	 * 
	 * @return  the value.
	 */
	public NXaperture getAperture();	
  
	/**
	 * Get a NXaperture node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXaperture for that node.
	 */
	public NXaperture getAperture(String name);
	
	/**
	 * Get all NXaperture nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXaperture for that node.
	 */
	public Map<String, NXaperture> getAllAperture();

	/**
	 * 
	 * @return  the value.
	 */
	public NXattenuator getAttenuator();	
  
	/**
	 * Get a NXattenuator node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXattenuator for that node.
	 */
	public NXattenuator getAttenuator(String name);
	
	/**
	 * Get all NXattenuator nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXattenuator for that node.
	 */
	public Map<String, NXattenuator> getAllAttenuator();

	/**
	 * 
	 * @return  the value.
	 */
	public NXbeam getBeam();	
  
	/**
	 * Get a NXbeam node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXbeam for that node.
	 */
	public NXbeam getBeam(String name);
	
	/**
	 * Get all NXbeam nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXbeam for that node.
	 */
	public Map<String, NXbeam> getAllBeam();

	/**
	 * 
	 * @return  the value.
	 */
	public NXbeam_stop getBeam_stop();	
  
	/**
	 * Get a NXbeam_stop node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXbeam_stop for that node.
	 */
	public NXbeam_stop getBeam_stop(String name);
	
	/**
	 * Get all NXbeam_stop nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXbeam_stop for that node.
	 */
	public Map<String, NXbeam_stop> getAllBeam_stop();

	/**
	 * 
	 * @return  the value.
	 */
	public NXbending_magnet getBending_magnet();	
  
	/**
	 * Get a NXbending_magnet node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXbending_magnet for that node.
	 */
	public NXbending_magnet getBending_magnet(String name);
	
	/**
	 * Get all NXbending_magnet nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXbending_magnet for that node.
	 */
	public Map<String, NXbending_magnet> getAllBending_magnet();

	/**
	 * 
	 * @return  the value.
	 */
	public NXcollimator getCollimator();	
  
	/**
	 * Get a NXcollimator node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcollimator for that node.
	 */
	public NXcollimator getCollimator(String name);
	
	/**
	 * Get all NXcollimator nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcollimator for that node.
	 */
	public Map<String, NXcollimator> getAllCollimator();

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
	public NXcapillary getCapillary();	
  
	/**
	 * Get a NXcapillary node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcapillary for that node.
	 */
	public NXcapillary getCapillary(String name);
	
	/**
	 * Get all NXcapillary nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcapillary for that node.
	 */
	public Map<String, NXcapillary> getAllCapillary();

	/**
	 * 
	 * @return  the value.
	 */
	public NXcrystal getCrystal();	
  
	/**
	 * Get a NXcrystal node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcrystal for that node.
	 */
	public NXcrystal getCrystal(String name);
	
	/**
	 * Get all NXcrystal nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcrystal for that node.
	 */
	public Map<String, NXcrystal> getAllCrystal();

	/**
	 * 
	 * @return  the value.
	 */
	public NXdetector getDetector();	
  
	/**
	 * Get a NXdetector node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXdetector for that node.
	 */
	public NXdetector getDetector(String name);
	
	/**
	 * Get all NXdetector nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXdetector for that node.
	 */
	public Map<String, NXdetector> getAllDetector();

	/**
	 * 
	 * @return  the value.
	 */
	public NXdetector_group getDetector_group();	
  
	/**
	 * Get a NXdetector_group node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXdetector_group for that node.
	 */
	public NXdetector_group getDetector_group(String name);
	
	/**
	 * Get all NXdetector_group nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXdetector_group for that node.
	 */
	public Map<String, NXdetector_group> getAllDetector_group();

	/**
	 * 
	 * @return  the value.
	 */
	public NXdisk_chopper getDisk_chopper();	
  
	/**
	 * Get a NXdisk_chopper node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXdisk_chopper for that node.
	 */
	public NXdisk_chopper getDisk_chopper(String name);
	
	/**
	 * Get all NXdisk_chopper nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXdisk_chopper for that node.
	 */
	public Map<String, NXdisk_chopper> getAllDisk_chopper();

	/**
	 * 
	 * @return  the value.
	 */
	public NXevent_data getEvent_data();	
  
	/**
	 * Get a NXevent_data node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXevent_data for that node.
	 */
	public NXevent_data getEvent_data(String name);
	
	/**
	 * Get all NXevent_data nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXevent_data for that node.
	 */
	public Map<String, NXevent_data> getAllEvent_data();

	/**
	 * 
	 * @return  the value.
	 */
	public NXfermi_chopper getFermi_chopper();	
  
	/**
	 * Get a NXfermi_chopper node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXfermi_chopper for that node.
	 */
	public NXfermi_chopper getFermi_chopper(String name);
	
	/**
	 * Get all NXfermi_chopper nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXfermi_chopper for that node.
	 */
	public Map<String, NXfermi_chopper> getAllFermi_chopper();

	/**
	 * 
	 * @return  the value.
	 */
	public NXfilter getFilter();	
  
	/**
	 * Get a NXfilter node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXfilter for that node.
	 */
	public NXfilter getFilter(String name);
	
	/**
	 * Get all NXfilter nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXfilter for that node.
	 */
	public Map<String, NXfilter> getAllFilter();

	/**
	 * 
	 * @return  the value.
	 */
	public NXflipper getFlipper();	
  
	/**
	 * Get a NXflipper node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXflipper for that node.
	 */
	public NXflipper getFlipper(String name);
	
	/**
	 * Get all NXflipper nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXflipper for that node.
	 */
	public Map<String, NXflipper> getAllFlipper();

	/**
	 * 
	 * @return  the value.
	 */
	public NXguide getGuide();	
  
	/**
	 * Get a NXguide node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXguide for that node.
	 */
	public NXguide getGuide(String name);
	
	/**
	 * Get all NXguide nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXguide for that node.
	 */
	public Map<String, NXguide> getAllGuide();

	/**
	 * 
	 * @return  the value.
	 */
	public NXinsertion_device getInsertion_device();	
  
	/**
	 * Get a NXinsertion_device node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXinsertion_device for that node.
	 */
	public NXinsertion_device getInsertion_device(String name);
	
	/**
	 * Get all NXinsertion_device nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXinsertion_device for that node.
	 */
	public Map<String, NXinsertion_device> getAllInsertion_device();

	/**
	 * 
	 * @return  the value.
	 */
	public NXmirror getMirror();	
  
	/**
	 * Get a NXmirror node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXmirror for that node.
	 */
	public NXmirror getMirror(String name);
	
	/**
	 * Get all NXmirror nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXmirror for that node.
	 */
	public Map<String, NXmirror> getAllMirror();

	/**
	 * 
	 * @return  the value.
	 */
	public NXmoderator getModerator();	
  
	/**
	 * Get a NXmoderator node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXmoderator for that node.
	 */
	public NXmoderator getModerator(String name);
	
	/**
	 * Get all NXmoderator nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXmoderator for that node.
	 */
	public Map<String, NXmoderator> getAllModerator();

	/**
	 * 
	 * @return  the value.
	 */
	public NXmonochromator getMonochromator();	
  
	/**
	 * Get a NXmonochromator node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXmonochromator for that node.
	 */
	public NXmonochromator getMonochromator(String name);
	
	/**
	 * Get all NXmonochromator nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXmonochromator for that node.
	 */
	public Map<String, NXmonochromator> getAllMonochromator();

	/**
	 * 
	 * @return  the value.
	 */
	public NXpolarizer getPolarizer();	
  
	/**
	 * Get a NXpolarizer node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXpolarizer for that node.
	 */
	public NXpolarizer getPolarizer(String name);
	
	/**
	 * Get all NXpolarizer nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXpolarizer for that node.
	 */
	public Map<String, NXpolarizer> getAllPolarizer();

	/**
	 * 
	 * @return  the value.
	 */
	public NXpositioner getPositioner();	
  
	/**
	 * Get a NXpositioner node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXpositioner for that node.
	 */
	public NXpositioner getPositioner(String name);
	
	/**
	 * Get all NXpositioner nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXpositioner for that node.
	 */
	public Map<String, NXpositioner> getAllPositioner();

	/**
	 * 
	 * @return  the value.
	 */
	public NXsource getSource();	
  
	/**
	 * Get a NXsource node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXsource for that node.
	 */
	public NXsource getSource(String name);
	
	/**
	 * Get all NXsource nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsource for that node.
	 */
	public Map<String, NXsource> getAllSource();

	/**
	 * 
	 * @return  the value.
	 */
	public NXvelocity_selector getVelocity_selector();	
  
	/**
	 * Get a NXvelocity_selector node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXvelocity_selector for that node.
	 */
	public NXvelocity_selector getVelocity_selector(String name);
	
	/**
	 * Get all NXvelocity_selector nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXvelocity_selector for that node.
	 */
	public Map<String, NXvelocity_selector> getAllVelocity_selector();

	/**
	 * 
	 * @return  the value.
	 */
	public NXxraylens getXraylens();	
  
	/**
	 * Get a NXxraylens node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXxraylens for that node.
	 */
	public NXxraylens getXraylens(String name);
	
	/**
	 * Get all NXxraylens nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXxraylens for that node.
	 */
	public Map<String, NXxraylens> getAllXraylens();

}
