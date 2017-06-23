/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;
	
/**
 * Eumeration of NeXus base classes.
 */
public enum NexusBaseClass {

	NX_DETECTOR_MODULE("NXdetector_module", NXdetector_module.class),
	NX_SAMPLE("NXsample", NXsample.class),
	NX_SAMPLE_COMPONENT("NXsample_component", NXsample_component.class),
	NX_BEAM("NXbeam", NXbeam.class),
	NX_CRYSTAL("NXcrystal", NXcrystal.class),
	NX_TRANSFORMATIONS("NXtransformations", NXtransformations.class),
	NX_SHAPE("NXshape", NXshape.class),
	NX_FRESNEL_ZONE_PLATE("NXfresnel_zone_plate", NXfresnel_zone_plate.class),
	NX_GUIDE("NXguide", NXguide.class),
	NX_APERTURE("NXaperture", NXaperture.class),
	NX_MODERATOR("NXmoderator", NXmoderator.class),
	NX_MONITOR("NXmonitor", NXmonitor.class),
	NX_INSTRUMENT("NXinstrument", NXinstrument.class),
	NX_ENTRY("NXentry", NXentry.class),
	NX_EVENT_DATA("NXevent_data", NXevent_data.class),
	NX_PINHOLE("NXpinhole", NXpinhole.class),
	NX_MONOCHROMATOR("NXmonochromator", NXmonochromator.class),
	NX_SUBENTRY("NXsubentry", NXsubentry.class),
	NX_SLIT("NXslit", NXslit.class),
	NX_CAPILLARY("NXcapillary", NXcapillary.class),
	NX_XRAYLENS("NXxraylens", NXxraylens.class),
	NX_FILTER("NXfilter", NXfilter.class),
	NX_PARAMETERS("NXparameters", NXparameters.class),
	NX_COLLECTION("NXcollection", NXcollection.class),
	NX_DATA("NXdata", NXdata.class),
	NX_INSERTION_DEVICE("NXinsertion_device", NXinsertion_device.class),
	NX_DISK_CHOPPER("NXdisk_chopper", NXdisk_chopper.class),
	NX_TRANSLATION("NXtranslation", NXtranslation.class),
	NX_VELOCITY_SELECTOR("NXvelocity_selector", NXvelocity_selector.class),
	NX_USER("NXuser", NXuser.class),
	NX_LOG("NXlog", NXlog.class),
	NX_DETECTOR_GROUP("NXdetector_group", NXdetector_group.class),
	NX_GEOMETRY("NXgeometry", NXgeometry.class),
	NX_PROCESS("NXprocess", NXprocess.class),
	NX_ROOT("NXroot", NXroot.class),
	NX_SOURCE("NXsource", NXsource.class),
	NX_POSITIONER("NXpositioner", NXpositioner.class),
	NX_ATTENUATOR("NXattenuator", NXattenuator.class),
	NX_POLARIZER("NXpolarizer", NXpolarizer.class),
	NX_NOTE("NXnote", NXnote.class),
	NX_MIRROR("NXmirror", NXmirror.class),
	NX_SENSOR("NXsensor", NXsensor.class),
	NX_CITE("NXcite", NXcite.class),
	NX_DETECTOR("NXdetector", NXdetector.class),
	NX_BEAM_STOP("NXbeam_stop", NXbeam_stop.class),
	NX_ORIENTATION("NXorientation", NXorientation.class),
	NX_GRATING("NXgrating", NXgrating.class),
	NX_ENVIRONMENT("NXenvironment", NXenvironment.class),
	NX_FLIPPER("NXflipper", NXflipper.class),
	NX_COLLIMATOR("NXcollimator", NXcollimator.class),
	NX_BENDING_MAGNET("NXbending_magnet", NXbending_magnet.class),
	NX_FERMI_CHOPPER("NXfermi_chopper", NXfermi_chopper.class),
	NX_REFLECTIONS("NXreflections", NXreflections.class),
	NX_MAGNETIC_KICKER("NXmagnetic_kicker", NXmagnetic_kicker.class),
	NX_SEPARATOR("NXseparator", NXseparator.class),
	NX_ELECTROSTATIC_KICKER("NXelectrostatic_kicker", NXelectrostatic_kicker.class),
	NX_SOLENOID_MAGNET("NXsolenoid_magnet", NXsolenoid_magnet.class),
	NX_QUADRUPOLE_MAGNET("NXquadrupole_magnet", NXquadrupole_magnet.class),
	NX_CONTAINER("NXcontainer", NXcontainer.class),
	NX_SPIN_ROTATOR("NXspin_rotator", NXspin_rotator.class);

	private String name;
	
	private Class<? extends NXobject> javaClass;
	
	private NexusBaseClass(final String name, final Class<? extends NXobject> javaClass) {
		this.name = name;
		this.javaClass = javaClass;
	}
	
	public Class<? extends NXobject> getJavaClass() {
		return javaClass;
	} 
	
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the nexus base class constant for the given name string.
	 */
	public static NexusBaseClass getBaseClassForName(final String name) {
		// Note: this method will not work correctly if any base classes include
		// capital letters in their name (excluding the initial 'NX')
		final String enumName = name.substring(0, 2) + '_' + name.substring(2).toUpperCase();
		return NexusBaseClass.valueOf(enumName);
	}
	

}
