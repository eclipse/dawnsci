package org.eclipse.dawnsci.remotedataset.server.utils;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.january.IMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataServerUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DataServerUtils.class);

	public static IDataHolder getDataHolderWithLogging(String path) throws Exception {
		long startTime = System.currentTimeMillis();
		final IDataHolder holder = ServiceHolder.getLoaderService().getData(path, new IMonitor.Stub()); // TOOD Make it cancellable?
		
		long endTime = System.currentTimeMillis()-startTime;
		
		long minInfo = Long.getLong("org.eclipse.dawnsci.remotedataset.server.logging.mininfo", 100);
		long minWarn = Long.getLong("org.eclipse.dawnsci.remotedataset.server.logging.minwarn", 500);
		
		if (endTime > minInfo && endTime < minWarn) {
			logger.info("Read of data holder from {} took {} ms", path, endTime);
		} else if (endTime >= minWarn) {
			logger.warn("Read of data holder from {} took {} ms", path, endTime);
		}
		
		return holder;
	}
	
}
