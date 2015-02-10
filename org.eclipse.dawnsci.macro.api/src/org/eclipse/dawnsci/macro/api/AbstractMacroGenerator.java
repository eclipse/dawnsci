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


public abstract class AbstractMacroGenerator<T> {

	/**
	 * Looks at the class of the source of this event 
	 * and tries to see if there is a standard macro.
	 * 
	 * Basically sets the python and jython commands on the event
	 * if the source generates commands.
	 * 
	 * @param evt
	 * @return
	 */
	public MacroEventObject generate(MacroEventObject evt) {
		
        final Object source = evt.getSource();        
        String cmd = getPythonCommand((T)source);
        if (cmd!=null) evt.setPythonCommand(cmd);
        
        cmd = getJythonCommand((T)source);
        if (cmd!=null) evt.setJythonCommand(cmd);
       
        return evt;
	}
	
	/**
	 * Get the specific python command for an object or null if one cannot be generated.
	 * @param source
	 * @return
	 */
	public abstract String getPythonCommand(T source);
	
	/**
	 * Get the specific jython command for an object or null if one cannot be generated.
	 * @param source
	 * @return
	 */
	public abstract String getJythonCommand(T source);
	

}
