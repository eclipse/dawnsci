/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-01-22T17:16:17.085Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.net.URI;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeImpl;
import org.eclipse.dawnsci.nexus.NexusBaseClass;

import org.eclipse.dawnsci.nexus.impl.NXfresnel_zone_plateImpl;

import org.eclipse.dawnsci.nexus.impl.NXxraylensImpl;

import org.eclipse.dawnsci.nexus.impl.NXfermi_chopperImpl;

import org.eclipse.dawnsci.nexus.impl.NXmonochromatorImpl;

import org.eclipse.dawnsci.nexus.impl.NXvelocity_selectorImpl;

import org.eclipse.dawnsci.nexus.impl.NXnoteImpl;

import org.eclipse.dawnsci.nexus.impl.NXgratingImpl;

import org.eclipse.dawnsci.nexus.impl.NXbending_magnetImpl;

import org.eclipse.dawnsci.nexus.impl.NXlogImpl;

import org.eclipse.dawnsci.nexus.impl.NXdisk_chopperImpl;

import org.eclipse.dawnsci.nexus.impl.NXcharacterizationImpl;

import org.eclipse.dawnsci.nexus.impl.NXshapeImpl;

import org.eclipse.dawnsci.nexus.impl.NXcapillaryImpl;

import org.eclipse.dawnsci.nexus.impl.NXguideImpl;

import org.eclipse.dawnsci.nexus.impl.NXbeamImpl;

import org.eclipse.dawnsci.nexus.impl.NXsampleImpl;

import org.eclipse.dawnsci.nexus.impl.NXmirrorImpl;

import org.eclipse.dawnsci.nexus.impl.NXciteImpl;

import org.eclipse.dawnsci.nexus.impl.NXentryImpl;

import org.eclipse.dawnsci.nexus.impl.NXflipperImpl;

import org.eclipse.dawnsci.nexus.impl.NXpositionerImpl;

import org.eclipse.dawnsci.nexus.impl.NXcollimatorImpl;

import org.eclipse.dawnsci.nexus.impl.NXmoderatorImpl;

import org.eclipse.dawnsci.nexus.impl.NXgeometryImpl;

import org.eclipse.dawnsci.nexus.impl.NXparametersImpl;

import org.eclipse.dawnsci.nexus.impl.NXorientationImpl;

import org.eclipse.dawnsci.nexus.impl.NXenvironmentImpl;

import org.eclipse.dawnsci.nexus.impl.NXmonitorImpl;

import org.eclipse.dawnsci.nexus.impl.NXrootImpl;

import org.eclipse.dawnsci.nexus.impl.NXprocessImpl;

import org.eclipse.dawnsci.nexus.impl.NXsourceImpl;

import org.eclipse.dawnsci.nexus.impl.NXuserImpl;

import org.eclipse.dawnsci.nexus.impl.NXcrystalImpl;

import org.eclipse.dawnsci.nexus.impl.NXtranslationImpl;

import org.eclipse.dawnsci.nexus.impl.NXinstrumentImpl;

import org.eclipse.dawnsci.nexus.impl.NXpolarizerImpl;

import org.eclipse.dawnsci.nexus.impl.NXdataImpl;

import org.eclipse.dawnsci.nexus.impl.NXpinholeImpl;

import org.eclipse.dawnsci.nexus.impl.NXsubentryImpl;

import org.eclipse.dawnsci.nexus.impl.NXapertureImpl;

import org.eclipse.dawnsci.nexus.impl.NXfilterImpl;

import org.eclipse.dawnsci.nexus.impl.NXevent_dataImpl;

import org.eclipse.dawnsci.nexus.impl.NXslitImpl;

import org.eclipse.dawnsci.nexus.impl.NXdetector_groupImpl;

import org.eclipse.dawnsci.nexus.impl.NXdetector_moduleImpl;

import org.eclipse.dawnsci.nexus.impl.NXinsertion_deviceImpl;

import org.eclipse.dawnsci.nexus.impl.NXbeam_stopImpl;

import org.eclipse.dawnsci.nexus.impl.NXtransformationsImpl;

import org.eclipse.dawnsci.nexus.impl.NXsensorImpl;

import org.eclipse.dawnsci.nexus.impl.NXattenuatorImpl;

import org.eclipse.dawnsci.nexus.impl.NXdetectorImpl;

import org.eclipse.dawnsci.nexus.impl.NXcollectionImpl;

import org.eclipse.dawnsci.nexus.impl.NXmagnetic_kickerImpl;

import org.eclipse.dawnsci.nexus.impl.NXquadrupole_magnetImpl;

import org.eclipse.dawnsci.nexus.impl.NXspin_rotatorImpl;

import org.eclipse.dawnsci.nexus.impl.NXsolenoid_magnetImpl;

import org.eclipse.dawnsci.nexus.impl.NXelectrostatic_kickerImpl;

import org.eclipse.dawnsci.nexus.impl.NXseparatorImpl;

/**
 * Factory class for creating instances of NeXus base classes.
 */
public class NexusNodeFactory {
	
	private long nextOid = 1l;

	public static NXobject createNXobjectForClass(String baseClassName, long oid) {
		final NexusBaseClass baseClass = NexusBaseClass.getBaseClassForName(baseClassName);
		return createNXobjectForClass(baseClass, oid);
	}
	
	public static NXobject createNXobjectForClass(NexusBaseClass baseClass, long oid) {
		switch (baseClass) {
			case NX_FRESNEL_ZONE_PLATE:
				return createNXfresnel_zone_plate(oid);
			case NX_XRAYLENS:
				return createNXxraylens(oid);
			case NX_FERMI_CHOPPER:
				return createNXfermi_chopper(oid);
			case NX_MONOCHROMATOR:
				return createNXmonochromator(oid);
			case NX_VELOCITY_SELECTOR:
				return createNXvelocity_selector(oid);
			case NX_NOTE:
				return createNXnote(oid);
			case NX_GRATING:
				return createNXgrating(oid);
			case NX_BENDING_MAGNET:
				return createNXbending_magnet(oid);
			case NX_LOG:
				return createNXlog(oid);
			case NX_DISK_CHOPPER:
				return createNXdisk_chopper(oid);
			case NX_CHARACTERIZATION:
				return createNXcharacterization(oid);
			case NX_SHAPE:
				return createNXshape(oid);
			case NX_CAPILLARY:
				return createNXcapillary(oid);
			case NX_GUIDE:
				return createNXguide(oid);
			case NX_BEAM:
				return createNXbeam(oid);
			case NX_SAMPLE:
				return createNXsample(oid);
			case NX_MIRROR:
				return createNXmirror(oid);
			case NX_CITE:
				return createNXcite(oid);
			case NX_ENTRY:
				return createNXentry(oid);
			case NX_FLIPPER:
				return createNXflipper(oid);
			case NX_POSITIONER:
				return createNXpositioner(oid);
			case NX_COLLIMATOR:
				return createNXcollimator(oid);
			case NX_MODERATOR:
				return createNXmoderator(oid);
			case NX_GEOMETRY:
				return createNXgeometry(oid);
			case NX_PARAMETERS:
				return createNXparameters(oid);
			case NX_ORIENTATION:
				return createNXorientation(oid);
			case NX_ENVIRONMENT:
				return createNXenvironment(oid);
			case NX_MONITOR:
				return createNXmonitor(oid);
			case NX_ROOT:
				return createNXroot(oid);
			case NX_PROCESS:
				return createNXprocess(oid);
			case NX_SOURCE:
				return createNXsource(oid);
			case NX_USER:
				return createNXuser(oid);
			case NX_CRYSTAL:
				return createNXcrystal(oid);
			case NX_TRANSLATION:
				return createNXtranslation(oid);
			case NX_INSTRUMENT:
				return createNXinstrument(oid);
			case NX_POLARIZER:
				return createNXpolarizer(oid);
			case NX_DATA:
				return createNXdata(oid);
			case NX_PINHOLE:
				return createNXpinhole(oid);
			case NX_SUBENTRY:
				return createNXsubentry(oid);
			case NX_APERTURE:
				return createNXaperture(oid);
			case NX_FILTER:
				return createNXfilter(oid);
			case NX_EVENT_DATA:
				return createNXevent_data(oid);
			case NX_SLIT:
				return createNXslit(oid);
			case NX_DETECTOR_GROUP:
				return createNXdetector_group(oid);
			case NX_DETECTOR_MODULE:
				return createNXdetector_module(oid);
			case NX_INSERTION_DEVICE:
				return createNXinsertion_device(oid);
			case NX_BEAM_STOP:
				return createNXbeam_stop(oid);
			case NX_TRANSFORMATIONS:
				return createNXtransformations(oid);
			case NX_SENSOR:
				return createNXsensor(oid);
			case NX_ATTENUATOR:
				return createNXattenuator(oid);
			case NX_DETECTOR:
				return createNXdetector(oid);
			case NX_COLLECTION:
				return createNXcollection(oid);
			case NX_MAGNETIC_KICKER:
				return createNXmagnetic_kicker(oid);
			case NX_QUADRUPOLE_MAGNET:
				return createNXquadrupole_magnet(oid);
			case NX_SPIN_ROTATOR:
				return createNXspin_rotator(oid);
			case NX_SOLENOID_MAGNET:
				return createNXsolenoid_magnet(oid);
			case NX_ELECTROSTATIC_KICKER:
				return createNXelectrostatic_kicker(oid);
			case NX_SEPARATOR:
				return createNXseparator(oid);
		}
		throw new IllegalArgumentException("Unknown base class: " + baseClass);
	}

	public long getNextOid() {
		return nextOid++;
	}
	
	/**
	 * Create a new tree with given URI
	 * @param uri
	 */
	public TreeImpl createTree(final URI uri) {
		return new TreeImpl(getNextOid(), uri);
	}
	
	/**
	 * Create a new tree file with given URI
	 * @param uri uri
	 */
	public TreeFileImpl createTreeFile(final URI uri) {
		return new TreeFileImpl(getNextOid(), uri);
	}
	
	/**
	 * Create a new tree file with given file name
	 * @param filename filename
	 * @return
	 */
	public TreeFileImpl createTreeFile(final String fileName) {
		return new TreeFileImpl(getNextOid(), fileName);
	}
	
	/**
	 * Create a new data node.
	 */
	public DataNode createDataNode() {
		return TreeFactory.createDataNode(getNextOid());
	}
	
	/**
	 * Create a new NXfresnel_zone_plate with the given oid.
	 */
	public static NXfresnel_zone_plate createNXfresnel_zone_plate(long oid) {
		return new NXfresnel_zone_plateImpl(oid);
	}

	/**
	 * Create a new NXfresnel_zone_plate.
	 */
	public NXfresnel_zone_plate createNXfresnel_zone_plate() {
		return new NXfresnel_zone_plateImpl(this);
	}

	/**
	 * Create a new NXxraylens with the given oid.
	 */
	public static NXxraylens createNXxraylens(long oid) {
		return new NXxraylensImpl(oid);
	}

	/**
	 * Create a new NXxraylens.
	 */
	public NXxraylens createNXxraylens() {
		return new NXxraylensImpl(this);
	}

	/**
	 * Create a new NXfermi_chopper with the given oid.
	 */
	public static NXfermi_chopper createNXfermi_chopper(long oid) {
		return new NXfermi_chopperImpl(oid);
	}

	/**
	 * Create a new NXfermi_chopper.
	 */
	public NXfermi_chopper createNXfermi_chopper() {
		return new NXfermi_chopperImpl(this);
	}

	/**
	 * Create a new NXmonochromator with the given oid.
	 */
	public static NXmonochromator createNXmonochromator(long oid) {
		return new NXmonochromatorImpl(oid);
	}

	/**
	 * Create a new NXmonochromator.
	 */
	public NXmonochromator createNXmonochromator() {
		return new NXmonochromatorImpl(this);
	}

	/**
	 * Create a new NXvelocity_selector with the given oid.
	 */
	public static NXvelocity_selector createNXvelocity_selector(long oid) {
		return new NXvelocity_selectorImpl(oid);
	}

	/**
	 * Create a new NXvelocity_selector.
	 */
	public NXvelocity_selector createNXvelocity_selector() {
		return new NXvelocity_selectorImpl(this);
	}

	/**
	 * Create a new NXnote with the given oid.
	 */
	public static NXnote createNXnote(long oid) {
		return new NXnoteImpl(oid);
	}

	/**
	 * Create a new NXnote.
	 */
	public NXnote createNXnote() {
		return new NXnoteImpl(this);
	}

	/**
	 * Create a new NXgrating with the given oid.
	 */
	public static NXgrating createNXgrating(long oid) {
		return new NXgratingImpl(oid);
	}

	/**
	 * Create a new NXgrating.
	 */
	public NXgrating createNXgrating() {
		return new NXgratingImpl(this);
	}

	/**
	 * Create a new NXbending_magnet with the given oid.
	 */
	public static NXbending_magnet createNXbending_magnet(long oid) {
		return new NXbending_magnetImpl(oid);
	}

	/**
	 * Create a new NXbending_magnet.
	 */
	public NXbending_magnet createNXbending_magnet() {
		return new NXbending_magnetImpl(this);
	}

	/**
	 * Create a new NXlog with the given oid.
	 */
	public static NXlog createNXlog(long oid) {
		return new NXlogImpl(oid);
	}

	/**
	 * Create a new NXlog.
	 */
	public NXlog createNXlog() {
		return new NXlogImpl(this);
	}

	/**
	 * Create a new NXdisk_chopper with the given oid.
	 */
	public static NXdisk_chopper createNXdisk_chopper(long oid) {
		return new NXdisk_chopperImpl(oid);
	}

	/**
	 * Create a new NXdisk_chopper.
	 */
	public NXdisk_chopper createNXdisk_chopper() {
		return new NXdisk_chopperImpl(this);
	}

	/**
	 * Create a new NXcharacterization with the given oid.
	 */
	public static NXcharacterization createNXcharacterization(long oid) {
		return new NXcharacterizationImpl(oid);
	}

	/**
	 * Create a new NXcharacterization.
	 */
	public NXcharacterization createNXcharacterization() {
		return new NXcharacterizationImpl(this);
	}

	/**
	 * Create a new NXshape with the given oid.
	 */
	public static NXshape createNXshape(long oid) {
		return new NXshapeImpl(oid);
	}

	/**
	 * Create a new NXshape.
	 */
	public NXshape createNXshape() {
		return new NXshapeImpl(this);
	}

	/**
	 * Create a new NXcapillary with the given oid.
	 */
	public static NXcapillary createNXcapillary(long oid) {
		return new NXcapillaryImpl(oid);
	}

	/**
	 * Create a new NXcapillary.
	 */
	public NXcapillary createNXcapillary() {
		return new NXcapillaryImpl(this);
	}

	/**
	 * Create a new NXguide with the given oid.
	 */
	public static NXguide createNXguide(long oid) {
		return new NXguideImpl(oid);
	}

	/**
	 * Create a new NXguide.
	 */
	public NXguide createNXguide() {
		return new NXguideImpl(this);
	}

	/**
	 * Create a new NXbeam with the given oid.
	 */
	public static NXbeam createNXbeam(long oid) {
		return new NXbeamImpl(oid);
	}

	/**
	 * Create a new NXbeam.
	 */
	public NXbeam createNXbeam() {
		return new NXbeamImpl(this);
	}

	/**
	 * Create a new NXsample with the given oid.
	 */
	public static NXsample createNXsample(long oid) {
		return new NXsampleImpl(oid);
	}

	/**
	 * Create a new NXsample.
	 */
	public NXsample createNXsample() {
		return new NXsampleImpl(this);
	}

	/**
	 * Create a new NXmirror with the given oid.
	 */
	public static NXmirror createNXmirror(long oid) {
		return new NXmirrorImpl(oid);
	}

	/**
	 * Create a new NXmirror.
	 */
	public NXmirror createNXmirror() {
		return new NXmirrorImpl(this);
	}

	/**
	 * Create a new NXcite with the given oid.
	 */
	public static NXcite createNXcite(long oid) {
		return new NXciteImpl(oid);
	}

	/**
	 * Create a new NXcite.
	 */
	public NXcite createNXcite() {
		return new NXciteImpl(this);
	}

	/**
	 * Create a new NXentry with the given oid.
	 */
	public static NXentry createNXentry(long oid) {
		return new NXentryImpl(oid);
	}

	/**
	 * Create a new NXentry.
	 */
	public NXentry createNXentry() {
		return new NXentryImpl(this);
	}

	/**
	 * Create a new NXflipper with the given oid.
	 */
	public static NXflipper createNXflipper(long oid) {
		return new NXflipperImpl(oid);
	}

	/**
	 * Create a new NXflipper.
	 */
	public NXflipper createNXflipper() {
		return new NXflipperImpl(this);
	}

	/**
	 * Create a new NXpositioner with the given oid.
	 */
	public static NXpositioner createNXpositioner(long oid) {
		return new NXpositionerImpl(oid);
	}

	/**
	 * Create a new NXpositioner.
	 */
	public NXpositioner createNXpositioner() {
		return new NXpositionerImpl(this);
	}

	/**
	 * Create a new NXcollimator with the given oid.
	 */
	public static NXcollimator createNXcollimator(long oid) {
		return new NXcollimatorImpl(oid);
	}

	/**
	 * Create a new NXcollimator.
	 */
	public NXcollimator createNXcollimator() {
		return new NXcollimatorImpl(this);
	}

	/**
	 * Create a new NXmoderator with the given oid.
	 */
	public static NXmoderator createNXmoderator(long oid) {
		return new NXmoderatorImpl(oid);
	}

	/**
	 * Create a new NXmoderator.
	 */
	public NXmoderator createNXmoderator() {
		return new NXmoderatorImpl(this);
	}

	/**
	 * Create a new NXgeometry with the given oid.
	 */
	public static NXgeometry createNXgeometry(long oid) {
		return new NXgeometryImpl(oid);
	}

	/**
	 * Create a new NXgeometry.
	 */
	public NXgeometry createNXgeometry() {
		return new NXgeometryImpl(this);
	}

	/**
	 * Create a new NXparameters with the given oid.
	 */
	public static NXparameters createNXparameters(long oid) {
		return new NXparametersImpl(oid);
	}

	/**
	 * Create a new NXparameters.
	 */
	public NXparameters createNXparameters() {
		return new NXparametersImpl(this);
	}

	/**
	 * Create a new NXorientation with the given oid.
	 */
	public static NXorientation createNXorientation(long oid) {
		return new NXorientationImpl(oid);
	}

	/**
	 * Create a new NXorientation.
	 */
	public NXorientation createNXorientation() {
		return new NXorientationImpl(this);
	}

	/**
	 * Create a new NXenvironment with the given oid.
	 */
	public static NXenvironment createNXenvironment(long oid) {
		return new NXenvironmentImpl(oid);
	}

	/**
	 * Create a new NXenvironment.
	 */
	public NXenvironment createNXenvironment() {
		return new NXenvironmentImpl(this);
	}

	/**
	 * Create a new NXmonitor with the given oid.
	 */
	public static NXmonitor createNXmonitor(long oid) {
		return new NXmonitorImpl(oid);
	}

	/**
	 * Create a new NXmonitor.
	 */
	public NXmonitor createNXmonitor() {
		return new NXmonitorImpl(this);
	}

	/**
	 * Create a new NXroot with the given oid.
	 */
	public static NXroot createNXroot(long oid) {
		return new NXrootImpl(oid);
	}

	/**
	 * Create a new NXroot.
	 */
	public NXroot createNXroot() {
		return new NXrootImpl(this);
	}

	/**
	 * Create a new NXprocess with the given oid.
	 */
	public static NXprocess createNXprocess(long oid) {
		return new NXprocessImpl(oid);
	}

	/**
	 * Create a new NXprocess.
	 */
	public NXprocess createNXprocess() {
		return new NXprocessImpl(this);
	}

	/**
	 * Create a new NXsource with the given oid.
	 */
	public static NXsource createNXsource(long oid) {
		return new NXsourceImpl(oid);
	}

	/**
	 * Create a new NXsource.
	 */
	public NXsource createNXsource() {
		return new NXsourceImpl(this);
	}

	/**
	 * Create a new NXuser with the given oid.
	 */
	public static NXuser createNXuser(long oid) {
		return new NXuserImpl(oid);
	}

	/**
	 * Create a new NXuser.
	 */
	public NXuser createNXuser() {
		return new NXuserImpl(this);
	}

	/**
	 * Create a new NXcrystal with the given oid.
	 */
	public static NXcrystal createNXcrystal(long oid) {
		return new NXcrystalImpl(oid);
	}

	/**
	 * Create a new NXcrystal.
	 */
	public NXcrystal createNXcrystal() {
		return new NXcrystalImpl(this);
	}

	/**
	 * Create a new NXtranslation with the given oid.
	 */
	public static NXtranslation createNXtranslation(long oid) {
		return new NXtranslationImpl(oid);
	}

	/**
	 * Create a new NXtranslation.
	 */
	public NXtranslation createNXtranslation() {
		return new NXtranslationImpl(this);
	}

	/**
	 * Create a new NXinstrument with the given oid.
	 */
	public static NXinstrument createNXinstrument(long oid) {
		return new NXinstrumentImpl(oid);
	}

	/**
	 * Create a new NXinstrument.
	 */
	public NXinstrument createNXinstrument() {
		return new NXinstrumentImpl(this);
	}

	/**
	 * Create a new NXpolarizer with the given oid.
	 */
	public static NXpolarizer createNXpolarizer(long oid) {
		return new NXpolarizerImpl(oid);
	}

	/**
	 * Create a new NXpolarizer.
	 */
	public NXpolarizer createNXpolarizer() {
		return new NXpolarizerImpl(this);
	}

	/**
	 * Create a new NXdata with the given oid.
	 */
	public static NXdata createNXdata(long oid) {
		return new NXdataImpl(oid);
	}

	/**
	 * Create a new NXdata.
	 */
	public NXdata createNXdata() {
		return new NXdataImpl(this);
	}

	/**
	 * Create a new NXpinhole with the given oid.
	 */
	public static NXpinhole createNXpinhole(long oid) {
		return new NXpinholeImpl(oid);
	}

	/**
	 * Create a new NXpinhole.
	 */
	public NXpinhole createNXpinhole() {
		return new NXpinholeImpl(this);
	}

	/**
	 * Create a new NXsubentry with the given oid.
	 */
	public static NXsubentry createNXsubentry(long oid) {
		return new NXsubentryImpl(oid);
	}

	/**
	 * Create a new NXsubentry.
	 */
	public NXsubentry createNXsubentry() {
		return new NXsubentryImpl(this);
	}

	/**
	 * Create a new NXaperture with the given oid.
	 */
	public static NXaperture createNXaperture(long oid) {
		return new NXapertureImpl(oid);
	}

	/**
	 * Create a new NXaperture.
	 */
	public NXaperture createNXaperture() {
		return new NXapertureImpl(this);
	}

	/**
	 * Create a new NXfilter with the given oid.
	 */
	public static NXfilter createNXfilter(long oid) {
		return new NXfilterImpl(oid);
	}

	/**
	 * Create a new NXfilter.
	 */
	public NXfilter createNXfilter() {
		return new NXfilterImpl(this);
	}

	/**
	 * Create a new NXevent_data with the given oid.
	 */
	public static NXevent_data createNXevent_data(long oid) {
		return new NXevent_dataImpl(oid);
	}

	/**
	 * Create a new NXevent_data.
	 */
	public NXevent_data createNXevent_data() {
		return new NXevent_dataImpl(this);
	}

	/**
	 * Create a new NXslit with the given oid.
	 */
	public static NXslit createNXslit(long oid) {
		return new NXslitImpl(oid);
	}

	/**
	 * Create a new NXslit.
	 */
	public NXslit createNXslit() {
		return new NXslitImpl(this);
	}

	/**
	 * Create a new NXdetector_group with the given oid.
	 */
	public static NXdetector_group createNXdetector_group(long oid) {
		return new NXdetector_groupImpl(oid);
	}

	/**
	 * Create a new NXdetector_group.
	 */
	public NXdetector_group createNXdetector_group() {
		return new NXdetector_groupImpl(this);
	}

	/**
	 * Create a new NXdetector_module with the given oid.
	 */
	public static NXdetector_module createNXdetector_module(long oid) {
		return new NXdetector_moduleImpl(oid);
	}

	/**
	 * Create a new NXdetector_module.
	 */
	public NXdetector_module createNXdetector_module() {
		return new NXdetector_moduleImpl(this);
	}

	/**
	 * Create a new NXinsertion_device with the given oid.
	 */
	public static NXinsertion_device createNXinsertion_device(long oid) {
		return new NXinsertion_deviceImpl(oid);
	}

	/**
	 * Create a new NXinsertion_device.
	 */
	public NXinsertion_device createNXinsertion_device() {
		return new NXinsertion_deviceImpl(this);
	}

	/**
	 * Create a new NXbeam_stop with the given oid.
	 */
	public static NXbeam_stop createNXbeam_stop(long oid) {
		return new NXbeam_stopImpl(oid);
	}

	/**
	 * Create a new NXbeam_stop.
	 */
	public NXbeam_stop createNXbeam_stop() {
		return new NXbeam_stopImpl(this);
	}

	/**
	 * Create a new NXtransformations with the given oid.
	 */
	public static NXtransformations createNXtransformations(long oid) {
		return new NXtransformationsImpl(oid);
	}

	/**
	 * Create a new NXtransformations.
	 */
	public NXtransformations createNXtransformations() {
		return new NXtransformationsImpl(this);
	}

	/**
	 * Create a new NXsensor with the given oid.
	 */
	public static NXsensor createNXsensor(long oid) {
		return new NXsensorImpl(oid);
	}

	/**
	 * Create a new NXsensor.
	 */
	public NXsensor createNXsensor() {
		return new NXsensorImpl(this);
	}

	/**
	 * Create a new NXattenuator with the given oid.
	 */
	public static NXattenuator createNXattenuator(long oid) {
		return new NXattenuatorImpl(oid);
	}

	/**
	 * Create a new NXattenuator.
	 */
	public NXattenuator createNXattenuator() {
		return new NXattenuatorImpl(this);
	}

	/**
	 * Create a new NXdetector with the given oid.
	 */
	public static NXdetector createNXdetector(long oid) {
		return new NXdetectorImpl(oid);
	}

	/**
	 * Create a new NXdetector.
	 */
	public NXdetector createNXdetector() {
		return new NXdetectorImpl(this);
	}

	/**
	 * Create a new NXcollection with the given oid.
	 */
	public static NXcollection createNXcollection(long oid) {
		return new NXcollectionImpl(oid);
	}

	/**
	 * Create a new NXcollection.
	 */
	public NXcollection createNXcollection() {
		return new NXcollectionImpl(this);
	}

	/**
	 * Create a new NXmagnetic_kicker with the given oid.
	 */
	public static NXmagnetic_kicker createNXmagnetic_kicker(long oid) {
		return new NXmagnetic_kickerImpl(oid);
	}

	/**
	 * Create a new NXmagnetic_kicker.
	 */
	public NXmagnetic_kicker createNXmagnetic_kicker() {
		return new NXmagnetic_kickerImpl(this);
	}

	/**
	 * Create a new NXquadrupole_magnet with the given oid.
	 */
	public static NXquadrupole_magnet createNXquadrupole_magnet(long oid) {
		return new NXquadrupole_magnetImpl(oid);
	}

	/**
	 * Create a new NXquadrupole_magnet.
	 */
	public NXquadrupole_magnet createNXquadrupole_magnet() {
		return new NXquadrupole_magnetImpl(this);
	}

	/**
	 * Create a new NXspin_rotator with the given oid.
	 */
	public static NXspin_rotator createNXspin_rotator(long oid) {
		return new NXspin_rotatorImpl(oid);
	}

	/**
	 * Create a new NXspin_rotator.
	 */
	public NXspin_rotator createNXspin_rotator() {
		return new NXspin_rotatorImpl(this);
	}

	/**
	 * Create a new NXsolenoid_magnet with the given oid.
	 */
	public static NXsolenoid_magnet createNXsolenoid_magnet(long oid) {
		return new NXsolenoid_magnetImpl(oid);
	}

	/**
	 * Create a new NXsolenoid_magnet.
	 */
	public NXsolenoid_magnet createNXsolenoid_magnet() {
		return new NXsolenoid_magnetImpl(this);
	}

	/**
	 * Create a new NXelectrostatic_kicker with the given oid.
	 */
	public static NXelectrostatic_kicker createNXelectrostatic_kicker(long oid) {
		return new NXelectrostatic_kickerImpl(oid);
	}

	/**
	 * Create a new NXelectrostatic_kicker.
	 */
	public NXelectrostatic_kicker createNXelectrostatic_kicker() {
		return new NXelectrostatic_kickerImpl(this);
	}

	/**
	 * Create a new NXseparator with the given oid.
	 */
	public static NXseparator createNXseparator(long oid) {
		return new NXseparatorImpl(oid);
	}

	/**
	 * Create a new NXseparator.
	 */
	public NXseparator createNXseparator() {
		return new NXseparatorImpl(this);
	}

}
