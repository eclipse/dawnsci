package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;

/**
 * An implementation of {@link AbstractNexusObjectProvider} that wraps the nexus object that
 * should be returned by {@link #getNexusObject()}.
 * 
 * @author Matthew Dickie 
 *
 * @param <N> nexus base class type, a subinterface of {@link NXobject}
 */
public class NexusObjectWrapper<N extends NXobject> extends AbstractNexusObjectProvider<N> {

	private N nexusObject = null;
	
	/**
	 * Creates a new {@link AbstractNexusObjectProvider} for given name, base class type
	 * and data node name.
	 * @param name name
	 * @param nexusBaseClass base class type
	 */
	public NexusObjectWrapper(String name, NexusBaseClass nexusBaseClass) {
		super(name, nexusBaseClass);
	}

	/**
	 * Creates a new {@link AbstractNexusObjectProvider} for given name, base class type
	 * and data node name.
	 * @param name name
	 * @param nexusObject nexusObject
	 */
	public NexusObjectWrapper(String name, N nexusObject) {
		this(name, nexusObject.getNexusBaseClass(), null);
		setNexusObject(nexusObject);
	}
	
	/**
	 * Sets the nexus object to be returned by this wrapepr
	 * @param nexusObject
	 */
	public void setNexusObject(N nexusObject) {
		this.nexusObject = nexusObject;
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
	 * @param nexusObject nexusObject to return
	 * @param defaultDataFieldName default data field, the default signal field for a detector,
	 *    the default axis field for any other type of nexus object
	 * @param additionalDataFieldNames the names of any additional data fields
	 */
	public NexusObjectWrapper(String name, N nexusObject ,
			String defaultDataFieldName, String... additionalDataFieldNames) {
		super(name, nexusObject.getNexusBaseClass(), defaultDataFieldName, additionalDataFieldNames);
		setNexusObject(nexusObject);
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
	public NexusObjectWrapper(String name, NexusBaseClass nexusBaseClass,
			String defaultDataFieldName, String... additionalDataFieldNames) {
		super(name, nexusBaseClass, defaultDataFieldName, additionalDataFieldNames);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.AbstractNexusObjectProvider#createNexusObject()
	 */
	@Override
	protected N createNexusObject() {
		return nexusObject;
	}

}
