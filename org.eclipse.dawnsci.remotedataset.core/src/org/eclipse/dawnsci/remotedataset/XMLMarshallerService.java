/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */


package org.eclipse.dawnsci.remotedataset;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;

public class XMLMarshallerService implements IMarshallerService {

	@Override
	public String marshal(Object anyObject) throws Exception {
		return marshal(anyObject, true);
	}

	@Override
	public String marshal(Object anyObject, boolean unused) throws Exception {

		final ByteArrayOutputStream stream = new ByteArrayOutputStream();

		final ClassLoader original = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(anyObject.getClass().getClassLoader());
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream(stream));
			e.writeObject(anyObject);
			e.close();
			String xml = stream.toString("UTF-8");
			xml = xml.replace("\n", "");
			xml = xml.replace("\r", "");
			return xml;
		} finally {
			Thread.currentThread().setContextClassLoader(original);
		}
	}

	@Override
	public <U> U unmarshal(String string, Class<U> beanClass) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(string.getBytes("UTF-8")));
		return (U)decoder.readObject();
	}

	@Override
	public boolean isObjMixInSupported(Object obj) {
		return true;
	}

}
