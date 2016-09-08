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
 * The marshaller service will marshal  existing dawnsci object types.
 * In addition, if object types are required to marshall/unmarshall
 * in projects which the marshaller service implementation cannot
 * see directly, extension points may be used.
 * 
 * @author Colin Palmer
 */
public interface IMarshallerService {

	/**
	 * Serialize the given object to a JSON string
	 * <p>
	 * Objects to be marshalled should have no-arg constructors, and getters and setters for their fields.
	 * <p>
	 * Where the object type cannot be determined inside the vanilla marshaller, class registries are used
	 * to provide a list of class id's and their corresponding class, through an extension point. 
	 *
	 * This is the same as marshal(anyObject, true)
	 *
	 * @param anyObject
	 *            the object to be serialized
	 * @return the JSON string representing the object
	 * @throws Exception
	 *             if the object cannot be marshalled correctly
	 */
	public String marshal(Object anyObject) throws Exception;
	
	
	/**
	 * This marshal gives the option of turning off the class registry id information in the
	 * serialization. If you are not using interfaces (the norm) then this information is not needed
	 * because the types are static.
	 * 
	 * @param anyObject
	 * @param useRegisteredClassTyping
	 * @return the JSON string representing the object
	 * @throws Exception
	 */
	public String marshal(Object anyObject, boolean useRegisteredClassTyping) throws Exception;
	

	/**
	 * Deserialize the given JSON string as an instance of the given class
	 * <p>
	 * The default implementation will try to find the correct classes for deserialization if possible,
	 * using type information encoded in the JSON string if available.
	 *
	 * @param string
	 *            the JSON string to be deserialized
	 * @param beanClass
	 *            the expected class of the deserialized object
	 * @return the deserialized object
	 * @throws Exception
	 *             if the object cannot be unmarshalled correctly
	 */
	public <U> U unmarshal(String string, Class<U> beanClass) throws Exception;

	/**
	 * Returns true if this Object has a mix-in class (for ROIs for instance)
	 * 
	 * @param obj
	 * @return boolean
	 */
	public boolean isObjMixInSupported(Object obj);
}
