package org.eclipse.dawnsci.nexus.impl;
	
/**
 * Eumeration of NeXus base classes.
 */
public enum NXbaseClass {

	NX_FRESNEL_ZONE_PLATE("NXfresnel_zone_plate"),
	NX_XRAYLENS("NXxraylens"),
	NX_FERMI_CHOPPER("NXfermi_chopper"),
	NX_MONOCHROMATOR("NXmonochromator"),
	NX_VELOCITY_SELECTOR("NXvelocity_selector"),
	NX_NOTE("NXnote"),
	NX_GRATING("NXgrating"),
	NX_BENDING_MAGNET("NXbending_magnet"),
	NX_LOG("NXlog"),
	NX_DISK_CHOPPER("NXdisk_chopper"),
	NX_CHARACTERIZATION("NXcharacterization"),
	NX_SHAPE("NXshape"),
	NX_CAPILLARY("NXcapillary"),
	NX_GUIDE("NXguide"),
	NX_BEAM("NXbeam"),
	NX_SAMPLE("NXsample"),
	NX_MIRROR("NXmirror"),
	NX_CITE("NXcite"),
	NX_ENTRY("NXentry"),
	NX_FLIPPER("NXflipper"),
	NX_POSITIONER("NXpositioner"),
	NX_COLLIMATOR("NXcollimator"),
	NX_MODERATOR("NXmoderator"),
	NX_GEOMETRY("NXgeometry"),
	NX_PARAMETERS("NXparameters"),
	NX_ORIENTATION("NXorientation"),
	NX_ENVIRONMENT("NXenvironment"),
	NX_MONITOR("NXmonitor"),
	NX_ROOT("NXroot"),
	NX_PROCESS("NXprocess"),
	NX_SOURCE("NXsource"),
	NX_USER("NXuser"),
	NX_CRYSTAL("NXcrystal"),
	NX_TRANSLATION("NXtranslation"),
	NX_INSTRUMENT("NXinstrument"),
	NX_POLARIZER("NXpolarizer"),
	NX_DATA("NXdata"),
	NX_PINHOLE("NXpinhole"),
	NX_SUBENTRY("NXsubentry"),
	NX_APERTURE("NXaperture"),
	NX_FILTER("NXfilter"),
	NX_EVENT_DATA("NXevent_data"),
	NX_SLIT("NXslit"),
	NX_DETECTOR_GROUP("NXdetector_group"),
	NX_DETECTOR_MODULE("NXdetector_module"),
	NX_INSERTION_DEVICE("NXinsertion_device"),
	NX_BEAM_STOP("NXbeam_stop"),
	NX_TRANSFORMATIONS("NXtransformations"),
	NX_SENSOR("NXsensor"),
	NX_ATTENUATOR("NXattenuator"),
	NX_DETECTOR("NXdetector"),
	NX_COLLECTION("NXcollection"),
	NX_MAGNETIC_KICKER("NXmagnetic_kicker"),
	NX_QUADRUPOLE_MAGNET("NXquadrupole_magnet"),
	NX_SPIN_ROTATOR("NXspin_rotator"),
	NX_SOLENOID_MAGNET("NXsolenoid_magnet"),
	NX_ELECTROSTATIC_KICKER("NXelectrostatic_kicker"),
	NX_SEPARATOR("NXseparator");

	private String name;

	private NXbaseClass(final String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the nexus base class constant for the given name string.
	 */
	public static NXbaseClass getBaseClassForName(final String name) {
		// Note: this method will not work correctly if any base classes include
		// capital letters in their name (excluding the initial 'NX')
		final String enumName = name.substring(0, 2) + '_' + name.substring(2).toUpperCase();
		return NXbaseClass.valueOf(enumName);
	}

}
