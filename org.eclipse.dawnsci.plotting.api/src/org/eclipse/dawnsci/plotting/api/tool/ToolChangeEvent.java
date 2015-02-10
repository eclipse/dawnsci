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
package org.eclipse.dawnsci.plotting.api.tool;

import java.util.EventObject;

import org.eclipse.ui.IWorkbenchPart;

public class ToolChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8805347445621628990L;

	private IToolPage oldPage;
	private IToolPage newPage;
	private IWorkbenchPart part;

	public ToolChangeEvent(Object source, IToolPage oldPage, IToolPage newPage, IWorkbenchPart part) {
		super(source);
		this.oldPage = oldPage;
		this.newPage = newPage;
		this.part    = part;
	}

	public IToolPage getOldPage() {
		return oldPage;
	}

	public IToolPage getNewPage() {
		return newPage;
	}

	public IWorkbenchPart getPart() {
		return part;
	}
}
