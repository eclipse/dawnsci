/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({

	JsonMarshallerInbuiltTypesTest.class,
	JsonMarshallerCustomClassesTest.class,
	JsonMarshallerROITypesTest.class,
	JsonMarshallerSerializerTest.class,
	JsonMarshallerClassRegistryTest.class,

})
public class Suite {
}
