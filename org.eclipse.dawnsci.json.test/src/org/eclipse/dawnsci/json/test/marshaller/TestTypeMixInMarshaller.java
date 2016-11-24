/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test.marshaller;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshaller;
import org.eclipse.dawnsci.json.test.testobject.TestTypeNonRegisteredImpl;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;

public class TestTypeMixInMarshaller implements IMarshaller {

	@Override
	public Class<?> getObjectClass() {
		return null;
	}

	@Override
	public Class<?> getSerializerClass() {
		return null;
	}

	@Override
	public Class<?> getDeserializerClass() {
		return null;
	}

	@Override
	public Class<TestTypeNonRegisteredImpl> getMixinAnnotationType() {
		return TestTypeNonRegisteredImpl.class;
	}

	@Override
	public Class<TestTypeRegisteredImpl> getMixinAnnotationClass() {
		return TestTypeRegisteredImpl.class;
	}

}
