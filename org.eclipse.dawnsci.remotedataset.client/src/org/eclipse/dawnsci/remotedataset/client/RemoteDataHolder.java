package org.eclipse.dawnsci.remotedataset.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.IFileLoader;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.tree.TreeToMapUtils;
import org.eclipse.dawnsci.remotedataset.XMLMarshallerService;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.LazyDynamicDataset;
import org.eclipse.january.io.ILazyLoader;
import org.eclipse.january.metadata.IMetadata;

public class RemoteDataHolder implements IDataHolder {
	
	private String path;
	private String host;
	private int port;
	private URLBuilder urlBuilder;
	private Tree tree;
	
	
	
	public RemoteDataHolder(String path, String host, int port) {
		this.urlBuilder = new URLBuilder(host, port);
		try {
			this.tree = TreeToMapUtils.mapToTree(getTreeMap(),path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	private Map<String, Object> getTreeMap() throws Exception {
		
		urlBuilder.setGet(true);
		final URL url = new URL(urlBuilder.getTreeURL());
		URLConnection  conn = url.openConnection();

		final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		try {
			String xml = reader.readLine();
			IMarshallerService marshaller = new XMLMarshallerService();
			return (Map<String, Object>)marshaller.unmarshal(xml, Map.class);

		} finally {
			reader.close();
		}
	}
	
	@Override
	public IDataHolder clone() {
		return null;
	}

	@Override
	public IMetadata getMetadata() {
		return null;
	}

	@Override
	public void setMetadata(IMetadata md) {

	}

	@Override
	public IDataset getDataset(int index) {
		return null;
	}

	@Override
	public IDataset getDataset(String name) {
		return null;
	}

	@Override
	public ILazyDataset getLazyDataset(int index) {
		return null;
	}

	@Override
	public ILazyDataset getLazyDataset(String name) {
		URLBuilder b = new URLBuilder(urlBuilder);
		b.setDataset(name);
		RemoteLoader loader = new RemoteLoader(urlBuilder);
		try {
			RemoteDatasetInfo datasetInfo = loader.getDatasetInfo();
			LazyDynamicDataset lazy = new LazyDynamicDataset(name, datasetInfo.getDtype(),
					datasetInfo.getIsize(), datasetInfo.getShape(),
					datasetInfo.getMaxShape(), loader);
			
			return lazy;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean contains(String name) {
		return false;
	}

	@Override
	public String[] getNames() {
		return null;
	}

	@Override
	public String getName(int index) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public int namesSize() {
		return 0;
	}

	@Override
	public String getFilePath() {
		return path;
	}

	@Override
	public void setFilePath(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addDataset(String name, ILazyDataset dataset) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, ILazyDataset> toLazyMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLoaderClass(Class<? extends IFileLoader> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends IFileLoader> getLoaderClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ILazyDataset> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tree getTree() {
		// TODO Auto-generated method stub
		return tree;
	}

	@Override
	public void setTree(Tree tree) {
		throw new RuntimeException("Cannot overwrite tree in remote data holder!");

	}

}
