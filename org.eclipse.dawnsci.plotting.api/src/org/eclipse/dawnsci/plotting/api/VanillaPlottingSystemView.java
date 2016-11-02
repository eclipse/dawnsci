package org.eclipse.dawnsci.plotting.api;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dawnsci.plotting.api.tool.IToolPageSystem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A view with a plotting system on it and connected to python.
 * 
 * You may use this class to replace plot view because it has fewer dependencies
 * as the whole of analysis.rcp is not required.
 * 
 * @author Matthew Gerring
 *
 */
public class VanillaPlottingSystemView extends ViewPart implements IAdaptable {
	
	private static final Logger logger = LoggerFactory.getLogger(VanillaPlottingSystemView.class);
	
	protected IPlottingSystem<Composite>     system;

	public VanillaPlottingSystemView() {
		try {
			system = PlottingFactory.createPlottingSystem(); // TODO Change to service

		} catch (Exception ne) {
			logger.error("Unable to make plotting system", ne);
			system = null; // It creates the view but there will be no plotting system 
		}

	}

	@Override
	public void createPartControl(Composite parent) {

		system.createPlotPart(parent, getPartName(), getViewSite().getActionBars(), PlotType.IMAGE, this);  

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		if (IPlottingSystem.class == adapter) return system;
		if (IToolPageSystem.class == adapter) return system.getAdapter(adapter);
		return super.getAdapter(adapter);
	}

	@Override
	public void setFocus() {
		if (system!=null) system.setFocus();
	}

	@Override
	public void dispose() {
		if (system!=null) system.dispose();
		super.dispose();
	}
	
}
