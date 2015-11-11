package org.eclipse.dawnsci.nexus.model.api;

import java.util.Iterator;

import org.eclipse.dawnsci.nexus.NexusBaseClass;

public interface NexusMetadataProvider extends NexusTreeModification {

	public interface MetadataEntry {
		
		public String getName();
		
		public Object getValue();
		
	}
	
	public Iterator<MetadataEntry> getMetadataEntries();
	
	/**
	 * Returns the category of this base class. The 
	 * @return
	 */
	public NexusBaseClass getCategory();
	
}
