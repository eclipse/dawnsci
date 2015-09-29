package org.eclipse.dawnsci.nexus.scan;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;

/**
 * A nexus file builder knows how to build a nexus file for a given scan.
 */
public interface NexusFileBuilder {
	
	public void setNexusScan(NexusScan nexusScan);
	
	public void setFilePath(String filepath);
	
	public TreeFile buildNexusFile();
	
}
