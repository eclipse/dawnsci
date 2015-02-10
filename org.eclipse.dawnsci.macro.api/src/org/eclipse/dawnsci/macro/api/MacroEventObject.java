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

import java.util.Arrays;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MacroEventObject extends EventObject {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1373321229445040760L;

	private String pythonCommand;
	private String jythonCommand;
	private boolean isGen = true;
	private boolean jythonAllowed = true;
	private boolean isImmediate = false;

	public MacroEventObject(Object arg0) {
		super(arg0);
	}
	public MacroEventObject(Object arg0, String cmd) {
		this(arg0, cmd, cmd);
	}
	public MacroEventObject(Object arg0, String cmd, boolean immediate) {
		this(arg0, cmd, cmd);
		isImmediate = immediate;
	}
	public MacroEventObject(Object arg0, String pcmd, String jcmd) {
		super(arg0);
		pythonCommand=pcmd;
		jythonCommand=jcmd;
		isGen = false;
	}


	public String getPythonCommand() {
		if (pythonCommand!=null) return pythonCommand;
		if (getSource() instanceof IMacroCommandProvider) return ((IMacroCommandProvider)getSource()).getPythonCommand();
		return null;
	}

	public void setPythonCommand(String pythonCommand) {
		this.pythonCommand = pythonCommand;
	}

	public String getJythonCommand() {
		if (jythonCommand!=null) return jythonCommand;
		if (getSource() instanceof IMacroCommandProvider) return ((IMacroCommandProvider)getSource()).getJythonCommand();
		if (pythonCommand!=null && isJythonAllowed()) return pythonCommand;
		return null;
	}

	public void setJythonCommand(String jythonCommand) {
		this.jythonCommand = jythonCommand;
	}

	/**
	 * Override to stop the python in the command being auto-generated.
	 * @return
	 */
    public boolean isGeneratable() {
    	return isGen;
    }
    
    public String toString() {
    	return getPythonCommand();
    }
    

	public boolean isImmediate() {
		return isImmediate;
	}
	public void setImmediate(boolean isImmediate) {
		this.isImmediate = isImmediate;
	}

	public void prepend(String command) {
		if (command==null) return;
		if (!command.endsWith("\n")) command = command+"\n";
		setPythonCommand(command+getPythonCommand());
		setJythonCommand(command+getJythonCommand());
	}

	public void append(String command) {
		if (command==null) return;
		setPythonCommand(getPythonCommand()+"\n"+command);
		setJythonCommand(getJythonCommand()+"\n"+command);
	}
	
	/**
	 * Get some string arguments as compatible with macro language
	 * @param args
	 * @return
	 */
	public String getStringArguments(String... args) {
		if (args==null) return "None";
		return getStringArguments(Arrays.asList(args));
	}
	
	/**
	 * Get some string arguments as compatible in macro-language
	 * @param args
	 * @return
	 */
	public String getStringArguments(List<String> args) {
		if (args==null) return "None";
		final StringBuilder buf = new StringBuilder();
		for (Iterator<String> iterator = args.iterator(); iterator.hasNext();) {
			String arg = iterator.next();
			buf.append("'");
			buf.append(arg.replace('\\', '/'));
			buf.append("'");
			if (iterator.hasNext()) buf.append(", ")		;
		}
		
		return buf.toString();
	}
	
	/**
	 * Get a Map compatible with macro language
	 * @param sliceDimensions
	 * @return
	 */
	public String getMap(Map<?, ?> map) {
		
		if (map==null)     return "None";
		if (map.isEmpty()) return "None";
        final StringBuilder buf = new StringBuilder("{");
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
			Object key = iterator.next();
        	buf.append(MacroUtils.toPythonString(key));
        	buf.append(" : ");
        	buf.append(MacroUtils.toPythonString(map.get(key)));
			if (iterator.hasNext()) buf.append(", ");
		}
        buf.append(" }");
		return buf.toString();
	}
	
	public boolean isCommandAvailable() {
		return getJythonCommand()!=null; // Also calls getPythonCommand if jython is null/
	}

	public boolean isJythonAllowed() {
		return jythonAllowed;
	}
	public void setJythonAllowed(boolean jythonAllowed) {
		this.jythonAllowed = jythonAllowed;
	}

}
