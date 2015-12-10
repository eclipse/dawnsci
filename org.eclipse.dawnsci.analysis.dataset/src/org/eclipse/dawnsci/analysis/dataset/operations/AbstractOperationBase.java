/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.operations;

import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.metadata.AxesMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.ErrorMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.IDiffractionMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.MaskMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.MetadataType;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.processing.OperationData;
import org.eclipse.dawnsci.analysis.api.processing.OperationException;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceFromSeriesMetadata;

public abstract class AbstractOperationBase<T extends IOperationModel, D extends OperationData> implements IOperation<T, D> {

	protected T model;

	private String            name;
	private String            description;
	
	private boolean storeOutput = false;
	private boolean passUnmodifiedData = false;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		if (description == null) return getId();
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public abstract D execute(IDataset slice, IMonitor monitor) throws OperationException;
	
	
	@Override
	public void init() {
		//do nothing
	}
	
	@Override
	public void dispose() {
		//do nothing
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (passUnmodifiedData ? 1231 : 1237);
		result = prime * result + (storeOutput ? 1231 : 1237);
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
		AbstractOperationBase<?, ?> other = (AbstractOperationBase<?, ?>) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passUnmodifiedData != other.passUnmodifiedData)
			return false;
		if (storeOutput != other.storeOutput)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractOperation [name=" + name + "]";
	}

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setModel(T model) {
		this.model = model;
	}

	/**
	 * Convenience method to get first set of axes from the Datasets metadata, can return null
	 * @param slice
	 * @return axes
	 */
	public static ILazyDataset[] getFirstAxes(IDataset slice) {
		List<AxesMetadata> metaList = null;

		try {
			metaList = slice.getMetadata(AxesMetadata.class);
			if (metaList == null || metaList.isEmpty())
				return null;
		} catch (Exception e) {
			return null;
		}

		AxesMetadata am = metaList.get(0);
		if (am == null)
			return null;

		return am.getAxes();
	}

	/**
	 * Convenience method to get first mask from the Datasets metadata, can return null
	 * @param slice
	 * @return mask
	 */
	public static ILazyDataset getFirstMask(IDataset slice) {

		List<MaskMetadata> metaList = null;

		try {
			metaList = slice.getMetadata(MaskMetadata.class);
			if (metaList == null || metaList.isEmpty())
				return null;
		} catch (Exception e) {
			return null;
		}

		MaskMetadata mm = metaList.get(0);
		if (mm == null)
			return null;

		return mm.getMask();
	}

	/**
	 * Convenience method to get first diffraction metadata from the Dataset, can return null
	 * @param slice
	 * @return dm
	 */
	public static IDiffractionMetadata getFirstDiffractionMetadata(IDataset slice) {

		List<IMetadata> metaList;

		try {
			metaList = slice.getMetadata(IMetadata.class);
			if (metaList == null || metaList.isEmpty())
				return null;
		} catch (Exception e) {
			return null;
		}

		for (IMetadata meta : metaList)
			if (meta instanceof IDiffractionMetadata)
				return (IDiffractionMetadata) meta;

		return null;
	}

	/**
	 * Convenience method to get the data dimensions of the original Dataset, can return null, but really should never happen
	 * @param slice
	 * @return datadims
	 */
	public static int[] getOriginalDataDimensions(IDataset slice) {

		SliceFromSeriesMetadata ssm = getSliceSeriesMetadata(slice);

		return ssm == null ? null : ssm.getDataDimensions();

	}
	
	public static SliceFromSeriesMetadata getSliceSeriesMetadata(IDataset slice) {
		
		List<SliceFromSeriesMetadata> metaList = null;

		try {
			metaList = slice.getMetadata(SliceFromSeriesMetadata.class);
			if (metaList == null || metaList.isEmpty())
				return null;
		} catch (Exception e) {
			return null;
		}

		SliceFromSeriesMetadata sm = metaList.get(0);
		if (sm == null)
			return null;
		
		return sm;
	}
	
	
	/**
	 * Get the absolute of the current slice in the view of the parent (starts at 0)
	 * @param origin
	 * @return int
	 */
//	public static int getCurrentSliceNumber(OriginMetadata origin) {
//		
//		int[] dd = origin.getDataDimensions();
//		dd = dd.clone();
//		Arrays.sort(dd);
//		//have to take a view, can't use check slice on AbstractDataset here...
//		ILazyDataset view = origin.getParent().getSliceView(origin.getInitialSlice());
//		int[] shape = view.getShape();
//		Slice[] current = origin.getCurrentSlice();
//		
//		int[] ddsh = new int[shape.length-dd.length];
//		int[] ddpos = new int[shape.length-dd.length];
//		
//		for (int i = 0, j = 0; i < shape.length; i++) {
//			if (Arrays.binarySearch(dd, i) < 0) {
//				ddsh[i-j] = shape[i];
//				ddpos[i-j] = current[i].getStart();
//			} else {
//				j++;
//			}
//		}
//		
//		int c = ddpos[ddpos.length-1];
//		for (int i = ddsh.length-2; i >-1; i--) {
//			int d = ddpos[i];
//			for (int j = i+1; j < ddsh.length; j++) {
//				d *= ddsh[j];
//			}
//			c+=d;
//		}
//		
//		return c;
//	}

	/**
	 * Convenience method to copy the metadata from one dataset to another.
	 * Use if a process doesnt change the shape of the data to maintain axes, masks etc
	 * 
	 * @param original
	 * @param out
	 */
	public void copyMetadata(IDataset original, IDataset out) {
		try {
			List<MetadataType> metadata = original.getMetadata(null);

			for (MetadataType m : metadata) {
				if (m instanceof ErrorMetadata) continue;
				out.setMetadata(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setStoreOutput(boolean storeOutput) {
		this.storeOutput = storeOutput;
	}
	
	@Override
	public boolean isStoreOutput() {
		return storeOutput;
	}
	
	@Override
	public void setPassUnmodifiedData(boolean passUnmodifiedData) {
		this.passUnmodifiedData = passUnmodifiedData;
	}

	@Override
	public boolean isPassUnmodifiedData() {
		return passUnmodifiedData;
	}
	
	public Class<? extends IOperationModel> getModelClass() {
		return (Class<? extends IOperationModel>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public static class OperationComparitor implements Comparator<IOperation<? extends IOperationModel, ? extends OperationData>> {

		@Override
		public int compare(IOperation<? extends IOperationModel, ? extends OperationData> arg0, IOperation<? extends IOperationModel, ? extends OperationData> arg1) {
			if (arg0==null) return -1;
			if (arg1==null) return 0;
			String a = arg0.getName();
			String b = arg1.getName();
			if (a==null) return -1;
			return a.compareTo(b);
		}

	}

}
