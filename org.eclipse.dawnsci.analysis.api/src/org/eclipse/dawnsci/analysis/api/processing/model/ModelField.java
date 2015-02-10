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

import java.lang.reflect.Field;

/**
 * Class to be used for editing the field of a Model
 * 
 * Reads annotations used on the models field and provides 
 * discovery information about the field.
 * 
 */
public class ModelField {

	private IOperationModel model;
	private String          name;

	public ModelField(IOperationModel model, String name) {
		this.model = model;
		this.name  = name;
	}

	public IOperationModel getModel() {
		return model;
	}

	public void setModel(IOperationModel model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisplayName() {
    	
    	OperationModelField anot = ModelUtils.getAnnotation(model, name);
    	if (anot!=null) {
    		String label = anot.label();
    		if (label!=null && !"".equals(label)) return label;
    	}
    	return name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelField other = (ModelField) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Class<? extends Object> getType() throws NoSuchFieldException, SecurityException {
		Field field = ModelUtils.getField(model, name);
		return field.getType();
	}
	

	public OperationModelField getAnnotation() {
		return ModelUtils.getAnnotation(model, name);
	}

	public boolean isFileProperty() {
		
    	final OperationModelField anot = getAnnotation();
    	if (anot!=null && anot.file()!=FileType.NONE) return true;

		Class<? extends Object> clazz;
		try {
			clazz = getType();
		} catch (NoSuchFieldException | SecurityException e) {
			return false;
		}
		return ModelUtils.isFileType(clazz);
	}

	/**
	 * Set fields value
	 * @param value
	 * @throws Exception
	 */
	public void set(Object value) throws Exception {
		model.set(name, value);
	}

	/**
	 * Get fields value
	 * @return value
	 */
	public Object get() {
		try {
		    return model.get(name);
		} catch (Exception ne) {
			return null;
		}
	}
}
