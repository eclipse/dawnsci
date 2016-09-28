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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;
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
import org.eclipse.dawnsci.json.internal.MarshallerServiceClassRegistry;
import org.eclipse.dawnsci.json.internal.MarshallerServiceClassRegistry.ClassRegistryDuplicateIdException;
import org.eclipse.dawnsci.json.internal.ROIClassRegistry;
import org.eclipse.dawnsci.json.internal.RegisteredClassIdResolver;
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
import com.fasterxml.jackson.databind.DeserializationFeature;
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
 * This implementation adds class_id type information to encoded JSON strings and will use it when deserializing.
 * <p>
 * Type information is typically obtained from the attribute definition of the class in which it is defined,
 * or the class passed to the {@link #marshal(Object anyObject, boolean useRegisteredClassTyping)}.
 * <p>
 * This is not useful when working with interface, abstract base class or Object definitions, as it could be a number of
 * different classes. In these situations, the key "@class_id" is used to indicate a string for class identification.
 *
 * @author Colin Palmer
 * @author Martin Gaughran
 */
public class MarshallerService implements IMarshallerService {

	private static final String TYPE_INFO_FIELD_NAME = "@class_id";

	private static final Logger logger = LoggerFactory.getLogger(MarshallerService.class);

	private List<IClassRegistry> extra_registries;
	private ObjectMapper registeredClassMapper;
	private ObjectMapper standardMapper;
	private ObjectMapper oldMapper;

	private List<IMarshaller> marshallers;

	private boolean platformIsRunning;

	static {
		System.out.println("Started " + IMarshallerService.class.getSimpleName());
	}

	/**
	 * Default public constructor - for testing purposes only! Otherwise use OSGi to get the service.
	 */
	public MarshallerService() {
		this(null, null);
	}

	/**
	 * Constructor for testing to allow IClassRegistry('s) to be injected.
	 *
	 * @param extra_registries
	 */
	public MarshallerService(IClassRegistry... extra_registries) {
		this(Arrays.asList(extra_registries), null);
	}

	/**
	 * Constructor for testing to allow IMarshaller(s) to be injected.
	 *
	 * @param marshallers
	 */
	public MarshallerService(IMarshaller... marshallers) {
		this(null, Arrays.asList(marshallers));
	}

	/**
	 * Constructor for testing to allow IMarshaller(s) or IClassRegistry('s) to be injected.
	 *
	 * @param extra_registries
	 * @param marshallers
	 */
	public MarshallerService(List<IClassRegistry> extra_registries, List<IMarshaller> marshallers) {
		platformIsRunning = Platform.isRunning();

		if (marshallers!=null) this.marshallers = Collections.unmodifiableList(marshallers);

		this.extra_registries = new ArrayList<IClassRegistry>();
		this.extra_registries.add(new ROIClassRegistry());
		if (extra_registries!=null) this.extra_registries.addAll(extra_registries);

		this.extra_registries = Collections.unmodifiableList(this.extra_registries);
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
		// Use registered class typing by default, as it is only an issue with out-of-date software.
		return marshal(anyObject, true);
	}

	@Override
	public String marshal(Object anyObject, boolean useRegisteredClassTyping)  throws Exception {
		String json;
		if (useRegisteredClassTyping) {
			if (registeredClassMapper==null) registeredClassMapper = createRegisteredClassMapper();
			json = registeredClassMapper.writeValueAsString(anyObject);
		} else {
			if (standardMapper==null) standardMapper = createJacksonMapper();
			json = standardMapper.writeValueAsString(anyObject);
		}

		return json;
	}

	private ObjectMapper createRegisteredClassMapper() throws InstantiationException, IllegalAccessException, ClassRegistryDuplicateIdException, CoreException {
		ObjectMapper mapper = createJacksonMapper();
		mapper.setDefaultTyping(createRegisteredTypeIdResolver());
		return mapper;
	}

	/**
	 * Deserialize the given JSON string as an instance of the given class
	 * <p>
	 * This method will try to find the correct classes for deserialization from the class id, if type not
	 * given.
	 */
	@Override
	public <U> U unmarshal(String string, Class<U> beanClass) throws Exception {
		try {
			if (registeredClassMapper == null) registeredClassMapper = createRegisteredClassMapper();
			if (beanClass != null) {
				return registeredClassMapper.readValue(string, beanClass);
			}
			// If bean class is not supplied, try using Object
			@SuppressWarnings("unchecked")
			U result = (U) registeredClassMapper.readValue(string, Object.class);
			return result;
		} catch (JsonMappingException | IllegalArgumentException ex) {
			// Check if this is due to missing type info. This can appear in two ways: the type info field can be
			// missing from an object, in which case JsonMappingException is thrown; or the first element of an array
			// might be wrongly interpreted as a class name, in which case we get a ClassNotFoundException
			if ((ex instanceof JsonMappingException && ex.getMessage().contains(TYPE_INFO_FIELD_NAME))
					|| ex instanceof IllegalArgumentException && ex.getCause() instanceof ClassNotFoundException) {
				// TODO @Martin: Determine if this gets triggered during any unit tests. If it does get used, it
				// shouldn't be! Possibly replace with standardMapper, as this old mapper is never used for encoding
				// anyway.

				// Possibly no class information in the JSON - fall back to old mapper in case JSON has come
				// from an older version
				try {
					if (oldMapper == null) oldMapper = createOldMapper();
					return oldMapper.readValue(string, beanClass);
				} catch (Exception withoutTypeException) {
					logger.error("Could not deserialize string assuming no type info present: {}", string, withoutTypeException);
				}
			}
			// Log and re-throw the original exception since this is more likely to be useful to clients
			logger.error("Could not deserialize string assuming type info present: {}", string, ex);
			throw ex;
		}
	}

	private final ObjectMapper createJacksonMapper() throws InstantiationException, IllegalAccessException, CoreException {

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
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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

	private void createModuleExtensions(SimpleModule module) throws InstantiationException, IllegalAccessException, CoreException {
        List<IMarshaller> ms = new ArrayList<>(7);

        ms.addAll(getAvailableMarshallerExtensions());

        if (marshallers!=null && !marshallers.isEmpty()) ms.addAll(marshallers);

        applyMarshallersToModule(module, ms);
	}

	private List<IMarshaller> getAvailableMarshallerExtensions() throws CoreException {
        List<IMarshaller> marshallers = new ArrayList<>(7);

		if (!platformIsRunning) return marshallers;

        IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.analysis.api.marshaller");

        for (IConfigurationElement e : elements) {
			final IMarshaller marshaller = (IMarshaller) e.createExecutableExtension("class");
			marshallers.add(marshaller);
        }

		return marshallers;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void applyMarshallersToModule(SimpleModule module, List<IMarshaller> marshallers) throws InstantiationException, IllegalAccessException {
		for (IMarshaller marshaller : marshallers) {
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
	 * Create a TypeResolverBuilder which will add class id information to JSON-serialized objects to
	 * allow the correct classes to be loaded during deserialization.
	 * <p>
	 * Any IMarshaller-provided serializers / deserializers take precedence over this class identification info.
	 * <p>
	 * NOTE: this strongly relies on the exact implementation of the Jackson library - it was written to work with
	 * version 2.2.0 and has not been tested with any other version.
	 *
	 * @return the customised TypeResolverBuilder
	 * @throws ClassRegistryDuplicateIdException
	 * @throws CoreException
	 */
	private TypeResolverBuilder<?> createRegisteredTypeIdResolver() throws ClassRegistryDuplicateIdException, CoreException {
		IClassRegistry registry = new MarshallerServiceClassRegistry(extra_registries);
		TypeResolverBuilder<?> typer = new RegisteredTypeResolverBuilder(registry);
		typer = typer.init(JsonTypeInfo.Id.CUSTOM, null);
		typer = typer.inclusion(JsonTypeInfo.As.PROPERTY);
		typer = typer.typeProperty(TYPE_INFO_FIELD_NAME);
		return typer;
	}

	/**
	 * A TypeResolverBuilder for use with registered classes.
	 */
	private class RegisteredTypeResolverBuilder extends DefaultTypeResolverBuilder {
		private static final long serialVersionUID = 1L;
		private IClassRegistry registry;

		public RegisteredTypeResolverBuilder(IClassRegistry registry) {
			this(null, registry);
		}

		public RegisteredTypeResolverBuilder(DefaultTyping typing, IClassRegistry registry) {
			super(typing);
			this.registry = registry;
		}

		// Override StdTypeResolverBuilder#idResolver() to return our custom RegisteredClassIdResolver
		// (We need this override, rather than just providing a custom resolver in StdTypeResolverBuilder#init(), because
		//  the default implementation does not normally pass the base type to the custom resolver but we need it.)
		@Override
		protected TypeIdResolver idResolver(MapperConfig<?> config,
		JavaType baseType, Collection<NamedType> subtypes,
		boolean forSer, boolean forDeser) {
			return new RegisteredClassIdResolver(baseType, config.getTypeFactory(), registry);
		}

		// Override DefaultTypeResolverBuilder#useForType() to add type information only to those required.
		@Override
		public boolean useForType(JavaType type) {
			Class<?> clazz = type.getRawClass();
			// Note: This does not work well with generics defined at or above the same scope as the call
			// to marshal or unmarshal. As a result, only use such generics there when dealing with a primitive
			// type or registered class, as these will cope with the idResolver.

			// We can lookup the class in the registry, for marshalling and unmarshalling.
			Boolean registryHasClass = registry.isClass(clazz);

			// We only ever declare as object if we intend to use one of our own classes (or a primitive).
			Boolean isObject = (Object.class.equals(clazz));

			// Also include abstract classes and interfaces as these are always defined with a type id. This
			// is not the case for container types, however, so these are excluded.
			Boolean isAbstract = type.isAbstract();
			Boolean isInterface = type.isInterface();
			Boolean isNotContainer = !type.isContainerType();

			// Primitive types are considered abstract, so exclude these as well.
			Boolean isNotPrimitive = !type.isPrimitive();

			return registryHasClass || ((isObject || isAbstract || isInterface) && isNotContainer && isNotPrimitive);
		}
	}

	private final ObjectMapper createOldMapper() {

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
