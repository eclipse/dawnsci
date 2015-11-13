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

package org.eclipse.dawnsci.nexus.builder;

import java.util.Iterator;

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NexusBaseClass;

/**
 * An interface defining a provider of metadata to a nexus entry ({@link NXentry} node),
 * or if {@link #getCategory()} does not return null, then to the object (i.e. group node)
 * within the tree of that type.
 * 
 * When added to a nexus entry using {@link NexusEntryBuilder#addMetadata(NexusMetadataProvider)}
 * a field is added for each {@link MetadataEntry} returned by the
 * {@link #getMetadataEntries()} iterator.
 */
public interface NexusMetadataProvider extends NexusEntryModification {

	/**
	 * A metadata entry, describing the field to add to the
	 * nexus entry or other node.
	 */
	public interface MetadataEntry {
		
		/**
		 * The name of the field to add.
		 * @return
		 */
		public String getName();
		
		/**
		 * The value of the field to add. This is converted to a dataset
		 * using {@link DatasetFactory#createFromObject(Object)}, and can
		 * therefore be a primitive, {@link String} or {@link Dataset}.
		 * @return
		 */
		public Object getValue();
		
	}
	
	/**
	 * Returns an iterator over the metadata entries.
	 * @return iterator over metadata entries
	 */
	public Iterator<MetadataEntry> getMetadataEntries();
	
	/**
	 * Returns the category of this metadata provider. If this method does not
	 * return <code>null</code>, then fields will be added to the
	 * nexus object (i.e. group node) in the tree with this type instead
	 * of the {@link NXentry} for each root node.
	 * @return category category, a member of the {@link NexusBaseClass}
	 *   enumeration of Nexus base class types.
	 */
	public NexusBaseClass getCategory();
	
}
