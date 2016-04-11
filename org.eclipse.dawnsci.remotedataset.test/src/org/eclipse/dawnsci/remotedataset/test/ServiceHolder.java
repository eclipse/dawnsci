package org.eclipse.dawnsci.remotedataset.test;

import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;

public class ServiceHolder {
	
	private static IMarshallerService marshaller;
	private static ILoaderService     lservice;
	public static IMarshallerService getMarshallerService() {
		return marshaller;
	}
	public static void setMarshallerService(IMarshallerService marshaller) {
		ServiceHolder.marshaller = marshaller;
	}
	public static ILoaderService getLoaderservice() {
		return lservice;
	}
	public static void setLoaderService(ILoaderService lservice) {
		ServiceHolder.lservice = lservice;
	}

}
