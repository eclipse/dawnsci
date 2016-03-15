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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.impl.PrimaryDataFieldModel;

/**
 * Abstract implementation of {@link NexusObjectProvider}.
 * 
 * <br>Use this class to help implementing NexusObjectProvider, for example:
   <pre>
   <code>

	class Detector implements IRunnableDevice, INexusDevice {
  
		private NexusObjectProvider prov;
      
		public Detector() {
			prov = new AbstractNexusProvider<NXdetector>(NexusBaseClass.NX_DETECTOR) {
			   
				protected NXdetector doCreateNexusObject(NexusNodeFactory nodeFactory) {
					final NXdetectorImpl detector = nodeFactory.createNXdetector();
					final int rank = 4;
					detector.initializeLazyDataset(NXdetectorImpl.NX_DATA, rank, Dataset.FLOAT64);
					return detector;
				}	
			};
		}
		
		public NexusObjectProvider<NXdetector> getNexusProvider() {
			return prov;
		}
		
		public void write(...) {
			// Use prov to help write the required datasets.
		}
	}
	</code>
    </pre>
 *
 * @param <N> nexus base class type, a subinterface of {@link NXobject}
 */
public abstract class AbstractNexusProvider<N extends NXobject> implements NexusObjectProvider<N> {

	public static final String DEFAULT_DATA_NODE_NAME = "data";

	private final NexusBaseClass nexusBaseClass;

	private N nexusObject = null;

	private String name;

	private final LinkedHashSet<String> dataFieldNames;
	
	private final List<String> additionalPrimaryDataFieldNames;
	
	private Map<String, PrimaryDataFieldModel> primaryDataFieldModels;
	
	private String externalFileName = null;

	private Map<String, Integer> externalDatasetRanks = null;
	
	private String demandDataFieldName = null;
	
	private String defaultWritableDataFieldName = null;
	
	private NexusBaseClass category;

	public AbstractNexusProvider(NexusBaseClass nexusBaseClass) {
		this(getDefaultName(nexusBaseClass), nexusBaseClass);
	}

	/**
	 * Creates a new {@link AbstractNexusProvider} for given name and base class type,
	 * with "data" as the name of the default data field - used as the default
	 * data field (i.e. the signal field) when this device is added to an NXdata.
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public AbstractNexusProvider(String name, NexusBaseClass nexusBaseClass) {
		this(name, nexusBaseClass, null, DEFAULT_DATA_NODE_NAME);
	}

	/**
	 * Creates a new {@link AbstractNexusProvider} for given name, base class type
	 * and data node name.
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public AbstractNexusProvider(String name, NexusBaseClass nexusBaseClass,
			String defaultDataFieldName) {
		this(name, nexusBaseClass, null, defaultDataFieldName);
	}

	/**
	 * Creates a new {@link AbstractNexusProvider} for given name, base class type,
	 * data node name and category.
	 * @param name name
	 * @param nexusBaseClass base class type
	 * @param category
	 * @param defaultDataFieldName name of the default data field
	 */
	public AbstractNexusProvider(String name, NexusBaseClass nexusBaseClass,
			NexusBaseClass category, String defaultDataFieldName, String... remainingDataFieldNames) {
		this.name = name;
		this.nexusBaseClass = nexusBaseClass;
		this.category = category;
		
		this.defaultWritableDataFieldName = defaultDataFieldName;
		this.dataFieldNames = new LinkedHashSet<>(remainingDataFieldNames.length + 1);
		if (defaultDataFieldName != null) {
			this.dataFieldNames.add(defaultDataFieldName);
		}
		if (remainingDataFieldNames.length > 0) {
			this.dataFieldNames.addAll(Arrays.asList(remainingDataFieldNames));
		}
		this.additionalPrimaryDataFieldNames = new ArrayList<>();
	}

	public static String getDefaultName(NexusBaseClass nexusBaseClass) {
		// the default name is the base class name without the initial 'NX',
		// e.g. for 'NXpositioner' the default name is 'positioner'
		return nexusBaseClass.toString().substring(2);
	}

	/**
	 * Creates the nexus object for this {@link NexusObjectProvider} using the
	 * given {@link NexusNodeFactory}.
	 * @param nodeFactory node factory
	 * @return new nexus object
	 */
	protected abstract N doCreateNexusObject(NexusNodeFactory nodeFactory);

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#createNexusObject(org.eclipse.dawnsci.nexus.impl.NexusNodeFactory)
	 */
	@Override
	public final N createNexusObject(NexusNodeFactory nodeFactory) {
		if (nexusObject != null) {
			throw new IllegalStateException("The nexus object for this provider already exists");
		}

		this.nexusObject = doCreateNexusObject(nodeFactory);
		return nexusObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getNexusObject(org.eclipse.dawnsci.nexus.impl.NexusNodeFactory, boolean)
	 */
	@Override
	public N getNexusObject(NexusNodeFactory nodeFactory,
			boolean createIfNecessary) {
		if (nexusObject == null && createIfNecessary) {
			this.nexusObject = doCreateNexusObject(nodeFactory);
		}

		return nexusObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getNexusObject()
	 */
	@Override
	public final N getNexusObject() {
		return nexusObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getNexusBaseClass()
	 */
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return nexusBaseClass;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getExternalFileName()
	 */
	@Override
	public String getExternalFileName() {
		return externalFileName;
	}
	
	/**
	 * Set the name of the external file that this device writes its data to.
	 * @param externalFileName external file name
	 */
	public void setExternalFileName(String externalFileName) {
		this.externalFileName = externalFileName;
	}

	/**
	 * A convenience method to add an external link to the given
	 * group node with the given name, while also setting the rank of the
	 * external dataset within this {@link AbstractNexusProvider}.
	 * This is required to be set when adding a {@link NexusObjectProvider}
	 * with external links to a {@link NexusDataBuilder} in order for the
	 * <code>axes</code> and <code>&lt;axisname&gt;_indices</code> to be
	 * created.
	 * <p>
	 * An external file must have been set by calling
	 * {@link #setExternalDatasetRank(String, int)} prior to calling this method.
	 *  
	 * @param groupNode group node to add external link to
	 * @param fieldName name of external dataset within the group
	 * @param pathToNode path of node to link to within the external file
	 * @param rank the rank of the 
	 */
	public void addExternalLink(NXobject groupNode, String fieldName,
			String pathToNode, int rank) {
		if (externalFileName == null) {
			throw new IllegalStateException("External file name not set.");
		}
		
		groupNode.addExternalLink(fieldName, externalFileName, pathToNode);
		setExternalDatasetRank(fieldName, rank);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getExternalDatasetRank(java.lang.String)
	 */
	@Override
	public int getExternalDatasetRank(String fieldName) {
		if (externalDatasetRanks == null || !externalDatasetRanks.containsKey(fieldName)) {
			throw new IllegalArgumentException("No rank set for external dataset: " + fieldName);
		}
		
		return externalDatasetRanks.get(fieldName);
	}

	/**
	 * Set the rank of an external dataset within the nexus object returned by
	 * {@link #getNexusObject()}. The method {@link #setExternalFileName(String)} must
	 * have been invoked before calling this method.
	 * @param fieldName the name of the external dataset within the nexus object
	 * @param rank the rank of the external dataset
	 */
	public void setExternalDatasetRank(String fieldName, int rank) {
//		if (externalFileName == null) { 
//			throw new IllegalStateException("External file name must be set before adding external datasets.");
//		}
		
		if (externalDatasetRanks == null) {
			externalDatasetRanks = new HashMap<>();
		}
		
		externalDatasetRanks.put(fieldName, rank);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getDefaultDataFieldName()
	 */
	@Override
	public String getPrimaryDataField() {
		if (defaultWritableDataFieldName != null) {
			return defaultWritableDataFieldName;
		}
		if (dataFieldNames != null && !dataFieldNames.isEmpty()) {
			return dataFieldNames.iterator().next();
		}
		
		return null;
	}
	
	public void setPrimaryDataField(String defaultWritableDataFieldName) {
		addDataFields(defaultWritableDataFieldName); // add as a data field if not already present
		this.defaultWritableDataFieldName = defaultWritableDataFieldName;
	}
	
	@Override
	public String getDemandDataField() {
		return demandDataFieldName;
	}
	
	public void setDemandDataField(String demandDataFieldName) {
		this.demandDataFieldName = demandDataFieldName;
		if (!dataFieldNames.contains(demandDataFieldName)) {
			dataFieldNames.add(demandDataFieldName);
		}
	}

	@Override
	public List<String> getDataFields() {
		return new ArrayList<String>(dataFieldNames);
	}

	public void setDataFields(String... dataFieldNames) {
		this.dataFieldNames.clear();
		this.dataFieldNames.addAll(Arrays.asList(dataFieldNames));  
	}
	
	public void addDataFields(String... dataFieldName) {
		this.dataFieldNames.addAll(Arrays.asList(dataFieldName));
	}
	
	public void addDataField(String dataFieldName, Integer defaultAxisDimension, int... dimensionMappings) {
		if (defaultWritableDataFieldName == null) {
			throw new IllegalStateException("Default writable data field not set.");
		}
		
		addDataField(dataFieldName, defaultWritableDataFieldName, defaultAxisDimension, dimensionMappings);
	}
	
	private PrimaryDataFieldModel getPrimaryDataFieldModel(String primaryDataFieldName, boolean create) {
		if (primaryDataFieldModels == null) {
			if (!create) return null;
			primaryDataFieldModels = new HashMap<>();
		}
			
		PrimaryDataFieldModel primaryDataFieldModel = primaryDataFieldModels.get(primaryDataFieldName);
		if (primaryDataFieldModel == null) {
			if (!create) return null;
			primaryDataFieldModel = new PrimaryDataFieldModel();
			primaryDataFieldModels.put(primaryDataFieldName, primaryDataFieldModel);
		}
		
		return primaryDataFieldModel;
	}
	
	public void addDataField(String dataFieldName, String primaryDataFieldName,
			Integer defaultAxisDimension, int... dimensionMappings) {
		dataFieldNames.add(dataFieldName);
		
		// if defaultAxisDimension is set and no dimension mappings are specified
		// assume this is a 1 dimensional dataset with mapping the defaultAxisDimension
		if (dimensionMappings.length == 0 && defaultAxisDimension != null) {
			// TODO can we remove this and make this assumption at a later stage?
			dimensionMappings = new int[] { defaultAxisDimension };
		} 
		
		PrimaryDataFieldModel primaryDataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, true);
		primaryDataFieldModel.addDataField(dataFieldName, defaultAxisDimension, dimensionMappings);
	}
	
	public void addAdditionalPrimaryDataField(String dataFieldName) {
		// TODO: is this the best name for this concept? it's a bit confusing
		// alternatives: addPrimaryDataField, addSecondarySignalField etc
		
		addDataFields(dataFieldName); // add as a data field if not already added
		additionalPrimaryDataFieldNames.add(dataFieldName);
	}
	
	public void addAdditionalPrimaryDataFields(String... dataFieldNames) {
		additionalPrimaryDataFieldNames.addAll(Arrays.asList(dataFieldNames));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getAdditionalPrimaryDataFieldNames()
	 */
	@Override
	public List<String> getAdditionalPrimaryDataFields() {
		return additionalPrimaryDataFieldNames;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getCategory()
	 */
	@Override
	public NexusBaseClass getCategory() {
		return category;
	}

	public void setCategory(NexusBaseClass category) {
		this.category = category;
	}
	
	@Override
	public Integer getDefaultAxisDimension(String primaryDataFieldName, String dataFieldName) {
		PrimaryDataFieldModel dataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, false);
		if (dataFieldModel != null) {
			return dataFieldModel.getDefaultAxisDimension(dataFieldName);
		}
		return null;
	}
	
	@Override
	public int[] getDimensionMappings(String primaryDataFieldName, String dataFieldName) {
		PrimaryDataFieldModel dataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, false);
		if (dataFieldModel != null) {
			return dataFieldModel.getDimensionMappings(dataFieldName);
		}
		return null;
	}

	public ILazyWriteableDataset getDefaultWriteableDataset() {
		final String defaultDataFieldName = getPrimaryDataField();
		return getNexusObject().getLazyWritableDataset(defaultDataFieldName);
	}

	public ILazyWriteableDataset getWriteableDataset(String fieldName) {
		return getNexusObject().getLazyWritableDataset(fieldName);
	}

}
