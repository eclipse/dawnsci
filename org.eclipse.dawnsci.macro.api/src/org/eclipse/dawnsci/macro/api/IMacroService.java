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
 * A class for coordinating events that have commands which
 * can be recorded in scripting.
 * 
 * You can publish a macro event, which if any console is active will
 * get inserted into the current script as an action which happened.
 * 
 * You can listen to events and do the insertion to the script. These events
 * can then be replayed.
 * 
 * @author fcp94556
 *
 */
public interface IMacroService {

	/**
	 * Notify any listeners which are recording macros that 
	 * they should append some instructions to their macro line.
	 * 
	 * @param evt
	 */
	public void publish(MacroEventObject evt);
	
	/**
	 * Add a listener which will append commands when they come in.
	 * NOTE all listeners should be removed from the service.
	 * 
	 * @param l
	 */
	public void addMacroListener(IMacroEventListener l);
	
	/**
	 * Remove a listener which will append commands when they come in.
	 * NOTE all listeners should be removed from the service.
	 * 
	 * @param l
	 */
	public void removeMacroListener(IMacroEventListener l);


	/**
	 * Get the macro command generator for a given class. This generator
	 * can return the macro commands for a given object, for instance a
	 * dataset or region.
	 * 
	 * @param clazz
	 * @return
	 */
	public AbstractMacroGenerator getGenerator(Class<? extends Object> clazz);
}
