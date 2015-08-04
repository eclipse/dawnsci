package org.eclipse.dawnsci.nexus.validation;

import java.sql.Date;
import java.text.MessageFormat;

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
	NX_FLOAT(Float.class), // TODO: is double also allowed
	
	/**
	 * any representation of an integer number
	 */
	NX_INT(Long.class), // TODO Integer, etc also allowed
	
	/**
	 * any valid NeXus number representation
	 */
	NX_NUMBER(Number.class),
	
	/**
	 * any representation of a positive integer number (greater than zero)
	 */
	NX_POSINT(Long.class) {
		@Override
		public void validate(final String fieldName, final IDataset dataset) throws NexusValidationException {
			super.validate(fieldName, dataset);
			
			for (int i = 0, max = dataset.getSize(); i < max; i++) {
				if (dataset.getInt(i) <= 0) {
					final String errorMessage = MessageFormat.format("Illegal value for field {0}"
							+ (dataset.getSize() > 1 ? "element at position " + i : "")
							+ " (declared type in NXDL application definition = {1}). Value must be positive integer, was {2}",
							fieldName, this.toString(), dataset.getInt(i));
					throw new NexusValidationException(errorMessage);
				}
			}
		}
	},
	
	/**
	 * any representation of an unsigned integer number (includes zero)
	 */
	NX_UINT(Long.class) {
		@Override
		public void validate(final String fieldName, final IDataset dataset) throws NexusValidationException {
			super.validate(fieldName, dataset);
			
			for (int i = 0, max = dataset.getSize(); i < max; i++) {
				if (dataset.getInt(i) < 0) {
					final String errorMessage = MessageFormat.format("Illegal value for field {0}"
							+ (dataset.getSize() > 1 ? "element at position " + i : "")
							+ " (declared type in NXDL application definition = {1}). Value must be positive integer or 0, was {2}",
							fieldName, this.toString(), dataset.getInt(i));
					throw new NexusValidationException(errorMessage);
				}
			}
		}
	};
	
	private Class<?> javaClass;
	
	private NexusDataType(final Class<?> javaClass) {
		this.javaClass = javaClass;
	}
	
	public void validate(final String fieldName, final IDataset dataset) throws NexusValidationException {
		Class<?> elementClass = dataset.elementClass();
		if (!javaClass.isAssignableFrom(elementClass)) {
			final String errorMessage = MessageFormat.format("Unexpected elementClass for field %s"
			+ " (declared type in NXDL application definition = %s). Expected element type %s, was %s",
			fieldName, this.toString(), javaClass.getName(), elementClass.getName());
			throw new NexusValidationException(errorMessage);
		}
	}

}
