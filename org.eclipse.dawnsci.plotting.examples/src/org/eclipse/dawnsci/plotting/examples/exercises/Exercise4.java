/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.examples.exercises;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.region.IROIListener;
import org.eclipse.dawnsci.plotting.api.region.IRegion;
import org.eclipse.dawnsci.plotting.api.region.IRegion.RegionType;
import org.eclipse.dawnsci.plotting.api.region.IRegionListener;
import org.eclipse.dawnsci.plotting.api.region.ROIEvent;
import org.eclipse.dawnsci.plotting.api.region.RegionEvent;
import org.eclipse.dawnsci.plotting.api.tool.AbstractToolPage;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.examples.Examples;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class Exercise4 extends AbstractToolPage implements IROIListener {
	
	/**
	 * This is a local plotting system to plot the masked result.
	 */
	private IPlottingSystem regionView;
	
	private IRegionListener regionListener;
	
	public Exercise4() {
		try {
		    regionView  = Examples.getCurrent().getPlottingService().createPlottingSystem();
		} catch (Exception ne) {
			ne.printStackTrace();
		}
		
		regionListener = new IRegionListener.Stub() {
			@Override
			public void regionAdded(RegionEvent evt) {
				evt.getRegion().addROIListener(Exercise4.this);
			}

			@Override
			public void regionRemoved(RegionEvent evt) {
				evt.getRegion().removeROIListener(Exercise4.this);
			}
		};
	}

	@Override
	public ToolPageRole getToolPageRole() {
		return ToolPageRole.ROLE_2D;
	}

	@Override
	public void createControl(Composite parent) {
		regionView.createPlotPart(parent, "Region Mask", getSite().getActionBars(), PlotType.IMAGE, getPart());
	}
	
	@Override
    public void activate() {
		super.activate();
		regionView.addRegionListener(regionListener);
		for (IRegion region : regionView.getRegions()) {
			region.addROIListener(this);
		}
		
		// We move the move to adding a region as soon as the tool is activated (not ideal)
		try {
			getPlottingSystem().createRegion("Threshold Mask Box", RegionType.BOX);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void deactivate() {
		super.deactivate();
		regionView.removeRegionListener(regionListener);
		for (IRegion region : regionView.getRegions()) {
			region.removeROIListener(this);
		}
		// We move the move to adding a region as soon as the tool is activated (not ideal)
		getPlottingSystem().clearRegionTool();
	}

	@Override
	public Control getControl() {
		return regionView.getPlotComposite();
	}

	@Override
	public void setFocus() {
		regionView.setFocus();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		regionView.dispose();
	}

	@Override
	public void roiDragged(ROIEvent evt) {
		updateRegionView(evt);
	}

	@Override
	public void roiChanged(ROIEvent evt) {
		updateRegionView(evt);
	}

	@Override
	public void roiSelected(ROIEvent evt) {
		updateRegionView(evt);
	}

	private void updateRegionView(ROIEvent evt) {
		
		IROI roi = evt.getROI();
		if (roi instanceof RectangularROI) {
			
			RectangularROI box = (RectangularROI)roi;
			IImageTrace trace  = getImageTrace();
			IDataset    data   = trace.getData();
			
			int start0 = (int)Math.round(box.getPoint()[0]);
			int end0   = (int)Math.round(box.getEndPoint()[0]);
			int start1 = (int)Math.round(box.getPoint()[1]);
			int end1   = (int)Math.round(box.getEndPoint()[1]);
			
			IDataset    slice  = data.getSliceView(new Slice(start0, end0), new Slice(start1, end1));
		}
	}

}
