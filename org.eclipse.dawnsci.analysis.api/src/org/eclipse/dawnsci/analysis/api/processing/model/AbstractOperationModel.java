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

package org.eclipse.dawnsci.analysis.api.processing.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.eclipse.dawnsci.analysis.api.Activator;
import org.eclipse.dawnsci.analysis.api.fitting.functions.IFunction;
import org.eclipse.dawnsci.analysis.api.persistence.IPersistenceService;
import org.eclipse.dawnsci.analysis.api.roi.IROI;

/**
 * Extend this class for your model to avoid having to implement the get and set manually.
 * Do not put non-POJO methods in your models, keep them vanilla.
 * 
 * BE WARNED the get and set are not especially fast - do not call them from big loops!
 * 
 * This class MUST define a no argument constructor with getters and setters.
 * 
 * NOTE You can currently have only two level of inheritance below this class so
 * 
 * class B extends A ...
 * class A extends AbstractOperationModel 
 * 
 * Would be ok; BUT class C extends B ...  IS NOT OK - TWO LEVELS ONLY ARE ALLOWED! :)
 * 
 */
public abstract class AbstractOperationModel implements IOperationModel {
	
	/**
	 * Tries to find the no-argument getter for this field, ignoring case
	 * so that camel case may be used in method names. This means that this
	 * method is not particularly fast, so avoid calling in big loops!
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Override
	public Object get(String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Object val = get(getClass(), name);
		if (val==null) val = get(getClass().getSuperclass(), name);
		return val;
	}
	
	private Object get(Class<?> clazz, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (clazz==null || clazz.equals(Object.class)) return null;
		
		final String getter = getGetterName(name).toLowerCase();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().toLowerCase().equals(getter)) {
				if (method.getParameterTypes().length<1) {
					method.setAccessible(true);
					return method.invoke(this);
				}
			}
		}
		
		final String isser  = getIsserName(name).toLowerCase();
		for (Method method : methods) {
			if (method.getName().toLowerCase().equals(isser)) {
				if (method.getParameterTypes().length<1) {
					method.setAccessible(true);
					return method.invoke(this);
				}
			}
		}
		return null;
	}

	@Override
	public boolean isModelField(String name) throws NoSuchFieldException, SecurityException {
		
		Field field = null;
		try {
		    field = getClass().getDeclaredField(name);
		} catch (Exception ne) {
			field = getClass().getSuperclass().getDeclaredField(name);
		}

		OperationModelField omf = field.getAnnotation(OperationModelField.class);

		
		final String getter = getGetterName(name).toLowerCase();
		Method[] methods = getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().toLowerCase().equals(getter)) {
				if (method.getParameterTypes().length<1) {
					return true;
				}
			}
		}
		
		final String isser  = getIsserName(name).toLowerCase();
		for (Method method : methods) {
			if (method.getName().toLowerCase().equals(isser)) {
				if (method.getParameterTypes().length<1) {
					return true;
				}
			}
		}

	    return false;
	}

	/**
	 * Set a field by name using reflection.
	 * @param name
	 * @return fields old value, or null
	 */
	@Override
	public Object set(String name, Object value)throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Object oldValue = get(name);
		
		final String setter = getSetterName(name).toLowerCase();
		Method[] methods = getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().toLowerCase().equals(setter)) {
				if (method.getParameterTypes().length==1) {
					method.setAccessible(true);
					method.invoke(this, value);
				}
			}
		}
		return oldValue;
	}

	
	private static String getSetterName(final String fieldName) {
		if (fieldName == null) return null;
		return getName("set", fieldName);
	}
	/**
	 * There must be a smarter way of doing this i.e. a JDK method I cannot find. However it is one line of Java so
	 * after spending some time looking have coded self.
	 * 
	 * @param fieldName
	 * @return String
	 */
	private static String getGetterName(final String fieldName) {
		if (fieldName == null) return null;
		return getName("get", fieldName);
	}
	
	private static String getIsserName(final String fieldName) {
		if (fieldName == null)
			return null;
		return getName("is", fieldName);
	}
	private static String getName(final String prefix, final String fieldName) {
		return prefix + getFieldWithUpperCaseFirstLetter(fieldName);
	}
	public static String getFieldWithUpperCaseFirstLetter(final String fieldName) {
		return fieldName.substring(0, 1).toUpperCase(Locale.US) + fieldName.substring(1);
	}

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName,
				listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}

	/**
	 * Creates the macro commands for constructing this model in python.
	 * In order for py4j to see the model class, its plugin must have been 
	 * registered with the extension point "org.eclipse.dawnsci.analysis.classloader"
	 * 
	 * @param varName
	 * @return the commands in correct macro language
	 * @throws Exception
	 */
	public String createMacroCommands(String varName) throws Exception {
		
		StringBuilder ret = new StringBuilder(); // Use a string builder because we are going to reflect getter and setter methods.
		ret.append("java_import(jvm, '"+getClass().getPackage().getName()+".*')\n");
		ret.append(varName+" = jvm."+getClass().getSimpleName()+"()\n");
		
		// We do list a low level way to reduce dependencies. There are apache APIs for doing this
		// kind of thing.
		final Method[] methods = getClass().getMethods();
		final Collection<String> names = new HashSet<String>();
		for (Method method : methods) names.add(method.getName());
		
		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("is") || name.startsWith("get")) {
				String setterName = name.startsWith("is")
						          ? "set"+name.substring(2)
						          : "set"+name.substring(3);
						          
				if (method.getParameterTypes().length==0 && names.contains(setterName)) { // No arguments and setter
					
					final Object value = method.invoke(this);
					if (value != null) { // Non-null

						// What the hey lets set it!
						Method set=null;
						try {
							try {
								set = getClass().getMethod(setterName, value.getClass());
								
							} catch (NoSuchMethodException nsm1) {
								// See if there will different signature
								for (Method m : methods) {
									if (m.getName().equals(setterName) && m.getParameterTypes().length==1) {
										Class<?> clazz = m.getParameterTypes()[0];
										if (clazz.isAssignableFrom(value.getClass())) {
											set = m;
											break;
										}
									}
								}

								if (set==null) {
									try {
										if (Double.class.isAssignableFrom(value.getClass())) {
											set = getClass().getMethod(setterName, new Class[]{double.class});
										} else if (Float.class.isAssignableFrom(value.getClass())) {
											set = getClass().getMethod(setterName, new Class[]{float.class});
										} else if (Long.class.isAssignableFrom(value.getClass())) {
											set = getClass().getMethod(setterName, new Class[]{long.class});
										} else if (Integer.class.isAssignableFrom(value.getClass())) {
											set = getClass().getMethod(setterName, new Class[]{int.class});
										} else if (Boolean.class.isAssignableFrom(value.getClass())) {
											set = getClass().getMethod(setterName, new Class[]{boolean.class});
										}

									} catch (NoSuchMethodException nsm2) {
										set = getClass().getMethod(setterName, new Class[]{Number.class});
									}
								}

							}
						} catch (Exception ne) {
							continue;
						}
						
						if (set == null) continue;
						
						if (value instanceof IROI || value instanceof IFunction) {
							IPersistenceService pservice = (IPersistenceService)Activator.getService(IPersistenceService.class);
					        if (pservice!=null) {
					        	String json = pservice.marshal(value);
								ret.append("pservice = dnp.plot.getService('"+IPersistenceService.class.getName()+"')\n");
								ret.append("roi = pservice.unmarshal('"+json+"')\n");
								ret.append(varName+"."+setterName+"(roi)\n");
					        }
						} else {
							ret.append(varName+"."+setterName+"("+toPythonString(value)+")\n");
						}


					}
				}
			}
		}
		
		return ret.toString();
	}
		


	/**
	 * Deals with primitive arrays, Strings and primitives.
	 * Duplicated method from MacroUtils to avoid unecessary dependency
	 * @param value
	 */
	private static String toPythonString(Object value) {
		
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
        	return "'"+((Enum<?>)value).name()+"'";
        	
        } 

        
        return value.toString();
	}

}
