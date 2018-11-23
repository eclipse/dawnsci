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
package org.eclipse.dawnsci.remotedataset.test.server;

import org.eclipse.january.dataset.DataEvent;
import org.junit.Test;

public class DataEventTest {

	@Test
	public void testSerializationNoPath() throws Exception {
		
		DataEvent evt = new DataEvent("Tests", new int[]{64,64});
		evt.setFilePath(null);
		
		String json = evt.encode();
		System.out.println(json);
		DataEvent evt2 = DataEvent.decode(json);
		
		if (!evt2.equals(evt)) throw new Exception("Data events were not equal!");
	}

	
	@Test
	public void testSerialization() throws Exception {
		
		DataEvent evt = new DataEvent("Tests", new int[]{64, 64});
		evt.setFilePath("C:/tmp/Fred.txt");
		
		String json = evt.encode();
		System.out.println(json);
		DataEvent evt2 = DataEvent.decode(json);
		
		if (!evt2.equals(evt)) throw new Exception("Data events were not equal!");
	}
}
