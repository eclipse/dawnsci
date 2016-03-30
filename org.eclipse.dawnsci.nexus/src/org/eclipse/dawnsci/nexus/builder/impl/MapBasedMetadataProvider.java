/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.builder.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.builder.NexusMetadataProvider;

/**
 * A implementation of {@link NexusMetadataProvider} that uses an internal {@link Map}.
 * The methods {@link #addMetadataEntry(String, Object)} and {@link #setCategory(NexusBaseClass)}
 * return this object, so that they can be chained.
 */
public class MapBasedMetadataProvider implements NexusMetadataProvider {
	
	private final Map<String, Object> metadataMap = new HashMap<>();
	
	private NexusBaseClass category = null;
	
	/**
	 * Create a new {@link MapBasedMetadataProvider}
	 */
	public MapBasedMetadataProvider() {
		// do nothing
	}
	
	/**
	 * Create a new {@link MapBasedMetadataProvider}
	 */
	public MapBasedMetadataProvider(NexusBaseClass category) {
		this.category = category;
	}
	
	/**
	 * Create a new {@link MapBasedMetadataProvider}.
	 * @param map map
	 */
	public MapBasedMetadataProvider(Map<String, ?> map) {
		metadataMap.putAll(map);
	}
	
	/**
	 * Adds a metadata entry with the given name and value
	 * @param name name
	 * @param value value
	 * @return this object, for convenience
	 */
	public MapBasedMetadataProvider addMetadataEntry(String name, Object value) {
		metadataMap.put(name, value);
		return this;
	}
	
	/**
	 * Sets the category of this metadata provider. This metadata entries will
	 * be added as fields to the group of this type within the nexus tree.
	 * @param category category, an enumeration value from {@link NexusBaseClass}.
	 * @return this object, for convenience
	 */
	public MapBasedMetadataProvider setCategory(NexusBaseClass category) {
		this.category = category;
		return this;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusMetadataProvider#getCategory()
	 */
	@Override
	public NexusBaseClass getCategory() {
		return category;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusMetadataProvider#getMetadataEntries()
	 */
	@Override
	public Iterator<MetadataEntry> getMetadataEntries() {
		return new MapBasedMetadataEntryIterator();
	}
	
	/**
	 * Iterator over entries.
	 */
	private class MapBasedMetadataEntryIterator implements Iterator<NexusMetadataProvider.MetadataEntry> {
		
		private final Iterator<Map.Entry<String, Object>> mapEntryIterator;
		
		public MapBasedMetadataEntryIterator() {
			mapEntryIterator = metadataMap.entrySet().iterator();
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return mapEntryIterator.hasNext();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public MetadataEntry next() {
			return new MapEntryBasedMetadataEntry(mapEntryIterator.next());
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// remove() not supported.
			throw new UnsupportedOperationException("remove");
		}
		
	}
	
	/**
	 * A metadata entry simply wraps a {@link Map.Entry} from the map within
	 * the metadata provider.
	 */
	private static class MapEntryBasedMetadataEntry implements MetadataEntry {
		
		private final Map.Entry<String, Object> entry;
		
		private MapEntryBasedMetadataEntry(Map.Entry<String, Object> entry) {
			this.entry = entry;
		}

		@Override
		public String getName() {
			return entry.getKey();
		}

		@Override
		public Object getValue() {
			return entry.getValue();
		}
		
	}
	

}
