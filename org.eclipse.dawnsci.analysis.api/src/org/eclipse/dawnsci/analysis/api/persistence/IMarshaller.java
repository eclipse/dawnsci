/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.persistence;

/**
 * A marshaller is an extension to the MarshallerService.
 * The marshaller service knows about common dawnsci types
 * but optionally may be extended.
 * 
 * Object class may be null, if it is not serializer class and deserializer class must also be provided.
 * 
 * getMixinAnnotationType() and getMixinAnnotationClass() default to null, they must both return non-null if they
 * are to be provided.
 * 
 * @author Matthew Gerring
 */
public interface IMarshaller {

	/**
	 * The class which we will serialize and deserialise using the serializer class and the deserializer class.
	 * 
	 * @return class to deal with
	 */
	<T> Class<T> getObjectClass();
	
	/**
	 * Class of the serializer which should have a no argument constructor
	 * @return the class
	 */
	<T> Class<T> getSerializerClass();
	
	/**
	 * Class of the deserializer which should have a no argument constructor
	 * 
	 * @return the class
	 */
	<T> Class<T> getDeserializerClass();
	
	/**
	 * The type, if any, of the mixin annotation
	 * This is the left hand side of SimpleModule.setMixInAnnotation(class, class);
	 * 
	 * @return class
	 */
	default <T> Class<T> getMixinAnnotationType() {
		return null;
	}
	
	/**
	 * The type, if any, of the mixin annotation
	 * This is the right hand side of SimpleModule.setMixInAnnotation(class, class);
     *
	 * @return class
	 */
	default <T> Class<T> getMixinAnnotationClass() {
		return null;
	}

}
