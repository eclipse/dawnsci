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

package org.eclipse.dawnsci.analysis.dataset.operations;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
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
import org.eclipse.dawnsci.analysis.api.processing.OperationRank;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceFromSeriesMetadata;

/**
 * Abstract implementation of IOperation.
 * 
 * Simplest method to add a new operation is to extend this class and override the process method, which receives either 1D or 2D
 * datasets and should return either 2D or 1D datasets with correctly configured axesmetadata, and single value datasets of shape [1]
 * as auxiliary data.
 * 
 * Overriding execute gives access to the unsqueezed data (rank the same as the initial dataset being processed) and all axes items,
 * but care must be taken to maintain the size 1 dimensions and axes, which the process method does for you.
 * 
 * Should return OperationData unless there is a very good reason to extend it.
 *
 * @param <T>
 * @param <D>
 */
public abstract class AbstractOperation<T extends IOperationModel, D extends OperationData> extends AbstractOperationBase<T, D> {


	protected abstract OperationData process(IDataset input, IMonitor monitor) throws OperationException;
	
	@Override
	public D execute(IDataset slice, IMonitor monitor) throws OperationException {
		
		IDataset view = slice.getSliceView().squeeze();
		
		//check mandatory metadata
		SliceFromSeriesMetadata ssm = getSliceSeriesMetadata(view);
		
		if (ssm == null) throw new OperationException(this, "Metadata not present, contact support");
		
		OperationData output = process(view,monitor);
		
		if (output == null) return null;
		
		return updateOutputToFullRank(output, slice);	
	}
	
	/**
	 * Pads the output dataset (and axes) to the match the rank of the input dataset, accouting for any loss of dimensionality
	 * in the process. Suitable only for 2D and 1D datasets
	 * 
	 * @param output
	 * @param original
	 * @return <D>
	 * @throws OperationException
	 */
	private D updateOutputToFullRank(OperationData output, IDataset original) throws OperationException {
		
		int outr = output.getData().getRank();
		int inr = original.getRank();
		
		//Check ranks acceptable for this step
		if (getOutputRank().equals(OperationRank.ZERO) || getOutputRank().equals(OperationRank.NONE) || getOutputRank().getRank() > 2) throw new OperationException(null, "Invalid Operation Rank!");
		
		int rankDif = 0;
		
		if (!getOutputRank().equals(OperationRank.SAME)) {
			
			rankDif = getInputRank().getRank() - getOutputRank().getRank();
		}
		
		//Single image/line case, nothing to alter
		if (inr == outr) return (D)output;
		
		List<AxesMetadata> metadata = null;
		
		try {
			metadata = original.getMetadata(AxesMetadata.class);
		} catch (Exception e) {
			throw new OperationException(this, e);
		}
		
		//Clone and sort dimensions for searching
		int[] datadims = getOriginalDataDimensions(original).clone();
		Arrays.sort(datadims);
		
		//Update rank of dataset (will automatically update rank of axes)
		updateOutputDataShape(output.getData(), inr-rankDif, datadims, rankDif);
		updateAxes(output.getData(),original,metadata,rankDif, datadims);
		updateAuxData(output.getAuxData(), original);
		
		return (D)output;
	}
	
	/**
	 * Update the rank of the output data, and its axes to be consistent with the input
	 * 
	 * @param out
	 * @param rank
	 * @param dataDims
	 * @param rankDif
	 */
	private void updateOutputDataShape(IDataset out, int rank, int[] dataDims, int rankDif) {
		int[] shape = out.getSliceView().squeeze().getShape();
		
		int[] updated = new int[rank];
		Arrays.fill(updated, 1);
		
		if (rankDif == 0) {
			//1D-1D or 2D - 2D
			for (int i = 0; i< shape.length; i++) updated[dataDims[i]] = shape[i]; 
		} else if ( rankDif > 0) {
			//1D || 0D
			if (shape.length != 0) updated[dataDims[0]] = shape[0];
		} else if (rankDif < 0) {
			//2D from 1D
			updated[dataDims[0]] = shape[0];
			updated[updated.length-1] = shape[1];
		}
		
		for (int i = 0 ; i < Math.min(dataDims.length,shape.length); i++) {
			updated[dataDims[i]] = shape[i]; 
		}
		
		out.setShape(updated);
		
		
	}
	
	/**
	 * Add missing axes to the updated rank axes metadata
	 * 
	 * @param output
	 * @param original
	 * @param ometadata
	 * @param rankDif
	 * @param datadims
	 */
	private void updateAxes(IDataset output, IDataset original, List<AxesMetadata> ometadata, int rankDif, int[] datadims) {
		if (ometadata != null && !ometadata.isEmpty() && ometadata.get(0) != null) {
			List<AxesMetadata> metaout = null;
			
			//update it all for new data;
			try {
				metaout = output.getMetadata(AxesMetadata.class);
			} catch (Exception e) {
				throw new OperationException(this, e);
			}
			
			AxesMetadata inMeta = ometadata.get(0);
			
			AxesMetadata axOut = null;
			if (metaout != null && !metaout.isEmpty()) axOut = metaout.get(0);
			if (axOut == null) axOut = inMeta.createAxesMetadata(original.getRank() - rankDif);
			
			//Clone to get copies of lazy datasets
			AxesMetadata cloneMeta = (AxesMetadata) inMeta.clone();
			
			if (rankDif == 0) {
				
				for (int i = 0; i< original.getRank(); i++) {
					if (Arrays.binarySearch(datadims, i) < 0) {
						ILazyDataset[] axis = cloneMeta.getAxis(i);
						if (axis != null) axOut.setAxis(i, cloneMeta.getAxis(i));
					}
				}
				
			} else {
				int j = 0;
				int[] shape = new int[output.getRank()];
				Arrays.fill(shape, 1);
				
				for (int i = 0; i< original.getRank(); i++) {
					if (Arrays.binarySearch(datadims, i) < 0) {
						ILazyDataset[] axis = cloneMeta.getAxis(i);
						if (axis != null) {
							for (ILazyDataset ax : axis) if (ax != null) ax.setShape(shape); 
							axOut.setAxis(i+j, cloneMeta.getAxis(i));
						}
						
					} else {
						j--;
					}
				}
			} 
			output.setMetadata(axOut);
		}
	}
	
	/**
	 * Updates the auxiliary data (of shape [1]) to correct rank and adds all axes of rank zero (when squeezed)
	 * @param auxData
	 * @param original
	 */
	private void updateAuxData(Serializable[] auxData, IDataset original){
		
		if (auxData == null || auxData[0] == null) return;
		
		List<AxesMetadata> metadata = null;
		
		try {
			metadata = original.getMetadata(AxesMetadata.class);
		} catch (Exception e) {
			throw new OperationException(this, e);
		}
		
		for (int i = 0; i < auxData.length; i++) {
			if (!(auxData[i] instanceof IDataset)) {
				continue;
			}
			
			IDataset ds = (IDataset)auxData[i];
			
			int outr = ds.getRank();
			int inr = original.getRank();
			
			int rankDif = 0;
			
			if (!getOutputRank().equals(OperationRank.SAME)) {
				rankDif = getInputRank().getRank() - outr;
			}
			
			int[] datadims = getOriginalDataDimensions(original).clone();
			
			if (datadims.length > outr) {
				datadims = new int[]{datadims[0]};
			}
			Arrays.sort(datadims);
			
			updateOutputDataShape(ds, inr-rankDif, datadims, rankDif);
			updateAxes(ds,original,metadata,rankDif, datadims);
			
		}
	}

}
