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

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelUtils {

	private ModelUtils() {
		
	}
	
	public static boolean isFileType(Class<? extends Object> clazz) {
		if (File.class.isAssignableFrom(clazz))       return true;
		if (Path.class.isAssignableFrom(clazz))       return true;
		if (clazz.getName().equals("org.eclipse.core.resources.IResource"))  return true;
		return false;
	}

	public static OperationModelField getAnnotation(IOperationModel model, String fieldName) {
		
		Field field = getField(model, fieldName);
	    if (field!=null) {
	    	OperationModelField anot = field.getAnnotation(OperationModelField.class);
	    	if (anot!=null) {
	    		return anot;
	        }
	    }
	    return null;
	}
	
	public static Field getField(IOperationModel model, String fieldName) {
		Class<?> klazz = model.getClass();
		
		// see remarks in getModelFields
		do {
			try {
				return klazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException | SecurityException e) {
				klazz = klazz.getSuperclass();
			}
		} while(klazz != AbstractOperationModel.class && klazz != null);
		return null;
	}

	
	/**
	 * Get a collection of the fields of the model that should be edited in the User interface
	 * for editing the model.
	 * 
	 * @return collection of fields.
	 * @throws Exception
	 */
	public static Collection<ModelField> getModelFields(IOperationModel model) throws Exception {
		
		// Decided not to use the obvious BeanMap here because class problems with
		// GDA and we have to read annotations anyway.
		final List<Field> allFields = new ArrayList<>(31);
		
		// exclude static and final fields!
		// go through the inheritance tree and stop when the class is 
		// 	1) equal to AbstractOperationModel OR
		//  2) there is no super class, meaning the class implements IOperationModel directly
		
		Class<?> klazz = model.getClass();
		
		do {
			allFields.addAll(Stream.of(klazz.getDeclaredFields()).filter(field -> !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())).collect(Collectors.toList()));
			klazz = klazz.getSuperclass();
		} while(klazz != AbstractOperationModel.class && klazz != null);
		
		// The returned descriptor
		final List<ModelField> ret = new ArrayList<>();
		
		// fields
		for (Field field : allFields) {
			
			// If there is a getter/isser for the field we assume it is a model field.
			try {
				if (model.isModelField(field.getName())) {			
					ret.add(new ModelField(model, field.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
		Collections.sort(ret, (o1, o2) -> {
			OperationModelField an1 = ModelUtils.getAnnotation(o1.getModel(), o1.getName());
			OperationModelField an2 = ModelUtils.getAnnotation(o2.getModel(), o2.getName());
			
			// It is legal for fields not to be annotated
			if (an1!=null && an2 !=null) {
				if (an1.fieldPosition() != Integer.MAX_VALUE && an2.fieldPosition() != Integer.MAX_VALUE) {
					return (an1.fieldPosition() - an2.fieldPosition());
				} else if (an1.fieldPosition() != Integer.MAX_VALUE) {
					return -1;
				} else if (an2.fieldPosition() != Integer.MAX_VALUE) {
					return 1;
				}
			}
			return o1.getDisplayName().toLowerCase().compareTo(o2.getDisplayName().toLowerCase());
		});
		
		return ret;
	}

}
