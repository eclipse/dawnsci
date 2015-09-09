package org.eclipse.dawnsci.plotting.api;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dawnsci.plotting.api.tool.IToolPageSystem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * A view with a plotting system on it and connected to python.
 * 
 * You may use this class to replace plot view because it has fewer dependencies
 * as the whole of analysis.rcp is not required.
 * 
 * @author fcp94556
 *
 */
public class VanillaPlottingSystemView extends ViewPart implements IAdaptable {
	
	protected IPlottingSystem     system;

	@Override
	public void createPartControl(Composite parent) {
		
		try {
		    system = PlottingFactory.createPlottingSystem(); // TODO Change to service
		    system.createPlotPart(parent, getPartName(), getViewSite().getActionBars(), PlotType.IMAGE, this);
		    		    
		} catch (Exception ne) {
			throw new RuntimeException(ne); // Lazy
		}
		
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		if (IPlottingSystem.class == adapter) return system;
		if (IToolPageSystem.class == adapter) return system.getAdapter(adapter);
		return super.getAdapter(adapter);
	}

	@Override
	public void setFocus() {
		system.setFocus();
	}

	@Override
	public void dispose() {
		system.dispose();
		super.dispose();
	}
	
}
