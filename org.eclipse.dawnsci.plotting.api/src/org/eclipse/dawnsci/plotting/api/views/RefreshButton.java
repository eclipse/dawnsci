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
package org.eclipse.dawnsci.plotting.api.views;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * IMPORTANT do not remove this funny class it is used to 
 * give a widget with the same classloader as plugin org.eclipse.dawnsci.plotting.api
 * in order to provide functionality for the squish tests!
 * @author Matthew Gerring
 *
 */
public class RefreshButton extends Button {

	public RefreshButton(Composite parent, int style) {
		super(parent, style);
		setText("Refresh");
	}

	protected void checkSubclass () {
		// We allow this one on purpose.
	}
}
