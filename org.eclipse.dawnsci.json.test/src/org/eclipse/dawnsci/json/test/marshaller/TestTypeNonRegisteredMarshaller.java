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

package org.eclipse.dawnsci.json.test.marshaller;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshaller;
import org.eclipse.dawnsci.json.test.testobject.ITestTypeNonRegistered;

public class TestTypeNonRegisteredMarshaller implements IMarshaller {

	@Override
	public Class<ITestTypeNonRegistered> getObjectClass() {
		return ITestTypeNonRegistered.class;
	}

	@Override
	public Class<TestTypeNonRegisteredSerializer> getSerializerClass() {
		return TestTypeNonRegisteredSerializer.class;
	}

	@Override
	public Class<TestTypeNonRegisteredDeserializer> getDeserializerClass() {
		return TestTypeNonRegisteredDeserializer.class;
	}
}
