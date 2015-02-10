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
import java.util.Iterator;

/**
 * Parses the calling method from the stack and creates a python command
 * of the form:
 * 
 *          varName.methodName(pythonified arguements)
 *  
 *  for instance:
 *  		if (mservice!=null) mservice.publish(new MethodEventObject("ps", this, "fred"));
 *  
 *  from a method called 'setTitle' (in any class)
 *
 *  gives:
 *          ps.setTitle("fred") as the python command.
 *          
 * The method name is parsed out of the thread stack as the method before the
 * constructor of this class.
 * 
 * You may also specify the method name directly using the second constructor
 * This aviods any stack parsing.
 *  
 * @author fcp94556
 *
 */
public class MethodEventObject<T> extends MacroEventObject {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2951162101084315254L;

	/**
	 * Does not auto-generate a method to call.
	 * @param source
	 */
	public MethodEventObject(Object source) {
		super(source);
	}

	/**
	 * Gets the method name from the stack.
	 * @param varName
	 * @param source
	 * @param args
	 */
	public MethodEventObject(String varName, Object source, T... args) {
		
		this(varName, getCallingMethodName(Thread.currentThread().getStackTrace()), source, args);
	}
	
	public MethodEventObject(String varName, StackTraceElement[] stack, Object source, T... args) {
		
		this(varName, getCallingMethodName(stack), source, args);
	}

	
	/**
	 * Specifies the method name to call on the object varName.
	 * @param varName
	 * @param methodName
	 * @param source
	 * @param args
	 */
	public MethodEventObject(String varName, String methodName, Object source, T... args) {
		super(source);
		setPythonCommand(createPythonCommand(varName, methodName, source, args));
	}

	/**
	 * This may be overridden to provide custom string commands.
	 * 
	 * @param varName
	 * @param methodName
	 * @param source
	 * @param args
	 * @return
	 */
	protected String createPythonCommand(String varName, String methodName, Object source, T... args) {
		
		if (varName==null || "".equals(varName)) return null;
		StringBuilder buf = new StringBuilder();
				
		// Make method call
		buf.append(varName);
		buf.append(".");
		buf.append(methodName);
		buf.append("(");
		buf.append(getPythonArgs(args));
		buf.append(")");
		
		return buf.toString();
	}

	protected String getPythonArgs(Object[] args) {
		
		if (args==null || args.length<1) return "";
		StringBuilder buf = new StringBuilder();
		for (Iterator<Object> it = Arrays.asList(args).iterator(); it.hasNext();) {
			Object next = it.next();
			buf.append(MacroUtils.toPythonString(next));
			if (it.hasNext()) buf.append(", ");
		}
		return buf.toString();
	}

	protected static String getCallingMethodName( StackTraceElement ste[] ) {  
		   
	    String methodName = "";  
	    boolean flag = false;  
	   
	    for ( StackTraceElement s : ste ) {  
	   
	        if ( flag ) {  
	   
	            methodName = s.getMethodName();  
	            break;  
	        }  
	        flag = s.getMethodName().equals( "<init>" );  
	    }  
	    return methodName;  
	}

    public boolean isGeneratable() {
    	return false;
    }
 
}
