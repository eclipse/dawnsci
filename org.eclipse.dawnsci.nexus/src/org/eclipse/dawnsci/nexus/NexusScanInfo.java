package org.eclipse.dawnsci.nexus;

import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;

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
	private List<String> axisNames;
	public NexusScanInfo() {
		
	}
	
	/**
	 * 
	 * @param axisNames must be ordered correctly into indices
	 */
	public NexusScanInfo(List<String> axisNames) {
		super();
		this.axisNames = axisNames;
		this.rank = axisNames.size();
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<String> getAxisNames() {
		return axisNames;
	}
	public void setAxisNames(List<String> axisNames) {
		this.axisNames = axisNames;
	}

	/**
	 * Attempts to make a chunk size from the scan.
	 * NOTE This assumes that the datashape is a resonable size currently.
	 * If the datashape is small, the chunking can become too small to usefully
	 * read.
	 * 
	 * @param datashape
	 * @return
	 */
	public int[] createChunk(int... datashape) {
		
		final int[] chunk = new int[rank+datashape.length];
		for (int i = 0; i < chunk.length-2; i++) {
			chunk[i] = 1;
		}
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
	public static SliceND createLocation(ILazyWriteableDataset context, List<String> names, Map<String,Integer> indices, int... datashape) {
		
		final int scanRank = names.size();
		final int[] start = new int[scanRank+datashape.length];
		final int[] stop  = new int[scanRank+datashape.length];
		for (int i = 0; i < scanRank; i++) {
			start[i] = indices.get(names.get(i));
			stop[i]  = indices.get(names.get(i))+1;
		}

		int index = 0;
		for (int i = datashape.length; i>0; i--) {
			start[start.length-i] = 0;
			stop[stop.length-i]  = datashape[index];
			index++;
		}
	  
		return new SliceND(context.getShape(), context.getMaxShape(), start, stop, null);
	}

	@Override
	public String toString() {
		return "NexusScanInfo [rank=" + rank + ", axisNames=" + axisNames + "]";
	}

}
