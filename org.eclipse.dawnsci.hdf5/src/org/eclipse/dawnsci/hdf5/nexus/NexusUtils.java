/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.nexus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Datatype;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.Group;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h5.H5Datatype;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.hdf5.H5Utils;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.IFileFormatDataFile;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to mark groups in the hdf5 tree with nexus attributes.
 * 
 * This is a way not to use the nexus API.
 * 
 * @author gerring
 *
 */
public class NexusUtils {

	public static final String NXCLASS = "NX_class";	
	public static final String AXIS    = "axis";	
	public static final String LABEL   = "label";	
	public static final String PRIM    = "primary";	
	public static final String SIGNAL  = "signal";	
	public static final String UNIT    = "units";	
	
	private final static Logger logger = LoggerFactory.getLogger(NexusUtils.class);

	/**
	 * Sets the nexus attribute so that if something is looking for them,
	 * then they are there.
	 * 
	 * @param file
	 * @param entry
	 * @param entryKey
	 * @throws Exception
	 */
	public static void setNexusAttribute(final FileFormat file, 
			                             final HObject    entry,
			                             final String     entryKey) throws Exception {
		
		setAttribute(file, entry, NXCLASS, entryKey);
	}
	
	/**
	 * Set any attribute on a HObject.
	 * 
	 * @param file
	 * @param entry
	 * @param name
	 * @param entryKey
	 * @throws Exception
	 */
	public static void setAttribute(final FileFormat file, 
									final HObject    entry,
									final String     name,
									final String     entryKey) throws Exception {
		setAttribute(file, entry, name, entryKey, ATTRIBUTE_TYPE.FORCE);
	}
	
	public enum ATTRIBUTE_TYPE {
		/**
		 * Create if necessary or overwrite
		 */
		FORCE,
		/**
		 * Do not create but overwrite if exists
		 */
		OVERWRITE,
		/**
		 * Do not update if exists and is equal, otherwise create
		 */
		NO_OVERWRITE;
	}

	public static void setAttribute(final FileFormat file,
			final HObject    entry,
			final String     name,
			final String     entryKey,
			final ATTRIBUTE_TYPE type) throws Exception {
		
		boolean attributeExist = false;
		// Check if attribute is already there
		@SuppressWarnings("unchecked")
		final List<Object> attrList = entry.getMetadata();
		if (attrList!=null) {
			LOOP: for (Object object : attrList) {
				if (object instanceof Attribute) {
					final Attribute a    = (Attribute)object;
					if (name.equals(a.getName())) {
						
						final Object  oValue = a.getValue();
						final String[] aValue = oValue instanceof String[] ? (String[])oValue : null;
						if (aValue!=null && type==ATTRIBUTE_TYPE.NO_OVERWRITE && entryKey.equals(aValue[0])) {
							return;
						}
						attributeExist = true;
						break LOOP;
					}
				}
			}
		}
		if (type==ATTRIBUTE_TYPE.OVERWRITE & !attributeExist) {
			return;
		}
		final int id = entry.open();
		try {
			String[] classValue = {entryKey};
			Datatype attrType = new H5Datatype(Datatype.CLASS_STRING, classValue[0].length()+1, -1, -1);
			Attribute attr = new Attribute(name, attrType, new long[]{1});
			attr.setValue(classValue);

			file.writeAttribute(entry, attr, attributeExist);

			if (entry instanceof Group) {
				attrList.add(attr);
				((Group)entry).writeMetadata(attrList);
			} else if (entry instanceof Dataset) {
				attrList.add(attr);
				((Dataset)entry).writeMetadata(attrList);
			}


		} finally {
			entry.close(id);
		}
	}


	/**
	 * Does not replace the attribute if it exists
	 * @param file
	 * @param entry
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public static void setIntAttribute(final FileFormat file, 
							           final HObject   entry,
							           final String    name,
							           final int       value) throws Exception {

		@SuppressWarnings("unchecked")
		final List<Object> attrList = entry.getMetadata();
		if (attrList!=null) for (Object object : attrList) {
			if (object instanceof Attribute) {
				final Attribute a      = (Attribute)object;
				if (name.equals(a.getName())) return;
			}
		}
		
		final int id = entry.open();
		try {
	        Datatype attrType = new H5Datatype(Datatype.CLASS_INTEGER, 1, -1, -1);
	        Attribute attr = new Attribute(name, attrType, new long[]{1});
	        attr.setValue(new int[]{value});
			
	        file.writeAttribute(entry, attr, false);

	        if (entry instanceof Group) {
	        	attrList.add(attr);
				((Group)entry).writeMetadata(attrList);
	        } else if (entry instanceof Dataset) {
	        	attrList.add(attr);
				((Dataset)entry).writeMetadata(attrList);
	        }
		        
		    
		} finally {
			entry.close(id);
		}
	}
	
	public static void setDatasetAttribute(IDataset dataset, String dsPath, IHierarchicalDataFile file) throws Exception {
		setDatasetAttribute(dataset, (Dataset)file.getData(dsPath), file);
	}

	public static void setDatasetAttribute(IDataset dataset, Dataset ds, IHierarchicalDataFile file) throws Exception {
		setDatasetAttribute(((IFileFormatDataFile)file).getFileFormat(), 
				                      ds, 
				                      dataset.getName(), 
				                      H5Utils.getDatatype(dataset), 
				                      H5Utils.getLong(dataset.getShape()), 
				                      ((org.eclipse.dawnsci.analysis.dataset.impl.Dataset)dataset).getBuffer());
	}

	/**
	 * Does not replace the attribute if it exists
	 * @param file
	 * @param entry
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public static void setDatasetAttribute(final FileFormat file, 
							           final HObject   entry,
							           final String    name,
							           final Datatype dtype, final long[] shape, final Object buffer) throws Exception {
		
		@SuppressWarnings("unchecked")
		final List<Object> attrList = entry.getMetadata();
		if (attrList!=null) for (Object object : attrList) {
			if (object instanceof Attribute) {
				final Attribute a      = (Attribute)object;
				if (name.equals(a.getName())) return;
			}
		}
		
		final int id = entry.open();
		try {
	        Attribute attr = new Attribute(name, dtype, shape);
	        attr.setValue(buffer);
			
	        file.writeAttribute(entry, attr, false);

	        if (entry instanceof Group) {
	        	attrList.add(attr);
				((Group)entry).writeMetadata(attrList);
	        } else if (entry instanceof Dataset) {
	        	attrList.add(attr);
				((Dataset)entry).writeMetadata(attrList);
	        }
		        
		    
		} finally {
			entry.close(id);
		}
	}
	
	
	/**
	 * Gets the nexus axes from the data node, if there are any there
	 * 
	 * TODO Deal with label attribute?
	 * 
	 * @param FileFormat - the file
	 * @param dataNode - the node with the signal
	 * @param dimension, we want the axis for 1, 2, 3 etc.
	 * @return
	 * @throws Exception especially if dims are ask for which the signal does not have.
	 */
	public static List<String> getAxes(final FileFormat file, final Dataset signal, int dimension) throws Exception {
		
		final List<String>         axesTmp = new ArrayList<String>(3);
        final Map<Integer, String> axesMap = new TreeMap<Integer, String>();
		
        signal.getMetadata();
       
        if (dimension>signal.getDims().length) return null;
        final long size = signal.getDims()[dimension-1];

        final String parentPath = signal.getFullName().substring(0, signal.getFullName().lastIndexOf("/"));
        
        final Group parent = (Group)file.get(parentPath);
        
        final List<HObject> children = parent.getMemberList();
		for (HObject hObject : children) {
			final List<?> att = hObject.getMetadata();
			if (!(hObject instanceof Dataset)) continue;
			if (hObject.getFullName().equals(signal.getFullName())) continue;
			
			String axis = null;
			int     pos  = -1;
			boolean isSignal = false;
			for (Object object : att) {
				if (object instanceof Attribute) {
					Attribute attribute = (Attribute)object;
					if (AXIS.equals(attribute.getName())) {
						int iaxis = getAttributeIntValue(attribute);
						if (iaxis<0) { // We look for comma separated string
							final int[]   axesDims = getAttributeIntValues(attribute);
							final long[]  shapeAxes = ((Dataset)hObject).getDims();
							final long[]  shapeData = signal.getDims();
							if (axesDims!=null && Arrays.equals(shapeAxes, shapeData)) {
								for (int dim : axesDims) {
									if (dim == dimension) {
										axis = ((Dataset)hObject).getFullName()+":"+dimension;
										break;
									}
								}
							}
						}
						if (iaxis == dimension) axis = ((Dataset)hObject).getFullName();
						
					} else if (PRIM.equals(attribute.getName())) {
						if (pos!=0) pos = getAttributeIntValue(attribute);
						
					} else if (LABEL.equals(attribute.getName())) {
						int labelAxis = getAttributeIntValue(attribute);
						if (labelAxis == dimension) pos = 0;
						
					} else if (SIGNAL.equals(attribute.getName())) {
						isSignal = true;
						axis     = null;
						pos      = -1;
						break;
					}
				}
			}
			
			// Add any the same shape as this dimension
			// providing that they are not signals
			// Some nexus files set axis wrong
			if (axis==null && !isSignal) {
				final long[] dims = ((Dataset)hObject).getDims();
				if (dims[0]==size && dims.length==1) {
					axis = ((Dataset)hObject).getFullName();
				}
			}
			
			if (axis!=null) {
				if (pos<0) {
					axesTmp.add(axis);
				} else {
					axesMap.put(pos, axis);
				}
			}
		}
		
		final List<String> axes = new ArrayList<String>(3);
		if (!axesMap.isEmpty()) {
			for (Integer pos : axesMap.keySet()) {
				axes.add(axesMap.get(pos));
			}
		}
		axes.addAll(axesTmp);
		
		return axes;
	}

	/**
	 * Gets the int value or returns -1 (Can only be used for values which are not allowed to be -1!)
	 * @param attribute
	 * @return
	 */
	private static int getAttributeIntValue(Attribute attribute) {
		final Object ob = attribute.getValue();
		if (ob instanceof int[]) {
			int[] ia = (int[])ob;
			return ia[0];
		} else if (ob instanceof String[]) {
			String[] sa = (String[])ob;
			try {
				return Integer.parseInt(sa[0]);
			} catch (Throwable ne) {
				return -1;
			}
		}

		return -1;
	}
	
	private static int[] getAttributeIntValues(Attribute attribute) {
		final Object ob = attribute.getValue();
		if (ob instanceof String[]) {
			String[] sa = (String[])ob;
			try {
				final String[] axes  = sa[0].split(",");
				int[] ret = new int[axes.length];
				for (int i = 0; i < axes.length; i++) {
					ret[i] = Integer.parseInt(axes[i].trim());
				}
				return ret;
			} catch (Throwable ne) {
				return null;
			}
		}

		return null;
	}

	/**
	 * Returns names of axes in group at same level as name passed in.
	 * 
	 * This opens and safely closes a nexus file if one is not already open for
	 * this location.
	 * 
	 * @param filePath
	 * @param nexusPath - path to signal dataset
	 * @param dimension, the dimension we want the axis for starting with 1
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAxisNames(String filePath, String nexusPath, int dimension) throws Exception {

		if (filePath==null || nexusPath==null) return null;
		if (dimension<1) return  null;
       	IHierarchicalDataFile file = null;
        try {
        	file = HierarchicalDataFactory.getReader(filePath, true);
        	return file.getNexusAxesNames(nexusPath, dimension);
        } finally {
        	if (file!=null) file.close();
        }
	}
	
	/**
	 * Returns the attribute name of a nexus group.
	 * 
	 * If the group has more than one attribute only the first is returned
	 * 
	 * @param group
	 * @throws Exception 
	 */
	public static String getNexusGroupAttributeValue(IHierarchicalDataFile file, String group, String name) throws Exception {
		
		final HObject object = (HObject)file.getData(group);
		for (Object ob: object.getMetadata()) {
			if (ob instanceof Attribute) {
				Attribute ab = (Attribute)ob;
				if (ab.getName().toLowerCase().equals(name.toLowerCase())) {
					Object test = ab.getValue();
					if (test instanceof String[])
						return ((String[])test)[0];
				}
			}
		}
		return null;
	}
	
	/**
	 * Breath first search of a hierarchical data file group.
	 * 
	 * @param finder - IFindInNexus object, used to test a group
	 * @param rootGroup - the group to be searched
	 * @param findFirst - whether the search returns when the first object is found (quicker for single objects)
	 */
	public static List<String> nexusBreadthFirstSearch(IHierarchicalDataFile file, IFindInNexus finder, String rootGroup, boolean findFirst) throws Exception {
		
		List<String> out = new ArrayList<String>();
		
		Queue<String> queue = new LinkedList<String>();
		for (String nxObject : file.memberList(rootGroup)) {
			if (finder.inNexus(nxObject)) {
				
				if (findFirst) return Arrays.asList(nxObject);
				else out.add(nxObject);
			}
			
			if(file.isGroup(nxObject)) {
				queue.add(nxObject);
			}
		}
		
		Integer i = 0;
		
		while (queue.size() != 0) {
			String group = queue.poll();
			for (String nxObject: file.memberList(group)) {
				
				if (finder.inNexus(nxObject)) {
					if (findFirst) return Arrays.asList(nxObject);
					else out.add(nxObject);
				}
				
				if (file.isGroup(nxObject)) {
					queue.add(nxObject);
				}
				
				i++;
			}
		}
		
		if (i > 50) {
			final String name = rootGroup.substring(rootGroup.lastIndexOf('/')+1);
			logger.debug("This many times through loop (For node "+ name +"): " + i.toString());
		}
		
		return out;
	}
}
