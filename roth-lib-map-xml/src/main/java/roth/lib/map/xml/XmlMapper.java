package roth.lib.map.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.annotation.Property;
import roth.lib.map.Deserializer;
import roth.lib.map.PropertyField;
import roth.lib.map.SerialMapper;
import roth.lib.map.Serializer;
import roth.lib.map.mapper.PropertyMapper;
import roth.lib.map.util.MapperUtil;
import roth.lib.map.xml.annotation.XmlAttribute;
import roth.lib.map.xml.annotation.XmlAttributes;
import roth.lib.map.xml.annotation.XmlElements;
import roth.lib.map.xml.mapper.XmlAttributeMapper;
import roth.lib.map.xml.mapper.XmlAttributesMapper;
import roth.lib.map.xml.mapper.XmlElementsMapper;

public class XmlMapper extends SerialMapper<XmlConfig>
{
	protected static final String XML_HEADER					= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	protected static final String ESCAPED_QUOTE					= "quot";
	protected static final String ESCAPED_SINGLE_QUOTE			= "apos";
	protected static final String ESCAPED_LEFT_ANGLE_BRACKET	= "lt";
	protected static final String ESCAPED_RIGHT_ANGLE_BRACKET	= "gt";
	protected static final String ESCAPED_AMPERSAND				= "amp";
	
	protected static XmlMapper instance;
	
	protected LinkedList<XmlElementsMapper<? extends Annotation>> xmlElementsMappers = new LinkedList<XmlElementsMapper<? extends Annotation>>();
	protected LinkedList<XmlAttributeMapper<? extends Annotation>> xmlAttributeMappers = new LinkedList<XmlAttributeMapper<? extends Annotation>>();
	protected LinkedList<XmlAttributesMapper<? extends Annotation>> xmlAttributesMappers = new LinkedList<XmlAttributesMapper<? extends Annotation>>();
	protected LinkedHashMap<Type, XmlAttributeFields> xmlAttributeFieldsMap = new LinkedHashMap<Type, XmlAttributeFields>();
	
	public XmlMapper()
	{
		super();
		propertyMappers.add(new PropertyMapper<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Field field, Property property)
			{
				if(property != null && property.xml())
				{
					if(isValid(property.xmlName()))
					{
						return property.xmlName();
					}
					else if(isValid(property.name()))
					{
						return property.name();
					}
				}
				return null;
			}
			
			@Override
			public boolean isEntityName(Field field, Property property)
			{
				return property != null && property.entityName();
			}
		});
		xmlElementsMappers.add(new XmlElementsMapper<XmlElements>(XmlElements.class)
		{
			@Override
			public String getXmlElementsName(Field field, XmlElements xmlElements)
			{
				if(xmlElements != null)
				{
					if(isValid(xmlElements.name()))
					{
						return xmlElements.name();
					}
				}
				return null;
			}
		});
		xmlAttributeMappers.add(new XmlAttributeMapper<XmlAttribute>(XmlAttribute.class)
		{
			@Override
			public String getXmlAttributeName(Field field, XmlAttribute xmlAttribute)
			{
				if(xmlAttribute != null)
				{
					if(isValid(xmlAttribute.name()))
					{
						return xmlAttribute.name();
					}
				}
				return null;
			}
		});
		xmlAttributesMappers.add(new XmlAttributesMapper<XmlAttributes>(XmlAttributes.class){});
	}
	
	public static XmlMapper get()
	{
		if(instance == null)
		{
			instance = new XmlMapper();
		}
		return instance;
	}
	
	public static void set(XmlMapper newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public XmlConfig defaultConfig()
	{
		return XmlConfig.get();
	}
	
	public String getXmlElementsName(Field field)
	{
		for(XmlElementsMapper<? extends Annotation> xmlElementsMapper : xmlElementsMappers)
		{
			String xmlElementsName = xmlElementsMapper.getXmlElementsNameFromField(field);
			if(xmlElementsName != null)
			{
				return xmlElementsName;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getXmlAttributeMap(Object object)
	{
		LinkedHashMap<String, String> xmlAttributeMap = new LinkedHashMap<String, String>();
		if(object != null)
		{
			Class<?> klass = object.getClass();
			XmlAttributeFields xmlAttributeFields = getXmlAttributeFields(klass);
			if(xmlAttributeFields != null)
			{
				for(Entry<String, Field> xmlAttributeFieldEntry : xmlAttributeFields.getXmlAttributeFieldMap().entrySet())
				{
					String name = xmlAttributeFieldEntry.getKey();
					Field field = xmlAttributeFieldEntry.getValue();
					try
					{
						Object fieldObject = field.get(object);
						if(fieldObject != null)
						{
							xmlAttributeMap.put(name, fieldObject.toString());
						}
					}
					catch(Exception e)
					{
						
					}
				}
				for(Field field : xmlAttributeFields.getXmlAttributesFields())
				{
					try
					{
						Object fieldObject = field.get(object);
						if(fieldObject instanceof Map)
						{
							xmlAttributeMap.putAll((Map<String, String>) fieldObject);
						}
					}
					catch(Exception e)
					{
						
					}
				}
			}
		}
		return xmlAttributeMap;
	}
	
	public XmlAttributeFields getXmlAttributeFields(Type type)
	{
		XmlAttributeFields xmlAttributeFields = null;
		xmlAttributeFields = xmlAttributeFieldsMap.get(type);
		if(xmlAttributeFields == null)
		{
			xmlAttributeFields = new XmlAttributeFields();
			for(Field field : getFields(type))
			{
				String name = getXmlAttributeName(field);
				if(name != null)
				{
					xmlAttributeFields.addXmlAttributeField(name, field);
				}
				else if(isXmlAttributes(field) && isMap(field.getType()))
				{
					xmlAttributeFields.addXmlAttributesField(field);
				}
			}
			xmlAttributeFieldsMap.put(type, xmlAttributeFields);
		}
		return xmlAttributeFields;
	}
	
	public String getXmlAttributeName(Field field)
	{
		for(XmlAttributeMapper<? extends Annotation> xmlAttributeMapper : xmlAttributeMappers)
		{
			String xmlAttributeName = xmlAttributeMapper.getXmlAttributeNameFromField(field);
			if(xmlAttributeName != null)
			{
				return xmlAttributeName;
			}
		}
		return null;
	}
	
	public boolean isXmlAttributes(Field field)
	{
		for(XmlAttributesMapper<? extends Annotation> xmlAttributesMapper : xmlAttributesMappers)
		{
			if(xmlAttributesMapper.hasXmlAttributes(field))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void serialize(Object object, Writer writer, XmlConfig config)
	{
		if(object == null) throw new IllegalArgumentException("Object cannot be null");
		try
		{
			writer.write(XML_HEADER);
			writeNewLine(writer, config, 0);
			String rootName = getEntityName(object.getClass());
			writeOpenTag(writer, config, rootName, getXmlAttributeMap(object));
			writeEntity(writer, config, object, 1);
			writeNewLine(writer, config, 0);
			writeCloseTag(writer, config, rootName);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new XmlException(e);
		}
	}
	
	@Override
	public void serialize(Map<String, ?> map, Writer writer, XmlConfig config)
	{
		serialize(map, "root", writer, config);
	}
	
	public void serialize(Map<String, ?> map, String rootName, Writer writer, XmlConfig config)
	{
		if(map == null) throw new IllegalArgumentException("Object cannot be null");
		try
		{
			writer.write(XML_HEADER);
			writeNewLine(writer, config, 0);
			writeOpenTag(writer, config, rootName);
			writeMap(writer, config, map, 1);
			writeNewLine(writer, config, 0);
			writeCloseTag(writer, config, rootName);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new XmlException(e);
		}
	}
	
	protected void writeEntity(Writer writer, XmlConfig config, Object entity, final int tabs) throws IOException
	{
		for(PropertyField propertyField : getPropertyFields(entity.getClass()))
		{
			Field field = propertyField.getField();
			String name = propertyField.getPropertyName();
			Object value = getPropertyObject(field, entity);
			if(value != null && propertyField.isEntityName())
			{
				name = getEntityName(value.getClass());
			}
			writeProperty(writer, config, name, value, field, tabs);
		}
	}
	
	protected void writeProperty(Writer writer, XmlConfig config, String name, Object value, Field field, final int tabs) throws IOException
	{
		if(name != null && (value != null || config.isSerializeNulls()))
		{
			if(value == null)
			{
				writeNewLine(writer, config, tabs);
				writeOpenTag(writer, config, name);
				writer.write(BLANK);
				writeCloseTag(writer, config, name);
			}
			else if(isEntity(value.getClass()))
			{
				writeNewLine(writer, config, tabs);
				writeOpenTag(writer, config, name, getXmlAttributeMap(value));
				writeEntity(writer, config, value, tabs + 1);
				writeNewLine(writer, config, tabs);
				writeCloseTag(writer, config, name);
			}
			else if(isArray(value.getClass()) || isCollection(value.getClass()) && field != null)
			{
				LinkedList<?> values = asCollection(value);
				String xmlElementsName = getXmlElementsName(field);
				if(xmlElementsName != null)
				{
					writeNewLine(writer, config, tabs);
					writeOpenTag(writer, config, name);
					if(!values.isEmpty())
					{
						writeArray(writer, config, xmlElementsName, values, tabs + 1);
						writeNewLine(writer, config, tabs);
					}
					writeCloseTag(writer, config, name);
				}
				else
				{
					if(!values.isEmpty())
					{
						writeArray(writer, config, name, values, tabs);
					}
				}
			}
			else if(isMap(value.getClass()))
			{
				LinkedHashMap<String, ?> valueMap = asMap(value);
				writeNewLine(writer, config, tabs);
				writeOpenTag(writer, config, name);
				if(!valueMap.isEmpty())
				{
					writeMap(writer, config, valueMap, tabs + 1);
					writeNewLine(writer, config, tabs);
				}
				writeCloseTag(writer, config, name);
			}
			else if(isSerializable(value.getClass(), config))
			{
				Serializer<?> serializer = config.getSerializer(value.getClass());
				String serializedValue = serializer.serializeInternal(value);
				if(serializedValue != null)
				{
					writeNewLine(writer, config, tabs);
					writeOpenTag(writer, config, name);
					writeValue(writer, config, serializedValue);
					writeCloseTag(writer, config, name);
				}
				else if(config.isSerializeNulls())
				{
					writeNewLine(writer, config, tabs);
					writeOpenTag(writer, config, name);
					writer.write(BLANK);
					writeCloseTag(writer, config, name);
				}
			}
		}
	}
	
	protected void writeOpenTag(Writer writer, XmlConfig config, String name) throws IOException
	{
		writeOpenTag(writer, config, name, null);
	}
	
	protected void writeOpenTag(Writer writer, XmlConfig config, String name, Map<String, String> attributeMap) throws IOException
	{
		writer.write(LEFT_ANGLE_BRACKET);
		writer.write(name);
		if(attributeMap != null)
		{
			for(Entry<String, String> attributeEntry : attributeMap.entrySet())
			{
				writer.write(SPACE);
				writer.write(attributeEntry.getKey());
				writer.write(EQUAL);
				writer.write(QUOTE);
				writeValue(writer, config, attributeEntry.getValue());
				writer.write(QUOTE);
			}
		}
		writer.write(RIGHT_ANGLE_BRACKET);
	}
	
	protected void writeCloseTag(Writer writer, XmlConfig config, String name) throws IOException
	{
		writer.write(LEFT_ANGLE_BRACKET);
		writer.write(SLASH);
		writer.write(name);
		writer.write(RIGHT_ANGLE_BRACKET);
	}
	
	protected void writeNewLine(Writer writer, XmlConfig config, int tabs) throws IOException
	{
		if(config.isPrettyPrinting())
		{
			writer.write(NEW_LINE);
			for(int i = 0; i < tabs; i++)
			{
				writer.write(TAB);
			}
		}
	}
	
	protected void writeValue(Writer writer, XmlConfig config, String value) throws IOException
	{
		if(value != null)
		{
			StringReader reader = new StringReader(value);
			int b;
			char c;
			while((b = reader.read()) > -1)
			{
				c = (char) b;
				switch(c)
				{
					case QUOTE:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_QUOTE);
						writer.write(SEMI_COLON);
						break;
					}
					case SINGLE_QUOTE:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_SINGLE_QUOTE);
						writer.write(SEMI_COLON);
						break;
					}
					case LEFT_ANGLE_BRACKET:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_LEFT_ANGLE_BRACKET);
						writer.write(SEMI_COLON);
						break;
					}
					case RIGHT_ANGLE_BRACKET:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_RIGHT_ANGLE_BRACKET);
						writer.write(SEMI_COLON);
						break;
					}
					case AMPERSAND:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_AMPERSAND);
						writer.write(SEMI_COLON);
						break;
					}
					default:
					{
						writer.write(c);
						break;
					}
				}
			}
		}
	}
	
	protected void writeArray(Writer writer, XmlConfig config, String name, Collection<?> values, final int tabs) throws IOException
	{
		for(Object value : values)
		{
			writeProperty(writer, config, name, value, null, tabs);
		}
	}
	
	protected void writeMap(Writer writer, XmlConfig config,  Map<String, ?> valueMap, final int tabs) throws IOException
	{
		for(Entry<String, ?> valueEntry : valueMap.entrySet())
		{
			writeProperty(writer, config, valueEntry.getKey(), valueEntry.getValue(), null, tabs);
		}
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type, XmlConfig config)
	{
		T model = null;
		try
		{
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			while((tag = readTag(reader, config)) != null)
			{
				if(tag instanceof DeclarationTag)
				{
					
				}
				else if(tag instanceof CommentTag)
				{
					
				}
				else if(tag instanceof CloseTag)
				{
					break;
				}
				else if(tag instanceof OpenTag)
				{
					model = readEntity(reader, type, (OpenTag) tag, config);
					break;
				}
				readUntil(reader, LEFT_ANGLE_BRACKET);
			}
		}
		catch(Exception e)
		{
			throw new XmlException(e);
		}
		return model;
	}
	
	@Override
	public LinkedHashMap<String, Object> deserialize(Reader reader, XmlConfig config)
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			while((tag = readTag(reader, config)) != null)
			{
				if(tag instanceof DeclarationTag)
				{
					
				}
				else if(tag instanceof CommentTag)
				{
					
				}
				else if(tag instanceof CloseTag)
				{
					break;
				}
				else if(tag instanceof OpenTag)
				{
					map = readMap(reader, String.class, config);
					break;
				}
				readUntil(reader, LEFT_ANGLE_BRACKET);
			}
		}
		catch(Exception e)
		{
			throw new XmlException(e);
		}
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T readEntity(Reader reader, Type type, OpenTag openTag, XmlConfig config) throws Exception
	{
		LinkedHashMap<String, PropertyField> propertyNameFieldMap = getPropertyNameFieldMap(type);
		Class<T> klass = MapperUtil.getClass(type);
		Constructor<T> constructor = klass.getDeclaredConstructor();
		constructor.setAccessible(true);
		T model = constructor.newInstance();
		XmlAttributeFields xmlAttributeFields = getXmlAttributeFields(klass);
		if(xmlAttributeFields != null)
		{
			xmlAttributeFields.setAttributes(model, openTag.getAttributeMap());
		}
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader, config)) != null)
		{
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			else if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				PropertyField propertyField = propertyNameFieldMap.get(fieldOpenTag.getName());
				if(propertyField != null)
				{
					Field field = propertyField.getField();
					Type fieldType = propertyField.getFieldType();
					Class<?> fieldClass = propertyField.getFieldClass();
					if(isEntity(fieldType))
					{
						Object value = readEntity(reader, fieldType, fieldOpenTag, config);
						field.set(model, value);
					}
					else if(isCollection(fieldType))
					{
						String xmlElementsName = getXmlElementsName(field);
						if(xmlElementsName != null)
						{
							Object value = readCollection(reader, fieldType, config);
							field.set(model, value);
						}
						else
						{
							Object value = readCollectionElement(reader, fieldOpenTag, fieldType, config);
							if(value != null)
							{
								Collection collection = (Collection) field.get(model);
								if(collection == null)
								{
									Constructor<?> fieldConstructor = MapperUtil.getClass(fieldType).getDeclaredConstructor();
									fieldConstructor.setAccessible(true);
									collection = (Collection) fieldConstructor.newInstance();
									field.set(model, collection);
								}
								collection.add(value);
							}
						}
					}
					else if(isArray(fieldType))
					{
						Object value = readArray(reader, fieldType, config);
						field.set(model, value);
					}
					else if(isMap(fieldType))
					{
						Object value = readMap(reader, fieldType, config);
						field.set(model, value);
					}
					else if(isSerializable(fieldType, config))
					{
						String value = readEscaped(reader, config, LEFT_ANGLE_BRACKET);
						readTag(reader, config);
						Deserializer<?> deserializer = config.getDeserializer(fieldClass);
						if(deserializer != null)
						{
							field.set(model, deserializer.deserialize(value, fieldClass));
						}
					}
					else
					{
						readUnmapped(reader, fieldOpenTag, config);
					}
				}
				else
				{
					readUnmapped(reader, fieldOpenTag, config);
				}
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return model;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readMap(Reader reader, Type type, XmlConfig config) throws Exception
	{
		Map<String, E> map = null;
		Class<T> klass = MapperUtil.getClass(type);
		Class<E> elementClass = (Class<E>) getElementClass(type);
		if(klass.isAssignableFrom(LinkedHashMap.class))
		{
			map = new LinkedHashMap<String, E>();
		}
		else
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			map = (Map<String, E>) constructor.newInstance();
		}
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader, config)) != null)
		{
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			else if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				if(isEntity(elementClass))
				{
					E value = readEntity(reader, elementClass,  (OpenTag) tag, config);
					if(value != null)
					{
						map.put(fieldOpenTag.getName(), value);
					}
				}
				else if(isCollection(elementClass))
				{
					E value = readCollection(reader, elementClass, config);
					if(value != null)
					{
						map.put(fieldOpenTag.getName(), value);
					}
				}
				else if(isArray(elementClass))
				{
					E value = readArray(reader, elementClass, config);
					if(value != null)
					{
						map.put(fieldOpenTag.getName(), value);
					}
				}
				else if(isMap(elementClass))
				{
					E value = readMap(reader, elementClass, config);
					if(value != null)
					{
						map.put(fieldOpenTag.getName(), value);
					}
				}
				else if(isSerializable(elementClass, config))
				{
					String value = readEscaped(reader, config, LEFT_ANGLE_BRACKET);
					readTag(reader, config);
					Deserializer<E> deserializer = (Deserializer<E>) config.getDeserializer(elementClass);
					if(deserializer != null)
					{
						E deserializedValue = deserializer.deserialize(value, elementClass);
						if(deserializedValue != null)
						{
							map.put(fieldOpenTag.getName(), deserializedValue);
						}
					}
				}
				else
				{
					readUnmapped(reader, fieldOpenTag, config);
				}
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return (T) map;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readArray(Reader reader, Type type, XmlConfig config) throws Exception
	{
		Class<E> elementClass = (Class<E>) getElementClass(type);
		LinkedList<E> collection = readCollection(reader, type, config);
		E[] array = (E[]) Array.newInstance(elementClass, collection.size());
		for(int i = 0; i < collection.size(); i++)
		{
			array[i] = collection.get(i);
		}
		return (T) array;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readCollection(Reader reader, Type type, XmlConfig config) throws Exception
	{
		Collection<E> collection = null;
		Class<T> klass = MapperUtil.getClass(type);
		Class<E> elementClass = (Class<E>) getElementClass(type);
		if(klass.isAssignableFrom(LinkedList.class) || isArray(klass))
		{
			collection = new LinkedList<E>();
		}
		else
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			collection = (Collection<E>) constructor.newInstance();
		}
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader, config)) != null)
		{
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			else if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				if(isEntity(elementClass))
				{
					E value = readEntity(reader, elementClass, fieldOpenTag, config);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isCollection(elementClass))
				{
					E value = readCollection(reader, elementClass, config);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isArray(elementClass))
				{
					E value = readArray(reader, elementClass, config);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isMap(elementClass))
				{
					E value = readMap(reader, elementClass, config);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isSerializable(elementClass, config))
				{
					String value = readEscaped(reader, config, LEFT_ANGLE_BRACKET);
					readTag(reader, config);
					Deserializer<E> deserializer = (Deserializer<E>) config.getDeserializer(elementClass);
					if(deserializer != null)
					{
						E deserializedValue = deserializer.deserialize(value, elementClass);
						if(deserializedValue != null)
						{
							collection.add(deserializedValue);
						}
					}
				}
				else
				{
					readUnmapped(reader, fieldOpenTag, config);
				}
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return (T) collection;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> E readCollectionElement(Reader reader, OpenTag tag, Type type, XmlConfig config) throws Exception
	{
		E element = null;
		Class<E> elementClass = (Class<E>) getElementClass(type);
		if(isEntity(elementClass))
		{
			element = readEntity(reader, elementClass, tag, config);
		}
		else if(isCollection(elementClass))
		{
			element = readCollection(reader, elementClass, config);
		}
		else if(isArray(elementClass))
		{
			element = readArray(reader, elementClass, config);
		}
		else if(isMap(elementClass))
		{
			element = readMap(reader, elementClass, config);
		}
		else if(isSerializable(elementClass, config))
		{
			String value = readEscaped(reader, config, LEFT_ANGLE_BRACKET);
			readTag(reader, config);
			Deserializer<E> deserializer = (Deserializer<E>) config.getDeserializer(elementClass);
			if(deserializer != null)
			{
				element = deserializer.deserialize(value, elementClass);
			}
		}
		else
		{
			readUnmapped(reader, tag, config);
		}
		return element;
	}
	
	protected Tag readTag(Reader reader, XmlConfig config) throws IOException
	{
		Tag tag = null;
		StringBuilder builder = null;
		int b;
		char c;
		char p = 0;
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			if(builder == null)
			{
				switch(c)
				{
					case QUESTION:
					{
						String value = readUntil(reader, "?>");
						tag = new DeclarationTag(value.replaceAll("\\?>$", "").trim());
						break read;
					}
					case EXCLAMATION:
					{
						String value = readUntil(reader, "-->");
						tag = new CommentTag(value.replaceAll("^--", "").replaceAll("-->$", "").trim());
						break read;
					}
					case SLASH:
					{
						String value = readUntil(reader, RIGHT_ANGLE_BRACKET);
						tag = new CloseTag(value.trim());
						break read;
					}
				}
				builder = new StringBuilder();
			}
			switch(c)
			{
				case TAB:
				case NEW_LINE:
				case CARRIAGE_RETURN:
				case FORM_FEED:
				case BACKSPACE:
				{
					break;
				}
				case SPACE:
				{
					String name = builder.toString();
					if(tag == null && !name.isEmpty())
					{
						tag = new OpenTag(name);
						builder.setLength(0);
					}
					break;
				}
				case EQUAL:
				{
					String name = builder.toString();
					if(!name.isEmpty())
					{
						String value = null;
						c = (char) reader.read();
						switch(c)
						{
							case QUOTE:
							{
								value = readEscaped(reader, config, QUOTE);
								break;
							}
							case SINGLE_QUOTE:
							{
								value = readEscaped(reader, config, SINGLE_QUOTE);
								break;
							}
						}
						if(tag != null && value != null && tag instanceof OpenTag)
						{
							((OpenTag) tag).addAttribute(name, value);
						}
						builder.setLength(0);
					}
					break;
				}
				case RIGHT_ANGLE_BRACKET:
				{
					if(tag == null)
					{
						String name = builder.toString();
						if(!name.isEmpty())
						{
							tag = new OpenTag(name);
							builder.setLength(0);
						}
					}
					if(p == SLASH && tag instanceof OpenTag)
					{
						tag = new EmptyTag((OpenTag) tag);
					}
					break read;
				}
				default:
				{
					builder.append(c);
					break;
				}
			}
			p = c;
		}
		return tag;
	}
	
	protected String readEscaped(Reader reader, XmlConfig config, final char until) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int b;
		char c;
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case AMPERSAND:
				{
					c = (char) reader.read();
					switch(c)
					{
						case HASH:
						{
							String code = readUntil(reader, SEMI_COLON);
							try
							{
								builder.append((char) Integer.parseInt(code, 16));
							}
							catch(Exception e)
							{
								
							}
							break;
						}
						default:
						{
							String code = c + readUntil(reader, SEMI_COLON);
							switch(code)
							{
								case ESCAPED_QUOTE:
								{
									builder.append(QUOTE);
									break;
								}
								case ESCAPED_SINGLE_QUOTE:
								{
									builder.append(SINGLE_QUOTE);
									break;
								}
								case ESCAPED_LEFT_ANGLE_BRACKET:
								{
									builder.append(LEFT_ANGLE_BRACKET);
									break;
								}
								case ESCAPED_RIGHT_ANGLE_BRACKET:
								{
									builder.append(RIGHT_ANGLE_BRACKET);
									break;
								}
								case ESCAPED_AMPERSAND:
								{
									builder.append(AMPERSAND);
									break;
								}
							}
							break;
						}
					}
					break;
				}
				default:
				{
					if(c == until)
					{
						break read;
					}
					else
					{
						builder.append(c);
						break;
					}
				}
			}
		}
		return builder.toString();
	}
	
	protected void readUnmapped(Reader reader, OpenTag openTag, XmlConfig config) throws IOException
	{
		int nested = 0;
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader, config)) != null)
		{
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof EmptyTag)
			{
				
			}
			else if(tag instanceof OpenTag)
			{
				nested++;
			}
			else if(tag instanceof CloseTag)
			{
				if(nested <= 0)
				{
					break;
				}
				nested--;
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
	}
	
	@Override
	public String format(Reader reader)
	{
		StringBuilder builder = new StringBuilder();
		XmlConfig config = defaultConfig();
		try
		{
			int tabs = 0;
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			Tag lastTag = null;
			while((tag = readTag(reader, config)) != null)
			{
				if(lastTag == null)
				{
					builder.append(tag);
				}
				else if(tag instanceof OpenTag)
				{
					if(lastTag instanceof OpenTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(++tabs));
					}
					else if(lastTag instanceof EmptyTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CloseTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof DeclarationTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CommentTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					builder.append(tag);
				}
				else if(tag instanceof EmptyTag)
				{
					if(lastTag instanceof OpenTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(++tabs));
					}
					else if(lastTag instanceof EmptyTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CloseTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof DeclarationTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CommentTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					builder.append(tag);
				}
				else if(tag instanceof CloseTag)
				{
					if(lastTag instanceof OpenTag)
					{
						
					}
					else if(lastTag instanceof EmptyTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(--tabs));
					}
					else if(lastTag instanceof CloseTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(--tabs));
					}
					else if(lastTag instanceof DeclarationTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CommentTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					builder.append(tag);
				}
				else if(tag instanceof DeclarationTag)
				{
					builder.append(NEW_LINE);
					builder.append(tabs(tabs));
					builder.append(tag);
				}
				else if(tag instanceof CommentTag)
				{
					builder.append(NEW_LINE);
					builder.append(tabs(tabs));
					builder.append(tag);
				}
				String value = readUntil(reader, LEFT_ANGLE_BRACKET);
				if(value != null)
				{
					builder.append(value.trim());
				}
				lastTag = tag;
			}
		}
		catch(IOException e)
		{
			
		}
		builder.append(NEW_LINE);
		return builder.toString();
	}

	protected String tabs(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < tabs; i++)
		{
			builder.append(TAB);
		}
		return builder.toString();
	}
	/*
	public static void main(String[] args)
	{
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><IsValidRoutingNumberResponse xmlns=\"https://secure.modpay.com/ws/\"><testBefore /><IsValidRoutingNumberResult>true</IsValidRoutingNumberResult><testAfter /></IsValidRoutingNumberResponse></soap:Body></soap:Envelope>";
		//String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><GetCustomerInfoResponse xmlns=\"https://secure.modpay.com/ws/\"><GetCustomerInfoResult><returnCode>1</returnCode><account /><firstName>First</firstName><lastName>Last</lastName><address /><city /><state /><zip /><phone /><fax /><email /><cDate>02/04/2015</cDate></GetCustomerInfoResult></GetCustomerInfoResponse></soap:Body></soap:Envelope>";
		System.out.println(get().format(data));
	}
	*/
	protected static abstract class Tag
	{
		
	}
	
	protected static class OpenTag extends Tag
	{
		protected String name;
		protected LinkedHashMap<String, String> attributeMap = new LinkedHashMap<String, String>();
		
		public OpenTag(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return name;
		}
		
		public LinkedHashMap<String, String> getAttributeMap()
		{
			return attributeMap;
		}
		
		public OpenTag setName(String name)
		{
			this.name = name;
			return this;
		}
		
		public OpenTag addAttribute(String name, String value)
		{
			attributeMap.put(name, value);
			return this;
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append(LEFT_ANGLE_BRACKET);
			builder.append(name);
			for(Entry<String, String> attributeEntry : attributeMap.entrySet())
			{
				builder.append(SPACE);
				builder.append(attributeEntry.getKey());
				builder.append(EQUAL);
				builder.append(QUOTE);
				builder.append(attributeEntry.getValue());
				builder.append(QUOTE);
			}
			builder.append(RIGHT_ANGLE_BRACKET);
			return builder.toString();
		}
		
	}
	protected static class EmptyTag extends Tag
	{
		protected String name;
		protected LinkedHashMap<String, String> attributeMap = new LinkedHashMap<String, String>();

		public EmptyTag(OpenTag openTag)
		{
			this.name = openTag.name;
			this.attributeMap = openTag.attributeMap;
		}
		
		public EmptyTag(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return name;
		}
		
		public LinkedHashMap<String, String> getAttributeMap()
		{
			return attributeMap;
		}
		
		public EmptyTag setName(String name)
		{
			this.name = name;
			return this;
		}
		
		public EmptyTag addAttribute(String name, String value)
		{
			attributeMap.put(name, value);
			return this;
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append(LEFT_ANGLE_BRACKET);
			builder.append(name);
			for(Entry<String, String> attributeEntry : attributeMap.entrySet())
			{
				builder.append(SPACE);
				builder.append(attributeEntry.getKey());
				builder.append(EQUAL);
				builder.append(QUOTE);
				builder.append(attributeEntry.getValue());
				builder.append(QUOTE);
			}
			builder.append(SPACE);
			builder.append(SLASH);
			builder.append(RIGHT_ANGLE_BRACKET);
			return builder.toString();
		}
		
	}
	
	protected static class CloseTag extends Tag
	{
		protected String name;
		
		public CloseTag(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return name;
		}
		
		public CloseTag setName(String name)
		{
			this.name = name;
			return this;
		}
		
		@Override
		public String toString()
		{
			return "</" + name + ">";
		}
		
	}
	
	protected static class DeclarationTag extends Tag
	{
		protected String value;
		
		public DeclarationTag(String value)
		{
			this.value = value;
		}
		
		public String getValue()
		{
			return value;
		}
		
		public DeclarationTag setValue(String value)
		{
			this.value = value;
			return this;
		}
		
		@Override
		public String toString()
		{
			return "<?" + value + "?>";
		}
		
	}
	
	protected static class CommentTag extends Tag
	{
		protected String value;
		
		public CommentTag(String value)
		{
			this.value = value;
		}
		
		public String getValue()
		{
			return value;
		}
		
		public CommentTag setValue(String value)
		{
			this.value = value;
			return this;
		}
		
		@Override
		public String toString()
		{
			return "<!-- " + value + " -->";
		}
		
	}
	
}
