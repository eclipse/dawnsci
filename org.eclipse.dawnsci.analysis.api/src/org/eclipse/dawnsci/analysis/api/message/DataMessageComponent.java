/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.eclipse.dawnsci.analysis.api.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.fitting.functions.IFunction;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.roi.IROI;

/**
 * This class is similar to a DataHolder in the scisoft diamond
 * plugins.
 * 
 * It is simpler and less subject to use else where. It is the main thing
 * which is passed around in the workflow system.
 *
 * It contains data and provenance, meta data, RIOs and functions.
 * 
 * @author gerring
 *
 */
public class DataMessageComponent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322907913441650831L;

	public enum VALUE_TYPE {
		/**
		 * Adds one string value to another if two are
		 * in one list
		 */
		ADDITIVE_STRING, 
		
		/**
		 * Overwrites the value if it exists already.
		 */
		OVERWRITE_STRING
	}
	
	/**
	 * The data is either primitive array or IDataset
	 */
	private Map<String,Serializable> list;
	/**
	 * What we did to the data in the pipeline
	 */
	private Map<String,String>   scalar;
	
	/**
	 * The data extends ROIBase
	 */
	private Map<String,Serializable> rois;
	
	/**
	 * The data extends AFunction
	 */
	private Map<String,Serializable> functions;
	
	/**
	 * User objects, any serializable object.
	 */
	private Map<String,Object> userObjects;

	
	/**
	 * A set of source meta data which may be altered
	 * to add more information if needed.
	 */
	private IMetadata            meta;
	
	/**
	 * 
	 */
	private Map<String,VALUE_TYPE>   valueTypes;
	
	/**
	 * Indicates if the message was generated during an error stream.
	 * 
	 */
	private boolean error = false;

	private long time;

	
	public DataMessageComponent() {
		time = System.currentTimeMillis();
	}

	public Map<String, Serializable> getList() {
		return list;
	}
	
	public Object getList(final String key) {
		if (list!=null) return list.get(key);
		return null;
	}

	public void setList(Map<String, Serializable> data) {
		this.list = data;
	}

	public IMetadata getMeta() {
		return meta;
	}

	public void setMeta(IMetadata metaData) {
		this.meta = metaData;
	}
	
	public void setList(final IDataset set) {
		if (list==null) list = new LinkedHashMap<String,Serializable>(1);
		String name = set.getName();
		if (name==null||"".equals(name)) name = "Unknown";
		putUnique(list, name, set);
	}
	
	public void clearList() {
		if (list!=null) list.clear();
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @param value
	 */
	private static <V> void putUnique(Map<String, V> data, String key, V value) {
		if (data.containsKey(key)) {
			key = getUniqueKey(data.keySet(), key);
		}
		data.put(key, value);
	}
	private static String getUniqueKey(Set<String> keySet, String key) {
		
        int num = 1;
        while(keySet.contains(key+num)) num++;
        return key+num;
	}	
    /**
     * Renames a list in the DataMessageComponent
     * @param existing
     * @param name
     * @return true if the name replaced was already there.
     */
	public boolean renameList(String existing, String name) {
		if (list==null) return false;
		
		Map<String,Serializable> map = new LinkedHashMap<String,Serializable>(1);
		map.putAll(list);
		final Serializable set = map.remove(existing);
		if (set instanceof IDataset) {
			((IDataset)set).setName(name);
		}
	
		boolean replaced = map.put(name, set) != null;
		this.list = map;
		return replaced;
	}

	
	public void addScalar(final Map<String,String> f) {
		if (f==null) return;
		addScalar(f, true);
	}

	public void addScalar(final Map<String, String> toAdd, boolean overwrite) {
		
		if (toAdd==null) return;
		
		if (scalar==null) scalar = new Hashtable<String,String>(toAdd.size());
		if (overwrite) {
			for (String key : toAdd.keySet()) {
				if (toAdd.get(key)!=null) {
					scalar.remove(key);
					scalar.put(key, toAdd.get(key));
				}
			}
		    
		} else {
			final Map<String, String> copy = new HashMap<String,String>(toAdd);
			copy.keySet().removeAll(scalar.keySet());
			for (String key : copy.keySet()) {
				if (copy.get(key)!=null) scalar.put(key, copy.get(key));
			}
		}
	}
	
	public void putScalar(final String key, final String value) {
		if (key==null) return;
		if (value == null) {
			if (scalar!=null) scalar.remove(key);
			return;
		}
		if (scalar==null) scalar = new Hashtable<String,String>(1);
		scalar.put(key,value);
	}
	
	public Map<String,String> getScalar() {
		return scalar;
	}
	
	public String getScalar(final String key) {
		if (scalar==null) return null;
		return scalar.get(key);
	}

	
	@Override
    public String toString() {
		StringBuilder buf = new StringBuilder("");
		if (list!=null&&!list.isEmpty()) {
			// Sorts the key set of the list
			Set<String> keySetList = new TreeSet<String>(list.keySet());
			for (String name: keySetList) {
				if (list.get(name)==null) continue;
				buf.append(this.formatString(name, list.get(name).toString()));
				buf.append("\n");
			}
		}
		if (scalar!=null&&!scalar.isEmpty()) {
			// Sorts the key set of the scalars
			Set<String> keySetList = new TreeSet<String>(scalar.keySet());
			for (String name: keySetList) {
				buf.append(this.formatString(name, scalar.get(name).toString()));
				buf.append("\n");
			}
		}
		if (meta!=null) {
			buf.append(meta.toString());
		}
		if ("".equals(buf.toString())) return super.toString();
		return buf.toString();
	}

	/**
	 * This private method format a given name and value to:
	 * 'name          : value [eventually trunkated]'
	 * @param name
	 * @param value
	 */
	private String formatString(String name, String value) {
		int maxStringLength = 60;
		int tabStop = 20;
		String objectName = name;
		String objectString = value.replaceAll("\n", "");
		if (objectName.length() < tabStop) objectName = String.format("%1$-" + tabStop + "s", objectName);
		if (objectString.length() > maxStringLength) objectString = objectString.substring(0, maxStringLength) + "...";
		return objectName + " : " + objectString;				
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (error ? 1231 : 1237);
		result = prime * result + ((functions == null) ? 0 : functions.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((rois == null) ? 0 : rois.hashCode());
		result = prime * result + ((scalar == null) ? 0 : scalar.hashCode());
		result = prime * result + ((userObjects == null) ? 0 : userObjects.hashCode());
		result = prime * result + ((valueTypes == null) ? 0 : valueTypes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataMessageComponent other = (DataMessageComponent) obj;
		if (error != other.error)
			return false;
		if (functions == null) {
			if (other.functions != null)
				return false;
		} else if (!functions.equals(other.functions))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (rois == null) {
			if (other.rois != null)
				return false;
		} else if (!rois.equals(other.rois))
			return false;
		if (scalar == null) {
			if (other.scalar != null)
				return false;
		} else if (!scalar.equals(other.scalar))
			return false;
		if (userObjects == null) {
			if (other.userObjects != null)
				return false;
		} else if (!userObjects.equals(other.userObjects))
			return false;
		if (valueTypes == null) {
			if (other.valueTypes != null)
				return false;
		} else if (!valueTypes.equals(other.valueTypes))
			return false;
		return true;
	}

	public VALUE_TYPE getValueType(String key) {
		if (valueTypes==null) return VALUE_TYPE.OVERWRITE_STRING;
		return valueTypes.get(key);
	}
	
	public void setValueType(final String key, final VALUE_TYPE type) {		
		if (valueTypes==null) valueTypes = new Hashtable<String,VALUE_TYPE>(7);
		valueTypes.put(key, type);
	}

	public void add(DataMessageComponent a) {
		
		if (a.list!=null) {
			if (list==null) list = new LinkedHashMap<String, Serializable>(a.list.size());
		    list.putAll(a.list);
		}
		
		if (a.scalar!=null) {
			if (scalar==null) scalar = new Hashtable<String, String>(a.scalar.size());
			scalar.putAll(a.scalar);
		}
		if (a.meta!=null)  meta = a.meta;
		
		if (a.valueTypes!=null) {
			if (valueTypes==null) valueTypes = new Hashtable<String, VALUE_TYPE>(a.valueTypes.size());
			valueTypes.putAll(a.valueTypes);
		}
		
		if (a.rois!=null) {
			for (String key : a.rois.keySet()) {
				addROI(key, a.getROI(key));
			}
		}
		
		if (a.functions!=null) {
			for (String key : a.functions.keySet()) {
				addFunction(key, a.getFunction(key));
			}
		}
	}

	public boolean isScalarOnly() {
		return (list==null || list.isEmpty()) && scalar!=null && scalar.size()>0;
	}

	public void addList(String name, IDataset a) {
		if (list==null) list = new LinkedHashMap<String,Serializable>(1);
		list.put(name, a);
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	
	// ROI Methods
	public void addROI(String name, IROI roi) {
		if (rois == null) rois = new LinkedHashMap<String,Serializable>(1);
		rois.put(name, roi);
	}
	
	public IROI getROI(String name) {
		if (rois == null) return null;
		return (IROI) rois.get(name);
	}
	
	public Map<String,Serializable> getROIs(){
		return rois;
	}
	
	public void clearROI() {
		if (rois!=null) rois.clear();
		rois = null;
	}
	public Map<String, Serializable> getRois() {
		return rois;
	}
	
	public void addList(Map<String, Serializable> data) {
		if (data==null) return;
		if (list==null) list = new LinkedHashMap<String,Serializable>(1);
		this.list.putAll(data);
	}
	
	public void addRois(final Map<String, Serializable> rdata) {
		if (rdata==null) return;
		if (rois==null) rois = new LinkedHashMap<String,Serializable>(1);
		this.rois.putAll(rdata);
	}

	public void addFunctions(Map<String, Serializable> functions2) {
		if (functions2==null) return;
		if (functions==null) functions = new LinkedHashMap<String,Serializable>(1);
		this.functions.putAll(functions2);
	}
	
	// Function Methods
	public void addFunction(String name, IFunction function) {
		if (functions == null) functions = new LinkedHashMap<String,Serializable>(1);
		functions.put(name, function);
	}
	
	// Function Methods
	public void addUserObject(String name, Object userObject) {
		if (userObjects == null) userObjects = new LinkedHashMap<String,Object>(1);
		userObjects.put(name, userObject);
	}

	public Object getUserObject(String name) {
		if (userObjects == null) return null;
		return userObjects.get(name);
	}
	
	public IFunction getFunction(String name) {
		if (functions == null) return null;
		return (IFunction) functions.get(name);
	}
	
	public Map<String,Serializable> getFunctions(){
		return functions;
	}
	
	public void clearFunctions() {
		if (functions!=null) functions.clear();
		functions = null;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
