package org.eclipse.dawnsci.nexus.impl;

import java.net.URI;

import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeImpl;

/**
 * Factory class for creating instances of NeXus base classes.
 */
public class NXobjectFactory {
	
	private long nextOid = 1l;

	public static NXobjectImpl createNXobjectForClass(String baseClassName, long oid) {
		final NXbaseClass baseClass = NXbaseClass.getBaseClassForName(baseClassName);
		return createNXobjectForClass(baseClass, oid);
	}
	
	public static NXobjectImpl createNXobjectForClass(NXbaseClass baseClass, long oid) {
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
		throw new IllegalArgumentException("Unknown base class: " + baseClass);	}
/**
	 * Create a new tree with given URI
	 * @param uri
	 */
	public TreeImpl createTree(final URI uri) {
		return new TreeImpl(nextOid++, uri);
	}
	
	/**
	 * Create a new tree file with given URI
	 * @param uri uri
	 */
	public TreeFileImpl createTreeFile(final URI uri) {
		return new TreeFileImpl(nextOid++, uri);
	}
	
	/**
	 * Create a new tree file with given file name
	 * @param filename filename
	 * @return
	 */
	public TreeFileImpl createTreeFile(final String fileName) {
		return new TreeFileImpl(nextOid++, fileName);
	}
	
	/**
	 * Create a new NXfresnel_zone_plate with the given oid.
	 */
	public static NXfresnel_zone_plateImpl createNXfresnel_zone_plate(long oid) {
		return new NXfresnel_zone_plateImpl(oid);
	}

	/**
	 * Create a new NXfresnel_zone_plate.
	 */
	public NXfresnel_zone_plateImpl createNXfresnel_zone_plate() {
		return new NXfresnel_zone_plateImpl(nextOid++);
	}

	/**
	 * Create a new NXxraylens with the given oid.
	 */
	public static NXxraylensImpl createNXxraylens(long oid) {
		return new NXxraylensImpl(oid);
	}

	/**
	 * Create a new NXxraylens.
	 */
	public NXxraylensImpl createNXxraylens() {
		return new NXxraylensImpl(nextOid++);
	}

	/**
	 * Create a new NXfermi_chopper with the given oid.
	 */
	public static NXfermi_chopperImpl createNXfermi_chopper(long oid) {
		return new NXfermi_chopperImpl(oid);
	}

	/**
	 * Create a new NXfermi_chopper.
	 */
	public NXfermi_chopperImpl createNXfermi_chopper() {
		return new NXfermi_chopperImpl(nextOid++);
	}

	/**
	 * Create a new NXmonochromator with the given oid.
	 */
	public static NXmonochromatorImpl createNXmonochromator(long oid) {
		return new NXmonochromatorImpl(oid);
	}

	/**
	 * Create a new NXmonochromator.
	 */
	public NXmonochromatorImpl createNXmonochromator() {
		return new NXmonochromatorImpl(nextOid++);
	}

	/**
	 * Create a new NXvelocity_selector with the given oid.
	 */
	public static NXvelocity_selectorImpl createNXvelocity_selector(long oid) {
		return new NXvelocity_selectorImpl(oid);
	}

	/**
	 * Create a new NXvelocity_selector.
	 */
	public NXvelocity_selectorImpl createNXvelocity_selector() {
		return new NXvelocity_selectorImpl(nextOid++);
	}

	/**
	 * Create a new NXnote with the given oid.
	 */
	public static NXnoteImpl createNXnote(long oid) {
		return new NXnoteImpl(oid);
	}

	/**
	 * Create a new NXnote.
	 */
	public NXnoteImpl createNXnote() {
		return new NXnoteImpl(nextOid++);
	}

	/**
	 * Create a new NXgrating with the given oid.
	 */
	public static NXgratingImpl createNXgrating(long oid) {
		return new NXgratingImpl(oid);
	}

	/**
	 * Create a new NXgrating.
	 */
	public NXgratingImpl createNXgrating() {
		return new NXgratingImpl(nextOid++);
	}

	/**
	 * Create a new NXbending_magnet with the given oid.
	 */
	public static NXbending_magnetImpl createNXbending_magnet(long oid) {
		return new NXbending_magnetImpl(oid);
	}

	/**
	 * Create a new NXbending_magnet.
	 */
	public NXbending_magnetImpl createNXbending_magnet() {
		return new NXbending_magnetImpl(nextOid++);
	}

	/**
	 * Create a new NXlog with the given oid.
	 */
	public static NXlogImpl createNXlog(long oid) {
		return new NXlogImpl(oid);
	}

	/**
	 * Create a new NXlog.
	 */
	public NXlogImpl createNXlog() {
		return new NXlogImpl(nextOid++);
	}

	/**
	 * Create a new NXdisk_chopper with the given oid.
	 */
	public static NXdisk_chopperImpl createNXdisk_chopper(long oid) {
		return new NXdisk_chopperImpl(oid);
	}

	/**
	 * Create a new NXdisk_chopper.
	 */
	public NXdisk_chopperImpl createNXdisk_chopper() {
		return new NXdisk_chopperImpl(nextOid++);
	}

	/**
	 * Create a new NXcharacterization with the given oid.
	 */
	public static NXcharacterizationImpl createNXcharacterization(long oid) {
		return new NXcharacterizationImpl(oid);
	}

	/**
	 * Create a new NXcharacterization.
	 */
	public NXcharacterizationImpl createNXcharacterization() {
		return new NXcharacterizationImpl(nextOid++);
	}

	/**
	 * Create a new NXshape with the given oid.
	 */
	public static NXshapeImpl createNXshape(long oid) {
		return new NXshapeImpl(oid);
	}

	/**
	 * Create a new NXshape.
	 */
	public NXshapeImpl createNXshape() {
		return new NXshapeImpl(nextOid++);
	}

	/**
	 * Create a new NXcapillary with the given oid.
	 */
	public static NXcapillaryImpl createNXcapillary(long oid) {
		return new NXcapillaryImpl(oid);
	}

	/**
	 * Create a new NXcapillary.
	 */
	public NXcapillaryImpl createNXcapillary() {
		return new NXcapillaryImpl(nextOid++);
	}

	/**
	 * Create a new NXguide with the given oid.
	 */
	public static NXguideImpl createNXguide(long oid) {
		return new NXguideImpl(oid);
	}

	/**
	 * Create a new NXguide.
	 */
	public NXguideImpl createNXguide() {
		return new NXguideImpl(nextOid++);
	}

	/**
	 * Create a new NXbeam with the given oid.
	 */
	public static NXbeamImpl createNXbeam(long oid) {
		return new NXbeamImpl(oid);
	}

	/**
	 * Create a new NXbeam.
	 */
	public NXbeamImpl createNXbeam() {
		return new NXbeamImpl(nextOid++);
	}

	/**
	 * Create a new NXsample with the given oid.
	 */
	public static NXsampleImpl createNXsample(long oid) {
		return new NXsampleImpl(oid);
	}

	/**
	 * Create a new NXsample.
	 */
	public NXsampleImpl createNXsample() {
		return new NXsampleImpl(nextOid++);
	}

	/**
	 * Create a new NXmirror with the given oid.
	 */
	public static NXmirrorImpl createNXmirror(long oid) {
		return new NXmirrorImpl(oid);
	}

	/**
	 * Create a new NXmirror.
	 */
	public NXmirrorImpl createNXmirror() {
		return new NXmirrorImpl(nextOid++);
	}

	/**
	 * Create a new NXcite with the given oid.
	 */
	public static NXciteImpl createNXcite(long oid) {
		return new NXciteImpl(oid);
	}

	/**
	 * Create a new NXcite.
	 */
	public NXciteImpl createNXcite() {
		return new NXciteImpl(nextOid++);
	}

	/**
	 * Create a new NXentry with the given oid.
	 */
	public static NXentryImpl createNXentry(long oid) {
		return new NXentryImpl(oid);
	}

	/**
	 * Create a new NXentry.
	 */
	public NXentryImpl createNXentry() {
		return new NXentryImpl(nextOid++);
	}

	/**
	 * Create a new NXflipper with the given oid.
	 */
	public static NXflipperImpl createNXflipper(long oid) {
		return new NXflipperImpl(oid);
	}

	/**
	 * Create a new NXflipper.
	 */
	public NXflipperImpl createNXflipper() {
		return new NXflipperImpl(nextOid++);
	}

	/**
	 * Create a new NXpositioner with the given oid.
	 */
	public static NXpositionerImpl createNXpositioner(long oid) {
		return new NXpositionerImpl(oid);
	}

	/**
	 * Create a new NXpositioner.
	 */
	public NXpositionerImpl createNXpositioner() {
		return new NXpositionerImpl(nextOid++);
	}

	/**
	 * Create a new NXcollimator with the given oid.
	 */
	public static NXcollimatorImpl createNXcollimator(long oid) {
		return new NXcollimatorImpl(oid);
	}

	/**
	 * Create a new NXcollimator.
	 */
	public NXcollimatorImpl createNXcollimator() {
		return new NXcollimatorImpl(nextOid++);
	}

	/**
	 * Create a new NXmoderator with the given oid.
	 */
	public static NXmoderatorImpl createNXmoderator(long oid) {
		return new NXmoderatorImpl(oid);
	}

	/**
	 * Create a new NXmoderator.
	 */
	public NXmoderatorImpl createNXmoderator() {
		return new NXmoderatorImpl(nextOid++);
	}

	/**
	 * Create a new NXgeometry with the given oid.
	 */
	public static NXgeometryImpl createNXgeometry(long oid) {
		return new NXgeometryImpl(oid);
	}

	/**
	 * Create a new NXgeometry.
	 */
	public NXgeometryImpl createNXgeometry() {
		return new NXgeometryImpl(nextOid++);
	}

	/**
	 * Create a new NXparameters with the given oid.
	 */
	public static NXparametersImpl createNXparameters(long oid) {
		return new NXparametersImpl(oid);
	}

	/**
	 * Create a new NXparameters.
	 */
	public NXparametersImpl createNXparameters() {
		return new NXparametersImpl(nextOid++);
	}

	/**
	 * Create a new NXorientation with the given oid.
	 */
	public static NXorientationImpl createNXorientation(long oid) {
		return new NXorientationImpl(oid);
	}

	/**
	 * Create a new NXorientation.
	 */
	public NXorientationImpl createNXorientation() {
		return new NXorientationImpl(nextOid++);
	}

	/**
	 * Create a new NXenvironment with the given oid.
	 */
	public static NXenvironmentImpl createNXenvironment(long oid) {
		return new NXenvironmentImpl(oid);
	}

	/**
	 * Create a new NXenvironment.
	 */
	public NXenvironmentImpl createNXenvironment() {
		return new NXenvironmentImpl(nextOid++);
	}

	/**
	 * Create a new NXmonitor with the given oid.
	 */
	public static NXmonitorImpl createNXmonitor(long oid) {
		return new NXmonitorImpl(oid);
	}

	/**
	 * Create a new NXmonitor.
	 */
	public NXmonitorImpl createNXmonitor() {
		return new NXmonitorImpl(nextOid++);
	}

	/**
	 * Create a new NXroot with the given oid.
	 */
	public static NXrootImpl createNXroot(long oid) {
		return new NXrootImpl(oid);
	}

	/**
	 * Create a new NXroot.
	 */
	public NXrootImpl createNXroot() {
		return new NXrootImpl(nextOid++);
	}

	/**
	 * Create a new NXprocess with the given oid.
	 */
	public static NXprocessImpl createNXprocess(long oid) {
		return new NXprocessImpl(oid);
	}

	/**
	 * Create a new NXprocess.
	 */
	public NXprocessImpl createNXprocess() {
		return new NXprocessImpl(nextOid++);
	}

	/**
	 * Create a new NXsource with the given oid.
	 */
	public static NXsourceImpl createNXsource(long oid) {
		return new NXsourceImpl(oid);
	}

	/**
	 * Create a new NXsource.
	 */
	public NXsourceImpl createNXsource() {
		return new NXsourceImpl(nextOid++);
	}

	/**
	 * Create a new NXuser with the given oid.
	 */
	public static NXuserImpl createNXuser(long oid) {
		return new NXuserImpl(oid);
	}

	/**
	 * Create a new NXuser.
	 */
	public NXuserImpl createNXuser() {
		return new NXuserImpl(nextOid++);
	}

	/**
	 * Create a new NXcrystal with the given oid.
	 */
	public static NXcrystalImpl createNXcrystal(long oid) {
		return new NXcrystalImpl(oid);
	}

	/**
	 * Create a new NXcrystal.
	 */
	public NXcrystalImpl createNXcrystal() {
		return new NXcrystalImpl(nextOid++);
	}

	/**
	 * Create a new NXtranslation with the given oid.
	 */
	public static NXtranslationImpl createNXtranslation(long oid) {
		return new NXtranslationImpl(oid);
	}

	/**
	 * Create a new NXtranslation.
	 */
	public NXtranslationImpl createNXtranslation() {
		return new NXtranslationImpl(nextOid++);
	}

	/**
	 * Create a new NXinstrument with the given oid.
	 */
	public static NXinstrumentImpl createNXinstrument(long oid) {
		return new NXinstrumentImpl(oid);
	}

	/**
	 * Create a new NXinstrument.
	 */
	public NXinstrumentImpl createNXinstrument() {
		return new NXinstrumentImpl(nextOid++);
	}

	/**
	 * Create a new NXpolarizer with the given oid.
	 */
	public static NXpolarizerImpl createNXpolarizer(long oid) {
		return new NXpolarizerImpl(oid);
	}

	/**
	 * Create a new NXpolarizer.
	 */
	public NXpolarizerImpl createNXpolarizer() {
		return new NXpolarizerImpl(nextOid++);
	}

	/**
	 * Create a new NXdata with the given oid.
	 */
	public static NXdataImpl createNXdata(long oid) {
		return new NXdataImpl(oid);
	}

	/**
	 * Create a new NXdata.
	 */
	public NXdataImpl createNXdata() {
		return new NXdataImpl(nextOid++);
	}

	/**
	 * Create a new NXpinhole with the given oid.
	 */
	public static NXpinholeImpl createNXpinhole(long oid) {
		return new NXpinholeImpl(oid);
	}

	/**
	 * Create a new NXpinhole.
	 */
	public NXpinholeImpl createNXpinhole() {
		return new NXpinholeImpl(nextOid++);
	}

	/**
	 * Create a new NXsubentry with the given oid.
	 */
	public static NXsubentryImpl createNXsubentry(long oid) {
		return new NXsubentryImpl(oid);
	}

	/**
	 * Create a new NXsubentry.
	 */
	public NXsubentryImpl createNXsubentry() {
		return new NXsubentryImpl(nextOid++);
	}

	/**
	 * Create a new NXaperture with the given oid.
	 */
	public static NXapertureImpl createNXaperture(long oid) {
		return new NXapertureImpl(oid);
	}

	/**
	 * Create a new NXaperture.
	 */
	public NXapertureImpl createNXaperture() {
		return new NXapertureImpl(nextOid++);
	}

	/**
	 * Create a new NXfilter with the given oid.
	 */
	public static NXfilterImpl createNXfilter(long oid) {
		return new NXfilterImpl(oid);
	}

	/**
	 * Create a new NXfilter.
	 */
	public NXfilterImpl createNXfilter() {
		return new NXfilterImpl(nextOid++);
	}

	/**
	 * Create a new NXevent_data with the given oid.
	 */
	public static NXevent_dataImpl createNXevent_data(long oid) {
		return new NXevent_dataImpl(oid);
	}

	/**
	 * Create a new NXevent_data.
	 */
	public NXevent_dataImpl createNXevent_data() {
		return new NXevent_dataImpl(nextOid++);
	}

	/**
	 * Create a new NXslit with the given oid.
	 */
	public static NXslitImpl createNXslit(long oid) {
		return new NXslitImpl(oid);
	}

	/**
	 * Create a new NXslit.
	 */
	public NXslitImpl createNXslit() {
		return new NXslitImpl(nextOid++);
	}

	/**
	 * Create a new NXdetector_group with the given oid.
	 */
	public static NXdetector_groupImpl createNXdetector_group(long oid) {
		return new NXdetector_groupImpl(oid);
	}

	/**
	 * Create a new NXdetector_group.
	 */
	public NXdetector_groupImpl createNXdetector_group() {
		return new NXdetector_groupImpl(nextOid++);
	}

	/**
	 * Create a new NXdetector_module with the given oid.
	 */
	public static NXdetector_moduleImpl createNXdetector_module(long oid) {
		return new NXdetector_moduleImpl(oid);
	}

	/**
	 * Create a new NXdetector_module.
	 */
	public NXdetector_moduleImpl createNXdetector_module() {
		return new NXdetector_moduleImpl(nextOid++);
	}

	/**
	 * Create a new NXinsertion_device with the given oid.
	 */
	public static NXinsertion_deviceImpl createNXinsertion_device(long oid) {
		return new NXinsertion_deviceImpl(oid);
	}

	/**
	 * Create a new NXinsertion_device.
	 */
	public NXinsertion_deviceImpl createNXinsertion_device() {
		return new NXinsertion_deviceImpl(nextOid++);
	}

	/**
	 * Create a new NXbeam_stop with the given oid.
	 */
	public static NXbeam_stopImpl createNXbeam_stop(long oid) {
		return new NXbeam_stopImpl(oid);
	}

	/**
	 * Create a new NXbeam_stop.
	 */
	public NXbeam_stopImpl createNXbeam_stop() {
		return new NXbeam_stopImpl(nextOid++);
	}

	/**
	 * Create a new NXtransformations with the given oid.
	 */
	public static NXtransformationsImpl createNXtransformations(long oid) {
		return new NXtransformationsImpl(oid);
	}

	/**
	 * Create a new NXtransformations.
	 */
	public NXtransformationsImpl createNXtransformations() {
		return new NXtransformationsImpl(nextOid++);
	}

	/**
	 * Create a new NXsensor with the given oid.
	 */
	public static NXsensorImpl createNXsensor(long oid) {
		return new NXsensorImpl(oid);
	}

	/**
	 * Create a new NXsensor.
	 */
	public NXsensorImpl createNXsensor() {
		return new NXsensorImpl(nextOid++);
	}

	/**
	 * Create a new NXattenuator with the given oid.
	 */
	public static NXattenuatorImpl createNXattenuator(long oid) {
		return new NXattenuatorImpl(oid);
	}

	/**
	 * Create a new NXattenuator.
	 */
	public NXattenuatorImpl createNXattenuator() {
		return new NXattenuatorImpl(nextOid++);
	}

	/**
	 * Create a new NXdetector with the given oid.
	 */
	public static NXdetectorImpl createNXdetector(long oid) {
		return new NXdetectorImpl(oid);
	}

	/**
	 * Create a new NXdetector.
	 */
	public NXdetectorImpl createNXdetector() {
		return new NXdetectorImpl(nextOid++);
	}

	/**
	 * Create a new NXcollection with the given oid.
	 */
	public static NXcollectionImpl createNXcollection(long oid) {
		return new NXcollectionImpl(oid);
	}

	/**
	 * Create a new NXcollection.
	 */
	public NXcollectionImpl createNXcollection() {
		return new NXcollectionImpl(nextOid++);
	}

	/**
	 * Create a new NXmagnetic_kicker with the given oid.
	 */
	public static NXmagnetic_kickerImpl createNXmagnetic_kicker(long oid) {
		return new NXmagnetic_kickerImpl(oid);
	}

	/**
	 * Create a new NXmagnetic_kicker.
	 */
	public NXmagnetic_kickerImpl createNXmagnetic_kicker() {
		return new NXmagnetic_kickerImpl(nextOid++);
	}

	/**
	 * Create a new NXquadrupole_magnet with the given oid.
	 */
	public static NXquadrupole_magnetImpl createNXquadrupole_magnet(long oid) {
		return new NXquadrupole_magnetImpl(oid);
	}

	/**
	 * Create a new NXquadrupole_magnet.
	 */
	public NXquadrupole_magnetImpl createNXquadrupole_magnet() {
		return new NXquadrupole_magnetImpl(nextOid++);
	}

	/**
	 * Create a new NXspin_rotator with the given oid.
	 */
	public static NXspin_rotatorImpl createNXspin_rotator(long oid) {
		return new NXspin_rotatorImpl(oid);
	}

	/**
	 * Create a new NXspin_rotator.
	 */
	public NXspin_rotatorImpl createNXspin_rotator() {
		return new NXspin_rotatorImpl(nextOid++);
	}

	/**
	 * Create a new NXsolenoid_magnet with the given oid.
	 */
	public static NXsolenoid_magnetImpl createNXsolenoid_magnet(long oid) {
		return new NXsolenoid_magnetImpl(oid);
	}

	/**
	 * Create a new NXsolenoid_magnet.
	 */
	public NXsolenoid_magnetImpl createNXsolenoid_magnet() {
		return new NXsolenoid_magnetImpl(nextOid++);
	}

	/**
	 * Create a new NXelectrostatic_kicker with the given oid.
	 */
	public static NXelectrostatic_kickerImpl createNXelectrostatic_kicker(long oid) {
		return new NXelectrostatic_kickerImpl(oid);
	}

	/**
	 * Create a new NXelectrostatic_kicker.
	 */
	public NXelectrostatic_kickerImpl createNXelectrostatic_kicker() {
		return new NXelectrostatic_kickerImpl(nextOid++);
	}

	/**
	 * Create a new NXseparator with the given oid.
	 */
	public static NXseparatorImpl createNXseparator(long oid) {
		return new NXseparatorImpl(oid);
	}

	/**
	 * Create a new NXseparator.
	 */
	public NXseparatorImpl createNXseparator() {
		return new NXseparatorImpl(nextOid++);
	}

}
