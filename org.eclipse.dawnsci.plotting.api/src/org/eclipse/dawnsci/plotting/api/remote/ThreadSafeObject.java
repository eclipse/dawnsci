/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.remote;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ThreadSafeObject {
	
	private static Logger logger = LoggerFactory.getLogger(ThreadSafeObject.class);

	protected Object delegate;
	public ThreadSafeObject(Object delegate) {
		this.delegate = delegate;
	}
	
	/**
	 * Calls method in a SWT thread safe way.
	 * @param methodName
	 * @param args
	 */
	protected Object call(final String methodName, final Object... args) {
		
		@SuppressWarnings("rawtypes")
		final Class[] classes = args!=null ? new Class[args.length] : null;
		if (classes!=null) {
			for (int i = 0; i < args.length; i++) classes[i]=args[i].getClass();
		}
		return call(methodName, classes, args);
	}
	
	/**
	 * Calls method in a SWT thread safe way.
	 * @param methodName
	 * @param args
	 */
	protected Object call(final String methodName, @SuppressWarnings("rawtypes") final Class[] classes, final Object... args) {
		
		if( delegate == null) return null;
		
		final List<Object> ret = new ArrayList<Object>(1);
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				try {
					try {
					    Method method = delegate.getClass().getMethod(methodName, classes);
					    Object val    = method.invoke(delegate, args);
					    ret.add(val);
					    
					} catch (NoSuchMethodException nsm1) {
						
						if (classes.length==1) { // Deal with primitive getters and setters
							Class<?> clazz = classes[0];
						    Method method = null;
						    
							// See if there will different signature
							METHOD_LOOP: for (Method m : delegate.getClass().getMethods()) {
								if (m.getName().equals(methodName) && m.getParameterTypes().length==classes.length) {
									for (int i = 0; i < m.getParameterTypes().length; i++) {
										Class type = m.getParameterTypes()[i];
										if (!type.isAssignableFrom(classes[i])) {
											break METHOD_LOOP;
										}
									}
									method = m;
								}
							}

							if (method==null) {
								try {

									if (Double.class.isAssignableFrom(clazz)) {
										method = delegate.getClass().getMethod(methodName, new Class[]{double.class});
									} else if (Float.class.isAssignableFrom(clazz)) {
										method = delegate.getClass().getMethod(methodName, new Class[]{float.class});
									} else if (Long.class.isAssignableFrom(clazz)) {
										method = delegate.getClass().getMethod(methodName, new Class[]{long.class});
									} else if (Integer.class.isAssignableFrom(clazz)) {
										method = delegate.getClass().getMethod(methodName, new Class[]{int.class});
									} else if (Boolean.class.isAssignableFrom(clazz)) {
										method = delegate.getClass().getMethod(methodName, new Class[]{boolean.class});
									}
								} catch (NoSuchMethodException nsm2) {
									method = delegate.getClass().getMethod(methodName, new Class[]{Number.class});
								}
							}
	
							
							if (method!=null) {
								Object val    = method.invoke(delegate, args);
							    ret.add(val);
							    return;
							}
						}
						
						throw nsm1;
					}
					
				} catch (Exception ne) {
					logger.error("Cannot execute "+methodName+" with "+args, ne);
				}
			}
		});
		return ret.isEmpty() ? null : ret.get(0);
	}

	public static String getMethodName ( StackTraceElement ste[] ) {  
		   
	    String methodName = "";  
	    boolean flag = false;  
	   
	    for ( StackTraceElement s : ste ) {  
	   
	        if ( flag ) {  
	   
	            methodName = s.getMethodName();  
	            break;  
	        }  
	        flag = s.getMethodName().equals( "getStackTrace" );  
	    }  
	    return methodName;  
	}
}
