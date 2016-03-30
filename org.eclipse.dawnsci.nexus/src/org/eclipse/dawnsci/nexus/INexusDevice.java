package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.dataset.impl.LazyDataset;
import org.eclipse.dawnsci.nexus.builder.AbstractNexusProvider;
import org.eclipse.dawnsci.nexus.builder.DelegateNexusProvider;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;

/**
 * Any device which can write NeXus should implement this interface.
 * 
 * This can be done easily by extending {@link AbstractNexusProvider} or
 * {@link DelegateNexusProvider}
 * 
 * @author Matthew Gerring
 *
 */
public interface INexusDevice<N extends NXobject> {

	/**
	 * The object provider required for writing correct NeXus files.
	 * @return
	 * @throws Exception
	 */
	NexusObjectProvider<N> getNexusProvider(NexusScanInfo info) ;
	
	/**
	 * <p>
	 * In this method you should prepare the {@link LazyDataset}s the device
	 * will fill during the scan. You can also write out static device metadata,
	 * which will not change during the scan.
	 * </p>
	 * <p>
	 * Use the provided <code>nodeFactory</code> to create a NeXus object which
	 * matches the device e.g. a {@link NXdetector}.
	 * <code>final NXdetector detector = nodeFactory.createNXdetector();</code>
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
	 * <p>
	 * 
	 * @param nodeFactory
	 *            Factory you can use to create an appropriate NeXus node
	 * @param info
	 *            Information about the scan which can be useful when creating
	 *            dataset e.g. <code>info.getRank()</code>
	 * @return The {@link NXobject} created using the <code>nodeFactory</code>
	 *         to represent this device
	 * @throws NexusException if the nexus object could not be created for any reason
	 */
	N createNexusObject(NexusNodeFactory nodeFactory, NexusScanInfo info) throws NexusException;

}
