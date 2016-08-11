package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.CustomNexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.january.dataset.LazyDataset;

/**
 * Any device which can write NeXus should implement this interface.
 * 
 * This can be done easily by extending {@link AbstractNexusObjectProvider}
 * 
 * @param <N> the type of nexus object to be created, a sub-interface of {@link NXobject},
 *   e.g. {@link NXdetector}
 *  
 * @author Matthew Gerring, Matthew Dickie
 *
 */
public interface INexusDevice<N extends NXobject> {

	/**
	 * Returns the object provider required for writing correct NeXus files.
	 * 
	 * <p>
	 * In this method you should prepare the {@link LazyDataset}s the device
	 * will fill during the scan. You can also write out static device metadata,
	 * which will not change during the scan.
	 * </p>
	 * <p>
	 * Use the methods on {@link NexusNodeFactory} to create a NeXus object which
	 * matches the device e.g. a {@link NXdetector}.
	 * <code>final NXdetector detector = NexusNodeFactory.createNXdetector();</code>
	 * </p>
	 * <p>
	 * On the detector object you can create {@link LazyDataset}s and keep
	 * references which you can later use during the scan to write data. e.g.
	 * <code>imageData = detector.initializeLazyDataset(NXdetector.NX_DATA, info.getRank() + 2, Dataset.FLOAT64);</code>
	 * You should also set chunking on the {@link LazyDataset}s you create e.g. <code>imageData.setChunking(info.createChunk(detectorXSize, detectorYSize));</code>.
	 * </p>
	 * In this method you can also write static metadata such as the detector
	 * exposure e.g.
	 * <code>detector.setField("exposure_time", model.getExposure());</code>. Or
	 * static datasets such as the image axis data
	 * <code>detector.setDataset("image_x_axis", DatasetFactory.createLinearSpace(minX, maxX, xPoints, Dataset.FLOAT64));</code>
	 * For fields that are defined in the NXDL base class definition for the
	 * returned nexus object, a setXXX or setXXXScalar method may be used as
	 * appropriate, e.g. <code>detector.setLocalName(DatasetFactory.createFromObject("my detector"));</code>
	 * or <code>detector.setLocalNameScalar("my detector");</code>
	 * <p>
	 * If this device is a 'metadata scannable', then the device should write
	 * its data at this point directly into the returned nexus object. This can be
	 * done with the {@link NXobject#setField(String, Object)} method, or the
	 * <code>setXXXScalar</code> methods for fields defined in the appropriate 
	 * NXDL base class definition.
	 * 
	 * @param info
	 *            Information about the scan which can be useful when creating
	 *            dataset e.g. <code>info.getRank()</code>
	 * @return The {@link NXobject} created using the <code>nodeFactory</code>
	 *         to represent this device
	 * @throws NexusException if the nexus object could not be created for any reason
	 */
	NexusObjectProvider<N> getNexusProvider(NexusScanInfo info) throws NexusException;
	
	/**
	 * Returns an object that performs a custom modification to
	 * an {@link NXentry}.
	 * <p>
	 * <em>NOTE: Use this method with caution as it can be used to break the central
	 * design concept of the new Nexus writing framework, namely that the nexus framework itself
	 * knows where to put the nexus groups for devices and build any required {@link NXdata} groups.
	 * </em> It is currently used by the new Nexus framework to partially support legacy GDA8 spring
	 * configurations, in particular the 'locationmap'.
	 * <p>
	 * The nexus framework will call this method after {@link #createNexusObject(NexusNodeFactory, NexusScanInfo)},
	 * so this method create links to nodes created in that method if appropriate.
	 * <p>
	 * The easiest way to implement this method is to make this object itself also implement
	 * {@link CustomNexusEntryModification}. This method can then be overridden to simply
	 * return <code>this</code>.
	 * 
	 * @return a {@link CustomNexusEntryModification} that makes a custom modification,
	 *    or <code>null</code> if this device should not make custom modifications
	 */
	default CustomNexusEntryModification getCustomNexusModification() {
		return null;
	}
	
}
