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
