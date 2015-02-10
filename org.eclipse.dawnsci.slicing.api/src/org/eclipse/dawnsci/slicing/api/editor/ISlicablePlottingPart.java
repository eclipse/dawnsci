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
package org.eclipse.dawnsci.slicing.api.editor;

import org.eclipse.dawnsci.slicing.api.data.ITransferableDataObject;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A workbench part which does plots and can participate in slicing.
 * 
 * @author Matthew Gerring
 *
 */
public interface ISlicablePlottingPart extends IWorkbenchPart {

	/**
	 * Update the plot with checkables selected by the user.
	 * @param selections
	 * @param participant
	 * @param useTask
	 */
	void updatePlot(final ITransferableDataObject[] selections, 
		            final ISliceSystem              sliceSystem,
		            final boolean                   useTask);

}
