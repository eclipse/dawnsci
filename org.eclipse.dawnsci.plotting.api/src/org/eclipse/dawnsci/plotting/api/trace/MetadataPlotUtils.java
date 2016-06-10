/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.trace;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.DatasetException;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.metadata.AxesMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.MaskMetadata;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class MetadataPlotUtils {

	
	public static void plotDataWithMetadata(IDataset data, final IPlottingSystem<?> system) {
		plotDataWithMetadata(data, system, true);
	}
	
	public static void plotDataWithMetadata(IDataset data, final IPlottingSystem<?> system, boolean clear) {
		
		IDataset x = null;
		IDataset y = null;
		IDataset mask = null;
		
		data = data.getSliceView().squeeze();
		
		IDataset[] axes = getAxesFromMetadata(data);
		
		MaskMetadata mmd = data.getFirstMetadata(MaskMetadata.class);
		
		if (mmd != null ) {
			mask = mmd.getMask().getSlice().squeeze();
		}
		
		if (data.getRank() == 2) {
			if (!system.is2D()) system.clear();
			x = axes == null ? null : axes[0];
			y = axes == null ? null : axes[1];
			
			if (x != null && x.getRank() == 2) {
				x = x.getSlice((Slice)null,new Slice(0,1)).squeeze();
			}
			if (y != null && y.getRank() == 2) {
				y = y.getSlice(new Slice(0,1),(Slice)null).squeeze();
			}
			
			
			final ITrace t = system.updatePlot2D(data, Arrays.asList(new IDataset[]{y,x}), null);
				
			final IDataset m = mask;

			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					if (t == null) return;
					((IImageTrace)t).setMask(m);
					if (!system.isDisposed())system.repaint();
				}
			});
				
			
		} else if (data.getRank() == 1) {
			x = axes == null ? null : axes[0];
			if (clear) system.clear();
			system.updatePlot1D(x,Arrays.asList(new IDataset[]{data}),null);
		}
		
	}
	

	
	public static IImageTrace buildTrace(String name, IDataset data, IPlottingSystem<Composite> system) {
		return buildTrace(name, data, system,-1);
	
	}
	
	public static IImageTrace buildTrace(IDataset data, IPlottingSystem<Composite> system) {
		return buildTrace(data.getName(), data, system,-1);
	
	}
	
	public static IImageTrace buildTrace(String name, IDataset data, IPlottingSystem<Composite> system, int alpha) {
		IDataset x = null;
		IDataset y = null;
		
		
		IDataset[] axes = getAxesFromMetadata(data, false);
		
		x = axes == null ? null : axes[0];
		y = axes == null ? null : axes[1];
		
		if (x != null) x.setName(removeSquareBrackets(x.getName()));
		if (y != null) y.setName(removeSquareBrackets(y.getName()));
		
		IImageTrace it = system.createImageTrace(name);
		it.setAlpha(alpha);
		it.setData(data, Arrays.asList(new IDataset[]{y,x}), false);
		
		return it;
	
	}
	
	public static void switchData(String name, IDataset data, IImageTrace trace) {
		IDataset x = null;
		IDataset y = null;
		
		IDataset[] axes = getAxesFromMetadata(data, false);
		
		x = axes == null ? null : axes[0];
		y = axes == null ? null : axes[1];
		
		if (x != null) x.setName(removeSquareBrackets(x.getName()));
		if (y != null) y.setName(removeSquareBrackets(y.getName()));
		
		trace.setData(data, Arrays.asList(new IDataset[]{y,x}), false);
		trace.setDataName(name);
		
	}
	
	
	public static ILineTrace buildLineTrace(IDataset data, IPlottingSystem<Composite> system) {
		IDataset x = null;

		
		data = data.getSliceView().squeeze();
		
		IDataset[] axes = getAxesFromMetadata(data);
		
		x = axes == null ? null : axes[0];

		ILineTrace it = system.createLineTrace(data.getName());
		it.setData(x, data);
		
		return it;
	
	}
	
	public static IDataset[] getAxesForDimension(ILazyDataset data, int dim) {
		
		List<AxesMetadata> amd = null;
		
		data = data.getSliceView().squeezeEnds();
		
		try {
			amd = data.getMetadata(AxesMetadata.class);
		} catch (Exception e) {
			return new IDataset[0];
		}
		
		
		if (amd != null && !amd.isEmpty()) {
			AxesMetadata am = amd.get(0);
			ILazyDataset[] axis = am.getAxis(dim);
			IDataset[] out = new IDataset[axis.length];
			for (int i = 0; i < out.length; i++) {
				try {
					out[i] = axis[i] == null ? null : axis[i].getSlice();
				} catch (DatasetException e) {
				}
			}
			
			return out;
		}
		return null;

	}
	
	public static IDataset[] getAxesFromMetadata(IDataset data) {
		return getAxesFromMetadata(data,true);
	}
	
	public static IDataset[] getAxesFromMetadata(IDataset data, boolean squeeze) {
		if (squeeze) data = data.getSliceView().squeeze();
		return getAxesFromMetadata((ILazyDataset)data);
	}
	
	
	public static IDataset[] getAxesFromMetadata(ILazyDataset data) {
		IDataset x = null;
		IDataset y = null;
		
		data = data.getSliceView();
		
		List<AxesMetadata> amd = null;
		try {
			amd = data.getMetadata(AxesMetadata.class);
		} catch (Exception e) {
			return new IDataset[0];
		}
		
		
		if (amd != null && !amd.isEmpty()) {
			AxesMetadata am = amd.get(0);
			ILazyDataset[] axes = am.getAxes();
			ILazyDataset lz0 = axes[0];
			ILazyDataset lz1 = null;
			IDataset[] out;
			if (data.getRank() > 1) {
				out = new IDataset[2];
				lz1 = axes[1];
			}else {
				out= new IDataset[1];
			}
			
			if (lz0 != null){
//				lz0.clearMetadata(null);
				try {
					x = lz0.getSlice().squeeze();
					out[0] = x;
				} catch (DatasetException e) {
				}
			}
			if (lz1 != null) {
//				lz1.clearMetadata(null);
				try {
					y = lz1.getSlice().squeeze();
					out[1] = y;
				} catch (DatasetException e) {
				}
			}
			
			return out;
		}
		return null;
	}
	
	public static String removeSquareBrackets(String string) {
		if (string == null) return null;
		return string.replaceAll("\\[(.+?)\\]$", "");
	}
}
