/*-
 * Copyright © 2016 Diamond Light Source Ltd.
 *
 * This file is part of GDA.
 *
 * GDA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License version 3 as published by the Free
 * Software Foundation.
 *
 * GDA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with GDA. If not, see <http://www.gnu.org/licenses/>.
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
