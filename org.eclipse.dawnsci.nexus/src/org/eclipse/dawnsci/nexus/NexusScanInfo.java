package org.eclipse.dawnsci.nexus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.SliceND;

/**
 * 
 * This class represents information about the scan which the NeXus device is running in.
 * 
 * For instance, names of scannables in the axes and the rank of the scan.
 * 
 * @author Matthew Gerring
 *
 */
public class NexusScanInfo {

	private int rank;
	private Collection<String> scannableNames;
	private Set<String> monitorNames;
	private Set<String> metadataScannableNames;
	
	public NexusScanInfo() {
		this(Collections.emptyList());
	}
	
	/**
	 * 
	 * @param axisNames must be ordered correctly into indices
	 */
	public NexusScanInfo(Collection<String> axisNames) {
		super();
		this.scannableNames = axisNames;
		this.rank = 1;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Collection<String> getScannableNames() {
		if (scannableNames == null) {
			return Collections.emptyList();
		}
		return scannableNames;
	}
	
	public void setScannableNames(List<String> axisNames) {
		this.scannableNames = axisNames;
	}
	
	public boolean isScannable(String name) {
		return scannableNames != null && scannableNames.contains(name);
	}
	
	public Set<String> getMonitorNames() {
		if (monitorNames == null) {
			return Collections.emptySet();
		}
		return monitorNames;
	}
	
	public void setMonitorNames(Set<String> monitorNames) {
		this.monitorNames = monitorNames;
	}
	
	public boolean isMonitor(String name) {
		return monitorNames != null && monitorNames.contains(name);
	}
	
	public Set<String> getMetadataScannableNames() {
		if (metadataScannableNames == null) {
			return Collections.emptySet();
		}
		return metadataScannableNames;
	}
	
	public void setMetadataScannableNames(Set<String> metadataScannableNames) {
		this.metadataScannableNames = metadataScannableNames;
	}
	
	public boolean isMetadataScannable(String name) {
		return metadataScannableNames != null && metadataScannableNames.contains(name);
	}

	/**
	 * Attempts to make a chunk size from the scan.
	 * NOTE This assumes that the datashape is a resonable size currently.
	 * If the datashape is small, the chunking can become too small to usefully
	 * read.
	 * 
	 * @param datashape
	 * @return the suggested chunk array
	 */
	public int[] createChunk(int... datashape) {
		// Create chunk array of correct length
		final int[] chunk = new int[rank+datashape.length];

		// Initialise the array to all 1
		// TODO this is slightly redundant but ensures no zeros can ever be allowed through
		Arrays.fill(chunk, 1);

		// Change end of chunk array to match datashape
		int index = 0;
		for (int i = datashape.length; i>0; i--) {
			chunk[chunk.length-i] = datashape[index];
			index++;
		}
		return chunk;
	}
	

	/**
	 * Create a location for a slice of data from the list (correctly ordered) of
	 * scan names and thier relative indices. This information is available from the 
	 * IPosition which is sent into the device during the scan.
	 * 
	 * @param context LazyDataset we are writing to 
	 * @param names available from the IPosition for instance
	 * @param indices available from the IPosition for instance
	 * @param datashape shape of data that the device is adding to the nD stack
	 * @return
	 */
	@Deprecated
	public static SliceND createLocation(ILazyWriteableDataset context, Collection<String> names, Map<String,Integer> indices, int... datashape) {
		throw new IllegalArgumentException("Please use IScanRankService to determine the correct slice information during a scan!");
	}

	@Override
	public String toString() {
		return "NexusScanInfo [rank=" + rank + ", axisNames=" + scannableNames + "]";
	}

}
