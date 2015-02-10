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
package org.eclipse.dawnsci.macro.api;

/**
 * A generic MacroEventObject to represent deleting an object
 * in python, setting it to None.
 * 
 * @author fcp94556
 *
 */
public class DeleteEventObject extends MacroEventObject {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9140611001543637314L;

	public DeleteEventObject(Object source, String name) {
		super(source);
		String cmd = MacroUtils.getLegalName(name)+" = None";
		setPythonCommand(cmd);
	}
	
	/**
	 * Override to stop the python in the command being auto-generated.
	 * @return
	 */
    public boolean isGeneratable() {
    	return false;
    }

}
