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

import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;

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

	protected N nexusObject = null;

	protected String name;

	protected final NexusBaseClass nexusBaseClass;

	protected String defaultDataNodeName;

	protected NexusBaseClass category;

	private boolean useDeviceNameAsAxisName = false;

	private String axisName = null;

	public AbstractNexusProvider(NexusBaseClass nexusBaseClass) {
		this(getDefaultName(nexusBaseClass), nexusBaseClass);
	}

	public static String getDefaultName(NexusBaseClass nexusBaseClass) {
		// the default name is the base class name without the initial 'NX',
		// e.g. for 'NXpositioner' the default name is 'positioner'
		return nexusBaseClass.toString().substring(2);
	}

	/**
	 * Creates a new {@link AbstractNexusProvider} for given name and base class type
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public AbstractNexusProvider(String name, NexusBaseClass nexusBaseClass) {
		this(name, nexusBaseClass, DEFAULT_DATA_NODE_NAME, null);
	}

	/**
	 * Creates a new {@link AbstractNexusProvider} for given name, base class type
	 * and data node name.
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public AbstractNexusProvider(String name, NexusBaseClass nexusBaseClass,
			String dataNodeName) {
		this(name, nexusBaseClass, dataNodeName, null);
	}

	/**
	 * Creates a new {@link AbstractNexusProvider} for given name, base class type,
	 * data node name and category.
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public AbstractNexusProvider(String name, NexusBaseClass nexusBaseClass,
			String dataNodeName, NexusBaseClass category) {
		this.name = name;
		this.nexusBaseClass = nexusBaseClass;
		this.defaultDataNodeName = dataNodeName;
		this.category = category;
	}

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

	/**
	 * Creates the nexus object for this {@link NexusObjectProvider} using the
	 * given {@link NexusNodeFactory}.
	 * @param nodeFactory node factory
	 * @return new nexus object
	 */
	protected abstract N doCreateNexusObject(NexusNodeFactory nodeFactory);

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
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getDefaultDataFieldName()
	 */
	@Override
	public String getDefaultDataFieldName() {
		return defaultDataNodeName;
	}

	public void setDefaultDataFieldName(String defaultDataFieldName) {
		this.defaultDataNodeName = defaultDataFieldName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getDefaultAxisName()
	 */
	@Override
	public String getDefaultAxisName() {
		if (axisName != null) {
			return axisName;
		}

		if (useDeviceNameAsAxisName) {
			return getName();
		}

		return getDefaultDataFieldName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusObjectProvider#getCategory()
	 */
	@Override
	public NexusBaseClass getCategory() {
		return category;
	}

	public AbstractNexusProvider<N> setCategory(NexusBaseClass category) {
		this.category = category;
		return this;
	}

	public AbstractNexusProvider<N> useDeviceNameAsAxisName(boolean useDeviceNameAsAxisName) {
		this.useDeviceNameAsAxisName = useDeviceNameAsAxisName;
		this.axisName = null;
		return this;
	}

	public AbstractNexusProvider<N> setAxisName(String axisName) {
		this.axisName = axisName;
		return this;
	}

	public ILazyWriteableDataset getDefaultDataset() {
		final String defaultDataFieldName = getDefaultDataFieldName();
		return getNexusObject().getLazyWritableDataset(defaultDataFieldName);
	}

	public ILazyWriteableDataset getWriteableDataset(String fieldName) {
		return getNexusObject().getLazyWritableDataset(fieldName);
	}

}
