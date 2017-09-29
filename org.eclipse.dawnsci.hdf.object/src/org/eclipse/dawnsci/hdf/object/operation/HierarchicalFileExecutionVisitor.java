/*-
 *******************************************************************************
 * Copyright (c) 2011, 2017 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jacob Filik - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.hdf.object.operation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.dawnsci.analysis.api.persistence.IPersistenceService;
import org.eclipse.dawnsci.analysis.api.persistence.IPersistentFile;
import org.eclipse.dawnsci.analysis.api.processing.IExecutionVisitor;
import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.processing.OperationData;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceFromSeriesMetadata;
import org.eclipse.dawnsci.hdf.object.H5Utils;
import org.eclipse.dawnsci.hdf.object.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf.object.IHierarchicalDataFile;
import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.ShapeUtils;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;
import org.eclipse.january.metadata.AxesMetadata;
import org.eclipse.january.metadata.OriginMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated use NexusFileExectutionVisitor instead
 * 
 * Nexus writing implementation of IExecutionVisitor.
 * 
 * Handles writing 2D or 1D (and 0D auxiliary data) including axes to file. The name of the dataset in the file is
 * based on the dataset name.
 * 
 * Also writes all the operation model values to NXProcess
 * 
 * The resulting tree structure of the Nexus file saved will be the following:<br>
 * <pre>
 *     Entry name              |   Class        |    Description          
 * -----------------------------------------------------------------
 * entry                       |   NXentry      |                         
 * entry/result                |   NXdata       | contains data + axes
 * entry/process               |   NXprocess    | contains NXnotes of models
 * entry/intermediate          |   NXcollection | contains NXdatas of intermediate data
 * entry/auxiliary             |   NXcollection | contains NXdatas of auxiliary data
 * 
 * </pre>
 * 
 * @author vdp96513
 *
 */
@Deprecated
public class HierarchicalFileExecutionVisitor implements IExecutionVisitor {

	private static IPersistenceService service;
	public static void setPersistenceService(IPersistenceService s) { // Injected
		service = s;
	}
	public HierarchicalFileExecutionVisitor() {
		// Used for OSGI
	}

	private final String RESULTS_GROUP = "result";
	private final String INTER_GROUP = "intermediate";
	private final String AUX_GROUP = "auxiliary";
	private final String ENTRY = "entry";

	private Map<IOperation, AtomicBoolean> firstNotifyMap;
	private Map<IOperation, Integer> positionMap;
	private AtomicBoolean firstNonNullExecution = new AtomicBoolean(true);

	private String results;
	private String intermediate;
	private String auxiliary;

	private ConcurrentHashMap<String,ConcurrentHashMap<Integer, String[]>> groupAxesNames = new ConcurrentHashMap<String,ConcurrentHashMap<Integer,String[]>>();
	private String filePath;
	private IHierarchicalDataFile file;

	private final static Logger logger = LoggerFactory.getLogger(HierarchicalFileExecutionVisitor.class);


	public HierarchicalFileExecutionVisitor(String filePath) {
		this.filePath = filePath;
		firstNotifyMap = new ConcurrentHashMap<IOperation, AtomicBoolean>();
		positionMap = new ConcurrentHashMap<IOperation, Integer>();
	}

	@Override
	public void init(IOperation<? extends IOperationModel, ? extends OperationData>[] series, ILazyDataset data) throws Exception {

		for (int i = 0; i < series.length; i++) {
			firstNotifyMap.put(series[i], new AtomicBoolean(true));
			positionMap.put(series[i], i);
		}

		OriginMetadata origin = null;
		List<SliceFromSeriesMetadata> metadata = data.getMetadata(SliceFromSeriesMetadata.class);
		if (metadata != null && metadata.get(0) != null) origin = metadata.get(0);
		file = HierarchicalDataFactory.getWriter(filePath);
		try {
			// don't fail process because of error persisting models
			IPersistentFile pf = service.createPersistentFile(file);
			pf.setOperations(series);
			pf.setOperationDataOrigin(origin);
		} catch (Exception e){
			logger.error("Cant persist operations!", e);
		}

		initGroups();
		boolean groupCreated = false;
		for (int i = 0; i < series.length; i++) {
			if (series[i].isStoreOutput()) {
				if (!groupCreated){
					createInterGroup();
					groupCreated = true;
				}
				String group = file.group(i + "-" + series[i].getName(), intermediate);
				file.setNexusAttribute(group, "NXdata");
			}
		}

	}

	/**
	 * Makes entry and result NXdata
	 * @throws Exception
	 */
	private void initGroups() throws Exception {
		file.group(ENTRY);
		results = file.group(RESULTS_GROUP, ENTRY);
		file.setNexusAttribute(results, "NXdata");
	}

	/**
	 * Make the intermediate data NXcollection to store data from the middle of the pipeline
	 */
	private void createInterGroup() {
		try {
			intermediate = file.group(INTER_GROUP,ENTRY);
			file.setNexusAttribute(intermediate, "NXcollection");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Makes NXCollection to store the Auxiliary data from each operation (if supplied)
	 */
	private void createAuxGroup() {
		try {
			synchronized (file) {
				auxiliary = file.group(AUX_GROUP,ENTRY);
				file.setNexusAttribute(auxiliary, "NXcollection");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void executed(OperationData result, IMonitor monitor) throws Exception {
		if (result == null) return;
		//not threadsafe but closer
		boolean fNNE = firstNonNullExecution.getAndSet(false);
		
		//Write data to file
		final IDataset integrated = result.getData();
		SliceFromSeriesMetadata metadata = integrated.getMetadata(SliceFromSeriesMetadata.class).get(0);
		int[] dataDims = metadata.getDataDimensions();
		int[] shape = metadata.getSubSampledShape();
		Slice[] slices = metadata.getSliceInOutput();
		updateAxes(integrated, slices, shape, dataDims, results,fNNE);
		integrated.setName("data");
		appendData(integrated,results, slices,shape, file);
		if (fNNE){
			synchronized (file) {
				file.setAttribute(results +"/" +integrated.getName(), "signal", String.valueOf(1));
			}
		}
	}

	@Override
	public void notify(IOperation<? extends IOperationModel, ? extends OperationData> intermeadiateData, OperationData data) {
		//make groups on first pass

		if (!intermeadiateData.isStoreOutput() && (data.getAuxData() == null || data.getAuxData()[0] == null)) return;

		boolean first = firstNotifyMap.get(intermeadiateData).getAndSet(false);

		String position = String.valueOf(positionMap.get(intermeadiateData));

		SliceFromSeriesMetadata metadata;
		try {
			metadata = data.getData().getMetadata(SliceFromSeriesMetadata.class).get(0);
		} catch (Exception e) {
			logger.error("", "Cannot access series metadata, contact DAWN support");
			return;
		}


		int[] dataDims = metadata.getDataDimensions();
		int[] shape = metadata.getSubSampledShape();
		Slice[] slices = metadata.getSliceInOutput();

		//if specified to save data, do it
		if (intermeadiateData.isStoreOutput()) {
			try {
				String group = file.group(position + "-" + intermeadiateData.getName(), intermediate);				
				IDataset d = data.getData();
				d.setName("data");
				appendData(d,group, slices,shape, file);
				if (first){
					synchronized (file) {
						file.setAttribute(group +"/" +d.getName(), "signal", String.valueOf(1));
					}
				}
				updateAxes(d, slices, shape, dataDims, group,first);

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		Serializable[] auxData = data.getAuxData();

		//save aux data (should be IDataset, with unit dimensions)
		if (auxData != null && auxData[0] != null) {
			for (int i = 0; i < auxData.length; i++) {
				if (auxData[i] instanceof IDataset) {
					
					try {
						IDataset ds = (IDataset)auxData[i];
						String group = "";
						synchronized (file) {
							if (auxiliary == null) createAuxGroup();
							group = file.group(position + "-" + intermeadiateData.getName(), auxiliary);
							file.setNexusAttribute(group, "NXCollection");
							group = file.group(ds.getName(), group);
							file.setNexusAttribute(group, "NXdata");
						}
						
						ds.setName("data");
						appendData(ds,group, slices,shape, file);
						if (first){
							synchronized (file) {
								file.setAttribute(group +"/" +ds.getName(), "signal", String.valueOf(1));
							}
						}
						
						updateAxes(ds, slices, shape, dataDims, group, first);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
	}

	private void updateAxes(IDataset data, Slice[] oSlice, int[] oShape, int[] dataDims, String groupName, boolean first) throws Exception {
		
		ConcurrentHashMap<Integer, String[]> axesNames = new ConcurrentHashMap<Integer,String[]>();

		groupAxesNames.putIfAbsent(groupName, axesNames);
		
		axesNames = groupAxesNames.get(groupName);

		Set<Integer> setDims = new HashSet<Integer>(dataDims.length);
		for (int i = 0; i < dataDims.length; i++) {
			setDims.add(dataDims[i]);
		}

		int[] dsShape = data.getShape();
		if (dsShape.length > dataDims.length) {
			//1D to 2D
			setDims.add(dsShape.length-1);
		}

		List<AxesMetadata> mList = data.getMetadata(AxesMetadata.class);
		if (mList!= null && !mList.isEmpty()) {

			for (AxesMetadata am : mList) {
				ILazyDataset[] axes = am.getAxes();
				if (axes == null) return;

				int rank = axes.length;

				for (int i = 0; i< rank; i++) {
					ILazyDataset[] axis = am.getAxis(i);
					if (axis == null) continue;
					String[] names = new String[axis.length];
					for (int j = 0; j < axis.length; j++) {
						ILazyDataset ax = axis[j];
						if (ax == null) continue;
						String name = ax.getName();
						names[j] = santiziseName(name);
						axesNames.putIfAbsent(i, names);
						name = axesNames.get(i)[j];
						IDataset axDataset = ax.getSlice();
						axDataset.setName(name);

						if (setDims.contains(i)) {
							if(first) {
								ILazyDataset error = axDataset.getErrors();
								IDataset e = null;
								if (error != null) {
									e = error.getSlice().squeeze();
									e.setName(axDataset.getName() + "_errors");
								}
								
								synchronized (file) {
									String ds = file.createDataset(axDataset.getName(), axDataset.squeeze(), groupName);
									file.setAttribute(ds, "axis", String.valueOf(i+1));
									if (e != null) file.createDataset(e.getName(), e.squeeze(), groupName);
								}
							}
						} else {
							appendSingleValueAxis(axDataset,groupName, oSlice,oShape, file,i);
							if (first) {
								synchronized (file) {
									file.setAttribute(groupName +"/" +axDataset.getName(), "axis", String.valueOf(i+1));
								}
							}

							ILazyDataset error = axDataset.getErrors();

							if (error != null) {
								IDataset e = error.getSlice();
								e.setName(axDataset.getName() + "_errors");
								appendSingleValueAxis(e,groupName, oSlice,oShape, file,i);
							}
						}

					}
				}

			}
		}
	}
	
	private String santiziseName(String name) {
		//assume only our slicing puts [ in a axis name!
		if (name.contains("[")) {
			name = name.split("\\[")[0];
		}

		if (name.contains("/")) {
			name = name.replace("/", "_");
		}

		//sanitize - can't have an axis called data
		if (name.isEmpty() || name.equals("data")) {
			int n = 0;
			while(groupAxesNames.containsKey("axis" + n)) n++;

			name = "axis" +n;
		}
		
		return name;
	}

	private void appendSingleValueAxis(IDataset dataset, String group, Slice[] oSlice, int[] oShape, IHierarchicalDataFile file, int axisDim) throws Exception{
		dataset = dataset.getSliceView();
		dataset.setShape(1);
		H5Utils.insertDataset(file, group, dataset, new Slice[]{oSlice[axisDim]}, new long[]{oShape[axisDim]});
	}

	/**
	 * Write the data into the correct position, in the correct dataset
	 * @param dataset
	 * @param group
	 * @param oSlice
	 * @param oShape
	 * @param file
	 * @throws Exception
	 */
	private void appendData(IDataset dataset, String group, Slice[] oSlice, int[] oShape, IHierarchicalDataFile file) throws Exception {
		
		if (ShapeUtils.squeezeShape(dataset.getShape(), false).length == 0) {
			//padding slice and shape does not play nice with single values of rank != 0
			dataset = dataset.getSliceView().squeeze();
		}
		
		//determine the dimensions of the original data
		int[] dd = getNonSingularDimensions(oSlice, oShape);
		//update the slice to reflect the new data shape/rank
		Slice[] sliceOut = getUpdatedSliceArray( oShape, dataset.getShape(), oSlice, dd);
		//determine shape of full output dataset
		long[] newShape = getNewShape(oShape, dataset.getShape(), dd);
		
		if (dataset.getRank() == 0) {
			int[] shape = new int[newShape.length];
			Arrays.fill(shape, 1);
			dataset.setShape(shape);
		}
		
		//write
		H5Utils.insertDataset(file, group, dataset, sliceOut, newShape);

		ILazyDataset error = dataset.getErrors();

		if (error != null) {
			IDataset e = error.getSlice();
			e.setName("errors");
			H5Utils.insertDataset(file, group, e, sliceOut, newShape);
		}

		return;
	}

	@Override
	public void close() throws Exception {
		if (file != null) file.close();

	}

	/**
	 * Parse slice array to determine which dimensions are not equal to 1 and assume these are the data dimensions
	 * @param slices
	 * @param shape
	 * @return data dims
	 */
	private int[] getNonSingularDimensions(Slice[] slices, int[] shape) {
		int[] newShape = new SliceND(shape, slices).getShape();

		List<Integer> notOne = new ArrayList<Integer>();

		for (int i = 0; i < newShape.length; i++) if (newShape[i] != 1) notOne.add(i);

		int[] out = new int[notOne.size()];
		for (int i = 0; i < out.length; i++) {
			out[i] = notOne.get(i);
		}

		return out;
	}

	/**
	 * Get a new slice array which reflects the position of the processed data in the full output dataset
	 * @param oShape
	 * @param dsShape
	 * @param oSlice
	 * @param datadims
	 * @return newSlices
	 */
	private Slice[] getUpdatedSliceArray(int[] oShape, int[] dsShape, Slice[] oSlice, int[] datadims) {

		if (ShapeUtils.squeezeShape(dsShape, false).length == 0) {
			List<Slice> l = new LinkedList<Slice>(Arrays.asList(oSlice));
			for (int i =  datadims.length-1; i >= 0; i--) {
				l.remove(datadims[i]);
			}
			return l.toArray(new Slice[l.size()]);

		}

		Arrays.sort(datadims);
		Slice[] out = null;
		int dRank = oShape.length - dsShape.length;
		int[] s = oShape.clone();
		out = oSlice.clone();
		if (dRank == 0) {
			for (int i = 0; i < datadims.length; i++) {
				out[datadims[i]] = new Slice(0, dsShape[datadims[i]], 1);
				s[datadims[i]] = s[datadims[i]];
			}
		} else if (dRank > 0) {
			List<Slice> l = new LinkedList<Slice>(Arrays.asList(out));
			l.remove(datadims[datadims.length-1]);
			out = l.toArray(new Slice[l.size()]);
			out[datadims[0]] = new Slice(0, dsShape[datadims[0]], 1);
		} else if (dRank < 0) {
			for (int i = 0; i < datadims.length; i++) {
				out[datadims[i]] = new Slice(0, dsShape[datadims[i]], 1);
				s[datadims[i]] = s[datadims[i]];
			}

			List<Slice> l = new LinkedList<Slice>(Arrays.asList(out));
			l.add(new Slice(0, dsShape[dsShape.length-1], 1));
			out = l.toArray(new Slice[l.size()]);
		}

		return out;
	}

	/**
	 * Get the expected final shape of the output dataset
	 * @param oShape
	 * @param dsShape
	 * @param dd
	 * @return newShape
	 */
	private long[] getNewShape(int[]oShape, int[] dsShape, int[] dd) {

		if (ShapeUtils.squeezeShape(dsShape, false).length == 0) {
			List<Integer> l = new LinkedList<Integer>();
			for (int i : oShape) l.add(i);
			for (int i =  dd.length-1; i >= 0; i--) {
				l.remove(dd[i]);
			}
			long[] out = new long[l.size()];
			for (int i = 0; i< l.size(); i++) out[i] = l.get(i);
			return out;

		}

		int dRank = oShape.length - dsShape.length;
		long[] out = null;

		if (dRank == 0) {
			out = new long[oShape.length];
			for (int i = 0; i < oShape.length; i++) out[i] = oShape[i];
			for (int i : dd) {
				out[i] = dsShape[i]; 
			}
		} else if (dRank > 0) {
			List<Integer> ll = new LinkedList<Integer>();
			for (int i : oShape) ll.add(i);
			for (int i = 0; i < dd.length ; i++) if (i < dRank) ll.remove(dd[i]);
			for (int i = 0;  i < dRank; i++) ll.set(dd[i], dsShape[dd[i]]);
			out = new long[ll.size()];
			for (int i = 0; i< ll.size(); i++) out[i] = ll.get(i);

		} else if (dRank < 0) {
			List<Integer> ll = new LinkedList<Integer>();
			for (int i : oShape) ll.add(i);
			for (int i = 0; i < dd.length ; i++) ll.set(dd[i], dsShape[dd[i]]);
			for (int i = dRank;  i < 0; i++){
				ll.add(dsShape[dsShape.length+i]);
			}
			out = new long[ll.size()];
			for (int i = 0; i< ll.size(); i++) out[i] = ll.get(i);

		}

		return out;
	}
	
}
