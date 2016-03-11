package org.eclipse.dawnsci.remotedataset.test;

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

// TODO Something that mocks the loader service.
public class LoaderServiceMock implements ILoaderService {

	@Override
	public IDataHolder getData(String filePath, IMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataHolder getData(String filePath, boolean lazily, IMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataset getDataset(String filePath, IMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataset getDataset(String filePath, String datasetPath, IMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMetadata getMetadata(String filePath, IMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDiffractionMetadata getLockedDiffractionMetaData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDiffractionMetadata setLockedDiffractionMetaData(IDiffractionMetadata diffMetaData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getSupportedExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearSoftReferenceCache() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSoftReferenceCache(String filePath) {
		// TODO Auto-generated method stub

	}

	@Override
	public Matcher getStackMatcher(String fileName) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
