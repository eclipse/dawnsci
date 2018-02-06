/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.test;

import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;

public class ServiceHolder {
	
	private static IMarshallerService marshaller;
	private static ILoaderService     lservice;
	public static IMarshallerService getMarshallerService() {
		return marshaller;
	}
	public void setMarshallerService(IMarshallerService marshaller) {
		ServiceHolder.marshaller = marshaller;
	}
	public static ILoaderService getLoaderservice() {
		return lservice;
	}
	public void setLoaderService(ILoaderService lservice) {
		ServiceHolder.lservice = lservice;
	}

}
