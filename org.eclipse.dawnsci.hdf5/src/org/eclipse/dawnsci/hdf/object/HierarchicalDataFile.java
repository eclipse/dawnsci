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
package org.eclipse.dawnsci.hdf.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.hdf.object.nexus.NexusUtils;
import org.eclipse.dawnsci.hdf.object.nexus.NexusUtils.ATTRIBUTE_TYPE;
import org.eclipse.january.dataset.DTypeUtils;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.SliceND;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hdf.hdf5lib.H5;
import hdf.hdf5lib.HDF5Constants;
import hdf.object.Attribute;
import hdf.object.Dataset;
import hdf.object.Datatype;
import hdf.object.FileFormat;
import hdf.object.Group;
import hdf.object.HObject;
import hdf.object.h5.H5Datatype;
import hdf.object.h5.H5File;
import hdf.object.h5.H5ScalarDS;

/**
 * 
 * This class is a singleton ensuring that one 
 * one hdf5 file for a given path is open at a time.
 * 
 * Currently used only for READING an HDF5 file can be
 * extended for writing later if required.
 * 
 * It keeps a count of the same file being accessed by
 * different threads. YOU MUST CALL close() in a finally
 * when using this class.
 * 
 * Usage:
 * 
 * HierarchicalDataFile file = null;
 * try {
 *     file = new HierarchicalDataFile(...);
 *     Group root = file.getRoot();
 * } finally {
 *     file.close();
 * }
 * 
 * @author gerring
 *
 */
public class HierarchicalDataFile implements IHierarchicalDataFile, IFileFormatDataFile {
	
	private static final Logger logger = LoggerFactory.getLogger(HierarchicalDataFile.class);
	
	private static Map<String,HierarchicalDataFile> readCache;
	private static Collection<String>               writeCache;
	private static Map<String,ReentrantLock>        lockMap;

	/**
	 * Opens a new HierarchicalDataFile or returns the currently
	 * open one if necessary. Keeps a count of threads which have
	 * opened the file and only finally closes the file when the count
	 * is back to zero.
	 * 
	 * Used through HierarchicalDataFactory only.
	 * 
	 * @param absolutePath
	 * @return
	 */
	static synchronized HierarchicalDataFile open(final String absolutePath, final int openType) throws Exception {
		return open(absolutePath, openType, false);
	}

	/**
	 * Opens a new HierarchicalDataFile or returns the currently
	 * open one if necessary. Keeps a count of threads which have
	 * opened the file and only finally closes the file when the count
	 * is back to zero.
	 * 
	 * Used through HierarchicalDataFactory only.
	 * 
	 * @param absolutePath
	 * @return
	 */
	static synchronized HierarchicalDataFile open(String absolutePath, final int openType, boolean waitForLock) throws Exception {
		absolutePath = HierarchicalDataFactory.canonicalisePath(absolutePath);

		if (openType == FileFormat.READ) {
			if (readCache==null) readCache = new Hashtable<String,HierarchicalDataFile>(7); // Synchronized!
			HierarchicalDataFile file = readCache.get(absolutePath);
			if (file!=null) {
				file.count++; 
				return file;
			}
	
			file = new HierarchicalDataFile(absolutePath, openType);
			readCache.put(absolutePath, file);
			return file;
			
		} else if (openType == FileFormat.WRITE || openType == FileFormat.CREATE) {
			
			if (writeCache==null) writeCache = new HashSet<String>(11);
			if (writeCache.contains(absolutePath)) {
				if (waitForLock) {
					lockMap.get(absolutePath).lock(); // Will block if another thread is using
				} else {
					throw new Exception("Already writing to "+absolutePath+"!");
				}
			}
			if (writeCache.contains(absolutePath)) throw new Exception("Already writing to "+absolutePath+"!");
			
			HierarchicalDataFile file = new HierarchicalDataFile(absolutePath, openType);
			
			synchronized(file) {
				writeCache.add(absolutePath);
				if (lockMap==null) lockMap = new Hashtable<String,ReentrantLock>(7); // Synchronized!
				
				final ReentrantLock lock = new ReentrantLock();
				lockMap.put(absolutePath, lock);
				lock.lock();
			}
			
			return file;
			
		} else {
			throw new Exception("Unrecognised FileFormat "+openType);
		}
	}
	
	/**
	 * Used internally to close all open references.
	 * @param path
	 */
	protected static void closeWriters(String path) throws Exception{
		if (writeCache.contains(path)) {
			writeCache.remove(path);
		}
	}

	/**
	 * Used internally to close all open references.
	 * @param path
	 */
	protected static void closeReaders(String path) throws Exception{
		if (readCache.containsKey(path)) {
			readCache.get(path).close(true);
		}
	}
	
	public boolean isClosed() {
		if (readCache==null || writeCache==null) return true;
		if (path==null) return true;
		return !readCache.containsKey(path) && !writeCache.contains(path);
	}
	
	/**
	 * close the files and clear them from the cache.
	 * @throws Exception
	 */
	public static synchronized void clear() throws Exception {
		if (readCache!=null) {
			final Collection<HierarchicalDataFile> files = readCache.values();
			for (HierarchicalDataFile file : files) file.close();
		}
	}
	
	private final int        openType;
	/**
	 * The reason that FileFormat file is not available through a getter is
	 * that we want to ensure that it is never closed externally.
	 * DO NOT EXPOSE IT WITH A GETTER
	 */
	private       FileFormat file;
	private final String     path;
	private int              count=0;

	private HierarchicalDataFile(final String path, final int openType) throws Exception {
		
		this.path = path;
		this.openType = openType;
		// retrieve an instance of H5File
		FileFormat fileFormat = FileFormat.getFileFormat(FileFormat.FILE_TYPE_HDF5);
        if (fileFormat == null) throw new NullPointerException("Cannot deal with hdf5 files!");

		// open the file with read-only access
		this.file = fileFormat.createInstance(path, openType);
		if (file == null) throw new Exception("Failed to open file: "+path);

		try {
		    file.open();
		} catch (hdf.hdf5lib.exceptions.HDF5LibraryException ne) {
			long fapl = -1; // FIXME revert once upstream has fixed broken backward compatibility
			try {
				fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
			} catch (hdf.hdf5lib.exceptions.HDF5LibraryException e) {
				logger.error("Could not create file access property list");
				throw e;
			}
			try {
				H5.H5Pset(fapl, "clear_status_flags", 1);
			} catch (hdf.hdf5lib.exceptions.HDF5LibraryException e) {
				logger.warn("Could not clear status flag but continuing to open file");
			}
			try {
			    ((H5File) file).open(fapl);

				if (fapl != -1) {
					try {
						H5.H5Pclose(fapl);
					} catch (hdf.hdf5lib.exceptions.HDF5LibraryException e2) {
						logger.error("Could not close file access property list");
						throw e2;
					}
				}
				} catch (hdf.hdf5lib.exceptions.HDF5LibraryException ex) {
				fileFormat = FileFormat.getFileFormat(FileFormat.FILE_TYPE_HDF4);
				
				this.file = fileFormat.createInstance(path, openType);
				if (file == null) throw new Exception("Failed to open file: "+path);
				
			    file.open();
			    
			    logger.error("The file "+path+" is HDF4, it will open but plotting features are disabled.");
			}
		}
		
		if (openType == FileFormat.WRITE || openType == FileFormat.CREATE) {
			if (!file.canWrite()) throw new Exception("Cannot write to '"+path+"'!");
		}
		count=1;
	}

	/**
	 * Returns the root TreeNode for iterating down the
	 * tree of data.
	 * @return
	 */
	public TreeNode getNode() {
		return file.getRootNode();
	}

	/**
	 * Path of the root group
	 */
	public String getRoot() {
		return _getRoot().getFullName();
	}
	/**
	 * Returns the root Group. Note that the close method should not
	 * have been called yet.
	 * 
	 * @return
	 */
	protected Group _getRoot() {
		return (Group)((DefaultMutableTreeNode)getNode()).getUserObject();
	}
	
	/**
	 * Returns the object with using full name.
	 * 
	 * Full name is a / separated full path to the
	 * data e.g. /entry1/counterTimer01/energy
	 * 
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public HObject getData(final String path) throws Exception {	
		HObject object = file.get(path);
		HObject link   = HierarchicalDataUtils.getDataLink(object, this);
		return link!=null ? link : object;
	}
	
	@Override
	public boolean isDataset(String fullPath) throws Exception {
		HObject object = getData(fullPath);
		return object instanceof Dataset;
	}
	
	@Override
	public boolean isGroup(String fullPath) throws Exception{
		HObject object = getData(fullPath);
		return object instanceof Group;
	}

	@Override
	public String rename(String path, String newName) throws Exception {
		HObject object = getData(path);
		object.setName(newName);
		return object.getFullName();
	}

	
	public String getParent(final String path) throws Exception {
		
		final int index = path.lastIndexOf(Node.SEPARATOR);
		if (index<0) return null;
		
    	final String parentPath = path.substring(0, index);
        Group group = (Group)getData(parentPath);
		return group!=null ? parentPath : null;
	}
	
	/**
	 * Reads the attribute from the file.
	 * 
	 * @throws Exception 
	 * 
	 */
	public String getAttributeValue(String fullAttributeKey) throws Exception {
		
		final String[] sa    = fullAttributeKey.split(Node.ATTRIBUTE);
		final HObject object = getData(sa[0]);
		List<?> attributes = object.getMetadata();
		if (attributes==null || attributes.isEmpty()) return null;

		for (Object attribute : attributes) {
			if (attribute instanceof Attribute) {
				Attribute a = (Attribute)attribute;
				if (!sa[1].equals(a.getName())) continue;
				return HierarchicalDataUtils.extractValue(a.getValue());
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> getAttributeValues(String path) {
		HObject object;
		try {
			object = getData(path);
		} catch (Exception e) {
			// Path does not exist
			object = null;
		}
		if (object == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<>();
		List<?> attributes;
		try {
			attributes = object.getMetadata();
		} catch (Exception e) {
			// can't get metadata, to be consistent with #getAttributeValues() we
			// return empty map for this node
			return map;
		}

		for (Object attribute : attributes) {
			if (attribute instanceof Attribute) {
				Attribute a = (Attribute) attribute;
				map.put(a.getName(), a.getValue());
			}
		}
    	return map;
	}
	
	
	@Override
	public Map<String, Object> getAttributeValues() {
		return getAttributeValues(_getRoot(), new HashMap<String, Object>(89));
	}

    private Map<String, Object> getAttributeValues(HObject object, Map<String, Object> allAttributes) {
    	
    	try {
	    	final List<?> attributes = object.getMetadata();
	    	if (attributes!=null && !attributes.isEmpty()) {
	    		for (Object attribute : attributes) {
	    			if (attribute instanceof Attribute) {
	    				Attribute a = (Attribute)attribute;
	    				final StringBuilder buf = new StringBuilder();
	    				buf.append(object.getFullName());
	    				buf.append(Node.ATTRIBUTE);
	    				buf.append(a.getName());
	    				allAttributes.put(buf.toString(), a.getValue());
	    			}
	    		}
	    	}
    	} catch (Exception ignored) {
    		// We try and read other attributes anyway!
    	}

		List<HObject> members = object instanceof Group ? ((Group)object).getMemberList() : null;
		if (members!=null && !members.isEmpty()) {
			for (HObject member : members) {
				getAttributeValues(member, allAttributes);
			}
		}
   	       
        return allAttributes;
    }

	/**
	 * 
	 * @return a list of the full paths of the data sets.
	 */
	@Override
	public HierarchicalInfo getDatasetInformation(int dataType)  throws Exception {
		final HierarchicalInfo info = new HierarchicalInfo();
		getDatasetInformation(_getRoot(), info, dataType);
		return info;
	}
	
	private void getDatasetInformation(Group g, HierarchicalInfo info, int dataType)  throws Exception {
		
		List<HObject> members = g.getMemberList();
		for (HObject object : members) {
			if (object instanceof Dataset) {
				final Dataset set  = (Dataset)object;
				final Dataset link = (Dataset)HierarchicalDataUtils.getDataLink(set, this);
				final Dataset data = link!=null ? link : set;
				
				if (!HierarchicalDataUtils.isDataType(data, dataType)) continue;

				final long[] longShape= (long[])data.getDims();
				final int[]  intShape = new int[longShape.length];
				
				long size = longShape[0];
				for (int i = 0; i < intShape.length; i++) {
					intShape[i] = (int)longShape[i];
					size*=longShape[i];
				}
				info.addDataInfo(set.getFullName(), intShape, (int) size);
			}
		
			if (object instanceof Group) {
				getDatasetInformation((Group)object, info, dataType);
			}
			
			
		}
	}

	/**
	 * 
	 * @param dataType one of NUMBER_ARRAY or TEXT or one of the Datatype.CLASS_* variables. Use -1 for everything.
	 * @return
	 * @throws Exception
	 */
	public List<String> getDatasetNames(final int dataType)  throws Exception {
		return getDatasetNames(_getRoot(), dataType, new ArrayList<String>(7));
	}

	private List<String> getDatasetNames(Group g, final int dataType, final List<String> names)  throws Exception {
		
		List<HObject> members = g.getMemberList();
		for (HObject object : members) {
			
			if (object instanceof Dataset) {
				final Dataset set  = (Dataset)object;
				final Dataset link = (Dataset)HierarchicalDataUtils.getDataLink(set, this);
				final Dataset data = link!=null ? link : set;

				if (!HierarchicalDataUtils.isDataType(data, dataType)) continue;
				names.add(set.getFullName());
			}
		
			if (object instanceof Group) {
				getDatasetNames((Group)object, dataType, names);
			}
		}
		return names;
	}
	
	/**
	 * 
	 * @return a list of the full paths of the data sets.
	 */
	public Map<String, Integer> getDatasetSizes(int dataType)  throws Exception{
		return getDatasetSizes(_getRoot(), new HashMap<String, Integer>(31), dataType);
	}
	
	private Map<String, Integer> getDatasetSizes(Group g, Map<String, Integer> sizes, int dataType) throws Exception {
		
		List<HObject> members = g.getMemberList();
		for (HObject object : members) {
			
			if (object instanceof Dataset) {
				final Dataset set  = (Dataset)object;
				final Dataset link = (Dataset)HierarchicalDataUtils.getDataLink(set, this);
				final Dataset data = link!=null ? link : set;
				if (!HierarchicalDataUtils.isDataType(data, dataType)) continue;
				
				final long[] shape = (long[])data.getDims();
				if (shape!=null) {
					long size = shape[0];
					for (int i = 1; i < shape.length; i++) size*=shape[i];
					sizes.put(set.getFullName(), new Integer((int)size));
				}
			}
		
			if (object instanceof Group) {
				getDatasetSizes((Group)object, sizes, dataType);
			}
		}
		return sizes;
	}

	/**
	 * 
	 * @return a list of the full paths of the data sets.
	 */
	@Override
	public Map<String, int[]> getDatasetShapes(int dataType)  throws Exception {
		return getDatasetShapes(_getRoot(), new HashMap<String, int[]>(31), dataType);
	}
	
	private Map<String, int[]> getDatasetShapes(Group g, Map<String, int[]> shapes, int dataType)  throws Exception {
		
		List<HObject> members = g.getMemberList();
		for (HObject object : members) {
			
			if (object instanceof Dataset) {
				final Dataset set  = (Dataset)object;
				final Dataset link = (Dataset)HierarchicalDataUtils.getDataLink(set, this);
				final Dataset data = link!=null ? link : set;
				if (!HierarchicalDataUtils.isDataType(data, dataType)) continue;

				final long[] longShape= (long[])data.getDims();
				final int[]  intShape = new int[longShape.length];
				for (int i = 0; i < intShape.length; i++) intShape[i] = (int)longShape[i];
				shapes.put(set.getFullName(), (int[])intShape);
			}
		
			if (object instanceof Group) {
				getDatasetShapes((Group)object, shapes, dataType);
			}
		}
		return shapes;
	}

	/**
	 * closes a file and removes it from the cache providing there
	 * are not other users of the file registered.
	 * 
	 * Synchronized to avoid more than one file
	 * being opened for a given path.
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception {
		close(false);
	}
	/**
	 * closes a file and removes it from the cache providing there
	 * are not other users of the file registered.
	 * 
	 * Synchronized to avoid more than one file
	 * being opened for a given path.
	 * 
	 * @throws Exception
	 */
	protected synchronized void close(boolean force) throws Exception {
		
		// Close links
		if (linkReferences!=null) {
			for (String aPath : linkReferences.keySet()) {
				((IHierarchicalDataFile)linkReferences.get(aPath)).close();
			}
			linkReferences.clear();
		}

		if (openType == FileFormat.READ) {
			count--;
			if (count<=0 || force) {
				count = 0; // Just to be sure it does not get<0, unlikely
				file.close();
				readCache.remove(path);
			}
		} else if (openType == FileFormat.WRITE || openType == FileFormat.CREATE) {
			file.close();
			synchronized (file) {
				writeCache.remove(path);
				final ReentrantLock lock = lockMap.remove(path);
				if (lock!=null) lock.unlock();
			}
		}
	}

	private Map<String,IHierarchicalDataFile> linkReferences;
	
    /**
     * 
     * @param aPath
     * @return
     * @throws Exception
     */
	protected IHierarchicalDataFile getLinkedFile(String aPath) throws Exception {
		if (linkReferences==null) linkReferences = new HashMap<String, IHierarchicalDataFile>(3);
		if (linkReferences.containsKey(aPath)) return linkReferences.get(aPath);
		
		IHierarchicalDataFile linkFile = HierarchicalDataFactory.getReader(aPath);			
		linkReferences.put(aPath, linkFile);
		return linkFile;
	}
	
	/**
	 * Creates a group or returns the existing one if there is one.
	 * @param name
	 * @throws Exception 
	 */
	public String group(final String path) throws Exception {
		
		HObject object = getData(path);
		if (object !=null && object instanceof Group) return object.getFullName();
		
		final String parent = getParent(path);
		final String name   = path.substring(path.lastIndexOf(Node.SEPARATOR)+1);
		
		Group par   = parent!=null ? (Group)getData(parent) : _getRoot();
		Group group = _group(name, par);
		
		return group!=null ? group.getFullName() : null;
	}

	/**
	 * Creates a group or returns the existing one if there is one.
	 * @param name
	 * @throws Exception 
	 */
	public String group(final String name, final String groupPath) throws Exception {
		Group parent = (Group)getData(groupPath);
		Group group  = _group(name, parent);
		return group!=null ? group.getFullName() : null;
	}

	@Override
	public String makeGroup(String path, boolean withParents) throws Exception {
		return createGroup(path, withParents).getFullName();
	}

	private Group createGroup(String path, boolean withParents) throws Exception {
		HObject object = null;
		try {
			object = getData(path);
		} catch (Exception e) {
		}

		if (object == null) {
			if (path.endsWith(Node.SEPARATOR)) {
				path = path.substring(0, path.length() - 1);
			}
			try {
				if (withParents) {
					// try from root
					Group g;
					if (path.startsWith(Tree.ROOT)) {
						path = path.substring(1);
					}
					g = _getRoot();
		
					int i;
					while ((i = path.indexOf(Node.SEPARATOR)) != -1) {
						g = _group(path.substring(0, i), g);
						path = path.substring(i + 1);
					}
					object = _group(path, g);
				} else {
					int i = path.lastIndexOf(Node.SEPARATOR);
					Group p = i == -1 ? _getRoot() : (Group) getData(path.substring(0, i));
					object = _group(path.substring(i + 1), p);
				}
			} catch (Exception e1) {
			}
		}
		if (!(object instanceof Group)) {
			throw new IllegalArgumentException("Path given was not for a group");
		}

		return (Group) object;
	}

	protected Group _group(final String name) throws Exception {
		return _group(name, _getRoot());
	}

	protected Group _group(final String name, final Group group) throws Exception {
		if ("/".equals(name)) return _getRoot();
		final HObject o = checkExists(name, group, Group.class);
		if (o!=null) return (Group)o;
		
		HObject object = (Group)getData(name);
		if (object!=null && object instanceof Group) return (Group)object;
		 
		return file.createGroup(name, group);
	}
	
	private HObject checkExists(String name, Group group, Class<? extends HObject> clazz) throws Exception{
		
		final List<?> childs = group.getMemberList();
		for (Object object : childs) {
			if (object instanceof HObject) {
				final HObject ho = (HObject)object;
				if (clazz.isInstance(ho) && ho.getName().equals(name)) {
					return (HObject)ho;
				}
				if (ho.getName().equals(name)) {
				    throw new Exception("'"+name+"' already exists in '"+group.getName()+"' and is not a '"+clazz.getName()+"'");
				}
			}
		}
		return null; // Not found
	}

	public void print() throws Exception {
		printGroup(_getRoot(), "");
	}

	/**
     * Recursively print a group and its members.
     * @throws Exception
     */
    private void printGroup(Group g, String indent) throws Exception {

        List<?> members = g.getMemberList();

        int n = members.size();
        indent += "    ";
        HObject obj = null;
        for (int i=0; i<n; i++)
        {
            obj = (HObject)members.get(i);
            System.out.println(indent+obj);
            if (obj instanceof Group)
            {
                printGroup((Group)obj, indent);
            }
        }
    }

	public String getPath() {
		return path;
	}

	@Override
	public void setIntAttribute(final String   entryPath,
					           final String    name,
					           final int       value) throws Exception {
		HObject entry = getData(entryPath);
		NexusUtils.setIntAttribute(file, entry, name, value);
	}

	@Override
	public void setNexusAttribute(String objectPath, String attribute) throws Exception {
		HObject object = getData(objectPath);
		NexusUtils.setNexusAttribute(file, object, attribute);
	}

	/**
	 * Creates and returns a new dataset with the given name and parent
	 * If it already exists then the dataset is overwritten
	 * 
	 * @param name
	 * @param value
	 */
	@Override
	public String replaceStringDataset(final String name, final String value, final String parent) throws Exception {
		return createStringDataset(name, value, parent, true);
	}

	@Override
	public String createStringDataset(final String name, final String value, final String parent) throws Exception {
		return createStringDataset(name, value, parent, false);
	}

	private String createStringDataset(String name, 
			                     final String value, 
			                     String  parentPath,
			                     final boolean overwrite) throws Exception {
		
		Group parent = _group(parentPath);
		final long id = parent.open();
		try {
			if (!overwrite) {
			    name = getUnique(name, parent, Dataset.class);
			} else {
				final String  fullPath = parent.getFullName();
				final HObject existing = checkExists(name, parent, Dataset.class);
				if (existing!=null) {
					file.delete(existing);
					parent = (Group)getData(fullPath);
				}
			}
			
	        String[] arrayValue = {value};
	        Datatype dtype = new H5Datatype(Datatype.CLASS_STRING, arrayValue[0].length()+1, -1, -1);
			Dataset dataset = file.createScalarDS(name, parent, dtype, new long[]{1}, null, null, 0, arrayValue);

			return dataset.getFullName();
			
		} finally {
			parent.close(id);
		}
	}

	@Override
	public String createStringDataset(final String name, final int size, final String parentPath) throws Exception {
		
		Group parent = _group(parentPath);
		final long id = parent.open();
		try {

			final String  fullPath = parent.getFullName();
			final HObject existing = checkExists(name, parent, Dataset.class);
			if (existing!=null) {
				file.delete(existing);
				parent = (Group)getData(fullPath);
			}
			
	        String[] arrayValue = {};
	        Datatype dtype  = H5Utils.getDatatype(org.eclipse.january.dataset.Dataset.STRING, size);
			Dataset dataset = file.createScalarDS(name, parent, dtype, new long[]{1}, null, null, 0, arrayValue);

			return dataset.getFullName();
			
		} finally {
			parent.close(id);
		}
	}

	@Override
	public String replaceDataset(final String name, IDataset data, final String parent) throws Exception {
		return createDataset(name, data, parent, true);
	}

	@Override
	public String createDataset(final String name, final IDataset data, final String parent) throws Exception {
		return createDataset(name, data, parent, false);
	}

    @Override
	public String createDataset(String     name,  
			                     final IDataset data,
			                     final String   parentPath,
			                     final boolean  overwrite) throws Exception {
    	int    dType   = DTypeUtils.getDType(data);
		long[] shape   = H5Utils.getLong(data.getShape());
		
		return createDataset(name, dType, shape, null, null, DatasetUtils.serializeDataset(data), null, parentPath, overwrite);
   	}

    @Override
	public String createDataset(final String name, final int dType, final long[] dims, final Object buffer, final String parent) throws Exception {
		return createDataset(name, dType, dims, null, null, buffer, null, parent, false);
	}

	@Override
	public String replaceDataset(final String name, final int dType, final long[] dims, final Object buffer, final String parent) throws Exception {
		return createDataset(name, dType, dims, null, null, buffer, null, parent, true);
	}

	protected String createDataset(String         name,  
			                    final int      dType, 
			                    final long[]   shape, 
			                    final long[]   maxShape, 
			                    final long[]   chunking, 
			                    final Object   buffer,
			                    final Object   fill,
			                    final String   parentPath,
								final boolean  overwrite) throws Exception {

		Datatype dtype = H5Utils.getDatatype(dType);

		int i = name.indexOf(Node.SEPARATOR); 
		if (i != -1) {
			name = name.substring(i + 1);
		}

		Group parent = createGroup(parentPath, true);
		final long id = parent.open();
		try {
			if (!overwrite) {
				name = getUnique(name, parent, Dataset.class);
			} else {
				final String  fullPath = parent.getFullName();
				final HObject existing = checkExists(name, parent, Dataset.class);
				if (existing!=null) {
					file.delete(existing);
					parent = (Group)getData(fullPath);
				}
			}

			Dataset dataset = file.createScalarDS(name, parent, dtype, shape, maxShape, chunking, 0, fill, buffer);
			return dataset.getFullName();

		} finally {
			parent.close(id);
		}

	}

	@Override
	public String createLink(String targetGroupPath, String linkName, String sourceFullPath) throws Exception {
		final HObject object = getData(sourceFullPath);
		if (object==null)  return null;
		
		final Group targetGroup = _group(targetGroupPath);
		HObject link = file.createLink(targetGroup, linkName, object);
		return link.getFullName();
	}

	public void delete(String fullPath) throws Exception {
		final HObject object = getData(fullPath);
		if (object==null) return;
		long id = object.open();
		try {
		    file.delete(object);
		} finally {
			object.close(id);
		}
	}
	
	public FileFormat getFileFormat() {
		return file;
	}

	/**
	 * Method currently synchronized, you cannot have more than one thread
	 * setting the shape size and writing the data at a time.
	 */
	@Override
	public synchronized String appendDataset(String         name,  
						                     final IDataset data,
						                     final String   parentGroupPath) throws Exception {
		
    	int    dType   = DTypeUtils.getDType(data);
		long[] shape   = H5Utils.getLong(data.getShape());
		
		return appendDataset(name, dType, shape, DatasetUtils.serializeDataset(data), parentGroupPath);
	}
	
	@Override
	public synchronized String appendDataset(String         name,  
								             final int      dType,
								             final long[]   shape,
								             final Object buffer,
								             final String   parentGroupPath) throws Exception {

		
		final Datatype dtype = H5Utils.getDatatype(dType);
	
		
		Group parent = _group(parentGroupPath);
		final long id = parent.open();
		
		try {
			final HObject o = checkExists(name, parent, Dataset.class);
			if (o==null) {
				final long[] appendShape = new long[shape.length+1];
				final long[] maxShape = new long[shape.length+1];
				appendShape[0] = 1;
				maxShape[0]    = -1; // unlimited
				for (int i = 0; i < shape.length; i++) {
					appendShape[i+1] = shape[i];
					maxShape[i+1] = shape[i];
				}
				Dataset dataset = file.createScalarDS(name, parent, dtype, appendShape, maxShape, null, 0, buffer);
				return dataset.getFullName();
				
			} else {
				H5ScalarDS dataset = (H5ScalarDS)o;
				dataset.getMetadata();
				final long[] dims  = dataset.getDims();
				final long   index = dims[0]+1;
				dims[0] = index;
				dataset.extend(dims);

				long[] start     = dataset.getStartDims();
		        long[] stride    = dataset.getStride();
		        long[] selected  = dataset.getSelectedDims();
		        
		        start[0] = index-1;
		        for (int i = 1; i < start.length; i++) start[i] = 0;
		        
		        stride[0] = 1;
		        for (int i = 1; i < stride.length; i++) stride[i] = 1;

		        selected[0] = 1;
		        for (int i = 1; i < selected.length; i++) selected[i] = dims[i];

		        dataset.write(buffer); // Hopefully at selected location
		        		        
				return dataset.getFullName();
			}
			
		} finally {
			parent.close(id);
		}
	}

	private String getUnique(String name, final Group parent, final Class<? extends HObject> clazz) throws Exception {
		
		final HObject o = checkExists(name, parent, clazz);
		if (o!=null) {
			int i=1;
			while(checkExists(name+i, parent, clazz)!=null) {
				++i;
			}
			name = name+i;
		}
		return name;
	}

	public static boolean isWriting(final String absolutePath) {
		return writeCache!=null && writeCache.contains(absolutePath);
	}
	public static boolean isReading(final String absolutePath) {
		return readCache!=null && readCache.containsKey(absolutePath);
	}

	@Override
	public synchronized List<String> getNexusAxesNames(String signalPath,
			int dimension) throws Exception {
		HObject signal = file.get(signalPath);
		return NexusUtils.getAxes(file, (Dataset) signal, dimension);
	}

	@Override
	public void setAttribute(String objectPath, String name, String value) throws Exception {
		HObject object = getData(objectPath);
		NexusUtils.setAttribute(file, object, name, value);
	}

	@Override
	public void setAttribute(String objectPath, String name, String value, boolean overwrite) throws Exception {
		HObject object = getData(objectPath);
		NexusUtils.setAttribute(file, object, name, value, overwrite ? ATTRIBUTE_TYPE.OVERWRITE : ATTRIBUTE_TYPE.NO_OVERWRITE);
	}

	@Override
	public long getDimensionSize(String datasetName, int dimension) throws Exception {
		
		Dataset signal = (Dataset)getData(datasetName);
		try {
			signal.getMetadata();
			return signal.getDims()[dimension - 1];
		} catch (Throwable ne) {
			return -1;
		}
	}

	@Override
	public synchronized String insertSlice(String name, final IDataset data,
			final String parentPath, final SliceND slice) throws Exception {
		return insertSlice(name, data, parentPath, slice, true);
	}

	protected synchronized String insertSlice(String name, final IDataset data,
			final String parentPath, final SliceND slice, boolean create)
			throws Exception {
		
		Datatype dtype = H5Utils.getDatatype(data);
		final Group parent = createGroup(parentPath, true);
		final long id = parent.open();
		
		try {
			
			final HObject o = checkExists(name, parent, Dataset.class);
			Dataset dataset;
			if (o==null) {
				if (create) {
					long[] chunks = null;
					if (data.getSize() > 1 && data.getRank() == slice.getShape().length) {
						chunks = H5Utils.getLong(data.getShape());
					}
					long[] totalShape = H5Utils.getLong(slice.getMaxShape());
					dataset = file.createScalarDS(name, parent, dtype, getMaxShape(H5Utils.getLong(data.getShape()), totalShape), totalShape, chunks, 0, null);
				} else {
					throw new IllegalArgumentException("Dataset does not exist");
				}
			} else {
				dataset = (Dataset)o;
			}

			dataset.getMetadata();
			int rank = dataset.getRank();
			long[] start    = dataset.getStartDims();
			long[] stride   = dataset.getStride();
			long[] selected = dataset.getSelectedDims();
			long[] shape = new long[rank];
			int[] istart = slice.getStart();
			int[] istep  = slice.getStep();
			int[] ishape = data.getShape();
			int[] sshape = slice.getSourceShape();

			for (int i = 0; i < rank; i++) {
				start[i] = istart[i];
				stride[i] = istep[i];
				selected[i] = ishape[i];
				shape[i] = sshape[i];
			}
			if (isShapeLarger(dataset.getDims(), shape)) {
				((H5ScalarDS) dataset).extend(shape);
			}
			dataset.write(DatasetUtils.serializeDataset(data));

			return dataset.getFullName();
		} finally {
			parent.close(id);
		}
	}

	private boolean isShapeLarger(long[] dims, long[] shape) {
		for (int i = 0; i < shape.length; i++) {
			if (shape[i] > dims[i])
				return true;
		}
		return false;
	}

	private long[] getMaxShape(long[] a, long[] b) {
		int rank = a.length;
		long[] shape = new long[rank];
		for (int i = 0; i < rank; i++) {
			shape[i] = Math.max(a[i], b[i]);
		}
		return shape;
	}

	@Override
	public List<String> memberList(String groupPath) throws Exception {
		
		Group group = _group(groupPath);
		List<HObject> members = group.getMemberList();
		List<String>  ret     = new ArrayList<String>(members.size());
		for (HObject member : members) {
			ret.add(member.getFullName());
		}
		return ret;
	}

}
