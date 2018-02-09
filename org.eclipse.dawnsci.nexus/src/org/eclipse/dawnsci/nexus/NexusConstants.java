/*-
 * Copyright 2018 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus;

/**
 * Constants used to deal with NeXus files
 */
public class NexusConstants {
	/**
	 * Group attribute to indicate NeXus class
	 */
	public static final String NXCLASS = "NX_class";

	/**
	 * Dataset attribute name for units
	 */
	public static final String UNITS = "units";

	/**
	 * base classes
	 */
	public static final String APERTURE = "NXaperture";
	public static final String ATTENUATOR = "NXattenuator";
	public static final String BEAM = "NXbeam";
	public static final String BEAMSTOP = "NXbeam_stop";
	public static final String BENDINGMAGNET = "NXbending_magnet";
	public static final String CAPILLARY = "NXcapillary";
	public static final String CITE = "NXcite";
	public static final String COLLECTION = "NXcollection";
	public static final String COLLIMATOR = "NXcollimator";
	public static final String CRYSTAL = "NXcrystal";
	public static final String DATA = "NXdata";
	public static final String DETECTORGROUP = "NXdetector_group";
	public static final String DETECTORMODULE = "NXdetector_module";
	public static final String DETECTOR = "NXdetector";
	public static final String DISKCHOPPER = "NXdisk_chopper";
	public static final String ENTRY = "NXentry";
	public static final String ENVIRONMENT = "NXenvironment";
	public static final String EVENTDATA = "NXevent_data";
	public static final String FERMICHOPPER = "NXfermi_chopper";
	public static final String FILTER = "NXfilter";
	public static final String FLIPPER = "NXflipper";
	public static final String FRESNELZONEPLATE = "NXfresnel_zone_plate";

	/**
	 * @deprecated superseded by {@link #TRANSFORMATIONS}
	 */
	@Deprecated
	public static final String GEOMETRY = "NXgeometry";
	public static final String GRATING = "NXgrating";
	public static final String GUIDE = "NXguide";
	public static final String INSERTIONDEVICE = "NXinsertion_device";
	public static final String INSTRUMENT = "NXinstrument";
	public static final String LOG = "NXlog";
	public static final String MIRROR = "NXmirror";
	public static final String MODERATOR = "NXmoderator";
	public static final String MONITOR = "NXmonitor";
	public static final String MONOCHROMATOR = "NXmonochromator";
	public static final String NOTE = "NXnote";
	public static final String OBJECT = "NXobject";
	public static final String ORIENTATION = "NXorientation";
	public static final String PARAMETERS = "NXparameters";
	public static final String PINHOLE = "NXpinhole";
	public static final String POLARIZER = "NXpolarizer";
	public static final String POSITIONER = "NXpositioner";
	public static final String PROCESS = "NXprocess";
	public static final String REFLECTIONS = "NXreflections";
	public static final String ROOT = "NXroot";
	public static final String SAMPLECOMPONENT = "NXsample_component";
	public static final String SAMPLE = "NXsample";
	public static final String SENSOR = "NXsensor";
	public static final String SHAPE = "NXshape";
	public static final String SLIT = "NXslit";
	public static final String SOURCE = "NXsource";
	public static final String SUBENTRY = "NXsubentry";
	public static final String TRANSFORMATIONS = "NXtransformations";
	public static final String TRANSLATION = "NXtranslation";
	public static final String USER = "NXuser";
	public static final String VELOCITYSELECTOR = "NXvelocity_selector";
	public static final String XRAYLENS = "NXxraylens";

	/**
	 * NXdata default data name
	 */
	public static final String DATA_DATA = "data";
	/**
	 * NXdata dataset attribute
	 */
	public static final String DATA_NAME = "long_name";
	/**
	 * NXdata attribute that points to main dataset
	 */
	public static final String DATA_SIGNAL = "signal";
	/**
	 * NXdata attribute that maps dimensions of main dataset to axis datasets
	 */
	public static final String DATA_AXES = "axes";
	/**
	 * NXdata attribute that points to error datasets (may also be legacy if attached to main dataset)
	 */
	public static final String DATA_UNCERTAINTIES ="uncertainties";

	/**
	 * NXdata indicate no default for axis in axes attribute
	 */
	public static final String DATA_AXESEMPTY = ".";

	/**
	 * Suffix for name of error datasets
	 */
	public static final String DATA_ERRORS_SUFFIX = "_errors";
	/**
	 * Suffix for name of attributes that indicate dimension mappings from axis dataset to main dataset
	 */
	public static final String DATA_INDICES_SUFFIX = "_indices";
	/**
	 * Suffix for names of datasets
	 */
	public static final String DATA_UNCERTAINTY_SUFFIX ="_uncertainty";
	/**
	 * Suffix for names of datasets that contain set values (for axes)
	 */
	public static final String DATA_AXESSET_SUFFIX = "_set";

	/**
	 * @deprecated legacy attribute name of main dataset
	 */
	@Deprecated
	public static final String DATA_ERRORS = "errors";
	/**
	 * @deprecated legacy attribute name
	 */
	@Deprecated
	public static final String DATA_AXIS = "axis";
	/**
	 * @deprecated legacy attribute name
	 */
	@Deprecated
	public static final String DATA_PRIMARY = "primary";
}
