/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.nexus;

import static org.eclipse.dawnsci.nexus.NexusScanInfo.NexusRole.PER_POINT;
import static org.eclipse.dawnsci.nexus.NexusScanInfo.NexusRole.PER_SCAN;
import static org.eclipse.dawnsci.nexus.NexusScanInfo.ScanRole.MONITOR_PER_SCAN;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
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
 * TODO mattd 2017-02-28: should this class be removed and we use ScanInformation / ScanModel instead?
 * 
 * @author Matthew Gerring
 *
 */
public class NexusScanInfo {
	
	public static enum NexusRole {
		PER_POINT, PER_SCAN
	}
	
	public static enum ScanRole {
		DETECTOR(PER_POINT),
		SCANNABLE(PER_POINT),
		MONITOR_PER_POINT(PER_POINT),
		MONITOR_PER_SCAN(PER_SCAN),
		NONE(PER_SCAN);
		
		private final NexusRole nexusRole;
		
		private ScanRole(NexusRole nexusRole) {
			this.nexusRole = nexusRole;
		}
		
		public NexusRole getNexusRole() {
			return nexusRole;
		}
		
	}
	
	private int rank;
	
	private final Map<ScanRole, Collection<String>> deviceNames;
	
	private int[] shape;
	
	private String filePath;
	
	public NexusScanInfo() {
		this(Collections.emptyList());
	}
	
	/**
	 * 
	 * @param axisNames must be ordered correctly into indices
	 */
	public NexusScanInfo(List<String> axisNames) {
		super();
		deviceNames = new EnumMap<>(ScanRole.class);
		deviceNames.put(ScanRole.SCANNABLE, axisNames);
		this.rank = 1;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	private void setDeviceNames(ScanRole scanRole, Collection<String> names) {
		// private so that we can ensure the correct type of collection for the role
		// e.g. List for Scannables
		deviceNames.put(scanRole, names);
	}
	
	public Collection<String> getDeviceNames(ScanRole scanRole) {
		Collection<String> names = deviceNames.get(scanRole);
		return names == null ? Collections.emptyList() : names;
	}
	
	public void setDetectorNames(Set<String> detectorNames) {
		setDeviceNames(ScanRole.DETECTOR, detectorNames);
	}
	
	public Collection<String> getDetectorNames() {
		return getDeviceNames(ScanRole.DETECTOR);
	}

	public List<String> getScannableNames() {
		return (List<String>) getDeviceNames(ScanRole.SCANNABLE);
	}
	
	public void setScannableNames(List<String> axisNames) {
		setDeviceNames(ScanRole.SCANNABLE, axisNames);
	}
	
	public Set<String> getPerPointMonitorNames() {
		return (Set<String>) getDeviceNames(ScanRole.MONITOR_PER_POINT);
	}
	
	public void setPerPointMonitorNames(Set<String> monitorNames) {
		setDeviceNames(ScanRole.MONITOR_PER_POINT, monitorNames);
	}
	
	public Set<String> getPerScanMonitorNames() {
		return (Set<String>) getDeviceNames(ScanRole.MONITOR_PER_SCAN);
	}
	
	public void setPerScanMonitorNames(Set<String> metadataScannableNames) {
		setDeviceNames(ScanRole.MONITOR_PER_SCAN, metadataScannableNames);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int[] getShape() {
		return shape;
	}

	public void setShape(int[] shape) {
		this.shape = shape;
	}

	/**
	 * Returns the {@link ScanRole} of the device with the given name within the scan. If the device
	 * is not in the scan {@link ScanRole#NONE} is returned.
	 * @param name name of device
	 * @return role or device within scan, never <code>null</code>
	 */
	public ScanRole getScanRole(String name) {
		for (ScanRole scanRole : deviceNames.keySet()) {
			Collection<String> names = deviceNames.get(scanRole);
			if (names != null && names.contains(name)) {
				return scanRole;
			}
		}
		
		return ScanRole.NONE;
	}
	
	/**
	 * Returns whether the device with the given name should write its data
	 * once for the whole scan, or 
	 * @param name
	 * @return
	 */
	public boolean writeDataPerScan(String name) {
		final ScanRole scanRole = getScanRole(name);
		return scanRole == null || scanRole == MONITOR_PER_SCAN;
	}
	
	public int[] createChunk(int... datashape) {
		return createChunk(true, datashape);
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
	public int[] createChunk(boolean append, int... datashape) {
		// Create chunk array of correct length
		final int[] chunk = append ? new int[rank+datashape.length] : new int[rank];

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
		return "NexusScanInfo [rank=" + rank + ", axisNames=" + getScannableNames() + "]";
	}

}
