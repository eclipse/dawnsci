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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.data.impl.PrimaryDataFieldModel;
import org.eclipse.january.dataset.ILazyWriteableDataset;

/**
 * Abstract implementation of {@link NexusObjectProvider}.
 * 
 * <br>Use this class to help implementing NexusObjectProvider, for example:
   <pre>
   <code>

	class Detector implements IRunnableDevice, INexusDevice {
  
		private NexusObjectProvider prov;
      
		public Detector() {
			prov = new AbstractNexusObjectProvider<NXdetector>(NexusBaseClass.NX_DETECTOR) {
			   
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
public abstract class AbstractNexusObjectProvider<N extends NXobject> implements NexusObjectProvider<N> {

	public static final String DEFAULT_DATA_NODE_NAME = "data";

	private final NexusBaseClass nexusBaseClass;

	private N nexusObject = null;

	private String name;

	private String primaryDataFieldName = null;
	
	private final LinkedHashSet<String> axisDataFieldNames;
	
	private final List<String> additionalPrimaryDataFieldNames;
	
	private Map<String, PrimaryDataFieldModel> primaryDataFieldModels = null;
	
	private String externalFileName = null;

	private Map<String, Integer> externalDatasetRanks = null;
	
	private String defaultAxisDataFieldName = null;
	
	private String collectionName = null;
	
	private NexusBaseClass category = null;
	
	private Boolean useDeviceNameInNXdata = null;

	/**
	 * Creates a new {@link AbstractNexusObjectProvider} for given name, base class type
	 * and data node name.
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public AbstractNexusObjectProvider(String name, NexusBaseClass nexusBaseClass) {
		this(name, nexusBaseClass, null);
	}

	/**
	 * Creates a new {@link AbstractNexusObjectProvider} for given name, base class type
	 * and data node name. The default data field will be used as the <code>@signal</code>
	 * for an {@link NexusBaseClass#NX_DETECTOR} when building an {@link NXdata} for this object,
	 * otherwise if this device is the default axis for a particular dimension of the
	 * <code>@signal</code> field of the device, this is the field that will be added to the
	 * <code>@axes</code> attribute of the {@link NXdata} group for that dimension.
	 *  
	 * @param name name
	 * @param nexusBaseClass base class type
	 * @param defaultDataFieldName default data field, the default signal field for a detector,
	 *    the default axis field for any other type of nexus object
	 * @param additionalDataFieldNames the names of any additional data fields
	 */
	public AbstractNexusObjectProvider(String name, NexusBaseClass nexusBaseClass,
			String defaultDataFieldName, String... additionalDataFieldNames) {
		this.name = name;
		this.nexusBaseClass = nexusBaseClass;
		this.axisDataFieldNames = new LinkedHashSet<>(additionalDataFieldNames.length);
		
		if (nexusBaseClass == NexusBaseClass.NX_DETECTOR) {
			setPrimaryDataFieldName(defaultDataFieldName);
		} else {
			setDefaultAxisDataFieldName(defaultDataFieldName);
		}
		
		if (additionalDataFieldNames.length > 0) {
			this.axisDataFieldNames.addAll(Arrays.asList(additionalDataFieldNames));
		}
		// field names should be prefixed by the device name for axis devices (e.g. positioners)
		setUseDeviceNameInNXdata(nexusBaseClass != NexusBaseClass.NX_DETECTOR);
		
		this.additionalPrimaryDataFieldNames = new ArrayList<>();
	}

	public static String getDefaultName(NexusBaseClass nexusBaseClass) {
		// the default name is the base class name without the initial 'NX' prefix,
		// e.g. for 'NXpositioner' the default name is 'positioner'
		return nexusBaseClass.toString().substring(2);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getNexusObject()
	 */
	@Override
	public final N getNexusObject() {
		if (nexusObject == null) {
			nexusObject = createNexusObject();
		}
		return nexusObject;
	}
	
	protected abstract N createNexusObject();

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
	 * external dataset within this {@link AbstractNexusObjectProvider}.
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
		if (externalFileName == null) { 
			throw new IllegalStateException("External file name must be set before adding external datasets.");
		}
		
		if (externalDatasetRanks == null) {
			externalDatasetRanks = new HashMap<>();
		}
		
		externalDatasetRanks.put(fieldName, rank);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getDefaultDataFieldName()
	 */
	@Override
	public String getPrimaryDataFieldName() {
		if (primaryDataFieldName != null) {
			return primaryDataFieldName;
		}

		// if the primary data field name hasn't been set, just use the first field
		if (axisDataFieldNames != null && !axisDataFieldNames.isEmpty()) {
			return axisDataFieldNames.iterator().next();
		}
		
		return null;
	}
	
	/**
	 * Sets the name of the field to use as the primary data field. If this device is the
	 * primary device for a scan then this field is used as the <code>@signal</code> field
	 * for the {@link NXdata} group.
	 * @param primaryDataFieldName name of the primary data field
	 */
	public void setPrimaryDataFieldName(String primaryDataFieldName) {
		this.primaryDataFieldName = primaryDataFieldName;
	}
	
	@Override
	public String getDefaultAxisDataFieldName() {
		return defaultAxisDataFieldName;
	}
	
	/**
	 * Sets the name of the data field for this device that acts as an axis.
	 * This field should only be set if this device is a scannable, i.e. a device that is
	 * set to a position at a particular point in the scan. 
	 * @param defaultAxisDataFieldName
	 */
	public void setDefaultAxisDataFieldName(String defaultAxisDataFieldName) {
		this.defaultAxisDataFieldName = defaultAxisDataFieldName;
		if (defaultAxisDataFieldName != null) {
			axisDataFieldNames.add(defaultAxisDataFieldName);
		}
	}

	@Override
	public List<String> getAxisDataFieldNames() {
		return new ArrayList<String>(axisDataFieldNames);
	}

	/**
	 * Sets the names of the data fields for this device. Each data field will be added
	 * to any {@link NXdata} group created for this scan.
	 * @param axisDataFieldNames names of data fields
	 */
	public void setAxisDataFieldNames(String... axisDataFieldNames) {
		this.axisDataFieldNames.clear();
		this.axisDataFieldNames.addAll(Arrays.asList(axisDataFieldNames));  
	}
	
	/**
	 * Adds the given name to the names of the axis data fields for this device. Each data field
	 * will be added to any {@link NXdata} group created for this scan (an {@link NXdata} group is
	 * created for each primary data field). In order to add a data field that should only
	 * be added to the {@link NXdata} group for a specific primary data field use
	 * {@link #addAxisDataFieldForPrimaryDataField(String, String, Integer, int...)}
	 * @param dataFieldName names of data fields
	 */
	public void addAxisDataFieldName(String dataFieldName) {
		this.axisDataFieldNames.add(dataFieldName);
	}
	
	/**
	 * Adds the given names to the names of the data fields for this device. Each data field
	 * will be added to any {@link NXdata} group created for this scan (an {@link NXdata} group is
	 * created for each primary data field). In order to add a data field that should only
	 * be added to the {@link NXdata} group for a specific primary data field use
	 * {@link #addAxisDataFieldForPrimaryDataField(String, String, Integer, int...)}
	 * @param axisDataFieldNames names of data fields
	 */
	public void addAxisDataFieldNames(String... axisDataFieldNames) {
		this.axisDataFieldNames.addAll(Arrays.asList(axisDataFieldNames));
	}
	
	/**
	 * Adds the given axis data field for the primary data field of this device. It will be
	 * added as an axis field to the {@link NXdata} group where the primary data field of this
	 * device is the <code>@signal</code> field.
	 * @param dataFieldName name of data field
	 * @param defaultAxisDimension the dimension of the primary data field for which this
	 *   field is a default axis
	 * @param dimensionMappings mappings between the dimensions of the axis data field and the
	 *    primary data field for this device, can be omitted if the mapping is one-to-one as is
	 *    usually the case
	 */
	public void addAxisDataField(String dataFieldName, Integer defaultAxisDimension, int... dimensionMappings) {
		if (primaryDataFieldName == null) {
			throw new IllegalStateException("Default writable data field not set.");
		}
		
		addAxisDataFieldForPrimaryDataField(dataFieldName, primaryDataFieldName,
				defaultAxisDimension, dimensionMappings);
	}
	
	/**
	 * Returns the names of the data fields that are axes for the given primary data field
	 * within this device.
	 * @param primaryDataFieldName primary data field name
	 * @return names of data fields
	 */
	public List<String> getAxisDataFieldsForPrimaryDataField(String primaryDataFieldName) {
		PrimaryDataFieldModel primaryDataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, false);
		if (primaryDataFieldModel == null) {
			return Collections.emptyList();
		}
		
		return primaryDataFieldModel.getAxisFieldNames();
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
	
	/**
	 * Adds a data field as an axis to a given primary data field. This field is only added
	 * to the {@link NXdata} group for the given primary data field (i.e. where it is the
	 * <code>@signal</code> field). 
	 * @param dataFieldName name of data field to add
	 * @param primaryDataFieldName name of primary data field that the new data field is an axis for
	 * @param defaultAxisDimension 
	 * @param dimensionMappings (optional) dimension mappings between the new data field
	 *    and the primary data. If this argument is not specified then the dimension mappings will
	 *    be assumed to be {0, 1, 2, etc} if the data field is multidimensional, or
	 *    { defaultAxisDimension } if the data field has a single dimension  
	 */
	public void addAxisDataFieldForPrimaryDataField(String dataFieldName, String primaryDataFieldName,
			Integer defaultAxisDimension, int... dimensionMappings) {
		PrimaryDataFieldModel primaryDataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, true);
		primaryDataFieldModel.addAxisField(dataFieldName, defaultAxisDimension, dimensionMappings);
	}
	
	/**
	 * Add an additional primary data field. This is a data field for which, when this
	 * device is the primary device in a scan, an additional {@link NXdata} group should be
	 * created with this field as the <code>@signal</code> field.
	 * @param dataFieldName the name of the additional primary data field
	 */
	public void addAdditionalPrimaryDataFieldName(String dataFieldName) {
		// TODO: is this the best name for this concept? it's a bit confusing
		// alternatives: addPrimaryDataField, addSecondarySignalField etc
		additionalPrimaryDataFieldNames.add(dataFieldName);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getAdditionalPrimaryDataFieldNames()
	 */
	@Override
	public List<String> getAdditionalPrimaryDataFieldNames() {
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
	
	public String getCollectionName() {
		return collectionName;
	}
	
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	@Override
	public Integer getDefaultAxisDimension(String primaryDataFieldName, String axisDataFieldName) {
		PrimaryDataFieldModel dataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, false);
		if (dataFieldModel != null) {
			return dataFieldModel.getDefaultAxisDimension(axisDataFieldName);
		}
		return null;
	}
	
	@Override
	public int[] getDimensionMappings(String primaryDataFieldName, String axisDataFieldName) {
		PrimaryDataFieldModel dataFieldModel = getPrimaryDataFieldModel(primaryDataFieldName, false);
		if (dataFieldModel != null) {
			return dataFieldModel.getDimensionMappings(axisDataFieldName);
		}
		return null;
	}

	public ILazyWriteableDataset getWriteableDataset(String fieldName) {
		return getNexusObject().getLazyWritableDataset(fieldName);
	}
	
	public Boolean getUseDeviceNameInNXdata() {
		return useDeviceNameInNXdata;
	}
	
	public void setUseDeviceNameInNXdata(boolean useDeviceNameInNXdata) {
		this.useDeviceNameInNXdata = useDeviceNameInNXdata;
	}

}
