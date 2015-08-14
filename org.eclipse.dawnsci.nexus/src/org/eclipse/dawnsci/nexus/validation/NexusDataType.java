package org.eclipse.dawnsci.nexus.validation;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Enumeration of Data types allowed in NXDL specifications.
 * 
 * <p>Source: <a href="http://download.nexusformat.org/doc/html/nxdl-types.html#data-types-allowed-in-nxdl-specifications">Data Types allowed in NXDL specifications</a>
 * 
 * TODO: establish the full list of valid classes
 */
public enum NexusDataType {
	
	/**
	 * Any representation of binary data - if text, line terminator is [CR][LF]
	 */
	NX_BINARY(Object.class),
	
	/**
	 * true/false value ( true | 1 | false | 0 )
	 */
	NX_BOOLEAN(Boolean.class),
	
	/**
	 * any string representation
	 */
	NX_CHAR(String.class),
	
	/**
	 * Alias for ISO8601. ISO 8601 date and time representation (http://www.w3.org/TR/NOTE-datetime)
	 */
	NX_DATE_TIME(Date.class),
	
	/**
	 * any representation of a floating point number
	 */
	NX_FLOAT(Float.class, Double.class), // TODO: is double also allowed
	
	/**
	 * any representation of an integer number
	 */
	NX_INT(Byte.class, Short.class, Integer.class, Long.class), // TODO Integer, etc also allowed
	
	/**
	 * any valid NeXus number representation
	 */
	NX_NUMBER(Number.class),
	
	/**
	 * any representation of a positive integer number (greater than zero)
	 * TODO: add unsigned flag to IDataset
	 */
	NX_POSINT(Long.class),
	
	/**
	 * any representation of an unsigned integer number (includes zero)
	 * TODO: add unsigned flag to IDataset
	 */
	NX_UINT(Long.class);
	
	private List<Class<?>> javaClasses;
	
	private NexusDataType(final Class<?>... javaClasses) {
		this.javaClasses = Arrays.asList(javaClasses);
	}
	
	public void validate(final String fieldName, final IDataset dataset) throws NexusValidationException {
		Class<?> elementClass = dataset.elementClass();
		for (Class<?> javaClass : javaClasses) {
			if (javaClass.isAssignableFrom(elementClass)) {
				return;
			}
		}
		
		final String errorMessage = MessageFormat.format("Unexpected elementClass for field %s"
		+ " (declared type in NXDL application definition = %s). Element",
		fieldName, this.toString(), elementClass.getName());
		throw new NexusValidationException(errorMessage);
	}

}
