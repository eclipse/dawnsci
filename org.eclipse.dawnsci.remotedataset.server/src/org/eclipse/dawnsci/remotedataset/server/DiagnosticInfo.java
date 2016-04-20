package org.eclipse.dawnsci.remotedataset.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * Simple class to hold info about what the DataServer does.
 * For instance for recording information about threads started.
 * 
 * @author Matthew Gerring
 *
 */
public class DiagnosticInfo {

	private Map<String, Collection<String>> info;
	
	public DiagnosticInfo() {
		info = new LinkedHashMap<>(7);
	}
	
	public void record(String key, String details) {
		Collection<String> inf = info.get(key);
		if (inf == null) {
			inf = new ArrayList<String>();
			info.put(key, inf);
		}
		inf.add(details);
	}
	
	public int getCount(String key) {
		return info.get(key) !=null ? info.get(key).size() : 0;
	}
	public Collection<String> get(String key) {
		return info.get(key);
	}
    public String toString() {
    	final StringBuilder buf = new StringBuilder();
        for (String key : info.keySet()) {
			buf.append(key);
			buf.append(":\n");
			buf.append(Arrays.toString(info.get(key).toArray(new String[getCount(key)])));
			buf.append("\n------\n");
		}
        return buf.toString();
    }
    
    public void merge(DiagnosticInfo other) {
    	info.putAll(other.info);
    }
}
