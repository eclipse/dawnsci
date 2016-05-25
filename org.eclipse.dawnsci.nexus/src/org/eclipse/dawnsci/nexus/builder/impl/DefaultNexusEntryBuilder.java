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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.nexus.NXcollection;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXuser;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.CustomNexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusMetadataProvider;
import org.eclipse.dawnsci.nexus.builder.NexusMetadataProvider.MetadataEntry;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder;
import org.eclipse.dawnsci.nexus.builder.appdef.impl.DefaultApplicationFactory;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.data.impl.DefaultNexusDataBuilder;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

/**
 * Default implementation of {@link NexusEntryBuilder}
 */
public class DefaultNexusEntryBuilder implements NexusEntryBuilder {

	private static final String APPDEF_SUBENTRY_SUFFIX = "_entry";

	private final NexusNodeFactory nexusNodeFactory;

	private final String entryName;
	
	private final NXentry nxEntry;

	private NXinstrument nxInstrument = null;

	private NXsample nxSample = null;

	private List<NXobject> defaultGroups = null;

	private final List<NexusApplicationBuilder> applications = new ArrayList<>();

	/**
	 * Creates a new {@link DefaultNexusEntryBuilder}. This constructor should only be called
	 * by {@link DefaultNexusFileBuilder}.
	 * @param nexusNodeFactory node factory
	 * @param entryName name of entry
	 * @param nxEntry entry to wrap
	 */
	protected DefaultNexusEntryBuilder(final NexusNodeFactory nexusNodeFactory,
			String entryName, final NXentry nxEntry) {
		this.nexusNodeFactory = nexusNodeFactory;
		this.nxEntry = nxEntry;
		this.entryName = entryName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#add(org.eclipse.dawnsci.nexus.builder.NexusObjectProvider)
	 */
	@Override
	public <N extends NXobject> N add(NexusObjectProvider<N> nexusObjectProvider) throws NexusException {
		final N nexusObject = nexusObjectProvider.createNexusObject(nexusNodeFactory);
		addGroupToNexusTree(nexusObjectProvider, nexusObject);

		return nexusObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#getNxEntry()
	 */
	@Override
	public NXentry getNXentry() {
		return nxEntry;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#getEntryName()
	 */
	@Override
	public String getEntryName() {
		return entryName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#getNodeFactory()
	 */
	@Override
	public NexusNodeFactory getNodeFactory() {
		return nexusNodeFactory;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#createDefaultData()
	 */
	@Override
	public NexusDataBuilder createDefaultData() {
		final NXdata nxData = nexusNodeFactory.createNXdata();
		nxEntry.setData(nxData);
		return new DefaultNexusDataBuilder(this, nxData);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#newData(java.lang.String)
	 */
	@Override
	public NexusDataBuilder newData(final String name) {
		final NXdata nxData = nexusNodeFactory.createNXdata();
		nxEntry.setData(name, nxData);
		return new DefaultNexusDataBuilder(this, nxData);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#newApplication(org.eclipse.dawnsci.nexus.NexusApplicationDefinition)
	 */
	@Override
	public NexusApplicationBuilder newApplication(NexusApplicationDefinition applicationDefinition) throws NexusException {
		final String appDefName = applicationDefinition.name();
		final String subentryName = appDefName.substring(appDefName.indexOf('_') + 1).toLowerCase() + APPDEF_SUBENTRY_SUFFIX;

		return newApplication(subentryName, applicationDefinition);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#newApplication(java.lang.String, org.eclipse.dawnsci.nexus.NexusApplicationDefinition)
	 */
	@Override
	public NexusApplicationBuilder newApplication(String subentryName,
			NexusApplicationDefinition applicationDefinition)
			throws NexusException {
		final NexusApplicationBuilder appBuilder = DefaultApplicationFactory.getApplicationDefinitionFactory().newApplicationDefinitionModel(
				this, applicationDefinition, subentryName);
		applications.add(appBuilder);

		return appBuilder;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#add(java.util.Collection)
	 */
	@Override
	public List<NXobject> addAll(Collection<? extends NexusObjectProvider<?>> nexusObjectProviders) throws NexusException {
		final List<NXobject> nexusObjects = new ArrayList<NXobject>(nexusObjectProviders.size());
		for (final NexusObjectProvider<?> nexusObjectProvider : nexusObjectProviders) {
			final NXobject nexusObject = add(nexusObjectProvider);
			nexusObjects.add(nexusObject);
		}

		return nexusObjects;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#modifyEntry(org.eclipse.dawnsci.nexus.builder.CustomNexusEntryModification)
	 */
	@Override
	public void modifyEntry(CustomNexusEntryModification modification) throws NexusException {
		modification.modifyEntry(nxEntry, nexusNodeFactory);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#addMetadata(org.eclipse.dawnsci.nexus.builder.NexusMetadataProvider)
	 */
	@Override
	public void addMetadata(NexusMetadataProvider metadataProvider) throws NexusException {
		final NexusBaseClass category = metadataProvider.getCategory();
		NXobject group;
		if (category == null) {
			group = nxEntry;
		} else {
			group = findGroupForCategory(category);
		}

		final Iterator<MetadataEntry> metadataEntryIterator = metadataProvider.getMetadataEntries();
		while (metadataEntryIterator.hasNext()) {
			final MetadataEntry entry = metadataEntryIterator.next();
			((NXobject) group).setField(entry.getName(), entry.getValue());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#modifyEntry(org.eclipse.dawnsci.nexus.builder.NexusEntryModification)
	 */
	@Override
	public void modifyEntry(NexusEntryModification modification) throws NexusException {
		if (modification instanceof NexusObjectProvider) {
			add((NexusObjectProvider<?>) modification);
		} else if (modification instanceof NexusMetadataProvider) {
			addMetadata((NexusMetadataProvider) modification);
		} else if (modification instanceof CustomNexusEntryModification) {
			modifyEntry((CustomNexusEntryModification) modification);
		} else {
			throw new IllegalArgumentException("Unknown modification type: " + modification.getClass());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#modifyEntry(java.util.Collection)
	 */
	@Override
	public void modifyEntry(
			Collection<NexusEntryModification> modifications) throws NexusException {
		for (final NexusEntryModification modification : modifications) {
			modifyEntry(modification);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#setInstrumentName(java.lang.String)
	 */
	@Override
	public void setInstrumentName(String instrumentName) {
		nxInstrument.setNameScalar(instrumentName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#getDataNode(java.lang.String)
	 */
	@Override
	public DataNode getDataNode(String relativePath) throws NexusException {
		final NodeLink nodeLink = nxEntry.findNodeLink(relativePath);
		if (nodeLink == null) {
			throw new NexusException("Cannot find expected data node within the entry with relative path: " + relativePath);
		}
		if (!nodeLink.isDestinationData()) {
			throw new NexusException("Node found was not a data node, relative path within the entry: " + relativePath);
		}

		return (DataNode) nodeLink.getDestination();
	}

	/**
	 * Adds the default groups for the entry. Subclasses may override as appropriate.
	 */
	@Override
	public void addDefaultGroups() {
		// TODO is this correct for all nexus trees we might want to create?
		// how do we configure it? (or just let subclasses override if they want?)
		defaultGroups = new ArrayList<>();
		defaultGroups.add(nxEntry);

		nxInstrument = nexusNodeFactory.createNXinstrument();
		defaultGroups.add(nxInstrument);
		nxEntry.setInstrument(nxInstrument);

		nxSample = nexusNodeFactory.createNXsample();
		defaultGroups.add(nxSample);
		nxEntry.setSample(nxSample);
		
		NXuser nxUser = nexusNodeFactory.createNXuser(); // TODO how to add multiple users?
		defaultGroups.add(nxUser);
		nxEntry.setUser(nxUser);
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder#validate()
	 */
	@Override
	public void validate() throws NexusValidationException {
		for (final NexusApplicationBuilder appDef : applications) {
			appDef.validate();
		}
	}

	/**
	 * Adds the new nexus object to the first skeleton class instance that it
	 * can be added to, unless category is specified, in which case it is added to the first
	 * element of that category that it can be added to.
	 * A special case is where the nexus object is of base class {@link NexusBaseClass#NX_SAMPLE},
	 * in which case this replaces the existing NXsample base class in the nexus tree.
	 * @param nexusObjectProvider
	 * @param nexusObject
	 * @throws NexusException
	 */
	protected <N extends NXobject> void addGroupToNexusTree(NexusObjectProvider<N> nexusObjectProvider, N nexusObject) throws NexusException {
		if (defaultGroups == null) {
			throw new IllegalStateException("There are no groups to add this element to. defaultGroups() must be invoked on this method before child groups can be added.");
		}

		if (nexusObject.getNexusBaseClass() == NexusBaseClass.NX_SAMPLE) {
			// special case for NXsample, replace the existing skeleton NXsample group
			nxSample = (NXsample) nexusObject;
			nxEntry.removeGroupNode("sample");
			nxEntry.setSample(nxSample);
			defaultGroups.remove(nxSample); // update the default groups list
			defaultGroups.add(nexusObject);
		} else {
			// normal case
			final String name = nexusObjectProvider.getName();
			final NexusBaseClass category = nexusObjectProvider.getCategory();

			// find the parent group
			NXobject parentGroup = null;
			if (category != null) {
				// if a category is specified, the parent group is the first group for this category
				parentGroup = findGroupForCategory(category);
			} else {
				// otherwise the parent group is the first group we can add this type of object to
				for (final NXobject group : defaultGroups) {
					if (group.canAddChild(nexusObject)) {
						parentGroup = group;
						break;
					}
				}
				if (parentGroup == null) {
					throw new NexusException("Cannot find a parent group that accepts a " + nexusObject.getNexusBaseClass());
				}
			}
			
			// if a collection name is specified, get the parent collection - creating it if necessary
			String collectionName = nexusObjectProvider.getCollectionName();
			if (collectionName != null) {
				parentGroup = getCollection(parentGroup, collectionName);
			}

			parentGroup.addGroupNode(name, nexusObject);
		}
	}
	
	private NXcollection getCollection(NXobject group, String collectionName) {
		NXcollection collection = null;
		
		GroupNode collectionGroup = group.getGroupNode(collectionName);
		if (collectionGroup == null) {
			collection = nexusNodeFactory.createNXcollection();
			group.addGroupNode(collectionName, collection);
		} else if (collectionGroup instanceof NXcollection) {
			collection = (NXcollection) collectionGroup;
		} else {
			throw new IllegalArgumentException("Cannot add collection " + collectionName +
					". A child group with that name already exists");
		}

		return collection;
	}

	private NXobject findGroupForCategory(NexusBaseClass category) throws NexusException {
		if (category == NexusBaseClass.NX_ENTRY) {
			return nxEntry;
		}

		for (final NXobject group : defaultGroups) {
			if (category == group.getNexusBaseClass()) {
				return group;
			}
		}

		throw new NexusException("No group found for category " + category);
	}

}
