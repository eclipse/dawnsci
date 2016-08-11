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

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * Collection of the components of the instrument or beamline.
 * Template of instrument descriptions comprising various beamline components.
 * Each component will also be a NeXus group defined by its distance from the
 * sample. Negative distances represent beamline components that are before the
 * sample while positive distances represent components that are after the sample.
 * This device allows the unique identification of beamline components in a way
 * that is valid for both reactor and pulsed instrumentation.
 * 
 * @version 1.0
 */
public class NXinstrumentImpl extends NXobjectImpl implements NXinstrument {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_APERTURE,
		NexusBaseClass.NX_ATTENUATOR,
		NexusBaseClass.NX_BEAM,
		NexusBaseClass.NX_BEAM_STOP,
		NexusBaseClass.NX_BENDING_MAGNET,
		NexusBaseClass.NX_COLLIMATOR,
		NexusBaseClass.NX_COLLECTION,
		NexusBaseClass.NX_CAPILLARY,
		NexusBaseClass.NX_CRYSTAL,
		NexusBaseClass.NX_DETECTOR,
		NexusBaseClass.NX_DETECTOR_GROUP,
		NexusBaseClass.NX_DISK_CHOPPER,
		NexusBaseClass.NX_EVENT_DATA,
		NexusBaseClass.NX_FERMI_CHOPPER,
		NexusBaseClass.NX_FILTER,
		NexusBaseClass.NX_FLIPPER,
		NexusBaseClass.NX_GUIDE,
		NexusBaseClass.NX_INSERTION_DEVICE,
		NexusBaseClass.NX_MIRROR,
		NexusBaseClass.NX_MODERATOR,
		NexusBaseClass.NX_MONOCHROMATOR,
		NexusBaseClass.NX_POLARIZER,
		NexusBaseClass.NX_POSITIONER,
		NexusBaseClass.NX_SOURCE,
		NexusBaseClass.NX_VELOCITY_SELECTOR,
		NexusBaseClass.NX_XRAYLENS);

	public NXinstrumentImpl() {
		super();
	}

	public NXinstrumentImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXinstrument.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_INSTRUMENT;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getNameScalar() {
		return getString(NX_NAME);
	}

	@Override
	public DataNode setName(IDataset name) {
		return setDataset(NX_NAME, name);
	}

	@Override
	public DataNode setNameScalar(String name) {
		return setString(NX_NAME, name);
	}

	@Override
	public String getNameAttributeShort_name() {
		return getAttrString(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME);
	}

	@Override
	public void setNameAttributeShort_name(String short_name) {
		setAttribute(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME, short_name);
	}

	@Override
	public NXaperture getAperture() {
		return getChild("aperture", NXaperture.class);
	}

	@Override
	public void setAperture(NXaperture aperture) {
		putChild("aperture", aperture);
	}

	@Override
	public NXaperture getAperture(String name) {
		return getChild(name, NXaperture.class);
	}

	@Override
	public void setAperture(String name, NXaperture aperture) {
		putChild(name, aperture);
	}

	@Override
	public Map<String, NXaperture> getAllAperture() {
		return getChildren(NXaperture.class);
	}
	
	@Override
	public void setAllAperture(Map<String, NXaperture> aperture) {
		setChildren(aperture);
	}

	@Override
	public NXattenuator getAttenuator() {
		return getChild("attenuator", NXattenuator.class);
	}

	@Override
	public void setAttenuator(NXattenuator attenuator) {
		putChild("attenuator", attenuator);
	}

	@Override
	public NXattenuator getAttenuator(String name) {
		return getChild(name, NXattenuator.class);
	}

	@Override
	public void setAttenuator(String name, NXattenuator attenuator) {
		putChild(name, attenuator);
	}

	@Override
	public Map<String, NXattenuator> getAllAttenuator() {
		return getChildren(NXattenuator.class);
	}
	
	@Override
	public void setAllAttenuator(Map<String, NXattenuator> attenuator) {
		setChildren(attenuator);
	}

	@Override
	public NXbeam getBeam() {
		return getChild("beam", NXbeam.class);
	}

	@Override
	public void setBeam(NXbeam beam) {
		putChild("beam", beam);
	}

	@Override
	public NXbeam getBeam(String name) {
		return getChild(name, NXbeam.class);
	}

	@Override
	public void setBeam(String name, NXbeam beam) {
		putChild(name, beam);
	}

	@Override
	public Map<String, NXbeam> getAllBeam() {
		return getChildren(NXbeam.class);
	}
	
	@Override
	public void setAllBeam(Map<String, NXbeam> beam) {
		setChildren(beam);
	}

	@Override
	public NXbeam_stop getBeam_stop() {
		return getChild("beam_stop", NXbeam_stop.class);
	}

	@Override
	public void setBeam_stop(NXbeam_stop beam_stop) {
		putChild("beam_stop", beam_stop);
	}

	@Override
	public NXbeam_stop getBeam_stop(String name) {
		return getChild(name, NXbeam_stop.class);
	}

	@Override
	public void setBeam_stop(String name, NXbeam_stop beam_stop) {
		putChild(name, beam_stop);
	}

	@Override
	public Map<String, NXbeam_stop> getAllBeam_stop() {
		return getChildren(NXbeam_stop.class);
	}
	
	@Override
	public void setAllBeam_stop(Map<String, NXbeam_stop> beam_stop) {
		setChildren(beam_stop);
	}

	@Override
	public NXbending_magnet getBending_magnet() {
		return getChild("bending_magnet", NXbending_magnet.class);
	}

	@Override
	public void setBending_magnet(NXbending_magnet bending_magnet) {
		putChild("bending_magnet", bending_magnet);
	}

	@Override
	public NXbending_magnet getBending_magnet(String name) {
		return getChild(name, NXbending_magnet.class);
	}

	@Override
	public void setBending_magnet(String name, NXbending_magnet bending_magnet) {
		putChild(name, bending_magnet);
	}

	@Override
	public Map<String, NXbending_magnet> getAllBending_magnet() {
		return getChildren(NXbending_magnet.class);
	}
	
	@Override
	public void setAllBending_magnet(Map<String, NXbending_magnet> bending_magnet) {
		setChildren(bending_magnet);
	}

	@Override
	public NXcollimator getCollimator() {
		return getChild("collimator", NXcollimator.class);
	}

	@Override
	public void setCollimator(NXcollimator collimator) {
		putChild("collimator", collimator);
	}

	@Override
	public NXcollimator getCollimator(String name) {
		return getChild(name, NXcollimator.class);
	}

	@Override
	public void setCollimator(String name, NXcollimator collimator) {
		putChild(name, collimator);
	}

	@Override
	public Map<String, NXcollimator> getAllCollimator() {
		return getChildren(NXcollimator.class);
	}
	
	@Override
	public void setAllCollimator(Map<String, NXcollimator> collimator) {
		setChildren(collimator);
	}

	@Override
	public NXcollection getCollection() {
		return getChild("collection", NXcollection.class);
	}

	@Override
	public void setCollection(NXcollection collection) {
		putChild("collection", collection);
	}

	@Override
	public NXcollection getCollection(String name) {
		return getChild(name, NXcollection.class);
	}

	@Override
	public void setCollection(String name, NXcollection collection) {
		putChild(name, collection);
	}

	@Override
	public Map<String, NXcollection> getAllCollection() {
		return getChildren(NXcollection.class);
	}
	
	@Override
	public void setAllCollection(Map<String, NXcollection> collection) {
		setChildren(collection);
	}

	@Override
	public NXcapillary getCapillary() {
		return getChild("capillary", NXcapillary.class);
	}

	@Override
	public void setCapillary(NXcapillary capillary) {
		putChild("capillary", capillary);
	}

	@Override
	public NXcapillary getCapillary(String name) {
		return getChild(name, NXcapillary.class);
	}

	@Override
	public void setCapillary(String name, NXcapillary capillary) {
		putChild(name, capillary);
	}

	@Override
	public Map<String, NXcapillary> getAllCapillary() {
		return getChildren(NXcapillary.class);
	}
	
	@Override
	public void setAllCapillary(Map<String, NXcapillary> capillary) {
		setChildren(capillary);
	}

	@Override
	public NXcrystal getCrystal() {
		return getChild("crystal", NXcrystal.class);
	}

	@Override
	public void setCrystal(NXcrystal crystal) {
		putChild("crystal", crystal);
	}

	@Override
	public NXcrystal getCrystal(String name) {
		return getChild(name, NXcrystal.class);
	}

	@Override
	public void setCrystal(String name, NXcrystal crystal) {
		putChild(name, crystal);
	}

	@Override
	public Map<String, NXcrystal> getAllCrystal() {
		return getChildren(NXcrystal.class);
	}
	
	@Override
	public void setAllCrystal(Map<String, NXcrystal> crystal) {
		setChildren(crystal);
	}

	@Override
	public NXdetector getDetector() {
		return getChild("detector", NXdetector.class);
	}

	@Override
	public void setDetector(NXdetector detector) {
		putChild("detector", detector);
	}

	@Override
	public NXdetector getDetector(String name) {
		return getChild(name, NXdetector.class);
	}

	@Override
	public void setDetector(String name, NXdetector detector) {
		putChild(name, detector);
	}

	@Override
	public Map<String, NXdetector> getAllDetector() {
		return getChildren(NXdetector.class);
	}
	
	@Override
	public void setAllDetector(Map<String, NXdetector> detector) {
		setChildren(detector);
	}

	@Override
	public NXdetector_group getDetector_group() {
		return getChild("detector_group", NXdetector_group.class);
	}

	@Override
	public void setDetector_group(NXdetector_group detector_group) {
		putChild("detector_group", detector_group);
	}

	@Override
	public NXdetector_group getDetector_group(String name) {
		return getChild(name, NXdetector_group.class);
	}

	@Override
	public void setDetector_group(String name, NXdetector_group detector_group) {
		putChild(name, detector_group);
	}

	@Override
	public Map<String, NXdetector_group> getAllDetector_group() {
		return getChildren(NXdetector_group.class);
	}
	
	@Override
	public void setAllDetector_group(Map<String, NXdetector_group> detector_group) {
		setChildren(detector_group);
	}

	@Override
	public NXdisk_chopper getDisk_chopper() {
		return getChild("disk_chopper", NXdisk_chopper.class);
	}

	@Override
	public void setDisk_chopper(NXdisk_chopper disk_chopper) {
		putChild("disk_chopper", disk_chopper);
	}

	@Override
	public NXdisk_chopper getDisk_chopper(String name) {
		return getChild(name, NXdisk_chopper.class);
	}

	@Override
	public void setDisk_chopper(String name, NXdisk_chopper disk_chopper) {
		putChild(name, disk_chopper);
	}

	@Override
	public Map<String, NXdisk_chopper> getAllDisk_chopper() {
		return getChildren(NXdisk_chopper.class);
	}
	
	@Override
	public void setAllDisk_chopper(Map<String, NXdisk_chopper> disk_chopper) {
		setChildren(disk_chopper);
	}

	@Override
	public NXevent_data getEvent_data() {
		return getChild("event_data", NXevent_data.class);
	}

	@Override
	public void setEvent_data(NXevent_data event_data) {
		putChild("event_data", event_data);
	}

	@Override
	public NXevent_data getEvent_data(String name) {
		return getChild(name, NXevent_data.class);
	}

	@Override
	public void setEvent_data(String name, NXevent_data event_data) {
		putChild(name, event_data);
	}

	@Override
	public Map<String, NXevent_data> getAllEvent_data() {
		return getChildren(NXevent_data.class);
	}
	
	@Override
	public void setAllEvent_data(Map<String, NXevent_data> event_data) {
		setChildren(event_data);
	}

	@Override
	public NXfermi_chopper getFermi_chopper() {
		return getChild("fermi_chopper", NXfermi_chopper.class);
	}

	@Override
	public void setFermi_chopper(NXfermi_chopper fermi_chopper) {
		putChild("fermi_chopper", fermi_chopper);
	}

	@Override
	public NXfermi_chopper getFermi_chopper(String name) {
		return getChild(name, NXfermi_chopper.class);
	}

	@Override
	public void setFermi_chopper(String name, NXfermi_chopper fermi_chopper) {
		putChild(name, fermi_chopper);
	}

	@Override
	public Map<String, NXfermi_chopper> getAllFermi_chopper() {
		return getChildren(NXfermi_chopper.class);
	}
	
	@Override
	public void setAllFermi_chopper(Map<String, NXfermi_chopper> fermi_chopper) {
		setChildren(fermi_chopper);
	}

	@Override
	public NXfilter getFilter() {
		return getChild("filter", NXfilter.class);
	}

	@Override
	public void setFilter(NXfilter filter) {
		putChild("filter", filter);
	}

	@Override
	public NXfilter getFilter(String name) {
		return getChild(name, NXfilter.class);
	}

	@Override
	public void setFilter(String name, NXfilter filter) {
		putChild(name, filter);
	}

	@Override
	public Map<String, NXfilter> getAllFilter() {
		return getChildren(NXfilter.class);
	}
	
	@Override
	public void setAllFilter(Map<String, NXfilter> filter) {
		setChildren(filter);
	}

	@Override
	public NXflipper getFlipper() {
		return getChild("flipper", NXflipper.class);
	}

	@Override
	public void setFlipper(NXflipper flipper) {
		putChild("flipper", flipper);
	}

	@Override
	public NXflipper getFlipper(String name) {
		return getChild(name, NXflipper.class);
	}

	@Override
	public void setFlipper(String name, NXflipper flipper) {
		putChild(name, flipper);
	}

	@Override
	public Map<String, NXflipper> getAllFlipper() {
		return getChildren(NXflipper.class);
	}
	
	@Override
	public void setAllFlipper(Map<String, NXflipper> flipper) {
		setChildren(flipper);
	}

	@Override
	public NXguide getGuide() {
		return getChild("guide", NXguide.class);
	}

	@Override
	public void setGuide(NXguide guide) {
		putChild("guide", guide);
	}

	@Override
	public NXguide getGuide(String name) {
		return getChild(name, NXguide.class);
	}

	@Override
	public void setGuide(String name, NXguide guide) {
		putChild(name, guide);
	}

	@Override
	public Map<String, NXguide> getAllGuide() {
		return getChildren(NXguide.class);
	}
	
	@Override
	public void setAllGuide(Map<String, NXguide> guide) {
		setChildren(guide);
	}

	@Override
	public NXinsertion_device getInsertion_device() {
		return getChild("insertion_device", NXinsertion_device.class);
	}

	@Override
	public void setInsertion_device(NXinsertion_device insertion_device) {
		putChild("insertion_device", insertion_device);
	}

	@Override
	public NXinsertion_device getInsertion_device(String name) {
		return getChild(name, NXinsertion_device.class);
	}

	@Override
	public void setInsertion_device(String name, NXinsertion_device insertion_device) {
		putChild(name, insertion_device);
	}

	@Override
	public Map<String, NXinsertion_device> getAllInsertion_device() {
		return getChildren(NXinsertion_device.class);
	}
	
	@Override
	public void setAllInsertion_device(Map<String, NXinsertion_device> insertion_device) {
		setChildren(insertion_device);
	}

	@Override
	public NXmirror getMirror() {
		return getChild("mirror", NXmirror.class);
	}

	@Override
	public void setMirror(NXmirror mirror) {
		putChild("mirror", mirror);
	}

	@Override
	public NXmirror getMirror(String name) {
		return getChild(name, NXmirror.class);
	}

	@Override
	public void setMirror(String name, NXmirror mirror) {
		putChild(name, mirror);
	}

	@Override
	public Map<String, NXmirror> getAllMirror() {
		return getChildren(NXmirror.class);
	}
	
	@Override
	public void setAllMirror(Map<String, NXmirror> mirror) {
		setChildren(mirror);
	}

	@Override
	public NXmoderator getModerator() {
		return getChild("moderator", NXmoderator.class);
	}

	@Override
	public void setModerator(NXmoderator moderator) {
		putChild("moderator", moderator);
	}

	@Override
	public NXmoderator getModerator(String name) {
		return getChild(name, NXmoderator.class);
	}

	@Override
	public void setModerator(String name, NXmoderator moderator) {
		putChild(name, moderator);
	}

	@Override
	public Map<String, NXmoderator> getAllModerator() {
		return getChildren(NXmoderator.class);
	}
	
	@Override
	public void setAllModerator(Map<String, NXmoderator> moderator) {
		setChildren(moderator);
	}

	@Override
	public NXmonochromator getMonochromator() {
		return getChild("monochromator", NXmonochromator.class);
	}

	@Override
	public void setMonochromator(NXmonochromator monochromator) {
		putChild("monochromator", monochromator);
	}

	@Override
	public NXmonochromator getMonochromator(String name) {
		return getChild(name, NXmonochromator.class);
	}

	@Override
	public void setMonochromator(String name, NXmonochromator monochromator) {
		putChild(name, monochromator);
	}

	@Override
	public Map<String, NXmonochromator> getAllMonochromator() {
		return getChildren(NXmonochromator.class);
	}
	
	@Override
	public void setAllMonochromator(Map<String, NXmonochromator> monochromator) {
		setChildren(monochromator);
	}

	@Override
	public NXpolarizer getPolarizer() {
		return getChild("polarizer", NXpolarizer.class);
	}

	@Override
	public void setPolarizer(NXpolarizer polarizer) {
		putChild("polarizer", polarizer);
	}

	@Override
	public NXpolarizer getPolarizer(String name) {
		return getChild(name, NXpolarizer.class);
	}

	@Override
	public void setPolarizer(String name, NXpolarizer polarizer) {
		putChild(name, polarizer);
	}

	@Override
	public Map<String, NXpolarizer> getAllPolarizer() {
		return getChildren(NXpolarizer.class);
	}
	
	@Override
	public void setAllPolarizer(Map<String, NXpolarizer> polarizer) {
		setChildren(polarizer);
	}

	@Override
	public NXpositioner getPositioner() {
		return getChild("positioner", NXpositioner.class);
	}

	@Override
	public void setPositioner(NXpositioner positioner) {
		putChild("positioner", positioner);
	}

	@Override
	public NXpositioner getPositioner(String name) {
		return getChild(name, NXpositioner.class);
	}

	@Override
	public void setPositioner(String name, NXpositioner positioner) {
		putChild(name, positioner);
	}

	@Override
	public Map<String, NXpositioner> getAllPositioner() {
		return getChildren(NXpositioner.class);
	}
	
	@Override
	public void setAllPositioner(Map<String, NXpositioner> positioner) {
		setChildren(positioner);
	}

	@Override
	public NXsource getSource() {
		return getChild("source", NXsource.class);
	}

	@Override
	public void setSource(NXsource source) {
		putChild("source", source);
	}

	@Override
	public NXsource getSource(String name) {
		return getChild(name, NXsource.class);
	}

	@Override
	public void setSource(String name, NXsource source) {
		putChild(name, source);
	}

	@Override
	public Map<String, NXsource> getAllSource() {
		return getChildren(NXsource.class);
	}
	
	@Override
	public void setAllSource(Map<String, NXsource> source) {
		setChildren(source);
	}

	@Override
	public NXvelocity_selector getVelocity_selector() {
		return getChild("velocity_selector", NXvelocity_selector.class);
	}

	@Override
	public void setVelocity_selector(NXvelocity_selector velocity_selector) {
		putChild("velocity_selector", velocity_selector);
	}

	@Override
	public NXvelocity_selector getVelocity_selector(String name) {
		return getChild(name, NXvelocity_selector.class);
	}

	@Override
	public void setVelocity_selector(String name, NXvelocity_selector velocity_selector) {
		putChild(name, velocity_selector);
	}

	@Override
	public Map<String, NXvelocity_selector> getAllVelocity_selector() {
		return getChildren(NXvelocity_selector.class);
	}
	
	@Override
	public void setAllVelocity_selector(Map<String, NXvelocity_selector> velocity_selector) {
		setChildren(velocity_selector);
	}

	@Override
	public NXxraylens getXraylens() {
		return getChild("xraylens", NXxraylens.class);
	}

	@Override
	public void setXraylens(NXxraylens xraylens) {
		putChild("xraylens", xraylens);
	}

	@Override
	public NXxraylens getXraylens(String name) {
		return getChild(name, NXxraylens.class);
	}

	@Override
	public void setXraylens(String name, NXxraylens xraylens) {
		putChild(name, xraylens);
	}

	@Override
	public Map<String, NXxraylens> getAllXraylens() {
		return getChildren(NXxraylens.class);
	}
	
	@Override
	public void setAllXraylens(Map<String, NXxraylens> xraylens) {
		setChildren(xraylens);
	}

}
