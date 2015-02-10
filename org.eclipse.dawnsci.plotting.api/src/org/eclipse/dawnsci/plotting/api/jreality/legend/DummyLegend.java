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

package org.eclipse.dawnsci.plotting.api.jreality.legend;

import org.eclipse.dawnsci.plotting.api.jreality.impl.Plot1DGraphTable;
import org.eclipse.swt.widgets.Composite;

/**
 *
 */
public class DummyLegend extends LegendComponent {

	/**
	 * @param parent
	 * @param style
	 */
	public DummyLegend(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void updateTable(Plot1DGraphTable table) {
		// TODO Auto-generated method stub

	}

}
