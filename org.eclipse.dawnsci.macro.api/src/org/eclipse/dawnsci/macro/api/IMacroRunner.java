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
 * Interface for running macros in the workbench. Normally the runner of the macros
 * is DAWN and the caller is an interpreter like Jython or Python.
 * 
 * The runner creates a local jython interpreter to accept the commands from the external
 * interpreter and execute them in the workbench.
 * 
 * For instance the external interpreter may wish to send a command to set the title of a plot.
 * 
 * Generally the macro runner is published on RMI.
 * 
 * @author Matthew Gerring
 *
 */
public interface IMacroRunner {

	/**
	 * Execute a string command
	 * @param s
	 * @throws Exception 
	 */
	void exec(String s) throws Exception;

	
}
