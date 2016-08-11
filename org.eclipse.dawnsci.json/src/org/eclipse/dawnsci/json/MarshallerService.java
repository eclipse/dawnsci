/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Colin Palmer - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshaller;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.roi.IOrientableROI;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularFitROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalFitROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.FreeDrawROI;
import org.eclipse.dawnsci.analysis.dataset.roi.GridROI;
import org.eclipse.dawnsci.analysis.dataset.roi.HyperbolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ParabolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PerimeterBoxROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolylineROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RingROI;
import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;
import org.eclipse.dawnsci.analysis.dataset.roi.XAxisBoxROI;
import org.eclipse.dawnsci.analysis.dataset.roi.YAxisBoxROI;
import org.eclipse.dawnsci.json.internal.BundleAndClassNameIdResolver;
import org.eclipse.dawnsci.json.internal.BundleProvider;
import org.eclipse.dawnsci.json.internal.OSGiBundleProvider;
import org.eclipse.dawnsci.json.mixin.roi.CircularFitROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.CircularROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.EllipticalFitMixIn;
import org.eclipse.dawnsci.json.mixin.roi.EllipticalROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.FreeDrawROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.GridROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.HyperbolicROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.IOrientableROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.IROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.IRectangularROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.LinearROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.ParabolicROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.PerimeterROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.PolygonalROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.PolylineROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.RectangularROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.RingROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.SectorROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.XAxisBoxROIMixIn;
import org.eclipse.dawnsci.json.mixin.roi.YAxisBoxROIMixIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTypeResolverBuilder;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * JSON marshaller implementation which allows objects to be converted to and from JSON strings
 * <p>
 * This implementation adds OSGi type information to encoded JSON strings and will use it if present when deserializing.
 * <p>
 * If type information is not present in a JSON string it will attempt to deserialize it anyway, but in an OSGi
 * environment this will probably fail for anything except primitive and core Java types since other classes will not be
 * available to this bundle's classloader. If classloading fails, it might still be possible to make deserialization
 * work correctly in some cases by setting the thread context classloader before calling the unmarshal method, since
 * some of the required classes (but not necessarily all) might be available to the classloader which loaded the
 * caller's class:
 * <pre>
 * ClassLoader tccl = Thread.currentThread().getContextClassLoader();
 * try {
 * 	Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
 * 	// call the unmarshaller
 * } finally {
 * 	Thread.currentThread().setContextClassLoader(tccl);
 * }
 * </pre>
 *
 * @author Colin Palmer
 */
public class MarshallerService implements IMarshallerService {

	private static final String TYPE_INFO_FIELD_NAME = "@bundle_and_class";

	private static final Logger logger = LoggerFactory.getLogger(MarshallerService.class);

	private BundleProvider bundleProvider;
	private ObjectMapper osgiMapper;
	private ObjectMapper standardMapper;
	private ObjectMapper nonOsgiMapper;

	private List<IMarshaller> marshallers;

	static {
		System.out.println("Started " + IMarshallerService.class.getSimpleName());
	}

	/**
	 * Default public constructor - for testing purposes only! Otherwise use OSGi to get the service.
	 */
	public MarshallerService() {
		this(null, new OSGiBundleProvider());
	}

	/**
	 * Constructor for testing to allow a BundleProvider to be injected.
	 *
	 * @param bundleProvider
	 */
	public MarshallerService(BundleProvider bundleProvider) {
		this(null, bundleProvider);
	}

	public MarshallerService(IMarshaller... marshallers) {
		this(Arrays.asList(marshallers), new OSGiBundleProvider());
	}

	public MarshallerService(List<IMarshaller> marshallers, BundleProvider bundleProvider) {
		if (marshallers!=null) this.marshallers = Collections.unmodifiableList(marshallers);
		this.bundleProvider = bundleProvider;
	}

	/**
	 * Serialize the given object to a JSON string
	 * <p>
	 * Objects to be marshalled with this implementation should have no-arg constructors, and getters and setters for
	 * their fields. Primitive and collection types (arrays, Collections and Maps) should work correctly. More
	 * complicated types (generics other than collections, inner classes etc) might or might not work properly.
	 */
	@Override
	public String marshal(Object anyObject) throws Exception {
		return marshal(anyObject, true);
	}

	@Override
	public String marshal(Object anyObject, boolean requireBundleAndClass)  throws Exception {
		String json;
		if (requireBundleAndClass) {
			if (osgiMapper==null) osgiMapper = createOsgiMapper();
			json = osgiMapper.writeValueAsString(anyObject);
		} else {
			if (standardMapper==null) standardMapper = createJacksonMapper();
			json = standardMapper.writeValueAsString(anyObject);
		}

		return json;
	}

	private ObjectMapper createOsgiMapper() throws InstantiationException, IllegalAccessException {
		ObjectMapper mapper = createJacksonMapper();
		mapper.setDefaultTyping(createOSGiTypeIdResolver());
		return mapper;
	}

	/**
	 * Deserialize the given JSON string as an instance of the given class
	 * <p>
	 * This method will try to find the correct classes for deserialization if possible. If you still have problems
	 * with ClassNotFoundExceptions, one option which might help is to try setting the thread context classloader
	 * before calling the unmarshal method:
	 * <pre>
	 * ClassLoader tccl = Thread.currentThread().getContextClassLoader();
	 * try {
	 *     Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
	 *     // call the unmarshaller
	 * } finally {
	 *     Thread.currentThread().setContextClassLoader(tccl);
	 * }
	 * </pre>
	 */
	@Override
	public <U> U unmarshal(String string, Class<U> beanClass) throws Exception {
		try {
			if (osgiMapper == null) osgiMapper = createOsgiMapper();
			if (beanClass != null) {
				return osgiMapper.readValue(string, beanClass);
			}
			// If bean class is not supplied, try using Object
			@SuppressWarnings("unchecked")
			U result = (U) osgiMapper.readValue(string, Object.class);
			return result;
		} catch (JsonMappingException | IllegalArgumentException ex) {
			// Check if this is due to missing type info. This can appear in two ways: the type info field can be
			// missing from an object, in which case JsonMappingException is thrown; or the first element of an array
			// might be wrongly interpreted as a class name, in which case we get a ClassNotFoundException
			if ((ex instanceof JsonMappingException && ex.getMessage().contains(TYPE_INFO_FIELD_NAME))
					|| ex instanceof IllegalArgumentException && ex.getCause() instanceof ClassNotFoundException) {
				// Possibly no bundle and class information in the JSON - fall back to old mapper in case JSON has come
				// from an older version
				try {
					if (nonOsgiMapper == null) nonOsgiMapper = createNonOsgiMapper();
					return nonOsgiMapper.readValue(string, beanClass);
				} catch (Exception withoutTypeException) {
					logger.error("Could not deserialize string assuming no type info present: {}", string, withoutTypeException);
				}
			}
			// Log and re-throw the original exception since this is more likely to be useful to clients
			logger.error("Could not deserialize string assuming type info present: {}", string, ex);
			throw ex;
		}
	}

	private final ObjectMapper createJacksonMapper() throws InstantiationException, IllegalAccessException {

		ObjectMapper mapper = new ObjectMapper();

		// Use custom serialization for IPosition objects
		// (Otherwise all IPosition subclasses will need to become simple beans, i.e. no-arg constructors with getters
		// and setters for all fields. MapPosition.getNames() caused problems because it just returns keys from the map
		// and has no corresponding setter.)
		SimpleModule module = new SimpleModule();

		// Add mix-in annotations for ROIs
		module.setMixInAnnotation(IROI.class, IROIMixIn.class);
		module.setMixInAnnotation(IOrientableROI.class, IOrientableROIMixIn.class);
		module.setMixInAnnotation(LinearROI.class, LinearROIMixIn.class);
		module.setMixInAnnotation(CircularROI.class, CircularROIMixIn.class);
		module.setMixInAnnotation(GridROI.class, GridROIMixIn.class);
		module.setMixInAnnotation(PerimeterBoxROI.class, PerimeterROIMixIn.class);
		module.setMixInAnnotation(IRectangularROI.class, IRectangularROIMixIn.class);
		module.setMixInAnnotation(RectangularROI.class, RectangularROIMixIn.class);
		module.setMixInAnnotation(RingROI.class, RingROIMixIn.class);
		module.setMixInAnnotation(SectorROI.class, SectorROIMixIn.class);
		module.setMixInAnnotation(FreeDrawROI.class, FreeDrawROIMixIn.class);
		module.setMixInAnnotation(PolylineROI.class, PolylineROIMixIn.class);
		module.setMixInAnnotation(PolygonalROI.class, PolygonalROIMixIn.class);
		module.setMixInAnnotation(XAxisBoxROI.class, XAxisBoxROIMixIn.class);
		module.setMixInAnnotation(YAxisBoxROI.class, YAxisBoxROIMixIn.class);
		module.setMixInAnnotation(EllipticalROI.class, EllipticalROIMixIn.class);
		module.setMixInAnnotation(CircularFitROI.class, CircularFitROIMixIn.class);
		module.setMixInAnnotation(EllipticalFitROI.class, EllipticalFitMixIn.class);
		module.setMixInAnnotation(ParabolicROI.class, ParabolicROIMixIn.class);
		module.setMixInAnnotation(HyperbolicROI.class, HyperbolicROIMixIn.class);

		// Add handlers
		createModuleExtensions(module);

		mapper.registerModule(module);

		// Be careful adjusting these settings - changing them will probably cause various unit tests to fail which
		// check the exact contents of the serialized JSON string
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.enable(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS);
		//mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper;
	}

	@Override
	public boolean isObjMixInSupported(Object roi) {
		if(roi instanceof PointROI
				|| roi instanceof LinearROI
				|| roi instanceof CircularROI
				|| roi instanceof GridROI
				|| roi instanceof PerimeterBoxROI
				|| roi instanceof RectangularROI
				|| roi instanceof RingROI
				|| roi instanceof SectorROI
				|| roi instanceof FreeDrawROI
				|| roi instanceof PolylineROI
				|| roi instanceof PolygonalROI
				|| roi instanceof XAxisBoxROI
				|| roi instanceof YAxisBoxROI
				|| roi instanceof EllipticalROI
				|| roi instanceof CircularFitROI
				|| roi instanceof EllipticalFitROI
				|| roi instanceof ParabolicROI
				|| roi instanceof HyperbolicROI)
			return true;
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createModuleExtensions(SimpleModule module) throws InstantiationException, IllegalAccessException {

        List<IMarshaller> ms = new ArrayList<>(7);
		try {
	        IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.analysis.api.marshaller");

	        for (IConfigurationElement e : elements) {
				final IMarshaller marshaller = (IMarshaller) e.createExecutableExtension("class");
				ms.add(marshaller);
	        }
		} catch (Exception ne) {
			// It is legal to fail the configuration elements because
			// one may use the json service in non-OSGi
			// Instead tests should attempt to serialize their custom objects
			// and problems with serialization be picked up this way.
		}
        if (marshallers!=null && !marshallers.isEmpty()) ms.addAll(marshallers);

        for (IMarshaller marshaller : ms) {
			Class<?> objectClass = marshaller.getObjectClass();
			if (objectClass!=null) {
				module.addSerializer(objectClass,  (JsonSerializer)marshaller.getSerializerClass().newInstance());
				module.addDeserializer(objectClass,(JsonDeserializer)marshaller.getDeserializerClass().newInstance());
			}

			Class<?> mixInType  = marshaller.getMixinAnnotationType();
			Class<?> mixInClass = marshaller.getMixinAnnotationClass();
			if (mixInClass!=null && mixInType!=null) {
				module.setMixInAnnotation(mixInType, mixInClass);
			}
		}
	}

	/**
	 * Create a TypeResolverBuilder which will add bundle and class name information to JSON-serialized objects to
	 * allow the correct classes to be loaded during deserialization.
	 * <p>
	 * NOTE: this strongly relies on the exact implementation of the Jackson library - it was written to work with
	 * version 2.2.0 and has not been tested with any other version.
	 *
	 * @return the customised TypeResolverBuilder for use in an OSGi environment
	 */
	private TypeResolverBuilder<?> createOSGiTypeIdResolver() {
		TypeResolverBuilder<?> typer = new OSGiTypeResolverBuilder();
		typer = typer.init(JsonTypeInfo.Id.CUSTOM, null);
		typer = typer.inclusion(JsonTypeInfo.As.PROPERTY);
		typer = typer.typeProperty(TYPE_INFO_FIELD_NAME);
		return typer;
	}

	/**
	 * A TypeResolverBuilder for use in an OSGi environment.
	 */
	private class OSGiTypeResolverBuilder extends DefaultTypeResolverBuilder {
		private static final long serialVersionUID = 1L;

		public OSGiTypeResolverBuilder() {
			this(null);
		}

		public OSGiTypeResolverBuilder(DefaultTyping typing) {
			super(typing);
		}

		// Override StdTypeResolverBuilder#idResolver() to return our custom BundleAndClassNameIdResolver
		// (We need this override, rather than just providing a custom resolver in StdTypeResolverBuilder#init(), because
		//  the default implementation does not normally pass the base type to the custom resolver but we need it.)
		@Override
		protected TypeIdResolver idResolver(MapperConfig<?> config,
				JavaType baseType, Collection<NamedType> subtypes,
				boolean forSer, boolean forDeser) {
			return new BundleAndClassNameIdResolver(baseType, config.getTypeFactory(), bundleProvider);
		}

		// Override DefaultTypeResolverBuilder#useForType() to add type information to all except primitive and final
		// core Java types
		@Override
		public boolean useForType(JavaType type) {
			while (type.isArrayType()) {
				type = type.getContentType();
			}
			boolean isNotPrimitive = !type.isPrimitive();
			boolean isFinal = type.isFinal();
			boolean isCoreJavaClass = type.getRawClass().getName().startsWith("java.");
			boolean isNotFinalCoreJavaClass = !(isFinal && isCoreJavaClass);
			return isNotPrimitive && isNotFinalCoreJavaClass;
		}
	}

	private final ObjectMapper createNonOsgiMapper() {

		ObjectMapper mapper = new ObjectMapper();

		// Use custom serialization for IPosition objects
		// (Otherwise all IPosition subclasses will need to become simple beans, i.e. no-arg constructors with getters
		// and setters for all fields. MapPosition.getNames() caused problems because it just returns keys from the map
		// and has no corresponding setter.)
		SimpleModule module = new SimpleModule();
		try { // Extension points might still work
			createModuleExtensions(module);
		} catch (Exception ne) {
			// Ignored, we allow the non-osgi mapper to continue without extension points.
		}
		mapper.registerModule(module);

		// Be careful adjusting these settings - changing them will probably cause various unit tests to fail which
		// check the exact contents of the serialized JSON string
		mapper.setSerializationInclusion(Include.NON_NULL);
		//mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper;
	}
}
