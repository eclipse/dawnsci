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
package org.eclipse.dawnsci.plotting.api.annotation;


/**
 * No methods in this interface are thread safe.
 * 
 * @author Matthew Gerring
 *
 */
public interface IAnnotationSystem {

	
	/**
	 * Creates an annotation. This does not create any user interface
	 * for the annotation. You can then call methods on the annotation.
	 * Use addAnnotation(...) and removeAnnotation(...) to control
	 * if the selection is active on the graph.
	 * 
	 * @param name
	 * @param regionType
	 * @return
	 * @throws Exception if name exists already.
	 */
	public IAnnotation createAnnotation(final String name) throws Exception;
	
	/**
	 * Add an annotation to the graph.
	 * @param region
	 */
	public void addAnnotation(final IAnnotation region);
	
	
	/**
	 * Remove an annotation to the graph.
	 * @param region
	 */
	public void removeAnnotation(final IAnnotation region);
	
	/**
	 * Get an annotation by name.
	 * @param name
	 * @return
	 */
	public IAnnotation getAnnotation(final String name);

	/**
	 * Remove all annotations
	 */
	public void clearAnnotations();

	/**
	 * Renames the annotation; better than calling setName on the IAnnotation as the
	 * collection of annotations is updated properly. No event will be fired.
	 */
	public void renameAnnotation(IAnnotation annotation, String name);
}
