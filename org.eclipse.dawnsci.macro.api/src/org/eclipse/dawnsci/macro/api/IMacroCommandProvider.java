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
 * Implement this interface and then fire events using the IMacroService.publish(..)
 * to allow pydev consoles and script files to record your actions when using the GUI.
 * 
 * @author fcp94556
 *
 */
public interface IMacroCommandProvider {

	/**
	 * Implement to provide the python to do this event in 
	 * python console or script.
	 * @return
	 */
	public String getPythonCommand();
	
	/**
	 * Implement to provide the jython to do this event 
	 * in jython console or script.
	 * @return
	 */
	public String getJythonCommand();
}
