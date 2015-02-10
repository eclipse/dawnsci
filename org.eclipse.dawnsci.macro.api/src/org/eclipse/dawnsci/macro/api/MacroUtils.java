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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacroUtils {

    /**
     * Return a legal name for most macro languages, but
     * especially in python.
     * 
     * @param setName
     * @return
     */
	public static String getLegalName(String setName) {
		if (setName.endsWith("/"))   setName = setName.substring(0,setName.length()-1);
		if (setName.indexOf('/')>-1) setName = setName.substring(setName.lastIndexOf('/'));
		
		setName = setName.replaceAll(" ", "_");
		setName = setName.replaceAll("[^a-zA-Z0-9_]", "");
		final Matcher matcher = Pattern.compile("(\\d+)(.+)").matcher(setName);
		if (matcher.matches()) {
			setName = matcher.group(2);
		}
		
		if (Pattern.compile("(\\d+)").matcher(setName).matches()) {
			setName= "x"+setName;
		}
		return setName;
	}

	/**
	 * Deals with primitive arrays, Strings and primitives.
	 * @param value
	 */
	public static String toPythonString(Object value) {
		
		if (value==null) return "None";
		
        if (value instanceof String) {
        	return "'"+(String)value+"'";
        	
        } else if (value instanceof Boolean) {
        	return ((Boolean)value).booleanValue() ? "True" : "False";
        	
        } else if (value instanceof short[]) {
        	return Arrays.toString((short[])value);
        	
        } else if  (value instanceof int[]) {
        	return Arrays.toString((int[])value);
        	
        } else if  (value instanceof long[]) {
        	return Arrays.toString((long[])value);
        	
        } else if  (value instanceof char[]) {
        	return Arrays.toString((char[])value);
        	
        } else if  (value instanceof float[]) {
        	return Arrays.toString((float[])value);
        	
        } else if  (value instanceof double[]) {
        	return Arrays.toString((double[])value);
        	
        } else if  (value instanceof boolean[]) {
        	return Arrays.toString((boolean[])value);
        	
        } else if  (value instanceof byte[]) {
        	return Arrays.toString((byte[])value);
        	
        } else if  (value instanceof Object[]) {
        	return Arrays.toString((Object[])value);
        	
        } else if (value instanceof Enum) {
        	return "'"+((Enum)value).name()+"'";
        }
        
        return value.toString();
	}

}
