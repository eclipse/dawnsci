package org.eclipse.dawnsci.nexus.impl;

/**
 * Factory class for creating instances of NeXus base classes.
 */
public class NXobjectFactory {
	
	private long nextOid = 1l;
	
	/**
	 * Create a new NXfresnel_zone_plate.
	 */
	public NXfresnel_zone_plateImpl createNXfresnel_zone_plate() {
		return new NXfresnel_zone_plateImpl(nextOid++);
	}

	/**
	 * Create a new NXxraylens.
	 */
	public NXxraylensImpl createNXxraylens() {
		return new NXxraylensImpl(nextOid++);
	}

	/**
	 * Create a new NXfermi_chopper.
	 */
	public NXfermi_chopperImpl createNXfermi_chopper() {
		return new NXfermi_chopperImpl(nextOid++);
	}

	/**
	 * Create a new NXmonochromator.
	 */
	public NXmonochromatorImpl createNXmonochromator() {
		return new NXmonochromatorImpl(nextOid++);
	}

	/**
	 * Create a new NXvelocity_selector.
	 */
	public NXvelocity_selectorImpl createNXvelocity_selector() {
		return new NXvelocity_selectorImpl(nextOid++);
	}

	/**
	 * Create a new NXnote.
	 */
	public NXnoteImpl createNXnote() {
		return new NXnoteImpl(nextOid++);
	}

	/**
	 * Create a new NXgrating.
	 */
	public NXgratingImpl createNXgrating() {
		return new NXgratingImpl(nextOid++);
	}

	/**
	 * Create a new NXbending_magnet.
	 */
	public NXbending_magnetImpl createNXbending_magnet() {
		return new NXbending_magnetImpl(nextOid++);
	}

	/**
	 * Create a new NXlog.
	 */
	public NXlogImpl createNXlog() {
		return new NXlogImpl(nextOid++);
	}

	/**
	 * Create a new NXdisk_chopper.
	 */
	public NXdisk_chopperImpl createNXdisk_chopper() {
		return new NXdisk_chopperImpl(nextOid++);
	}

	/**
	 * Create a new NXcharacterization.
	 */
	public NXcharacterizationImpl createNXcharacterization() {
		return new NXcharacterizationImpl(nextOid++);
	}

	/**
	 * Create a new NXshape.
	 */
	public NXshapeImpl createNXshape() {
		return new NXshapeImpl(nextOid++);
	}

	/**
	 * Create a new NXcapillary.
	 */
	public NXcapillaryImpl createNXcapillary() {
		return new NXcapillaryImpl(nextOid++);
	}

	/**
	 * Create a new NXguide.
	 */
	public NXguideImpl createNXguide() {
		return new NXguideImpl(nextOid++);
	}

	/**
	 * Create a new NXbeam.
	 */
	public NXbeamImpl createNXbeam() {
		return new NXbeamImpl(nextOid++);
	}

	/**
	 * Create a new NXsample.
	 */
	public NXsampleImpl createNXsample() {
		return new NXsampleImpl(nextOid++);
	}

	/**
	 * Create a new NXmirror.
	 */
	public NXmirrorImpl createNXmirror() {
		return new NXmirrorImpl(nextOid++);
	}

	/**
	 * Create a new NXcite.
	 */
	public NXciteImpl createNXcite() {
		return new NXciteImpl(nextOid++);
	}

	/**
	 * Create a new NXentry.
	 */
	public NXentryImpl createNXentry() {
		return new NXentryImpl(nextOid++);
	}

	/**
	 * Create a new NXflipper.
	 */
	public NXflipperImpl createNXflipper() {
		return new NXflipperImpl(nextOid++);
	}

	/**
	 * Create a new NXpositioner.
	 */
	public NXpositionerImpl createNXpositioner() {
		return new NXpositionerImpl(nextOid++);
	}

	/**
	 * Create a new NXcollimator.
	 */
	public NXcollimatorImpl createNXcollimator() {
		return new NXcollimatorImpl(nextOid++);
	}

	/**
	 * Create a new NXmoderator.
	 */
	public NXmoderatorImpl createNXmoderator() {
		return new NXmoderatorImpl(nextOid++);
	}

	/**
	 * Create a new NXgeometry.
	 */
	public NXgeometryImpl createNXgeometry() {
		return new NXgeometryImpl(nextOid++);
	}

	/**
	 * Create a new NXparameters.
	 */
	public NXparametersImpl createNXparameters() {
		return new NXparametersImpl(nextOid++);
	}

	/**
	 * Create a new NXorientation.
	 */
	public NXorientationImpl createNXorientation() {
		return new NXorientationImpl(nextOid++);
	}

	/**
	 * Create a new NXenvironment.
	 */
	public NXenvironmentImpl createNXenvironment() {
		return new NXenvironmentImpl(nextOid++);
	}

	/**
	 * Create a new NXmonitor.
	 */
	public NXmonitorImpl createNXmonitor() {
		return new NXmonitorImpl(nextOid++);
	}

	/**
	 * Create a new NXroot.
	 */
	public NXrootImpl createNXroot() {
		return new NXrootImpl(nextOid++);
	}

	/**
	 * Create a new NXprocess.
	 */
	public NXprocessImpl createNXprocess() {
		return new NXprocessImpl(nextOid++);
	}

	/**
	 * Create a new NXsource.
	 */
	public NXsourceImpl createNXsource() {
		return new NXsourceImpl(nextOid++);
	}

	/**
	 * Create a new NXuser.
	 */
	public NXuserImpl createNXuser() {
		return new NXuserImpl(nextOid++);
	}

	/**
	 * Create a new NXcrystal.
	 */
	public NXcrystalImpl createNXcrystal() {
		return new NXcrystalImpl(nextOid++);
	}

	/**
	 * Create a new NXtranslation.
	 */
	public NXtranslationImpl createNXtranslation() {
		return new NXtranslationImpl(nextOid++);
	}

	/**
	 * Create a new NXinstrument.
	 */
	public NXinstrumentImpl createNXinstrument() {
		return new NXinstrumentImpl(nextOid++);
	}

	/**
	 * Create a new NXpolarizer.
	 */
	public NXpolarizerImpl createNXpolarizer() {
		return new NXpolarizerImpl(nextOid++);
	}

	/**
	 * Create a new NXdata.
	 */
	public NXdataImpl createNXdata() {
		return new NXdataImpl(nextOid++);
	}

	/**
	 * Create a new NXpinhole.
	 */
	public NXpinholeImpl createNXpinhole() {
		return new NXpinholeImpl(nextOid++);
	}

	/**
	 * Create a new NXsubentry.
	 */
	public NXsubentryImpl createNXsubentry() {
		return new NXsubentryImpl(nextOid++);
	}

	/**
	 * Create a new NXaperture.
	 */
	public NXapertureImpl createNXaperture() {
		return new NXapertureImpl(nextOid++);
	}

	/**
	 * Create a new NXfilter.
	 */
	public NXfilterImpl createNXfilter() {
		return new NXfilterImpl(nextOid++);
	}

	/**
	 * Create a new NXevent_data.
	 */
	public NXevent_dataImpl createNXevent_data() {
		return new NXevent_dataImpl(nextOid++);
	}

	/**
	 * Create a new NXslit.
	 */
	public NXslitImpl createNXslit() {
		return new NXslitImpl(nextOid++);
	}

	/**
	 * Create a new NXdetector_group.
	 */
	public NXdetector_groupImpl createNXdetector_group() {
		return new NXdetector_groupImpl(nextOid++);
	}

	/**
	 * Create a new NXdetector_module.
	 */
	public NXdetector_moduleImpl createNXdetector_module() {
		return new NXdetector_moduleImpl(nextOid++);
	}

	/**
	 * Create a new NXinsertion_device.
	 */
	public NXinsertion_deviceImpl createNXinsertion_device() {
		return new NXinsertion_deviceImpl(nextOid++);
	}

	/**
	 * Create a new NXbeam_stop.
	 */
	public NXbeam_stopImpl createNXbeam_stop() {
		return new NXbeam_stopImpl(nextOid++);
	}

	/**
	 * Create a new NXtransformations.
	 */
	public NXtransformationsImpl createNXtransformations() {
		return new NXtransformationsImpl(nextOid++);
	}

	/**
	 * Create a new NXsensor.
	 */
	public NXsensorImpl createNXsensor() {
		return new NXsensorImpl(nextOid++);
	}

	/**
	 * Create a new NXattenuator.
	 */
	public NXattenuatorImpl createNXattenuator() {
		return new NXattenuatorImpl(nextOid++);
	}

	/**
	 * Create a new NXdetector.
	 */
	public NXdetectorImpl createNXdetector() {
		return new NXdetectorImpl(nextOid++);
	}

	/**
	 * Create a new NXcollection.
	 */
	public NXcollectionImpl createNXcollection() {
		return new NXcollectionImpl(nextOid++);
	}

	/**
	 * Create a new NXmagnetic_kicker.
	 */
	public NXmagnetic_kickerImpl createNXmagnetic_kicker() {
		return new NXmagnetic_kickerImpl(nextOid++);
	}

	/**
	 * Create a new NXquadrupole_magnet.
	 */
	public NXquadrupole_magnetImpl createNXquadrupole_magnet() {
		return new NXquadrupole_magnetImpl(nextOid++);
	}

	/**
	 * Create a new NXspin_rotator.
	 */
	public NXspin_rotatorImpl createNXspin_rotator() {
		return new NXspin_rotatorImpl(nextOid++);
	}

	/**
	 * Create a new NXsolenoid_magnet.
	 */
	public NXsolenoid_magnetImpl createNXsolenoid_magnet() {
		return new NXsolenoid_magnetImpl(nextOid++);
	}

	/**
	 * Create a new NXelectrostatic_kicker.
	 */
	public NXelectrostatic_kickerImpl createNXelectrostatic_kicker() {
		return new NXelectrostatic_kickerImpl(nextOid++);
	}

	/**
	 * Create a new NXseparator.
	 */
	public NXseparatorImpl createNXseparator() {
		return new NXseparatorImpl(nextOid++);
	}

}
