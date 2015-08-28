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

	public static final String NX_NAME = "name";
	public static final String NX_NAME_ATTRIBUTE_SHORT_NAME = "short_name";

	protected NXinstrumentImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXinstrument.class;
	}

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	public void setName(IDataset name) {
		setDataset(NX_NAME, name);
	}

	@Override
	public String getNameAttributeShort_name() {
		return getAttrString(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME);
	}

	public void setNameAttributeShort_name(String short_name) {
		setAttribute(NX_NAME, NX_NAME_ATTRIBUTE_SHORT_NAME, short_name);
	}

	@Override
	public NXaperture getAperture() {
		return getFirstChild(NXaperture.class);
	}

	public void setAperture(NXaperture aperture) {
		putChild(aperture);
	}

	@Override
	public NXaperture getAperture(String name) {
		return getChild(name, NXaperture.class);
	}

	public void setAperture(String name, NXaperture aperture) {
		putChild(name, aperture);
	}

	@Override
	public Map<String, NXaperture> getAllAperture() {
		return getChildren(NXaperture.class);
	}

	public void setAllAperture(Map<String, NXaperture> aperture) {
		setChildren(aperture);
	}

	@Override
	public NXattenuator getAttenuator() {
		return getFirstChild(NXattenuator.class);
	}

	public void setAttenuator(NXattenuator attenuator) {
		putChild(attenuator);
	}

	@Override
	public NXattenuator getAttenuator(String name) {
		return getChild(name, NXattenuator.class);
	}

	public void setAttenuator(String name, NXattenuator attenuator) {
		putChild(name, attenuator);
	}

	@Override
	public Map<String, NXattenuator> getAllAttenuator() {
		return getChildren(NXattenuator.class);
	}

	public void setAllAttenuator(Map<String, NXattenuator> attenuator) {
		setChildren(attenuator);
	}

	@Override
	public NXbeam getBeam() {
		return getFirstChild(NXbeam.class);
	}

	public void setBeam(NXbeam beam) {
		putChild(beam);
	}

	@Override
	public NXbeam getBeam(String name) {
		return getChild(name, NXbeam.class);
	}

	public void setBeam(String name, NXbeam beam) {
		putChild(name, beam);
	}

	@Override
	public Map<String, NXbeam> getAllBeam() {
		return getChildren(NXbeam.class);
	}

	public void setAllBeam(Map<String, NXbeam> beam) {
		setChildren(beam);
	}

	@Override
	public NXbeam_stop getBeam_stop() {
		return getFirstChild(NXbeam_stop.class);
	}

	public void setBeam_stop(NXbeam_stop beam_stop) {
		putChild(beam_stop);
	}

	@Override
	public NXbeam_stop getBeam_stop(String name) {
		return getChild(name, NXbeam_stop.class);
	}

	public void setBeam_stop(String name, NXbeam_stop beam_stop) {
		putChild(name, beam_stop);
	}

	@Override
	public Map<String, NXbeam_stop> getAllBeam_stop() {
		return getChildren(NXbeam_stop.class);
	}

	public void setAllBeam_stop(Map<String, NXbeam_stop> beam_stop) {
		setChildren(beam_stop);
	}

	@Override
	public NXbending_magnet getBending_magnet() {
		return getFirstChild(NXbending_magnet.class);
	}

	public void setBending_magnet(NXbending_magnet bending_magnet) {
		putChild(bending_magnet);
	}

	@Override
	public NXbending_magnet getBending_magnet(String name) {
		return getChild(name, NXbending_magnet.class);
	}

	public void setBending_magnet(String name, NXbending_magnet bending_magnet) {
		putChild(name, bending_magnet);
	}

	@Override
	public Map<String, NXbending_magnet> getAllBending_magnet() {
		return getChildren(NXbending_magnet.class);
	}

	public void setAllBending_magnet(Map<String, NXbending_magnet> bending_magnet) {
		setChildren(bending_magnet);
	}

	@Override
	public NXcollimator getCollimator() {
		return getFirstChild(NXcollimator.class);
	}

	public void setCollimator(NXcollimator collimator) {
		putChild(collimator);
	}

	@Override
	public NXcollimator getCollimator(String name) {
		return getChild(name, NXcollimator.class);
	}

	public void setCollimator(String name, NXcollimator collimator) {
		putChild(name, collimator);
	}

	@Override
	public Map<String, NXcollimator> getAllCollimator() {
		return getChildren(NXcollimator.class);
	}

	public void setAllCollimator(Map<String, NXcollimator> collimator) {
		setChildren(collimator);
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
	public NXcapillary getCapillary() {
		return getFirstChild(NXcapillary.class);
	}

	public void setCapillary(NXcapillary capillary) {
		putChild(capillary);
	}

	@Override
	public NXcapillary getCapillary(String name) {
		return getChild(name, NXcapillary.class);
	}

	public void setCapillary(String name, NXcapillary capillary) {
		putChild(name, capillary);
	}

	@Override
	public Map<String, NXcapillary> getAllCapillary() {
		return getChildren(NXcapillary.class);
	}

	public void setAllCapillary(Map<String, NXcapillary> capillary) {
		setChildren(capillary);
	}

	@Override
	public NXcrystal getCrystal() {
		return getFirstChild(NXcrystal.class);
	}

	public void setCrystal(NXcrystal crystal) {
		putChild(crystal);
	}

	@Override
	public NXcrystal getCrystal(String name) {
		return getChild(name, NXcrystal.class);
	}

	public void setCrystal(String name, NXcrystal crystal) {
		putChild(name, crystal);
	}

	@Override
	public Map<String, NXcrystal> getAllCrystal() {
		return getChildren(NXcrystal.class);
	}

	public void setAllCrystal(Map<String, NXcrystal> crystal) {
		setChildren(crystal);
	}

	@Override
	public NXdetector getDetector() {
		return getFirstChild(NXdetector.class);
	}

	public void setDetector(NXdetector detector) {
		putChild(detector);
	}

	@Override
	public NXdetector getDetector(String name) {
		return getChild(name, NXdetector.class);
	}

	public void setDetector(String name, NXdetector detector) {
		putChild(name, detector);
	}

	@Override
	public Map<String, NXdetector> getAllDetector() {
		return getChildren(NXdetector.class);
	}

	public void setAllDetector(Map<String, NXdetector> detector) {
		setChildren(detector);
	}

	@Override
	public NXdetector_group getDetector_group() {
		return getFirstChild(NXdetector_group.class);
	}

	public void setDetector_group(NXdetector_group detector_group) {
		putChild(detector_group);
	}

	@Override
	public NXdetector_group getDetector_group(String name) {
		return getChild(name, NXdetector_group.class);
	}

	public void setDetector_group(String name, NXdetector_group detector_group) {
		putChild(name, detector_group);
	}

	@Override
	public Map<String, NXdetector_group> getAllDetector_group() {
		return getChildren(NXdetector_group.class);
	}

	public void setAllDetector_group(Map<String, NXdetector_group> detector_group) {
		setChildren(detector_group);
	}

	@Override
	public NXdisk_chopper getDisk_chopper() {
		return getFirstChild(NXdisk_chopper.class);
	}

	public void setDisk_chopper(NXdisk_chopper disk_chopper) {
		putChild(disk_chopper);
	}

	@Override
	public NXdisk_chopper getDisk_chopper(String name) {
		return getChild(name, NXdisk_chopper.class);
	}

	public void setDisk_chopper(String name, NXdisk_chopper disk_chopper) {
		putChild(name, disk_chopper);
	}

	@Override
	public Map<String, NXdisk_chopper> getAllDisk_chopper() {
		return getChildren(NXdisk_chopper.class);
	}

	public void setAllDisk_chopper(Map<String, NXdisk_chopper> disk_chopper) {
		setChildren(disk_chopper);
	}

	@Override
	public NXevent_data getEvent_data() {
		return getFirstChild(NXevent_data.class);
	}

	public void setEvent_data(NXevent_data event_data) {
		putChild(event_data);
	}

	@Override
	public NXevent_data getEvent_data(String name) {
		return getChild(name, NXevent_data.class);
	}

	public void setEvent_data(String name, NXevent_data event_data) {
		putChild(name, event_data);
	}

	@Override
	public Map<String, NXevent_data> getAllEvent_data() {
		return getChildren(NXevent_data.class);
	}

	public void setAllEvent_data(Map<String, NXevent_data> event_data) {
		setChildren(event_data);
	}

	@Override
	public NXfermi_chopper getFermi_chopper() {
		return getFirstChild(NXfermi_chopper.class);
	}

	public void setFermi_chopper(NXfermi_chopper fermi_chopper) {
		putChild(fermi_chopper);
	}

	@Override
	public NXfermi_chopper getFermi_chopper(String name) {
		return getChild(name, NXfermi_chopper.class);
	}

	public void setFermi_chopper(String name, NXfermi_chopper fermi_chopper) {
		putChild(name, fermi_chopper);
	}

	@Override
	public Map<String, NXfermi_chopper> getAllFermi_chopper() {
		return getChildren(NXfermi_chopper.class);
	}

	public void setAllFermi_chopper(Map<String, NXfermi_chopper> fermi_chopper) {
		setChildren(fermi_chopper);
	}

	@Override
	public NXfilter getFilter() {
		return getFirstChild(NXfilter.class);
	}

	public void setFilter(NXfilter filter) {
		putChild(filter);
	}

	@Override
	public NXfilter getFilter(String name) {
		return getChild(name, NXfilter.class);
	}

	public void setFilter(String name, NXfilter filter) {
		putChild(name, filter);
	}

	@Override
	public Map<String, NXfilter> getAllFilter() {
		return getChildren(NXfilter.class);
	}

	public void setAllFilter(Map<String, NXfilter> filter) {
		setChildren(filter);
	}

	@Override
	public NXflipper getFlipper() {
		return getFirstChild(NXflipper.class);
	}

	public void setFlipper(NXflipper flipper) {
		putChild(flipper);
	}

	@Override
	public NXflipper getFlipper(String name) {
		return getChild(name, NXflipper.class);
	}

	public void setFlipper(String name, NXflipper flipper) {
		putChild(name, flipper);
	}

	@Override
	public Map<String, NXflipper> getAllFlipper() {
		return getChildren(NXflipper.class);
	}

	public void setAllFlipper(Map<String, NXflipper> flipper) {
		setChildren(flipper);
	}

	@Override
	public NXguide getGuide() {
		return getFirstChild(NXguide.class);
	}

	public void setGuide(NXguide guide) {
		putChild(guide);
	}

	@Override
	public NXguide getGuide(String name) {
		return getChild(name, NXguide.class);
	}

	public void setGuide(String name, NXguide guide) {
		putChild(name, guide);
	}

	@Override
	public Map<String, NXguide> getAllGuide() {
		return getChildren(NXguide.class);
	}

	public void setAllGuide(Map<String, NXguide> guide) {
		setChildren(guide);
	}

	@Override
	public NXinsertion_device getInsertion_device() {
		return getFirstChild(NXinsertion_device.class);
	}

	public void setInsertion_device(NXinsertion_device insertion_device) {
		putChild(insertion_device);
	}

	@Override
	public NXinsertion_device getInsertion_device(String name) {
		return getChild(name, NXinsertion_device.class);
	}

	public void setInsertion_device(String name, NXinsertion_device insertion_device) {
		putChild(name, insertion_device);
	}

	@Override
	public Map<String, NXinsertion_device> getAllInsertion_device() {
		return getChildren(NXinsertion_device.class);
	}

	public void setAllInsertion_device(Map<String, NXinsertion_device> insertion_device) {
		setChildren(insertion_device);
	}

	@Override
	public NXmirror getMirror() {
		return getFirstChild(NXmirror.class);
	}

	public void setMirror(NXmirror mirror) {
		putChild(mirror);
	}

	@Override
	public NXmirror getMirror(String name) {
		return getChild(name, NXmirror.class);
	}

	public void setMirror(String name, NXmirror mirror) {
		putChild(name, mirror);
	}

	@Override
	public Map<String, NXmirror> getAllMirror() {
		return getChildren(NXmirror.class);
	}

	public void setAllMirror(Map<String, NXmirror> mirror) {
		setChildren(mirror);
	}

	@Override
	public NXmoderator getModerator() {
		return getFirstChild(NXmoderator.class);
	}

	public void setModerator(NXmoderator moderator) {
		putChild(moderator);
	}

	@Override
	public NXmoderator getModerator(String name) {
		return getChild(name, NXmoderator.class);
	}

	public void setModerator(String name, NXmoderator moderator) {
		putChild(name, moderator);
	}

	@Override
	public Map<String, NXmoderator> getAllModerator() {
		return getChildren(NXmoderator.class);
	}

	public void setAllModerator(Map<String, NXmoderator> moderator) {
		setChildren(moderator);
	}

	@Override
	public NXmonochromator getMonochromator() {
		return getFirstChild(NXmonochromator.class);
	}

	public void setMonochromator(NXmonochromator monochromator) {
		putChild(monochromator);
	}

	@Override
	public NXmonochromator getMonochromator(String name) {
		return getChild(name, NXmonochromator.class);
	}

	public void setMonochromator(String name, NXmonochromator monochromator) {
		putChild(name, monochromator);
	}

	@Override
	public Map<String, NXmonochromator> getAllMonochromator() {
		return getChildren(NXmonochromator.class);
	}

	public void setAllMonochromator(Map<String, NXmonochromator> monochromator) {
		setChildren(monochromator);
	}

	@Override
	public NXpolarizer getPolarizer() {
		return getFirstChild(NXpolarizer.class);
	}

	public void setPolarizer(NXpolarizer polarizer) {
		putChild(polarizer);
	}

	@Override
	public NXpolarizer getPolarizer(String name) {
		return getChild(name, NXpolarizer.class);
	}

	public void setPolarizer(String name, NXpolarizer polarizer) {
		putChild(name, polarizer);
	}

	@Override
	public Map<String, NXpolarizer> getAllPolarizer() {
		return getChildren(NXpolarizer.class);
	}

	public void setAllPolarizer(Map<String, NXpolarizer> polarizer) {
		setChildren(polarizer);
	}

	@Override
	public NXpositioner getPositioner() {
		return getFirstChild(NXpositioner.class);
	}

	public void setPositioner(NXpositioner positioner) {
		putChild(positioner);
	}

	@Override
	public NXpositioner getPositioner(String name) {
		return getChild(name, NXpositioner.class);
	}

	public void setPositioner(String name, NXpositioner positioner) {
		putChild(name, positioner);
	}

	@Override
	public Map<String, NXpositioner> getAllPositioner() {
		return getChildren(NXpositioner.class);
	}

	public void setAllPositioner(Map<String, NXpositioner> positioner) {
		setChildren(positioner);
	}

	@Override
	public NXsource getSource() {
		return getFirstChild(NXsource.class);
	}

	public void setSource(NXsource source) {
		putChild(source);
	}

	@Override
	public NXsource getSource(String name) {
		return getChild(name, NXsource.class);
	}

	public void setSource(String name, NXsource source) {
		putChild(name, source);
	}

	@Override
	public Map<String, NXsource> getAllSource() {
		return getChildren(NXsource.class);
	}

	public void setAllSource(Map<String, NXsource> source) {
		setChildren(source);
	}

	@Override
	public NXvelocity_selector getVelocity_selector() {
		return getFirstChild(NXvelocity_selector.class);
	}

	public void setVelocity_selector(NXvelocity_selector velocity_selector) {
		putChild(velocity_selector);
	}

	@Override
	public NXvelocity_selector getVelocity_selector(String name) {
		return getChild(name, NXvelocity_selector.class);
	}

	public void setVelocity_selector(String name, NXvelocity_selector velocity_selector) {
		putChild(name, velocity_selector);
	}

	@Override
	public Map<String, NXvelocity_selector> getAllVelocity_selector() {
		return getChildren(NXvelocity_selector.class);
	}

	public void setAllVelocity_selector(Map<String, NXvelocity_selector> velocity_selector) {
		setChildren(velocity_selector);
	}

	@Override
	public NXxraylens getXraylens() {
		return getFirstChild(NXxraylens.class);
	}

	public void setXraylens(NXxraylens xraylens) {
		putChild(xraylens);
	}

	@Override
	public NXxraylens getXraylens(String name) {
		return getChild(name, NXxraylens.class);
	}

	public void setXraylens(String name, NXxraylens xraylens) {
		putChild(name, xraylens);
	}

	@Override
	public Map<String, NXxraylens> getAllXraylens() {
		return getChildren(NXxraylens.class);
	}

	public void setAllXraylens(Map<String, NXxraylens> xraylens) {
		setChildren(xraylens);
	}

}
