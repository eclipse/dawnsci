package org.eclipse.dawnsci.remotedataset.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.IFileLoader;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.metadata.AxesMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.IDiffractionMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileFactoryHDF5;
import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NexusFile;

/**
 * This mocks the loader service, only implementing dataset loading
 * where the dataset path is known.
 * 
 * @author Matthew Gerring
 *
 */
public class LoaderServiceMock implements ILoaderService {

	private INexusFileFactory nexusFileFactoryHDF5;
	private String dataset;

	public LoaderServiceMock() {
		this(new NexusFileFactoryHDF5());
	}
	
	public LoaderServiceMock(INexusFileFactory nexusFileFactoryHDF5) {
		this(nexusFileFactoryHDF5, "/entry/data/image");
	}

	public LoaderServiceMock(INexusFileFactory nexusFileFactoryHDF52, String dataset) {
		this.nexusFileFactoryHDF5 = nexusFileFactoryHDF52;
		this.dataset              = dataset;
	}

	@Override
	public IDataHolder getData(String filePath, IMonitor monitor) throws Exception {
		return getData(filePath, true, monitor);
	}

	@Override
	public IDataHolder getData(String filePath, boolean lazily, IMonitor monitor) throws Exception {
		NexusFile file = nexusFileFactoryHDF5.newNexusFile(filePath);
		try {
			file.openToRead();
			final DataNode node = file.getData(dataset);
			ILazyDataset ds = node.getDataset();
			MockDataHolder dh = new MockDataHolder();
			dh.addDataset(dataset, ds);
			return dh;
		} finally {
			file.close();
		}
	}

	@Override
	public IDataset getDataset(String filePath, IMonitor monitor) throws Exception {
		throw new Exception("This method is not implemented by "+getClass().getSimpleName());
	}

	@Override
	public IDataset getDataset(String filePath, String datasetPath, IMonitor monitor) throws Exception {
		throw new Exception("This method is not implemented by "+getClass().getSimpleName());
	}

	@Override
	public IMetadata getMetadata(String filePath, IMonitor monitor) throws Exception {
		throw new Exception("This method is not implemented by "+getClass().getSimpleName());
	}

	private IDiffractionMetadata diffMetaData;
	@Override
	public IDiffractionMetadata getLockedDiffractionMetaData() {
		return diffMetaData;
	}

	@Override
	public IDiffractionMetadata setLockedDiffractionMetaData(IDiffractionMetadata diffMetaData) {
		IDiffractionMetadata old = this.diffMetaData;
		this.diffMetaData = diffMetaData;
		return old;
	}

	@Override
	public Collection<String> getSupportedExtensions() {
		// TODO Auto-generated method stub
		return Arrays.asList("h5", "nxs");
	}

	@Override
	public void clearSoftReferenceCache() {
		
	}

	@Override
	public void clearSoftReferenceCache(String filePath) {

	}

	@Override
	public Matcher getStackMatcher(String fileName) {
		return null;
	}

	@Override
	public AxesMetadata getAxesMetadata(ILazyDataset parent, String path, Map<Integer, String> axesNames, boolean lazy)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends IFileLoader> getLoaderClass(String extension) {
		return null;
	}

}
