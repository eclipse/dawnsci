package org.eclipse.dawnsci.remotedataset.server.test;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.junit.Test;

public class DataEventTest {

	@Test
	public void testSerializationNoPath() throws Exception {
		
		DataEvent evt = new DataEvent("Tests", new int[]{1024, 1024});
		evt.setFilePath(null);
		
		String json = evt.encode();
		System.out.println(json);
		DataEvent evt2 = DataEvent.decode(json);
		
		if (!evt2.equals(evt)) throw new Exception("Data events were not equal!");
	}

	
	@Test
	public void testSerialization() throws Exception {
		
		DataEvent evt = new DataEvent("Tests", new int[]{1024, 1024});
		evt.setFilePath("C:/tmp/Fred.txt");
		
		String json = evt.encode();
		System.out.println(json);
		DataEvent evt2 = DataEvent.decode(json);
		
		if (!evt2.equals(evt)) throw new Exception("Data events were not equal!");
	}
}
